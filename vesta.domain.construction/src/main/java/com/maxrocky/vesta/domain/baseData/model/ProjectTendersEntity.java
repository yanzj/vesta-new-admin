package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/18.
 * 工程标段实体
 */

@Entity
@Table(name = "project_tenders")
public class ProjectTendersEntity {
    private String tenderId;      //标段ID
    private String projectId;     //项目ID
    private String supplierId;    //责任单位ID
    private String tenderName;    //名称
    private String tenderState;   //状态
    private String createBy;      //创建人
    private String modifyBy;      //修改人
    private Date createOn;        //创建时间
    private Date modifyOn;        //修改时间


    @Id
    @Column(name = "TENDER_ID",length = 50,unique = true,nullable = false)
    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
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
    @Column(name = "SUPPLIER_ID",length = 50)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "TENDER_NAME",length = 32)
    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    @Basic
    @Column(name = "TENDER_STATE",length = 2)
    public String getTenderState() {
        return tenderState;
    }

    public void setTenderState(String tenderState) {
        this.tenderState = tenderState;
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
    @Column(name = "MODIFY_BY",length = 30)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
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
}
