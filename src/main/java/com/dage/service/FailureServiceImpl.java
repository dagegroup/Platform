package com.dage.service;

import com.dage.dao.FailureDao;
import com.dage.dao.RepayDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:FailureServiceImpl
 * discription:
 * author:zn
 * createTime:2018-12-18 11:37
 */
@Service
public class FailureServiceImpl implements FailureService {

    @Autowired
    private FailureDao failureDao;
    @Autowired
    private FailureService failureService;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Map getlist(Map map) {
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> getlist = failureDao.getlist(map);
        PageInfo<Map> pageInfo = new PageInfo<>(getlist);
        mp.put("page",pageInfo);
        return mp;
    }

    @Override
    public Map getMapByBidid(String bidid) {
        Map mp = new HashMap();
        mp.put("data",failureDao.getMapByBidid(bidid));
        return mp;
    }

    @Override
    public Map getUserAccountByUserid(String userid) {
        return failureDao.getUserAccountByUserid(userid);
    }

    @Override
    public Map getBidInfoByBidid(String bidid) {
        return failureDao.getBidInfoByBidid(bidid);
    }

    @Override
    public int repayFailure(String bidid) {
        SqlSession sqlSession = null;
        Map submitMap = failureService.getMapByBidid(bidid);
        List data = (List)submitMap.get("data");
        try {
            sqlSession = sqlSessionFactory.openSession();
            FailureDao failureDaos = sqlSession.getMapper(FailureDao.class);
            for (Object o : data) {
                Map m = (Map) o;
                Map userMap = failureDao.getUserAccountByUserid(m.get("USERID") + "");
                if (userMap != null && userMap.size() > 0) {
                    Integer a = Integer.valueOf(userMap.get("AVAILABLEBALANCE") + "");
                    Integer a1 = Integer.valueOf(m.get("BIDAMOUNT") + "");
                    Integer amount = Integer.valueOf(m.get("BIDAMOUNT") + "");
                    String accountid = userMap.get("ACCOUNTID") + "";
                    m.put("AVAILABLEBALANCE", a + a1);
                    m.put("ACCOUNTID", accountid);
                    m.put("FLOWTYPE", "投资");
                    m.put("AMOUNT",amount);
                    int i = failureDaos.updateUserAccount(m);
                    int x= failureDaos.insertUserFlow(m);
                    if (i <0||x<0) {
                        sqlSession.rollback();
                        return 0;
                    }
                }
            }
            Map bidinfo = failureService.getBidInfoByBidid(bidid);
            int y=0;
            if(bidinfo!=null&&bidinfo.size()>0){
                String userid = bidinfo.get("USERID") + "";
                 y= failureDaos.updateUserInfo(userid);
            }
            int z = failureDaos.updateBidSubmit(bidid);
            if(y<0||z<0){
                sqlSession.rollback();
                return 0;
            }else{
                sqlSession.commit();
                return 1;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return 1;
    }
}
