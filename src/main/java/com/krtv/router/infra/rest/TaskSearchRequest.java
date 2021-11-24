package com.krtv.router.infra.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchRequest {
    
    private Integer port;
    private String ip;
    
}
