package com.krtv.router.infra.selenium.service;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UpdateFieldServiceCssType implements InputDataService {
    @Override
    public void execute(WebDriver browser, String key, String value) {
        browser.findElements(By.cssSelector("input[name=" + key + "]")).stream().forEach(webElement -> {
            if (webElement.getAttribute("value").equals(value)) {
                webElement.click();
            }
        });
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.RADIO_BUTTON_BY_NAME;
    }
}
