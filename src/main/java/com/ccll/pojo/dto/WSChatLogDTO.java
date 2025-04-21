package com.ccll.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WSChatLogDTO {
    private Long senderId;
    private Long recipientId;
    private  String chatText;
    private LocalDateTime createTime;

    public WSChatLogDTO(Long senderId, String chatText, Long recipientId) {
        this.senderId = senderId;
        this.chatText = chatText;
        this.recipientId = recipientId;
    }
}
