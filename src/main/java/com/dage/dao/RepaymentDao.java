package com.dage.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:RepaymentDao
 * discription: 用于处理个人用户的还款数据和数据库的交互
 * author:CZP
 * createTime:2018-12-19 10:28
 */
public interface RepaymentDao {

    /**
     *根据用户Id查询用户的借款记录
     * 还款计划表
     * @param map
     * @return
     */
    @Select("<script>select to_char(biderpaydate,'yyyy-mm-dd') as biderpaydate,repayid,userid,bidrepayamount," +
            " to_char(biderpaydeaddate,'yyyy-mm-dd') as biderpaydeaddate," +
            " to_char(bidnextrepaydate,'yyyy-mm-dd') as bidnextrepaydate," +
            " bidrepayState, bidrepaynumber,bidrepaytotpmts,bidrepaymethod,bidrepayuserid" +
            " from bid_repay_info where userid=#{userId}" +
            "<if test=\" repayid!=null and repayid!=''\"> and repayid=#{repayid}</if>" +
            "<if test=\" type!=null and type!=''\"> and bidrepaystate=#{type}</if>" +
            "</script>")
    List<Map> getList(Map map);

}
