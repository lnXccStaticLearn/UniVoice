package com.ccll.controller;

import com.ccll.common.context.BaseContext;
import com.ccll.common.result.PageResult;
import com.ccll.common.result.PageResult2;
import com.ccll.common.result.Result;
import com.ccll.pojo.dto.ChatLogDTO;
import com.ccll.pojo.vo.ChatLogVo;
import com.ccll.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/chat")
@CrossOrigin(origins = "*")
public class ChatController {
    @Autowired
    private UserService userService;

    @PostMapping("/chatLog")
    public PageResult2 getChatLog(@RequestBody ChatLogDTO chatLogDTO) {
        log.info("{} 获取聊天记录 {}", BaseContext.getCurrentId(),chatLogDTO);
        List<ChatLogVo> chatLogVos =  userService.getChatLogList(chatLogDTO);
        return new PageResult2(chatLogVos.size(),chatLogVos);
    }

}
