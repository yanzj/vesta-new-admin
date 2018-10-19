package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/10/19.
 */
public class PropertyCheckYNDTO {
    private List<QuestionUpdateCheckAllDTO> checkList;
    public PropertyCheckYNDTO(){
        this.checkList=new ArrayList<QuestionUpdateCheckAllDTO>();
    }
    public List<QuestionUpdateCheckAllDTO> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<QuestionUpdateCheckAllDTO> checkList) {
        this.checkList = checkList;
    }
}
