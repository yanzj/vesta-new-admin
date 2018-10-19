package com.maxrocky.vesta.application.inspectAcceptance.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiazefeng on 2016/10/26.
 */
public class CheckForUpdate {
    private List<CheckForUpdateToAcceptanceDTO> list;
    public CheckForUpdate(){
        this.list = new ArrayList<CheckForUpdateToAcceptanceDTO>();
    }

    public List<CheckForUpdateToAcceptanceDTO> getList() {
        return list;
    }

    public void setList(List<CheckForUpdateToAcceptanceDTO> list) {
        this.list = list;
    }
}
