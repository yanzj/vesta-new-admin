package com.maxrocky.vesta.application.DTO;

/**
 * Created by mql on 2016/6/12.
 */
public class QuestionUpdateCheckDTO {
    private String updateFlag;//是否更新:Y/N

    public QuestionUpdateCheckDTO(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }
}
