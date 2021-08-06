package com.krtv.router.infra.scheduled;

import com.krtv.router.domain.RouterModel;
import com.krtv.router.infra.repository.RouterTaskDataMapper;
import com.krtv.router.infra.repository.RouterTaskDsGateway;
import com.krtv.router.infra.selenium.service.UpdateRouterService;
import com.krtv.router.infra.selenium.service.UpdateRouterStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
@RequiredArgsConstructor
public class UpdateRouterScheduled implements UpdateRouterCommand {

    private static Boolean RUNNING = false;
    private static Boolean ENABLED = true;

    private final UpdateRouterStrategyFactory strategyFactory;

    private final RouterTaskDsGateway routerTaskDsGateway;

    @Override
    @Scheduled(fixedRate = 30000)
    public void execute() {
        if (!ENABLED) return;
        log.info("the scheduled task was invoked");

        UpdateRouterDto updateRouterDto = null;

        try {
            updateRouterDto = routerTaskDsGateway.getNextRouterWaitingForUpdate();
            Map<String, String> data = routerTaskDsGateway.getFieldsFromRouter(updateRouterDto.getRouter());
            data.forEach(updateRouterDto::addData);

            UpdateRouterService service = strategyFactory.findService(updateRouterDto.getModel());
            service.execute(updateRouterDto);

        }  catch (Exception ex) {
            log.error("Error to update router: {}", updateRouterDto, ex);
        }

    }
}