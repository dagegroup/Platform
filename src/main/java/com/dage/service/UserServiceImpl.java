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
    public List<Map> getRepay(Map map) {
        return userDao.getRepay(map);
    }

    @Override
    public List<Map> getSubmit(Map map) {
        return userDao.getSubmit(map);
    }

    @Override
    public List<Map> getFlow(Map map) {
        return userDao.getFlow(map);
    }

    @Override
    public List<Map> getList(Map map) {
        return userDao.getList(map);
    }


    public Map getByuserName(String telephone, String password) {
        System.out.println(telephone + password);
        return userDao.getByuserName(telephone, password);
    }

}
