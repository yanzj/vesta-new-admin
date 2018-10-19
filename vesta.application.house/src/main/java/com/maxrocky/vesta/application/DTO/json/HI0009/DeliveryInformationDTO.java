package com.maxrocky.vesta.application.DTO.json.HI0009;

import java.util.Date;

/**
 * Created by Magic on 2016/5/23.
 */
public class DeliveryInformationDTO {

    private String roomid;  //房间id
    private String roomaddress;//房间位置
    private String ownername;//业主姓名
    private String mobile;//联系电话
    private String deliveryDate ;//交房时间

    private String meternumber;//电表号
    private String meterbase;//电表底数
    private String waternumber;//水表号
    private String waterbase;//水表底数
    private String gastablenumber;//气表号
    private String gastablebase;//气表底数
    private String retainkey;//钥匙是否留用
    private String overallfeeling;//总体感觉

    private String deliveryPlan;//关联计划
    private String roomnum;

    public DeliveryInformationDTO(String roomid,String deliveryPlan,String roomnum,String roomaddress,String ownername,String mobile, String deliveryDate,String meternumber,String meterbase, String waternumber,String waterbase,String gastablenumber,String gastablebase, String retainkey, String overallfeeling){
        this.roomid=roomid;
        this.roomaddress=roomaddress;
        this.ownername=ownername;
        this.mobile=mobile;
        this.deliveryDate=deliveryDate;
        this.meternumber=meternumber;
        this.meterbase=meterbase;
        this.waternumber=waternumber;
        this.waterbase=waterbase;
        this.gastablenumber=gastablenumber;
        this.gastablebase=gastablebase;
        this.retainkey=retainkey;
        this.overallfeeling=overallfeeling;
        this.deliveryPlan=deliveryPlan;
        this.roomnum=roomnum;
    }
    public DeliveryInformationDTO(){
        roomid=null;
        roomaddress=null;
        ownername=null;
        mobile=null;
        deliveryDate=null;
        meternumber=null;
        meterbase=null;
        waternumber=null;
        waterbase=null;
        gastablenumber=null;
        gastablebase=null;
        retainkey=null;
        overallfeeling=null;
        roomnum=null;
        deliveryPlan=null;
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

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMeternumber() {
        return meternumber;
    }

    public void setMeternumber(String meternumber) {
        this.meternumber = meternumber;
    }

    public String getMeterbase() {
        return meterbase;
    }

    public void setMeterbase(String meterbase) {
        this.meterbase = meterbase;
    }

    public String getWaternumber() {
        return waternumber;
    }

    public void setWaternumber(String waternumber) {
        this.waternumber = waternumber;
    }

    public String getWaterbase() {
        return waterbase;
    }

    public void setWaterbase(String waterbase) {
        this.waterbase = waterbase;
    }

    public String getGastablenumber() {
        return gastablenumber;
    }

    public void setGastablenumber(String gastablenumber) {
        this.gastablenumber = gastablenumber;
    }

    public String getGastablebase() {
        return gastablebase;
    }

    public void setGastablebase(String gastablebase) {
        this.gastablebase = gastablebase;
    }

    public String getRetainkey() {
        return retainkey;
    }

    public void setRetainkey(String retainkey) {
        this.retainkey = retainkey;
    }

    public String getOverallfeeling() {
        return overallfeeling;
    }

    public void setOverallfeeling(String overallfeeling) {
        this.overallfeeling = overallfeeling;
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
