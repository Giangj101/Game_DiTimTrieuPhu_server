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


    public void createReward(Reward reward) throws ExecutionException,InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionsApiFuture = dbFirestore.collection(COLLECTION_reward).add(reward);
    }

// CRUD là badge_relation
    public String createCrud(CRUD crud)throws ExecutionException,InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_badge_relation).document(crud.getBadgeRelationId()).set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String createItemRelation(ItemRelation itemrelation)throws ExecutionException,InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_item_relation).document(itemrelation.getItemRelationId()).set(itemrelation);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String createUserInfor(UserInfo userInfo)throws ExecutionException,InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_user_infor).document(userInfo.getUserId()).set(userInfo);
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
    public List<Reward> getRewardList() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_reward);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        List<Reward> rewardList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            if (document.exists()) {
                Reward reward = document.toObject(Reward.class);
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

    public List<ItemRelation> getItemRelationList() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_item_relation);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        List<ItemRelation> itemRelationList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            if (document.exists()) {
                ItemRelation itemrelation = document.toObject(ItemRelation.class);
                itemRelationList.add(itemrelation);
            }
        }

        return itemRelationList;
    }

    public List<UserInfo> getUserInfor() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_user_infor);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        List<UserInfo> userInfoList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            if (document.exists()) {
                UserInfo userInfo = document.toObject(UserInfo.class);
                userInfoList.add(userInfo);
            }
        }

        return userInfoList;
    }

    public ApiFuture<WriteResult> updateUserInfo(UserInfo info) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        return dbFirestore.collection(COLLECTION_user_infor).document(info.getId()).set(info);
    }

    public ApiFuture<WriteResult> updateItemRelation(ItemRelation itemRelation) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        return dbFirestore.collection(COLLECTION_item_relation).document(itemRelation.getItemRelationId()).set(itemRelation);
    }
}
