package com.dage.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:CustomerService
 * discription:
 * author:zn
 * createTime:2018-12-17 10:18
 */

public interface CustomerService {





    /**
     *查询客户信息表
     * @return
     */
    Map getlist(Map map);
}
