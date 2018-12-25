package com.dage.service;

import com.dage.dao.EmpDao;
import com.dage.entity.Emp;
import com.dage.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className:EmpServiceImpl
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-13 15:00
 */
@Service
public class EmpServiceImpl implements EmpService{

    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> getList() {
        return empDao.getList();
    }

    @Override
    public Emp getEmpByPhone(String phone) {
        return empDao.getEmpByPhone(phone);
    }

    @Override
    public int add(Emp emp) {
        String encrypt = AESUtil.getInstance().encrypt(emp.getPassword());
        emp.setPassword(encrypt);
        return empDao.add(emp);
    }

    @Override
    public int updateTime(Integer id) {
        return empDao.updateTime(id);
    }

    @Override
    public int updateStateTo(Integer id, String state) {
        return empDao.updateStateTo(id,state);
    }

    @Override
    public int update(Emp emp) {
        return empDao.update(emp);
    }
}
