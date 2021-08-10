package com.krtv.router.infra.selenium.gwr1200ac;

import com.krtv.router.domain.RouterModel;
import com.krtv.router.infra.selenium.service.FieldType;

public enum FieldsGwr1200ac {
    PASSWORD("password", FieldType.NAME),
    ON_OFF("autoexec", FieldType.CSS),
    URL("url", FieldType.NAME),
    USERNAME("username", FieldType.NAME);

    private final String id;
    private final FieldType type;

    FieldsGwr1200ac(String id, FieldType type) {
        this.id = id;
        this.type = type;
    }

    public static FieldsGwr1200ac fromString(String model) {
        if (model != null) {
            for (FieldsGwr1200ac fieldsGwr1200ac : FieldsGwr1200ac.values()) {
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
