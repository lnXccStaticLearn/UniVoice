package com.ccll.pojo.dto;

import lombok.Data;

@Data
public class AddCourseDto {
    private String courseName;
    private String teacherName;
    private String campus;
    private String subjectName;
    private String academyName;
    private Integer midtermPercent;
    private Integer finalePercent;     //期末评分占比
    private String elective;    //其中评分占比
    private String coverUrl;
}
