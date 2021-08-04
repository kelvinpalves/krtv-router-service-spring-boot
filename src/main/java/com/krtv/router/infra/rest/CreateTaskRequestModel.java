/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.rest;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author kelvin
 */

@Data
@NoArgsConstructor
public class CreateTaskRequestModel {
    
    private final List<CreateTaskDto> routers = new ArrayList<>();
    
}
