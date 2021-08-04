package com.krtv.router.infra.repository;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Builder
@Data
public class CreateRouterTaskDto {
    
    private final String ip;
    private final Integer port;
    private final String protocol;
    private final String context;
    private final String model;
    private final Map<String, String> data;

}
