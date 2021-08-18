package com.krtv.router.infra.selenium.zxhnh198a;

import com.krtv.router.infra.selenium.gwr1200ac.FieldsGwr1200ac;
import com.krtv.router.infra.selenium.service.fields.ButtonClickService;
import com.krtv.router.infra.selenium.service.fields.InputDataService;
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
public class TR069PageZxhnh198a extends PageObject {

    private final UpdateFieldStrategyFactory updateFieldStrategyFactory;

    public void load(WebDriver browser, String url) {
        this.start(browser);
        this.url = url;
        FieldsZxhnh198a button = FieldsZxhnh198a.MANAGEMENT_AND_DIAGNOSIS;
        ButtonClickService management = (ButtonClickService) updateFieldStrategyFactory.findService(button.getType());
        management.execute(this.browser, button.getId());

        FieldsZxhnh198a buttonTr069 = FieldsZxhnh198a.TR069;
        ButtonClickService tr069 = (ButtonClickService) updateFieldStrategyFactory.findService(buttonTr069.getType());
        tr069.execute(this.browser, buttonTr069.getId());
    }

    public void execute(Map<String, String> data) throws Exception {
        try {
            data.forEach(this::executeField);
            FieldsZxhnh198a button = FieldsZxhnh198a.APPLY;
            ButtonClickService buttonClickService = (ButtonClickService) updateFieldStrategyFactory.findService(button.getType());
            buttonClickService.execute(browser, button.getId());
            Thread.sleep(3000);
        } catch (Exception ex) {
            log.error("Error to update router.");
            throw ex;
        }
    }

    private void executeField(String field, String value) {
        try {
            log.info("field: {}, new value: {}", FieldsGwr1200ac.fromString(field).getId(), value);
            FieldsZxhnh198a current = FieldsZxhnh198a.fromString(field);

            InputDataService inputDataService = (InputDataService) updateFieldStrategyFactory.findService(current.getType());
            inputDataService.execute(browser, current.getId(), value);
        } catch (Exception ex) {
            log.error("Error to update field({}).", field);
            throw ex;
        }
    }
}
