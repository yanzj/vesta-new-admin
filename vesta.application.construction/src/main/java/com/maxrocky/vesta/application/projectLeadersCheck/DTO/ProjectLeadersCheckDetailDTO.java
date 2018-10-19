package com.maxrocky.vesta.application.projectLeadersCheck.DTO;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/3/20.
 */
public class ProjectLeadersCheckDetailDTO {
    private String id;//����ID
    private String checkId;//���ID
    private String description;//����
    private String createBy;//������
    private String createOn;//����ʱ��
    private String type;//0����Ŀ����
    private String serialNumber;//�����
    private List<LeadersCheckImageDTO> imageList;//ͼƬ

    public ProjectLeadersCheckDetailDTO(){
        this.id="";
        this.checkId="";
        this.description="";
        this.createBy="";
        this.createOn="";
        this.type="";
        this.serialNumber="";
        this.imageList=new ArrayList<LeadersCheckImageDTO>();
    }

    public List<LeadersCheckImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<LeadersCheckImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
