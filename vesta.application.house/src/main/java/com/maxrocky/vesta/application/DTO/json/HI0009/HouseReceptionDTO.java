package com.maxrocky.vesta.application.DTO.json.HI0009;

import java.util.Date;

/**
 * Created by Magic on 2016/5/23.
 */
public class HouseReceptionDTO {
    private String roomid;  //房间id
    private String roomaddress;//房间位置
    private String ownername;//业主姓名
    private String mobile;//联系电话
    /**评价细节
     * highlySatisfied          五星
     * satisfied                四星
     * qualified                三星
     * dissatisfied             两星
     * highlyDissatisfied       一星
     * */
    private String overallfeeling;//总体感觉
    private String detailprocessing;//细节处理
    private String serviceattitude;//服务态度
    private String rectificationspeed;//整改速度
    private String professionaldegree;//专业程度

    private String ownersignature;//业主签字
    private String datetime;//时间

    private String deliveryPlan;//关联计划
    private String roomnum;

    public HouseReceptionDTO(String roomid,String roomnum,String deliveryPlan,String roomaddress,String ownername,String mobile,String overallfeeling, String detailprocessing,String serviceattitude,String rectificationspeed,String professionaldegree, String ownersignature,String datetime){
        this.roomid=roomid;
        this.roomaddress=roomaddress;
        this.ownername=ownername;
        this.mobile=mobile;
        this.overallfeeling=overallfeeling;
        this.detailprocessing=detailprocessing;
        this.serviceattitude=serviceattitude;
        this.rectificationspeed=rectificationspeed;
        this.professionaldegree=professionaldegree;
        this.ownersignature=ownersignature;
        this.datetime=datetime;
        this.deliveryPlan=deliveryPlan;
        this.roomnum=roomnum;

    }
    public HouseReceptionDTO(){
        roomid=null;
        roomaddress=null;
        ownername=null;
        mobile=null;
        overallfeeling=null;
        detailprocessing=null;
        serviceattitude=null;
        rectificationspeed=null;
        professionaldegree=null;
        ownersignature=null;
        datetime=null;
        deliveryPlan=null;
        roomnum=null;
    }
    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getRoomaddress() {
        return roomaddress;
    }

    public void setRoomaddress(String roomaddress) {
        this.roomaddress = roomaddress;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getOverallfeeling() {
        return overallfeeling;
    }

    public void setOverallfeeling(String overallfeeling) {
        this.overallfeeling = overallfeeling;
    }

    public String getDetailprocessing() {
        return detailprocessing;
    }

    public void setDetailprocessing(String detailprocessing) {
        this.detailprocessing = detailprocessing;
    }

    public String getServiceattitude() {
        return serviceattitude;
    }

    public void setServiceattitude(String serviceattitude) {
        this.serviceattitude = serviceattitude;
    }

    public String getRectificationspeed() {
        return rectificationspeed;
    }

    public void setRectificationspeed(String rectificationspeed) {
        this.rectificationspeed = rectificationspeed;
    }

    public String getProfessionaldegree() {
        return professionaldegree;
    }

    public void setProfessionaldegree(String professionaldegree) {
        this.professionaldegree = professionaldegree;
    }

    public String getOwnersignature() {
        return ownersignature;
    }

    public void setOwnersignature(String ownersignature) {
        this.ownersignature = ownersignature;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDeliveryPlan() {
        return deliveryPlan;
    }

    public void setDeliveryPlan(String deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
