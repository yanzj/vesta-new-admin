package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by yifan on 2016/4/1.
 */
@Entity
@Table(name = "community_house_type")
public class CommunityHouseTypeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6649995395151419089L;

	private String id; // 户型ID

	private String type; // 户型类型

	private Integer state; // 是否在售 0，不在，1，在售

	private String constructImg; // 户型构造图片

	private Integer area; // 户型面积

	private String description; // 户型描述

	private Integer number;// 剩余户型数量

	private Integer totalNumber;//户型总数量

	private String houseTypeImg;//户型类型图片

	private String phone;//联系电话


	// 户型与社区，多对一
	//private CommunityOverviewEntity community;
	private String communityId;

	@Id
	@Column(name = "id", length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic
	@Column(name = "type",length = 50)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Basic
	@Column(name = "state",length = 5)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Basic
	@Column(name = "construct_img",length = 50)
	public String getConstructImg() {
		return constructImg;
	}

	public void setConstructImg(String constructImg) {
		this.constructImg = constructImg;
	}

	@Basic
	@Column(name = "house_type_img",length = 50)
	public String getHouseTypeImg() {
		return houseTypeImg;
	}

	public void setHouseTypeImg(String houseTypeImg) {
		this.houseTypeImg = houseTypeImg;
	}

	@Basic
	@Column(name = "area",length = 5)
	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Basic
	@Column(name = "description",length = 50)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	@Column(name = "number",length = 5)
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Basic
	@Column(name = "totalNumber",length = 50)
	public Integer getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
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
	@Column(name = "communityId",length = 50)
	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

//	public CommunityOverviewEntity getCommunity() {
//		return community;
//	}
//
//	public void setCommunity(CommunityOverviewEntity community) {
//		this.community = community;
//	}


}
