/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.repository;

import com.krtv.router.domain.RouterStatus;
import com.krtv.router.infra.exception.NoRoutersWaitingException;
import com.krtv.router.infra.rest.ListOpenTasksDto;
import com.krtv.router.infra.rest.ListTasksDto;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author kelvin
 */

@Component
@RequiredArgsConstructor
@Log4j2
public class JpaRouterTask implements RouterTaskDsGateway {
    
    private final JpaRouterTaskRepository routerTaskRepository;
    private final JpaRouterTaskFieldRepository routerTaskFieldRepository;
    private final CommonMapper commonMapper;

    @Value("${config.maximumNumberTries}")
    private Integer maximumNumberTries;

    @Override
    @Transactional
    public void create(CreateRouterTaskDto dto) {
        RouterTaskDataMapper entity = commonMapper.createRouterTaskDtoToRouterTaskDataMapper(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCurrentStatus(RouterStatus.CREATED.toString());
        RouterTaskDataMapper finalEntity = routerTaskRepository.save(entity);;
        dto.getData().forEach((key, value) -> createFieldToTask(key, value, finalEntity));
    }

    @Override
    public void setStartedTime(String router) {
        Optional<RouterTaskDataMapper> routerTaskDataMapper = routerTaskRepository.findById(router);

        if (routerTaskDataMapper.isPresent()) {
            RouterTaskDataMapper model = routerTaskDataMapper.get();
            model.setStartedAt(LocalDateTime.now());
            routerTaskRepository.saveAndFlush(model);
        }
    }

    @Override
    public void updateStatus(String router, RouterStatus status) {
        Optional<RouterTaskDataMapper> routerTaskDataMapper = routerTaskRepository.findById(router);

        if (routerTaskDataMapper.isPresent()) {
            RouterTaskDataMapper model = routerTaskDataMapper.get();
            model.setCurrentStatus(status.toString());
            model.setFinishedAt(LocalDateTime.now());
            routerTaskRepository.saveAndFlush(model);
        }
    }

    @Override
    public void updateNumberOfExecutedTries(String router) {
        Optional<RouterTaskDataMapper> routerTaskDataMapper = routerTaskRepository.findById(router);

        if (routerTaskDataMapper.isPresent()) {
            RouterTaskDataMapper model = routerTaskDataMapper.get();
            model.setTries(model.getTries() + 1);

            if (model.numberOfTriesExceededMaximumAllowed(maximumNumberTries)) {
                model.setExpired(Boolean.TRUE);
            }

            routerTaskRepository.saveAndFlush(model);
        }
    }

    @Override
    public Page<ListTasksDto> listAll(Specification<RouterTaskDataMapper> specification, Pageable pageable) {
        Page<ListTasksDto> list = routerTaskRepository.findAll(specification, pageable).map(ListTasksDto::converterMapperToDto);

        list.getContent().stream().forEach(task -> {
            Map<String, String> data = getFieldsFromRouter(task.getId());
            task.getData().putAll(data);
        });

        return list;
    }

    @Override
    public Page<ListOpenTasksDto> listOpenTasks(Pageable pageable) {
        return routerTaskRepository.findAllByStartedAtIsNull(pageable).map(ListOpenTasksDto::converterMapperToDto);
    }

    @Override
    public UpdateRouterDto getNextRouterWaitingForUpdate() throws Exception {
        Optional<RouterTaskDataMapper> optionalRouterTaskDataMapper = routerTaskRepository
                .findAll(Sort.by("createdAt"))
                .stream()
                .filter(this::canExecute)
                .findFirst();

        if (!optionalRouterTaskDataMapper.isPresent()) {
            throw new NoRoutersWaitingException("There is no routers waiting for update.");
        }

        return UpdateRouterDto.create(optionalRouterTaskDataMapper.get());
    }

    private boolean canExecute(RouterTaskDataMapper router) {
        boolean statusValid = router.getCurrentStatus().equals(RouterStatus.CREATED.toString())
                || router.getCurrentStatus().equals(RouterStatus.ERROR.toString());

        boolean numberOfTriesIsNotExceeded = !router.numberOfTriesExceededMaximumAllowed(maximumNumberTries);
        boolean notExpired = !router.getExpired();

        return statusValid
                && numberOfTriesIsNotExceeded
                && notExpired;
    }

    @Override
    public Map<String, String> getFieldsFromRouter(String router) {
        List<RouterTaskFieldDataMapper> list = routerTaskFieldRepository.findAllByRouterId(router);

        return list.stream()
                .collect(
                    Collectors.toMap(
                        RouterTaskFieldDataMapper::getKey,
                        RouterTaskFieldDataMapper::getValue
                    )
                );
    }

    @Override
    public void setTaskToExpired(String router) {
        Optional<RouterTaskDataMapper> routerTaskDataMapper = routerTaskRepository.findById(router);

        if (routerTaskDataMapper.isPresent()) {
            RouterTaskDataMapper model = routerTaskDataMapper.get();
            model.setExpired(Boolean.TRUE);
            routerTaskRepository.saveAndFlush(model);
        }
    }

    private void createFieldToTask(String key, String value, RouterTaskDataMapper router) {
        RouterTaskFieldDataMapper field = new RouterTaskFieldDataMapper(router, key, value);
        routerTaskFieldRepository.save(field);
    }
    
}
