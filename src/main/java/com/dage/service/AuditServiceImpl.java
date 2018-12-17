package com.dage.service;

import com.dage.dao.AuditDao;
import com.dage.entity.Emp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className:AuditServiceImpl
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-15 09:02
 */
@Service
public class AuditServiceImpl implements AuditService {
    @Autowired
    private AuditDao auditDao;
    @Override
    public Map getListAudit(Map map) {
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> listAudit = auditDao.getListAudit(map);
        PageInfo<Map> info = new PageInfo<>(listAudit);
        mp.put("page",info);
        return mp;
    }

    @Override
    public Map getListAuditDetial(String bidid) {
        return auditDao.getListAuditDetial(bidid);
    }

    @Override
    public int updateBidState(Map map, HttpSession session) {
        Emp admin = (Emp)session.getAttribute("admin");
        map.put("EMPID",admin.getId());
        return auditDao.updateBidState(map);
    }

//    auditorid,audittime,auditorname,auditresult,auditremarks,
    @Override
    public int updateRealState(Map map, HttpSession session) {
        Emp admin = (Emp)session.getAttribute("admin");
        map.put("AUDITORID",admin.getId());
        map.put("AUDITORNAME",admin.getName());
        return auditDao.updateRealState(map);
    }

    @Override
    public Map getRealByUserId(String id) {
        return auditDao.getRealByUserId(id);
    }
}
