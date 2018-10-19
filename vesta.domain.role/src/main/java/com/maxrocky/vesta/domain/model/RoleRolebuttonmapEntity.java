package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by JillChen on 2016/1/4.
 */
@Entity
@Table(name = "role_rolebuttonmap")
@IdClass(RoleRolebuttonmapEntityPK.class)
public class RoleRolebuttonmapEntity {
    private String roleId;
    private String menuId;
    private String buttonId;  //质检APP暂时将此字段作为角色ID
    private String buttonState;
    private Date makeDate;
    private Time makeTime;

    @Id
    @Column(name = "RoleId", length = 32, nullable = false)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "MenuId", length = 32, nullable = false)
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Id
    @Column(name = "ButtonId", length = 32, nullable = false)
    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    @Basic
    @Column(name = "ButtonState", length = 32)
    public String getButtonState() {
        return buttonState;
    }

    public void setButtonState(String buttonState) {
        this.buttonState = buttonState;
    }

    @Basic
    @Column(name = "MakeDate", length = 50)
    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    @Basic
    @Column(name = "MakeTime", length = 50)
    public Time getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Time makeTime) {
        this.makeTime = makeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleRolebuttonmapEntity that = (RoleRolebuttonmapEntity) o;

        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (menuId != null ? !menuId.equals(that.menuId) : that.menuId != null) return false;
        if (buttonId != null ? !buttonId.equals(that.buttonId) : that.buttonId != null) return false;
        if (buttonState != null ? !buttonState.equals(that.buttonState) : that.buttonState != null) return false;
        if (makeDate != null ? !makeDate.equals(that.makeDate) : that.makeDate != null) return false;
        if (makeTime != null ? !makeTime.equals(that.makeTime) : that.makeTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (menuId != null ? menuId.hashCode() : 0);
        result = 31 * result + (buttonId != null ? buttonId.hashCode() : 0);
        result = 31 * result + (buttonState != null ? buttonState.hashCode() : 0);
        result = 31 * result + (makeDate != null ? makeDate.hashCode() : 0);
        result = 31 * result + (makeTime != null ? makeTime.hashCode() : 0);
        return result;
    }
}
