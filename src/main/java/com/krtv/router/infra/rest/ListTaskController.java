package com.krtv.router.infra.rest;

import com.krtv.router.usecase.listopentasks.ListOpenTasksInputBoundary;
import com.krtv.router.usecase.listtasks.ListTasksInputBoundary;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("router-task")
@Log4j2
@RequiredArgsConstructor
public class ListTaskController {

    private final ListTasksInputBoundary listTasksInputBoundary;
    private final ListOpenTasksInputBoundary listOpenTasksInputBoundary;

    @Operation(description = "List all tasks")
    @GetMapping
    public ResponseEntity<Page<ListTasksDto>> list(Pageable pageable) {
        log.info("List all tasks");
        return ResponseEntity.ok(listTasksInputBoundary.list(pageable));
    }

    @Operation(description = "List all opened tasks")
    @GetMapping("only-open")
    public ResponseEntity<Page<ListOpenTasksDto>> listOpen(Pageable pageable) {
        log.info("List all tasks");
        return ResponseEntity.ok(listOpenTasksInputBoundary.list(pageable));
    }

}
