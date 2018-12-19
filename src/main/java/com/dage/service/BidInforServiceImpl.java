package com.dage.service;

import com.dage.dao.BidInforDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * className:BidInforServiceImpl
 * discription:
 * author:lc
 * createTime:2018-12-14 11:07
 */
@Service
public class BidInforServiceImpl implements BidInforService {

    @Autowired
    private BidInforDao bidInforDao;

    /**
     * 项目详情页：获取标信息
     * @param map
     * @return
     */
    public List<Map> getList(Map map){
        return bidInforDao.getList(map);
    }

    /**
     * 项目详情页：借款人信息
     * @param map
     * @return
     */
    @Override
    public List<Map> getUserList(Map map) {
        return bidInforDao.getUserList(map);
    }

    /**
     * 项目详情页：投资记录
     * @param map
     * @return
     */
    @Override
    public List<Map> getUserInvest(Map map) {
        return bidInforDao.getUserInvest(map);
    }

    /**
     * 项目详情页：还款记录
     * @param map
     * @return
     */
    @Override
    public List<Map> getRefundRecord(Map map) {
        return bidInforDao.getRefundRecord(map);
    }

    /**
     * 我要投标
     * @param map
     * @return
     */
    public Map tender(Map map){

        return bidInforDao.tender(map);
    }
    /**
     * 投标提交至记录表
     * @param map
     * @return
     */
    @Override
    public int bidSubmit(Map map,HttpSession session) {
        String userid = (String)session.getAttribute("userid");
        map.put("userid",userid);
        return bidInforDao.bidSubmit(map);
    }

    /**
     * 判断是否可投
     * @param map
     * @return
     */
    public int canMoney(Map map){
        Integer canmoney = bidInforDao.canMoney(map);
        Integer bidamount = Integer.valueOf(map.get("BIDAMOUNT").toString());
        if (canmoney>=bidamount){
            return 1;
        }
        return 0;
    }

}
