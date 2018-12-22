package com.dage.service;

import javax.servlet.http.HttpSession;
import java.util.List;
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

    /**
     * 更改标的状态
     * @param map
     * @return
     */
    int updateBidState(Map map, HttpSession session);


    /**
     * 获取待放款的投标信息
     * @param map
     * @return
     */
    Map getListBidByLoan(Map map);
}
