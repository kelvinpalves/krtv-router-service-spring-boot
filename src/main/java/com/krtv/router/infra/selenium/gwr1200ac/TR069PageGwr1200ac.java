package com.krtv.router.infra.selenium.gwr1200ac;

import com.krtv.router.infra.selenium.service.FieldType;
import com.krtv.router.infra.selenium.service.PageObject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

@Log4j2
public class TR069PageGwr1200ac extends PageObject {

    public TR069PageGwr1200ac(WebDriver browser, String url, String urlWithCredentials) {
        super(browser);
        this.url = url;
        this.urlBaseWithCredentials = urlWithCredentials;
    }

    public void load() {
        log.info("calling tr069 page, {}", this.url + "tr069config.htm");
        this.browser.navigate().to(this.url + "tr069config.htm");
    }

    public void execute(Map<String, String> data) {
        data.forEach((field, value) -> {
            log.info("{} - {}", FieldsGwr1200ac.fromString(field).getId(), value);

            FieldsGwr1200ac current = FieldsGwr1200ac.fromString(field);

            switch (current.getType()) {
                case CSS: {
                    log.info("CSS Method is called.");
                    this.browser.findElements(By.cssSelector("input[name=autoexec]")).stream().forEach(webElement -> {
                        log.info("{} - {}", webElement.getAttribute("value"), value);
                        if (webElement.getAttribute("value").equals(value)) {
                            webElement.click();
                        }
                    });
                } break;
                default: {
                    log.info("Default method is called.");
                    String currentValue = this.browser.findElement(By.name(current.getId())).getAttribute("value");
                    if (!currentValue.equals(value)) {
                        this.browser.findElement(By.name(current.getId())).clear();
                        this.browser.findElement(By.name(current.getId())).sendKeys(value);
                    }
                } break;

            }

            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
            }


        });

        try {
            Thread.sleep(3000);
        } catch (Exception ex) {
        }
    }
}
