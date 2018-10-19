package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by zhanghj on 2016/1/28.
 */
public class WorkBenchDTO {

        private String staffId;//用户Id

        private String userName;//用户名

        private String logo;//头像路径

        private int countPraise;//未读表扬数

        private String version;//版本信息



    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getCountPraise() {
        return countPraise;
    }

    public void setCountPraise(int countPraise) {
        this.countPraise = countPraise;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
