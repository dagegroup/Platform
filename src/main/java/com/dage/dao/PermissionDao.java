package com.dage.dao;

import com.dage.entity.Permission;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;
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
@CacheNamespace(implementation = RedisCache.class)
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
            " values(seq_permissionid.nextval,#{pname},#{url},#{pid},#{prefix},#{token},#{state},#{iconcls})")
    int add(Map map);

    /**
     * tb_role_permission 往角色权限关联表添加数据
     * @param roleid permissionid
     * @return
     */
    @Insert("insert into tb_role_permission (id,roleid,permissionid) values (seq_roleid_permission_id.nextval,#{param1},#{param2})")
    int saveRolePower(Integer roleid,Integer permissionid);

    /**
     * 给角色添加权限时的删除方法
     * @param roleid
     * @return
     */
    @Delete("delete from tb_role_permission where roleid=#{roleid}")
    int delRolePower(Integer roleid);

    /**
     * 权限树去更新操作 根据id获取权限的 信息
     * @param id
     * @return
     */
    @Select("select permissionid as id,pname as text,purl as url,pid,prefix,token,state,iconcls from tb_permission where permissionid=#{id}")
    Permission getPowerById(Integer id);

    /**
     * 权限树更新操作
     * @param map
     * @return
     */
    @Update("update tb_permission set pname=#{pname},purl=#{url},pid=#{pid},prefix=#{prefix},token=#{token},state=#{state},iconcls=#{iconcls} where permissionid=#{id}")
    int update(Map map);

    /**
     * 权限树删除操作
     * @param id
     * @return
     */
    @Delete("delete from tb_permission where permissionid = #{id}")
    int del(Integer id);

    /**
     * 删除权限之前 先删除角色对应的相应权限
     * @param powerid
     * @return
     */
    @Delete("delete from tb_role_permission where permissionid=#{powerid}")
    int delPowerRole(Integer powerid);
}
