package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:43
 * Describe: 公告范围表_限定功盖范围，此表需要优化
 */
@Entity
@Table(name = "announcement_scope")
public class AnnouncementScopeEntity implements Serializable {
    private String id;         //主键
    private String announcementDetailId; //
    private Integer isAll;       //全部        0,不是全部 1,全部
    private String city;     //所属城市
    private String cityId;  //所属城市Id
    private String projectName;     //项目名称
    private String projectId;       //项目Id
    private String scope;           //范围等级设定城市为二级菜单，设定项目为三级菜单
    private Integer status;     // 0,启用 1，不启用

    private Date createDate;      //创建日期
    private String createPerson;    //创建人
    private Date operateDate;      //操作日期
    private String operatePerson;    //操作人

    @Id
    @Column(name = "id", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "announcement_detail_id", length = 50, nullable = true, updatable = true)
    public String getAnnouncementDetailId() {
        return announcementDetailId;
    }

    public void setAnnouncementDetailId(String announcementDetailId) {
        this.announcementDetailId = announcementDetailId;
    }

    @Basic
    @Column(name = "is_all", length = 5, nullable = true, updatable = true)
    public Integer getIsAll() {
        return isAll;
    }

    public void setIsAll(Integer isAll) {
        this.isAll = isAll;
    }

    @Basic
    @Column(name = "city_name", length = 50, nullable = true, updatable = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "project_name", length = 50, nullable = true, updatable = true)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "scope", length = 50, nullable = true, updatable = true)
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "status", length = 5, nullable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", length = 50, nullable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_person", length = 50, nullable = true, updatable = true)
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operate_date", length = 50, nullable = true, updatable = true)
    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    @Basic
    @Column(name = "operate_person", length = 50, nullable = true, updatable = true)
    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson;
    }

    @Basic
    @Column(name = "city_id",length = 50,nullable = true,updatable = true)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "project_id",length = 50,nullable = true,updatable = true)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
