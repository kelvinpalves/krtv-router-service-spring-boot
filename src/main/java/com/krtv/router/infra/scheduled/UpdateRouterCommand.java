package com.krtv.router.infra.scheduled;

import org.springframework.stereotype.Component;

@Component
public interface UpdateRouterCommand {

    void execute();

}
