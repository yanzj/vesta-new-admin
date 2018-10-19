package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/25.
 * 项目与人或责任单位关联关系实体
 */
@Entity
@Table(name = "project_staff_employ",uniqueConstraints = {@UniqueConstraint(columnNames = {"PROJECT_ID","PROJECT_ROLE","DATA_TYPE","DATA_ID","SOURCE"})})
public class ProjectStaffEmployEntity {
    private long id;                   // 主键
    private String projectId;          // 项目ID
    private String projectRole;        // 1:甲方权限 2:数据查看(项目系统管理员) 3:非正常关单(已删除) 4:项目经理权限（工程经理） 5：领导权限
    private String dataType;           // 1:责任单位 2:人员
    private String dataId;             //人或责任单位ID
    private String status;             // 1:正常 0:删除
    private Date modifyTime;           // 修改时间

    /**
     * 20170913
     * 袁亚男新增字段   来区分是否由后台管理员添加的关系
     */
    private String source;             // 默认为0  当为1时是由后台系统管理员添加的关系
    private String abbreviationName;   //机构简称

    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 50,nullable = false)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_ROLE",length = 2)
    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    @Basic
    @Column(name = "DATA_TYPE",length = 2)
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "STATUS",length = 2)
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

    @Basic
    @Column(name = "DATA_ID",length = 50)
    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    @Basic
    @Column(name = "SOURCE", columnDefinition = "varchar(32)default 0", insertable = true, updatable = true)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    @Basic
    @Column(name = "ABBREVIATION_NAME",length = 32)
    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }
}
