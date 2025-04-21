package com.ccll.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddCourseCommDto {
    private Long id;
    private Long courseId;
    private String text;
    private String username;
    LocalDateTime createTime;
}
