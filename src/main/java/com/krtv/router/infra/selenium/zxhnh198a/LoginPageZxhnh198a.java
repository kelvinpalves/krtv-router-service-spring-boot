package com.krtv.router.infra.selenium.zxhnh198a;

import com.krtv.router.infra.selenium.service.fields.ButtonClickService;
import com.krtv.router.infra.selenium.service.fields.InputDataService;
import com.krtv.router.infra.selenium.service.fields.UpdateFieldStrategyFactory;
import com.krtv.router.infra.selenium.service.router.PageObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoginPageZxhnh198a extends PageObject  {
    private final TR069PageZxhnh198a tr069PageZxhnh198a;
    private final UpdateFieldStrategyFactory updateFieldStrategyFactory;

    public TR069PageZxhnh198a submit(String urlBase, String username, String password) {
        this.start(null);

        log.info("Trying to login: {}", this.url);

        this.setUrlBase(urlBase);
        this.browser.get(this.url);

        this.setFormData(username, password);
        this.submit();

        tr069PageZxhnh198a.load(this.browser, this.url);
        return tr069PageZxhnh198a;
    }

    private void setFormData(String username, String password) {
        FieldsZxhnh198a usernameLogin = FieldsZxhnh198a.USERNAME_LOGIN;
        FieldsZxhnh198a passwordLogin = FieldsZxhnh198a.PASSWORD_LOGIN;

        InputDataService usernameInputData = (InputDataService) updateFieldStrategyFactory.findService(usernameLogin.getType());
        usernameInputData.execute(this.browser, usernameLogin.getId(), username);

        InputDataService passwordInputData = (InputDataService) updateFieldStrategyFactory.findService(passwordLogin.getType());
        passwordInputData.execute(this.browser, passwordLogin.getId(), password);
    }

    private void submit() {
        FieldsZxhnh198a login = FieldsZxhnh198a.LOGIN;
        ButtonClickService loginButton = (ButtonClickService) updateFieldStrategyFactory.findService(login.getType());
        loginButton.execute(this.browser, login.getId());
    }


}
