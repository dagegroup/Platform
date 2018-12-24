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
    @RequestMapping("account")
    public String toAccount(){
        return "/reception/Account";
    }
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

        return "FinanceAllService.getEChartsData(map)";
    }
    /**
     * 导出Excel表
     * @return
     */
    @RequestMapping("/echartDataExp")
    public String echartDataExp(Model model, @RequestParam Map map){
        model.addAttribute("dataList",  "FinanceAllService.getEChartsData(map)");
        return "finance/financeExl";
    }
    /**
     * 跳转财务汇总界面
     */
    @RequestMapping("/showfinance")
    public String ShowFinance(){
        return "finance/financeAll";
    }
    /**
     * 跳转到财务统计界面
     */
    @RequestMapping("/showfinanceAccount")
    public String ShowFinanceAccount(){
        return "finance/financeCount";
    }
    /**
     * 财务汇总数据
     */
    @ResponseBody
    @RequestMapping("/page")
    public Map GetFinanceAll(@RequestParam Map paramsMap){
        Map map= new HashMap();
        //分页查询总数量
        map.put("total"," FinanceAllService.getPageCount(paramsMap)");
        //分页查询结果
        map.put("rows"," FinanceAllService.getPage(paramsMap)");

        return map;
    }
}
