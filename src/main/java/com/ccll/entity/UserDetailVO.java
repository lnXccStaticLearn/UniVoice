package com.ccll.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailVO {
    private Long id;
    private String username;
    private String mail;
    private String createTime;
    private String avatarUrl;
}
