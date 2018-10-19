package com.maxrocky.vesta.application.basic.impl;

import com.maxrocky.vesta.application.basic.DTO.AssessTempDTO;
import com.maxrocky.vesta.application.basic.DTO.AssessTempExcelDTO;
import com.maxrocky.vesta.application.basic.inf.BasicService;
import com.maxrocky.vesta.domain.baisc.model.SecurityAssessmentTempEntity;
import com.maxrocky.vesta.domain.baisc.repository.BasicRepository;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.ExportExcel;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * Created by Jason on 2017/6/15.
 */
@Service
public class BasicServiceImpl implements BasicService {
    @Autowired
    private BasicRepository basicRepository;

    @Override
    public String importAssessTempExcel(InputStream fis, String domain, UserInformationEntity userInformationEntity) {
        SecurityAssessmentTempEntity securityAssessmentTempEntity = new SecurityAssessmentTempEntity();
        SecurityAssessmentTempEntity securityAssessmentTempEntity1 = new SecurityAssessmentTempEntity();
        SecurityAssessmentTempEntity assessmentTempEntity = new SecurityAssessmentTempEntity();
        SecurityAssessmentTempEntity assessmentTempEntity1 = new SecurityAssessmentTempEntity();
        try {
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                sheet = hwb.getSheetAt(i);
                //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    if (!StringUtil.isEmpty(row.getCell(0).getRichStringCellValue().getString())) {
                        //考核项目
                        String assessmentName = row.getCell(0).getRichStringCellValue().getString().trim();
                        //根据名称查询数据库中是否有这条数据
                        assessmentTempEntity = basicRepository.getSecurityAssessByName("1", "", assessmentName, domain);
                        //如果已经存在，使用已存在的数据ID
                        if (assessmentTempEntity != null) {
                            assessmentTempEntity.setModifyDate(new Date());
                            assessmentTempEntity.setModifyBy(userInformationEntity.getStaffId());
                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                                assessmentTempEntity.setAssessmentScore(row.getCell(1).getStringCellValue());//考核分数
                            } else {
                                return "错误！数据不规范！ 第" + j + 1 + "行第2列数据有空";
                            }
                            if(!StringUtil.isEmpty(row.getCell(7).getRichStringCellValue().getString())){
                                //是否加分
                                String isBonusPoints = row.getCell(7).getRichStringCellValue().getString().trim();
                                if(isBonusPoints.equals("是")){
                                    assessmentTempEntity.setIsBonusPoints("1");
                                }else {
                                    assessmentTempEntity.setIsBonusPoints("0");
                                }
                            }else {
                                assessmentTempEntity.setIsBonusPoints("0");
                            }
                            basicRepository.updateEntity(assessmentTempEntity);
                            securityAssessmentTempEntity.setAssessmentId(assessmentTempEntity.getAssessmentId());
                        } else {//如果不存在，创建ID
                            securityAssessmentTempEntity.setAssessmentId(IdGen.uuid());
                            securityAssessmentTempEntity.setAssessmentName(assessmentName);
                            securityAssessmentTempEntity.setDomain(domain);
                            securityAssessmentTempEntity.setState("1");
                            securityAssessmentTempEntity.setLevel("1");
                            securityAssessmentTempEntity.setCreateBy(userInformationEntity.getStaffId());
                            securityAssessmentTempEntity.setModifyBy(userInformationEntity.getStaffId());
                            securityAssessmentTempEntity.setCreateDate(new Date());
                            securityAssessmentTempEntity.setModifyDate(new Date());
                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                                securityAssessmentTempEntity.setAssessmentScore(row.getCell(1).getStringCellValue());//考核分数
                            } else {
                                return "错误！数据不规范！ 第" + j + 1 + "行第2列数据有空";
                            }
                            if(!StringUtil.isEmpty(row.getCell(7).getRichStringCellValue().getString())){
                                //是否加分
                                String isBonusPoints = row.getCell(7).getRichStringCellValue().getString().trim();
                                if(isBonusPoints.equals("是")){
                                    securityAssessmentTempEntity.setIsBonusPoints("1");
                                }else {
                                    securityAssessmentTempEntity.setIsBonusPoints("0");
                                }
                            }else {
                                securityAssessmentTempEntity.setIsBonusPoints("0");
                            }
                            basicRepository.addEntity(securityAssessmentTempEntity);//处理一级内容
                        }
                    } else {
                        return "错误！数据不规范！ 第" + j + 1 + "行第1列数据有空";
                    }
                    if (!StringUtil.isEmpty(row.getCell(3).getRichStringCellValue().getString())) {
                        //考核内容
                        String content = row.getCell(3).getRichStringCellValue().getString().trim();
                        assessmentTempEntity1 = basicRepository.getSecurityAssessByContent("2", securityAssessmentTempEntity.getAssessmentId(), content, domain);
                        securityAssessmentTempEntity1.setParentId(securityAssessmentTempEntity.getAssessmentId());
                        if (assessmentTempEntity1 != null) {
                            assessmentTempEntity1.setModifyBy(userInformationEntity.getStaffId());
                            assessmentTempEntity1.setModifyDate(new Date());
                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                                assessmentTempEntity1.setContentNumber(row.getCell(2).getStringCellValue());//排序号
                            } else {
                                return "错误！数据不规范！ 第" + j + 1 + "行第3列数据有空";
                            }
                            if (row.getCell(4) != null) {
                                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                assessmentTempEntity1.setGrade(row.getCell(4).getStringCellValue());//级别
                            } else {
                                assessmentTempEntity1.setGrade("");
                            }
                            if (!StringUtil.isEmpty(row.getCell(5).getRichStringCellValue().getString())) {
                                assessmentTempEntity1.setDeductionPrinciple(row.getCell(5).getRichStringCellValue().getString().trim());//扣分原则
                            } else {
                                return "错误！数据不规范！ 第" + j + 1 + "行第6列数据有空";
                            }
                            if(!StringUtil.isEmpty(row.getCell(6).getRichStringCellValue().getString())){
                                //是否红线
                                String isRedLine = row.getCell(6).getRichStringCellValue().getString().trim();
                                if(isRedLine.equals("是")){
                                    assessmentTempEntity1.setIsRedLine("1");
                                }else {
                                    assessmentTempEntity1.setIsRedLine("0");
                                }
                            }else {
                                assessmentTempEntity1.setIsRedLine("0");
                            }
                            basicRepository.updateEntity(assessmentTempEntity1);
                            securityAssessmentTempEntity1.setAssessmentId(assessmentTempEntity1.getAssessmentId());
                        } else {
                            securityAssessmentTempEntity1.setAssessmentId(IdGen.uuid());
                            securityAssessmentTempEntity1.setAssessmentContent(content);
                            securityAssessmentTempEntity1.setState("1");
                            securityAssessmentTempEntity1.setLevel("2");
                            securityAssessmentTempEntity1.setDomain(domain);
                            securityAssessmentTempEntity1.setCreateBy(userInformationEntity.getStaffId());
                            securityAssessmentTempEntity1.setModifyBy(userInformationEntity.getStaffId());
                            securityAssessmentTempEntity1.setCreateDate(new Date());
                            securityAssessmentTempEntity1.setModifyDate(new Date());
                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                                securityAssessmentTempEntity1.setContentNumber(row.getCell(2).getStringCellValue());//排序号
                            } else {
                                return "错误！数据不规范！ 第" + j + 1 + "行第3列数据有空";
                            }
                            if (row.getCell(4) != null) {
                                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                securityAssessmentTempEntity1.setGrade(row.getCell(4).getStringCellValue());//级别
                            } else {
                                securityAssessmentTempEntity1.setGrade("");
                            }
                            if (!StringUtil.isEmpty(row.getCell(5).getRichStringCellValue().getString())) {
                                securityAssessmentTempEntity1.setDeductionPrinciple(row.getCell(5).getRichStringCellValue().getString().trim());//扣分原则
                            } else {
                                return "错误！数据不规范！ 第" + j + 1 + "行第6列数据有空";
                            }
                            if(!StringUtil.isEmpty(row.getCell(6).getRichStringCellValue().getString())){
                                //是否红线
                                String isRedLine = row.getCell(6).getRichStringCellValue().getString().trim();
                                if(isRedLine.equals("是")){
                                    securityAssessmentTempEntity1.setIsRedLine("1");
                                }else {
                                    securityAssessmentTempEntity1.setIsRedLine("0");
                                }
                            }else {
                                securityAssessmentTempEntity1.setIsRedLine("0");
                            }
                            basicRepository.addEntity(securityAssessmentTempEntity1);
                        }
                    } else {
                        return "错误！数据不规范！ 第" + j + 1 + "行第4列数据有空";
                    }
                }
            }
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "导入模板出错！";
        }
    }

    @Override
    public List<AssessTempDTO> getAssessTempList(AssessTempDTO assessTempDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("domain", assessTempDTO.getDomain());
        int pageCount = basicRepository.getCount(map);
        webPage.setRecordCount(pageCount);
        List<Object[]> securityAssessmentTempEntityList = basicRepository.getAssessTempList(map, webPage);
        List<AssessTempDTO> assessTempDTOS = new ArrayList<AssessTempDTO>();
        if (securityAssessmentTempEntityList != null && securityAssessmentTempEntityList.size() > 0) {
            for (Object[] obj : securityAssessmentTempEntityList) {
                AssessTempDTO assessTempDTO1 = new AssessTempDTO();
                assessTempDTO1.setAssessmentId(obj[0].toString());
                assessTempDTO1.setAssessmentName(obj[1].toString());
                assessTempDTO1.setAssessmentScore(obj[2].toString());
                assessTempDTO1.setContentNumber(obj[3].toString());
                assessTempDTO1.setAssessmentContent(obj[4].toString());
                assessTempDTO1.setGrade(obj[5].toString());
                assessTempDTO1.setDeductionPrinciple(obj[6].toString());
                if ((obj[7].toString()).equals("1")) {
                    assessTempDTO1.setDomain("区域");
                } else if ((obj[7].toString()).equals("2")) {
                    assessTempDTO1.setDomain("项目公司");
                } else if ((obj[7].toString()).equals("3")) {
                    assessTempDTO1.setDomain("监理");
                } else if ((obj[7].toString()).equals("4")) {
                    assessTempDTO1.setDomain("施工单位");
                }
                assessTempDTOS.add(assessTempDTO1);
            }
        }
        return assessTempDTOS;
    }

    @Override
    public String exportModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        BufferedInputStream buffer = null;
        OutputStream out = null;
        HttpSession session = httpServletRequest.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "AssessmentTemp.xlsx";
        File file = new File(filePath);
        if (!file.exists()) {
            return "File not found!";
        }
        try {
            buffer = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int len = 0;
            httpServletResponse.reset(); //非常重要
            httpServletResponse.setContentType("application/x-msdownload");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
            out = httpServletResponse.getOutputStream();
            while ((len = buffer.read(buf)) > 0)
                out.write(buf, 0, len);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                out.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return "ok";
    }

    @Override
    public void exportAssessmentTempExcel(String title, String[] headers, ServletOutputStream out, AssessTempDTO assessTempDTO, UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        map.put("domain", assessTempDTO.getDomain());
        List<Object[]> assessmentTempList = basicRepository.getAssessTempList(map);
        // 导出数据
        ExportExcel<AssessTempExcelDTO> ex = new ExportExcel<AssessTempExcelDTO>();

        List<AssessTempExcelDTO> assessTempExcelDTOS = new ArrayList<AssessTempExcelDTO>();

        if (assessmentTempList != null && assessmentTempList.size() > 0) {
//            int num = 1;
            for (Object[] obj : assessmentTempList) {
                AssessTempExcelDTO assessTempExcelDTO = new AssessTempExcelDTO();
                assessTempExcelDTO.setAssessmentName(obj[1].toString());
                assessTempExcelDTO.setAssessmentScore(obj[2].toString());
                assessTempExcelDTO.setContentNumber(obj[3].toString());
                assessTempExcelDTO.setAssessmentContent(obj[4].toString());
                assessTempExcelDTO.setGrade(obj[5].toString());
                assessTempExcelDTO.setDeductionPrinciple(obj[6].toString());

                assessTempExcelDTOS.add(assessTempExcelDTO);
            }
        }
        ex.exportExcel(title, headers, assessTempExcelDTOS, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }
}
