package com.dage.service;

import java.util.List;
import java.util.Map;

/**
 * className:AccService
 * discription:
 * author:ChenMing
 * creatTime:2018-12-22 10:19
 */
public interface AccService {
    /**
     * 获取系统账户列表
     * @param map
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 查询流水类型
     * @return
     */
    List<Map> getFlowtype();

    /**
     * 账户流水
     * @param map
     * @return
     */
    List<Map> getAccountFlow(Map map);

    /**
     * 获得账户总余额
     * @param map
     * @return
     */
    List<Map> getBalance(Map map);

    /**
     * 获得收入
     * @param map
     * @return
     */
    List<Map> getIncome(Map map);
}
