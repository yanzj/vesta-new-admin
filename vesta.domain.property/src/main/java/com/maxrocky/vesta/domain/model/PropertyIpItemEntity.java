package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 物业收费类目基础数据
 * Created by WeiYangDong on 2016/11/17.
 */
@Entity
@Table(name = "property_ip_item")
public class PropertyIpItemEntity implements Serializable{

    private String IpItemID;        //收费项目ID
    private String IpItemName;      //收费项目名称
    private Integer State;           //是否生效状态:0:无效,1:有效

    private String CreateBy;    //创建人
    private Date CreateOn;      //创建时间

    @Id
    @Column(name = "IpItem_ID",length = 50)
    public String getIpItemID() {
        return IpItemID;
    }

    public void setIpItemID(String ipItemID) {
        IpItemID = ipItemID;
    }

    @Basic
    @Column(name = "IpItem_Name", length = 500)
    public String getIpItemName() {
        return IpItemName;
    }

    public void setIpItemName(String ipItemName) {
        IpItemName = ipItemName;
    }

    @Basic
    @Column(name = "State")
    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }

    @Basic
    @Column(name = "Create_By", nullable = true, length = 50)
    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    @Basic
    @Column(name = "Create_On", nullable = true)
    public Date getCreateOn() {
        return CreateOn;
    }

    public void setCreateOn(Date createOn) {
        CreateOn = createOn;
    }
}
