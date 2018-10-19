package com.maxrocky.vesta.application.DTO.json;

import com.maxrocky.vesta.application.DTO.admin.RectificationListDTO;

import java.util.List;

/**
 * Created by Magic on 2016/5/27.
 */
public class RepairJsonDTO {
    private List<RectificationListDTO> changeList;                   //整改单列表

    public List<RectificationListDTO> getChangeList() {
        return changeList;
    }

    public void setChangeList(List<RectificationListDTO> changeList) {
        this.changeList = changeList;
    }
}
