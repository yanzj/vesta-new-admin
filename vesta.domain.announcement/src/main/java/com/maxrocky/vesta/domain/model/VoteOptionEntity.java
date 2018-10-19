package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/21
 * Time: 11:43
 * Describe: 投票项表_存储投票项
 */
@Entity
@Table(name = "vote_option")
public class VoteOptionEntity implements Serializable {
    private String id;         //主键
    private String voteId;      //投票详情id
    private String url;        //投票图片
    private String description;     //投票描述
    private Integer status;     // 0,启用 1，不启用
    private Integer optionOrder;      //选项排序

    private Date createDate;      //创建日期
    private String createPerson;    //创建人
    private Date operateDate;      //操作日期
    private String operatePerson;    //操作人

    @Id
    @Column(name = "id", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "vote_id", length = 50, nullable = true, updatable = true)
    private String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    @Basic
    @Column(name = "url", length = 100, nullable = true, updatable = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "description", length = 100, nullable = true, updatable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    @Basic
    @Column(name = "status", length = 5, nullable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", length = 50, nullable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_person", length = 50, nullable = true, updatable = true)
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operate_date", length = 50, nullable = true, updatable = true)
    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    @Basic
    @Column(name = "operate_person", length = 50, nullable = true, updatable = true)
    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson;
    }

    @Basic
    @Column(name = "option_order",length = 5, nullable = true, updatable = true)
    public Integer getOptionOrder() {
        return optionOrder;
    }

    public void setOptionOrder(Integer optionOrder) {
        this.optionOrder = optionOrder;
    }
}
