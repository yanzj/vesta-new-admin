package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.SystemNotificationDTO;
import com.maxrocky.vesta.application.inf.SystemNotificationService;
import com.maxrocky.vesta.domain.model.SystemNotificationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.SystemNotificationRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/5/21.
 */
@Service
public class SystemNotificationServiceImpl implements SystemNotificationService {

    @Autowired
    private SystemNotificationRepository systemNotificationRepository;

    @Override
    public List<SystemNotificationDTO> querySystemNotification(SystemNotificationDTO systemNotification, WebPage webPage) {
        List<SystemNotificationDTO> systemNotificationDTOList = new ArrayList<SystemNotificationDTO>();
        SystemNotificationEntity systemNotificationEntity = new SystemNotificationEntity();
        if(systemNotification!=null) {
            systemNotificationEntity.setNotificationType(systemNotification.getNotificationType());//系统通知类型
            systemNotificationEntity.setNotificationStatus(systemNotification.getNotificationStatus());//系统通知发布状态
            systemNotificationEntity.setNotificationTopStatus(systemNotification.getNotificationTopStatus());//系统通知置顶状态
            systemNotificationEntity.setNotificationTitle(systemNotification.getNotificationTitle());//系统通知标题
            systemNotificationEntity.setNotificationContent(systemNotification.getNotificationContent());//系统通知内容
            systemNotificationEntity.setNotificationCreater(systemNotification.getNotificationCreater());//发布人
            systemNotificationEntity.setNotificationCreateBeginTime(systemNotification.getNotificationCreateBeginTime());//系统公告查询创建开始时间
            systemNotificationEntity.setNotificationCreateEndTime(systemNotification.getNotificationCreateEndTime());//系统公告查询创建结束时间
        }

        List<SystemNotificationEntity> systemNotificationEntities = systemNotificationRepository.querySystemNotificationEntityList(systemNotificationEntity, webPage);

        for(SystemNotificationEntity systemNotificationEntity1 : systemNotificationEntities){
            SystemNotificationDTO systemNotificationDTO = new SystemNotificationDTO();

            systemNotificationDTO.setNotificationId(systemNotificationEntity1.getNotificationId());//系统通知id
            systemNotificationDTO.setNotificationType(systemNotificationEntity1.getNotificationType());//系统通知类型
//            systemNotificationDTO.setNotificationTopStatus(systemNotificationEntity1.getNotificationTopStatus());//系统通知置顶状态
            systemNotificationDTO.setNotificationStatus(systemNotificationEntity1.getNotificationStatus());//系统通知发布状态

            //置顶状态，将置顶两字放到标题里面
            if(SystemNotificationEntity.NOTIFICATION_TOPSTATUS_Y.equals(systemNotificationEntity1.getNotificationTopStatus())){
                systemNotificationDTO.setNotificationTitle("【置顶】"+systemNotificationEntity1.getNotificationTitle());//系统通知标题
            }else{
                systemNotificationDTO.setNotificationTitle(systemNotificationEntity1.getNotificationTitle());//系统通知标题
            }


            systemNotificationDTO.setNotificationContent(systemNotificationEntity1.getNotificationContent());//系统通知内容
            systemNotificationDTO.setNotificationCreater(systemNotificationEntity1.getNotificationCreater());//系统通知创建人
            systemNotificationDTO.setNotificationCreateTime(DateUtils.format(systemNotificationEntity1.getNotificationCreateTime()));//系统通知创建时间

            systemNotificationDTOList.add(systemNotificationDTO);
        }

        return systemNotificationDTOList;
    }

    @Override
    public String addSystemNotification(SystemNotificationDTO systemNotificationDTO,UserPropertyStaffEntity userProperty) {

        String resultMessage = "0";//失败
        SystemNotificationEntity systemNotificationEntity = new SystemNotificationEntity();

        systemNotificationEntity.setNotificationId(IdGen.getDateId());//系统通知ID
        systemNotificationEntity.setNotificationTitle(systemNotificationDTO.getNotificationTitle());//系统通知标题
        systemNotificationEntity.setNotificationContent(systemNotificationDTO.getNotificationContent()); //系统通知内容
        systemNotificationEntity.setNotificationTopStatus(systemNotificationDTO.getNotificationTopStatus());//系统通知置顶状态
        systemNotificationEntity.setNotificationStatus(systemNotificationDTO.getNotificationStatus()); // 系统通知状态
        systemNotificationEntity.setNotificationType(systemNotificationDTO.getNotificationType()); //系统通知类型
        systemNotificationEntity.setNotificationCreater(userProperty.getStaffName());//系统通知创建人
        systemNotificationEntity.setNotificationCreateTime(DateUtils.getDate());//系统通知创建时间
        systemNotificationEntity.setStatus("1");//有效状态

        if(systemNotificationRepository.saveSystemNotification(systemNotificationEntity)){
            resultMessage = "1"; //成功
        }

        return resultMessage;


    }

    @Override
    public String editSystemNotification(SystemNotificationDTO systemNotificationDTO,UserPropertyStaffEntity userProperty) {
        String resultMessage = "0";//失败

        SystemNotificationEntity systemNotificationEntity = systemNotificationRepository.getSystemNotificationEntity(systemNotificationDTO.getNotificationId());

        if(null != systemNotificationEntity){
            systemNotificationEntity.setNotificationTitle(systemNotificationDTO.getNotificationTitle());//系统通知标题
            systemNotificationEntity.setNotificationContent(systemNotificationDTO.getNotificationContent()); //系统通知内容
            systemNotificationEntity.setNotificationTopStatus(systemNotificationDTO.getNotificationTopStatus());//系统通知置顶状态
            systemNotificationEntity.setNotificationType(systemNotificationDTO.getNotificationType()); //系统通知类型
            systemNotificationEntity.setNotificationCreater(userProperty.getStaffName());//系统通知创建人
            systemNotificationEntity.setNotificationCreateTime(DateUtils.getDate());//系统通知修改时间
            systemNotificationEntity.setNotificationStatus(systemNotificationDTO.getNotificationStatus());//更改发布状态

            if(systemNotificationRepository.updateSystemNotification(systemNotificationEntity)){
                resultMessage = "1";//成功
            }
        }


        return resultMessage;
    }

    @Override
    public String removeSystemNotification(String systemNotificationId,UserPropertyStaffEntity userProperty) {

        String resultMessage = "0";//失败
        if(null != systemNotificationId && !"".equals(systemNotificationId)) {
            SystemNotificationEntity systemNotificationEntity = systemNotificationRepository.getSystemNotificationEntity(systemNotificationId);
            systemNotificationEntity.setNotificationCreater(userProperty.getStaffName());//系统通知创建人
            systemNotificationEntity.setNotificationCreateTime(DateUtils.getDate());//系统通知修改时间
            systemNotificationEntity.setStatus("0");//删除状态
            //物理删除，更改状态
            if(systemNotificationRepository.updateSystemNotification(systemNotificationEntity)){
                resultMessage = "1";
            }
        }

        return resultMessage;
    }

    @Override
    public SystemNotificationDTO getSystemNotificationDTOById(String systemNotificationId) {

        SystemNotificationDTO systemNotificationDTO = new SystemNotificationDTO();
        SystemNotificationEntity systemNotificationEntity = systemNotificationRepository.getSystemNotificationEntity(systemNotificationId);

        systemNotificationDTO.setNotificationId(systemNotificationEntity.getNotificationId());//系统通知id
        systemNotificationDTO.setNotificationType(systemNotificationEntity.getNotificationType());//系统通知类型
        systemNotificationDTO.setNotificationTopStatus(systemNotificationEntity.getNotificationTopStatus());//系统通知置顶状态
        systemNotificationDTO.setNotificationStatus(systemNotificationEntity.getNotificationStatus());//系统通知发布状态
        systemNotificationDTO.setNotificationTitle(systemNotificationEntity.getNotificationTitle());//系统通知标题
        systemNotificationDTO.setNotificationContent(htmlRemoveTag(systemNotificationEntity.getNotificationContent()));//系统通知内容
        systemNotificationDTO.setNotificationCreater(systemNotificationEntity.getNotificationCreater());//系统通知创建人
        systemNotificationDTO.setNotificationCreateTime(DateUtils.format(systemNotificationEntity.getNotificationCreateTime()));//系统通知创建时间

        return systemNotificationDTO;
    }

    /**
     * 通过正则表达式删除Html标签
     * param:含有html标签的字符串
     * createBy:liudongxin
     * return
     */
    public static String htmlRemoveTag(String inputString) {
        if (inputString == null)
            return null;
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        try {
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            p_script = java.util.regex.Pattern.compile(regEx_script, java.util.regex.Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = java.util.regex.Pattern.compile(regEx_style, java.util.regex.Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = java.util.regex.Pattern.compile(regEx_html, java.util.regex.Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;// 返回文本字符串
    }


    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user,SystemNotificationDTO systemNotification,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        List<SystemNotificationDTO> systemDTO = querySystemNotification(systemNotification, webPage);

        XSSFSheet sheet = workBook.createSheet("通知信息列表");

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

        String[] titles = {"序号", "公告类型", "标题", "发布人", "创建时间", "发布状态"};
        XSSFRow headRow = sheet.createRow(0);


        if (systemDTO.size() > 0) {

            systemDTO.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (systemDTO.size() > 0) {
                    for (int i = 0; i < systemDTO.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        SystemNotificationDTO system = systemDTO.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if(system.getNotificationType().equals("10001")){
                            system.setNotificationType("通知");
                        }else if(system.getNotificationType().equals("10000")){
                            system.setNotificationType("其他");
                        }
                        cell.setCellValue(system.getNotificationType());//公告类型

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(system.getNotificationTitle());//标题

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(system.getNotificationCreater());//发布人

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(system.getNotificationCreateTime());//创建时间

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if(system.getNotificationStatus().equals("10001")){
                            system.setNotificationStatus("已发布");
                        }else if(system.getNotificationStatus().equals("10000")){
                            system.setNotificationStatus("未发布");
                        }
                        cell.setCellValue(system.getNotificationStatus());//发布状态
                    }
                }
            });
        }
        try {
           /* String fileName = new String(("通知信息管理").getBytes(), "ISO8859_1");*/
            String fileName = new String(("通知信息管理").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("通知信息管理", "UTF8");
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
}
