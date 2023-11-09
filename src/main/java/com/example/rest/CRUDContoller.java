package com.example.rest;

import com.google.cloud.firestore.Firestore;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class CRUDContoller {

    private Firestore firestore;
    public CRUDService crudService;
    public CRUDContoller(CRUDService crudService){

        this.crudService=crudService;
    }


    private  List<Reward> listReward;
    private List<CRUD> listCrud;
    private  List<ItemRelation> listItemRelation;
    private List<UserInfo> listUserInfor;
//đoạn này viết nhầm
    private Reward reward;
    private CRUD crud;
    private ItemRelation itemrelation;
    private UserInfo userInfo;
//
    @PostMapping("/item_relation")
    public String CreateItemRelation(@RequestBody ItemRelation itemrelation) throws InterruptedException, ExecutionException{
        return crudService.createItemRelation(itemrelation);
    }

    @PostMapping("/user_infor")
    public String CreateUserInfor(@RequestBody UserInfo userInfo) throws InterruptedException, ExecutionException{
        return crudService.createUserInfor(userInfo);
    }

    @PostMapping("/badge_relation")
    public String CreateCrud(@RequestBody CRUD crud) throws InterruptedException, ExecutionException{
        return crudService.createCrud(crud);
    }


    @GetMapping("/reward")
    public List<Reward> getReward() throws InterruptedException, ExecutionException {
    System.out.println("Send email 2 to producers to inform quantity sold items in " + LocalDateTime.now());
        listReward = crudService.getRewardList();
        if(listReward == null){
            System.out.println("Danh sách reward rong");

        }
        return listReward;
    }

    @GetMapping("/item_relation")
    public List<ItemRelation> getListItemRelation() throws InterruptedException, ExecutionException {
        System.out.println("Send email 2 to producers to inform quantity sold items in " + LocalDateTime.now());
        listItemRelation = crudService.getItemRelationList();
        if(listItemRelation == null){
            System.out.println("Danh sách reward rong");

        }
        return listItemRelation;
    }

    @GetMapping("/user_infor")
    public List<UserInfo> getListUserInfor() throws InterruptedException, ExecutionException {
        System.out.println("Send email 2 to producers to inform quantity sold items in " + LocalDateTime.now());
        listUserInfor = crudService.getUserInfor();
        if(listUserInfor == null){
            System.out.println("Danh sách reward rong");

        }
        return listUserInfor;
    }

//đối tượng CRUD là badge_relation
    @GetMapping("/badge_relation")
    public List<CRUD> getCrud() throws InterruptedException, ExecutionException {
        System.out.println("Send email 2 to producers to inform quantity sold items in " + LocalDateTime.now());
        listCrud = crudService.getCrudList();
        if(listCrud == null){
            System.out.println("Danh sách badge_relation rỗng");

        }
        System.out.println("danh sách badge_relation: "+listCrud);
        return listCrud;
    }





}


