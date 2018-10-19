package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分商城_商品表
 * Created by WeiYangDong on 2017/7/17.
 */
@Entity
@Table(name = "mall_product")
public class MallProductEntity {
    private String productId;//商品ID
    private String productNum;//商品编码
    private String productName;//商品名称
    private String productTypeId;//商品类目ID
    private BigDecimal productPrice;//商品价格(商品应付)
    private Integer productIntegral;//商品积分
    private String productBusiness;//所属商家
    private String productSpec;//商品规格
    private Integer productStock;//商品库存
    private String productPicUrl;//商品导图
    private String productDetails;//商品详情
    private Integer productState;//商品状态(0,已下架;1,已上架)

    private Integer isCardCoupons;//是否为卡券(0,否;1,是)
    private String cardCouponsPassword;//卡券密码
    private Date cardCouponsTerm;//有效期限
    private String cardCouponsQRCode;//卡券二维码

    private Integer clientId;//商品所属客户端ID
    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "product_id", length = 32)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "product_num",length = 20)
    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    @Basic
    @Column(name = "product_name",length = 50)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "product_type_id",length = 32)
    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    @Basic
    @Column(name = "product_price",precision = 10, scale = 2)
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "product_integral",length = 5)
    public Integer getProductIntegral() {
        return productIntegral;
    }

    public void setProductIntegral(Integer productIntegral) {
        this.productIntegral = productIntegral;
    }

    @Basic
    @Column(name = "product_business",length = 100)
    public String getProductBusiness() {
        return productBusiness;
    }

    public void setProductBusiness(String productBusiness) {
        this.productBusiness = productBusiness;
    }

    @Basic
    @Column(name = "product_spec",length = 200)
    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    @Basic
    @Column(name = "product_stock",length = 5)
    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    @Basic
    @Column(name = "product_pic_url",length = 200)
    public String getProductPicUrl() {
        return productPicUrl;
    }

    public void setProductPicUrl(String productPicUrl) {
        this.productPicUrl = productPicUrl;
    }

    @Basic
    @Column(name = "product_details",length = 16777216)
    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    @Basic
    @Column(name = "product_state",length = 1)
    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    @Basic
    @Column(name = "is_cardCoupons",length = 1)
    public Integer getIsCardCoupons() {
        return isCardCoupons;
    }

    public void setIsCardCoupons(Integer isCardCoupons) {
        this.isCardCoupons = isCardCoupons;
    }

    @Basic
    @Column(name = "cardCoupons_password",length = 50)
    public String getCardCouponsPassword() {
        return cardCouponsPassword;
    }

    public void setCardCouponsPassword(String cardCouponsPassword) {
        this.cardCouponsPassword = cardCouponsPassword;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cardCoupons_term", nullable = true, length = 50)
    public Date getCardCouponsTerm() {
        return cardCouponsTerm;
    }

    public void setCardCouponsTerm(Date cardCouponsTerm) {
        this.cardCouponsTerm = cardCouponsTerm;
    }

    @Basic
    @Column(name = "cardCoupons_QRCode",length = 200)
    public String getCardCouponsQRCode() {
        return cardCouponsQRCode;
    }

    public void setCardCouponsQRCode(String cardCouponsQRCode) {
        this.cardCouponsQRCode = cardCouponsQRCode;
    }

    @Basic
    @Column(name = "client_id",length = 2)
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
