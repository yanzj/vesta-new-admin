package com.maxrocky.vesta.application.projectSideStation.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Talent on 2016/11/9.
 */
public class ProjectSideStationRequestListDTO {
    private List<ProjectSideStationDTO> list;

    public ProjectSideStationRequestListDTO() {
        this.list = new ArrayList<ProjectSideStationDTO>();
    }

    public List<ProjectSideStationDTO> getList() {
        return list;
    }

    public void setList(List<ProjectSideStationDTO> list) {
        this.list = list;
    }
}
