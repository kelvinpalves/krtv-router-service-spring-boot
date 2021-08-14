package com.krtv.router.infra.selenium.service.fields;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public interface InputDataService {

    void execute(WebDriver browser, String key, String value);

}


