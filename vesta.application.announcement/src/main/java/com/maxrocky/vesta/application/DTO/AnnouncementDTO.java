package com.maxrocky.vesta.application.DTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:43
 * Describe:
 */
public class AnnouncementDTO {
    private String id;         //主键
    private String title;       //标题
    private String content;     //内容
    private String contentSynopsis; //公告内容简介_2016-06-17-Wyd
    private Integer releaseStatus;       //发布状态(1:发布,0::暂不发布)
    private String releasePerson;       //发布人
    private Date releaseDate;       //发布日期
    private Date createDate;      //创建日期
    private String createPerson;    //创建人
    private Date operatDate;      //操作日期
    private String operatPerson;    //操作人
    private Long voteNum;       //投票数
    private Integer isVote;     //是否进行投票
    private Long likeNum;       //点赞数
    private Long replyNum;      //回复数
    private Integer status;      //状态
    private String voteId;      //投票id

    private String city;        //城市
    private String citys;        //城市集合
    private String cityIds;      //城市Id集合
    private String project;     //项目
    private String projects;     //项目集合
    private String projectIds;  //项目Id集合

    private String staDate;
    private String endDate;

    /* 新增查询字段(用户权限范围)_2016-08-23_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getOperatDate() {
        return operatDate;
    }

    public void setOperatDate(Date operatDate) {
        this.operatDate = operatDate;
    }

    public String getOperatPerson() {
        return operatPerson;
    }

    public void setOperatPerson(String operatPerson) {
        this.operatPerson = operatPerson;
    }

    public Long getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(Long voteNum) {
        this.voteNum = voteNum;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Long getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Long replyNum) {
        this.replyNum = replyNum;
    }


    public String getStaDate() {
        return staDate;
    }

    public void setStaDate(String staDate) {
        this.staDate = staDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsVote() {
        return isVote;
    }

    public void setIsVote(Integer isVote) {
        this.isVote = isVote;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCitys() {
        return citys;
    }

    public void setCitys(String citys) {
        this.citys = citys;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getVoteId() {
        return voteId;
    }


    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getContentSynopsis() {
        return contentSynopsis;
    }

    public void setContentSynopsis(String contentSynopsis) {
        this.contentSynopsis = contentSynopsis.trim();
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }
}
