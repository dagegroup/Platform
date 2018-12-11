package com.dage.entity;

import java.util.Date;

/**
 * className:Bid
 * discription:
 * author:lc
 * createTime:2018-12-10 17:29
 */
public class Bid {
    private String bidid;/*标的编号*/
    private String userid;/*用户编号*/
    private String auditid;/*审核编号*/
    private String bidproject;/*招标项目*/
    private Integer bidamount;/*招标金额*/
    private Integer bidcurrentamount;/*已投标金额*/
    private String bidrepaymentmethod;/*还款方式*/
    private Integer bidrate;/*还款利率*/
    private Integer biddeadline;/*还款期限*/
    private Date bidissuedate;/*发布时间*/
    private Integer biddeadday;/*招标天数*/
    private Date bidapplydate;/*标申请时间*/
    private Date biddeaddate;/*招标结束时间*/
    private String biddesc;/*借款描述*/
    private String bidtype;/*标的类型*/
    private String bidstate;/*标的状态*/
    private String bidschedule;/*招标进度*/

    public String getBidstate() {
        return bidstate;
    }

    public void setBidstate(String bidstate) {
        this.bidstate = bidstate;
    }

    public String getBidschedule() {
        return bidschedule;
    }

    public void setBidschedule(String bidschedule) {
        this.bidschedule = bidschedule;
    }

    public String getBidid() {
        return bidid;
    }

    public void setBidid(String bidid) {
        this.bidid = bidid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid;
    }

    public String getBidproject() {
        return bidproject;
    }

    public void setBidproject(String bidproject) {
        this.bidproject = bidproject;
    }

    public Integer getBidamount() {
        return bidamount;
    }

    public void setBidamount(Integer bidamount) {
        this.bidamount = bidamount;
    }

    public Integer getBidcurrentamount() {
        return bidcurrentamount;
    }

    public void setBidcurrentamount(Integer bidcurrentamount) {
        this.bidcurrentamount = bidcurrentamount;
    }

    public String getBidrepaymentmethod() {
        return bidrepaymentmethod;
    }

    public void setBidrepaymentmethod(String bidrepaymentmethod) {
        this.bidrepaymentmethod = bidrepaymentmethod;
    }

    public Integer getBidrate() {
        return bidrate;
    }

    public void setBidrate(Integer bidrate) {
        this.bidrate = bidrate;
    }

    public Integer getBiddeadline() {
        return biddeadline;
    }

    public void setBiddeadline(Integer biddeadline) {
        this.biddeadline = biddeadline;
    }

    public Date getBidissuedate() {
        return bidissuedate;
    }

    public void setBidissuedate(Date bidissuedate) {
        this.bidissuedate = bidissuedate;
    }

    public Integer getBiddeadday() {
        return biddeadday;
    }

    public void setBiddeadday(Integer biddeadday) {
        this.biddeadday = biddeadday;
    }

    public Date getBidapplydate() {
        return bidapplydate;
    }

    public void setBidapplydate(Date bidapplydate) {
        this.bidapplydate = bidapplydate;
    }

    public Date getBiddeaddate() {
        return biddeaddate;
    }

    public void setBiddeaddate(Date biddeaddate) {
        this.biddeaddate = biddeaddate;
    }

    public String getBiddesc() {
        return biddesc;
    }

    public void setBiddesc(String biddesc) {
        this.biddesc = biddesc;
    }

    public String getBidtype() {
        return bidtype;
    }

    public void setBidtype(String bidtype) {
        this.bidtype = bidtype;
    }


}
