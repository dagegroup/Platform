package com.dage.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:FailureDao
 * discription:
 * author:zn
 * createTime:2018-12-18 11:29
 */
public interface FailureDao {

    /**
     * 获取流标详情
     * @param map
     * @return
     */
    @Select("<script>select distinct a.*,s.bidstate as SUBMITSTATE from(select b.bidid,b.userid,b.bidamount,b.bidrepaymentmethod,b.bidrate||'%' as bidrate,b.biddeadline,b.bidproject,r.realname,b.bidcurrentamount from bid_info b left join  TB_REALNAME_CERTIFICATION r on b.userid=r.userid where b.bidstate = '流标' " +
            "<if test=\" BIDID!=null and BIDID!=''\"> and b.bidid = #{BIDID}</if>" +
            "<if test=\" USERID!=null and USERID!=''\"> and b.userid = #{USERID}</if>" +
            "<if test=\" REALNAME!=null and REALNAME!=''\"> and r.realname like  '%'||#{REALNAME}||'%'</if>" +
            ") a left join bid_submit s on a.bidid=s.bidid </script>")
    List<Map> getlist(Map map);

    /**
     * 根据标号Id获取 bid_sumbit 表中 对应的流标信息
     * @param bidid
     * @return
     */
    @Select("select s.userid,s.bidid,s.bidamount,to_char(s.biddate,'yyyy-mm-dd hh24:mi:ss') as biddate,s.bidstate,r.realname from bid_submit s left join tb_realname_certification r on s.userid=r.userid where s.bidid=#{bidid}")
    List<Map> getMapByBidid(String bidid);


    /**
     * 通过userid查询user_account表信息
     * @param userid
     * @return
     */
    @Select("select * from user_account where userid=#{userid}")
    Map getUserAccountByUserid(String userid);

    /**
     * 通过借款标号bidid查找bid_info表信息
     * @param bidid
     * @return
     */
    @Select("select userid from bid_info where bidid=#{BIDID}")
    Map getBidInfoByBidid(String bidid);

    /**
     * 流标时汇款时更新投资列表状态
     * @param bidid
     * @return
     */
    @Update("update bid_submit set bidstate='已回款' where bidid=#{bidid}")
    int updateBidSubmit(String bidid);

    /**
     * 流标时回款更新用户账户表可用余额
     * @param map
     * @return
     */
    @Update("update user_account set availablebalance=#{AVAILABLEBALANCE} where userid=#{USERID}")
    int updateUserAccount(Map map);

    /**
     * 流标时汇款更新用户表状态为正常
     * @param userid
     * @return
     */
    @Update("update tb_user_info set state='正常' where userid=#{userid}")
    int updateUserInfo(String userid);

    /**
     * 流标时退钱生成流水账单
     * @param map
     * @return
     */
    @Insert("insert into user_account_flow values(( select 'UFLOW'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*1000),5,0)from dual),#{USERID},#{ACCOUNTID},#{AMOUNT},#{AVAILABLEBALANCE},sysdate,#{FLOWTYPE})")
    int insertUserFlow(Map map);

}
