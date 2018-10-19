package com.maxrocky.vesta.application.projectSideStation.DTO;

import com.maxrocky.vesta.application.inspectAcceptance.DTO.ProjectAcceptanceBatchDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talent on 2016/11/8.
 */
public class ProjectSideStationListDTO {
    private String id;
    private String timeStamp;
    private List<ProjectSideStationDTO> list;

    public ProjectSideStationListDTO() {
        this.id = "";
        this.timeStamp = "";
        this.list = new ArrayList<ProjectSideStationDTO>();
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

    public List<ProjectSideStationDTO> getList() {
        return list;
    }

    public void setList(List<ProjectSideStationDTO> list) {
        this.list = list;
    }
}
