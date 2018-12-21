package com.dage.controller;

import com.dage.service.RentInfoService;
import com.dage.util.FtpConfiguration;
import com.dage.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * className:rentInfoController
 * discription:
 * author:ChenMing
 * creatTime:2018-12-17 11:39
 */
@Controller
@RequestMapping("rent")
public class rentInfoController {
    @Autowired
     private RentInfoService rentInfoService;
    //依赖注入ftp工具类

    @Autowired
    private FtpConfiguration ftpConfig;
    @Autowired
    private FtpUtil ftpUtil;
    @Autowired
    private ResourceLoader resourceLoader;
    @ResponseBody
    @RequestMapping("rentInfo1")
    public Object rentInfo(@RequestBody Map map, HttpSession session){

        //System.out.println(map);
        Object userid = session.getAttribute("userid");
        Map getrent = rentInfoService.getrent(userid + "");
        if(getrent!=null&&getrent.size()>0){
            return 0;
        }
        // System.out.println(userid);
        map.put("userid",userid.toString());
        return rentInfoService.add(map);
    }
    @ResponseBody
    @RequestMapping("rentInfo2")
    public Object rentInfo1(@RequestBody Map map, HttpSession session){
        Object userid = session.getAttribute("userid");
        System.out.println(userid);
        map.put("userid",userid.toString());
        return rentInfoService.addRentDetialInfo(map);
    }
    @ResponseBody
    @RequestMapping("rentInfo3")
    public Object rentInfo2(@RequestBody Map map, HttpSession session){
        System.out.println(map);
        if(map.get("pic1")==""||map.get("pic2")==""||map.get("pic3")==""|| map.get("pic4")==""){
            return 0;
        }
        //System.out.println(map);
        Object userid = session.getAttribute("userid");
       // System.out.println(userid);
        map.put("userid",userid.toString());
        return rentInfoService.addRentInfo2(map);
    }
    /**
     * 上传方法
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/upLoadPic")
    public Object upLoadPic(@RequestParam MultipartFile file){
        String s = ftpUtil.upLoad(file);//调用上传方法
        return s;
    }
    /**
     * 下载方法
     * @param fileName
     * @param response
     */
    @RequestMapping("/downloadPic")
    public void downloadPic(String fileName, HttpServletResponse response){
        ftpUtil.downLoad(fileName,response);
    }

    @RequestMapping("show")
    public ResponseEntity show(String fileName) {
        try {
            //  ftp://192.168.1.14/98f20a5d-7304-41c7-ac5a-db07d2aaffd3.png
            return ResponseEntity.ok(resourceLoader.getResource("ftp://" + ftpConfig.getFtpUsername() + ":" + ftpConfig.getFtpPassWord() + "@" + ftpConfig.getRemoteIp() + ftpConfig.getRemotePath() + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @ResponseBody
    @RequestMapping("getInfo")
    public Object getInfo(HttpSession session){
        Object userid = session.getAttribute("userid");
        return rentInfoService.getInfo(userid+"");
    }
}
