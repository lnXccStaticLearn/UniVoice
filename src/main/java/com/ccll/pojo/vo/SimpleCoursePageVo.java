package com.ccll.pojo.vo;

import lombok.Data;

@Data
public class SimpleCoursePageVo {
    Long id;
    String courseName;
    String teacherName;
    String imageUrl;
    Float averageScore;
}
