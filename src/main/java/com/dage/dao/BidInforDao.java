package com.dage.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * className:BidInforDao
 * discription:
 * author:lc
 * createTime:2018-12-14 11:05
 */
@Repository
public interface BidInforDao {

    /**
     * 项目详情页：获取标信息
     *
     * @return
     */
    @Select(value = "select bidid,userid,auditid,bidproject,to_char(bidamount, '9999990.00') as bidamount,to_char(bidcurrentamount, '9999990.00') as bidcurrentamount," +
            "bidrepaymentmethod,bidrate,100*round(bidcurrentamount/bidamount,4)||'%' as bidschedule,biddeadline," +
            "to_char(bidissuedate,'yyyy-MM-dd') as bidissuedate,biddeadday,to_char(bidapplydate,'yyyy-MM-dd') as bidapplydate," +
            "to_char(biddeaddate,'yyyy-MM-dd') as biddeaddate,biddesc,bidtype,bidstate,bidmoney," +
            "round(to_number(biddeaddate - sysdate)) as bidendday,to_char((bidamount - bidcurrentamount), '9999990.00') as bidendmoney" +
            " from bid_info where bidid=#{bidid}")
    List<Map> getList(Map map);

    /**
     * 项目详情页：借款人信息
     * @param map
     * @return
     */
    @Select("<script> " +
            "select r.realname,r.academic,r.marriage,r.address,r.income " +
            "from tb_user_info u  " +
            "left join tb_realname_certification r on u.realnameid=r.realnameid  " +
            "where u.bidid=#{bidid} " +
            "</script>")
    List<Map> getUserList(Map map);

    /**
     * 项目详情页：投资记录
     * @param map
     * @return
     */
    @Select("<script> " +
            "select s.userid,s.bidid,s.bidamount,to_char(s.biddate,'yyyy-mm-dd hh24:mi:ss') as biddate," +
            "r.realname from bid_submit s left join tb_realname_certification r on s.userid=r.userid " +
            "where s.bidid=#{bidid} order by biddate desc" +
            "</script>")
    List<Map> getUserInvest(Map map);

    /**
     * 项目详情页：还款记录
     * @param map
     * @return
     */
    @Select("<script> " +
            "select u.userid,to_char(r.biderpaydeaddate,'yyyy-mm-dd hh24:mi:ss') as bidrepaydeaddate," +
            "to_char(r.biderpaydate,'yyyy-mm-dd hh24:mi:ss') as bidrepaydate," +
            "r.bidrepaytotpmts,r.bidrepaynumber,r.bidrepayamount,r.bidrepaystate from  tb_user_info u " +
            "left join bid_repay_info r on u.userid=r.userid where u.bidid=#{bidid} " +
            "order by bidrepaydeaddate desc" +
            "</script>")
    List<Map> getRefundRecord(Map map);

    /**
     * 我要投标
     * @param map
     * @return
     */
    @Select("<script>" +
            "select bidid,bidproject,bidrate,to_char(bidissuedate+5,'yyyy-MM-dd') as biddeaddate," +
            "to_char((bidamount - bidcurrentamount), '9999990.00') as bidendmoney " +
            "from bid_info where bidid= #{bidid} and bidstate='待投标'" +
            "</script>")
    Map tender(Map map);

    /**
     * 根据session里的username得到realname
     * @param username
     * @return
     */
    @Select("<script>" +
            "select r.realname from tb_user_info u left join " +
            "tb_realname_certification r on u.userid=r.userid " +
            "where username=#{username}' " +
            "</script>")
    Map getUser(@Param("username") String username);
}
