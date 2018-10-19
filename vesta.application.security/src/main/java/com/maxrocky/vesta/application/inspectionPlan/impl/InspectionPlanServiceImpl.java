package com.maxrocky.vesta.application.inspectionPlan.impl;

import com.maxrocky.vesta.application.inspectionPlan.DTO.PlanConditionDTO;
import com.maxrocky.vesta.application.inspectionPlan.DTO.PlanListDTO;
import com.maxrocky.vesta.application.inspectionPlan.inf.InspectionPlanService;
import com.maxrocky.vesta.domain.inspectionPlan.repository.InspectionPlanRepository;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.repository.AuthAgencyRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by magic on 2017/6/20.
 */
@Service
public class InspectionPlanServiceImpl implements InspectionPlanService {
    @Autowired
    InspectionPlanRepository inspectionPlanRepository;

    @Autowired
    private AuthAgencyRepository authAgencyRepository;
    /**
     * Describe:安全app 查询检查计划列表
     * CreateBy:Magic
     * CreateOn:2017-06-20
     */
    @Override
    public List<PlanListDTO> getPlanList(PlanConditionDTO planConditionDTO, WebPage webPage, UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        map.put("groupId", "");//集团公司id
        map.put("areaId", "");//区域公司id
        map.put("projectId", "");//项目公司id
        if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000000",userInformationEntity.getStaffId())){
            map.put("groupId", planConditionDTO.getGroupId());//集团公司id
            map.put("areaId", planConditionDTO.getAreaId());//区域公司id
            map.put("projectId", planConditionDTO.getProjectId());//项目公司id
        }else if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000001",userInformationEntity.getStaffId())){
            map.put("areaId", planConditionDTO.getAreaId());//区域公司id
            map.put("projectId", planConditionDTO.getProjectId());//项目公司id
        }else if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000002",userInformationEntity.getStaffId())
                ||authAgencyRepository.checkAuthRoleByUserIdandtype("100000003",userInformationEntity.getStaffId())){
            map.put("projectId", planConditionDTO.getProjectId());//项目公司id
        }
        map.put("startDate", planConditionDTO.getPlanStart());//检查计划开始时间
        map.put("endDate", planConditionDTO.getPlanEnd());//检查计划结束时间
        map.put("state", planConditionDTO.getState());//状态
        map.put("planName","");//检查计划名称

        if(!StringUtil.isEmpty(planConditionDTO.getPlanName())){
            map.put("planName", "%"+planConditionDTO.getPlanName()+"%");//检查计划名称
        }

//        String projectList="";
//        if(!StringUtil.isEmpty(planConditionDTO.getProjectId())){
//
//        }else if(!StringUtil.isEmpty(planConditionDTO.getAreaId())){
//            List<String> areaList=inspectionPlanRepository.getProjectIdListByArea(planConditionDTO.getAreaId());
//            for(String area:areaList){
//                projectList += "'" + area + "',";
//            }
//            projectList +="'"+planConditionDTO.getAreaId()+"'";
//        }else if(!StringUtil.isEmpty(planConditionDTO.getGroupId())){
//            //根据集团公司id查询其下所有区域公司id
//            List<String> areaList=inspectionPlanRepository.getAreaIdListByGroup(planConditionDTO.getGroupId());
//            for(String are:areaList){
//                  projectList += "'" + are + "',";
//            }
//            //根据集团公司查询其下所有项目公司id
//            List<String> proList=inspectionPlanRepository.getProjectIdListByGroup(planConditionDTO.getGroupId());
//            for(String pro:proList){
//                  projectList += "'" + pro + "',";
//            }
//            projectList +="'"+planConditionDTO.getGroupId()+"'";
//        }


        List<PlanListDTO> retList=new ArrayList<PlanListDTO>();
        List<Object[]> list = inspectionPlanRepository.getInspectionPlanList(map,webPage);
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                PlanListDTO planDTO=new PlanListDTO();
                planDTO.setPlanId(obj[0] == null ? "" : obj[0].toString());
                planDTO.setPlanName(obj[1] == null ? "" : obj[1].toString());
                planDTO.setCreateDate(obj[2] == null ? "" :DateUtils.format((Date)obj[2], DateUtils.FORMAT_LONG));
                planDTO.setCreateUnitId(obj[3] == null ? "" : obj[3].toString());
                planDTO.setCreateUnitName(obj[4] == null ? "" : obj[4].toString());
                planDTO.setPlanStart(obj[5] == null ? "" : DateUtils.format((Date)obj[5], DateUtils.FORMAT_LONG));
                planDTO.setPlanEnd(obj[6] == null ? "" : DateUtils.format((Date)obj[6], DateUtils.FORMAT_LONG));
                planDTO.setScore(obj[7] == null ? "" : obj[7].toString());
                planDTO.setState(obj[8] == null ? "" : obj[8].toString());
                planDTO.setCreateName(obj[9] == null ? "" : obj[9].toString());
                retList.add(planDTO);
            }
        }
        return retList;
    }
}
