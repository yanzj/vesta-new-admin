package com.maxrocky.vesta.application.admin.dto;

/**
 * zTree_节点数据结构
 * Created by WeiYangDong on 2017/5/9.
 */
public class ZtreeNodeDTO {
    private String id;
    private String pid;
    private boolean isParent;
    private boolean open;
    private boolean checked;
    private String name;
    private String value;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{id:\"");
        sb.append(id);
        sb.append("\",pId:\"");
        sb.append(pid);
        sb.append("\",name:\"");
        sb.append(name);
        sb.append("\",value:\"");
        sb.append(value);
        sb.append("\",isParent:\"");
        sb.append(isParent);
        sb.append("\",open:\"");
        sb.append(open);
        sb.append("\",checked:\"");
        sb.append(checked);
        sb.append("\"}");
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
