package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/10/25.
 */
public class CheckDailyPatrolInspectionListDTO {
    private List<CheckDailyPatrolInspectionDTO> checkList;
    public CheckDailyPatrolInspectionListDTO(){
        this.checkList=new ArrayList<CheckDailyPatrolInspectionDTO>();
    }
    public List<CheckDailyPatrolInspectionDTO> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<CheckDailyPatrolInspectionDTO> checkList) {
        this.checkList = checkList;
    }
}
