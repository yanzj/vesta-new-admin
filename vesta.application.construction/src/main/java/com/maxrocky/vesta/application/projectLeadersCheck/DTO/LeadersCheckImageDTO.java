package com.maxrocky.vesta.application.projectLeadersCheck.DTO;

/**
 * Created by Magic on 2017/3/20.
 */
public class LeadersCheckImageDTO {
    private String id;
    private String businessId;//ҵ��id
    private String imageUrl;//ͼƬ��ַ
    private String type;//���� 1:�ճ�Ѳ�죻2������գ�3���ؼ�����4�����������5���������գ�6����վ; 8:�쵼���
    private String state;//״̬ 0:�����ã�1������
    private String qualifiedState;//�ϸ� ���ϸ�
    private String createOn;//����ʱ��
    private String modifyOn;//�޸�ʱ��
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
