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
     *添加借款信息
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 添加详细信息
     * @param map
     * @return
     */
    int addRentDetialInfo(Map map);

    /**
     * 添加图片认证
     * @param map
     * @return
     */
    int addRentInfo2(Map map);

    /**
     *查询已经借款用户的详细信息 直接加载到用户详情页面
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
