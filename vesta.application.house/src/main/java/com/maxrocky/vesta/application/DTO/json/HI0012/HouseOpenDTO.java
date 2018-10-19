package com.maxrocky.vesta.application.DTO.json.HI0012;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/10/20.
 */
public class HouseOpenDTO {
    private String id;
    private String deliveryPlan;//计划
    private String roomNum;//房间
    private String completedStatus;//业主开放日状态
    private String describes;//业主开放日描述
    private String quality;//工程质量
    private String schedulesatisfied;//活动组织
    private String updateTime;//修改时间
    private String customerName;//客户姓名
    private String togetherName;//陪验人
    private List<HouseTransferImageDTO> imageList;//图片列表
    private List<HouseTransferImageDTO> signList;//签字图片列表
    public HouseOpenDTO(){
        this.togetherName="";
        this.customerName="";
        this.id="";
        this.deliveryPlan="";
        this.roomNum="";
        this.completedStatus="";
        this.describes="";
        this.quality="";
        this.schedulesatisfied="";
        this.updateTime="";
        this.imageList=new ArrayList<HouseTransferImageDTO>();
        this.signList=new ArrayList<HouseTransferImageDTO>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeliveryPlan() {
        return deliveryPlan;
    }

    public void setDeliveryPlan(String deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }


    public String getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getSchedulesatisfied() {
        return schedulesatisfied;
    }

    public void setSchedulesatisfied(String schedulesatisfied) {
        this.schedulesatisfied = schedulesatisfied;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTogetherName() {
        return togetherName;
    }

    public void setTogetherName(String togetherName) {
        this.togetherName = togetherName;
    }

    public List<HouseTransferImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<HouseTransferImageDTO> imageList) {
        this.imageList = imageList;
    }

    public List<HouseTransferImageDTO> getSignList() {
        return signList;
    }

    public void setSignList(List<HouseTransferImageDTO> signList) {
        this.signList = signList;
    }
}
