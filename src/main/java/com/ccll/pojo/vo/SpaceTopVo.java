package com.ccll.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceTopVo {

    private String username;
    private String avatarUrl;
    private Integer commentCount;
    private Integer submitCount;
    private Integer auditCount;

}
