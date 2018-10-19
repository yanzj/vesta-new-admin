package com.maxrocky.vesta.application.projectSampleCheck.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/1/10.
 */
public class ProjectUpSamplCheckListDTO {
    private List<ProjectUpSamplCheckDTO> list;
    public ProjectUpSamplCheckListDTO(){
        this.list=new ArrayList<ProjectUpSamplCheckDTO>();
    }

    public List<ProjectUpSamplCheckDTO> getList() {
        return list;
    }

    public void setList(List<ProjectUpSamplCheckDTO> list) {
        this.list = list;
    }
}
