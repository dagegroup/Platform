package com.dage.dao;

import com.dage.entity.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className:Permission
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-07 15:24
 */
@Repository
public interface PermissionDao {
    /**
     * 根据登陆角色信息，查询对应权限，出对应的权限树
     * @param roleid
     * @return
     */
    @Select("select permissionid as id,pname as text,purl as url,pid,prefix,token,state,iconcls from tb_permission where " +
            "permissionid in (select permissionid from tb_role_permission where roleid=#{roleid})")
    List<Permission> getListByRole(Integer roleid);
}
