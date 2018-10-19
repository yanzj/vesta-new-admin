package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;

/**
 * 投票DTO
 * @author Wyd_2016/05/26
 */
public class VoteDTO {

    //主键ID
    private String id;
    //截至日期
    private Date endDate;
    //截止日期(字符)
    private String endDateStr;
    //标题
    private String title;
    // 0,启用 1，不启用
    private Integer status;
    //创建日期
    private Date createDate;
    //创建人
    private String createPerson;
    //操作日期
    private Date operateDate;
    //操作人
    private String operatePerson;
    //检索条件_开始时间
    private String queryStaDate;
    //检索条件_结束时间
    private String queryEndDate;
    //检索条件_投票状态(0:未开始,1:进行中,2:已结束)
    private Integer voteStatus;
    //新增选项_投票形式(0:单选,1:多选)
    private Integer voteType;
    //新增选项_投票项描述(数组)
    private String[] descriptions;
    //新增选项_文件流
    private MultipartFile[] multipartFiles;
    //文件Url(集合)
    private ArrayList<String> imgUrls;
    //新增选项_时
    private Integer hour;
    //新增选项_分
    private Integer minute;
    //结果列表_投票人数
    private Long voteCount;

    /**
     * Set/Get
     */

    public Integer getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(Integer voteStatus) {
        this.voteStatus = voteStatus;
    }
    public String getQueryStaDate() {
        return queryStaDate;
    }

    public void setQueryStaDate(String queryStaDate) {
        this.queryStaDate = queryStaDate;
    }

    public String getQueryEndDate() {
        return queryEndDate;
    }

    public void setQueryEndDate(String queryEndDate) {
        this.queryEndDate = queryEndDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getId() {
        return id;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson;
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String[] descriptions) {
        this.descriptions = descriptions;
    }

    public MultipartFile[] getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(MultipartFile[] multipartFiles) {
        this.multipartFiles = multipartFiles;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getVoteType() {
        return voteType;
    }

    public void setVoteType(Integer voteType) {
        this.voteType = voteType;
    }

    public ArrayList<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(ArrayList<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}
