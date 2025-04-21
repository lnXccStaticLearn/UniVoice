package com.ccll.mapper;

import com.ccll.entity.ToAverage;
import com.ccll.entity.UserCourseScore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserCourseScoreMapper {

    @Insert("insert into univoice.user_course_score(user_id, course_id, score, create_time) values (#{userId},#{courseId},#{score},#{createTime})")
    void insertOfAll(UserCourseScore userCourseScore);

    @Select("select SUM(score) as scoreSum,COUNT(score) as total from univoice.user_course_score where course_id=#{courseId}")
    ToAverage getScoreSum(Long courseId);
}
