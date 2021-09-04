package com.krtv.router.infra.rest;

import com.krtv.router.infra.repository.RouterTaskDataMapper;
import com.krtv.utils.DateUtils;
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

    public static ListTasksDto converterMapperToDto(RouterTaskDataMapper mapper) {
        return ListTasksDto.builder()
                .id(mapper.getId())
                .url(mapper.getURL())
                .model(mapper.getModel())
                .createdAt(DateUtils.localDateTimeToStringDate(mapper.getCreatedAt()))
                .startedAt(DateUtils.localDateTimeToStringDate(mapper.getStartedAt()))
                .finishedAt(DateUtils.localDateTimeToStringDate(mapper.getFinishedAt()))
                .build();
    }
}
