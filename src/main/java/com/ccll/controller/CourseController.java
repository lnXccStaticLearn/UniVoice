package com.ccll.controller;

import com.ccll.common.properties.JwtProperties;
import com.ccll.common.result.PageResult;
import com.ccll.common.result.PageResult1List;
import com.ccll.common.result.Result;
import com.ccll.pojo.dto.*;
import com.ccll.pojo.vo.CommentsPageVo;
import com.ccll.pojo.vo.CourseDetailVo;
import com.ccll.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user/course")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 返回课程分页查询结果
     * @param coursePageDto
     * @return
     */
    @GetMapping("/pageCourse")
    public PageResult pageCourse(CoursePageDto coursePageDto) {
        log.info("课程分页查询:{}", coursePageDto);
        PageResult pageResult = courseService.pageCourse(coursePageDto);
        return pageResult;

    }

    @GetMapping("/detail")
    public Result courseDetail(Long id) {
        log.info("获取课程详情:{}", id);
        CourseDetailVo courseDetailVo = courseService.queryCourseDetail(id);
        return Result.success(courseDetailVo);
    }

    /**
     * 课程评论分页查询
     * @return
     */
    @GetMapping("/pageComments")
    public PageResult1List pageComments(CommentsPageDto commentsPageDto) throws InterruptedException {
        log.info("课程评论分页查询：{}", commentsPageDto);
        PageResult1List<CommentsPageVo> pageResult = courseService.pageComments(commentsPageDto);
        return pageResult;
    }

    @PostMapping("/permission/addComment")
    public Result addComment(@RequestBody AddCourseCommDto addCourseCommDto) {
        log.info("发布课程评论{}", addCourseCommDto);
        courseService.addComment(addCourseCommDto);
        return Result.success();

    }

    @GetMapping("/permission/leaveCourseScore")
    public Result leaveCourseScore(AddCourseScoreDto addCourseScoreDto){
        log.info("发表课程评分{}", addCourseScoreDto);
        courseService.leaveCourseScore(addCourseScoreDto);
        return Result.success();
    }

    /**
     * 用户投稿课程接口
     * @param addCourseDto
     * @return
     */
    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody AddCourseDto addCourseDto) {
        log.info("用户上传投稿课程{}", addCourseDto);
        courseService.addCourse(addCourseDto);
        return Result.success();

    }
}
