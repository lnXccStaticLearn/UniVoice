package com.ccll.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendListItem {
    private String friendName;
    private String friendId;
    private String avatarUrl;
}
