/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.repository;

import com.krtv.router.infra.rest.ListTasksDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

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
        System.out.println(routerTaskRepository.findAll(pageable).toList());

        return null;
    }

    private void createFieldToTask(String key, String value, RouterTaskDataMapper router) {
        RouterTaskFieldDataMapper field = new RouterTaskFieldDataMapper(router, key, value);
        routerTaskFieldRepository.save(field);
    }
    
}
