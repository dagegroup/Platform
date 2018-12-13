package com.dage.controller;

import com.dage.entity.Emp;
import com.dage.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className:EmpController
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-13 15:01
 */
@Controller
@RequestMapping("emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 跳转角色页面
     * @return
     */
    @RequestMapping("tolist")
    public Object toList(){
        return "emp/emps";
    }
    /**
     * 展示所有角色信息
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public Object getEmps(@RequestBody Map map){
        Map mp = new HashMap<>();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Emp> list = empService.getList();
        PageInfo<Emp> info = new PageInfo<>(list);
        mp.put("page",info);
        return mp;
    }

    /**
     * 账户添加方法
     * @param emp
     * @return
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestBody Emp emp){
        return empService.add(emp);
    }

    /**
     * 账户更新方法
     * @param emp
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody Emp emp){
        return empService.update(emp);
    }

    /**
     * 账户状态更改方法
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("updatestate")
    public Object updateState(@RequestBody Map map){
        return empService.updateStateTo(Integer.valueOf(map.get("id")+""),map.get("state")+"");
    }

}
