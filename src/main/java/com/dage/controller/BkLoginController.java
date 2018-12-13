package com.dage.controller;

import com.dage.entity.Emp;
import com.dage.service.EmpService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @className:BkLoginController
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-13 17:11
 */
@Controller
public class BkLoginController {

    @Autowired
    private EmpService empService;
    /**
     * 登陆操作
     * @param map
     * @return
     */
    @RequestMapping("bklogin")
    public Object bklogin(@RequestParam Map map, Model model, HttpSession session){
        System.out.println(map);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(map.get("phone")+"",map.get("password")+"");
        try {
            subject.login(token);
            Emp emp = (Emp)subject.getPrincipal();
            session.setAttribute("admin",emp);
            int i = empService.updateTime(emp.getId());
            if (i>0)
                return "redirect:/power/index";
            else
                return "forward:/bklogin.html";
        }catch (Exception e){
            model.addAttribute("msg","用户名或密码错误");
            return "forward:/bklogin.html";
        }
    }
    @RequestMapping("bklogout")
    public Object bklogout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "forward:/bklogin.html";
    }

}
