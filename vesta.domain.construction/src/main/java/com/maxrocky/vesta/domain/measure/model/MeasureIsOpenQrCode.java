package com.maxrocky.vesta.domain.measure.model;

import javax.persistence.*;

/**
 * Created by Itzxs on 2018/7/24.
 */
@Entity
@Table(name = "measure_isopen_qrcode")
public class MeasureIsOpenQrCode {
    private String id;
    private String isOpenQrCode;//0 公开 1 不公开、
    private String createBy;//修改人ID
    private String modifyDate;//修改时间

    @Id
    @Column(name = "ID", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ISOPEN_QRCODE", length = 10,nullable = true, insertable = true, updatable = true)
    public String getIsOpenQrCode() {
        return isOpenQrCode;
    }

    public void setIsOpenQrCode(String isOpenQrCode) {
        this.isOpenQrCode = isOpenQrCode;
    }

    @Basic
    @Column(name = "CREATE_BY", length = 50,nullable = true, insertable = true, updatable = true)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "MODIFY_DATE", length = 50,nullable = true, insertable = true, updatable = true)
    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
