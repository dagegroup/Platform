package com.dage.service;

import java.util.Map;

/**
 * className:RentInfoService
 * discription:
 * author:ChenMing
 * creatTime:2018-12-17 14:35
 */
public interface RentInfoService {
    /**
     *
     * @param map
     * @return
     */
    int add(Map map);

    /**
     *
     * @param map
     * @return
     */
    int addRentDetialInfo(Map map);

    /**
     *
     * @param map
     * @return
     */
    int addRentInfo2(Map map);

    /**
     *
     * @param userid
     * @return
     */
    Map getInfo(String userid);

    /**
     * 查询是否正在借款
     * @return
     */
    Map getrent(String userid);
}
