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
    @Select(value = "select bidid,userid,auditid,bidproject,to_char(bidamount, '9999999999.00') as bidamount,to_char(bidcurrentamount, '9999999999.00') as bidcurrentamount," +
            "bidrepaymentmethod,bidrate,100*round(bidcurrentamount/bidamount,4)||'%' as bidschedule,biddeadline," +
            "to_char(bidissuedate,'yyyy-MM-dd') as bidissuedate,biddeadday,to_char(bidapplydate,'yyyy-MM-dd') as bidapplydate," +
            "to_char(biddeaddate,'yyyy-MM-dd') as biddeaddate,biddesc,bidtype,bidstate,bidmoney," +
            "round(to_number(biddeaddate - sysdate)) as bidendday,to_char((bidamount - bidcurrentamount), '9999999999.00') as bidendmoney" +
            " from bid_info where bidid=#{bidid}")
    List<Map> getList(Map map);

}
