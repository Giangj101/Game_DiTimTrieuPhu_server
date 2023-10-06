package com.example.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;


@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    public CRUDService crudService;

    /*
        public void scheduleTaskWithFixedRate() {
        }

        public void scheduleTaskWithFixedDelay() {
        }

        public void scheduleTaskWithInitialDelay() {
        }
    */
    @Scheduled(cron = "15 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        System.out.println("Send email 1 to producers to inform quantity sold items in " + LocalDateTime.now());

    }
}
