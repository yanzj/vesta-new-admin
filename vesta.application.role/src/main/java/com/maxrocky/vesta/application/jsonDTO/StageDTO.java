package com.maxrocky.vesta.application.jsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/4.
 */
public class StageDTO {
    private String stageName;                //阶段名
    private List<MenuDTO> menuList;          //菜单列表

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<MenuDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuDTO> menuList) {
        this.menuList = menuList;
    }
}
