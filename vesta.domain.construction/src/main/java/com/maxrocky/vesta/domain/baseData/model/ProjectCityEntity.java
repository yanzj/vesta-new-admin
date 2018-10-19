package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/12/6.
 */
@Entity
@Table(name = "project_city")
public class ProjectCityEntity {            //工程阶段城市表
    private String cityId;      //城市ID
    private String cityName;    //城市名
    private String optId;       //所属经营单位
    private Date createOn;      //创建时间
    private Date modifyOn;      //修改时间
    private String state;       //状态 0删除 1正常


    @Id
    @Column(name = "CITY_ID",unique = true,nullable = false,length = 50)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "CITY_NAME",length = 32)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
    @Column(name = "OPERATION_ID",length = 50)
    public String getOptId() {
        return optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    @Basic
    @Column(name = "STATE",length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
