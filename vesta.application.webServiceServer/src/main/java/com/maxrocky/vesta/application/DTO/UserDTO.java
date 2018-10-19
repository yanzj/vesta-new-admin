package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by liudongxin on 2016/4/10.
 * 会员个人信息
 */
public class UserDTO {

    private String id;//用户id
    //private String memberId;//会员编号
    private String userName;//用户名
    private String realName;//真实名
    private String sex;//性别
    private Date birthday;//生日
    private String nationality;//国籍
    private String nativePlace;//籍贯
    private String status;//会员状态
    private String registerDate;//注册日期
    private String eduLevel;//教育程度
    private String hobby;//爱好
    private String englishName;//英文名称
    private String occupation;//职业
    private String jobUnit;//工作单位
    private String IncomeLevel;//会员收入
    private String companyType;//单位类别
    private String phone;//电话
    private String idCard;//身份证号
    private String userType;//用户类型
    private String state;//酒店会员标识
    private Date modifyDate;//最后修改时间(需要用这个时间去更新)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel;
    }

    public String getIncomeLevel() {
        return IncomeLevel;
    }

    public void setIncomeLevel(String incomeLevel) {
        IncomeLevel = incomeLevel;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /*public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }*/

    public String getJobUnit() {
        return jobUnit;
    }

    public void setJobUnit(String jobUnit) {
        this.jobUnit = jobUnit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
