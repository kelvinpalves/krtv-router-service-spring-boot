package com.krtv.router.infra.selenium.gwr300n;

import com.krtv.router.infra.selenium.service.fields.common.FieldType;

public enum FieldsGwr300n {
    PASSWORD("password", FieldType.INPUT_TEXT_BY_NAME),
    ON_OFF("autoexec", FieldType.RADIO_BUTTON_BY_NAME),
    URL("url", FieldType.INPUT_TEXT_BY_NAME),
    USERNAME("username", FieldType.INPUT_TEXT_BY_NAME),
    SAVE_AND_APPLY("save", FieldType.BUTTON_CLICK);

    private final String id;
    private final FieldType type;

    FieldsGwr300n(String id, FieldType type) {
        this.id = id;
        this.type = type;
    }

    public static FieldsGwr300n fromString(String model) {
        if (model != null) {
            for (FieldsGwr300n fieldsGwr300n : FieldsGwr300n.values()) {
                if (fieldsGwr300n.name().equals(model))
                    return fieldsGwr300n;
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
