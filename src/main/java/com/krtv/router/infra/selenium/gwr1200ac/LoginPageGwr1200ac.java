package com.krtv.router.infra.selenium.gwr1200ac;

import com.krtv.router.infra.selenium.service.PageObject;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginPageGwr1200ac extends PageObject  {

    public LoginPageGwr1200ac() {
        super(null);
    }

    public TR069PageGwr1200ac submit(String urlBase, String username, String password) {
        this.setUrlBaseWithCredentials(urlBase, username, password);

        log.info("Trying to login: {}", this.url);
        log.info("URL with credentials: {}", this.urlBaseWithCredentials);

        this.browser.get(this.urlBaseWithCredentials);

        return new TR069PageGwr1200ac(this.browser, this.url, this.urlBaseWithCredentials);
    }


}
