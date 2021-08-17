package com.krtv.router.infra.selenium.zteh199a;

import com.krtv.router.infra.selenium.service.fields.common.FieldType;

public enum FieldsZteh199a {
    PASSWORD("UserPassword", FieldType.INPUT_TEXT_BY_NAME),
    ON_OFF("SupportCertAuth", FieldType.RADIO_BUTTON_BY_NAME),
    URL("URL", FieldType.INPUT_TEXT_BY_NAME),
    USERNAME("UserName", FieldType.INPUT_TEXT_BY_NAME),
    APPLY("Btn_apply_TR069BasicConf", FieldType.BUTTON_CLICK);

    private final String id;
    private final FieldType type;

    FieldsZteh199a(String id, FieldType type) {
        this.id = id;
        this.type = type;
    }

    public static FieldsZteh199a fromString(String model) {
        if (model != null) {
            for (FieldsZteh199a fieldsZteh199a : FieldsZteh199a.values()) {
                if (fieldsZteh199a.name().equals(model))
                    return fieldsZteh199a;
            }
        }

        return null;
    }

    public String getId() {
        return id;
    }

    public FieldType getType() {
        return type;
    }
}
