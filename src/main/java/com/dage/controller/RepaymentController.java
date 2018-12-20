package com.dage.controller;

import com.dage.service.RepaymentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:RepaymentController
 * discription: 用于处理 用户还款的业务
 * author:CZP
 * createTime:2018-12-19 09:24
 */
@Controller
@RequestMapping("/Repayment")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;
    /**
     * 根据用户id查询用户的借款记录
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<Map> getRepayment(@RequestParam Map map){
        return repaymentService.getList(map);
    }

    /**
     * 分页配置
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        System.out.println(map);
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        System.out.println(Integer.valueOf(map.get("pageNo")+"")+Integer.valueOf(map.get("pageSize")+""));
        //用PageInfo对结果进行包装
        PageInfo<Map> pageInfo=new PageInfo<Map>(repaymentService.getList(map));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 根据 还款表的主键查询 还款计划的详细信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/particulars")
    public List<Map> getParticulars(@RequestParam Map map){
        return repaymentService.getList(map);
    }
}
