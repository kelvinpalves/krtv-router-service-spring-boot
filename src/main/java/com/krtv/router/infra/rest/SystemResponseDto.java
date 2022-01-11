package com.krtv.router.infra.rest;

import lombok.Data;

import java.util.List;

@Data
public class SystemResponseDto {

    private String version;
    private List<String> models;

}
