/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.domain;

/**
 *
 * @author kelvin
 */
public enum RouterModel {
    ZXHNH198A,
    GWR1200AC,
    MULTILASER_PRO_RE708,
    GWR300N;

    public static RouterModel fromString(String model) {
        if (model != null) {
            for (RouterModel routerModel : RouterModel.values()) {
                if (routerModel.name().equals(model))
                    return routerModel;
            }
        }
        
        throw new IllegalArgumentException("Model is invalid");
    }
}
