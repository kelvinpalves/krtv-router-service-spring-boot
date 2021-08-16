package com.krtv.router.infra.selenium.gwr1200ac;

import com.krtv.router.domain.RouterModel;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import com.krtv.router.infra.selenium.service.router.UpdateRouterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Component
@RequiredArgsConstructor
@Log4j2
public class UpdateRouterServiceGwr1200ac implements UpdateRouterService {
    private final LoginPageGwr1200ac login;

    @Override
    public void execute(UpdateRouterDto updateRouterDto) throws Exception {
        log.info("Service for update router is called {}", updateRouterDto.getModel());

        try {
            TR069PageGwr1200ac tr069PageGwr1200ac = this.login.submit(updateRouterDto.getUrl(),
                    updateRouterDto.getUsername(),
                    updateRouterDto.getPassword());

            tr069PageGwr1200ac.execute(updateRouterDto.getData());
        } catch (Exception ex) {
            log.error("An error occurred during the updating process.");
            throw ex;
        } finally {
            this.login.close();
        }

    }

    @Override
    public RouterModel getRouterModel() {
        return RouterModel.GWR1200AC;
    }

}
