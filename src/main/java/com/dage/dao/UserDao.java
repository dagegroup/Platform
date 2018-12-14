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
 * creatTime:2018-12-10 16:39
 */
public interface UserDao {

    /**
     * 根据用户编号查询
     * @param userId
     * @return
     */
    @Select("select userid,bidrepayuserid,BIDREPAYMETHOD,to_char(biderpaydeaddate,'yyyy-mm-dd') as bidrepaydate,bidrepaynumber,bidrepayamount from bid_repay_info where userid=#{userId}")
    List<Map> getList(String userId);

    /**
     * 根据用户编号查询用户流水
     * @param userId
     * @return
     */
    @Select("select to_char(flowdate,'yyyy-mm-dd') flowdate,availablebalance,flowtype,amount,flowtype from user_account_flow where userid=#{userId}")
    List<Map> getFlow(String userId);

    /**
     * 根据用户编号查询用户投资记录
     * @param userId
     * @return
     */
    @Select("select to_char(biddate,'yyyy-mm-dd') " +
            "biddate,submitid,bidid,userid,bidamount,bidrate from bid_submit where userid=#{userId}")
    List<Map> getSubmit(String userId);

    /**
     * 根据用户编号查询用户还款计划
     * @param userId
     * @return
     */
    @Select("select * from bid_repay_info where userid=#{userId} ")
    List<Map> getRepay(String userId);


    /**
     * 用户充值添加用户流水表
     * @param map
     * @return
     */
    @Insert("insert into user_account_flow" +
            " values((select 'UFLOW'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0) from dual)," +
            " 'U201812071032',123456788999,#{actualMoney1},(select (select availablebalance from " +
            "(select flowdate,availablebalance from user_account_flow where userid='U201812071032' order by flowdate  desc)" +
            " where rownum =1) + #{actualMoney1} from dual),sysdate,'充值')")
    int recharge(Map map);


    /**
     * 根据用户id 查询用户账户信息
     * @param userid
     * @return
     */
    @Select("select * from user_account where userid=#{userId}")
    List<Map> getAccount(String userid);

    /**
     * 用户提现之后更新用户账户表
     * @param map
     * @return
     */
    @Update("update user_account  set availablebalance=#{money}  where userid=#{userId}")
    int withdraw(Map map);

    /**
     * 添加系统账户流水记录
     * @param map
     * @return
     */
    @Insert("insert into")
    int system(Map map);
    /**
     * author:ChenMing
     * creatTime:2018-12-10 16:39
     */
    @Select("select  telephone,userName,password from TB_USER_INFO where telephone=#{param1} and password=#{param2} ")
    Map getByuserName(String telephone, String password);
}
