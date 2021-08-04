package com.krtv.router.usecase.listtasks;

import com.krtv.router.infra.rest.ListTasksDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface ListTasksInputBoundary {
    Page<ListTasksDto> list(Pageable pageable);
}
