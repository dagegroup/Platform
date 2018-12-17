package com.dage.controller;

import com.dage.service.AuditService;
import com.dage.util.FtpConfiguration;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @className:AuditController
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-14 16:58
 */
@Controller
@RequestMapping("audit")
public class AuditController {

    @Autowired
    private AuditService auditService;
    @Autowired
    private FtpConfiguration ftpConfiguration;

    private final ResourceLoader resourceLoader;

    public AuditController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 跳转后台审核页面
     * @return
     */
    @RequestMapping("check")
    public Object toAuditCheck(){
        return "business/auditcheck";
    }

    /**
     * 展示待审核标的详细信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("page")
    public Object AuditList(@RequestBody Map map){
       return auditService.getListAudit(map);
    }


    /**
     * 获取单个标的详细信息
     * @param bidid
     * @return
     */
    @ResponseBody
    @RequestMapping("one")
    public Object One(String bidid){
        return auditService.getListAuditDetial(bidid);
    }

    /**
     * 更改标的状态
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("updbidstate")
    public Object updState(@RequestBody Map map){
        return auditService.updateBidState(map);
    }

    /**
     * 更改认证信息的状态
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("updrealstate")
    public Object updRealState(@RequestBody Map map, HttpServletRequest request){
        HttpSession session = request.getSession();
        return auditService.updateRealState(map,session);
    }

    /**
     * 根据用户id获取用户认证信息情况
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("real")
    public Object getReal(String id){
        return auditService.getRealByUserId(id);
    }

    /**
     * 图片显示
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
