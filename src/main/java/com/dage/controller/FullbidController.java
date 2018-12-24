package com.dage.controller;

import com.dage.service.FullService;
import com.dage.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * className:FullbidController
 * discription:
 * author:zn
 * createTime:2018-12-21 09:55
 */
@Controller
@RequestMapping("/full")
public class FullbidController {

    @Autowired
    private FullService fullService;
    /**
     * 跳转后台审核页面
     * @return
     */
    @RequestMapping("tofull")
    public Object toSubmit(){
        return "business/fullbiddetails";
    }

    /**
     * 展示满标已审核的详细信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("page")
    public Object submitList(@RequestBody Map map){
        return fullService.getListBidByLoan(map);
    }
}
