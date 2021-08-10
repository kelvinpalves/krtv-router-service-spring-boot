/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.repository;

import com.krtv.router.infra.rest.ListOpenTasksDto;
import com.krtv.router.infra.rest.ListTasksDto;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    @Transactional
    public void create(CreateRouterTaskDto dto) {
        RouterTaskDataMapper entity = commonMapper.createRouterTaskDtoToRouterTaskDataMapper(dto);
        entity.setCreatedAt(LocalDateTime.now());
        RouterTaskDataMapper finalEntity = routerTaskRepository.save(entity);;
        dto.getData().forEach((key, value) -> createFieldToTask(key, value, finalEntity));
    }

    @Override
    public Page<ListTasksDto> listAll(Pageable pageable) {
        return routerTaskRepository.findAll(pageable).map(ListTasksDto::converterMapperToDto);
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
                .filter(router -> router.getStartedAt() == null)
                .findFirst();

        if (!optionalRouterTaskDataMapper.isPresent()) {
            throw new Exception("There is no routers waiting for update.");
        }

        return UpdateRouterDto.create(optionalRouterTaskDataMapper.get());
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

    private void createFieldToTask(String key, String value, RouterTaskDataMapper router) {
        RouterTaskFieldDataMapper field = new RouterTaskFieldDataMapper(router, key, value);
        routerTaskFieldRepository.save(field);
    }
    
}
