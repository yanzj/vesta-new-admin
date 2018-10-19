package com.maxrocky.vesta.application.DTO;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/3/10.
 * 联动下来公司
 * ModifyBy:liudongxin
 * 用于金茂项目：便民信息-添加默认城市、小区list、城市list
 */
public class HouseCompanyDTO {
    private String id;//主键
    private String name;//公司名称
    private List<HouseProjectDTO> cityList;
    private List<PropertyPriceSettingDTO> communityList;
    private String defaultCity;//默认城市

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HouseProjectDTO> getCityList() {
        return cityList;
    }

    public void setCityList(List<HouseProjectDTO> cityList) {
        this.cityList = cityList;
    }

    public List<PropertyPriceSettingDTO> getCommunityList() {
        return communityList;
    }

    public void setCommunityList(List<PropertyPriceSettingDTO> communityList) {
        this.communityList = communityList;
    }

    public String getDefaultCity() {
        return defaultCity;
    }

    public void setDefaultCity(String defaultCity) {
        this.defaultCity = defaultCity;
    }
}
