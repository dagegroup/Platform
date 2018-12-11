package com.dage.dao;

import com.dage.entity.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取所有权限列表
     * @return
     */
    @Select("select permissionid as id,pname as text,purl as url,pid,prefix,token,state,iconcls from tb_permission")
    List<Permission> getList();
    /**
     * 添加权限菜单
     * @param map
     * @return
     */
    @Insert("insert into tb_permission(permissionid,pname,purl,pid,prefix,token,state,iconcls)" +
            " values(seq_permissionid.nextval,#{text},#{url},#{pid},#{prefix},#{token},#{state},#{iconcls})")
    int add(Map map);

    /**
     * tb_role_permission 往角色权限关联表添加数据
     * @param roleid permissionid
     * @return
     */
    @Insert("insert into tb_role_permission (id,roleid,permissionid) values (seq_roleid_permission_id.nextval,#{param1},#{param2})")
    int saveRolePower(Integer roleid,Integer permissionid);

    @Delete("delete from tb_role_permission where roleid=#{roleid}")
    int delRolePower(Integer roleid);
}
