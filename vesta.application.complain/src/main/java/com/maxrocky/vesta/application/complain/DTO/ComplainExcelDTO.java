package com.maxrocky.vesta.application.complain.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/7/18.
 */
public class ComplainExcelDTO {
    private int number;//投诉ID
    private String projectName;//项目名称
    private String houseName;//房间名称
    private String complaintsDescribes;//投诉描述
    private String documentsState;//单据状态 100000000：已创建；100000001：处理中；100000002：已完成；100000003：已评价；100000004：强制关闭(物业)；100000005：已废弃
    private String createByName;//创建人名称
    private String complaintPersonName;//投诉人姓名
    private String limitedReplyTime;// 限时答复时间
    private String submitTime;//投诉时间

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getComplaintsDescribes() {
        return complaintsDescribes;
    }

    public void setComplaintsDescribes(String complaintsDescribes) {
        this.complaintsDescribes = complaintsDescribes;
    }

    public String getDocumentsState() {
        return documentsState;
    }

    public void setDocumentsState(String documentsState) {
        this.documentsState = documentsState;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getComplaintPersonName() {
        return complaintPersonName;
    }

    public void setComplaintPersonName(String complaintPersonName) {
        this.complaintPersonName = complaintPersonName;
    }

    public String getLimitedReplyTime() {
        return limitedReplyTime;
    }

    public void setLimitedReplyTime(String limitedReplyTime) {
        this.limitedReplyTime = limitedReplyTime;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
}
