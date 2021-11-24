package com.krtv.router.usecase.listtasks;

import com.krtv.router.infra.repository.RouterTaskDsGateway;
import com.krtv.router.infra.repository.specification.TaskFilterSpecification;
import com.krtv.router.infra.rest.ListTasksDto;
import com.krtv.router.infra.rest.TaskSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListTasksInteractor implements ListTasksInputBoundary {

    private final RouterTaskDsGateway routerTaskDsGateway;

    @Override
    public Page<ListTasksDto> list(TaskSearchRequest searchRequest, Pageable pageable) {
        TaskFilterSpecification taskFilterSpecification = new TaskFilterSpecification(searchRequest);
        return routerTaskDsGateway.listAll(taskFilterSpecification, pageable);
    }
}
