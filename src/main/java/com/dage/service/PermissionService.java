package com.dage.service;

import com.dage.entity.Permission;
import com.dage.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @className:PermissionService
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-07 15:29
 */
public interface PermissionService {
    /**
     * 根据登陆角色信息，查询对应权限，出对应的权限树
     * @param
     * @return
     */
    List<Permission> getListByRole();

    /**
     * 根据登陆角色信息，查询对应权限，出对应的权限树
     * @param
     * @return
     */
    List<Permission> getCheckList(Integer roleid);

    /**
     * 权限添加
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * tb_role_permission 往角色权限关联表添加数据
     * @param role
     * @return
     */
    int saveRolePower(Role role);
}
