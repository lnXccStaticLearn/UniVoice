package com.ccll.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatVo {
    Long senderId;
    Long recipientId;
    String chatText;
    String recipientName;
    LocalDateTime createTime;
}
