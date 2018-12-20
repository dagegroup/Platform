package com.dage.service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @className:AuditService
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-15 09:01
 */
public interface AuditService {

    /**
     * 获取待审核的投标信息
     * @param map
     * @return
     */
    Map getListAudit(Map map);

    /**
     * 获取审核表的详细信息
     * @param bidid
     * @return
     */
    Map getListAuditDetial(String bidid);

    /**
     * 更改标的状态
     * @param map
     * @return
     */
    int updateBidState(Map map, HttpSession session);
    /**
     * 更改认证信息的状态
     * @param map
     * @return
     */
    int updateRealState(Map map, HttpSession session);

    /**
     * 根据userid获取认证表详情
     * @param id
     * @return
     */
    Map getRealByUserId(String id);
}
