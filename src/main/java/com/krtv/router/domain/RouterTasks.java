/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.domain;

import java.util.Locale;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author kelvin
 */

@Builder
@Getter
@ToString
public class RouterTasks {

    private final String ip;
    private final Integer port;
    private final Protocol protocol;
    private final String context;
    private final RouterModel model;
    private final Map<String, String> data;

    public String getProtocol() {
        return protocol.name().toLowerCase(Locale.ROOT);
    }

    public String getModel() {
        return model.name();
    }

}
