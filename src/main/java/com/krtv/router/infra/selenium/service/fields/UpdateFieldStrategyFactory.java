package com.krtv.router.infra.selenium.service.fields;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Log4j2
public class UpdateFieldStrategyFactory {
    private Map<FieldType, UpdateFieldService> services;

    @Autowired
    public UpdateFieldStrategyFactory(Set<UpdateFieldService> serviceSet) {
        createService(serviceSet);
    }

    public UpdateFieldService findService(FieldType type) {
        log.debug(services);
        return services.get(type);
    }

    private void createService(Set<UpdateFieldService> serviceSet) {
        services = new HashMap<>();
        serviceSet.forEach(
                service -> services.put(service.getFieldType(), service));
    }
}

