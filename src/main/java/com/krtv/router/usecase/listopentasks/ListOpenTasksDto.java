package com.krtv.router.usecase.listopentasks;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListOpenTasksDto {
    private final String id;
    private final String createdAt;
    private final String url;
    private final String model;
}
