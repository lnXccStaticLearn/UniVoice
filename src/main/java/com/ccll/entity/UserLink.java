package com.ccll.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLink {
    private Long id;
    private Long userId;
    private Long friendId;
    private String friendName;

}
