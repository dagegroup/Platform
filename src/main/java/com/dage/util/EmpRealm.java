package com.dage.util;


import com.dage.entity.Emp;
import com.dage.entity.Permission;
import com.dage.service.EmpService;
import com.dage.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @className:UserRealm
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-11-28 20:28
 */
public class EmpRealm extends AuthorizingRealm {

    @Autowired
    private EmpService empService;
    @Autowired
    private PermissionService permissionService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("news");
        Subject subject = SecurityUtils.getSubject();
        Emp emp = (Emp)subject.getPrincipal();
        List<Permission> listByRoleid = permissionService.getListByRoleid();
        for (Permission permission : listByRoleid) {
            if (permission.getPrefix()!=null&&permission.getPrefix()!=""&&permission.getPrefix()!="null") {
                info.addStringPermission(permission.getToken());
            }
        }
        //info.addStringPermission(user.getPerms());
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
       UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //System.out.println(token.getUsername());
        Emp emp = empService.getEmpByPhone(token.getUsername());
        if (emp!=null){
            return new SimpleAuthenticationInfo(emp,emp.getPassword(),"");
        }
        return null;
    }

}
