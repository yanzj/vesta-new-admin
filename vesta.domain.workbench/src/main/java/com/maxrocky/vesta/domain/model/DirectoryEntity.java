package com.maxrocky.vesta.domain.model;

/**
 * Created by zhanghj on 2016/1/22.
 */

import javax.persistence.*;
import java.util.Date;

/**
 * 工作台模块通讯录功能
 */
@Entity
@Table(name = "staff_directory")
public class DirectoryEntity {

    private String directoryId;     //通讯录Id

    private String companyName;       //公司名称

    private String section;     //部门

    private String staffName;       //员工姓名

    private String staffMobile;     //电话

    private Date createTime;          //创建时间

    private String sort;                //排序

    @Id
    @Column(name = "DIRECTORY_ID",insertable = true,nullable = false, length = 32)
    public String getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(String directoryId) {
        this.directoryId = directoryId;
    }
    @Basic
    @Column(name = "COMPANY_NAME", length = 32)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "SECTION", length = 50)
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Basic
    @Column(name = "STAFF_NAME", length = 50)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name = "STAFF_MOBILE", length = 50)
    public String getStaffMobile() {
        return staffMobile;
    }

    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }

    @Basic
    @Column(name = "CTEATETIME", length = 50)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "SORT", length = 50)
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


}
