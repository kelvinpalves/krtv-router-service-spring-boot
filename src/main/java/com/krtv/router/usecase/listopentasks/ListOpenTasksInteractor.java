package com.krtv.router.usecase.listopentasks;

import com.krtv.router.infra.repository.RouterTaskDsGateway;
import com.krtv.router.infra.rest.ListOpenTasksDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListOpenTasksInteractor implements ListOpenTasksInputBoundary {

    private final RouterTaskDsGateway routerTaskDsGateway;

    @Override
    public Page<ListOpenTasksDto> list(Pageable pageable) {
        return routerTaskDsGateway.listOpenTasks(pageable);
    }
}
