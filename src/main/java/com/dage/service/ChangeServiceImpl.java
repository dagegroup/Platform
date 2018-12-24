package com.dage.service;

import com.dage.dao.ChangeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:ChangeServiceImpl
 * discription:
 * author:lc
 * createTime:2018-12-22 10:56
 */
@Service
public class ChangeServiceImpl implements ChangeService{

    @Autowired
    private ChangeDao changeDao;
    /**
     * 通过userid查询手机号码
     * @param map
     * @return
     */
    @Override
    public Map getPhone(Map map) {
        return changeDao.getPhone(map);
    }

    /**
     * 更新用户信息
     * @param map
     * @return
     */
    @Override
    public int updatePassword(Map map) {
        return changeDao.updatePassword(map);
    }

    @Override
    public int updatePayment(Map map) {
        return changeDao.updatePayment(map);
    }
}
