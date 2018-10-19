package com.maxrocky.vesta.domain.model;

import org.apache.commons.collections.map.HashedMap;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * App项目可视功能模块配置表
 * Created by WeiYangDong on 2017/4/12.
 */
@Entity
@Table(name = "app_function")
public class AppFunctionConfigEntity {
    public static String FUNCTION_MODULE_SQGG = "SQGG";     //社区公告
    public static String FUNCTION_MODULE_XWZX = "XWZX";     //新闻资讯
    public static String FUNCTION_MODULE_JMLP = "JMLP";     //金茂楼盘
    public static String FUNCTION_MODULE_YJFK = "YJFK";     //意见反馈
    public static String FUNCTION_MODULE_WYBX = "WYBX";     //物业报修
    public static String FUNCTION_MODULE_HDBM = "HDBM";     //活动报名
    public static String FUNCTION_MODULE_BMXX = "BMXX";     //便民信息
    public static String FUNCTION_MODULE_JFYY = "JFYY";     //交付预约
    public static String FUNCTION_MODULE_ZNMJ = "ZNMJ";     //智能门禁
    public static String FUNCTION_MODULE_WYJF = "WYJF";     //物业缴费
    public static String FUNCTION_MODULE_FKTX = "FKTX";     //访客通行
    public static String FUNCTION_MODULE_GJXX = "GJXX";     //管家信息
    public static String FUNCTION_MODULE_XSHD = "XSHD";     //线上活动

    public static Map<String,Object> functionModuleMap;     //功能模块集合

    public static Map<String, Object> getFunctionModuleMap() {
        return functionModuleMap;
    }

    public static void setFunctionModuleMap() {
        Map<String, Object> map = new HashedMap();
        map.put(FUNCTION_MODULE_SQGG,"社区公告");
        map.put(FUNCTION_MODULE_XWZX,"新闻资讯");
        map.put(FUNCTION_MODULE_JMLP,"金茂楼盘");
        map.put(FUNCTION_MODULE_YJFK,"意见反馈");
        map.put(FUNCTION_MODULE_WYBX,"物业报修");
        map.put(FUNCTION_MODULE_HDBM,"活动报名");
        map.put(FUNCTION_MODULE_BMXX,"便民信息");
        map.put(FUNCTION_MODULE_JFYY,"交付预约");
        map.put(FUNCTION_MODULE_ZNMJ,"智能门禁");
        map.put(FUNCTION_MODULE_WYJF,"物业缴费");
        map.put(FUNCTION_MODULE_FKTX,"访客通行");
        map.put(FUNCTION_MODULE_GJXX,"管家信息");
        map.put(FUNCTION_MODULE_XSHD,"线上活动");
        AppFunctionConfigEntity.functionModuleMap = map;
    }

    private String id;          //主键ID
    private String cityId;      //城市ID
    private String cityNum;     //城市编码
    private String cityName;    //城市名称
    private String projectNum;  //项目编码
    private String projectName; //项目名称

    private String functionModules;   //功能模块(SQGG,XWZX,)

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "id",nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city_id", length = 50)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "city_num", length = 50)
    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }

    @Basic
    @Column(name = "city_name", length = 50)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "project_num", length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "project_name", length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "function_modules", length = 100)
    public String getFunctionModules() {
        return functionModules;
    }

    public void setFunctionModules(String functionModules) {
        this.functionModules = functionModules;
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
