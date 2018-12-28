package com.dage.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @className:SubmitDao
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-17 14:00
 */
@Repository
public interface SubmitDao {

    /**
     * 获取待审核的投标信息
     * @param map
     * @return
     */
    @Select("<script>select b.bidid,b.userid,b.bidamount,b.bidrepaymentmethod,b.bidrate||'%' as bidrate,b.biddeadline,b.bidproject,r.realname,b.bidcurrentamount from bid_info b left join  TB_REALNAME_CERTIFICATION r on b.userid=r.userid where b.bidstate = '待投标' and b.bidcurrentamount &lt; b.bidamount" +
            "<if test=\" BIDID!=null and BIDID!=''\"> and b.bidid = #{BIDID}</if>" +
            "<if test=\" USERID!=null and USERID!=''\"> and b.userid = #{USERID}</if>" +
            "<if test=\" REALNAME!=null and REALNAME!=''\"> and r.realname like  '%'||#{REALNAME}||'%'</if>" +
            "</script>")
    List<Map> getListBid(Map map);

    /**
     * 获取满标待审核的投标信息
     * @param map
     * @return
     */
    @Select("<script>select b.bidid,b.userid,b.bidamount,b.bidrepaymentmethod,b.bidrate||'%' as bidrate,b.biddeadline,b.bidproject,r.realname,b.bidcurrentamount from bid_info b left join  TB_REALNAME_CERTIFICATION r on b.userid=r.userid where b.bidstate = '满标待审核'" +
            "<if test=\" BIDID!=null and BIDID!=''\"> and b.bidid = #{BIDID}</if>" +
            "<if test=\" USERID!=null and USERID!=''\"> and b.userid = #{USERID}</if>" +
            "<if test=\" REALNAME!=null and REALNAME!=''\"> and r.realname like  '%'||#{REALNAME}||'%'</if>" +
            "</script>")
    List<Map> getListBidByFull(Map map);

    /**
     * 获取待放款的投标信息
     * @param map
     * @return
     */
    @Select("<script>select b.bidid,b.userid,b.bidamount,b.bidrepaymentmethod,b.bidrate||'%' as bidrate,b.biddeadline,b.bidproject,r.realname,b.bidcurrentamount,b.bidstate from bid_info b left join  TB_REALNAME_CERTIFICATION r on b.userid=r.userid where b.bidstate = '待放款'" +
            "<if test=\" BIDID!=null and BIDID!=''\"> and b.bidid = #{BIDID}</if>" +
            "<if test=\" USERID!=null and USERID!=''\"> and b.userid = #{USERID}</if>" +
            "<if test=\" REALNAME!=null and REALNAME!=''\"> and r.realname like  '%'||#{REALNAME}||'%'</if>" +
            "</script>")
    List<Map> getListBidByLoan(Map map);
    /**
     * 根据标号Id获取 bid_sumbit 表中 对应的投标信息
     * @param bidid
     * @return
     */
    @Select("select s.userid,s.bidid,s.bidamount,to_char(s.biddate,'yyyy-mm-dd hh24:mi:ss') as biddate,nvl(r.realname,'***') as realname from bid_submit s left join tb_realname_certification r on s.userid=r.userid where s.bidid=#{bidid}")
    List<Map> getSubmitByBidid(String bidid);


    /**
     * 更改标的状态
     * @param map
     * @return
     */
    @Update("update bid_info set bidstate=#{BIDSTATE} where bidid=#{BIDID} ")
    int updateBidState(Map map);
}
