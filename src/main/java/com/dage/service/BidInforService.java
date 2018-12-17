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

}
