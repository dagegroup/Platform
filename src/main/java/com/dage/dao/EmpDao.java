package com.dage.dao;

import com.dage.entity.Emp;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className:EmpDao
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-12 18:35
 */
@Repository
@CacheNamespace(implementation = RedisCache.class)
public interface EmpDao {

    /**
     * 显示所有可以登陆平台的账户
     * @return
     */
    @Select("select id,name,sex,idcard,phone,e.roleid,state,password,rolename,to_char(nearlogintime,'yyyy-mm-dd hh24:mi:ss') as nearlogintime from tb_emp e left join tb_role r on e.roleid=r.roleid  order by id")
    List<Emp> getList();

    /**
     * 账户登陆时的方法
     * @param phone
     * @return
     */
    @Select("select id,name,sex,idcard,phone,e.roleid,state,password,rolename from tb_emp e left join tb_role r on e.roleid=r.roleid where phone=#{phone}")
    Emp getEmpByPhone(String phone);

    /**
     * 添加平台登陆账户
     * @param emp
     * @return
     */
    @Insert("insert into tb_emp (id,name,sex,idcard,phone,roleid,state,password) values(seq_empid.nextval,#{name},#{sex},#{idcard},#{phone},#{roleid},#{state},#{password})")
    int add(Emp emp);

    /**
     * 账户登陆更新表内登陆时间
     * @param id
     * @return
     */
    @Update("update tb_emp set nearlogintime=sysdate where id=#{id}")
    int updateTime(Integer id);


    /**
     * 更新账户使用状态
     * @param id
     * @param state
     * @return
     */
    @Update("update tb_emp set state=#{param2} where id=#{param1}")
    int updateStateTo(Integer id,String state);

    /**
     * 更改账户信息
     * @param emp
     * @return
     */
    @Update("update tb_emp set name=#{name},sex=#{sex},idcard=#{idcard},phone=#{phone},roleid=#{roleid},state=#{state},password=#{password} where id=#{id}")
    int update(Emp emp);
}
