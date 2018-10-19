package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyContractDTO;
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
 * @author WeiYangDong
 * @date 2018/3/8 16:52
 * @deprecated 合同查询模块Service
 */
public interface PropertyContractService {

    /**
     * 合同导入模板下载
     */
    void downLoadExcelTemplate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;

    /**
     * 合同导入模板及导入数据下载
     */
    void downLoadExcelTemplateAndData(HttpServletResponse response,HttpServletRequest request,PropertyContractDTO propertyContractDTO) throws Exception;

    /**
     * 获取合同管理列表
     */
    List<PropertyContractDTO> getPropertyContractList(PropertyContractDTO propertyContractDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException;

    /**
     * 导入excel WeiYangDong_2018-03-08
     * @param user 操作人
     * @param fis 输入流
     * @return boolean
     */
    Map<String,Object> importEmployeeByPoi(UserPropertyStaffEntity user, InputStream fis);
}
