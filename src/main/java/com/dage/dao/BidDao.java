package com.dage.dao;

import org.apache.ibatis.annotations.Select;
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
     * 获取新闻列表
     * @return
     */
    @Select(value ="select bidid,userid,auditid,bidproject,bidamount,bidcurrentamount,bidrepaymentmethod,bidrate,100*round(bidcurrentamount/bidamount,4)||'%' as bidschedule,biddeadline,bidissuedate,biddeadday,bidapplydate,biddeaddate,biddesc,bidtype,bidstate from bid_info")
    List<Map> getList(Map map);
}
