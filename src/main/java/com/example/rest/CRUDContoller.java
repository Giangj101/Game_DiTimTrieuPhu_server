package com.example.rest;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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


    private  List<REWARD> listReward;
    private List<CRUD> listCrud;
    private  List<ITEMRELATION> listItemRelation;
    private List<USERINFOR> listUserInfor;

    private REWARD reward;
    private CRUD crud;
    private ITEMRELATION itemrelation;
    private USERINFOR userinfor;

    @PostMapping("/reward")
    public String CreateReward(@RequestBody REWARD reward) throws InterruptedException, ExecutionException{
        return crudService.createReward(reward);
    }
    @PostMapping("/item_relation")
    public String CreateItemRelation(@RequestBody ITEMRELATION itemrelation) throws InterruptedException, ExecutionException{
        return crudService.createItemRelation(itemrelation);
    }

    @PostMapping("/user_infor")
    public String CreateUserInfor(@RequestBody USERINFOR userinfor) throws InterruptedException, ExecutionException{
        return crudService.createUserInfor(userinfor);
    }

    @PostMapping("/badge_relation")
    public String CreateCrud(@RequestBody CRUD crud) throws InterruptedException, ExecutionException{
        return crudService.createCrud(crud);
    }


    @GetMapping("/reward")
    public List<REWARD> getReward() throws InterruptedException, ExecutionException {
    System.out.println("Send email 2 to producers to inform quantity sold items in " + LocalDateTime.now());
        listReward = crudService.getRewardList();
        if(listReward == null){
            System.out.println("Danh sách reward rong");

        }
        return listReward;
    }

    @GetMapping("/item_relation")
    public List<ITEMRELATION> getListItemRelation() throws InterruptedException, ExecutionException {
        System.out.println("Send email 2 to producers to inform quantity sold items in " + LocalDateTime.now());
        listItemRelation = crudService.getItemRelationList();
        if(listItemRelation == null){
            System.out.println("Danh sách reward rong");

        }
        return listItemRelation;
    }

    @GetMapping("/user_infor")
    public List<USERINFOR> getListUserInfor() throws InterruptedException, ExecutionException {
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


