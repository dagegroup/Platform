package com.dage.service;

import java.util.Map;

/**
 * className:UserService
 * discription:
 * author:ChenMing
 * creatTime:2018-12-10 16:41
 */
public interface UserService {

    Map  getByuserName(String telephone,String password);
    String getUserid(String userid);
    int addUserid(String userid);
}
