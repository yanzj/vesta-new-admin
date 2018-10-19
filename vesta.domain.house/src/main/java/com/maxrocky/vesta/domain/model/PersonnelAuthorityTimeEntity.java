package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2016/8/31.
 * 人员对应权限
 */
@Entity
@Table(name = "personnel_authority_time",uniqueConstraints = {@UniqueConstraint(columnNames = {"CURRENT_ID","PARENT_ID","GRADED","ROLE_GROUP"})})
public class PersonnelAuthorityTimeEntity {
    private Long id;          //主键
    private String currentId;   // 当前id    不可为空  1 projectnum
    private String name;        // 名称 1    项目名 2员工姓名
    private String parentId;    // 父级id    项目级别该字段为空  可为空
    private String graded;      // 级别      项目为1，人2
    private Date timeStamp;   //时间戳     新增和修改当前字段取当前时间
    private String roleGroup;   //组     第二级 判断所属权限1 （数据查看 ）2 （工程接单）3 （派单） 100000001（物业接单 ）100000000（开发接单）
    private String start;       //状态    1 启用  0. 删除

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "CURRENT_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }
    @Basic
    @Column(name = "NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "PARENT_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    @Basic
    @Column(name = "GRADED", nullable = true, insertable = true, updatable = true, length = 100)
    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }
    @Basic
    @Column(name = "TIME_STAMP", nullable = true, insertable = true, updatable = true, length = 100)
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    @Basic
    @Column(name = "ROLE_GROUP", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
    }
    @Basic
    @Column(name = "START", nullable = true, insertable = true, updatable = true, length = 100)
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

}
