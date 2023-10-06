package com.example.rest;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CRUDService {
    private static final String COLLECTION_badge_relation = "badge_relation";
    private static final String COLLECTION_reward = "reward";
    private static final String COLLECTION_item_relation = "bonus_items_relation";
    private static final String COLLECTION_user_infor = "user_info";


    public String createReward(REWARD reward)throws ExecutionException,InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_reward).document(reward.getRewardId()).set(reward);
        return collectionsApiFuture.get().getUpdateTime().toString();


    }
// CRUD là badge_relation
    public String createCrud(CRUD crud)throws ExecutionException,InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_badge_relation).document(crud.getBadgeRelationId()).set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();


    }
    public String createItemRelation(ITEMRELATION itemrelation)throws ExecutionException,InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_item_relation).document(itemrelation.getItemRelationId()).set(itemrelation);
        return collectionsApiFuture.get().getUpdateTime().toString();


    }

    public String createUserInfor(USERINFOR userinfor)throws ExecutionException,InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_user_infor).document(userinfor.getUserId()).set(userinfor);
        return collectionsApiFuture.get().getUpdateTime().toString();


    }






//    public CRUD getCRUD() throws ExecutionException, InterruptedException {
//        Firestore dbFireStore = FirestoreClient.getFirestore();
//            DocumentReference documentReference = dbFireStore.collection(COLLECTION_NAME).document("G07IiaaLvESyqd9aVFy5");
//        ApiFuture<DocumentSnapshot> future = documentReference.get();
//        DocumentSnapshot document= future.get();
//        CRUD crud ;
//        if(document.exists()){
//            crud=document.toObject(CRUD.class);
//            return crud ;
//        }
//        return null;
//    }
    public List<REWARD> getRewardList() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_reward);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        List<REWARD> rewardList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            if (document.exists()) {
                REWARD reward = document.toObject(REWARD.class);
                rewardList.add(reward);
            }
        }

        return rewardList;
    }

    public List<CRUD> getCrudList() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_badge_relation);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        List<CRUD> crudList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            if (document.exists()) {
                CRUD crud = document.toObject(CRUD.class);
                crudList.add(crud);
            }
        }

        return crudList;
    }

    public List<ITEMRELATION> getItemRelationList() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_item_relation);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        List<ITEMRELATION> itemRelationList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            if (document.exists()) {
                ITEMRELATION itemrelation = document.toObject(ITEMRELATION.class);
                itemRelationList.add(itemrelation);
            }
        }

        return itemRelationList;
    }

    public List<USERINFOR> getUserInfor() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_user_infor);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        List<USERINFOR> userinforList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            if (document.exists()) {
                USERINFOR userinfor = document.toObject(USERINFOR.class);
                userinforList.add(userinfor);
            }
        }

        return userinforList;
    }




}
