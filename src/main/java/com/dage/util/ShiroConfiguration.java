package com.dage.util;



import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.dage.dao.PermissionDao;
import com.dage.entity.Permission;
import com.dage.service.PermissionService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @className:ShiroConfiguration
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-11-28 20:27
 */
@Configuration
public class ShiroConfiguration {

    @Autowired
    private PermissionService permissionService;
    /**
     * 创建ShiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new LinkedHashMap();
        List<Permission> list = permissionService.getList();
        for (Permission permission : list) {
            if (permission.getPrefix()!=null&&permission.getPrefix()!=""&&permission.getPrefix()!="null"){
                map.put(permission.getPrefix(),"perms["+permission.getToken()+"]");
            }
        }
        map.put("/js/**","anon");
        map.put("/easyui/**","anon");
        map.put("/foreground/**","anon");
        map.put("/*.html","anon");
        map.put("/bklogin","anon");
        //map.put("/user/login","anon");
        map.put("/**","anon");
        //anon 不拦截
        //authc 登陆访问
        //perms[]  权限访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/bklogin.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth.html");
        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     * @param empRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") EmpRealm empRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(empRealm);
        return securityManager;
    }
    /**
     * 创建Realm
     * @return
     */
    @Bean(name = "userRealm")
    public EmpRealm getRealm(){
        return new EmpRealm();
    }

    /***
     * 配置ShiroDialect 用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
