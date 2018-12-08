package com.dage.util;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


/**
 * @className:UserRealm
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-11-28 20:28
 */
public class UserRealm extends AuthorizingRealm {

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
       // Subject subject = SecurityUtils.getSubject();
        //User user = (User)subject.getPrincipal();
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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       // UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //System.out.println(token.getUsername());
        //User user = userService.getUserByName(token.getUsername());
//        if (user!=null){
//            return new SimpleAuthenticationInfo(user,user.getPassword(),"");
//        }
        return null;
    }
}
