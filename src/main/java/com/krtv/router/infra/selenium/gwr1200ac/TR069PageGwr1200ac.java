package com.krtv.router.infra.selenium.gwr1200ac;

import com.krtv.router.infra.selenium.service.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.util.Map;

@Log4j2
public class TR069PageGwr1200ac extends PageObject {

    private UpdateFieldStrategyFactory updateFieldStrategyFactory;

    public TR069PageGwr1200ac(WebDriver browser, String url, String urlWithCredentials) {
        super(browser);
        this.url = url;
        this.urlBaseWithCredentials = urlWithCredentials;
    }

    public void setUpdateFieldStrategyFactory(UpdateFieldStrategyFactory updateFieldStrategyFactory) {
        this.updateFieldStrategyFactory = updateFieldStrategyFactory;
    }

    public void load() {
        log.info("calling tr069 page, {}", this.url + "tr069config.htm");
        this.browser.navigate().to(this.url + "tr069config.htm");
    }

    public void execute(Map<String, String> data) {
        data.forEach(this::executeField);
        FieldsGwr1200ac button = FieldsGwr1200ac.SAVE_AND_APPLY;
        ButtonClickService buttonClickService = (ButtonClickService) updateFieldStrategyFactory.findService(button.getType());
        buttonClickService.execute(browser, button.getId());
    }

    private void executeField(String field, String value) {
        log.info("field: {}, new value: {}", FieldsGwr1200ac.fromString(field).getId(), value);
        FieldsGwr1200ac current = FieldsGwr1200ac.fromString(field);
        InputDataService inputDataService = (InputDataService) updateFieldStrategyFactory.findService(current.getType());
        inputDataService.execute(browser, current.getId(), value);
    }
}
