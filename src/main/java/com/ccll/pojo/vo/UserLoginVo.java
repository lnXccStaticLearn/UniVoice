package com.ccll.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    private String username;
    private Long id;
    private String token;
    private String avatarUrl;

    public UserLoginVo(String username, String token, Long id) {
        this.username = username;
        this.token = token;
        this.id = id;
    }

}
