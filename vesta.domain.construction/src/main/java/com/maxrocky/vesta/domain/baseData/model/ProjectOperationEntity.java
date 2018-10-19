package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 工程阶段经营单位实体
 * Created by chen on 2016/12/6.
 */
@Entity
@Table(name ="project_operation")
public class ProjectOperationEntity {  //经营单位实体
    private String optId;        //主键
    private String optName;      //经营单位名
    private Date createOn;       //创建时间
    private Date modifyOn;       //修改时间
    private String state;        //状态 0删除 1正常

    @Id
    @Column(name = "OPERATION_ID",unique = true,nullable = false,length = 50)
    public String getOptId() {
        return optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    @Basic
    @Column(name = "OPERATION_NAME",length = 32)
    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
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

    @Basic
    @Column(name = "OPERATION_STATE",length = 2,nullable = false)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
