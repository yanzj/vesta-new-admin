package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 物业收款方式基础数据
 * Created by WeiYangDong on 2016/11/17.
 */
@Entity
@Table(name = "property_trading")
public class PropertyTradingEntity implements Serializable{

    private String TradingID;       //ID
    private String TradingName;     //Name
    private Integer State;          //是否生效状态:0:无效,1:有效

    private String CreateBy;    //创建人
    private Date CreateOn;      //创建时间

    @Id
    @Column(name = "Trading_ID",length = 50)
    public String getTradingID() {
        return TradingID;
    }

    public void setTradingID(String tradingID) {
        TradingID = tradingID;
    }

    @Basic
    @Column(name = "Trading_Name", length = 300)
    public String getTradingName() {
        return TradingName;
    }

    public void setTradingName(String tradingName) {
        TradingName = tradingName;
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
