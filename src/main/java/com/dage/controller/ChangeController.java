package com.dage.controller;

import com.dage.service.ChangeService;
import com.dage.util.AESUtil;
import com.dage.util.IndustrySMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * className:ChangeController
 * discription:修改密码 涉及到手机验证
 * author:lc
 * createTime:2018-12-22 10:49
 */
@Controller
@RequestMapping("/changePassword")
public class ChangeController {

    @Autowired
    private ChangeService changeService;


    /**
     * 获取userid，自动输入手机号
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/phone")
    public Map getPhone( HttpSession session){
        Object userid = session.getAttribute("userid");
        Map map = new HashMap();
        map.put("USERID",userid);
        //根据userid查询用户手机号
        //System.out.println(changeService.getPhone(map)+"00000000000000000000000000");
        return changeService.getPhone(map);
    }

    /**
     * 发送手机验证码
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/verifyCode")
    public Object verifyCode(@RequestBody Map map,HttpSession session){
        String phone = (String)map.get("phone");
        //发送手机验证码
        int execute = IndustrySMS.execute(phone);
        //将验证码放入session中
        session.setAttribute("verifyCode",execute);
        // 输出验证码
        //System.out.println(execute+"-----手机------");
        //前台判定验证码是否发送成功
        return 1;
    }

    /**
     * 比较验证码是否输入正确
     * @param session
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/canCode")
    public Object canCode(HttpSession session,@RequestBody Map map){
        //从session中获取给手机发送的验证码
        int verifyCode1 = Integer.valueOf(session.getAttribute("verifyCode")+"");
        //获取前台输入的验证码
        String o = (String)map.get("inputCode" + "");
        if (o!=null&&o!=""){
            //将验证码转化为int类型进行比较发送的验证码和输入的验证码是否相同
            int code = Integer.valueOf(o);
            if (verifyCode1==code){
                return  1;
            }else {
                return -1;
            }
        }else {
            return -1;
        }


    }
    /**
     * 修改密码
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/verify")
    public Object updatePassword(@RequestBody Map map,HttpSession session){
        //从session中获取userid
        Object userid = session.getAttribute("userid");
        //将userid放入map传入后台（根据userid修改密码）
        map.put("USERID",userid);
        if (userid!=null&&userid!=""){
            //获取map中的密码
            String password = (String)map.get("PASSWORD");
            //给密码加密
            String encrypt = AESUtil.getInstance().encrypt(password);
            map.put("PASSWORD",encrypt);
            //将map传到后台调用修改密码方法
            changeService.updatePassword(map);
            return 1;
        }
        return -1;
    }

    /**
     * 修改支付密码
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/payment")
    public Object updatePayment(@RequestBody Map map,HttpSession session){
        //从session中获取userid
        Object userid = session.getAttribute("userid");
        //将userid放入map传入后台（根据userid修改密码）
        map.put("USERID",userid);
        if (userid!=null&&userid!=""){
            //获取map中的密码
            String payPassword = (String)map.get("TRANSACTIONPASSWORD");
            //给密码加密
            String encrypt = AESUtil.getInstance().encrypt(payPassword);
            map.put("TRANSACTIONPASSWORD",encrypt);
            //将map传到后台调用修改支付密码方法
            changeService.updatePayment(map);
            return 1;
        }
        return -1;
    }

}
