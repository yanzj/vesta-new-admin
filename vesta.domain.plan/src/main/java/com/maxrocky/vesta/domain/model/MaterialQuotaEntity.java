package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by chen on 2016/5/21.
 * 材料验收 检查指标实体
 */
@Entity
@Table(name = "material_quota")
public class MaterialQuotaEntity {
    private String id;           //主键
    private String quotaDesc;    //指标说明
    private String qualified;    //是否合格 0不合格 1合格
    private String materialId;   //材料验收ID

    @Id
    @Column(name = "ID",length = 60,unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MATERIAL_ID",length = 60,nullable = false)
    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    @Basic
    @Column(name = "QUALIFIED",length = 2)
    public String getQualified() {
        return qualified;
    }

    public void setQualified(String qualified) {
        this.qualified = qualified;
    }

    @Basic
    @Column(name = "QUOTA_DESC",length = 300)
    public String getQuotaDesc() {
        return quotaDesc;
    }

    public void setQuotaDesc(String quotaDesc) {
        this.quotaDesc = quotaDesc;
    }
}
