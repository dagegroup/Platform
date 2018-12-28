package com.dage.entity;

import java.io.Serializable;

/**
 * @className:Emp
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-13 14:55
 */
public class Emp implements Serializable{
//    id,name,sex,idcard,phone,roleid,state

    private static final long serialVersionUID=123456789;
    private Integer id;
    private String name;
    private String sex;
    private String idcard;
    private String phone;
    private Integer roleid;
    private String rolename;
    private String nearlogintime;
    private String state;
    private String password;

    public String getNearlogintime() {
        return nearlogintime;
    }

    public void setNearlogintime(String nearlogintime) {
        this.nearlogintime = nearlogintime;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
