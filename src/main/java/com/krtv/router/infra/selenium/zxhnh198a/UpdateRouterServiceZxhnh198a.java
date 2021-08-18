package com.krtv.router.infra.selenium.zxhnh198a;

import com.krtv.router.domain.RouterModel;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import com.krtv.router.infra.selenium.gwr1200ac.LoginPageGwr1200ac;
import com.krtv.router.infra.selenium.gwr1200ac.TR069PageGwr1200ac;
import com.krtv.router.infra.selenium.service.router.UpdateRouterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class UpdateRouterServiceZxhnh198a implements UpdateRouterService {
    private final LoginPageZxhnh198a login;

    @Override
    public void execute(UpdateRouterDto updateRouterDto) throws Exception {
        log.info("Service for update router is called {}", updateRouterDto.getModel());

        try {
            TR069PageZxhnh198a tr069PageZxhnh198a = this.login.submit(updateRouterDto.getUrl(),
                    updateRouterDto.getUsername(),
                    updateRouterDto.getPassword());

            tr069PageZxhnh198a.execute(updateRouterDto.getData());
        } catch (Exception ex) {
            log.error("An error occurred during the updating process.");
            throw ex;
        } finally {
            this.login.close();
        }

    }

    @Override
    public RouterModel getRouterModel() {
        return RouterModel.ZXHNH198A;
    }

}
