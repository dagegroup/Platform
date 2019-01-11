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
public class RentInfoController {
    @Autowired
     private RentInfoService rentInfoService;
    //依赖注入ftp工具类

    @Autowired
    private FtpConfiguration ftpConfig;
    @Autowired
    private FtpUtil ftpUtil;
    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 借款信息添加并查询是否正在借款中
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("rentInfo1")
    public Object rentInfo(@RequestBody Map map, HttpSession session){

        //System.out.println(map);
        Object userid = session.getAttribute("userid");
        Map getrent = rentInfoService.getrent(userid + "");
           map.put("userid",userid.toString());
        if(getrent!=null&&getrent.size()>0){
            session.setAttribute("rentInfo1",map);
//            rentInfoService.add(map);
            return 1;
        }else{
            return 0;
        }

    }

    /**
     * 填写详细信息
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("rentInfo2")
    public Object rentInfo1(@RequestBody Map map, HttpSession session){
        //System.out.println(map);
        session.setAttribute("sheng1",map.get("sheng1"));
        session.setAttribute("shi1",map.get("shi1"));
        session.setAttribute("qu1",map.get("qu1"));
        session.setAttribute("sheng2",map.get("sheng2"));
        session.setAttribute("shi2",map.get("shi2"));
        session.setAttribute("qu2",map.get("qu2"));
        session.setAttribute("sheng3",map.get("sheng3"));
        session.setAttribute("shi3",map.get("shi3"));
        session.setAttribute("qu3",map.get("qu3"));
        session.setAttribute("sheng4",map.get("sheng4"));
        session.setAttribute("shi4",map.get("shi4"));
        session.setAttribute("qu4",map.get("qu4"));
        session.setAttribute("address",map.get("address"));
        String addr = map.get("addrA").toString().concat(map.get("address") + "");
        Object userid = session.getAttribute("userid");
        map.put("addrA",addr);
        map.put("userid",userid.toString());
        session.setAttribute("rentInfo2",map);
//        rentInfoService.addRentDetialInfo(map);
        return 1;
    }

    /**
     * 添加各种照片证明
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("rentInfo3")
    public Object rentInfo2(@RequestBody Map map, HttpSession session){
        //System.out.println(map);
        if(map.get("pic1")==""||map.get("pic2")==""||map.get("pic3")==""|| map.get("pic4")==""){
            return 0;
        }
        Object userid = session.getAttribute("userid");
        rentInfoService.updateState(userid+"");
        map.put("userid",userid.toString());
        Map rentInfo2 = (Map)session.getAttribute("rentInfo2");
        Map rentInfo1 = (Map)session.getAttribute("rentInfo1");
        int add = rentInfoService.add(rentInfo1);
        int i = rentInfoService.addRentDetialInfo(rentInfo2);
        int i1 = rentInfoService.addRentInfo2(map);
        if (add>0&&i>0&&i1>0)
        return 1;
        else return 0;
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

    /**
     * 获取详细信息自动加载到详细信息页面
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("getInfo")
    public Object getInfo(HttpSession session){
        Object userid = session.getAttribute("userid");
        Map map=rentInfoService.getInfo(userid+"");
        map.put("sheng1",session.getAttribute("sheng1"));
        map.put("shi1",session.getAttribute("shi1"));
        map.put("qu1", session.getAttribute("qu1"));
        map.put("sheng2",session.getAttribute("sheng2"));
        map.put("shi2",session.getAttribute("shi2"));
        map.put("qu2",session.getAttribute("qu2"));
        map.put("sheng3",session.getAttribute("sheng3"));
        map.put("shi3",session.getAttribute("shi3"));
        map.put("qu3",session.getAttribute("qu3"));
        map.put("sheng4",session.getAttribute("sheng4"));
        map.put("shi4",session.getAttribute("shi4"));
        map.put("qu4",session.getAttribute("qu4"))  ;
        map.put("address",session.getAttribute("address"))  ;
        return map;
    }
    @ResponseBody
    @RequestMapping("getInfo1")
    public Object getBackInfo1(HttpSession session){
      return    session.getAttribute("rentInfo1");
    }
    @ResponseBody
    @RequestMapping("getInfo2")
    public Object getBackInfo2(HttpSession session){
    Map map= (Map) session.getAttribute("rentInfo2");

        return    map;
    }
}
