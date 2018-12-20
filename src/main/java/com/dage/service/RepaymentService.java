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
}
