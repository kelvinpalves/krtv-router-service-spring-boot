/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.repository;

import com.krtv.router.infra.rest.ListOpenTasksDto;
import com.krtv.router.infra.rest.ListTasksDto;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * @author kelvin
 */

@Component
public interface RouterTaskDsGateway {
    
    void create(CreateRouterTaskDto dto);

    Page<ListTasksDto> listAll(Pageable pageable);

    Page<ListOpenTasksDto> listOpenTasks(Pageable pageable);

    UpdateRouterDto getNextRouterWaitingForUpdate() throws Exception;

    Map<String, String> getFieldsFromRouter(String router);

}
