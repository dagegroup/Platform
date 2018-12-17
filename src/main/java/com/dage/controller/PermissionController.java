package com.dage.controller;

import com.dage.entity.Permission;
import com.dage.entity.Role;
import com.dage.service.PermissionService;
import oracle.net.aso.r;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 跳转(power elementui+vue)后台页面
     * @return
     */
    @RequestMapping("permission")
    public Object toPermission(){
        return "power/permission";
    }

    /**
     * 跳转(power  easyui)后台页面
     * @return
     */
    @RequestMapping("power")
    public Object toPower(){
        return "power/power";
    }


    /**
     * 获取所有得权限列表
     * @return
     */
    @ResponseBody //返回json格式数据
    @RequestMapping("tree")
    public Object getTree(){
       return permissionService.getList();
    }


    /**
     * 根据登陆角色不同，获取不同的权限
     * @return
     */
    @ResponseBody //返回json格式数据
    @RequestMapping("checktree")
    public Object getCheckTree(Integer roleid){
        return permissionService.getCheckList(roleid);
    }

    /**
     * 跳转权限添加界面
     * @return
     */
    @RequestMapping("toadd")
    public Object toadd(){
        return "power/add";
    }

    /**
     * 添加权限方法
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestParam Map map){
        int add = permissionService.add(map);
        if (add>0)
        return add;
        else
            return 0 ;
    }

    /**
     *  给角色添加权限并保存到角色权限关联表
     * @param role
     * @return
     */
    @RequestMapping("saverolepower")
    @ResponseBody
    public Object saverolepower(@RequestBody Role role){
       // System.out.println(role.getPowersid());
        return permissionService.saveRolePower(role);
    }

    /**
     * 去更新
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("toUpdate")
    public Object toUpdate(Integer id,Model model){
        Permission power = permissionService.getPowerById(id);
        model.addAttribute("power",power);
        return "power/update";
    }

    /**
     * 更新权限方法
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestParam Map map){
        int update = permissionService.update(map);
        if (update>0)
            return update;
        else
            return 0 ;
    }

    /**
     * 删除权限方法
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("del")
    public Object del(Integer id){
        int del = permissionService.del(id);
        if (del>0)
            return del;
        else
            return 0;
    }
}
