package com.dage.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * className:RepaymentDao
 * discription: 用于处理个人用户的还款数据和数据库的交互
 * author:CZP
 * createTime:2018-12-19 10:28
 */
@Repository
public interface RepaymentDao {

    /**
     *根据用户Id查询用户的还款计划
     * 还款计划表
     * @param map
     * @return
     */
    @Select("<script>select bidid, to_char(biderpaydate,'yyyy-mm-dd') as biderpaydate,repayid,userid,bidrepayamount," +
            " to_char(biderpaydeaddate,'yyyy-mm-dd') as biderpaydeaddate," +
            " to_char(bidnextrepaydate,'yyyy-mm-dd') as bidnextrepaydate," +
            " bidrepayState, bidrepaynumber,bidrepaytotpmts,bidrepaymethod,bidrepayuserid" +
            " from bid_repay_info where userid=#{userId} and bidrepaystate like '%待还款'"  +
            "<if test=\" repayid!=null and repayid!=''\"> and repayid=#{repayid}</if>" +
            "<if test=\" type!=null and type!=''\"> and bidrepaystate=#{type}</if>" +
            "</script>")
    List<Map> getList(Map map);

    /**
     * 根据用户id查询用户的账户余额
     * @param map
     * @return
     */
    @Select("select * from user_account where userid=#{userId}")
    Map getBalance(Map map);


    /**
     * 根据bidid查询标的总额
     * @param bidid
     * @return
     */
    @Select("select bidamount from bid_info where bidid=#{bidid}")
    double getBidamount(String bidid);

    /**
     * 当还款结束时更改标的状态为已还款
     * @param bidid
     * @return
     */
    @Update("update bid_info set bidstate='已还款' where bidid=#{bidid}")
    int updateBid(String bidid);

    /**
     * 当还款结束时更改标的状态为已还款
     * @param userid
     * @return
     */
    @Update("update tb_user_info set state='正常' where userid=#{userid}")
    int updateUser(String userid);

    /**
     * 根据userid bidid 查询待还款剩余的期数
     *
     * @param map
     * @return
     */
    @Select("select count(*) from bid_repay_info where userid=#{userId}" +
            " and bidid=#{bidid} and bidrepaystate like '%待还款%'")
    int getBidrepaynumber(Map map);
    /**
     * 根据还款计划Id查询需要还的钱
     *
     * @param map
     * @return
     */
    @Select("select * from bid_repay_info where repayid=#{repayid}")
    Map getAmount(Map map);

    /**
     * 查询每一个投资人投资了该标多少钱
     * @param map
     * @return
     */
    @Select("select sum(bidamount) from bid_submit where userid=#{userId} and bidid=#{bidid}")
    double getSum(Map map);

    /**
     * 用户余额够时 用户还款 更新用户可用余额
     * @param map
     * @return
     */
    @Update("update user_account set availablebalance=#{money},returnamount=#{money1} where userid=#{userId}")
    int updateAmount(Map map);

    /**
     * 用户余额够时 用户还款 添加还款人账户流水
     * @param map
     * @return
     */
    @Insert("insert into user_account_flow" +
            " values((select 'UFLOW'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0) from dual)," +
            " #{userId},'A111111111',#{money2},(select (select availablebalance from " +
            "(select flowdate,availablebalance from user_account_flow where userid=#{userId} order by flowdate  desc)" +
            " where rownum =1) - #{money2} from dual),sysdate,'还款')")
    int updateAccountFlow(Map map);


    /**
     * 根据 标信息表Id查询投资人信息
     * @param bidid
     * @return
     */
    @Select("select * from user_account where userid in(select userid from bid_submit where bidid=#{bidid})")
    List<Map> getInvestor(String bidid);

    /**
     * 用户余额够时 用户还款 更新投资者的 账户余额
     *
     * @param map
     * @return
     */
    @Update("update user_account set availablebalance=#{money3}, receiveprincipal=#{money4} where userid=#{userId}")
    int updateInvestor(Map map);

    /**
     * 用户余额够时 用户还款 添加投资者的账户流水
     * @param map
     * @return
     */
    @Insert("insert into user_account_flow" +
            " values((select 'UFLOW'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0) from dual)," +
            " #{userId},'A111111111',#{money5},(select (select availablebalance from " +
            "(select flowdate,availablebalance from user_account_flow where userid=#{userId} order by flowdate  desc)" +
            " where rownum =1) + #{money5} from dual),sysdate,'回款')")
    int insertInvestorFlow(Map map);


    /**
     *
     *用户余额够时 用户还款 更新还款计划表中该列的 还款状态
     * @param map
     * @return
     */
    @Update("update bid_repay_info set bidrepaystate='已还款' where repayid=#{repayid}")
    int updateRepay(Map map);

}
