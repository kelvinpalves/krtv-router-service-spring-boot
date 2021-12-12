package com.krtv.router.infra.selenium.mprore708;

import com.krtv.router.infra.selenium.gwr300n.TR069PageGwr300n;
import com.krtv.router.infra.selenium.service.router.PageObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoginPageMultilaserProRe708 extends PageObject  {
    private final TR069PageMultilaserProRe708 tr069Page;

    public TR069PageMultilaserProRe708 submit(String urlBase, String username, String password) {
        this.start(null);
        this.setUrlBaseWithCredentials(urlBase, username, password);

        log.info("Trying to login: {}", this.url);
        log.info("URL with credentials: {}", this.urlBaseWithCredentials);

        this.browser.get(this.urlBaseWithCredentials);

        tr069Page.load(this.browser, this.url, this.urlBaseWithCredentials);
        return tr069Page;
    }


}
