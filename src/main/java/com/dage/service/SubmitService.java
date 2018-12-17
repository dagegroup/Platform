package com.dage.service;

import java.util.Map;

/**
 * @className:SubmitService
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-17 14:04
 */
public interface SubmitService {

    /**
     * 获取待审核的投标信息
     * @param map
     * @return
     */
    Map getListBid(Map map);

    /**
     * 根据标号Id获取 bid_sumbit 表中 对应的投标信息
     * @param bidid
     * @return
     */
    Map getSubmitByBidid(String bidid);


    /**
     * 获取满标待审核的投标信息
     * @param map
     * @return
     */
    Map getListBidByFull(Map map);

}
