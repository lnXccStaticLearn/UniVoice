package com.ccll.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatLog {
    private Long id;
    private Long senderId;
    private Long recipientId;
    private Long recipientName;
    private String chatText;
    private LocalDateTime createTime;
}
