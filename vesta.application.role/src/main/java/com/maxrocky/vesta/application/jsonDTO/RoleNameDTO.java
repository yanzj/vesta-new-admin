package com.maxrocky.vesta.application.jsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/5.
 */
public class RoleNameDTO {
    private String roleName;           //角色名
    private List<StageDTO> stageList;  //阶段名列表

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<StageDTO> getStageList() {
        return stageList;
    }

    public void setStageList(List<StageDTO> stageList) {
        this.stageList = stageList;
    }
}
