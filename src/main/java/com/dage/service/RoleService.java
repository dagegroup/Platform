package com.dage.service;

import com.dage.entity.Role;

import java.util.List;

/**
 * @className:RoleService
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-11 15:03
 */
public interface RoleService {

    /**
     * 获取所有角色信息
     * @return
     */
    List<Role> getRoles();

    /**
     * 角色添加
     * @param role
     * @return
     */
    int add(Role role);

    /**
     * 角色更新
     * @param role
     * @return
     */
    int update(Role role);
}
