package com.maxrocky.vesta.taglib.vesta;

/**
 * Created by JillChen on 2015/12/17.
 */

import javax.persistence.*;
import java.io.Serializable;
public class Viewmodel implements Serializable {
    private String menuId;
    private String menuName;
    private String menuDescription;
    private String menuState;  //01 可用  02 不可用
    private String operator;
    private String runscript;  //運行腳本
    private String menulevel;  //菜單級別  一級、二級、三級等
    private String parantmenuid;  //父級菜單id
    private String childFlag;   //是否有子菜單  Y 是  N 否
    private String menuorder;   //菜單順序
    private String owner ;      //所属系统
    private String belong;      //所属模块

    @Id
    @Column(name = "MenuId", nullable = false, insertable = true, updatable = true, length = 30)
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }


    @Basic
    @Column(name = "Owner", nullable = true, insertable = true, updatable = true, length = 30)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "MenuName", nullable = true, insertable = true, updatable = true, length = 30)
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "MenuDescription", nullable = true, insertable = true, updatable = true, length = 50)
    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    @Basic
    @Column(name = "MenuState", nullable = true, insertable = true, updatable = true, length = 2)
    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    @Basic
    @Column(name = "Operator", nullable = true, insertable = true, updatable = true, length = 30)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "Runscript", nullable = true, insertable = true, updatable = true, length = 200)
    public String getRunscript() {
        return runscript;
    }

    public void setRunscript(String runscript) {
        this.runscript = runscript;
    }

    @Basic
    @Column(name = "Menulevel", nullable = true, insertable = true, updatable = true, length = 20)
    public String getMenulevel() {
        return menulevel;
    }

    public void setMenulevel(String menulevel) {
        this.menulevel = menulevel;
    }

    @Basic
    @Column(name = "Parantmenuid", nullable = true, insertable = true, updatable = true, length = 30)
    public String getParantmenuid() {
        return parantmenuid;
    }

    public void setParantmenuid(String parantmenuid) {
        this.parantmenuid = parantmenuid;
    }

    @Basic
    @Column(name = "ChildFlag", nullable = true, insertable = true, updatable = true, length = 2)
    public String getChildFlag() {
        return childFlag;
    }

    public void setChildFlag(String childFlag) {
        this.childFlag = childFlag;
    }

    @Basic
    @Column(name = "Menuorder", nullable = true, insertable = true, updatable = true, length = 4)
    public String getMenuorder() {
        return menuorder;
    }

    public void setMenuorder(String menuorder) {
        this.menuorder = menuorder;
    }

    @Basic
    @Column(name = "BELONG",nullable = true, insertable = true, updatable = true, length = 30)
    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }
}
