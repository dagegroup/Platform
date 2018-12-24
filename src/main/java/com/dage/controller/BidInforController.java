package com.dage.controller;

import com.dage.service.BidInforService;
import com.dage.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

    /**
     * 项目详情页：还款记录
     * @param map
     * @return
     */
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

    /**
     * 我要投标
     * @param map
     * @return
     * RequestBody 该方法接收的数据为json对象
     * ResponseBody 该方法的返回值为json对象
     */
    @ResponseBody
    @RequestMapping("/tender")
    public Object tender(@RequestBody Map map,HttpSession session){

        Object username = session.getAttribute("userName");

        if (username!=null&&username!=""){
            return bidInforService.tender(map);
        }else{
            return -1;
        }
    }

    /**
     * 投标提交至记录表
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/bidSubmit")
    public Object bidSubmit(@RequestBody Map map,HttpSession session){
        return bidInforService.bidSubmit(map,session);
    }

    /**
     * 判断投资金额与可投金额
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/canMoney")
    public Object canMoney(@RequestBody Map map){
        int i = bidInforService.canMoney(map);
        if (i>0){
            return 1;
        }
        return 0;
    }


}
