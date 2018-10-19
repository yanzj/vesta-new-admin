package com.maxrocky.vesta.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by ZhangBailiang on 2016/1/15.
 * 物业项目公司
 */

@Entity
@Table(name = "property_company")
public class PropertyCompanyEntity {

    public final static String COMPANY__TYPE_ENABLE = "1";  // 物业公司信息状态（启用）
    public final static String COMPANY_TYPE_DISABLE = "2";  // 物业公司信息状态（禁用）

    private String companyId;        // ID
    private String companyName;      // 公司表(ID)
    private String propertyProject;  // 项目表(ID)
    private String propertyArea;     // 区域表(ID)
    private String projectAdmin;     // 项目管理员
    private String roleName;         // 角色
    private String name;             // 姓名
    private String phnoe;            // 手机号

    private Date   propertyMessageTime; // 项目公司添加时间
    private String propertyType;     // 状态(禁用或启用)
    private String optname;           //  操作人
    private Date optdate;             //  操作时间

    private String responsiblePerson;// 总负责人
    private String responsiblePhone; // 总负责人电话
    private String projectAdminPhone;// 项目管理员电话


    @Id
    @Column(name = "COMPANY_ID", unique = true, nullable = false, length = 32)
    public String getCompanyId() { return this.companyId; }

    public void setCompanyId(String companyId) { this.companyId = companyId; }

    @Column(name = "PHONE", length = 32)
    public String getPhnoe() {  return phnoe;  }

    public void setPhnoe(String phnoe) {  this.phnoe = phnoe; }

    @Column(name = "NAME", length = 32)
    public String getName() { return name;  }

    public void setName(String name) { this.name = name;  }

    @Column(name = "ROLE_NAME", length = 32)
    public String getRoleName() { return roleName; }

    public void setRoleName(String roleName) {  this.roleName = roleName;  }

    @Column(name = "COMPANY_NAME", length = 32)
    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "PROPERTY_PROJECT", length = 32)
    public String getPropertyProject() {  return propertyProject; }

    public void setPropertyProject(String propertyProject) {this.propertyProject = propertyProject; }

    @Column(name = "PROPERTY_AREA", length = 32)
    public String getPropertyArea() {  return propertyArea;  }

    public void setPropertyArea(String propertyArea) {  this.propertyArea = propertyArea;  }

    @Column(name = "RESPONSIBLE_PERSON", length = 32)
    public String getResponsiblePerson() {  return responsiblePerson; }

    public void setResponsiblePerson(String responsiblePerson) {  this.responsiblePerson = responsiblePerson; }

    @Column(name = "RESPONSIBLE_PHONE_PROJECT", length = 32)
    public String getResponsiblePhone() { return responsiblePhone;  }

    public void setResponsiblePhone(String responsiblePhone) { this.responsiblePhone = responsiblePhone; }

    @Column(name = "PROJECT_ADMIN", length = 32)
    public String getProjectAdmin() {  return projectAdmin;   }

    public void setProjectAdmin(String projectAdmin) { this.projectAdmin = projectAdmin;  }

    @Column(name = "PROJECT_ADMIN_PHONE", length = 32)
    public String getProjectAdminPhone() { return projectAdminPhone;  }

    public void setProjectAdminPhone(String projectAdminPhone) { this.projectAdminPhone = projectAdminPhone; }

    @Column(name = "OPTNAME", length = 32)
    public String getOptname() {  return this.optname;  }

    public void setOptname(String optname) { this.optname = optname;  }

    @Column(name = "OPTDATE", length = 19)
    public Date getOptdate() {  return this.optdate;  }

    public void setOptdate(Date optdate) {  this.optdate = optdate;  }

    @Column(name = "PROPERTY_MESSAGE_TIME", length = 19)
    public Date getPropertyMessageTime() {
        return propertyMessageTime;
    }

    public void setPropertyMessageTime(Date propertyMessageTime) {
        this.propertyMessageTime = propertyMessageTime;
    }

    @Column(name = "PROPERTY_TYPE", length = 19)
    public String getPropertyType() { return propertyType;  }

    public void setPropertyType(String propertyType) {  this.propertyType = propertyType;  }
}
