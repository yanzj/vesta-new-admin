package com.maxrocky.vesta.application.adminDTO;

/**
 * Created by hp on 2018/5/23.
 */
public class GuideDTO {

    private String id1;//id
    private String state;       //状态  1启用  0.关闭
    private String fileName1;    //文件名

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFileName1() {
        return fileName1;
    }

    public void setFileName1(String fileName1) {
        this.fileName1 = fileName1;
    }
}
