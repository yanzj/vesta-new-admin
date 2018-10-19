package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ExportExcelHouseDataDTO;
import com.maxrocky.vesta.application.DTO.PropertyContractDTO;
import com.maxrocky.vesta.application.inf.PropertyContractService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.PropertyContractRepository;
import com.maxrocky.vesta.domain.repository.PropertyDoorRepository;
import com.maxrocky.vesta.domain.repository.PropertyManageRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.ExportExcel;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/3/8 16:52
 * @deprecated 合同查询模块Service实现类
 */
@Service
public class PropertyContractServiceImpl implements PropertyContractService{

    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    PropertyContractRepository propertyContractRepository;
    @Autowired
    PropertyDoorRepository propertyDoorRepository;
    @Autowired
    PropertyManageRepository propertyManageRepository;

    /**
     * 合同导入模板下载
     */
    public void downLoadExcelTemplate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        //创建Sheet页
        XSSFSheet sheet = workBook.createSheet("合同管理批量导入");

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
        font.setFontName("微软雅黑");
        font.setFontHeight((short) 200);
        style1.setFont(font);

        String[] titles = {"序号","项目编码","项目","地块","楼栋","单元","楼层","房间号","业主姓名","合同类型","办理进度","状态"};
        XSSFRow headRow = sheet.createRow(0);

        XSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = headRow.createCell(i);
            headRow.createCell(i).setCellValue(titles.length);

            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }
        sheet.setColumnWidth(0, 2000);  //序号
        sheet.setColumnWidth(1, 4000);  //项目编码
        sheet.setColumnWidth(2, 5000);  //项目
        sheet.setColumnWidth(3, 2000);  //地块
        sheet.setColumnWidth(4, 2000);  //楼栋
        sheet.setColumnWidth(5, 2000);  //单元
        sheet.setColumnWidth(6, 2000);  //楼层
        sheet.setColumnWidth(7, 4000);  //房间号
        sheet.setColumnWidth(8, 4000);  //业主姓名
        sheet.setColumnWidth(9, 2000);  //办理方式
        sheet.setColumnWidth(10, 7000);  //办理进度
        sheet.setColumnWidth(11, 2000);  //状态

        XSSFRow bodyRow = sheet.createRow(1);
        cell = bodyRow.createCell(0);
        cell.setCellStyle(bodyStyle);//表格黑色边框
        cell.setCellValue("1");//序号
        cell = bodyRow.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("BJ-YZJMY");//项目编码
        cell = bodyRow.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("亦庄金茂悦");//项目
        cell = bodyRow.createCell(3);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("X88");//地块
        cell = bodyRow.createCell(4);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("02");//楼栋
        cell = bodyRow.createCell(5);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("01");//单元
        cell = bodyRow.createCell(6);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("03");//楼层
        cell = bodyRow.createCell(7);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("0302");//房间号
        cell = bodyRow.createCell(8);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("张三");//业主姓名
        cell = bodyRow.createCell(9);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("购房合同/按揭合同");//合同类型
        cell = bodyRow.createCell(10);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("待提交资料");//办理进度
        cell = bodyRow.createCell(11);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("1");//状态
        try {
            String fileName = new String(("合同管理批量导入_模板").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("合同管理批量导入_模板", "UTF8");
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
    }

    /**
     * 合同导入模板及导入数据下载
     */
    public void downLoadExcelTemplateAndData(HttpServletResponse response,HttpServletRequest request,PropertyContractDTO propertyContractDTO) throws Exception {
        ServletOutputStream out = response.getOutputStream();
        String fileName = "合同管理批量导入_模板及数据";
        response.reset();
        response.setContentType("application/x-xls");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        if (agent.contains("firefox")) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
        }
        String title = "合同管理批量导入";
        String[] headers = {"序号","项目编码","项目","地块","楼栋","单元","楼层","房间号","业主姓名","合同类型","办理进度","状态"};

        //获取数据
        Map<String,Object> params = new HashedMap();
        params.put("projectCode",propertyContractDTO.getProjectCode());
        params.put("blockCode",propertyContractDTO.getBlockCode());
        List<Map<String,Object>> dataList = propertyManageRepository.getHouseDataByProject(params);

        List<ExportExcelHouseDataDTO> exportExcelHouseDataDTOS = new ArrayList<>();
        if (null != dataList){
            int num = 1;
            ExportExcelHouseDataDTO exportExcelCommonUserDTO = null;
            Map<String,Object> dataMap = null;
            for (int i = 0,length = dataList.size();i<length;i++){
                dataMap = dataList.get(i);
                exportExcelCommonUserDTO = new ExportExcelHouseDataDTO();

                //序号
                exportExcelCommonUserDTO.setNum(num++);
                //项目编码
                exportExcelCommonUserDTO.setProjectCode(dataMap.get("projectCode") == null ? "" : dataMap.get("projectCode").toString());
                //项目
                exportExcelCommonUserDTO.setProjectName(dataMap.get("projectName") == null ? "" : dataMap.get("projectName").toString());
                //地块
                exportExcelCommonUserDTO.setAreaName(dataMap.get("areaName") == null ? "#" : dataMap.get("areaName").toString());
                //楼栋
                exportExcelCommonUserDTO.setBuildingName(dataMap.get("buildingName") == null ? "#" : dataMap.get("buildingName").toString());
                //单元
                exportExcelCommonUserDTO.setUnitName(dataMap.get("unitName") == null ? "#" : dataMap.get("unitName").toString());
                //楼层
                exportExcelCommonUserDTO.setFloorName(dataMap.get("floorName") == null ? "#" : dataMap.get("floorName").toString());
                //房间号
                exportExcelCommonUserDTO.setRoomName(dataMap.get("roomName") == null ? "" : dataMap.get("roomName").toString());
                //业主姓名
                exportExcelCommonUserDTO.setOwnerName("");
                //办理方式
                exportExcelCommonUserDTO.setHandleMode("");
                //办理进度
                exportExcelCommonUserDTO.setHandleProgress("");
                //状态
                exportExcelCommonUserDTO.setHandleStatus("");

                exportExcelHouseDataDTOS.add(exportExcelCommonUserDTO);
            }
            ExportExcel<ExportExcelHouseDataDTO> ex = new ExportExcel<ExportExcelHouseDataDTO>();
            ex.exportExcel(title, headers, exportExcelHouseDataDTOS, out, "yyyy-MM-dd");
            System.out.println("excel导出成功！");
        }
    }

    /**
     * 获取合同管理列表
     */
    public List<PropertyContractDTO> getPropertyContractList(PropertyContractDTO propertyContractDTO,
                                                             WebPage webPage) throws InvocationTargetException, IllegalAccessException {
        Map<String,Object> params = new HashedMap();
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = propertyContractDTO.getRoleScopeList();
        for (Map<String, Object> roleScope : roleScopeList) {
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")) {
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList) {
                    if (!roleScopes.contains(project[0].toString())) {
                        roleScopes += "'" + project[0].toString() + "',";
                    }
                }
            } else if (scopeType.equals("2")) {
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())) {
                    roleScopes += "'" + roleScope.get("scopeId") + "',";
                }
            } else if (scopeType.equals("0")) {
                //全部城市
                roleScopes += "all,";
            }
        }
        params.put("roleScopes",roleScopes);//权限范围
        if (null != propertyContractDTO.getScopeId() && !"".equals(propertyContractDTO.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(propertyContractDTO.getScopeId());
            String cityProjects = "";
            for (Object[] project : projectList) {
                cityProjects += "'" + project[0].toString() + "',";
                //此处过滤会导致BJ-YZJMY正常的项目编码被过滤
//                if (!cityProjects.contains(project[0].toString())) {
//                    cityProjects += "'" + project[0].toString() + "',";
//                }
            }
            params.put("cityProjects",cityProjects);//城市下所有项目,以城市为单位检索
        }
        params.put("projectCode",propertyContractDTO.getProjectCode());
        params.put("blockCode",propertyContractDTO.getBlockCode());
        params.put("buildingNum",propertyContractDTO.getBuildingNum());
        params.put("unitCode",propertyContractDTO.getUnitCode());
        params.put("roomName",propertyContractDTO.getRoomName());
        params.put("ownerName",propertyContractDTO.getOwnerName());
        params.put("propertyType",propertyContractDTO.getPropertyType());
        params.put("handleStatus",propertyContractDTO.getHandleStatus());
        //执行查询
        List<PropertyContractEntity> propertyContractList = propertyContractRepository.getPropertyContractList(params, webPage);
        List<PropertyContractDTO> propertyContractDTOList = new ArrayList<>();
        PropertyContractDTO propertyContractDTO_1 = null;
        for (PropertyContractEntity propertyEntry : propertyContractList){
            propertyContractDTO_1 = new PropertyContractDTO();
            BeanUtils.copyProperties(propertyContractDTO_1, propertyEntry);
            propertyContractDTOList.add(propertyContractDTO_1);
        }
        return propertyContractDTOList;
    }

    /**
     * 导入excel WeiYangDong_2018-03-08
     * @param user 操作人
     * @param fis 输入流
     * @return boolean
     */
    public Map<String,Object> importEmployeeByPoi(UserPropertyStaffEntity user, InputStream fis){
        Map<String,Object> resultMap = new HashedMap();
        int successNum = 0;//记录成功导入记录数
        int errorNum = 0;//记录导入失败记录数
        String errorNotes = "";//记录导入失败的序号
        PropertyContractEntity propertyContractEntity = null;
        try{
            //创建Excel工作薄
            Workbook wb = new HSSFWorkbook(fis);
            //得到第一个工作表
            Sheet sheet = wb.getSheetAt(0);
            Row row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for(int i = 0; i < wb.getNumberOfSheets(); i++) {
                sheet = wb.getSheetAt(i);
                //遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for(int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    propertyContractEntity = new PropertyContractEntity();
                    //主键Id
                    propertyContractEntity.setPropertyId(IdGen.uuid());
                    propertyContractEntity.setCreateBy(user.getStaffName());
                    propertyContractEntity.setCreateOn(new Date());
                    //项目编码
                    String projectCode = "";
                    if (null != row.getCell(1) && !"".equals(row.getCell(1).toString())){
                        propertyContractEntity.setProjectCode(getCellValue(row.getCell(1)).trim());
                        projectCode = getCellValue(row.getCell(1)).trim();
                    }
                    //项目名称
                    if (null != row.getCell(2) && !"".equals(row.getCell(2).toString())){
                        propertyContractEntity.setProjectName(getCellValue(row.getCell(2)).trim());
                    }
                    //地块
                    String areaName = "";
                    if (null != row.getCell(3) && !"".equals(row.getCell(3).toString())){
                        areaName = String.valueOf(row.getCell(3)).trim();
                        propertyContractEntity.setArea(areaName);
                    }
                    //地块编码
                    String blockCode = "";
                    if (!"".equals(projectCode) && !"".equals(areaName)){
                        //通过项目编码与地块名称检索地块编码
                        List<HouseAreaEntity> houseAreaEntities = propertyDoorRepository.getAreaListByProjectAndAreaName(projectCode, areaName);
                        if (houseAreaEntities.size() > 0){
                            propertyContractEntity.setBlockCode(houseAreaEntities.get(0).getBlockCode());
                            blockCode = houseAreaEntities.get(0).getBlockCode();
                        }
                    }
                    //预售楼号
                    //备案楼号
                    String buildingRecord = "";
                    if (null != row.getCell(4) && !"".equals(row.getCell(4).toString())){
                        buildingRecord = String.valueOf(row.getCell(4)).trim();
                        if (buildingRecord.contains(".")){
                            buildingRecord = buildingRecord.substring(0,buildingRecord.indexOf("."));
                        }
//                        if (buildingRecord.length() == 1){
//                            buildingRecord = "0"+buildingRecord;
//                        }
                        propertyContractEntity.setBuildingRecord(buildingRecord);
                    }
                    //楼号编码
                    String buildingNum = "";
                    if (!"".equals(blockCode) && !"".equals(buildingRecord)){
                        List<HouseBuildingEntity> houseBuildingEntities = null;
                        //通过地块编码与备案楼号检索楼栋信息
                        houseBuildingEntities = propertyDoorRepository.getBuildingListByAreaAndBuildingRecord(blockCode, buildingRecord);
                        if (houseBuildingEntities.size() > 0){
                            propertyContractEntity.setBuildingNum(houseBuildingEntities.get(0).getBuildingNum());
                            buildingNum = houseBuildingEntities.get(0).getBuildingNum();
                        }else{
                            //如果备案楼号查无结果,则通过地块编码与预售楼号检索楼栋信息
                            houseBuildingEntities = propertyDoorRepository.getBuildingListByAreaAndBuildingSale(blockCode,buildingRecord);
                            if (houseBuildingEntities.size() > 0){
                                propertyContractEntity.setBuildingNum(houseBuildingEntities.get(0).getBuildingNum());
                                propertyContractEntity.setBuildingSale(houseBuildingEntities.get(0).getBuildingSale());
                                propertyContractEntity.setBuildingRecord(null); //若预售楼号查得结果,则清空备案楼号字段
                                buildingNum = houseBuildingEntities.get(0).getBuildingNum();
                            }
                        }
                    }
                    //单元
                    String unitName = "";
                    if (null != row.getCell(5) && !"".equals(row.getCell(5).toString())){
                        unitName = String.valueOf(row.getCell(5)).trim();
                        if (unitName.contains(".")){
                            unitName = unitName.substring(0,unitName.indexOf("."));
                        }
//                        if (unitName.length() == 1){
//                            unitName = "0"+unitName;
//                        }
                        propertyContractEntity.setUnit(unitName);
                    }
                    //单元编码
                    String unitCode = "";
                    if (!"".equals(buildingNum) && !"".equals(unitName)){
                        //通过楼栋编码与单元名称检索单元信息
                        if ("#".equals(unitName)){
                            unitName = null;
                        }
                        List<HouseUnitEntity> houseUnitEntities = propertyDoorRepository.getUnitListByBuildingAndUnitName(buildingNum, unitName);
                        if (houseUnitEntities.size() > 0){
                            //检索单元列表数据中有可能出现公共单元-#G,需要排除此类
                            for(int o=0;o<houseUnitEntities.size();o++){
                                String uc = houseUnitEntities.get(o).getUnitCode();
                                if (!uc.substring(uc.lastIndexOf("-",uc.length())).contains("#G")){
                                    propertyContractEntity.setUnitCode(houseUnitEntities.get(o).getUnitCode());
                                    unitCode = houseUnitEntities.get(o).getUnitCode();
                                }
                            }
                        }
                    }
                    //楼层
                    String floorName = "";
                    if (null != row.getCell(6) && !"".equals(row.getCell(6).toString())){
                        floorName = String.valueOf(row.getCell(6)).trim();
                        if (floorName.contains(".")){
                            floorName = floorName.substring(0,floorName.indexOf("."));
                        }
                        propertyContractEntity.setFloor(floorName);
                    }
                    //楼层编码
                    String floorCode = "";
                    if (!"".equals(unitCode) && !"".equals(floorName)){
                        //通过单元编码与楼层名称检索楼层信息
                        if ("#".equals(floorName)){
                            floorName = null;
                        }
                        //通过单元编码与楼层名称检索楼层信息
                        List<HouseFloorEntity> houseFloorEntities = propertyDoorRepository.getFloorListByUnitAndFloorName(unitCode, floorName);
                        if (houseFloorEntities.size() > 0){
                            propertyContractEntity.setFloorCode(houseFloorEntities.get(0).getFloorCode());
                            floorCode = houseFloorEntities.get(0).getFloorCode();
                        }
                    }
                    //房间号
                    String roomName = "";
                    if (null != row.getCell(7) && !"".equals(row.getCell(7).toString())){
                        roomName = String.valueOf(row.getCell(7)).trim();
                        roomName = roomName.substring(0,roomName.length()-1);
                        propertyContractEntity.setRoomName(roomName);
                    }
                    String roomNum = "";
                    if (!"".equals(floorCode) && !"".equals(roomName)){
                        List<HouseInfoEntity> houseInfoEntities = propertyDoorRepository.getHouseInfoListByFloorAndRoomName(floorCode, roomName);
                        if (houseInfoEntities.size() > 0){
                            propertyContractEntity.setRoomAddress(houseInfoEntities.get(0).getAddress());
                            propertyContractEntity.setRoomNum(houseInfoEntities.get(0).getRoomNum());
                            roomNum = houseInfoEntities.get(0).getRoomNum();
                        }
                    }
                    //业主姓名
                    if (null != row.getCell(8) && !"".equals(row.getCell(8).toString())){
                        propertyContractEntity.setOwnerName(getCellValue(row.getCell(8)).trim());
                    }
                    //办理方式
                    /*if (null != row.getCell(9) && !"".equals(row.getCell(9).toString())){
                        String handleModeStr = getCellValue(row.getCell(9)).trim();
                        if (handleModeStr.contains("代办")){
                            propertyContractEntity.setHandleMode(PropertyContractEntity.HANDLEMODE_AGENCY);
                        }else if (handleModeStr.contains("自办")){
                            propertyContractEntity.setHandleMode(PropertyContractEntity.HANDLEMODE_SELF);
                        }
                    }*/
                    //合同类型
                    if (null != row.getCell(9) && !"".equals(row.getCell(9).toString())){
                        String propertyTypeStr = getCellValue(row.getCell(9)).trim();
                        if (propertyTypeStr.contains("购房")){
                            propertyContractEntity.setPropertyType("0");
                        }else if (propertyTypeStr.contains("按揭")){
                            propertyContractEntity.setPropertyType("1");
                        }else{
                            propertyContractEntity.setPropertyType("");
                        }
                    }
                    //记录
                    if (!"".equals(roomNum)){
                        successNum ++;
                        //判断房间编码是否已存在，设置主键
                        List<PropertyContractEntity> propertyContractEntityList = propertyContractRepository.getPropertyContractByRoomNum(roomNum,propertyContractEntity.getPropertyType());
                        if (null != propertyContractEntityList && propertyContractEntityList.size() > 0){
                            propertyContractEntity.setPropertyId(propertyContractEntityList.get(0).getPropertyId());
                        }
                    }else{
                        errorNum ++;
                        if (null != row.getCell(0) && !"".equals(row.getCell(0).toString())){
                            errorNotes += getCellValue(row.getCell(0)) + ",";
                        }else {
                            errorNotes += "未获取到序号记录,";
                        }
                        continue;
                    }
                    //办理进度
                    if (null != row.getCell(10) && !"".equals(row.getCell(10).toString())){
                        propertyContractEntity.setHandleProgress(getCellValue(row.getCell(10)).trim());
                    }
                    //状态
                    if (null != row.getCell(11) && !"".equals(row.getCell(11).toString())){
                        propertyContractEntity.setHandleStatus(getCellValue(row.getCell(11)).trim());
                    }
                    propertyContractRepository.saveOrUpdate(propertyContractEntity);
                }
            }
            resultMap.put("successNum",successNum);
            resultMap.put("errorNum",errorNum);
            if ("".equals(errorNotes)){
                resultMap.put("errorNotes","0条记录");
            }else{
                resultMap.put("errorNotes",errorNotes.substring(0,errorNotes.length()-1)+" 条记录");
            }
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            return resultMap;
        }
    }

    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(Cell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }
}
