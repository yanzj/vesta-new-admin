package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/1/14.
 * 商户实体
 */
@Entity
@Table(name = "shop_seller")
public class ShopSellerEntity {
    public final static String STATUS_VALID = "1";    //禁用
    public final static String STATUS_INVALID = "2";  //启用

    private String sellerId;                  //商户ID
    private String sellerName;                //商户名称
    private String sellerStatus;              //商户状态
    private String sellerDsc;                 //商户简介
    private String sellerLevel;               //商户星级
    private String sellerTel;                 //商户电话
    private String sellerAddress;             //商户地址
    private String sellerDirector;            //商户负责人
    private String SellerDirectorId;          //商户负责人ID
    private String SellerDirectorTel;         //商户负责人电话
    private Date beginBussiness;              //商户开始营业时间
    private Date endBussiness;                //商户营业结束时间
    private Date sellerCreate;                //商户创建时间
    private Date sellerExpire;                //商户到期时间
    private Date sellerModify;                //商户修改时间
    private String sellerLogo;                //商户Logo
    private String sellerType;                //商户类型
    private String sellerRecommend;           //商户推荐商品
    private String sellerLabel;               //商户标签
    private String sellerBrand;               //商户品牌
    private String sellerTariffLine;          //商户税号
    private Integer sellerSort;               //商户排序
    private String sellerMainBussiness;       //商户主营
    private String range;                     //距离
    private String projectId;                 //项目ID

    @Basic
    @Column(name="BEGIN_BUSSINESS",length = 20)
    public Date getBeginBussiness() {
        return beginBussiness;
    }

    public void setBeginBussiness(Date beginBussiness) {
        this.beginBussiness = beginBussiness;
    }

    @Basic
    @Column(name="END_BUSSINESS",length = 20)
    public Date getEndBussiness() {
        return endBussiness;
    }

    public void setEndBussiness(Date endBussiness) {
        this.endBussiness = endBussiness;
    }

    @Basic
    @Column(name="SELLER_ADDRESS",length = 50)
    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    @Basic
    @Column(name="SELLER_BRAND",length = 50)
    public String getSellerBrand() {
        return sellerBrand;
    }

    public void setSellerBrand(String sellerBrand) {
        this.sellerBrand = sellerBrand;
    }

    @Basic
    @Column(name="SELLER_CREATE",length = 20)
    public Date getSellerCreate() {
        return sellerCreate;
    }

    public void setSellerCreate(Date sellerCreate) {
        this.sellerCreate = sellerCreate;
    }

    @Basic
    @Column(name="SELLER_DIRECTOR",length = 30)
    public String getSellerDirector() {
        return sellerDirector;
    }

    public void setSellerDirector(String sellerDirector) {
        this.sellerDirector = sellerDirector;
    }

    @Basic
    @Column(name="SELLER_DIRECTOR_ID",length = 32)
    public String getSellerDirectorId() {
        return SellerDirectorId;
    }

    public void setSellerDirectorId(String sellerDirectorId) {
        SellerDirectorId = sellerDirectorId;
    }

    @Basic
    @Column(name="SELLER_DIRECTOR_TEL",length =13 )
    public String getSellerDirectorTel() {
        return SellerDirectorTel;
    }

    public void setSellerDirectorTel(String sellerDirectorTel) {
        SellerDirectorTel = sellerDirectorTel;
    }

    @Basic
    @Column(name="SELLER_DSC",length = 100)
    public String getSellerDsc() {
        return sellerDsc;
    }

    public void setSellerDsc(String sellerDsc) {
        this.sellerDsc = sellerDsc;
    }

    @Basic
    @Column(name="SELLER_EXPIRE",length = 20)
    public Date getSellerExpire() {
        return sellerExpire;
    }

    public void setSellerExpire(Date sellerExpire) {
        this.sellerExpire = sellerExpire;
    }

    @Id
    @Column(name="SELLER_ID",length = 32,unique = true,nullable = false)
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name="SELLER_LABEL",length = 20)
    public String getSellerLabel() {
        return sellerLabel;
    }

    public void setSellerLabel(String sellerLabel) {
        this.sellerLabel = sellerLabel;
    }

    @Basic
    @Column(name="SELLER_LEVEL",length = 10)
    public String getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(String sellerLevel) {
        this.sellerLevel = sellerLevel;
    }

    @Basic
    @Column(name="SELLER_LOGO",length = 300)
    public String getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    @Basic
    @Column(name="SELLER_MAINBUSSINESS",length =30 )
    public String getSellerMainBussiness() {
        return sellerMainBussiness;
    }

    public void setSellerMainBussiness(String sellerMainBussiness) {
        this.sellerMainBussiness = sellerMainBussiness;
    }

    @Basic
    @Column(name="SELLER_MODIFY",length = 25)
    public Date getSellerModify() {
        return sellerModify;
    }

    public void setSellerModify(Date sellerModify) {
        this.sellerModify = sellerModify;
    }

    @Basic
    @Column(name="SELLER_NAME",length = 20,nullable = false)
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Basic
    @Column(name="SELLER_RECOMMEND",length = 50)
    public String getSellerRecommend() {
        return sellerRecommend;
    }

    public void setSellerRecommend(String sellerRecommend) {
        this.sellerRecommend = sellerRecommend;
    }

    @Basic
    @Column(name="SELLER_SORT",length = 32)
    public Integer getSellerSort() {
        return sellerSort;
    }

    public void setSellerSort(Integer sellerSort) {
        this.sellerSort = sellerSort;
    }

    @Basic
    @Column(name="SELLER_STATUS",length = 3,nullable = false)
    public String getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(String sellerStatus) {
        this.sellerStatus = sellerStatus;
    }

    @Basic
    @Column(name="SELLER_TARIFFLINE",length = 32)
    public String getSellerTariffLine() {
        return sellerTariffLine;
    }

    public void setSellerTariffLine(String sellerTariffLine) {
        this.sellerTariffLine = sellerTariffLine;
    }

    @Basic
    @Column(name="SELLER_TEL",length = 13)
    public String getSellerTel() {
        return sellerTel;
    }

    public void setSellerTel(String sellerTel) {
        this.sellerTel = sellerTel;
    }

    @Basic
    @Column(name="SELLER_TYPE",length = 25)
    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    @Basic
    @Column(name="SELLER_RANGE",length=30)
    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 32)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
