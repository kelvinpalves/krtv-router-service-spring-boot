package com.krtv.router.infra.selenium.gwr1200ac;

import com.krtv.router.domain.RouterModel;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import com.krtv.router.infra.selenium.service.UpdateRouterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UpdateRouterServiceGwr1200ac implements UpdateRouterService {

    private LoginPageGwr1200ac login;

    @Override
    public void execute(UpdateRouterDto updateRouterDto) {
        log.info("Service for update router is called {}", updateRouterDto.getModel());

        try {
            this.login = new LoginPageGwr1200ac();
            TR069PageGwr1200ac tr069PageGwr1200ac = this.login.submit(updateRouterDto.getUrl(),
                    updateRouterDto.getUsername(),
                    updateRouterDto.getPassword());

            tr069PageGwr1200ac.load();
            tr069PageGwr1200ac.execute(updateRouterDto.getData());
        } catch (Exception ex) {
            log.info(ex);
        } finally {
            this.login.close();
        }

    }

    @Override
    public RouterModel getRouterModel() {
        return RouterModel.GWR1200AC;
    }

}
