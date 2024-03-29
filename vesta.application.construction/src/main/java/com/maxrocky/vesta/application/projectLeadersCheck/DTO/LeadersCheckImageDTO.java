package com.maxrocky.vesta.application.projectLeadersCheck.DTO;

/**
 * Created by Magic on 2017/3/20.
 */
public class LeadersCheckImageDTO {
    private String id;
    private String businessId;//业务id
    private String imageUrl;//图片地址
    private String type;//类型 1:日常巡检；2检查验收；3：关键工序；4：样板点评；5：材料验收；6：旁站; 8:领导检查
    private String state;//状态 0:不可用；1：可用
    private String qualifiedState;//合格 不合格
    private String createOn;//创建时间
    private String modifyOn;//修改时间
    public LeadersCheckImageDTO(){
        this.id="";
        this.businessId="";
        this.imageUrl="";
        this.type="";
        this.state="";
        this.createOn="";
        this.modifyOn="";
        this.qualifiedState="";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getQualifiedState() {
        return qualifiedState;
    }

    public void setQualifiedState(String qualifiedState) {
        this.qualifiedState = qualifiedState;
    }
}
