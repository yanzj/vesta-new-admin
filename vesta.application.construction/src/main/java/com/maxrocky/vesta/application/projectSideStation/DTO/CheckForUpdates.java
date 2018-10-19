package com.maxrocky.vesta.application.projectSideStation.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talent on 2016/11/8.
 */
public class CheckForUpdates {
    private List<CheckForUpdatesFromSideStationDTO> list;

    public CheckForUpdates() {
        this.list = new ArrayList<CheckForUpdatesFromSideStationDTO>();
    }

    public List<CheckForUpdatesFromSideStationDTO> getList() {
        return list;
    }

    public void setList(List<CheckForUpdatesFromSideStationDTO> list) {
        this.list = list;
    }
}
