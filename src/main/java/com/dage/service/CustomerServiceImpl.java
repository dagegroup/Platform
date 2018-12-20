package com.dage.service;

import com.dage.dao.CustomerDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * className:CustomerServiceImpl
 * discription:
 * author:zn
 * createTime:2018-12-17 10:20
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Map getlist(Map map) {
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> getlist = customerDao.getlist(map);
        PageInfo<Map> pageInfo = new PageInfo<>(getlist);
        Map mp = new HashMap();
        mp.put("page",pageInfo);
        return mp;
    }
}
