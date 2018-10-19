package com.maxrocky.vesta.application.DTO.json.HI0012;


/**
 * Created by Magic on 2016/6/16.
 */
public class ThirdTypeClassUPListDTO {
    private String id;          //自增长id
    private String currentId;   //当前级别id
    private String name;        //名称
    private String parentId;    //上一级id
    private String start;       //状态  1 启用  0. 删除
    private String graded;      //级别 eg: 1.一级分类  2.二级分类  3. 三级分类  4.维修方式 4.简要描述  5. 维修工时
    private String type;        //类型  第4级别才有值 0.维修方式  1.简要描述
    private String timeStamp;   //时间戳  增加修改数据  均修改为当前时间
    private int itemOrder;   //新增加的排序字段
    public ThirdTypeClassUPListDTO(){
        this.id="";
        this.currentId="";
        this.name="";
        this.parentId="";
        this.start="";
        this.graded="";
        this.type="";
        this.timeStamp="";
        this.itemOrder=-1;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


    public int getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(int itemOrder) {
        this.itemOrder = itemOrder;
    }
}
