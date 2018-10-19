package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.List;

/**
 * Created by Magic on 2016/5/30.
 */
public class AraeListDTO {
    private String araeId;      //区域id
    private String araeName;    //区域名称
    private String araeNum;     //区域编码
    private List buildingList;//楼栋list
    public AraeListDTO(String araeId,String araeName,String araeNum,List buildingList){
        this.araeId=araeId;
        this.araeName=araeName;
        this.araeNum=araeNum;
        this.buildingList=buildingList;
    }
    public AraeListDTO(){
        araeId="";
        araeName="";
        araeNum="";
        buildingList=null;
    }
    public String getAraeId() {
        return araeId;
    }

    public void setAraeId(String araeId) {
        this.araeId = araeId;
    }

    public String getAraeName() {
        return araeName;
    }

    public void setAraeName(String araeName) {
        this.araeName = araeName;
    }

    public String getAraeNum() {
        return araeNum;
    }

    public void setAraeNum(String araeNum) {
        this.araeNum = araeNum;
    }


    public List getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List buildingList) {
        this.buildingList = buildingList;
    }
}
