/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.usecase.createtask;

import com.krtv.router.infra.rest.CreateTaskResponseModel;
import com.krtv.router.infra.rest.CreateTaskRequestModel;
import org.springframework.stereotype.Component;

/**
 *
 * @author kelvin
 */

@Component
public interface CreateTaskInputBoundary {
    CreateTaskResponseModel startProcess(CreateTaskRequestModel requestModel);
}
