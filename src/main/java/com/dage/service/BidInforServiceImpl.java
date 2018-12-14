package com.dage.service;

import com.dage.dao.BidInforDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:BidInforServiceImpl
 * discription:
 * author:lc
 * createTime:2018-12-14 11:07
 */
@Service
public class BidInforServiceImpl implements BidInforService {

    @Autowired
    private BidInforDao bidInforDao;

    public List<Map> getList(Map map){
        return bidInforDao.getList(map);
    }

}
