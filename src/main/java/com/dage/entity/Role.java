package com.dage.entity;


import java.io.Serializable;

/**
 * @className:Role
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-11 14:58
 */
public class Role  {
    //roleid,rolename,roledesc,rolestate
   // private static final long serialVersionUID=123456781;
    private Integer roleid;
    private String rolename;
    private String roledesc;
    private String rolestate;
    private String powersid;

    public String getPowersid() {
        return powersid;
    }

    public void setPowersid(String powersid) {
        this.powersid = powersid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public String getRolestate() {
        return rolestate;
    }

    public void setRolestate(String rolestate) {
        this.rolestate = rolestate;
    }
}
