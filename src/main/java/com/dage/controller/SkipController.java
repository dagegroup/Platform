package com.dage.controller;

import com.dage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * className:SkipController
 * discription: 用于个人中心内各页面的跳转
 * author:CZP
 * createTime:2018-12-12 19:51
 */
@Controller
@RequestMapping("/skip")
public class SkipController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到个人中心首页
     * 用户充值之后添加用户流水表和更新用户账户表
     * @return
     */
    @RequestMapping("/toPersonIndex")
    public String toPersonIndex(@RequestParam Map map){
        if (map.size()>0){
            /*Object o = map.get("bankCardNo");
            System.out.println(o);
            Object o1 = map.get("actualMoney1");
            System.out.println(o1);*/
            try {
                userService.recharge(map);
            } catch (Exception e) {
                System.out.println("出错了！！");
            }
        }
        return "个人中心首页";
    }

    /**
     * 跳转到个人中心充值
     *
     * 用户提现之后更新用户账户  添加用用户账户流水 添加系统账户流水
     * @return
     */
    @RequestMapping("/Recharge")
    public String toRecharge(@RequestParam Map map){
        if (map.size()>0){
            //System.out.println(map.get("money"));
            String userid= "U201812071032";
            map.put("userId",userid);
            System.out.println("手续费"+map.get("procedure"));

            //System.out.println(map.get("userId"));
        }
        return "个人中心-充值";
    }
    /**
     * 跳转到个人中心充值1
     * @return
     */
    @RequestMapping("/Recharge1")
    public String toRecharge1(){
        return "个人中心-充值1";
    }

    /**
     * 跳转到个人中心兑换历史
     * @return
     */
    @RequestMapping("//Conversion")
    public String toConversion(){
        return "个人中心-兑换历史";
    }

    /**
     * 跳转到个人中心回款计划
     * @return
     */
    @RequestMapping("/Plan")
    public String toPlan(){
        return "个人中心-回款计划";
    }

    /**
     * 跳转到个人中心开通第三方
     * @return
     */
    @RequestMapping("/Thirdparty")
    public String toThirdparty(){
        return "个人中心-开通第三方";
    }

    /**
     * 跳转到个人中心开通第三方1
     * @return
     */
    @RequestMapping("/Thirdparty1")
    public String toThirdparty1(){
        return "个人中心-开通第三方1";
    }

    /**
     * 跳转到个人中心我的红包
     * @return
     */
    @RequestMapping("/Packet")
    public String toPacket(){
        return "个人中心-我的红包";
    }

    /**
     * 跳转到个人中心投资记录
     * @return
     */
    @RequestMapping("/Investment")
    public String toInvestment(){
        return "个人中心-投资记录";
    }

    /**
     * 跳转到个人中心提现
     * @return
     */
    @RequestMapping("/Withdraw")
    public String toWithdraw(){
        return "个人中心-提现";
    }

    /**
     * 跳转到个人中心提现1
     * @return
     */
    @RequestMapping("/Withdraw1")
    public String toWithdraw1(){
        return "个人中心-提现1";
    }

    /**
     * 跳转到个人中心系统消息
     * @return
     */
    @RequestMapping("/Messages")
    public String toMessages(){
        return "个人中心-系统消息";
    }

    /**
     * 跳转到个人中心账户设置
     * @return
     */
    @RequestMapping("/Setting")
    public String toSetting(){
        return "个人中心-账户设置";
    }

    /**
     * 跳转到个人中心资金记录
     * @return
     */
    @RequestMapping("/Record")
    public String toRecord(){
        return "个人中心-资金记录";
    }

    /**
     * 跳转到个人中心还款
     *
     * @return
     */
    @RequestMapping("/repayment")
    public String toRepayment(){
        return "个人中心-还款";
    }
}
