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
     * 获取标列表
     *
     * @return
     */
    @Select(value = "select bidid,userid,auditid,bidproject,bidamount,bidcurrentamount,bidrepaymentmethod,bidrate," +
            "100*round(bidcurrentamount/bidamount,4)||'%' as bidschedule,biddeadline,bidissuedate,biddeadday,bidapplydate," +
            "biddeaddate,biddesc,bidtype,bidstate from bid_info where bidstate='待投标' ")
    List<Map> getList(Map map);

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
     * 雇员分页查询(思想历程，最终失败了)
     * @param map
     * @return
     */
    @Select("<script>" +
            "select bidid,userid,auditid,bidproject,bidamount,bidcurrentamount,bidrepaymentmethod,bidrate," +
            "100*round(bidcurrentamount/bidamount,4)||'%' as bidschedule,biddeadline,bidissuedate,biddeadday,bidapplydate," +
            "biddeaddate,biddesc,bidtype,bidstate from " +
            "(" +
            "select rownum rn,bidid,userid,auditid,bidproject,bidamount,bidcurrentamount,bidrepaymentmethod,bidrate," +
            "100*round(bidcurrentamount/bidamount,4)||'%' as bidschedule,biddeadline,bidissuedate,biddeadday,bidapplydate," +
            "biddeaddate,biddesc,bidtype,bidstate from bid_info " +
            "where rownum&lt;${end} " +
            "<if test=\" bidrate!=null and bidrate!=''\"> and ${bidrate}</if>" +
            "<if test=\" biddeadline!=null and biddeadline!=''\"> and ${biddeadline}</if>" +
            "<if test=\" bidrepaymentmethod!=null and bidrepaymentmethod!=''\"> and ${bidrepaymentmethod}</if>" +
            ") a " +
            " where  a.rn &gt; ${start} </script>")
    List<Map> getPage(Map map);

}
