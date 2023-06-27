package com.elm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.elm.po.User;

@Mapper
public interface UserMapper {

    @Select("select count(*) from user where userName=#{username}")
    public int GetUserByName(String username);

    @Select("select * from user where userId=#{userId} and password=#{password}")
    public User GetUserByIdByPass(User user);

    @Select("select count(*) from user where userId=#{userId}")
    public int getUserById(String userId);

    @Insert("Insert into user values(#{userId},#{password},#{userName},#{userSex},null,1)")
    public int saveUser(User user);
}
