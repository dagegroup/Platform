package com.dage.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * className:ChangeDao
 * discription:
 * author:lc
 * createTime:2018-12-22 10:55
 */
@Repository
@CacheNamespace(implementation = RedisCache.class)
public interface ChangeDao {

    /**
     * 获取用户信息表的用户手机号
     * @param map
     * @return
     */
    @Select("select telephone from tb_user_info where userid=#{USERID} ")
    Map getPhone(Map map);

    /**
     * 更新用户密码
     * @param map
     * @return
     */
    @Update("update tb_user_info set password=#{PASSWORD} where userid=#{USERID} ")
    int updatePassword(Map map);

    /**
     * 更新支付密码
     * @param map
     * @return
     */
    @Update("update user_account set transactionPassword=#{TRANSACTIONPASSWORD} where userid=#{USERID} ")
    int updatePayment(Map map);

}
