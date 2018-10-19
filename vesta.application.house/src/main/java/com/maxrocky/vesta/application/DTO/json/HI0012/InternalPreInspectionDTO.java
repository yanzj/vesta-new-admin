package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.List;

/**
 * Created by Magic on 2016/5/18.
 */
public class InternalPreInspectionDTO {
    private List internalPreInspectionList;

    public InternalPreInspectionDTO(List internalPreInspectionList){
        this.internalPreInspectionList=internalPreInspectionList;
    }
    public InternalPreInspectionDTO(){
        internalPreInspectionList=null;
    }
    public List getInternalPreInspectionList() {
        return internalPreInspectionList;
    }

    public void setInternalPreInspectionList(List internalPreInspectionList) {
        this.internalPreInspectionList = internalPreInspectionList;
    }
}
