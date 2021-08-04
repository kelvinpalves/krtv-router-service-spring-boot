package com.krtv.router.infra.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListTasksDto {
    private final String id;
    private final String url;
    private final String model;
    private final String createdAt;
    private final String startedAt;
    private final String finishedAt;
}
