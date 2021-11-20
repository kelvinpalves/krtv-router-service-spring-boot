package com.krtv.router.infra.selenium.zxhnh198a;

import com.krtv.router.infra.selenium.service.fields.common.FieldType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FieldsZxhnh198a {
    PASSWORD("UserPassword", FieldType.INPUT_TEXT_BY_NAME, true),
    ON_OFF("SupportCertAuth", FieldType.RADIO_BUTTON_BY_NAME, true),
    URL("URL", FieldType.INPUT_TEXT_BY_NAME, true),
    USERNAME("UserName", FieldType.INPUT_TEXT_BY_NAME, true),
    APPLY("Btn_apply_TR069BasicConf", FieldType.BUTTON_CLICK_BY_ID, false),
    MANAGEMENT_AND_DIAGNOSIS("mgrAndDiag", FieldType.BUTTON_CLICK_BY_ID, false),
    TR069("remoteMgr", FieldType.BUTTON_CLICK_BY_ID, false),
    USERNAME_LOGIN("Frm_Username", FieldType.INPUT_TEXT_BY_NAME, false),
    PASSWORD_LOGIN("Frm_Password", FieldType.INPUT_TEXT_BY_NAME, false),
    LOGIN("LoginId", FieldType.BUTTON_CLICK_BY_ID, false),
    BASIC_CONFIGURATION_COLLAPSE("TR069BasicConfBar", FieldType.BUTTON_CLICK_BY_ID, false),
    STUN_CONFIGURATION_COLLAPSE("StunConfBar", FieldType.BUTTON_CLICK_BY_ID, false),
    SERVER_ADDRESS("ServerAddress", FieldType.INPUT_TEXT_BY_NAME, false),
    SERVER_PORT("ServerPort", FieldType.INPUT_TEXT_BY_NAME, false),
    STUN_USERNAME("STUN_Username", FieldType.INPUT_TEXT_BY_NAME, false),
    STUN_PASSWORD("STUN_Password", FieldType.INPUT_TEXT_BY_NAME, false),
    STUN_ON_OFF("StunEnable", FieldType.RADIO_BUTTON_BY_NAME, false),
    STUN_APPLY("Btn_apply_StunConf", FieldType.BUTTON_CLICK_BY_ID, false);

    private final String id;
    private final FieldType type;
    private final boolean firstPhase;

    public static FieldsZxhnh198a fromString(String model) {
        if (model != null) {
            for (FieldsZxhnh198a fieldsZxhnh198A : FieldsZxhnh198a.values()) {
                if (fieldsZxhnh198A.name().equals(model))
                    return fieldsZxhnh198A;
            }
        }

        return null;
    }

}
