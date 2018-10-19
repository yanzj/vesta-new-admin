package com.maxrocky.vesta.application.DTO;

import java.util.List;

/**
 * Created by liudongxin on 2016/2/18.
 * 工单通讯录部门信息(员工端)
 */
public class WorkOrderDepartmentDTO {
    private String groupName;//部门名称
    private String src;//图片路径
    private List<WorkOrderUserDTO> memberList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<WorkOrderUserDTO> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<WorkOrderUserDTO> memberList) {
        this.memberList = memberList;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
