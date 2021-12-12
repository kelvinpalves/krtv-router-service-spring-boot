package com.krtv.router.infra.selenium.mprore708;

import com.krtv.router.infra.selenium.service.fields.common.FieldType;

public enum FieldsMultilaserProRe708 {
    PASSWORD("password", FieldType.INPUT_TEXT_BY_NAME),
    ON_OFF("autoexec", FieldType.RADIO_BUTTON_BY_NAME),
    URL("url", FieldType.INPUT_TEXT_BY_NAME),
    USERNAME("username", FieldType.INPUT_TEXT_BY_NAME),
    SAVE_AND_APPLY("save_apply", FieldType.BUTTON_CLICK_BY_NAME);

    private final String id;
    private final FieldType type;

    FieldsMultilaserProRe708(String id, FieldType type) {
        this.id = id;
        this.type = type;
    }

    public static FieldsMultilaserProRe708 fromString(String model) {
        if (model != null) {
            for (FieldsMultilaserProRe708 fieldsGwr1200ac : FieldsMultilaserProRe708.values()) {
                if (fieldsGwr1200ac.name().equals(model))
                    return fieldsGwr1200ac;
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
