package com.dage.service;

import java.util.Map;

/**
 * className:RentInfoService
 * discription:
 * author:ChenMing
 * creatTime:2018-12-17 14:35
 */
public interface RentInfoService {
    int add(Map map);
    int addRentDetialInfo(Map map);
    int addRentInfo2(Map map);
    Map getInfo(String userid);
}
