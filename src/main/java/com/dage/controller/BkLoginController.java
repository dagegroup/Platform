package com.dage.controller;

import com.dage.entity.Emp;
import com.dage.service.EmpService;
import com.dage.service.PermissionService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
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
    private PermissionService permissionService;



    @RequestMapping("bklogout")
    public Object bklogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "forward:/goto/backLogin";
    }

    /**
     * 跳转未授权页面
     *
     * @return
     */
    @RequestMapping("noAuth")
    public Object noAuth() {
        return "noAuth";
    }

    /**
     * 跳转后台页面(index elementui)
     *
     * @return
     */
    @RequestMapping("index")
    public Object index() {
        return "index";
    }



    /**
     * 根据登陆角色不同，获取不同的权限
     *
     * @return
     */
    @ResponseBody //返回json格式数据
    @RequestMapping("tree")
    public Object getTree() {
        return permissionService.getListByRole();
    }


}
