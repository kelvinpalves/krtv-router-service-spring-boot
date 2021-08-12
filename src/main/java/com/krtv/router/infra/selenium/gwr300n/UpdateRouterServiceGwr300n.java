package com.krtv.router.infra.selenium.gwr300n;

import com.krtv.router.domain.RouterModel;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import com.krtv.router.infra.selenium.service.UpdateFieldStrategyFactory;
import com.krtv.router.infra.selenium.service.UpdateRouterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UpdateRouterServiceGwr300n implements UpdateRouterService {

    @Override
    public void execute(UpdateRouterDto updateRouterDto, UpdateFieldStrategyFactory updateFieldStrategyFactory) {
        log.info("Service to update router is executing: {}", updateRouterDto.getModel());
    }

    @Override
    public RouterModel getRouterModel() {
        return RouterModel.GWR300N;
    }

}


