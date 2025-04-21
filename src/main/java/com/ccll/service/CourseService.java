package com.ccll.service;

import com.ccll.common.result.PageResult;
import com.ccll.common.result.PageResult1List;
import com.ccll.pojo.dto.*;
import com.ccll.pojo.vo.CommentsPageVo;
import com.ccll.pojo.vo.CourseDetailVo;

public interface CourseService {
    /**
     * 课程列表分页查询
     * @param coursePageDto
     * @return
     */
    PageResult pageCourse(CoursePageDto coursePageDto);

    /**
     * 课程详情分页查询
     * @param id
     * @return
     */
    CourseDetailVo queryCourseDetail(Long id);

    /**
     * 课程评论数据分页查询
     * @param commentsPageDto
     * @return
     */
    PageResult1List<CommentsPageVo> pageComments(CommentsPageDto commentsPageDto);

    /**
     * 用户发评
     * @param addCourseCommDto
     */
    void addComment(AddCourseCommDto addCourseCommDto);

    /**
     * 用户发表课程评分
     * @param addCourseScoreDto
     */
    void leaveCourseScore(AddCourseScoreDto addCourseScoreDto);

    /**
     * 用户投稿课程
     * @param addCourseDto
     */
    void addCourse(AddCourseDto addCourseDto);

}
