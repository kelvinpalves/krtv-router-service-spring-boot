package com.krtv.router.usecase.updaterouter;

import com.krtv.router.domain.RouterStatus;
import com.krtv.router.infra.exception.NoRoutersWaitingException;
import com.krtv.router.infra.repository.RouterTaskDsGateway;
import com.krtv.router.infra.scheduled.UpdateRouterDto;
import com.krtv.router.infra.selenium.service.router.UpdateRouterService;
import com.krtv.router.infra.selenium.service.router.UpdateRouterStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class UpdateRouterInteractor implements UpdateRouterInputBoundary {

    private final UpdateRouterStrategyFactory strategyFactory;
    private final RouterTaskDsGateway routerTaskDsGateway;

    @Override
    public void update() throws Exception {
        UpdateRouterDto updateRouterDto = null;

        try {
            updateRouterDto = routerTaskDsGateway.getNextRouterWaitingForUpdate();
            routerTaskDsGateway.setStartedTime(updateRouterDto.getRouter());

            Map<String, String> data = routerTaskDsGateway.getFieldsFromRouter(updateRouterDto.getRouter());
            data.forEach(updateRouterDto::addData);

            UpdateRouterService service = strategyFactory.findService(updateRouterDto.getModel());

            service.execute(updateRouterDto);

            this.updateStatus(updateRouterDto.getRouter(), RouterStatus.EXECUTED);
            this.updateNumberOfTries(updateRouterDto.getRouter());
            this.setTaskToExpired(updateRouterDto.getRouter());
        } catch (NoRoutersWaitingException nex) {
            log.error(nex.getMessage());
            throw nex;
        }  catch (Exception ex) {
            log.error("Error to update router: {}", updateRouterDto);
            log.error(ex);

            String router = updateRouterDto == null ? null : updateRouterDto.getRouter();
            this.updateStatus(router, RouterStatus.ERROR);
            this.updateNumberOfTries(router);

            throw ex;
        }
    }

    private void setTaskToExpired(String router) {
        if (router != null) {
            routerTaskDsGateway.setTaskToExpired(router);
        }
    }

    private void updateNumberOfTries(String router) {
        if (router != null) {
            routerTaskDsGateway.updateNumberOfExecutedTries(router);
        }
    }

    private void updateStatus(String router, RouterStatus status) {
        if (router != null) {
            routerTaskDsGateway.updateStatus(router, status);
        }
    }

}
