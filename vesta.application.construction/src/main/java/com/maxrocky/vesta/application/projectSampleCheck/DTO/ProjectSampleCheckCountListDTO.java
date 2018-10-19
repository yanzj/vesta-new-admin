package com.maxrocky.vesta.application.projectSampleCheck.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/1/16.
 */
public class ProjectSampleCheckCountListDTO {
    private String projectId;
    private String total;//总数
    private String qualified;//合格率
    private String unqualified;//不合格率
    private List<ProjectSampleCheckCountDTO> list;
    public ProjectSampleCheckCountListDTO(){
        this.total="";
        this.qualified="";
        this.projectId="";
        this.unqualified="";
        this.list=new ArrayList<ProjectSampleCheckCountDTO>();
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getQualified() {
        return qualified;
    }

    public void setQualified(String qualified) {
        this.qualified = qualified;
    }

    public String getUnqualified() {
        return unqualified;
    }

    public void setUnqualified(String unqualified) {
        this.unqualified = unqualified;
    }

    public List<ProjectSampleCheckCountDTO> getList() {
        return list;
    }

    public void setList(List<ProjectSampleCheckCountDTO> list) {
        this.list = list;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
