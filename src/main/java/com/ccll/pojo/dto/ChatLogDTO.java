package com.ccll.pojo.dto;

import lombok.Data;

@Data
public class ChatLogDTO {
    private Long userId;
    private Long friendId;
    private Integer page;
    private Integer pageSize;
    private String searchParam;
}
