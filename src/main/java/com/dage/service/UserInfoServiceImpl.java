package com.dage.service;

import com.dage.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:UserInfoServiceImpl
 * discription:
 * author:zn
 * createTime:2018-12-11 17:14
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;
    /**
     * 获得用户信息表
     * @return
     */
    @Override
    public List<Map> getList() {
        return userInfoDao.getList();
    }

    @Override
    public List<Map> getListByPhone(String telephone) {
        return userInfoDao.getListByPhone(telephone);
    }

    @Override
    public List<Map> getListByRefname(String refereeName) {
        return userInfoDao.getListByRefname(refereeName);
    }

    @Override
    public List<Map> getListByName(String name) {
        return userInfoDao.getListByName(name);
    }

    @Override
    public int insertInfo(Map map) {
        map.put("state","正常");
        return userInfoDao.insetInfo(map);
    }
}
