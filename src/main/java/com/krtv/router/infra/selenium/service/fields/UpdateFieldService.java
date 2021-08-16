package com.krtv.router.infra.selenium.service.fields;

import com.krtv.router.infra.selenium.service.fields.common.FieldType;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public interface UpdateFieldService {

    FieldType getFieldType();

}


