package com.krtv.router.infra.selenium.service;

import com.krtv.router.domain.RouterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UpdateRouterStrategyFactory {

    private Map<RouterModel, UpdateRouterService> services;

    @Autowired
    public UpdateRouterStrategyFactory(Set<UpdateRouterService> serviceSet) {
        createService(serviceSet);
    }

    public UpdateRouterService findService(RouterModel model) {
        return services.get(model);
    }
    private void createService(Set<UpdateRouterService> serviceSet) {
        services = new HashMap<>();
        serviceSet.forEach(
                service -> services.put(service.getRouterModel(), service));
    }


}
