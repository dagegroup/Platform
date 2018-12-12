package com.dage.service;

import com.dage.dao.BidDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:BidServiceImpl
 * discription:
 * author:lc
 * createTime:2018-12-10 17:29
 */
@Service
public class BidServiceImpl implements BidService{

    //依赖注入
    @Autowired
    private BidDao bidDao;

    /**
     * 查询
     * @return
     */
    @Override
    public List<Map> getList(Map map) {
        return bidDao.getList(null);
    }

    /**
     * 条件查询
     * @param map
     * @return
     */
    @Override
    public List<Map> getTerm(Map map) {
        return bidDao.getTerm(map);
    }
}
