package com.dage.controller;

import com.dage.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * @className:PermissionController
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-07 19:01
 */
@Controller
@RequestMapping("power")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 跳转前台页面
     * @return
     */
    @RequestMapping("index")
    public Object toIndex(){
        return "foreground/index";
    }

    /**
     * 跳转后台页面
     * @return
     */
    @RequestMapping("topower")
    public Object toPower(){
        return "power/power";
    }


    /**
     * 根据登陆角色不同，获取不同的权限
     * @return
     */
    @ResponseBody //返回json格式数据
    @RequestMapping("tree")
    public Object getTree(){
       return permissionService.getListByRole();
    }

    /**
     * 跳转权限添加界面
     * @return
     */
    @RequestMapping("toadd")
    public Object toadd(){
        return "power/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(@RequestParam Map map){
        return permissionService.add(map);
    }
}
