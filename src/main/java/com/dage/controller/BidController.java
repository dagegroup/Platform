package com.dage.controller;

import com.dage.service.BidService;
import com.dage.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:BidController
 * discription:
 * author:lc
 * createTime:2018-12-10 17:26
 */
@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    private BidService bidService;

    /**
     * 条件+分页查询标列表
     * @return
     */
    @RequestMapping("/term")
    @ResponseBody
    public Object term(@RequestParam Map map){
       /* return bidService.getTerm(map);*/
        Map mp = new HashMap();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<Map> term = bidService.getTerm(map);
        PageInfo<Map> info = new PageInfo<>(term);
        mp.put("page",info);
        return mp;
    }

    /**
     * 投资总金额
     * @return
     */
    @ResponseBody
    @RequestMapping("/getGrossAssets")
    public Map getGrossAssets(){
        //获取总投资金额
        double grossAssets=bidService.getGrossAssets();
        //成功招标总金额
        double getSuccse=bidService.getSuccse();
        Map map = new HashMap();
        map.put("invest",grossAssets);//投资总额
        map.put("succse",getSuccse);//借款总额
        return map;
    }

}
