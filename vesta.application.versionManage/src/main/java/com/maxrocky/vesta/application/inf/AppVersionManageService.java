package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.AppVersionDTO;
import com.maxrocky.vesta.application.DTO.QualityAppVersionManageDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by Talent on 2016/11/3.
 */
public interface AppVersionManageService {
    /**
     * 分页查询app版本列表
     *
     * @param qualityAppVersionManageDTO
     * @param webPage
     * @return
     */
    List<QualityAppVersionManageDTO> getVersionListByType(QualityAppVersionManageDTO qualityAppVersionManageDTO, WebPage webPage);

    /**
     * 添加版本信息
     *
     * @param appVersionDTO
     */
    boolean saveAppVersion(QualityAppVersionManageDTO appVersionDTO);

    /**
     * 根据ID查询版本信息
     *
     * @param appVersionId
     * @return
     */
    QualityAppVersionManageDTO getVersionById(String appVersionId);
    /**
     * 根据版本类型查询最新版本信息
     *
     * @param appVersionType
     * @return
     */
    QualityAppVersionManageDTO getNewVersionByAppVersionType(String appVersionType,String appEdition);
    /**
     * 编辑版本信息
     *
     * @param appVersionDTO
     * @return
     */
    boolean updateAppVersion(QualityAppVersionManageDTO appVersionDTO);

    /**
     * 删除版本信息
     *
     * @param appVersionId
     * @param userInformationEntity
     */
    boolean delAppVersion(String appVersionId, UserInformationEntity userInformationEntity);

    /**
     * 导出excel
     *
     * @param title
     * @param headers
     * @param out
     * @param qualityAppVersionManageDTO
     * @param webPage
     */
    void exportExcel(String title, String[] headers, ServletOutputStream out, QualityAppVersionManageDTO qualityAppVersionManageDTO, WebPage webPage);
}
