package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/17.
 * 工程楼栋实体
 */

@Entity
@Table(name = "project_building")
public class ProjectBuildingEntity {
    private String id;            //楼栋ID
    private String projectId;     //项目ID
    private String name;          //名称
    private String state;         //状态
    private Date createOn;        //创建时间
    private Date modifyOn;        //修改时间
    private long autoNum;         //自增长数


    @Basic
    @Column(name = "BUILD_ID",unique = true,nullable = false,length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "BUILD_NAME",length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "BUILD_STATE",length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
    @Column(name = "MODIFY_ON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Id
    @Column(name = "AUTO_NUM",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getAutoNum() {
        return autoNum;
    }

    public void setAutoNum(long autoNum) {
        this.autoNum = autoNum;
    }
}
