package com.dage.service;

import com.dage.dao.FullDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:FullServiceImpl
 * discription:
 * author:zn
 * createTime:2018-12-22 10:55
 */
@Service
public class FullServiceImpl implements FullService {

    @Autowired
    private FullDao fullDao;
    @Override
    public Map getListBidByLoan(Map map) {
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> listBid = fullDao.getListBidByLoan(map);
        PageInfo<Map> info = new PageInfo<>(listBid);
        mp.put("page",info);
        return mp;
    }
}
