package com.dage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:GotoController
 * discription:
 * author:ChenMing
 * creatTime:2018-12-14 08:36
 */
@Controller
@RequestMapping("goto")
public class GotoController {
    /**
     * 跳转index
     * @return
     */
    @RequestMapping("index")
    public String toIndex(){
        return "/reception/index";
    }
    /**
     * 跳转infor
     * @return
     */
    @RequestMapping("infor")
    public String toInfor(){
        return "/reception/infor";
    }
    /**
     * 跳转list
     * @return
     */
    @RequestMapping("list")
    public String toList(){
        return "/reception/list";
    }
    /**
     * 跳转register
     * @return
     */
    @RequestMapping("register")
    public String toRegister(){
        return "/reception/register";
    }
    /**
     * 跳转register1
     * @return
     */
    @RequestMapping("register1")
    public String toRegister1(){
        return "/reception/register1";
    }
    /**
     * 跳转借款1
     * @return
     */
    @RequestMapping("rent")
    public String toRent(){
        return "/reception/借款1";
    }
    /**
     * 跳转借款信息填写1
     * @return
     */
    @RequestMapping("rentInfo")
    public String toRentInfo(){
        return "/reception/借款信息填写1";
    }
    /**
     * 跳转公司公告详细
     * @return
     */
    @RequestMapping("comDetial")
    public String toComDetial(){
        return "/reception/公司公告详细";
    }
    /**
     * 跳转公司简介
     * @return
     */
    @RequestMapping("comInfo")
    public String toComInfo(){
        return "/reception/公司简介";
    }
    /**
     * 跳转合作伙伴
     * @return
     */
    @RequestMapping("partner")
    public String toPartner(){
        return "/reception/合作伙伴";
    }
    /**
     * 跳转团队风采
     * @return
     */
    @RequestMapping("teamInfo")
    public String toTeamInfo(){
        return "/reception/团队风采";
    }
    /**
     * 跳转媒体报道
     * @return
     */
    @RequestMapping("media")
    public String toMedia (){
        return "/reception/媒体报道";
    }
    /**
     * 跳转帮助中心
     * @return
     */
    @RequestMapping("helpInfo")
    public String toHelpInfo(){
        return "/reception/帮助中心";
    }
    /**
     * 跳转招贤纳士
     * @return
     */
    @RequestMapping("theRap")
    public String toTheRap(){
        return "/reception/招贤纳士";
    }
    /**
     * 跳转法律声明
     * @return
     */
    @RequestMapping("lawInfo")
    public String toLawInfo(){
        return "/reception/法律声明";
    }
    /**
     * 跳转法律政策
     * @return
     */
    @RequestMapping("lawpolicy")
    public String toLawPolicy(){
        return "/reception/法律政策";
    }
    /**
     * 跳转管理团队
     * @return
     */
    @RequestMapping("ipmt")
    public String toIPMT(){
        return "/reception/管理团队";
    }
    /**
     * 跳转网站公告
     * @return
     */
    @RequestMapping("netNotice")
    public String toNetNotice(){
        return "/reception/网站公告";
    }
    /**
     * 跳转联系我们
     * @return
     */
    @RequestMapping("contactUs")
    public String toContaceUs(){
        return "/reception/联系我们";
    }
    /**
     * 跳转资费说明
     * @return
     */
    @RequestMapping("alertpay")
    public String toAlertpay(){
        return "/reception/资费说明";
    }
    /**
     * 跳转borrow
     * @return
     */
    @RequestMapping("borrow")
    public String toBorrow(){
        return "/reception/borrow";
    }
    /**
     * 跳转借款信息填写2
     * @return
     */
    @RequestMapping("rentInfo1")
    public String toRentInfo2(){
        return "/reception/借款信息填写2";
    }
    /**
     * 跳转借款信息填写3
     * @return
     */
    @RequestMapping("rentInfo2")
    public String toRentInfo3(){
        return "/reception/借款信息填写3";
    }

    /**
     * 跳转后台登录
     * @return
     */
    @RequestMapping("backLogin")
    public String tobackLoign(){
        return "/reception/BackLogin";
    }
    /**
     * 跳转index
     * @return
     */
    @RequestMapping("detial")
    public String torentIn(){
        return "/reception/借款介绍";
    }


}
