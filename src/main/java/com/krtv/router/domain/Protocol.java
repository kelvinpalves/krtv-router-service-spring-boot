package com.krtv.router.domain;

public enum Protocol {
    HTTP,
    HTTPS;

    public static Protocol fromString(String model) {
        if (model != null) {
            for (Protocol protocol : Protocol.values()) {
                if (protocol.name().equals(model)) {
                    return protocol;
                }
            }
        }

        throw new IllegalArgumentException("Protocol is invalid");
    }
}
