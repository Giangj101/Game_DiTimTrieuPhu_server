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

@PostMapping("/create")
    public String addReward(@RequestBody REWARD reward) throws InterruptedException, ExecutionException{
    return crudService.createCRUD(reward);
}
//
//@GetMapping("/test")
//    public ResponseEntity<String> testGetEndPoint(){
//        return ResponseEntity.ok("Test .....");
//    }
//@GetMapping("")
//    public CRUD getCRUD() throws InterruptedException, ExecutionException {
//    return crudService.getCRUD();
//}

//    private CRUD crud;
    private  List<CRUD> listCrud;
    private CRUD crud;
    @Scheduled(cron = "15 * * * * ?")
    public void updateTimeCRUD() throws ExecutionException, InterruptedException {
        System.out.println("Updating CRUD object at " + LocalDateTime.now());
        listCrud = crudService.getCRUDList();
        System.out.println(listCrud.get(0));


    }
    @GetMapping("")
    public List<CRUD> getCRUD() throws InterruptedException, ExecutionException {
    System.out.println("Send email 2 to producers to inform quantity sold items in " + LocalDateTime.now());

        if(listCrud == null){
            System.out.println("ko co gi");

        }
        return listCrud;
    }





}


