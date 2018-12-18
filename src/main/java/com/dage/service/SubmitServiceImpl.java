package com.dage.service;

import com.dage.dao.SubmitDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className:SubmitServiceImpl
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-17 14:04
 */
@Service
public class SubmitServiceImpl implements SubmitService {

    @Autowired
    private SubmitDao submitDao;

    @Override
    public Map getListBid(Map map) {
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> listBid = submitDao.getListBid(map);
        PageInfo<Map> info = new PageInfo<>(listBid);
        mp.put("page",info);
        return mp;
    }

    @Override
    public Map getSubmitByBidid(String bidid) {
        Map mp = new HashMap();
        mp.put("data",submitDao.getSubmitByBidid(bidid));
        return mp;
    }

    @Override
    public Map getListBidByFull(Map map) {
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> listBid = submitDao.getListBidByFull(map);
        PageInfo<Map> info = new PageInfo<>(listBid);
        mp.put("page",info);
        return mp;
    }

    @Override
    public int updateBidState(Map map) {
        return submitDao.updateBidState(map);
    }

    @Override
    public Map getListBidByLoan(Map map) {
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> listBid = submitDao.getListBidByLoan(map);
        PageInfo<Map> info = new PageInfo<>(listBid);
        mp.put("page",info);
        return mp;
    }
}
