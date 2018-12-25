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

    /**
     * 向详细信息表添加userid
     * @param userid
     * @return
     */
    @Override
    public int adduserid1(String userid) {
        return userDao.adduserid1(userid);
    }

    /**
     * 获取详细信息中的userid
     * @param userid
     * @return
     */
    @Override
    public String getuesrid(String userid) {
        return userDao.getuesrid(userid);
    }

    /**
     * 向 账户中添加userid
     * @param userid
     * @return
     */
    @Override
    public int addUserid(String userid) {
        return userDao.adduserid(userid);
    }

    /**
     * 通过username获取用户信息
     * @param userName
     * @return
     */
    @Override
    public Map getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    /**
     * 获取账户中的userid
     * @param userid
     * @return
     */
    @Override
    public String getUserid(String userid) {

        return userDao.getUserbyUserid(userid);
    }


    /**
     * 用户提现  之后更新用户账户表
     * @param map
     * @return
     */
    @Override
    public int withdraw1(Map map) {
        return userDao.withdraw1(map);
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

    /**
     * 用户 提现添加用户流水表
     * @param map
     * @return
     */
    @Override
    public int recharge1(Map map) {
        return userDao.recharge1(map);
    }

    /**
     * 根据userId 统计用户累计投资和累计收益
     * @param map
     * @return
     */
    @Override
    public Map statistics(Map map) {
        return userDao.statistics(map);
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

    /**
     * 通过用户账号密码查询用户
     * @param telephone
     * @param password
     * @return
     */
    public Map getByuserName(String telephone, String password) {
       // System.out.println(telephone + password);
        return userDao.getByuserName(telephone, password);
    }

}
