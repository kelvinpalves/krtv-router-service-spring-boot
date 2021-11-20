package com.krtv.router.infra.rest;

import com.krtv.router.infra.repository.RouterTaskDataMapper;
import com.krtv.utils.DateUtils;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ListTasksDto {
    private final String id;
    private final String url;
    private final String model;
    private final String createdAt;
    private final String startedAt;
    private final String finishedAt;
    private final String currentStatus;
    private final Boolean expired;
    private final Integer tries;
    private final Map<String, String> data;

    public static ListTasksDto converterMapperToDto(RouterTaskDataMapper mapper) {
        return ListTasksDto.builder()
                .id(mapper.getId())
                .url(mapper.getURL())
                .model(mapper.getModel())
                .createdAt(DateUtils.localDateTimeToStringDate(mapper.getCreatedAt()))
                .startedAt(DateUtils.localDateTimeToStringDate(mapper.getStartedAt()))
                .finishedAt(DateUtils.localDateTimeToStringDate(mapper.getFinishedAt()))
                .currentStatus(mapper.getCurrentStatus())
                .expired(mapper.getExpired())
                .tries(mapper.getTries())
                .data(new HashMap<>())
                .build();
    }
}
