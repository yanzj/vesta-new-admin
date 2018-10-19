package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.dto.adminDTO.RoleMenuDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by zhanghj on 2016/4/18.
 */
public interface RoleMenuService {

    /**
     * 添加一级菜单
     * @param roleMenuDTO
     * @return
     */
    public String addFirMenu(RoleMenuDTO roleMenuDTO);

    /**
     * 添加二级菜单，对应权限，权限菜单关系
     * @param roleMenuDTO
     * @return
     */
    public String addSecMenu(RoleMenuDTO roleMenuDTO);

    /**
     * 获取一级菜单列表
     * @return
     */
    public List<RoleMenuDTO> listFirMenu(WebPage webPage);

    /**
     * 获取二级菜单列表
     * @return
     */
    public List<RoleMenuDTO> listSecMenu(String parId,WebPage webPage);
}
