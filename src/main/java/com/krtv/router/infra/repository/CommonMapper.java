package com.krtv.router.infra.repository;

import com.krtv.router.infra.rest.ListTasksDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = { LocalDateTime.class })
public interface CommonMapper {

    RouterTaskDataMapper createRouterTaskDtoToRouterTaskDataMapper(CreateRouterTaskDto createRouterTaskDto);

}
