package com.dage.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @className:RepayDao
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-18 11:35
 */
@Repository
public interface RepayDao {

    /**
     * 添加还款计划
     * @param map
     * @return
     */
    @Insert("insert into bid_repay_info(repayid,bidid,userid,bidrepayamount,biderpaydate,biderpaydeaddate,bidrepaystate,bidrepaynumber,bidrepaytotpmts)" +
            "values(('BREP'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0)),#{BIDID},#{USERID},#{BIDREPAYAMOUNT},add_months(trunc(sysdate,'mm')+8,#{num}),add_months(trunc(sysdate,'mm')+8, #{nums}),'待还款',#{num},#{nums})")
    int addRepayPlan(Map map);


    /**
     * 根据标id 获取标信息
     * @param bidid
     * @return
     */
    @Select("select * from bid_info where bidid = #{bidid}")
    Map getBidInfoByBid(String bidid);


    /**
     * 获取待还款的投标信息
     * @param map
     * @return
     */
    @Select("<script>select b.bidid,b.userid,b.bidamount,b.bidrepaymentmethod,b.bidrate||'%' as bidrate,b.biddeadline,b.bidproject,r.realname,b.bidcurrentamount from bid_info b left join  TB_REALNAME_CERTIFICATION r on b.userid=r.userid where b.bidstate = '待还款'" +
            "<if test=\" BIDID!=null and BIDID!=''\"> and b.bidid = #{BIDID}</if>" +
            "<if test=\" USERID!=null and USERID!=''\"> and b.userid = #{USERID}</if>" +
            "<if test=\" REALNAME!=null and REALNAME!=''\"> and r.realname like  '%'||#{REALNAME}||'%'</if>" +
            "</script>")
    List<Map> getListBidByRepay(Map map);

    /**
     * 根据标id 查询 repay表中的标分期信息
     * @param bidid
     * @return
     *
     */
    @Select("select a.*,b.telephone from (select r.bidid,r.userid,r.bidrepayamount,to_char(r.biderpaydate,'yyyy-mm-dd') as biderpaydate,to_char(r.biderpaydeaddate,'yyyy-mm-dd') as biderpaydeaddate," +
            "r.bidrepaynumber,r.bidrepaystate,t.realname from bid_repay_info r left join tb_realname_certification t on r.userid=t.userid where bidid = #{bidid}) a left join tb_user_info b on a.userid=b.userid order by a.bidrepaynumber")
    List<Map> getListByBid(String bidid);
}
