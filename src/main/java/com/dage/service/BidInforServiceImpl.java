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
     * 投标提交至记录表(比较投资金与账户余额)
     * @param map
     * @return
     */
    @Override
    public int bidSubmit(Map map,HttpSession session) {
        String userid = (String)session.getAttribute("userid");//从session得到投资人id
        map.put("USERID",userid);//将投资人id加到map里传到dao层
        Integer availablebalance = bidInforDao.balance(map);//从数据库里得到账户余额
        Integer bidamount = Integer.valueOf(map.get("BIDAMOUNT").toString());//从前台得到输入投标金额
        if (availablebalance>=bidamount){
            return bidInforDao.bidSubmit(map);//返回值为成功的行数
        }
        return 0;
    }

    /**
     * 判断是否可投
     * @param map
     * @return
     */
    public int canMoney(Map map){
        Integer canmoney = bidInforDao.canMoney(map);//从数据库获取可投金额
        Integer bidamount = Integer.valueOf(map.get("BIDAMOUNT").toString());//从前台得到输入投标金额
        if (canmoney>=bidamount){
            return 1;
        }
        return 0;
    }

}
