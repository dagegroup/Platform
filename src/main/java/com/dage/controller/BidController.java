package com.dage.controller;

import com.dage.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * className:BidController
 * discription:
 * author:lc
 * createTime:2018-12-10 17:26
 */
@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    private BidService bidService;

    /**
     * 标列表方法
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(Map map){
        return bidService.getList(map);
    }

    /**
     * 条件查询标列表
     * @return
     */
    @RequestMapping("/term")
    @ResponseBody
    public Object term(@RequestParam Map map){
        return bidService.getTerm(map);
    }




}
