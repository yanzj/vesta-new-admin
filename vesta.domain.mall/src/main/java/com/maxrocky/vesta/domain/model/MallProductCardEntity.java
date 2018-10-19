package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/5.
 */
@Entity
@Table(name = "mall_card")
public class MallProductCardEntity {

    @Id
    @Column(name = "card_id", length = 32)
    private String cardId;//ID

    @Basic
    @Column(name = "code",length = 50)
    private String code;//编码

    @Basic
    @Column(name = "pwd",length = 50)
    private String pwd; //密码

    @Basic
    @Column(name = "user_id",length = 255)
    private String userId; //用户ID

    @Basic
    @Column(name = "mall_id",length = 255)
    private String mallId; //商品ID


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    private Date createOn; //创建日期

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
