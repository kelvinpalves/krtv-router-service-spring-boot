package com.krtv.router.infra.scheduled;

import com.krtv.router.usecase.updaterouter.UpdateRouterInputBoundary;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@Log4j2
@RequiredArgsConstructor
@RequestMapping("simulate")
public class UpdateRouterScheduled implements UpdateRouterCommand {

    private static Boolean RUNNING = false;
    private static Boolean ENABLED = true;

    private final UpdateRouterInputBoundary updateRouterInputBoundary;

    @GetMapping
    public String test() {
        log.info("the scheduled task was invoked by simulate");
        this.execute();
        return "";
    }

    @Override
    @Scheduled(fixedRate = 10000)
    public void execute() {
        if (!ENABLED || RUNNING) {
            log.error("Task is running, aborting...");
            return;
        }

        try {
            enableRunning();
            updateRouterInputBoundary.update();
            disableRunning();
        } catch (Exception ex) {
            log.error(ex);
            disableRunning();
        }
    }

    private void enableRunning() {
        RUNNING = true;
    }

    private void disableRunning() {
        RUNNING = false;
    }
}
