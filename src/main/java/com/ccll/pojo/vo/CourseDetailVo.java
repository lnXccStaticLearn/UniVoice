package com.ccll.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailVo {
    String courseName;
    String teacherName;
    Integer midterm;
    Float averageScore ;
    Integer finale;
    String ratingStructure;
    Float topScore;
    Float lowScore;
}
