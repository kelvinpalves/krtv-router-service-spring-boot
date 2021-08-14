package com.krtv.router.infra.selenium.gwr1200ac;

import com.krtv.router.infra.selenium.service.router.PageObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoginPageGwr1200ac extends PageObject  {
    private final TR069PageGwr1200ac tr069PageGwr1200ac;

    public TR069PageGwr1200ac submit(String urlBase, String username, String password) {
        this.start(null);
        this.setUrlBaseWithCredentials(urlBase, username, password);

        log.info("Trying to login: {}", this.url);
        log.info("URL with credentials: {}", this.urlBaseWithCredentials);

        this.browser.get(this.urlBaseWithCredentials);

        tr069PageGwr1200ac.load(this.browser, this.url, this.urlBaseWithCredentials);
        return tr069PageGwr1200ac;
    }


}
