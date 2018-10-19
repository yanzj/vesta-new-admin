package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 管家信息表
 * Created by WeiYangDong on 2017/5/5.
 */
@Entity
@Table(name = "butler_style")
public class ButlerStyleEntity {
    private String butlerId;//管家ID
    private String butlerNum;//管家名称
    private String realName;//真实姓名
    private String butlerHeadImgUrl;//管家头像
    private String telephone;//联系电话
    private String wechatNickname;//微信昵称
    private String wechatQRCodeUrl;//微信二维码Url
    private String butlerScore;//管家评分
    private String serviceBuilding;//服务楼栋
    private String remarks;//备注

    private String serviceCityId;//城市ID
    private String serviceCityName;//城市名称
    private String serviceProjectCode;//项目编码
    private String serviceProjectName;//项目名称

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "butler_id", length = 32)
    public String getButlerId() {
        return butlerId;
    }

    public void setButlerId(String butlerId) {
        this.butlerId = butlerId;
    }

    @Basic
    @Column(name = "butler_num",length = 50)
    public String getButlerNum() {
        return butlerNum;
    }

    public void setButlerNum(String butlerNum) {
        this.butlerNum = butlerNum;
    }

    @Basic
    @Column(name = "real_name",length = 50)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "remarks",length = 200)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "butler_headImg_url",length = 200)
    public String getButlerHeadImgUrl() {
        return butlerHeadImgUrl;
    }

    public void setButlerHeadImgUrl(String butlerHeadImgUrl) {
        this.butlerHeadImgUrl = butlerHeadImgUrl;
    }

    @Basic
    @Column(name = "telephone",length = 20)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "wechat_nickname",length = 100)
    public String getWechatNickname() {
        return wechatNickname;
    }

    public void setWechatNickname(String wechatNickname) {
        this.wechatNickname = wechatNickname;
    }

    @Basic
    @Column(name = "wechat_QRCode_url",length = 100)
    public String getWechatQRCodeUrl() {
        return wechatQRCodeUrl;
    }

    public void setWechatQRCodeUrl(String wechatQRCodeUrl) {
        this.wechatQRCodeUrl = wechatQRCodeUrl;
    }

    @Basic
    @Column(name = "butler_score",length = 10)
    public String getButlerScore() {
        return butlerScore;
    }

    public void setButlerScore(String butlerScore) {
        this.butlerScore = butlerScore;
    }

    @Basic
    @Column(name = "service_city_id",length = 100)
    public String getServiceCityId() {
        return serviceCityId;
    }

    public void setServiceCityId(String serviceCityId) {
        this.serviceCityId = serviceCityId;
    }

    @Basic
    @Column(name = "service_city_name",length = 100)
    public String getServiceCityName() {
        return serviceCityName;
    }

    public void setServiceCityName(String serviceCityName) {
        this.serviceCityName = serviceCityName;
    }

    @Basic
    @Column(name = "service_project_code",length = 100)
    public String getServiceProjectCode() {
        return serviceProjectCode;
    }

    public void setServiceProjectCode(String serviceProjectCode) {
        this.serviceProjectCode = serviceProjectCode;
    }

    @Basic
    @Column(name = "service_project_name",length = 100)
    public String getServiceProjectName() {
        return serviceProjectName;
    }

    public void setServiceProjectName(String serviceProjectName) {
        this.serviceProjectName = serviceProjectName;
    }

    @Basic
    @Column(name = "service_building",length = 200)
    public String getServiceBuilding() {
        return serviceBuilding;
    }

    public void setServiceBuilding(String serviceBuilding) {
        this.serviceBuilding = serviceBuilding;
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
