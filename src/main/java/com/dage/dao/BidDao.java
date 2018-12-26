package com.dage.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * className:BidDao
 * discription:
 * author:lc
 * createTime:2018-12-10 17:29
 */
@Repository
public interface BidDao {
    /**
     * 条件查询标列表
     * @return
     */
    @Select("<script> " +
            "select bidid,userid,auditid,bidproject,bidamount,bidcurrentamount,bidrepaymentmethod,bidrate," +
            "100*round(bidcurrentamount/bidamount,4)||'%' as bidschedule,biddeadline,bidissuedate,biddeadday,bidapplydate," +
            "biddeaddate,biddesc,bidtype,bidstate from bid_info " +
            "where bidstate='待投标' " +
            "<if test=\" bidrate!=null and bidrate!=''\"> and ${bidrate}</if>" +
            "<if test=\" biddeadline!=null and biddeadline!=''\"> and ${biddeadline}</if>" +
            /*"<if test=\" bidrepaymentmethod!=null and bidrepaymentmethod!=''\"> and ${bidrepaymentmethod}</if>" +*/
            "</script>")
    List<Map> getTerm(Map map);

    /**
     * 投资总金额
     * @return
     */
    @Select("select nvl(sum(amount),0) from user_account_flow where flowtype='投资' ")
    double getGrossAssets();

    /**
     *成功招标总金额
     * @return
     */
    @Select("select nvl(sum(bidamount),0) from bid_info where bidstate='已还款'")
    double getSuccse();
}
