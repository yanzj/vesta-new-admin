package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by JillChen on 2016/1/4.
 */
@Entity
@Table(name = "role_viewmodel")
public class RoleViewmodelEntity {
    private String menuId;
    private String childFlag;
    private String menuDescription;
    private String menuName;
    private String menuState;
    private String menulevel;
    private String menuorder;
    private String operator;
    private String parantmenuid;
    private String runscript;
    private String owner;
    private String belong;  //1会员 、 2质检 3待定

    @Id
    @Column(name = "MenuId" ,length = 50)
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "ChildFlag",length = 50)
    public String getChildFlag() {
        return childFlag;
    }

    public void setChildFlag(String childFlag) {
        this.childFlag = childFlag;
    }

    @Basic
    @Column(name = "MenuDescription",length = 50)
    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    @Basic
    @Column(name = "MenuName",length = 50)
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "MenuState",length = 50)
    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    @Basic
    @Column(name = "Menulevel",length = 50)
    public String getMenulevel() {
        return menulevel;
    }

    public void setMenulevel(String menulevel) {
        this.menulevel = menulevel;
    }

    @Basic
    @Column(name = "Menuorder",length = 50)
    public String getMenuorder() {
        return menuorder;
    }

    public void setMenuorder(String menuorder) {
        this.menuorder = menuorder;
    }

    @Basic
    @Column(name = "Operator",length = 50)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "Parantmenuid",length = 50)
    public String getParantmenuid() {
        return parantmenuid;
    }

    public void setParantmenuid(String parantmenuid) {
        this.parantmenuid = parantmenuid;
    }

    @Basic
    @Column(name = "Runscript",length = 50)
    public String getRunscript() {
        return runscript;
    }

    public void setRunscript(String runscript) {
        this.runscript = runscript;
    }

    @Basic
    @Column(name = "Owner",length = 50)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "BELONG",length = 30)
    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleViewmodelEntity that = (RoleViewmodelEntity) o;

        if (menuId != null ? !menuId.equals(that.menuId) : that.menuId != null) return false;
        if (childFlag != null ? !childFlag.equals(that.childFlag) : that.childFlag != null) return false;
        if (menuDescription != null ? !menuDescription.equals(that.menuDescription) : that.menuDescription != null)
            return false;
        if (menuName != null ? !menuName.equals(that.menuName) : that.menuName != null) return false;
        if (menuState != null ? !menuState.equals(that.menuState) : that.menuState != null) return false;
        if (menulevel != null ? !menulevel.equals(that.menulevel) : that.menulevel != null) return false;
        if (menuorder != null ? !menuorder.equals(that.menuorder) : that.menuorder != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (parantmenuid != null ? !parantmenuid.equals(that.parantmenuid) : that.parantmenuid != null) return false;
        if (runscript != null ? !runscript.equals(that.runscript) : that.runscript != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = menuId != null ? menuId.hashCode() : 0;
        result = 31 * result + (childFlag != null ? childFlag.hashCode() : 0);
        result = 31 * result + (menuDescription != null ? menuDescription.hashCode() : 0);
        result = 31 * result + (menuName != null ? menuName.hashCode() : 0);
        result = 31 * result + (menuState != null ? menuState.hashCode() : 0);
        result = 31 * result + (menulevel != null ? menulevel.hashCode() : 0);
        result = 31 * result + (menuorder != null ? menuorder.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (parantmenuid != null ? parantmenuid.hashCode() : 0);
        result = 31 * result + (runscript != null ? runscript.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
