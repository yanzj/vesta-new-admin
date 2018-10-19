package com.maxrocky.vesta.application.adminDTO;


import java.util.List;

/**
 * Created by mql on 2016/5/18.
 * 问题检查项DTO
 */
public class QuestionCheckDTO {
    private String checkId;//检查项代码
    private String checkName;//检查项名称
    private String isQualified;//是否合格
    private String checkDesc;//描述
    private String contractor;//承建商
    private String responsibility;//责任单位
    private List<QuestionImageDTO> imageList;//图片列表
    private List<CaseRepairDTO> repairList;//整改记录

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(String isQualified) {
        this.isQualified = isQualified;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public List<QuestionImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<QuestionImageDTO> imageList) {
        this.imageList = imageList;
    }

    public List<CaseRepairDTO> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<CaseRepairDTO> repairList) {
        this.repairList = repairList;
    }
}
