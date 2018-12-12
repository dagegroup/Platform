package com.dage.dao;

import com.dage.entity.Role;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className:RoleDao
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-11 15:01
 */
@Repository
//@CacheNamespace(implementation = RedisCache.class)
public interface RoleDao {

    /**
     * 获取所有角色信息
     * @return
     */
    @Select("select roleid,rolename,roledesc,rolestate from tb_role order by roleid")
    List<Role> getRoles();

    /**
     * 角色添加
     * @param role
     * @return
     */
    @Insert("insert into tb_role(roleid,rolename,roledesc,rolestate) values(seq_roleid.nextval,#{rolename},#{roledesc},#{rolestate})")
    int add(Role role);

    /**
     * 角色更新
     * @param role
     * @return
     */
    @Update("update tb_role set rolename=#{rolename},roledesc=#{roledesc},rolestate=#{rolestate} where roleid=#{roleid}")
    int update(Role role);

    /**
     * 注销角色时，查询该角色下是否有员工
     * @param roleid
     * @return
     */
    @Select("select count(*) from employee where eroleid=#{roleid}")
    int haveEmp(Integer roleid);
}
