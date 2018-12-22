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
        return userService.getUser(map1);
    }

    /**
     * 根据用户编号查询用户流水
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/flow")
    public List<Map> getFlow(@RequestParam Map map,HttpSession session){
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
        return userService.getFlow(map1);
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
    public List<Map> getSubmit(@RequestParam Map map,HttpSession session){
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
        return userService.getSubmit(map1);
    }

    /**
     * 根据用户id查询用户还款计划
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/repay")
    public List<Map> getRepay(@RequestParam Map map,HttpSession session){
        if (map.get("type")!=null&&map.get("type")!=""){
            if (map.get("type").equals("全部")){
                map.put("type",null);
            }
        }
        Map map1= putTime(map);
        //根据session的userid 查询 为map 加入session里的 userid
        String userid=(String)session.getAttribute("userid");
        map1.put("userId",userid);
        return userService.getRepay(map1);
    }

    /**
     * 根据用户id查询用户账户信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/account")
    public List<Map> getAccount(String userId){
        //System.out.println(userId);
        return userService.getAccount(userId);
    }

    /**
     * 更具用户id查询 用户正在回款的投资记录
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/MSBs")
    public List<Map> getMSBs(@RequestParam Map map,HttpSession session){
        String userid=(String)session.getAttribute("userid");
        map.put("userId","U201812076613");

    //[{"BIDPROJECT":"我啊啊啊啊啊啊","BIDAPPLYDATE":"2018-12-19T16:00:00.000+0000",
        // "BIDREPAYMENTMETHOD":"等额本息","BIDDESC":"结婚","BIDDEADDAY":2,"USERID":"log201812140592",
        // "BIDAMOUNT":666666,"BIDDEADLINE":12,"BIDID":"BID201812203953","BIDRATE":6}
        return userService.getMSBs(map);
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
