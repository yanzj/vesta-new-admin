package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/2/18.
 * 工单通讯录人员信息(员工端)
 * ModifyBy:liudongxin
 * 修改：增加地址、服务内容
 */
public class WorkOrderUserDTO {
    private String userId;//用户id
    private String groupName;//部门名称
    private String name;//用户姓名
    private String mobile;//用户电话
    private String address;//地址
    private String content;//服务内容
    private Integer sortNum;//排序序号

    public WorkOrderUserDTO() {
        this.userId = "";
        this.groupName="";
        this.name = "";
        this.mobile = "";
        this.address="";
        this.content="";
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
