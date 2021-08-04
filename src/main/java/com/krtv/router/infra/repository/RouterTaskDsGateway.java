/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.repository;

import com.krtv.router.infra.rest.ListTasksDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 *
 * @author kelvin
 */

@Component
public interface RouterTaskDsGateway {
    
    void create(CreateRouterTaskDto dto);

    Page<ListTasksDto> listAll(Pageable pageable);
    
}