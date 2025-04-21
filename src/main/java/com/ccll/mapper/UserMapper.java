package com.ccll.mapper;

import com.ccll.entity.User;
import com.ccll.pojo.dto.ChangeUserMsgDto;
import com.ccll.pojo.dto.UserLoginDto;
import com.ccll.pojo.dto.UserRegisterDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from univoice.user where user_name=#{username} and password=#{password}")
    User selectForLogin(UserLoginDto userLoginDto);

    void insertForRegister(User user);

    @Select("select * from univoice.user where id =#{id}")
    User selectALLById(Long id);


    /**
     * 动态修改
     * @param user
     */
    void update(User user);
}
