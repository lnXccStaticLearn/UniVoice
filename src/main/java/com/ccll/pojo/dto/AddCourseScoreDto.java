package com.ccll.pojo.dto;

import lombok.Data;

@Data
public class AddCourseScoreDto {
    private Long courseId;
    private Long id;
    private Float score;
}
