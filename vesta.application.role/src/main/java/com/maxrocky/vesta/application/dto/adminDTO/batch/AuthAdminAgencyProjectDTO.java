package com.maxrocky.vesta.application.dto.adminDTO.batch;

import java.util.List;

/**
 * 项目角色 授权实体
 * Created by Magic on 2018/1/4.
 */
public class AuthAdminAgencyProjectDTO {
    private String authAgencyId;//组织机构id
    private String authAgencyName;//组织机构名
    private String agencyType;  //类型
    private List<AuthAdminProjectListDTO> list;

    public String getAuthAgencyId() {
        return authAgencyId;
    }

    public void setAuthAgencyId(String authAgencyId) {
        this.authAgencyId = authAgencyId;
    }

    public String getAuthAgencyName() {
        return authAgencyName;
    }

    public void setAuthAgencyName(String authAgencyName) {
        this.authAgencyName = authAgencyName;
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public List<AuthAdminProjectListDTO> getList() {
        return list;
    }

    public void setList(List<AuthAdminProjectListDTO> list) {
        this.list = list;
    }
}
