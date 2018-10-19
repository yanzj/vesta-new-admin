package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.AppVersionDTO;
import com.maxrocky.vesta.application.inf.AppVersionService;
import com.maxrocky.vesta.domain.model.AppVersionEntity;
import com.maxrocky.vesta.domain.model.ClickUsersEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.AppVersionRepository;
import com.maxrocky.vesta.domain.repository.ClickUserRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/24.
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {

    @Autowired
    private AppVersionRepository appVersionRepository;
    @Autowired
    private ClickUserRepository clickUserRepository;

    /**
     * 获取某类型版本列表
     *
     * @param versionDTO
     * @param webPage
     * @return
     */
    @Override
    public List<AppVersionDTO> getVersionByType(AppVersionDTO versionDTO, WebPage webPage) {

        AppVersionEntity appVersion = new AppVersionEntity();
        if (versionDTO != null) {
            appVersion.setAppVersionType(versionDTO.getAppVersionType());//版本类型
            appVersion.setAppVersionName(versionDTO.getAppVersionName());
            appVersion.setBeginDate(versionDTO.getBeginDate());
            appVersion.setEndDate(versionDTO.getEndDate());
        }
        List<AppVersionEntity> appVersionEntities = appVersionRepository.getVersionByType(appVersion, webPage);
        List<AppVersionDTO> appVersionDTOs = new ArrayList<>();
        if (appVersionEntities.size() > 0) {
            for (AppVersionEntity appVersionEntity : appVersionEntities) {
                AppVersionDTO appVersionDTO = new AppVersionDTO();
                appVersionDTO.setAppVersionId(appVersionEntity.getAppVersionId());//版本id
                appVersionDTO.setAppVersionType(appVersionEntity.getAppVersionType());//版本类型
                appVersionDTO.setAppVersion(appVersionEntity.getAppVersion());//版本号
                appVersionDTO.setAppVersionName(appVersionEntity.getAppVersionName());//版本名称
                appVersionDTO.setAppVersionStatus(appVersionEntity.getAppVersionStatus());//更新类型
                appVersionDTO.setFileSize(appVersionEntity.getFileSize());//文件大小
                appVersionDTO.setCreateBy(appVersionEntity.getCreateBy());//发布人
                appVersionDTO.setAppRemark(appVersionEntity.getAppRemark());//发布记录
                appVersionDTO.setCreateOn(DateUtils.format(appVersionEntity.getCreateOn()));//发布时间
                appVersionDTO.setModifyOn(DateUtils.format(appVersionEntity.getModifyOn()));//修改时间
                appVersionDTO.setModifyBy(appVersionEntity.getModifyBy());//修改人
                appVersionDTO.setDeleteOn(DateUtils.format(appVersionEntity.getDeleteOn()));//删除时间
                appVersionDTO.setDeleteBy(appVersionEntity.getDeleteBy());//删除人
                appVersionDTO.setState(appVersionEntity.getState());//是否发布
                appVersionDTO.setDownUrl(appVersionEntity.getDownUrl());//下载地址
                appVersionDTO.setUserType(appVersionEntity.getUserType());//用户类型
                appVersionDTOs.add(appVersionDTO);
            }
        }
        return appVersionDTOs;
    }

    /**
     * 获得手机系统类型最新版本信息
     *
     * @param appVersionType
     * @return
     */
    @Override
    public AppVersionDTO getNewVersionByType(String appVersionType) {
        AppVersionEntity appVersionEntity = appVersionRepository.getAppVersionByAppType(appVersionType);
        AppVersionDTO appVersionDTO = new AppVersionDTO();
        appVersionDTO.setAppVersionId(appVersionEntity.getAppVersionId());//版本id
        if (!StringUtil.isEmpty(appVersionEntity.getAppVersionType())) {//版本类型
            if (appVersionEntity.getAppVersionType().equals("1")) {
                appVersionDTO.setAppVersionType("iOS");
            } else if (appVersionEntity.getAppVersionType().equals("2")) {
                appVersionDTO.setAppVersionType("android");
            }
        }
//        if (!StringUtil.isEmpty(appVersionEntity.getAppVersionStatus())) {//更新类型
//            if (appVersionEntity.getAppVersionStatus().equals("1")) {
//                appVersionDTO.setAppVersionStatus("推荐升级");
//            } else if (appVersionEntity.getAppVersionStatus().equals("2")) {
//                appVersionDTO.setAppVersionStatus("强制升级");
//            } else {
//                appVersionDTO.setAppVersionStatus("不需要升级");
//            }
//        }
        appVersionDTO.setAppVersion(appVersionEntity.getAppVersion() == null ? "" : appVersionEntity.getAppVersion());//版本号
        appVersionDTO.setAppVersionName(appVersionEntity.getAppVersionName() == null ? "" : appVersionEntity.getAppVersionName());//版本名称
//        appVersionDTO.setAppVersionType(appVersionEntity.getAppVersionType() == null ? "" : appVersionEntity.getAppVersionType());//版本类型

        appVersionDTO.setAppVersionStatus(appVersionEntity.getAppVersionStatus() == null ? "" : appVersionEntity.getAppVersionStatus());//更新类型
        appVersionDTO.setFileSize(appVersionEntity.getFileSize() == null ? "" : appVersionEntity.getFileSize());//文件大小
        appVersionDTO.setCreateBy(appVersionEntity.getCreateBy() == null ? "" : appVersionEntity.getCreateBy());//发布人
        appVersionDTO.setAppRemark(appVersionEntity.getAppRemark() == null ? "" : appVersionEntity.getAppRemark());//发布记录
        appVersionDTO.setCreateOn(DateUtils.format(appVersionEntity.getCreateOn()) == null ? "" : DateUtils.format(appVersionEntity.getCreateOn()));//发布时间
        appVersionDTO.setModifyOn(DateUtils.format(appVersionEntity.getModifyOn()) == null ? "" : DateUtils.format(appVersionEntity.getModifyOn()));//修改时间
        appVersionDTO.setModifyBy(appVersionEntity.getModifyBy() == null ? "" : appVersionEntity.getModifyBy());//修改人
        appVersionDTO.setDeleteOn(DateUtils.format(appVersionEntity.getDeleteOn()) == null ? "" : DateUtils.format(appVersionEntity.getDeleteOn()));//删除时间
        appVersionDTO.setDeleteBy(appVersionEntity.getDeleteBy() == null ? "" : appVersionEntity.getDeleteBy());//删除人
        if (!StringUtil.isEmpty(appVersionEntity.getState())) {//是否有效
            if (appVersionEntity.getState().equals("1")) {
                appVersionDTO.setState("有效");
            } else {
                appVersionDTO.setState("无效");
            }
        }
//        appVersionDTO.setState(appVersionEntity.getState() == null ? "" : appVersionEntity.getState());//是否发布
        appVersionDTO.setDownUrl(appVersionEntity.getDownUrl() == null ? "" : appVersionEntity.getDownUrl());//下载地址
        appVersionDTO.setUserType(appVersionEntity.getUserType() == null ? "" : appVersionEntity.getUserType());//用户类型
        return appVersionDTO;
    }

    /**
     * 保存版本信息
     *
     * @param appVersionDTO
     * @return
     */
    @Override
    public boolean saveAppVersion(AppVersionDTO appVersionDTO) {
        AppVersionEntity appVersionEntity = new AppVersionEntity();
        appVersionEntity.setAppVersionId(IdGen.getSixRandom() + "");//id
        appVersionEntity.setAppVersionType(appVersionDTO.getAppVersionType());//手机类型
        appVersionEntity.setAppVersion(appVersionDTO.getAppVersion());//版本号
        appVersionEntity.setAppVersionStatus(appVersionDTO.getAppVersionStatus());//是否推荐升级
        appVersionEntity.setCreateBy(appVersionDTO.getCreateBy());//发布人
        appVersionEntity.setCreateOn(DateUtils.getDate());//发布时间
        appVersionEntity.setModifyBy(appVersionDTO.getCreateBy());//修改人
        appVersionEntity.setModifyOn(DateUtils.getDate());//修改时间
        appVersionEntity.setState(AppVersionEntity.STATE_ON);//发布状态
        appVersionEntity.setDownUrl(appVersionDTO.getDownUrl());//下载地址
        appVersionEntity.setAppVersionName(appVersionDTO.getAppVersionName());//版本名称
        appVersionEntity.setFileSize(appVersionDTO.getFileSize());//文件大小
        appVersionEntity.setAppRemark(appVersionDTO.getAppRemark());//更新记录
        return appVersionRepository.saveAppVersion(appVersionEntity);
    }

    /**
     * 更新版本信息
     *
     * @param appVersionDTO
     * @return
     */
    @Override
    public boolean updateAppVersion(AppVersionDTO appVersionDTO) {
        AppVersionEntity appVersionEntity = appVersionRepository.getVersionBy(appVersionDTO.getAppVersionId());
        if (appVersionEntity != null) {
            appVersionEntity.setAppVersion(appVersionDTO.getAppVersion());//版本号
            appVersionEntity.setAppVersionStatus(appVersionDTO.getAppVersionStatus());//是否推荐升级
            appVersionEntity.setModifyBy(appVersionDTO.getModifyBy());//修改人
            appVersionEntity.setModifyOn(DateUtils.getDate());//修改时间
            appVersionEntity.setDownUrl(appVersionDTO.getDownUrl());//下载地址
            appVersionEntity.setAppVersionName(appVersionDTO.getAppVersionName());//版本名称
            appVersionEntity.setFileSize(appVersionDTO.getFileSize());//文件大小
            appVersionEntity.setAppRemark(appVersionDTO.getAppRemark());//更新记录
            /* 补充修改APP系统类型 2016-07-07_Wyd */
            appVersionEntity.setAppVersionType(appVersionDTO.getAppVersionType());
            return appVersionRepository.updateAppVersion(appVersionEntity);
        }
        return false;
    }

    /**
     * 获得某版本详情
     *
     * @param id
     * @return
     */
    @Override
    public AppVersionDTO getVersionBy(String id, String userId) {
        AppVersionEntity appVersionEntity = appVersionRepository.getVersionBy(id);
        AppVersionDTO appVersionDTO = new AppVersionDTO();
        if (appVersionEntity != null) {
            appVersionDTO.setAppVersionId(appVersionEntity.getAppVersionId());//版本id
            appVersionDTO.setAppVersionName(appVersionEntity.getAppVersionName());//版本名称
            appVersionDTO.setAppVersionType(appVersionEntity.getAppVersionType());//版本类型
            appVersionDTO.setAppVersion(appVersionEntity.getAppVersion());//版本号
            appVersionDTO.setAppVersionStatus(appVersionEntity.getAppVersionStatus());//更新类型
            appVersionDTO.setCreateBy(appVersionEntity.getCreateBy());//发布人
            appVersionDTO.setAppRemark(appVersionEntity.getAppRemark());//发布记录
            appVersionDTO.setCreateOn(DateUtils.format(appVersionEntity.getCreateOn()));//发布时间
            appVersionDTO.setModifyOn(DateUtils.format(appVersionEntity.getModifyOn()));//修改时间
            appVersionDTO.setModifyBy(appVersionEntity.getModifyBy());//修改人
            appVersionDTO.setDeleteOn(DateUtils.format(appVersionEntity.getDeleteOn()));//删除时间
            appVersionDTO.setDeleteBy(appVersionEntity.getDeleteBy());//删除人
            appVersionDTO.setState(appVersionEntity.getState());//是否有效
            appVersionDTO.setDownUrl(appVersionEntity.getDownUrl());//下载地址
            appVersionDTO.setUserType(appVersionEntity.getUserType());//用户类型
            appVersionDTO.setFileSize(appVersionEntity.getFileSize());//文件大小
        }
        //调用点击人统计
        String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
        ClickUsersEntity clickUserEntity = clickUserRepository.getTotalInfo(dateNow, "14", userId);
        if (clickUserEntity == null) {
            ClickUsersEntity clickUser = new ClickUsersEntity();
            clickUser.setId(IdGen.uuid());
            clickUser.setCreateDate(new Date());
            clickUser.setClicks(1);
            clickUser.setUserId(userId);
            clickUser.setForeignId("14");
            clickUserRepository.create(clickUser);
        } else {
            clickUserEntity.setClicks(clickUserEntity.getClicks() + 1);
            clickUserRepository.update(clickUserEntity);
        }
        return appVersionDTO;
    }

    /**
     * 删除版本
     *
     * @param appId
     * @param propertyStaffEntity
     * @return
     */
    @Override
    public boolean delAppVersion(String appId, UserPropertyStaffEntity propertyStaffEntity) {
        AppVersionEntity appVersionEntity = appVersionRepository.getVersionBy(appId);
        if (appVersionEntity != null) {
            appVersionEntity.setState(AppVersionEntity.STATE_OFF);//无效
            if (propertyStaffEntity.getStaffName() != null) {
                appVersionEntity.setDeleteBy(propertyStaffEntity.getStaffName());
            } else if (propertyStaffEntity.getUserName() != null) {
                appVersionEntity.setDeleteBy(propertyStaffEntity.getUserName());
            }
            appVersionEntity.setDeleteOn(DateUtils.getDate());//删除时间
            return appVersionRepository.updateAppVersion(appVersionEntity);
        }
        return false;
    }


    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user, AppVersionDTO appVersionDTO, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        List<AppVersionDTO> AppVersionDTO = getVersionByType(appVersionDTO, webPage);

        XSSFSheet sheet = workBook.createSheet("版本信息列表");

        ExportUtil exportUtil = new ExportUtil(workBook, sheet);
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();

        // 百分比
        XSSFDataFormat fmt = workBook.createDataFormat();
        XSSFDataFormat fmt1 = workBook.createDataFormat();

        // 四个边框加粗
        XSSFCellStyle style1 = workBook.createCellStyle();
        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
        XSSFFont font = workBook.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        style1.setFont(font);

        String[] titles = {"序号", "类型", "版本名称", "版本号", "文件大小", "发布人", "发布时间"};
        XSSFRow headRow = sheet.createRow(0);


        if (AppVersionDTO.size() > 0) {

            AppVersionDTO.forEach(userDTO -> {

                XSSFCell cell = null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (AppVersionDTO.size() > 0) {
                    for (int i = 0; i < AppVersionDTO.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        AppVersionDTO appVersion = AppVersionDTO.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if (appVersion.getAppVersionType().equals("1")) {
                            appVersion.setAppVersionType("IOS");
                        } else if (appVersion.getAppVersionType().equals("2")) {
                            appVersion.setAppVersionType("Android");
                        }
                        cell.setCellValue(appVersion.getAppVersionType());//类型

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(appVersion.getAppVersionName());//版本名称

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(appVersion.getAppVersion());//版本号

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(appVersion.getFileSize());//文件大小

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(appVersion.getCreateBy());//发布人

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(appVersion.getCreateOn());//发布时间
                    }
                }
            });
        }
        try {
            //String fileName = new String(("版本信息管理").getBytes(), "ISO8859_1");
            String fileName = new String(("版本信息管理").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("版本信息管理", "UTF8");
            }
            httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* --------------------APP接口Service-------------------- */

    /**
     * 通过appVersionType获得版本详情
     * 2016-07-19_Wyd
     *
     * @param appVersionType IOS:1,安卓:2
     * @return AppVersionEntity
     */
    public AppVersionEntity getAppVersionByAppType(String appVersionType) {
        return appVersionRepository.getAppVersionByAppType(appVersionType);
    }

}
