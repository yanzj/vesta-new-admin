package com.maxrocky.vesta.application.ProjectMaterial.DTO;

/**
 * Created by Magic on 2016/12/6.
 */
public class SearchMaterialDTO {
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String classifyOne;//一级分类
    private String classifyOneName;//一级分类名称
    private int materialAll;//全部
    private int materialOk;//合格
    private int materialNo;//不合格
    private int materialOut;//退场
    public SearchMaterialDTO(){
        this.projectId="";
        this.projectName="";
        this.materialAll=0;
        this.materialOk=0;
        this.materialNo=0;
        this.materialOut=0;
    }
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getMaterialAll() {
        return materialAll;
    }

    public void setMaterialAll(int materialAll) {
        this.materialAll = materialAll;
    }

    public int getMaterialOk() {
        return materialOk;
    }

    public void setMaterialOk(int materialOk) {
        this.materialOk = materialOk;
    }

    public int getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(int materialNo) {
        this.materialNo = materialNo;
    }

    public int getMaterialOut() {
        return materialOut;
    }

    public void setMaterialOut(int materialOut) {
        this.materialOut = materialOut;
    }

    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    public String getClassifyOneName() {
        return classifyOneName;
    }

    public void setClassifyOneName(String classifyOneName) {
        this.classifyOneName = classifyOneName;
    }
}
