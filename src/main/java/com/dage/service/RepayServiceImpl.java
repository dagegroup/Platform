package com.dage.service;

import com.dage.dao.RepayDao;
import com.dage.dao.SubmitDao;
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
 * @className:RepayServiceImpl
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-18 14:07
 */
@Service
public class RepayServiceImpl implements RepayService {

    @Autowired
    private RepayDao repayDao;
    @Autowired
    private SubmitDao submitDao;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public int repayPlanHandle(Map map) {
        SqlSession sqlSession = null;
        Map bidInfo = repayDao.getBidInfoByBid(map.get("BIDID") + "");
        double bidamount = Integer.valueOf(bidInfo.get("BIDAMOUNT")+"");
        double bidrate = Integer.valueOf(bidInfo.get("BIDRATE")+"");
        double biddeadline = Integer.valueOf(bidInfo.get("BIDDEADLINE")+"");
        String bidid = bidInfo.get("BIDID")+"";
        String userid = bidInfo.get("USERID")+"";
        double bidamounts = bidamount * (bidrate / 100) / 12*biddeadline + bidamount;
        double x = bidamount * (bidrate / 100) / 12  + bidamount / biddeadline;
        //System.out.println(x);
        //System.out.println(bidamounts);
        Boolean flag = true;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
            RepayDao repayDaos = sqlSession.getMapper(RepayDao.class);
            Map mp = new HashMap();
            mp.put("BIDID",bidid);
            mp.put("USERID",userid);
            mp.put("BIDREPAYAMOUNT",x);
            mp.put("nums",biddeadline);
            mp.put("BIDSTATE","待还款");
            for(int y=1;y<=biddeadline;y++){
                mp.put("num",y);
                if(y==biddeadline){
                    double z = bidamounts - x*(y-1);
                    //System.out.println(z);
                    mp.put("BIDREPAYAMOUNT",z);
                }
                int i = repayDaos.addRepayPlan(mp);
                repayDaos.updateUserState(userid);
                if (i==0){
                    flag=false;
                }
            }
            int j = submitDao.updateBidState(mp);
            if(j<0){
                flag=false;
            }
            if (flag){
                sqlSession.commit();
                return 1;
            }else {
                sqlSession.rollback();
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return 1;
    }

    @Override
    public Map getListBidByRepay(Map map) {
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> listBid = repayDao.getListBidByRepay(map);
        PageInfo<Map> info = new PageInfo<>(listBid);
        mp.put("page",info);
        return mp;
    }

    @Override
    public Map getRepayByBidid(String bidid) {
        Map mp = new HashMap();
        mp.put("data",repayDao.getListByBid(bidid));
        return mp;
    }
}
