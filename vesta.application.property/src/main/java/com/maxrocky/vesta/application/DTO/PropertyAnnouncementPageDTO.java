package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by ZhangBailiang on 2016/1/26.
 * 物业公告(后端交互) DTO
 */
public class PropertyAnnouncementPageDTO {
    private String announcementId;// 公告id
    private String announcementSummary;     //公告简介
    private String announcementContent;// 公告内容
    private String announcementTime;// 公告时间
    private String type;// 公告类别 1 一般 2 重要 3 紧急
    private String createTime;//创建时间
    private String releasePerson;//发布人
    private String title;//公告标题
    private String memo;//备注
    private String operator;//操作人
    private String operatDate;//操作时间
    private String stat ;//状态 0 禁用 1 启用
    private String project;// 项目id
    private String queryScope;//查询负责范围(模块条件)
    /*物业公告时间搜索条件*/
    private String startTime;//公告开始时间
    private String endTime;//公告结束时间

    private Map<String,String> cityMap;//城市下拉框
    private Map<String,String> projectMap;//项目下拉框
    private MultipartFile image;//图片
    private String imagePath;//图片路径

    private String city;        //城市
    private String citys;        //城市集合
    private String cityIds;      //城市Id集合
    private String projects;     //项目集合
    private String projectIds;  //项目Id集合

    /* 新增查询字段(用户权限范围)_2016-08-30_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */

    private Integer sortNumber;//排序序号

    private String menuId;      //菜单ID

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitys() {
        return citys;
    }

    public void setCitys(String citys) {
        this.citys = citys;
    }

    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public String getQueryScope() {
        return queryScope;
    }

    public void setQueryScope(String queryScope) {
        this.queryScope = queryScope;
    }

    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncementSummary() {
        return announcementSummary;
    }

    public void setAnnouncementSummary(String announcementSummary) {
        this.announcementSummary = announcementSummary;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public String getAnnouncementTime() {
        return announcementTime;
    }

    public void setAnnouncementTime(String announcementTime) {
        this.announcementTime = announcementTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatDate() {
        return operatDate;
    }

    public void setOperatDate(String operatDate) {
        this.operatDate = operatDate;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Map<String, String> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<String, String> cityMap) {
        this.cityMap = cityMap;
    }

    public Map<String, String> getProjectMap() {
        return projectMap;
    }

    public void setProjectMap(Map<String, String> projectMap) {
        this.projectMap = projectMap;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
