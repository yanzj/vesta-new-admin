package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.NavigationMenuEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by luxinxin on 2016/8/31.
 */
public interface NavigationMenuRepository {
    /**
     * 添加导航菜单信息
     * @param navigationMenuDTO
     * @return
     */
    public boolean saveNavigationMenu(NavigationMenuEntity navigationMenuDTO);
    /**
     *删除导航信息
     * @param navigationMenuId
     * @return
     */
    public boolean delNavigationMenu(String navigationMenuId);
    /**
     * 修改导航菜单消息
     * @param navigationMenuDTO
     * @return
     */
    public boolean updateNavigationMenu(NavigationMenuEntity navigationMenuDTO);
    /**
     * 根据id获取导航菜单信息
     * @param navigationMenuId
     * @return
     */
    public NavigationMenuEntity getNavigationMenu(String navigationMenuId);
    /**
     * 获取导航菜单列表
     * @param navigationMenuDTO
     * @return
     */
    public List<NavigationMenuEntity> getAllnavigationMenus(NavigationMenuEntity navigationMenuDTO,WebPage webPage);

    /**
     * 获取导航菜单列表
     * @return
     */
    public List<NavigationMenuEntity> getAllnavigationMenus();

}
