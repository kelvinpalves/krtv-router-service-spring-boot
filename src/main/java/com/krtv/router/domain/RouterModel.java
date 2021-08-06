/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.domain;

import com.krtv.router.infra.selenium.gwr300n.UpdateRouterServiceGwr300n;
import com.krtv.router.infra.selenium.service.UpdateRouterService;

/**
 *
 * @author kelvin
 */
public enum RouterModel {
    ZTEH199A {
        @Override
        public UpdateRouterService getService() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },
    GWR1200AC {
        @Override
        public UpdateRouterService getService() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },
    GWR300N {
        @Override
        public UpdateRouterService getService() {
            return new UpdateRouterServiceGwr300n();
        }
    },
    TENDA {
        @Override
        public UpdateRouterService getService() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static RouterModel fromString(String model) {
        if (model != null) {
            for (RouterModel routerModel : RouterModel.values()) {
                if (routerModel.name().equals(model))
                    return routerModel;
            }
        }
        
        throw new IllegalArgumentException("Model is invalid");
    }

    public abstract UpdateRouterService getService();
}
