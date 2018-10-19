package com.maxrocky.vesta.application.impl;

/**
 * Created by 27978 on 2016/8/2.
 */

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.OrganizeService;
import com.maxrocky.vesta.application.inf.PropertyRepairCRMService;
import com.maxrocky.vesta.application.inf.RepairClientService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Service
public class PropertyRepairCRMServiceImpl implements PropertyRepairCRMService {
    @Autowired
    OrganizeService organizeService;
    @Autowired
    PropertyRepairCRMRepository propertyRepairCRMRepository;

    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;

    @Autowired
    PropertyImageRepository propertyImageRepository;

    @Autowired
    ImgService imgService;

    @Autowired
    RepairClientService repairClientService;

    @Autowired
    HouseBuildingRepository houseBuildingRepository;

    @Autowired
    private PropertyRepairRepository propertyRepairRepository;

    @Autowired
    private PropertyRepairTaskRepository propertyRepairTaskRepository;

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 获取报修表列表
     */
    @Override
    public List<PropertyRepairCRMDTO> getQuestionList(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("projectId", propertyRectifyCRMSelDTO.getProjectId());
        map.put("proType", propertyRectifyCRMSelDTO.getProType());
        map.put("oneType", propertyRectifyCRMSelDTO.getOneType());
        map.put("twoType", propertyRectifyCRMSelDTO.getTwoType());
        map.put("threeType", propertyRectifyCRMSelDTO.getThreeType());
        map.put("caseState", propertyRectifyCRMSelDTO.getCaseState());
        map.put("buildingId", propertyRectifyCRMSelDTO.getBuildingId());
        map.put("unitId", propertyRectifyCRMSelDTO.getUnitId());
        map.put("floorId", propertyRectifyCRMSelDTO.getFloorId());
        map.put("roomId", propertyRectifyCRMSelDTO.getRoomId());
        map.put("startDate", propertyRectifyCRMSelDTO.getStartDate());
        map.put("endDate", propertyRectifyCRMSelDTO.getEndDate());
        map.put("problemDesc", propertyRectifyCRMSelDTO.getProblemDesc());
        map.put("userProject", propertyRectifyCRMSelDTO.getUserProject());
        map.put("area", propertyRectifyCRMSelDTO.getArea());

        List<Object[]> list = null;
        if(!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getProjectId())){
            list = propertyRepairCRMRepository.getQuestionList(map, webPage);
        }
        List<PropertyRepairCRMDTO> retList = new ArrayList<PropertyRepairCRMDTO>();
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                PropertyRepairCRMDTO propertyRepairCRMDTO = new PropertyRepairCRMDTO();
                propertyRepairCRMDTO.setCaseId(obj[0] == null ? "" : obj[0].toString());//问题id
                propertyRepairCRMDTO.setCaseDesc(obj[5] == null ? "" : obj[5].toString());//问题描述
                propertyRepairCRMDTO.setCaseState(obj[6] == null ? "" : obj[6].toString());//问题状态
                propertyRepairCRMDTO.setCreateDate(obj[8] == null ? null : (Date) obj[8]);//登记时间
                propertyRepairCRMDTO.setUserName(obj[9] == null ? "" : obj[9].toString());//记录人
                propertyRepairCRMDTO.setAddress(obj[10] == null ? "" : obj[10].toString());//房间位置
                //标准工时repairDate，ClassificationTemporaryTimeEntity
                if (obj[12] != null) {
                    //repairDate是id，去ClassificationTemporaryTimeEntity中查询graded为5的对应currentid对应的值
                    String repairDate = propertyRepairCRMRepository.getRepairDate(obj[12].toString());
                    propertyRepairCRMDTO.setStandardDate(repairDate);
                }
                propertyRepairCRMDTO.setProjectId(obj[13] == null ? "" : obj[13].toString());//项目编号
                propertyRepairCRMDTO.setProjectName(obj[14] == null ? "" : obj[14].toString());//项目名称
                propertyRepairCRMDTO.setBuildingId(obj[15] == null ? "" : obj[15].toString());//楼栋编号
                propertyRepairCRMDTO.setFloorId(obj[16] == null ? "" : obj[16].toString());//楼层编号
                propertyRepairCRMDTO.setUnitId(obj[17] == null ? "" : obj[17].toString());//单元编号
                propertyRepairCRMDTO.setOneType(obj[18] == null ? "" : obj[18].toString());//一级分类
                propertyRepairCRMDTO.setTwoType(obj[19] == null ? "" : obj[19].toString());//二级分类
                propertyRepairCRMDTO.setThreeType(obj[20] == null ? "" : obj[20].toString());//三级分类
                propertyRepairCRMDTO.setCasePlace(obj[21] == null ? "" : obj[21].toString());//问题部位
                propertyRepairCRMDTO.setRepairManager(obj[22] == null ? "" : obj[22].toString());//维修负责人

                retList.add(propertyRepairCRMDTO);
            }
        }
        return retList;
    }

    @Override
    public List<PropertyRepairCRMDTO> getQuestionListNew(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("projectId", propertyRectifyCRMSelDTO.getProjectId());
        map.put("proType", propertyRectifyCRMSelDTO.getProType());
        map.put("oneType", propertyRectifyCRMSelDTO.getOneType());
        map.put("twoType", propertyRectifyCRMSelDTO.getTwoType());
        map.put("threeType", propertyRectifyCRMSelDTO.getThreeType());
        map.put("caseState", propertyRectifyCRMSelDTO.getCaseState());
        map.put("buildingId", propertyRectifyCRMSelDTO.getBuildingId());
        map.put("unitId", propertyRectifyCRMSelDTO.getUnitId());
        map.put("floorId", propertyRectifyCRMSelDTO.getFloorId());
        map.put("roomId", propertyRectifyCRMSelDTO.getRoomId());
        map.put("startDate", propertyRectifyCRMSelDTO.getStartDate());
        map.put("endDate", propertyRectifyCRMSelDTO.getEndDate());
        map.put("problemDesc", propertyRectifyCRMSelDTO.getProblemDesc());
        map.put("userProject", propertyRectifyCRMSelDTO.getUserProject());
        map.put("area",propertyRectifyCRMSelDTO.getArea());
        List<Object[]> list = null;
        if(!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getProjectId())){
            list = propertyRepairCRMRepository.getQuestionListNew(map, webPage);
        }
        List<PropertyRepairCRMDTO> retList = new ArrayList<PropertyRepairCRMDTO>();
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                /**
                 *    hql.append(" r.REPAIR_ID,r.CONTENT,r.CREATE_DATE,h.ADDRESS,r.STATE,t.LABEL ");
                 * */
                PropertyRepairCRMDTO propertyRepairCRMDTO = new PropertyRepairCRMDTO();
                propertyRepairCRMDTO.setCaseId(obj[0] == null ? "" : obj[0].toString());//问题id
                propertyRepairCRMDTO.setCaseDesc(obj[1] == null ? "" : obj[1].toString());//问题描述
                propertyRepairCRMDTO.setCaseState(obj[4] == null ? "" : obj[4].toString());//问题状态
                propertyRepairCRMDTO.setCreateDate(obj[2] == null ? null : (Date) obj[2]);//登记时间
                propertyRepairCRMDTO.setAddress(obj[3] == null ? "" : obj[3].toString());//房间位置
                propertyRepairCRMDTO.setThreeType(obj[5] == null ? "" : obj[5].toString());//三级分类
                retList.add(propertyRepairCRMDTO);
            }
        }
        return retList;
    }


    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 获取报修详情
     */
    @Override
    public PropertyRepairCRMDTO getAdminQuestionById(PropertyRectifyAdminDTO propertyRectifyAdminDTO,String userid) {
        Object[] obj = propertyRepairCRMRepository.getRepairDetail(propertyRectifyAdminDTO.getRectifyId());
        String type="0";
        //如果当前处理人为当前登录人放入代办

        if(obj[27].toString()!=null){
            List<String> projectRole = organizeService.getProjectRoleByNum(userid, obj[27].toString());
            if(obj[29]!=null && obj[29].toString().equals(userid)){
                type="1";
            }else if(obj[30]!=null && obj[29]==null){
                for(String per:projectRole){
                    if(obj[30].toString().equals(per)){
                        type="1";
                        continue;
                    }
                }
            }
        }
        PropertyRepairCRMDTO propertyRepairCRMDTO = new PropertyRepairCRMDTO();
        propertyRepairCRMDTO.setHandleType(type);//是否有权限接单
        propertyRepairCRMDTO.setCaseId(obj[0] == null ? "" : obj[0].toString());//问题id
        propertyRepairCRMDTO.setCreateDate(obj[1] == null ? null : (Date) obj[1]);//登记时间
        propertyRepairCRMDTO.setCaseState(obj[2] == null ? "" : obj[2].toString());//问题状态
        propertyRepairCRMDTO.setUserName(obj[3] == null ? "" : obj[3].toString());//记录人
        propertyRepairCRMDTO.setUserPhone(obj[4] == null ? "" : obj[4].toString());//联系电话
        propertyRepairCRMDTO.setAddress(obj[5] == null ? "" : obj[5].toString());//房间位置
        propertyRepairCRMDTO.setCaseDesc(obj[6] == null ? "" : obj[6].toString());//问题描述
        //标准工时repairDate从ClassificationTemporaryTimeEntity中获取，graded为5
        if (obj[7] != null) {
            String repairDate = propertyRepairCRMRepository.getRepairDate(obj[7].toString());
            propertyRepairCRMDTO.setStandardDate(repairDate);
        }
        propertyRepairCRMDTO.setDealMode(obj[8] == null ? "" : obj[8].toString());//处理方式
        propertyRepairCRMDTO.setRepairManager(obj[9] == null ? "" : obj[9].toString());//维修负责人
        propertyRepairCRMDTO.setTaskDate(obj[10] == null ? null : (Date) obj[10]);//接单时间
        propertyRepairCRMDTO.setDealCompleteDate(obj[11] == null ? null : (Date) obj[11]);//完工时间
        propertyRepairCRMDTO.setDealWay(obj[12] == null ? "" : obj[12].toString());//处理过程
        propertyRepairCRMDTO.setCasePlace(obj[13] == null ? "" : obj[13].toString());//问题部位
        propertyRepairCRMDTO.setOneType(obj[14] == null ? "" : obj[14].toString());//一级分类
        propertyRepairCRMDTO.setTwoType(obj[15] == null ? "" : obj[15].toString());//二级分类
        propertyRepairCRMDTO.setThreeType(obj[16] == null ? "" : obj[16].toString());//三级分类
        propertyRepairCRMDTO.setRepairMode(obj[17] == null ? "" : obj[17].toString());//维修方式
        propertyRepairCRMDTO.setCompanyOne(obj[18] == null ? "" : obj[18].toString());//责任单位1
        propertyRepairCRMDTO.setCompanyTwo(obj[19] == null ? "" : obj[19].toString());//责任单位2
        propertyRepairCRMDTO.setCompanyThree(obj[20] == null ? "" : obj[20].toString());//责任单位3
        propertyRepairCRMDTO.setUserNum(obj[21] == null ? "" : obj[21].toString());//记录人编号
        propertyRepairCRMDTO.setClassifyOne(obj[22] == null ? "" : obj[22].toString());//一级分类id
        propertyRepairCRMDTO.setClassifyTwo(obj[23] == null ? "" : obj[23].toString());//二级分类id
        propertyRepairCRMDTO.setClassifyThree(obj[24] == null ? "" : obj[24].toString());//三级分类id
        propertyRepairCRMDTO.setRepairWay(obj[25] == null ? "" : obj[25].toString());//维修方式id
        propertyRepairCRMDTO.setThirdRepair(obj[26] == null ? "" : obj[26].toString());//维修第三方
        propertyRepairCRMDTO.setProjectId(obj[27] == null ? "" : obj[27].toString());//项目编号
        propertyRepairCRMDTO.setStandardDate(obj[28] == null ? "" : obj[28].toString());//维修工时
        //根据repairId获取对应报修图片
        if (obj[0] != null) {
            //获取报修图片
            List<Object[]> repairList = propertyRepairCRMRepository.getImageList(obj[0].toString(), 0 + "");
            List<RectifyImageDTO> rectifyImageDTOList = new ArrayList<>();
            if (repairList != null) {
                for (Object[] object : repairList) {
                    RectifyImageDTO rectifyImageDTO = new RectifyImageDTO();
                    rectifyImageDTO.setImageId(object[0] == null ? "" : object[0].toString());//图片id
                    rectifyImageDTO.setCaseId(object[1] == null ? "" : object[1].toString());//问题id
                    rectifyImageDTO.setImageUrl(object[2] == null ? "" : object[2].toString());//图片路径
                    rectifyImageDTOList.add(rectifyImageDTO);
                }
                propertyRepairCRMDTO.setQuestionImageList(rectifyImageDTOList);
            }
            //获取处理图片
            List<Object[]> repairList2 = propertyRepairCRMRepository.getImageList(obj[0].toString(), 2 + "");
            List<RectifyImageDTO> rectifyImageDTOList2 = new ArrayList<>();
            if (repairList2 != null) {
                for (Object[] object : repairList2) {
                    RectifyImageDTO rectifyImageDTO = new RectifyImageDTO();
                    rectifyImageDTO.setImageId(object[0] == null ? "" : object[0].toString());//图片id
                    rectifyImageDTO.setCaseId(object[1] == null ? "" : object[1].toString());//问题id
                    rectifyImageDTO.setImageUrl(object[2] == null ? "" : object[2].toString());//图片路径
                    rectifyImageDTOList2.add(rectifyImageDTO);
                }
                propertyRepairCRMDTO.setRepairImageList(rectifyImageDTOList2);
            }
        }
        return propertyRepairCRMDTO;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 作废
     */
    @Override
    public void deleteAdminQeustion(PropertyRectifyAdminDTO propertyRectifyAdminDTO) {
        PropertyRepairCRMEntity propertyRepairCRMEntity = propertyRepairCRMRepository.getById(propertyRectifyAdminDTO.getRectifyId());
        propertyRepairCRMEntity.setState("closed");
        propertyRepairCRMEntity.setModifyDate(new Date());
        //uploadCRM(propertyRepairCRMEntity);
        repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
        propertyRepairCRMRepository.update(propertyRepairCRMEntity);
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 接受任务
     */
    @Override
    public void acceptTask(UserInformationEntity user, PropertyRepairCRMDTO propertyRepairCRMDTO) {
        try{
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(propertyRepairCRMDTO.getCaseId());
            if (propertyRepairInfo!=null && "已受理".equals(propertyRepairInfo.getState())){
                propertyRepairInfo.setUserId(user.getStaffId());
                propertyRepairInfo.setState("处理中");//状态由正在派工改为处理中
                propertyRepairInfo.setModifyBy(user.getStaffId());
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskNode("抢单成功");//任务节点
                propertyRepairTaskEntity.setTaskUserId(user.getStaffId());//工单任务人
                propertyRepairTaskEntity.setTaskUserPhone(user.getMobile());//任务人电话
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                propertyRepairTaskEntity.setReadStatus("0");
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setTaskStatus("4");//4为维修人员接单
                        propertyRepairTaskEntity.setTaskName("处理中");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("4");//4为维修人员接单
                        propertyRepairTaskEntity.setTaskUserType("0");//0为维修人员;1为客服人员
                        propertyRepairTaskEntity.setTaskContent("维修专员" + user.getStaffName()+ " " + user.getMobile() + "将与您联系。");//任务内容
                    }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("2");//2为客服接单
                        propertyRepairTaskEntity.setTaskName("处理中");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("2");//2为客服接单
                        propertyRepairTaskEntity.setTaskUserType("1");//0为维修人员;1为客服人员
                        propertyRepairTaskEntity.setTaskContent("客服专员" + user.getStaffName()+ " " + user.getMobile() + "将与您联系。");//任务内容
                    }
                }else{
                    propertyRepairInfo.setTaskStatus("4");//4为维修人员接单
                    propertyRepairTaskEntity.setTaskName("处理中");//任务名称
                    propertyRepairTaskEntity.setTaskStatus("4");//4为维修人员接单
                    propertyRepairTaskEntity.setTaskUserType("0");//0为维修人员;1为客服人员
                    propertyRepairTaskEntity.setTaskContent("维修专员" + user.getStaffName()+ " " + user.getMobile() + "将与您联系。");//任务内容
                }
                PropertyRepairCRMEntity propertyRepairCRMEntity=propertyRepairCRMRepository.getById(propertyRepairInfo.getRepairId());
                if(propertyRepairCRMEntity.getDealPeople()!=null && !"".equals(propertyRepairCRMEntity.getDealPeople())){
                    if(!propertyRepairCRMEntity.getDealPeople().equals(user.getStaffId())){
                        return;
                    }
                }
                propertyRepairCRMEntity.setRepairId(propertyRepairInfo.getRepairId());
                propertyRepairCRMEntity.setRoomId(propertyRepairInfo.getRoomId());
                if(propertyRepairCRMDTO.getDealMode()!=null){
                    propertyRepairCRMEntity.setDealMode(propertyRepairCRMDTO.getDealMode());
                }
                propertyRepairCRMEntity.setState("processing");
                if(!StringUtil.isEmpty(propertyRepairCRMDTO.getOneType())){
                    propertyRepairCRMEntity.setClassifyOne(propertyRepairCRMDTO.getOneType());
                }
                if(!StringUtil.isEmpty(propertyRepairCRMDTO.getTwoType())){
                    propertyRepairCRMEntity.setClassifyTwo(propertyRepairCRMDTO.getTwoType());
                }

                if(!StringUtil.isEmpty(propertyRepairCRMDTO.getThreeType())){
                    propertyRepairCRMEntity.setClassifyThree(propertyRepairCRMDTO.getThreeType());
                }
                if(!StringUtil.isEmpty(propertyRepairCRMDTO.getRepairMode())){
                    propertyRepairCRMEntity.setMode(propertyRepairCRMDTO.getRepairMode());
                }

                if(!StringUtil.isEmpty(propertyRepairCRMDTO.getStandardDate())){
                    propertyRepairCRMEntity.setRepairDate(propertyRepairCRMDTO.getStandardDate());
                }
                propertyRepairCRMEntity.setDealPeople(propertyRepairTaskEntity.getTaskUserId());
                propertyRepairCRMEntity.setRepairManager(user.getUserName());
                propertyRepairCRMEntity.setDealPhone(propertyRepairTaskEntity.getTaskUserPhone());
                propertyRepairCRMEntity.setTaskDate(propertyRepairTaskEntity.getTaskDate());
                propertyRepairCRMEntity.setModifyDate(propertyRepairInfo.getModifyDate());
                propertyRepairCRMRepository.update(propertyRepairCRMEntity);
                //调用webService接口,向CRM传送数据
                new Thread() {
                    public void run() {
                        repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
                    }
                }.start();
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 提交
     */
    @Override
    public void tijiao(PropertyRepairCRMDTO propertyRepairCRMDTO) {
        PropertyRepairCRMEntity propertyRepairCRMEntity = propertyRepairCRMRepository.getById(propertyRepairCRMDTO.getCaseId());
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getOneType())){
            propertyRepairCRMEntity.setClassifyOne(propertyRepairCRMDTO.getOneType());

        }
        PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(propertyRepairCRMDTO.getCaseId());

        propertyRepairCRMEntity.setState("completed");
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getTwoType())){
            propertyRepairCRMEntity.setClassifyTwo(propertyRepairCRMDTO.getTwoType());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getThreeType())){
            propertyRepairCRMEntity.setClassifyThree(propertyRepairCRMDTO.getThreeType());

        }
        //维修方式？
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getRepairMode())){
            propertyRepairCRMEntity.setMode(propertyRepairCRMDTO.getRepairMode());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getDealMode())){
            propertyRepairCRMEntity.setDealMode(propertyRepairCRMDTO.getDealMode());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getCompanyOne())){
            propertyRepairCRMEntity.setDutyCompanyOne(propertyRepairCRMDTO.getCompanyOne());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getCompanyTwo())){
            propertyRepairCRMEntity.setDutyCompanyTwo(propertyRepairCRMDTO.getCompanyTwo());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getCompanyThree())){
            propertyRepairCRMEntity.setDutyCompanyThree(propertyRepairCRMDTO.getCompanyThree());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getRepairManager())){
            propertyRepairCRMEntity.setRepairManager(propertyRepairCRMDTO.getRepairManager());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getThirdRepair())){
            propertyRepairCRMEntity.setRepairCompany(propertyRepairCRMDTO.getThirdRepair());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getDealWay())){
            propertyRepairCRMEntity.setDealWay(propertyRepairCRMDTO.getDealWay());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getStandardDate())){
            propertyRepairCRMEntity.setRepairDate(propertyRepairCRMDTO.getStandardDate());
        }
        propertyRepairCRMEntity.setModifyDate(new Date());
        propertyRepairCRMEntity.setDealCompleteDate(new Date());
        propertyRepairInfo.setModifyDate(propertyRepairCRMEntity.getModifyDate());

        //添加图片
        //删除不在包含的图片
        if (propertyRepairCRMDTO.getReviewImage() != null && !propertyRepairCRMDTO.getReviewImage().isEmpty()) {
            propertyImageRepository.deleteByNotIds(propertyRepairCRMDTO.getReviewImage());
        } else {
            propertyImageRepository.deleteByFkId(propertyRepairCRMDTO.getCaseId(), "2");
        }
        //保存新增图片
        if (propertyRepairCRMDTO.getReviewImgFile() != null && propertyRepairCRMDTO.getReviewImgFile().length > 0) {
            for (int i = 0; i < propertyRepairCRMDTO.getReviewImgFile().length; i++) {
                if (propertyRepairCRMDTO.getReviewImgFile()[i] != null && !"".equals(propertyRepairCRMDTO.getReviewImgFile()[i].getOriginalFilename())) {
                    String fileName = imgService.uploadAdminImage(propertyRepairCRMDTO.getReviewImgFile()[i], ImgType.ACTIVITY);
                    String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                    fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                    PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                    propertyImageEntity.setImageId(propertyRepairCRMDTO.getUserNum() + IdGen.getDateId());//图片id
                    propertyImageEntity.setUploadDate(new Date());//上传日期
                    propertyImageEntity.setImgFkId(propertyRepairCRMEntity.getRepairId());//图片外键id
                    propertyImageEntity.setImagePath(fileName);//图片路径
                    propertyImageEntity.setImageType("2");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                    propertyImageEntity.setState("0");//状态:0为有效；1为无效
                    propertyImageRepository.saveImage(propertyImageEntity);
                }
            }
        }
        new Thread() {
            public void run() {
                String checkCrmState=repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
                if(!"200".equals(checkCrmState) && !"".equals(checkCrmState)){
                    //调用接口失败
                    propertyRepairCRMEntity.setFailNum(1);
                    propertyRepairCRMEntity.setFailType("1");
                    propertyRepairRepository.updateRepairCrm(propertyRepairCRMEntity);
                }
            }
        }.start();
        propertyRepairRepository.updateRepair(propertyRepairInfo);
//        repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
        propertyRepairCRMRepository.update(propertyRepairCRMEntity);
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 保存
     */
    @Override
    public void saveRepair(PropertyRepairCRMDTO propertyRepairCRMDTO) {
        PropertyRepairCRMEntity propertyRepairCRMEntity = propertyRepairCRMRepository.getById(propertyRepairCRMDTO.getCaseId());
        PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(propertyRepairCRMDTO.getCaseId());
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getOneType())){
            propertyRepairCRMEntity.setClassifyOne(propertyRepairCRMDTO.getOneType());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getTwoType())){
            propertyRepairCRMEntity.setClassifyTwo(propertyRepairCRMDTO.getTwoType());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getThreeType())){
            propertyRepairCRMEntity.setClassifyThree(propertyRepairCRMDTO.getThreeType());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getThirdRepair())){
            propertyRepairCRMEntity.setRepairCompany(propertyRepairCRMDTO.getThirdRepair());

        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getStandardDate())){
            propertyRepairCRMEntity.setRepairDate(propertyRepairCRMDTO.getStandardDate());
        }
        if(!StringUtil.isEmpty(propertyRepairCRMDTO.getRepairMode())){
            propertyRepairCRMEntity.setMode(propertyRepairCRMDTO.getRepairMode());
        }
        //维修方式
        if (!"accepted".equals(propertyRepairCRMEntity.getState())) {
            if(!StringUtil.isEmpty(propertyRepairCRMDTO.getDealMode())){
                propertyRepairCRMEntity.setDealMode(propertyRepairCRMDTO.getDealMode());
            }

            if(!StringUtil.isEmpty(propertyRepairCRMDTO.getCompanyOne())){
                propertyRepairCRMEntity.setDutyCompanyOne(propertyRepairCRMDTO.getCompanyOne());
            }

            if(!StringUtil.isEmpty(propertyRepairCRMDTO.getCompanyTwo())){
                propertyRepairCRMEntity.setDutyCompanyTwo(propertyRepairCRMDTO.getCompanyTwo());
            }

            if(!StringUtil.isEmpty(propertyRepairCRMDTO.getCompanyThree())){
                propertyRepairCRMEntity.setDutyCompanyThree(propertyRepairCRMDTO.getCompanyThree());
            }

            if(!StringUtil.isEmpty(propertyRepairCRMDTO.getRepairManager())){
                propertyRepairCRMEntity.setRepairManager(propertyRepairCRMDTO.getRepairManager());
            }

            if(!StringUtil.isEmpty(propertyRepairCRMDTO.getThirdRepair())){
                propertyRepairCRMEntity.setRepairCompany(propertyRepairCRMDTO.getThirdRepair());
            }

            if(!StringUtil.isEmpty(propertyRepairCRMDTO.getDealWay())){
                propertyRepairCRMEntity.setDealWay(propertyRepairCRMDTO.getDealWay());
            }
        }
        propertyRepairCRMEntity.setModifyDate(new Date());
        propertyRepairInfo.setModifyDate(propertyRepairCRMEntity.getModifyDate());


        //添加图片
        //删除不在包含的图片
        if (propertyRepairCRMDTO.getReviewImage() != null && !propertyRepairCRMDTO.getReviewImage().isEmpty()) {
            propertyImageRepository.deleteByNotIds(propertyRepairCRMDTO.getReviewImage());
        } else {
            propertyImageRepository.deleteByFkId(propertyRepairCRMDTO.getCaseId(), "2");
        }
        //保存新增图片
        if (propertyRepairCRMDTO.getReviewImgFile() != null && propertyRepairCRMDTO.getReviewImgFile().length > 0) {
            for (int i = 0; i < propertyRepairCRMDTO.getReviewImgFile().length; i++) {
                if (propertyRepairCRMDTO.getReviewImgFile()[i] != null && !"".equals(propertyRepairCRMDTO.getReviewImgFile()[i].getOriginalFilename())) {
                    String fileName = imgService.uploadAdminImage(propertyRepairCRMDTO.getReviewImgFile()[i], ImgType.ACTIVITY);
                    String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                    fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                    PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                    propertyImageEntity.setImageId(propertyRepairCRMDTO.getUserNum() + IdGen.getDateId());//图片id
                    propertyImageEntity.setUploadDate(new Date());//上传日期
                    propertyImageEntity.setImgFkId(propertyRepairCRMEntity.getRepairId());//图片外键id
                    propertyImageEntity.setImagePath(fileName);//图片路径
                    propertyImageEntity.setImageType("2");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                    propertyImageEntity.setState("0");//状态:0为有效；1为无效
                    propertyImageRepository.saveImage(propertyImageEntity);
                }
            }
        }
        new Thread() {
            public void run() {
                String checkCrmState=repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
                if(!"200".equals(checkCrmState) && !"".equals(checkCrmState)){
                    //调用接口失败
                    propertyRepairCRMEntity.setFailNum(1);
                    propertyRepairCRMEntity.setFailType("1");
                    propertyRepairRepository.updateRepairCrm(propertyRepairCRMEntity);
                }
            }
        }.start();
        propertyRepairCRMRepository.update(propertyRepairCRMEntity);
        propertyRepairRepository.updateRepair(propertyRepairInfo);

    }

    @Override
    public ApiResult getRepairsTotal(String projectCode) {
        List<PropertyRepairCountDTO> propertyRepairCountDTOs = new ArrayList<PropertyRepairCountDTO>();
        List<HouseBuildingEntity> houseBuildingEntities = houseBuildingRepository.getListByProjectCode(projectCode);  //根据项目编码获取楼栋列表
        if (houseBuildingEntities != null) {
            PropertyRepairCountDTO propertyRepartCountAll = new PropertyRepairCountDTO();
            PropertyRepairCountDTO propertyRepairCountDTO;

//            Integer totalAll=0;//总计
            int acceptNum,acceptAll=0;
            int processingNum,processingAll=0;
            int completedNum,completeAll=0;
            for(HouseBuildingEntity houseBuildingEntity:houseBuildingEntities){
                propertyRepairCountDTO = new PropertyRepairCountDTO();
                propertyRepairCountDTO.setProjectCode(projectCode);
                propertyRepairCountDTO.setBuildingCode(houseBuildingEntity.getBuildingNum());
                if (!StringUtil.isEmpty(houseBuildingEntity.getBuildingRecord())) {
                    propertyRepairCountDTO.setBuildingName(houseBuildingEntity.getBuildingRecord());  //如果为空则用预售楼号
                } else if(!StringUtil.isEmpty(houseBuildingEntity.getBuildingSale())){
                    propertyRepairCountDTO.setBuildingName(houseBuildingEntity.getBuildingSale());
                }else{
                    propertyRepairCountDTO.setBuildingName("公共区域");
                }
                acceptNum = propertyRepairCRMRepository.getRepairsTotal(projectCode, houseBuildingEntity.getBuildingNum(), "accepted").intValue();
                processingNum = propertyRepairCRMRepository.getRepairsTotal(projectCode, houseBuildingEntity.getBuildingNum(), "processing").intValue();
                completedNum = propertyRepairCRMRepository.getRepairsTotal(projectCode, houseBuildingEntity.getBuildingNum(), "completed").intValue();
                propertyRepairCountDTO.setAcceptedNum(acceptNum);   //获取项目楼栋下待确认统计数
                propertyRepairCountDTO.setProcessingNum(processingNum);
                propertyRepairCountDTO.setCompletedNum(completedNum);
                propertyRepairCountDTO.setAllNum(acceptNum+processingNum+completedNum);
                acceptAll+=acceptNum;
                processingAll+=processingNum;
                completeAll+=completedNum;
                propertyRepairCountDTOs.add(propertyRepairCountDTO);
            }

            //当前项目下统计数据
            propertyRepartCountAll.setProjectCode(projectCode);
            propertyRepartCountAll.setBuildingCode("totalAll");
            propertyRepartCountAll.setBuildingName("totalAll");
            propertyRepartCountAll.setAcceptedNum(acceptAll);
            propertyRepartCountAll.setProcessingNum(processingAll);
            propertyRepartCountAll.setCompletedNum(completeAll);
            propertyRepartCountAll.setAllNum(acceptAll+processingAll+completeAll);
            propertyRepairCountDTOs.add(propertyRepartCountAll);
        }
        return new SuccessApiResult(propertyRepairCountDTOs);
    }

    @Override
    public void propertyRepairExportExcels(String title, String[] headers, ServletOutputStream out, PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage) {
        List<PropertyRepairCRMDTO> list = getQuestionList(propertyRectifyCRMSelDTO, null);
        List<PropertyRepairExcelDTO> propertyRepairExcelDTOs = new ArrayList<PropertyRepairExcelDTO>();
        PropertyExcel<PropertyRepairExcelDTO> pe = new PropertyExcel<PropertyRepairExcelDTO>();
        if (list != null && list.size() > 0) {
            int num = 1;
            for (PropertyRepairCRMDTO propertyRepairCRMDTO : list) {
                PropertyRepairExcelDTO propertyRepairExcelDTO = new PropertyRepairExcelDTO();
                propertyRepairExcelDTO.setNumber(num++);//序号
                propertyRepairExcelDTO.setCaseDesc(propertyRepairCRMDTO.getCaseDesc());//问题描述
                propertyRepairExcelDTO.setAddress(propertyRepairCRMDTO.getAddress());//地址
                propertyRepairExcelDTO.setOneType(propertyRepairCRMDTO.getOneType());//一级分类
                propertyRepairExcelDTO.setTwoType(propertyRepairCRMDTO.getTwoType());//二级分类
                propertyRepairExcelDTO.setThreeType(propertyRepairCRMDTO.getThreeType());//三级分类
                propertyRepairExcelDTO.setRepairManager(propertyRepairCRMDTO.getRepairManager());//维修责任人
                propertyRepairExcelDTO.setCreateDate(propertyRepairCRMDTO.getCreateDate());//创建时间
                propertyRepairExcelDTO.setCaseState(propertyRepairCRMDTO.getCaseState());//维修状态

                propertyRepairExcelDTOs.add(propertyRepairExcelDTO);
            }
        }
        pe.exportExcel(title, headers, propertyRepairExcelDTOs, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 预修改
     */
    /*@Override
    public PropertyRepairCRMDTO getPreRepairDetail(PropertyRectifyAdminDTO propertyRectifyAdminDTO) {
        Object[] obj = propertyRepairCRMRepository.getRepairDetail(propertyRectifyAdminDTO.getRectifyId());
        return null;
    }*/

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 导出excel
     */
    @Override
    public String exportExcel(UserInformationEntity user, HttpServletResponse httpServletResponse, PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        List<PropertyRepairCRMDTO> list = getQuestionList(propertyRectifyCRMSelDTO, null);

        //创建Sheet页
        XSSFSheet sheet = workBook.createSheet("报修表");

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

        String[] titles = {"序号", "问题描述", "位置", "一级分类", "二级分类", "三级分类", "维修责任人", "登记时间", "状态"};

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
                sheet.setColumnWidth(1, 10000);
                sheet.setColumnWidth(2, 13000);
                sheet.setColumnWidth(3, 4000);
                sheet.setColumnWidth(4, 4000);
                sheet.setColumnWidth(5, 4000);
                sheet.setColumnWidth(6, 4000);
                sheet.setColumnWidth(7, 4000);
                sheet.setColumnWidth(8, 3200);
                for (int i = 0; i < list.size(); i++) {
                    XSSFRow bodyRow = sheet.createRow(i + 1);
                    PropertyRepairCRMDTO propertyRepairCRMDTO = list.get(i);

                    cell = bodyRow.createCell(0);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(i + 1);//序号


                    cell = bodyRow.createCell(1);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(propertyRepairCRMDTO.getCaseDesc());//问题描述

                    cell = bodyRow.createCell(2);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(propertyRepairCRMDTO.getAddress());//位置

                    cell = bodyRow.createCell(3);
                    cell.setCellStyle(bodyStyle);// 一级分类
                    cell.setCellValue(propertyRepairCRMDTO.getOneType());//一级分类

                    cell = bodyRow.createCell(4);
                    cell.setCellStyle(bodyStyle);// 二级分类
                    cell.setCellValue(propertyRepairCRMDTO.getTwoType());//二级分类

                    cell = bodyRow.createCell(5);
                    cell.setCellStyle(bodyStyle);// 三级分类
                    cell.setCellValue(propertyRepairCRMDTO.getThreeType());//三级分类


                    cell = bodyRow.createCell(6);
                    cell.setCellStyle(bodyStyle);// 责任人
                    cell.setCellValue(propertyRepairCRMDTO.getRepairManager());//责任人


                    cell = bodyRow.createCell(7);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(DateUtils.format(propertyRepairCRMDTO.getCreateDate()));//创建时间

                    cell = bodyRow.createCell(8);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(propertyRepairCRMDTO.getCaseState());//状态

                }
            });
            try {
                //String fileName = new String(("报修单").getBytes(), "ISO8859-1");
                String fileName = new String(("报修单").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("报修单", "UTF8");
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
}
