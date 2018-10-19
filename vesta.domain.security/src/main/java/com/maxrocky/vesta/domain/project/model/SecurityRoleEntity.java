package com.maxrocky.vesta.domain.project.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 安全管理权限
 * Created by Jason on 2017/6/6.
 */
@Entity
@Table(name = "st_role" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"TYPE_ID","TYPE_ROLE","DATA_TYPE","DATA_ID"})})
public class SecurityRoleEntity {
    private int id;                   // 主键
    private String typeId;          // 集团、区域、项目
    /**
     * 集团：1：HSE部门
     * 区域：2：项目安全员
     * 项目：3：项目全体成员
     *       4：甲方全全员
     *       5：甲方工程师
     *       6：总包
     *       7：监理
     */
    private String typeRole;
    /**
     * 1：责任单位
     * 2：人员
     */
    private String dataType;
    private String dataId;             //人或责任单位ID
    private String status;             // 1:正常 0:删除
    private Date modifyTime;           // 修改时间
    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "TYPE_ID",length = 50,nullable = false)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    @Basic
    @Column(name = "TYPE_ROLE",length = 6)
    public String getTypeRole() {
        return typeRole;
    }

    public void setTypeRole(String typeRole) {
        this.typeRole = typeRole;
    }
    @Basic
    @Column(name = "DATA_TYPE",length = 6)
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    @Basic
    @Column(name = "DATA_ID",length = 50,nullable = false)
    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    @Basic
    @Column(name = "STATUS",length = 6)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Basic
    @Column(name = "MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
