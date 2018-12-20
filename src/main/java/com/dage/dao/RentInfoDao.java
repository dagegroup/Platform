package com.dage.dao;

import org.apache.ibatis.annotations.Insert;

import java.util.Map;

/**
 * className:rentInfoDao
 * discription:
 * author:ChenMing
 * creatTime:2018-12-17 11:40
 */
public interface RentInfoDao {

    @Insert("insert into bid_info(bidid,userid,bidproject,bidamount,bidrepaymentmethod,bidrate,biddeadline,biddeadday,bidapplydate,biddesc) values(('BID'||to_char(sysdate,'yyyyMMdd')||lpad(trunc(dbms_random.value*10000),4,0)),#{userid},#{desc},to_number(trunc(#{money},2)),#{backtype},#{apr},to_number(#{month}),to_number(#{biddeadday}),to_date(#{bidapplydate},'yyyy-mm-dd'),#{useInfo})")
    int addRentInfo(Map map);
    @Insert("insert into tb_realname_certification(userid,realname,sex,address,email,idnumber,academic,housed,income,marriage,jobtype,comname,jointime,linkman1name,linkman1rela,linkman1phone,linkman1sex,linkman1address,linkman2name,linkman2rela,linkman2phone,linkman2sex,linkman2address,linkman3name,linkman3rela,linkman3phone,linkman3sex,linkman3address) values(#{userid},#{realName},#{gender},'联动没写',#{email},#{idcard},#{maxdeucation},#{house},#{income},#{marrige},#{jobtype},#{comname},to_date(#{jointime},'yyyy-mm-dd'),#{familyname},#{familyrelationship},#{familytel},'男','联动没写',#{jobcontactname},#{jobrelationship},#{jobtel},'男','联动没写',#{othername},#{otherrelationship},#{othertel},'男','联动没写')")
    int addRentDetialInfo(Map map);
    @Insert("update  tb_realname_certification set IDIMAGEFOUNTVAR=#{pic1},IDIMAGEBACKVAR=#{pic2},creditimg=#{pic3},valueimg=#{pic4} where userid=#{userid}")
    int addRentInfo2(Map map);
}
