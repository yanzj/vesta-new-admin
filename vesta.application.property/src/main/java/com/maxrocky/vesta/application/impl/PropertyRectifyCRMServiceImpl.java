package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.DTO.admin.HouseTypeDTO;
import com.maxrocky.vesta.application.DTO.admin.RectificationListDTO;
import com.maxrocky.vesta.application.DTO.admin.RectifyOrderDTO;
import com.maxrocky.vesta.application.DTO.admin.ReturnJsonDTO;
import com.maxrocky.vesta.application.inf.HouseRoomTypeService;
import com.maxrocky.vesta.application.inf.PropertyRectifyCRMService;
import com.maxrocky.vesta.application.inf.RepairClientService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.setting.ImageConfig;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by mql on 2016/6/12.
 */
@Service
public class PropertyRectifyCRMServiceImpl implements PropertyRectifyCRMService {
    @Autowired
    PropertyRectifyCRMRepository propertyRectifyCRMRepository;

    @Autowired
    PropertyImageRepository propertyImageRepository;

    @Autowired
    RepairClientService repairClientService;

    @Autowired
    OrganizeRepository organizeRepository;

    @Autowired
    ImgService imgService;

    @Autowired
    HouseRoomTypeService houseRoomTypeService;

    @Autowired
    DescribeRepository describeRepository;

    @Autowired
    DeliveryPlanCRMRepository deliveryPlanCRMRepository;

    @Autowired
    HouseRoomRepository houseRoomRepository;

    @Autowired
    private PropertyRepairCRMRepository propertyRepairCRMRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    RoleDataRepository roleDataRepository;
    @Autowired
    RectificationRepository rectificationRepository;

    @Override
    public QuestionUpdateCheckDTO questionUpdateCheck(List<String> projectList, Date beginTime, String id) {
        boolean checkFlag = propertyRectifyCRMRepository.haveNewData(projectList, beginTime, id);
        if (checkFlag) {
            return new QuestionUpdateCheckDTO("Y");
        }
        return new QuestionUpdateCheckDTO("N");
    }

    @Override
    public QuestionUpdateCheckDTO questionUpdateCheckByType(List<String> projectList, String beginTime, String id, String planType, String idNew, String timeStampNew,String projectNum,String userId,List<String> userPro) {
        //代表没有5权限的项目列表
        List<String> ListAll=new ArrayList<String>();
        //代表有5权限的项目列表
        List<String> List5=new ArrayList<String>();
        //交房验房单项目列表
        for(String project:projectList){
            String name=project.substring(project.length()-1);
            String pj=project.substring(0,project.length()-1);
            if("5".equals(name)){
                List5.add(pj);
            }else{
                ListAll.add(pj);
            }
        }
        userPro.add("");
        boolean checkFlag = propertyRectifyCRMRepository.haveNewDataByType(ListAll,List5, beginTime, id, planType, projectNum,userId);
        if (checkFlag) {
            return new QuestionUpdateCheckDTO("Y");
        }
        if (!"-1".equals(idNew) && !"-1".equals(timeStampNew) && "13".equals(planType)) {
            boolean checkFlagNew = propertyRectifyCRMRepository.haveNewDatajiaofangByType(idNew, timeStampNew,userPro);
            if (checkFlagNew) {
                return new QuestionUpdateCheckDTO("Y");
            }
        }
        return new QuestionUpdateCheckDTO("N");
    }

    @Override
    public QuestionDTO getQuestionList(List<String> userPro, Date beginTime, String id) {
        List<Object[]> list = propertyRectifyCRMRepository.getQuestionList(userPro, beginTime, id);
        List<PropertyRectifyDTO> questionList = new ArrayList<PropertyRectifyDTO>();
        QuestionDTO questionDTO = new QuestionDTO();
        if (list != null && !list.isEmpty()) {
            questionDTO.setTimeStamp(DateUtils.format((Date) list.get(list.size() - 1)[19]));
            questionDTO.setId(list.get(list.size() - 1)[35] == null ? "" : list.get(list.size() - 1)[35].toString());
            for (Object[] obj : list) {
                PropertyRectifyDTO propertyRectifyDTO = new PropertyRectifyDTO();
                propertyRectifyDTO.setRectifyId(obj[0] == null ? "" : obj[0].toString());//整改单号
                propertyRectifyDTO.setDepartment(obj[1] == null ? "" : obj[1].toString());//部门
                propertyRectifyDTO.setRoomId(obj[2] == null ? "" : obj[2].toString());//房间id
                propertyRectifyDTO.setRoomNum(obj[3] == null ? "" : obj[3].toString());//房间编码
                propertyRectifyDTO.setPlanNum(obj[4] == null ? "" : obj[4].toString());//房间计划编码
                propertyRectifyDTO.setAcceptanceDate(obj[5] == null ? "" : DateUtils.format((Date) obj[5]));//内部预验房日期
                propertyRectifyDTO.setProblemType(obj[6] == null ? "" : obj[6].toString());//问题类型
                propertyRectifyDTO.setClassifyOne(obj[7] == null ? "" : obj[7].toString());//一级分类
                propertyRectifyDTO.setClassifyTwo(obj[8] == null ? "" : obj[8].toString());//二级分类
                propertyRectifyDTO.setClassifyThree(obj[9] == null ? "" : obj[9].toString());//三级分类
                propertyRectifyDTO.setRegisterDate(obj[10] == null ? "" : DateUtils.format((Date) obj[10]));//登记日期
                propertyRectifyDTO.setRectifyState(obj[11] == null ? "" : obj[11].toString());//整改状态
                propertyRectifyDTO.setRoomLocation(obj[12] == null ? "" : obj[12].toString());//房屋位置
                propertyRectifyDTO.setSupplier(obj[13] == null ? "" : obj[13].toString());//供应商
                propertyRectifyDTO.setRectifyCompleteDate(obj[14] == null ? "" : DateUtils.format((Date) obj[14]));//整改完成时间
                propertyRectifyDTO.setRealityDate(obj[15] == null ? "" : DateUtils.format((Date) obj[15]));//实际完成时间
                propertyRectifyDTO.setProblemDescription(obj[16] == null ? "" : obj[16].toString());//问题描述
                propertyRectifyDTO.setDealResult(obj[17] == null ? "" : obj[17].toString());//处理结果
                propertyRectifyDTO.setCreateDate(obj[18] == null ? "" : DateUtils.format((Date) obj[18]));//创建时间
                propertyRectifyDTO.setModifyDate(obj[19] == null ? "" : DateUtils.format((Date) obj[19]));//修改时间
                propertyRectifyDTO.setCreateBy(obj[20] == null ? "" : obj[20].toString());//创建人编码
                propertyRectifyDTO.setCreatePhone(obj[21] == null ? "" : obj[21].toString());//创建人联系电话
                propertyRectifyDTO.setPlanType(obj[22] == null ? "" : obj[22].toString());//活动类型
                propertyRectifyDTO.setRepairManager(obj[23] == null ? "" : obj[23].toString());//整改负责人ID
                propertyRectifyDTO.setRepairPhone(obj[24] == null ? "" : obj[24].toString());//整改人联系电话
                propertyRectifyDTO.setDutyTaskDate(obj[25] == null ? "" : DateUtils.format((Date) obj[25]));//接单时间
                propertyRectifyDTO.setLimitDate(obj[26] == null ? "" : DateUtils.format((Date) obj[26], "yyyy-MM-dd"));//整改时限
                propertyRectifyDTO.setxCoordinates(obj[27] == null ? "" : obj[27].toString());//X坐标
                propertyRectifyDTO.setyCoordinates(obj[28] == null ? "" : obj[28].toString());//Y坐标
                propertyRectifyDTO.setProjectNum(obj[29] == null ? "" : obj[29].toString());//项目编码
                propertyRectifyDTO.setProjectName(obj[30] == null ? "" : obj[30].toString());//项目名称
                propertyRectifyDTO.setCreateName(obj[31] == null ? "" : obj[31].toString());//创建人名称
                propertyRectifyDTO.setAddress(obj[32] == null ? "" : obj[32].toString());//房屋地址
                propertyRectifyDTO.setRepairDescription(obj[33] == null ? "" : obj[33].toString());//整改记录描述
                propertyRectifyDTO.setUpdateFlag(obj[34] == null ? "" : obj[34].toString());//整改记录描述
                propertyRectifyDTO.setLocationName(obj[36] == null ? "" : obj[36].toString());//问题位置名称
                propertyRectifyDTO.setUnitNum(obj[37] == null ? "" : obj[37].toString());//单元编码
                UserPropertyStaffEntity UserPropertyStaff = rectificationRepository.getusername(obj[38] == null ? "" : obj[38].toString());
                if (UserPropertyStaff != null) {
                    if (!StringUtil.isEmpty(UserPropertyStaff.getUserName())) {
                        propertyRectifyDTO.setRepairManager(UserPropertyStaff.getStaffName());
                    }
                }
                List<PropertyImageEntity> imgList = propertyImageRepository.getImageByType(propertyRectifyDTO.getRectifyId(), "5");
                if (imgList != null && !imgList.isEmpty()) {
                    List<RectifyImageDTO> imageList = new ArrayList<RectifyImageDTO>();
                    for (PropertyImageEntity p : imgList) {
                        RectifyImageDTO image = new RectifyImageDTO();
                        image.setCaseId(p.getImgFkId());
                        image.setImageId(p.getImageId());
                        image.setImageUrl(p.getImagePath());
                        imageList.add(image);
                    }
                    propertyRectifyDTO.setImageList(imageList);
                }

                List<PropertyImageEntity> reviewList = propertyImageRepository.getImageByType(propertyRectifyDTO.getRectifyId(), "6");
                if (reviewList != null && !reviewList.isEmpty()) {
                    List<RectifyImageDTO> reviewImageList = new ArrayList<RectifyImageDTO>();
                    for (PropertyImageEntity p : reviewList) {
                        RectifyImageDTO image = new RectifyImageDTO();
                        image.setCaseId(p.getImgFkId());
                        image.setImageId(p.getImageId());
                        image.setImageUrl(p.getImagePath());
                        reviewImageList.add(image);
                    }
                    propertyRectifyDTO.setReviewImgList(reviewImageList);
                }
                questionList.add(propertyRectifyDTO);
            }
        }

        questionDTO.setList(questionList);
        return questionDTO;
    }

    @Override
    public QuestionDTO getQuestionListByType(List<String> userPro, String beginTime, String id, String planType,String projectNum,String userId) {
        //代表没有5权限的项目列表
        List<String> ListAll=new ArrayList<String>();
        //代表有5权限的项目列表
        List<String> List5=new ArrayList<String>();
        for(String project:userPro){
            String name=project.substring(project.length()-1);
            String pj=project.substring(0,project.length()-1);
            if("5".equals(name)){
                List5.add(pj);
            }else{
                ListAll.add(pj);
            }
        }
        List<Object[]> list = propertyRectifyCRMRepository.getQuestionListByType(ListAll,List5, beginTime, id, planType, projectNum,userId);
        List<PropertyRectifyDTO> questionList = new ArrayList<PropertyRectifyDTO>();
        QuestionDTO questionDTO = new QuestionDTO();
        if (list != null && !list.isEmpty()) {
            questionDTO.setTimeStamp(DateUtils.format((Date) list.get(list.size() - 1)[19]));
            questionDTO.setId(list.get(list.size() - 1)[42] == null ? "" : list.get(list.size() - 1)[42].toString());
            for (Object[] obj : list) {
                PropertyRectifyDTO propertyRectifyDTO = new PropertyRectifyDTO();
                propertyRectifyDTO.setRectifyId(obj[0] == null ? "" : obj[0].toString());//整改单号
                propertyRectifyDTO.setDepartment(obj[1] == null ? "" : obj[1].toString());//部门
                propertyRectifyDTO.setRoomId(obj[2] == null ? "" : obj[2].toString());//房间id
                propertyRectifyDTO.setRoomNum(obj[3] == null ? "" : obj[3].toString());//房间编码
                propertyRectifyDTO.setPlanNum(obj[4] == null ? "" : obj[4].toString());//房间计划编码
                propertyRectifyDTO.setAcceptanceDate(obj[5] == null ? "" : DateUtils.format((Date) obj[5]));//内部预验房日期
                propertyRectifyDTO.setProblemType(obj[6] == null ? "" : obj[6].toString());//问题类型
                propertyRectifyDTO.setClassifyOne(obj[7] == null ? "" : obj[7].toString());//一级分类
                propertyRectifyDTO.setClassifyTwo(obj[8] == null ? "" : obj[8].toString());//二级分类
                propertyRectifyDTO.setClassifyThree(obj[9] == null ? "" : obj[9].toString());//三级分类
                propertyRectifyDTO.setRegisterDate(obj[10] == null ? "" : DateUtils.format((Date) obj[10]));//登记日期
                propertyRectifyDTO.setRectifyState(obj[11] == null ? "" : obj[11].toString());//整改状态
                propertyRectifyDTO.setRoomLocation(obj[12] == null ? "" : obj[12].toString());//房屋位置
                propertyRectifyDTO.setSupplier(obj[13] == null ? "" : obj[13].toString());//供应商
                propertyRectifyDTO.setRectifyCompleteDate(obj[15] == null ? "" : DateUtils.format((Date) obj[15]));//整改完成时间
                propertyRectifyDTO.setRealityDate(obj[15] == null ? "" : DateUtils.format((Date) obj[15]));//实际完成时间
                propertyRectifyDTO.setProblemDescription(obj[16] == null ? "" : obj[16].toString());//问题描述
                propertyRectifyDTO.setDealResult(obj[17] == null ? "" : obj[17].toString());//处理结果
                propertyRectifyDTO.setCreateDate(obj[18] == null ? "" : DateUtils.format((Date) obj[18]));//创建时间
                propertyRectifyDTO.setModifyDate(obj[19] == null ? "" : DateUtils.format((Date) obj[19]));//修改时间
                propertyRectifyDTO.setCreateBy(obj[20] == null ? "" : obj[20].toString());//创建人编码
                propertyRectifyDTO.setCreatePhone(obj[21] == null ? "" : obj[21].toString());//创建人联系电话
                propertyRectifyDTO.setPlanType(obj[22] == null ? "" : obj[22].toString());//活动类型
                propertyRectifyDTO.setRepairManager(obj[40] == null ? "" : obj[40].toString());//整改负责人名称
                propertyRectifyDTO.setRepairPhone(obj[24] == null ? "" : obj[24].toString());//整改人联系电话
                propertyRectifyDTO.setDutyTaskDate(obj[25] == null ? "" : DateUtils.format((Date) obj[25]));//接单时间
                propertyRectifyDTO.setLimitDate(obj[26] == null ? "" : DateUtils.format((Date) obj[26], "yyyy-MM-dd"));//整改时限
                propertyRectifyDTO.setxCoordinates(obj[27] == null ? "" : obj[27].toString());//X坐标
                propertyRectifyDTO.setyCoordinates(obj[28] == null ? "" : obj[28].toString());//Y坐标
                propertyRectifyDTO.setProjectNum(obj[29] == null ? "" : obj[29].toString());//项目编码
                propertyRectifyDTO.setProjectName(obj[30] == null ? "" : obj[30].toString());//项目名称
                propertyRectifyDTO.setCreateName(obj[31] == null ? "" : obj[31].toString());//创建人名称
                propertyRectifyDTO.setAddress(obj[32] == null ? "" : obj[32].toString());//房屋地址
                propertyRectifyDTO.setRepairDescription(obj[33] == null ? "" : obj[33].toString());//整改记录描述
                propertyRectifyDTO.setUpdateFlag(obj[34] == null ? "" : obj[34].toString());//整改记录描述
                propertyRectifyDTO.setLocationName(obj[35] == null ? "" : obj[35].toString());//问题位置名称
                propertyRectifyDTO.setUnitNum(obj[36] == null ? "" : obj[36].toString());//单元编码
                propertyRectifyDTO.setSerialNumber(obj[37] == null ? "" : obj[37].toString());//问题编号
                propertyRectifyDTO.setRepairManagerId(obj[38] == null ? "" : obj[38].toString());//内部负责人id
                propertyRectifyDTO.setSupplierID(obj[39] == null ? "" : obj[39].toString());//供应商负责人id
                propertyRectifyDTO.setReminderTime(obj[41] == null ? "" : DateUtils.format((Date) obj[41]));//提醒时间
                List<PropertyImageEntity> imgList = propertyImageRepository.getImageByType(propertyRectifyDTO.getRectifyId(), "5");
                if (imgList != null && !imgList.isEmpty()) {
                    List<RectifyImageDTO> imageList = new ArrayList<RectifyImageDTO>();
                    for (PropertyImageEntity p : imgList) {
                        RectifyImageDTO image = new RectifyImageDTO();
                        image.setCaseId(p.getImgFkId());
                        image.setImageId(p.getImageId());
                        image.setImageUrl(p.getImagePath());
                        imageList.add(image);
                    }
                    propertyRectifyDTO.setImageList(imageList);
                }

                List<PropertyImageEntity> reviewList = propertyImageRepository.getImageByType(propertyRectifyDTO.getRectifyId(), "6");
                if (reviewList != null && !reviewList.isEmpty()) {
                    List<RectifyImageDTO> reviewImageList = new ArrayList<RectifyImageDTO>();
                    for (PropertyImageEntity p : reviewList) {
                        RectifyImageDTO image = new RectifyImageDTO();
                        image.setCaseId(p.getImgFkId());
                        image.setImageId(p.getImageId());
                        image.setImageUrl(p.getImagePath());
                        reviewImageList.add(image);
                    }
                    propertyRectifyDTO.setReviewImgList(reviewImageList);
                }
                questionList.add(propertyRectifyDTO);
            }
        }

        questionDTO.setList(questionList);
        return questionDTO;
    }

    /*
    * departmentList 有关的都需要修改
    * */
    @Override
    public RectifyOrderDTO getOrderedList(List userProject, String beginTime, String id, String userid) {
        RectifyOrderDTO rectifyOrderDTO = new RectifyOrderDTO();
        List<Object[]> list = propertyRectifyCRMRepository.getOrderedList(userProject, beginTime, id, userid);
        List<RectificationListDTO> orderList = new ArrayList<RectificationListDTO>();
        if (list != null && !list.isEmpty()) {
            rectifyOrderDTO.setTimeStamp(DateUtils.format((Date) list.get(list.size() - 1)[19]));
            rectifyOrderDTO.setId(list.get(list.size() - 1)[35] == null ? "" : list.get(list.size() - 1)[35].toString());
            for (Object[] obj : list) {
                RectificationListDTO rectificationListDTO = new RectificationListDTO();
                rectificationListDTO.setRepairId(obj[0] == null ? "" : obj[0].toString());//整改单号d
                rectificationListDTO.setRoomId(obj[2] == null ? "" : obj[2].toString());//房间ID
                rectificationListDTO.setRoomNum(obj[3] == null ? "" : obj[3].toString());//房间编码
                rectificationListDTO.setRoomLocation(obj[36] == null ? "" : obj[36].toString());//房屋位置名称
                rectificationListDTO.setUsername(obj[31] == null ? "" : obj[31].toString());//报修人名称
                rectificationListDTO.setUserPhone(obj[21] == null ? "" : obj[21].toString());//创建人联系电话
                rectificationListDTO.setClassifyOne(obj[7] == null ? "" : obj[7].toString());//一级分类
                rectificationListDTO.setClassifyTwo(obj[8] == null ? "" : obj[8].toString());//二级分类
                rectificationListDTO.setClassifyThree(obj[9] == null ? "" : obj[9].toString());//三级分类
                rectificationListDTO.setSupplier(obj[13] == null ? "" : obj[13].toString());//供应商
                rectificationListDTO.setCreateDate(obj[10] == null ? "" : DateUtils.format((Date) obj[10]));//登记日期
                rectificationListDTO.setLimitDate(obj[26] == null ? "" : DateUtils.format((Date) obj[26], "yyyy-MM-dd"));//整改时限
                rectificationListDTO.setxCoordinates(obj[27] == null ? "" : obj[27].toString());
                rectificationListDTO.setyCoordinates(obj[28] == null ? "" : obj[28].toString());
                rectificationListDTO.setDealCompleteDate(obj[15] == null ? "" : DateUtils.format((Date) obj[15]));//整改完成时间
                rectificationListDTO.setDealResult(obj[17] == null ? "" : obj[17].toString());//整改记录描述
                rectificationListDTO.setType("1");//代表整改单
                rectificationListDTO.setUptime(obj[19] == null ? "" : DateUtils.format((Date) obj[19]));//修改时间
                rectificationListDTO.setDepartment(obj[1] == null ? "" : obj[1].toString());//部门，派单部门
                rectificationListDTO.setContent(obj[16] == null ? "" : obj[16].toString());//问题描述
                rectificationListDTO.setState(obj[11] == null ? "" : obj[11].toString());
                rectificationListDTO.setProblemType(obj[6] == null ? "" : obj[6].toString());//问题类型
                rectificationListDTO.setPlantype(obj[22] == null ? "" : obj[22].toString());//类型：内部预演/正式交房/工地开放
                rectificationListDTO.setAddress(obj[32] == null ? "" : obj[32].toString());//地址
                rectificationListDTO.setProjectNum(obj[29] == null ? "" : obj[29].toString());//计划编码
                rectificationListDTO.setDealPhone(obj[24] == null ? "" : obj[24].toString());//整改负责人联系电话
                rectificationListDTO.setDealPeoplename(obj[38] == null ? "" : obj[38].toString());//整改负责人
                rectificationListDTO.setSerialNumber(obj[39] == null ? "" : obj[39].toString());//问题编号
                rectificationListDTO.setRepairManagerId(obj[40] == null ? "" : obj[40].toString());//内部负责人id
                rectificationListDTO.setSupplierId(obj[41] == null ? "" : obj[41].toString());//供应商负责人id
                rectificationListDTO.setDealPeople(obj[42] == null ? "" : obj[42].toString());//整改负责人
                rectificationListDTO.setReminderTime(obj[43] == null ? "" : DateUtils.format((Date) obj[43]));//提醒时间
                UserPropertyStaffEntity UserPropertyStaff = rectificationRepository.getusername(obj[42] == null ? "" : obj[42].toString());
                if (UserPropertyStaff != null) {
                    if (!StringUtil.isEmpty(UserPropertyStaff.getUserName())) {
                        rectificationListDTO.setDealPeoplename(UserPropertyStaff.getStaffName());
                    }
                }
                List<PropertyImageEntity> imgList = propertyImageRepository.getImageByType(rectificationListDTO.getRepairId(), "5");
                if (imgList != null && !imgList.isEmpty()) {
                    List<RectifyImageDTO> imageList = new ArrayList<RectifyImageDTO>();
                    for (PropertyImageEntity p : imgList) {
                        RectifyImageDTO image = new RectifyImageDTO();
                        image.setCaseId(p.getImgFkId());
                        image.setImageId(p.getImageId());
                        image.setImageUrl(p.getImagePath());
                        imageList.add(image);
                    }
                    rectificationListDTO.setImageList(imageList);//整改图片
                }

                List<PropertyImageEntity> reviewList = propertyImageRepository.getImageByType(rectificationListDTO.getRepairId(), "6");
                if (reviewList != null && !reviewList.isEmpty()) {
                    List<RectifyImageDTO> reviewImageList = new ArrayList<RectifyImageDTO>();
                    for (PropertyImageEntity p : reviewList) {
                        RectifyImageDTO image = new RectifyImageDTO();
                        image.setCaseId(p.getImgFkId());
                        image.setImageId(p.getImageId());
                        image.setImageUrl(p.getImagePath());
                        reviewImageList.add(image);
                    }
                    rectificationListDTO.setImgList(reviewImageList);//整改后图片
                }

                orderList.add(rectificationListDTO);

            }
        }
        rectifyOrderDTO.setList(orderList);
        return rectifyOrderDTO;
    }

    @Override
    public String getOrderedCount(List userProject, String beginTime, String id, String userid) {
        String ss = propertyRectifyCRMRepository.getOrderedCount(userProject, beginTime, id, userid);
        return ss;
    }

    @Override
    public List<String> getOrderedidList(List userProject, String beginTime, String id, String userid) {
//        List<String> ss = propertyRectifyCRMRepository.getOrderedCountList(userProject, beginTime, id, userid);
        List<String> sy = propertyRectifyCRMRepository.getOrderedCountAllList(beginTime, id);

        return sy;
    }

    @Override
    public QuestionDTO saveQuestion(List<PropertyRectifyDTO> questionList, String userName) {
        List<PropertyRectifyDTO> list = new ArrayList<PropertyRectifyDTO>();
        String updateflag = "1";
        QuestionDTO questionDTO = new QuestionDTO();
        List<PropertyRectifyDTO> idList = new ArrayList<PropertyRectifyDTO>();
        if (questionList != null && !questionList.isEmpty()) {
            for (PropertyRectifyDTO propertyRectifyDTO : questionList) {
                List<OrganizeEntity> orgList = organizeRepository.getOrganizeInProjectNum(propertyRectifyDTO.getProjectNum());
                PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(propertyRectifyDTO.getRectifyId());
                String[] ss = propertyRectifyDTO.getProjectNum().split("-");
                String recifyid = propertyRectifyCRMRepository.getrectifyidNew(ss[ss.length - 1] + "-Z");
                if (recifyid.length() == 1) {
                    recifyid = "000" + recifyid;
                }
                if (recifyid.length() == 2) {
                    recifyid = "00" + recifyid;
                }
                if (recifyid.length() == 3) {
                    recifyid = "0" + recifyid;
                }
                String id = ss[ss.length - 1] + "-Z-" + DateUtils.getNow("yyyyMMdd") + "-A-" + recifyid;
                System.out.println(id + "!~~~~~~~~~~idbianmaxian !!!!!!!!!!!!!!!!!!!~~~~~~~~~");
                if (propertyRectifyCRMEntity == null) {
                    propertyRectifyCRMEntity = new PropertyRectifyCRMEntity();
                    updateflag = "0";
                    propertyRectifyCRMEntity.setRectifyId(id);//整改单号'
                }
                propertyRectifyCRMEntity.setDepartment("2");//工程接单权限,即派单部门
                if (propertyRectifyDTO.getReminderTime() != null && !"".equals(propertyRectifyDTO.getReminderTime())) {
                    propertyRectifyCRMEntity.setReminderTime(DateUtils.parse(propertyRectifyDTO.getReminderTime()));
                }
                String roomSequence = propertyRectifyCRMRepository.getRoomSequence(propertyRectifyDTO.getRoomNum());
                if (propertyRectifyDTO.getRepairManagerId() != null && !"".equals(propertyRectifyDTO.getRepairManagerId())) {
                    propertyRectifyCRMEntity.setDealPeople(propertyRectifyDTO.getRepairManagerId());
                    propertyRectifyCRMEntity.setRepairManagerId(propertyRectifyDTO.getRepairManagerId());
                    UserPropertyStaffEntity UserPropertyStaff = rectificationRepository.getusername(propertyRectifyDTO.getRepairManagerId());
                    if (UserPropertyStaff != null) {
                        if (UserPropertyStaff.getUserName() != null && !"".equals(UserPropertyStaff.getUserName())) {
                            propertyRectifyCRMEntity.setRepairManager(UserPropertyStaff.getUserName());
                        }
                    }
                }
                if (propertyRectifyDTO.getSupplierID() != null && !"".equals(propertyRectifyDTO.getSupplierID())) {
                    propertyRectifyCRMEntity.setSupplierID(propertyRectifyDTO.getSupplierID());
                    propertyRectifyCRMEntity.setDealPeople(propertyRectifyDTO.getSupplierID());
                    UserPropertyStaffEntity UserPropertyStaff = rectificationRepository.getusername(propertyRectifyDTO.getSupplierID());
                    if (UserPropertyStaff != null) {
                        if (UserPropertyStaff.getUserName() != null && !"".equals(UserPropertyStaff.getUserName())) {
                            propertyRectifyCRMEntity.setSupplierName(UserPropertyStaff.getUserName());
                            propertyRectifyCRMEntity.setRepairManager(UserPropertyStaff.getUserName());
                        }
                    }
                }
                propertyRectifyCRMEntity.setSerialNumber(roomSequence);//当前房间序列号
                propertyRectifyCRMEntity.setRoomId(propertyRectifyDTO.getRoomId());//房间id
                propertyRectifyCRMEntity.setRoomNum(propertyRectifyDTO.getRoomNum());//房间编码
                propertyRectifyCRMEntity.setPlanNum(propertyRectifyDTO.getPlanNum());//房间计划编码
                propertyRectifyCRMEntity.setAcceptanceDate((propertyRectifyDTO.getAcceptanceDate() == null || "".equals(propertyRectifyDTO.getAcceptanceDate())) ? null : DateUtils.parse(propertyRectifyDTO.getAcceptanceDate()));//内部预验房日期
                propertyRectifyCRMEntity.setProblemType(propertyRectifyDTO.getProblemType());//问题类型
                propertyRectifyCRMEntity.setClassifyOne(propertyRectifyDTO.getClassifyOne());//一级分类
                propertyRectifyCRMEntity.setClassifyTwo(propertyRectifyDTO.getClassifyTwo());//二级分类
                propertyRectifyCRMEntity.setClassifyThree(propertyRectifyDTO.getClassifyThree());//三级分类
                //propertyRectifyCRMEntity.setRegisterDate((propertyRectifyDTO.getRegisterDate() == null || "".equals(propertyRectifyDTO.getRegisterDate())) ? new Date() : DateUtils.parse(propertyRectifyDTO.getRegisterDate()));//登记日期
                propertyRectifyCRMEntity.setRegisterDate(new Date());
                propertyRectifyCRMEntity.setRectifyState(propertyRectifyDTO.getRectifyState());//整改状态
                propertyRectifyCRMEntity.setRoomLocation(propertyRectifyDTO.getRoomLocation());//房屋位置
                propertyRectifyCRMEntity.setSupplier(propertyRectifyDTO.getSupplier());//供应商
                //propertyRectifyCRMEntity.setRectifyCompleteDate((propertyRectifyDTO.getRectifyCompleteDate() == null || "".equals(propertyRectifyDTO.getRectifyCompleteDate())) ? null : DateUtils.parse(propertyRectifyDTO.getRectifyCompleteDate()));//整改完成时间
                //propertyRectifyCRMEntity.setRealityDate((propertyRectifyDTO.getRealityDate() == null || "".equals(propertyRectifyDTO.getRealityDate())) ? null : DateUtils.parse(propertyRectifyDTO.getRealityDate()));//实际完成时间
                propertyRectifyCRMEntity.setProblemDescription(propertyRectifyDTO.getProblemDescription());//问题描述
                //propertyRectifyCRMEntity.setDealResult(propertyRectifyDTO.getDealResult());//处理结果
                //propertyRectifyCRMEntity.setCreateDate((propertyRectifyDTO.getCreateDate() == null || "".equals(propertyRectifyDTO.getCreateDate())) ? new Date() : DateUtils.parse(propertyRectifyDTO.getCreateDate()));//创建时间
                //propertyRectifyCRMEntity.setModifyDate((propertyRectifyDTO.getModifyDate() == null || "".equals(propertyRectifyDTO.getModifyDate())) ? null : DateUtils.parse(propertyRectifyDTO.getModifyDate()));//修改时间
                propertyRectifyCRMEntity.setCreateDate(new Date());
                propertyRectifyCRMEntity.setModifyDate(new Date());
                propertyRectifyCRMEntity.setCreateBy(propertyRectifyDTO.getCreateBy());//创建人
                propertyRectifyCRMEntity.setCreateByName(userName == null ? "" : userName);//创建人姓名
                propertyRectifyCRMEntity.setCreatePhone(propertyRectifyDTO.getCreatePhone());//创建人联系电话
                propertyRectifyCRMEntity.setPlanType(propertyRectifyDTO.getPlanType());//活动类型
//                propertyRectifyCRMEntity.setRepairManager(propertyRectifyDTO.getRepairManager());//整改负责人
                propertyRectifyCRMEntity.setRepairPhone(propertyRectifyDTO.getRepairPhone());//整改人联系电话
                //propertyRectifyCRMEntity.setDutyTaskDate((propertyRectifyDTO.getRegisterDate() == null || "".equals(propertyRectifyDTO.getRegisterDate())) ? null : DateUtils.parse(propertyRectifyDTO.getRegisterDate()));//接单时间
                propertyRectifyCRMEntity.setLimitDate((propertyRectifyDTO.getLimitDate() == null || "".equals(propertyRectifyDTO.getLimitDate())) ? null : DateUtils.parse(propertyRectifyDTO.getLimitDate()));//整改时限
                propertyRectifyCRMEntity.setxCoordinates((propertyRectifyDTO.getxCoordinates() == null || "".equals(propertyRectifyDTO.getxCoordinates())) ? null : new BigDecimal(propertyRectifyDTO.getxCoordinates()));//X坐标
                propertyRectifyCRMEntity.setyCoordinates((propertyRectifyDTO.getyCoordinates() == null || "".equals(propertyRectifyDTO.getyCoordinates())) ? null : new BigDecimal(propertyRectifyDTO.getyCoordinates()));//Y坐标
                propertyRectifyCRMEntity.setProjectNum(propertyRectifyDTO.getProjectNum());//项目编码
                propertyRectifyCRMEntity.setUpdateFlag(propertyRectifyDTO.getUpdateFlag());//更新标志：新建时为0，更新时为1

                if (updateflag.equals("0")) {
                    propertyRectifyCRMRepository.saveQuestion(propertyRectifyCRMEntity);
                } else {
                    propertyRectifyCRMRepository.update(propertyRectifyCRMEntity);
                }
                idList.add(propertyRectifyDTO);
                if (!"处理中".equals(propertyRectifyCRMEntity.getRectifyState()) && !"已完成".equals(propertyRectifyCRMEntity.getRectifyState())) {
                    //整改单图片
                    propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "5");//先删除
                    List<RectifyImageDTO> qImageList = propertyRectifyDTO.getImageList();
                    if (qImageList != null && !qImageList.isEmpty()) {
                        for (RectifyImageDTO rectifyImageDTO : qImageList) {
                            PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                            propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                            propertyImageEntity.setUploadDate(new Date());//上传日期
                            propertyImageEntity.setImgFkId(propertyRectifyCRMEntity.getRectifyId());//图片外键id
                            propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                            propertyImageEntity.setImageType("5");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                            propertyImageEntity.setState("0");//状态:0为有效；1为无效
                            propertyImageRepository.saveImage(propertyImageEntity);
                        }
                    }
                }
                System.out.println("~~~~整改单分割线1~~~~~~~~~propertyRectifyDTO.getRectifyId():" + propertyRectifyDTO.getRectifyId() + "     ~~~~propertyRectifyCRMEntity.getDepartment():" + propertyRectifyCRMEntity.getDepartment());
                if (!StringUtil.isEmpty(propertyRectifyDTO.getRectifyId()) && !StringUtil.isEmpty(propertyRectifyCRMEntity.getDepartment())) {
                    List<Object> listzu = propertyRepairCRMRepository.getStafidForzhijian(propertyRectifyCRMEntity.getDepartment());
                    List<Object> listnum = propertyRepairCRMRepository.getStafidForNum(propertyRectifyDTO.getRoomNum());
                    System.out.println("~~~~整改单分割线2~~~~~~~~~listnum：" + listnum + "~~~~~~~~~~~~~listzu" + listzu);
                    if (listnum != null && listzu != null) {
                        listzu.retainAll(listnum);
                        System.out.println("~~~~整改单分割线3~~~~~~~~~listzu：" + listzu);
                        if (listzu != null) {
                            for (Object obj : listzu) {//listzu  拥有查看该保修单权限的员工id
                                System.out.println("---------------------------整改单分割线4-------------------------obj:" + obj);
                                List<MessageTokenEntity> MessageTokenlist = propertyRepairCRMRepository.getMessageToken(obj.toString());
                                if (MessageTokenlist != null) {
                                    System.out.println("---------------------------整改单分割线5-------------------------MessageTokenlist.size():" + MessageTokenlist.size());
                                    for (MessageTokenEntity MessageToken : MessageTokenlist) { //2 0IOS 1 AD
                                        System.out.println("---------------------------整改单分割线6-------------------------MessageToken.getMessageTokenNum():" + MessageToken.getMessageTokenNum());
                                        Random random = new Random();
                                        String result = "";
                                        for (int i = 0; i < 6; i++) {
                                            result += random.nextInt(10);
                                        }
                                        String messid = id + "/" + DateUtils.format(new Date(), "yyyyMMddHHmmss" + result);
                                        MessageTargetEntity messageTargetEntity = new MessageTargetEntity();
                                        messageTargetEntity.setUserId(MessageToken.getUserId());
                                        messageTargetEntity.setMessageTargetId(messid);
                                        messageTargetEntity.setMessageDetailId(messid);
                                        messageTargetEntity.setMessageType("1");
                                        messageTargetEntity.setTargetCreateTime(new Date());
                                        messageTargetEntity.setMessagePushStatus("0");
                                        messageTargetEntity.setMessageReadStatus("0");
                                        messageTargetEntity.setMessageDeleteStatue("1");
                                        messageTargetEntity.setMessageTokenNum(MessageToken.getMessageTokenNum());
                                        messageTargetEntity.setUserType(String.valueOf(MessageToken.getMobileType()));
                                        propertyRepairCRMRepository.savemessageTarget(messageTargetEntity);
                                        MessageDetailEntity messageDetailEntity = new MessageDetailEntity();
                                        messageDetailEntity.setMessageDetailId(messid);
                                        messageDetailEntity.setMessageTitle("整改单");
                                        if (updateflag.equals("0")) {
                                            messageDetailEntity.setMessageContent("您有新的整改单信息!");
                                        } else {
                                            messageDetailEntity.setMessageContent("您的整改单信息有更新！");
                                        }
                                        messageDetailEntity.setMessageCreateTime(new Date());
                                        messageDetailEntity.setMessageType("1");
                                        propertyRepairCRMRepository.saveMessageDetail(messageDetailEntity);
                                    }
                                }
                            }
                        }
                    }
                }
                //整改处理图片
                propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "6");//先删除
                List<RectifyImageDTO> repairImageList = propertyRectifyDTO.getReviewImgList();
                if (repairImageList != null && !repairImageList.isEmpty()) {
                    for (RectifyImageDTO rectifyImageDTO : repairImageList) {
                        PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                        propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                        propertyImageEntity.setUploadDate(new Date());//上传日期
                        propertyImageEntity.setImgFkId(propertyRectifyCRMEntity.getRectifyId());//图片外键id
                        propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                        propertyImageEntity.setImageType("6");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                        propertyImageEntity.setState("0");//状态:0为有效；1为无效
                        propertyImageRepository.saveImage(propertyImageEntity);
                    }
                }

                uploadCRM(propertyRectifyCRMEntity);//上传crm

                if (questionList.size() == 1) {//实时保存返回
                    List<Object[]> savedList = propertyRectifyCRMRepository.getQuestionListByRectifyId(propertyRectifyCRMEntity.getRectifyId());
                    if (savedList != null && !savedList.isEmpty()) {
                        Object[] obj = savedList.get(0);
                        PropertyRectifyDTO propertyRectify = new PropertyRectifyDTO();
                        propertyRectify.setRectifyId(obj[0] == null ? "" : obj[0].toString());//整改单号
                        propertyRectify.setDepartment(obj[1] == null ? "" : obj[1].toString());//部门
                        propertyRectify.setRoomId(obj[2] == null ? "" : obj[2].toString());//房间id
                        propertyRectify.setRoomNum(obj[3] == null ? "" : obj[3].toString());//房间编码
                        propertyRectify.setPlanNum(obj[4] == null ? "" : obj[4].toString());//房间计划编码
                        propertyRectify.setAcceptanceDate(obj[5] == null ? "" : DateUtils.format((Date) obj[5]));//内部预验房日期
                        propertyRectify.setProblemType(obj[6] == null ? "" : obj[6].toString());//问题类型
                        propertyRectify.setClassifyOne(obj[7] == null ? "" : obj[7].toString());//一级分类
                        propertyRectify.setClassifyTwo(obj[8] == null ? "" : obj[8].toString());//二级分类
                        propertyRectify.setClassifyThree(obj[9] == null ? "" : obj[9].toString());//三级分类
                        propertyRectify.setRegisterDate(obj[10] == null ? "" : DateUtils.format((Date) obj[10]));//登记日期
                        propertyRectify.setRectifyState(obj[11] == null ? "" : obj[11].toString());//整改状态
                        propertyRectify.setRoomLocation(obj[12] == null ? "" : obj[12].toString());//房屋位置
                        propertyRectify.setSupplier(obj[13] == null ? "" : obj[13].toString());//供应商
                        propertyRectify.setRectifyCompleteDate(obj[14] == null ? "" : DateUtils.format((Date) obj[14]));//整改完成时间
                        propertyRectify.setRealityDate(obj[15] == null ? "" : DateUtils.format((Date) obj[15]));//实际完成时间
                        propertyRectify.setProblemDescription(obj[16] == null ? "" : obj[16].toString());//问题描述
                        propertyRectify.setDealResult(obj[17] == null ? "" : obj[17].toString());//处理结果
                        propertyRectify.setCreateDate(obj[18] == null ? "" : DateUtils.format((Date) obj[18]));//创建时间
                        propertyRectify.setModifyDate(obj[19] == null ? "" : DateUtils.format((Date) obj[19]));//修改时间
                        propertyRectify.setCreateBy(obj[20] == null ? "" : obj[20].toString());//创建人编码
                        propertyRectify.setCreatePhone(obj[21] == null ? "" : obj[21].toString());//创建人联系电话
                        propertyRectify.setPlanType(obj[22] == null ? "" : obj[22].toString());//活动类型
                        propertyRectify.setRepairManager(obj[23] == null ? "" : obj[23].toString());//整改负责人ID
                        propertyRectify.setRepairPhone(obj[24] == null ? "" : obj[24].toString());//整改人联系电话
                        propertyRectify.setDutyTaskDate(obj[25] == null ? "" : DateUtils.format((Date) obj[25]));//接单时间
                        propertyRectify.setLimitDate(obj[26] == null ? "" : DateUtils.format((Date) obj[26], "yyyy-MM-dd"));//整改时限
                        propertyRectify.setxCoordinates(obj[27] == null ? "" : obj[27].toString());//X坐标
                        propertyRectify.setyCoordinates(obj[28] == null ? "" : obj[28].toString());//Y坐标
                        propertyRectify.setProjectNum(obj[29] == null ? "" : obj[29].toString());//项目编码
                        propertyRectify.setProjectName(obj[30] == null ? "" : obj[30].toString());//项目名称
                        propertyRectify.setCreateName(obj[31] == null ? "" : obj[31].toString());//创建人名称
                        propertyRectify.setAddress(obj[32] == null ? "" : obj[32].toString());//房屋地址
                        propertyRectify.setRepairDescription(obj[33] == null ? "" : obj[33].toString());//整改记录描述
                        propertyRectify.setUpdateFlag(obj[34] == null ? "" : obj[34].toString());//整改记录描述
                        propertyRectify.setLocationName(obj[36] == null ? "" : obj[36].toString());//问题位置名称
                        propertyRectify.setUnitNum(obj[37] == null ? "" : obj[37].toString());//单元编码
                        propertyRectify.setImageList(propertyRectifyDTO.getImageList());
                        propertyRectify.setReviewImgList(propertyRectifyDTO.getReviewImgList());
                        list.add(propertyRectify);
                    }
                }

            }
        }
        questionDTO.setList(list);
        questionDTO.setIdList(idList);
        return questionDTO;
    }
    public PropertyRectifyDTO getRectifyByid(String rectifyId){
        List<Object[]> savedList = propertyRectifyCRMRepository.getQuestionListByRectifyId(rectifyId);
        PropertyRectifyDTO propertyRectify = new PropertyRectifyDTO();
        if (savedList != null && !savedList.isEmpty()) {
            Object[] obj = savedList.get(0);
            propertyRectify.setRectifyId(obj[0] == null ? "" : obj[0].toString());//整改单号
            propertyRectify.setDepartment(obj[1] == null ? "" : obj[1].toString());//部门
            propertyRectify.setRoomId(obj[2] == null ? "" : obj[2].toString());//房间id
            propertyRectify.setRoomNum(obj[3] == null ? "" : obj[3].toString());//房间编码
            propertyRectify.setPlanNum(obj[4] == null ? "" : obj[4].toString());//房间计划编码
            propertyRectify.setAcceptanceDate(obj[5] == null ? "" : DateUtils.format((Date) obj[5]));//内部预验房日期
            propertyRectify.setProblemType(obj[6] == null ? "" : obj[6].toString());//问题类型
            propertyRectify.setClassifyOne(obj[7] == null ? "" : obj[7].toString());//一级分类
            propertyRectify.setClassifyTwo(obj[8] == null ? "" : obj[8].toString());//二级分类
            propertyRectify.setClassifyThree(obj[9] == null ? "" : obj[9].toString());//三级分类
            propertyRectify.setRegisterDate(obj[10] == null ? "" : DateUtils.format((Date) obj[10]));//登记日期
            propertyRectify.setRectifyState(obj[11] == null ? "" : obj[11].toString());//整改状态
            propertyRectify.setRoomLocation(obj[12] == null ? "" : obj[12].toString());//房屋位置
            propertyRectify.setSupplier(obj[13] == null ? "" : obj[13].toString());//供应商
            propertyRectify.setRectifyCompleteDate(obj[14] == null ? "" : DateUtils.format((Date) obj[14]));//整改完成时间
            propertyRectify.setRealityDate(obj[15] == null ? "" : DateUtils.format((Date) obj[15]));//实际完成时间
            propertyRectify.setProblemDescription(obj[16] == null ? "" : obj[16].toString());//问题描述
            propertyRectify.setDealResult(obj[17] == null ? "" : obj[17].toString());//处理结果
            propertyRectify.setCreateDate(obj[18] == null ? "" : DateUtils.format((Date) obj[18]));//创建时间
            propertyRectify.setModifyDate(obj[19] == null ? "" : DateUtils.format((Date) obj[19]));//修改时间
            propertyRectify.setCreateBy(obj[20] == null ? "" : obj[20].toString());//创建人编码
            propertyRectify.setCreatePhone(obj[21] == null ? "" : obj[21].toString());//创建人联系电话
            propertyRectify.setPlanType(obj[22] == null ? "" : obj[22].toString());//活动类型
            propertyRectify.setRepairManager(obj[23] == null ? "" : obj[23].toString());//整改负责人ID
            propertyRectify.setRepairPhone(obj[24] == null ? "" : obj[24].toString());//整改人联系电话
            propertyRectify.setDutyTaskDate(obj[25] == null ? "" : DateUtils.format((Date) obj[25]));//接单时间
            propertyRectify.setLimitDate(obj[26] == null ? "" : DateUtils.format((Date) obj[26], "yyyy-MM-dd"));//整改时限
            propertyRectify.setxCoordinates(obj[27] == null ? "" : obj[27].toString());//X坐标
            propertyRectify.setyCoordinates(obj[28] == null ? "" : obj[28].toString());//Y坐标
            propertyRectify.setProjectNum(obj[29] == null ? "" : obj[29].toString());//项目编码
            propertyRectify.setProjectName(obj[30] == null ? "" : obj[30].toString());//项目名称
            propertyRectify.setCreateName(obj[31] == null ? "" : obj[31].toString());//创建人名称
            propertyRectify.setAddress(obj[32] == null ? "" : obj[32].toString());//房屋地址
            propertyRectify.setRepairDescription(obj[33] == null ? "" : obj[33].toString());//整改记录描述
            propertyRectify.setUpdateFlag(obj[34] == null ? "" : obj[34].toString());//整改记录描述
            propertyRectify.setLocationName(obj[36] == null ? "" : obj[36].toString());//问题位置名称
            propertyRectify.setUnitNum(obj[37] == null ? "" : obj[37].toString());//单元编码
            propertyRectify.setSerialNumber(obj[38] == null ? "" : obj[38].toString());
//            propertyRectify.setImageList(propertyRectifyDTO.getImageList());
//            propertyRectify.setReviewImgList(propertyRectifyDTO.getReviewImgList());
        }
        return propertyRectify;
    }
    @Override
    public QuestionDTO saveQuestionNew(List<PropertyRectifyDTO> questionList, String userName) {
        List<PropertyRectifyDTO> list = new ArrayList<PropertyRectifyDTO>(); //返回   APP数据
        String updateflag = "1";
        QuestionDTO questionDTO = new QuestionDTO();    //返回app实体
        List<PropertyRectifyDTO> idList = new ArrayList<PropertyRectifyDTO>();
        if (questionList != null && !questionList.isEmpty()) {
            for (PropertyRectifyDTO propertyRectifyDTO : questionList) {
                PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(propertyRectifyDTO.getRectifyId());
                List<OrganizeEntity> orgList = organizeRepository.getOrganizeInProjectNum(propertyRectifyDTO.getProjectNum());
                String rectifyId = "";
                if(propertyRectifyCRMEntity!=null){
                    updateflag = "1";
                }else{
                    PropertyRectifyCRMEntity rectifyCRMByAppId = propertyRectifyCRMRepository.getByAppId(propertyRectifyDTO.getRectifyId());
                    if(rectifyCRMByAppId!=null){
                        //根据app传入id 查询 rectifyId无数据  查询appId有数据 证明重复提交   调用本地接口
                        PropertyRectifyDTO propertyRectify = getRectifyByid(rectifyCRMByAppId.getRectifyId());
                        propertyRectify.setImageList(propertyRectifyDTO.getImageList());
                        propertyRectify.setReviewImgList(propertyRectifyDTO.getReviewImgList());
                        list.add(propertyRectify);
                        continue;
                    }else{
                        propertyRectifyCRMEntity = new PropertyRectifyCRMEntity();
                        String[] ss = propertyRectifyDTO.getProjectNum().split("-");
                        String recifyid = propertyRectifyCRMRepository.getrectifyidNew(ss[ss.length - 1] + "-Z");
                        if (recifyid.length() == 1) {
                            recifyid = "000" + recifyid;
                        }
                        if (recifyid.length() == 2) {
                            recifyid = "00" + recifyid;
                        }
                        if (recifyid.length() == 3) {
                            recifyid = "0" + recifyid;
                        }
                        rectifyId = ss[ss.length - 1] + "-Z-" + DateUtils.getNow("yyyyMMdd") + "-A-" + recifyid;
                        updateflag = "0";

                        propertyRectifyCRMEntity.setRectifyId(rectifyId);
                        propertyRectifyCRMEntity.setAppId(propertyRectifyDTO.getRectifyId());//appid
                        String roomSequence = propertyRectifyCRMRepository.getRoomSequence(propertyRectifyDTO.getRoomNum());
                        propertyRectifyCRMEntity.setSerialNumber(roomSequence);//当前房间序列号
                    }
                }
                // 修改和新增走同set
                propertyRectifyCRMEntity.setDepartment("2");//工程接单权限,即派单部门
                if (propertyRectifyDTO.getReminderTime() != null && !"".equals(propertyRectifyDTO.getReminderTime())) {
                    propertyRectifyCRMEntity.setReminderTime(DateUtils.parse(propertyRectifyDTO.getReminderTime()));
                }
                //如果传入内部负责人则将信息处理存入dealPeopel
                if (propertyRectifyDTO.getRepairManagerId() != null && !"".equals(propertyRectifyDTO.getRepairManagerId())) {
                    propertyRectifyCRMEntity.setDealPeople(propertyRectifyDTO.getRepairManagerId());
                    propertyRectifyCRMEntity.setRepairManagerId(propertyRectifyDTO.getRepairManagerId());
                    UserPropertyStaffEntity UserPropertyStaff = rectificationRepository.getusername(propertyRectifyDTO.getRepairManagerId());
                    if (UserPropertyStaff != null) {
                        if (UserPropertyStaff.getUserName() != null && !"".equals(UserPropertyStaff.getUserName())) {
                            propertyRectifyCRMEntity.setRepairManager(UserPropertyStaff.getUserName());
                        }
                    }
                }
                //如果传入供应商负责人则将信息处理存入dealPeopel
                if (propertyRectifyDTO.getSupplierID() != null && !"".equals(propertyRectifyDTO.getSupplierID())) {
                    propertyRectifyCRMEntity.setSupplierID(propertyRectifyDTO.getSupplierID());
                    propertyRectifyCRMEntity.setDealPeople(propertyRectifyDTO.getSupplierID());
                    UserPropertyStaffEntity UserPropertyStaff = rectificationRepository.getusername(propertyRectifyDTO.getSupplierID());
                    if (UserPropertyStaff != null) {
                        if (UserPropertyStaff.getUserName() != null && !"".equals(UserPropertyStaff.getUserName())) {
                            propertyRectifyCRMEntity.setSupplierName(UserPropertyStaff.getUserName());
                            propertyRectifyCRMEntity.setRepairManager(UserPropertyStaff.getUserName());
                        }
                    }
                }

                propertyRectifyCRMEntity.setRoomId(propertyRectifyDTO.getRoomId());//房间id
                propertyRectifyCRMEntity.setRoomNum(propertyRectifyDTO.getRoomNum());//房间编码
                propertyRectifyCRMEntity.setPlanNum(propertyRectifyDTO.getPlanNum());//房间计划编码
                propertyRectifyCRMEntity.setAcceptanceDate((propertyRectifyDTO.getAcceptanceDate() == null || "".equals(propertyRectifyDTO.getAcceptanceDate())) ? null : DateUtils.parse(propertyRectifyDTO.getAcceptanceDate()));//内部预验房日期
                propertyRectifyCRMEntity.setProblemType(propertyRectifyDTO.getProblemType());//问题类型
                propertyRectifyCRMEntity.setClassifyOne(propertyRectifyDTO.getClassifyOne());//一级分类
                propertyRectifyCRMEntity.setClassifyTwo(propertyRectifyDTO.getClassifyTwo());//二级分类
                propertyRectifyCRMEntity.setClassifyThree(propertyRectifyDTO.getClassifyThree());//三级分类
                propertyRectifyCRMEntity.setRegisterDate(new Date());
                propertyRectifyCRMEntity.setRectifyState(propertyRectifyDTO.getRectifyState());//整改状态
                propertyRectifyCRMEntity.setRoomLocation(propertyRectifyDTO.getRoomLocation());//房屋位置
                propertyRectifyCRMEntity.setSupplier(propertyRectifyDTO.getSupplier());//供应商
                propertyRectifyCRMEntity.setProblemDescription(propertyRectifyDTO.getProblemDescription());//问题描述
                propertyRectifyCRMEntity.setCreateDate(new Date());
                propertyRectifyCRMEntity.setModifyDate(new Date());
                propertyRectifyCRMEntity.setCreateBy(propertyRectifyDTO.getCreateBy());//创建人
                propertyRectifyCRMEntity.setCreateByName(userName == null ? "" : userName);//创建人姓名
                propertyRectifyCRMEntity.setCreatePhone(propertyRectifyDTO.getCreatePhone());//创建人联系电话
                propertyRectifyCRMEntity.setPlanType(propertyRectifyDTO.getPlanType());//活动类型
                propertyRectifyCRMEntity.setRepairPhone(propertyRectifyDTO.getRepairPhone());//整改人联系电话
                propertyRectifyCRMEntity.setLimitDate((propertyRectifyDTO.getLimitDate() == null || "".equals(propertyRectifyDTO.getLimitDate())) ? null : DateUtils.parse(propertyRectifyDTO.getLimitDate()));//整改时限
                propertyRectifyCRMEntity.setxCoordinates((propertyRectifyDTO.getxCoordinates() == null || "".equals(propertyRectifyDTO.getxCoordinates())) ? null : new BigDecimal(propertyRectifyDTO.getxCoordinates()));//X坐标
                propertyRectifyCRMEntity.setyCoordinates((propertyRectifyDTO.getyCoordinates() == null || "".equals(propertyRectifyDTO.getyCoordinates())) ? null : new BigDecimal(propertyRectifyDTO.getyCoordinates()));//Y坐标
                propertyRectifyCRMEntity.setProjectNum(propertyRectifyDTO.getProjectNum());//项目编码
                propertyRectifyCRMEntity.setUpdateFlag(propertyRectifyDTO.getUpdateFlag());//更新标志：新建时为0，更新时为1

                if (!"处理中".equals(propertyRectifyCRMEntity.getRectifyState()) && !"已完成".equals(propertyRectifyCRMEntity.getRectifyState())) {
                    //整改单图片
                    propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "5");//先删除
                    List<RectifyImageDTO> qImageList = propertyRectifyDTO.getImageList();
                    if (qImageList != null && !qImageList.isEmpty()) {
                        for (RectifyImageDTO rectifyImageDTO : qImageList) {
                            PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                            propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                            propertyImageEntity.setUploadDate(new Date());//上传日期
                            propertyImageEntity.setImgFkId(propertyRectifyCRMEntity.getRectifyId());//图片外键id
                            propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                            propertyImageEntity.setImageType("5");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                            propertyImageEntity.setState("0");//状态:0为有效；1为无效
                            propertyImageRepository.saveImage(propertyImageEntity);
                        }
                    }
                }
                //整改处理图片
                propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "6");//先删除
                List<RectifyImageDTO> repairImageList = propertyRectifyDTO.getReviewImgList();
                if (repairImageList != null && !repairImageList.isEmpty()) {
                    for (RectifyImageDTO rectifyImageDTO : repairImageList) {
                        PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                        propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                        propertyImageEntity.setUploadDate(new Date());//上传日期
                        propertyImageEntity.setImgFkId(propertyRectifyCRMEntity.getRectifyId());//图片外键id
                        propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                        propertyImageEntity.setImageType("6");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                        propertyImageEntity.setState("0");//状态:0为有效；1为无效
                        propertyImageRepository.saveImage(propertyImageEntity);
                    }
                }
                 //根据updateflag 判断走新增还是修改方法
                /***
                try{
                    String checkCrmState=uploadCRM(propertyRectifyCRMEntity);//上传crm
                    System.out.print("--------+++++++++++-----checkCrmState----------+++++++"+checkCrmState);
                    if("200".equals(checkCrmState) || "".equals(checkCrmState)){
                        if (updateflag.equals("0")) {
                            propertyRectifyCRMEntity.setFailType("0");//代表成功
                            propertyRectifyCRMEntity.setFailNum(0);
                            propertyRectifyCRMRepository.saveQuestion(propertyRectifyCRMEntity);
                        } else {
                            propertyRectifyCRMEntity.setFailType("0");//代表成功
                            propertyRectifyCRMRepository.update(propertyRectifyCRMEntity);
                        }
                    }else{
                        if (updateflag.equals("0")) {
                            propertyRectifyCRMEntity.setFailType("1");//代表是失败
                            propertyRectifyCRMEntity.setFailNum(1);//第一次失败
                            propertyRectifyCRMRepository.saveQuestion(propertyRectifyCRMEntity);
                        } else {
                            propertyRectifyCRMEntity.setFailType("1");//代表是失败
                            if("null".equals(String.valueOf(propertyRectifyCRMEntity.getFailNum())) || "0".equals(String.valueOf(propertyRectifyCRMEntity.getFailNum()))){
                                propertyRectifyCRMEntity.setFailNum(1);//第N+1次失败
                            }else{
                                propertyRectifyCRMEntity.setFailNum(propertyRectifyCRMEntity.getFailNum()+1);//第N+1次失败
                            }
                            propertyRectifyCRMRepository.update(propertyRectifyCRMEntity);
                        }
                    }

                }catch (Exception e){
                    PropertyRectifyCRMEntity rectifyCRMByAppId = propertyRectifyCRMRepository.getByAppId(propertyRectifyDTO.getRectifyId());
                    if(rectifyCRMByAppId!=null){
                        //根据app传入id 查询 rectifyId无数据  查询appId有数据 证明重复提交   调用本地接口
                        PropertyRectifyDTO propertyRectify = getRectifyByid(rectifyCRMByAppId.getRectifyId());
                        propertyRectify.setImageList(propertyRectifyDTO.getImageList());
                        propertyRectify.setReviewImgList(propertyRectifyDTO.getReviewImgList());
                        list.add(propertyRectify);
                        continue;
                    }else{
                        if (updateflag.equals("0")) {
                            propertyRectifyCRMEntity.setFailType("1");//代表是失败
                            propertyRectifyCRMEntity.setFailNum(1);//第一次失败
                            propertyRectifyCRMRepository.saveQuestion(propertyRectifyCRMEntity);
                        } else {
                            propertyRectifyCRMEntity.setFailType("1");//代表是失败
                            if("null".equals(String.valueOf(propertyRectifyCRMEntity.getFailNum())) || "0".equals(String.valueOf(propertyRectifyCRMEntity.getFailNum()))){
                                propertyRectifyCRMEntity.setFailNum(1);//第N+1次失败
                            }else{
                                propertyRectifyCRMEntity.setFailNum(propertyRectifyCRMEntity.getFailNum()+1);//第N+1次失败
                            }
                            propertyRectifyCRMRepository.update(propertyRectifyCRMEntity);
                        }
                    }

                }*/
                // 先存入本地在上传给crm  防止重复数据和本地未存却上传给crm
                try{
                    if (updateflag.equals("0")) {
                        propertyRectifyCRMEntity.setFailType("0");//代表成功
                        propertyRectifyCRMEntity.setFailNum(0);
                        propertyRectifyCRMRepository.saveQuestion(propertyRectifyCRMEntity);
                        String checkCrmState=uploadCRM(propertyRectifyCRMEntity);//上传crm
                        if(!"200".equals(checkCrmState) && !"".equals(checkCrmState)){
                            propertyRectifyCRMEntity.setFailType("1");//代表是失败
                            propertyRectifyCRMEntity.setFailNum(1);//第一次失败
                            propertyRectifyCRMRepository.update(propertyRectifyCRMEntity);
                        }
                    } else {
                        propertyRectifyCRMEntity.setFailType("0");//代表成功
                        propertyRectifyCRMRepository.update(propertyRectifyCRMEntity);
                        String checkCrmState=uploadCRM(propertyRectifyCRMEntity);//上传crm
//                        propertyRectifyCRMEntity.setFailType("1");//代表是失败
                        if(!"200".equals(checkCrmState) && !"".equals(checkCrmState)) {
                            if ("null".equals(String.valueOf(propertyRectifyCRMEntity.getFailNum())) || "0".equals(String.valueOf(propertyRectifyCRMEntity.getFailNum()))) {
                                propertyRectifyCRMEntity.setFailNum(1);//第N+1次失败
                            } else {
                                propertyRectifyCRMEntity.setFailNum(propertyRectifyCRMEntity.getFailNum() + 1);//第N+1次失败
                            }
                            propertyRectifyCRMRepository.update(propertyRectifyCRMEntity);
                        }
                    }
                }catch (Exception e){
                    PropertyRectifyCRMEntity rectifyCRMByAppId = propertyRectifyCRMRepository.getByAppId(propertyRectifyDTO.getRectifyId());
                    if(rectifyCRMByAppId!=null){
                        //根据app传入id 查询 rectifyId无数据  查询appId有数据 证明重复提交   调用本地接口
                        PropertyRectifyDTO propertyRectify = getRectifyByid(rectifyCRMByAppId.getRectifyId());
                        propertyRectify.setImageList(propertyRectifyDTO.getImageList());
                        propertyRectify.setReviewImgList(propertyRectifyDTO.getReviewImgList());
                        list.add(propertyRectify);
                        continue;
                    }else{
                        if (updateflag.equals("0")) {
                            propertyRectifyCRMEntity.setFailType("1");//代表是失败
                            propertyRectifyCRMEntity.setFailNum(1);//第一次失败
                            propertyRectifyCRMRepository.saveQuestion(propertyRectifyCRMEntity);
                        } else {
                            propertyRectifyCRMEntity.setFailType("1");//代表是失败
                            if("null".equals(String.valueOf(propertyRectifyCRMEntity.getFailNum())) || "0".equals(String.valueOf(propertyRectifyCRMEntity.getFailNum()))){
                                propertyRectifyCRMEntity.setFailNum(1);//第N+1次失败
                            }else{
                                propertyRectifyCRMEntity.setFailNum(propertyRectifyCRMEntity.getFailNum()+1);//第N+1次失败
                            }
                            propertyRectifyCRMRepository.update(propertyRectifyCRMEntity);
                        }
                    }
                }

                idList.add(propertyRectifyDTO);
                PropertyRectifyDTO propertyRectify = getRectifyByid(propertyRectifyCRMEntity.getRectifyId());
                propertyRectify.setImageList(propertyRectifyDTO.getImageList());
                propertyRectify.setReviewImgList(propertyRectifyDTO.getReviewImgList());
                list.add(propertyRectify);
            }
        }
        questionDTO.setList(list);
        questionDTO.setIdList(idList);
        return questionDTO;
    }

    /**
     * 整改单上传crm
     */
    private String uploadCRM(PropertyRectifyCRMEntity propertyRectifyCRMEntity) {
        if (propertyRectifyCRMEntity.getRectifyState() != null && "草稿".equals(propertyRectifyCRMEntity.getRectifyState())) {
            return "";
        }
        //上传crm，新增或者修改都调用同一个接口
        PropertyRectifyCRMEntity propertyRectify = null;//整改单
        PropertyRepairCRMEntity propertyRepair = null;//保修单
        propertyRectify = new PropertyRectifyCRMEntity();
        propertyRectify.setRectifyId(propertyRectifyCRMEntity.getRectifyId());//整改单号
        propertyRectify.setDepartment(propertyRectifyCRMEntity.getDepartment());//部门
        propertyRectify.setRoomId(propertyRectifyCRMEntity.getRoomId());//房间id
        propertyRectify.setRoomNum(propertyRectifyCRMEntity.getRoomNum());//房间编码
        propertyRectify.setPlanNum(propertyRectifyCRMEntity.getPlanNum());//房间计划编码
        propertyRectify.setAcceptanceDate(propertyRectifyCRMEntity.getAcceptanceDate());//内部预验房日期
        propertyRectify.setProblemType(propertyRectifyCRMEntity.getProblemType());//问题类型
        propertyRectify.setClassifyOne(propertyRectifyCRMEntity.getClassifyOne());//一级分类
        propertyRectify.setClassifyTwo(propertyRectifyCRMEntity.getClassifyTwo());//二级分类
        propertyRectify.setClassifyThree(propertyRectifyCRMEntity.getClassifyThree());//三级分类
        propertyRectify.setCreateDate(propertyRectifyCRMEntity.getCreateDate());//登记日期
        if ("已完成".equals(propertyRectifyCRMEntity.getRectifyState())) {
            propertyRectify.setRectifyState("已完成");
        } else if ("已废弃".equals(propertyRectifyCRMEntity.getRectifyState())) {
            propertyRectify.setRectifyState("已废弃");
        } else if ("处理中".equals(propertyRectifyCRMEntity.getRectifyState())){
            propertyRectify.setRectifyState("处理中");
        } else if ("强制关闭".equals(propertyRectifyCRMEntity.getRectifyState())){
            propertyRectify.setRectifyState("强制关闭");
        } else{
            propertyRectify.setRectifyState("开始");
        }
        propertyRectify.setRoomLocation(propertyRectifyCRMEntity.getRoomLocation());//房屋位置
        propertyRectify.setSupplier(propertyRectifyCRMEntity.getSupplier());//供应商
        propertyRectify.setRectifyCompleteDate(propertyRectifyCRMEntity.getRectifyCompleteDate());//整改完成时间
        propertyRectify.setRealityDate(propertyRectifyCRMEntity.getRealityDate());//实际完成时间
        propertyRectify.setProblemDescription(propertyRectifyCRMEntity.getProblemDescription());//问题描述
        propertyRectify.setDealResult(propertyRectifyCRMEntity.getDealResult());//处理结果
        propertyRectify.setCreateDate(propertyRectifyCRMEntity.getCreateDate());//创建时间
        propertyRectify.setModifyDate(propertyRectifyCRMEntity.getModifyDate());//修改时间
//        propertyRectify.setCreateBy(propertyRectifyCRMEntity.getCreateBy());//创建人
        if (!StringUtil.isEmpty(propertyRectifyCRMEntity.getRepairManagerId())) {
            UserPropertyStaffEntity UserPropertyStaff = rectificationRepository.getusername(propertyRectifyCRMEntity.getRepairManagerId());
            if (UserPropertyStaff != null) {
                if (!StringUtil.isEmpty(UserPropertyStaff.getUserName())) {
                    propertyRectify.setRepairManager(UserPropertyStaff.getStaffName());
                }
            }
        }
        if (!StringUtil.isEmpty(propertyRectifyCRMEntity.getSupplierID())) {
            UserPropertyStaffEntity UserPropertyStaff = rectificationRepository.getusername(propertyRectifyCRMEntity.getSupplierID());
            if (UserPropertyStaff != null) {
                if (!StringUtil.isEmpty(UserPropertyStaff.getUserName())) {
                    propertyRectify.setSupplierName(UserPropertyStaff.getStaffName());
                    propertyRectify.setRepairPhone(UserPropertyStaff.getMobile());
                }
            }
        }
        propertyRectify.setCreateByName(propertyRectifyCRMEntity.getCreateByName());//创建人姓名
        propertyRectify.setUpdateUserName(propertyRectifyCRMEntity.getUpdateUserName());//填报人姓名
        propertyRectify.setDealPeople(propertyRectifyCRMEntity.getDealPeople());
        propertyRectify.setLimitDate(propertyRectifyCRMEntity.getLimitDate());//整改时间
        return repairClientService.getPropertyRepair(propertyRepair, propertyRectify);
    }

    @Override
    public ApiResult orderQuestion(WorkApportionDTO workApportionDTO, UserPropertyStaffEntity user) {
        if (workApportionDTO != null) {
            PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(workApportionDTO.getId());
            if (propertyRectifyCRMEntity != null) {
                if (propertyRectifyCRMEntity.getDealPeople() != null && !"".equals(propertyRectifyCRMEntity.getDealPeople())) {
                    if (!propertyRectifyCRMEntity.getDealPeople().equals(user.getStaffId())) {
                        return ErrorResource.getError("tip_pe00000035");
                    }
                }
                if (!"待接单".equals(propertyRectifyCRMEntity.getRectifyState())) {
                    return ErrorResource.getError("tip_pe00000035");
                }
                if (propertyRectifyCRMEntity.getSupplierID() != null && !"".equals(propertyRectifyCRMEntity.getSupplierID())) {
                    propertyRectifyCRMEntity.setDealPeople(user.getStaffId());
                } else {
                    propertyRectifyCRMEntity.setRepairManagerId(user.getStaffId());
                    propertyRectifyCRMEntity.setRepairManager(user.getStaffName());
                }
                propertyRectifyCRMEntity.setClassifyOne(workApportionDTO.getClassifyOne());
                propertyRectifyCRMEntity.setClassifyTwo(workApportionDTO.getClassifyTwo());
                propertyRectifyCRMEntity.setClassifyThree(workApportionDTO.getClassifyThree());
                propertyRectifyCRMEntity.setRepairManager(user.getStaffName());
                propertyRectifyCRMEntity.setDealPeople(user.getStaffId());
                propertyRectifyCRMEntity.setRepairPhone(user.getMobile());
                propertyRectifyCRMEntity.setDutyTaskDate(new Date());
                propertyRectifyCRMEntity.setRectifyState("处理中");
                propertyRectifyCRMEntity.setModifyDate(new Date());
                propertyRectifyCRMEntity.setUpdateFlag("1");//修改标志
                propertyRectifyCRMEntity.setSupplier(workApportionDTO.getSupplier());//供应商
                propertyRectifyCRMEntity.setLimitDate(DateUtils.parse(workApportionDTO.getLimitDate()));
                propertyRectifyCRMEntity.setProblemDescription(workApportionDTO.getContent());//问题描述
                propertyRectifyCRMEntity.setDepartment(workApportionDTO.getDepartmentId());
                propertyRectifyCRMEntity.setProblemType(workApportionDTO.getProblemType());
                propertyRectifyCRMEntity.setDealPeopleName(user.getStaffName());//处理人姓名


                propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);

                //整改单图片
                List<RectifyImageDTO> qImageList = workApportionDTO.getRectifyImgList();
                if (qImageList != null && !qImageList.isEmpty()) {
                    propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "5");//先删除
                    for (RectifyImageDTO rectifyImageDTO : qImageList) {
                        PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                        propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                        propertyImageEntity.setUploadDate(new Date());//上传日期
                        propertyImageEntity.setImgFkId(rectifyImageDTO.getCaseId());//图片外键id
                        propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                        propertyImageEntity.setImageType("5");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                        propertyImageEntity.setState("0");//状态:0为有效；1为无效
                        propertyImageRepository.saveImage(propertyImageEntity);
                    }
                }

                //整改处理图片
                List<RectifyImageDTO> repairImageList = workApportionDTO.getImgList();
                if (repairImageList != null && !repairImageList.isEmpty()) {
                    propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "6");//先删除
                    for (RectifyImageDTO rectifyImageDTO : repairImageList) {
                        PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                        propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                        propertyImageEntity.setUploadDate(new Date());//上传日期
                        propertyImageEntity.setImgFkId(rectifyImageDTO.getCaseId());//图片外键id
                        propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                        propertyImageEntity.setImageType("6");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                        propertyImageEntity.setState("0");//状态:0为有效；1为无效
                        propertyImageRepository.saveImage(propertyImageEntity);
                    }
                }

                uploadCRM(propertyRectifyCRMEntity);//上传crm
                return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), "1");
            } else {
                return new SuccessApiResult(SuccessResource.getResource("tip_pe00000035"), "0");
            }
        } else {
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000035"), "0");

        }
    }

    @Override
    public ReturnJsonDTO recigfyRepaire(List<WorkApportionDTO> workApportionDTOList, UserPropertyStaffEntity user) {
        ReturnJsonDTO returnJsonDTO = new ReturnJsonDTO();
        List<String> successList = new ArrayList<String>();
        List<String> failList = new ArrayList<String>();
        if (workApportionDTOList != null && !workApportionDTOList.isEmpty()) {
            for (WorkApportionDTO workApportionDTO : workApportionDTOList) {
                PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(workApportionDTO.getRepairId());
                if (propertyRectifyCRMEntity != null) {
                    if (propertyRectifyCRMEntity.getModifyDate().compareTo(DateUtils.parse(workApportionDTO.getUptime())) > 0) {
                        failList.add(propertyRectifyCRMEntity.getRectifyId());
                        continue;
                    }
                    propertyRectifyCRMEntity.setClassifyOne(workApportionDTO.getClassifyOne());
                    propertyRectifyCRMEntity.setClassifyTwo(workApportionDTO.getClassifyTwo());
                    propertyRectifyCRMEntity.setClassifyThree(workApportionDTO.getClassifyThree());
                    propertyRectifyCRMEntity.setRectifyState(workApportionDTO.getState());
                    propertyRectifyCRMEntity.setModifyDate(new Date());
                    propertyRectifyCRMEntity.setUpdateFlag("1");//修改标志reminderTime
                    propertyRectifyCRMEntity.setReminderTime(DateUtils.parse(workApportionDTO.getReminderTime()));
                    propertyRectifyCRMEntity.setSupplier(workApportionDTO.getSupplier());//供应商
                    propertyRectifyCRMEntity.setLimitDate(DateUtils.parse(workApportionDTO.getLimitDate()));//限时
                    propertyRectifyCRMEntity.setProblemDescription(workApportionDTO.getContent());//问题描述
                    //propertyRectifyCRMEntity.setDepartment(workApportionDTO.getDepartmentId());
                    propertyRectifyCRMEntity.setProblemType(workApportionDTO.getProblemType());//问题类型
                    propertyRectifyCRMEntity.setDealResult(workApportionDTO.getDealResult());//处理结果,整改描述

                    if ("已完成".equals(workApportionDTO.getState())) {
                        propertyRectifyCRMEntity.setRectifyCompleteDate(new Date());//整改完成时间
                        propertyRectifyCRMEntity.setRealityDate(new Date());

                    }
                    propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);
                    successList.add(propertyRectifyCRMEntity.getRectifyId());

                    //整改单图片
                    List<RectifyImageDTO> qImageList = workApportionDTO.getRectifyImgList();
                    if (qImageList != null && !qImageList.isEmpty()) {
                        propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "5");//先删除
                        for (RectifyImageDTO rectifyImageDTO : qImageList) {
                            PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                            propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                            propertyImageEntity.setUploadDate(new Date());//上传日期
                            propertyImageEntity.setImgFkId(rectifyImageDTO.getCaseId());//图片外键id
                            propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                            propertyImageEntity.setImageType("5");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                            propertyImageEntity.setState("0");//状态:0为有效；1为无效
                            propertyImageRepository.saveImage(propertyImageEntity);
                        }
                    }

                    //整改处理图片
                    List<RectifyImageDTO> repairImageList = workApportionDTO.getImgList();
                    if (repairImageList != null && !repairImageList.isEmpty()) {
                        propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "6");//先删除
                        for (RectifyImageDTO rectifyImageDTO : repairImageList) {
                            PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                            propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                            propertyImageEntity.setUploadDate(new Date());//上传日期
                            propertyImageEntity.setImgFkId(rectifyImageDTO.getCaseId());//图片外键id
                            propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                            propertyImageEntity.setImageType("6");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                            propertyImageEntity.setState("0");//状态:0为有效；1为无效
                            propertyImageRepository.saveImage(propertyImageEntity);
                        }
                    }

//                    if("已完成".equals(workApportionDTO.getState())) {
                    uploadCRM(propertyRectifyCRMEntity);//上传crm
//                    }

                }
            }
        }
        returnJsonDTO.setFail(failList);
        returnJsonDTO.setSuccess(successList);
        return returnJsonDTO;
    }

    @Override
    public ReturnJsonDTO recigfyRepaired(List<WorkApportionDTO> workApportionDTOList, UserPropertyStaffEntity user) {
        ReturnJsonDTO returnJsonDTO = new ReturnJsonDTO();
        List<String> successList = new ArrayList<String>();
        List<String> failList = new ArrayList<String>();
        if (workApportionDTOList != null && !workApportionDTOList.isEmpty()) {
            for (WorkApportionDTO workApportionDTO : workApportionDTOList) {
                PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(workApportionDTO.getId());
                if (propertyRectifyCRMEntity != null) {
                    if (propertyRectifyCRMEntity.getModifyDate().compareTo(DateUtils.parse(workApportionDTO.getUptime())) > 0) {
                        failList.add(propertyRectifyCRMEntity.getRectifyId());
                        continue;
                    }
                    propertyRectifyCRMEntity.setClassifyOne(workApportionDTO.getClassifyOne());
                    propertyRectifyCRMEntity.setClassifyTwo(workApportionDTO.getClassifyTwo());
                    propertyRectifyCRMEntity.setClassifyThree(workApportionDTO.getClassifyThree());
                    propertyRectifyCRMEntity.setRepairManager(user.getStaffId());
                    propertyRectifyCRMEntity.setDealPeople(user.getStaffId());
                    propertyRectifyCRMEntity.setRepairPhone(user.getMobile());
                    propertyRectifyCRMEntity.setDutyTaskDate(new Date());
                    propertyRectifyCRMEntity.setRectifyState(workApportionDTO.getState());
                    propertyRectifyCRMEntity.setModifyDate(new Date());
                    propertyRectifyCRMEntity.setUpdateFlag("1");//修改标志
                    propertyRectifyCRMEntity.setSupplier(workApportionDTO.getSupplier());//供应商
                    propertyRectifyCRMEntity.setLimitDate(DateUtils.parse(workApportionDTO.getLimitDate()));//限时
                    propertyRectifyCRMEntity.setDealResult(workApportionDTO.getDealResult());//处理结果
                    propertyRectifyCRMEntity.setProblemDescription(workApportionDTO.getContent());//问题描述
                    //propertyRectifyCRMEntity.setDepartment(workApportionDTO.getDepartmentId());
                    propertyRectifyCRMEntity.setProblemType(workApportionDTO.getProblemType());//问题类型
                    propertyRectifyCRMEntity.setRectifyCompleteDate(new Date());//整改完成时间
                    propertyRectifyCRMEntity.setRealityDate(new Date());


                    propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);

                    successList.add(propertyRectifyCRMEntity.getRectifyId());

                    //整改单图片
                    List<RectifyImageDTO> qImageList = workApportionDTO.getRectifyImgList();
                    propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "5");//先删除
                    if (qImageList != null && !qImageList.isEmpty()) {

                        for (RectifyImageDTO rectifyImageDTO : qImageList) {
                            PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                            propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                            propertyImageEntity.setUploadDate(new Date());//上传日期
                            propertyImageEntity.setImgFkId(rectifyImageDTO.getCaseId());//图片外键id
                            propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                            propertyImageEntity.setImageType("5");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                            propertyImageEntity.setState("0");//状态:0为有效；1为无效
                            propertyImageRepository.saveImage(propertyImageEntity);
                        }
                    }

                    //整改处理图片
                    List<RectifyImageDTO> repairImageList = workApportionDTO.getImgList();
                    propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "6");//先删除
                    if (repairImageList != null && !repairImageList.isEmpty()) {

                        for (RectifyImageDTO rectifyImageDTO : repairImageList) {
                            PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                            propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                            propertyImageEntity.setUploadDate(new Date());//上传日期
                            propertyImageEntity.setImgFkId(rectifyImageDTO.getCaseId());//图片外键id
                            propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                            propertyImageEntity.setImageType("6");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                            propertyImageEntity.setState("0");//状态:0为有效；1为无效
                            propertyImageRepository.saveImage(propertyImageEntity);
                        }
                    }
                    uploadCRM(propertyRectifyCRMEntity);//上传crm
                }
            }
        }
        returnJsonDTO.setFail(failList);
        returnJsonDTO.setSuccess(successList);
        return returnJsonDTO;
    }

    @Override
    public List<PropertyRectifyCRMListDTO> getQuestionList(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage) {
        Map map = new HashMap();
        //保证URL和表单提交的数据一致
        String area=propertyRectifyCRMSelDTO.getArea();
        if (area != null && !area.equals("") && area.contains("%23")) {
            area = area.replaceAll("%23", "#");
        }
        String roomId = propertyRectifyCRMSelDTO.getRoomId();
        if (roomId != null && !roomId.equals("") && roomId.contains("%23")) {
            roomId = roomId.replaceAll("%23", "#");
        }
        String buildingId=propertyRectifyCRMSelDTO.getBuildingId();
        if (buildingId != null && !buildingId.equals("") && buildingId.contains("%23")) {
            buildingId = buildingId.replaceAll("%23", "#");
        }
        String unitId=propertyRectifyCRMSelDTO.getUnitId();
        if (unitId != null && !unitId.equals("") && unitId.contains("%23")) {
            unitId = unitId.replaceAll("%23", "#");
        }
        String floorId=propertyRectifyCRMSelDTO.getFloorId();
        if (floorId != null && !floorId.equals("") && floorId.contains("%23")) {
            floorId = floorId.replaceAll("%23", "#");
        }

        map.put("projectId", propertyRectifyCRMSelDTO.getProjectId());
        map.put("proType", propertyRectifyCRMSelDTO.getProType());
        map.put("oneType", propertyRectifyCRMSelDTO.getOneType());
        map.put("twoType", propertyRectifyCRMSelDTO.getTwoType());
        map.put("threeType", propertyRectifyCRMSelDTO.getThreeType());
        map.put("caseState", propertyRectifyCRMSelDTO.getCaseState());
        map.put("area",area);
        map.put("buildingId", buildingId);
        map.put("unitId", unitId);
        map.put("floorId", floorId);
        map.put("roomId", roomId);
        map.put("startDate", propertyRectifyCRMSelDTO.getStartDate());
        map.put("endDate", propertyRectifyCRMSelDTO.getEndDate());
        map.put("problemDesc", propertyRectifyCRMSelDTO.getProblemDesc());
        map.put("userProject", propertyRectifyCRMSelDTO.getUserProject());
        map.put("supplier", propertyRectifyCRMSelDTO.getSupplier());
        map.put("planNum",propertyRectifyCRMSelDTO.getPlanNum());
        map.put("createByName", "");
        map.put("dealPeopleName", "");
        map.put("sendUserName", "");
        map.put("updateUserName", "");
        map.put("bewrite", "");
        map.put("rectifyId", "");
        map.put("successOrFailure", "0");//调用crm是否成功

        if(!"".equals(propertyRectifyCRMSelDTO.getSuccessOrFailure()) && propertyRectifyCRMSelDTO.getSuccessOrFailure()!=null){
            map.put("successOrFailure", propertyRectifyCRMSelDTO.getSuccessOrFailure());//调用crm是否成功
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getCreateByName()) && propertyRectifyCRMSelDTO.getCreateByName() != null) {
            map.put("createByName", "%" + propertyRectifyCRMSelDTO.getCreateByName() + "%");
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getDealPeopleName()) && propertyRectifyCRMSelDTO.getDealPeopleName() != null) {
            map.put("dealPeopleName", "%" + propertyRectifyCRMSelDTO.getDealPeopleName() + "%");
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getSenUserName()) && propertyRectifyCRMSelDTO.getSenUserName() != null) {
            map.put("sendUserName", "%" + propertyRectifyCRMSelDTO.getSenUserName() + "%");
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getBewrite()) && propertyRectifyCRMSelDTO.getBewrite() != null) {
            map.put("bewrite", "%" + propertyRectifyCRMSelDTO.getBewrite() + "%");
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getUpcloseName()) && propertyRectifyCRMSelDTO.getUpcloseName() != null) {
            map.put("updateUserName", "%" + propertyRectifyCRMSelDTO.getUpcloseName() + "%");
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getRectifyId()) && propertyRectifyCRMSelDTO.getRectifyId() != null) {
            map.put("rectifyId", "%" + propertyRectifyCRMSelDTO.getRectifyId() + "%");
        }
        List<Object[]> list = null;
        if(!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getProjectId())){
            list = propertyRectifyCRMRepository.getQuestionList(map, webPage);
        }
        List<PropertyRectifyCRMListDTO> retList = new ArrayList<PropertyRectifyCRMListDTO>();
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                PropertyRectifyCRMListDTO propertyRectifyCRMListDTO = new PropertyRectifyCRMListDTO();
                propertyRectifyCRMListDTO.setCaseId(obj[0] == null ? "" : obj[0].toString());         //问题ID 主键
                propertyRectifyCRMListDTO.setSetId(obj[1] == null ? "" : obj[1].toString());          //批次ID
                propertyRectifyCRMListDTO.setCaseTitle(obj[2] == null ? "" : obj[2].toString());      //问题标题
                propertyRectifyCRMListDTO.setCasePlace(obj[3] == null ? "" : obj[3].toString());      //问题部位
                if(obj[3] != null){
                    if(!StringUtil.isEmpty(obj[3].toString())){
                        propertyRectifyCRMListDTO.setCasePlace(propertyRectifyCRMRepository.getRoomLocation(obj[3].toString()));
                    }else{
                        propertyRectifyCRMListDTO.setCasePlace("");
                    }
                }else{
                    propertyRectifyCRMListDTO.setCasePlace("");
                }
                propertyRectifyCRMListDTO.setCaseType(obj[4] == null ? "" : obj[4].toString());       //问题类型
                propertyRectifyCRMListDTO.setOneType(obj[5] == null ? "" : obj[5].toString());        //一级分类
                propertyRectifyCRMListDTO.setTwoType(obj[6] == null ? "" : obj[6].toString());        //二级分类
                propertyRectifyCRMListDTO.setThreeType(obj[7] == null ? "" : obj[7].toString());      //三级分类
                propertyRectifyCRMListDTO.setCaseDesc(obj[8] == null ? "" : obj[8].toString());       //问题描述
                propertyRectifyCRMListDTO.setCaseState(obj[9] == null ? "" : obj[9].toString());      //问题状态,------草稿、待接单、处理中、已完成、已废弃
                propertyRectifyCRMListDTO.setRoomId(obj[10] == null ? "" : obj[10].toString());         //房间ID,或者公共区域ID
                propertyRectifyCRMListDTO.setProjectId(obj[11] == null ? "" : obj[11].toString());      //项目名称
                propertyRectifyCRMListDTO.setPlanType(obj[12] == null ? "" : obj[12].toString());         //计划（模块）类型
                propertyRectifyCRMListDTO.setCreateDate(obj[13] == null ? null : (Date) obj[13]);       //创建时间
                propertyRectifyCRMListDTO.setCreateBy(obj[14] == null ? "" : obj[14].toString());       //创建人
                propertyRectifyCRMListDTO.setLimitTime(obj[15] == null ? null : (Date) obj[15]);          //整改时限
                propertyRectifyCRMListDTO.setComments(obj[16] == null ? "" : obj[16].toString());        //批注留言
                propertyRectifyCRMListDTO.setContractor(obj[17] == null ? "" : obj[17].toString());     //承建商（整改单位）
                propertyRectifyCRMListDTO.setModifyBy(obj[18] == null ? "" : obj[18].toString());   // 修改人
                propertyRectifyCRMListDTO.setModifyDate(obj[19] == null ? null : (Date) obj[19]);//修改时间
                propertyRectifyCRMListDTO.setxCoordinates(obj[20] == null ? null : new BigDecimal(obj[20].toString()));//X坐标
                propertyRectifyCRMListDTO.setyCoordinates(obj[21] == null ? null : new BigDecimal(obj[21].toString()));//Y坐标
                propertyRectifyCRMListDTO.setProjectName(obj[22] == null ? "" : obj[22].toString());// 工程名称
                propertyRectifyCRMListDTO.setFirstTypeName(obj[23] == null ? "" : obj[23].toString());// 一级分类
                propertyRectifyCRMListDTO.setSecondTyoeName(obj[24] == null ? "" : obj[24].toString());// 二级分类
                propertyRectifyCRMListDTO.setThirdTypeName(obj[25] == null ? "" : obj[25].toString());// 三级分类
                propertyRectifyCRMListDTO.setBuildingId(obj[26] == null ? "" : obj[26].toString());// 楼栋ID
                propertyRectifyCRMListDTO.setUnitId(obj[27] == null ? "" : obj[27].toString());// 单元Id
                propertyRectifyCRMListDTO.setFloorId(obj[28] == null ? "" : obj[28].toString());// 楼层ID
                propertyRectifyCRMListDTO.setRealityDate(obj[29] == null ? null : (Date) obj[29]);//整改完成期限
                propertyRectifyCRMListDTO.setAddress(obj[30] == null ? "" : obj[30].toString());
                propertyRectifyCRMListDTO.setCreateByName(obj[31] == null ? "" : obj[31].toString());
                if (propertyRectifyCRMListDTO.getLimitTime() != null) {
                    if ((propertyRectifyCRMListDTO.getRealityDate() != null && propertyRectifyCRMListDTO.getLimitTime().after(propertyRectifyCRMListDTO.getRealityDate()))
                            || propertyRectifyCRMListDTO.getLimitTime().before(new Date())) {
                        propertyRectifyCRMListDTO.setIsOverdue("是");
                    } else {
                        propertyRectifyCRMListDTO.setIsOverdue("否");
                    }
                }
                retList.add(propertyRectifyCRMListDTO);
            }
        }
        return retList;
    }

    @Override
    public List<PropertyRectifyCRMListDTO> getQuestionLists(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("userName", propertyRectifyCRMSelDTO.getUserName());


        List<Object[]> list = propertyRectifyCRMRepository.getQuestionLists(map, webPage);
        List<PropertyRectifyCRMListDTO> retList = new ArrayList<PropertyRectifyCRMListDTO>();
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                PropertyRectifyCRMListDTO propertyRectifyCRMListDTO = new PropertyRectifyCRMListDTO();
                propertyRectifyCRMListDTO.setCaseId(obj[0] == null ? "" : obj[0].toString());         //问题ID 主键
                propertyRectifyCRMListDTO.setSetId(obj[1] == null ? "" : obj[1].toString());          //批次ID
                propertyRectifyCRMListDTO.setCaseTitle(obj[2] == null ? "" : obj[2].toString());      //问题标题
                propertyRectifyCRMListDTO.setCasePlace(obj[3] == null ? "" : obj[3].toString());      //问题部位
                propertyRectifyCRMListDTO.setCaseType(obj[4] == null ? "" : obj[4].toString());       //问题类型
                propertyRectifyCRMListDTO.setOneType(obj[5] == null ? "" : obj[5].toString());        //一级分类
                propertyRectifyCRMListDTO.setTwoType(obj[6] == null ? "" : obj[6].toString());        //二级分类
                propertyRectifyCRMListDTO.setThreeType(obj[7] == null ? "" : obj[7].toString());      //三级分类
                propertyRectifyCRMListDTO.setCaseDesc(obj[8] == null ? "" : obj[8].toString());       //问题描述
                propertyRectifyCRMListDTO.setCaseState(obj[9] == null ? "" : obj[9].toString());      //问题状态,------草稿、待接单、处理中、已完成、已废弃
                propertyRectifyCRMListDTO.setRoomId(obj[10] == null ? "" : obj[10].toString());         //房间ID,或者公共区域ID
                propertyRectifyCRMListDTO.setProjectId(obj[11] == null ? "" : obj[11].toString());      //项目名称
                propertyRectifyCRMListDTO.setPlanType(obj[12] == null ? "" : obj[12].toString());         //计划（模块）类型
                propertyRectifyCRMListDTO.setCreateDate(obj[13] == null ? null : (Date) obj[13]);       //创建时间
                propertyRectifyCRMListDTO.setCreateBy(obj[14] == null ? "" : obj[14].toString());       //创建人
                propertyRectifyCRMListDTO.setLimitTime(obj[15] == null ? null : (Date) obj[15]);          //整改时限
                propertyRectifyCRMListDTO.setComments(obj[16] == null ? "" : obj[16].toString());        //批注留言
                propertyRectifyCRMListDTO.setContractor(obj[17] == null ? "" : obj[17].toString());     //承建商（整改单位）
                propertyRectifyCRMListDTO.setModifyBy(obj[18] == null ? "" : obj[18].toString());   // 修改人
                propertyRectifyCRMListDTO.setModifyDate(obj[19] == null ? null : (Date) obj[19]);//修改时间
                propertyRectifyCRMListDTO.setxCoordinates(obj[20] == null ? null : new BigDecimal(obj[20].toString()));//X坐标
                propertyRectifyCRMListDTO.setyCoordinates(obj[21] == null ? null : new BigDecimal(obj[21].toString()));//Y坐标
                propertyRectifyCRMListDTO.setProjectName(obj[22] == null ? "" : obj[22].toString());// 工程名称
                propertyRectifyCRMListDTO.setFirstTypeName(obj[23] == null ? "" : obj[23].toString());// 一级分类
                propertyRectifyCRMListDTO.setSecondTyoeName(obj[24] == null ? "" : obj[24].toString());// 二级分类
                propertyRectifyCRMListDTO.setThirdTypeName(obj[25] == null ? "" : obj[25].toString());// 三级分类
                propertyRectifyCRMListDTO.setBuildingId(obj[26] == null ? "" : obj[26].toString());// 楼栋ID
                propertyRectifyCRMListDTO.setUnitId(obj[27] == null ? "" : obj[27].toString());// 单元Id
                propertyRectifyCRMListDTO.setFloorId(obj[28] == null ? "" : obj[28].toString());// 楼层ID
                propertyRectifyCRMListDTO.setRealityDate(obj[29] == null ? null : (Date) obj[29]);//整改完成期限
                propertyRectifyCRMListDTO.setAddress(obj[30] == null ? "" : obj[30].toString());
                retList.add(propertyRectifyCRMListDTO);
            }
        }
        return retList;
    }

    @Override
    public ReturnJsonDTO updateModifyPe(String id, String repairManager) {
        ReturnJsonDTO returnJsonDTO = new ReturnJsonDTO();
        PropertyRectifyCRMEntity propertyRectifyCRMEntity = new PropertyRectifyCRMEntity();
        propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(id);
        propertyRectifyCRMEntity.setRepairManager(repairManager);
        propertyRectifyCRMEntity.setModifyDate(new Date());
        propertyRectifyCRMRepository.update(propertyRectifyCRMEntity);
        return returnJsonDTO;
    }

    @Override
    public void saveAdminQeustion(PropertyRectifyAdminDTO propertyRectifyAdminDTO, String userName) {
        PropertyRectifyCRMEntity propertyRectifyCRMEntity = new PropertyRectifyCRMEntity();
        List<OrganizeEntity> orgList = organizeRepository.getOrganizeInProjectNum(propertyRectifyAdminDTO.getProjectNum());
        String[] ss = propertyRectifyAdminDTO.getProjectNum().split("-");
        String recifyid = propertyRectifyCRMRepository.getrectifyidNew(ss[ss.length - 1] + "-Z");
        if (recifyid.length() == 1) {
            recifyid = "000" + recifyid;
        }
        if (recifyid.length() == 2) {
            recifyid = "00" + recifyid;
        }
        if (recifyid.length() == 3) {
            recifyid = "0" + recifyid;
        }

        String id = ss[ss.length - 1] + "-Z-" + DateUtils.getNow("yyyyMMdd") + "-A-" + recifyid;
        System.out.println(id + "!~~~~~~~~~~idbianmaxian !!!!!!!!!!!!!!!!!!!~~~~~~~~~");
        propertyRectifyCRMEntity.setRectifyId(id);//整改单号'
//
//        if(orgList!= null && !orgList.isEmpty()){
//            for(OrganizeEntity organizeEntity : orgList){
//                if(organizeEntity.getOrganizeName().indexOf("工程组") != -1){
//                    propertyRectifyCRMEntity.setDepartment(organizeEntity.getOrganizeId());//部门,即派单部门
//                    break;
//                }
//            }
//        }else{
//            propertyRectifyCRMEntity.setDepartment(propertyRectifyAdminDTO.getDepartment());//部门,即派单部门
//        }
        //设置一个默认组
        propertyRectifyCRMEntity.setDepartment("2");//工程接单权限,即派单部门
        String roomSequence = propertyRectifyCRMRepository.getRoomSequence(propertyRectifyAdminDTO.getRoomNum());
        propertyRectifyCRMEntity.setSerialNumber(roomSequence);//当前房间序列号
        propertyRectifyCRMEntity.setRoomId(propertyRectifyAdminDTO.getRoomId());//房间id
        propertyRectifyCRMEntity.setCreateByName(userName == null ? "" : userName);
        propertyRectifyCRMEntity.setRoomNum(propertyRectifyAdminDTO.getRoomNum());//房间编码
        propertyRectifyCRMEntity.setPlanNum(propertyRectifyAdminDTO.getPlanNum());//房间计划编码
        propertyRectifyCRMEntity.setProblemType(propertyRectifyAdminDTO.getProblemType());//问题类型
        propertyRectifyCRMEntity.setClassifyOne(propertyRectifyAdminDTO.getClassifyOne());//一级分类
        propertyRectifyCRMEntity.setClassifyTwo(propertyRectifyAdminDTO.getClassifyTwo());//二级分类
        propertyRectifyCRMEntity.setClassifyThree(propertyRectifyAdminDTO.getClassifyThree());//三级分类
        propertyRectifyCRMEntity.setRegisterDate(new Date());//登记日期
        propertyRectifyCRMEntity.setRectifyState(propertyRectifyAdminDTO.getRectifyState());//整改状态
        propertyRectifyCRMEntity.setRoomLocation(propertyRectifyAdminDTO.getRoomLocation());//房屋位置
        propertyRectifyCRMEntity.setSupplier(propertyRectifyAdminDTO.getSupplier());//供应商
        propertyRectifyCRMEntity.setProblemDescription(propertyRectifyAdminDTO.getProblemDescription());//问题描述
        propertyRectifyCRMEntity.setCreateDate(new Date());
        propertyRectifyCRMEntity.setModifyDate(new Date());
        propertyRectifyCRMEntity.setCreateBy(propertyRectifyAdminDTO.getCreateBy());//创建人
        propertyRectifyCRMEntity.setCreatePhone(propertyRectifyAdminDTO.getCreatePhone());//创建人联系电话
        propertyRectifyCRMEntity.setPlanType(propertyRectifyAdminDTO.getPlanType());//活动类型
        propertyRectifyCRMEntity.setLimitDate((propertyRectifyAdminDTO.getLimitDate() == null || "".equals(propertyRectifyAdminDTO.getLimitDate())) ? null : DateUtils.parse(propertyRectifyAdminDTO.getLimitDate(), "yyyy-MM-dd"));//整改时限
        propertyRectifyCRMEntity.setxCoordinates((propertyRectifyAdminDTO.getxCoordinates() == null || "".equals(propertyRectifyAdminDTO.getxCoordinates())) ? null : new BigDecimal(propertyRectifyAdminDTO.getxCoordinates()));//X坐标
        propertyRectifyCRMEntity.setyCoordinates((propertyRectifyAdminDTO.getyCoordinates() == null || "".equals(propertyRectifyAdminDTO.getyCoordinates())) ? null : new BigDecimal(propertyRectifyAdminDTO.getyCoordinates()));//Y坐标
        propertyRectifyCRMEntity.setProjectNum(propertyRectifyAdminDTO.getProjectNum());//项目编码
        propertyRectifyCRMEntity.setUpdateFlag("0");//更新标志：新建时为0，更新时为1

        Object[] deliveryPlanCrmEntity = deliveryPlanCRMRepository.getByRoomNum(propertyRectifyAdminDTO.getRoomNum());
        if (deliveryPlanCrmEntity != null) {
            propertyRectifyCRMEntity.setPlanNum(deliveryPlanCrmEntity[1] == null ? "" : deliveryPlanCrmEntity[1].toString());
        }

        HouseRoomEntity houseRoomEntity = houseRoomRepository.getByRoomNum(propertyRectifyAdminDTO.getRoomNum());
        if (houseRoomEntity != null) {
            propertyRectifyCRMEntity.setRoomId(houseRoomEntity.getId());
        }
        System.out.println("~~~~整改单分割线1~~~~~~~~~propertyRectifyCRMEntity.getRectifyId():" + propertyRectifyCRMEntity.getRectifyId() + "     ~~~~propertyRectifyCRMEntity.getDepartment():" + propertyRectifyCRMEntity.getDepartment());
        if (!"草稿".equals(propertyRectifyCRMEntity.getRectifyState()) && !StringUtil.isEmpty(propertyRectifyCRMEntity.getRectifyId()) && !StringUtil.isEmpty(propertyRectifyCRMEntity.getDepartment())) {
            List<Object> listzu = propertyRepairCRMRepository.getStafidForzhijian(propertyRectifyCRMEntity.getDepartment());
            List<Object> listnum = propertyRepairCRMRepository.getStafidForNum(propertyRectifyCRMEntity.getRoomNum());
            System.out.println("~~~~整改单分割线2~~~~~~~~~listnum：" + listnum + "~~~~~~~~~~~~~listzu" + listzu);
            if (listnum != null && listzu != null) {
                listzu.retainAll(listnum);
                System.out.println("~~~~整改单分割线3~~~~~~~~~listzu：" + listzu);
                if (listzu != null) {
                    for (Object obj : listzu) {//listzu  拥有查看该保修单权限的员工id
                        System.out.println("---------------------------整改单分割线4-------------------------obj:" + obj);
                        List<MessageTokenEntity> MessageTokenlist = propertyRepairCRMRepository.getMessageToken(obj.toString());
                        if (MessageTokenlist != null) {
                            System.out.println("---------------------------整改单分割线5-------------------------MessageTokenlist.size():" + MessageTokenlist.size());
                            for (MessageTokenEntity MessageToken : MessageTokenlist) { //2 0IOS 1 AD
                                System.out.println("---------------------------整改单分割线6-------------------------MessageToken.getMessageTokenNum():" + MessageToken.getMessageTokenNum());
                                Random random = new Random();
                                String result = "";
                                for (int i = 0; i < 6; i++) {
                                    result += random.nextInt(10);
                                }
                                String messid = propertyRectifyCRMEntity.getRectifyId() + "/" + DateUtils.format(new Date(), "yyyyMMddHHmmss" + result);
                                MessageTargetEntity messageTargetEntity = new MessageTargetEntity();
                                messageTargetEntity.setUserId(MessageToken.getUserId());
                                messageTargetEntity.setMessageTargetId(messid);
                                messageTargetEntity.setMessageDetailId(messid);
                                messageTargetEntity.setMessageType("1");
                                messageTargetEntity.setTargetCreateTime(new Date());
                                messageTargetEntity.setMessagePushStatus("0");
                                messageTargetEntity.setMessageReadStatus("0");
                                messageTargetEntity.setMessageDeleteStatue("1");
                                messageTargetEntity.setMessageTokenNum(MessageToken.getMessageTokenNum());
                                messageTargetEntity.setUserType(String.valueOf(MessageToken.getMobileType()));
                                propertyRepairCRMRepository.savemessageTarget(messageTargetEntity);
                                MessageDetailEntity messageDetailEntity = new MessageDetailEntity();
                                messageDetailEntity.setMessageDetailId(messid);
                                messageDetailEntity.setMessageTitle("整改单");
                                if (propertyRectifyCRMEntity.getUpdateFlag().equals("0")) {
                                    messageDetailEntity.setMessageContent("您有新的整改单信息!");
                                } else {
                                    messageDetailEntity.setMessageContent("您的整改单信息有更新！");
                                }
                                messageDetailEntity.setMessageCreateTime(new Date());
                                messageDetailEntity.setMessageType("1");
                                propertyRepairCRMRepository.saveMessageDetail(messageDetailEntity);
                            }
                        }
                    }
                }
            }
        }
        propertyRectifyCRMRepository.saveQuestion(propertyRectifyCRMEntity);

        if (propertyRectifyAdminDTO.getImgFile() != null && propertyRectifyAdminDTO.getImgFile().length > 0) {
            for (int i = 0; i < propertyRectifyAdminDTO.getImgFile().length; i++) {
                if (propertyRectifyAdminDTO.getImgFile()[i] != null && !"".equals(propertyRectifyAdminDTO.getImgFile()[i].getOriginalFilename())) {
                    String fileName = imgService.uploadAdminImage(propertyRectifyAdminDTO.getImgFile()[i], ImgType.ACTIVITY);
                    String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                    fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                    PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                    propertyImageEntity.setImageId(propertyRectifyAdminDTO.getCreateBy() + IdGen.getDateId());//图片id
                    propertyImageEntity.setUploadDate(new Date());//上传日期
                    propertyImageEntity.setImgFkId(propertyRectifyCRMEntity.getRectifyId());//图片外键id
                    propertyImageEntity.setImagePath(fileName);//图片路径
                    propertyImageEntity.setImageType("5");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                    propertyImageEntity.setState("0");//状态:0为有效；1为无效
                    propertyImageRepository.saveImage(propertyImageEntity);
                }
            }
        }

        uploadCRM(propertyRectifyCRMEntity);//上传crm
    }

    @Override
    public void modifyAdminQeustion(PropertyRectifyAdminDTO propertyRectifyAdminDTO, UserInformationEntity userInformationEntity) {
        PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(propertyRectifyAdminDTO.getRectifyId());
        if (propertyRectifyCRMEntity != null) {
            propertyRectifyCRMEntity.setProblemType(propertyRectifyAdminDTO.getProblemType());//问题类型，简要描述
            propertyRectifyCRMEntity.setClassifyOne(propertyRectifyAdminDTO.getClassifyOne());//一级分类
            propertyRectifyCRMEntity.setClassifyTwo(propertyRectifyAdminDTO.getClassifyTwo());//二级分类
            propertyRectifyCRMEntity.setClassifyThree(propertyRectifyAdminDTO.getClassifyThree());//三级分类
            propertyRectifyCRMEntity.setModifyDate(new Date());

            //草稿的修改或者提交
            if ("草稿".equals(propertyRectifyCRMEntity.getRectifyState())) {
                propertyRectifyCRMEntity.setRectifyState(propertyRectifyAdminDTO.getRectifyState());//整改状态
                propertyRectifyCRMEntity.setRoomNum(propertyRectifyAdminDTO.getRoomNum());//房间编码
                propertyRectifyCRMEntity.setPlanNum(propertyRectifyAdminDTO.getPlanNum());//房间计划编码
                propertyRectifyCRMEntity.setLimitDate((propertyRectifyAdminDTO.getLimitDate() == null || "".equals(propertyRectifyAdminDTO.getLimitDate())) ? null : DateUtils.parse(propertyRectifyAdminDTO.getLimitDate()));//整改时限
                propertyRectifyCRMEntity.setxCoordinates((propertyRectifyAdminDTO.getxCoordinates() == null || "".equals(propertyRectifyAdminDTO.getxCoordinates())) ? null : new BigDecimal(propertyRectifyAdminDTO.getxCoordinates()));//X坐标
                propertyRectifyCRMEntity.setyCoordinates((propertyRectifyAdminDTO.getyCoordinates() == null || "".equals(propertyRectifyAdminDTO.getyCoordinates())) ? null : new BigDecimal(propertyRectifyAdminDTO.getyCoordinates()));//Y坐标
                propertyRectifyCRMEntity.setRoomLocation(propertyRectifyAdminDTO.getRoomLocation());//房屋位置
                propertyRectifyCRMEntity.setSupplier(propertyRectifyAdminDTO.getSupplier());//供应商
                propertyRectifyCRMEntity.setProblemDescription(propertyRectifyAdminDTO.getProblemDescription());//问题描述
                propertyRectifyCRMEntity.setUpdateFlag("1");//更新标志：新建时为0，更新时为1
                Object[] deliveryPlanCrmEntity = deliveryPlanCRMRepository.getByRoomNum(propertyRectifyAdminDTO.getRoomNum());
                if (deliveryPlanCrmEntity != null) {
                    propertyRectifyCRMEntity.setPlanNum(deliveryPlanCrmEntity[1] == null ? "" : deliveryPlanCrmEntity[1].toString());
                }

                HouseRoomEntity houseRoomEntity = houseRoomRepository.getByRoomNum(propertyRectifyAdminDTO.getRoomNum());
                if (houseRoomEntity != null) {
                    propertyRectifyCRMEntity.setRoomId(houseRoomEntity.getId());
                }

                propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);

                //删除不在包含的图片
                if (propertyRectifyAdminDTO.getImage() != null && !propertyRectifyAdminDTO.getImage().isEmpty()) {
                    propertyImageRepository.deleteByNotIds(propertyRectifyAdminDTO.getImage());
                }

                //保存新增图片
                if (propertyRectifyAdminDTO.getImgFile() != null && propertyRectifyAdminDTO.getImgFile().length > 0) {
                    for (int i = 0; i < propertyRectifyAdminDTO.getImgFile().length; i++) {
                        if (propertyRectifyAdminDTO.getImgFile()[i] != null && !"".equals(propertyRectifyAdminDTO.getImgFile()[i].getOriginalFilename())) {
                            String fileName = imgService.uploadAdminImage(propertyRectifyAdminDTO.getImgFile()[i], ImgType.ACTIVITY);
                            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                            PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                            propertyImageEntity.setImageId(propertyRectifyAdminDTO.getCreateBy() + IdGen.getDateId());//图片id
                            propertyImageEntity.setUploadDate(new Date());//上传日期
                            propertyImageEntity.setImgFkId(propertyRectifyCRMEntity.getRectifyId());//图片外键id
                            propertyImageEntity.setImagePath(fileName);//图片路径
                            propertyImageEntity.setImageType("5");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                            propertyImageEntity.setState("0");//状态:0为有效；1为无效
                            propertyImageRepository.saveImage(propertyImageEntity);
                        }
                    }
                }
                uploadCRM(propertyRectifyCRMEntity);//上传crm
            }

            if ("待接单".equals(propertyRectifyCRMEntity.getRectifyState()) && "处理中".equals(propertyRectifyAdminDTO.getRectifyState())) {//接单
                propertyRectifyCRMEntity.setRectifyState(propertyRectifyAdminDTO.getRectifyState());//整改状态
                if (propertyRectifyCRMEntity.getSupplierID() != null && !"".equals(propertyRectifyCRMEntity.getSupplierID())) {
                    propertyRectifyCRMEntity.setDealPeople(userInformationEntity.getStaffId());
                } else {
                    propertyRectifyCRMEntity.setRepairManager(userInformationEntity.getStaffName());//整改负责人
                    propertyRectifyCRMEntity.setRepairManagerId(userInformationEntity.getStaffId());
                }
                propertyRectifyCRMEntity.setDealPeople(userInformationEntity.getStaffId());//
                propertyRectifyCRMEntity.setDealPeopleName(userInformationEntity.getStaffName());
                propertyRectifyCRMEntity.setRepairPhone(userInformationEntity.getMobile());//整改人联系电话
                propertyRectifyCRMEntity.setDutyTaskDate(new Date());//接单时间
                propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);//保存修改
                uploadCRM(propertyRectifyCRMEntity);//上传crm
            }

            //处理中
            if ("处理中".equals(propertyRectifyCRMEntity.getRectifyState()) && "处理中".equals(propertyRectifyAdminDTO.getRectifyState())) {
                propertyRectifyCRMEntity.setRectifyState(propertyRectifyAdminDTO.getRectifyState());//整改状态
                propertyRectifyCRMEntity.setDealResult(propertyRectifyAdminDTO.getDealResult());

                propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);//保存修改

                //删除不在包含的图片
                if (propertyRectifyAdminDTO.getReviewImage() != null && !propertyRectifyAdminDTO.getReviewImage().isEmpty()) {
                    propertyImageRepository.deleteByNotIds(propertyRectifyAdminDTO.getReviewImage());
                } else {
                    propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "6");
                }
                //保存新增图片
                if (propertyRectifyAdminDTO.getReviewImgFile() != null && propertyRectifyAdminDTO.getReviewImgFile().length > 0) {
                    for (int i = 0; i < propertyRectifyAdminDTO.getReviewImgFile().length; i++) {
                        if (propertyRectifyAdminDTO.getReviewImgFile()[i] != null && !"".equals(propertyRectifyAdminDTO.getReviewImgFile()[i].getOriginalFilename())) {
                            String fileName = imgService.uploadAdminImage(propertyRectifyAdminDTO.getReviewImgFile()[i], ImgType.ACTIVITY);
                            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                            PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                            propertyImageEntity.setImageId(propertyRectifyAdminDTO.getCreateBy() + IdGen.getDateId());//图片id
                            propertyImageEntity.setUploadDate(new Date());//上传日期
                            propertyImageEntity.setImgFkId(propertyRectifyCRMEntity.getRectifyId());//图片外键id
                            propertyImageEntity.setImagePath(fileName);//图片路径
                            propertyImageEntity.setImageType("6");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                            propertyImageEntity.setState("0");//状态:0为有效；1为无效
                            propertyImageRepository.saveImage(propertyImageEntity);
                        }
                    }
                }
                uploadCRM(propertyRectifyCRMEntity);//上传crm
            }

            //处理完成
            if ("处理中".equals(propertyRectifyCRMEntity.getRectifyState()) && "已完成".equals(propertyRectifyAdminDTO.getRectifyState())) {
                propertyRectifyCRMEntity.setRectifyState(propertyRectifyAdminDTO.getRectifyState());//整改状态
                propertyRectifyCRMEntity.setDealResult(propertyRectifyAdminDTO.getDealResult());
                propertyRectifyCRMEntity.setRectifyCompleteDate(new Date());//整改完成时间
                propertyRectifyCRMEntity.setRealityDate(new Date());
                propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);//保存修改

                //删除不在包含的图片
                if (propertyRectifyAdminDTO.getReviewImage() != null && !propertyRectifyAdminDTO.getReviewImage().isEmpty()) {
                    propertyImageRepository.deleteByNotIds(propertyRectifyAdminDTO.getReviewImage());
                } else {
                    propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "6");
                }


                //保存新增图片
                if (propertyRectifyAdminDTO.getReviewImgFile() != null && propertyRectifyAdminDTO.getReviewImgFile().length > 0) {
                    for (int i = 0; i < propertyRectifyAdminDTO.getReviewImgFile().length; i++) {
                        if (propertyRectifyAdminDTO.getReviewImgFile()[i] != null && !"".equals(propertyRectifyAdminDTO.getReviewImgFile()[i].getOriginalFilename())) {
                            String fileName = imgService.uploadAdminImage(propertyRectifyAdminDTO.getReviewImgFile()[i], ImgType.ACTIVITY);
                            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                            PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                            propertyImageEntity.setImageId(propertyRectifyAdminDTO.getCreateBy() + IdGen.getDateId());//图片id
                            propertyImageEntity.setUploadDate(new Date());//上传日期
                            propertyImageEntity.setImgFkId(propertyRectifyCRMEntity.getRectifyId());//图片外键id
                            propertyImageEntity.setImagePath(fileName);//图片路径
                            propertyImageEntity.setImageType("6");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                            propertyImageEntity.setState("0");//状态:0为有效；1为无效
                            propertyImageRepository.saveImage(propertyImageEntity);
                        }
                    }
                }
                uploadCRM(propertyRectifyCRMEntity);//上传crm
            }
        }
    }

    @Override
    public void modifyAdminQeustionClose(PropertyRectifyAdminDTO propertyRectifyAdminDTO, UserInformationEntity userInformationEntity) {
        {
            PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(propertyRectifyAdminDTO.getRectifyId());
            if (propertyRectifyCRMEntity != null) {
                propertyRectifyCRMEntity.setProblemType(propertyRectifyAdminDTO.getProblemType());//问题类型，简要描述
                propertyRectifyCRMEntity.setClassifyOne(propertyRectifyAdminDTO.getClassifyOne());//一级分类
                propertyRectifyCRMEntity.setClassifyTwo(propertyRectifyAdminDTO.getClassifyTwo());//二级分类
                propertyRectifyCRMEntity.setClassifyThree(propertyRectifyAdminDTO.getClassifyThree());//三级分类
                propertyRectifyCRMEntity.setModifyDate(new Date());

                propertyRectifyCRMEntity.setRectifyState(propertyRectifyAdminDTO.getRectifyState());//整改状态
                propertyRectifyCRMEntity.setDealResult(propertyRectifyAdminDTO.getDealResult());
                propertyRectifyCRMEntity.setRectifyCompleteDate(new Date());//整改完成时间
                propertyRectifyCRMEntity.setRealityDate(new Date());
                propertyRectifyCRMEntity.setUpdateUserName(userInformationEntity.getStaffName());
                propertyRectifyCRMEntity.setUpdateUserDate(new Date());
                propertyRectifyCRMEntity.setRectifyState("已完成");
                propertyRectifyCRMEntity.setModifyDate(new Date());
                uploadCRM(propertyRectifyCRMEntity);
                propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);//保存修改
                //删除不在包含的图片
                if (propertyRectifyAdminDTO.getReviewImage() != null && !propertyRectifyAdminDTO.getReviewImage().isEmpty()) {
                    propertyImageRepository.deleteByNotIds(propertyRectifyAdminDTO.getReviewImage());
                } else {
                    propertyImageRepository.deleteByFkId(propertyRectifyCRMEntity.getRectifyId(), "6");
                }
                //保存新增图片
                if (propertyRectifyAdminDTO.getReviewImgFile() != null && propertyRectifyAdminDTO.getReviewImgFile().length > 0) {
                    for (int i = 0; i < propertyRectifyAdminDTO.getReviewImgFile().length; i++) {
                        if (propertyRectifyAdminDTO.getReviewImgFile()[i] != null && !"".equals(propertyRectifyAdminDTO.getReviewImgFile()[i].getOriginalFilename())) {
                            String fileName = imgService.uploadAdminImage(propertyRectifyAdminDTO.getReviewImgFile()[i], ImgType.ACTIVITY);
                            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                            PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                            propertyImageEntity.setImageId(propertyRectifyAdminDTO.getCreateBy() + IdGen.getDateId());//图片id
                            propertyImageEntity.setUploadDate(new Date());//上传日期
                            propertyImageEntity.setImgFkId(propertyRectifyCRMEntity.getRectifyId());//图片外键id
                            propertyImageEntity.setImagePath(fileName);//图片路径
                            propertyImageEntity.setImageType("6");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                            propertyImageEntity.setState("0");//状态:0为有效；1为无效
                            propertyImageRepository.saveImage(propertyImageEntity);
                        }
                    }
                }
                uploadCRM(propertyRectifyCRMEntity);//上传crm
            }
        }
    }

    @Override
    public void deleteAdminQeustion(String rectifyId,UserInformationEntity user) {
        PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(rectifyId);
        propertyRectifyCRMEntity.setRectifyState("已废弃");
        propertyRectifyCRMEntity.setToVoidBy(user.getStaffName());//废弃操作人
        propertyRectifyCRMEntity.setToVoidDate(new Date());//废弃时间
        propertyRectifyCRMEntity.setModifyDate(new Date());
        uploadCRM(propertyRectifyCRMEntity);
        propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);
    }

    @Override
    public PropertyRectifyCRMListDTO getAdminQuestionById(PropertyRectifyAdminDTO propertyRectifyAdminDTO) {
        Object[] obj = propertyRectifyCRMRepository.getAdminQuestionDetail(propertyRectifyAdminDTO.getRectifyId());
        PropertyRectifyCRMListDTO propertyRectifyCRMListDTO = new PropertyRectifyCRMListDTO();
        propertyRectifyCRMListDTO.setCaseId(obj[0] == null ? "" : obj[0].toString());         //问题ID 主键
        propertyRectifyCRMListDTO.setSetId(obj[1] == null ? "" : obj[1].toString());          //批次ID
        propertyRectifyCRMListDTO.setCaseTitle(obj[2] == null ? "" : obj[2].toString());      //问题标题
        propertyRectifyCRMListDTO.setCasePlace(obj[3] == null ? "" : obj[3].toString());      //问题部位
        propertyRectifyCRMListDTO.setCaseType(obj[4] == null ? "" : obj[4].toString());       //问题类型
        propertyRectifyCRMListDTO.setOneType(obj[5] == null ? "" : obj[5].toString());        //一级分类
        propertyRectifyCRMListDTO.setTwoType(obj[6] == null ? "" : obj[6].toString());        //二级分类
        propertyRectifyCRMListDTO.setThreeType(obj[7] == null ? "" : obj[7].toString());      //三级分类
        propertyRectifyCRMListDTO.setCaseDesc(obj[8] == null ? "" : obj[8].toString());       //问题描述
        propertyRectifyCRMListDTO.setCaseState(obj[9] == null ? "" : obj[9].toString());      //问题状态,------草稿、待接单、处理中、已完成、已废弃
        propertyRectifyCRMListDTO.setRoomId(obj[10] == null ? "" : obj[10].toString());         //房间ID,或者公共区域ID
        propertyRectifyCRMListDTO.setProjectId(obj[11] == null ? "" : obj[11].toString());      //项目名称
        propertyRectifyCRMListDTO.setPlanType(obj[12] == null ? "" : obj[12].toString());         //计划（模块）类型
        propertyRectifyCRMListDTO.setCreateDate(obj[13] == null ? null : (Date) obj[13]);       //创建时间
        propertyRectifyCRMListDTO.setCreateBy(obj[14] == null ? "" : obj[14].toString());       //创建人
        propertyRectifyCRMListDTO.setLimitTime(obj[15] == null ? null : (Date) obj[15]);          //整改时限
        propertyRectifyCRMListDTO.setComments(obj[16] == null ? "" : obj[16].toString());        //批注留言
        propertyRectifyCRMListDTO.setContractor(obj[17] == null ? "" : obj[17].toString());     //承建商（整改单位）
        propertyRectifyCRMListDTO.setModifyBy(obj[18] == null ? "" : obj[18].toString());   // 修改人
        propertyRectifyCRMListDTO.setModifyDate(obj[19] == null ? null : (Date) obj[19]);//修改时间
        propertyRectifyCRMListDTO.setxCoordinates(obj[20] == null ? null : new BigDecimal(obj[20].toString()));//X坐标
        propertyRectifyCRMListDTO.setyCoordinates(obj[21] == null ? null : new BigDecimal(obj[21].toString()));//Y坐标
        propertyRectifyCRMListDTO.setProjectName(obj[22] == null ? "" : obj[22].toString());// 工程名称
        propertyRectifyCRMListDTO.setFirstTypeName(obj[23] == null ? "" : obj[23].toString());// 一级分类
        propertyRectifyCRMListDTO.setSecondTyoeName(obj[24] == null ? "" : obj[24].toString());// 二级分类
        propertyRectifyCRMListDTO.setThirdTypeName(obj[25] == null ? "" : obj[25].toString());// 三级分类
        propertyRectifyCRMListDTO.setBuildingId(obj[26] == null ? "" : obj[26].toString());// 楼栋ID
        propertyRectifyCRMListDTO.setUnitId(obj[27] == null ? "" : obj[27].toString());// 单元Id
        propertyRectifyCRMListDTO.setFloorId(obj[28] == null ? "" : obj[28].toString());// 楼层ID
        propertyRectifyCRMListDTO.setRealityDate(obj[29] == null ? null : (Date) obj[29]);//整改完成期限
        propertyRectifyCRMListDTO.setAddress(obj[30] == null ? "" : obj[30].toString());
        propertyRectifyCRMListDTO.setCreateCode(obj[31] == null ? "" : obj[31].toString());
//        propertyRectifyCRMListDTO.setRepairManager(obj[32] == null ? "" : obj[32].toString());
        propertyRectifyCRMListDTO.setRepairPhone(obj[33] == null ? "" : obj[33].toString());// 33 整内部负责人手机号
        propertyRectifyCRMListDTO.setDealPeople(obj[34] == null ? "" : obj[34].toString());
        propertyRectifyCRMListDTO.setRepairManager(obj[35] == null ? "" : obj[35].toString());//整改负责人姓名
        propertyRectifyCRMListDTO.setCreateByName(obj[36] == null ? "" : obj[36].toString());//创建人姓名
        propertyRectifyCRMListDTO.setSenUserName(obj[37] == null ? "" : obj[37].toString());//派单人姓名
        propertyRectifyCRMListDTO.setUpcloseName(obj[38] == null ? "" : obj[38].toString());//填报人姓名
        propertyRectifyCRMListDTO.setSendDate(obj[39] == null ? null : DateUtils.parse(obj[39].toString()));//派单时间
        propertyRectifyCRMListDTO.setUpdateDate(obj[40] == null ? null : DateUtils.parse(obj[40].toString()));//填报时间
        propertyRectifyCRMListDTO.setDutyTaskDate(obj[41] == null ? null : DateUtils.parse(obj[41].toString()));//接单时间
        propertyRectifyCRMListDTO.setForceClose(obj[42] == null ? "" : obj[42].toString());//强制关闭原因
        propertyRectifyCRMListDTO.setForceCloseName(obj[43] == null ? "" : obj[43].toString());//强制关单人
        propertyRectifyCRMListDTO.setForceCloseDate(obj[44] == null ? null : DateUtils.parse(obj[44].toString()));//强制关单时间
        propertyRectifyCRMListDTO.setCreatePhone(obj[45] == null ? "" : obj[45].toString());//创建人手机号

//        UserPropertyStaffEntity UserPropertyStaff=rectificationRepository.getusername(obj[34] == null ? "" : obj[34].toString());
//        if(UserPropertyStaff!=null){
//            if(!StringUtil.isEmpty(UserPropertyStaff.getUserName())){
//                propertyRectifyCRMListDTO.setRepairManager(UserPropertyStaff.getStaffName());
//            }
//        }
        HouseTypeDTO houseTypeDTO = houseRoomTypeService.getHouseRoomTypeUrlByRoomNum(propertyRectifyCRMListDTO.getRoomId());
        if (houseTypeDTO != null) {
            propertyRectifyCRMListDTO.setHouseTypeUrl(houseTypeDTO.getImgUrl());
            propertyRectifyCRMListDTO.setHouseTypeId(houseTypeDTO.getId());
        }

        if (propertyRectifyCRMListDTO.getCaseType() != null) {
            List<DescribeEntity> describeEntityList = describeRepository.getListsByIds(propertyRectifyCRMListDTO.getCaseType());
            StringBuffer caseType = new StringBuffer();
            if (describeEntityList != null && !describeEntityList.isEmpty()) {
                for (int i = 0; i < describeEntityList.size(); i++) {
                    DescribeEntity describeEntity = describeEntityList.get(i);
                    caseType.append(describeEntity.getLabel()).append("；");
                }
            }
            propertyRectifyCRMListDTO.setCaseType(caseType.toString());
        }


        List<PropertyImageEntity> imgList = propertyImageRepository.getImageByType(propertyRectifyAdminDTO.getRectifyId(), "5");
        if (imgList != null && !imgList.isEmpty()) {
            List<RectifyImageDTO> imageList = new ArrayList<RectifyImageDTO>();
            for (PropertyImageEntity p : imgList) {
                RectifyImageDTO image = new RectifyImageDTO();
                image.setCaseId(p.getImgFkId());
                image.setImageId(p.getImageId());
                image.setImageUrl(p.getImagePath());
                imageList.add(image);
            }
            propertyRectifyCRMListDTO.setQuestionImageList(imageList);//整改图片
        }

        List<PropertyImageEntity> reviewList = propertyImageRepository.getImageByType(propertyRectifyAdminDTO.getRectifyId(), "6");
        if (reviewList != null && !reviewList.isEmpty()) {
            List<RectifyImageDTO> reviewImageList = new ArrayList<RectifyImageDTO>();
            for (PropertyImageEntity p : reviewList) {
                RectifyImageDTO image = new RectifyImageDTO();
                image.setCaseId(p.getImgFkId());
                image.setImageId(p.getImageId());
                image.setImageUrl(p.getImagePath());
                reviewImageList.add(image);
            }
            propertyRectifyCRMListDTO.setRectifyImageList(reviewImageList);//整改后图片
        }

        return propertyRectifyCRMListDTO;
    }

    @Override
    public Map<String, String> getDescriptionByThirdId(String thirId) {
        List<DescribeEntity> list = describeRepository.getListByThirdId(thirId);
        Map<String, String> map = new LinkedHashMap<>();
        if (list != null && !list.isEmpty()) {
            for (DescribeEntity describeEntity : list) {
                map.put(describeEntity.getValue(), describeEntity.getLabel());
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getPlanByProjectNum(String projectNum,String planType) {
        List<String> listPlan= new ArrayList<String>();
        listPlan.add("");
        if("11".equals(planType)){
            listPlan.add("houseInternalPreInspection");
        }else if("12".equals(planType)){
            listPlan.add("clientOpendayActivity");
        }else if("13".equals(planType)){
            listPlan.add("centralizeDeliverHouse");
            listPlan.add("scatteredDeliverHouse");
        }
        List<Object[]> list=deliveryPlanCRMRepository.queryPlanByProjectNum(projectNum,listPlan);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("","请选择");
        if(list != null && !list.isEmpty()){
            for(Object[] b:list){
                map.put(b[0].toString(),b[1].toString());
            }
        }
        return map;
    }

    @Override
    public PropertyRectifyAdminDTO getAdminQuestionByRectifyId(PropertyRectifyAdminDTO propertyRectifyAdminDTO) {
        Object[] obj = propertyRectifyCRMRepository.getAdminQuestionByRectifyId(propertyRectifyAdminDTO.getRectifyId());
        if (obj == null) {
            return null;
        } else {
            PropertyRectifyAdminDTO propertyRectifyAdminDTO1 = new PropertyRectifyAdminDTO();
            propertyRectifyAdminDTO1.setRectifyId(obj[0] == null ? "" : obj[0].toString());//整改单号
            propertyRectifyAdminDTO1.setDepartment(obj[1] == null ? "" : obj[1].toString());//部门
            propertyRectifyAdminDTO1.setRoomId(obj[2] == null ? "" : obj[2].toString());//房间id
            propertyRectifyAdminDTO1.setRoomNum(obj[3] == null ? "" : obj[3].toString());//房间编码
            propertyRectifyAdminDTO1.setPlanNum(obj[4] == null ? "" : obj[4].toString());//房间计划编码
            propertyRectifyAdminDTO1.setAcceptanceDate(obj[5] == null ? "" : DateUtils.format((Date) obj[5]));//内部预验房日期
            propertyRectifyAdminDTO1.setProblemType(obj[6] == null ? "" : obj[6].toString());//问题类型
            propertyRectifyAdminDTO1.setClassifyOne(obj[7] == null ? "" : obj[7].toString());//一级分类
            propertyRectifyAdminDTO1.setClassifyTwo(obj[8] == null ? "" : obj[8].toString());//二级分类
            propertyRectifyAdminDTO1.setClassifyThree(obj[9] == null ? "" : obj[9].toString());//三级分类
            propertyRectifyAdminDTO1.setRegisterDate(obj[10] == null ? "" : DateUtils.format((Date) obj[10]));//登记日期
            propertyRectifyAdminDTO1.setRectifyState(obj[11] == null ? "" : obj[11].toString());//整改状态
            propertyRectifyAdminDTO1.setRoomLocation(obj[12] == null ? "" : obj[12].toString());//房屋位置
            propertyRectifyAdminDTO1.setSupplier(obj[13] == null ? "" : obj[13].toString());//供应商
            propertyRectifyAdminDTO1.setRectifyCompleteDate(obj[14] == null ? "" : DateUtils.format((Date) obj[14]));//整改完成时间
            propertyRectifyAdminDTO1.setRealityDate(obj[15] == null ? "" : DateUtils.format((Date) obj[15]));//实际完成时间
            propertyRectifyAdminDTO1.setProblemDescription(obj[16] == null ? "" : obj[16].toString());//问题描述
            propertyRectifyAdminDTO1.setDealResult(obj[17] == null ? "" : obj[17].toString());//处理结果
            propertyRectifyAdminDTO1.setCreateDate(obj[18] == null ? "" : DateUtils.format((Date) obj[18]));//创建时间
            propertyRectifyAdminDTO1.setModifyDate(obj[19] == null ? "" : DateUtils.format((Date) obj[19]));//修改时间
            propertyRectifyAdminDTO1.setCreateBy(obj[20] == null ? "" : obj[20].toString());//创建人编码
            propertyRectifyAdminDTO1.setCreatePhone(obj[21] == null ? "" : obj[21].toString());//创建人联系电话
            propertyRectifyAdminDTO1.setPlanType(obj[22] == null ? "" : obj[22].toString());//活动类型
            propertyRectifyAdminDTO1.setRepairManager(obj[23] == null ? "" : obj[23].toString());//整改负责人ID
            propertyRectifyAdminDTO1.setRepairPhone(obj[24] == null ? "" : obj[24].toString());//整改人联系电话
            propertyRectifyAdminDTO1.setDutyTaskDate(obj[25] == null ? "" : DateUtils.format((Date) obj[25]));//接单时间
            propertyRectifyAdminDTO1.setLimitDate(obj[26] == null ? "" : DateUtils.format((Date) obj[26], "yyyy-MM-dd"));//整改时限
            propertyRectifyAdminDTO1.setxCoordinates(obj[27] == null ? "" : obj[27].toString());//X坐标
            propertyRectifyAdminDTO1.setyCoordinates(obj[28] == null ? "" : obj[28].toString());//Y坐标
            propertyRectifyAdminDTO1.setProjectNum(obj[29] == null ? "" : obj[29].toString());//项目编码
            propertyRectifyAdminDTO1.setBuildingNum(obj[30] == null ? "" : obj[30].toString());
            propertyRectifyAdminDTO1.setUnitNum(obj[31] == null ? "" : obj[31].toString());
            propertyRectifyAdminDTO1.setFloorNum(obj[32] == null ? "" : obj[32].toString());

            HouseTypeDTO houseTypeDTO = houseRoomTypeService.getHouseRoomTypeUrlByRoomNum(propertyRectifyAdminDTO1.getRoomNum());
            if (houseTypeDTO != null) {
                propertyRectifyAdminDTO1.setHouseTypeId(houseTypeDTO.getId());
                propertyRectifyAdminDTO1.setHouseTyprUrl(houseTypeDTO.getImgUrl());
            }

            List<PropertyImageEntity> imgList = propertyImageRepository.getImageByType(propertyRectifyAdminDTO.getRectifyId(), "5");
            if (imgList != null && !imgList.isEmpty()) {
                List<RectifyImageDTO> imageList = new ArrayList<RectifyImageDTO>();
                for (PropertyImageEntity p : imgList) {
                    RectifyImageDTO image = new RectifyImageDTO();
                    image.setCaseId(p.getImgFkId());
                    image.setImageId(p.getImageId());
                    image.setImageUrl(p.getImagePath());
                    imageList.add(image);
                }
                propertyRectifyAdminDTO1.setImageList(imageList);//整改图片
            }

            List<PropertyImageEntity> reviewList = propertyImageRepository.getImageByType(propertyRectifyAdminDTO.getRectifyId(), "6");
            if (reviewList != null && !reviewList.isEmpty()) {
                List<RectifyImageDTO> reviewImageList = new ArrayList<RectifyImageDTO>();
                for (PropertyImageEntity p : reviewList) {
                    RectifyImageDTO image = new RectifyImageDTO();
                    image.setCaseId(p.getImgFkId());
                    image.setImageId(p.getImageId());
                    image.setImageUrl(p.getImagePath());
                    reviewImageList.add(image);
                }
                propertyRectifyAdminDTO1.setReviewImgList(reviewImageList);//整改后图片
            }

            return propertyRectifyAdminDTO1;
        }
    }

    @Override
    public String exportExcel(UserPropertyStaffEntity user, HttpServletResponse httpServletResponse, PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
//        List<PropertyRectifyCRMListDTO> list = getQuestionList(propertyRectifyCRMSelDTO, null);

        List<PropertyRectifyExcelListDTO> list = getRectifyExcelList(propertyRectifyCRMSelDTO, null);
        //创建Sheet页
        XSSFSheet sheet = workBook.createSheet("问题清单");

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

        String[] titles = {"序号","整改单ID", "是否超期", "问题描述", "位置", "一级分类", "二级分类", "三级分类", "供应商", "整改责任人", "登记时间", "状态","城市","地块","项目","楼栋","单元","楼层","房间"};

        XSSFRow headRow = sheet.createRow(0);

        if (list != null && list.size() > 0) {
            list.forEach(a -> {
                XSSFCell cell = null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);

                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                sheet.setColumnWidth(0, 1600);
                sheet.setColumnWidth(1, 3200);
                sheet.setColumnWidth(2, 3200);
                sheet.setColumnWidth(3, 10000);
                sheet.setColumnWidth(4, 13000);
                sheet.setColumnWidth(5, 4000);
                sheet.setColumnWidth(6, 4000);
                sheet.setColumnWidth(7, 4000);
                sheet.setColumnWidth(8, 4000);
                sheet.setColumnWidth(9, 4000);
                sheet.setColumnWidth(10, 4000);
                sheet.setColumnWidth(11, 3200);
                sheet.setColumnWidth(12, 3200);
                sheet.setColumnWidth(13, 3200);
                sheet.setColumnWidth(14, 3200);
                sheet.setColumnWidth(15, 3200);
                sheet.setColumnWidth(16, 3200);
                sheet.setColumnWidth(17, 3200);
                sheet.setColumnWidth(18, 3200);

                for (int i = 0; i < list.size(); i++) {
                    XSSFRow bodyRow = sheet.createRow(i + 1);
//                    PropertyRectifyCRMListDTO propertyRectifyCRMListDTO = list.get(i);
                    PropertyRectifyExcelListDTO rectifyExcel = list.get(i);
                    cell = bodyRow.createCell(0);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(i + 1);//序号

                    cell = bodyRow.createCell(1);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getRectifyId());//整改单ID

                    cell = bodyRow.createCell(2);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    if (rectifyExcel.getLimitTime() != null) {
                        if ((rectifyExcel.getRealityDate() != null && rectifyExcel.getLimitTime().after(rectifyExcel.getRealityDate()))
                                || rectifyExcel.getLimitTime().before(new Date())) {
                            cell.setCellValue("已超期");
                        } else {
                            cell.setCellValue("进行中");
                        }
                    }

                    cell = bodyRow.createCell(3);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getProblemDescription());//问题描述

                    cell = bodyRow.createCell(4);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getAddress());//位置

                    cell = bodyRow.createCell(5);
                    cell.setCellStyle(bodyStyle);// 一级分类
                    cell.setCellValue(rectifyExcel.getFirstTypeName());//一级分类

                    cell = bodyRow.createCell(6);
                    cell.setCellStyle(bodyStyle);// 二级分类
                    cell.setCellValue(rectifyExcel.getSecondTyoeName());//二级分类

                    cell = bodyRow.createCell(7);
                    cell.setCellStyle(bodyStyle);// 三级分类
                    cell.setCellValue(rectifyExcel.getThirdTypeName());//三级分类

                    cell = bodyRow.createCell(8);
                    cell.setCellStyle(bodyStyle);// 供应商
                    cell.setCellValue(rectifyExcel.getSupplierName());// 供应商
//                    if (propertyRectifyCRMListDTO.getContractor() != null && !"".equals(propertyRectifyCRMListDTO.getContractor())) {
//                        SupplierEntity supplierEntity = supplierRepository.getById(propertyRectifyCRMListDTO.getContractor());
//                        if (supplierEntity != null) {
//                            cell.setCellValue(supplierEntity.getName());//供应商
//                        }
//                    }

                    cell = bodyRow.createCell(9);
                    cell.setCellStyle(bodyStyle);// 责任人
                    cell.setCellValue(rectifyExcel.getDealPeopleName());//责任人
//                    if (propertyRectifyCRMListDTO.getModifyBy() != null && !"".equals(propertyRectifyCRMListDTO.getModifyBy())) {
//                        UserPropertyStaffEntity userPropertyStaffEntity = userPropertyStaffRepository.get(propertyRectifyCRMListDTO.getModifyBy());
//                        if (userPropertyStaffEntity != null) {
//                            cell.setCellValue(userPropertyStaffEntity.getStaffName());//责任人
//                        }
//                    }


                    cell = bodyRow.createCell(10);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(DateUtils.format(rectifyExcel.getCreateDate()));//创建时间

                    cell = bodyRow.createCell(11);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getRectifyState());//状态

//                    PropertyRectifyExcelListDTO roomExcelList=getHouseInfoByRoomNum(propertyRectifyCRMListDTO.getRoomId());//查询房屋基础数据

                    cell = bodyRow.createCell(12);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getCity());//城市

                    cell = bodyRow.createCell(13);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getArea());//地块

                    cell = bodyRow.createCell(14);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getProject());//项目

                    cell = bodyRow.createCell(15);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getBuild());//楼栋

                    cell = bodyRow.createCell(16);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getUnit());//单元

                    cell = bodyRow.createCell(17);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getFloor());//楼层

                    cell = bodyRow.createCell(18);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(rectifyExcel.getRoom());//房间
                }
            });
            try {
                //String fileName = new String(("问题清单").getBytes(), "ISO8859-1");
                String fileName = new String(("问题清单").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("报名信息列表", "UTF8");
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
        } else {
            outputStream.flush();
            outputStream.close();
        }
        return "";
    }

    @Override
    public void batchCommit(PropertyRectifyAdminDTO propertyRectifyAdminDTO, UserInformationEntity userInformationEntity) {
        if (propertyRectifyAdminDTO.getIds() != null && !propertyRectifyAdminDTO.getIds().isEmpty()) {
            for (String id : propertyRectifyAdminDTO.getIds()) {
                PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(id);
                if (propertyRectifyCRMEntity != null) {
                    propertyRectifyCRMEntity.setModifyDate(new Date());
                    //草稿提交
                    if ("草稿".equals(propertyRectifyCRMEntity.getRectifyState())) {
                        propertyRectifyCRMEntity.setRectifyState("待接单");//整改状态
                        propertyRectifyCRMEntity.setUpdateFlag("1");//更新标志：新建时为0，更新时为1
                        /*Object[] deliveryPlanCrmEntity = deliveryPlanCRMRepository.getByRoomNum(propertyRectifyAdminDTO.getRoomNum());
                        if(deliveryPlanCrmEntity!=null){
                            propertyRectifyCRMEntity.setPlanNum(deliveryPlanCrmEntity[1]==null?"":deliveryPlanCrmEntity[1].toString());
                        }

                        HouseRoomEntity houseRoomEntity = houseRoomRepository.getByRoomNum(propertyRectifyAdminDTO.getRoomNum());
                        if(houseRoomEntity !=null){
                            propertyRectifyCRMEntity.setRoomId(houseRoomEntity.getId());
                        }*/
                        propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);
                        uploadCRM(propertyRectifyCRMEntity);//上传crm
                    }
                }
            }
        }
    }

    @Override
    public void closeAdminQeustion(PropertyRectifyAdminDTO propertyRectifyAdminDTO, String name) {
        PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(propertyRectifyAdminDTO.getRectifyId());
//        private String updateUserName;//填报人
//        private Date updateUserDate;// 填报时间
        propertyRectifyCRMEntity.setUpdateUserName(name);
        propertyRectifyCRMEntity.setUpdateUserDate(new Date());
        propertyRectifyCRMEntity.setRectifyState("已完成");
        propertyRectifyCRMEntity.setModifyDate(new Date());
        uploadCRM(propertyRectifyCRMEntity);
        propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);
    }

    @Override
    public void forceCloseAdminQeustion(String rectifyId,String forceClose,UserInformationEntity userInformationEntity) {
        PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(rectifyId);
        propertyRectifyCRMEntity.setForceCloseName(userInformationEntity.getStaffName());//强制关单人姓名
        propertyRectifyCRMEntity.setForceCloseUserName(userInformationEntity.getUserName());//强制关单人账号
        propertyRectifyCRMEntity.setForceCloseDate(new Date());//强制关单时间
        propertyRectifyCRMEntity.setForceClose(forceClose);//强制关闭原因
        propertyRectifyCRMEntity.setRectifyState("强制关闭");
        propertyRectifyCRMEntity.setModifyDate(new Date());
        uploadCRM(propertyRectifyCRMEntity);
        propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);
    }

    /**
     * 根据ID,项目编码查询该职工的权限
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/6
     */
    @Override
    public ProblemRoleDTO getProblemRole(String staffID, String projectNum, String dealPeople, String creaBy) {
        // 1数据查看  2工程接单  3派单到人  100000001物业接单  100000000开发接单
        // dealProblem;//处理2
        // deleProblem;//废弃2 创建人  接单权限  派单权限 关单权限
        // closeProblem;//关单4
        //redirectProblem;//派单3
        //1数据查看  2工程接单  3派单到人  4关单权限
        boolean isDealPeople = (staffID.equals(dealPeople) && StringUtil.isEmpty(dealPeople)) ? true : false;
        boolean iscreateBy = (staffID.equals(creaBy) && StringUtil.isEmpty(creaBy) ? true : false);
        ProblemRoleDTO problemRoleDTO = new ProblemRoleDTO("0", "0", "0", "0");
        if (StringUtil.isEmpty(projectNum)) {
            return problemRoleDTO;
        }
        boolean isDealOrdeleProblem = roleDataRepository.havaDispatch(staffID, projectNum, "2");
        boolean isRedirectProblem = roleDataRepository.havaDispatch(staffID, projectNum, "3");
        boolean isCloseProblem = roleDataRepository.havaDispatch(staffID, projectNum, "4");
        if (iscreateBy || isDealPeople) {
            problemRoleDTO.setDeleProblem("1");
        }
        if (isDealOrdeleProblem) {
            problemRoleDTO.setDealProblem("1");
            problemRoleDTO.setDeleProblem("1");
        }
        if (isCloseProblem) {
            problemRoleDTO.setCloseProblem("1");
            problemRoleDTO.setDeleProblem("1");
        }
        if (isRedirectProblem) {
            problemRoleDTO.setRedirectProblem("1");
            problemRoleDTO.setDeleProblem("1");
        }
        return problemRoleDTO;
    }

    @Override
    public Map<String, String> getInnerPersonList(String projectNum) {
        List<Object[]> entityList = propertyRectifyCRMRepository.getInnerPersonList(projectNum);
        Map<String, String> map = new HashMap<>();
        if (entityList != null && entityList.size() > 0) {
            for (Object[] objs : entityList) {
                map.put(objs[0].toString(), objs[1].toString());
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getSupplierResponsibleList(String supplier) {
        List<Object[]> entityList = propertyRectifyCRMRepository.getSupplierResponsibleList(supplier);
        Map<String, String> map = new HashMap<>();
        if (entityList != null && entityList.size() > 0) {
            for (Object[] objs : entityList) {
                map.put(objs[0].toString(), objs[1].toString());
            }
            return map;
        }
        return null;

    }

    @Override
    public QuestionUpdateCheckDTO searchToUpdateByType(String idNew, String beginDateNew, String projectNum, String planNum) {
        boolean checkFlag=false;
        String planType = null;
        if (planNum != null && !planNum.equals("")) {
            if (planNum.equals("11")) {
               planType="houseInternalPreInspection";
            }else if(planNum.equals("12")){
                planType="clientOpendayActivity";
            }else {
                planType="centralizeDeliverHouse";
            }
        }
        checkFlag= propertyRectifyCRMRepository.searchToUpdateForPlan(idNew, beginDateNew, projectNum, planType);
        if (checkFlag) {
            return new QuestionUpdateCheckDTO("Y");
        }else {
            checkFlag =propertyRectifyCRMRepository.searchToUpdateForBuilding(idNew, beginDateNew, projectNum);
            if(checkFlag){
                return new QuestionUpdateCheckDTO("Y");
            }else{
                checkFlag=propertyRectifyCRMRepository.searchToUpdateForHouseType(idNew, beginDateNew, projectNum);
                if(checkFlag){
                    return new QuestionUpdateCheckDTO("Y");
                }else {
                    return new QuestionUpdateCheckDTO("N");
                }
            }
        }
    }

    public PropertyRectifyExcelListDTO getHouseInfoByRoomNum(String roomNum) {
        HouseInfoEntity houseInfo=propertyRectifyCRMRepository.getHouseInfoByRoomNum(roomNum);
        PropertyRectifyExcelListDTO PropertyRectifyExcel=new PropertyRectifyExcelListDTO();
        if(houseInfo!=null){
            if(!StringUtil.isEmpty(houseInfo.getCity())){//城市
                PropertyRectifyExcel.setCity(houseInfo.getCity());
            }
            if(!StringUtil.isEmpty(houseInfo.getCityNum())){//城市编码
                PropertyRectifyExcel.setCityNum(houseInfo.getCityNum());
            }
            if(!StringUtil.isEmpty(houseInfo.getArea())){//地块
                PropertyRectifyExcel.setArea(houseInfo.getArea());
            }
            if(!StringUtil.isEmpty(houseInfo.getAreaNum())){//地块编码
                PropertyRectifyExcel.setAreaNum(houseInfo.getAreaNum());
            }
            if(!StringUtil.isEmpty(houseInfo.getProjectName())){//项目
                PropertyRectifyExcel.setProject(houseInfo.getProjectName());
            }
            if(!StringUtil.isEmpty(houseInfo.getProjectNum())){//项目编码
                PropertyRectifyExcel.setProjectNum(houseInfo.getProjectNum());
            }
            if(!StringUtil.isEmpty(houseInfo.getBuildingNum())){//楼栋编码
                PropertyRectifyExcel.setBuildNum(houseInfo.getBuildingNum());
                if(!StringUtil.isEmpty(houseInfo.getBuildingSale())){//楼栋
                    PropertyRectifyExcel.setBuild(houseInfo.getBuildingSale());
                }else if(!StringUtil.isEmpty(houseInfo.getBuildingRecord())){
                    PropertyRectifyExcel.setBuild(houseInfo.getBuildingRecord());
                }else{
                    String build=houseInfo.getBuildingNum().substring(houseInfo.getBuildingNum().lastIndexOf("-")+1, houseInfo.getBuildingNum().length());
                    if("#G".equals(build)){
                        PropertyRectifyExcel.setBuild("公共区域");
                    }else{
                        PropertyRectifyExcel.setBuild("");
                    }
                }
            }
            if(!StringUtil.isEmpty(houseInfo.getUnitNum())){//单元编码
                PropertyRectifyExcel.setUnitNum(houseInfo.getUnitNum());
                if(!StringUtil.isEmpty(houseInfo.getUnit())){//单元
                    PropertyRectifyExcel.setUnit(houseInfo.getUnit());
                }else{
                    String unit=houseInfo.getUnitNum().substring(houseInfo.getUnitNum().lastIndexOf("-")+1, houseInfo.getUnitNum().length());
                    if("#G".equals(unit)){
                        PropertyRectifyExcel.setUnit("公共区域");
                    }else if("#".equals(unit)){
                        PropertyRectifyExcel.setUnit("无单元");
                    }else{
                        PropertyRectifyExcel.setUnit("");
                    }
                }
            }
            if(!StringUtil.isEmpty(houseInfo.getFloorNum())) {//楼层编码
                PropertyRectifyExcel.setFloorNum(houseInfo.getFloorNum());
                if(!StringUtil.isEmpty(houseInfo.getFloor())) {//楼层
                    PropertyRectifyExcel.setFloor(houseInfo.getFloor());
                }else{
                    String floor=houseInfo.getFloorNum().substring(houseInfo.getFloorNum().lastIndexOf("-")+1,houseInfo.getFloorNum().length());
                    if("#G".equals(floor)){
                        PropertyRectifyExcel.setFloor("公共区域");
                    }else{
                        PropertyRectifyExcel.setFloor("");
                    }
                }
            }
            if(!StringUtil.isEmpty(houseInfo.getRoomName())) {//房屋
                PropertyRectifyExcel.setRoom(houseInfo.getRoomName());
            }
            if(!StringUtil.isEmpty(houseInfo.getRoomNum())) {//房屋编码
                PropertyRectifyExcel.setRoomNum(houseInfo.getRoomNum());
            }
        }
        return PropertyRectifyExcel;
    }


    public List<PropertyRectifyExcelListDTO> getRectifyExcelList(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage) {

        Map map = new HashMap();
        //保证URL和表单提交的数据一致
        String area = propertyRectifyCRMSelDTO.getArea();
        if (area != null && !area.equals("") && area.contains("%23")) {
            area = area.replaceAll("%23", "#");
        }
        String roomId = propertyRectifyCRMSelDTO.getRoomId();
        if (roomId != null && !roomId.equals("") && roomId.contains("%23")) {
            roomId = roomId.replaceAll("%23", "#");
        }
        String buildingId=propertyRectifyCRMSelDTO.getBuildingId();
        if (buildingId != null && !buildingId.equals("") && buildingId.contains("%23")) {
            buildingId = buildingId.replaceAll("%23", "#");
        }
        String unitId=propertyRectifyCRMSelDTO.getUnitId();
        if (unitId != null && !unitId.equals("") && unitId.contains("%23")) {
            unitId = unitId.replaceAll("%23", "#");
        }
        String floorId=propertyRectifyCRMSelDTO.getFloorId();
        if (floorId != null && !floorId.equals("") && floorId.contains("%23")) {
            floorId = floorId.replaceAll("%23", "#");
        }
        map.put("projectId", propertyRectifyCRMSelDTO.getProjectId());
        map.put("proType", propertyRectifyCRMSelDTO.getProType());
        map.put("oneType", propertyRectifyCRMSelDTO.getOneType());
        map.put("twoType", propertyRectifyCRMSelDTO.getTwoType());
        map.put("threeType", propertyRectifyCRMSelDTO.getThreeType());
        map.put("caseState", propertyRectifyCRMSelDTO.getCaseState());
        map.put("buildingId", buildingId);
        map.put("unitId", unitId);
        map.put("area",area);
        map.put("floorId", floorId);
        map.put("roomId", roomId);
        map.put("startDate", propertyRectifyCRMSelDTO.getStartDate());
        map.put("endDate", propertyRectifyCRMSelDTO.getEndDate());
        map.put("problemDesc", propertyRectifyCRMSelDTO.getProblemDesc());
        map.put("userProject", propertyRectifyCRMSelDTO.getUserProject());
        map.put("supplier", propertyRectifyCRMSelDTO.getSupplier());
        map.put("planNum", propertyRectifyCRMSelDTO.getPlanNum());
        map.put("createByName", "");
        map.put("dealPeopleName", "");
        map.put("sendUserName", "");
        map.put("updateUserName", "");
        map.put("bewrite", "");
        map.put("rectifyId", "");
        map.put("successOrFailure", "0");//调用crm是否成功
        if(!"".equals(propertyRectifyCRMSelDTO.getSuccessOrFailure()) && propertyRectifyCRMSelDTO.getSuccessOrFailure()!=null){
            map.put("successOrFailure", propertyRectifyCRMSelDTO.getSuccessOrFailure());//调用crm是否成功
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getCreateByName()) && propertyRectifyCRMSelDTO.getCreateByName() != null) {
            map.put("createByName", "%" + propertyRectifyCRMSelDTO.getCreateByName() + "%");
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getDealPeopleName()) && propertyRectifyCRMSelDTO.getDealPeopleName() != null) {
            map.put("dealPeopleName", "%" + propertyRectifyCRMSelDTO.getDealPeopleName() + "%");
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getSenUserName()) && propertyRectifyCRMSelDTO.getSenUserName() != null) {
            map.put("sendUserName", "%" + propertyRectifyCRMSelDTO.getSenUserName() + "%");
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getBewrite()) && propertyRectifyCRMSelDTO.getBewrite() != null) {
            map.put("bewrite", "%" + propertyRectifyCRMSelDTO.getBewrite() + "%");
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getUpcloseName()) && propertyRectifyCRMSelDTO.getUpcloseName() != null) {
            map.put("updateUserName", "%" + propertyRectifyCRMSelDTO.getUpcloseName() + "%");
        }
        if (!"".equals(propertyRectifyCRMSelDTO.getRectifyId()) && propertyRectifyCRMSelDTO.getRectifyId() != null) {
            map.put("rectifyId", "%" + propertyRectifyCRMSelDTO.getRectifyId() + "%");
        }
        List<Object[]> list = propertyRectifyCRMRepository.getQuestionListExcel(map, webPage);
        List<Object []> imgList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            List<String> imageIdList = new ArrayList<String>();
            for (Object[] obj : list) {
                imageIdList.add(obj[0] == null ? "" : obj[0].toString());
            }
            //根据idList查询随手拍整改图片信息
            imgList = propertyRectifyCRMRepository.getRectifyImageList(imageIdList);
        }

        List<PropertyRectifyExcelListDTO> rectifyExcelList = new ArrayList<PropertyRectifyExcelListDTO>();
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                PropertyRectifyExcelListDTO rectifyExcel = new PropertyRectifyExcelListDTO();
                rectifyExcel.setRectifyId(obj[0] == null ? "" : obj[0].toString());             //问题ID 主键
                rectifyExcel.setLimitTime(obj[1] == null ? null : (Date) obj[1]);               //整改时限
                rectifyExcel.setProblemDescription(obj[2] == null ? "" : obj[2].toString());    //问题描述
                rectifyExcel.setAddress(obj[3] == null ? "" : obj[3].toString());               //房屋位置
                rectifyExcel.setFirstTypeName(obj[4] == null ? "" : obj[4].toString());         //一级分类
                rectifyExcel.setSecondTyoeName(obj[5] == null ? "" : obj[5].toString());        //二级分类
                rectifyExcel.setThirdTypeName(obj[6] == null ? "" : obj[6].toString());         //三级分类
                rectifyExcel.setSupplierName(obj[7] == null ? "" : obj[7].toString());          //供应商
                rectifyExcel.setDealPeopleName(obj[8] == null ? "" : obj[8].toString());        //处理人  整改人 接单人
                rectifyExcel.setCreateDate(obj[9] == null ? null : (Date) obj[9]);               //创建时间
                rectifyExcel.setRectifyState(obj[10] == null ? "" : obj[10].toString());        //状态
                rectifyExcel.setCity(obj[11] == null ? "" : obj[11].toString());                //城市
                rectifyExcel.setCityNum(obj[12] == null ? "" : obj[12].toString());             //城市编码
                rectifyExcel.setArea(obj[13] == null ? "" : obj[13].toString());                //地块
                rectifyExcel.setAreaNum(obj[14] == null ? "" : obj[14].toString());             //地块编码
                rectifyExcel.setProject(obj[15] == null ? "" : obj[15].toString());             //项目
                rectifyExcel.setProjectNum(obj[16] == null ? "" : obj[16].toString());          //项目编码
                rectifyExcel.setCreateBy(obj[27] == null ? "" : obj[27].toString());            //创建人
                rectifyExcel.setLocaltion(obj[28] == null ? "" : obj[28].toString());            //部位
                rectifyExcel.setDealResult(obj[29] == null ? "" : obj[29].toString());          //整改信息
                rectifyExcel.setDutyTaskDate(obj[30] == null ? null : (Date)obj[30]);           //截单时间
                rectifyExcel.setUpdateUserName(obj[31] == null ? "" : obj[31].toString());//填报人
                rectifyExcel.setUpdateUserDate(obj[33] == null ? null : (Date)obj[33]);//填报时间  obj 32 时间完成时间
                rectifyExcel.setCreatePhone(obj[34] == null ? "" : obj[34].toString()); //创建人联系电话
                rectifyExcel.setRepairPhone(obj[35] == null ? "" : obj[35].toString());//整改人联系电话
                rectifyExcel.setProblemType(obj[36] == null ? "" : obj[36].toString());//简要描述
                rectifyExcel.setPlanNum(obj[37] == null ? "" : obj[37].toString());//计划编码
                rectifyExcel.setRepairManager(obj[38] == null ? "" : obj[38].toString());//整改责任人
                //楼栋和楼栋编码
                if(obj[19] != null){
                    rectifyExcel.setBuildNum(obj[19] == null ? "" : obj[19].toString());        //楼栋编码
                    if(obj[17] != null){                                //备案楼号
                        rectifyExcel.setBuild(obj[17] == null ? "" : obj[17].toString());
                    }else if(obj[18] != null){                          //预售
                        rectifyExcel.setBuild(obj[18].toString());
                    }else{                                                                      //判断公共区域
                        String build=rectifyExcel.getBuildNum().substring(rectifyExcel.getBuildNum().lastIndexOf("-")+1, rectifyExcel.getBuildNum().length());
                        if("#G".equals(build)){
                            rectifyExcel.setBuild("公共区域");
                        }else{
                            rectifyExcel.setBuild("");
                        }
                    }
                }
                //判断单元 公共区域和无单元
                if(obj[21] != null){//单元编码
                    rectifyExcel.setUnitNum(obj[21].toString());
                    if(obj[20] != null){//单元
                        rectifyExcel.setUnit(obj[20].toString());
                    }else{
                        String unit=rectifyExcel.getUnitNum().substring(rectifyExcel.getUnitNum().lastIndexOf("-")+1, rectifyExcel.getUnitNum().length());
                        if("#G".equals(unit)){
                            rectifyExcel.setUnit("公共区域");
                        }else if("#".equals(unit)){
                            rectifyExcel.setUnit("无单元");
                        }else{
                            rectifyExcel.setUnit("");
                        }
                    }
                }
                //判断楼层的公共区域
                if(obj[23] != null) {//楼层编码
                    rectifyExcel.setFloorNum(obj[23].toString());
                    if(obj[22] != null) {//楼层
                        rectifyExcel.setFloor(obj[22].toString());
                    }else{
                        String floor=rectifyExcel.getFloorNum().substring(rectifyExcel.getFloorNum().lastIndexOf("-")+1,rectifyExcel.getFloorNum().length());
                        if("#G".equals(floor)){
                            rectifyExcel.setFloor("公共区域");
                        }else{
                            rectifyExcel.setFloor("");
                        }
                    }
                }
                rectifyExcel.setRoom(obj[24] == null ? "" : obj[24].toString());                //房间
                rectifyExcel.setRoomNum(obj[25] == null ? "" : obj[25].toString());             //房间编码
                rectifyExcel.setRealityDate(obj[26] == null ? null : (Date)obj[26]);            //整改完成期限
                //图片删除list
                List<Object[]> del = new ArrayList<>();
                if (imgList != null && imgList.size() > 0) {
                    List<String> imageList = new ArrayList<String>();
                    for (Object[] img : imgList) {
                        //判断外键是否相等随手拍ID
                        if (img[0].toString().equals(rectifyExcel.getRectifyId())) {
                            //随手拍图片
                            imageList.add(img[1].toString());
                            del.add(img);
                        }
                    }
                    imgList.removeAll(del);
                    rectifyExcel.setImageList(imageList);
                }
                rectifyExcelList.add(rectifyExcel);
            }
        }
        return rectifyExcelList;
    }

    @Override
    public String exportNewExcel(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO,OutputStream out) throws IOException {
        PropertyExcelRectify<PropertyRectifyExcelDTO> ex=new PropertyExcelRectify<PropertyRectifyExcelDTO>();
        List<PropertyRectifyExcelDTO> propertyRectifyExcelDTO=new ArrayList<PropertyRectifyExcelDTO>();
        List<PropertyRectifyExcelListDTO> list = null;
        if(!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getProjectId())){
            list = getRectifyExcelList(propertyRectifyCRMSelDTO, null);
        }

//        String[] titles = {"序号", "整改单ID", "是否超期","部位", "问题描述", "位置", "一级分类", "二级分类", "三级分类", "供应商", "整改责任人", "登记人", "登记时间", "状态", "城市", "地块", "项目", "楼栋", "单元", "楼层", "房间"};
        String[] titles = {"序号","整改单ID", "是否超期","登记人","登记时间","整改期限","部位","问题描述","整改信息","位置","一级分类", "二级分类", "三级分类","供应商", "内部负责人","接单人","接单时间","填报人","完成时间","登记人电话","整改人电话","状态", "城市", "地块", "项目", "楼栋", "单元", "楼层", "房间","简要描述","计划编码","问题图片","问题图片","问题图片" };

        if(list!=null && list.size()>0){
            int i=1;
            for(PropertyRectifyExcelListDTO propertyRectifyExcelList:list){
                PropertyRectifyExcelDTO propertyRectifyExcel=new PropertyRectifyExcelDTO();
                propertyRectifyExcel.setSerialNumber(i++);//序号
                propertyRectifyExcel.setRectifyId(propertyRectifyExcelList.getRectifyId());//整改单id
                if (propertyRectifyExcelList.getLimitTime() != null) {
                    if ((propertyRectifyExcelList.getRealityDate() != null && propertyRectifyExcelList.getLimitTime().after(propertyRectifyExcelList.getRealityDate()))
                            || propertyRectifyExcelList.getLimitTime().before(new Date())) {
                        propertyRectifyExcel.setOverdue("已超期");//是否超期
                    } else {
                        propertyRectifyExcel.setOverdue("进行中");//是否超期
                    }
                }
                propertyRectifyExcel.setCreateBy(propertyRectifyExcelList.getCreateBy());//创建人
                propertyRectifyExcel.setCreateDate(DateUtils.parse(DateUtils.format(propertyRectifyExcelList.getCreateDate(), DateUtils.FORMAT_SHORT),DateUtils.FORMAT_SHORT));//创建时间
                propertyRectifyExcel.setLimitTime(propertyRectifyExcelList.getLimitTime());//整改时限
                propertyRectifyExcel.setLocaltion(propertyRectifyExcelList.getLocaltion());//部位
                propertyRectifyExcel.setProblemDescription(propertyRectifyExcelList.getProblemDescription());//问题描述
                propertyRectifyExcel.setDealResult(propertyRectifyExcelList.getDealResult());//整改信息
                propertyRectifyExcel.setAddress(propertyRectifyExcelList.getAddress());//位置
                propertyRectifyExcel.setFirstTypeName(propertyRectifyExcelList.getFirstTypeName());// 一级分类
                propertyRectifyExcel.setSecondTyoeName(propertyRectifyExcelList.getSecondTyoeName());// 二级分类
                propertyRectifyExcel.setThirdTypeName(propertyRectifyExcelList.getThirdTypeName());// 三级分类
                propertyRectifyExcel.setSupplierName(propertyRectifyExcelList.getSupplierName());//供应商
                propertyRectifyExcel.setRepairManager(propertyRectifyExcelList.getRepairManager());//整改责任人
                propertyRectifyExcel.setDealPeopleName(propertyRectifyExcelList.getDealPeopleName());//接单人
                propertyRectifyExcel.setDutyTaskDate(propertyRectifyExcelList.getDutyTaskDate());//接单时间
                propertyRectifyExcel.setUpdateUserName(propertyRectifyExcelList.getUpdateUserName());//填报人
                propertyRectifyExcel.setUpdateUserDate(propertyRectifyExcelList.getUpdateUserDate());//填报时间
                propertyRectifyExcel.setCreatePhone(propertyRectifyExcelList.getCreatePhone());//创建人联系电话
                propertyRectifyExcel.setRepairPhone(propertyRectifyExcelList.getRepairPhone());//整改人联系电话
                propertyRectifyExcel.setRectifyState(propertyRectifyExcelList.getRectifyState());//状态
                propertyRectifyExcel.setCity(propertyRectifyExcelList.getCity());//城市
                propertyRectifyExcel.setArea(propertyRectifyExcelList.getArea());//地块
                propertyRectifyExcel.setProject(propertyRectifyExcelList.getProject());//项目
                propertyRectifyExcel.setBuild(propertyRectifyExcelList.getBuild());//楼栋
                propertyRectifyExcel.setUnit(propertyRectifyExcelList.getUnit());//单元
                propertyRectifyExcel.setFloor(propertyRectifyExcelList.getFloor());//楼层
                propertyRectifyExcel.setRoom(propertyRectifyExcelList.getRoom());//房间
                propertyRectifyExcel.setProblemType(propertyRectifyExcelList.getProblemType());//简要描述
                propertyRectifyExcel.setPlanNum(propertyRectifyExcelList.getPlanNum());//计划编码
                //校验是否导出图片
                if(!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getCheckImage()) && "1".equals(propertyRectifyCRMSelDTO.getCheckImage())){
                    if(propertyRectifyExcelList.getImageList()!=null  && propertyRectifyExcelList.getImageList().size()>0){
                        for(String imagnUrl:propertyRectifyExcelList.getImageList()){
                            try {
                                URL url = new URL(imagnUrl.toString());
                                //打开链接
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                //设置请求方式为"GET"
                                conn.setRequestMethod("GET");
                                //超时响应时间为5秒
                                conn.setConnectTimeout(5 * 1000);
                                //通过输入流获取图片数据
                                InputStream inStream = conn.getInputStream();
                                //得到图片的二进制数据，以二进制封装得到数据
                                byte[] data = readInputStream(inStream);
                                if(propertyRectifyExcel.getImage1() != null && propertyRectifyExcel.getImage1().length>0){
                                    if(propertyRectifyExcel.getImage2() != null && propertyRectifyExcel.getImage2().length>0){
                                        if(propertyRectifyExcel.getImage3() != null && propertyRectifyExcel.getImage3().length>0){

                                        }else{
                                            propertyRectifyExcel.setImage3(data);
                                        }
                                    }else{
                                        propertyRectifyExcel.setImage2(data);
                                    }
                                }else{
                                    propertyRectifyExcel.setImage1(data);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
                propertyRectifyExcelDTO.add(propertyRectifyExcel);
            }
        }

        ex.exportExcel("问题清单", titles, propertyRectifyExcelDTO, out, "yyyy-MM-dd");

        return "";
    }

    @Override
    public ApiResult deleteQeustion(String rectifyId, UserPropertyStaffEntity user) {
        try {
            PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(rectifyId);
            if(propertyRectifyCRMEntity!=null){
                propertyRectifyCRMEntity.setRectifyState("已废弃");
                propertyRectifyCRMEntity.setModifyDate(new Date());
                propertyRectifyCRMEntity.setToVoidBy(user.getStaffName());//废弃操作人
                propertyRectifyCRMEntity.setToVoidDate(new Date());//废弃时间
                uploadCRM(propertyRectifyCRMEntity);
                propertyRectifyCRMRepository.updateQuestion(propertyRectifyCRMEntity);
                return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), "1");
            }
            return new ErrorApiResult("");


        }catch (Exception e){
            return new ErrorApiResult("error_00000035"+e);
        }
    }

    @Override
    public List<PropertyRectifyListMagicDTO> getPrintList(PropertyRectifyMagicDTO rectifyMagicDTO, WebPage webPage) {
        Map map = new HashMap();
        //保证URL和表单提交的数据一致
        String roomNum = rectifyMagicDTO.getRoomNum();
        if (!StringUtil.isEmpty(roomNum) && roomNum.contains("%23")) {
            roomNum = roomNum.replaceAll("%23", "#");//房间编码
        }
        map.put("projectNum", rectifyMagicDTO.getProjectNum());//项目编码
        map.put("cityNum", rectifyMagicDTO.getCityNum());//城市编码
        map.put("planNum",rectifyMagicDTO.getPlanNum());//活动计划编码
        map.put("roomNum",roomNum);//房间编码
        map.put("state",rectifyMagicDTO.getState());//办理状态
        map.put("userName",rectifyMagicDTO.getUserName());//客户姓名
        map.put("detype",rectifyMagicDTO.getDetype());//0 当前  1 所有
        map.put("startDate", rectifyMagicDTO.getStartDate());
        map.put("endDate", rectifyMagicDTO.getEndDate());
        map.put("userProject", rectifyMagicDTO.getUserProject());
        map.put("planType", rectifyMagicDTO.getPlanType());
        map.put("successOrFailure", rectifyMagicDTO.getSuccessOrFailure());
        String successOrFa="0";
        if("1".equals(rectifyMagicDTO.getSuccessOrFailure())){
            successOrFa="1";
        }
        List<PropertyRectifyListMagicDTO> rectifyListMagicList=new ArrayList<PropertyRectifyListMagicDTO>();
        List<Object[]> list = new ArrayList<>();
        if("11".equals(rectifyMagicDTO.getPlanType())){
            list=propertyRectifyCRMRepository.getPrintAcceptanceList(map, webPage);
        }else if("12".equals(rectifyMagicDTO.getPlanType())){
            list=propertyRectifyCRMRepository.getPrintHouseOpenList(map, webPage);
        }else if("13".equals(rectifyMagicDTO.getPlanType())){
            list=propertyRectifyCRMRepository.getPrintList(map, webPage);
        }
        if(list!=null && !list.isEmpty()){
            for(Object[] obj : list){
                //hh.projectNum, hh.projectName, hh.roomNum, hh.address, hd.customerName, hd.acceptanceStatus, hd.inspectionTime, count(1) as coun
                PropertyRectifyListMagicDTO rectifyListMagicDTO=new PropertyRectifyListMagicDTO();
                rectifyListMagicDTO.setProjectNum(obj[0] == null ? "" : obj[0].toString());//项目编码
                rectifyListMagicDTO.setProjectName(obj[1] == null ? "" : obj[1].toString());//项目名称
                rectifyListMagicDTO.setRoomNum(obj[2] == null ? "" : obj[2].toString());//房间编码
                rectifyListMagicDTO.setRoomAddress(obj[3] == null ? "" : obj[3].toString());//房间位置
                rectifyListMagicDTO.setUserName(obj[4] == null ? "" : obj[4].toString());//客户姓名
                rectifyListMagicDTO.setPlanNum(rectifyMagicDTO.getPlanNum());
                rectifyListMagicDTO.setState(obj[5] == null ? "" : obj[5].toString());//办理状态
                rectifyListMagicDTO.setUserTime(obj[6] == null ? null: (Date)obj[6]);//签字时间
                if("11".equals(rectifyMagicDTO.getPlanType())){
                    rectifyListMagicDTO.setCount(propertyRectifyCRMRepository.getCountPintRectify("11",rectifyMagicDTO.getPlanNum(),rectifyListMagicDTO.getRoomNum(),successOrFa,rectifyMagicDTO.getCaseState()));//问题统计
                }else if("12".equals(rectifyMagicDTO.getPlanType())){
                    rectifyListMagicDTO.setCount(propertyRectifyCRMRepository.getCountPintRectify("12",rectifyMagicDTO.getPlanNum(),rectifyListMagicDTO.getRoomNum(),successOrFa,rectifyMagicDTO.getCaseState()));//问题统计
                }else if("13".equals(rectifyMagicDTO.getPlanType())){
                    rectifyListMagicDTO.setCount(propertyRectifyCRMRepository.getCountPintRectify("13",rectifyMagicDTO.getPlanNum(),rectifyListMagicDTO.getRoomNum(),successOrFa,rectifyMagicDTO.getCaseState()));//问题统计
                }
                rectifyListMagicDTO.setCaseState(rectifyMagicDTO.getCaseState());
                rectifyListMagicDTO.setSuccessOrFailure(successOrFa);
//                rectifyListMagicDTO.setCount(obj[7] == null ? "" : obj[7].toString());//问题统计
                rectifyListMagicDTO.setPlanType(rectifyMagicDTO.getPlanType());//11 内部预验 12工地开放  13 正式交房
                rectifyListMagicList.add(rectifyListMagicDTO);
            }
        }
        return rectifyListMagicList;
    }

    @Override
    public String printExportNewExcel(PropertyRectifyMagicDTO rectifyMagicDTO, OutputStream out) throws IOException {
        PropertyExcel<PropertyRectifyExcelMagicDTO> ex=new PropertyExcel<PropertyRectifyExcelMagicDTO>();
        List<PropertyRectifyExcelMagicDTO> propertyRectifyExcelDTO=new ArrayList<PropertyRectifyExcelMagicDTO>();

        List<PropertyRectifyListMagicDTO> list = getPrintList(rectifyMagicDTO, null);
        String[] titles = {"序号","项目名称","房间信息","客户姓名","办理状态","问题数量合计","签字时间"};
        if(list!=null && list.size()>0){
            int i=1;
            for(PropertyRectifyListMagicDTO rectifyListMagicDTO:list){
                PropertyRectifyExcelMagicDTO propertyRectifyExcel=new PropertyRectifyExcelMagicDTO();
                propertyRectifyExcel.setSerialNumber(i++);//序号
                propertyRectifyExcel.setProjectName(rectifyListMagicDTO.getProjectName());//项目名称
                propertyRectifyExcel.setRoomAddress(rectifyListMagicDTO.getRoomAddress());//房间地址
                propertyRectifyExcel.setUserName(rectifyListMagicDTO.getUserName());//客户姓名
                //办理状态
                if("11".equals(rectifyMagicDTO.getPlanType())){
                    if("start".equals(rectifyListMagicDTO.getState())){
                        propertyRectifyExcel.setState("验房开始");
                    }else{
                        propertyRectifyExcel.setState("验房结束");
                    }
                }else if("12".equals(rectifyMagicDTO.getPlanType())){
                    if("start".equals(rectifyListMagicDTO.getState())){
                        propertyRectifyExcel.setState("业主未到访");
                    }else if("finish".equals(rectifyListMagicDTO.getState())){
                        propertyRectifyExcel.setState("业主已到访");
                    }
                }else if("13".equals(rectifyMagicDTO.getPlanType())){
                    if("preservation".equals(rectifyListMagicDTO.getState())){
                        propertyRectifyExcel.setState("数据保存");
                    }else if("acceptanceBy".equals(rectifyListMagicDTO.getState())){
                        propertyRectifyExcel.setState("验收通过");
                    }else if("acceptanceNotThrough".equals(rectifyListMagicDTO.getState())){
                        propertyRectifyExcel.setState("验收未通过");
                    }else if("absence".equals(rectifyListMagicDTO.getState())){
                        propertyRectifyExcel.setState("业主未到访");
                    }
                }
                propertyRectifyExcel.setCount(rectifyListMagicDTO.getCount());//问题数量统计
                propertyRectifyExcel.setUserTime(rectifyListMagicDTO.getUserTime() == null ? "" : DateUtils.format(rectifyListMagicDTO.getUserTime(),DateUtils.FORMAT_LONG));//签字时间
                propertyRectifyExcelDTO.add(propertyRectifyExcel);
            }
        }
        ex.exportExcel("问题清单", titles, propertyRectifyExcelDTO, out, "yyyy-MM-dd");
        return "";
    }

    @Override
    public List<PropertyRectifyListMagicDTO> getSignaPrintList(PropertyRectifyMagicDTO rectifyMagicDTO, WebPage webPage) {
        Map map = new HashMap();
        //保证URL和表单提交的数据一致
        String roomNum = rectifyMagicDTO.getRoomNum();
        if (!StringUtil.isEmpty(roomNum) && roomNum.contains("%23")) {
            roomNum = roomNum.replaceAll("%23", "#");//房间编码
        }
        map.put("projectNum", rectifyMagicDTO.getProjectNum());//项目编码
        map.put("cityNum", rectifyMagicDTO.getCityNum());//城市编码
        map.put("planNum",rectifyMagicDTO.getPlanNum());//活动计划编码
        map.put("roomNum",roomNum);//房间编码
        map.put("state",rectifyMagicDTO.getState());//办理状态
        map.put("userName",rectifyMagicDTO.getUserName());//客户姓名
        map.put("detype",rectifyMagicDTO.getDetype());//0 当前  1 所有
        map.put("startDate", rectifyMagicDTO.getStartDate());
        map.put("endDate", rectifyMagicDTO.getEndDate());
        map.put("userProject", rectifyMagicDTO.getUserProject());
        map.put("planType", rectifyMagicDTO.getPlanType());
        map.put("successOrFailure", rectifyMagicDTO.getSuccessOrFailure());
        String successOrFa="0";
        if("1".equals(rectifyMagicDTO.getSuccessOrFailure())){
            successOrFa="1";
        }
        List<PropertyRectifyListMagicDTO> rectifyListMagicList=new ArrayList<PropertyRectifyListMagicDTO>();
        List<Object[]> list = propertyRectifyCRMRepository.getSignaPrintList(map, webPage);
        if(list!=null && !list.isEmpty()){
            for(Object[] obj : list){
                //hh.projectNum, hh.projectName, hh.roomNum, hh.address, hd.customerName, hd.acceptanceStatus, hd.inspectionTime, count(1) as coun
                PropertyRectifyListMagicDTO rectifyListMagicDTO=new PropertyRectifyListMagicDTO();
                rectifyListMagicDTO.setProjectNum(obj[0] == null ? "" : obj[0].toString());//项目编码
                rectifyListMagicDTO.setProjectName(obj[1] == null ? "" : obj[1].toString());//项目名称
                rectifyListMagicDTO.setRoomNum(obj[2] == null ? "" : obj[2].toString());//房间编码
                rectifyListMagicDTO.setRoomAddress(obj[3] == null ? "" : obj[3].toString());//房间位置
                rectifyListMagicDTO.setPlanNum(rectifyMagicDTO.getPlanNum());
//                rectifyListMagicDTO.setState(obj[4] == null ? "" : obj[4].toString());//办理状态
                rectifyListMagicDTO.setUserTime(obj[4] == null ? null: (Date)obj[4]);//签字时间

                if(!StringUtil.isEmpty(rectifyMagicDTO.getPlanType())) {
                    rectifyListMagicDTO.setCount(propertyRectifyCRMRepository.getCountPintRectify(rectifyMagicDTO.getPlanType(), rectifyMagicDTO.getPlanNum(), rectifyListMagicDTO.getRoomNum(), successOrFa, rectifyMagicDTO.getCaseState()));//问题统计
                }else{
                    rectifyListMagicDTO.setCount("0");
                }
                List<Object[]> user=houseRoomRepository.getUserCrmByRoomNum(rectifyListMagicDTO.getRoomNum());

                if(user!=null && user.size()>0){
                    String userNames="";
                    for(Object[] ob : user){
                        if(obj[0] !=null && !"".equals(ob[0].toString())){
                            userNames=userNames+ob[0].toString()+",";
                        }
                    }
                    rectifyListMagicDTO.setUserName(userNames.substring(0,userNames.length()-1));//客户姓名
                } else{
                    rectifyListMagicDTO.setUserName("");//客户姓名
                }
                rectifyListMagicDTO.setCaseState(rectifyMagicDTO.getCaseState());
                rectifyListMagicDTO.setSuccessOrFailure(successOrFa);
                rectifyListMagicDTO.setPlanType(rectifyMagicDTO.getPlanType());//11 内部预验 12工地开放  13 正式交房
                rectifyListMagicList.add(rectifyListMagicDTO);
            }
        }
        return rectifyListMagicList;
    }

    /**
     * 把图片以二进制流的方式保存
     *
     * @param inStream
     * @return
     * @throws Exception
     */

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
