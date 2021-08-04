package com.krtv.router.usecase.listopentasks;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;

@Component
public interface ListOpenTasksInputBoundary {
    ResponseEntity<Page<ListOpenTasksDto>> list(Pageable pageable);
}
