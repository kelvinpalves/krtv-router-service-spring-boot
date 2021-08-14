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
    ZTEH199A,
    GWR1200AC,
    GWR300N,
    TENDA;

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
