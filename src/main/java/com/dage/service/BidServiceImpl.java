package com.dage.service;

import com.dage.dao.BidDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        /*List<Map> term = bidDao.getTerm(map);
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        PageInfo<Map> info = new PageInfo<>(term);
        Map mp = new HashMap();
        mp.put("page",info);
        return mp;*/


        return bidDao.getTerm(map);
    }

}
