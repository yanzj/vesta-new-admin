package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.List;

/**
 * Created by Talent on 2016/11/25.
 */
public class ProjectKeyProcessesCountListDTO {
    private String qualifiedRate;//合格率
    private String unqualifiedRate;//不合格率
    private String onePassRate;//一次通过率
    private List<ProjectKeyProcessesCountDTO> list;

    public String getQualifiedRate() {
        return qualifiedRate;
    }

    public void setQualifiedRate(String qualifiedRate) {
        this.qualifiedRate = qualifiedRate;
    }

    public String getUnqualifiedRate() {
        return unqualifiedRate;
    }

    public void setUnqualifiedRate(String unqualifiedRate) {
        this.unqualifiedRate = unqualifiedRate;
    }

    public String getOnePassRate() {
        return onePassRate;
    }

    public void setOnePassRate(String onePassRate) {
        this.onePassRate = onePassRate;
    }

    public List<ProjectKeyProcessesCountDTO> getList() {
        return list;
    }

    public void setList(List<ProjectKeyProcessesCountDTO> list) {
        this.list = list;
    }
}
