package com.ccll.service.Impl;

import com.ccll.common.Exception.LoginException;
import com.ccll.common.constant.JwtClaimsConstant;
import com.ccll.common.constant.MessageConstant;
import com.ccll.common.context.BaseContext;
import com.ccll.common.properties.JwtProperties;
import com.ccll.common.utils.JwtUtil;
import com.ccll.entity.ChatLog;
import com.ccll.entity.TwoCountOfUser;
import com.ccll.entity.User;
import com.ccll.entity.UserDetailVO;
import com.ccll.mapper.*;
import com.ccll.pojo.dto.ChangeUserMsgDto;
import com.ccll.pojo.dto.ChatLogDTO;
import com.ccll.pojo.dto.UserLoginDto;
import com.ccll.pojo.dto.UserRegisterDto;
import com.ccll.pojo.vo.*;
import com.ccll.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserLinkMapper userLinkMapper;
    @Autowired
    private ChatLogMapper chatLogMapper;

    @Override
    public UserLoginVo login(UserLoginDto userLoginDto) {
        //根据用户名与密码查询用户
        User user = userMapper.selectForLogin(userLoginDto);

        //如用户不存在则抛出异常
        if (user == null) {
            throw new LoginException(MessageConstant.LOGIN_MSG_ERR);
        }

        //用户存在则构造jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String jwt = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        //封装为Vo返回
        UserLoginVo userLoginVo = new UserLoginVo();
        BeanUtils.copyProperties(user, userLoginVo);
        userLoginVo.setToken(jwt);
        return userLoginVo;


    }

    @Override
    public UserRegisterVo register(UserRegisterDto userRegisterDto) {
        //创建用户并添加进数据库中
        User user = new User();
        BeanUtils.copyProperties(userRegisterDto, user);
        userMapper.insertForRegister(user);
        //TODO 判断邮箱唯一
        //生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, userRegisterDto.getId());
        String jwt = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        //封装VO返回
        UserRegisterVo userRegisterVo = new UserRegisterVo();
        userRegisterVo.setId(user.getId());
        userRegisterVo.setUsername(user.getUsername());
        userRegisterVo.setToken(jwt);
        return userRegisterVo;
    }

    @Override
    public SpaceTopVo querySpaceTop() {
        //查询用户表，获得用户头像与用户名
        User user = userMapper.selectALLById(BaseContext.getCurrentId());
        //查询评论表，获取用户总评论数
        Integer commCount = commentsMapper.queryCommCountOfUser(BaseContext.getCurrentId());
        //TODO查询设施表与课程表查找用户投稿数与待审核数，设施表还未建立
        TwoCountOfUser twoCountOfUser = courseMapper.query2CountOfUser(BaseContext.getCurrentId());
        //封装为VO返回
        SpaceTopVo spaceTopVo = new SpaceTopVo();
        BeanUtils.copyProperties(twoCountOfUser, spaceTopVo);
        spaceTopVo.setCommentCount(commCount);
        BeanUtils.copyProperties(user, spaceTopVo);
        return spaceTopVo;
    }

    @Override
    public List<SpaceBottomItem> spaceBottom() {
        //TODO查询用户投稿的课程、设施的名字，投稿时间和状态，设施表还未建立
        List<SpaceBottomItem> spaceBottomItems = courseMapper.queryStatusOfUser(BaseContext.getCurrentId());
        return spaceBottomItems;
    }

    @Override
    public void changeUserMsg(ChangeUserMsgDto changeUserMsgDto) {
        User user = new User();
        BeanUtils.copyProperties(changeUserMsgDto, user);
        user.setId(BaseContext.getCurrentId());
        userMapper.update(user);
    }

    @Override
    public List<FriendListItem> getFriendList(String searchName) {
        String searchNameL = "%";
        for (int i = 0; i < searchName.length(); i++) {
            searchNameL += searchName.charAt(i);
        }
        searchNameL+="%";
        List<FriendListItem> friends =  userLinkMapper.selectFriendByUserId(BaseContext.getCurrentId(),searchNameL);
        return friends;
    }

    @Override
    public List<ChatLogVo> getChatLogList(ChatLogDTO chatLogDTO) {
        chatLogDTO.setUserId(BaseContext.getCurrentId());
        //计算起始索引
        chatLogDTO.setPage((chatLogDTO.getPage()-1)*chatLogDTO.getPageSize());
        List<ChatLogVo> chatLogVos = chatLogMapper.getLogForUser(chatLogDTO);
        return chatLogVos;
    }

    @Override
    public UserDetailVO getUserDetail(Long friendId) {
        UserDetailVO userDetailVO = new UserDetailVO();
        User user = userMapper.selectALLById(friendId);
        BeanUtils.copyProperties(user, userDetailVO);
        return userDetailVO;
    }

}
