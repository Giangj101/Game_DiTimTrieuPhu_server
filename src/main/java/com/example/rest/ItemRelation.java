package com.example.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRelation {
    private int amount;
    private boolean shopBuy;
    private String itemRelationId;
    private String itemId;
    private String userId;
}
