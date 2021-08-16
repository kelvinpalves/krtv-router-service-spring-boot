/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.rest;

import com.krtv.router.usecase.createtask.CreateTaskInputBoundary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author kelvin
 */
@RestController
@RequestMapping("router-task")
@Log4j2
@RequiredArgsConstructor
public class CreateTaskController {
    
    private final CreateTaskInputBoundary updateRouterInput;

    @Operation(description = "Para cada roteador enviado na lista como entrada," +
            "uma tarefa será criada no sistema. Um serviço vai buscar as tarefas em aberto e executá-las uma após a outra. " +
            "Conforme as tarefas forem executadas, informações de início de execução e fim são gravadas para posterior consulta.", summary = "Agendar atualização para lista de roteadores", tags = "Atualização Roteador")
    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarefa agendada com sucesso."),
        @ApiResponse(responseCode = "406", description = "Nenhuma tarefa foi criada, não há roteadores informados.")})
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTaskResponseModel startProcess(@Valid @RequestBody CreateTaskRequestModel dto) {
        log.info("Process created!");

        if (dto.getRouters().isEmpty()) {
            log.error("No routers to update");
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No routers to update.");
        }
        
        return updateRouterInput.startProcess(dto);
    }

}
