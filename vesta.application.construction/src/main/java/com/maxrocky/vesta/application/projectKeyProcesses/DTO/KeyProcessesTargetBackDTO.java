package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talent on 2016/11/22.
 */
public class KeyProcessesTargetBackDTO {
    private String id;//工序指标id
    private String processId;//工序id
    private String targetId;//指标ID
    private String targetName;//指标名称
    private String description;//指标描述
    private String imageUrl;//指标图片地址
    private String qualifiedState;//合格(合格、不合格)
    private String targetDescription;//指标描述
    private String flag;//指标标识
    private List<KeyProcessesTargetDetailsBackDTO> targetDetailsBackDTOList;//整改验收信息

    public KeyProcessesTargetBackDTO() {
        this.processId = "";//工序id
        this.targetId = "";//指标ID
        this.targetName = "";//指标名称
        this.description = "";//指标描述
        this.imageUrl = "";//指标图片地址
        this.qualifiedState = "";//合格(合格、不合格)
        this.targetDescription="";//指标描述
        this.flag="";
        this.targetDetailsBackDTOList = new ArrayList<KeyProcessesTargetDetailsBackDTO>();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTargetDescription() {
        return targetDescription;
    }

    public void setTargetDescription(String targetDescription) {
        this.targetDescription = targetDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<KeyProcessesTargetDetailsBackDTO> getTargetDetailsBackDTOList() {
        return targetDetailsBackDTOList;
    }

    public void setTargetDetailsBackDTOList(List<KeyProcessesTargetDetailsBackDTO> targetDetailsBackDTOList) {
        this.targetDetailsBackDTOList = targetDetailsBackDTOList;
    }
}
