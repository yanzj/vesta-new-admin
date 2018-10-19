package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by maxrocky on 2017/5/5.
 */
public class PropertyRectifyListMagicDTO {
    private String projectName;//项目名称
    private String projectNum;//项目编码
    private String roomAddress;//房间位置
    private String roomNum;//房间编码
    private String planNum;//房间编码
    private String userName;//客户姓名
    private String state;//办理状态
    private String count;//问题统计
    private Date userTime;//签字时间
    private String planType;//11 内部预验 12工地开放  13 正式交房
    private String successOrFailure;//0 成功 1 失败 推送crm
    private String caseState;//问题状态  默认有效

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }



    public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getSuccessOrFailure() {
        return successOrFailure;
    }

    public void setSuccessOrFailure(String successOrFailure) {
        this.successOrFailure = successOrFailure;
    }

    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
    }
}
