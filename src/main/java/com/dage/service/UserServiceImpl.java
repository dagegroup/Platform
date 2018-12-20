package com.dage.service;

import com.dage.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:UserServiceImpl
 * discription:
 * author:ChenMing
 * creatTime:2018-12-10 16:42
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUserid(String userid) {
        return userDao.adduserid(userid);
    }

    @Override
    public String getUserid(String userid) {

        return userDao.getUserbyUserid(userid);
    }


    @Override
    public int withdraw(Map map) {
        return userDao.withdraw(map);
    }

    @Override
    public List<Map> getAccount(String userid) {
        return userDao.getAccount(userid);
    }

    @Override
    public int recharge(Map map) {
        return userDao.recharge(map);
    }

    @Override
    public List<Map> getRepay(String userId) {
        return userDao.getRepay(userId);
    }

    @Override
    public List<Map> getSubmit(String userId) {
        return userDao.getSubmit(userId);
    }

    @Override
    public List<Map> getFlow(String userId) {
        return userDao.getFlow(userId);
    }

    @Override
    public List<Map> getList(String userId) {
        return userDao.getList(userId);
    }


    public Map getByuserName(String telephone, String password) {
        System.out.println(telephone + password);
        return userDao.getByuserName(telephone, password);
    }

}
