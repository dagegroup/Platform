package com.dage.dao;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * className:UserDao
 * discription:
 * author:ChenMing
 * creatTime:2018-12-10 16:39
 */
public interface UserDao {
    @Select("select  telephone,userName,password from TB_USER_INFO where telephone=#{param1} and password=#{param2} ")
    Map getByuserName(String telephone,String password);
}
