package com.ccll.controller;

import com.ccll.common.context.BaseContext;
import com.ccll.common.result.Result;
import com.ccll.entity.User;
import com.ccll.entity.UserDetailVO;
import com.ccll.pojo.dto.ChangeUserMsgDto;
import com.ccll.pojo.dto.UserLoginDto;
import com.ccll.pojo.dto.UserRegisterDto;
import com.ccll.pojo.vo.*;
import com.ccll.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserConteoller {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        log.info("用户登录：{}", userLoginDto);
        UserLoginVo userLoginVo = userService.login(userLoginDto);
        return Result.success(userLoginVo);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        log.info("用户注册{}", userRegisterDto);
        //TODO 添加邮箱验证
        UserRegisterVo userRegisterVo = userService.register(userRegisterDto);
        return Result.success(userRegisterVo);
    }

    @GetMapping("/space/spaceTop")
    public Result spaceTop() {
        log.info("用户空间顶部信息查询");
        SpaceTopVo spaceTopVo = userService.querySpaceTop();
        return Result.success(spaceTopVo);
    }

    @GetMapping("/space/spaceBottom")
    public Result spaceBottom() {
        log.info("用户空间底部信息查询");
        List<SpaceBottomItem> spaceBottomItems = userService.spaceBottom();

        return Result.success(spaceBottomItems);
    }

    @PostMapping("/changeUserMsg")
    public Result changeUserMsg(@RequestBody ChangeUserMsgDto changeUserMsgDto) {
        log.info("用户修改个人信息：{}", changeUserMsgDto);
        userService.changeUserMsg(changeUserMsgDto);
        return Result.success();

    }

    @GetMapping("/friendList")
    public Result userFriendList(String searchName) {
        log.info("用户好友列表查询{},+,{}", BaseContext.getCurrentId(),searchName);
        List<FriendListItem> friendList = userService.getFriendList(searchName);
        return Result.success(friendList);
    }



    @GetMapping("/friendMsg")
    public Result getUserDetail(Long friendId) {
        log.info("{}查找用户好友信息{}", BaseContext.getCurrentId(),friendId);
        UserDetailVO userDetailVO =  userService.getUserDetail(friendId);
        return  Result.success(userDetailVO);
    }

}
