package com.maxrocky.vesta.application.measure.DTO;

/**
 * Created by Itzxs on 2018/7/23.
 */
public class MeasureDataDTO {
    private String totalPercent;//总合格率
    private String firstPercent;//混凝土结构工程
    private String secondPercent;//砌筑工程
    private String thirdPercent;//抹灰工程
    private String fourthPercent;//设备安装工程
    private String fifthPercent;//涂饰工程
    private String sixth;//饰面砖、石材
    private String seventhPercent;//室内门
    private String eighth;//门窗工程
    private String ninth;//木地板安装；
    private String tenth;//防开裂；
    private String type;//页面用来判断是项目还是楼栋还是楼层 1 项目 2 楼栋 3 楼层

    public String getTotalPercent() {
        return totalPercent;
    }

    public void setTotalPercent(String totalPercent) {
        this.totalPercent = totalPercent;
    }

    public String getFirstPercent() {
        return firstPercent;
    }

    public void setFirstPercent(String firstPercent) {
        this.firstPercent = firstPercent;
    }

    public String getSecondPercent() {
        return secondPercent;
    }

    public void setSecondPercent(String secondPercent) {
        this.secondPercent = secondPercent;
    }

    public String getThirdPercent() {
        return thirdPercent;
    }

    public void setThirdPercent(String thirdPercent) {
        this.thirdPercent = thirdPercent;
    }

    public String getFourthPercent() {
        return fourthPercent;
    }

    public void setFourthPercent(String fourthPercent) {
        this.fourthPercent = fourthPercent;
    }

    public String getFifthPercent() {
        return fifthPercent;
    }

    public void setFifthPercent(String fifthPercent) {
        this.fifthPercent = fifthPercent;
    }

    public String getSixth() {
        return sixth;
    }

    public void setSixth(String sixth) {
        this.sixth = sixth;
    }

    public String getSeventhPercent() {
        return seventhPercent;
    }

    public void setSeventhPercent(String seventhPercent) {
        this.seventhPercent = seventhPercent;
    }

    public String getEighth() {
        return eighth;
    }

    public void setEighth(String eighth) {
        this.eighth = eighth;
    }

    public String getNinth() {
        return ninth;
    }

    public void setNinth(String ninth) {
        this.ninth = ninth;
    }

    public String getTenth() {
        return tenth;
    }

    public void setTenth(String tenth) {
        this.tenth = tenth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
