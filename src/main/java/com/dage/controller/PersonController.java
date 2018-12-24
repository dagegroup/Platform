package com.dage.controller;

import com.dage.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:PersonController
 * discription: 用于个人中心的各个模块ajax的请求
 * author:CZP
 * createTime:2018-12-10 09:29
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private UserService userService;

    /**
     * 根据用户编号查询用户信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("index")
    public List<Map> getPerson(@RequestParam Map map,HttpSession session){
       /* System.out.println(userId);
        System.out.println(userService.getList(userId));*/
        //System.out.println(map.get("time"));

        // 为每一个 时间选项 添加 map的键  便于 在dao层 判断查询
        Map map1 = putTime(map);
        // 根据session的userid 查询 为map 加入session里的 userid
        String userid=(String)session.getAttribute("userid");
        map1.put("userId",userid);
        //获取账户信息
        List<Map> account = userService.getAccount(userid);
        Map map2 = account.get(0);
        //获取用户信息
        List<Map> user = userService.getUser(map1);
        //二者合并
        user.add(map2);
        return user;
    }

    /**
     * 根据用户编号查询用户流水
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/flow")
    public Object getFlow(@RequestBody Map map,HttpSession session){

        //当 选择全部时 将类型重置为空 自动查询全部
        if (map.get("type")!=null&&map.get("type")!=" "){
            String type = (String) map.get("type");
            if (type.equals("全部")){
                map.put("type",null);
            }
        }
        Map map1 = putTime(map);
        // 根据session的userid 查询 为map 加入session里的 userid
        String userid=(String)session.getAttribute("userid");
        map1.put("userId",userid);
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        System.out.println(Integer.valueOf(map.get("pageNo")+"")+Integer.valueOf(map.get("pageSize")+""));
        //用PageInfo对结果进行包装
        PageInfo<Map> pageInfo=new PageInfo<Map>(userService.getFlow(map1));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 分页配置
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        System.out.println(Integer.valueOf(map.get("pageNo")+"")+Integer.valueOf(map.get("pageSize")+""));
        //用PageInfo对结果进行包装
        PageInfo<Map> pageInfo=new PageInfo<Map>(userService.getList(map));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageDate",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 根据用户ID查询用户投资记录
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/submit")
    public Object getSubmit(@RequestBody Map map,HttpSession session){
        //System.out.println(map.get("userId"));
        //System.out.println(map.get("type"));
        if (map.get("type")!=null&&map.get("type")!=""){
            if (map.get("type").equals("全部")){
                map.put("type",null);
            }
        }
         Map map1= putTime(map);
        // 根据session的userid 查询 为map 加入session里的 userid
        String userid=(String)session.getAttribute("userid");
        map1.put("userId",userid);
        /*String userid="U201812071032";
        map1.put("userId",userid);*/
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        System.out.println(Integer.valueOf(map.get("pageNo")+"")+Integer.valueOf(map.get("pageSize")+""));
        //用PageInfo对结果进行包装
        PageInfo<Map> pageInfo=new PageInfo<Map>(userService.getSubmit(map1));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 根据用户id查询用户还款计划
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/repay")
    public Object getRepay(@RequestBody Map map,HttpSession session){
        if (map.get("type")!=null&&map.get("type")!=""){
            if (map.get("type").equals("全部")){
                map.put("type",null);
            }
        }
        Map map1= putTime(map);
        //根据session的userid 查询 为map 加入session里的 userid
        String userid=(String)session.getAttribute("userid");
        map1.put("userId",userid);
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        System.out.println(Integer.valueOf(map.get("pageNo")+"")+Integer.valueOf(map.get("pageSize")+""));
        //用PageInfo对结果进行包装
        PageInfo<Map> pageInfo=new PageInfo<Map>(userService.getRepay(map1));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 根据用户id查询用户账户信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/account")
    public List<Map> getAccount(HttpSession session){
        String  userId = (String)session.getAttribute("userid");
        return userService.getAccount(userId);
    }

    /**
     * 更具用户id查询 用户正在回款的投资记录
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/MSBs")
    public Object getMSBs(@RequestBody Map map,HttpSession session){
        String userid=(String)session.getAttribute("userid");
        map.put("userId",userid);
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        System.out.println(Integer.valueOf(map.get("pageNo")+"")+Integer.valueOf(map.get("pageSize")+""));
        //用PageInfo对结果进行包装
        PageInfo<Map> pageInfo=new PageInfo<Map>(userService.getSubmit(map));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }



    /**
     * 条件查询时为map 添加查询条件
     * @param map
     * @return
     */
    public Map putTime(Map map){
        // 为每一个 时间选项 添加 map的键  便于 在dao层 判断查询
        if (map.get("time")!=null&&map.get("time")!=" "){
            String time = (String) map.get("time");
            if (time.equals("今天")){
                map.put("time1","sysdate");
            }else if (time.equals("本周")){
                map.put("time2","sysdate");
            }else if (time.equals("本月")){
                map.put("time3","sysdate");
            }else if (time.equals("本年")){
                map.put("time4","sysdate");
            }
        }
        return map;
    }
}
