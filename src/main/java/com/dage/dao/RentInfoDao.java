package com.dage.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * className:rentInfoDao
 * discription:
 * author:ChenMing
 * creatTime:2018-12-17 11:40
 */
public interface RentInfoDao {
    /**
     * 查询用户是否真正处于借款状态
     * @param userid
     * @return
     */
    @Select("select * from tb_user_info where state='正常' and userid=#{userid}")
    Map getrent(String userid);


    /**
     *添加借款信息
     * @param map
     * @return
     */
    @Insert("insert into bid_info(bidid,userid,bidproject,bidamount,bidrepaymentmethod,bidrate,biddeadline,biddeadday,bidapplydate,biddesc,bidstate) values(('BID'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0)),#{userid},#{desc},to_number(trunc(#{money},2)),#{backtype},#{apr},to_number(#{month}),to_number(#{biddeadday}),to_date(#{bidapplydate},'yyyy-mm-dd'),#{useInfo},'待审核')")
    int addRentInfo(Map map);

    /**
     *更新详细信息
     * @param map
     * @return
     */
    @Insert("update   tb_realname_certification set auditresult='未审核', realname=#{REALNAME},sex=#{GENDER},address=#{addrA},email=#{EMAIL},idnumber=#{IDCARD},academic=#{MAXEDUCATION},housed=#{HOUSE},income=#{INCOME},marriage=#{MARRIAGE},jobtype=#{JOBTYPE},comname=#{COMNAME},jointime=to_date(#{JOINTIME},'yyyy-mm-dd hh24:mi:ss'),linkman1name=#{FAMILYNAME},linkman1rela=#{FAMILYRELATIONSHIP},linkman1phone=#{FAMILYTEL},linkman1sex='男',linkman1address=#{addrB},linkman2name=#{JOBCONTACTNAME},linkman2rela=#{JOBRELATIONSHIP},linkman2phone=#{JOBTEL},linkman2sex='男',linkman2address=#{addrC},linkman3name=#{OTHERNAME},linkman3rela=#{OTHERRELATIONSHIP},linkman3phone=#{OTHERTEL},linkman3sex='男',linkman3address=#{addrD},applytime=sysdate  where userid=#{userid}")
    int addRentDetialInfo(Map map);

    /**
     *添加认证图片
     * @param map
     * @return
     */
    @Insert("update  tb_realname_certification set IDIMAGEFOUNTVAR=#{pic1},IDIMAGEBACKVAR=#{pic2},creditimg=#{pic3},valueimg=#{pic4} where userid=#{userid}")
    int addRentInfo2(Map map);

    /**
     *查询已经借款用户的详细信息 直接加载到用户详情页面
     * @param userid
     * @return
     */
    @Select("select realname,sex GENDER,address,email,idnumber IDCARD,academic MAXEDUCATION,housed HOUSE,income ,marriage,jobtype,comname,to_char(jointime,'yyyy-mm-dd hh24:mi:ss'),linkman1name FAMILYNAME,linkman1rela FAMILYRELATIONSHIP,linkman1phone FAMILYTEL,linkman1sex ,linkman1address,linkman2name JOBCONTACTNAME ,linkman2rela JOBRELATIONSHIP,linkman2phone JOBTEL,linkman2sex,linkman2address ADDRESSS,linkman3name OTHERNAME,linkman3rela OTHERRELATIONSHIP,linkman3phone OTHERTEL,linkman3sex,linkman3address from tb_realname_certification where userid=#{userid}")
    Map getInfo(String userid);
}
