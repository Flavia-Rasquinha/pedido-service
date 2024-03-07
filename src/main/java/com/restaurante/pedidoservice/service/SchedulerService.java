package com.restaurante.pedidoservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final HealthEndpoint healthEndpoint;
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Scheduled(fixedRate = 900000)
    public void schedulerAliveApp() {

        logger.info("Checking if the app is still alive at: {}", LocalDateTime.now());

        Status status = healthEndpoint.health().getStatus();

        logger.info("Current status of the app is: {}", status);
    }
}
