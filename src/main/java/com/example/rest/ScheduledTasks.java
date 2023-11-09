package com.example.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;


@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final String CRON_STAMINA_RESET_AT_DAY = "0 0 0 * * ?"; // Chay vao 0h moi ngay
    private static final String CRON_UPDATE_REWARD = "0 0 12 ? * MON"; // Chay vao moi 12h t2 hang tuan

    private List<Reward> listReward;
    @Autowired
    public CRUDService crudService;

    @Scheduled(fixedRate = 60000)
    public void updateRewardTop1Server() throws InterruptedException, ExecutionException {
        List<UserInfo> userInfos = crudService.getUserInfor().stream().sorted((o1, o2) -> Integer.compare(o2.getProperty(), o1.getProperty())).toList();

        // Cap nhat phan thuong that
        UserInfo userInfo = userInfos.get(0);
        userInfo.setRocket(userInfo.getRocket() + 5);
        userInfo.setProperty(userInfo.getProperty() + 1000000);
        crudService.updateUserInfo(userInfo);

        Reward reward = new Reward();
        reward.setRewardMessage("Bạn đã nhận được phần thưởng top 1 server gồm 1000000 tiền, 5 tên lửa");
        reward.setUserId(userInfo.getUserId());

        crudService.createReward(reward);
    }

    @Scheduled(cron = CRON_STAMINA_RESET_AT_DAY)
    public void resetResourcePerDay() throws ExecutionException, InterruptedException {
        // reset stamina
        List<UserInfo> userInfos = crudService.getUserInfor();
        for (UserInfo userInfo: userInfos) {
            userInfo.setStamina(10);
            crudService.updateUserInfo(userInfo);
        }

        // reset item trong shop
        List<ItemRelation> itemRelations = crudService.getItemRelationList();
        for (ItemRelation itemRelation: itemRelations) {
            itemRelation.setShopBuy(false);
            crudService.updateItemRelation(itemRelation);
        }
    }
}
