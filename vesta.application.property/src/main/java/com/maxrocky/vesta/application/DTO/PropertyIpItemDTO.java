package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * 物业收费类目DTO
 * Created by WeiYangDong on 2016/11/21.
 */
public class PropertyIpItemDTO {
    private String IpItemID;        //收费项目ID
    private String IpItemName;      //收费项目名称
    private Integer State;           //是否生效状态:0:无效,1:有效

    private String CreateBy;    //创建人
    private Date CreateOn;      //创建时间

    public Date getCreateOn() {
        return CreateOn;
    }

    public void setCreateOn(Date createOn) {
        CreateOn = createOn;
    }

    public String getIpItemID() {
        return IpItemID;
    }

    public void setIpItemID(String ipItemID) {
        IpItemID = ipItemID;
    }

    public String getIpItemName() {
        return IpItemName;
    }

    public void setIpItemName(String ipItemName) {
        IpItemName = ipItemName;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }
}
