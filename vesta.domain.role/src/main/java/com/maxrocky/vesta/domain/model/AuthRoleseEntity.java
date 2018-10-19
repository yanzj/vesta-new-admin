package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2017/12/11.
 * 人员角色
 */
@Entity
@Table(name = "auth_role_roleset")
public class AuthRoleseEntity {
    private String roleId;            //角色id
    private String prefix;            //角色前缀
    private String roleName;        //角色描述
    private Date createOn;          //创建时间
    private String createBy;            //创建人
    private Date modifyOn;          //修改时间
    private String modifyBy;        //修改人
    private String roleType;        // 角色类型
    private String roleNature;       //角色性质 级别
    private String state;           //0 启用  1删除
    private String category;//类别 1.客关 2.工程 3.安全
    @Id
    @Column(name = "ROLE_ID",unique = true,nullable = false,length = 50)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    @Basic
    @Column(name = "ROLE_NAME",length = 100)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "ROLE_TYPE",length = 30)
    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Basic
    @Column(name = "STATE",length = 30)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "ROLE_NATURE",length = 30)
    public String getRoleNature() {
        return roleNature;
    }

    public void setRoleNature(String roleNature) {
        this.roleNature = roleNature;
    }
    @Basic
    @Column(name = "CREATEON",length = 30)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "CREATEBY",length = 100)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "MODIFYON",length = 30)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
    @Basic
    @Column(name = "MODIFYBY",length = 100)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
    @Basic
    @Column(name = "CATEGORY",length = 10)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Basic
    @Column(name = "PREFIX",length = 100)
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
