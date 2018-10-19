package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.List;

/**
 * 关键工序批量提交申请信息
 * Created by Talent on 2016/11/22.
 */
public class KeyProcessesApplyListDTO {
    List<KeyProcessesApplyDTO> list;

    public List<KeyProcessesApplyDTO> getList() {
        return list;
    }

    public void setList(List<KeyProcessesApplyDTO> list) {
        this.list = list;
    }
}
