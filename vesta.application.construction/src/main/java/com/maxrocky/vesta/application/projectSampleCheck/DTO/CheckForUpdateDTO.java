package com.maxrocky.vesta.application.projectSampleCheck.DTO;

import com.maxrocky.vesta.application.projectKeyProcesses.DTO.KeyProcessesCheckForUpdateDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talent on 2016/11/23.
 */
public class CheckForUpdateDTO {
    private List<SampleCheckForUpdateDTO> list;

    public CheckForUpdateDTO() {
        this.list = new ArrayList<SampleCheckForUpdateDTO>();
    }

    public List<SampleCheckForUpdateDTO> getList() {
        return list;
    }

    public void setList(List<SampleCheckForUpdateDTO> list) {
        this.list = list;
    }
}
