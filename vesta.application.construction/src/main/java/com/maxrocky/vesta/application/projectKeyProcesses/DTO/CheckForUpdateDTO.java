package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talent on 2016/11/23.
 */
public class CheckForUpdateDTO {
    private List<KeyProcessesCheckForUpdateDTO> list;

    public CheckForUpdateDTO() {
        this.list = new ArrayList<KeyProcessesCheckForUpdateDTO>();
    }

    public List<KeyProcessesCheckForUpdateDTO> getList() {
        return list;
    }

    public void setList(List<KeyProcessesCheckForUpdateDTO> list) {
        this.list = list;
    }
}
