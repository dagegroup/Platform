package com.dage.controller;

import com.dage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * className:PersonController
 * discription:
 * author:CZP
 * createTime:2018-12-10 09:29
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private UserService userService;

    /**
     * 根据用户编号查询用户信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("index")
    public List<Map> getPerson(String userId){
       /* System.out.println(userId);
        System.out.println(userService.getList(userId));*/
        return userService.getList(userId);
    }

    /**
     * 根据用户编号查询用户流水
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/flow")
    public List<Map> getFlow(String userId){
        return userService.getFlow(userId);
    }

    /**
     * 根据用户ID查询用户投资记录
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/submit")
    public List<Map> getSubmit(String userId){
        return userService.getSubmit(userId);
    }

    /**
     * 根据用户id查询用户还款计划
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/repay")
    public List<Map> getRepay(String userId){
        return userService.getRepay(userId);
    }

    /**
     * 根据用户id查询用户账户信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/account")
    public List<Map> getAccount(String userId){
        System.out.println(userId);
        return userService.getAccount(userId);
    }


}
