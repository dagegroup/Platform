package com.dage.service;

import com.dage.dao.RepaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:RepaymentServiceImpl
 * discription: 由于处理个人的还款业务
 * author:CZP
 * createTime:2018-12-19 10:30
 */
@Service
public class RepaymentServiceImpl implements RepaymentService {

    @Autowired
    private RepaymentDao repaymentDao;

    /**
     * 根据用户Id查询用户借款列表
     * @param map
     * @return
     */
    @Override
    public List<Map> getList(Map map) {
        return repaymentDao.getList(map);
    }
}
