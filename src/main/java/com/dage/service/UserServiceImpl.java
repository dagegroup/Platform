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
    public int adduserid1(String userid) {
        return userDao.adduserid1(userid);
    }

    @Override
    public String getuesrid(String userid) {
        return userDao.getuesrid(userid);
    }

    @Override
    public int addUserid(String userid) {
        return userDao.adduserid(userid);
    }

    @Override
    public Map getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
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

    /**
     * 用户充值时添加到系统的总账户
     * @param map
     * @return
     */
    @Override
    public int addSys(Map map) {
        return userDao.addSys(map);
    }

    /**
     * 用户充提现时更新系统的总账户
     * @param map
     * @return
     */
    @Override
    public int subSys(Map map) {
        return userDao.subSys(map);
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
    public List<Map> getMSBs(Map map) {
        return userDao.getMSBs(map);
    }

    @Override
    public List<Map> getFlow(Map map) {
        return userDao.getFlow(map);
    }

    @Override
    public List<Map> getUser(Map map) {
        return userDao.getUser(map);
    }

    /**
     *  添加系统账户流水记录
     * @param map
     * @return
     */
    @Override
    public int system(Map map) {
        return userDao.system(map);
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
