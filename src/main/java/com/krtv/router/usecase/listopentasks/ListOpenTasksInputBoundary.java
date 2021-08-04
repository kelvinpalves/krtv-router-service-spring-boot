package com.krtv.router.usecase.listopentasks;

import com.krtv.router.infra.rest.ListOpenTasksDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface ListOpenTasksInputBoundary {
    Page<ListOpenTasksDto> list(Pageable pageable);
}
