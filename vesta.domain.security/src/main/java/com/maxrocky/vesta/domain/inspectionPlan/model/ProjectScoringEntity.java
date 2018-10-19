package com.maxrocky.vesta.domain.inspectionPlan.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2017/6/13.
 */
@Entity
@Table(name = "st_project_scoring")
public class ProjectScoringEntity {
    private Long id;//
    private Date modifyDate;//修改时间
    private String planId;//检查计划id
    private String planName;//检查计划名
    private String projectId;//项目公司id
    private String projectName;//项目公司名称
    private String checkClassOneId;//一级检查项id
    private String checkClassOne;//一级检查项
    private String checkClassTwoId;//二级检查项id
    private String checkClassTwo;//二级检查项
    private String checkClassThreeId;//三级检查项id
    private String checkClassThree;//三级检查项
//    private String checkItemId;//检查项id
//    private String checkItemName;//检查项
    private int fraction;//分数
    private int score;//得分
    private int points;//扣分
    private String domain;//所属模块 1：区域；2：项目公司；3：监理；4：施工单位
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",length = 50,nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "PLAN_ID", nullable = false, insertable = true, updatable = true, length = 100)
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
    @Basic
    @Column(name = "PLAN_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
    @Basic
    @Column(name = "PROJECT_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "PROJECT_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "FRACTION", nullable = true, insertable = true, updatable = true, length = 100)
    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }
    @Basic
    @Column(name = "SCORE", nullable = true, insertable = true, updatable = true, length = 100)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Basic
    @Column(name = "POINTS", nullable = true, insertable = true, updatable = true, length = 100)
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    @Basic
    @Column(name = "CHECK_CLASS_ONE_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCheckClassOneId() {
        return checkClassOneId;
    }

    public void setCheckClassOneId(String checkClassOneId) {
        this.checkClassOneId = checkClassOneId;
    }
    @Basic
    @Column(name = "CHECK_CLASS_ONE", nullable = true, insertable = true, updatable = true, length = 500)
    public String getCheckClassOne() {
        return checkClassOne;
    }

    public void setCheckClassOne(String checkClassOne) {
        this.checkClassOne = checkClassOne;
    }
    @Basic
    @Column(name = "CHECK_CLASS_TWO_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCheckClassTwoId() {
        return checkClassTwoId;
    }

    public void setCheckClassTwoId(String checkClassTwoId) {
        this.checkClassTwoId = checkClassTwoId;
    }
    @Basic
    @Column(name = "CHECK_CLASS_TWO", nullable = true, insertable = true, updatable = true, length = 500)
    public String getCheckClassTwo() {
        return checkClassTwo;
    }

    public void setCheckClassTwo(String checkClassTwo) {
        this.checkClassTwo = checkClassTwo;
    }
    @Basic
    @Column(name = "CHECK_CLASS_THREE_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCheckClassThreeId() {
        return checkClassThreeId;
    }

    public void setCheckClassThreeId(String checkClassThreeId) {
        this.checkClassThreeId = checkClassThreeId;
    }
    @Basic
    @Column(name = "CHECK_CLASS_THREE", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getCheckClassThree() {
        return checkClassThree;
    }

    public void setCheckClassThree(String checkClassThree) {
        this.checkClassThree = checkClassThree;
    }
    @Basic
    @Column(name = "DOMAIN", length = 50)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }



}
