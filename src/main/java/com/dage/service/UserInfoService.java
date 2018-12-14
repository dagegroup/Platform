package com.dage.service;

import java.util.List;
import java.util.Map;

/**
 * className:UserInfoService
 * discription:
 * author:zn
 * createTime:2018-12-11 17:14
 */
public interface UserInfoService {

    /**
     * 获得用户信息表信息
     * @return
     */
    List<Map> getList();

    /**
     * 通过电话号码查询用户信息
     * @return
     */
    List<Map> getListByPhone(String telephone);

    /**
     * 通过用户信息查询信息
     * @param refereeName
     * @return
     */
    List<Map> getListByRefname(String refereeName);

    /**
     * 通过姓名获取用户信息
     * @param userName
     * @return
     */
    List<Map> getListByName(String userName);

    /**
     * 插入用户信息
     * @param map
     * @return
     */
    int insertInfo(Map map);
}
