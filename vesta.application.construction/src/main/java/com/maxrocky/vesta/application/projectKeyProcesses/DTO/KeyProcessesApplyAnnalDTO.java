package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talent on 2016/11/24.
 */
public class KeyProcessesApplyAnnalDTO {
    private String processId;//工序ID
    private String qualifiedState;//合格状态：合格、不合格
    private String type;//整改记录分类：（0: 乙方整改  1: 监理审核  2: 甲方审核）
    private List<KeyProcessesTargetApplyAnnalDTO> targetApplyAnnalDTOs;

    public KeyProcessesApplyAnnalDTO() {
        this.processId = "";
        this.qualifiedState = "";
        this.type = "";
        this.targetApplyAnnalDTOs = new ArrayList<KeyProcessesTargetApplyAnnalDTO>();
    }

    public String getQualifiedState() {
        return qualifiedState;
    }

    public void setQualifiedState(String qualifiedState) {
        this.qualifiedState = qualifiedState;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public List<KeyProcessesTargetApplyAnnalDTO> getTargetApplyAnnalDTOs() {
        return targetApplyAnnalDTOs;
    }

    public void setTargetApplyAnnalDTOs(List<KeyProcessesTargetApplyAnnalDTO> targetApplyAnnalDTOs) {
        this.targetApplyAnnalDTOs = targetApplyAnnalDTOs;
    }
}
