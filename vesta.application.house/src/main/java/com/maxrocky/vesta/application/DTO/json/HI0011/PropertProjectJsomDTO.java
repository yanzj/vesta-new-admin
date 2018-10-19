package com.maxrocky.vesta.application.DTO.json.HI0011;

/**
 * Created by Magic on 2016/5/12.
 */
public class PropertProjectJsomDTO{
    private String id; //repairid保修单id
    private String name;//标题
    private String type;//状态
    private String address;//地址位置
    private String roomname;//维修位置
    private String content;//描述
    private String date;//时间
    private String time;//截单时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;

  }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
