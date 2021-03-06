/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Data
@Builder
public class CreateTaskDto {
    @NotEmpty (message = "IP is required!")
    @Schema (example = "192.168.0.1")
    private final String ip;
    
    @NotEmpty (message = "Port is required!")
    @Size(min = 0, max = 65536)
    @Schema (example = "8080")
    private final Integer port;
    
    @NotEmpty (message = "Protocol is required!")
    @Schema (example = "HTTP|HTTPS")
    private final String protocol;
    
    @Schema (example = "/admin")
    private final String contextPath;

    @NotEmpty(message = "username is required!")
    private final String username;

    @NotEmpty(message = "password is required!")
    private final String password;
    
    @NotNull (message = "Model is required!")
    @Schema (example = "GREATEK_MESH_1200_AC|ZTEH199A|TENDA")
    private final String model;
    
    private final Map<String, String> data;
}
