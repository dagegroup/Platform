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
    @RequestMapping("index")
    public String toIndex(){
        return "/reception/index";
    }
    @RequestMapping("infor")
    public String toInfor(){
        return "/reception/infor";
    }
    @RequestMapping("list")
    public String toList(){
        return "/reception/list";
    }
    @RequestMapping("register")
    public String toRegister(){
        return "/reception/register";
    }
    @RequestMapping("register1")
    public String toRegister1(){
        return "/reception/register1";
    }
    @RequestMapping("rent")
    public String toRent(){
        return "/reception/借款1";
    }
    @RequestMapping("rentInfo")
    public String toRentInfo(){
        return "/reception/借款信息填写1";
    }
    @RequestMapping("comDetial")
    public String toComDetial(){
        return "/reception/公司公告详细";
    }
    @RequestMapping("comInfo")
    public String toComInfo(){
        return "/reception/公司简介";
    }
    @RequestMapping("partner")
    public String toPartner(){
        return "/reception/合作伙伴";
    }
    @RequestMapping("teamInfo")
    public String toTeamInfo(){
        return "/reception/团队风采";
    }
    @RequestMapping("media")
    public String toMedia (){
        return "/reception/媒体报道";
    }
    @RequestMapping("helpInfo")
    public String toHelpInfo(){
        return "/reception/帮助中心";
    }
    @RequestMapping("theRap")
    public String toTheRap(){
        return "/reception/招贤纳士";
    }
    @RequestMapping("lawInfo")
    public String toLawInfo(){
        return "/reception/法律声明";
    }
    @RequestMapping("lawpolicy")
    public String toLawPolicy(){
        return "/reception/法律政策";
    }
    @RequestMapping("ipmt")
    public String toIPMT(){
        return "/reception/管理团队";
    }
    @RequestMapping("netNotice")
    public String toNetNotice(){
        return "/reception/网站公告";
    }
    @RequestMapping("contactUs")
    public String toContaceUs(){
        return "/reception/联系我们";
    }
    @RequestMapping("alertpay")
    public String toAlertpay(){
        return "/reception/资费说明";
    }
    @RequestMapping("borrow")
    public String toBorrow(){
        return "/reception/borrow";
    }
    @RequestMapping("rentInfo1")
    public String toRentInfo2(){
        return "/reception/借款信息填写2";
    }
    @RequestMapping("rentInfo2")
    public String toRentInfo3(){
        return "/reception/借款信息填写3";
    }

}
