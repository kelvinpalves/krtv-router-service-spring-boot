package com.krtv.router.infra.selenium.gwr300n;

import com.krtv.router.infra.selenium.gwr1200ac.TR069PageGwr1200ac;
import com.krtv.router.infra.selenium.service.router.PageObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoginPageGwr300n extends PageObject  {
    private final TR069PageGwr300n tr069PageGwr300n;

    public TR069PageGwr300n submit(String urlBase, String username, String password) {
        this.start(null);
        this.setUrlBaseWithCredentials(urlBase, username, password);

        log.info("Trying to login: {}", this.url);
        log.info("URL with credentials: {}", this.urlBaseWithCredentials);

        this.browser.get(this.urlBaseWithCredentials);

        tr069PageGwr300n.load(this.browser, this.url, this.urlBaseWithCredentials);
        return tr069PageGwr300n;
    }


}
