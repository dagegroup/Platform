package com.dage.service;

import com.dage.entity.Emp;

import java.util.List;

/**
 * @className:EmpService
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-13 14:59
 */
public interface EmpService {
    /**
     * 显示所有可以登陆平台的账户
     * @return
     */
    List<Emp> getList();

    /**
     * 账户登陆时的方法
     * @param phone
     * @return
     */
    Emp getEmpByPhone(String phone);

    /**
     * 添加平台登陆账户
     * @param emp
     * @return
     */
    int add(Emp emp);

    /**
     * 账户登陆更新表内登陆时间
     * @param id
     * @return
     */
    int updateTime(Integer id);


    /**
     * 更新账户使用状态
     * @param id
     * @param state
     * @return
     */
    int updateStateTo(Integer id,String state);

    /**
     * 更改账户信息
     * @param emp
     * @return
     */
    int update(Emp emp);
}
