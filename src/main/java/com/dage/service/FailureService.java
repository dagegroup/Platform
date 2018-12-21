package com.dage.service;

import java.util.List;
import java.util.Map;

/**
 * className:FailureService
 * discription:
 * author:zn
 * createTime:2018-12-18 11:36
 */
public interface FailureService {

    /**
     * 获取流标详情
     * @param map
     * @return
     */
    Map getlist(Map map);

    /**
     * 根据标号Id获取 bid_sumbit 表中 对应的流标信息
     * @param bidid
     * @return
     */
    Map getMapByBidid(String bidid);

    /**
     * 通过userid查询user_account表信息
     * @param userid
     * @return
     */
    Map getUserAccountByUserid(String userid);

    /**
     * 通过借款标号bidid查找bid_info表信息
     * @param bidid
     * @return
     */
    Map getBidInfoByBidid(String bidid);

    /**
     * 流水还款操作
     * @param
     * @return
     */
    int repayFailure(String bidid);

}
