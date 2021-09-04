/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.domain;

import com.krtv.router.infra.rest.CreateTaskDto;

/**
 *
 * @author kelvin
 */

public abstract class RouterTaskMapper {

    public static RouterTasks toRouterTask(CreateTaskDto dto) {
        return RouterTasks.builder()
                .ip(dto.getIp())
                .port(dto.getPort())
                .protocol(Protocol.fromString(dto.getProtocol()))
                .context(dto.getContextPath())
                .data(dto.getData())
                .model(RouterModel.fromString(dto.getModel()))
                .build();
    }
}
