package com.maxrocky.vesta.application.baseData.adminDTO;

import java.util.List;

/**
 * Created by maxrocky on 2017/8/29.
 */
public class ProjectStaffRelationDTO {
    private String id;
    private String name;
    private String modifyDate;//最后修改时间
    private List<ProjectRoleDTO> groupProject;//关联的项目
    private List<ProjectRoleDTO> groupStaff;//关联的人
    private List<ProjectRoleDTO> groupArea;//关联的区域
    private List<ProjectRoleDTO> groupDepartment;//关联的部门


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public List<ProjectRoleDTO> getGroupProject() {
        return groupProject;
    }

    public void setGroupProject(List<ProjectRoleDTO> groupProject) {
        this.groupProject = groupProject;
    }

    public List<ProjectRoleDTO> getGroupStaff() {
        return groupStaff;
    }

    public void setGroupStaff(List<ProjectRoleDTO> groupStaff) {
        this.groupStaff = groupStaff;
    }

    public List<ProjectRoleDTO> getGroupArea() {
        return groupArea;
    }

    public void setGroupArea(List<ProjectRoleDTO> groupArea) {
        this.groupArea = groupArea;
    }

    public List<ProjectRoleDTO> getGroupDepartment() {
        return groupDepartment;
    }

    public void setGroupDepartment(List<ProjectRoleDTO> groupDepartment) {
        this.groupDepartment = groupDepartment;
    }
}
