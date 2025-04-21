package com.ccll.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoCountOfUser {
    private Integer submitCount;    //用户投稿数
    private Integer auditCount;
}
