package com.dage.service;

import com.dage.dao.RoleDao;
import com.dage.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className:RoleServiceImpl
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-11 15:04
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Override
    public int update(Role role) {
        String rolestate = role.getRolestate();
        if (rolestate.equals("注销")) {
            int i = roleDao.haveEmp(role.getRoleid());
            if (i>0) {
                return -1;
            }
        }
        return roleDao.update(role);
    }

    @Override
    public int add(Role role) {
        return roleDao.add(role);
    }
}
