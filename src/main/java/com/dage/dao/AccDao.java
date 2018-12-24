package com.dage.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:AccDao
 * discription:
 * author:ChenMing
 * creatTime:2018-12-22 10:19
 */
public interface AccDao {
    @Select("<script>select * from tb_system_account_flow <where>" +
            " <if test=\"USERID!=null and USERID!=''\"> and userid=#{USERID}</if>" +
            " <if test=\"FLOWTYPE!=null and FLOWTYPE!=''\"> and flowtype like '%'||#{FLOWTYPE}||'%'</if>" +
            " <if test=\"FLOWDATE!=null and FLOWDATE!=''\">  and flowdate like '%'||to_date(#{FLOWDATE},'yyyy-mm-dd')||'%'</if>" +
            " </where></script>")
    List<Map> getList(Map map);
    @Select("select flowtype from tb_system_account_flow group by flowtype ")
    List<Map> getFlowtype();
}
