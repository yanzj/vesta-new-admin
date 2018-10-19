package com.maxrocky.vesta.application.ProjectMaterial.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/12/1.
 */
public class ProjectMaterialOutListDTO {
    private List<ProjectMaterialOutDTO> list;
    public ProjectMaterialOutListDTO(){
        this.list=new ArrayList<ProjectMaterialOutDTO>();
    }
    public List<ProjectMaterialOutDTO> getList() {
        return list;
    }

    public void setList(List<ProjectMaterialOutDTO> list) {
        this.list = list;
    }
}
