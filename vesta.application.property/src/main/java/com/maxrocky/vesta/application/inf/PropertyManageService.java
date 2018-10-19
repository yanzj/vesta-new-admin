package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyManageDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 产权管理Service
 * Created by WeiYangDong on 2017/6/22.
 */
public interface PropertyManageService {

    /**
     * 产权导入模板下载
     */
    void downLoadExcelTemplate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;

    /**
     * 产权导入模板及导入数据下载
     */
    void downLoadExcelTemplateAndData(HttpServletResponse response,HttpServletRequest request,PropertyManageDTO propertyManageDTO) throws Exception;

    /**
     * 获取产权管理列表
     */
    List<PropertyManageDTO> getPropertyManageList(PropertyManageDTO propertyManageDTO,WebPage webPage) throws InvocationTargetException, IllegalAccessException;

    /**
     * 导入excel WeiYangDong_2017-06-28
     * @param user 操作人
     * @param fis 输入流
     * @return boolean
     */
    Map<String,Object> importEmployeeByPoi(UserPropertyStaffEntity user, InputStream fis);
}
