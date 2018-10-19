package com.maxrocky.vesta.application.DTO.json.HI0012;

/**
 * Created by Magic on 2016/6/16.
 */
public class PlanActivityUpTimeListDTO {

    private String id;//自增长id
    private String currentId;//当前级别id
    private String currentNum;//当前级别编码
    private String start;       //状态  1 启用  0. 删除
    private String name;//当前级别
    private String parentId;//父级id
    private String parentNum;//父级编码
    private String type;//区分活动
    private String graded;//级别   1.活动 2.房间
    private String timeStamp;//时间戳    增加和修改时候均修改当前字段
    private String path;//路径  eg：1./projectNum(项目级别)  2./projectNum/buildingNum(楼栋级别)  3./projectNum/buildingNum/unitNum(单元级别)
    public PlanActivityUpTimeListDTO(){
        this.type="";
        this.id="";
        this.currentId="";
        this.currentNum="";
        this.start="";
        this.name="";
        this.parentId="";
        this.parentNum="";
        this.graded="";
        this.timeStamp="";
        this.path="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentNum() {
        return parentNum;
    }

    public void setParentNum(String parentNum) {
        this.parentNum = parentNum;
    }

    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
