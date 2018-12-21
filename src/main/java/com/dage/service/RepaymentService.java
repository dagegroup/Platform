package com.dage.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:RepaymentService
 * discription: 用于处理用户的还款业务
 * author:CZP
 * createTime:2018-12-19 10:29
 */
public interface RepaymentService {

    /**
     *根据用户Id查询用户的借款记录
     * 还款计划表
     * @param map
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 根据用户id查询用户的账户余额
     * @param map
     * @return
     */
    Map getBalance(Map map);

    /**
     * 根据还款计划Id查询需要还的钱
     * @param map
     * @return
     */
    Map getAmount(Map map);

    /**
     * 用户余额够时 用户还款 更新用户可用余额
     * @param map
     * @return
     */
    int updateAmount(Map map);


    /**
     * 用户余额够时 用户还款 添加用户账户流水
     * @param map
     * @return
     */
    int updateAccountFlow(Map map);

    /**
     *
     *用户余额够时 用户还款 更新还款计划表中该列的 还款状态
     * @param map
     * @return
     */
    int updateRepay(Map map);

    /**
     * 根据 标信息表Id查询投资人信息
     * @param bidid
     * @return
     */
    List<Map> getInvestor(String bidid);

    /**
     * 用户余额够时 用户还款 更新投资者的 账户余额
     *
     * @param map
     * @return
     */
    int updateInvestor(Map map);

    /**
     * 用户余额够时 用户还款 添加借款人账户流水
     * @param map
     * @return
     */
    int insertInvestorFlow(Map map);
}
