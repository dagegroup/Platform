package com.dage.controller;

import com.dage.entity.Role;
import com.dage.service.RoleService;
import com.dage.util.FtpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className:RoleController
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-11 15:05
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 跳转角色页面
     * @return
     */
    @RequestMapping("tolist")
    public Object toList(){
        return "role/roles";
    }
    /**
     * 展示所有角色信息
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public Object getRoles(@RequestBody Map map){
        Map mp = new HashMap<>();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Role> roles = roleService.getRoles();
        PageInfo<Role> info = new PageInfo<>(roles);
        mp.put("page",info);
        return mp;
    }

    /**
     * 获取所有正常角色
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("all")
    public Object getAllRoles(){
        return roleService.getRolesByState();
    }

    /**
     * 角色添加
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestBody Role role){
        return roleService.add(role);
    }

    /**
     * 角色更新
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody Role role){
        return roleService.update(role);
    }


}
