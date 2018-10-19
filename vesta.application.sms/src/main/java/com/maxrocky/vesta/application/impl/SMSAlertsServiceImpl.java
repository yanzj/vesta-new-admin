package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.SMSAlertsDTO;
import com.maxrocky.vesta.application.DTO.SMSAlertsSearchDTO;
import com.maxrocky.vesta.application.DTO.SMSEditDTO;
import com.maxrocky.vesta.application.DTO.SMSPeopleAlertsDTO;
import com.maxrocky.vesta.application.inf.SMSAlertsService;
import com.maxrocky.vesta.domain.model.SMSAlertsEntity;
import com.maxrocky.vesta.domain.model.SMSPeopleAlertsEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.SMSAlertsRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.StringUtil;
import com.mysql.jdbc.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by 27978 on 2016/9/1.
 */
@Service
public class SMSAlertsServiceImpl implements SMSAlertsService {

    @Autowired
    SMSAlertsRepository smsAlertsRepository;

    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 编辑短信，保存短信
    */
    @Override
    public boolean addSMSAlerts(UserPropertyStaffEntity userPropertystaffEntity, SMSEditDTO smsEditDTO) {
        SMSAlertsEntity smsAlertsEntity = new SMSAlertsEntity();
        smsAlertsEntity.setActivityModelNum(smsEditDTO.getActivityModelNum());
        smsAlertsEntity.setActivityContent(smsEditDTO.getActivityContent());
        smsAlertsEntity.setAppealModelNum(smsEditDTO.getAppealModelNum());
        smsAlertsEntity.setAppealContent(smsEditDTO.getAppealContent());
        smsAlertsEntity.setRepairModelNum(smsEditDTO.getRepairModelNum());
        smsAlertsEntity.setRepairContent(smsEditDTO.getRepairContent());
        smsAlertsEntity.setPaymentModelNum(smsEditDTO.getPaymentModelNum());
        smsAlertsEntity.setPaymentContent(smsEditDTO.getPaymentContent());
        smsAlertsEntity.setOperator(smsEditDTO.getOperator());
        if ("0".equals(smsEditDTO.getCityNum())){
            //全部城市，只能查询出全部项目
            List list = smsAlertsRepository.getAllCity();
            for (int i=0; i<list.size(); i++) {
                String cityId = (String)list.get(i);
                smsAlertsEntity.setCityNum(cityId);
                List<Object[]> projectList = smsAlertsRepository.getAllProjectByCityId(cityId);
                for (Object[] obj : projectList) {
                    smsAlertsEntity.setId(UUID.randomUUID().toString());
                    String projectNum = obj[0] == null ? "" : obj[0].toString();
                    smsAlertsEntity.setProjectNum(projectNum);
                    //赋予短信顺序，获取顺序最大值
                    Integer sequence = smsAlertsRepository.getSequence();
                    if (sequence == null || "".equals(sequence)){
                        sequence = 1;
                    }else {
                        sequence += 1;
                    }
                    smsAlertsEntity.setSequence(sequence);
                    smsAlertsEntity.setMakedate(new Date());
                    try {
                        smsAlertsRepository.addSMSAlerts(smsAlertsEntity);
                    }catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }else {
            smsAlertsEntity.setCityNum(smsEditDTO.getCityNum());
            if ("0".equals(smsEditDTO.getProjectNum())) {
                //某个城市下全部项目
                List<Object[]> list = smsAlertsRepository.getAllProjectByCityId(smsEditDTO.getCityNum());
                for (Object[] obj : list) {
                    smsAlertsEntity.setId(UUID.randomUUID().toString());
                    String projectNum = obj[0] == null ? "" : obj[0].toString();
                    smsAlertsEntity.setProjectNum(projectNum);
                    //赋予短信顺序，获取顺序最大值
                    Integer sequence = smsAlertsRepository.getSequence();
                    if (sequence == null || "".equals(sequence)){
                        sequence = 1;
                    }else {
                        sequence += 1;
                    }
                    smsAlertsEntity.setSequence(sequence);
                    smsAlertsEntity.setMakedate(new Date());
                    try {
                        smsAlertsRepository.addSMSAlerts(smsAlertsEntity);
                    }catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }else {
                //某个城市下某个项目
                smsAlertsEntity.setId(UUID.randomUUID().toString());
                smsAlertsEntity.setProjectNum(smsEditDTO.getProjectNum());
                //赋予短信顺序，获取顺序最大值
                Integer sequence = smsAlertsRepository.getSequence();
                if (sequence == null || "".equals(sequence)){
                    sequence = 1;
                }else {
                    sequence += 1;
                }
                smsAlertsEntity.setSequence(sequence);
                smsAlertsEntity.setMakedate(new Date());
                try {
                    smsAlertsRepository.addSMSAlerts(smsAlertsEntity);
                }catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取所有短信提醒人员
    */
    @Override
    public List<SMSAlertsDTO> getAllSMSPeople(SMSAlertsSearchDTO smsAlertsSearchDTO, WebPage webPage) {
        SMSPeopleAlertsEntity smsPeopleAlertsEntity = new SMSPeopleAlertsEntity();
        if ("0".equals(smsAlertsSearchDTO.getCityNum())) {
            smsPeopleAlertsEntity.setCity(null);
        }else {
            smsPeopleAlertsEntity.setCity(smsAlertsSearchDTO.getCityNum());
        }
        if ("0".equals(smsAlertsSearchDTO.getProjectNum())) {
            smsPeopleAlertsEntity.setScope(null);
        }else {
            String projectName = smsAlertsRepository.getProjectName(smsAlertsSearchDTO.getProjectNum());
            smsPeopleAlertsEntity.setScope(projectName);
        }
        smsPeopleAlertsEntity.setModel(smsAlertsSearchDTO.getModel());
        smsPeopleAlertsEntity.setName(smsAlertsSearchDTO.getName());
        smsPeopleAlertsEntity.setPhone(smsAlertsSearchDTO.getPhone());
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = smsAlertsSearchDTO.getRoleScopeList();
        for (Map<String,Object> roleScope : roleScopeList){
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")){
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList){
                    if (!roleScopes.contains(project[0].toString())){
                        roleScopes += "'"+project[0].toString()+"',";
                    }
                }
            }else if (scopeType.equals("2")){
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())){
                    roleScopes += "'"+roleScope.get("scopeId")+"',";
                }
            }else if (scopeType.equals("0")){
                //全部城市
                roleScopes += "all,";
            }
        }
        List<SMSPeopleAlertsEntity> list = smsAlertsRepository.getAllSMSAlertsPeople(smsPeopleAlertsEntity, webPage, roleScopes);

        List<SMSAlertsDTO> smsAlertsDTOList = new ArrayList<>();
        if (!list.isEmpty()) {
            for (SMSPeopleAlertsEntity smsPeopleAlerts : list) {
                SMSAlertsDTO smsAlerts = new SMSAlertsDTO();
                smsAlerts.setId(smsPeopleAlerts.getId());
                smsAlerts.setModel(smsPeopleAlerts.getModel());
                smsAlerts.setContent(smsPeopleAlerts.getContent());
                smsAlerts.setName(smsPeopleAlerts.getName());
                smsAlerts.setPhone(smsPeopleAlerts.getPhone());
                smsAlerts.setScope(smsPeopleAlerts.getScope());
                smsAlertsDTOList.add(smsAlerts);
            }
        }
        return smsAlertsDTOList;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 单个人员编辑
    */
    @Override
    public void addSMSPeople(UserPropertyStaffEntity userPropertystaffEntity, SMSPeopleAlertsDTO smsPeopleAlertsDTO) {
        SMSPeopleAlertsEntity smsPeopleAlertsEntity = new SMSPeopleAlertsEntity();
        if (smsPeopleAlertsDTO.getCityIds()!=null && !"".equals(smsPeopleAlertsDTO.getCityIds())) {
            String[] cityIds = smsPeopleAlertsDTO.getCityIds().split(",");
            for (String cityId : cityIds) {
                if ("0".equals(cityId)) {
                    //全部城市下只能选择全部项目
                    List list = smsAlertsRepository.getAllCity();
                    for (int i=0; i<list.size(); i++) {
                        String city = (String)list.get(i);
                        smsPeopleAlertsEntity.setCity(city);
                        //全部项目
                        List<Object[]> list1 = smsAlertsRepository.getAllProjectByCityId(city);
                        for (Object[] obj : list1) {
                            String projectNum = (obj[0] == null ? "" : obj[0].toString());
                            String projectName = (obj[1] == null ? "" : obj[1].toString());
                            smsPeopleAlertsEntity.setScope(projectName);
                            smsPeopleAlertsEntity.setProjectNum(projectNum);
                            this.save(smsPeopleAlertsEntity, smsPeopleAlertsDTO, projectNum);
                        }
                    }
                }else {
                    //某城市下
                    //某城市下全部项目
                    smsPeopleAlertsEntity.setCity(cityId);
                    if (smsPeopleAlertsDTO.getProjectIds() != null && !"".equals(smsPeopleAlertsDTO.getProjectIds())) {
                        String[] projectIds = smsPeopleAlertsDTO.getProjectIds().split(",");
                        for (String projectId : projectIds) {
                            if ("0".equals(projectId)) {
                                //全部项目
                                List<Object[]> list1 = smsAlertsRepository.getAllProjectByCityId(cityId);
                                for (Object[] obj : list1) {
                                    String projectNum = (obj[0] == null ? "" : obj[0].toString());
                                    String projectName = (obj[1] == null ? "" : obj[1].toString());
                                    smsPeopleAlertsEntity.setScope(projectName);
                                    smsPeopleAlertsEntity.setProjectNum(projectNum);
                                    //smsPeopleAlertsEntity.setCity(cityId);
                                    this.save(smsPeopleAlertsEntity, smsPeopleAlertsDTO, projectNum);
                                }
                            }else {
                                //某城市下的某些项目
                                //smsPeopleAlertsEntity.setCity(cityId);
                                smsPeopleAlertsEntity.setProjectNum(projectId);
                                String projectName = smsAlertsRepository.getProjectName(projectId);
                                smsPeopleAlertsEntity.setScope(projectName);
                                this.save(smsPeopleAlertsEntity, smsPeopleAlertsDTO, projectId);
                            }
                        }
                    }
                }
            }
        }
        /*//获取的是项目的id，并不是名字
        //将对应的短信文本赋予提醒模块
        if (!StringUtils.isNullOrEmpty(smsPeopleAlertsDTO.getProjectIds())) {
            String[] projectIds = smsPeopleAlertsDTO.getProjectIds().split(",");
            for (String projectId : projectIds) {
                String projectName = smsAlertsRepository.getProjectName(projectId);
                smsPeopleAlertsEntity.setScope(projectName);
                this.save(smsPeopleAlertsEntity, smsPeopleAlertsDTO, projectId);
            }

        }*/
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 各项目下的存储
    */
    public boolean save(SMSPeopleAlertsEntity smsPeopleAlertsEntity, SMSPeopleAlertsDTO smsPeopleAlertsDTO, String projectNum) {
        //获取该项目下的SMSAlertsEntity
        Object[] object = smsAlertsRepository.getSMSAlerts(projectNum, smsPeopleAlertsEntity.getCity());
        String repairContent = "";
        String appealContent = "";
        String activityContent = "";
        if (object != null) {
            repairContent = (object[0] == null ? "" : object[0].toString());
            appealContent = (object[1] == null ? "" : object[1].toString());
            activityContent = (object[2] == null ? "" : object[2].toString());
        }
        if (!StringUtils.isNullOrEmpty(smsPeopleAlertsDTO.getModelList())) {
            String[] models = smsPeopleAlertsDTO.getModelList().split(",");
            //String smsContent = "";
            for (String model : models) {
                if ("房屋报修管理".equals(model)) {
                    //005700030000
                    if (repairContent != null && !"".equals(repairContent)){
                        smsPeopleAlertsEntity.setModel(model);
                        smsPeopleAlertsEntity.setContent(repairContent);
                        this.saveEntity(smsPeopleAlertsEntity, smsPeopleAlertsDTO);
                    }
                }
                if ("身份申诉管理".equals(model)) {
                    //000900030000
                    //于20170313菜单变更->005200010000
                    if (appealContent != null && !"".equals(appealContent)){
                        smsPeopleAlertsEntity.setModel(model);
                        smsPeopleAlertsEntity.setContent(appealContent);
                        this.saveEntity(smsPeopleAlertsEntity, smsPeopleAlertsDTO);
                    }
                }
                if ("活动报名管理".equals(model)) {
                    //005400030000
                    if (activityContent != null && !"".equals(activityContent)){
                        smsPeopleAlertsEntity.setModel(model);
                        smsPeopleAlertsEntity.setContent(activityContent);
                        this.saveEntity(smsPeopleAlertsEntity, smsPeopleAlertsDTO);
                    }
                }
                if ("物业缴费管理".equals(model)) {
                    //005700010000
                    if (activityContent != null && !"".equals(activityContent)){
                        smsPeopleAlertsEntity.setModel(model);
                        smsPeopleAlertsEntity.setContent("物业缴费管理");
                        this.saveEntity(smsPeopleAlertsEntity, smsPeopleAlertsDTO);
                    }
                }
                if ("商品订单管理".equals(model)) {
                    //006700030000
                    if (activityContent != null && !"".equals(activityContent)){
                        smsPeopleAlertsEntity.setModel(model);
                        smsPeopleAlertsEntity.setContent("商品订单管理");
                        this.saveEntity(smsPeopleAlertsEntity, smsPeopleAlertsDTO);
                    }
                }
            }
        }
        return true;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 各提醒模块下的对应存储
    */
    public boolean saveEntity (SMSPeopleAlertsEntity smsPeopleAlertsEntity, SMSPeopleAlertsDTO smsPeopleAlertsDTO) {
        smsPeopleAlertsEntity.setId(UUID.randomUUID().toString());
        smsPeopleAlertsEntity.setName(smsPeopleAlertsDTO.getName());
        smsPeopleAlertsEntity.setPhone(smsPeopleAlertsDTO.getPhone());
        //smsPeopleAlertsEntity.setCity(smsPeopleAlertsDTO.getCityIds());
        smsPeopleAlertsEntity.setOperator(smsPeopleAlertsDTO.getOperator());
        smsPeopleAlertsEntity.setMakeDate(DateUtils.format(new Date()));

        //判断是否有相同name、phone、scope、model的人员存在，存在则删除
        smsAlertsRepository.deleteCommonPeople(smsPeopleAlertsEntity);

        //保存
        smsAlertsRepository.addSMSPeople(smsPeopleAlertsEntity);
        return true;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 修改单个人员
    */
    @Override
    public void updateSMSPeople(SMSAlertsDTO smsAlertsDTO) {
        SMSPeopleAlertsEntity smsPeopleAlertsEntity = smsAlertsRepository.getSMSPeopleById(smsAlertsDTO.getId());
        smsPeopleAlertsEntity.setName(smsAlertsDTO.getName());
        smsPeopleAlertsEntity.setPhone(smsAlertsDTO.getPhone());
        smsPeopleAlertsEntity.setMakeDate(DateUtils.format(new Date()));

        if (!StringUtils.isNullOrEmpty(smsAlertsDTO.getCityIds())) {
            String[] cityIds = smsAlertsDTO.getCityIds().split(",");
            for (String cityId : cityIds) {
                if ("0".equals(cityId)) {
                    //全部城市下只能选择全部项目
                    List list = smsAlertsRepository.getAllCity();
                    for (int i=0; i<list.size(); i++) {
                        String city = (String)list.get(i);
                        smsPeopleAlertsEntity.setCity(city);
                        //全部项目
                        List<Object[]> list1 = smsAlertsRepository.getAllProjectByCityId(city);
                        for (Object[] obj : list1) {
                            String projectNum = (obj[0] == null ? "" : obj[0].toString());
                            String projectName = (obj[1] == null ? "" : obj[1].toString());
                            smsPeopleAlertsEntity.setScope(projectName);
                            smsPeopleAlertsEntity.setProjectNum(projectNum);
                            if (!StringUtils.isNullOrEmpty(smsAlertsDTO.getModelList())) {
                                String[] models = smsAlertsDTO.getModelList().split(",");
                                for (String model : models) {
                                    smsPeopleAlertsEntity.setModel(model);
                                    //smsPeopleAlertsEntity.setId(UUID.randomUUID().toString());
                                    smsAlertsRepository.updatePeople(smsPeopleAlertsEntity);
                                }
                            }
                        }
                    }
                }else {
                    //某城市下
                    //某城市下全部项目
                    smsPeopleAlertsEntity.setCity(cityId);
                    if (!StringUtils.isNullOrEmpty(smsAlertsDTO.getProjectIds())) {
                        String[] projectIds = smsAlertsDTO.getProjectIds().split(",");
                        for (String projectId : projectIds) {
                            if ("0".equals(projectId)) {
                                //全部项目
                                List<Object[]> list1 = smsAlertsRepository.getAllProjectByCityId(cityId);
                                for (Object[] obj : list1) {
                                    String projectNum = (obj[0] == null ? "" : obj[0].toString());
                                    String projectName = (obj[1] == null ? "" : obj[1].toString());
                                    smsPeopleAlertsEntity.setScope(projectName);
                                    smsPeopleAlertsEntity.setProjectNum(projectNum);
                                    if (!StringUtils.isNullOrEmpty(smsAlertsDTO.getModelList())) {
                                        String[] models = smsAlertsDTO.getModelList().split(",");
                                        for (String model : models) {
                                            smsPeopleAlertsEntity.setModel(model);
                                            //smsPeopleAlertsEntity.setId(UUID.randomUUID().toString());
                                            smsAlertsRepository.updatePeople(smsPeopleAlertsEntity);
                                        }
                                    }
                                }
                            }else {
                                //某城市下的某些项目
                                //smsPeopleAlertsEntity.setCity(cityId);
                                //判断该项目是否在该城市下
                                if (smsAlertsRepository.isCity(projectId, cityId)) {
                                    smsPeopleAlertsEntity.setProjectNum(projectId);
                                    String projectName = smsAlertsRepository.getProjectName(projectId);
                                    smsPeopleAlertsEntity.setScope(projectName);
                                    if (!StringUtils.isNullOrEmpty(smsAlertsDTO.getModelList())) {
                                        String[] models = smsAlertsDTO.getModelList().split(",");
                                        for (String model : models) {
                                            smsPeopleAlertsEntity.setModel(model);
                                            //smsPeopleAlertsEntity.setId(UUID.randomUUID().toString());
                                            smsAlertsRepository.updatePeople(smsPeopleAlertsEntity);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 预修改
    */
    @Override
    public SMSPeopleAlertsEntity getPeopleById(String id) {
        SMSPeopleAlertsEntity smsPeopleAlertsEntity = smsAlertsRepository.getSMSPeopleById(id);
        return smsPeopleAlertsEntity;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 删除人员
    */
    @Override
    public void deleteSMSPeople(String id) {
        SMSPeopleAlertsEntity smsPeopleAlertsEntity = smsAlertsRepository.getSMSPeopleById(id);
        smsAlertsRepository.deletePeople(smsPeopleAlertsEntity);
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取需要发送短信的人员
    */
    @Override
    public List<SMSPeopleAlertsEntity> getAllByModel(String projectName, String model) {
        return smsAlertsRepository.getPeople(projectName, model);
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取最新的短信
    */
    @Override
    public SMSAlertsDTO getSMSAlerts(String cityNum, String projectNum) {
        SMSAlertsEntity smsAlertsEntity = smsAlertsRepository.getSMSAlertsNew(cityNum, projectNum);
        SMSAlertsDTO smsAlertsDTO = new SMSAlertsDTO();
        if (null != smsAlertsEntity){
            smsAlertsDTO.setCityNum(smsAlertsEntity.getCityNum());
            smsAlertsDTO.setProjectNum(smsAlertsEntity.getProjectNum());
            if (!StringUtils.isNullOrEmpty(smsAlertsEntity.getProjectNum())) {
                String projectName = smsAlertsRepository.getProjectName(smsAlertsEntity.getProjectNum());
                smsAlertsDTO.setScope(projectName);
            }
            if (!StringUtils.isNullOrEmpty(smsAlertsEntity.getActivityModelNum())) {
                smsAlertsDTO.setActivityModelNum(smsAlertsEntity.getActivityModelNum());
            }
            if (!StringUtils.isNullOrEmpty(smsAlertsEntity.getActivityContent())) {
                smsAlertsDTO.setActivityContent(smsAlertsEntity.getActivityContent());
            }
            if (!StringUtils.isNullOrEmpty(smsAlertsEntity.getAppealModelNum())) {
                smsAlertsDTO.setAppealModelNum(smsAlertsEntity.getAppealModelNum());
            }
            if (!StringUtils.isNullOrEmpty(smsAlertsEntity.getAppealContent())) {
                smsAlertsDTO.setAppealContent(smsAlertsEntity.getAppealContent());
            }
            if (!StringUtils.isNullOrEmpty(smsAlertsEntity.getRepairModelNum())) {
                smsAlertsDTO.setRepairModelNum(smsAlertsEntity.getRepairModelNum());
            }
            if (!StringUtils.isNullOrEmpty(smsAlertsEntity.getRepairContent())) {
                smsAlertsDTO.setRepairContent(smsAlertsEntity.getRepairContent());
            }
            if (!StringUtils.isNullOrEmpty(smsAlertsEntity.getPaymentModelNum())){
                smsAlertsDTO.setPaymentModelNum(smsAlertsEntity.getPaymentModelNum());
            }
            if (!StringUtils.isNullOrEmpty(smsAlertsEntity.getPaymentContent())){
                smsAlertsDTO.setPaymentContent(smsAlertsEntity.getPaymentContent());
            }
        }
        return smsAlertsDTO;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取数据范围权限
    */
    @Override
    public String getRoleScopes(List<Map<String, Object>> roleScopeList) {

        return null;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 下载模板
    */
    @Override
    public String downLoadTemplate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException{
        OutputStream outputStream = httpServletResponse.getOutputStream();
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet

        //创建Sheet页
        XSSFSheet sheet = workBook.createSheet("短信消息提醒人员表");

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

        String[] titles = {"姓名", "手机号", "数据查看范围","提醒模块"};

        XSSFRow headRow = sheet.createRow(0);

        XSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = headRow.createCell(i);
            headRow.createCell(i).setCellValue(titles.length);

            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }
        sheet.setColumnWidth(0, 1600);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 10000);
        sheet.setColumnWidth(3, 4000);

        XSSFRow bodyRow = sheet.createRow(1);

        cell = bodyRow.createCell(0);
        cell.setCellStyle(bodyStyle);// 表格黑色边框
        cell.setCellValue("测试2");//姓名

        cell = bodyRow.createCell(1);
        cell.setCellStyle(bodyStyle);// 表格黑色边框
        cell.setCellValue("11612260574");//手机号

        cell = bodyRow.createCell(2);
        cell.setCellStyle(bodyStyle);//
        cell.setCellValue("广渠金茂府");//数据查看范围

        cell = bodyRow.createCell(3);
        cell.setCellStyle(bodyStyle);//
        cell.setCellValue("身份申诉管理");//提醒模块

        XSSFRow bodyRow2 = sheet.createRow(2);

        cell = bodyRow2.createCell(0);
        cell.setCellStyle(bodyStyle);// 表格黑色边框
        cell.setCellValue("郭佳2");//姓名

        cell = bodyRow2.createCell(1);
        cell.setCellStyle(bodyStyle);// 表格黑色边框
        cell.setCellValue("15201531611");//手机号

        cell = bodyRow2.createCell(2);
        cell.setCellStyle(bodyStyle);//
        cell.setCellValue("亚奥．金茂悦");//数据查看范围

        cell = bodyRow2.createCell(3);
        cell.setCellStyle(bodyStyle);//
        cell.setCellValue("房屋报修管理");//提醒模块

        XSSFRow bodyRow3 = sheet.createRow(3);

        cell = bodyRow3.createCell(0);
        cell.setCellStyle(bodyStyle);// 表格黑色边框
        cell.setCellValue("maqiang2");//姓名

        cell = bodyRow3.createCell(1);
        cell.setCellStyle(bodyStyle);// 表格黑色边框
        cell.setCellValue("12621608267");//手机号

        cell = bodyRow3.createCell(2);
        cell.setCellStyle(bodyStyle);//
        cell.setCellValue("广渠金茂府");//数据查看范围

        cell = bodyRow3.createCell(3);
        cell.setCellStyle(bodyStyle);//
        cell.setCellValue("活动报名管理");//提醒模块

        XSSFRow bodyRow4 = sheet.createRow(4);

        cell = bodyRow4.createCell(0);
        cell.setCellStyle(bodyStyle);// 表格黑色边框
        cell.setCellValue("maqiang2");//姓名

        cell = bodyRow4.createCell(1);
        cell.setCellStyle(bodyStyle);// 表格黑色边框
        cell.setCellValue("12621608267");//手机号

        cell = bodyRow4.createCell(2);
        cell.setCellStyle(bodyStyle);//
        cell.setCellValue("广渠金茂府,亚奥．金茂悦");//数据查看范围

        cell = bodyRow4.createCell(3);
        cell.setCellStyle(bodyStyle);//
        cell.setCellValue("活动报名管理,房屋报修管理");//提醒模块

        try {
            //String fileName = new String(("报修单").getBytes(), "ISO8859-1");
            String fileName = new String(("短信消息提醒人员表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("短信消息提醒人员表", "UTF8");
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
        return "";
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 导出excel
    */
    @Override
    public String exportExcel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, SMSAlertsDTO smsAlertsDTO, WebPage webPage, SMSAlertsSearchDTO smsAlertsSearchDTO) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        List<SMSAlertsDTO> list = getAllSMSPeople(smsAlertsSearchDTO, webPage);

        //创建Sheet页
        XSSFSheet sheet = workBook.createSheet("短信消息提醒人员表");

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

        String[] titles = {"序号", "消息提醒事项", "短信提醒范围","短信文本","姓名","手机号"};

        XSSFRow headRow = sheet.createRow(0);

        if(list!=null&&list.size()>0){
            list.forEach(a -> {
                XSSFCell cell = null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);

                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                sheet.setColumnWidth(0, 1000);
                sheet.setColumnWidth(1, 4000);
                sheet.setColumnWidth(2, 10000);
                sheet.setColumnWidth(3, 10000);
                sheet.setColumnWidth(4, 2500);
                sheet.setColumnWidth(5, 3000);
                for (int i = 0; i < list.size(); i++) {
                    XSSFRow bodyRow = sheet.createRow(i + 1);
                    SMSAlertsDTO smsAlerts = list.get(i);

                    cell = bodyRow.createCell(0);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(i + 1);//序号


                    cell = bodyRow.createCell(1);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(smsAlerts.getModel());//消息提醒事项

                    cell = bodyRow.createCell(2);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(smsAlerts.getScope());//短信提醒范围

                    cell = bodyRow.createCell(3);
                    cell.setCellStyle(bodyStyle);//
                    cell.setCellValue(smsAlerts.getContent());//短信文本

                    cell = bodyRow.createCell(4);
                    cell.setCellStyle(bodyStyle);//
                    cell.setCellValue(smsAlerts.getName());//姓名

                    cell = bodyRow.createCell(5);
                    cell.setCellStyle(bodyStyle);//
                    cell.setCellValue(smsAlerts.getPhone());//手机号

                }
            });
            try {
                //String fileName = new String(("报修单").getBytes(), "ISO8859-1");
                String fileName = new String(("短信消息提醒人员表").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("短信消息提醒人员表", "UTF8");
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
        }else{
            outputStream.flush();
            outputStream.close();
        }
        return "";
    }

    /**
     * 导入excel
     * POI:解析Excel文件中的数据并把每行数据封装成一个实体
     * @param fis 文件输入流
     * List<SMSPeopleAlertsEntity> Excel中数据封装实体的集合
     *            shanshan
     */
    @Override
    public boolean importEmployeeByPoi(UserPropertyStaffEntity user, InputStream fis) {
        SMSPeopleAlertsDTO smsPeopleAlertsDTO = null;
        SMSPeopleAlertsEntity smsPeopleAlertsEntity = null;

        try {
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for(int i = 0; i < hwb.getNumberOfSheets(); i++) {
                sheet = hwb.getSheetAt(i);
                //遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for(int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    smsPeopleAlertsDTO = new SMSPeopleAlertsDTO();
                    smsPeopleAlertsEntity = new SMSPeopleAlertsEntity();

                    /*此方法规定Excel文件中的数据必须为文本格式，所以在解析数据的时候未进行判断*/

                    //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                    if(SMSAlertsServiceImpl.getCellValue(row.getCell(0)) != null && !"".equals(SMSAlertsServiceImpl.getCellValue(row.getCell(0)))) {
                        smsPeopleAlertsDTO.setName(SMSAlertsServiceImpl.getCellValue(row.getCell(0)));
                    }
                    if(SMSAlertsServiceImpl.getCellValue(row.getCell(1)) != null && !"".equals(SMSAlertsServiceImpl.getCellValue(row.getCell(1)))) {
                        smsPeopleAlertsDTO.setPhone(SMSAlertsServiceImpl.getCellValue(row.getCell(1)));
                    }
                    smsPeopleAlertsDTO.setOperator(user.getStaffName());

                    //获取该项目下的SMSAlertsEntity并保存
                    if (SMSAlertsServiceImpl.getCellValue(row.getCell(2)) != null || !"".equals(SMSAlertsServiceImpl.getCellValue(row.getCell(2)))){
                        String[] projectNames = SMSAlertsServiceImpl.getCellValue(row.getCell(2)).split(",");
                        for (String projectName : projectNames) {
                            projectName = projectName.trim();
                            smsPeopleAlertsEntity.setScope(projectName);
                            String projectId = smsAlertsRepository.getProjectNum(projectName);
                            smsPeopleAlertsEntity.setProjectNum(projectId);
                            String cityId = smsAlertsRepository.getCityId(projectId);
                            smsPeopleAlertsEntity.setCity(cityId);
                            smsPeopleAlertsDTO.setCityIds(cityId);
                            Object[] object = smsAlertsRepository.getSMSAlerts(projectId,cityId);
                            String repairContent = "";
                            String appealContent = "";
                            String activityContent = "";
                            if (object != null) {
                                repairContent = (object[0] == null ? "" : object[0].toString());
                                appealContent = (object[1] == null ? "" : object[1].toString());
                                activityContent = (object[2] == null ? "" : object[2].toString());
                            }

                            if (!StringUtils.isNullOrEmpty(SMSAlertsServiceImpl.getCellValue(row.getCell(3)))) {
                                String[] models = SMSAlertsServiceImpl.getCellValue(row.getCell(3)).split(",");
                                for (String model : models) {
                                    model = model.trim();
                                    if ("房屋报修管理".equals(model)) {
                                        //005700030000
                                        if (repairContent != null && !"".equals(repairContent)){
                                            smsPeopleAlertsEntity.setModel(model);
                                            smsPeopleAlertsEntity.setContent(repairContent);
                                            this.saveEntity(smsPeopleAlertsEntity, smsPeopleAlertsDTO);
                                        }
                                    }
                                    if ("身份申诉管理".equals(model)) {
                                        //000900030000
                                        //于20170313菜单变更->005200010000
                                        if (appealContent != null && !"".equals(appealContent)){
                                            smsPeopleAlertsEntity.setModel(model);
                                            smsPeopleAlertsEntity.setContent(appealContent);
                                            this.saveEntity(smsPeopleAlertsEntity, smsPeopleAlertsDTO);
                                        }
                                    }
                                    if ("活动报名管理".equals(model)) {
                                        //005400030000
                                        if (activityContent != null && !"".equals(activityContent)){
                                            smsPeopleAlertsEntity.setModel(model);
                                            smsPeopleAlertsEntity.setContent(activityContent);
                                            this.saveEntity(smsPeopleAlertsEntity, smsPeopleAlertsDTO);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell){
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
