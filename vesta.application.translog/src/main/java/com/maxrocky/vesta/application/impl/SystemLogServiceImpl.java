package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.ExportExcelAddUserLogDTO;
import com.maxrocky.vesta.application.AdminDTO.ExportExcelInfoReleaseDTO;
import com.maxrocky.vesta.application.AdminDTO.ExportExcelUserVisitLogDTO;
import com.maxrocky.vesta.application.AdminDTO.SystemLogDTO;
import com.maxrocky.vesta.application.inf.SystemLogService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.HouseProjectRepository;
import com.maxrocky.vesta.domain.repository.SystemLogRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportExcel;
import com.maxrocky.vesta.utility.ExportUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/7/19.
 */
@Service
public class SystemLogServiceImpl implements SystemLogService {
    @Autowired
    SystemLogRepository systemLogRepository;
    @Autowired
    HouseProjectRepository houseProjectRepository;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;

    @Override
    public List<SystemLogDTO> getSystemLogList(SystemLogDTO systemLogDTO, WebPage webPage) {
        SystemLogEntity systemLogEntity = new SystemLogEntity();
        systemLogEntity.setProjectId(systemLogDTO.getProjectId());
        systemLogEntity.setUserType(systemLogDTO.getUserType());
        systemLogEntity.setSourceFrom(systemLogDTO.getSourceFrom());
        systemLogEntity.setUserMobile(systemLogDTO.getUserMobile());
        systemLogEntity.setLogType(systemLogDTO.getLogType());
        List<SystemLogEntity> systemLogEntities = systemLogRepository.getSystemLogList(systemLogEntity,systemLogDTO.getBeginTime(),systemLogDTO.getEndTime(),webPage);
        List<SystemLogDTO> systemLogDTOs = new ArrayList<SystemLogDTO>();
        if(systemLogEntities!=null){
            SystemLogDTO systemLogDTO1;
            for(SystemLogEntity systemLogEntity1:systemLogEntities){
                systemLogDTO1 = new SystemLogDTO();
                systemLogDTO1.setLogId(systemLogEntity1.getLogId());
                systemLogDTO1.setUserName(systemLogEntity1.getUserName());
                if(systemLogEntity1.getProjectId()!=null){
                    HouseProjectEntity houseProjectEntity = houseProjectRepository.get(systemLogEntity1.getProjectId());
                    if(houseProjectEntity!=null){
                        systemLogDTO1.setProjectName(houseProjectEntity.getName());
                    }
                }
                systemLogDTO1.setUserMobile(systemLogEntity1.getUserMobile());
                systemLogDTO1.setSourceFrom(systemLogEntity1.getSourceFrom());
                systemLogDTO1.setUserType(systemLogEntity1.getUserType());
                systemLogDTO1.setDocNum(systemLogEntity1.getDocNum());
                systemLogDTO1.setIdCard(systemLogEntity1.getIdCard());
                systemLogDTO1.setSysVersion(systemLogEntity1.getSysVersion());
                systemLogDTO1.setThisFunction(systemLogEntity1.getThisFunction());
                systemLogDTO1.setLogTime(DateUtils.format(systemLogEntity1.getLogTime()));
                systemLogDTO1.setLogContent(systemLogEntity1.getLogContent());
                systemLogDTO1.setUserType(systemLogEntity1.getUserType());
                systemLogDTO1.setThisStatus(systemLogEntity1.getThisStatus());
                systemLogDTO1.setThisSection(systemLogEntity1.getThisSection());
                systemLogDTO1.setProjectName(systemLogEntity1.getProjectId());
                systemLogDTOs.add(systemLogDTO1);
            }
        }
        return systemLogDTOs;
    }

    @Override
    public List<SystemLogDTO> getuserVisitlLogList(SystemLogDTO systemLogDTO, WebPage webPage) {
        UserVisitLogEntity userVisitLog=new UserVisitLogEntity();
        userVisitLog.setProjectId(systemLogDTO.getProjectId());
        userVisitLog.setUserType(systemLogDTO.getUserType());
        userVisitLog.setSourceFrom(systemLogDTO.getSourceFrom());
        userVisitLog.setUserMobile(systemLogDTO.getUserMobile());
        List<UserVisitLogEntity> systemLogEntities = systemLogRepository.getUserVisitLogList(userVisitLog, systemLogDTO.getBeginTime(), systemLogDTO.getEndTime(), webPage);
        List<SystemLogDTO> userVisitLogDTOs = new ArrayList<SystemLogDTO>();
        if(systemLogEntities!=null){
            SystemLogDTO systemLogDTO1;
            for(UserVisitLogEntity systemLogEntity1:systemLogEntities){
                systemLogDTO1 = new SystemLogDTO();
                systemLogDTO1.setLogId(systemLogEntity1.getLogId());
                systemLogDTO1.setUserName(systemLogEntity1.getUserName());
                if(systemLogEntity1.getProjectId()!=null){
                    HouseProjectEntity houseProjectEntity = houseProjectRepository.get(systemLogEntity1.getProjectId());
                    if(houseProjectEntity!=null){
                        systemLogDTO1.setProjectName(houseProjectEntity.getName());
                    }
                }
                systemLogDTO1.setUserMobile(systemLogEntity1.getUserMobile());
                systemLogDTO1.setSourceFrom(systemLogEntity1.getSourceFrom());
                systemLogDTO1.setUserType(systemLogEntity1.getUserType());
                systemLogDTO1.setThisFunction(systemLogEntity1.getThisFunction());
                systemLogDTO1.setLogTime(DateUtils.format(systemLogEntity1.getLogTime()));
                systemLogDTO1.setLogContent(systemLogEntity1.getLogContent());
                systemLogDTO1.setUserType(systemLogEntity1.getUserType());
                systemLogDTO1.setThisSection(systemLogEntity1.getThisSection());
                userVisitLogDTOs.add(systemLogDTO1);
            }
        }
        return userVisitLogDTOs;
    }

    /**
     * 优化新增用户日志列表导出EXCEL
     */
    public void addUserLogExportExcel2(String title, String[] headers, ServletOutputStream out,SystemLogDTO systemLogDTO) throws Exception {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(2000);

        SystemLogEntity systemLogEntity = new SystemLogEntity();
        systemLogEntity.setProjectId(systemLogDTO.getProjectId());
        systemLogEntity.setUserType(systemLogDTO.getUserType());
        systemLogEntity.setSourceFrom(systemLogDTO.getSourceFrom());
        systemLogEntity.setUserMobile(systemLogDTO.getUserMobile());
        List<SystemLogEntity> systemLogEntities = systemLogRepository.getSystemLogList(systemLogEntity, systemLogDTO.getBeginTime(), systemLogDTO.getEndTime(), webPage);

        List<ExportExcelAddUserLogDTO> exportExcelAddUserLogDTOList = new ArrayList<>();
        if (null != systemLogEntities){
            int num = 1;
            ExportExcelAddUserLogDTO exportExcelAddUserLogDTO = null;
            SystemLogEntity systemLog = null;
            for (int i = 0,length = systemLogEntities.size(); i<length; i++){
                systemLog = systemLogEntities.get(i);
                exportExcelAddUserLogDTO = new ExportExcelAddUserLogDTO();
                //序号
                exportExcelAddUserLogDTO.setNum(num ++);
                //注册时间
                exportExcelAddUserLogDTO.setLogTime(systemLog.getLogTime() == null ? "" : DateUtils.format(systemLog.getLogTime(), DateUtils.FORMAT_LONG));
                //用户昵称
                exportExcelAddUserLogDTO.setUserName(systemLog.getUserName() == null ? "" : systemLog.getUserName());
                //用户类型
                if (null != systemLog.getUserType()){
                    String userType = systemLog.getUserType();
                    switch (userType){
                        case "1":
                            exportExcelAddUserLogDTO.setUserType("游客");
                            break;
                        case "2":
                            exportExcelAddUserLogDTO.setUserType("会员");
                            break;
                        case "3":
                            exportExcelAddUserLogDTO.setUserType("业主");
                            break;
                        case "4":
                            exportExcelAddUserLogDTO.setUserType("同住人");
                            break;
                        case "6":
                            exportExcelAddUserLogDTO.setUserType("虚拟用户");
                            break;
                        default:
                            exportExcelAddUserLogDTO.setUserType("无法捕获");
                            break;
                    }
                }else{
                    exportExcelAddUserLogDTO.setUserType("无法捕获");
                }
                //手机号
                exportExcelAddUserLogDTO.setUserMobile(systemLog.getUserMobile() == null ? "" : systemLog.getUserMobile());
                //身份证
                exportExcelAddUserLogDTO.setIdCard(systemLog.getIdCard() == null ? "" : systemLog.getIdCard());
                //注册来源
                if (null != systemLog.getSourceFrom()){
                    String sourceFrom = systemLog.getSourceFrom();
                    switch (sourceFrom){
                        case "1":
                            exportExcelAddUserLogDTO.setSourceFrom("APP");
                            break;
                        case "2":
                            exportExcelAddUserLogDTO.setSourceFrom("微信");
                            break;
                        default:
                            exportExcelAddUserLogDTO.setSourceFrom("无法捕获");
                            break;
                    }
                }else{
                    exportExcelAddUserLogDTO.setSourceFrom("无法捕获");
                }
                //系统版本
                exportExcelAddUserLogDTO.setSysVersion(systemLog.getSysVersion() == null ? "无法捕获" : systemLog.getSysVersion());
                //项目
                exportExcelAddUserLogDTO.setProjectId(systemLog.getProjectId() == null ? "" : systemLog.getProjectId());
                exportExcelAddUserLogDTOList.add(exportExcelAddUserLogDTO);
            }
            ExportExcel<ExportExcelAddUserLogDTO> ex = new ExportExcel<ExportExcelAddUserLogDTO>();
            ex.exportExcel(title, headers, exportExcelAddUserLogDTOList, out, "yyyy-MM-dd");
            System.out.println("excel导出成功！");
        }
    }

    @Override
    public String addUserLogExportExcel(SystemLogDTO systemLogDTO, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {

        OutputStream outputStream = httpServletResponse.getOutputStream();

        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        SystemLogEntity systemLogEntity = new SystemLogEntity();
        systemLogEntity.setProjectId(systemLogDTO.getProjectId());
        systemLogEntity.setUserType(systemLogDTO.getUserType());
        systemLogEntity.setSourceFrom(systemLogDTO.getSourceFrom());
        systemLogEntity.setUserMobile(systemLogDTO.getUserMobile());
        List<SystemLogEntity> systemLogEntities = systemLogRepository.getSystemLogList(systemLogEntity, systemLogDTO.getBeginTime(), systemLogDTO.getEndTime(), webPage);

        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("增加用户日志");
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
        String[] titles = {"序号", "注册时间", "用户昵称", "用户类型", "手机号", "身份证","来源","系统版本","项目"};
        XSSFRow headRow = sheet.createRow(0);

        if (systemLogEntities.size() > 0) {

            systemLogEntities.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (systemLogEntities.size() > 0) {
                    for (int i = 0; i < systemLogEntities.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        SystemLogEntity activityApplyAdmin = systemLogEntities.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                       /* cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getLogTime());//注册时间*/

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String str = sdf.format(activityApplyAdmin.getLogTime());
                        cell.setCellValue(str);//应用时间


                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getUserName());//用户名

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if (null != activityApplyAdmin.getUserType()){
                            String userType = activityApplyAdmin.getUserType();
                            switch (userType){
                                case "1":
                                    cell.setCellValue("游客");
                                    break;
                                case "2":
                                    cell.setCellValue("会员");
                                    break;
                                case "3":
                                    cell.setCellValue("业主");
                                    break;
                                case "4":
                                    cell.setCellValue("同住人");
                                    break;
                                default:
                                    cell.setCellValue("无法捕获");
                                    break;
                            }
                        }else{
                            cell.setCellValue("无法捕获");
                        }

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getUserMobile());//手机号

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getIdCard());//身份证

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if (null != activityApplyAdmin.getSourceFrom()){
                            String sourceFrom = activityApplyAdmin.getSourceFrom();
                            switch (sourceFrom){
                                case "1":
                                    cell.setCellValue("APP");
                                    break;
                                case "2":
                                    cell.setCellValue("微信");
                                    break;
                                default:
                                    cell.setCellValue("无法捕获");
                                    break;
                            }
                        }else{
                            cell.setCellValue("无法捕获");
                        }

                        cell = bodyRow.createCell(7);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if (null != activityApplyAdmin.getSysVersion() && !"".equals(activityApplyAdmin.getSysVersion())){
                            cell.setCellValue(activityApplyAdmin.getSysVersion());//系统版本
                        }else{
                            cell.setCellValue("无法捕获");
                        }

                        cell = bodyRow.createCell(8);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getProjectId());//项目名称


                    }
                }
            });
        }
        try {
            //String fileName = new String(("增加用户日志列表").getBytes(), "ISO8859-1");
            String fileName = new String(("增加用户日志列表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("增加用户日志列表", "UTF8");
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

    /**
     * 优化用户访问日志列表导出EXCEL
     */
    public void userVisitLogExportExcel2(String title, String[] headers, ServletOutputStream out,SystemLogDTO systemLogDTO) throws Exception {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(2000);

        UserVisitLogEntity userVisitLogEntity = new UserVisitLogEntity();
        userVisitLogEntity.setProjectId(systemLogDTO.getProjectId());
        userVisitLogEntity.setUserType(systemLogDTO.getUserType());
        userVisitLogEntity.setSourceFrom(systemLogDTO.getSourceFrom());
        userVisitLogEntity.setUserMobile(systemLogDTO.getUserMobile());
        List<UserVisitLogEntity> systemLogEntities = systemLogRepository.getUserVisitLogList(userVisitLogEntity, systemLogDTO.getBeginTime(), systemLogDTO.getEndTime(), webPage);

        List<ExportExcelUserVisitLogDTO> exportExcelUserVisitLogDTOs = new ArrayList<>();
        if (null != systemLogEntities){
            int num = 1;
            ExportExcelUserVisitLogDTO exportExcelUserVisitLogDTO = null;
            UserVisitLogEntity userVisitLog = null;
            for (int i = 0,length = systemLogEntities.size(); i<length; i++){
                userVisitLog = systemLogEntities.get(i);
                exportExcelUserVisitLogDTO = new ExportExcelUserVisitLogDTO();
                //序号
                exportExcelUserVisitLogDTO.setNum(num ++);
                //注册时间
                exportExcelUserVisitLogDTO.setLogTime(userVisitLog.getLogTime() == null ? "" : DateUtils.format(userVisitLog.getLogTime(), DateUtils.FORMAT_LONG));
                //用户昵称
                exportExcelUserVisitLogDTO.setUserName(userVisitLog.getUserName() == null ? "" : userVisitLog.getUserName());
                //用户类型
                if (null != userVisitLog.getUserType()){
                    String userType = userVisitLog.getUserType();
                    switch (userType){
                        case "1":
                            exportExcelUserVisitLogDTO.setUserType("游客");
                            break;
                        case "2":
                            exportExcelUserVisitLogDTO.setUserType("会员");
                            break;
                        case "3":
                            exportExcelUserVisitLogDTO.setUserType("业主");
                            break;
                        case "4":
                            exportExcelUserVisitLogDTO.setUserType("同住人");
                            break;
                        case "6":
                            exportExcelUserVisitLogDTO.setUserType("虚拟用户");
                            break;
                        default:
                            exportExcelUserVisitLogDTO.setUserType("无法捕获");
                            break;
                    }
                }else{
                    exportExcelUserVisitLogDTO.setUserType("无法捕获");
                }
                //手机号
                exportExcelUserVisitLogDTO.setUserMobile(userVisitLog.getUserMobile() == null ? "" : userVisitLog.getUserMobile());
                //访问版块
                exportExcelUserVisitLogDTO.setThisSection(userVisitLog.getThisSection() == null ? "" : userVisitLog.getThisSection());
                //访问功能
                exportExcelUserVisitLogDTO.setThisFunction(userVisitLog.getThisFunction() == null ? "" : userVisitLog.getThisFunction());
                //访问内容
                exportExcelUserVisitLogDTO.setLogContent(userVisitLog.getLogContent() == null ? "" : userVisitLog.getLogContent());
                //访问来源
                if (null != userVisitLog.getSourceFrom()){
                    String sourceFrom = userVisitLog.getSourceFrom();
                    switch (sourceFrom){
                        case "1":
                            exportExcelUserVisitLogDTO.setSourceFrom("APP");
                            break;
                        case "2":
                            exportExcelUserVisitLogDTO.setSourceFrom("微信");
                            break;
                        default:
                            exportExcelUserVisitLogDTO.setSourceFrom("无法捕获");
                            break;
                    }
                }else{
                    exportExcelUserVisitLogDTO.setSourceFrom("无法捕获");
                }
                exportExcelUserVisitLogDTOs.add(exportExcelUserVisitLogDTO);
            }
            ExportExcel<ExportExcelUserVisitLogDTO> ex = new ExportExcel<ExportExcelUserVisitLogDTO>();
            ex.exportExcel(title, headers, exportExcelUserVisitLogDTOs, out, "yyyy-MM-dd");
            System.out.println("excel导出成功！");
        }
    }

    @Override
    public String userVisitLogExportExcel(SystemLogDTO systemLogDTO, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();

        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        UserVisitLogEntity userVisitLogEntity = new UserVisitLogEntity();
        userVisitLogEntity.setProjectId(systemLogDTO.getProjectId());
        userVisitLogEntity.setUserType(systemLogDTO.getUserType());
        userVisitLogEntity.setSourceFrom(systemLogDTO.getSourceFrom());
        userVisitLogEntity.setUserMobile(systemLogDTO.getUserMobile());
        List<UserVisitLogEntity> systemLogEntities = systemLogRepository.getUserVisitLogList(userVisitLogEntity, systemLogDTO.getBeginTime(), systemLogDTO.getEndTime(), webPage);

        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("用户访问日志");
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
        String[] titles = {"序号", "访问时间", "用户昵称", "用户类型", "手机号", "访问版块","访问功能","访问内容","访问来源"};
        XSSFRow headRow = sheet.createRow(0);

        if (systemLogEntities.size() > 0) {

            systemLogEntities.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (systemLogEntities.size() > 0) {
                    for (int i = 0; i < systemLogEntities.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        UserVisitLogEntity activityApplyAdmin = systemLogEntities.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String str = sdf.format(activityApplyAdmin.getLogTime());
                        cell.setCellValue(str);//访问时间

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getUserName());//用户昵称

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if (null != activityApplyAdmin.getUserType()){
                            String userType = activityApplyAdmin.getUserType();
                            switch (userType){
                                case "1":
                                    cell.setCellValue("游客");
                                    break;
                                case "2":
                                    cell.setCellValue("会员");
                                    break;
                                case "3":
                                    cell.setCellValue("业主");
                                    break;
                                case "4":
                                    cell.setCellValue("同住人");
                                    break;
                                default:
                                    cell.setCellValue("游客");
                                    break;
                            }
                        }else{
                            cell.setCellValue("游客");
                        }
                        //用户类型

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getUserMobile());//手机号

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getThisSection());//访问版块

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getThisFunction());//访问功能

                        cell = bodyRow.createCell(7);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getLogContent());//访问内容

                        cell = bodyRow.createCell(8);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if (null != activityApplyAdmin.getSourceFrom()){
                            String sourceFrom = activityApplyAdmin.getSourceFrom();
                            switch (sourceFrom){
                                case "1":
                                    cell.setCellValue("APP");
                                    break;
                                case "2":
                                    cell.setCellValue("微信");
                                    break;
                                default:
                                    cell.setCellValue("无法捕获");
                                    break;
                            }
                        }else{
                            cell.setCellValue("无法捕获");
                        }
                        //访问内容
                    }
                }
            });
        }
        try {
            //String fileName = new String(("用户访问日志列表").getBytes(), "ISO8859-1");
            String fileName = new String(("用户访问日志列表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("用户访问日志列表", "UTF8");
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

    @Override
    public List<SystemLogDTO> getuserDocumentsLogList(SystemLogDTO systemLogDTO, WebPage webPage) {
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = systemLogDTO.getRoleScopeList();
        for (Map<String,Object> roleScope : roleScopeList){
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")){
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList){
                    if (!roleScopes.contains(project[1].toString())){
                        roleScopes += "'"+project[1].toString()+"',";
                    }
                }
            }else if (scopeType.equals("2")){
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeName").toString())){
                    roleScopes += "'"+roleScope.get("scopeName")+"',";
                }
            }else if (scopeType.equals("0")){
                //全部城市
                roleScopes += "all,";
            }
        }
        UserDocumentsEntity userDocumentsLog=new UserDocumentsEntity();
//        userDocumentsLog.setProjectId(systemLogDTO.getProjectId());
        //追加区域项目检索条件-Wyd20170407
        //如果检索项目不为Null,直接set userDocumentsLog的projectId
        //如果检索项目为Null,城市不为Null,set userDocumentsLog的projectId为该城市下所有项目Code,逗号间隔
        if (null != systemLogDTO.getProjectCode() && !"全部项目".equals(systemLogDTO.getProjectCode()) && !"".equals(systemLogDTO.getProjectCode())){
            userDocumentsLog.setProjectId("'"+systemLogDTO.getProjectCode()+"',");
        }else if (null != systemLogDTO.getScopeId() && !"0".equals(systemLogDTO.getScopeId()) && !"".equals(systemLogDTO.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(systemLogDTO.getScopeId());
            String projectIds = "";
            for (Object[] project : projectList) {
                projectIds += "'" + project[1].toString() + "',";
            }
            userDocumentsLog.setProjectId(projectIds);
        }
        userDocumentsLog.setUserType(systemLogDTO.getUserType());
        userDocumentsLog.setSourceFrom(systemLogDTO.getSourceFrom());
        userDocumentsLog.setUserMobile(systemLogDTO.getUserMobile());
        userDocumentsLog.setThisType(systemLogDTO.getThisType());
        List<UserDocumentsEntity> systemLogEntities = systemLogRepository.getUserDocumentsLogList(userDocumentsLog, systemLogDTO.getBeginTime(), systemLogDTO.getEndTime(), webPage,roleScopes);
        List<SystemLogDTO> userDocumentsLogDTOs = new ArrayList<SystemLogDTO>();
        if(systemLogEntities!=null){
            SystemLogDTO systemLogDTO1;
            for(UserDocumentsEntity systemLogEntity1:systemLogEntities){
                systemLogDTO1 = new SystemLogDTO();
                systemLogDTO1.setLogId(systemLogEntity1.getLogId());
                systemLogDTO1.setUserName(systemLogEntity1.getUserName());
                if(systemLogEntity1.getProjectId()!=null){
                    HouseProjectEntity houseProjectEntity = houseProjectRepository.get(systemLogEntity1.getProjectId());
                    if(houseProjectEntity!=null){
                        systemLogDTO1.setProjectName(houseProjectEntity.getName());
                    }
                }
                systemLogDTO1.setUserMobile(systemLogEntity1.getUserMobile());
                systemLogDTO1.setSourceFrom(systemLogEntity1.getSourceFrom());
                systemLogDTO1.setUserType(systemLogEntity1.getUserType());
                systemLogDTO1.setThisFunction(systemLogEntity1.getThisFunction());
                systemLogDTO1.setLogTime(DateUtils.format(systemLogEntity1.getLogTime()));
                systemLogDTO1.setLogContent(systemLogEntity1.getLogContent());
                systemLogDTO1.setUserType(systemLogEntity1.getUserType());
                systemLogDTO1.setThisSection(systemLogEntity1.getThisSection());
                systemLogDTO1.setThisType(systemLogEntity1.getThisType());
                systemLogDTO1.setDocNum(systemLogEntity1.getDocNum());
                systemLogDTO1.setProjectName(systemLogEntity1.getProjectId());
                systemLogDTO1.setRealEstate(systemLogEntity1.getRealEstate());
                userDocumentsLogDTOs.add(systemLogDTO1);
            }
        }
        return userDocumentsLogDTOs;
    }

    @Override
    public List<SystemLogDTO> getInfoReleaseLogList(SystemLogDTO systemLogDTO, WebPage webPage) {
        InfoReleaseEntity userDocumentsLog=new InfoReleaseEntity();
        userDocumentsLog.setUserType(systemLogDTO.getUserType());
        userDocumentsLog.setUserMobile(systemLogDTO.getUserMobile());
        List<InfoReleaseEntity> systemLogEntities = systemLogRepository.getInfoReleaseLogList(userDocumentsLog, systemLogDTO.getBeginTime(), systemLogDTO.getEndTime(), webPage);
        List<SystemLogDTO> userDocumentsLogDTOs = new ArrayList<SystemLogDTO>();
        if(systemLogEntities!=null){
            SystemLogDTO systemLogDTO1;
            for(InfoReleaseEntity systemLogEntity1:systemLogEntities){
                systemLogDTO1 = new SystemLogDTO();
                systemLogDTO1.setLogId(systemLogEntity1.getLogId());
                systemLogDTO1.setUserName(systemLogEntity1.getUserName());
                if(systemLogEntity1.getProjectId()!=null){
                    HouseProjectEntity houseProjectEntity = houseProjectRepository.get(systemLogEntity1.getProjectId());
                    if(houseProjectEntity!=null){
                        systemLogDTO1.setProjectName(houseProjectEntity.getName());
                    }
                }
                systemLogDTO1.setUserMobile(systemLogEntity1.getUserMobile());
                systemLogDTO1.setSourceFrom(systemLogEntity1.getSourceFrom());
                systemLogDTO1.setUserType(systemLogEntity1.getUserType());
                systemLogDTO1.setThisFunction(systemLogEntity1.getThisFunction());
                systemLogDTO1.setLogTime(DateUtils.format(systemLogEntity1.getLogTime()));
                systemLogDTO1.setLogContent(systemLogEntity1.getLogContent());
                systemLogDTO1.setUserType(systemLogEntity1.getUserType());
                systemLogDTO1.setThisSection(systemLogEntity1.getThisSection());
                systemLogDTO1.setAsscommunity(systemLogEntity1.getAsscommunity());
                systemLogDTO1.setThisType(systemLogEntity1.getThisType());
                userDocumentsLogDTOs.add(systemLogDTO1);
            }
        }
        return userDocumentsLogDTOs;
    }

    @Override
    public String userDocumentsLogExportExcel(SystemLogDTO systemLogDTO, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();

        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = systemLogDTO.getRoleScopeList();
        for (Map<String,Object> roleScope : roleScopeList){
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")){
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList){
                    if (!roleScopes.contains(project[1].toString())){
                        roleScopes += "'"+project[1].toString()+"',";
                    }
                }
            }else if (scopeType.equals("2")){
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeName").toString())){
                    roleScopes += "'"+roleScope.get("scopeName")+"',";
                }
            }else if (scopeType.equals("0")){
                //全部城市
                roleScopes += "all,";
            }
        }
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        UserDocumentsEntity userVisitLogEntity = new UserDocumentsEntity();
//        userVisitLogEntity.setProjectId(systemLogDTO.getProjectId());
        //追加区域项目检索条件-Wyd20170407
        //如果检索项目不为Null,直接set userDocumentsLog的projectId
        //如果检索项目为Null,城市不为Null,set userDocumentsLog的projectId为该城市下所有项目Code,逗号间隔
        if (null != systemLogDTO.getProjectCode() && !"全部项目".equals(systemLogDTO.getProjectCode()) && !"".equals(systemLogDTO.getProjectCode())){
            userVisitLogEntity.setProjectId("'"+systemLogDTO.getProjectCode()+"',");
        }else if (null != systemLogDTO.getScopeId() && !"0".equals(systemLogDTO.getScopeId()) && !"".equals(systemLogDTO.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(systemLogDTO.getScopeId());
            String projectIds = "";
            for (Object[] project : projectList) {
                projectIds += "'" + project[1].toString() + "',";
            }
            userVisitLogEntity.setProjectId(projectIds);
        }
        userVisitLogEntity.setUserType(systemLogDTO.getUserType());
        userVisitLogEntity.setSourceFrom(systemLogDTO.getSourceFrom());
        userVisitLogEntity.setUserMobile(systemLogDTO.getUserMobile());
        userVisitLogEntity.setThisType(systemLogDTO.getThisType());
        List<UserDocumentsEntity> systemLogEntities = systemLogRepository.getUserDocumentsLogList(userVisitLogEntity, systemLogDTO.getBeginTime(), systemLogDTO.getEndTime(), webPage,roleScopes);

        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("用户单据日志");
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
        String[] titles = {"序号", "应用时间", "用户名", "手机号","单据类型","单据编号","单据内容"};
        XSSFRow headRow = sheet.createRow(0);

        if (systemLogEntities.size() > 0) {

            systemLogEntities.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (systemLogEntities.size() > 0) {
                    for (int i = 0; i < systemLogEntities.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        UserDocumentsEntity activityApplyAdmin = systemLogEntities.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String str = sdf.format(activityApplyAdmin.getLogTime());
                        cell.setCellValue(str);//应用时间
                       // cell.setCellValue(activityApplyAdmin.getLogTime());//应用时间

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getUserName());//用户名

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getUserMobile());//手机号

                     /*   cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if(activityApplyAdmin.getUserType().equals("3")){
                            cell.setCellValue("业主");
                        }else if(activityApplyAdmin.getUserType().equals("4")){
                            cell.setCellValue("同住人");
                        }else{
                            cell.setCellValue("普通用户");
                        }*/

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getThisType());//单据类型

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getDocNum());//单据编号

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getLogContent());//单据内容
                    }
                }
            });
        }
        try {
            //String fileName = new String(("用户单据日志列表").getBytes(), "ISO8859-1");
            String fileName = new String(("用户单据日志列表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("用户单据日志列表", "UTF8");
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

    /**
     * 优化信息发布日志列表导出EXCEL
     */
    public void infoReleaseLogExportExcel2(String title, String[] headers, ServletOutputStream out,SystemLogDTO systemLogDTO) throws Exception {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(2000);

        InfoReleaseEntity userVisitLogEntity = new InfoReleaseEntity();
        userVisitLogEntity.setProjectId(systemLogDTO.getProjectId());
        userVisitLogEntity.setUserType(systemLogDTO.getUserType());
        userVisitLogEntity.setSourceFrom(systemLogDTO.getSourceFrom());
        userVisitLogEntity.setUserMobile(systemLogDTO.getUserMobile());
        List<InfoReleaseEntity> systemLogEntities = systemLogRepository.getInfoReleaseLogList(userVisitLogEntity, systemLogDTO.getBeginTime(), systemLogDTO.getEndTime(), webPage);

        List<ExportExcelInfoReleaseDTO> exportExcelInfoReleaseDTOs = new ArrayList<>();
        if (null != systemLogEntities){
            int num = 1;
            ExportExcelInfoReleaseDTO exportExcelInfoReleaseDTO = null;
            InfoReleaseEntity infoReleaseEntity = null;
            for (int i = 0,length = systemLogEntities.size(); i<length; i++){
                infoReleaseEntity = systemLogEntities.get(i);
                exportExcelInfoReleaseDTO = new ExportExcelInfoReleaseDTO();
                //序号
                exportExcelInfoReleaseDTO.setNum(num++);
                //发布时间
                exportExcelInfoReleaseDTO.setLogTime(infoReleaseEntity.getLogTime() == null ? "" : DateUtils.format(infoReleaseEntity.getLogTime(), DateUtils.FORMAT_LONG));
                //用户名
                exportExcelInfoReleaseDTO.setUserName(infoReleaseEntity.getUserName() == null ? "" : infoReleaseEntity.getUserName());
                //版块
                exportExcelInfoReleaseDTO.setThisSection(infoReleaseEntity.getThisSection() == null ? "" : infoReleaseEntity.getThisSection());
                //功能
                exportExcelInfoReleaseDTO.setThisFunction(infoReleaseEntity.getThisFunction() == null ? "" : infoReleaseEntity.getThisFunction());
                //操作类型
                exportExcelInfoReleaseDTO.setThisType(infoReleaseEntity.getThisType() == null ? "" : infoReleaseEntity.getThisType());
                //关联社区
                exportExcelInfoReleaseDTO.setAsscommunity(infoReleaseEntity.getAsscommunity() == null ? "" : infoReleaseEntity.getAsscommunity());
                //发布内容
                exportExcelInfoReleaseDTO.setLogContent(infoReleaseEntity.getLogContent() == null ? "" : infoReleaseEntity.getLogContent());
                exportExcelInfoReleaseDTOs.add(exportExcelInfoReleaseDTO);
            }
            ExportExcel<ExportExcelInfoReleaseDTO> ex = new ExportExcel<ExportExcelInfoReleaseDTO>();
            ex.exportExcel(title, headers, exportExcelInfoReleaseDTOs, out, "yyyy-MM-dd");
            System.out.println("excel导出成功！");
        }
    }

    @Override
    public String infoReleaseLogExportExcel(SystemLogDTO systemLogDTO, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();

        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        InfoReleaseEntity userVisitLogEntity = new InfoReleaseEntity();
        userVisitLogEntity.setProjectId(systemLogDTO.getProjectId());
        userVisitLogEntity.setUserType(systemLogDTO.getUserType());
        userVisitLogEntity.setSourceFrom(systemLogDTO.getSourceFrom());
        userVisitLogEntity.setUserMobile(systemLogDTO.getUserMobile());
        List<InfoReleaseEntity> systemLogEntities = systemLogRepository.getInfoReleaseLogList(userVisitLogEntity, systemLogDTO.getBeginTime(), systemLogDTO.getEndTime(), webPage);

        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("信息发布日志");
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
        String[] titles = {"序号", "发布时间", "后台用户名","版块","功能","操作类型","关联社区","发布内容"};
        XSSFRow headRow = sheet.createRow(0);

        if (systemLogEntities.size() > 0) {

            systemLogEntities.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (systemLogEntities.size() > 0) {
                    for (int i = 0; i < systemLogEntities.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        InfoReleaseEntity activityApplyAdmin = systemLogEntities.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String str = sdf.format(activityApplyAdmin.getLogTime());
                        cell.setCellValue(str);//应用时间

                       /* cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getLogTime());//应用时间*/

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getUserName());//用户名

                        /*cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getUserMobile());//手机号*/

                     /*   cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if(activityApplyAdmin.getUserType().equals("3")){
                            cell.setCellValue("业主");
                        }else if(activityApplyAdmin.getUserType().equals("4")){
                            cell.setCellValue("同住人");
                        }else{
                            cell.setCellValue("普通用户");
                        }*/

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getThisSection());//访问版块

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getThisFunction());//访问功能

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getThisType());//操作类型

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getAsscommunity());//操作类型

                        cell = bodyRow.createCell(7);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(activityApplyAdmin.getLogContent());//访问内容
                    }
                }
            });
        }
        try {
            //String fileName = new String(("信息发布日志列表").getBytes(), "ISO8859-1");
            String fileName = new String(("信息发布日志列表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("信息发布日志列表", "UTF8");
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

    /**
     * 清除新增用户日志重复数据
     */
    @Override
    public void cleanSystemLogRepeatData(){
        //清除普通用户重复新增记录
        List<Map<String, Object>> systemLogCountList2 = systemLogRepository.getSystemLogListByUserType("2");
        for (Map<String,Object> systemLogCount:systemLogCountList2){
            String userMobile = systemLogCount.get("userMobile").toString();
            List<SystemLogEntity> systemLogList = systemLogRepository.getSystemLogListByUser("2", userMobile);
            if (null != systemLogList && systemLogCount.size() > 1){
                for (int i = 1; i< systemLogList.size(); i++){
                    systemLogRepository.delete(systemLogList.get(i));
                }
            }
        }
        //清除业主用户重复新增记录
        List<Map<String, Object>> systemLogCountList3 = systemLogRepository.getSystemLogListByUserType("3");
        for (Map<String,Object> systemLogCount:systemLogCountList3){
            String userMobile = systemLogCount.get("userMobile").toString();
            List<SystemLogEntity> systemLogList = systemLogRepository.getSystemLogListByUser("3", userMobile);
            if (null != systemLogList && systemLogCount.size() > 1){
                for (int i = 1; i< systemLogList.size(); i++){
                    systemLogRepository.delete(systemLogList.get(i));
                }
            }
        }
    }
}
