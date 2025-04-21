package com.ccll.mapper;

import com.ccll.pojo.dto.ChatLogDTO;
import com.ccll.pojo.dto.WSChatLogDTO;
import com.ccll.pojo.vo.ChatLogVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatLogMapper {
    @Select("select sender_id, chat_text,create_time from chatlog " +
            "where (sender_id=#{userId} and recipient_id = #{friendId}) or (sender_id=#{friendId} " +
            "and recipient_id = #{userId}) order by create_time desc limit #{page},#{pageSize} ")
    List<ChatLogVo> getLogForUser(ChatLogDTO chatLogDTO);

    @Insert("insert into chatlog(sender_id, recipient_id, chat_text, create_time)" +
            "values(#{senderId},#{recipientId},#{chatText},#{createTime}) ")
    void addChatLog(WSChatLogDTO wsChatLogDTO);
}
