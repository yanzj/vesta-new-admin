package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yifan on 2016/4/1.
 */
@Entity
@Table(name = "community_overview")
public class CommunityOverviewEntity implements Serializable {

    private static final long serialVersionUID = -7262475735937604157L;

    private String id; // 社区ID
    private String name; // 楼盘名称
    private String address;// 项目地址
    private String introduction;// 项目介绍
    private String periphery;// 配套设施描述
    private String panoramaImg; // 全景图
    private String favorable; // 优惠信息
    private String priceAverage; // 均价
    private String phone; // 电话
    private Integer status;//樓盤是否在售
    private String types; // 标注【科技住宅，d公园地产】
    private String city;//所属城市
    private Date createDate;      //创建时间
    private String createPerson;     //创建人
    private Date operatorDate;    //操作时间
    private String operator;        //操作人
    private Integer orderNum;   //排序字段
    private String releasePerson;   //发布人
    private Date releaseDate;   //发布日期
    private Integer releaseStatus;          //发布状态  0，未发布，1，已经发布
    private String url;     //h5_url 金茂制作
    private Date orderDate; //排序时间


    // 社区对社区位置信息，一对一
    //private CommunityLocationEntity location;
    private String locationId;

    // 社区对社区详情表，一对一
    // private CommunityDetailEntity detail;
    private String detailId;


    // 社区对户型，一对多
    //private Set<CommunityHouseTypeEntity> houseTypes = new HashSet<CommunityHouseTypeEntity>();
    private String houseTypeId;

    @Id
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name",length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address",length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "introduction",length = 500)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "periphery",length = 500)
    public String getPeriphery() {
        return periphery;
    }

    public void setPeriphery(String periphery) {
        this.periphery = periphery;
    }

    @Basic
    @Column(name = "panorama_img",length = 50)
    public String getPanoramaImg() {
        return panoramaImg;
    }

    public void setPanoramaImg(String panoramaImg) {
        this.panoramaImg = panoramaImg;
    }

    @Basic
    @Column(name = "favorable",length = 50)
    public String getFavorable() {
        return favorable;
    }

    public void setFavorable(String favorable) {
        this.favorable = favorable;
    }

    @Basic
    @Column(name = "price_average",length = 50)
    public String getPriceAverage() {
        return priceAverage;
    }

    public void setPriceAverage(String priceAverage) {
        this.priceAverage = priceAverage;
    }

    @Basic
    @Column(name = "status",length = 50)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "phone",length = 50)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "types",length = 50)
    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Basic
    @Column(name = "location_id",length = 50)
    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "detail_id",length = 50)
    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    @Basic
    @Column(name = "city",length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "house_type_id",length = 50)
    public String getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(String houseTypeId) {
        this.houseTypeId = houseTypeId;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false,length = 50)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_person",length = 50)
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operator_date", nullable = false,length = 50)
    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    @Basic
    @Column(name = "operator",length = 50)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "order_num",length = 5)
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "release_person",length = 50)
    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    @Basic
    @Column(name = "release_Date",length = 50)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "release_status",length = 5)
    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    @Basic
    @Column(name = "url",length = 300)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", nullable = false,length = 50)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    /*
    @OneToOne(optional = true)
    public CommunityLocationEntity getLocation() {
        return location;
    }

    public void setLocation(CommunityLocationEntity location) {
        this.location = location;
    }

    @OneToOne(optional = true)
    public CommunityDetailEntity getDetail() {
        return detail;
    }

    public void setDetail(CommunityDetailEntity detail) {
        this.detail = detail;
    }

    @Transient
    public Set<CommunityHouseTypeEntity> getHouseTypes() {
        return houseTypes;
    }

    public void setHouseTypes(Set<CommunityHouseTypeEntity> houseTypes) {
        this.houseTypes = houseTypes;
    }
*/


}
