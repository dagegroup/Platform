package com.dage.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * className:FullService
 * discription:
 * author:zn
 * createTime:2018-12-22 10:53
 */
public interface FullService {


    /**
     * 获取已还款的投标信息
     * @param map
     * @return
     */
    Map getListBidByLoan(Map map);
}
