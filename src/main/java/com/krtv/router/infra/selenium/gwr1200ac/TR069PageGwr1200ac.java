package com.krtv.router.infra.selenium.gwr1200ac;

import com.krtv.router.infra.selenium.service.fields.ButtonClickService;
import com.krtv.router.infra.selenium.service.fields.InputDataService;
import com.krtv.router.infra.selenium.service.fields.UpdateFieldService;
import com.krtv.router.infra.selenium.service.fields.UpdateFieldStrategyFactory;
import com.krtv.router.infra.selenium.service.router.PageObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class TR069PageGwr1200ac extends PageObject {

    private final UpdateFieldStrategyFactory updateFieldStrategyFactory;

    public void load(WebDriver browser, String url, String urlWithCredentials) {
        this.start(browser);
        this.url = url;
        this.urlBaseWithCredentials = urlWithCredentials;
        log.info("calling tr069 page, {}", this.url + "tr069config.htm");
        this.browser.navigate().to(this.url + "tr069config.htm");
    }

    public void execute(Map<String, String> data) throws Exception {
        try {
            data.forEach(this::executeField);
            FieldsGwr1200ac button = FieldsGwr1200ac.SAVE_AND_APPLY;

            ButtonClickService buttonClickService = (ButtonClickService) updateFieldStrategyFactory.findService(button.getType());

            buttonClickService.execute(browser, button.getId());
        } catch (Exception ex) {
            log.error("Error to update router.");
            throw ex;
        }
    }

    private void executeField(String field, String value) {
        try {
            log.info("field: {}, new value: {}", FieldsGwr1200ac.fromString(field).getId(), value);
            FieldsGwr1200ac current = FieldsGwr1200ac.fromString(field);

            InputDataService inputDataService = (InputDataService) updateFieldStrategyFactory.findService(current.getType());
            inputDataService.execute(browser, current.getId(), value);
        } catch (Exception ex) {
            log.error("Error to update field({}).", field);
            throw ex;
        }
    }
}
