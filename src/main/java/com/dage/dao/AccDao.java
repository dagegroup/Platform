package com.dage.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;
import java.util.Map;

/**
 * className:AccDao
 * discription:
 * author:ChenMing
 * creatTime:2018-12-22 10:19
 */
  public interface AccDao {
    @Select("<script>select a.*,to_char(a.flowdate,'yyyy-mm-dd hh24:mi:ss') as time,b.name from tb_system_account_flow a ,tb_emp b where a.orderid=b.phone  " +
            " <if test=\"USERID!=null and USERID!=''\"> and userid=#{USERID}</if>" +
            " <if test=\"FLOWTYPE!=null and FLOWTYPE!=''\"> and flowtype like '%'||#{FLOWTYPE}||'%'</if>" +
            " <if test=\"FLOWDATE!=null and FLOWDATE!=''\">  and flowdate like '%'||to_date(#{FLOWDATE},'yyyy-mm-dd')||'%'</if>" +
            "</script>")
    List<Map> getList(Map map);
    @Select("select flowtype from tb_system_account_flow group by flowtype ")
    List<Map> getFlowtype();

    /**
     * 账户流水
     * @return
     */
    @Select("<script>select to_char(flowdate,'yyyy-mm-dd') as ttime ,nvl(sum(case when t.flowtype='充值' then nvl(t.amount,0) end),0) as incoome,nvl(sum(case when t.flowtype='提现' then nvl(t.amount,0) end),0) as outcome   from tb_system_account_flow t   <if test=\" time!=null and time!=''\"> where to_char(flowdate,'yyyy-mm-dd') like '%'||substr(#{time},0,7)||'%'</if>   group by to_char(flowdate,'yyyy-mm-dd')  order by to_char(flowdate,'yyyy-mm-dd') </script>")
    List<Map> getAccountFlow(Map map);
    @Select("<script>select to_char(flowdate,'yyyy-mm-dd') as ttime ,nvl(sum(case when t.flowtype='充值' then nvl(t.amount,0) end),0)-nvl(sum(case when t.flowtype='提现' then nvl(t.amount,0) end),0) as leave    ,nvl(sum(case when t.flowtype='手续费' then nvl(t.amount,0) end),0)+nvl(sum(case when t.flowtype='借款收入' then nvl(t.amount,0) end),0) as income   from tb_system_account_flow t   <if test=\" time!=null and time!=''\"> where to_char(flowdate,'yyyy-mm-dd') like '%'||substr(#{time},0,7)||'%'</if>   group by to_char(flowdate,'yyyy-mm-dd')  order by to_char(flowdate,'yyyy-mm-dd')</script>  ")
    List<Map> getBalance(Map map);

}
