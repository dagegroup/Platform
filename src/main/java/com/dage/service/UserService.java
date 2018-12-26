package com.dage.service;


import java.util.List;
import java.util.Map;

/**
 * className:UserService
 * discription:
 * author:ChenMing
 * creatTime:2018-12-10 16:41
 */
public interface UserService {

    /**
     *
     * @param map
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 根据用户编号查询用户流水
     * @param userId
     * @return
     */
    List<Map> getFlow(Map map);

    /**
     *  添加系统账户流水记录
     * @param map
     * @return
     */
    int system(Map map);

    /**
     * 用户充值添加用户流水表
     * @param map
     * @return
     */
    int recharge(Map map);

    /**
     * 用户 提现添加用户流水表
     * @param map
     * @return
     */
    int recharge1(Map map);

    /**
     * 用户充提现时更新系统的总账户
     * @param map
     * @return
     */
    int subSys(Map map);

    /**
     * 根据userId 统计用户累计投资和累计收益
     * @param map
     * @return
     */
    Map statistics(Map map);

    /**
     *根据用户id查询可以回款标信息标
     * @param map
     * @return
     */
    List<Map> getMSBs(Map map);

    /**
     * 根据用户编号查询用户投资记录
     * @param userId
     * @return
     */
    List<Map> getSubmit(Map map);

    /**
     * 根据用户编号查询用户还款计划
     * @param map
     * @return
     */
    List<Map> getRepay(Map map);


    /**
     * 根据用户id 查询用户账户信息
     * @param userid
     * @return
     */
    List<Map> getAccount(String userid);

    /**
     * 用户充值时添加到系统的总账户
     * @param money
     * @return
     */
    int addSys(Map map);

    /**
     * 用户充值之后更新用户账户表
     * @param map
     * @return
     */
    int withdraw(Map map);

    /**
     * 用户提现  之后更新用户账户表
     * @param map
     * @return
     */
    int withdraw1(Map map);


    /**
     * 根据session里 获取的用户姓名 查询用户的信息
     * @param userName
     * @return
     */
    Map getUserByUserName(String userName);
    /**
     * 根据用户id查询用户的个人信息
     * @param map
     * @return
     */
    List<Map> getUser(Map map);

    /**
     *  添加 用户提现之后减少 系统账户流水记录
     * @param map
     * @return
     */
    int system1(Map map);

    /**
     *  添加 用户提现之后添加手续费记录 系统账户流水记录
     * @param map
     * @return
     */
    int system2(Map map);


    /**
     * author:ChenMing
     * creatTime:2018-12-10 16:41
     */
    Map getByuserName(String telephone, String password);

    /**
     * 查询账户中是否有userid
     * @param userid
     * @return
     */

    String getUserid(String userid);

    /**
     * 添加账户的userid
     * @param userid
     * @return
     */

    int addUserid(String userid);

    /**
     * 查询用户信息详情表是否有userid
     * @param userid
     * @return
     */
    String getuesrid(String userid);

    /**
     * 向用户详细信息中插入userid
     * @param userid
     * @return
     */
    int adduserid1(String userid);
}
