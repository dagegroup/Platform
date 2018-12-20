package com.dage.controller;

import com.dage.service.CustomerService;
import com.dage.util.FtpConfiguration;
import com.dage.util.FtpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.SystemColor.info;

/**
 * className:CustomerController
 * discription:
 * author:zn
 * createTime:2018-12-17 09:20
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

   //依赖注入ftp工具类
    @Autowired
    private FtpUtil ftpUtil;

    @Autowired
    private FtpConfiguration ftpConfiguration;

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 跳转客户信息页面
     * @return
     */
    @RequestMapping("tolist")
    public Object toList(){
        return "customer/customers";
    }

    @ResponseBody
    @RequestMapping("getlist")
    public Object getList(@RequestBody Map map){

       return customerService.getlist(map);
    }


    /**
     * 显示Ftp图片
     * @param fileName
     * @return
     */
    @RequestMapping("show")
    public ResponseEntity show(String fileName){
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("ftp://"+ftpConfiguration.getFtpUsername()+":"+ftpConfiguration.getFtpPassWord()+"@"+ftpConfiguration.getRemoteIp()+"/"+fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
