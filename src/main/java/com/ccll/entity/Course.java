package com.ccll.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Course {
    private Long id;
    private String courseName;
    private Integer elective;
    private String campus;
    private Integer status;
    private String rejection_reason;
    private Long teacherId;
    private String teacherName;
    private Float topScore ;
    private Float lowScore ;
    private Float averageScore ;
    private String classroom ;
    private Integer midterm;
    private Integer finale;
    private String ratingStructure;
    private Long createUser;
    private LocalDateTime createTime;
    private Long updateAdmin;
    private LocalDateTime updateTime;
    private String subjectName;
    private String academyName;

}
