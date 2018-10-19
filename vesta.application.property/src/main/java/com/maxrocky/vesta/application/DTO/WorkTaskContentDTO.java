package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/26.
 * 工单内容
 */
public class WorkTaskContentDTO {
    private List<WorkOrderContentDTO> taskConentList;

    public WorkTaskContentDTO() {
        this.taskConentList = new ArrayList<WorkOrderContentDTO>();
    }

    public List<WorkOrderContentDTO> getTaskConentList() {
        return taskConentList;
    }

    public void setTaskConentList(List<WorkOrderContentDTO> taskConentList) {
        this.taskConentList = taskConentList;
    }
}
