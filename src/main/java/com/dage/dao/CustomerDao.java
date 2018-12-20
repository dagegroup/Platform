package com.dage.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:CustomerDao
 * discription:
 * author:zn
 * createTime:2018-12-17 09:04
 */
public interface CustomerDao {

    /**
     * 根据userid获取认证表详情
     * @param
     * @return
     */
    @Select("<script>select r.realnameid,r.userid,r.realname,r.sex,r.address,r.email,r.idnumber,u.telephone,u.state from tb_realname_certification r left join tb_user_info u on r.userid=u.userid  " +
            "<if test=\" USERID!=null and USERID!=''\"> and r.userid = #{USERID}</if>" +
            "<if test=\" REALNAME!=null and REALNAME!=''\"> and r.realname like  '%'||#{REALNAME}||'%'</if>" +
            "</script>")
    List<Map> getlist(Map map);

}
