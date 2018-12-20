package com.dage.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * className:UserDao
 * discription:
 * author:ChenMing
 * creatTime:2018-12-10 16:39
 */
public interface UserDao {
    @Select("select  userid,telephone,userName,password from TB_USER_INFO where telephone=#{param1} and password=#{param2} ")
    Map getByuserName(String telephone,String password);
    @Select("select userid from user_account where userid=#{userid}")
    String getUserbyUserid(String userid);
    @Insert("insert into user_account(accountid,userid) values('A'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0),#{userid})")
    int adduserid(String userid);
}
