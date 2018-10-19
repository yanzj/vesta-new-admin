package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/4/13.
 * 会员账号信息
 */
public class AccountDTO {

    private String id;//会员账号id
    //private String memberId;//会员编号
    private String PhoneNumber;//注册手机号
    private String registerId;//注册账号
    private String nickName;//昵称
    private String password;//密码
    private String mailBox;//注册电子邮箱

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }*/

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }
}
