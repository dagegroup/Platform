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

        double oldavailablebalance = bidInforDao.balance(map);//从数据库里得到账户余额

        double bidamount = Double.valueOf(map.get("BIDAMOUNT").toString());//从前台得到输入投标金额
        double bidrate = Double.valueOf(map.get("BIDRATE").toString()) ;//从前台得到招标利率

        /*插入账户流水表开始*/
        Map mp = bidInforDao.userAccountid(map);//根据userid从数据库账户表里获取账户id
        String accountid = mp.get("ACCOUNTID")+"";
        map.put("ACCOUNTID",accountid);//将ACCOUNTID加到map中

        double amount=bidamount;//资金变动金额=前台输的投标金额bidamount
        map.put("AMOUNT",amount);//将资金变动金额加到map里

        double availablebalance=oldavailablebalance-bidamount;//投标后更新可用金额
        map.put("AVAILABLEBALANCE",availablebalance);//投标后可用金额传到map里
        /*插入账户流水表结束*/

        /*更新账户表数据开始*/
        double principal = bidInforDao.principal(map);//从数据库获取历史代收本金
        double receiveprincipal = principal + bidamount;//代收本金 = 历史代收本金 + 投资金额
        map.put("RECEIVEPRINCIPAL",receiveprincipal); //投标后的代收本金传到map里

        double interest = bidInforDao.interest(map);//从数据库获取历史代收利息
        double receivenumbererest =interest + bidamount*(bidrate/12)*0.01;//代收利息=历史利息+投标金额*招标利率
        map.put("RECEIVENUMBEREREST",receivenumbererest);//投标后代收利息传到map里

        //变动后可用余额调用上面的AVAILABLEBALANCE即可
        /*更新账户表数据结束*/
        map.put("BIDCURRENTAMOUNT",bidamount);//将前台的投标金额赋给BIDCURRENTAMOUNT加入到map
        /*更新标信息表开始*/

        /*更新标信息表结束*/
        if (oldavailablebalance>=bidamount){
            bidInforDao.changeBidInfo(map);//更新标信息表可投金额
            bidInforDao.userAccount(map);//更新账户表
            bidInforDao.accountRun(map);//向账户流水表里插入数据
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
        double canmoney = bidInforDao.canMoney(map);//从数据库获取可投金额
        double bidamount = Double.valueOf(map.get("BIDAMOUNT").toString());//从前台得到输入投标金额
        if (canmoney>=bidamount){
            if (bidamount>0){
                return 1;
            }
        }
        return 0;
    }

}
