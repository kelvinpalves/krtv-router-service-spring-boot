package com.krtv.router.infra.selenium.service.router;

import com.krtv.router.domain.RouterModel;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import org.springframework.stereotype.Component;

@Component
public interface UpdateRouterService {

    void execute(UpdateRouterDto updateRouterDto) throws Exception;
    RouterModel getRouterModel();

}
