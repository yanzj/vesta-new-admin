package com.maxrocky.vesta.application;

import com.maxrocky.vesta.application.admin.dto.BillInfoAdminListResDto;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.BillInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.model.ViewAppHouseInfoEntity;
import com.maxrocky.vesta.domain.repository.BillInfoRepository;
import com.maxrocky.vesta.domain.repository.PaymentedRepository;
import com.maxrocky.vesta.domain.repository.ViewAppHouseInfoRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuwei on 2016/3/5.
 */
@Service
public class BillInfoServiceImpl implements BillInfoService {

    @Autowired
    BillInfoRepository billInfoRepository;

    @Autowired
    ViewAppHouseInfoRepository viewAppHouseInfoRepository;

    @Autowired
    PaymentedRepository paymentedRepository;

    @Override
    public List<BillInfoAdminListResDto> getBillInfoList(String projectId, WebPage webPage) {

        if (StringUtil.isEmpty(projectId)) {
            return null;
        }
        List<BillInfoAdminListResDto> billInfoAdminListResDtos = new ArrayList<>();
        List<BillInfoEntity> billInfoEntities = billInfoRepository.getBillInfosByProject(projectId, webPage);
        if (billInfoEntities != null && billInfoEntities.size() > 0) {
            billInfoEntities.forEach(billInfoEntity -> {
                ViewAppHouseInfoEntity viewAppHouseInfoEntity = viewAppHouseInfoRepository.getHomeInfoByHouseId(billInfoEntity.getHouseId());
                billInfoAdminListResDtos.add(new BillInfoAdminListResDto().setBillInfoId(billInfoEntity.getId())
                        .setPayerName(billInfoEntity.getPayerUserName())
                        .setPayerPhone(billInfoEntity.getPayerPhone())
                        .setHouseInfo(viewAppHouseInfoEntity == null ? "" : viewAppHouseInfoEntity.getAddress())
                        .setPayTime(billInfoEntity.getCreateTime())
                        .setBillSendWay(billInfoEntity.getSendWay())
                        .setBillTitle(billInfoEntity.getBillInfo())
                        .setProcessStatus(billInfoEntity.getProcessStatusDes())
                        .setProcessUser(billInfoEntity.getProcessUserName())
                        .setProcessTime(billInfoEntity.getProcessTime())
                        .setProcessStatusValue(billInfoEntity.getProcessStatus())
                );
            });
        }

        return billInfoAdminListResDtos;
    }

    @Override
    public ApiResult updateBillInfoStatus(UserPropertyStaffEntity userPropertystaffEntity, String billInfoId) throws Exception {
        if(StringUtil.isEmpty(billInfoId)){
            throw new Exception("billinfoId为空");
        }
        BillInfoEntity billInfoEntity = billInfoRepository.getBillInfoById(billInfoId);

        if(billInfoEntity == null){
            throw  new Exception("未查询到账单信息");
        }

        billInfoEntity.setProcessStatus(BillInfoEntity.BillInfoProcessStatus.proccess_did);
        billInfoEntity.setProcessUserId(userPropertystaffEntity.getStaffId());
        billInfoEntity.setProcessUserName(userPropertystaffEntity.getStaffName());
        billInfoEntity.setProcessTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
        this.billInfoRepository.updateBillInfo(billInfoEntity);

        return new SuccessApiResult();
    }

    @Override
    public String exportExcel(UserPropertyStaffEntity userPropertystaffEntity, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {

        OutputStream outputStream = httpServletResponse.getOutputStream();
        String projectId = userPropertystaffEntity.getQueryScope();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        List<BillInfoAdminListResDto> billInfoAdminListResDtos = getBillInfoList(projectId,webPage);

        if(billInfoAdminListResDtos != null && billInfoAdminListResDtos.size() > 0){

            billInfoAdminListResDtos.forEach(billInfoAdminListResDto -> {

                // 创建一个workbook 对应一个excel应用文件
                XSSFWorkbook workBook = new XSSFWorkbook();
                // 在workbook中添加一个sheet,对应Excel文件中的sheet
                // 房屋列表
                XSSFSheet sheet = workBook.createSheet("发票信息");

                ExportUtil exportUtil = new ExportUtil(workBook, sheet);

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


                XSSFCellStyle headStyle = exportUtil.getHeadStyle();
                XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();

                String[] titles = {"序号","缴费人","联系方式","房产","缴费时间","发票配送","发票抬头","处理状态","处理人","处理时间"};
                XSSFRow headRow = sheet.createRow(0);
                XSSFCell cell = null;
                for (int i = 0; i < titles.length; i++)
                {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (billInfoAdminListResDtos.size()>0){
                    for (int i =0;i<billInfoAdminListResDtos.size();i++){
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        BillInfoAdminListResDto houseInfoEntity = billInfoAdminListResDtos.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i+1);//发票编号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(houseInfoEntity.getPayerName());

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(houseInfoEntity.getPayerPhone());
//
//                        cell = bodyRow.createCell(3);
//                        cell.setCellStyle(bodyStyle);// 表格黑色边框
//                        cell.setCellValue(houseInfoEntity.getPayType());

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(houseInfoEntity.getHouseInfo());

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(houseInfoEntity.getPayTime());

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(houseInfoEntity.getBillSendWay());

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(houseInfoEntity.getBillTitle());

                        cell = bodyRow.createCell(7);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(houseInfoEntity.getProcessStatus());

                        cell = bodyRow.createCell(8);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(houseInfoEntity.getProcessUser());

                        cell = bodyRow.createCell(9);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(houseInfoEntity.getProcessTime());



                    }
                }
                try {
                    //String fileName = new String(("发票信息列表").getBytes(), "ISO8859_1");
                    String fileName = new String(("发票信息列表").getBytes(), "ISO8859-1");
                    String agent = httpServletRequest.getHeader("USER-AGENT");
                    if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                            && -1 != agent.indexOf("Trident")) {// ie

                        fileName = java.net.URLEncoder.encode("发票信息列表", "UTF8");
                    }
                    httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                    workBook.write(outputStream);
                    outputStream.flush();
                    outputStream.close();
                }  catch (IOException e)  {
                    e.printStackTrace();
                }finally  {
                    try{
                        outputStream.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }



            });
        }

        return "";

    }
}
