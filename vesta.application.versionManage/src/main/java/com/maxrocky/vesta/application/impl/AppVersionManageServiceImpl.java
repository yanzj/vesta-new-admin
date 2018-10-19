package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.AppVersionDTO;
import com.maxrocky.vesta.application.DTO.QualityAppVersionExcelDTO;
import com.maxrocky.vesta.application.DTO.QualityAppVersionManageDTO;
import com.maxrocky.vesta.application.inf.AppVersionManageService;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.ExportExcel;
import com.maxrocky.vesta.domain.model.AppVersionEntity;
import com.maxrocky.vesta.domain.model.AppVersionInfoEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.AppVersionManageRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2016/11/3.
 */
@Service
public class AppVersionManageServiceImpl implements AppVersionManageService {
    @Autowired
    AppVersionManageRepository appVersionManageRepository;

    @Override
    public List<QualityAppVersionManageDTO> getVersionListByType(QualityAppVersionManageDTO qualityAppVersionManageDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("appEdition", qualityAppVersionManageDTO.getAppEdition());//版本类型
        map.put("versionType", qualityAppVersionManageDTO.getAppVersionType());//系统类型
        map.put("versionName", qualityAppVersionManageDTO.getAppVersionName());//版本名称
        map.put("startDate", qualityAppVersionManageDTO.getBeginDate());//开始时间
        map.put("endDate", qualityAppVersionManageDTO.getEndDate());//结束时间
        List<AppVersionInfoEntity> appVersionInfoEntityList = appVersionManageRepository.getVersionListByType(map, webPage);
        List<QualityAppVersionManageDTO> qualityAppVersionManageDTOs = new ArrayList<QualityAppVersionManageDTO>();
        if (appVersionInfoEntityList != null && appVersionInfoEntityList.size() > 0) {
            for (AppVersionInfoEntity appVersionInfoEntity : appVersionInfoEntityList) {
                QualityAppVersionManageDTO qualityAppVersionManageDTO1 = new QualityAppVersionManageDTO();
                qualityAppVersionManageDTO1.setAppVersionId(appVersionInfoEntity.getAppVersionId());//版本id
                qualityAppVersionManageDTO1.setAppVersionName(appVersionInfoEntity.getAppVersionName() == null ? "" : appVersionInfoEntity.getAppVersionName());//版本名称
                qualityAppVersionManageDTO1.setAppVersionType(appVersionInfoEntity.getAppVersionType() == null ? "" : appVersionInfoEntity.getAppVersionType());//系统类型
                qualityAppVersionManageDTO1.setAppVersionNum(appVersionInfoEntity.getAppVersionNum() == null ? "" : appVersionInfoEntity.getAppVersionNum());//版本号
                qualityAppVersionManageDTO1.setAppVersionStatus(appVersionInfoEntity.getAppVersionStatus() == null ? "" : appVersionInfoEntity.getAppVersionStatus());//更新类型
                qualityAppVersionManageDTO1.setFileSize(appVersionInfoEntity.getFileSize() == null ? "" : appVersionInfoEntity.getFileSize());//文件大小
                qualityAppVersionManageDTO1.setCreateBy(appVersionInfoEntity.getCreateBy() == null ? "" : appVersionInfoEntity.getCreateBy());//发布人
                qualityAppVersionManageDTO1.setAppRemark(appVersionInfoEntity.getAppRemark() == null ? "" : appVersionInfoEntity.getAppRemark());//发布记录
                qualityAppVersionManageDTO1.setCreateOn(DateUtils.format(appVersionInfoEntity.getCreateOn()) == null ? "" : DateUtils.format(appVersionInfoEntity.getCreateOn()));//发布时间
                qualityAppVersionManageDTO1.setModifyOn(DateUtils.format(appVersionInfoEntity.getModifyOn()) == null ? "" : DateUtils.format(appVersionInfoEntity.getModifyOn()));//修改时间
                qualityAppVersionManageDTO1.setModifyBy(appVersionInfoEntity.getModifyBy() == null ? "" : appVersionInfoEntity.getModifyBy());//修改人
                qualityAppVersionManageDTO1.setDeleteOn(DateUtils.format(appVersionInfoEntity.getDeleteOn()) == null ? "" : DateUtils.format(appVersionInfoEntity.getDeleteOn()));//删除时间
                qualityAppVersionManageDTO1.setDeleteBy(appVersionInfoEntity.getDeleteBy() == null ? "" : appVersionInfoEntity.getDeleteBy());//删除人
                qualityAppVersionManageDTO1.setState(appVersionInfoEntity.getState() == null ? "" : appVersionInfoEntity.getState());//是否有效
                qualityAppVersionManageDTO1.setDownUrl(appVersionInfoEntity.getDownUrl() == null ? "" : appVersionInfoEntity.getDownUrl());//下载地址
                qualityAppVersionManageDTO1.setAppEdition(appVersionInfoEntity.getAppEdition() == null ? "" : appVersionInfoEntity.getAppEdition());//app类型
                qualityAppVersionManageDTOs.add(qualityAppVersionManageDTO1);
            }
        }
        return qualityAppVersionManageDTOs;
    }

    @Override
    public boolean saveAppVersion(QualityAppVersionManageDTO appVersionDTO) {
        AppVersionInfoEntity appVersionEntity = new AppVersionInfoEntity();
        appVersionEntity.setAppVersionId(IdGen.uuid());//id
        appVersionEntity.setAppEdition(appVersionDTO.getAppEdition());//app类型
        appVersionEntity.setAppVersionType(appVersionDTO.getAppVersionType());//系统类型
        appVersionEntity.setAppVersionNum(appVersionDTO.getAppVersionNum());//版本号
        appVersionEntity.setAppVersionStatus(appVersionDTO.getAppVersionStatus());//是否推荐升级
        appVersionEntity.setCreateBy(appVersionDTO.getCreateBy());//发布人
        appVersionEntity.setCreateOn(DateUtils.getDate());//发布时间
        appVersionEntity.setModifyBy(appVersionDTO.getCreateBy());//修改人
        appVersionEntity.setModifyOn(DateUtils.getDate());//修改时间
        appVersionEntity.setState(AppVersionInfoEntity.STATE_ON);//发布状态
        appVersionEntity.setDownUrl(appVersionDTO.getDownUrl());//下载地址
        appVersionEntity.setAppVersionName(appVersionDTO.getAppVersionName());//版本名称
        appVersionEntity.setFileSize(appVersionDTO.getFileSize());//文件大小
        appVersionEntity.setAppRemark(appVersionDTO.getAppRemark());//更新记录
        return appVersionManageRepository.saveAppVersion(appVersionEntity);
    }

    @Override
    public QualityAppVersionManageDTO getVersionById(String appVersionId) {
        AppVersionInfoEntity appVersionInfoEntity = appVersionManageRepository.getVersionById(appVersionId);
        QualityAppVersionManageDTO qualityAppVersionManageDTO = new QualityAppVersionManageDTO();
        if (appVersionInfoEntity != null) {
            qualityAppVersionManageDTO.setAppVersionId(appVersionInfoEntity.getAppVersionId());//版本id
            qualityAppVersionManageDTO.setAppVersionName(appVersionInfoEntity.getAppVersionName() == null ? "" : appVersionInfoEntity.getAppVersionName());//版本名称
            qualityAppVersionManageDTO.setAppVersionType(appVersionInfoEntity.getAppVersionType() == null ? "" : appVersionInfoEntity.getAppVersionType());//版本类型
            qualityAppVersionManageDTO.setAppVersionNum(appVersionInfoEntity.getAppVersionNum() == null ? "" : appVersionInfoEntity.getAppVersionNum());//版本号
            qualityAppVersionManageDTO.setAppVersionStatus(appVersionInfoEntity.getAppVersionStatus() == null ? "" : appVersionInfoEntity.getAppVersionStatus());//更新类型
            qualityAppVersionManageDTO.setFileSize(appVersionInfoEntity.getFileSize() == null ? "" : appVersionInfoEntity.getFileSize());//文件大小
            qualityAppVersionManageDTO.setCreateBy(appVersionInfoEntity.getCreateBy() == null ? "" : appVersionInfoEntity.getCreateBy());//发布人
            qualityAppVersionManageDTO.setAppRemark(appVersionInfoEntity.getAppRemark() == null ? "" : appVersionInfoEntity.getAppRemark());//发布记录
            qualityAppVersionManageDTO.setCreateOn(DateUtils.format(appVersionInfoEntity.getCreateOn()) == null ? "" : DateUtils.format(appVersionInfoEntity.getCreateOn()));//发布时间
            qualityAppVersionManageDTO.setModifyOn(DateUtils.format(appVersionInfoEntity.getModifyOn()) == null ? "" : DateUtils.format(appVersionInfoEntity.getModifyOn()));//修改时间
            qualityAppVersionManageDTO.setModifyBy(appVersionInfoEntity.getModifyBy() == null ? "" : appVersionInfoEntity.getModifyBy());//修改人
            qualityAppVersionManageDTO.setDeleteOn(DateUtils.format(appVersionInfoEntity.getDeleteOn()) == null ? "" : DateUtils.format(appVersionInfoEntity.getDeleteOn()));//删除时间
            qualityAppVersionManageDTO.setDeleteBy(appVersionInfoEntity.getDeleteBy() == null ? "" : appVersionInfoEntity.getDeleteBy());//删除人
            qualityAppVersionManageDTO.setState(appVersionInfoEntity.getState() == null ? "" : appVersionInfoEntity.getState());//是否有效
            qualityAppVersionManageDTO.setDownUrl(appVersionInfoEntity.getDownUrl() == null ? "" : appVersionInfoEntity.getDownUrl());//下载地址
            qualityAppVersionManageDTO.setAppEdition(appVersionInfoEntity.getAppEdition() == null ? "" : appVersionInfoEntity.getAppEdition());
        }
        return qualityAppVersionManageDTO;
    }

    @Override
    public QualityAppVersionManageDTO getNewVersionByAppVersionType(String appVersionType,String appEdition) {
        AppVersionInfoEntity appVersionInfoEntity = appVersionManageRepository.getNewVersionByAppVersionType(appVersionType,appEdition);
        QualityAppVersionManageDTO qualityAppVersionManageDTO = new QualityAppVersionManageDTO();
        if (appVersionInfoEntity != null) {
            qualityAppVersionManageDTO.setAppVersionId(appVersionInfoEntity.getAppVersionId());//版本id
            qualityAppVersionManageDTO.setAppVersionName(appVersionInfoEntity.getAppVersionName() == null ? "" : appVersionInfoEntity.getAppVersionName());//版本名称
            if (!StringUtil.isEmpty(appVersionInfoEntity.getAppVersionType())) {
                if (appVersionInfoEntity.getAppVersionType().equals("1")) {
                    qualityAppVersionManageDTO.setAppVersionType("iOS");
                } else if (appVersionInfoEntity.getAppVersionType().equals("2")) {
                    qualityAppVersionManageDTO.setAppVersionType("android");
                }
            }
//            qualityAppVersionManageDTO.setAppVersionType(appVersionInfoEntity.getAppVersionType()==null?"":appVersionInfoEntity.getAppVersionType());//版本类型
            if (!StringUtil.isEmpty(appVersionInfoEntity.getAppEdition())) {
                if (appVersionInfoEntity.getAppEdition().equals("1")) {
                    qualityAppVersionManageDTO.setAppEdition("交付APP");
                } else if (appVersionInfoEntity.getAppEdition().equals("2")) {
                    qualityAppVersionManageDTO.setAppEdition("工程APP");
                }
            }
            qualityAppVersionManageDTO.setAppVersionNum(appVersionInfoEntity.getAppVersionNum() == null ? "" : appVersionInfoEntity.getAppVersionNum());//版本号
            qualityAppVersionManageDTO.setAppVersionStatus(appVersionInfoEntity.getAppVersionStatus() == null ? "" : appVersionInfoEntity.getAppVersionStatus());//更新类型
            qualityAppVersionManageDTO.setFileSize(appVersionInfoEntity.getFileSize() == null ? "" : appVersionInfoEntity.getFileSize());//文件大小
            qualityAppVersionManageDTO.setCreateBy(appVersionInfoEntity.getCreateBy() == null ? "" : appVersionInfoEntity.getCreateBy());//发布人
            qualityAppVersionManageDTO.setAppRemark(appVersionInfoEntity.getAppRemark() == null ? "" : appVersionInfoEntity.getAppRemark());//发布记录
            qualityAppVersionManageDTO.setCreateOn(DateUtils.format(appVersionInfoEntity.getCreateOn()) == null ? "" : DateUtils.format(appVersionInfoEntity.getCreateOn()));//发布时间
            qualityAppVersionManageDTO.setModifyOn(DateUtils.format(appVersionInfoEntity.getModifyOn()) == null ? "" : DateUtils.format(appVersionInfoEntity.getModifyOn()));//修改时间
            qualityAppVersionManageDTO.setModifyBy(appVersionInfoEntity.getModifyBy() == null ? "" : appVersionInfoEntity.getModifyBy());//修改人
            qualityAppVersionManageDTO.setDeleteOn(DateUtils.format(appVersionInfoEntity.getDeleteOn()) == null ? "" : DateUtils.format(appVersionInfoEntity.getDeleteOn()));//删除时间
            qualityAppVersionManageDTO.setDeleteBy(appVersionInfoEntity.getDeleteBy() == null ? "" : appVersionInfoEntity.getDeleteBy());//删除人
            if (!StringUtil.isEmpty(appVersionInfoEntity.getState())) {//是否有效
                if (appVersionInfoEntity.getState().equals("1")) {
                    qualityAppVersionManageDTO.setState("有效");
                } else {
                    qualityAppVersionManageDTO.setState("无效");
                }
            }
//            qualityAppVersionManageDTO.setState(appVersionInfoEntity.getState()==null?"":appVersionInfoEntity.getState());//是否有效
            qualityAppVersionManageDTO.setDownUrl(appVersionInfoEntity.getDownUrl() == null ? "" : appVersionInfoEntity.getDownUrl());//下载地址
        }
        return qualityAppVersionManageDTO;
    }

    @Override
    public boolean updateAppVersion(QualityAppVersionManageDTO appVersionDTO) {
        AppVersionInfoEntity appVersionInfoEntity = appVersionManageRepository.getVersionById(appVersionDTO.getAppVersionId());
        if (appVersionInfoEntity != null) {
            appVersionInfoEntity.setAppVersionNum(appVersionDTO.getAppVersionNum());//版本号
            appVersionInfoEntity.setAppVersionStatus(appVersionDTO.getAppVersionStatus());//是否推荐升级
            appVersionInfoEntity.setModifyBy(appVersionDTO.getModifyBy());//修改人
            appVersionInfoEntity.setModifyOn(DateUtils.getDate());//修改时间
            appVersionInfoEntity.setDownUrl(appVersionDTO.getDownUrl());//下载地址
            appVersionInfoEntity.setAppVersionName(appVersionDTO.getAppVersionName());//版本名称
            appVersionInfoEntity.setFileSize(appVersionDTO.getFileSize());//文件大小
            appVersionInfoEntity.setAppRemark(appVersionDTO.getAppRemark());//更新记录
            return appVersionManageRepository.updateAppVersion(appVersionInfoEntity);
        }
        return false;
    }

    @Override
    public boolean delAppVersion(String appVersionId, UserInformationEntity userInformationEntity) {
        AppVersionInfoEntity appVersionInfoEntity = appVersionManageRepository.getVersionById(appVersionId);
        if (appVersionInfoEntity != null) {
            appVersionInfoEntity.setState(AppVersionInfoEntity.STATE_OFF);//无效
            if (userInformationEntity.getStaffName() != null) {
                appVersionInfoEntity.setDeleteBy(userInformationEntity.getStaffName());
            } else if (userInformationEntity.getUserName() != null) {
                appVersionInfoEntity.setDeleteBy(userInformationEntity.getUserName());
            }
            appVersionInfoEntity.setDeleteOn(DateUtils.getDate());//删除时间
            return appVersionManageRepository.updateAppVersion(appVersionInfoEntity);
        }
        return false;
    }

    public List<QualityAppVersionExcelDTO> getVersionListByTypeToExcel(QualityAppVersionManageDTO qualityAppVersionManageDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("versionType", qualityAppVersionManageDTO.getAppVersionType());//版本类型
        map.put("versionName", qualityAppVersionManageDTO.getAppVersionName());//版本名称
        map.put("startDate", qualityAppVersionManageDTO.getBeginDate());//开始时间
        map.put("endDate", qualityAppVersionManageDTO.getEndDate());//结束时间
        List<AppVersionInfoEntity> appVersionInfoEntityList = appVersionManageRepository.getVersionListByType(map, webPage);
        List<QualityAppVersionExcelDTO> qualityAppVersionExcelDTOs = new ArrayList<QualityAppVersionExcelDTO>();
        if (appVersionInfoEntityList != null && appVersionInfoEntityList.size() > 0) {
            int number = 1;
            for (AppVersionInfoEntity appVersionInfoEntity : appVersionInfoEntityList) {
                QualityAppVersionExcelDTO qualityAppVersionExcelDTO = new QualityAppVersionExcelDTO();
                qualityAppVersionExcelDTO.setSerialNumber(number++);//编号
                if (!StringUtil.isEmpty(appVersionInfoEntity.getAppVersionType())) {
                    if (appVersionInfoEntity.getAppVersionType().equals("1")) {
                        qualityAppVersionExcelDTO.setAppVersionType("iOS");
                    } else {
                        qualityAppVersionExcelDTO.setAppVersionType("Android");
                    }
                }
//                qualityAppVersionExcelDTO.setAppVersionType(appVersionInfoEntity.getAppVersionType() == null ? "" : appVersionInfoEntity.getAppVersionType());//版本类型
                if (!StringUtil.isEmpty(appVersionInfoEntity.getAppEdition())) {
                    if (appVersionInfoEntity.getAppEdition().equals("1")) {
                        qualityAppVersionExcelDTO.setAppEdition("交付APP");
                    } else {
                        qualityAppVersionExcelDTO.setAppEdition("工程APP");
                    }
                }
//                qualityAppVersionExcelDTO.setAppEdition(appVersionInfoEntity.getAppEdition() == null ? "" : appVersionInfoEntity.getAppEdition());
                qualityAppVersionExcelDTO.setAppVersionName(appVersionInfoEntity.getAppVersionName() == null ? "" : appVersionInfoEntity.getAppVersionName());//版本名称
                qualityAppVersionExcelDTO.setAppVersionNum(appVersionInfoEntity.getAppVersionNum() == null ? "" : appVersionInfoEntity.getAppVersionNum());//版本号
                qualityAppVersionExcelDTO.setFileSize(appVersionInfoEntity.getFileSize() == null ? "" : appVersionInfoEntity.getFileSize());//文件大小
                qualityAppVersionExcelDTO.setCreateBy(appVersionInfoEntity.getCreateBy() == null ? "" : appVersionInfoEntity.getCreateBy());//发布人
                qualityAppVersionExcelDTO.setCreateOn(DateUtils.format(appVersionInfoEntity.getCreateOn()) == null ? "" : DateUtils.format(appVersionInfoEntity.getCreateOn()));//发布时间
                qualityAppVersionExcelDTO.setDownUrl(appVersionInfoEntity.getDownUrl() == null ? "" : appVersionInfoEntity.getDownUrl());//下载地址

                qualityAppVersionExcelDTOs.add(qualityAppVersionExcelDTO);
            }
        }
        return qualityAppVersionExcelDTOs;
    }

    @Override
    public void exportExcel(String title, String[] headers, ServletOutputStream out, QualityAppVersionManageDTO qualityAppVersionManageDTO, WebPage webPage) {
        List<QualityAppVersionExcelDTO> qualityAppVersionExcelDTOs = this.getVersionListByTypeToExcel(qualityAppVersionManageDTO, webPage);
        // 导出数据
        ExportExcel<QualityAppVersionExcelDTO> ex = new ExportExcel<QualityAppVersionExcelDTO>();
        ex.exportExcel(title, headers, qualityAppVersionExcelDTOs, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

}
