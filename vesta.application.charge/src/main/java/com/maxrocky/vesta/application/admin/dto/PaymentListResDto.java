package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/1/28.
 */
public class PaymentListResDto {


    private String id ;
    private String title ;
    private String address ;
    private String date ;
    private String detail ;
    private String lifetime; //停车位的使用周期
    private String energy; //电量
    private String money;
    private String prompt; //付款期限
    private String carNums;
    private String type ;
    private String checkDate; //检测日期

    public String getCheckDate() {
        return checkDate;
    }

    public PaymentListResDto setCheckDate(String checkDate) {
        this.checkDate = checkDate;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLifetime() {
        return lifetime;
    }

    public void setLifetime(String lifetime) {
        this.lifetime = lifetime;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getCarNums() {
        return carNums;
    }

    public void setCarNums(String carNums) {
        this.carNums = carNums;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    /*

				id:'1',
				title:"物业费",
				address:"8号楼1709",
				date:"2015年6-8月份",
				detail:"物业管理费",
				lifetime:"",
				energy:"",
				money:"168.11",
				prompt:"",
				carNums:"",
				type:"1"

     */
}
