package com.dage.service;

import org.apache.ibatis.annotations.Select;

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
     * 根据用户编号查询用户投资记录
     * @param userId
     * @return
     */
    List<Map> getSubmit(Map map);

    /**
     * 根据用户编号查询用户还款计划
     * @param userId
     * @return
     */
    List<Map> getRepay(Map map);

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
     * author:ChenMing
     * creatTime:2018-12-10 16:41
     */
    Map getByuserName(String telephone, String password);

    String getUserid(String userid);

    int addUserid(String userid);
}
