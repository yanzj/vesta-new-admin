package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/4/22.
 * 旁站实体
 */
@Entity
@Table(name = "side_stand")
public class SideStandEntity {
    private String standId;         //旁站ID 主键
    private String CaseName;        //工序名称
    private String standPlace;      //旁站位置
    private String projectId;       //项目ID
    private Date createOn;          //创建时间
    private Date standTime;         //旁站时间
    private String standBy;         //旁站人ID
    private String standDesc;       //旁站要点
    private String createBy;        //创建人
    private String status;          //状态 1正常 2删除 3关闭

    @Basic
    @Column(name = "CASE_NAME",length = 50)
    public String getCaseName() {
        return CaseName;
    }

    public void setCaseName(String caseName) {
        CaseName = caseName;
    }

    @Basic
    @Column(name = "CREATE_BY",length = 30)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "STAND_BY",length = 30)
    public String getStandBy() {
        return standBy;
    }

    public void setStandBy(String standBy) {
        this.standBy = standBy;
    }

    @Basic
    @Column(name = "STAND_DESC",length = 500)
    public String getStandDesc() {
        return standDesc;
    }

    public void setStandDesc(String standDesc) {
        this.standDesc = standDesc;
    }

    @Id
    @Column(name = "STAND_ID",length = 50,unique = true,nullable = false)
    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }

    @Basic
    @Column(name = "STAND_PLACE",length = 80)
    public String getStandPlace() {
        return standPlace;
    }

    public void setStandPlace(String standPlace) {
        this.standPlace = standPlace;
    }

    @Basic
    @Column(name = "STAND_TIME")
    public Date getStandTime() {
        return standTime;
    }

    public void setStandTime(Date standTime) {
        this.standTime = standTime;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 80)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "STATUS",length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
