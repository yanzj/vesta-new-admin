package com.maxrocky.vesta.application.dto.adminDTO.batch;

import java.util.List;

/**
 * Created by maxrocky on 2017/12/26.
 */
public class AuthFunctionPointListDTO {
    private String checked;
    private List<AuthFunctionPointDTO> list;
    private String classification;//类型
    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public List<AuthFunctionPointDTO> getList() {
        return list;
    }

    public void setList(List<AuthFunctionPointDTO> list) {
        this.list = list;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
