package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Itzxs on 2018/5/7
 */

@Entity
@Table(name = "role_staff_projectqc_map",uniqueConstraints = {@UniqueConstraint(columnNames = {"STAFF_ID","AGENCY_ID","ROLE_ID"})})
public class RoleStaffProjectMapQCEntity {
    private Long id;//自增长ID
    private String staffId;  //人员Id
    private String roleId;   //角色Id
    private String agencyId; //项目Id
    private String state;    //状态
    private Date modifyOn;   //修改时间


    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "STAFF_ID", length = 50)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "ROLE_ID", length = 50)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    @Basic
    @Column(name = "AGENCY_ID", length = 50)
    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    @Basic
    @Column(name = "STATE", length = 32)

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "MODIFY_ON")

    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
