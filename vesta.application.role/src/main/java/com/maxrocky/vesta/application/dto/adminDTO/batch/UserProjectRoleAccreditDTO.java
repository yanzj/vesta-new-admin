package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2018/1/11.
 */
public class UserProjectRoleAccreditDTO {
    private String proName;
    private String proId;
    private String rName;
    private String rId;
    private String uName;
    private String uId;

    public UserProjectRoleAccreditDTO() {
        this.proName = "";
        this.proId = "";
        this.rName = "";
        this.rId = "";
        this.uName = "";
        this.uId = "";
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}
