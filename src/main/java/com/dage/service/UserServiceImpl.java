package com.dage.service;

import com.dage.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Map getByuserName(String telephone,String password) {
        System.out.println(telephone + password);
        return userDao.getByuserName(telephone,password);
    }
}
