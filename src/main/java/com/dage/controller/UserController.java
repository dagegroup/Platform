package com.dage.controller;

import com.dage.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * className:UserController
 * discription:
 * author:ChenMing
 * creatTime:2018-12-10 16:32
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("toLogin")
    public String toLogin(HttpSession session){
        if(session.getAttribute("userName")!=null) {
            session.setAttribute("userName",null);
            session.removeAttribute("userName");
        }
        return "login";
    }
    @RequestMapping("toOwn")
    public String toOwn(HttpSession session){
        Object userName = session.getAttribute("userName");
        if(userName!=null&&userName!=""){
            return "redirect:/skip/toPersonIndex";
        }
        return "redirect:/user/toLogin";

    }
    @RequestMapping("toOwn1")
    public String toOwn1(){
        return "个人中心-资金记录";
    }
    @RequestMapping("toOwn2")
    public String toOwn2(){
        return "个人中心-投资记录";
    }

    /**
     * 回款计划
     * @return
     */
    @RequestMapping("toOwn3")
    public String toOwn3(){
        return "个人中心-回款计划";
    }

    /**
     *开通第三方
     * @return
     */
    @RequestMapping("toOwn4")
    public String toOwn4(){
        return "个人中心-开通第三方";
    }
    //充值
    @RequestMapping("toOwn5")
    public String toOwn5(){
        return "个人中心-充值";
    }
    //提现
    @RequestMapping("toOwn6")
    public String toOwn6(){
        return "个人中心-提现1";
    }
    //我的红包
    @RequestMapping("toOwn7")
    public String toOwn7(){
        return "个人中心-我的红包";
    }
    //兑换历史
    @RequestMapping("toOwn8")
    public String toOwn8(){
        return "个人中心-兑换历史";
    }
    //系统信息
    @RequestMapping("toOwn9")
    public String toOwn9(){
        return "个人中心-系统消息";
    }
    //账户设置
    @RequestMapping("toOwn10")
    public String toOwn10(){
        return "个人中心-账户设置";
    }
    //@ResponseBody
    @RequestMapping("login")
    public String userLogin(@RequestParam Map map, Model model, HttpSession session) {
        //System.out.println(map);

        //System.out.println(map.get("telephone").toString());
        Map user = userService.getByuserName(map.get("telephone").toString(),map.get("password").toString());
        if (user != null && user.size() > 0) {
              session.setAttribute("userName",user.get("USERNAME"));
            session.setAttribute("userid",user.get("USERID"));
            String userid = userService.getUserid(user.get("USERID") + "");
            if(userid==null){
                        userService.addUserid(user.get("USERID")+"");
            }
            return "forward:/user/toOwn";

        }else{
            model.addAttribute("msg","用户名密码错误");
            return "forward:toLogin";
        }

    }
    @RequestMapping("tocheck")
    public String tocheck(HttpSession session){
        Object userName = session.getAttribute("userName");
        if(userName!=null&&userName!=""){
            return "forward:/goto/rentInfo";
        }
        return "redirect:/user/toLogin";
    }
}
