package com.dage.controller;

import com.dage.service.BidService;
import com.dage.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
     * 标列表方法
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(Map map){
        return bidService.getList(map);
    }

    /**
     * 条件查询标列表
     * @return
     */
    @RequestMapping("/term")
    @ResponseBody
    public Object term(@RequestParam Map map){
        return bidService.getTerm(map);
    }

    /**
     * 雇员列表方法
     * @param pageNo
     * @param model
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Object listPage(@RequestParam Map map, Integer pageNo,HttpServletRequest request){
        int pageSize=2;
        Map map1=new HashMap();
        int tPageNo = pageNo==null?1:pageNo;
        map1.put("pageNo",pageNo);
        map1.put("pageSize",pageSize);
        map1.put("bidList",bidService.getPage(map));

        String pageString = new PageUtil(tPageNo, pageSize, bidService.getPageCount(map),request).getPageString();
        map1.put("pageString", pageString);

        return map1;
    }

}
