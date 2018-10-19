package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 促销信息发布范围表
 * Created by WeiYangDong on 2017/5/12.
 */
@Entity
@Table(name = "sales_promotion_info_scope")
public class SalesPromotionInfoScopeEntity {
    private String id;//主键ID
    private String salesPromotionInfoId;//促销信息ID
    private String city;//发布城市
    private String cityId;//发布城市Id
    private String projectName;//发布项目
    private String projectId;//发布项目Id
    private Integer scopeLev;//范围等级设定:1,全部城市.2,城市项目

    private String createBy;    //创建人
    private Date createOn;      //创建时间

    @Id
    @Column(name = "id",length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "salesPromotionInfo_id",length = 32)
    public String getSalesPromotionInfoId() {
        return salesPromotionInfoId;
    }

    public void setSalesPromotionInfoId(String salesPromotionInfoId) {
        this.salesPromotionInfoId = salesPromotionInfoId;
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
    @Column(name = "city_id",length = 50,nullable = true,updatable = true)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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
    @Column(name = "project_id",length = 50,nullable = true,updatable = true)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "scope_lev", length = 5)
    public Integer getScopeLev() {
        return scopeLev;
    }

    public void setScopeLev(Integer scopeLev) {
        this.scopeLev = scopeLev;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

}
