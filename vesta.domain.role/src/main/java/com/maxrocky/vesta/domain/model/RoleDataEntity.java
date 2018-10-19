package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/6/16.
 * 数据权限关系实体
 */
@Entity
@Table(name = "role_data",uniqueConstraints = {@UniqueConstraint(columnNames = {"AUTHORITY_ID","AUTHORITY_TYPE","DATA_ID","DATA_TYPE","PERMISSION"})})
public class RoleDataEntity {
    private String id;                    //主键
    private String dataType;              //数据类型 1项目 2角色
    private String dataId;                //数据ID
    private String authorityType;         //组织类型 1机构 2群组 3用户
    private String authorityId;           //组织ID
    private String status;                //状态 0删除 1正常
    //交验房： 1 （数据查看）5 （验房工程师） 6（质量经理）
    //报修：100000001 （物业接单 ） 100000000（开发接单） 10（数据查看） 12（项目前台）
    //投诉： 9 （总部客观） 11 （数据查看） 8（项目前台）
    private String permission;            //项目下的权限  1数据查看  2工程接单  3派单到人  4关单权限  5验房工程师  100000001物业接单  100000000开发接单 6质量经理  7乙方工程师 8报事派单（目前被称为：客观信息员(2017-07-18 被修改为“项目前台”)）9总部客观（投诉单废弃权限（2017-07-18 Jason新加））  //角色情况下 admin后台角色
    private String projectNum;            //项目编码
    private Date modifyTime;              //修改时间

    @Basic
    @Column(name = "AUTHORITY_ID",length = 50)
    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }


    @Basic
    @Column(name = "AUTHORITY_TYPE",length = 20)
    public String getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(String authorityType) {
        this.authorityType = authorityType;
    }

    @Id
    @Column(name = "ID",length = 50,unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "DATA_TYPE",length = 20)
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "STATUS",length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "PERMISSION",length = 64)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Basic
    @Column(name = "PROJECT_NUM",length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
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
