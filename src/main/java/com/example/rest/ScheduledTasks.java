package com.example.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private List<REWARD> listReward;
    @Autowired
    public CRUDService crudService;

    /*
        public void scheduleTaskWithFixedRate() {
        }

        public void scheduleTaskWithFixedDelay() {
        }

        public void scheduleTaskWithInitialDelay() {
        }
    */
    //@Scheduled(cron = "15 * * * * ?")
    @Scheduled(cron = "0 0 12 ? * MON")
    public void scheduleTaskWithCronExpression() throws InterruptedException, ExecutionException {
        System.out.println("Send email 1 to producers to inform quantity sold items in " + LocalDateTime.now());
        listReward = crudService.getRewardList();
        System.out.println("lst"+ listReward);

        for (REWARD reward: listReward) {
            CRUD crud = new CRUD(reward);
            crudService.createCrud(crud);
            System.out.println("đối tượng reward được gắn vào crud:" + crudService.createCrud(crud));
        }
    }
}
