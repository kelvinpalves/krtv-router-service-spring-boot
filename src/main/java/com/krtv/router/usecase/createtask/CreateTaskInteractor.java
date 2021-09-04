package com.krtv.router.usecase.createtask;


import com.krtv.router.domain.RouterTasks;
import com.krtv.router.domain.RouterTaskMapper;
import com.krtv.router.infra.repository.CreateRouterTaskDto;
import com.krtv.router.infra.repository.RouterTaskDsGateway;
import com.krtv.router.infra.rest.CreateTaskDto;
import com.krtv.router.infra.rest.CreateTaskRequestModel;
import com.krtv.router.infra.rest.CreateTaskResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kelvin
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class CreateTaskInteractor implements CreateTaskInputBoundary {

    private final RouterTaskDsGateway routerTaskDsGateway;

    @Override
    public CreateTaskResponseModel startProcess(CreateTaskRequestModel requestModel) {
        requestModel.getRouters().stream().forEach(this::addRouterToWaitList);
        return CreateTaskResponseModel.create("Success.");
    }
    
    private void addRouterToWaitList(CreateTaskDto router) {
        try {
            RouterTasks routerTask = RouterTaskMapper.toRouterTask(router);
            log.info("Adding {} to wait list.", routerTask);

            CreateRouterTaskDto createRouterTaskDto = CreateRouterTaskDto.builder()
                    .protocol(routerTask.getProtocol())
                    .ip(routerTask.getIp())
                    .port(routerTask.getPort())
                    .context(routerTask.getContext())
                    .model(routerTask.getModel())
                    .username(router.getUsername())
                    .password(router.getPassword())
                    .data(routerTask.getData())
                    .build();

            log.info("Create Router Task DTO: {}", createRouterTaskDto);
            routerTaskDsGateway.create(createRouterTaskDto);
        } catch (Exception ex) {
            log.error("Error saving router. {}", ex.getMessage(), ex);
        }
    }
    
}

