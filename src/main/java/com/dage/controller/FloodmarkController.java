package com.dage.controller;

import com.dage.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @className:FloodmarkController
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-17 15:41
 */
@Controller
@RequestMapping("floodmark")
public class FloodmarkController {

    @Autowired
    private SubmitService submitService;
    /**
     * 跳转后台审核页面
     * @return
     */
    @RequestMapping("floodmark")
    public Object toSubmit(){
        return "business/floodmark";
    }

    /**
     * 展示满标待审核的详细信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("page")
    public Object submitList(@RequestBody Map map){
        return submitService.getListBidByFull(map);
    }

    /**
     * 满标审核
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public Object submitCheck(@RequestBody Map map){
        return submitService.updateBidState(map);
    }
}
