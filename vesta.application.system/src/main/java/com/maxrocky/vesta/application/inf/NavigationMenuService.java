package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.NavigationMenuDTO;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by luxinxin on 2016/8/31.
 */
public interface NavigationMenuService  {
    /**
     * 添加导航菜单信息
     * @param navigationMenuDTO
     * @return
     */
    public boolean saveNavigationMenu(NavigationMenuDTO navigationMenuDTO);
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
    public boolean updateNavigationMenu(NavigationMenuDTO navigationMenuDTO);
    /**
     * 根据id获取导航菜单信息
     * @param navigationMenuId
     * @return
     */
    public NavigationMenuDTO getNavigationMenu(String navigationMenuId);

    /**
     * 获取导航菜单列表
     * @param navigationMenuDTO
     * @return
     */
    public List<NavigationMenuDTO> getAllnavigationMenus(NavigationMenuDTO navigationMenuDTO,WebPage webPage);

    public ApiResult queryList() throws GeneralException;
}
