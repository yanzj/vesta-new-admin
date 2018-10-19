package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chen on 2016/4/1.
 * 优惠券
 */
@Entity
@Table(name = "coupon_voucher")
public class VoucherEntity {
    private String id;                         //主键
    private String voucherCode;                //券码
    private Integer number;                    //数量
    private String title;                      //标题
    private String content;                    //内容
    private String logo;                       //图片
    private String status;                     //状态 1未开始 2进行中 3结束 4已过期
    private String type;                       //类别
    private String crtBy;                      //创建人
    private Date crtDate;                      //创建时间
    private Date beginTime;                    //有效期起始时间
    private Date endTime;                      //有效期结束时间
    private BigDecimal money;                  //优惠金额
    private BigDecimal fullMoney;              //条件金额
    private String mallId;                     //商户ID
    private String commodityId;                //商品ID

    @Basic
    @Column(name = "BEGIN_TIME")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "COMMODITY_ID",length = 32)
    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    @Basic
    @Column(name = "CONTENT",length = 10000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "CREATE_BY",length = 20)
    public String getCrtBy() {
        return crtBy;
    }

    public void setCrtBy(String crtBy) {
        this.crtBy = crtBy;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    @Basic
    @Column(name = "END_TIME")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "FULL_MONEY")
    public BigDecimal getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(BigDecimal fullMoney) {
        this.fullMoney = fullMoney;
    }

    @Id
    @Column(name = "ID",length = 32,unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "MALL_ID",length = 32)
    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    @Basic
    @Column(name = "MONEY")
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Basic
    @Column(name = "NUMBER")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "STATUS",length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "TITLE",length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "TYPE",length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "VOUCHER_CODE",length = 32)
    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    @Basic
    @Column(name = "VOUCHER_LOGO",length = 60)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
