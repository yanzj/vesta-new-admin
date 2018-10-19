package com.maxrocky.vesta.application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/5/30.
 */
public class PreInspectionList {
    private String projectid;       //项目id
    private String projectName;     //项目名称
    private String companyId;       //公司id
    private String instalment;      //几期
    private String projectNum;      //项目编码
    private List building;          //楼栋List
    public PreInspectionList(String projectNum,String projectid,String projectName,String companyId,String instalment,List building){
        this.projectid=projectid;
        this.projectName=projectName;
        this.companyId=companyId;
        this.instalment=instalment;
        this.building=building;
        this.projectNum=projectNum;
    }
    public PreInspectionList(){
        projectid="";
        projectName="";
        companyId="";
        instalment="";
        projectNum="";
        building=new ArrayList<>();
    }
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getInstalment() {
        return instalment;
    }

    public void setInstalment(String instalment) {
        this.instalment = instalment;
    }

    public List getBuilding() {
        return building;
    }

    public void setBuilding(List building) {
        this.building = building;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
}
