package com.dage.service;

import com.dage.dao.AuditDao;
import com.dage.dao.RepayDao;
import com.dage.dao.SubmitDao;
import com.dage.entity.Emp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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
@Transactional
public class RepayServiceImpl implements RepayService {

    @Autowired
    private RepayDao repayDao;
    @Autowired
    private SubmitDao submitDao;
    @Autowired
    private AuditDao auditDao;
    @Override
    public int repayPlanHandle(Map map, HttpSession session) {
        Map bidInfo = repayDao.getBidInfoByBid(map.get("BIDID") + "");
        double bidamount = Integer.valueOf(bidInfo.get("BIDAMOUNT")+"");
        double bidrate = Integer.valueOf(bidInfo.get("BIDRATE")+"");
        double biddeadline = Integer.valueOf(bidInfo.get("BIDDEADLINE")+"");
        double money = bidamount * 0.95;
        double smoney = bidamount-money;
        String bidid = bidInfo.get("BIDID")+"";
        String userid = bidInfo.get("USERID")+"";
        double bidamounts = bidamount * (bidrate / 100) / 12*biddeadline + bidamount;
        double x = bidamount * (bidrate / 100) / 12  + bidamount / biddeadline;
        Boolean flag = true;
        Map mp = new HashMap();
        mp.put("BIDID",bidid);
        mp.put("USERID",userid);
        mp.put("BIDREPAYAMOUNT",x);
        mp.put("nums",biddeadline);
        mp.put("BIDSTATE","待还款");
        mp.put("MONEY",money);
        mp.put("money",smoney);
        for(int y=1;y<=biddeadline;y++){
            mp.put("num",y);
            if(y==biddeadline){
                double z = bidamounts - x*(y-1);
                //System.out.println(z);
                mp.put("BIDREPAYAMOUNT",z);
            }
            int i = repayDao.addRepayPlan(mp);
            if (i==0){
                flag=false;
            }
        }
        int a = repayDao.updateUserState(userid);
        int b = repayDao.updateUserAccount(mp);
        int c = repayDao.updateUserAccFlow(mp);
        int d = repayDao.updateSysAccFlow(mp);
        int j = submitDao.updateBidState(mp);
        Emp admin = (Emp)session.getAttribute("admin");
        mp.put("EMPID",admin.getId());
        int e = auditDao.AddAudit(mp);
        if(j<1||a<1||b<1||c<1||d<1||e<1){
            flag=false;
        }
        if (flag){
            return 1;
        }else {
            return 0;
        }
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
