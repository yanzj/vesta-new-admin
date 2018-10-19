package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.PropertyElectricDTO;
import com.maxrocky.vesta.application.inf.MessageInsertService;
import com.maxrocky.vesta.application.inf.PropertyElectricService;
import com.maxrocky.vesta.application.DTO.adminDTO.MessageInsertDTO;
import com.maxrocky.vesta.application.inf.UserOwnerService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/23.
 */
@Service
public class PropertyElectricServiceImpl implements PropertyElectricService {

    @Autowired
    private PropertyElectricRepository propertyElectricRepository;
    @Autowired
    private HouseInfoRepository houseInfoRepository;
    @Autowired
    private HouseUserBookRepository houseUserBookRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private ElectricWarnRepository electricWarnRepository;
    @Autowired
    private MessageInsertService messageInsertService;

    /**
     * 获取电量列表
     * @param propertyElectricDTO
     * @param webPage
     * @return
     */
    @Override
    public List<PropertyElectricDTO> listEleDTO(PropertyElectricDTO propertyElectricDTO, WebPage webPage) {
        PropertyElectricEntity propertyElectricEntity = new PropertyElectricEntity();
        if (propertyElectricDTO.getProjectId()!=null&&!"".equals(propertyElectricDTO.getProjectId())){
            propertyElectricEntity.setProjectId(propertyElectricDTO.getProjectId());//项目id
        }
        if (propertyElectricDTO.getHouseNum()!=null&&!"".equals(propertyElectricDTO.getHouseNum())){
            propertyElectricEntity.setHouseNum(propertyElectricEntity.getHouseNum());//房间号
        }
        if (propertyElectricDTO.getEleSign()!=null&&!"".equals(propertyElectricDTO.getEleSign())){
                if (propertyElectricDTO.getElectricQuantity()!=null&&!"".equals(propertyElectricDTO.getElectricQuantity())){
                    propertyElectricEntity.setStaffName(propertyElectricDTO.getEleSign());//符号
                    propertyElectricEntity.setElectricQuantity(propertyElectricDTO.getElectricQuantity());//电量
                }
        }
        List<PropertyElectricEntity> propertyElectricEntities = propertyElectricRepository.listElectric(propertyElectricEntity,webPage);
        List<PropertyElectricDTO> pDtos = new ArrayList<>();
        if (propertyElectricEntities.size()>0){
            for (PropertyElectricEntity p :propertyElectricEntities){
                PropertyElectricDTO pDto = new PropertyElectricDTO();
                pDto.setElectricId(p.getElectricId());//电量Id
                pDto.setHouseId(p.getHouseId());//房间id
                pDto.setHouseNum(p.getHouseNum());//房间号
                pDto.setUserId(p.getUserId());//用户Id
                pDto.setUserName(p.getUserName());//用户名
                pDto.setUserMobile(p.getUserMobile());//用户电话
                pDto.setProjectId(p.getProjectId());//项目Id
                pDto.setProjectName(p.getProjectName());//项目名称
                pDto.setElectricQuantity(p.getElectricQuantity());//电量
                pDto.setCreateOn(p.getCreateOn());//抄表时间
                pDto.setStaffName(p.getStaffName());//抄表人姓名
                pDtos.add(pDto);
            }
        }
        return pDtos;
    }

    /**
     * 删除电量信息
     * @param eleId
     * @return
     */
    @Override
    public boolean delEleDTO(String eleId) {
        PropertyElectricEntity propertyElectricEntity = propertyElectricRepository.getElectricByid(eleId);
        if (propertyElectricEntity!=null){
            propertyElectricEntity.setState(PropertyElectricEntity.State.ElectricState_NO);
            return propertyElectricRepository.updateElectric(propertyElectricEntity);
        }
        return false;
    }

    /**
     * 导出房产信息
     * @param titles
     */
    @Override
    public void exportExcel(String[] titles,ServletOutputStream outputStream,String projectId,String building,String formatId) {

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        // 房屋列表
        XSSFSheet sheet = workBook.createSheet("房产列表");

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

        // 构建表头(房产)
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

        //查询业主列表
        WebPage webPage=new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(9999);
        List<HouseInfoEntity> houseInfoEntities = houseInfoRepository.houseInfoByProjectId(projectId,building,formatId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (houseInfoEntities.size()>0){
            for (int i =0;i<houseInfoEntities.size();i++){
                HouseInfoEntity houseInfoEntity = houseInfoEntities.get(i);
                if (houseInfoEntity.getProjectName()== null || houseInfoEntity.getProjectName().equals("")){
                    continue;
                }
                /*if (houseInfoEntity.getBuildingName()== null || houseInfoEntity.getBuildingName().equals("")){
                    continue;
                }
                if (houseInfoEntity.getUnitName()== null || houseInfoEntity.getUnitName().equals("")){
                    continue;
                }*/
                if (houseInfoEntity.getRoomName()== null || houseInfoEntity.getRoomName().equals("")){
                    continue;
                }

                XSSFRow bodyRow = sheet.createRow(i + 1);
                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle);// 表格黑色边框
                cell.setCellValue(houseInfoEntity.getProjectId());//项目id

                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle);// 表格黑色边框
                cell.setCellValue(houseInfoEntity.getProjectName());//项目名称

                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);// 表格黑色边框
                cell.setCellValue(houseInfoEntity.getId());//房屋id

                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle);// 表格黑色边框
                cell.setCellValue(houseInfoEntity.getFormatName());//业态

                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle);// 表格黑色边框
                //cell.setCellValue(houseInfoEntity.getBuildingName());//楼号

                cell = bodyRow.createCell(5);
                cell.setCellStyle(bodyStyle);// 表格黑色边框
                //cell.setCellValue(houseInfoEntity.getUnitName());//单元

                cell = bodyRow.createCell(6);
                cell.setCellStyle(bodyStyle);// 表格黑色边框
                cell.setCellValue(houseInfoEntity.getRoomName());//房间号

                cell = bodyRow.createCell(7);
                cell.setCellStyle(bodyStyle);// 表格黑色边框
                cell.setCellValue("");//剩余电量

                cell = bodyRow.createCell(8);
                cell.setCellStyle(bodyStyle);// 表格黑色边框
                cell.setCellValue("");//抄表人
            }
        }
        try {
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


    }

    /**
     * 导入电量Excel
     * @param myfiles
     * @return
     */
    @Override
    public String importExcel(MultipartFile[] myfiles,HttpServletRequest request,String staffProjectId) throws IOException{
//
        String result = "";

        List<PropertyElectricDTO> propertyElectricDTOs = new ArrayList<>();
        int i = myfiles.length;
        File[] files = new File[myfiles.length];
        int count = 0;
        for (MultipartFile myfile : myfiles) {
            if (myfile.isEmpty()) {
                System.out.println("文件未上传");
            }else {
                System.out.println("文件长度: " + myfile.getSize());
                System.out.println("文件类型: " + myfile.getContentType());
                System.out.println("文件名称: " + myfile.getName());
                System.out.println("文件原名: " + myfile.getOriginalFilename());
                System.out.println("========================================");
                // 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
                String realPath = request.getSession().getServletContext().getRealPath("/files/upload/loanData");
                File file = new File(realPath, myfile.getOriginalFilename());
//                FileUtils.copyInputStreamToFile(myfile.getInputStream(), file);
                // 上传xls格式
                if(myfile.getOriginalFilename().toLowerCase().endsWith("xls")){
                    //readXls(myfile.getInputStream());
                }else {
                    // 上传xlsx
                    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file+"");
                    try {
                        // 循环工作表Sheet
                        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
                            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                            if (xssfSheet == null) {
                                continue;
                            }
                            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                                if (xssfRow != null) {
//                                    XSSFCell projectId = xssfRow.getCell(0);//项目id
//                                    XSSFCell projectName = xssfRow.getCell(1);//项目名称
//                                    XSSFCell houseId = xssfRow.getCell(2);//房屋id
//                                    XSSFCell buildName = xssfRow.getCell(3);//楼号
//                                    XSSFCell unitName = xssfRow.getCell(4);//单元
//                                    XSSFCell roomName = xssfRow.getCell(5);//房间号
//                                    XSSFCell eleNum = xssfRow.getCell(6);//电量
//                                    XSSFCell modUser = xssfRow.getCell(7);//抄表人
//                                    System.out.println("***********"+modUser.equals(""));//false
                                    String projectId = getValue(xssfRow.getCell(0));//项目id
                                    String projectName = getValue(xssfRow.getCell(1));//项目名称
                                    String houseId = getValue(xssfRow.getCell(2));//房屋id
                                    String buildName = getValue(xssfRow.getCell(3));//楼号
                                    String unitName = getValue(xssfRow.getCell(5));//单元
                                    String roomName = getValue(xssfRow.getCell(6));//房间号
                                    String eleNum = getValue(xssfRow.getCell(7));//电量
                                    String modUser = getValue(xssfRow.getCell(8));//抄表人
                                    System.out.print(count++ + "----------");

                                    PropertyElectricDTO propertyElectricDTO = new PropertyElectricDTO();
                                    propertyElectricDTO.setProjectId(projectId);
                                    propertyElectricDTO.setProjectName(projectName);
                                    propertyElectricDTO.setHouseId(houseId);
                                    propertyElectricDTO.setHouseNum(buildName + unitName + roomName);
                                    propertyElectricDTO.setElectricQuantity(eleNum);
                                    propertyElectricDTO.setStaffName(modUser);

                                    propertyElectricDTOs.add(propertyElectricDTO);


                                }
                            }
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                        // 失败跳转
//                        model.addAttribute("error","上传失败!请检查Excel");
                        result = "上传失败，请检查Excel";
                    }
                }
            }
        }
                result =  saveElectric(propertyElectricDTOs,staffProjectId);//执行添加信息
        return result;
    }

    /**
     * 解析excel
     * @param xssfCell
     * @return
     */
    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }


    /**
     * 批量保存电量信息
     * @param propertyElectricDTOs
     * @return
     */
    @Override
    public String saveElectric(List<PropertyElectricDTO> propertyElectricDTOs,String staffProjectId) {

        String warnStutus = "";
        float warnValue = 0;
        ElectricWarnEntity electricWarn = electricWarnRepository.getEleWarnByProId(propertyElectricDTOs.get(0).getProjectId());
        if (electricWarn!=null){
            warnStutus=electricWarn.getWarnStutus();
            warnValue = Float.parseFloat(electricWarn.getWarnValue());
        }
        int total = propertyElectricDTOs.size();//一共多少条信息
        int success = 0;
        if (propertyElectricDTOs.size()>0){
            for (PropertyElectricDTO propertyElectricDTO:propertyElectricDTOs){
                if (!propertyElectricDTO.getProjectId().equals(staffProjectId)){
                    continue;
                }
                List<HouseUserBookEntity> houseUserBookRepositories = new ArrayList<>();
                 List<HouseUserBookEntity> houseOwnerBooks =  houseUserBookRepository.listUser(propertyElectricDTO.getHouseId(), HouseUserBookEntity.USER_TYPE_OWNER);
                List<HouseUserBookEntity> houseFamBooks = houseUserBookRepository.listUser(propertyElectricDTO.getHouseId(), HouseUserBookEntity.USER_TYPE_FAMILY);
                List<HouseUserBookEntity> houseTenBooks = houseUserBookRepository.listUser(propertyElectricDTO.getHouseId(), HouseUserBookEntity.USER_TYPE_TENANT);
                /********************************电量预警信息*************************************/
                if (warnStutus.equals(ElectricWarnEntity.WarnStatus.Warn_Yes)){
                    if (Float.parseFloat(propertyElectricDTO.getElectricQuantity())<warnValue){
                        List<String> userid = new ArrayList<>();
                        if (houseOwnerBooks.size()>0){
                            for (HouseUserBookEntity us :houseOwnerBooks){
                                userid.add(us.getUserId());
                            }
                        }
                        if (houseFamBooks.size()>0){
                            for (HouseUserBookEntity us :houseFamBooks){
                                userid.add(us.getUserId());
                            }
                        }
                        if (houseTenBooks.size()>0){
                            for (HouseUserBookEntity us :houseTenBooks){
                                userid.add(us.getUserId());
                            }
                        }
                        MessageInsertDTO messageInsertDTO = new MessageInsertDTO();
//                        messageInsertDTO.setMessageTitle("123");
                        messageInsertDTO.setMessageUserType("1");
                        messageInsertDTO.setMessageType("10");
                        messageInsertDTO.setMessageTypeState("1");
                        messageInsertDTO.setMessageUrl("9999");
                        messageInsertDTO.setIsPush("1");
                        messageInsertService.InsertMessage(messageInsertDTO,userid);
                    }
                }
                /********************************电量预警信息*************************************/
                if (houseOwnerBooks.size()>0){
                    houseUserBookRepositories.addAll(houseOwnerBooks);
                }else if (houseFamBooks.size()>0){
                    houseUserBookRepositories.addAll(houseFamBooks);
                }
                else if (houseTenBooks.size()>0){
                    houseUserBookRepositories.addAll(houseTenBooks);
                }
                if (houseUserBookRepositories.size()>0){
                        HouseUserBookEntity houseUserBookEntity=houseUserBookRepositories.get(0);
                        UserInfoEntity userInfoEntity = userInfoRepository.get(houseUserBookEntity.getUserId());
                        PropertyElectricEntity propertyElectricEntity = new PropertyElectricEntity();
                        propertyElectricEntity.setElectricId(IdGen.getDateId());
                        propertyElectricEntity.setHouseId(houseUserBookEntity.getHouseId());
                        propertyElectricEntity.setHouseNum(propertyElectricDTO.getHouseNum());
                        propertyElectricEntity.setUserId(userInfoEntity.getUserId());
                        if (houseUserBookEntity.getUserType().equals(HouseUserBookEntity.USER_TYPE_OWNER)) {
                            propertyElectricEntity.setUserName(userInfoEntity.getRealName());
                        }
                        if (houseUserBookEntity.getUserType().equals(HouseUserBookEntity.USER_TYPE_FAMILY)) {
                            propertyElectricEntity.setUserName("(家属)"+userInfoEntity.getRealName());
                        }
                        if (houseUserBookEntity.getUserType().equals(HouseUserBookEntity.USER_TYPE_TENANT)) {
                            propertyElectricEntity.setUserName("(租户)"+userInfoEntity.getRealName());
                        }
                        propertyElectricEntity.setUserMobile(userInfoEntity.getMobile());
                        propertyElectricEntity.setProjectId(propertyElectricDTO.getProjectId());
                        propertyElectricEntity.setProjectName(propertyElectricDTO.getProjectName());
                        if (propertyElectricDTO.getElectricQuantity().equals("")){
                            continue;//如果电量为空，则该数据不添加
                        }
                        propertyElectricEntity.setElectricQuantity(propertyElectricDTO.getElectricQuantity());
                        propertyElectricEntity.setCreateOn(DateUtils.format(DateUtils.getDate()));
                        propertyElectricEntity.setState(PropertyElectricEntity.State.ElectricState_YES);
                        propertyElectricEntity.setStaffName(propertyElectricDTO.getStaffName());
                        propertyElectricEntity.setUserType(houseUserBookEntity.getUserType());
                        propertyElectricRepository.saveElectric(propertyElectricEntity);
                        success++;
                    }


            }
        }
        int failed = total - success;
        String result = "添加成功"+success+"人，添加失败"+failed+"人";
        System.out.println(result);
        return result;
    }
}
