package com.maxrocky.vesta.application.measure.impl;

import com.maxrocky.vesta.Json.JsonUtil;
import com.maxrocky.vesta.application.measure.DTO.*;
import com.maxrocky.vesta.application.measure.inf.MeasureService;
import com.maxrocky.vesta.domain.measure.model.*;
import com.maxrocky.vesta.domain.measure.repository.MeasureRepository;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.repository.AuthAgencyRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.QRCodeUtil;
import com.maxrocky.vesta.utility.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Itzxs on 2018/7/9.
 */
@Service
@Transactional
public class MeasureServiceImpl implements MeasureService {

    @Autowired
    MeasureRepository measureRepository;

    @Autowired
    AuthAgencyRepository authAgencyRepository;

    @Override
    public List<MeasureDetailDTO> getMeasures(WebPage webPage, MeasureDTO measureDTO) {
        List<MeasureDetailDTO> measureDetailDTOS = new ArrayList<>();
        if (measureDTO != null) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setMaximumFractionDigits(1);
            //根据条件分页查询实测实量详情列表
            List<Object[]> measureDetails = measureRepository.getMeasureDetailByWebPage(webPage, measureDTO.getProjectId(), measureDTO.getBuildingId(), measureDTO.getFloorId());
            if (measureDetails != null && measureDetails.size() > 0) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (Object[] objects : measureDetails) {
                    MeasureDetailDTO measureDetailDTO = new MeasureDetailDTO();
                    measureDetailDTO.setProjectId(objects[0] == null ? "" : objects[0].toString());
                    measureDetailDTO.setProjectName(objects[1] == null ? "" : objects[1].toString());
                    measureDetailDTO.setBuildingId(objects[2] == null ? "" : objects[2].toString());
                    measureDetailDTO.setBuildingName(objects[3] == null ? "" : objects[3].toString());
                    measureDetailDTO.setFloorId(objects[4] == null ? "" : objects[4].toString());
                    measureDetailDTO.setFloorName(objects[5] == null ? "" : objects[5].toString());
                    Double acceptanceNum = objects[6] == null ? 0 : Double.parseDouble(objects[6].toString());
                    Double totalNum = objects[7] == null ? 0 : Double.parseDouble(objects[7].toString());
                    if (totalNum != 0) {
                        String qualificationRate = numberFormat.format(acceptanceNum / totalNum * 100);
                        if(qualificationRate.indexOf(".") == -1){
                            qualificationRate += ".0";
                        }
                        measureDetailDTO.setQualificationRate(qualificationRate + "%");
                    } else {
                        measureDetailDTO.setQualificationRate("0.0%");
                    }
                    measureDetailDTO.setCreateDate(objects[8] == null ? "" : simpleDateFormat.format(objects[8]));
                    measureDetailDTO.setMeasureId(objects[9] == null ? "" : objects[9].toString());
                    measureDetailDTOS.add(measureDetailDTO);
                }
            }
        }
        return measureDetailDTOS;
    }

    @Override
    public MeasureDataDTO getMeasureData(MeasureDTO measureDTO) {
        MeasureDataDTO measureDataDTO = new MeasureDataDTO();
        //查询项目下数据
        if (!StringUtil.isEmpty(measureDTO.getProjectId()) && StringUtil.isEmpty(measureDTO.getBuildingId())) {
            measureDataDTO.setType("1");
            //根据项目ID获取项目的总合格数和总数
            List<Object[]> peojectDetail = measureRepository.getMeasureNumByPIdAndBidAndFid(measureDTO.getProjectId(), null, null);
            if (peojectDetail != null && peojectDetail.size() > 0) {
                //项目总合格率
                measureDataDTO.setTotalPercent(getMeasureDataDto(peojectDetail.get(0)) + "%");
            }
            List<Object[]> measureDetailEntities = measureRepository.getMeasureDetailByPIdAndBIdAndFId(measureDTO.getProjectId(), null, null);
            if (measureDetailEntities != null && measureDetailEntities.size() > 0) {
                for (Object[] objects : measureDetailEntities) {
                    String id = objects[2] == null ? "" : objects[2].toString();
                    if ("1366d7d51e604568a041948a6268d6e4".equals(id)) {
                        measureDataDTO.setFirstPercent(getMeasureDataDto(objects) + "%");
                    } else if ("2870027d5a21468596c10b1c345e0249".equals(id)) {
                        measureDataDTO.setSecondPercent(getMeasureDataDto(objects) + "%");
                    } else if ("4db28e9d1fa04550a5d162aa28bc9dec".equals(id)) {
                        measureDataDTO.setThirdPercent(getMeasureDataDto(objects) + "%");
                    } else if ("2d228f8e643140a98b5785fb0b16ffe0".equals(id)) {
                        measureDataDTO.setFourthPercent(getMeasureDataDto(objects) + "%");
                    } else if ("6d29b9e3e72f447f8c04b1dd4aa46bf7".equals(id)) {
                        measureDataDTO.setFifthPercent(getMeasureDataDto(objects) + "%");
                    } else if ("613ea3aa363d4002b4ddefd57476e8c4".equals(id)) {
                        measureDataDTO.setSixth(getMeasureDataDto(objects) + "%");
                    } else if ("cfed63cad27f4af1b36e89ca12c0ebde".equals(id)) {
                        measureDataDTO.setSeventhPercent(getMeasureDataDto(objects) + "%");
                    } else if ("2042d49e97304630bdfd3d5e2e4fcd74".equals(id)) {
                        measureDataDTO.setEighth(getMeasureDataDto(objects) + "%");
                    } else if ("83390512ef864b1d9e0ca3a29162bf4b".equals(id)) {
                        measureDataDTO.setNinth(getMeasureDataDto(objects) + "%");
                    } else if ("1d695a7c7d9a4f3090101b9478d9584a".equals(id)) {
                        measureDataDTO.setTenth(getMeasureDataDto(objects) + "%");
                    }
                }
            }
        } else if (!StringUtil.isEmpty(measureDTO.getProjectId()) && !StringUtil.isEmpty(measureDTO.getBuildingId())
                && StringUtil.isEmpty(measureDTO.getFloorId())) {//查询楼栋下数据
            measureDataDTO.setType("2");
            //根据楼栋ID获取楼栋的总合格数和总数
            List<Object[]> buildDetail = measureRepository.getMeasureNumByPIdAndBidAndFid(measureDTO.getProjectId(), measureDTO.getBuildingId(), null);
            if (buildDetail != null && buildDetail.size() > 0) {
                //项目总合格率
                measureDataDTO.setTotalPercent(getMeasureDataDto(buildDetail.get(0)) + "%");
            }
            List<Object[]> measureDetailEntities = measureRepository.getMeasureDetailByPIdAndBIdAndFId(measureDTO.getProjectId(), measureDTO.getBuildingId(), null);
            if (measureDetailEntities != null && measureDetailEntities.size() > 0) {
                for (Object[] objects : measureDetailEntities) {
                    String id = objects[2] == null ? "" : objects[2].toString();
                    if ("1366d7d51e604568a041948a6268d6e4".equals(id)) {
                        measureDataDTO.setFirstPercent(getMeasureDataDto(objects) + "%");
                    } else if ("2870027d5a21468596c10b1c345e0249".equals(id)) {
                        measureDataDTO.setSecondPercent(getMeasureDataDto(objects) + "%");
                    } else if ("4db28e9d1fa04550a5d162aa28bc9dec".equals(id)) {
                        measureDataDTO.setThirdPercent(getMeasureDataDto(objects) + "%");
                    } else if ("2d228f8e643140a98b5785fb0b16ffe0".equals(id)) {
                        measureDataDTO.setFourthPercent(getMeasureDataDto(objects) + "%");
                    } else if ("6d29b9e3e72f447f8c04b1dd4aa46bf7".equals(id)) {
                        measureDataDTO.setFifthPercent(getMeasureDataDto(objects) + "%");
                    } else if ("613ea3aa363d4002b4ddefd57476e8c4".equals(id)) {
                        measureDataDTO.setSixth(getMeasureDataDto(objects) + "%");
                    } else if ("cfed63cad27f4af1b36e89ca12c0ebde".equals(id)) {
                        measureDataDTO.setSeventhPercent(getMeasureDataDto(objects) + "%");
                    } else if ("2042d49e97304630bdfd3d5e2e4fcd74".equals(id)) {
                        measureDataDTO.setEighth(getMeasureDataDto(objects) + "%");
                    } else if ("83390512ef864b1d9e0ca3a29162bf4b".equals(id)) {
                        measureDataDTO.setNinth(getMeasureDataDto(objects) + "%");
                    } else if ("1d695a7c7d9a4f3090101b9478d9584a".equals(id)) {
                        measureDataDTO.setTenth(getMeasureDataDto(objects) + "%");
                    }
                }
            }
        } else if (!StringUtil.isEmpty(measureDTO.getFloorId())) {
            measureDataDTO.setType("3");
            //根据楼栋ID获取楼栋的总合格数和总数
            List<Object[]> floorDetail = measureRepository.getMeasureNumByPIdAndBidAndFid(null, null, measureDTO.getFloorId());
            if (floorDetail != null && floorDetail.size() > 0) {
                //项目总合格率
                measureDataDTO.setTotalPercent(getMeasureDataDto(floorDetail.get(0)) + "%");
            }
            List<Object[]> measureDetailEntities = measureRepository.getMeasureDetailByPIdAndBIdAndFId(null, null, measureDTO.getFloorId());
            if (measureDetailEntities != null && measureDetailEntities.size() > 0) {
                for (Object[] objects : measureDetailEntities) {
                    String id = objects[2] == null ? "" : objects[2].toString();
                    if ("1366d7d51e604568a041948a6268d6e4".equals(id)) {
                        measureDataDTO.setFirstPercent(getMeasureDataDto(objects) + "%");
                    } else if ("2870027d5a21468596c10b1c345e0249".equals(id)) {
                        measureDataDTO.setSecondPercent(getMeasureDataDto(objects) + "%");
                    } else if ("4db28e9d1fa04550a5d162aa28bc9dec".equals(id)) {
                        measureDataDTO.setThirdPercent(getMeasureDataDto(objects) + "%");
                    } else if ("2d228f8e643140a98b5785fb0b16ffe0".equals(id)) {
                        measureDataDTO.setFourthPercent(getMeasureDataDto(objects) + "%");
                    } else if ("6d29b9e3e72f447f8c04b1dd4aa46bf7".equals(id)) {
                        measureDataDTO.setFifthPercent(getMeasureDataDto(objects) + "%");
                    } else if ("613ea3aa363d4002b4ddefd57476e8c4".equals(id)) {
                        measureDataDTO.setSixth(getMeasureDataDto(objects) + "%");
                    } else if ("cfed63cad27f4af1b36e89ca12c0ebde".equals(id)) {
                        measureDataDTO.setSeventhPercent(getMeasureDataDto(objects) + "%");
                    } else if ("2042d49e97304630bdfd3d5e2e4fcd74".equals(id)) {
                        measureDataDTO.setEighth(getMeasureDataDto(objects) + "%");
                    } else if ("83390512ef864b1d9e0ca3a29162bf4b".equals(id)) {
                        measureDataDTO.setNinth(getMeasureDataDto(objects) + "%");
                    } else if ("1d695a7c7d9a4f3090101b9478d9584a".equals(id)) {
                        measureDataDTO.setTenth(getMeasureDataDto(objects) + "%");
                    }
                }
            }
        }
        return measureDataDTO;
    }

    @Override
    public Map<String, Object> getMeasureData(MeasureDTO measureDTO, UserInformationEntity userInformationEntity) {
        //该用户所拥有的项目
        List<AuthAgencyESEntity> authAgencyESEntities = getAuthAgencyList(userInformationEntity);
        Map<String, Object> map = new HashMap<>();
        List<MeasureDTO> measureDTOS = new ArrayList<>();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(1);
        if (measureDTO != null) {
            //如果区域为空或者选择全部，则查找总部数据
            if ("1".equals(measureDTO.getRegionId()) && StringUtil.isEmpty(measureDTO.getCityId())) {
                List<Object[]> list = measureRepository.getMeasureDetail("1", null);
                measureDTOS = getMeasureDtoByMeasureDetail(list,"1");
                /*for (AuthAgencyESEntity authAgencyESEntity : authAgencyESEntities) {
                    if (2 == authAgencyESEntity.getLevel()) {//筛选区域级别
                        MeasureDTO measureDTO1 = new MeasureDTO();
                        measureDTO1.setRegionId(authAgencyESEntity.getAgencyId());
                        measureDTO1.setRegionName(authAgencyESEntity.getAgencyName());
                        for (Object[] objects : list) {//实测实量的数据
                            if (authAgencyESEntity.getAgencyId().equals(objects[2] == null ? "" : objects[2].toString())) {
                                double acceptanceNum = objects[0] == null ? 0 : Double.parseDouble(objects[0].toString());
                                double totalNum = objects[1] == null ? 0 : Double.parseDouble(objects[1].toString());
                                String mensurePercentOfPass = null;
                                if (totalNum != 0) {
                                    mensurePercentOfPass = numberFormat.format(acceptanceNum / totalNum * 100);
                                    if(mensurePercentOfPass.indexOf(".") == -1){
                                        mensurePercentOfPass += ".0";
                                    }
                                } else {
                                    mensurePercentOfPass = "0.0";
                                }
                                measureDTO1.setMensurePercentOfPass(mensurePercentOfPass);
                            }
                        }
                        measureDTOS.add(measureDTO1);
                    }
                }*/
                map.put("success", "0");
                map.put("data", measureDTOS);
                map.put("isExcel", list.size());
                return map;
            }
            //区域下的所有城市
            if (!StringUtil.isEmpty(measureDTO.getRegionId()) && !"1".equals(measureDTO.getRegionId()) && ("1".equals(measureDTO.getCityId()) || StringUtil.isEmpty(measureDTO.getCityId()))) {
                List<Object[]> list = measureRepository.getMeasureDetail(measureDTO.getRegionId(), "1");
                measureDTOS = getMeasureDtoByMeasureDetail(list,"2");
                /*for (AuthAgencyESEntity authAgencyESEntity : authAgencyESEntities) {
                    if (3 == authAgencyESEntity.getLevel() && authAgencyESEntity.getParentId().equals(measureDTO.getRegionId())) {//筛选城市级别
                        MeasureDTO measureDTO1 = new MeasureDTO();
                        measureDTO1.setCityId(authAgencyESEntity.getAgencyId());
                        measureDTO1.setCityName(authAgencyESEntity.getAgencyName());
                        for (Object[] objects : list) {//实测实量的数据
                            if (authAgencyESEntity.getAgencyId().equals(objects[2] == null ? "" : objects[2].toString())) {
                                double acceptanceNum = objects[0] == null ? 0 : Double.parseDouble(objects[0].toString());
                                double totalNum = objects[1] == null ? 0 : Double.parseDouble(objects[1].toString());
                                String mensurePercentOfPass = null;
                                if (totalNum != 0) {
                                    mensurePercentOfPass = numberFormat.format(acceptanceNum / totalNum * 100);
                                    if(mensurePercentOfPass.indexOf(".") == -1){
                                        mensurePercentOfPass += ".0";
                                    }
                                } else {
                                    mensurePercentOfPass = "0.0";
                                }
                                measureDTO1.setMensurePercentOfPass(mensurePercentOfPass);
                            }
                        }
                        measureDTOS.add(measureDTO1);
                    }
                }*/
                map.put("success", "0");
                map.put("data", measureDTOS);
                map.put("isExcel", list.size());
                return map;
            }
            //所有区域下的所有城市
            if ("1".equals(measureDTO.getRegionId()) && "1".equals(measureDTO.getCityId())) {
                List<Object[]> list = measureRepository.getMeasureDetail("1", "1");
                measureDTOS = getMeasureDtoByMeasureDetail(list,"2");
                /*for (AuthAgencyESEntity authAgencyESEntity : authAgencyESEntities) {
                    if (3 == authAgencyESEntity.getLevel()) {//筛选城市级别
                        MeasureDTO measureDTO1 = new MeasureDTO();
                        measureDTO1.setCityId(authAgencyESEntity.getAgencyId());
                        measureDTO1.setCityName(authAgencyESEntity.getAgencyName());
                        for (Object[] objects : list) {//实测实量的数据
                            if (authAgencyESEntity.getAgencyId().equals(objects[2] == null ? "" : objects[2].toString())) {
                                double acceptanceNum = objects[0] == null ? 0 : Double.parseDouble(objects[0].toString());
                                double totalNum = objects[1] == null ? 0 : Double.parseDouble(objects[1].toString());
                                String mensurePercentOfPass = null;
                                if (totalNum != 0) {
                                    mensurePercentOfPass = numberFormat.format(acceptanceNum / totalNum * 100);
                                    if(mensurePercentOfPass.indexOf(".") == -1){
                                        mensurePercentOfPass += ".0";
                                    }
                                } else {
                                    mensurePercentOfPass = "0.0";
                                }
                                measureDTO1.setMensurePercentOfPass(mensurePercentOfPass);
                            }
                        }
                        measureDTOS.add(measureDTO1);
                    }
                }*/
                map.put("success", "0");
                map.put("data", measureDTOS);
                map.put("isExcel", list.size());
                return map;
            }
            //城市下的项目
            if (!StringUtil.isEmpty(measureDTO.getCityId()) && !"1".equals(measureDTO.getCityId())) {
                List<Object[]> list = measureRepository.getMeasureDetail(measureDTO.getRegionId(), measureDTO.getCityId());
                /*for (AuthAgencyESEntity authAgencyESEntity : authAgencyESEntities) {
                    if (4 == authAgencyESEntity.getLevel() && authAgencyESEntity.getParentId().equals(measureDTO.getCityId())) {//筛选项目级别
                        MeasureDTO measureDTO1 = new MeasureDTO();
                        measureDTO1.setProjectId(authAgencyESEntity.getAgencyId());
                        measureDTO1.setProjectName(authAgencyESEntity.getAgencyName());
                        for (Object[] objects : list) {//实测实量的数据
                            if (authAgencyESEntity.getAgencyId().equals(objects[2] == null ? "" : objects[2].toString())) {
                                double acceptanceNum = objects[0] == null ? 0 : Double.parseDouble(objects[0].toString());
                                double totalNum = objects[1] == null ? 0 : Double.parseDouble(objects[1].toString());
                                String mensurePercentOfPass = null;
                                if (totalNum != 0) {
                                    mensurePercentOfPass = numberFormat.format(acceptanceNum / totalNum * 100);
                                    if(mensurePercentOfPass.indexOf(".") == -1){
                                        mensurePercentOfPass += ".0";
                                    }
                                } else {
                                    mensurePercentOfPass = "0.0";
                                }
                                measureDTO1.setMensurePercentOfPass(mensurePercentOfPass);
                            }
                        }
                        measureDTOS.add(measureDTO1);
                    }
                }*/
                measureDTOS = getMeasureDtoByMeasureDetail(list, "3");
                map.put("success", "0");
                map.put("data", measureDTOS);
                map.put("isExcel", list.size());
                return map;
            }

        }
        map.put("success", "1");
        map.put("data", measureDTOS);
        map.put("error", "请选择搜索条件");
        map.put("isExcel",0);
        return map;
    }

    @Override
    public WebPage getMeasuresCount(WebPage webPage, MeasureDTO measureDTO) {
        //根据条件查找分页总条数，并赋值webPage
        webPage.setRecordCount(measureRepository.getMeasuresCount(measureDTO.getProjectId(), measureDTO.getBuildingId(), measureDTO.getFloorId()));
        return webPage;
    }

    @Override
    public Map<String, String> addMeasure(MeasureEntity measureEntity, MeasureDetailEntity measureDetailEntity, InputStream fis, UserInformationEntity userInformationEntity) {
        Map<String, String> map = new HashMap<>();
        String msg = "";
        String error = "";
        String buildingId = measureEntity.getBuildingId();
        String floorId = measureEntity.getFloorId();
        List<String> str = new ArrayList<>();
        str.add(measureEntity.getProjectName());
        str.add(measureEntity.getBuildingName() + " " + measureEntity.getFloorName() + "实测实量");
        int nullNum = 0;
        //获取实测实量模板
        List<MeasureModelEntity> measureModelEntities = measureRepository.getAllMeasureModel();
        //需要添加的数据
        List<MeasureDetailEntity> addMeasureDetailList = new ArrayList<>();
        //需要添加的数据
        List<MeasureDateEntity> addMeasureDataList = new ArrayList<>();
        int addNum = 0;//导入数据条数
        try {
            //创建Excel工作薄
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            XSSFRow row = null;
            //判断导入文件是否跟用户所选楼栋及楼层相对应
            String buildingName = getCellValue(sheet.getRow(1).getCell(2));
            String floorName = getCellValue(sheet.getRow(1).getCell(4));
            if (StringUtil.isEmpty(buildingName) || StringUtil.isEmpty(floorName)) {
                map.put("error", "导入文件请填写楼栋及楼层");
                map.put("success", "1");
                return map;
            }
            if (!buildingName.equals(measureEntity.getBuildingName())) {
                map.put("error", "很抱歉！您上传的实测实量数据与选择的数据不符，请重新上传！");
                map.put("success", "1");
                return map;
            }
            if (!floorName.equals(measureEntity.getFloorName())) {
                map.put("error", "很抱歉！您上传的实测实量数据与选择的数据不符，请重新上传！");
                map.put("success", "1");
                return map;
            }
            //实测实量ID
            String measureId = null;
            //实测实量详情数据
            List<MeasureDetailEntity> measureDetailEntities = new ArrayList<>();
            //获取所有实测实量常量数据
            List<MeasureDataConstant> measureDataConstants = measureRepository.getAllMeasureDataConstant();
            //根据条件查询是否添加过数据
            List<MeasureDetailEntity> measureDetailEntities1 = measureRepository.getMeasureByBuildIdAndFloorIdAndInspectionPhaseId(measureEntity.getBuildingId(), measureEntity.getFloorId(), measureDetailEntity.getInspectionPhaseId(), measureEntity.getUnitName());
            /*int num = measureRepository.getMeasureModelNumById(measureDetailEntity.getInspectionPhaseId());
            if(measureDetailEntities1 != null && measureDetailEntities1.size() > 0 && measureDetailEntities1.size() == num){
                map.put("error","该楼层已经添加过数据");
                map.put("success","1");
                return map;
            }*/
            //根据楼栋及楼层获取实测实量实体类
            List<MeasureEntity> measureEntities = measureRepository.getMeasureByBuildIdAndFloorIdAndUnitId(measureEntity.getBuildingId(), measureEntity.getFloorId(), measureEntity.getUnitName());
            //判断之前有没有添加过
            if (measureEntities != null && measureEntities.size() > 0) {
                if (measureEntities.size() > 1) {
                    map.put("error", "数据有误，请联系管理员");
                    return map;
                } else {
                    measureEntity = measureEntities.get(0);
                    measureId = measureEntity.getId();
                }
                measureDetailEntities = measureRepository.getMeasureDetailByMeasureId(measureId);
            } else {
                measureId = IdGen.uuid();
                measureEntity.setId(measureId);
                measureEntity.setCreateBy(userInformationEntity.getStaffId());
                measureEntity.setCreateByName(userInformationEntity.getStaffName());
                measureEntity.setCreateDate(new Date());
                measureEntity.setModifyDate(new Date());
                measureEntity.setState("0");
                if (!StringUtil.isEmpty(measureEntity.getUnitName()) && StringUtil.isEmpty(measureEntity.getUnitId())
                        && !"0".equals(measureEntity.getUnitId()) && !"1".equals(measureEntity.getUnitId())) {
                    measureEntity.setUnitId(IdGen.uuid());
                }
                //添加实测实量数据
                measureRepository.addMeasure(measureEntity);
            }
            //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
            //因为模板有了两行空行，所以加2，数据从第四行开始
            row:
            for (int j = 4; j < sheet.getPhysicalNumberOfRows() + 1; j++) {
                row = sheet.getRow(j);
                //只要该检查分项的
                if (measureDetailEntity.getInspectionPhaseName().equals(getCellValue(row.getCell(1)))) {
                    if (measureDetailEntities1 != null && measureDetailEntities1.size() > 0) {
                        for (MeasureDetailEntity measureDetailEntity1 : measureDetailEntities1) {
                            //如果数据库已经有数据，则不添加
                            if (getCellValue(row.getCell(0)).equals(measureDetailEntity1.getSerialNum())) {
                                //为空，跳过
                                if ((row.getCell(7) == null || StringUtil.isEmpty(getCellValue(row.getCell(7))))
                                        && (row.getCell(8) == null || StringUtil.isEmpty(getCellValue(row.getCell(8))))
                                        && (row.getCell(9) == null || StringUtil.isEmpty(getCellValue(row.getCell(9))))
                                        && (row.getCell(10) == null || StringUtil.isEmpty(getCellValue(row.getCell(10))))
                                        && (row.getCell(11) == null || StringUtil.isEmpty(getCellValue(row.getCell(11))))) {

                                }else{
                                    msg += (j + 1) + "/";
                                    addNum++;
//                                msg += "第" + (j + 1) + "行已经添加过。\n";
                                    continue row;
                                }
                            }
                        }
                    }
                    StringBuffer data = new StringBuffer();
                    //实际循环次数
                    int count = 0;
                    //合格数据总和
                    double allData = 0.00;
                    //合格率
                    double qualificationRate = 0.00;
                    int index = 0;
                    NumberFormat numberFormat = NumberFormat.getNumberInstance();
                    numberFormat.setMaximumFractionDigits(3);
                    //为空直接跳过，下一行--------下面为校验
                    if ((row.getCell(7) == null || StringUtil.isEmpty(getCellValue(row.getCell(7))))
                            && (row.getCell(8) == null || StringUtil.isEmpty(getCellValue(row.getCell(8))))
                            && (row.getCell(9) == null || StringUtil.isEmpty(getCellValue(row.getCell(9))))
                            && (row.getCell(10) == null || StringUtil.isEmpty(getCellValue(row.getCell(10))))
                            && (row.getCell(11) == null || StringUtil.isEmpty(getCellValue(row.getCell(11))))) {
                        nullNum++;
                        continue row;
                    } else {
                        addNum++;
                        if (j == 8 || j == 18 || j == 28 || j == 38) {//五个一组
                            five:
                            for (int i = 7; i < measureDataConstants.get(j - 4).getCheckNum() + 7; i++, index++) {
                                //能被5整除
                                if (index % 5 == 0) {
                                    if ((row.getCell(i) == null || StringUtil.isEmpty(getCellValue(row.getCell(i))))
                                            && (row.getCell(i + 1) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 1))))
                                            && (row.getCell(i + 2) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 2))))
                                            && (row.getCell(i + 3) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 3))))
                                            && (row.getCell(i + 4) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 4))))) {
                                        i += 4;
                                        index += 4;
                                        continue five;
                                    }
                                    if (row.getCell(i) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i)))) {
                                        if (row.getCell(i + 1) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i + 1)))) {
                                            if (row.getCell(i + 2) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i + 2)))) {
                                                if (row.getCell(i + 3) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i + 3)))) {
                                                    if (row.getCell(i + 4) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i + 4)))) {
                                                        continue five;
                                                    } else {
                                                        map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                                        map.put("success", "1");
                                                        return map;
                                                    }
                                                } else {
                                                    map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                                    map.put("success", "1");
                                                    return map;
                                                }
                                            } else {
                                                map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                                map.put("success", "1");
                                                return map;
                                            }
                                        } else {
                                            map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                            map.put("success", "1");
                                            return map;
                                        }
                                    } else {
                                        map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                        map.put("success", "1");
                                        return map;
                                    }
                                }
                            }
                        } else if (j == 66) {//七个一组
                            seven:
                            for (int i = 7; i < measureDataConstants.get(j - 4).getCheckNum() + 7; i++, index++) {
                                //能被7整除
                                if (index % 7 == 0) {
                                    if ((row.getCell(i) == null || StringUtil.isEmpty(getCellValue(row.getCell(i))))
                                            && (row.getCell(i + 1) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 1))))
                                            && (row.getCell(i + 2) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 2))))
                                            && (row.getCell(i + 3) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 3))))
                                            && (row.getCell(i + 4) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 4))))
                                            && (row.getCell(i + 5) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 5))))
                                            && (row.getCell(i + 6) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 6))))) {
                                        i += 6;
                                        index += 6;
                                        continue seven;
                                    }
                                    if (row.getCell(i) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i)))) {
                                        if (row.getCell(i + 1) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i + 1)))) {
                                            if (row.getCell(i + 2) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i + 2)))) {
                                                if (row.getCell(i + 3) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i + 3)))) {
                                                    if (row.getCell(i + 4) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i + 4)))) {
                                                        if (row.getCell(i + 5) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i + 5)))) {
                                                            if (row.getCell(i + 6) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i + 6)))) {
                                                                break seven;
                                                            } else {
                                                                map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                                                map.put("success", "1");
                                                                return map;
                                                            }
                                                        } else {
                                                            map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                                            map.put("success", "1");
                                                            return map;
                                                        }
                                                    } else {
                                                        map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                                        map.put("success", "1");
                                                        return map;
                                                    }
                                                } else {
                                                    map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                                    map.put("success", "1");
                                                    return map;
                                                }
                                            } else {
                                                map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                                map.put("success", "1");
                                                return map;
                                            }
                                        } else {
                                            map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                            map.put("success", "1");
                                            return map;
                                        }
                                    } else {
                                        map.put("error", "您上传的第" + (j + 1) + "行数据的一组数据未全部填写,请重新添加。");
                                        map.put("success", "1");
                                        return map;
                                    }
                                }
                            }
                        } else {
                            one:
                            for (int i = 7; i < measureDataConstants.get(j - 4).getCheckNum() + 7; i++) {
                                boolean a = row.getCell(i) == null;
                                boolean b = StringUtil.isEmpty(getCellValue(row.getCell(i)));
                                if (row.getCell(i) == null || StringUtil.isEmpty(getCellValue(row.getCell(i)))) {
                                    if (row.getCell(i + 1) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 1)))) {
                                        if (row.getCell(i + 2) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 2)))) {
                                            if (row.getCell(i + 3) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 3)))) {
                                                if (row.getCell(i + 4) == null || StringUtil.isEmpty(getCellValue(row.getCell(i + 4)))) {
                                                    continue one;
                                                } else {
                                                    map.put("error", "第" + (j + 1) + "行，第" + (i + 1) + "列、第" + (i + 2) + "列、第" + (i + 3) + "列和第" + (i + 4) + "列为空,请检查表格后再重新导入");
                                                    map.put("success", "1");
                                                    return map;
                                                }
                                            } else {
                                                map.put("error", "第" + (j + 1) + "行，第" + (i + 1) + "列、第" + (i + 2) + "列和第" + (i + 3) + "列为空,请检查表格后再重新导入");
                                                map.put("success", "1");
                                                return map;
                                            }
                                        } else {
                                            map.put("error", "第" + (j + 1) + "行，第" + (i + 1) + "列和第" + (i + 2) + "列为空,请检查表格后再重新导入");
                                            map.put("success", "1");
                                            return map;
                                        }
                                    } else {
                                        map.put("error", "第" + (j + 1) + "行，第" + (i + 1) + "列为空,请检查表格后再重新导入");
                                        map.put("success", "1");
                                        return map;
                                    }
                                }
                            }
                        }
                    }
                    //第一列为空------下面为合格率计算
                    if ((row.getCell(7) == null || StringUtil.isEmpty(getCellValue(row.getCell(7))))
                            && (row.getCell(8) == null || StringUtil.isEmpty(getCellValue(row.getCell(8))))
                            && (row.getCell(9) == null || StringUtil.isEmpty(getCellValue(row.getCell(9))))
                            && (row.getCell(10) == null || StringUtil.isEmpty(getCellValue(row.getCell(10))))
                            && (row.getCell(11) == null || StringUtil.isEmpty(getCellValue(row.getCell(11))))) {
                        nullNum++;
                        continue row;
                    } else {
                        if (j == 8) {//五个一组 最大偏差值在20以内
                            int cellIndex = 0;
                            cell_8:
                            for (int i = 7; i < measureDataConstants.get(j - 4).getCheckNum() + 7; i++,cellIndex++) {
                                if (row.getCell(i) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i)))) {
                                    data.append("/" + getCellValue(row.getCell(i)));
                                    if(0 == cellIndex%5){
                                        List<Double> list = new ArrayList<>();
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+1))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+2))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+3))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+4))));
                                        list.sort(new Comparator<Double>() {//排序
                                            @Override
                                            public int compare(Double o1, Double o2) {
                                                if(o1 < o2){
                                                    return -1;
                                                }else {
                                                    return 1;
                                                }
                                            }
                                        });
                                        Double maxNum = list.get(4) - list.get(0);//最大偏差值
                                        if(maxNum <= 20){
                                            List<Double> list1 = new ArrayList<>();
                                            list1.add(0.0);
                                            list1.add(list.get(1)-list.get(0));
                                            list1.add(list.get(2)-list.get(0));
                                            list1.add(list.get(3)-list.get(0));
                                            list1.add(list.get(4)-list.get(0));
                                            for (int k = 0; k < 5; k++) {
                                                allData = allData + (((list1.get(k) >= measureDataConstants.get(j - 4).getLeftCode()
                                                        && list1.get(k) <= measureDataConstants.get(j - 4).getRightCode())) ? 1 : 0);
                                                count++;
                                            }
                                        }else{//大于20 全不合格
                                            count+=5;
                                        }
                                    }
                                } else {
                                    break cell_8;
                                }
                            }
                        }else if(j == 18 || j == 28 || j == 38){//五个一组 >15判断
                            int cellIndex = 0;
                            cell_18_28_38:
                            for (int i = 7; i < measureDataConstants.get(j - 4).getCheckNum() + 7; i++,cellIndex++) {
                                if (row.getCell(i) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i)))) {
                                    data.append("/" + getCellValue(row.getCell(i)));
                                    if(0 == cellIndex%5){
                                        List<Double> list = new ArrayList<>();
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+1))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+2))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+3))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+4))));
                                        list.sort(new Comparator<Double>() {//排序
                                            @Override
                                            public int compare(Double o1, Double o2) {
                                                if(o1 < o2){
                                                    return -1;
                                                }else {
                                                    return 1;
                                                }
                                            }
                                        });
                                        Double maxNum = list.get(4) - list.get(0);//最大偏差值
                                        if(maxNum <= 15){
                                            List<Double> list1 = new ArrayList<>();
                                            list1.add(0.0);
                                            list1.add(list.get(1)-list.get(0));
                                            list1.add(list.get(2)-list.get(0));
                                            list1.add(list.get(3)-list.get(0));
                                            list1.add(list.get(4)-list.get(0));
                                            for (int k = 0; k < 5; k++) {
                                                allData = allData + (((list1.get(k) >= measureDataConstants.get(j - 4).getLeftCode()
                                                        && list1.get(k) <= measureDataConstants.get(j - 4).getRightCode())) ? 1 : 0);
                                                count++;
                                            }
                                        }else{//大于15 全不合格
                                            count+=5;
                                        }
                                    }
                                } else {
                                    break cell_18_28_38;
                                }
                            }
                        }else if(j == 66){//七个一组
                            int cellIndex = 0;
                            cell_66:
                            for (int i = 7; i < measureDataConstants.get(j - 4).getCheckNum() + 7; i++,cellIndex++) {
                                if (row.getCell(i) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i)))) {
                                    data.append("/" + getCellValue(row.getCell(i)));
                                    if(0 == cellIndex%7){
                                        List<Double> list = new ArrayList<>();
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+1))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+2))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+3))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+4))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+5))));
                                        list.add(Double.parseDouble(getCellValue(row.getCell(i+6))));
                                        list.sort(new Comparator<Double>() {//排序
                                            @Override
                                            public int compare(Double o1, Double o2) {
                                                if(o1 < o2){
                                                    return -1;
                                                }else {
                                                    return 1;
                                                }
                                            }
                                        });
                                        Double maxNum = list.get(6) - list.get(0);//最大偏差值
                                        if(maxNum <= 15){
                                            List<Double> list1 = new ArrayList<>();
                                            list1.add(0.0);
                                            list1.add(list.get(1)-list.get(0));
                                            list1.add(list.get(2)-list.get(0));
                                            list1.add(list.get(3)-list.get(0));
                                            list1.add(list.get(4)-list.get(0));
                                            list1.add(list.get(5)-list.get(0));
                                            list1.add(list.get(6)-list.get(0));
                                            for (int k = 0; k < 7; k++) {
                                                allData = allData + (((list1.get(k) >= measureDataConstants.get(j - 4).getLeftCode()
                                                        && list1.get(k) <= measureDataConstants.get(j - 4).getRightCode())) ? 1 : 0);
                                                count++;
                                            }
                                        }else{//大于15 全不合格
                                            count+=7;
                                        }
                                    }
                                } else {
                                    break cell_66;
                                }
                            }
                        }else{
                            //最多循环次数
                            cell:
                            for (int i = 7; i < measureDataConstants.get(j - 4).getCheckNum() + 7; i++) {
                                if (row.getCell(i) != null && !StringUtil.isEmpty(getCellValue(row.getCell(i)))) {
                                    data.append("/" + getCellValue(row.getCell(i)));
                                    //如果是防开裂
                                    if (measureDetailEntity.getInspectionPhaseId().equals("1d695a7c7d9a4f3090101b9478d9584a")) {
                                        allData = allData + (Float.parseFloat(String.valueOf(row.getCell(i))) == measureDataConstants.get(j - 4).getLeftCode() ? 1 : 0);
                                    } else {
                                        //在区间范围内的为1，否则为0
                                        allData = allData + ((Float.parseFloat(String.valueOf(row.getCell(i))) >= measureDataConstants.get(j - 4).getLeftCode()
                                                && (Float.parseFloat(String.valueOf(row.getCell(i))) <= measureDataConstants.get(j - 4).getRightCode())) ? 1 : 0);
                                    }
                                    count++;
                                } else {
                                    break cell;
                                }
                            }
                        }
                    }
                    if (count != 0) {
                        String qualificationRateStr = numberFormat.format(allData / count);
                        if(qualificationRateStr.indexOf(".") == -1){
                            qualificationRateStr += ".0";
                        }
                        qualificationRate = Double.parseDouble(qualificationRateStr);
                    }
                    //实测实量ID
                    String measureDetailId = IdGen.uuid();
                    MeasureDetailEntity measureDetailEntity1 = new MeasureDetailEntity();
                    measureDetailEntity1.setId(measureDetailId);
                    measureDetailEntity1.setMeasureId(measureId);
                    measureDetailEntity1.setBuildingId(measureEntity.getBuildingId());
                    measureDetailEntity1.setBuildingName(measureEntity.getBuildingName());
                    measureDetailEntity1.setFloorId(measureEntity.getFloorId());
                    measureDetailEntity1.setFloorName(measureEntity.getFloorName());
                    measureDetailEntity1.setProjectId(measureEntity.getProjectId());
                    measureDetailEntity1.setProjectName(measureEntity.getProjectName());
                    //实测实量模板是从0下标开始的，所以要减4
                    measureDetailEntity1.setInspectionPhaseId(measureModelEntities.get(j - 4).getInspectionPhaseId());
                    measureDetailEntity1.setInspectionPhaseName(measureDetailEntity.getInspectionPhaseName());
                    measureDetailEntity1.setSerialNum(getCellValue(row.getCell(0)));
                    measureDetailEntity1.setInspectionContentId(measureModelEntities.get(j - 4).getInspectionContentId());
                    measureDetailEntity1.setInspectionContentName(measureModelEntities.get(j - 4).getInspectionContent());
                    measureDetailEntity1.setCheckPoints(Integer.parseInt(getCellValue(row.getCell(5))));
                    measureDetailEntity1.setState("0");
                    measureDetailEntity1.setQualificationRate(qualificationRate);
                    measureDetailEntity1.setIsOpenQrCode(measureDetailEntity.getIsOpenQrCode());
                    measureDetailEntity1.setUnitId(measureEntity.getUnitId());
                    measureDetailEntity1.setUnitName(measureEntity.getUnitName());
                    measureDetailEntity1.setCreateDate(new Date());
                    measureDetailEntity1.setAcceptanceNum((int) allData);
                    measureDetailEntity1.setTotalNum(count);
                    MeasureDateEntity measureDateEntity = new MeasureDateEntity();
                    measureDateEntity.setId(IdGen.uuid());
                    measureDateEntity.setMeasureId(measureDetailId);
                    measureDateEntity.setInspectionPhaseId(measureDataConstants.get(j - 4).getId());
                    measureDateEntity.setData(data.toString());
                    measureDateEntity.setState("0");
                    measureDateEntity.setSerialNum(getCellValue(row.getCell(0)));
                    addMeasureDetailList.add(measureDetailEntity1);
                    addMeasureDataList.add(measureDateEntity);
                }
            }

            //循环添加数据  放在for循环外面添加，防止for循环里面循环过程中出现错误，return了，数据添加不完整
            for (MeasureDetailEntity measureDetailEntity1 : addMeasureDetailList) {
                measureRepository.addMeasureDetail(measureDetailEntity1);
            }
            //循环添加数据
            for (MeasureDateEntity measureDateEntity : addMeasureDataList) {
                measureRepository.addMeasureData(measureDateEntity);
            }
            //生成二维码
            new Thread() {
                public void run() {
                    if (measureRepository.getQrCodeByBuildingIdAndFloorId(buildingId, floorId)) {
                        MeasureQrCodeEntity measureQrCodeEntity = new MeasureQrCodeEntity();
                        measureQrCodeEntity.setBuildingId(buildingId);
                        measureQrCodeEntity.setFloorId(floorId);
                        measureQrCodeEntity.setId(IdGen.uuid());
                        //生成二维码
                        String path = ImageConfig.PIC_OSS_ADMIN_URL + "QRCode"; //域名访问地址
                        String filePath = ImageConfig.PIC_SERVER_ADMIN_URL + "QRCode"; //服务器路径
                        String destPath = filePath + "/ESLogo.png";//logo图片位置
                        String url = IdGen.uuid();
                        String imgName = path + "/" + url + ".png";//访问地址
                        filePath = filePath + "/" + url + ".png";//保存地址

                        try {
                            QRCodeUtil.encode(AppConfig.QR_Code_Url + "buildingId=" + buildingId + "&floorId=" + floorId, destPath, filePath, 600, 600, str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        measureQrCodeEntity.setQrCodeUrl(imgName);
                        measureRepository.saveMeasureQrCodeEntity(measureQrCodeEntity);
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "未知错误");
            map.put("success", "1");
            return map;
        }
        int num = 0;
        for (MeasureModelEntity measureModelEntity : measureModelEntities) {
            if (measureModelEntity.getInspectionPhaseName().equals(measureDetailEntity.getInspectionPhaseName())) {
                num++;
            }
        }
        if (nullNum == num) {
            map.put("error", "很抱歉！您上传的实测实量数据与选择的数据不符，请重新上传！");
            map.put("success", "1");
            return map;
        }
        if (!StringUtil.isEmpty(msg)) {
            String msgArr[] = msg.split("/");
            num = 0;
            String errStr = "";
            for (MeasureModelEntity measureModelEntity : measureModelEntities) {
                if (measureModelEntity.getInspectionPhaseName().equals(measureDetailEntity.getInspectionPhaseName())) {
                    errStr += (Integer.parseInt(measureModelEntity.getSerialNum()) + 4) + "行、";
                    num++;
                }
            }
            if (msgArr.length == num || msgArr.length == addNum) {
                error = "很抱歉！您上传的数据已存在，不可再次添加。";
                map.put("error", error);
                map.put("success", "1");
                return map;
            } else {
                errStr = "";
                if ((msgArr.length + nullNum) == num) {
                    for (int i = 0; i < msgArr.length; i++) {
                        if (i != msgArr.length - 1) {
                            errStr += msgArr[i] + "行、";
                        } else {
                            errStr += msgArr[i] + "行";
                        }
                    }
                    error = "很抱歉！您上传文件中的" + errStr + "数据已存在，不可再次添加。";
                    map.put("error", error);
                    map.put("success", "1");
                    return map;
                }
                msg = "您上传的数据中，";
                for (int i = 0; i < msgArr.length; i++) {
                    if (i != msgArr.length - 1) {
                        msg += msgArr[i] + "行、";
                    } else {
                        msg += msgArr[i] + "行";
                    }
                }
                msg += "数据已存在，无法再次添加，其余数据已添加成功";
            }
        }
        map.put("msg", msg);
        map.put("success", "0");
        return map;
    }

    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(Cell cell) {
        String value = null;
        //简单的查检列类型
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long) cell.getNumericCellValue();
                value = dd + "";
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

    @Override
    public List<AuthAgencyESEntity> getAgencyList(UserInformationEntity userInformationEntity) {
        List<AuthAgencyESEntity> list = new ArrayList<>();
        list.add(new AuthAgencyESEntity("", "请选择", "", 2));
        list.add(new AuthAgencyESEntity("", "请选择", "", 3));
        list.add(new AuthAgencyESEntity("", "请选择", "", 4));
        Set<AuthAgencyESEntity> authAgencyESEntities = new LinkedHashSet<>();
        //查看是否给当前登录人授权过总部权限
        if (authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000", userInformationEntity.getStaffId())) {
            authAgencyESEntities.add(new AuthAgencyESEntity("1", "全部", "", 2));
            authAgencyESEntities.add(new AuthAgencyESEntity("1", "全部", "", 3));
            //查看总部下的所有内容
            List<AuthAgencyESEntity> agencyEsEntities = authAgencyRepository.getESAllAgencyList();
            authAgencyESEntities.addAll(agencyEsEntities);
        }
        if (authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001", userInformationEntity.getStaffId())) {
            authAgencyESEntities.add(new AuthAgencyESEntity("1", "全部", "", 2));
            authAgencyESEntities.add(new AuthAgencyESEntity("1", "全部", "", 3));
            //如果是区域级别，找出拥有区域权限的权限id
            List<String> parentIdList = authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000001", userInformationEntity.getStaffId());
            if (parentIdList != null && !parentIdList.isEmpty()) {
                //区域 城市下项目
                List<AuthAgencyESEntity> parEntities = authAgencyRepository.getESAllAgencyListByParentId(parentIdList, null, "100000003");
                if (parEntities != null && !parEntities.isEmpty()) {
                    List<String> cityIarentId = new ArrayList<>();
                    for (AuthAgencyESEntity pro : parEntities) {
                        cityIarentId.add(pro.getAgencyId());
                    }
                    if (cityIarentId != null && !cityIarentId.isEmpty()) {
                        List<String> cityAndParIds = new ArrayList();
                        cityAndParIds.addAll(cityIarentId);
                        cityAndParIds.addAll(parentIdList);
                        List<AuthAgencyESEntity> cityAgencyEntities = authAgencyRepository.getESAllAgencyListByParentId(cityIarentId, cityAndParIds, "100000002");
                        authAgencyESEntities.addAll(cityAgencyEntities);
                    }
                }
            }
        }
        if (authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003", userInformationEntity.getStaffId())) {
            authAgencyESEntities.add(new AuthAgencyESEntity("1", "全部", "", 3));
            //城市ID
            List<String> parentIdList = authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000003", userInformationEntity.getStaffId());
            //根据城市id获取城市实体类
            List<AuthAgencyESEntity> cityList = authAgencyRepository.getESAllAgencyListByParentId(parentIdList, parentIdList, "100000003");
            authAgencyESEntities.addAll(cityList);
            //区域ID
            List<String> regionIdList = new ArrayList<>();
            for (AuthAgencyESEntity authAgencyESEntity : cityList) {
                regionIdList.add(authAgencyESEntity.getParentId());
            }
            if (regionIdList != null && regionIdList.size() > 0) {
                //根据区域ID获取区域实体类
                List<AuthAgencyESEntity> regionList = authAgencyRepository.getESAllAgencyListByParentId(regionIdList, regionIdList, "100000001");
                authAgencyESEntities.addAll(regionList);
            }
            //根据条件删选
            if (parentIdList != null && !parentIdList.isEmpty()) {
                List<AuthAgencyESEntity> agencyEsEntities = authAgencyRepository.getESAllAgencyListByParentId(parentIdList, parentIdList, "100000002");
                authAgencyESEntities.addAll(agencyEsEntities);
            }
        }
        if (authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002", userInformationEntity.getStaffId())) {
            List<String> agencyIdList = authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000002", userInformationEntity.getStaffId());
            //根据项目id获取项目实体类
            List<AuthAgencyESEntity> projectList = authAgencyRepository.getESAllAgencyListByParentId(agencyIdList, agencyIdList, "100000002");
            authAgencyESEntities.addAll(projectList);
            //区域ID
            List<String> cityIdList = new ArrayList<>();
            for (AuthAgencyESEntity authAgencyESEntity : projectList) {
                cityIdList.add(authAgencyESEntity.getParentId());
            }
            if (cityIdList != null && cityIdList.size() > 0) {
                //根据城市ID获取城市实体类
                List<AuthAgencyESEntity> cityList = authAgencyRepository.getESAllAgencyListByParentId(cityIdList, cityIdList, "100000003");
                authAgencyESEntities.addAll(cityList);
                if (cityIdList != null && cityIdList.size() > 0) {
                    List<String> regionIdList = new ArrayList<>();
                    for (AuthAgencyESEntity authAgencyESEntity : cityList) {
                        regionIdList.add(authAgencyESEntity.getParentId());
                    }
                    if (regionIdList != null && regionIdList.size() > 0) {
                        //根据城市ID获取城市实体类
                        List<AuthAgencyESEntity> regionList = authAgencyRepository.getESAllAgencyListByParentId(regionIdList, regionIdList, "100000001");
                        authAgencyESEntities.addAll(regionList);
                    }
                }
            }
            //根据条件删选
            /*if (agencyIdList != null && !agencyIdList.isEmpty()) {
                List<AuthAgencyESEntity> agencyEsEntities = authAgencyRepository.getESAllAgencyListByParentId(null, agencyIdList, "100000002");
                authAgencyESEntities.addAll(agencyEsEntities);
            }*/
        }
        Iterator<AuthAgencyESEntity> iterator = authAgencyESEntities.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @Override
    public Map<String, String> getUnitByBuildIdAndFloorId(String buildId, String floorId) {
        List<MeasureEntity> measureEntities = measureRepository.getMeasureUnitByBuildIdAndFloorId(buildId, floorId);
        Map<String, String> units = new LinkedHashMap<>();
        units.put("0", "请选择");
        if (measureEntities != null && measureEntities.size() > 0) {
            for (MeasureEntity measureEntity : measureEntities) {
                units.put(measureEntity.getUnitId(), measureEntity.getUnitName());
            }
        }
        units.put("999", "其他单元");
        return units;
    }

    @Override
    public List<MeasureDetailEntity> isFirstSave(String projectId, String buildingId, String floorId) {
        return measureRepository.getMeasureDetailByFloorId(projectId, buildingId, floorId);
    }

    @Override
    public Map<String, String> getInspectionPhaseList() {
        Map<String, String> inspectionPhases = new LinkedHashMap<>();
        inspectionPhases.put("", "请选择");
        List<MeasureModelEntity> measureModelEntities = measureRepository.getMeasureModelByInspectionPhase();
        if (measureModelEntities != null && measureModelEntities.size() > 0) {
            for (MeasureModelEntity measureModelEntity : measureModelEntities) {
                inspectionPhases.put(measureModelEntity.getInspectionPhaseId(), measureModelEntity.getInspectionPhaseName());
            }
        }
        return inspectionPhases;
    }

    @Override
    public MeasureDTO getMeasureDTOById(String measureId, String projectId, String buildingId, String floorId) {
        MeasureDTO measureDTO = new MeasureDTO();
        MeasureEntity measureEntity = measureRepository.getMeasureById(measureId);
        if (measureEntity != null) {
            measureDTO.setRegionName(measureEntity.getRegionName());
            measureDTO.setCityName(measureEntity.getCityName());
            measureDTO.setProjectName(measureEntity.getProjectName());
            measureDTO.setBuildingName(measureEntity.getBuildingName());
            measureDTO.setFloorName(measureEntity.getFloorName());
            measureDTO.setCreateBy(measureEntity.getCreateByName());
        }
        //项目合格数及总数
        List<Object[]> measureDetailProject = measureRepository.getMeasureNumByPIdAndBidAndFid(projectId, null, null);
        if (measureDetailProject != null && measureDetailProject.size() > 0) {
            measureDTO.setProjectPercentOfPass(getMeasureDataDto(measureDetailProject.get(0)) + "%");
        }
        //楼栋合格数及总数
        List<Object[]> measureDetailBuild = measureRepository.getMeasureNumByPIdAndBidAndFid(projectId, buildingId, null);
        if (measureDetailBuild != null && measureDetailBuild.size() > 0) {
            measureDTO.setBuildPercentOfPass(getMeasureDataDto(measureDetailBuild.get(0)) + "%");
        }
        //楼栋合格数及总数
        List<Object[]> measureDetailFloor = measureRepository.getMeasureNumByPIdAndBidAndFid(null, null, floorId);
        if (measureDetailFloor != null && measureDetailFloor.size() > 0) {
            measureDTO.setMensurePercentOfPass(getMeasureDataDto(measureDetailFloor.get(0)) + "%");
        }
        return measureDTO;
    }

    @Override
    public List<MeasureInspectionPhaseDTO> getMeasureInspectionPhaseByFloorId(String floorId) {
        List<MeasureInspectionPhaseDTO> measureInspectionPhaseDTOS = new ArrayList<>();
        //根据楼层ID获取该楼层所拥有的检查分项及单元
        List<Object[]> list = measureRepository.getInspectionPhaseByFloorId(floorId);
        if (list != null && list.size() > 0) {
            for (Object[] objects : list) {
                MeasureInspectionPhaseDTO measureInspectionPhaseDTO = new MeasureInspectionPhaseDTO();
                measureInspectionPhaseDTO.setInspectionPhaseId(objects[0] == null ? "" : objects[0].toString());
                measureInspectionPhaseDTO.setInspectionPhaseName(objects[1] == null ? "" : objects[1].toString());
                measureInspectionPhaseDTO.setUnitId(objects[2] == null ? "" : objects[2].toString());
                measureInspectionPhaseDTO.setUnitName(objects[3] == null ? "" : objects[3].toString());
                measureInspectionPhaseDTOS.add(measureInspectionPhaseDTO);
            }
        }
        return measureInspectionPhaseDTOS;
    }

    @Override
    public List<MeasureModelDTO> getMeasureModelByFloorAndInspectionPhaseId(String floorId, String inspectionPhaseId, String unitId) {
        List<MeasureModelDTO> measureModelDTOS = new ArrayList<>();
        List<Object[]> list = measureRepository.getMeasureModelDTOByFloorId(floorId, inspectionPhaseId, unitId);
        if (list != null && list.size() > 0) {
            String num = list.get(0)[4] == null ? "" : list.get(0)[4].toString();
            for (Object[] objects : list) {
                MeasureModelDTO measureModelDTO = new MeasureModelDTO();
                measureModelDTO.setSerialNum(objects[0] == null ? "" : objects[0].toString());
                measureModelDTO.setInspectionPhaseName(objects[1] == null ? "" : objects[1].toString());
                measureModelDTO.setInspectionContentName(objects[2] == null ? "" : objects[2].toString());
                measureModelDTO.setEvaluationCriteria(objects[3] == null ? "" : objects[3].toString());
                measureModelDTO.setCheckPoints(objects[4] == null ? "" : objects[4].toString());
                measureModelDTO.setQualificationRate(objects[5] == null ? "" : String.valueOf(Double.parseDouble(objects[5].toString()) * 100) + "%");
                String data = objects[6] == null ? "" : objects[6].toString();
                List<String> dataList = new ArrayList<>();
                if (!StringUtil.isEmpty(data)) {
                    String[] dataArr = data.split("/");
                    if (dataArr != null && dataArr.length > 0) {
                        for (int i = 1; i < dataArr.length; i++) {
                            dataList.add(dataArr[i]);
                        }
                    }
                }
                measureModelDTO.setData(dataList);
                measureModelDTO.setNum(num);
                measureModelDTOS.add(measureModelDTO);
            }
        }
        return measureModelDTOS;
    }

    @Override
    public void getMeasureDataToExcle(MeasureDTO measureDTO, UserInformationEntity userInformationEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后1位
        numberFormat.setMaximumFractionDigits(1);
        //判断导出数据的项目级别
        HttpSession session = request.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        if (!StringUtil.isEmpty(measureDTO.getFloorId())) { //搜索到楼层
            List<Object[]> list = measureRepository.getQuantityByFloorId(measureDTO.getFloorId());//0：分项名称 1：单元名称 2：内容名称 3：评判标准 4：检测点数 5：合格率 6：导入数据
            String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "QuantityFloorData.xlsx";
            File file = new File(filePath);
            //得到Excel常用对象
            InputStream input = new FileInputStream(file);
            //得到Excel工作簿对象
            XSSFWorkbook wb = new XSSFWorkbook(input);
            input.close();
            //设置单元格样式
            XSSFCellStyle bodyStyle = wb.createCellStyle();
            //设置样式
            bodyStyle.setWrapText(true);
            bodyStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            bodyStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            bodyStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            bodyStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
            bodyStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
            //获取工作表
            XSSFSheet sheet = wb.getSheetAt(0);
            if (null != list && list.size() > 0) {
                int flag = 1;
                XSSFRow row = null;
                for (Object[] obj : list) {
                    List<String> list1 = new ArrayList<>();
                    list1.add(String.valueOf(flag));
                    String unit = (obj[1] == null ? "" : (obj[1].toString()));
                    if (!StringUtil.isEmpty(unit)) {
                        list1.add(unit + "\r\n" + (obj[0] == null ? "" : obj[0].toString()));
                    } else {
                        list1.add(obj[0] == null ? "" : obj[0].toString());
                    }
                    list1.add(obj[2] == null ? "" : obj[2].toString());
                    list1.add(obj[3] == null ? "" : obj[3].toString());
                    list1.add(obj[4] == null ? "" : obj[4].toString());
                    String resultQualified = "0.0";
                    if (null != obj[5] && !"".equals(obj[5])) {
                        resultQualified = numberFormat.format(Double.parseDouble(obj[5].toString()) * 100);
                        if (resultQualified.indexOf(".") == -1) {
                            resultQualified = resultQualified + ".0";
                        }
                    }
                    list1.add(resultQualified + "%");
                    if (null != obj[6] && !StringUtil.isEmpty(obj[6].toString())) {
                        String str[] = obj[6].toString().replace("/", ",").substring(1).split(",");
                        for (int i = 0; i < str.length; i++) {
                            list1.add(str[i]);
                        }
                    }
                    //得到Excel工作表的行
                    row = sheet.createRow(flag);
                    XSSFCell cell = null;
                    for (int i = 0; i < list1.size(); i++) {
                        //得到Excel工作表的列
                        cell = row.createCell(i);
                        cell.setCellStyle(bodyStyle);
                        cell.setCellValue(new XSSFRichTextString(list1.get(i)));
                    }
                    flag++;
                }
            }
            String fileName = measureDTO.getProjectName() + "-" + measureDTO.getBuildingName() + "-" + measureDTO.getFloorName() + "实测实量数据.xlsx";
            this.export(response, fileName, wb);
        } else if (!StringUtil.isEmpty(measureDTO.getBuildingId())) {//搜索到楼栋
            List<Object[]> list = measureRepository.getFloorByBuildingId(measureDTO.getBuildingId());//0：楼层id 1：楼层名 2：合格数 3：总数
            List<Object[]> score = measureRepository.getSubitemScoreByBuildingId(measureDTO.getBuildingId());//0：楼层id 1：分项id 2：合格数 3：总数
            List<String> subitemIds = measureRepository.getMeasureSubitemModel();//分项id  按导出顺序查询
            String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "QuantityBuildingData.xlsx";
            String fileName = measureDTO.getProjectName() + "-" + measureDTO.getBuildingName() + "实测实量数据.xlsx";
            this.exportForTemplate(list, score, subitemIds, filePath, response, fileName);
        } else if (!StringUtil.isEmpty(measureDTO.getProjectId())) {//搜索到项目
            List<Object[]> list = measureRepository.getBuildingByProjectId(measureDTO.getProjectId());//0：楼栋id 1：楼栋名 2：合格数 3：总数
            List<Object[]> score = measureRepository.getSubitemScoreByProjectId(measureDTO.getProjectId());//0：楼栋id 1：分项id 2：合格数 3：总数
            List<String> subitemIds = measureRepository.getMeasureSubitemModel();//分项id  按导出顺序查询
            String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "QuantityProjectData.xlsx";
            String fileName = measureDTO.getProjectName() + "实测实量数据.xlsx";
            this.exportForTemplate(list, score, subitemIds, filePath, response, fileName);
        } else if (!StringUtil.isEmpty(measureDTO.getCityId()) && !"1".equals(measureDTO.getCityId())) {//搜索到城市
            List<Object[]> list = measureRepository.getProjectByCityId(measureDTO.getCityId());//0：项目id 1：项目名 2：合格数 3：总数
            List<Object[]> score = measureRepository.getSubitemScoreByCityId(measureDTO.getCityId());//0：项目id 1：分项id 2：合格数 3：总数
            List<String> subitemIds = measureRepository.getMeasureSubitemModel();//分项id  按导出顺序查询
            String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "QuantityCityData.xlsx";
            String fileName = measureRepository.getAgencyNameByAgencyId(measureDTO.getCityId()) + "城市实测实量数据.xlsx";
            this.exportForTemplate(list, score, subitemIds, filePath, response, fileName);
        } else if (!StringUtil.isEmpty(measureDTO.getRegionId()) && !"1".equals(measureDTO.getRegionId())) {//搜索到区域
            List<Object[]> list = measureRepository.getCityByRegionId(measureDTO.getRegionId());//0：城市id 1：城市名 2：合格数 3：总数
            List<Object[]> score = measureRepository.getSubitemScoreByRegionId(measureDTO.getRegionId());//0：城市id 1：分项id 2：合格数 3：总数
            List<String> subitemIds = measureRepository.getMeasureSubitemModel();//分项id  按导出顺序查询
            String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "QuantityRegionData.xlsx";
            String fileName = measureRepository.getAgencyNameByAgencyId(measureDTO.getRegionId()) + "区域实测实量数据.xlsx";
            this.exportForTemplate(list, score, subitemIds, filePath, response, fileName);
        } else if ("1".equals(measureDTO.getRegionId())) {
            List<Object[]> list = measureRepository.getRegionByGroupId();//0：区域id 1：区域名 2：合格数 3：总数
            List<Object[]> score = measureRepository.getSubitemScoreByGroupId();//0：区域id 1：分项id 2：合格数 3：总数
            List<String> subitemIds = measureRepository.getMeasureSubitemModel();//分项id  按导出顺序查询group
            String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "QuantityGroupData.xlsx";
            this.exportForTemplate(list, score, subitemIds, filePath, response, "金茂集团实测实量数据.xlsx");
        }
    }

    public String getMeasureExcelModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        BufferedInputStream buffer = null;
        OutputStream out = null;
        HttpSession session = httpServletRequest.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "实测实量添加模板.xlsx";
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
    public void updateQrCodeState(UserInformationEntity userInformationEntity, String state) {
        measureRepository.updateQrCode(userInformationEntity.getStaffId(), state);
    }

    @Override
    public String getQrCodeState() {
        return measureRepository.getQrCodeState();
    }

    public String getQrCodeByBuildingIdAndFloorId(String buildingId, String floorId) {
        String url = "";
        List<MeasureQrCodeEntity> measureQrCodeEntities = measureRepository.getQrCodeByBuildingId(buildingId, floorId);
        if (null != measureQrCodeEntities && measureQrCodeEntities.size() > 0) {
            url = measureQrCodeEntities.get(0).getQrCodeUrl();
        }
        return url;
    }

    @Override
    public boolean isUnit(String projectId, String buildingId, String floorId, String inspectionPhaseId) {
        //该楼层下该检查分项下的单元
        List<String> list = measureRepository.getUnit(projectId, buildingId, floorId, inspectionPhaseId);
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void statisticsQuantityByTiming() {

        List<Object[]> projectId = measureRepository.getAgency("3");//项目
        List<Object[]> cityId = measureRepository.getAgency("2");//城市
        List<Object[]> regionId = measureRepository.getAgency("1");//区域

        List<Object[]> projectList = measureRepository.getScoreByAgencyId("3"); //项目检查分项、分数
        List<Object[]> cityList = measureRepository.getScoreByAgencyId("2");//城市检查分项、分数
        List<Object[]> regionList = measureRepository.getScoreByAgencyId("1");//区域检查分项、分数

        List<Object[]> projectContentList = measureRepository.getContentScoreByAgencyId("3"); //项目检查内容、分数
        List<Object[]> cityContentList = measureRepository.getContentScoreByAgencyId("2");//城市检查内容、分数
        List<Object[]> regionContentList = measureRepository.getContentScoreByAgencyId("1");//区域检查内容、分数
        this.saveMeasureCount(projectId, projectList, projectContentList, "3");
        this.saveMeasureCount(cityId, cityList, cityContentList, "2");
        this.saveMeasureCount(regionId, regionList, regionContentList, "1");

    }

    @Override
    public void exportAllDataForExcel(String agencyId, UserInformationEntity userInformationEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String serverRealPath = request.getSession().getServletContext().getRealPath("");
        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "QuantityAllData.xlsx";
        //判断导出数据的项目级别
        File file = new File(filePath);
        //得到Excel常用对象
        InputStream input = new FileInputStream(file);
        //得到Excel工作簿对象
        XSSFWorkbook wb = new XSSFWorkbook(input);
        input.close();
        //设置单元格样式
        XSSFCellStyle bodyStyle = wb.createCellStyle();
        //设置样式
        bodyStyle.setWrapText(true);
        bodyStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        bodyStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        //获取工作表
        XSSFSheet sheet = wb.getSheetAt(0);
        if (!StringUtil.isEmpty(agencyId)) {
            String str[] = agencyId.split(",");
            List<String> strList = Arrays.asList(str);
            List<MeasureCountEntity> measureCountEntities = measureRepository.getMeasureCount(strList);
            List<Object[]> objects = measureRepository.getAgency(strList);//0:项目id 1：项目名 2：城市id 3：城市名 4:区域id 5：区域名
            List<String> subitemIds = measureRepository.getMeasureSubitemModel();//分项id  按导出顺序查询
            List<Object[]> countIds = measureRepository.getMeasureCountModel();//分项id  按导出顺序查询
            List<String> order = new ArrayList<>();
            if (null != subitemIds && subitemIds.size() > 0) {
                for (String subitemId : subitemIds) {
                    order.add(subitemId);
                    if (null != countIds && countIds.size() > 0) {
                        for (Object[] obj : countIds) {
                            if(null != subitemId && subitemId.equals(obj[0] == null ? "" : obj[0].toString())){
                                order.add(obj[1] == null ? "" : obj[1].toString());
                            }
                        }
                    }
                }
            }
            if (null != measureCountEntities && measureCountEntities.size() > 0) {
                int flag = 1;
                XSSFRow row = null;
                for (MeasureCountEntity measureCountEntity : measureCountEntities) {
                    List<String> list = new ArrayList<>();
                    if (null != objects && objects.size() > 0) {
                        for (Object[] obj : objects) {
                            if (measureCountEntity.getLevel().equals("1")) {
                                list.add(measureCountEntity.getAgencyName());
                                list.add("");
                                list.add("");
                                break;
                            } else if (measureCountEntity.getLevel().equals("2")) {
                                if ((obj[2] == null ? "" : obj[2].toString()).equals(measureCountEntity.getAgencyId())) {
                                    list.add(obj[5] == null ? "" : obj[5].toString());
                                    list.add(measureCountEntity.getAgencyName());
                                    list.add("");
                                    break;
                                }
                            } else if (measureCountEntity.getLevel().equals("3")) {
                                if ((obj[0] == null ? "" : obj[0].toString()).equals(measureCountEntity.getAgencyId())) {
                                    list.add(obj[5] == null ? "" : obj[5].toString());//区域
                                    list.add(obj[3] == null ? "" : obj[3].toString());//城市
                                    list.add(measureCountEntity.getAgencyName());//项目
                                    break;
                                }
                            }
                        }
                    }
                    list.add(measureCountEntity.getTotalScore());//总分
                    if (null != order && order.size() > 0) {
                        for(String id : order){
                            String value = "";
                            if (!StringUtil.isEmpty(measureCountEntity.getInspectionSubitemScore())) {//检查分项
                                if (!StringUtil.isEmpty(measureCountEntity.getInspectionContentScore())) {//检查内容
                                    String js = measureCountEntity.getInspectionSubitemScore().replace("]",",") + measureCountEntity.getInspectionContentScore().substring(1);
                                    JSONArray json = JSONArray.fromObject(js);
                                    for (Object obj : json) {
                                        JSONObject jo = (JSONObject) obj;
                                        if (jo.getString("inspectId").equals(id)) {
                                            value = jo.getString("score");
                                            break;
                                        }
                                    }
                                }
                            }
                            list.add(value);
                        }
                    }
                    //得到Excel工作表的行
                    row = sheet.createRow(flag);
                    XSSFCell cell = null;
                    for (int i = 0; i < list.size(); i++) {
                        //得到Excel工作表的列
                        cell = row.createCell(i);
                        cell.setCellStyle(bodyStyle);
                        cell.setCellValue(new XSSFRichTextString(list.get(i)));
                    }
                    flag++;
                }
            }
            this.export(response, "全数据.xlsx", wb);
        }
    }

    @Override
    public boolean getDataByagencyId(String agencyId) {
        boolean flag = false;
        if (!StringUtil.isEmpty(agencyId)) {
            String str[] = agencyId.split(",");
            List<String> strList = Arrays.asList(str);
            List<MeasureCountEntity> measureCountEntities = measureRepository.getMeasureCount(strList);
            if(null != measureCountEntities && measureCountEntities.size()>0){
                flag = true;
            }
        }
        return flag;
    }

    public List<MeasureDTO> getMeasureDtoByMeasureEntity(List<MeasureEntity> measureEntities) {
        List<MeasureDTO> measureDTOS = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (measureEntities != null && measureEntities.size() > 0) {
            for (MeasureEntity measureEntity : measureEntities) {
                MeasureDTO measureDTO = new MeasureDTO();
                measureDTO.setRegionId(measureEntity.getRegionId());
                measureDTO.setCityId(measureEntity.getCityId());
                measureDTO.setProjectId(measureEntity.getProjectId());
                measureDTO.setProjectNum(measureEntity.getProjectNum());
                measureDTO.setProjectName(measureEntity.getProjectName());
                measureDTO.setProjectTotalScore(String.valueOf(measureEntity.getProjectToTalScore()));
                measureDTO.setBuildingId(measureEntity.getBuildingId());
                measureDTO.setBuildingName(measureEntity.getBuildingName());
                measureDTO.setBuildingTotalScode(String.valueOf(measureEntity.getBuildingToTalScore()));
                measureDTO.setFloorId(measureEntity.getFloorId());
                measureDTO.setFloorName(measureEntity.getFloorName());
                measureDTO.setMensurePercentOfPass(String.valueOf(measureEntity.getMensurePercentOfPass()) == null ? "" : String.valueOf((int) (measureEntity.getMensurePercentOfPass() * 100)));
                measureDTO.setModifyDate(measureEntity.getModifyDate() == null ? "" : simpleDateFormat.format(measureEntity.getModifyDate()));
                measureDTOS.add(measureDTO);
            }
        }
        return measureDTOS;
    }

    //type区分是按区域还是城市或者项目
    public List<MeasureDTO> getMeasureDtoByMeasureDetail(List<Object[]> list, String type) {
        List<MeasureDTO> measureDTOS = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Object[] objects : list) {
                MeasureDTO measureDTO = new MeasureDTO();
                //区域
                if ("1".equals(type)) {
                    measureDTO.setRegionId(objects[2] == null ? "" : objects[2].toString());
                    measureDTO.setRegionName(objects[3] == null ? "" : objects[3].toString());
                } else if ("2".equals(type)) {
                    measureDTO.setCityId(objects[2] == null ? "" : objects[2].toString());
                    measureDTO.setCityName(objects[3] == null ? "" : objects[3].toString());
                } else if ("3".equals(type)) {
                    measureDTO.setProjectId(objects[2] == null ? "" : objects[2].toString());
                    measureDTO.setProjectName(objects[3] == null ? "" : objects[3].toString());
                }
                NumberFormat numberFormat = NumberFormat.getNumberInstance();
                numberFormat.setMaximumFractionDigits(1);
                double acceptanceNum = objects[0] == null ? 0 : Double.parseDouble(objects[0].toString());
                double totalNum = objects[1] == null ? 0 : Double.parseDouble(objects[1].toString());
                String mensurePercentOfPass = null;
                if (totalNum != 0) {
                    mensurePercentOfPass = numberFormat.format(acceptanceNum / totalNum * 100);
                    if(mensurePercentOfPass.indexOf(".") == -1){
                        mensurePercentOfPass += ".0";
                    }
                } else {
                    mensurePercentOfPass = "0.0";
                }
                measureDTO.setMensurePercentOfPass(mensurePercentOfPass);
                measureDTOS.add(measureDTO);
            }
        }
        return measureDTOS;
    }

    public String getMeasureDataDto(Object[] objects) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(1);
        double acceptanceNum = objects[0] == null ? 0 : Double.parseDouble(objects[0].toString());
        double totalNum = objects[1] == null ? 0 : Double.parseDouble(objects[1].toString());
        String mensurePercentOfPass = null;
        if (totalNum != 0) {
            mensurePercentOfPass = numberFormat.format(acceptanceNum / totalNum * 100);
            if(mensurePercentOfPass.indexOf(".") == -1){
                mensurePercentOfPass += ".0";
            }
        } else {
            mensurePercentOfPass = "0.0";
        }
        return mensurePercentOfPass;
    }

    //没有请选择和全部
    public List<AuthAgencyESEntity> getAuthAgencyList(UserInformationEntity userInformationEntity) {
        List<AuthAgencyESEntity> list = new ArrayList<>();
        Set<AuthAgencyESEntity> authAgencyESEntities = new LinkedHashSet<>();
        //查看是否给当前登录人授权过总部权限
        if (authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000", userInformationEntity.getStaffId())) {
            //查看总部下的所有内容
            List<AuthAgencyESEntity> agencyEsEntities = authAgencyRepository.getESAllAgencyList();
            authAgencyESEntities.addAll(agencyEsEntities);
        } else if (authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001", userInformationEntity.getStaffId())) {
            //如果是区域级别，找出拥有区域权限的权限id
            List<String> parentIdList = authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000001", userInformationEntity.getStaffId());
            if (parentIdList != null && !parentIdList.isEmpty()) {
                //区域 城市下项目
                List<AuthAgencyESEntity> parEntities = authAgencyRepository.getESAllAgencyListByParentId(parentIdList, null, "100000003");
                if (parEntities != null && !parEntities.isEmpty()) {
                    List<String> cityIarentId = new ArrayList<>();
                    for (AuthAgencyESEntity pro : parEntities) {
                        cityIarentId.add(pro.getAgencyId());
                    }
                    if (cityIarentId != null && !cityIarentId.isEmpty()) {
                        List<String> cityAndParIds = new ArrayList();
                        cityAndParIds.addAll(cityIarentId);
                        cityAndParIds.addAll(parentIdList);
                        List<AuthAgencyESEntity> cityAgencyEntities = authAgencyRepository.getESAllAgencyListByParentId(cityIarentId, cityAndParIds, "100000002");
                        authAgencyESEntities.addAll(cityAgencyEntities);
                    }
                }
            }
        } else if (authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003", userInformationEntity.getStaffId())) {
            List<String> parentIdList = authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000003", userInformationEntity.getStaffId());
            //根据条件删选
            if (parentIdList != null && !parentIdList.isEmpty()) {
                List<AuthAgencyESEntity> agencyEsEntities = authAgencyRepository.getESAllAgencyListByParentId(parentIdList, parentIdList, "100000002");
                authAgencyESEntities.addAll(agencyEsEntities);
            }
        } else if (authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002", userInformationEntity.getStaffId())) {
            List<String> agencyIdList = authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000002", userInformationEntity.getStaffId());
            //根据条件删选
            if (agencyIdList != null && !agencyIdList.isEmpty()) {
                List<AuthAgencyESEntity> agencyEsEntities = authAgencyRepository.getESAllAgencyListByParentId(null, agencyIdList, "100000002");
                authAgencyESEntities.addAll(agencyEsEntities);
            }
        }
        Iterator<AuthAgencyESEntity> iterator = authAgencyESEntities.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public void saveMeasureCount(List<Object[]> projectId, List<Object[]> projectList, List<Object[]> projectContentList, String level) {
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后1位
        numberFormat.setMaximumFractionDigits(1);
        if (null != projectId && projectId.size() > 0) {
            for (Object[] obj : projectId) { //obj[0]项目id obj[1] 项目名称
                MeasureCountEntity measureCountEntity = new MeasureCountEntity();
                measureCountEntity.setAgencyId(obj[0] == null ? "" : obj[0].toString());//项目id
                measureCountEntity.setAgencyName(obj[1] == null ? "" : obj[1].toString());//项目id
                measureCountEntity.setLevel(level);//级别
                List<ScoreDTO> list = new ArrayList<>();
                int qualified = 0; //项目下的合格数
                int total = 0; //项目下的总数
                if (null != projectList && projectList.size() > 0) {
                    for (Object[] obj1 : projectList) {//obj1[0]项目id obj1[1] 检查分项Id obj1[2] 合格数 obj1[3] 总数
                        if (null != measureCountEntity.getAgencyId() && measureCountEntity.getAgencyId().equals(obj1[0] == null ? "" : obj1[0].toString())) {
                            String resultQualified0 = "0.0";
                            if (null != obj1[3] && Integer.valueOf(obj1[3].toString()) > 0) {
                                total += Integer.valueOf(obj1[3].toString());
                                if (null != obj1[2]) {
                                    qualified += Integer.valueOf(obj1[2].toString());
                                    resultQualified0 = numberFormat.format(Double.parseDouble(obj1[2].toString()) / Double.parseDouble(obj1[3].toString()) * 100);
                                    if (resultQualified0.indexOf(".") == -1) {
                                        resultQualified0 = resultQualified0 + ".0";
                                    }
                                }
                            }
                            ScoreDTO scoreDTO = new ScoreDTO();
                            scoreDTO.setInspectId(obj1[1] == null ? "" : obj1[1].toString());
                            scoreDTO.setScore(resultQualified0 + "%");
                            list.add(scoreDTO);
                        }
                    }
                }
                String resultQualified = "0.0";
                if (total > 0) {
                    resultQualified = numberFormat.format((double) qualified / (double) total * 100);
                    if (resultQualified.indexOf(".") == -1) {
                        resultQualified = resultQualified + ".0";
                    }
                }
                measureCountEntity.setInspectionSubitemScore(JsonUtil.toJson(list).toString());//检查分项
                measureCountEntity.setTotalScore(resultQualified + "%");
                list.clear();
                if (null != projectContentList && projectContentList.size() > 0) {
                    for (Object[] obj2 : projectContentList) {//obj[0]项目id obj[1] 检查内容Id obj[2] 合格数 obj[3] 总数
                        if (null != measureCountEntity.getAgencyId() && measureCountEntity.getAgencyId().equals(obj2[0] == null ? "" : obj2[0].toString())) {
                            String resultQualified1 = "0.0";
                            if (null != obj2[3] && Integer.valueOf(obj2[3].toString()) > 0) {
                                if (null != obj2[2]) {
                                    resultQualified1 = numberFormat.format(Double.parseDouble(obj2[2].toString()) / Double.parseDouble(obj2[3].toString()) * 100);
                                    if (resultQualified1.indexOf(".") == -1) {
                                        resultQualified1 = resultQualified1 + ".0";
                                    }
                                }
                            }
                            ScoreDTO scoreDTO = new ScoreDTO();
                            scoreDTO.setInspectId(obj2[1] == null ? "" : obj2[1].toString());
                            scoreDTO.setScore(resultQualified1 + "%");
                            list.add(scoreDTO);
                        }
                    }
                }
                measureCountEntity.setInspectionContentScore(JsonUtil.toJson(list).toString());//检查内容
                measureRepository.saveMeasureCount(measureCountEntity);
            }
        }
    }

    //同一模板数据导出
    public void exportForTemplate(List<Object[]> list, List<Object[]> score, List<String> subitemIds, String filePath, HttpServletResponse response, String fileName) throws Exception {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后1位
        numberFormat.setMaximumFractionDigits(1);
        //判断导出数据的项目级别
        File file = new File(filePath);
        //得到Excel常用对象
        InputStream input = new FileInputStream(file);
        //得到Excel工作簿对象
        XSSFWorkbook wb = new XSSFWorkbook(input);
        input.close();
        //设置单元格样式
        XSSFCellStyle bodyStyle = wb.createCellStyle();
        //设置样式
        bodyStyle.setWrapText(true);
        bodyStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        bodyStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        //获取工作表
        XSSFSheet sheet = wb.getSheetAt(0);
        if (null != list && list.size() > 0) {
            int flag = 1;
            XSSFRow row = null;
            for (Object[] obj : list) {
                List<String> list1 = new ArrayList<>();
                list1.add(obj[1] == null ? "" : obj[1].toString());//楼层名称
                String resultQualified = "0.0";
                double acceptanceNum = obj[2] == null ? 0 : Double.parseDouble(obj[2].toString());
                double totalNum = obj[3] == null ? 0 : Double.parseDouble(obj[3].toString());
                if (totalNum > 0) {
                    resultQualified = numberFormat.format(acceptanceNum / totalNum * 100);
                    if (resultQualified.indexOf(".") == -1) {
                        resultQualified = resultQualified + ".0";
                    }
                }
                list1.add(resultQualified + "%"); //楼层总分
                if (null != score && score.size() > 0) {
                    if (null != subitemIds && subitemIds.size() > 0) {
                        for (String subitemId : subitemIds) {
                            String str = "";
                            for (Object[] obj1 : score) {
                                if (obj[0].equals(obj1[0])) { //相同楼层
                                    if (subitemId.equals(obj1[1] == null ? "" : obj1[1].toString())) {
                                        double subitemAcceptanceNum = obj1[2] == null ? 0 : Double.parseDouble(obj1[2].toString());
                                        double subitemTotalNum = obj1[3] == null ? 0 : Double.parseDouble(obj1[3].toString());
                                        if (subitemTotalNum > 0) {
                                            str = "0.0";
                                            str = numberFormat.format(subitemAcceptanceNum / subitemTotalNum * 100);
                                            if (str.indexOf(".") == -1) {
                                                str += ".0";
                                            }
                                            str += "%";
                                        }
                                    }
                                }
                            }
                            list1.add(str);
                        }
                    }
                }
                //得到Excel工作表的行
                row = sheet.createRow(flag);
                XSSFCell cell = null;
                for (int i = 0; i < list1.size(); i++) {
                    //得到Excel工作表的列
                    cell = row.createCell(i);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(new XSSFRichTextString(list1.get(i)));
                }
                flag++;
            }
        }
        this.export(response, fileName, wb);
    }

    //导出
    public void export(HttpServletResponse response, String fileName, XSSFWorkbook wb) {
        OutputStream outputStream = null;
        try {
            response.reset(); //非常重要
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int a = 1;
        if (a == 1) {
            System.out.println(1);
        } else if (a == 1) {
            System.out.println(2);
        }
    }
}
