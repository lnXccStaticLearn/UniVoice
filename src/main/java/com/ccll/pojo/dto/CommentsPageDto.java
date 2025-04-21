package com.ccll.pojo.dto;

import lombok.Data;

@Data
public class CommentsPageDto {
    Long id;
    Integer page;
    Integer pageSize;
}
