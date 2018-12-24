package com.dage.service;

import java.util.List;
import java.util.Map;

/**
 * className:ChangeService
 * discription:
 * author:lc
 * createTime:2018-12-22 10:55
 */
public interface ChangeService {

    /**
     * 通过电话号码查询用户信息
     * @return
     */
    Map getPhone(Map map);
    /**
     * 更新用户密码
     * @param map
     * @return
     */
    int updatePassword(Map map);

    /**
     * 更新支付密码
     * @param map
     * @return
     */
    int updatePayment(Map map);
}
