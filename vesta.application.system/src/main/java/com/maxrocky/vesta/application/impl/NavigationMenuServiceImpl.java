package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.AppVersionDTO;
import com.maxrocky.vesta.application.DTO.NavigationMenuDTO;
import com.maxrocky.vesta.application.inf.AppVersionService;
import com.maxrocky.vesta.application.inf.NavigationMenuService;
import com.maxrocky.vesta.application.service.config.ImgType;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.NavigationMenuEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.NavigationMenuRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luxinxin on 2016/8/31.
 */
@Service
public class NavigationMenuServiceImpl implements NavigationMenuService {
    @Autowired
    ImgService imgService;
    @Autowired
    private NavigationMenuRepository navigationMenuRepository;

    @Override
    public boolean saveNavigationMenu(NavigationMenuDTO navigationMenuDTO) {
        NavigationMenuEntity NavigationMenuEntity=new NavigationMenuEntity();
        NavigationMenuEntity.setNavigationId(IdGen.uuid() + "");//id
        NavigationMenuEntity.setNavigationName(navigationMenuDTO.getNavigationName());
        String fileName = imgService.uploadAdminImage(navigationMenuDTO.getNavigationImg(), ImgType.ACTIVITY);
        //图片地址特殊处理
        String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
        fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
        NavigationMenuEntity.setNavigationUrl(navigationMenuDTO.getNavigationUrl());
        NavigationMenuEntity.setNavigationImg(fileName);
        return navigationMenuRepository.saveNavigationMenu(NavigationMenuEntity);
    }

    @Override
    public boolean delNavigationMenu(String navigationMenuId) {
        return navigationMenuRepository.delNavigationMenu(navigationMenuId);
    }

    @Override
    public boolean updateNavigationMenu(NavigationMenuDTO navigationMenuDTO) {
        NavigationMenuEntity navigationMenuEntity = navigationMenuRepository.getNavigationMenu(navigationMenuDTO.getNavigationId());
        if(navigationMenuEntity!=null){
            navigationMenuEntity.setNavigationId(navigationMenuDTO.getNavigationId());
            navigationMenuEntity.setNavigationName(navigationMenuDTO.getNavigationName());
            navigationMenuEntity.setNavigationUrl(navigationMenuDTO.getNavigationUrl());
            String fileName = imgService.uploadAdminImage(navigationMenuDTO.getNavigationImg(), ImgType.ACTIVITY);
            //图片地址特殊处理
            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            navigationMenuEntity.setNavigationImg(fileName);
            return navigationMenuRepository.updateNavigationMenu(navigationMenuEntity);
        }
        return false;
    }

    @Override
    public NavigationMenuDTO getNavigationMenu(String navigationMenuId) {
        NavigationMenuEntity navigationMenuEntity = navigationMenuRepository.getNavigationMenu(navigationMenuId);
        NavigationMenuDTO NavigationMenuDTO=new NavigationMenuDTO();
        if(navigationMenuEntity!=null){
            NavigationMenuDTO.setNavigationId(navigationMenuEntity.getNavigationId());
            NavigationMenuDTO.setNavigationName(navigationMenuEntity.getNavigationName());
            NavigationMenuDTO.setNavigationUrl(navigationMenuEntity.getNavigationUrl());
            NavigationMenuDTO.setImgUrl(navigationMenuEntity.getNavigationImg());
        }
        return NavigationMenuDTO;
    }

    @Override
    public List<NavigationMenuDTO> getAllnavigationMenus(NavigationMenuDTO navigationMenuDTO, WebPage webPage) {
        NavigationMenuEntity NavigationMenuEntity=new NavigationMenuEntity();
       /* if(navigationMenuDTO!=null){
        }*/
         List<NavigationMenuEntity> NavigationMenuEntitys= navigationMenuRepository.getAllnavigationMenus(NavigationMenuEntity,webPage);
       List<NavigationMenuDTO> navigationMenuDTOs = new ArrayList<>();
        if (NavigationMenuEntitys.size() > 0) {
            for (NavigationMenuEntity navigationMenuEntity : NavigationMenuEntitys) {
                NavigationMenuDTO NavigationMenuDTO=new NavigationMenuDTO();
                NavigationMenuDTO.setNavigationId(navigationMenuEntity.getNavigationId());
                NavigationMenuDTO.setNavigationName(navigationMenuEntity.getNavigationName());
                NavigationMenuDTO.setNavigationUrl(navigationMenuEntity.getNavigationUrl());
                NavigationMenuDTO.setImgUrl(navigationMenuEntity.getNavigationImg());
                navigationMenuDTOs.add(NavigationMenuDTO);
            }
        }
        return navigationMenuDTOs;
    }

    @Override
    public ApiResult queryList() throws GeneralException {
        List<Map> maps = new ArrayList<Map>();
        List<NavigationMenuEntity> NavigationMenuEntitys= navigationMenuRepository.getAllnavigationMenus();
        for (NavigationMenuEntity navigationMenuEntity : NavigationMenuEntitys) {
            Map map = new HashMap();
            map.put("navigationId",navigationMenuEntity.getNavigationId());
            map.put("navigationName", navigationMenuEntity.getNavigationName());
            map.put("navigationUrl", navigationMenuEntity.getNavigationUrl());
            map.put("navigationImg", navigationMenuEntity.getNavigationImg());
            maps.add(map);
        }
        ApiResult apiResult = new SuccessApiResult(maps);
        return apiResult;
    }
}
