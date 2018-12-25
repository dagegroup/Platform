package com.dage.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


import java.util.List;
import java.util.Map;

/**
 * className:UserInfoDao
 * discription:
 * author:zn
 * createTime:2018-12-11 16:29
 */
public interface UserInfoDao {

    /**
     * 获得用户信息表信息
     *
     * @return
     */
    @Select("select * from tb_user_info")
    List<Map> getList();

    /**
     * 通过电话号码查询用户信息
     *
     * @return
     */
    @Select("select * from tb_user_info where telephone=#{telephone}")
    List<Map> getListByPhone(String telephone);

    /**
     * 通过推荐人查询用户信息
     *
     * @param refereeName
     * @return
     */
    @Select("select * from tb_user_info where telephone=#{refereeName}")
    List<Map> getListByRefname(String refereeName);

    /**
     * 通过用户姓名查询用户信息
     *
     * @param userName
     * @return
     */
    @Select("select * from tb_user_info where userName=#{userName}")
    List<Map> getListByName(String userName);

    /**
     * 插入用户信息
     * @param map
     * @return
     */
    @Insert("insert into tb_user_info(userid,userName,password,telephone,refereeName,state) values(( select 'log'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*1000),4,0)from dual),#{userName},#{password},#{telephone},#{refereeName},#{state})")
    int insetInfo(Map map);

}
