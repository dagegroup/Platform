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


    /**
     * 分页
     * @param map
     * @return
     */
    @Override
    public List<Map> getPage(Map map) {
        int pageNo = map.get("pageNo") == null?1:Integer.valueOf(map.get("pageNo")+"");
        int pageSize = map.get("pageSize") == null?5:Integer.valueOf(map.get("pageSize")+"");
        map.put("start",(pageNo-1)*pageSize);
        map.put("end",pageNo+pageSize+1);
        return bidDao.getPage(map);
    }
    /**
     * 总数量
     * @param map
     * @return
     */
    @Override
    public int getPageCount(Map map) {
        return bidDao.getPageCount(map);
    }
}
