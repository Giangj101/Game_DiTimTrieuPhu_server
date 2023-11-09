package com.example.rest;

import lombok.Data;

@Data
public class UserInfo {
    private String id;
    private String displayName;
    private int level;
    private int levelProgress;
    private int property;
    private int stamina;
    private int rocket;
    private String userId;
}
