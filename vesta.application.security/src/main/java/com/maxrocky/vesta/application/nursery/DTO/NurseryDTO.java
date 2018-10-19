package com.maxrocky.vesta.application.nursery.DTO;

/**
 * Created by yuanyn on 2017/9/29.
 */
public class NurseryDTO {
    private String id; //uuid
    private String nurseryName;//苗木名称
    private String num; //数量
    private String height; //高度
    private String remark; //备注
    private String imageUrl; //图片路径

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
