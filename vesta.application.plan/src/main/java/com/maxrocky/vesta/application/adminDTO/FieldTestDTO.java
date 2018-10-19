package com.maxrocky.vesta.application.adminDTO;

import java.util.List;

/**
 * Created by mql on 2016/5/23.
 * 现场试验
 */
public class FieldTestDTO {
    private List<PlanSetDTO> planSetList;//批次列表
    //private List<SetInitDTO> setInitDTOList;//批次初始化列表

    public List<PlanSetDTO> getPlanSetList() {
        return planSetList;
    }

    public void setPlanSetList(List<PlanSetDTO> planSetList) {
        this.planSetList = planSetList;
    }

    /*public List<SetInitDTO> getSetInitDTOList() {
        return setInitDTOList;
    }

    public void setSetInitDTOList(List<SetInitDTO> setInitDTOList) {
        this.setInitDTOList = setInitDTOList;
    }*/
}
