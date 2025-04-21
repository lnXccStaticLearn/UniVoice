package com.ccll.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comments {
    Long id;
    String text;
    Long userId;
    String username;
    Integer commentTarget;
    Long facilitiesId;
    Long courseId;
    String facilitiesName;
    String courseName;
    LocalDateTime createTime;
    Long likeCount;

}
