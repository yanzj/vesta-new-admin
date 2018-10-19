package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiazefeng on 2016/10/24.
 */
public class FromPartyBProblemSolvingListDTO {
    private List<FromPartyBProblemSolvingDTO> list;
    public FromPartyBProblemSolvingListDTO() {
        this.list = new ArrayList<FromPartyBProblemSolvingDTO>();
    }

    public List<FromPartyBProblemSolvingDTO> getList() {
        return list;
    }

    public void setList(List<FromPartyBProblemSolvingDTO> list) {
        this.list = list;
    }
}
