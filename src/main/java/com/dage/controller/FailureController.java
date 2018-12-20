package com.dage.controller;


import com.dage.service.FailureService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;


/**
 * className:FailureController
 * discription:
 * author:zn
 * createTime:2018-12-18 11:25
 */
@Controller
@RequestMapping("failure")
public class FailureController {

    @Autowired
    private FailureService failureService;

    /**
     * 跳转流标信息页面
     * @return
     */
    @RequestMapping("tolist")
    public Object toList(){
        return "business/failure";
    }

    @ResponseBody
    @RequestMapping("page")
    public Object getPage(@RequestBody  Map map){
        return failureService.getlist(map);
    }

    @ResponseBody
    @RequestMapping("one")
    public Object submitOne(String bidid, HttpSession session){
        session.setAttribute("bidid",bidid);
        return failureService.getMapByBidid(bidid);
    }

    @ResponseBody
    @RequestMapping("update")
    public Object toUpdate(HttpSession session){
        String bidid = (session.getAttribute("bidid") + "");
        int i = failureService.updateBidSubmit(bidid);
        System.out.println(i);
        if (i>0){
              i = failureService.updateUserAccount(bidid);
            if (i>0){
                 i = failureService.updateUserInfo(bidid);
                 if (i>0){
                    i= failureService.insertUserFlow(bidid);
                 }
            }
        }
        session.removeAttribute("bidid");
        return i;
    }
}
