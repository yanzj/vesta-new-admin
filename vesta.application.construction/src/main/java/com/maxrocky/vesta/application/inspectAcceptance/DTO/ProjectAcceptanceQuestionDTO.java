package com.maxrocky.vesta.application.inspectAcceptance.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JIAZEFENG on 2016/10/24.
 */
public class ProjectAcceptanceQuestionDTO {
    private String id;
    private String timeStamp;
    private List<ProjectAcceptanceBatchDTO> list;

    public ProjectAcceptanceQuestionDTO() {
        this.id = "";
        this.timeStamp = "";
        this.list = new ArrayList<ProjectAcceptanceBatchDTO>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<ProjectAcceptanceBatchDTO> getList() {
        return list;
    }

    public void setList(List<ProjectAcceptanceBatchDTO> list) {
        this.list = list;
    }
}
