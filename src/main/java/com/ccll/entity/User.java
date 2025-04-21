package com.ccll.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String mail;
    private String createTime;
    private String updateTime;
    private String avatarUrl;
}
