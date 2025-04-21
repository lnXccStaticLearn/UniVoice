package com.ccll.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentsPageVo {
    Long id;
    String text;
    Long userId;
    String username;
    LocalDateTime createTime;
    Long likeCount;
}

