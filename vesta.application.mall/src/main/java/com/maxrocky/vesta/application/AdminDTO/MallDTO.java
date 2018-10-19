package com.maxrocky.vesta.application.AdminDTO;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分商城 DTO
 * Created by WeiYangDong on 2017/7/10.
 */
public class MallDTO {
    private String menuId;      //菜单ID
    private String modifyBy;    //操作人

    private String productTypeId;//商品种类ID
    private String productTypeName;//商品种类名称
    private Integer productTypeState;//商品类型状态(0,禁用;1,启用)

    private String productId;//商品ID
    private String productNum;//商品编码
    private String productName;//商品名称
    private BigDecimal productPrice;//商品价格
    private Integer productIntegral;//商品积分
    private String productBusiness;//所属商家
    private String productSpec;//商品规格
    private Integer productStock;//商品库存
    private MultipartFile productPicFile;//商品导图文件上传
    private String productPicUrl;//商品导图
    private String productDetails;//商品详情
    private Integer productState;//商品状态(0,已下架;1,已上架)
    private Integer clientId;//商品所属客户端ID
    private String clientName;//客户端名称

    private Integer isCardCoupons;//是否为卡券(0,否;1,是)
    private String cardCouponsPassword;//卡券密码
    private String cardCouponsTermStr;//有效期限(字符串)
    private Date cardCouponsTerm;//有效期限
    private MultipartFile cardCouponsQRCodeFile;//卡券二维码文件上传
    private String cardCouponsQRCode;//卡券二维码

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Integer getProductTypeState() {
        return productTypeState;
    }

    public void setProductTypeState(Integer productTypeState) {
        this.productTypeState = productTypeState;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductIntegral() {
        return productIntegral;
    }

    public void setProductIntegral(Integer productIntegral) {
        this.productIntegral = productIntegral;
    }

    public String getProductBusiness() {
        return productBusiness;
    }

    public void setProductBusiness(String productBusiness) {
        this.productBusiness = productBusiness;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductPicUrl() {
        return productPicUrl;
    }

    public void setProductPicUrl(String productPicUrl) {
        this.productPicUrl = productPicUrl;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public MultipartFile getProductPicFile() {
        return productPicFile;
    }

    public void setProductPicFile(MultipartFile productPicFile) {
        this.productPicFile = productPicFile;
    }

    public Integer getIsCardCoupons() {
        return isCardCoupons;
    }

    public void setIsCardCoupons(Integer isCardCoupons) {
        this.isCardCoupons = isCardCoupons;
    }

    public String getCardCouponsPassword() {
        return cardCouponsPassword;
    }

    public void setCardCouponsPassword(String cardCouponsPassword) {
        this.cardCouponsPassword = cardCouponsPassword;
    }

    public Date getCardCouponsTerm() {
        return cardCouponsTerm;
    }

    public void setCardCouponsTerm(Date cardCouponsTerm) {
        this.cardCouponsTerm = cardCouponsTerm;
    }

    public String getCardCouponsTermStr() {
        return cardCouponsTermStr;
    }

    public void setCardCouponsTermStr(String cardCouponsTermStr) {
        this.cardCouponsTermStr = cardCouponsTermStr;
    }

    public MultipartFile getCardCouponsQRCodeFile() {
        return cardCouponsQRCodeFile;
    }

    public void setCardCouponsQRCodeFile(MultipartFile cardCouponsQRCodeFile) {
        this.cardCouponsQRCodeFile = cardCouponsQRCodeFile;
    }

    public String getCardCouponsQRCode() {
        return cardCouponsQRCode;
    }

    public void setCardCouponsQRCode(String cardCouponsQRCode) {
        this.cardCouponsQRCode = cardCouponsQRCode;
    }
}
