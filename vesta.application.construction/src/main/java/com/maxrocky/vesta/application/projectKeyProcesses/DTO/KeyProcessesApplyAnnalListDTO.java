package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talent on 2016/11/24.
 */
public class KeyProcessesApplyAnnalListDTO {
    private List<KeyProcessesApplyAnnalDTO> list;

    public KeyProcessesApplyAnnalListDTO() {
        this.list = new ArrayList<KeyProcessesApplyAnnalDTO>();
    }

    public List<KeyProcessesApplyAnnalDTO> getList() {
        return list;
    }

    public void setList(List<KeyProcessesApplyAnnalDTO> list) {
        this.list = list;
    }
}
