package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/22.
 * 物业报修/报修整改单
 */
public class RepairInfoDTO {
    List<RepairDTO> repairList=new ArrayList<>();
    List<RectifyDTO> rectifyList=new ArrayList<>();

    public List<RepairDTO> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<RepairDTO> repairList) {
        this.repairList = repairList;
    }

    public List<RectifyDTO> getRectifyList() {
        return rectifyList;
    }

    public void setRectifyList(List<RectifyDTO> rectifyList) {
        this.rectifyList = rectifyList;
    }
}
