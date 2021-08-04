/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Builder
@Data
public class CreateTaskResponseModel {
    String creationTime;
    String message;
    
    public static CreateTaskResponseModel create(String message) {
        LocalDateTime now = LocalDateTime.now();
                
        String creationTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        return CreateTaskResponseModel.builder()
                .message(message)
                .creationTime(creationTime)
                .build();
    }
}
