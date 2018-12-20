package com.dage.controller;

import com.dage.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @className:SubmitController
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-17 14:06
 */
@Controller
@RequestMapping("submit")
public class SubmitController {
    @Autowired
    private SubmitService submitService;
    /**
     * 跳转后台审核页面
     * @return
     */
    @RequestMapping("submit")
    public Object toSubmit(){
        return "business/submit";
    }

    /**
     * 展示待投标的详细信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("page")
    public Object submitList(@RequestBody Map map){
        return submitService.getListBid(map);
    }

    /**
     * 根据标id 查询 submit标 中的投标记录
     * @param bidid
     * @return
     */
    @ResponseBody
    @RequestMapping("one")
    public Object submitOne(String bidid){
        return submitService.getSubmitByBidid(bidid);
    }
}
