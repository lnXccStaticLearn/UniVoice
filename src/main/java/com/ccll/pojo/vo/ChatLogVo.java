package com.ccll.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatLogVo {
    private Long senderId;
    private LocalDateTime createTime;
    private String chatText;
}
