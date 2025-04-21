package com.ccll.pojo.dto;

import lombok.Data;

@Data
public class CoursePageDto {
    Integer page;
    Integer pageSize;
    String courseName;
}
