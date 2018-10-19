package com.maxrocky.vesta.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by liuwei on 2016/1/27.
 */


//@Entity
//@Table(name = "view_paymentid_OwnerID" )
public class ViewPaymentidOwnerID {

    @Id
    @Column
    private String id;
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "owner_id")
    private String ownerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
