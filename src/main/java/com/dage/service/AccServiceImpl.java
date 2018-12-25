package com.dage.service;

import com.dage.dao.AccDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:AccServiceImpl
 * discription:
 * author:ChenMing
 * creatTime:2018-12-22 10:19
 */
@Service
public class AccServiceImpl implements AccService {
    @Autowired
    private AccDao accDao;

    @Override
    public List<Map> getBalance(Map map) {
        return accDao.getBalance(map);
    }

    @Override
    public List<Map> getIncome(Map map) {
        return accDao.getIncome(map);
    }

    @Override
    public List<Map> getAccountFlow(Map map) {
        return accDao.getAccountFlow(map);
    }

    @Override
    public List<Map> getFlowtype() {
        return accDao.getFlowtype();
    }

    @Override
    public List<Map> getList(Map map) {
        return accDao.getList(map) ;
    }
}
