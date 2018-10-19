package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by yuanyn on 2018/2/5.
 * 同步OA分部信息
 */
@Entity
@Table(name = "subcompany_crm")
public class SubcompanyCRMEntity {

    private String subcompanyid;//分部id
    private String shortname;//简称
    private String fullname;//全称
    private String supsubcompanyid;//上级分部id
    private String website;//网站
    private String showorder;//显示顺序
    private String code;//公司编码
    private String canceled;//是否封存
    private String lastChangdate;//最后更新日期

    @Id
    @Column(name = "SUBCOMPANY_ID", unique = true, nullable = false,length = 32)
    public String getSubcompanyid() {
        return subcompanyid;
    }

    public void setSubcompanyid(String subcompanyid) {
        this.subcompanyid = subcompanyid;
    }
    @Basic
    @Column(name = "SHORT_NAME")
    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    @Basic
    @Column(name = "FULL_NAME")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    @Basic
    @Column(name = "SUPSUBCOMPANY_ID")
    public String getSupsubcompanyid() {
        return supsubcompanyid;
    }

    public void setSupsubcompanyid(String supsubcompanyid) {
        this.supsubcompanyid = supsubcompanyid;
    }
    @Basic
    @Column(name = "WEBSITE")
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    @Basic
    @Column(name = "SHOWORDER")
    public String getShoworder() {
        return showorder;
    }

    public void setShoworder(String showorder) {
        this.showorder = showorder;
    }
    @Basic
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Basic
    @Column(name = "CANCELED")
    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }
    @Basic
    @Column(name = "LAST_CHANG_DATE")
    public String getLastChangdate() {
        return lastChangdate;
    }

    public void setLastChangdate(String lastChangdate) {
        this.lastChangdate = lastChangdate;
    }
}
