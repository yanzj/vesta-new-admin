package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/2/18.
 * 工单通讯录信息(员工端)/多单分配(员工端)
 * ModifyBy:liudongxin
 * 修改：增加图片路径
 */
public class WorkOrderTelBookDTO {
    private String src;//图片路径
    private List<WorkOrderDepartmentDTO> groupList;//部门list
    //分配部分
    private String userId;//任务人id(用户id)
    private List<WorkApportionDTO> apportionList;

    public WorkOrderTelBookDTO() {
        this.userId="";
        this.groupList = new ArrayList<WorkOrderDepartmentDTO>();
        this.apportionList=new ArrayList<WorkApportionDTO>();
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<WorkOrderDepartmentDTO> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<WorkOrderDepartmentDTO> groupList) {
        this.groupList = groupList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<WorkApportionDTO> getApportionList() {
        return apportionList;
    }

    public void setApportionList(List<WorkApportionDTO> apportionList) {
        this.apportionList = apportionList;
    }
}
