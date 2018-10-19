package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2017/12/18.
 * 角色功能单关联关系
 */
@Entity
@Table(name = "auth_function_point_role")
public class AuthFunctionPointRoleEntity {
    private Long id;//主键id
    private String authRoleId;//角色id
    private String authFunctionId;//功能点id
    private String control;//控制方式
    private String state;//状态 0 正常 1删除
    private Date modifyDate;//修改时间
    private String category;//类别 1.客关 2.工程 3.安全
    private String classification;//分类   1.前段app 2.管理平台
    private String modifyBy;//修改人
    @Id
    @Column(name = "ID",nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "AUTH_ROLE_ID",length = 100)
    public String getAuthRoleId() {
        return authRoleId;
    }

    public void setAuthRoleId(String authRoleId) {
        this.authRoleId = authRoleId;
    }
    @Basic
    @Column(name = "AUTH_FUNCTION_ID",length = 100)
    public String getAuthFunctionId() {
        return authFunctionId;
    }

    public void setAuthFunctionId(String authFunctionId) {
        this.authFunctionId = authFunctionId;
    }
    @Basic
    @Column(name = "CONTROL",length = 100)
    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }
    @Basic
    @Column(name = "STATE",length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "MODIFY_DATE",length = 200)

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
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
    @Column(name = "CLASSIFICATION",length = 10)
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Basic
    @Column(name = "MODIFY_BY",length = 100)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }



}
