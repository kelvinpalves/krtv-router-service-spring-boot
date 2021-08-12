package com.krtv.router.infra.selenium.service;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public interface ButtonClickService extends UpdateFieldService {

    void execute(WebDriver browser, String key);

}

