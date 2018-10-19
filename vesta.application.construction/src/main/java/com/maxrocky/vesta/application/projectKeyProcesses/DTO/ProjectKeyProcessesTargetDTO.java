package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.List;

/**
 * Created by Talent on 2016/11/24.
 */
public class ProjectKeyProcessesTargetDTO {
    private String targetName;//指标名称
    private String targetDescription;//指标描述
    private String description;//工序指标描述
    private String targetImgUrl;//指标图片地址
    private String qualifiedState;//合格(合格、不合格)
    private List<KeyProcessesTargetDetailsBackDTO> detailsBackDTOs;
    private List<ProjectKeyProcessesTargetDTO> targetDTOByPartyBAnnalList;//乙方整改信息
    private List<ProjectKeyProcessesTargetDTO> targetDTOBySupervisionAnnalList;//监理整改信息
    private List<ProjectKeyProcessesTargetDTO> targetDTOByPartyAAnnalList;//甲方整改信息

    public List<ProjectKeyProcessesTargetDTO> getTargetDTOByPartyBAnnalList() {
        return targetDTOByPartyBAnnalList;
    }

    public void setTargetDTOByPartyBAnnalList(List<ProjectKeyProcessesTargetDTO> targetDTOByPartyBAnnalList) {
        this.targetDTOByPartyBAnnalList = targetDTOByPartyBAnnalList;
    }

    public List<ProjectKeyProcessesTargetDTO> getTargetDTOBySupervisionAnnalList() {
        return targetDTOBySupervisionAnnalList;
    }

    public void setTargetDTOBySupervisionAnnalList(List<ProjectKeyProcessesTargetDTO> targetDTOBySupervisionAnnalList) {
        this.targetDTOBySupervisionAnnalList = targetDTOBySupervisionAnnalList;
    }

    public List<ProjectKeyProcessesTargetDTO> getTargetDTOByPartyAAnnalList() {
        return targetDTOByPartyAAnnalList;
    }

    public void setTargetDTOByPartyAAnnalList(List<ProjectKeyProcessesTargetDTO> targetDTOByPartyAAnnalList) {
        this.targetDTOByPartyAAnnalList = targetDTOByPartyAAnnalList;
    }

    public List<KeyProcessesTargetDetailsBackDTO> getDetailsBackDTOs() {
        return detailsBackDTOs;
    }

    public void setDetailsBackDTOs(List<KeyProcessesTargetDetailsBackDTO> detailsBackDTOs) {
        this.detailsBackDTOs = detailsBackDTOs;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetDescription() {
        return targetDescription;
    }

    public void setTargetDescription(String targetDescription) {
        this.targetDescription = targetDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetImgUrl() {
        return targetImgUrl;
    }

    public void setTargetImgUrl(String targetImgUrl) {
        this.targetImgUrl = targetImgUrl;
    }

    public String getQualifiedState() {
        return qualifiedState;
    }

    public void setQualifiedState(String qualifiedState) {
        this.qualifiedState = qualifiedState;
    }
}
