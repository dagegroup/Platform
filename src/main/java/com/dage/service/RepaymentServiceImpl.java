package com.dage.service;

import com.dage.dao.RepaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
     * 根据bidid查询标的总额
     * @param bidid
     * @return
     */
    @Override
    public double getBidamount(String bidid) {
        return repaymentDao.getBidamount(bidid);
    }

    /**
     * 用户余额够时 用户还款 更新投资者的 账户余额
     *
     * @param map
     * @return
     */
    @Override
    public int updateInvestor(Map map) {
        return repaymentDao.updateInvestor(map);
    }


    /**
     * 根据 标信息表Id查询投资人信息
     * @param bidid
     * @return
     */
    @Override
    public List<Map> getInvestor(String bidid) {
        return repaymentDao.getInvestor(bidid);
    }

    /**
     *查询每一个投资人投资了该标多少钱
     * @param map
     * @return
     */
    @Override
    public double getSum(Map map) {
        return repaymentDao.getSum(map);
    }

    /**
     * 用户余额够时 用户还款 添加借款人账户流水
     * @param map
     * @return
     */
    @Override
    public int insertInvestorFlow(Map map) {
        return repaymentDao.insertInvestorFlow(map);
    }

    /**
     *
     *用户余额够时 用户还款 更新还款计划表中该列的 还款状态
     * @param map
     * @return
     */
    @Override
    public int updateRepay(Map map) {
        return repaymentDao.updateRepay(map);
    }

    /**
     * 根据用户id查询用户的账户余额
     * @param map
     * @return
     */
    @Override
    public Map getBalance(Map map) {
        return repaymentDao.getBalance(map);
    }

    /**
     * 用户余额够时 用户还款 更新用户可用余额
     * @param map
     * @return
     */
    @Override
    public int updateAmount(Map map) {
        return repaymentDao.updateAmount(map);
    }

    /**
     * 用户余额够时 用户还款 添加用户账户流水
     * @param map
     * @return
     */
    @Override
    public int updateAccountFlow(Map map) {
        return repaymentDao.updateAccountFlow(map);
    }

    /**
     * 根据还款计划Id查询需要还的钱
     * @param map
     * @return
     */
    @Override
    public Map getAmount(Map map) {
        return repaymentDao.getAmount(map);
    }

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
