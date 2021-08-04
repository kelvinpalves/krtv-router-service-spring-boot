package com.krtv.router.infra.repository;

import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = { LocalDateTime.class })
public interface CommonMapper {

    RouterTaskDataMapper createRouterTaskDtoToRouterTaskDataMapper(CreateRouterTaskDto createRouterTaskDto);

}
