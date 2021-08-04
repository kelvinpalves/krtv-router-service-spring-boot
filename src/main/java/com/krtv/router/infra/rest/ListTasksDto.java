package com.krtv.router.infra.rest;

import com.krtv.router.infra.repository.RouterTaskDataMapper;
import com.krtv.utils.DataUtils;
import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

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
                .createdAt(DataUtils.localDateTimeToStringDate(mapper.getCreatedAt()))
                .startedAt(DataUtils.localDateTimeToStringDate(mapper.getStartedAt()))
                .finishedAt(DataUtils.localDateTimeToStringDate(mapper.getFinishedAt()))
                .build();
    }
}
