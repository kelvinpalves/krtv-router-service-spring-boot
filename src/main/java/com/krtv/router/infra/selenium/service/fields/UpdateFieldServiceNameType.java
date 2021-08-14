package com.krtv.router.infra.selenium.service.fields;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UpdateFieldServiceNameType implements InputDataService, UpdateFieldService {
    @Override
    public void execute(WebDriver browser, String key, String value) {
        String currentValue = browser.findElement(By.name(key)).getAttribute("value");
        if (!currentValue.equals(value)) {
            browser.findElement(By.name(key)).clear();
            browser.findElement(By.name(key)).sendKeys(value);
        }
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.INPUT_TEXT_BY_NAME;
    }
}
