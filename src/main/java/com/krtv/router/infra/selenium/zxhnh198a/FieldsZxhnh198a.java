package com.krtv.router.infra.selenium.zxhnh198a;

import com.krtv.router.infra.selenium.service.fields.common.FieldType;

public enum FieldsZxhnh198a {
    PASSWORD("UserPassword", FieldType.INPUT_TEXT_BY_NAME),
    ON_OFF("SupportCertAuth", FieldType.RADIO_BUTTON_BY_NAME),
    URL("URL", FieldType.INPUT_TEXT_BY_NAME),
    USERNAME("UserName", FieldType.INPUT_TEXT_BY_NAME),
    APPLY("Btn_apply_TR069BasicConf", FieldType.BUTTON_CLICK_BY_ID),
    MANAGEMENT_AND_DIAGNOSIS("mgrAndDiag", FieldType.BUTTON_CLICK_BY_ID),
    TR069("remoteMgr", FieldType.BUTTON_CLICK_BY_ID),
    USERNAME_LOGIN("Frm_Username", FieldType.INPUT_TEXT_BY_NAME),
    PASSWORD_LOGIN("Frm_Password", FieldType.INPUT_TEXT_BY_NAME),
    LOGIN("LoginId", FieldType.BUTTON_CLICK_BY_ID);

    private final String id;
    private final FieldType type;

    FieldsZxhnh198a(String id, FieldType type) {
        this.id = id;
        this.type = type;
    }

    public static FieldsZxhnh198a fromString(String model) {
        if (model != null) {
            for (FieldsZxhnh198a fieldsZxhnh198A : FieldsZxhnh198a.values()) {
                if (fieldsZxhnh198A.name().equals(model))
                    return fieldsZxhnh198A;
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
