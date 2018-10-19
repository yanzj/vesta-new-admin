package com.maxrocky.vesta.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by liuwei on 2016/1/31.
 */

//@Entity
//@Table(name = "AR_BillItem")
public class ARBillItem {

    @Id
    @Column
    private String id;

    @Column(name = "BillItemID")
    private String BillItemID;

    @Column(name = "ItemName")
    private String ItemName;

    public String getBillItemID() {
        return BillItemID;
    }

    public void setBillItemID(String billItemID) {
        BillItemID = billItemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
