package com.dage.service;

import java.util.List;
import java.util.Map;

/**
 * className:BidService
 * discription:
 * author:lc
 * createTime:2018-12-10 17:29
 */
public interface BidService {

    /**
     *条件查询标列表
     * @param map
     * @return
     */
    List<Map> getTerm(Map map);

    /**
     * 投资总金额
     * @return
     */
    double getGrossAssets();

    /**
     * 成功招标总金额
     * @return
     */
    double getSuccse();

}
