package com.ccll.service.Impl;

import com.ccll.common.context.BaseContext;
import com.ccll.common.result.PageResult;
import com.ccll.common.result.PageResult1List;
import com.ccll.entity.*;
import com.ccll.mapper.CommentsMapper;
import com.ccll.mapper.CourseMapper;
import com.ccll.mapper.UserCourseScoreMapper;
import com.ccll.mapper.UserMapper;
import com.ccll.pojo.dto.*;
import com.ccll.pojo.vo.CommentsPageVo;
import com.ccll.pojo.vo.CourseDetailVo;
import com.ccll.pojo.vo.SimpleCoursePageVo;
import com.ccll.service.CourseService;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private UserCourseScoreMapper userCourseScoreMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult pageCourse(CoursePageDto coursePageDto) {
        //构造查询字符串
        String nameOfSeach = "%";
        if (coursePageDto.getCourseName() != null) {
            for (int i = 0; i < coursePageDto.getCourseName().length(); i++) {
                nameOfSeach += coursePageDto.getCourseName().charAt(i);
                nameOfSeach += "%";
            }
            coursePageDto.setCourseName(nameOfSeach);
        }

        //计算起始索引
        Integer start = (coursePageDto.getPage() - 1) * coursePageDto.getPageSize();
        //将所需的所有信息查询出来
        List<SimpleCoursePageVo> result = courseMapper.pageSimple(coursePageDto.getCourseName(), coursePageDto.getPageSize(), start);
        int total = result.size();
        //TODO 插入教师名称
        for (SimpleCoursePageVo simpleCoursePageVo : result) {
            simpleCoursePageVo.setTeacherName("教师名称待录入");
        }

        //将其组成一个二维数组，每5个一组
        List<List<SimpleCoursePageVo>> lists = new ArrayList<>();
        List<SimpleCoursePageVo> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {

            if (i != 0 && i % 5 == 0) {      //当到每一列的末尾时，将该列加入二维数组，并准备下一行
                lists.add(list);
                list = new ArrayList<>();   //list拷贝是地址传递，要重新new一个，用clean会把二维列表中的一起清空
            }
            if (i == result.size() - 1) {   //处理最后一列不是整列的情况
                lists.add(list);
            }
            list.add(result.get(i));
        }

        return new PageResult<SimpleCoursePageVo>(total, lists);
    }

    @Override
    public CourseDetailVo queryCourseDetail(Long id) {
        Course course = courseMapper.queryDetailById(id);
        CourseDetailVo courseDetailVo = new CourseDetailVo();
        BeanUtils.copyProperties(course, courseDetailVo);
        return courseDetailVo;
    }

    @Override
    public PageResult1List pageComments(CommentsPageDto commentsPageDto) {
        //计算课程索引
        Integer start = (commentsPageDto.getPage() - 1) * commentsPageDto.getPageSize();
        //查询评论列表
        List<Comments> commentsList = commentsMapper.pageCommentsOfCourse(start, commentsPageDto.getPageSize(), commentsPageDto.getId());
        //进行敏感词处理
        for (Comments comments : commentsList) {
            comments.setText(SensitiveWordHelper.replace(comments.getText()));
        }
        //封装返回
        List<CommentsPageVo> commentsPageVos = new ArrayList<>();
        for (Comments comments : commentsList) {
            CommentsPageVo commentsPageVo = new CommentsPageVo();
            BeanUtils.copyProperties(comments, commentsPageVo);
            commentsPageVos.add(commentsPageVo);
        }
        return new PageResult1List<CommentsPageVo>(commentsPageVos.size(), commentsPageVos);
    }

    @Override
    public void addComment(AddCourseCommDto addCourseCommDto) {
        //插入课程Id、标志位、创建时间和课程名称
        //查询出课程信息
        Course course = courseMapper.queryDetailById(addCourseCommDto.getCourseId());
        //创建Comments
        Comments comments = new Comments();
        BeanUtils.copyProperties(addCourseCommDto, comments);
        comments.setCommentTarget(1);
        comments.setUsername(addCourseCommDto.getUsername());
        comments.setUserId(BaseContext.getCurrentId());
        comments.setCreateTime(LocalDateTime.now());
        comments.setLikeCount(0L);
        comments.setCourseName(course.getCourseName());
        comments.setFacilitiesId(-1L);
        comments.setFacilitiesName("no");
        commentsMapper.addComment(comments);
    }

    @Override
    public void leaveCourseScore(AddCourseScoreDto addCourseScoreDto) {
        //获取用户id插入DTO中
        addCourseScoreDto.setId(BaseContext.getCurrentId());
        //TODO如果评分不在范围内，用全局异常处理器返回异常

        //TODO如果该用户近期发表过评分，用全局异常处理器返回异常

        //向用户与评分信息表中插入该条记录
        UserCourseScore userCourseScore = new UserCourseScore();
        userCourseScore.setCourseId(addCourseScoreDto.getCourseId());
        userCourseScore.setScore(addCourseScoreDto.getScore());
        userCourseScore.setUserId(addCourseScoreDto.getId());
        userCourseScore.setCreateTime(LocalDateTime.now());
        userCourseScoreMapper.insertOfAll(userCourseScore);
        //获取该课程所有评分，并计算出平均分
        ToAverage toAverage = userCourseScoreMapper.getScoreSum(addCourseScoreDto.getCourseId());
        Float average = Float.valueOf('0');
        if (toAverage.getTotal() != null && toAverage.getTotal() > 0) {
            average = toAverage.getScoreSum() / toAverage.getTotal();
        }


        //查询数据库中改课程信息，更新最低分、最高分和平均分
        Course course = courseMapper.queryDetailById(addCourseScoreDto.getCourseId());
        if (course.getLowScore() > addCourseScoreDto.getScore()) {
            course.setLowScore(addCourseScoreDto.getScore());
        }
        if (course.getTopScore() < addCourseScoreDto.getScore()) {
            course.setTopScore(addCourseScoreDto.getScore());
        }
        course.setAverageScore(average);
        courseMapper.update3Score(course);

    }

    @Override
    public void addCourse(AddCourseDto addCourseDto) {
        //构建插入的课程数据
        Course course = new Course();
        //根据用户输入的评分占比，得出评分构成与有无期中期末考试
        if (addCourseDto.getMidtermPercent() > 0) {
            course.setMidterm(1);
        } else {
            course.setMidterm(0);
        }
        if (addCourseDto.getFinalePercent() > 0) {
            course.setFinale(1);
        } else {
            course.setFinale(0);
        }
        course.setRatingStructure("期中" + addCourseDto.getMidtermPercent() + "%"
                + "期末" + addCourseDto.getFinalePercent() + "%"
                + "平时" + (100 - (addCourseDto.getMidtermPercent() + addCourseDto.getFinalePercent())));

        BeanUtils.copyProperties(addCourseDto, course);
        course.setCreateTime(LocalDateTime.now());
        course.setStatus(0);
        course.setCreateUser(BaseContext.getCurrentId());
        courseMapper.insert(course);
    }
}
