package com.dage.controller;

import com.dage.service.UserInfoService;
import com.dage.util.IndustrySMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:UserInfoController
 * discription:
 * author:zn
 * createTime:2018-12-11 19:46
 */
@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private UserInfoService userInfoService;


    /**
     * 注册验证用户姓名
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/verifyName")
    public Object verifyName( String userName){
        Map map = new HashMap();
        List<Map> list = userInfoService.getListByName(userName);
        if (list!=null&&list.size()>0){
            map.put("msg",0);
        }else {
            map.put("msg",1);
        }
        return map;
    }


    /**
     * 注册获取随即验证码
     * @param phone
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/check")
    public Object getCheckCode( String phone, HttpSession session){
        Map map = new HashMap();
        List<Map> list = userInfoService.getListByPhone(phone);
        if (list!=null&&list.size()>0){
            map.put("msg",1);
        }else{
            //int execute = IndustrySMS.execute(phone);
            int execute=111111;
            if(execute<=0){
                map.put("msg",3);
            }else {
                map.put("msg",2);
            }
            session.setAttribute("checkcode",execute);
        }
        return map;
    }

    /**
     * 注册验证随机验证码是否输入正确
     * @param verifyCode
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/verifyCode")
    public Object verifyCode(String verifyCode,HttpSession session){
        Map map = new HashMap();
        if (verifyCode!=null&&!"".equals(verifyCode)){
             int c = Integer.valueOf(verifyCode);
            int s =Integer.valueOf(session.getAttribute("checkcode")+"");
            if (c==s){
                map.put("msg",1);
            }else{
                map.put("msg",2);
            }
        }else{
            map.put("msg",2);
        }
        return  map;
    }

    /**
     * 注册查询推荐人是否存在
     * @param refereeName
     * @return
     */
    @ResponseBody
    @RequestMapping("/findref")
    public Object findRef(String refereeName){
        Map map = new HashMap();
        List<Map> list = userInfoService.getListByRefname(refereeName);
        if(list!=null&&list.size()>0){
            map.put("msg",1);
        }else {
            map.put("msg",2);
        }
        return map;
    }

    /**
     * 注册提交验证
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/verify")
    public Object verify(@RequestParam Map map,HttpSession session){
        Map map1 = new HashMap();
        List<Map> list = userInfoService.getListByName(map.get("userName") + "");
        if (list!=null&&list.size()>0){
            map1.put("msg",2);
        }else{
            int i = userInfoService.insertInfo(map);
            if (i>0) {
                session.setAttribute("userName",map.get("userName"));
                map1.put("msg", 1);
            }else{
                map1.put("msg",3);
            }
        }
        return map1;
    }

    @ResponseBody
    @RequestMapping("/getName")
    public Object getName(HttpSession session){
        Object userName = session.getAttribute("userName");
        return userName;
    }
}
