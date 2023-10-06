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


    public String createCRUD(REWARD reward)throws ExecutionException,InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_reward).document(reward.getRewardId()).set(reward);
        return collectionsApiFuture.get().getUpdateTime().toString();


    }

    public String deleteCRUD(String documentId){
            Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COLLECTION_reward).document(documentId).delete();
        return "successfully deleted" + documentId ;
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
    public List<CRUD> getCRUDList() throws ExecutionException, InterruptedException {
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




}
