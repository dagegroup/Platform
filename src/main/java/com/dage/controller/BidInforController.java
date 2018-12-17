package com.dage.controller;

import com.dage.service.BidInforService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.scene.control.Alert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Source;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 项目详情页：标信息模块
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list(@RequestParam Map map){
        return bidInforService.getList(map);
    }

    /**
     *项目详情页：借款信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/userList")
    public Object userList(@RequestParam Map map){
        return bidInforService.getUserList(map);
    }

    /**
     * 项目详情页：投资记录
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/userInvest")
    public Object userInvest(@RequestParam Map map){
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> term = bidInforService.getUserInvest(map);
        PageInfo<Map> info = new PageInfo<>(term);
        mp.put("page",info);
        mp.put("total",info.getTotal());
        return mp;

        /*return bidInforService.getUserInvest(map);*/
    }

    @ResponseBody
    @RequestMapping("/refundRecord")
    public Object refundRecord(@RequestParam Map map){
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> term = bidInforService.getRefundRecord(map);
        PageInfo<Map> info = new PageInfo<>(term);
        mp.put("page",info);
        mp.put("total",info.getTotal());
        return mp;
    }

}
