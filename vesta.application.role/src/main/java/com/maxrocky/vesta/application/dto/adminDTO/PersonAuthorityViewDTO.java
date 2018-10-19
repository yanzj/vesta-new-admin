package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by chen on 2016/9/6.
 */
public class PersonAuthorityViewDTO {
    private String currentId="";
    private String name="";
    private String parentId="";
    private String graded="";
    private String timeStamp="";
    private String roleGroup="";
    private String start="";
    private String unionCode="";

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUnionCode() {
        return unionCode;
    }

    public void setUnionCode(String unionCode) {
        this.unionCode = unionCode;
    }
}
