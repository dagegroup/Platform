package com.dage.controller;

import com.dage.service.RepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @className:RepayController
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-18 15:41
 */
@Controller
@RequestMapping("repay")
public class RepayController {
    @Autowired
    private RepayService repayService;

    /**
     * 跳转后台审核页面
     * @return
     */
    @RequestMapping("repay")
    public Object toSubmit(){
        return "business/repay";
    }
    /**
     * 展示待还款的详细信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("page")
    public Object loanList(@RequestBody Map map){
        return repayService.getListBidByRepay(map);
    }

    /**
     * 根据标id 查询 repay表中的标分期信息
     * @param bidid
     * @return
     */
    @ResponseBody
    @RequestMapping("one")
    public Object submitOne(String bidid){
        return repayService.getRepayByBidid(bidid);
    }
}
