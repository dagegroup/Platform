package com.dage.service;

import com.dage.dao.RentInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * className:RentInfoServiceImpl
 * discription:
 * author:ChenMing
 * creatTime:2018-12-17 14:36
 */
@Service
public class RentInfoServiceImpl implements RentInfoService {


    @Autowired
    private RentInfoDao rentInfoDao;

    @Override
    public Map getrent(String userid) {
        return rentInfoDao.getrent(userid);
    }

    @Override
    public Map getInfo(String userid) {
        return rentInfoDao.getInfo(userid);
    }

    @Override
    public int addRentInfo2(Map map) {
        return rentInfoDao.addRentInfo2(map);
    }

    @Override
    public int addRentDetialInfo(Map map) {
        return rentInfoDao.addRentDetialInfo(map);
    }

    @Override
    public int add(Map map) {
        return rentInfoDao.addRentInfo(map);
    }
}
