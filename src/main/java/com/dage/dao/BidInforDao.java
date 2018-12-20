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
     * 我要投标弹窗展示信息
     * @param map
     * @return
     */
    @Select("<script>" +
            "select bidid,bidproject,biddeadline,bidrate,to_char(bidissuedate+5,'yyyy-MM-dd') as biddeaddate," +
            "to_char((bidamount - bidcurrentamount), '9999990.00') as bidendmoney " +
            "from bid_info where bidid= #{bidid} and bidstate='待投标'" +
            "</script>")
    Map tender(Map map);

    /**
     * 投标提交至投资记录表
     * @param map
     * @return
     */
    @Insert("insert into bid_submit(submitid,bidid,userid,bidamount,bidrate,biddate) " +
            " values('BSUB'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0), " +
            " #{BIDID},#{USERID},#{BIDAMOUNT},#{BIDRATE},sysdate )")
    int bidSubmit(Map map);

    /**
     * 根据标编号查询该标可投金额
     * @param map
     * @return
     */
    @Select("select bidamount-bidcurrentamount as canmoney from bid_info where bidid=#{BIDID} ")
    Integer canMoney(Map map);

    /**
     * 从用户账户表获取账户余额
     * @param map
     * @return
     */
    @Select("select availablebalance,receiveprincipal,receivenumbererest from user_account where userid=#{USERID} ")
    Integer balance(Map map);

}
