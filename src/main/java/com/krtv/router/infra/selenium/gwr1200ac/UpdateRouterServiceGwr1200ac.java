package com.krtv.router.infra.selenium.gwr1200ac;

import com.krtv.router.domain.RouterModel;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import com.krtv.router.infra.selenium.service.UpdateRouterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UpdateRouterServiceGwr1200ac implements UpdateRouterService {

    @Override
    public void execute(UpdateRouterDto updateRouterDto) {
        log.info("Service for update router is called {}", updateRouterDto.getModel());
    }

    @Override
    public RouterModel getRouterModel() {
        return RouterModel.GWR1200AC;
    }

}
