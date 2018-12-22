package com.dage.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:FullDao
 * discription:
 * author:zn
 * createTime:2018-12-22 10:51
 */
public interface FullDao {

    /**
     * 获取已还款的投标信息
     * @param map
     * @return
     */
    @Select("<script>select b.bidid,b.userid,b.bidamount,b.bidrepaymentmethod,b.bidrate||'%' as bidrate,b.biddeadline,b.bidproject,r.realname,b.bidcurrentamount,b.bidstate from bid_info b left join  TB_REALNAME_CERTIFICATION r on b.userid=r.userid where b.bidstate = '已还款'" +
            "<if test=\" BIDID!=null and BIDID!=''\"> and b.bidid = #{BIDID}</if>" +
            "<if test=\" USERID!=null and USERID!=''\"> and b.userid = #{USERID}</if>" +
            "<if test=\" REALNAME!=null and REALNAME!=''\"> and r.realname like  '%'||#{REALNAME}||'%'</if>" +
            "</script>")
    List<Map> getListBidByLoan(Map map);
}
