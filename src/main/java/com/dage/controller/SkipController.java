package com.dage.controller;

import com.dage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * className:SkipController
 * discription: 用于个人中心内各页面的跳转
 * author:CZP
 * createTime:2018-12-12 19:51
 */
@Controller
@RequestMapping("/skip")
public class SkipController {

    @Autowired
    private UserService userService;


    /**
     * 跳转到个人中心首页
     * 用户充值之后添加用户流水表和更新用户账户表
     * @return
     */
    @RequestMapping("/toPersonIndex")
    public String toPersonIndex(@RequestParam Map map,HttpSession session){
        //获取session的userName
        String userName=(String)session.getAttribute("userName");
        //根据userName 查找用户id
        Map user=userService.getUserByUserName(userName);
        String userid=(String) user.get("USERID");
        //将userid放入session 便于使用
        session.setAttribute("userid",userid);
        //String userid1= "U201812076613";
        //session.setAttribute("userid",userid1);
        if (map.size()>0){
            /*Object o = map.get("bankCardNo");
            System.out.println(o);
            Object o1 = map.get("actualMoney1");
            System.out.println(o1);*/
            userService.recharge(map);
        }
        return "个人中心首页";
    }

    /**
     * 跳转到个人中心充值
     *
     * 用户充值之后更新用户账户  添加用用户账户流水 添加系统账户流水
     * @return
     */
    @RequestMapping("/Recharge")
    @Transactional//事务
    public String toRecharge(@RequestParam Map map,HttpSession session){
        if (map.size()>0){
            //System.out.println(map.get("actualMoney1"));
            //System.out.println(map.get("bankCardNo"));
            String userid=(String)session.getAttribute("userid");
            //System.out.println(userid);
            map.put("userId",userid);
            map.put("type","充值");
            //获取用户账户信息
            List<Map> account = userService.getAccount(userid);
            //查询用户账户 accountid
            String accountid =(String)account.get(0).get("ACCOUNTID");
            map.put("accountid",accountid);
            //账户可用余额
            BigDecimal availablebalance = (BigDecimal)account.get(0).get("AVAILABLEBALANCE");
            //用户充值的金额
            String actualMoney11 =(String) map.get("actualMoney1");
            BigDecimal actualMoney1 =new BigDecimal(actualMoney11);
            //用户账户流水表内的账户余额
            BigDecimal v = availablebalance.add(actualMoney1);

            map.put("moneyflow",v);
            //改变用户账户可用余额
            userService.withdraw(map);
            //添加系统账户流水
            userService.system(map);
            //添加用户账户流水
            userService.recharge(map);
            // 加入系统的账户
            userService.addSys(map);
        }
        return "个人中心-充值";
    }
    /**
     * 跳转到个人中心充值1
     * @return
     */
    @RequestMapping("/Recharge1")
    public String toRecharge1(){
        return "个人中心-充值1";
    }

    /**
     * 跳转到个人中心还款记录
     * @return
     */
    @RequestMapping("/Conversion")
    public String toConversion(){
        return "个人中心-还款记录";
    }

    /**
     * 跳转到个人中心回款计划
     * @return
     */
    @RequestMapping("/Plan")
    public String toPlan(){
        return "个人中心-回款计划";
    }

    /**
     * 跳转到个人中心融资记录
     * @return
     */
    @RequestMapping("/Thirdparty")
    public String toThirdparty(){
        return "个人中心-融资记录";
    }

    /**
     * 跳转到个人中心开通第三方1
     * @return
     */
    @RequestMapping("/Thirdparty1")
    public String toThirdparty1(){
        return "个人中心-开通第三方1";
    }

    /**
     * 跳转到个人中心我的红包
     * @return
     */
    @RequestMapping("/Packet")
    public String toPacket(){
        return "个人中心-我的还款";
    }

    /**
     * 跳转到个人中心投资记录
     * @return
     */
    @RequestMapping("/Investment")
    public String toInvestment(){
        return "个人中心-投资记录";
    }


    /**
     * 处理用户提现
     * @return
     */
    @RequestMapping("/Withdraw2")
    public String toWithdraw2(@RequestParam Map map,HttpSession session){
        String userid = (String)session.getAttribute("userid");
        // 提现金额 money=123   手续费 procedure=2, 类型  type= 提现
        String type = (String) map.get("form:j_idt78");
        //提现金额
        String  moeny1 = (String)map.get("money");
        Double money = Double.valueOf(moeny1);
        //procedure 手续费
        String procedure1 =(String) map.get("procedure");
        Double procedure = Double.valueOf(procedure1);
        //真实到账金额
        double v = money - procedure;
        //提现的手续费
        map.put("procedure",procedure);
        //真实到账金额  系统账户里减少的金额
        map.put("money1",v);
        // 用户账户减少 和用户流水 提现的金额
        map.put("actualMoney1",money);

        map.put("userId",userid);
        map.put("type", type);

        //系统账户减去到账金额
        userService.subSys(map);
        //添加系统账户流水 按照用户提现的金额 添加
        userService.system1(map);
        //添加系统账户流水 手续费
        userService.system2(map);
        //添加用户账户流水 按照用户提现的金额 添加
        userService.recharge1(map);
        //减少用户账户的金额 按照用户提现的金额 减少
        userService.withdraw1(map);
        return "个人中心首页";
    }
    /**
     * 跳转到个人中心提现
     * @return
     */
    @RequestMapping("/Withdraw")
    public String toWithdraw(){

        return "个人中心-提现";
    }

    /**
     * 跳转到个人中心提现1
     * @return
     */
    @RequestMapping("/Withdraw1")
    public String toWithdraw1(){
        return "个人中心-提现1";
    }

    /**
     * 跳转到个人中心系统消息
     * @return
     */
    @RequestMapping("/Messages")
    public String toMessages(){
        return "个人中心-系统消息";
    }

    /**
     * 跳转到个人中心账户密码修改
     * @return
     */
    @RequestMapping("/Setting")
    public String toSetting(){
        return "个人中心-登陆密码";
    }

    /**
     * 跳转到个人中心账户支付密码修改
     * @return
     */
    @RequestMapping("/Change")
    public String toChange(){
        return "个人中心-支付密码";
    }

    /**
     * 跳转到个人中心资金记录
     * @return
     */
    @RequestMapping("/Record")
    public String toRecord(){
        return "个人中心-资金记录";
    }

    /**
     * 跳转到个人中心还款
     *
     * @return
     */
    @RequestMapping("/repayment")
    public String toRepayment(){
        return "个人中心-还款";
    }
}
