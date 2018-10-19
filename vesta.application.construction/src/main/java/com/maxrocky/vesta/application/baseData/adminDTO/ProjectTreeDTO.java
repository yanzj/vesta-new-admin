package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * 项目组织树
 * Created by yuanyn on 2017/8/29.
 */
public class ProjectTreeDTO {
    private String id; //id
    private String pId;//父级ID  区域id
    private String name;//项目名
    private String isParent;// 是否是父级
    private String icon;
    private String iconOpen; //打开图片
    private String iconClose; //关闭图片
    private String type; //0 集团  1 区域  2项目
    private String open; //加载是否打开

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
