package com.krtv.router.infra.rest;

import com.krtv.router.infra.repository.RouterTaskDataMapper;
import com.krtv.utils.DateUtils;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListOpenTasksDto {
    private final String id;
    private final String createdAt;
    private final String url;
    private final String model;

    public static ListOpenTasksDto converterMapperToDto(RouterTaskDataMapper mapper) {
        return ListOpenTasksDto.builder()
                .id(mapper.getId())
                .url(mapper.getURL())
                .model(mapper.getModel())
                .createdAt(DateUtils.localDateTimeToStringDate(mapper.getCreatedAt()))
                .build();
    }
}
