package com.dage.controller;

import com.dage.service.RepayService;
import com.dage.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @className:LoanController
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-18 11:09
 */
@Controller
@RequestMapping("loan")
public class LoanController {
    @Autowired
    private SubmitService submitService;

    @Autowired
    private RepayService repayService;
    /**
     * 跳转后台审核页面
     * @return
     */
    @RequestMapping("loan")
    public Object toSubmit(){
        return "business/loan";
    }

    /**
     * 展示待放款的详细信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("page")
    public Object loanList(@RequestBody Map map){
        return submitService.getListBidByLoan(map);
    }

    @ResponseBody
    @RequestMapping("check")
    public Object loanRepay(@RequestBody Map map){
        return repayService.repayPlanHandle(map);
    }

}
