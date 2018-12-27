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

    /**
     * 查询用户是否真正处于借款状态
     * @param userid
     * @return
     */
    @Override
    public Map getrent(String userid) {
        return rentInfoDao.getrent(userid);
    }
    /**
     *查询已经借款用户的详细信息 直接加载到用户详情页面
     * @param userid
     * @return
     */
    @Override
    public Map getInfo(String userid) {
        return rentInfoDao.getInfo(userid);
    }
    /**
     *添加认证图片
     * @param map
     * @return
     */
    @Override
    public int addRentInfo2(Map map) {
        return rentInfoDao.addRentInfo2(map);
    }
    /**
     *更新详细信息
     * @param map
     * @return
     */
    @Override
    public int addRentDetialInfo(Map map) {
        return rentInfoDao.addRentDetialInfo(map);
    }

    @Override
    public int updateState(String userid) {
        return rentInfoDao.updateState(userid);
    }

    /**
     *添加借款信息
     * @param map
     * @return
     */
    @Override
    public int add(Map map) {
        return rentInfoDao.addRentInfo(map);
    }
}
