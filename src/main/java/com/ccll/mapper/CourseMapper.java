package com.ccll.mapper;

import com.ccll.entity.Course;
import com.ccll.entity.TwoCountOfUser;
import com.ccll.pojo.vo.SimpleCoursePageVo;
import com.ccll.pojo.vo.SpaceBottomItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<SimpleCoursePageVo> pageSimple(String courseName,Integer pageSize,Integer start);

    @Select("select * from univoice.course where id=#{id}")
    Course queryDetailById(Long id);

    @Update("update univoice.course set top_score=#{topScore},low_score=#{lowScore},average_score=#{averageScore} where id=#{id}")
    void update3Score(Course course);

    @Select("select COUNT(*) as submitCount,COUNT(status=0 or null) as auditCount from univoice.course where create_user =#{userId}")
    TwoCountOfUser query2CountOfUser(Long userId);

    @Select("select course_name as name,status,create_time from univoice.course where create_user=#{userId} order by create_time desc")
    List<SpaceBottomItem> queryStatusOfUser(Long userId);

    @Insert("insert into course(course_name,elective,campus,status,teacher_name,midterm,finale,rating_structure,create_user,create_time,subject_name,academy_name) " +
            "values " +
            "(#{courseName},#{elective},#{campus},#{status},#{teacherName},#{midterm},#{finale},#{ratingStructure},#{createUser},#{createTime},#{subjectName},#{academyName})")
    void insert(Course course);
}
