package com.dage.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @className:Audit
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-15 08:56
 */
@Repository
public interface AuditDao {

    /**
     * 获取待审核的投标信息
     * @param map
     * @return
     */
    @Select("<script>select b.bidid,b.userid,b.bidamount,b.bidrepaymentmethod,b.bidrate||'%' as bidrate,b.biddeadline,b.bidproject,r.realname,r.auditresult from bid_info b left join  TB_REALNAME_CERTIFICATION r on b.userid=r.userid where b.bidstate = '待审核'" +
            "<if test=\" BIDID!=null and BIDID!=''\"> and b.bidid = #{BIDID}</if>" +
            "<if test=\" USERID!=null and USERID!=''\"> and b.userid = #{USERID}</if>" +
            "<if test=\" REALNAME!=null and REALNAME!=''\"> and r.realname like  '%'||#{REALNAME}||'%'</if>" +
            "</script>")
    List<Map> getListAudit(Map map);

    /**
     * 获取标的详细信息
     * @param bidid
     * @return
     */
    @Select("select b.bidid,b.userid,b.bidamount,b.bidrepaymentmethod,b.bidrate||'%' as bidrate,b.biddeadline,b.bidproject,b.biddeadday,b.bidapplydate,u.telephone from bid_info b left join tb_user_info u on b.userid=u.userid where b.bidid=#{bidid}and b.bidstate='待审核'")
    Map getListAuditDetial(String bidid);


    /**
     * 更改标的状态
     * @param map
     * @return
     */
    @Update("update bid_info set bidstate=#{BIDSTATE},infos=#{INFOS} where bidid=#{BIDID} ")
    int updateBidState(Map map);

    /**
     * 往标审核表添加信息
     * @param map
     * @return
     */
    @Insert("insert into bid_audit (auditid,bidid,empid,audittime,auditstate) values(seq_auditid.nextval,#{BIDID},#{EMPID},sysdate," +
            "#{BIDSTATE}) ")
    int AddAudit(Map map);


    /**
     * 获取所有审核信息
     * @param map
     * @return
     */
    @Select("<script> select a.bidid,a.empid,e.name,to_char(audittime,'yyyy-mm-dd hh24:mi:ss') as audittime,a.auditstate from bid_audit a left join tb_emp e on a.empid=e.id  where 1=1 " +
            "<if test=\" BIDID!=null and BIDID!=''\"> and b.bidid = #{BIDID}</if> " +
            " <if test=\" start!=null and start!=''\"> and audittime &gt; to_date(start,'yyyy-mm-dd')</if> " +
            " <if test=\" end!=null and end!=''\"> and audittime &lt; to_date(end,'yyyy-mm-dd')</if> " +
            " order by audittime desc</script>")
    List<Map> getAuditList(Map map);
    /**
     * 更改认证信息的状态  auditorid,audittime,auditorname,auditresult,auditremarks,
     * @param map
     * @return
     */
    @Update("update tb_realname_certification set auditorid=#{AUDITORID},auditorname=#{AUDITORNAME},audittime=sysdate,auditresult=#{AUDITRESULT},auditremarks=#{AUDITREMARKS} where realnameid=#{REALNAMEID} ")
    int updateRealState(Map map);


    /**
     * 根据userid获取认证表详情
     * @param id
     * @return
     */
    @Select("select realnameid,userid,realname,sex,address,email,idnumber,\n" +
            "  academic, housed,marriage,income,idimagefountvar,idimagebackvar,to_char(applytime,'yyyy-mm-dd hh24:mi:ss') as applytime,\n" +
            "  auditorid,auditorname,audittime,auditresult,auditremarks,linkman1name,linkman1rela,linkman1sex,\n" +
            "  linkman1phone,linkman1address,linkman2name,linkman2rela,linkman2sex,linkman2phone,\n" +
            "  linkman2address,linkman3name,linkman3rela,linkman3sex,linkman3phone,linkman3address,creditimg,valueimg  from tb_realname_certification where userid = #{id} ")
    Map getRealByUserId(String id);

    /**
     * 根据用户id更改用户状态信息
     * @return
     */
    @Update("update tb_user_info set state='借款中' where userid =#{userid}")
    int updateUserState(String userid);
}
