package com.maxrocky.vesta.application.inspectAcceptance.DTO;

import java.util.List;

/**
 * 工程验收通统计
 * Created by Talent on 2016/11/7.
 */
public class ProjectAcceptanceStatisticsDTO {
    private String qualifiedRate;//合格率
    private String unqualifiedRate;//不合格率
    private String onePassRate;//一次通过率
    private List<InspectAcceptanceCountDTO> list;

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

    public List<InspectAcceptanceCountDTO> getList() {
        return list;
    }

    public void setList(List<InspectAcceptanceCountDTO> list) {
        this.list = list;
    }
}
