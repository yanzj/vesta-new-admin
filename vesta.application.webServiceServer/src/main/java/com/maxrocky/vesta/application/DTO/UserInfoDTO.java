package com.maxrocky.vesta.application.DTO;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/10.
 *
 */
public class UserInfoDTO {
    private String memberId;//会员编号
    //List<MemberDTO> memberList=new ArrayList<>();
    List<UserDTO> userList=new ArrayList<UserDTO>();
    List<HouseDTO> houseList=new ArrayList<>();
    List<FamilyDTO> familyList=new ArrayList<>();
    List<ContactDTO> contactList=new ArrayList<>();
    List<CarDTO> carList=new ArrayList<>();
    List<AccountDTO> accountList=new ArrayList<>();
    List<CardDTO> cardList=new ArrayList<>();

    /*public List<MemberDTO> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberDTO> memberList) {
        this.memberList = memberList;
    }*/

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public List<HouseDTO> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<HouseDTO> houseList) {
        this.houseList = houseList;
    }

    public List<FamilyDTO> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<FamilyDTO> familyList) {
        this.familyList = familyList;
    }

    public List<ContactDTO> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactDTO> contactList) {
        this.contactList = contactList;
    }

    public List<CarDTO> getCarList() {
        return carList;
    }

    public void setCarList(List<CarDTO> carList) {
        this.carList = carList;
    }

    public List<AccountDTO> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountDTO> accountList) {
        this.accountList = accountList;
    }

    public List<CardDTO> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardDTO> cardList) {
        this.cardList = cardList;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
