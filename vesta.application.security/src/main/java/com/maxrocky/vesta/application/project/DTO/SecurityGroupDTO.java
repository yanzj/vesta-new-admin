package com.maxrocky.vesta.application.project.DTO;

/**
 * 安全集团返回数据
 * Created by Jason on 2017/6/5.
 */
public class SecurityGroupDTO {
    private String groupId;//集团ID
    private String groupName;//集团名称
    private String creatDate;//创建时间
    private String state;//状态
    private String groupDept;//集团部门
    private String groupStaff;//集团人员

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGroupDept() {
        return groupDept;
    }

    public void setGroupDept(String groupDept) {
        this.groupDept = groupDept;
    }

    public String getGroupStaff() {
        return groupStaff;
    }

    public void setGroupStaff(String groupStaff) {
        this.groupStaff = groupStaff;
    }
}
