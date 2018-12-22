package com.dage.service;

import com.dage.dao.RepayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @className:RepayService
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-18 14:04
 */
public interface RepayService {

    /**
     * 还款计划操作
     * @param map
     * @return
     */
    int repayPlanHandle(Map map, HttpSession session);
    /**
     * 获取待还款的投标信息
     * @param map
     * @return
     */
    Map getListBidByRepay(Map map);

    /**
     * 根据标号Id获取 bid_repay_info表中 对应的还款信息
     * @param bidid
     * @return
     */
    Map getRepayByBidid(String bidid);
}
