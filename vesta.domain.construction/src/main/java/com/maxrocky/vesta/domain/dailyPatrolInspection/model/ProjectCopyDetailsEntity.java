package com.maxrocky.vesta.domain.dailyPatrolInspection.model;

import javax.persistence.*;

/**
 * 抄送详情
 * Created by Magic on 2016/10/17.
 */
@Entity
@Table(name = "project_copy_details")
public class ProjectCopyDetailsEntity {
    private String id;//
    private String copyId;//抄送ID 关联 project_copy id
    private String memberId;//人员ID
    private String memberName;//人员姓名
    @Id
    @Column(name = "ID", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "COPY_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getCopyId() {
        return copyId;
    }

    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }
    @Basic
    @Column(name = "MEMBER_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    @Basic
    @Column(name = "MEMBER_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
