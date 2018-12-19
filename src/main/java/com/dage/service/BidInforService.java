package com.dage.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * className:BidInforService
 * discription:
 * author:lc
 * createTime:2018-12-14 11:06
 */
public interface BidInforService {

    /**
     * 标列表查询
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 项目详情页：借款人信息
     * @param map
     * @return
     */
    List<Map> getUserList(Map map);

    /**
     * 项目详情页：投资记录
     * @param map
     * @return
     */
    List<Map> getUserInvest(Map map);

    /**
     * 项目详情页：还款记录
     * @param map
     * @return
     */
    List<Map> getRefundRecord(Map map);
    /**
     * 我要投标
     * @param map
     * @return
     */
    Map tender(Map map);

    /**
     * 根据session里的username得到realname
     * @param username
     * @return
     */
    Map getUser(String username);
}
