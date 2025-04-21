package com.ccll.service;

import com.ccll.entity.User;
import com.ccll.entity.UserDetailVO;
import com.ccll.pojo.dto.ChangeUserMsgDto;
import com.ccll.pojo.dto.ChatLogDTO;
import com.ccll.pojo.dto.UserLoginDto;
import com.ccll.pojo.dto.UserRegisterDto;
import com.ccll.pojo.vo.*;

import java.util.List;

public interface UserService {
    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    UserLoginVo login(UserLoginDto userLoginDto);

    /**
     * 用户注册
     * @param userRegisterDto
     * @return
     */
    UserRegisterVo register(UserRegisterDto userRegisterDto);

    /**
     * 查询用户空间上半部分信息
     * @return
     */
    SpaceTopVo querySpaceTop();

    /**
     * 查询用户空间下半部分信息
     * @return
     */
    List<SpaceBottomItem> spaceBottom();

    /**
     * 用户修改个人信息
     * @param changeUserMsgDto
     */
    void changeUserMsg(ChangeUserMsgDto changeUserMsgDto);

    /**
     * 获取用户好友列表
     */
    List<FriendListItem> getFriendList(String searchName);

    /**
     * 获取用户与id为friend的聊天记录
     * @param friend
     * @return
     */
    List<ChatLogVo> getChatLogList(ChatLogDTO chatLogDTO);

    /**
     * 获取好友详细信息
     * @param friendId
     * @return
     */
    UserDetailVO getUserDetail(Long friendId);
}
