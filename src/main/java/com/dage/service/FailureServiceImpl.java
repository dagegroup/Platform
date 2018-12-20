package com.dage.service;

import com.dage.dao.FailureDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:FailureServiceImpl
 * discription:
 * author:zn
 * createTime:2018-12-18 11:37
 */
@Service
public class FailureServiceImpl implements FailureService {

    @Autowired
    private FailureDao failureDao;
    @Autowired
    private FailureService failureService;
    @Override
    public Map getlist(Map map) {
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> getlist = failureDao.getlist(map);
        PageInfo<Map> pageInfo = new PageInfo<>(getlist);
        mp.put("page",pageInfo);
        return mp;
    }

    @Override
    public Map getMapByBidid(String bidid) {
        Map mp = new HashMap();
        mp.put("data",failureDao.getMapByBidid(bidid));
        return mp;
    }

    @Override
    public Map getUserAccountByUserid(String userid) {
        return failureDao.getUserAccountByUserid(userid);
    }

    @Override
    public int updateBidSubmit(String bidid) {
        return failureDao.updateBidSubmit(bidid) ;
    }

    @Override
    public int updateUserInfo(String bidid) {
        int i=0;
        Map bidinfo = failureService.getBidInfoByBidid(bidid);
        if (bidinfo!=null&&bidinfo.size()>0) {
            String userid = bidinfo.get("USERID") + "";
             i = failureDao.updateUserInfo(userid);
        }
        return i;
    }

    @Override
    public Map getBidInfoByBidid(String bidid) {
        return failureDao.getBidInfoByBidid(bidid);
    }

    @Override
    public int updateUserAccount(String bidid) {
        Map map = failureService.getMapByBidid(bidid);
        List data = (List)map.get("data");
        int i=0;
        for (Object o : data) {
            Map o1 = (Map) o;
            Map userMap = failureDao.getUserAccountByUserid(o1.get("USERID") + "");
            if(userMap!=null&&userMap.size()>0) {
                Integer a = Integer.valueOf(userMap.get("AVAILABLEBALANCE") + "");
                Integer a1 = Integer.valueOf(o1.get("BIDAMOUNT") + "");
                o1.put("AVAILABLEBALANCE", a + a1);
                i = failureDao.updateUserAccount(o1);
            }

        }
        return i;
    }

    @Override
    public int insertUserFlow(String bidid) {
        Map submitMap = failureService.getMapByBidid(bidid);
        List data = (List)submitMap.get("data");
        int i=0;
        for (Object o : data) {
            Map m = (Map) o;
            Map userMap = failureDao.getUserAccountByUserid(m.get("USERID") + "");
            if (userMap!=null&&userMap.size()>0) {
                Integer a = Integer.valueOf(userMap.get("AVAILABLEBALANCE") + "");
                m.put("AMOUNT", Integer.valueOf(m.get("BIDAMOUNT") + ""));
                m.put("AVAILABLEBALANCE", a);
                String accountid = userMap.get("ACCOUNTID") + "";
                m.put("ACCOUNTID", accountid);
                m.put("FLOWTYPE", "投资");
                i = failureDao.insertUserFlow(m);
            }
        }
        return i;
    }
}
