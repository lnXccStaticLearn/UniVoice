package com.ccll.mapper;

import com.ccll.entity.UserLink;
import com.ccll.pojo.vo.FriendListItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserLinkMapper {
    @Select("select * from user_link where user_id = #{userId}")
    List<UserLink> selectAllByUserId(Long userId);

    @Select("select user_link.friend_id as friendId,user_link.friend_name as friendName,user.avatar_url as avatarUrl from user_link left join user on user_link.friend_id = user.id where user_link.user_id = #{userId} and friend_name like #{searchName}")
    List<FriendListItem> selectFriendByUserId(Long userId,String searchName);
}
