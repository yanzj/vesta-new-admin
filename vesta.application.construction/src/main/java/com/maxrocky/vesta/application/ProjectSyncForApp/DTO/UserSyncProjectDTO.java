package com.maxrocky.vesta.application.ProjectSyncForApp.DTO;

/**
 * 同步项目角色DTO
 * Created by yuanyn on 2018/4/24.
 */
public class UserSyncProjectDTO {

    private String proId;// 项目Id
    private String proName;//机构名称
    private String peoId;// 人员id
    private String peoName;//人员名称
    private String proIdApp;//app端同步项目id
    private String proNameApp;//app端同步项目名称
    private String loginName;//本系统用户名

    public UserSyncProjectDTO() {
        this.proId = "";
        this.proName = "";
        this.peoId = "";
        this.peoName = "";
        this.proIdApp = "";
        this.proNameApp = "";
        this.loginName = "";
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getPeoId() {
        return peoId;
    }

    public void setPeoId(String peoId) {
        this.peoId = peoId;
    }

    public String getPeoName() {
        return peoName;
    }

    public void setPeoName(String peoName) {
        this.peoName = peoName;
    }

    public String getProIdApp() {
        return proIdApp;
    }

    public void setProIdApp(String proIdApp) {
        this.proIdApp = proIdApp;
    }

    public String getProNameApp() {
        return proNameApp;
    }

    public void setProNameApp(String proNameApp) {
        this.proNameApp = proNameApp;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

}
