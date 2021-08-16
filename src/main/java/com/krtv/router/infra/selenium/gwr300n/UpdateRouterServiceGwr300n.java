package com.krtv.router.infra.selenium.gwr300n;

import com.krtv.router.domain.RouterModel;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import com.krtv.router.infra.selenium.gwr1200ac.LoginPageGwr1200ac;
import com.krtv.router.infra.selenium.gwr1200ac.TR069PageGwr1200ac;
import com.krtv.router.infra.selenium.service.router.UpdateRouterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class UpdateRouterServiceGwr300n implements UpdateRouterService {
    private final LoginPageGwr300n login;

    @Override
    public void execute(UpdateRouterDto updateRouterDto) throws Exception {
        log.info("Service for update router is called {}", updateRouterDto.getModel());

        try {
            TR069PageGwr300n tr069PageGwr300n = this.login.submit(updateRouterDto.getUrl(),
                    updateRouterDto.getUsername(),
                    updateRouterDto.getPassword());

            tr069PageGwr300n.execute(updateRouterDto.getData());
        } catch (Exception ex) {
            log.error("An error occurred during the updating process.");
            throw ex;
        } finally {
            this.login.close();
        }
    }

    @Override
    public RouterModel getRouterModel() {
        return RouterModel.GWR300N;
    }

}


