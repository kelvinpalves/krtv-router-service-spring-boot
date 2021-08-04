package com.krtv.router.infra.rest;

import com.krtv.router.usecase.listtasks.ListTasksInputBoundary;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("router")
@Log4j2
@RequiredArgsConstructor
public class ListTaskController {

    private final ListTasksInputBoundary inputBoundary;

    @Operation(description = "List all tasks")
    @GetMapping
    public ResponseEntity<Page<ListTasksDto>> list(Pageable pageable) {
        log.info("List all tasks");
        System.out.println(inputBoundary.list(pageable));
        return ResponseEntity.ok(inputBoundary.list(pageable));
    }

}