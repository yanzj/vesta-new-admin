package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.AppVersionDTO;
import com.maxrocky.vesta.domain.model.AppVersionEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/24.
 */
public interface AppVersionService {
    /**
     * 根据手机类型获取版本列表
     * @param appVersionDTO
     * @return
     */
    public List<AppVersionDTO> getVersionByType(AppVersionDTO appVersionDTO,WebPage webPage);

    /**
     * 根据手机系统类型获取最新版本号
     * @param appVersionType
     * @return
     */
    public AppVersionDTO getNewVersionByType(String appVersionType);

    /**
     * 添加版本信息
     * @param appVersionDTO
     * @return
     */
    public boolean saveAppVersion(AppVersionDTO appVersionDTO);

    /**
     * 修改版本消息
     * @param appVersionDTO
     * @return
     */
    public boolean updateAppVersion(AppVersionDTO appVersionDTO);

    /**
     * 根据id获取版本信息
     * @param id
     * @return
     */
    public AppVersionDTO getVersionBy(String id,String userId);

    /**
     *
     * @param appId
     * @param propertyStaffEntity
     * @return
     */
    public boolean delAppVersion(String appId,UserPropertyStaffEntity propertyStaffEntity);

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(UserPropertyStaffEntity user,AppVersionDTO appVersionDTO, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

    /**
     * 通过appVersionType获得版本详情
     * 2016-07-19_Wyd
     * @param appVersionType IOS:1,安卓:2
     * @return AppVersionEntity
     */
    AppVersionEntity getAppVersionByAppType(String appVersionType);
}
