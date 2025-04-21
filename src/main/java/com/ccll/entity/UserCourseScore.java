package com.ccll.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCourseScore {
    private Long id;
    private Long userId;
    private Long courseId;
    private Float score;
    private LocalDateTime createTime;
}
