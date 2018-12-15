package com.dage.controller;

import com.dage.service.BidInforService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * className:BidInforController
 * discription:
 * author:lc
 * createTime:2018-12-14 08:47
 */
@Controller
@RequestMapping("/infor")
public class BidInforController {

    @Autowired
    private BidInforService bidInforService;

    @ResponseBody
    @RequestMapping("/list")
    public Object list(@RequestParam Map map){

        return bidInforService.getList(map);
    }
}
