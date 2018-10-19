package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by chen on 2016/1/27.
 * 商户收藏实体
 */

@Entity
@Table(name = "seller_collect")
public class SellerCollectEntity {
    private String collectId;   //收藏ID
    private String userId;      //用户ID
    private String sellerId;    //商户ID

    @Id
    @Column(name="COLLECT_ID",length = 32,unique = true,nullable = false)
    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    @Basic
    @Column(name="SELLER_ID",length = 32,nullable = false)
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name="USER_ID",length = 32,nullable = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
