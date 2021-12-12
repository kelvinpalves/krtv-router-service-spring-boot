package com.krtv.router.infra.selenium.mprore708;

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
public class UpdateRouterServiceMultilaserProRe708 implements UpdateRouterService {
    private final LoginPageMultilaserProRe708 login;

    @Override
    public void execute(UpdateRouterDto updateRouterDto) throws Exception {
        log.info("Service for update router is called {}", updateRouterDto.getModel());

        try {
            TR069PageMultilaserProRe708 tr069Page = this.login.submit(updateRouterDto.getUrl(),
                    updateRouterDto.getUsername(),
                    updateRouterDto.getPassword());

            Thread.sleep(5000);

            tr069Page.execute(updateRouterDto.getData());

            Thread.sleep(5000);
        } catch (Exception ex) {
            log.error(ex);
            log.error("An error occurred during the updating process.");
            throw ex;
        } finally {
            this.login.close();
        }

    }

    @Override
    public RouterModel getRouterModel() {
        return RouterModel.MULTILASER_PRO_RE708;
    }

}
