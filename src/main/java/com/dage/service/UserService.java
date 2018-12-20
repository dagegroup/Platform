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
     * @param userId
     * @return
     */
    List<Map> getList(String userId);

    /**
     * 根据用户编号查询用户流水
     * @param userId
     * @return
     */
    List<Map> getFlow(String userId);

    /**
     * 根据用户编号查询用户投资记录
     * @param userId
     * @return
     */
    List<Map> getSubmit(String userId);

    /**
     * 根据用户编号查询用户还款计划
     * @param userId
     * @return
     */
    List<Map> getRepay(String userId);

    /**
     * 用户充值
     * @param map
     * @return
     */
    int recharge(Map map);

    /**
     * 根据用户id 查询用户账户信息
     * @param userid
     * @return
     */
    List<Map> getAccount(String userid);

    /**
     * 用户提现之后更新用户账户表
     * @param map
     * @return
     */
    int withdraw(Map map);


    /**
     * author:ChenMing
     * creatTime:2018-12-10 16:41
     */
    Map getByuserName(String telephone, String password);

    String getUserid(String userid);
    int addUserid(String userid);
}
