package com.example.rest;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class CRUD {
    private String badgeId;
    private String userId;
    private Boolean equipped;
    private String badgeRelationId;

    public CRUD(Reward reward) {

    }

}