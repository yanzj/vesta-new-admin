package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/17.
 * 工程阶段项目实体
 */
@Entity
@Table(name = "project_project")
public class ProjectProjectEntity {
    private String id;        //主键
    private String name;      //名称
    private String state;     //状态
    private String cityId;    //区域
    private Date createOn;    //创建时间
    private Date modifyOn;    //修改时间
    private int autoNum;      //自增长数


    @Basic
    @Column(name = "PROJECT_ID",length = 50,unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROJECT_NAME",length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name="PROJECT_STATE",length = 2)
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

    @Basic
    @Column(name = "CITY_ID",length = 50)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Id
    @Column(name = "AUTO_NUM",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAutoNum() {
        return autoNum;
    }

    public void setAutoNum(int autoNum) {
        this.autoNum = autoNum;
    }
}
