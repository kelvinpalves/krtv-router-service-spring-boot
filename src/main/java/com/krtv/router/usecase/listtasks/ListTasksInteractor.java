package com.krtv.router.usecase.listtasks;

import com.krtv.router.infra.repository.RouterTaskDsGateway;
import com.krtv.router.infra.rest.ListTasksDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListTasksInteractor implements ListTasksInputBoundary {

    private final RouterTaskDsGateway routerTaskDsGateway;

    @Override
    public Page<ListTasksDto> list(Pageable pageable) {
        return routerTaskDsGateway.listAll(pageable);
    }
}
