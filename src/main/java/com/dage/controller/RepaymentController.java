package com.dage.controller;

import com.dage.service.RepaymentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:RepaymentController
 * discription: 用于处理 用户还款的业务
 * author:CZP
 * createTime:2018-12-19 09:24
 */
@Controller
@RequestMapping("/Repayment")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;
    /**
     * 根据用户id查询用户的借款记录
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<Map> getRepayment(@RequestBody Map map){
        return repaymentService.getList(map);
    }

    /**
     * 分页配置
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        System.out.println(map);
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        System.out.println(Integer.valueOf(map.get("pageNo")+"")+Integer.valueOf(map.get("pageSize")+""));
        //用PageInfo对结果进行包装
        PageInfo<Map> pageInfo=new PageInfo<Map>(repaymentService.getList(map));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 根据 还款表的主键查询 还款计划的详细信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/particulars")
    public List<Map> getParticulars(@RequestBody Map map){
        return repaymentService.getList(map);
    }

    /**
     * 用户点击 还款
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/huankuan")
    public int repayment(@RequestBody Map map){
        BigDecimal balance1=null;
        BigDecimal balance2=null;
        BigDecimal balance3=null;
        String bidid=null;
        String userid=(String) map.get("userId");
        String repayid=(String) map.get("repayid");
        //根据还款人的id查询用户余额
        Map map1=repaymentService.getBalance(map);
        if (map1.size()>0){
            //System.out.println(map1);
            balance1 =(BigDecimal)map1.get("AVAILABLEBALANCE");
            balance3 =(BigDecimal)map1.get("RETURNAMOUNT");
            //System.out.println("账户余额"+balance1);
            //System.out.println("账户待还金额"+balance3);
        }
        //根据还款计划Id查询需要还的钱
        Map map2=repaymentService.getAmount(map);
        if (map2.size()>0){
            //System.out.println(map2);
            bidid=(String)map2.get("BIDID");
            //System.out.println(bidid);
            balance2 =(BigDecimal)map2.get("BIDREPAYAMOUNT");
            //System.out.println("应还金额"+balance2);
        }
        // 账户余额减去应还金额 返回值  i
        //i==-1 时 账户余额小于应还金额 i==0时 账户余额与应还金额相等 i==1 时 账户余额大于应还金额
        int i = balance1.compareTo(balance2);
        if(i==-1){
            //System.out.println("账户余额小于应还金额");
            //当账户余额小于应还金额 提醒用户充值
            return 1;
        }else{
            // i==0 System.out.println("账户余额与应还金额相等");
            // 只要账户的余额不小于 应还金额就可以还款
            // i==1 System.out.println("账户余额大于应还金额");
            Map map3=new HashMap();
            map3.put("userId",userid);
            BigDecimal bignum3 = balance1.subtract(balance2);
            //System.out.println("两者之差是"+bignum3);
            //还款人还款之后的余额
            map3.put("money",bignum3);
            //还款人还款之后的待还金额
            BigDecimal bignum4 = balance3.subtract(balance2);
            map3.put("money1",bignum4);
            //还款人的应还金额
            map3.put("money2",balance2);
            //System.out.println(map3.get("money1"));
            //更新用户账户表
            repaymentService.updateAmount(map3);
            //添加用户流水
            repaymentService.updateAccountFlow(map3);
            //改变 还款计划状态
            repaymentService.updateRepay(map);
            //查找借款人的账户表
            List<Map> investor = repaymentService.getInvestor(bidid);
            for (Map map4 : investor){
                //投资者的userid
                String userid1=(String)map4.get("USERID");
                //System.out.println(userid1);
                map4.put("userId",userid1);
                //投资者的账户余额
                BigDecimal money3=(BigDecimal)map4.get("AVAILABLEBALANCE");
                //投资者的待收本金
                BigDecimal money4=(BigDecimal)map4.get("RECEIVEPRINCIPAL");
                //还款后投资者的账户余额     两个BigDecimal相加  balance2.add(money3).doubleValue();
                map4.put("money3",balance2.add(money3).doubleValue());
                //还款后投资者的待收本金   两个BigDecimal相减   b1.subtract(b2).doubleValue();
                map4.put("money4",money4.subtract(balance2).doubleValue());
                //更新投资者的账户
                repaymentService.updateInvestor(map4);
                // 添加投资者的账户流水 账户变动金额 为 balance2
                map4.put("money5",balance2);
                // 添加投资者的账户流水
                repaymentService.insertInvestorFlow(map4);
                return 1;
            }
        }
        return 2;
    }
}
