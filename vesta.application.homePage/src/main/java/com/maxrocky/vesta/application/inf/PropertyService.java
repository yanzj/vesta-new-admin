package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/26.
 */
public interface PropertyService {
    List<PropertyDTO> PropertyManage(WebPage webPage);
    /****
     * 首页模块排序
     */
    List<PropertyDTO>  Propertysort(String id,String state,WebPage webPage);
}
