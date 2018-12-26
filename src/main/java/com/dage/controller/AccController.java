package com.dage.controller;

import com.dage.service.AccService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * className:AccController
 * discription:
 * author:ChenMing
 * creatTime:2018-12-22 10:18
 */
@Controller
@RequestMapping("acc")
public class AccController {
    @Autowired
    private AccService accService;

    /**
     * 跳转系统账户流水页面
     * @return
     */
    @RequestMapping("account")
    public String toAccount(){
        return "/reception/Account";
    }

    /**
     * 获取系统账户流水数据
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public Object  getList(@RequestBody Map map){
        System.out.println(map);
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        PageInfo<Map> pageInfo=new PageInfo<Map>(accService.getList(map));
        System.out.println(accService.getList(map));
        List<Map> flowtype = accService.getFlowtype();
        Map resultMap=new HashMap();
        resultMap.put("list",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
        resultMap.put("flowtype",flowtype);
        return resultMap;

    }
    /**
     * 获取模拟数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/edata")
    public Object getEchartsData(@RequestParam Map map){
        System.out.println(map);
        System.out.println(accService.getAccountFlow(map));
        return accService.getAccountFlow(map);
    }

    /**
     * 获取系统账户流水统计图数据
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/edata1")
    public Object getEchartsData1(@RequestParam Map map){

        System.out.println(map);

        return  accService.getBalance(map);
    }
    /**
     * 跳转账户流水界面
     */
    @RequestMapping("/showfinance")
    public String ShowFinance(){
        return "reception/FinanceAccount";
    }


}
