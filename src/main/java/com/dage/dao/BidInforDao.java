package com.dage.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * className:BidInforDao
 * discription:
 * author:lc
 * createTime:2018-12-14 11:05
 */
@Repository
public interface BidInforDao {

    /**
     * 获取标列表
     *
     * @return
     */
    @Select(value = "select bidid,userid,auditid,bidproject,bidamount,bidcurrentamount,bidrepaymentmethod,bidrate," +
            "100*round(bidcurrentamount/bidamount,4)||'%' as bidschedule,biddeadline,bidissuedate,biddeadday,bidapplydate," +
            "biddeaddate,biddesc,bidtype,bidstate from bid_info")
    List<Map> getList(Map map);

}
