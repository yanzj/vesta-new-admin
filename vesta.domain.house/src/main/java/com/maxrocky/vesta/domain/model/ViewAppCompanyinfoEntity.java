package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ZhangBailiang on 2016/1/28.
 */
@Entity
@Table(name = "view_app_companyinfo")
public class ViewAppCompanyinfoEntity {

    private int companyid;//公司ID
    private String companyname;//公司名称
    private String parentCompany;//所属分公司
    private String areaName;//所属区域

    @Id
    @Column(name = "CompanyId", nullable = false, length = 38)
    public int getCompanyid() {  return companyid;  }

    public void setCompanyid(int companyid) {  this.companyid = companyid;  }

    @Basic
    @Column(name = "CompanyName", length = 300)
    public String getCompanyname() {  return companyname; }

    public void setCompanyname(String companyname) { this.companyname = companyname;  }

    @Basic
    @Column(name = "ParentCompany", length = 300)
    public String getParentCompany() { return parentCompany;  }

    public void setParentCompany(String parentCompany) { this.parentCompany = parentCompany;  }

    @Basic
    @Column(name = "AreaName", length = 300)
    public String getAreaName() {  return areaName;  }

    public void setAreaName(String areaName) { this.areaName = areaName;  }

}
