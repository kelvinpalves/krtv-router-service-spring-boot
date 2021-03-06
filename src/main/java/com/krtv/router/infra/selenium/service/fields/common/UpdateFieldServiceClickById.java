package com.krtv.router.infra.selenium.service.fields.common;

import com.krtv.router.infra.selenium.service.fields.ButtonClickService;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UpdateFieldServiceClickById implements ButtonClickService {

    @Override
    public void execute(WebDriver browser, String key) {
        log.info("Click by ID was called. Key: {}", key);
        browser.findElement(By.id(key)).click();
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.BUTTON_CLICK_BY_ID;
    }
}
