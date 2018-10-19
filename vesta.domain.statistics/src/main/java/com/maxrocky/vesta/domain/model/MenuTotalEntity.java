package com.maxrocky.vesta.domain.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by liudongxin on 2016/6/4.
 * 菜单点击次数统计
 */
@Entity
@javax.persistence.Table(name = "menu_total")
public class MenuTotalEntity {
    private String id;
    private Date createDate;//创建时间
    private String moduleName;//模块名称

    @Id
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @javax.persistence.Column(name = "MODULE_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }




}
