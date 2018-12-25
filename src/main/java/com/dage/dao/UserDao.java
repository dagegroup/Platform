package com.dage.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:UserDao
 * discription:
 * author:CZP
 * creatTime:2018-12-19 14:39
 */
public interface UserDao {

    /**
     * 查询员工账户和密码
     * @param telephone
     * @param password
     * @return
     */
    @Select("select  userid,telephone,userName,password from TB_USER_INFO where telephone=#{param1} and password=#{param2} ")
    Map getByuserName(String telephone, String password);


    /**
     * 根据session里 获取的用户姓名 查询用户的信息
     * @param userName
     * @return
     */
    @Select("select * from tb_user_info where username=#{userName}")
    Map getUserByUserName(String userName);

    /**
     * 根据用户id查询用户的个人信息
     * @param map
     * @return
     */
    @Select("select * from tb_user_info where userid=#{userId}")
    List<Map> getUser(Map map);

    /**
     * 根据用户编号查询用户回款计划
     *
     * 还款计划表
     * @param
     * @return
     */
    @Select("<script>select userid,bidrepayuserid,BIDREPAYMETHOD,to_char(biderpaydeaddate,'yyyy-mm-dd') as bidrepaydate,bidrepaynumber,bidrepayamount" +
            " from bid_repay_info where userid=#{userId}" +
            "<if test=\" time1!=null and time1!='' \"> and to_char(biderpaydate,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')</if>"+
            "<if test=\" time2!=null and time2!='' \"> and biderpaydate &gt;= trunc(next_day(sysdate-8,1)+1) and biderpaydate &lt; trunc(next_day(sysdate-8,1)+7)+1</if>"+
            "<if test=\" time3!=null and time3!='' \"> and to_char(biderpaydate,'yyyy-mm')=to_char(sysdate,'yyyy-mm')</if>"+
            "<if test=\" time4!=null and time4!='' \"> and to_char(biderpaydate,'yyyy')=to_char(sysdate,'yyyy')</if>"+
            "</script>")
    List<Map> getList(Map map);

    /**
     * 根据用户编号查询用户流水
     * @param map
     * @return
     */
    @Select("<script>"+
            "select to_char(flowdate,'yyyy-mm-dd') as flowdate,availablebalance,flowtype,amount  " +
            "from user_account_flow where userid=#{userId}" +
            "<if test=\" type!=null and type!=''\"> and flowtype=#{type}</if>" +
            "<if test=\" time1!=null and time1!='' \"> and to_char(flowdate,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')</if>"+
            "<if test=\" time2!=null and time2!='' \"> and flowdate &gt;= trunc(next_day(sysdate-8,1)+1) and flowdate &lt; trunc(next_day(sysdate-8,1)+7)+1</if>"+
            "<if test=\" time3!=null and time3!='' \"> and to_char(flowdate,'yyyy-mm')=to_char(sysdate,'yyyy-mm')</if>"+
            "<if test=\" time4!=null and time4!='' \"> and to_char(flowdate,'yyyy')=to_char(sysdate,'yyyy')</if>"+
            "</script>")
    List<Map> getFlow(Map map);

    /**
     * 根据用户编号查询用户投资记录
     * 用户 投资记录表
     * @param map
     * @return
     */
    @Select("<script>select to_char(biddate,'yyyy-mm-dd') " +
            "biddate,submitid,bidid,userid,bidamount,bidrate,bidstate " +
            "from bid_submit where userid=#{userId} " +
            "<if test=\" type!=null and type!=''\"> and bidstate=#{type}</if>" +
            "<if test=\" time1!=null and time1!='' \"> and to_char(biddate,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')</if>"+
            "<if test=\" time2!=null and time2!='' \"> and biddate &gt;= trunc(next_day(sysdate-8,1)+1) and biddate &lt; trunc(next_day(sysdate-8,1)+7)+1</if>"+
            "<if test=\" time3!=null and time3!='' \"> and to_char(biddate,'yyyy-mm')=to_char(sysdate,'yyyy-mm')</if>"+
            "<if test=\" time4!=null and time4!='' \"> and to_char(biddate,'yyyy')=to_char(sysdate,'yyyy')</if>"+
            "</script>")
    List<Map> getSubmit(Map map);

    /**
     * 根据用户编号查询用户还款计划
     * @param map
     * @return
     */
    @Select("<script>select repayid,userid,bidrepayamount,to_char(biderpaydate,'yyyy-mm-dd') biderpaydate," +
            " to_char(biderpaydeaddate,'yyyy-mm-dd') biderpaydeaddate,bidrepayState,bidrepaynumber" +
            " from bid_repay_info where userid=#{userId}" +
            " <if test=\" type!=null and type!=''\"> and bidrepayState=#{type}</if>" +
            " <if test=\" time1!=null and time1!='' \"> and to_char(biderpaydeaddate,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')</if>"+
            " <if test=\" time2!=null and time2!='' \"> and biderpaydeaddate &gt;= trunc(next_day(sysdate-8,1)+1) and biderpaydeaddate &lt; trunc(next_day(sysdate-8,1)+7)+1</if>"+
            " <if test=\" time3!=null and time3!='' \"> and to_char(biderpaydeaddate,'yyyy-mm')=to_char(sysdate,'yyyy-mm')</if>"+
            " <if test=\" time4!=null and time4!='' \"> and to_char(biderpaydeaddate,'yyyy')=to_char(sysdate,'yyyy')</if>"+
            " </script>")
    List<Map> getRepay(Map map);

    /**
     * 更具
     * @param map
     * @return
     */
    int  getSum1(Map map);

    /**
     *根据用户id查询可以回款标信息标
     * @param map
     * @return
     */
    @Select("<script>select * from bid_info where bidid in (select bidid from bid_submit where userid=#{userId}) "+
            " <if test=\" type!=null and type!=''\"> and bidrepaymentmethod=#{type}</if>" +
            " <if test=\" time1!=null and time1!='' \"> and to_char(biderpaydeaddate,'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')</if>"+
            " <if test=\" time2!=null and time2!='' \"> and biderpaydeaddate &gt;= trunc(next_day(sysdate-8,1)+1) and biderpaydeaddate &lt; trunc(next_day(sysdate-8,1)+7)+1</if>"+
            " <if test=\" time3!=null and time3!='' \"> and to_char(biderpaydeaddate,'yyyy-mm')=to_char(sysdate,'yyyy-mm')</if>"+
            " <if test=\" time4!=null and time4!='' \"> and to_char(biderpaydeaddate,'yyyy')=to_char(sysdate,'yyyy')</if>"+
            " </script>")
    List<Map> getMSBs(Map map);

    /**
     * 用户 充值 添加用户流水表
     * @param map
     * @return
     */
    @Insert("insert into user_account_flow" +
            " values((select 'UFLOW'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0) from dual)," +
            " #{userId},#{accountid},#{actualMoney1},#{moneyflow},sysdate,#{type})")
    int recharge(Map map);

    /**
     * 用户 提现添加用户流水表
     * @param map
     * @return
     */
    @Insert("insert into user_account_flow" +
            " values((select 'UFLOW'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0) from dual)," +
            " #{userId},123456788999,#{actualMoney1},(select (select availablebalance from " +
            " (select flowdate,availablebalance from user_account_flow where userid=#{userId} order by flowdate  desc)" +
            " where rownum =1) - #{actualMoney1} from dual),sysdate,#{type})")
    int recharge1(Map map);

    /**
     * 根据用户id 查询用户账户信息
     * @param userid
     * @return
     */
    @Select("select * from user_account where userid=#{userId}")
    List<Map> getAccount(String userid);

    /**
     * 用户充值  之后更新用户账户表
     * @param map
     * @return
     */
    @Update("update user_account  set availablebalance=" +
            "((select availablebalance from user_account where userid=#{userId})+#{actualMoney1}) " +
            " where userid=#{userId}")
    int withdraw(Map map);

    /**
     * 用户提现  之后更新用户账户表
     * @param map
     * @return
     */
    @Update("update user_account  set availablebalance=" +
            "((select availablebalance from user_account where userid=#{userId})-#{actualMoney1}) " +
            " where userid=#{userId}")
    int withdraw1(Map map);

    /**
     *  添加系统账户流水记录
     * @param map
     * @return
     */
    @Insert("insert into tb_system_account_flow" +
            " values((select 'SFLOW'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0) from dual)," +
            " #{userId},'A111111111',#{actualMoney1},(select (select availablebalance from" +
            " (select flowdate,availablebalance from tb_system_account_flow where userid=#{userId} order by flowdate  desc)" +
            " where rownum =1)+#{actualMoney1} from dual),sysdate,#{type},null,123)")
    int system(Map map);

    /**
     * 查询账户id
     * @param userid
     * @return
     */
    @Select("select userid from user_account where userid=#{userid}")
    String getUserbyUserid(String userid);

    /**
     * 查询用户详情userid
     * @param userid
     * @return
     */
    @Select("select userid from tb_realname_certification where userid=#{userid}")
    String getuesrid(String userid);

    /**
     * 向账户表中插入userid
     * @param userid
     * @return
     */
    @Insert("insert into user_account(accountid,userid) values('A'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0),#{userid})")
    int adduserid(String userid);

    /**
     * 向用户详情中插入userid
     * @param userid
     * @return
     */
    @Insert("insert into tb_realname_certification(userid) values(#{userid}) ")
    int adduserid1(String userid);

    /**
     * 用户充值时添加到系统的总账户
     * @param map
     * @return
     */
    @Update("update system_rental set money=((select money from system_rental " +
            "where sysid='SYS201812221314')+ #{actualMoney1}) where sysid='SYS201812221314'")
    int addSys(Map map);

    /**
     * 用户充提现时更新系统的总账户
     * @param map
     * @return
     */
    @Update("update system_rental set money=((select money from system_rental " +
            "where sysid='SYS201812221314') - #{money1}) where sysid='SYS201812221314'")
    int subSys(Map map);

    /**
     * 根据userId 统计用户累计投资和累计收益
     * @param map
     * @return
     */
    @Select("select sum(bidamount) as sum1,sum(bidrate) as sum2 from bid_submit where userid=#{userId}")
    Map statistics(Map map);
}
