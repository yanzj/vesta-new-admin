package com.maxrocky.vesta.application.baseData.JsonDTO;

/**
 * Created by chen on 2016/10/25.
 * 工程户型图数据封装类
 */
public class FloorImageDTO {
    private String imgId="";      //户型图ID
    private String floorId="";    //楼层id
    private String imgName="";    //户型图名称
    private String imgDesc="";    //描述
    private String imgUrl="";     //地址
    private String imgState="";   //状态

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgState() {
        return imgState;
    }

    public void setImgState(String imgState) {
        this.imgState = imgState;
    }
}
