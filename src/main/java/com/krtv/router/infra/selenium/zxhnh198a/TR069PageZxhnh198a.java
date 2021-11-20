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

import java.time.LocalDateTime;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class TR069PageZxhnh198a extends PageObject {

    private final UpdateFieldStrategyFactory updateFieldStrategyFactory;
    private final static boolean FIRST_FORM = true;
    private final static boolean SECOND_FORM = false;

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
            data.forEach((field, value) -> {
                this.executeField(field, value, FIRST_FORM);
            });

            FieldsZxhnh198a button = FieldsZxhnh198a.APPLY;
            ButtonClickService buttonClickService = (ButtonClickService) updateFieldStrategyFactory.findService(button.getType());
            buttonClickService.execute(browser, button.getId());
            Thread.sleep(3000);

            log.info("Basic configuration updated. Date={}", LocalDateTime.now());
            log.info("Closing basic configuration");

            FieldsZxhnh198a basicConfigurationCollapse = FieldsZxhnh198a.BASIC_CONFIGURATION_COLLAPSE;
            ButtonClickService basicConfigurationCollapseButton = (ButtonClickService) updateFieldStrategyFactory.findService(basicConfigurationCollapse.getType());
            basicConfigurationCollapseButton.execute(this.browser, basicConfigurationCollapse.getId());

            Thread.sleep(3000);

            log.info("Opening stun configuration");

            FieldsZxhnh198a stunConfigurationCollapse = FieldsZxhnh198a.STUN_CONFIGURATION_COLLAPSE;
            ButtonClickService stunConfigurationCollapseButton = (ButtonClickService) updateFieldStrategyFactory.findService(stunConfigurationCollapse.getType());
            stunConfigurationCollapseButton.execute(this.browser, stunConfigurationCollapse.getId());

            Thread.sleep(3000);

            data.forEach((field, value) -> {
                this.executeField(field, value, SECOND_FORM);
            });

            Thread.sleep(5000);
        } catch (Exception ex) {
            log.error("Error to update router.");
            throw ex;
        }
    }

    private void executeField(String field, String value, boolean firstPhase) {
        try {
            log.info("field: {}, new value: {}", FieldsZxhnh198a.fromString(field).getId(), value);
            FieldsZxhnh198a current = FieldsZxhnh198a.fromString(field);

            if (current.isFirstPhase() != firstPhase)
                return;

            InputDataService inputDataService = (InputDataService) updateFieldStrategyFactory.findService(current.getType());

            String id = current.getId();

            if (!current.isFirstPhase()) {
                if (id.equals(FieldsZxhnh198a.STUN_USERNAME.getId()) || id.equals(FieldsZxhnh198a.STUN_PASSWORD.getId()))
                    id = id.replace("STUN_", "");
            }

            inputDataService.execute(browser, id, value);
        } catch (Exception ex) {
            log.error("Error to update field({}).", field);
            throw ex;
        }
    }
}
