package com.ccll.mapper;

import com.ccll.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentsMapper {

    @Select("select * from univoice.comments where course_id=#{courseId} order by create_time desc limit #{start},#{pageSize}  ")
    List<Comments> pageCommentsOfCourse(Integer start, Integer pageSize, Long courseId);


    void addComment(Comments comments);

    @Select("select COUNT(*) from univoice.comments where user_id =#{userId}")
    Integer queryCommCountOfUser(Long userId);
}
