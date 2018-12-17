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
     * 标列表方法(已弃，连数据库)
     * @return
    */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(Map map){
        return bidService.getList(map);
    }

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

}
