package com.maxrocky.vesta.application.dailyPatrolInspection.impl;

import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.*;
import com.maxrocky.vesta.application.dailyPatrolInspection.inf.DailyPatrolInspectionService;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.ExportExcel;
import com.maxrocky.vesta.application.jsonDTO.PurviewNameDTO;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.ProjectHouseImageEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectHouseImageRepository;
import com.maxrocky.vesta.domain.baseData.repository.StaffEmployRepository;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.DailyPatrolInspectionDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.DailyPatrolInspectionEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.repository.DailyPatrolInspectionRepository;
import com.maxrocky.vesta.domain.model.AgencyEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.domain.repository.RectificationRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportDoc;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Magic on 2016/10/17.
 */
@Service
public class DailyPatrolInspectionServiceImpl implements DailyPatrolInspectionService {
    @Autowired
    DailyPatrolInspectionRepository dailyPatrolInspectionRepository;
    @Autowired
    RectificationRepository rectificationRepository;
    @Autowired
    StaffEmployRepository staffEmployRepository;
    @Autowired
    ImgService imgService;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    ProjectHouseImageRepository projectHouseImageRepository;
    @Autowired
    GetAllClassifyService getAllClassifyService;


    /**
     * 查询日常巡检列表
     */
    @Override
    public List<DailyPatrolInspectionDTO> getInspection(GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO, WebPage webPage, String creaBy) {
        Map map = new HashMap();
        map.put("projectId", getDailyPatrolInspectionDTO.getProjectId());//项目id
        map.put("buildingId", getDailyPatrolInspectionDTO.getBuildingId());//楼栋id
        map.put("state", getDailyPatrolInspectionDTO.getState());//状态
        map.put("classifyOne", getDailyPatrolInspectionDTO.getClassifyOne());//一级分类
        map.put("classifyTwo", getDailyPatrolInspectionDTO.getClassifyTwo());//二级分类
//        map.put("classfiyThree", getDailyPatrolInspectionDTO.getClassifyThree());//三级分类
        map.put("severityLevel", getDailyPatrolInspectionDTO.getSeverityLevel());//严重等级
        map.put("startDate", getDailyPatrolInspectionDTO.getStartDate());//开始日期
        map.put("endDate", getDailyPatrolInspectionDTO.getEndDate());//结束时间

        map.put("supplier", "");//责任单位
        map.put("firstPartyName", "");//甲方负责人名字
        map.put("supervisorName", "");//第三方监理名字
        map.put("assignName", "");//整改人名字
        map.put("createName", "");//创建人姓名
        map.put("projectList", "NO");//项目权限
        map.put("creaBy", creaBy);
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getSupplier())) {
            map.put("supplier", "%" + getDailyPatrolInspectionDTO.getSupplier() + "%");//责任单位
        }
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getFirstPartyName())) {
            map.put("firstPartyName", "%" + getDailyPatrolInspectionDTO.getFirstPartyName() + "%");//甲方负责人名字
        }
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getSupervisorName())) {
            map.put("supervisorName", "%" + getDailyPatrolInspectionDTO.getSupervisorName() + "%");
        }
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getAssignName())) {
            map.put("assignName", "%" + getDailyPatrolInspectionDTO.getAssignName() + "%");
        }
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getCreateName())) {
            map.put("createName", "%" + getDailyPatrolInspectionDTO.getCreateName() + "%");
        }
        List<DailyPatrolInspectionDTO> retList = new ArrayList<DailyPatrolInspectionDTO>();
        List<Object[]> list = null;
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getProjectId())) {
            list = dailyPatrolInspectionRepository.getInspectionList(map, webPage);
        }
//        else {
//            boolean f = getAllClassifyService.getRoleViewmodelByStaffId(creaBy);
//            if (f) {
//                list = dailyPatrolInspectionRepository.getInspectionList(map, webPage);
//            } else {
//                List<ProjectProjectEntity> projectList1 = staffEmployRepository.getProjectListForAgency(creaBy);
//                String proejctAll = "";
//                if (projectList1 != null) {
//                    for (ProjectProjectEntity project : projectList1) {
//                        proejctAll += "'" + project.getId() + "',";
//                    }
//                }
//                List<ProjectProjectEntity> projectList2 = staffEmployRepository.getProjectListByStaffId(creaBy);
//                if (projectList2 != null) {
//                    for (ProjectProjectEntity project : projectList2) {
//                        proejctAll += "'" + project.getId() + "',";
//                    }
//                }
//                String project = proejctAll.substring(0, proejctAll.length() - 1);
//                map.put("projectList", project);//项目权限
//                list = dailyPatrolInspectionRepository.getInspectionList(map, webPage);
//            }
//        }

        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                DailyPatrolInspectionDTO dailyPatrolInspectionDTO = new DailyPatrolInspectionDTO();
                //日常巡检单id
                dailyPatrolInspectionDTO.setInspectionId(obj[0] == null ? "" : obj[0].toString());
                //超期？
//                dailyPatrolInspectionDTO.setOverdue("否");
//                if (obj[1] != null) {
//                    if (DateUtils.parse(obj[1].toString()).before(new Date())) {
//                        dailyPatrolInspectionDTO.setOverdue("是");
//                    }
//                }
                dailyPatrolInspectionDTO.setProjectName(obj[2] == null ? "" : obj[2].toString());//项目名称
                //位置 楼栋+ 层
                dailyPatrolInspectionDTO.setAddress((obj[3] == null ? "" : obj[3].toString()) + (obj[4] == null ? "" : obj[4].toString()));
                dailyPatrolInspectionDTO.setSeverityLevel(obj[5] == null ? "" : obj[5].toString());//严重等级
                dailyPatrolInspectionDTO.setClassifyThree(obj[6] == null ? "" : obj[6].toString());//三级分类
                dailyPatrolInspectionDTO.setSupplier(obj[7] == null ? "" : obj[7].toString());//责任单位
                dailyPatrolInspectionDTO.setAssignName(obj[8] == null ? "" : obj[8].toString());//整改人名字
                dailyPatrolInspectionDTO.setSupervisorName(obj[9] == null ? "" : obj[9].toString());//第三方监理名字
                dailyPatrolInspectionDTO.setCreateOn(obj[10] == null ? "" : DateUtils.format(DateUtils.parse(obj[10].toString(), "yyyy-MM-dd"), "yyyy-MM-dd"));//创建时间
                dailyPatrolInspectionDTO.setState(obj[11] == null ? "" : obj[11].toString());//状态
                dailyPatrolInspectionDTO.setProjectId(obj[12] == null ? "" : obj[12].toString());//项目id
                dailyPatrolInspectionDTO.setCreateName(obj[13] == null ? "" : obj[13].toString());//创建人
                retList.add(dailyPatrolInspectionDTO);
            }
        }
        return retList;
    }

    /**
     * APP新增日常巡检单
     * Magic 2016/10/24
     */

    @Override
    public ApiResult saveDailyPatrolInspectionForApp(PostDailyPatrolInspectionListDTO dailyList, UserPropertyStaffEntity userPropertyStaffEntity) {
        if (dailyList == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        try {
            PostDailyPatrolInspectionListDTO getDailyPatrolInspectionListDTO = new PostDailyPatrolInspectionListDTO();
            List<PostDailyPatrolInspectionDTO> getDailyPatrolInspectionList = new ArrayList<PostDailyPatrolInspectionDTO>();
            DailyPatrolInspectionEntity dailyPatrolInspection = null;
            List<PostDailyPatrolInspectionDTO> dailyPatrolList = dailyList.getList();
            for (PostDailyPatrolInspectionDTO postDailyPatrolInspection : dailyPatrolList) {
                if ("草稿".equals(postDailyPatrolInspection.getState())) {

                } else {
                    if (StringUtil.isEmpty(postDailyPatrolInspection.getProjectId())) {
                        return ErrorResource.getError("tip_00000572");//项目编码不能为空
                    }
                    if (StringUtil.isEmpty(postDailyPatrolInspection.getTitle())) {
                        return ErrorResource.getError("tip_00000573");//标题不存在
                    }
                    if (StringUtil.isEmpty(postDailyPatrolInspection.getSeverityLevel())) {
                        return ErrorResource.getError("tip_00000574");//严重等级不存在
                    }
                    if (StringUtil.isEmpty(postDailyPatrolInspection.getBuildingId())) {
                        return ErrorResource.getError("tip_00000575");//楼栋编码不存在
                    }
                    if (StringUtil.isEmpty(postDailyPatrolInspection.getFloorId())) {
                        return ErrorResource.getError("tip_00000576");//楼层编码不存在
                    }
                    if (StringUtil.isEmpty(postDailyPatrolInspection.getAssignId())) {
                        return ErrorResource.getError("tip_00000584");//整改工程师不存在
                    }
                    if (StringUtil.isEmpty(postDailyPatrolInspection.getSupplierId())) {
                        return ErrorResource.getError("tip_00000585");//责任单位
                    }
                }
                //判断是新增还是修改
                String checkUp = "0";
                dailyPatrolInspection = dailyPatrolInspectionRepository.getInspectionEntityById(postDailyPatrolInspection.getInspectionId());
                if (dailyPatrolInspection != null) {
                    checkUp = "1";
                } else {
                    dailyPatrolInspection = dailyPatrolInspectionRepository.getInspectionEntityByAppId(postDailyPatrolInspection.getAppId());
                    if (dailyPatrolInspection != null) {
                        checkUp = "1";
                    } else {
                        dailyPatrolInspection = new DailyPatrolInspectionEntity();
                        dailyPatrolInspection.setInspectionId(IdGen.uuid());//日常巡检单id
                        dailyPatrolInspection.setAppId(postDailyPatrolInspection.getAppId());
                    }
                }
                if (!StringUtil.isEmpty(postDailyPatrolInspection.getSupervisor())) {
                    dailyPatrolInspection.setSupervisor(postDailyPatrolInspection.getSupervisor());//第三方监理id
                    //判断第三方监理名字  有则直接存入无则查询
                    if (!StringUtil.isEmpty(postDailyPatrolInspection.getSupervisorName())) {
                        dailyPatrolInspection.setSupervisorName(postDailyPatrolInspection.getSupervisorName());//第三方监理姓名
                    } else {
                        UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(postDailyPatrolInspection.getSupervisor());
                        if (UserStaff != null) {
                            dailyPatrolInspection.setSupervisorName(UserStaff.getStaffName());//第三方监理姓名
                        }
                    }
                } else {
                    //将当前创建人存入第三方监理中
                    dailyPatrolInspection.setSupervisor(userPropertyStaffEntity.getStaffId());//第三方监理id
                    dailyPatrolInspection.setSupervisorName(userPropertyStaffEntity.getStaffName());//第三方监理姓名
                }
                if (!StringUtil.isEmpty(postDailyPatrolInspection.getFirstParty())) {
                    dailyPatrolInspection.setFirstParty(postDailyPatrolInspection.getFirstParty());//甲方
                    if (!StringUtil.isEmpty(postDailyPatrolInspection.getFirstPartyName())) {
                        dailyPatrolInspection.setFirstPartyName(postDailyPatrolInspection.getFirstPartyName());//甲方人姓名
                    } else {
                        UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(postDailyPatrolInspection.getFirstParty());
                        if (UserStaff != null) {
                            dailyPatrolInspection.setFirstPartyName(UserStaff.getStaffName());//jiafa
                        }
                    }

                }
                dailyPatrolInspection.setPointId(postDailyPatrolInspection.getPoint());//位置id
                dailyPatrolInspection.setProjectId(postDailyPatrolInspection.getProjectId());//工程项目_ID
                dailyPatrolInspection.setProjectNum(postDailyPatrolInspection.getProjectName());//项目名称
                dailyPatrolInspection.setBuildingId(postDailyPatrolInspection.getBuildingId());//楼栋id
                dailyPatrolInspection.setBuildingNum(postDailyPatrolInspection.getBuildingName());//楼栋名称
                dailyPatrolInspection.setFloorId(postDailyPatrolInspection.getFloorId());//楼层id
                dailyPatrolInspection.setFloorNum(postDailyPatrolInspection.getFloorName());//楼层名称
                dailyPatrolInspection.setSupplierId(postDailyPatrolInspection.getSupplierId());//责任单位ID
                AgencyEntity agency = agencyRepository.getAgencyDetail(postDailyPatrolInspection.getSupplierId());
                if (agency != null) {
                    dailyPatrolInspection.setSupplier(agency.getAgencyName());//责任单位
                }
                if (!StringUtil.isEmpty(postDailyPatrolInspection.getAssignId())) {
                    dailyPatrolInspection.setAssignId(postDailyPatrolInspection.getAssignId());//整改人ID
                    dailyPatrolInspection.setDealPeople(postDailyPatrolInspection.getAssignId());//处理人
                    //整改人姓名 有则直接存入  无则查询存入
                    if (!StringUtil.isEmpty(postDailyPatrolInspection.getAssignName())) {
                        dailyPatrolInspection.setAssignName(postDailyPatrolInspection.getAssignName());//整改人名字
                    } else {
                        UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(postDailyPatrolInspection.getAssignId());
                        if (UserStaff != null) {
                            dailyPatrolInspection.setAssignName(UserStaff.getStaffName());//整改人名字
                        }
                    }
                }
                dailyPatrolInspection.setTitle(postDailyPatrolInspection.getTitle());//巡检标题
                dailyPatrolInspection.setDescription(postDailyPatrolInspection.getDescription());//描述
                dailyPatrolInspection.setxCoordinate((postDailyPatrolInspection.getxCoordinate() == null || "".equals(postDailyPatrolInspection.getxCoordinate())) ? null : new BigDecimal(postDailyPatrolInspection.getxCoordinate()));//x坐标
                dailyPatrolInspection.setyCoordinate((postDailyPatrolInspection.getyCoordinate() == null || "".equals(postDailyPatrolInspection.getyCoordinate())) ? null : new BigDecimal(postDailyPatrolInspection.getyCoordinate()));//y坐标
                dailyPatrolInspection.setState(postDailyPatrolInspection.getState());//状态
                dailyPatrolInspection.setCreateBy(userPropertyStaffEntity.getStaffId());//创建人
                dailyPatrolInspection.setCreateName(userPropertyStaffEntity.getStaffName());//创建人名字
                dailyPatrolInspection.setCreateOn(new Date());//创建时间
                dailyPatrolInspection.setClassifyOne(postDailyPatrolInspection.getClassifyOne());//一级分类
                dailyPatrolInspection.setClassifyOneName(postDailyPatrolInspection.getClassifyOneName());
                dailyPatrolInspection.setClassifyTwo(postDailyPatrolInspection.getClassifyTwo());//二级分类
                dailyPatrolInspection.setClassifyTwoName(postDailyPatrolInspection.getClassifyTwoName());
                dailyPatrolInspection.setClassifyThree(postDailyPatrolInspection.getClassifyThree());//三级分类
                dailyPatrolInspection.setClassifyThreeName(postDailyPatrolInspection.getClassifyThreeName());
                dailyPatrolInspection.setSeverityLevel(postDailyPatrolInspection.getSeverityLevel());//严重等级
                dailyPatrolInspection.setRectificationPeriod(DateUtils.parse(postDailyPatrolInspection.getRectificationPeriod(), "yyyy-MM-dd"));//整改时限
                dailyPatrolInspection.setModifyOn(new Date());//修改时间
                try {
                    if ("1".equals(checkUp)) {
                        dailyPatrolInspectionRepository.updateInspection(dailyPatrolInspection);//修改整改单
                    } else {
                        dailyPatrolInspectionRepository.saveInspection(dailyPatrolInspection);//保存整改单
                    }
                } catch (Exception e) {
                    dailyPatrolInspection = dailyPatrolInspectionRepository.getInspectionEntityByAppId(postDailyPatrolInspection.getInspectionId());

                    PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                    getInspection.setImageList(postDailyPatrolInspection.getImageList());
                    getInspection.setIdList(postDailyPatrolInspection.getIdList());
                    getDailyPatrolInspectionList.add(getInspection);
                    continue;
                }
                //删除图片
                dailyPatrolInspectionRepository.deleteProjectImages(dailyPatrolInspection.getInspectionId(), "1");
                List<InspectionImageDTO> imageList = postDailyPatrolInspection.getImageList();//整改图片
                if (imageList.size() > 0) {
                    for (InspectionImageDTO InspectionImage : imageList) {
                        ProjectImagesEntity ProjectImagesEntity = new ProjectImagesEntity();
                        ProjectImagesEntity.setId(IdGen.uuid());//id
                        ProjectImagesEntity.setBusinessId(dailyPatrolInspection.getInspectionId());//当前巡检整改单id
                        ProjectImagesEntity.setUrl(InspectionImage.getImageUrl());//图片链接
                        ProjectImagesEntity.setType("1");
                        ProjectImagesEntity.setState("1");
                        ProjectImagesEntity.setModifyOn(new Date());
                        ProjectImagesEntity.setCreateOn(new Date());
                        dailyPatrolInspectionRepository.saveProjectImages(ProjectImagesEntity);//保存整改图片
                    }
                }
                //先删除抄送
                dailyPatrolInspectionRepository.deleteProjectCopy(dailyPatrolInspection.getInspectionId(), "1");
                List<CopyDetailsListDTO> idList = postDailyPatrolInspection.getIdList();//抄送人list
                if (idList.size() > 0) {
                    ProjectCopyEntity projectCopy = new ProjectCopyEntity();
                    projectCopy.setId(IdGen.uuid());//抄送单id
                    projectCopy.setSender(userPropertyStaffEntity.getStaffId());//发送人id
                    projectCopy.setSenderName(userPropertyStaffEntity.getStaffName());//发送人姓名
                    projectCopy.setBusiness(dailyPatrolInspection.getInspectionId());//当前巡检整改单id
                    projectCopy.setDamain("1");
                    projectCopy.setCreateOn(new Date());
                    dailyPatrolInspectionRepository.saveProjectCopy(projectCopy);

                    for (CopyDetailsListDTO copyDetailsList : idList) {
                        CopyDetailsListDTO newId = new CopyDetailsListDTO();
                        ProjectCopyDetailsEntity projectCopyDetails = new ProjectCopyDetailsEntity();
                        projectCopyDetails.setId(IdGen.uuid());
                        projectCopyDetails.setCopyId(projectCopy.getId());//抄送单id
                        newId.setBusiness(projectCopyDetails.getCopyId());
                        projectCopyDetails.setMemberId(copyDetailsList.getId());
                        newId.setId(projectCopyDetails.getMemberId());
                        if (!StringUtil.isEmpty(copyDetailsList.getName())) {
                            projectCopyDetails.setMemberName(copyDetailsList.getName());
                            newId.setName(projectCopyDetails.getMemberName());
                        } else {
                            UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(copyDetailsList.getId());
                            if (UserStaff != null) {
                                projectCopyDetails.setMemberName(UserStaff.getStaffName());
                                newId.setName(projectCopyDetails.getMemberName());
                            }
                        }
                        dailyPatrolInspectionRepository.saveProjectCopyDetails(projectCopyDetails);
                    }
                }
                PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                getDailyPatrolInspectionList.add(getInspection);
            }
            getDailyPatrolInspectionListDTO.setList(getDailyPatrolInspectionList);
            return new SuccessApiResult(getDailyPatrolInspectionListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * APP按项目查询
     * Magic 2016/10/24
     */
    @Override
    public PostDailyPatrolInspectionListDTO getDailyPatrolInspectionForApp(String id, String timeStamp, String projectId, String creaid) {
        PostDailyPatrolInspectionListDTO postDailyPatrolInspectionList = new PostDailyPatrolInspectionListDTO();
        String type = "1";
        if (staffEmployRepository.checkOwner(creaid, projectId, "1")) {//甲方
            //甲方   该项目下所有数据和自己创建的草稿
            type = "1";
        } else if ("4".equals(staffEmployRepository.getPurviewName(creaid, projectId))) {//责任方
            //乙方   处理人为自己 + 完成状态  乙方负责人为自己
            type = "3";

        } else if ("5".equals(staffEmployRepository.getPurviewName(creaid, projectId))) {//监理
            //第三方监理   自己创建 + 第三方监理为自己 + 抄送自己 + 处理人为自己
            type = "2";
        }
        List<PostDailyPatrolInspectionDTO> postInspectionList = new ArrayList<PostDailyPatrolInspectionDTO>();

        List<Object[]> dailyPatrolInspectionList = dailyPatrolInspectionRepository.getInspectionListByApp(id, timeStamp, projectId, creaid, type);
        if (dailyPatrolInspectionList != null) {
            for (Object[] obj : dailyPatrolInspectionList) {
                List<InspectionImageDTO> getimageList = new ArrayList<InspectionImageDTO>();//******************返回数据list*******整改图片*********
                PostDailyPatrolInspectionDTO dailyPatrolDTO = new PostDailyPatrolInspectionDTO();
                dailyPatrolDTO.setTitle(obj[0] == null ? "" : obj[0].toString());
                dailyPatrolDTO.setClassifyOne(obj[1] == null ? "" : obj[1].toString());
                dailyPatrolDTO.setClassifyTwo(obj[2] == null ? "" : obj[2].toString());
                dailyPatrolDTO.setClassifyThree(obj[3] == null ? "" : obj[3].toString());
                dailyPatrolDTO.setProjectId(obj[4] == null ? "" : obj[4].toString());
                dailyPatrolDTO.setProjectName(obj[5] == null ? "" : obj[5].toString());
                dailyPatrolDTO.setPoint(obj[6] == null ? "" : obj[6].toString());
                dailyPatrolDTO.setBuildingId(obj[7] == null ? "" : obj[7].toString());
                dailyPatrolDTO.setBuildingName(obj[8] == null ? "" : obj[8].toString());
                dailyPatrolDTO.setFloorId(obj[9] == null ? "" : obj[9].toString());
                dailyPatrolDTO.setFloorName(obj[10] == null ? "" : obj[10].toString());
                dailyPatrolDTO.setSeverityLevel(obj[11] == null ? "" : obj[11].toString());
                dailyPatrolDTO.setDescription(obj[12] == null ? "" : obj[12].toString());
                dailyPatrolDTO.setSupplierId(obj[13] == null ? "" : obj[13].toString());
                dailyPatrolDTO.setSupplier(obj[14] == null ? "" : obj[14].toString());
                dailyPatrolDTO.setDealPeople(obj[15] == null ? "" : obj[15].toString());
                dailyPatrolDTO.setFirstParty(obj[16] == null ? "" : obj[16].toString());
                dailyPatrolDTO.setFirstPartyName(obj[17] == null ? "" : obj[17].toString());
                dailyPatrolDTO.setAssignId(obj[18] == null ? "" : obj[18].toString());
                dailyPatrolDTO.setAssignName(obj[19] == null ? "" : obj[19].toString());
                dailyPatrolDTO.setSupervisor(obj[20] == null ? "" : obj[20].toString());
                dailyPatrolDTO.setSupervisorName(obj[21] == null ? "" : obj[21].toString());
                dailyPatrolDTO.setRectificationPeriod(obj[22] == null ? "" : DateUtils.format(DateUtils.parse(obj[22].toString(), "yyyy-MM-dd"), "yyyy-MM-dd"));
                dailyPatrolDTO.setxCoordinate(obj[23] == null ? "" : obj[23].toString());
                dailyPatrolDTO.setyCoordinate(obj[24] == null ? "" : obj[24].toString());
                dailyPatrolDTO.setState(obj[25] == null ? "" : obj[25].toString());
                dailyPatrolDTO.setId(obj[26] == null ? "" : obj[26].toString());
                dailyPatrolDTO.setInspectionId(obj[27] == null ? "" : obj[27].toString());
                dailyPatrolDTO.setCreateName(obj[28] == null ? "" : obj[28].toString());
                dailyPatrolDTO.setCreateOn(obj[29] == null ? "" : DateUtils.format(DateUtils.parse(obj[29].toString(), DateUtils.FORMAT_LONG), DateUtils.FORMAT_LONG));
                dailyPatrolDTO.setModifyOn(obj[30] == null ? "" : DateUtils.format(DateUtils.parse(obj[30].toString(), DateUtils.FORMAT_LONG), DateUtils.FORMAT_LONG));
                dailyPatrolDTO.setClassifyOneName(obj[31] == null ? "" : obj[31].toString());
                dailyPatrolDTO.setClassifyTwoName(obj[32] == null ? "" : obj[32].toString());
                dailyPatrolDTO.setClassifyThreeName(obj[33] == null ? "" : obj[33].toString());
                dailyPatrolDTO.setAppId(obj[34] == null ? "" : obj[34].toString());
                List<ProjectImagesEntity> projectImagesList = dailyPatrolInspectionRepository.getProjectImages(obj[27] == null ? "" : obj[27].toString());
                if (projectImagesList != null) {
                    for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                        InspectionImageDTO getImage = new InspectionImageDTO();
                        getImage.setId(projectImagesEntity.getId());
                        getImage.setBusinessId(projectImagesEntity.getBusinessId());
                        getImage.setImageUrl(projectImagesEntity.getUrl());
                        getImage.setType(projectImagesEntity.getType());
                        getImage.setState(projectImagesEntity.getState());
                        getImage.setCreateOn(DateUtils.format(projectImagesEntity.getCreateOn()));
                        getImage.setModifyOn(DateUtils.format(projectImagesEntity.getModifyOn()));
                        getimageList.add(getImage);
                    }
                }
                dailyPatrolDTO.setImageList(getimageList);
                List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(obj[27] == null ? "" : obj[27].toString());//抄送人list
                List<CopyDetailsListDTO> copyDetailsList = new ArrayList<CopyDetailsListDTO>();
                if (idList != null) {
                    for (Object[] idObj : idList) {
                        CopyDetailsListDTO copyDetailsListDTO = new CopyDetailsListDTO();
                        copyDetailsListDTO.setBusiness(idObj[0] == null ? "" : idObj[0].toString());
                        copyDetailsListDTO.setId(idObj[1] == null ? "" : idObj[1].toString());
                        copyDetailsListDTO.setName(idObj[2] == null ? "" : idObj[2].toString());
                        copyDetailsList.add(copyDetailsListDTO);
                    }
                }
                dailyPatrolDTO.setIdList(copyDetailsList);
                List<PostDailyPatrolInspectionDetailsDTO> inspectionList = new ArrayList<PostDailyPatrolInspectionDetailsDTO>();//整改记录
                List<DailyPatrolInspectionDetailsEntity> dailyPatrolInspectionDetails = dailyPatrolInspectionRepository.getDailyPatrolInspectionDetails(obj[27] == null ? "" : obj[27].toString());
                if (dailyPatrolInspectionDetails != null) {
                    for (DailyPatrolInspectionDetailsEntity dailyPatrolInspectionDetailsEntity : dailyPatrolInspectionDetails) {
                        PostDailyPatrolInspectionDetailsDTO postDailyPatrolInspectionDetailsDTO = new PostDailyPatrolInspectionDetailsDTO();
                        postDailyPatrolInspectionDetailsDTO.setId(dailyPatrolInspectionDetailsEntity.getId());
                        postDailyPatrolInspectionDetailsDTO.setInspectionId(dailyPatrolInspectionDetailsEntity.getInspectionId());
                        postDailyPatrolInspectionDetailsDTO.setDescription(dailyPatrolInspectionDetailsEntity.getDescription());
                        postDailyPatrolInspectionDetailsDTO.setCreateName(dailyPatrolInspectionDetailsEntity.getCreateName());
                        postDailyPatrolInspectionDetailsDTO.setCreateBy(dailyPatrolInspectionDetailsEntity.getCreateBy());
                        postDailyPatrolInspectionDetailsDTO.setCreateOn(DateUtils.format(dailyPatrolInspectionDetailsEntity.getCreateOn()));
                        postDailyPatrolInspectionDetailsDTO.setType(dailyPatrolInspectionDetailsEntity.getType());
                        postDailyPatrolInspectionDetailsDTO.setFrequency(dailyPatrolInspectionDetailsEntity.getFrequency());
                        postDailyPatrolInspectionDetailsDTO.setDetailsState(dailyPatrolInspectionDetailsEntity.getDetailsState());
                        List<ProjectImagesEntity> postImageList = dailyPatrolInspectionRepository.getProjectImages(dailyPatrolInspectionDetailsEntity.getId());
                        List<InspectionImageDTO> posmageList = new ArrayList<InspectionImageDTO>();//整改记录图片
                        if (postImageList != null) {
                            for (ProjectImagesEntity projectImagesEntity : postImageList) {
                                InspectionImageDTO getImage = new InspectionImageDTO();
                                getImage.setId(projectImagesEntity.getId());
                                getImage.setBusinessId(projectImagesEntity.getBusinessId());
                                getImage.setImageUrl(projectImagesEntity.getUrl());
                                getImage.setType(projectImagesEntity.getType());
                                getImage.setState(projectImagesEntity.getState());
                                getImage.setCreateOn(DateUtils.format(projectImagesEntity.getCreateOn()));
                                getImage.setModifyOn(DateUtils.format(projectImagesEntity.getModifyOn()));
                                posmageList.add(getImage);
                            }
                        }
                        postDailyPatrolInspectionDetailsDTO.setImageList(posmageList);
                        inspectionList.add(postDailyPatrolInspectionDetailsDTO);
                    }
                    dailyPatrolDTO.setInspectionList(inspectionList);
                }
                postInspectionList.add(dailyPatrolDTO);
            }
            if (postInspectionList.size() > 0) {
                postDailyPatrolInspectionList.setList(postInspectionList);
                postDailyPatrolInspectionList.setId(postInspectionList.get(postInspectionList.size() - 1).getId());
                postDailyPatrolInspectionList.setTimeStamp(postInspectionList.get(postInspectionList.size() - 1).getModifyOn());
                postDailyPatrolInspectionList.setProjectId(projectId);
            } else {
//                postDailyPatrolInspectionList.setList(postInspectionList);
                postDailyPatrolInspectionList.setId(id);
                postDailyPatrolInspectionList.setTimeStamp(timeStamp);
                postDailyPatrolInspectionList.setProjectId(projectId);
            }
        }
        return postDailyPatrolInspectionList;
    }

    /**
     * APP查询所有项目是否有数据更新
     * creaby  当前登录人员工id
     */
    @Override
    public List<CheckDailyPatrolInspectionDTO> checkDailyPatrolInspectionForApp(CheckDailyPatrolInspectionListDTO checkDailyPatrolInspectionList, String creaby) {
        List<CheckDailyPatrolInspectionDTO> checkList = checkDailyPatrolInspectionList.getCheckList();
        List<CheckDailyPatrolInspectionDTO> getCheckList = new ArrayList<CheckDailyPatrolInspectionDTO>();
        if (checkList != null) {
            for (CheckDailyPatrolInspectionDTO check : checkList) {
                CheckDailyPatrolInspectionDTO getCheck = new CheckDailyPatrolInspectionDTO();
                String type = "1";
                if (staffEmployRepository.checkOwner(creaby, check.getProjectId(), "1")) {//甲方
                    //甲方   该项目下所有数据和自己创建的草稿
                    type = "1";
                } else {
                    String chec = staffEmployRepository.getPurviewName(creaby, check.getProjectId());
                    if ("4".equals(chec)) {
                        //乙方   处理人为自己 + 完成状态  乙方负责人为自己
                        type = "3";
                    } else if ("5".equals(chec)) {
                        //第三方监理   自己创建 + 第三方监理为自己 + 抄送自己 + 处理人为自己
                        type = "2";
                    }
                }
                String time = "";
                if (check.getTimeStamp() != null && !"".equals(check.getTimeStamp())) {
                    time = DateUtils.format(DateUtils.parse(check.getTimeStamp(), "yyyyMMddHHmmss"));
                }
                boolean checkFlag = dailyPatrolInspectionRepository.checkInspectionByApp(check.getId(), time, check.getProjectId(), creaby, type);
                if (checkFlag) {
                    getCheck.setUpdateFlag("Y");
                } else {
                    getCheck.setUpdateFlag("N");
                }
                getCheck.setProjectId(check.getProjectId());
                getCheck.setId(check.getId());
                getCheck.setTimeStamp(check.getTimeStamp());
                getCheckList.add(getCheck);
            }
        }
        return getCheckList;
    }

    /**
     * APP按项目查询统计列表
     * Magic 2016/10/24
     */
    @Override
    public ApiResult searchInspection(String projectId) {
        if (StringUtil.isEmpty(projectId)) {
            return ErrorResource.getError("tip_00000572");//项目编码不能为空
        }
        try {
            List<SearchDailyPatrolInspectionDTO> searchInspectionList = new ArrayList<SearchDailyPatrolInspectionDTO>();
            int all = 0;
            int ing = 0;
            int end = 0;
            List<Object[]> list = dailyPatrolInspectionRepository.searchInspection(projectId);
            if (list != null && list.size() > 0) {
                SearchDailyPatrolInspectionDTO searchDailyInspection = new SearchDailyPatrolInspectionDTO();
                for (Object[] obj : list) {
                    SearchDailyPatrolInspectionDTO searchInspection = new SearchDailyPatrolInspectionDTO();
                    searchInspection.setBuildingId(obj[0] == null ? "" : obj[0].toString());//楼栋id
                    searchInspection.setBuildingName(obj[1] == null ? "" : obj[1].toString());//楼栋名称
                    searchInspection.setProjectId(obj[2] == null ? "" : obj[2].toString());//项目id
                    searchInspection.setProjectName(obj[3] == null ? "" : obj[3].toString());//项目名
                    searchInspection.setInspectionAll(obj[4] == null ? 0 : ((BigInteger) obj[4]).intValue());//所有问题 <> 草稿
                    searchInspection.setInspectionIng(obj[5] == null ? 0 : ((BigInteger) obj[5]).intValue());//进行中问题
                    searchInspection.setInspectionEnd(obj[6] == null ? 0 : ((BigInteger) obj[6]).intValue());//已完成问题

                    //总计数据
                    all += searchInspection.getInspectionAll();
                    ing += searchInspection.getInspectionIng();
                    end += searchInspection.getInspectionEnd();
                    searchInspectionList.add(searchInspection);
                }
                searchDailyInspection.setBuildingId("totalAll");
                searchDailyInspection.setBuildingName("总计");
                searchDailyInspection.setProjectName(searchInspectionList.get(0).getProjectName());
                searchDailyInspection.setProjectId(searchInspectionList.get(0).getProjectId());
                searchDailyInspection.setInspectionAll(all);
                searchDailyInspection.setInspectionIng(ing);
                searchDailyInspection.setInspectionEnd(end);
                searchInspectionList.add(searchDailyInspection);
            }
            return new SuccessApiResult(searchInspectionList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * APP待办事项查询
     * Magic 2016/10/24
     */
    @Override
    public PostDailyPatrolInspectionListDTO getInspectionForAppTodo(String id, String timeStamp, String creaid) {
        PostDailyPatrolInspectionListDTO postDailyPatrolInspectionList = new PostDailyPatrolInspectionListDTO();
        List<PostDailyPatrolInspectionDTO> postInspectionList = new ArrayList<PostDailyPatrolInspectionDTO>();
        List<Object[]> dailyPatrolInspectionList = dailyPatrolInspectionRepository.getInspectionListByAppTodo(id, timeStamp, creaid);
        if (dailyPatrolInspectionList != null) {
            for (Object[] obj : dailyPatrolInspectionList) {
                List<InspectionImageDTO> getimageList = new ArrayList<InspectionImageDTO>();//******************返回数据list*******整改图片*********
                PostDailyPatrolInspectionDTO dailyPatrolDTO = new PostDailyPatrolInspectionDTO();
                dailyPatrolDTO.setTitle(obj[0] == null ? "" : obj[0].toString());
                dailyPatrolDTO.setClassifyOne(obj[1] == null ? "" : obj[1].toString());
                dailyPatrolDTO.setClassifyTwo(obj[2] == null ? "" : obj[2].toString());
                dailyPatrolDTO.setClassifyThree(obj[3] == null ? "" : obj[3].toString());
                dailyPatrolDTO.setProjectId(obj[4] == null ? "" : obj[4].toString());
                dailyPatrolDTO.setProjectName(obj[5] == null ? "" : obj[5].toString());
                dailyPatrolDTO.setPoint(obj[6] == null ? "" : obj[6].toString());
                dailyPatrolDTO.setBuildingId(obj[7] == null ? "" : obj[7].toString());
                dailyPatrolDTO.setBuildingName(obj[8] == null ? "" : obj[8].toString());
                dailyPatrolDTO.setFloorId(obj[9] == null ? "" : obj[9].toString());
                dailyPatrolDTO.setFloorName(obj[10] == null ? "" : obj[10].toString());
                dailyPatrolDTO.setSeverityLevel(obj[11] == null ? "" : obj[11].toString());
                dailyPatrolDTO.setDescription(obj[12] == null ? "" : obj[12].toString());
                dailyPatrolDTO.setSupplierId(obj[13] == null ? "" : obj[13].toString());
                dailyPatrolDTO.setSupplier(obj[14] == null ? "" : obj[14].toString());
                dailyPatrolDTO.setAssignId(obj[15] == null ? "" : obj[15].toString());
                dailyPatrolDTO.setAssignName(obj[16] == null ? "" : obj[16].toString());
                dailyPatrolDTO.setSupervisor(obj[17] == null ? "" : obj[17].toString());
                dailyPatrolDTO.setSupervisorName(obj[18] == null ? "" : obj[18].toString());
                dailyPatrolDTO.setRectificationPeriod(obj[19] == null ? "" : obj[19].toString());
                dailyPatrolDTO.setxCoordinate(obj[20] == null ? "" : obj[20].toString());
                dailyPatrolDTO.setyCoordinate(obj[21] == null ? "" : obj[21].toString());
                dailyPatrolDTO.setState(obj[22] == null ? "" : obj[22].toString());
                dailyPatrolDTO.setId(obj[23] == null ? "" : obj[23].toString());
                dailyPatrolDTO.setInspectionId(obj[24] == null ? "" : obj[24].toString());
                dailyPatrolDTO.setCreateName(obj[25] == null ? "" : obj[25].toString());
                dailyPatrolDTO.setCreateOn(obj[26] == null ? "" : obj[26].toString());
                dailyPatrolDTO.setModifyOn(obj[27] == null ? "" : obj[27].toString());
                List<ProjectImagesEntity> projectImagesList = dailyPatrolInspectionRepository.getProjectImages(obj[24] == null ? "" : obj[24].toString());
                if (projectImagesList != null) {
                    for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                        InspectionImageDTO getImage = new InspectionImageDTO();
                        getImage.setId(projectImagesEntity.getId());
                        getImage.setBusinessId(projectImagesEntity.getBusinessId());
                        getImage.setImageUrl(projectImagesEntity.getUrl());
                        getImage.setType(projectImagesEntity.getType());
                        getImage.setState(projectImagesEntity.getState());
                        getImage.setCreateOn(DateUtils.format(projectImagesEntity.getCreateOn()));
                        getImage.setModifyOn(DateUtils.format(projectImagesEntity.getModifyOn()));
                        getimageList.add(getImage);
                    }
                }
                dailyPatrolDTO.setImageList(getimageList);
                List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(obj[24] == null ? "" : obj[24].toString());//抄送人list
                List<CopyDetailsListDTO> copyDetailsList = new ArrayList<CopyDetailsListDTO>();
                if (idList != null) {
                    for (Object[] idObj : idList) {
                        CopyDetailsListDTO copyDetailsListDTO = new CopyDetailsListDTO();
                        copyDetailsListDTO.setBusiness(idObj[0] == null ? "" : idObj[0].toString());
                        copyDetailsListDTO.setId(idObj[1] == null ? "" : idObj[1].toString());
                        copyDetailsListDTO.setName(idObj[2] == null ? "" : idObj[2].toString());
                        copyDetailsList.add(copyDetailsListDTO);
                    }
                }
                dailyPatrolDTO.setIdList(copyDetailsList);
                List<PostDailyPatrolInspectionDetailsDTO> inspectionList = new ArrayList<PostDailyPatrolInspectionDetailsDTO>();//整改记录
                List<DailyPatrolInspectionDetailsEntity> dailyPatrolInspectionDetails = dailyPatrolInspectionRepository.getDailyPatrolInspectionDetails(obj[24] == null ? "" : obj[24].toString());
                if (dailyPatrolInspectionDetails != null) {
                    for (DailyPatrolInspectionDetailsEntity dailyPatrolInspectionDetailsEntity : dailyPatrolInspectionDetails) {
                        PostDailyPatrolInspectionDetailsDTO postDailyPatrolInspectionDetailsDTO = new PostDailyPatrolInspectionDetailsDTO();
                        postDailyPatrolInspectionDetailsDTO.setId(dailyPatrolInspectionDetailsEntity.getId());
                        postDailyPatrolInspectionDetailsDTO.setInspectionId(dailyPatrolInspectionDetailsEntity.getInspectionId());
                        postDailyPatrolInspectionDetailsDTO.setDescription(dailyPatrolInspectionDetailsEntity.getDescription());
                        postDailyPatrolInspectionDetailsDTO.setCreateName(dailyPatrolInspectionDetailsEntity.getCreateName());
                        postDailyPatrolInspectionDetailsDTO.setCreateBy(dailyPatrolInspectionDetailsEntity.getCreateBy());
                        postDailyPatrolInspectionDetailsDTO.setCreateOn(DateUtils.format(dailyPatrolInspectionDetailsEntity.getCreateOn()));
                        postDailyPatrolInspectionDetailsDTO.setType(dailyPatrolInspectionDetailsEntity.getType());
                        List<ProjectImagesEntity> postImageList = dailyPatrolInspectionRepository.getProjectImages(dailyPatrolInspectionDetailsEntity.getId());
                        List<InspectionImageDTO> posmageList = new ArrayList<InspectionImageDTO>();//整改记录图片
                        if (postImageList != null) {
                            for (ProjectImagesEntity projectImagesEntity : postImageList) {
                                InspectionImageDTO getImage = new InspectionImageDTO();
                                getImage.setId(projectImagesEntity.getId());
                                getImage.setBusinessId(projectImagesEntity.getBusinessId());
                                getImage.setImageUrl(projectImagesEntity.getUrl());
                                getImage.setType(projectImagesEntity.getType());
                                getImage.setState(projectImagesEntity.getState());
                                getImage.setCreateOn(DateUtils.format(projectImagesEntity.getCreateOn()));
                                getImage.setModifyOn(DateUtils.format(projectImagesEntity.getModifyOn()));
                                posmageList.add(getImage);
                            }
                        }
                        postDailyPatrolInspectionDetailsDTO.setImageList(posmageList);
                        inspectionList.add(postDailyPatrolInspectionDetailsDTO);
                    }
                    dailyPatrolDTO.setInspectionList(inspectionList);
                }
                postInspectionList.add(dailyPatrolDTO);
            }
            if (postInspectionList.size() > 0) {
                postDailyPatrolInspectionList.setList(postInspectionList);
                postDailyPatrolInspectionList.setId(postInspectionList.get(postInspectionList.size() - 1).getId());
                postDailyPatrolInspectionList.setTimeStamp(postInspectionList.get(postInspectionList.size() - 1).getModifyOn());
            }
        }
        return postDailyPatrolInspectionList;
    }

    /**
     * 判断戴白事项是否有数据更新
     */
    @Override
    public String checkInspectionForAppTodo(String id, String timeStamp, String creaid) {
        boolean checkFlag = dailyPatrolInspectionRepository.checkInspectionTodo(id, timeStamp, creaid);
        if (checkFlag) {
            return "Y";
        } else {
            return "N";
        }
    }

    /**
     * 日常巡检处理问题（乙方）
     *
     * @param fromPartyBProblemSolvingListDTO
     * @param userPropertyStaffEntity
     * @return
     */
    @Override
    public ApiResult fromPartyBProblemSolving(FromPartyBProblemSolvingListDTO fromPartyBProblemSolvingListDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        try {
            PostDailyPatrolInspectionListDTO getDailyPatrolInspectionListDTO = new PostDailyPatrolInspectionListDTO();
            List<PostDailyPatrolInspectionDTO> getDailyPatrolInspectionList = new ArrayList<PostDailyPatrolInspectionDTO>();
            List<FromPartyBProblemSolvingDTO> fromPartyBProblemSolvingDTOList = fromPartyBProblemSolvingListDTO.getList();
            for (FromPartyBProblemSolvingDTO fromPartyBProblemSolvingDTO : fromPartyBProblemSolvingDTOList) {
                DailyPatrolInspectionEntity dailyPatrolInspection = dailyPatrolInspectionRepository.getInspectionEntityById(fromPartyBProblemSolvingDTO.getInspectionId());
                if (dailyPatrolInspection != null) {
                    if (!"整改中".equals(dailyPatrolInspection.getState())) {
                        PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                        getDailyPatrolInspectionList.add(getInspection);
                        continue;
                    }
                    if (!userPropertyStaffEntity.getStaffId().equals(dailyPatrolInspection.getDealPeople())) {
                        PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                        getDailyPatrolInspectionList.add(getInspection);
                        continue;
                    }
                    dailyPatrolInspection.setDealPeople(dailyPatrolInspection.getSupervisor());//处理人为第三方监理
                    dailyPatrolInspection.setModifyOn(new Date());
                    //日常巡检详情

                    DailyPatrolInspectionDetailsEntity dailyPatrolInspectionDetailsEntity = new DailyPatrolInspectionDetailsEntity();
                    dailyPatrolInspectionDetailsEntity.setId(IdGen.uuid());
                    dailyPatrolInspectionDetailsEntity.setInspectionId(fromPartyBProblemSolvingDTO.getInspectionId());//巡检ID
                    dailyPatrolInspectionDetailsEntity.setDescription(fromPartyBProblemSolvingDTO.getDescription());//描述
                    dailyPatrolInspectionDetailsEntity.setType("0");//0 乙方整改  1 第三方监理整改   2 甲方整改
                    dailyPatrolInspectionDetailsEntity.setCreateBy(userPropertyStaffEntity.getStaffId());//创建人ID
                    dailyPatrolInspectionDetailsEntity.setCreateName(userPropertyStaffEntity.getStaffName());//创建人名称
                    dailyPatrolInspectionDetailsEntity.setCreateOn(new Date());//创建时间
                    dailyPatrolInspectionDetailsEntity.setAppId(fromPartyBProblemSolvingDTO.getAppId());
                    dailyPatrolInspectionDetailsEntity.setFrequency(fromPartyBProblemSolvingDTO.getFrequency());//整改次数
                    try {
                        dailyPatrolInspectionRepository.saveInspectionDetais(dailyPatrolInspectionDetailsEntity);
                        dailyPatrolInspectionRepository.updateInspection(dailyPatrolInspection);//修改主表

                    } catch (Exception e) {
                        PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                        getDailyPatrolInspectionList.add(getInspection);
                        continue;
                    }
                    List<InspectionImageDTO> imageList = fromPartyBProblemSolvingDTO.getImageList();//整改图片
                    if (imageList.size() > 0) {
                        for (InspectionImageDTO InspectionImage : imageList) {
                            ProjectImagesEntity ProjectImagesEntity = new ProjectImagesEntity();
                            ProjectImagesEntity.setId(IdGen.uuid());//id
                            ProjectImagesEntity.setBusinessId(dailyPatrolInspectionDetailsEntity.getId());//当前巡检详情单id
                            ProjectImagesEntity.setUrl(InspectionImage.getImageUrl());//图片链接
                            ProjectImagesEntity.setType("1");
                            ProjectImagesEntity.setState("1");
                            ProjectImagesEntity.setModifyOn(new Date());
                            ProjectImagesEntity.setCreateOn(new Date());
                            dailyPatrolInspectionRepository.saveProjectImages(ProjectImagesEntity);//保存整改图片
                        }
                    }
                    PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                    getDailyPatrolInspectionList.add(getInspection);
                }
            }
            getDailyPatrolInspectionListDTO.setList(getDailyPatrolInspectionList);
            return new SuccessApiResult(getDailyPatrolInspectionListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * 日常巡检处理问题（第三方监理）
     *
     * @param fromPartyBProblemSolvingListDTO
     * @param userPropertyStaffEntity
     * @return
     */
    @Override
    public ApiResult thirdPartySupervisionRectification(FromPartyBProblemSolvingListDTO fromPartyBProblemSolvingListDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        try {
            PostDailyPatrolInspectionListDTO getDailyPatrolInspectionListDTO = new PostDailyPatrolInspectionListDTO();

            List<PostDailyPatrolInspectionDTO> getDailyPatrolInspectionList = new ArrayList<PostDailyPatrolInspectionDTO>();
            List<FromPartyBProblemSolvingDTO> fromPartyBProblemSolvingDTOList = fromPartyBProblemSolvingListDTO.getList();
            for (FromPartyBProblemSolvingDTO fromPartyBProblemSolvingDTO : fromPartyBProblemSolvingDTOList) {
                DailyPatrolInspectionEntity dailyPatrolInspection = dailyPatrolInspectionRepository.getInspectionEntityById(fromPartyBProblemSolvingDTO.getInspectionId());
                if (dailyPatrolInspection != null) {
                    if (!"整改中".equals(dailyPatrolInspection.getState())) {
                        PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                        getDailyPatrolInspectionList.add(getInspection);
                        continue;
                    }
                    if (!userPropertyStaffEntity.getStaffId().equals(dailyPatrolInspection.getDealPeople())) {
                        PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                        getDailyPatrolInspectionList.add(getInspection);
                        continue;
                    }
                    DailyPatrolInspectionDetailsEntity dailyPatrolInspectionDetailsEntity = new DailyPatrolInspectionDetailsEntity();
                    if ("通过".equals(fromPartyBProblemSolvingDTO.getState())) {
                        //判断查询出当前整改单是否有甲方负责人，有的话处理人为甲方，没有状态变更完成
                        if (!StringUtil.isEmpty(dailyPatrolInspection.getFirstParty())) {
                            dailyPatrolInspection.setDealPeople(dailyPatrolInspection.getFirstParty());//处理人为甲方
                        } else {
                            dailyPatrolInspection.setState("完成");
                        }
                        dailyPatrolInspectionDetailsEntity.setDetailsState("合格");
                    } else if ("不通过".equals(fromPartyBProblemSolvingDTO.getState())) {
                        dailyPatrolInspection.setDealPeople(dailyPatrolInspection.getAssignId());
                        dailyPatrolInspectionDetailsEntity.setDetailsState("不合格");
                    }
                    dailyPatrolInspection.setModifyOn(new Date());
                    //日常巡检详情
                    dailyPatrolInspectionDetailsEntity.setId(IdGen.uuid());
                    dailyPatrolInspectionDetailsEntity.setInspectionId(fromPartyBProblemSolvingDTO.getInspectionId());//巡检ID
                    dailyPatrolInspectionDetailsEntity.setDescription(fromPartyBProblemSolvingDTO.getDescription());//描述
                    dailyPatrolInspectionDetailsEntity.setType("1");//0 乙方整改  1 第三方监理整改   2 甲方整改
                    dailyPatrolInspectionDetailsEntity.setCreateBy(userPropertyStaffEntity.getStaffId());//创建人ID
                    dailyPatrolInspectionDetailsEntity.setCreateName(userPropertyStaffEntity.getStaffName());//创建人名称
                    dailyPatrolInspectionDetailsEntity.setCreateOn(new Date());//创建时间
                    dailyPatrolInspectionDetailsEntity.setAppId(fromPartyBProblemSolvingDTO.getAppId());
                    dailyPatrolInspectionDetailsEntity.setFrequency(fromPartyBProblemSolvingDTO.getFrequency());//次数
                    try {
                        dailyPatrolInspectionRepository.saveInspectionDetais(dailyPatrolInspectionDetailsEntity);
                        dailyPatrolInspectionRepository.updateInspection(dailyPatrolInspection);//修改主表
                    } catch (Exception e) {
                        PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                        getDailyPatrolInspectionList.add(getInspection);
                        continue;
                    }
                    List<InspectionImageDTO> imageList = fromPartyBProblemSolvingDTO.getImageList();//整改图片
                    if (imageList.size() > 0) {
                        for (InspectionImageDTO InspectionImage : imageList) {
                            ProjectImagesEntity ProjectImagesEntity = new ProjectImagesEntity();
                            ProjectImagesEntity.setId(IdGen.uuid());//id
                            ProjectImagesEntity.setBusinessId(dailyPatrolInspectionDetailsEntity.getId());//当前巡检详情单id
                            ProjectImagesEntity.setUrl(InspectionImage.getImageUrl());//图片链接
                            ProjectImagesEntity.setType("1");
                            ProjectImagesEntity.setState("1");
                            ProjectImagesEntity.setModifyOn(new Date());
                            ProjectImagesEntity.setCreateOn(new Date());
                            dailyPatrolInspectionRepository.saveProjectImages(ProjectImagesEntity);//保存整改图片
                        }
                    }
                    PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                    getDailyPatrolInspectionList.add(getInspection);
                }

            }
            getDailyPatrolInspectionListDTO.setList(getDailyPatrolInspectionList);
            return new SuccessApiResult(getDailyPatrolInspectionListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * 日常巡检处理问题（甲方）
     *
     * @param fromPartyBProblemSolvingListDTO
     * @param userPropertyStaffEntity
     * @return
     */
    @Override
    public ApiResult partyADealWith(FromPartyBProblemSolvingListDTO fromPartyBProblemSolvingListDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        try {
            PostDailyPatrolInspectionListDTO getDailyPatrolInspectionListDTO = new PostDailyPatrolInspectionListDTO();

            List<PostDailyPatrolInspectionDTO> getDailyPatrolInspectionList = new ArrayList<PostDailyPatrolInspectionDTO>();
            List<FromPartyBProblemSolvingDTO> fromPartyBProblemSolvingDTOList = fromPartyBProblemSolvingListDTO.getList();
            for (FromPartyBProblemSolvingDTO fromPartyBProblemSolvingDTO : fromPartyBProblemSolvingDTOList) {
                DailyPatrolInspectionEntity dailyPatrolInspection = dailyPatrolInspectionRepository.getInspectionEntityById(fromPartyBProblemSolvingDTO.getInspectionId());
                if (dailyPatrolInspection != null) {
                    if (!"整改中".equals(dailyPatrolInspection.getState())) {
                        PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                        getDailyPatrolInspectionList.add(getInspection);
                        continue;
                    }
                    if (!userPropertyStaffEntity.getStaffId().equals(dailyPatrolInspection.getDealPeople())) {
                        PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                        getDailyPatrolInspectionList.add(getInspection);
                        continue;
                    }
                    DailyPatrolInspectionDetailsEntity dailyPatrolInspectionDetailsEntity = new DailyPatrolInspectionDetailsEntity();
                    if ("通过".equals(fromPartyBProblemSolvingDTO.getState())) {
                        //
                        dailyPatrolInspection.setState("完成");
                        dailyPatrolInspectionDetailsEntity.setDetailsState("合格");
                    } else if ("不通过".equals(fromPartyBProblemSolvingDTO.getState())) {
                        //不通过转给乙方
                        dailyPatrolInspection.setDealPeople(dailyPatrolInspection.getAssignId());
                        dailyPatrolInspectionDetailsEntity.setDetailsState("不合格");
                    }
                    dailyPatrolInspection.setModifyOn(new Date());
                    //日常巡检详情
                    dailyPatrolInspectionDetailsEntity.setId(IdGen.uuid());
                    dailyPatrolInspectionDetailsEntity.setInspectionId(fromPartyBProblemSolvingDTO.getInspectionId());//巡检ID
                    if (!StringUtil.isEmpty(fromPartyBProblemSolvingDTO.getDescription())) {
                        dailyPatrolInspectionDetailsEntity.setDescription(fromPartyBProblemSolvingDTO.getDescription());//描述
                    } else if ("通过".equals(fromPartyBProblemSolvingDTO.getState())) {
                        dailyPatrolInspectionDetailsEntity.setDescription("同意监理验收！");
                    }
                    dailyPatrolInspectionDetailsEntity.setType("2");//0 乙方整改  1 第三方监理整改   2 甲方整改
                    dailyPatrolInspectionDetailsEntity.setCreateBy(userPropertyStaffEntity.getStaffId());//创建人ID
                    dailyPatrolInspectionDetailsEntity.setCreateName(userPropertyStaffEntity.getStaffName());//创建人名称
                    dailyPatrolInspectionDetailsEntity.setCreateOn(new Date());//创建时间
                    dailyPatrolInspectionDetailsEntity.setAppId(fromPartyBProblemSolvingDTO.getAppId());
                    dailyPatrolInspectionDetailsEntity.setFrequency(fromPartyBProblemSolvingDTO.getFrequency());//次数
                    try {
                        dailyPatrolInspectionRepository.saveInspectionDetais(dailyPatrolInspectionDetailsEntity);
                        dailyPatrolInspectionRepository.updateInspection(dailyPatrolInspection);//修改主表

                    } catch (Exception e) {
                        PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                        getDailyPatrolInspectionList.add(getInspection);
                        continue;
                    }
                    List<InspectionImageDTO> imageList = fromPartyBProblemSolvingDTO.getImageList();//整改图片
                    if (imageList.size() > 0) {
                        for (InspectionImageDTO InspectionImage : imageList) {
                            ProjectImagesEntity ProjectImagesEntity = new ProjectImagesEntity();
                            ProjectImagesEntity.setId(IdGen.uuid());//id
                            ProjectImagesEntity.setBusinessId(dailyPatrolInspectionDetailsEntity.getId());//当前巡检详情单id
                            ProjectImagesEntity.setUrl(InspectionImage.getImageUrl());//图片链接
                            ProjectImagesEntity.setType("1");
                            ProjectImagesEntity.setState("1");
                            ProjectImagesEntity.setModifyOn(new Date());
                            ProjectImagesEntity.setCreateOn(new Date());
                            dailyPatrolInspectionRepository.saveProjectImages(ProjectImagesEntity);//保存整改图片
                        }
                    }
                    PostDailyPatrolInspectionDTO getInspection = getInspectionDTO(dailyPatrolInspection);
                    getDailyPatrolInspectionList.add(getInspection);
                }
            }
            getDailyPatrolInspectionListDTO.setList(getDailyPatrolInspectionList);
            return new SuccessApiResult(getDailyPatrolInspectionListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * 日常巡检（楼栋）统计列表
     *
     * @param patrolInspectionCountDTO
     * @param webPage
     * @return
     */
    @Override
    public ProjectDailyPatrolInspectionDTO searchdailyPatrolInspectionCountList(ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, WebPage webPage, String creaBy) {
        if (patrolInspectionCountDTO == null) {
            new ErrorApiResult("error_00000002");
        }
        Map map = new HashMap();
        map.put("projectId", patrolInspectionCountDTO.getProjectId());
        map.put("tenderId", patrolInspectionCountDTO.getTenderId());
        map.put("buildingId", patrolInspectionCountDTO.getBuildingId());
        map.put("projectList", "NO");//项目权限
        int count = dailyPatrolInspectionRepository.searchDailyPatrolInspectionCount(map);
        int pageCount = dailyPatrolInspectionRepository.getCount(map);
        webPage.setRecordCount(pageCount);
        List<Object[]> list = null;
        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(creaBy);
        if (f) {
            list = dailyPatrolInspectionRepository.searchInspectionCount(map, webPage);
        } else {
            List<ProjectProjectEntity> projectList1 = staffEmployRepository.getProjectListForAgency(creaBy);
            String proejctAll = "";
            if (projectList1 != null) {
                for (ProjectProjectEntity project : projectList1) {
                    proejctAll += "'" + project.getId() + "',";
                }
            }
            List<ProjectProjectEntity> projectList2 = staffEmployRepository.getProjectListByStaffId(creaBy);
            if (projectList2 != null) {
                for (ProjectProjectEntity project : projectList2) {
                    proejctAll += "'" + project.getId() + "',";
                }
            }
            String project = proejctAll.substring(0, proejctAll.length() - 1);
            map.put("projectList", project);//项目权限
            list = dailyPatrolInspectionRepository.searchInspectionCount(map, webPage);
        }


        List<ProjectDailyPatrolInspectionCountDTO> patrolInspectionCountDTOs = new ArrayList<ProjectDailyPatrolInspectionCountDTO>();
        int qualifiedAllToatl = 0;//合格总数
        int unqualifiedAllToatl = 0;//不合格总数
        String qualifiedRate = "";//合格率
        String unqualifiedRate = "";//不合格率
        String resultQualified = "0";
        String resultUnqualified = "0";
        ProjectDailyPatrolInspectionDTO projectDailyPatrolInspectionDTO = new ProjectDailyPatrolInspectionDTO();
        if (list != null && list.size() > 0) {
            List<ProjectDailyPatrolInspectionCountDTO> patrolInspectionCountDTOList = new ArrayList<ProjectDailyPatrolInspectionCountDTO>();
            for (Object[] obj : list) {
                ProjectDailyPatrolInspectionCountDTO dailyPatrolInspectionCountDTO = new ProjectDailyPatrolInspectionCountDTO();
                dailyPatrolInspectionCountDTO.setBuildingName((String) obj[0]);//楼栋名称
//                dailyPatrolInspectionCountDTO.setTenderName((String) obj[1]);//标段名称
                dailyPatrolInspectionCountDTO.setProjectName((String) obj[1]);//项目名称
                dailyPatrolInspectionCountDTO.setQualifiedToatl(((BigInteger) obj[2]).intValue());//合格数
                dailyPatrolInspectionCountDTO.setUnqualifiedToatl(((BigInteger) obj[3]).intValue());//不合格数
                dailyPatrolInspectionCountDTO.setClosses(((BigInteger) obj[4]).intValue());//非正常关闭
                dailyPatrolInspectionCountDTO.setTotal(dailyPatrolInspectionCountDTO.getQualifiedToatl() + dailyPatrolInspectionCountDTO.getUnqualifiedToatl());
                qualifiedAllToatl += dailyPatrolInspectionCountDTO.getQualifiedToatl();
                unqualifiedAllToatl += dailyPatrolInspectionCountDTO.getUnqualifiedToatl();
                patrolInspectionCountDTOs.add(dailyPatrolInspectionCountDTO);
            }

            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            if (count > 0) {
                resultQualified = numberFormat.format((float) qualifiedAllToatl / (float) count * 100);
                resultUnqualified = numberFormat.format((float) unqualifiedAllToatl / (float) count * 100);
            }
            qualifiedRate = resultQualified + "%";//合格率
            unqualifiedRate = resultUnqualified + "%";//不合格率

            projectDailyPatrolInspectionDTO.setQualifiedRate(qualifiedRate);
            projectDailyPatrolInspectionDTO.setUnqualifiedRate(unqualifiedRate);

            projectDailyPatrolInspectionDTO.setList(patrolInspectionCountDTOs);
        }
        return projectDailyPatrolInspectionDTO;
    }

    /**
     * 日常巡检统计列表(项目)
     *
     * @param patrolInspectionCountDTO
     * @param webPage
     * @return
     */
    @Override
    public ProjectDailyPatrolInspectionDTO searchProjectInspectionCountList(ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, WebPage webPage, String creaBy) {
        if (patrolInspectionCountDTO == null) {
            new ErrorApiResult("error_00000002");
        }
        Map map = new HashMap();
        map.put("projectId", patrolInspectionCountDTO.getProjectId());
        map.put("operationId", patrolInspectionCountDTO.getOperationId());

        int pageCount = dailyPatrolInspectionRepository.getProjectCount(map);
        webPage.setRecordCount(pageCount);
        List<Object[]> list = dailyPatrolInspectionRepository.searchInspectionProjecrCount(map, webPage);

        List<ProjectDailyPatrolInspectionCountDTO> patrolInspectionCountDTOs = new ArrayList<ProjectDailyPatrolInspectionCountDTO>();

        ProjectDailyPatrolInspectionDTO projectDailyPatrolInspectionDTO = new ProjectDailyPatrolInspectionDTO();
        if (list != null && list.size() > 0) {
            List<ProjectDailyPatrolInspectionCountDTO> patrolInspectionCountDTOList = new ArrayList<ProjectDailyPatrolInspectionCountDTO>();
            for (Object[] obj : list) {
                ProjectDailyPatrolInspectionCountDTO dailyPatrolInspectionCountDTO = new ProjectDailyPatrolInspectionCountDTO();
                dailyPatrolInspectionCountDTO.setOperationName(obj[1] == null ? "" : obj[1].toString());//区域名称
                dailyPatrolInspectionCountDTO.setProjectName(obj[3] == null ? "" : obj[3].toString());//项目名称
                dailyPatrolInspectionCountDTO.setQualifiedToatl(obj[4] == null ? 0 : ((BigInteger) obj[4]).intValue());//完成数量
                dailyPatrolInspectionCountDTO.setUnqualifiedToatl(obj[5] == null ? 0 : ((BigInteger) obj[5]).intValue());//不合格数
                dailyPatrolInspectionCountDTO.setClosses(obj[6] == null ? 0 : ((BigInteger) obj[6]).intValue());//非正常关闭数量
                dailyPatrolInspectionCountDTO.setTotal(dailyPatrolInspectionCountDTO.getQualifiedToatl() + dailyPatrolInspectionCountDTO.getUnqualifiedToatl());
                patrolInspectionCountDTOs.add(dailyPatrolInspectionCountDTO);
            }
            projectDailyPatrolInspectionDTO.setList(patrolInspectionCountDTOs);
        }
        return projectDailyPatrolInspectionDTO;
    }

    /**
     * 日常巡检统计列表（区域）
     *
     * @param patrolInspectionCountDTO
     * @param webPage
     * @return
     */
    @Override
    public ProjectDailyPatrolInspectionDTO searchPoreatInspectionCountList(ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, WebPage webPage, String creaBy) {
        if (patrolInspectionCountDTO == null) {
            new ErrorApiResult("error_00000002");
        }
        Map map = new HashMap();
        map.put("operationId", patrolInspectionCountDTO.getOperationId());

        int pageCount = dailyPatrolInspectionRepository.getOperatCount(map);
        webPage.setRecordCount(pageCount);
        List<Object[]> list = dailyPatrolInspectionRepository.searchInspectionOperaCount(map, webPage);

        List<ProjectDailyPatrolInspectionCountDTO> patrolInspectionCountDTOs = new ArrayList<ProjectDailyPatrolInspectionCountDTO>();

        ProjectDailyPatrolInspectionDTO projectDailyPatrolInspectionDTO = new ProjectDailyPatrolInspectionDTO();
        if (list != null && list.size() > 0) {
            List<ProjectDailyPatrolInspectionCountDTO> patrolInspectionCountDTOList = new ArrayList<ProjectDailyPatrolInspectionCountDTO>();
            for (Object[] obj : list) {
                ProjectDailyPatrolInspectionCountDTO dailyPatrolInspectionCountDTO = new ProjectDailyPatrolInspectionCountDTO();
                dailyPatrolInspectionCountDTO.setGroupName("中国金茂");
                dailyPatrolInspectionCountDTO.setOperationName(obj[1] == null ? "" : obj[1].toString());//区域名称
                dailyPatrolInspectionCountDTO.setQualifiedToatl(obj[2] == null ? 0 : ((BigInteger) obj[2]).intValue());//完成数量
                dailyPatrolInspectionCountDTO.setUnqualifiedToatl(obj[3] == null ? 0 : ((BigInteger) obj[3]).intValue());//不合格数
                dailyPatrolInspectionCountDTO.setClosses(obj[4] == null ? 0 : ((BigInteger) obj[4]).intValue());//非正常关闭数量
                dailyPatrolInspectionCountDTO.setTotal(dailyPatrolInspectionCountDTO.getQualifiedToatl() + dailyPatrolInspectionCountDTO.getUnqualifiedToatl());//全部
                patrolInspectionCountDTOs.add(dailyPatrolInspectionCountDTO);
            }
            projectDailyPatrolInspectionDTO.setList(patrolInspectionCountDTOs);
        }
        return projectDailyPatrolInspectionDTO;
    }

    /**
     * 查询详情
     *
     * @return PostDailyPatrolInspectionDTO
     */
    @Override
    public GetDailyPatrolInspectionAdminDTO getInspectionByinspectionId(GetDailyPatrolInspectionAdminDTO getDailyPatrolInspectionAdmin, UserInformationEntity userInformationEntity) {
        String type = "";
        if (staffEmployRepository.checkOwner(userInformationEntity.getStaffId(), getDailyPatrolInspectionAdmin.getProjectId(), "1")) {//甲方
            //甲方   该项目下所有数据和自己创建的草稿
            type = "1";

        } else if ("4".equals(staffEmployRepository.getPurviewName(userInformationEntity.getStaffId(), getDailyPatrolInspectionAdmin.getProjectId()))) {//责任方
            //乙方   处理人为自己 + 完成状态  乙方负责人为自己
            type = "3";

        } else if ("5".equals(staffEmployRepository.getPurviewName(userInformationEntity.getStaffId(), getDailyPatrolInspectionAdmin.getProjectId()))) {//监理
            //第三方监理   自己创建 + 第三方监理为自己 + 抄送自己 + 处理人为自己
            type = "2";
        }
        getDailyPatrolInspectionAdmin.setType(type);
        GetDailyPatrolInspectionAdminDTO getDailyPatrolInspection = new GetDailyPatrolInspectionAdminDTO();
        Object[] obj = dailyPatrolInspectionRepository.getInspectionListByAdmin(getDailyPatrolInspectionAdmin.getInspectionId());
        if (obj != null) {
            getDailyPatrolInspection.setInspectionId(obj[0] == null ? "" : obj[0].toString());
            getDailyPatrolInspection.setTitle(obj[1] == null ? "" : obj[1].toString());
            getDailyPatrolInspection.setCreateName(obj[2] == null ? "" : obj[2].toString());
            getDailyPatrolInspection.setCreateOn(obj[3] == null ? "" : obj[3].toString());
            getDailyPatrolInspection.setState(obj[4] == null ? "" : obj[4].toString());
            getDailyPatrolInspection.setProjectId(obj[5] == null ? "" : obj[5].toString());
            getDailyPatrolInspection.setProjectName(obj[6] == null ? "" : obj[6].toString());
            getDailyPatrolInspection.setPoint(obj[7] == null ? "" : obj[7].toString());
            getDailyPatrolInspection.setClassifyThree(obj[8] == null ? "" : obj[8].toString());
            getDailyPatrolInspection.setSeverityLevel(obj[9] == null ? "" : obj[9].toString());
            getDailyPatrolInspection.setRectificationPeriod(obj[10] == null ? "" : DateUtils.format((Date) obj[10], "yyyy-MM-dd"));
            getDailyPatrolInspection.setDescription(obj[11] == null ? "" : obj[11].toString());
            getDailyPatrolInspection.setSupplier(obj[12] == null ? "" : obj[12].toString());
            getDailyPatrolInspection.setAssignName(obj[13] == null ? "" : obj[13].toString());
            getDailyPatrolInspection.setFirstPartyName(obj[14] == null ? "" : obj[14].toString());
            getDailyPatrolInspection.setSupervisorName(obj[15] == null ? "" : obj[15].toString());
            getDailyPatrolInspection.setxCoordinate(obj[16] == null ? "" : obj[16].toString());
            getDailyPatrolInspection.setyCoordinate(obj[17] == null ? "" : obj[17].toString());
            if (obj[19] != null) {
                ProjectHouseImageEntity projectHouseImageEntity = projectHouseImageRepository.getImageByFloorId(obj[19].toString());
                if (projectHouseImageEntity != null) {
                    getDailyPatrolInspection.setHouseTypeId(projectHouseImageEntity.getImgId());
                    getDailyPatrolInspection.setHouseTyprUrl(projectHouseImageEntity.getImgUrl());
                }
            }
            getDailyPatrolInspection.setShutDown(obj[20] == null ? "" : obj[20].toString());
            getDailyPatrolInspection.setShutDownBy(obj[21] == null ? "" : obj[21].toString());
            getDailyPatrolInspection.setShutDownOn(obj[22] == null ? "" : DateUtils.format((Date) obj[22], DateUtils.FORMAT_LONG));
            getDailyPatrolInspection.setType(type);
            if (obj[18] != null) {
                if (obj[18].toString().equals(userInformationEntity.getStaffId())) {
                    getDailyPatrolInspection.setDealState("1");//有处理权限
                } else {
                    getDailyPatrolInspection.setDealState("0");//无处理权限
                }
            } else {
                getDailyPatrolInspection.setDealState("0");//无处理权限
            }
            //判断是否有关闭权限
            boolean check = staffEmployRepository.checkOwner(userInformationEntity.getStaffId(), getDailyPatrolInspectionAdmin.getProjectId(), "4");
            if (check) {
                getDailyPatrolInspection.setShutDownState("1");//有处理权限
            } else {
                getDailyPatrolInspection.setShutDownState("0");//无处理权限
            }
//            if (obj[18] != null) {
//                if (obj[18].toString().equals(userPropertyStaffEntity.getStaffId())) {
//                    getDailyPatrolInspection.setShutDownState("1");//有处理权限
//                } else {
//                    getDailyPatrolInspection.setShutDownState("0");//无处理权限
//                }
//            } else {
//                getDailyPatrolInspection.setShutDownState("0");//无处理权限
//            }
            List<ProjectImagesEntity> projectImagesList = dailyPatrolInspectionRepository.getProjectImages(obj[0] == null ? "" : obj[0].toString());
            List<InspectionImageDTO> getImageList = new ArrayList<InspectionImageDTO>();
            if (projectImagesList != null) {
                for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                    InspectionImageDTO getImage = new InspectionImageDTO();
                    getImage.setId(projectImagesEntity.getId());
                    getImage.setBusinessId(projectImagesEntity.getBusinessId());
                    getImage.setImageUrl(projectImagesEntity.getUrl());
                    getImage.setType(projectImagesEntity.getType());
                    getImage.setState(projectImagesEntity.getState());
                    getImage.setCreateOn(DateUtils.format(projectImagesEntity.getCreateOn()));
                    getImage.setModifyOn(DateUtils.format(projectImagesEntity.getModifyOn()));
                    getImageList.add(getImage);
                }
            }
            getDailyPatrolInspection.setImageList(getImageList);
            List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(obj[0] == null ? "" : obj[0].toString());//抄送人list
            List<CopyDetailsListDTO> copyDetailsList = new ArrayList<CopyDetailsListDTO>();
            if (idList != null) {
                for (Object[] idObj : idList) {
                    CopyDetailsListDTO copyDetailsListDTO = new CopyDetailsListDTO();
                    copyDetailsListDTO.setBusiness(idObj[0] == null ? "" : idObj[0].toString());
                    copyDetailsListDTO.setId(idObj[1] == null ? "" : idObj[1].toString());
                    copyDetailsListDTO.setName(idObj[2] == null ? "" : idObj[2].toString());
                    copyDetailsList.add(copyDetailsListDTO);
                }
            }
            getDailyPatrolInspection.setIdList(copyDetailsList);
            List<PostDailyPatrolInspectionDetailsDTO> inspectionList = new ArrayList<PostDailyPatrolInspectionDetailsDTO>();//整改记录
            List<DailyPatrolInspectionDetailsEntity> dailyPatrolInspectionDetails = dailyPatrolInspectionRepository.getDailyPatrolInspectionDetails(obj[0] == null ? "" : obj[0].toString());
            if (dailyPatrolInspectionDetails != null) {
                for (DailyPatrolInspectionDetailsEntity dailyPatrolInspectionDetailsEntity : dailyPatrolInspectionDetails) {
                    PostDailyPatrolInspectionDetailsDTO postDailyPatrolInspectionDetailsDTO = new PostDailyPatrolInspectionDetailsDTO();
                    postDailyPatrolInspectionDetailsDTO.setId(dailyPatrolInspectionDetailsEntity.getId());
                    postDailyPatrolInspectionDetailsDTO.setInspectionId(dailyPatrolInspectionDetailsEntity.getInspectionId());
                    postDailyPatrolInspectionDetailsDTO.setDescription(dailyPatrolInspectionDetailsEntity.getDescription());
                    postDailyPatrolInspectionDetailsDTO.setCreateName(dailyPatrolInspectionDetailsEntity.getCreateName());
                    postDailyPatrolInspectionDetailsDTO.setCreateBy(dailyPatrolInspectionDetailsEntity.getCreateBy());
                    postDailyPatrolInspectionDetailsDTO.setCreateOn(DateUtils.format(dailyPatrolInspectionDetailsEntity.getCreateOn()));
                    postDailyPatrolInspectionDetailsDTO.setType(dailyPatrolInspectionDetailsEntity.getType());
                    String detailsState = "";//后台使用该字段 拼接
                    if ("0".equals(dailyPatrolInspectionDetailsEntity.getType())) {
                        detailsState = "第" + dailyPatrolInspectionDetailsEntity.getFrequency() + "次整改-乙方";
                    } else if ("1".equals(dailyPatrolInspectionDetailsEntity.getType())) {
                        detailsState = "第" + dailyPatrolInspectionDetailsEntity.getFrequency() + "次验收-监理-" + dailyPatrolInspectionDetailsEntity.getDetailsState();
                    } else if ("2".equals(dailyPatrolInspectionDetailsEntity.getType())) {
                        detailsState = "第" + dailyPatrolInspectionDetailsEntity.getFrequency() + "次验收-甲方-" + dailyPatrolInspectionDetailsEntity.getDetailsState();
                    }
                    postDailyPatrolInspectionDetailsDTO.setDetailsState(detailsState);
                    List<ProjectImagesEntity> postImageList = dailyPatrolInspectionRepository.getProjectImages(dailyPatrolInspectionDetailsEntity.getId());
                    List<InspectionImageDTO> posmageList = new ArrayList<InspectionImageDTO>();//整改记录图片
                    if (postImageList != null) {
                        for (ProjectImagesEntity projectImagesEntity : postImageList) {
                            InspectionImageDTO getImage = new InspectionImageDTO();
                            getImage.setId(projectImagesEntity.getId());
                            getImage.setBusinessId(projectImagesEntity.getBusinessId());
                            getImage.setImageUrl(projectImagesEntity.getUrl());
                            getImage.setType(projectImagesEntity.getType());
                            getImage.setState(projectImagesEntity.getState());
                            getImage.setCreateOn(DateUtils.format(projectImagesEntity.getCreateOn()));
                            getImage.setModifyOn(DateUtils.format(projectImagesEntity.getModifyOn()));
                            posmageList.add(getImage);
                        }
                    }
                    postDailyPatrolInspectionDetailsDTO.setImageList(posmageList);
                    inspectionList.add(postDailyPatrolInspectionDetailsDTO);
                }
                getDailyPatrolInspection.setInspectionList(inspectionList);
            }
        }
        return getDailyPatrolInspection;

    }

    /**
     * 导出统计EXCEL
     *
     * @param title
     * @param headers
     * @param out
     * @param patrolInspectionCountDTO
     * @param webPage
     */
    public void exportCountExcel(String title, String[] headers, ServletOutputStream out, ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, WebPage webPage, String creaBy) {
        if (patrolInspectionCountDTO == null) {
            new ErrorApiResult("error_00000002");
        }
        // 导出数据
        ExportExcel<ProjectDailyPatrolCountExcelDTO> ex = new ExportExcel<ProjectDailyPatrolCountExcelDTO>();

        List<ProjectDailyPatrolCountExcelDTO> patrolInspectionCountDTOs = new ArrayList<ProjectDailyPatrolCountExcelDTO>();
        if (patrolInspectionCountDTO.getTabIndex().equals("1")) {//区域导出excel
            patrolInspectionCountDTOs = areaExportExcel(patrolInspectionCountDTO);
        } else if (patrolInspectionCountDTO.getTabIndex().equals("2")) {//项目导出excel
            patrolInspectionCountDTOs = projectExportExcel(patrolInspectionCountDTO);
        } else if (patrolInspectionCountDTO.getTabIndex().equals("3")) {//楼栋导出excel
            patrolInspectionCountDTOs = buildExportExcel(patrolInspectionCountDTO, creaBy);
        }
        ex.exportExcel(title, headers, patrolInspectionCountDTOs, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");

    }

    /**
     * 第三方监理 + 甲方负责人  验收
     *
     * @return PostDailyPatrolInspectionDTO
     * type 1 甲方  2 乙方
     */
    @Override
    public String checkBeforeAcceptance(GetDailyPatrolInspectionAdminDTO getDailyPatrolInspectionAdmin,  UserInformationEntity userInformationEntity) {
        try {
            //判断 整改id + 状态 + type 是否存在
            if (!StringUtil.isEmpty(getDailyPatrolInspectionAdmin.getInspectionId())
                    && !StringUtil.isEmpty(getDailyPatrolInspectionAdmin.getCheckState())
                    && !StringUtil.isEmpty(getDailyPatrolInspectionAdmin.getType())) {

                //先查询日常巡检单
                DailyPatrolInspectionEntity dailyPatrolInspection = dailyPatrolInspectionRepository.getInspectionEntityById(getDailyPatrolInspectionAdmin.getInspectionId());
                if (dailyPatrolInspection != null) {
                    if ("合格".equals(getDailyPatrolInspectionAdmin.getCheckState())) {
                        //甲方
                        if ("1".equals(getDailyPatrolInspectionAdmin.getType())) {
                            dailyPatrolInspection.setState("完成");
                        }
                        //第三方监理
                        if ("2".equals(getDailyPatrolInspectionAdmin.getType())) {
                            dailyPatrolInspection.setDealPeople(dailyPatrolInspection.getFirstParty());//处理人为甲方
                        } else {
                            dailyPatrolInspection.setState("完成");
                        }
                    } else if ("不合格".equals(getDailyPatrolInspectionAdmin.getCheckState())) {
                        //甲方
                        if ("1".equals(getDailyPatrolInspectionAdmin.getType())) {
                            dailyPatrolInspection.setDealPeople(dailyPatrolInspection.getSupervisor());//处理人为第三方监理
                        }
                        //第三方监理
                        if ("2".equals(getDailyPatrolInspectionAdmin.getType())) {
                            dailyPatrolInspection.setDealPeople(dailyPatrolInspection.getAssignId());//处理人改为乙方
                        }
                    }
                    dailyPatrolInspection.setModifyOn(new Date());
                    dailyPatrolInspectionRepository.updateInspection(dailyPatrolInspection);//修改主表
                    //增加详情表
                    DailyPatrolInspectionDetailsEntity dailyPatrolInspectionDetailsEntity = new DailyPatrolInspectionDetailsEntity();

                    dailyPatrolInspectionDetailsEntity.setId(IdGen.uuid());
                    dailyPatrolInspectionDetailsEntity.setInspectionId(dailyPatrolInspection.getInspectionId());//巡检ID
                    dailyPatrolInspectionDetailsEntity.setDescription(getDailyPatrolInspectionAdmin.getDetailsDescription());//描述
                    if ("1".equals(getDailyPatrolInspectionAdmin.getType())) {
                        dailyPatrolInspectionDetailsEntity.setType("2");//0 乙方整改  1 第三方监理整改   2 甲方整改
                    } else {
                        dailyPatrolInspectionDetailsEntity.setType("1");//0 乙方整改  1 第三方监理整改   2 甲方整改
                    }
                    dailyPatrolInspectionDetailsEntity.setCreateBy(userInformationEntity.getStaffId());//创建人ID
                    dailyPatrolInspectionDetailsEntity.setCreateName(userInformationEntity.getStaffName());//创建人名称
                    dailyPatrolInspectionDetailsEntity.setCreateOn(new Date());//创建时间
                    dailyPatrolInspectionDetailsEntity.setAppId(dailyPatrolInspectionDetailsEntity.getId());
                    dailyPatrolInspectionRepository.saveInspectionDetais(dailyPatrolInspectionDetailsEntity);

                    if (getDailyPatrolInspectionAdmin.getImgFile() != null && getDailyPatrolInspectionAdmin.getImgFile().length > 0) {
                        for (int i = 0; i < getDailyPatrolInspectionAdmin.getImgFile().length; i++) {
                            if (getDailyPatrolInspectionAdmin.getImgFile()[i] != null && !"".equals(getDailyPatrolInspectionAdmin.getImgFile()[i].getOriginalFilename())) {
                                String fileName = imgService.uploadAdminImage(getDailyPatrolInspectionAdmin.getImgFile()[i], ImgType.ACTIVITY);
                                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                                fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");

                                ProjectImagesEntity ProjectImagesEntity = new ProjectImagesEntity();
                                ProjectImagesEntity.setId(IdGen.uuid());//id
                                ProjectImagesEntity.setBusinessId(dailyPatrolInspectionDetailsEntity.getId());//当前巡检详情单id
                                ProjectImagesEntity.setUrl(fileName);//图片链接
                                ProjectImagesEntity.setType("1");
                                ProjectImagesEntity.setState("1");
                                ProjectImagesEntity.setModifyOn(new Date());
                                ProjectImagesEntity.setCreateOn(new Date());
                                dailyPatrolInspectionRepository.saveProjectImages(ProjectImagesEntity);//保存整改图片
                            }
                        }
                    }
                    return "1";
                }

            }
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * 后台非正常关闭
     */
    @Override
    public String shutDownAdmin(GetDailyPatrolInspectionAdminDTO getDailyPatrolInspectionAdmin,UserInformationEntity userInformationEntity) {
        try {
            //判断 整改id + 状态 + type 是否存在
            if (!StringUtil.isEmpty(getDailyPatrolInspectionAdmin.getInspectionId())) {
                //先查询日常巡检单
                DailyPatrolInspectionEntity dailyPatrolInspection = dailyPatrolInspectionRepository.getInspectionEntityById(getDailyPatrolInspectionAdmin.getInspectionId());
                if (dailyPatrolInspection != null) {
                    dailyPatrolInspection.setModifyOn(new Date());
                    dailyPatrolInspection.setShutDownOn(new Date());
                    dailyPatrolInspection.setShutDownBy(userInformationEntity.getStaffName());
                    dailyPatrolInspection.setState("非正常关闭");
                    //如果理由部位空
                    if (!StringUtil.isEmpty(getDailyPatrolInspectionAdmin.getShutDown())) {
                        dailyPatrolInspection.setShutDown(getDailyPatrolInspectionAdmin.getShutDown());
                    }
                    dailyPatrolInspectionRepository.updateInspection(dailyPatrolInspection);//修改主表
                    return "1";
                }
            }
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public void exportExcel(String title, String[] headers, ServletOutputStream out, GetDailyPatrolInspectionDTO getDailyPatrolInspectionDTO, WebPage webPage, String creaBy) {
        Map map = new HashMap();
        map.put("projectId", getDailyPatrolInspectionDTO.getProjectId());//项目id
        map.put("buildingId", getDailyPatrolInspectionDTO.getBuildingId());//楼栋id
        map.put("state", getDailyPatrolInspectionDTO.getState());//状态
        map.put("classfiyOne", getDailyPatrolInspectionDTO.getClassifyOne());//一级分类
        map.put("classfiyTwo", getDailyPatrolInspectionDTO.getClassifyTwo());//二级分类
        map.put("classfiyThree", getDailyPatrolInspectionDTO.getClassifyThree());//三级分类

        map.put("severityLevel", getDailyPatrolInspectionDTO.getSeverityLevel());//严重等级
        map.put("startDate", getDailyPatrolInspectionDTO.getStartDate());//开始日期
        map.put("endDate", getDailyPatrolInspectionDTO.getEndDate());//结束时间

        map.put("supplier", "");//责任单位
        map.put("firstPartyName", "");//甲方负责人名字
        map.put("supervisorName", "");//第三方监理名字
        map.put("createName", "");//创建人
        map.put("assignName", "");//整改人名字
        map.put("projectList", "NO");//项目权限
        map.put("creaBy", creaBy);
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getSupplier())) {
            map.put("supplier", "%" + getDailyPatrolInspectionDTO.getSupplier() + "%");//责任单位
        }
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getFirstPartyName())) {
            map.put("firstPartyName", "%" + getDailyPatrolInspectionDTO.getFirstPartyName() + "%");//甲方负责人名字
        }
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getSupervisorName())) {
            map.put("supervisorName", "%" + getDailyPatrolInspectionDTO.getSupervisorName() + "%");
        }
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getAssignName())) {
            map.put("assignName", "%" + getDailyPatrolInspectionDTO.getAssignName() + "%");
        }
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getCreateName())) {
            map.put("createName", "%" + getDailyPatrolInspectionDTO.getCreateName() + "%");
        }
        List<Object[]> list = null;
        List<ExportExcelInspectionDTO> retList = new ArrayList<ExportExcelInspectionDTO>();
        if (!StringUtil.isEmpty(getDailyPatrolInspectionDTO.getProjectId())) {
            list = dailyPatrolInspectionRepository.getexportExcelList(map);
        }
//        else {
//            boolean f = getAllClassifyService.getRoleViewmodelByStaffId(creaBy);
//            if (f) {
//                list = dailyPatrolInspectionRepository.getexportExcelList(map);
//            } else {
//                List<ProjectProjectEntity> projectList1 = staffEmployRepository.getProjectListForAgency(creaBy);
//                String proejctAll = "";
//                if (projectList1 != null) {
//                    for (ProjectProjectEntity project : projectList1) {
//                        proejctAll += "'" + project.getId() + "',";
//                    }
//                }
//                List<ProjectProjectEntity> projectList2 = staffEmployRepository.getProjectListByStaffId(creaBy);
//                if (projectList2 != null) {
//                    for (ProjectProjectEntity project : projectList2) {
//                        proejctAll += "'" + project.getId() + "',";
//                    }
//                }
//                String project = proejctAll.substring(0, proejctAll.length() - 1);
//                map.put("projectList", project);//项目权限
//                list = dailyPatrolInspectionRepository.getexportExcelList(map);
//            }
//        }
        if (list != null && !list.isEmpty()) {
            int num = 1;
            for (Object[] obj : list) {
                ExportExcelInspectionDTO exportExcelInspection = new ExportExcelInspectionDTO();
                //序号
                exportExcelInspection.setNum(num++);
//                //超期？
//                exportExcelInspection.setOverdue("否");
//                if (obj[0] != null) {
//                    if (DateUtils.parse(obj[0].toString()).before(new Date())) {
//                        exportExcelInspection.setOverdue("是");
//                    }
//                }
                exportExcelInspection.setProjectName(obj[1] == null ? "" : obj[1].toString());//项目名称
                exportExcelInspection.setAddress((obj[2] == null ? "" : obj[2].toString()) + (obj[3] == null ? "" : obj[3].toString()));
                exportExcelInspection.setSeverityLevel(obj[4] == null ? "" : obj[4].toString());//严重等级
                exportExcelInspection.setClassifyThree(obj[5] == null ? "" : obj[5].toString());//三级分类
                exportExcelInspection.setSupplier(obj[6] == null ? "" : obj[6].toString());//责任单位
                exportExcelInspection.setFirstPartyName(obj[7] == null ? "" : obj[7].toString());//甲方负责人名字
                exportExcelInspection.setSupervisorName(obj[8] == null ? "" : obj[8].toString());//第三方监理名字
                exportExcelInspection.setAssignName(obj[9] == null ? "" : obj[9].toString());//整改人名字
                exportExcelInspection.setCreateOn(obj[10] == null ? "" : DateUtils.format(DateUtils.parse(obj[10].toString(), "yyyy-MM-dd"), "yyyy-MM-dd"));//创建时间
                exportExcelInspection.setDescription(obj[11] == null ? "" : obj[11].toString());//描述
                exportExcelInspection.setPoint(obj[12] == null ? "" : obj[12].toString());//部位
                exportExcelInspection.setState(obj[13] == null ? "" : obj[13].toString());//状态
                exportExcelInspection.setClassifyOne(obj[14] == null ? "" : obj[14].toString());//一级分类
                exportExcelInspection.setClassifyTwo(obj[15] == null ? "" : obj[15].toString());//二级分类
                exportExcelInspection.setCreateName(obj[16] == null ? "" : obj[16].toString());//创建人
                retList.add(exportExcelInspection);
            }
        }
        ExportExcel<ExportExcelInspectionDTO> ex = new ExportExcel<ExportExcelInspectionDTO>();
        ex.exportExcel(title, headers, retList, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    /**
     * 草稿详情
     */
    @Override
    public GetDailyPatrolInspectionDraftDTO getDraftInspection(GetDailyPatrolInspectionAdminDTO getDailyPatrolInspectionAdmin) {
        GetDailyPatrolInspectionDraftDTO dailyPatrolInspectionDraft = new GetDailyPatrolInspectionDraftDTO();

        DailyPatrolInspectionEntity dailyInspectionEntity = dailyPatrolInspectionRepository.getDailyPatrolInspection(getDailyPatrolInspectionAdmin.getInspectionId());
        if (dailyInspectionEntity != null) {

            dailyPatrolInspectionDraft.setInspectionId(dailyInspectionEntity.getInspectionId());//日常巡检id
            dailyPatrolInspectionDraft.setTitle(dailyInspectionEntity.getTitle());//巡检标题
            dailyPatrolInspectionDraft.setCreateName(dailyInspectionEntity.getCreateName());//创建人名字
            dailyPatrolInspectionDraft.setCreateOn(DateUtils.format(dailyInspectionEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
            dailyPatrolInspectionDraft.setState(dailyInspectionEntity.getState());//状态
            dailyPatrolInspectionDraft.setProjectName(dailyInspectionEntity.getProjectNum());//工程项目名称
            dailyPatrolInspectionDraft.setPoint(dailyInspectionEntity.getPointId());//详细位置
            dailyPatrolInspectionDraft.setClassifyOne(dailyInspectionEntity.getClassifyOne());//一级分类
            dailyPatrolInspectionDraft.setClassifyTwo(dailyInspectionEntity.getClassifyTwo());//二级分类
            dailyPatrolInspectionDraft.setClassifyThree(dailyInspectionEntity.getClassifyThree());//三级分类
            dailyPatrolInspectionDraft.setSeverityLevel(dailyInspectionEntity.getSeverityLevel());//严重等级
            dailyPatrolInspectionDraft.setDescription(dailyInspectionEntity.getDescription());//描述
            dailyPatrolInspectionDraft.setFirstParty(dailyInspectionEntity.getFirstParty());//甲方负责人ID
            dailyPatrolInspectionDraft.setAssignId(dailyInspectionEntity.getAssignId());//整改人ID
            dailyPatrolInspectionDraft.setSupervisor(dailyInspectionEntity.getSupervisor());//第三方监理id
            dailyPatrolInspectionDraft.setSupplierId(dailyInspectionEntity.getSupplierId());//责任单位ID
            dailyPatrolInspectionDraft.setRectificationPeriod(DateUtils.format(dailyInspectionEntity.getRectificationPeriod(), DateUtils.FORMAT_SHORT));//整改时限
            dailyPatrolInspectionDraft.setAssignName(dailyInspectionEntity.getAssignName());//整改人名字
            dailyPatrolInspectionDraft.setFirstPartyName(dailyInspectionEntity.getFirstPartyName()); //甲方负责人名字
            dailyPatrolInspectionDraft.setSupervisorName(dailyInspectionEntity.getSupervisorName());//第三方监理名字
            dailyPatrolInspectionDraft.setSupplier(dailyInspectionEntity.getSupplier());//责任单位
            dailyPatrolInspectionDraft.setxCoordinates(String.valueOf(dailyInspectionEntity.getxCoordinate()));
            dailyPatrolInspectionDraft.setyCoordinates(String.valueOf(dailyInspectionEntity.getyCoordinate()));
            ProjectHouseImageEntity projectHouseImageEntity = projectHouseImageRepository.getImageByFloorId(dailyInspectionEntity.getFloorId());
            if (projectHouseImageEntity != null) {
                dailyPatrolInspectionDraft.setHouseTypeId(projectHouseImageEntity.getImgId());
                dailyPatrolInspectionDraft.setHouseTyprUrl(projectHouseImageEntity.getImgUrl());
            }
            List<ProjectImagesEntity> projectImagesList = dailyPatrolInspectionRepository.getProjectImages(dailyInspectionEntity.getInspectionId());
            List<InspectionImageDTO> getImageList = new ArrayList<InspectionImageDTO>();
            if (projectImagesList != null) {
                for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                    InspectionImageDTO getImage = new InspectionImageDTO();
                    getImage.setId(projectImagesEntity.getId());
                    getImage.setBusinessId(projectImagesEntity.getBusinessId());
                    getImage.setImageUrl(projectImagesEntity.getUrl());
                    getImage.setType(projectImagesEntity.getType());
                    getImage.setState(projectImagesEntity.getState());
                    getImage.setCreateOn(DateUtils.format(projectImagesEntity.getCreateOn()));
                    getImage.setModifyOn(DateUtils.format(projectImagesEntity.getModifyOn()));
                    getImageList.add(getImage);
                }
            }
            dailyPatrolInspectionDraft.setImageList(getImageList);
        }
        return dailyPatrolInspectionDraft;
    }

    @Override
    public String updateInspectionDraft(GetDailyPatrolInspectionDraftDTO getDailyPatrolInspectionDraftDTO,  UserInformationEntity userInformationEntity) {

        try {
            DailyPatrolInspectionEntity dailyInspectionEntity = dailyPatrolInspectionRepository.getDailyPatrolInspection(getDailyPatrolInspectionDraftDTO.getInspectionId());
            dailyInspectionEntity.setState("整改中");
            dailyInspectionEntity.setModifyOn(new Date());
            //判断管理平台传入 整改人是否为空 和是否修改
            if (!StringUtil.isEmpty(getDailyPatrolInspectionDraftDTO.getAssignId()) && !dailyInspectionEntity.getAssignId().equals(getDailyPatrolInspectionDraftDTO.getAssignId())) {
                dailyInspectionEntity.setAssignId(getDailyPatrolInspectionDraftDTO.getAssignId());
                UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(getDailyPatrolInspectionDraftDTO.getAssignId());
                if (UserStaff != null) {
                    dailyInspectionEntity.setAssignName(UserStaff.getStaffName());
                }
                dailyInspectionEntity.setDealPeople(getDailyPatrolInspectionDraftDTO.getAssignId());
            }
            //判断 第三方监理是否存在值 和是否修改
            if (!StringUtil.isEmpty(getDailyPatrolInspectionDraftDTO.getSupervisor()) && !dailyInspectionEntity.getSupervisor().equals(getDailyPatrolInspectionDraftDTO.getSupervisor())) {
                dailyInspectionEntity.setSupervisor(getDailyPatrolInspectionDraftDTO.getSupervisor());
                UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(getDailyPatrolInspectionDraftDTO.getSupervisor());
                if (UserStaff != null) {
                    dailyInspectionEntity.setSupervisorName(UserStaff.getStaffName());
                }
            }
            //判断详细位置
            if (!StringUtil.isEmpty(getDailyPatrolInspectionDraftDTO.getPoint()) && !dailyInspectionEntity.getPointId().equals(getDailyPatrolInspectionDraftDTO.getPoint())) {
                dailyInspectionEntity.setPointId(getDailyPatrolInspectionDraftDTO.getPoint());
            }
            //判断责任单位
            if (!StringUtil.isEmpty(getDailyPatrolInspectionDraftDTO.getSupplierId()) && !dailyInspectionEntity.getSupplierId().equals(getDailyPatrolInspectionDraftDTO.getSupplierId())) {
                dailyInspectionEntity.setSupplierId(getDailyPatrolInspectionDraftDTO.getSupplierId());
                AgencyEntity agency = agencyRepository.getAgencyDetail(getDailyPatrolInspectionDraftDTO.getSupplierId());
                if (agency != null) {
                    dailyInspectionEntity.setSupplier(agency.getAgencyName());
                }
            }
            //一级分类
            if (!StringUtil.isEmpty(getDailyPatrolInspectionDraftDTO.getClassifyOne())) {
                dailyInspectionEntity.setClassifyOne(getDailyPatrolInspectionDraftDTO.getClassifyOne());
            }
            //二级分类
            if (!StringUtil.isEmpty(getDailyPatrolInspectionDraftDTO.getClassifyTwo())) {
                dailyInspectionEntity.setClassifyTwo(getDailyPatrolInspectionDraftDTO.getClassifyTwo());
            }
            //三级分类
            if (!StringUtil.isEmpty(getDailyPatrolInspectionDraftDTO.getClassifyThree())) {
                dailyInspectionEntity.setClassifyThree(getDailyPatrolInspectionDraftDTO.getClassifyThree());
            }
            //严重等级
            if (!StringUtil.isEmpty(getDailyPatrolInspectionDraftDTO.getSeverityLevel())) {
                dailyInspectionEntity.setSeverityLevel(getDailyPatrolInspectionDraftDTO.getSeverityLevel());
            }
            //描述
            if (!StringUtil.isEmpty(getDailyPatrolInspectionDraftDTO.getDescription())) {
                dailyInspectionEntity.setDescription(getDailyPatrolInspectionDraftDTO.getDescription());
            }
            //删除不在包含的图片
            if (getDailyPatrolInspectionDraftDTO.getImage() != null && !getDailyPatrolInspectionDraftDTO.getImage().isEmpty()) {
                dailyPatrolInspectionRepository.deleteByNotIds(getDailyPatrolInspectionDraftDTO.getImage());
            }
            //保存新增图片
            if (getDailyPatrolInspectionDraftDTO.getImgFile() != null && getDailyPatrolInspectionDraftDTO.getImgFile().length > 0) {
                for (int i = 0; i < getDailyPatrolInspectionDraftDTO.getImgFile().length; i++) {
                    if (getDailyPatrolInspectionDraftDTO.getImgFile()[i] != null && !"".equals(getDailyPatrolInspectionDraftDTO.getImgFile()[i].getOriginalFilename())) {
                        String fileName = imgService.uploadAdminImage(getDailyPatrolInspectionDraftDTO.getImgFile()[i], ImgType.ACTIVITY);
                        String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                        fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");

                        ProjectImagesEntity ProjectImagesEntity = new ProjectImagesEntity();
                        ProjectImagesEntity.setId(IdGen.uuid());//id
                        ProjectImagesEntity.setBusinessId(dailyInspectionEntity.getInspectionId());//当前巡检详情单id
                        ProjectImagesEntity.setUrl(fileName);//图片链接
                        ProjectImagesEntity.setType("1");
                        ProjectImagesEntity.setState("1");
                        ProjectImagesEntity.setModifyOn(new Date());
                        ProjectImagesEntity.setCreateOn(new Date());
                        dailyPatrolInspectionRepository.saveProjectImages(ProjectImagesEntity);//保存整改图片
                    }
                }
            }
            if (!StringUtil.isEmpty(getDailyPatrolInspectionDraftDTO.getRectificationPeriod())) {
                dailyInspectionEntity.setRectificationPeriod(DateUtils.parse(getDailyPatrolInspectionDraftDTO.getRectificationPeriod(), DateUtils.FORMAT_SHORT));
            }
            dailyPatrolInspectionRepository.updateInspection(dailyInspectionEntity);//修改主表
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public void exportWord(HttpServletRequest request, HttpServletResponse response, List<GetDailyPatrolInspectionDraftDTO> dailyInspectionDTO) {
        try {
            if (dailyInspectionDTO != null) {
                HttpSession session = request.getSession();
                ServletContext application = session.getServletContext();
                String serverRealPath = application.getRealPath("");
                String oldFilePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "exportWord.jpg"; //用于将远程图片下载到tomcat中
                Map<String, Object> dataMap = new HashMap<String, Object>();// 要填入模本的数据文件
                List<Map<String, Object>> dataMapList = new ArrayList<Map<String, Object>>();
                int i = 1;
                for (GetDailyPatrolInspectionDraftDTO draftDTO : dailyInspectionDTO) {
                    Map<String, Object> draftDataMap = new HashMap<String, Object>();
                    dataMap.put("projectName", draftDTO.getProjectName());//项目名称
                    dataMap.put("exportDate", DateUtils.format(new Date(), DateUtils.FORMAT_SHORT_CN));//导出日期
                    draftDataMap.put("number", i);
                    draftDataMap.put("title", draftDTO.getTitle());//批次名称
                    draftDataMap.put("description", draftDTO.getDescription());//问题描述

                    List<Map<String, String>> imageList = new ArrayList<Map<String, String>>();
                    int k = 1;
                    if (draftDTO.getImageList() != null && draftDTO.getImageList().size() > 0) {
                        for (InspectionImageDTO inspectionImageDTO : draftDTO.getImageList()) {
                            ExportDoc.saveImage(inspectionImageDTO.getImageUrl(), request);
                            Map<String, String> imageMap = new HashMap<String, String>();
                            imageMap.put("imageUrl", ExportDoc.getImageStr(oldFilePath));
                            imageMap.put("imageIndex", ExportDoc.getImgName(inspectionImageDTO.getImageUrl()));
                            imageList.add(imageMap);
                            k++;
                        }
                    }
                    draftDataMap.put("imageList", imageList);
                    i++;
                    dataMapList.add(draftDataMap);
                }
                ExportDoc exportDoc = new ExportDoc();
                dataMap.put("list", dataMapList);
                String fileName = "日常巡检";
                String templetName = "inspection.ftl";
                exportDoc.create(dataMap, response, request, fileName, templetName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GetDailyPatrolInspectionDraftDTO> getDraftInspectionList(GetDailyPatrolInspectionAdminDTO dailyPatrolInspectionAdminDTO) {
        List<GetDailyPatrolInspectionDraftDTO> draftDTOS = new ArrayList<GetDailyPatrolInspectionDraftDTO>();
//        String arr[] = dailyPatrolInspectionAdminDTO.getInspectionId().split(",");
//        for (String item : arr) {
//
//        }
        List<DailyPatrolInspectionEntity> dailyInspectionEntityList = dailyPatrolInspectionRepository.getDailyPatrolInspectionByInspectionId(dailyPatrolInspectionAdminDTO.getInspectionId());
        if (dailyInspectionEntityList != null) {
            for (DailyPatrolInspectionEntity dailyInspectionEntity : dailyInspectionEntityList) {
                GetDailyPatrolInspectionDraftDTO dailyPatrolInspectionDraft = new GetDailyPatrolInspectionDraftDTO();
                dailyPatrolInspectionDraft.setInspectionId(dailyInspectionEntity.getInspectionId());//日常巡检id
                dailyPatrolInspectionDraft.setTitle(dailyInspectionEntity.getTitle());//巡检标题
                dailyPatrolInspectionDraft.setCreateName(dailyInspectionEntity.getCreateName());//创建人名字
                dailyPatrolInspectionDraft.setCreateOn(DateUtils.format(dailyInspectionEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
                dailyPatrolInspectionDraft.setState(dailyInspectionEntity.getState());//状态
                dailyPatrolInspectionDraft.setProjectName(dailyInspectionEntity.getProjectNum());//工程项目名称
                dailyPatrolInspectionDraft.setPoint(dailyInspectionEntity.getPointId());//详细位置
                dailyPatrolInspectionDraft.setClassifyOne(dailyInspectionEntity.getClassifyOne());//一级分类
                dailyPatrolInspectionDraft.setClassifyTwo(dailyInspectionEntity.getClassifyTwo());//二级分类
                dailyPatrolInspectionDraft.setClassifyThree(dailyInspectionEntity.getClassifyThree());//三级分类
                dailyPatrolInspectionDraft.setSeverityLevel(dailyInspectionEntity.getSeverityLevel());//严重等级
                dailyPatrolInspectionDraft.setDescription(dailyInspectionEntity.getDescription());//描述
                dailyPatrolInspectionDraft.setFirstParty(dailyInspectionEntity.getFirstParty());//甲方负责人ID
                dailyPatrolInspectionDraft.setAssignId(dailyInspectionEntity.getAssignId());//整改人ID
                dailyPatrolInspectionDraft.setSupervisor(dailyInspectionEntity.getSupervisor());//第三方监理id
                dailyPatrolInspectionDraft.setSupplierId(dailyInspectionEntity.getSupplierId());//责任单位ID
                dailyPatrolInspectionDraft.setRectificationPeriod(DateUtils.format(dailyInspectionEntity.getRectificationPeriod(), DateUtils.FORMAT_SHORT));//整改时限
                dailyPatrolInspectionDraft.setAssignName(dailyInspectionEntity.getAssignName());//整改人名字
                dailyPatrolInspectionDraft.setFirstPartyName(dailyInspectionEntity.getFirstPartyName()); //甲方负责人名字
                dailyPatrolInspectionDraft.setSupervisorName(dailyInspectionEntity.getSupervisorName());//第三方监理名字
                dailyPatrolInspectionDraft.setSupplier(dailyInspectionEntity.getSupplier());//责任单位
                dailyPatrolInspectionDraft.setxCoordinates(String.valueOf(dailyInspectionEntity.getxCoordinate()));
                dailyPatrolInspectionDraft.setyCoordinates(String.valueOf(dailyInspectionEntity.getyCoordinate()));
                ProjectHouseImageEntity projectHouseImageEntity = projectHouseImageRepository.getImageByFloorId(dailyInspectionEntity.getFloorId());
                if (projectHouseImageEntity != null) {
                    dailyPatrolInspectionDraft.setHouseTypeId(projectHouseImageEntity.getImgId());
                    dailyPatrolInspectionDraft.setHouseTyprUrl(projectHouseImageEntity.getImgUrl());
                }
                List<ProjectImagesEntity> projectImagesList = dailyPatrolInspectionRepository.getProjectImages(dailyInspectionEntity.getInspectionId());
                List<InspectionImageDTO> getImageList = new ArrayList<InspectionImageDTO>();
                if (projectImagesList != null) {
                    for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                        InspectionImageDTO getImage = new InspectionImageDTO();
                        getImage.setId(projectImagesEntity.getId());
                        getImage.setBusinessId(projectImagesEntity.getBusinessId());
                        getImage.setImageUrl(projectImagesEntity.getUrl());
                        getImage.setType(projectImagesEntity.getType());
                        getImage.setState(projectImagesEntity.getState());
                        getImage.setCreateOn(DateUtils.format(projectImagesEntity.getCreateOn()));
                        getImage.setModifyOn(DateUtils.format(projectImagesEntity.getModifyOn()));
                        getImageList.add(getImage);
                    }
                }
                dailyPatrolInspectionDraft.setImageList(getImageList);
                draftDTOS.add(dailyPatrolInspectionDraft);
            }
        }
        return draftDTOS;
    }

    public PostDailyPatrolInspectionDTO getInspectionDTO(DailyPatrolInspectionEntity dailyPatrolInspection) {
        PostDailyPatrolInspectionDTO getDailyPatrolInspection = new PostDailyPatrolInspectionDTO();
        List<InspectionImageDTO> getimageList = new ArrayList<InspectionImageDTO>();//******************返回数据list*******整改图片*********
        getDailyPatrolInspection.setInspectionId(dailyPatrolInspection.getInspectionId() == null ? "" : dailyPatrolInspection.getInspectionId());//--------------返回数据
        getDailyPatrolInspection.setSupervisor(dailyPatrolInspection.getSupervisor() == null ? "" : dailyPatrolInspection.getSupervisor());//--------------返回数据--------第三方监理id
        getDailyPatrolInspection.setSupervisorName(dailyPatrolInspection.getSupervisorName() == null ? "" : dailyPatrolInspection.getSupervisorName());//--------------返回数据--------第三方监理姓名
        getDailyPatrolInspection.setPoint(dailyPatrolInspection.getPointId() == null ? "" : dailyPatrolInspection.getPointId());
        getDailyPatrolInspection.setProjectId(dailyPatrolInspection.getProjectId() == null ? "" : dailyPatrolInspection.getProjectId());
        getDailyPatrolInspection.setBuildingId(dailyPatrolInspection.getBuildingId() == null ? "" : dailyPatrolInspection.getBuildingId());
        getDailyPatrolInspection.setFloorId(dailyPatrolInspection.getFloorId() == null ? "" : dailyPatrolInspection.getFloorId());
        getDailyPatrolInspection.setSupplierId(dailyPatrolInspection.getSupplierId() == null ? "" : dailyPatrolInspection.getSupplierId());
        getDailyPatrolInspection.setSupplier(dailyPatrolInspection.getSupplier() == null ? "" : dailyPatrolInspection.getSupplier());
        getDailyPatrolInspection.setAssignName(dailyPatrolInspection.getAssignName() == null ? "" : dailyPatrolInspection.getAssignName());
        getDailyPatrolInspection.setAssignId(dailyPatrolInspection.getAssignId() == null ? "" : dailyPatrolInspection.getAssignId());
        getDailyPatrolInspection.setTitle(dailyPatrolInspection.getTitle() == null ? "" : dailyPatrolInspection.getTitle());
        getDailyPatrolInspection.setDescription(dailyPatrolInspection.getDescription() == null ? "" : dailyPatrolInspection.getDescription());
        getDailyPatrolInspection.setxCoordinate(dailyPatrolInspection.getxCoordinate() == null ? "" : dailyPatrolInspection.getxCoordinate().toString());
        getDailyPatrolInspection.setyCoordinate(dailyPatrolInspection.getyCoordinate() == null ? "" : dailyPatrolInspection.getyCoordinate().toString());
        getDailyPatrolInspection.setState(dailyPatrolInspection.getState() == null ? "" : dailyPatrolInspection.getState());
        getDailyPatrolInspection.setCreateName(dailyPatrolInspection.getCreateName() == null ? "" : dailyPatrolInspection.getCreateName());
        getDailyPatrolInspection.setCreateOn(dailyPatrolInspection.getCreateOn() == null ? null : DateUtils.format(dailyPatrolInspection.getCreateOn()));
        getDailyPatrolInspection.setClassifyOne(dailyPatrolInspection.getClassifyOne() == null ? "" : dailyPatrolInspection.getClassifyOne());
        getDailyPatrolInspection.setClassifyTwo(dailyPatrolInspection.getClassifyTwo() == null ? "" : dailyPatrolInspection.getClassifyTwo());
        getDailyPatrolInspection.setClassifyThree(dailyPatrolInspection.getClassifyThree() == null ? "" : dailyPatrolInspection.getClassifyThree());
        getDailyPatrolInspection.setSeverityLevel(dailyPatrolInspection.getSeverityLevel() == null ? "" : dailyPatrolInspection.getSeverityLevel());
        getDailyPatrolInspection.setRectificationPeriod(dailyPatrolInspection.getRectificationPeriod() == null ? "" : DateUtils.format(dailyPatrolInspection.getRectificationPeriod(), "yyyy-MM-dd"));
        getDailyPatrolInspection.setModifyOn(dailyPatrolInspection.getModifyOn() == null ? null : DateUtils.format(dailyPatrolInspection.getModifyOn()));
        getDailyPatrolInspection.setClassifyOneName(dailyPatrolInspection.getClassifyOneName() == null ? "" : dailyPatrolInspection.getClassifyOneName());
        getDailyPatrolInspection.setClassifyTwoName(dailyPatrolInspection.getClassifyTwoName() == null ? "" : dailyPatrolInspection.getClassifyTwoName());
        getDailyPatrolInspection.setClassifyThreeName(dailyPatrolInspection.getClassifyThreeName() == null ? "" : dailyPatrolInspection.getClassifyThreeName());
        getDailyPatrolInspection.setDealPeople(dailyPatrolInspection.getDealPeople() == null ? "" : dailyPatrolInspection.getDealPeople());
        getDailyPatrolInspection.setAppId(dailyPatrolInspection.getAppId() == null ? "" : dailyPatrolInspection.getAppId());
        getDailyPatrolInspection.setFirstPartyName(dailyPatrolInspection.getFirstPartyName() == null ? "" : dailyPatrolInspection.getFirstPartyName());
        getDailyPatrolInspection.setFirstParty(dailyPatrolInspection.getFirstParty() == null ? "" : dailyPatrolInspection.getFirstParty());
        getDailyPatrolInspection.setProjectName(dailyPatrolInspection.getProjectNum() == null ? "" : dailyPatrolInspection.getProjectNum());
        getDailyPatrolInspection.setBuildingName(dailyPatrolInspection.getBuildingNum() == null ? "" : dailyPatrolInspection.getBuildingNum());
        getDailyPatrolInspection.setFloorName(dailyPatrolInspection.getFloorNum() == null ? "" : dailyPatrolInspection.getFloorNum());
        List<ProjectImagesEntity> projectImagesList = dailyPatrolInspectionRepository.getProjectImages(dailyPatrolInspection.getInspectionId());
        if (projectImagesList != null) {
            for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                InspectionImageDTO getImage = new InspectionImageDTO();
                getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());
                getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());
                getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());
                getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());
                getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? null : DateUtils.format(projectImagesEntity.getCreateOn()));
                getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? null : DateUtils.format(projectImagesEntity.getModifyOn()));
                getimageList.add(getImage);
            }
        }
        getDailyPatrolInspection.setImageList(getimageList);
        List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(dailyPatrolInspection.getInspectionId());//抄送人list
        List<CopyDetailsListDTO> copyDetailsList = new ArrayList<CopyDetailsListDTO>();
        if (idList != null) {
            for (Object[] idObj : idList) {
                CopyDetailsListDTO copyDetailsListDTO = new CopyDetailsListDTO();
                copyDetailsListDTO.setBusiness(idObj[0] == null ? "" : idObj[0].toString());
                copyDetailsListDTO.setId(idObj[1] == null ? "" : idObj[1].toString());
                copyDetailsListDTO.setName(idObj[2] == null ? "" : idObj[2].toString());
                copyDetailsList.add(copyDetailsListDTO);
            }
        }
        getDailyPatrolInspection.setIdList(copyDetailsList);
        List<PostDailyPatrolInspectionDetailsDTO> inspectionList = new ArrayList<PostDailyPatrolInspectionDetailsDTO>();//整改记录
        List<DailyPatrolInspectionDetailsEntity> dailyPatrolInspectionDetails = dailyPatrolInspectionRepository.getDailyPatrolInspectionDetails(dailyPatrolInspection.getInspectionId());
        if (dailyPatrolInspectionDetails != null) {
            for (DailyPatrolInspectionDetailsEntity dailyPatrolInspectionDetailsEntity : dailyPatrolInspectionDetails) {
                PostDailyPatrolInspectionDetailsDTO postDailyPatrolInspectionDetailsDTO = new PostDailyPatrolInspectionDetailsDTO();
                postDailyPatrolInspectionDetailsDTO.setId(dailyPatrolInspectionDetailsEntity.getId() == null ? "" : dailyPatrolInspectionDetailsEntity.getId());
                postDailyPatrolInspectionDetailsDTO.setInspectionId(dailyPatrolInspectionDetailsEntity.getInspectionId() == null ? "" : dailyPatrolInspectionDetailsEntity.getInspectionId());
                postDailyPatrolInspectionDetailsDTO.setDescription(dailyPatrolInspectionDetailsEntity.getDescription() == null ? "" : dailyPatrolInspectionDetailsEntity.getDescription());
                postDailyPatrolInspectionDetailsDTO.setCreateName(dailyPatrolInspectionDetailsEntity.getCreateName() == null ? "" : dailyPatrolInspectionDetailsEntity.getCreateName());
                postDailyPatrolInspectionDetailsDTO.setCreateBy(dailyPatrolInspectionDetailsEntity.getCreateBy() == null ? "" : dailyPatrolInspectionDetailsEntity.getCreateBy());
                postDailyPatrolInspectionDetailsDTO.setCreateOn(dailyPatrolInspectionDetailsEntity.getCreateOn() == null ? null : DateUtils.format(dailyPatrolInspectionDetailsEntity.getCreateOn()));
                postDailyPatrolInspectionDetailsDTO.setType(dailyPatrolInspectionDetailsEntity.getType() == null ? "" : dailyPatrolInspectionDetailsEntity.getType());
                postDailyPatrolInspectionDetailsDTO.setFrequency(dailyPatrolInspectionDetailsEntity.getFrequency());
                postDailyPatrolInspectionDetailsDTO.setDetailsState(dailyPatrolInspectionDetailsEntity.getDetailsState() == null ? "" : dailyPatrolInspectionDetailsEntity.getDetailsState());
                List<ProjectImagesEntity> postImageList = dailyPatrolInspectionRepository.getProjectImages(dailyPatrolInspectionDetailsEntity.getId());
                List<InspectionImageDTO> posmageList = new ArrayList<InspectionImageDTO>();//整改记录图片
                if (postImageList != null) {
                    for (ProjectImagesEntity projectImagesEntity : postImageList) {
                        InspectionImageDTO getImage = new InspectionImageDTO();
                        getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                        getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());
                        getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());
                        getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());
                        getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());
                        getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? null : DateUtils.format(projectImagesEntity.getCreateOn()));
                        getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? null : DateUtils.format(projectImagesEntity.getModifyOn()));
                        posmageList.add(getImage);
                    }
                }
                postDailyPatrolInspectionDetailsDTO.setImageList(posmageList);
                inspectionList.add(postDailyPatrolInspectionDetailsDTO);
            }
            getDailyPatrolInspection.setInspectionList(inspectionList);
        }
        return getDailyPatrolInspection;
    }

    /**
     * 区域统计导出EXCEL
     *
     * @param patrolInspectionCountDTO
     * @return
     */
    public List<ProjectDailyPatrolCountExcelDTO> areaExportExcel(ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO) {
        Map map = new HashMap();
        map.put("operationId", patrolInspectionCountDTO.getOperationId());

        List<Object[]> list = dailyPatrolInspectionRepository.searchInspectionOperaCount(map);

        List<ProjectDailyPatrolCountExcelDTO> patrolInspectionCountDTOs = new ArrayList<ProjectDailyPatrolCountExcelDTO>();
        int num = 1;
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                ProjectDailyPatrolCountExcelDTO dailyPatrolInspectionCountDTO = new ProjectDailyPatrolCountExcelDTO();
                dailyPatrolInspectionCountDTO.setNumber(num++);
                dailyPatrolInspectionCountDTO.setProjectName("中国金茂");//集团名称
                dailyPatrolInspectionCountDTO.setBuildingName(obj[1] == null ? "" : obj[1].toString());//项目名称
                dailyPatrolInspectionCountDTO.setQualifiedToatl(obj[2] == null ? 0 : ((BigInteger) obj[2]).intValue());//完成总数
                dailyPatrolInspectionCountDTO.setUnqualifiedToatl(obj[3] == null ? 0 : ((BigInteger) obj[3]).intValue());//不合格总数
                dailyPatrolInspectionCountDTO.setClosses(obj[4] == null ? 0 : ((BigInteger) obj[4]).intValue());//非正常关闭
                dailyPatrolInspectionCountDTO.setTotal(dailyPatrolInspectionCountDTO.getQualifiedToatl() + dailyPatrolInspectionCountDTO.getUnqualifiedToatl());//总数

                patrolInspectionCountDTOs.add(dailyPatrolInspectionCountDTO);
            }
        }
        return patrolInspectionCountDTOs;
    }

    /**
     * 项目统计导出excel
     *
     * @param patrolInspectionCountDTO
     * @return
     */
    public List<ProjectDailyPatrolCountExcelDTO> projectExportExcel(ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO) {
        Map map = new HashMap();
        map.put("projectId", patrolInspectionCountDTO.getProjectId());
        map.put("operationId", patrolInspectionCountDTO.getOperationId());

        List<Object[]> list = dailyPatrolInspectionRepository.searchInspectionProjecrCount(map);
        List<ProjectDailyPatrolCountExcelDTO> patrolInspectionCountDTOs = new ArrayList<ProjectDailyPatrolCountExcelDTO>();
        int num = 1;
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                ProjectDailyPatrolCountExcelDTO dailyPatrolInspectionCountDTO = new ProjectDailyPatrolCountExcelDTO();
                dailyPatrolInspectionCountDTO.setNumber(num++);
                dailyPatrolInspectionCountDTO.setProjectName(obj[1] == null ? "" : obj[1].toString());//区域名称
                dailyPatrolInspectionCountDTO.setBuildingName(obj[3] == null ? "" : obj[3].toString());//项目名称
                dailyPatrolInspectionCountDTO.setQualifiedToatl(obj[4] == null ? 0 : ((BigInteger) obj[4]).intValue());//完成总数
                dailyPatrolInspectionCountDTO.setUnqualifiedToatl(obj[5] == null ? 0 : ((BigInteger) obj[5]).intValue());//不合格总数
                dailyPatrolInspectionCountDTO.setClosses(obj[6] == null ? 0 : ((BigInteger) obj[6]).intValue());//非正常关闭
                dailyPatrolInspectionCountDTO.setTotal(dailyPatrolInspectionCountDTO.getQualifiedToatl() + dailyPatrolInspectionCountDTO.getUnqualifiedToatl());//总数

                patrolInspectionCountDTOs.add(dailyPatrolInspectionCountDTO);
            }
        }
        return patrolInspectionCountDTOs;
    }

    /**
     * 楼栋统计导出EXCEL
     *
     * @param patrolInspectionCountDTO
     * @param creaBy
     * @return
     */
    public List<ProjectDailyPatrolCountExcelDTO> buildExportExcel(ProjectDailyPatrolInspectionCountDTO patrolInspectionCountDTO, String creaBy) {
        Map map = new HashMap();
        map.put("projectId", patrolInspectionCountDTO.getProjectId());
        map.put("tenderId", patrolInspectionCountDTO.getTenderId());
        map.put("buildingId", patrolInspectionCountDTO.getBuildingId());
        map.put("projectList", "NO");//项目权限

        List<Object[]> list = null;
        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(creaBy);
        if (f) {
            list = dailyPatrolInspectionRepository.searchInspectionCount(map);
        } else {
            List<ProjectProjectEntity> projectList1 = staffEmployRepository.getProjectListForAgency(creaBy);
            String proejctAll = "";
            if (projectList1 != null) {
                for (ProjectProjectEntity project : projectList1) {
                    proejctAll += "'" + project.getId() + "',";
                }
            }
            List<ProjectProjectEntity> projectList2 = staffEmployRepository.getProjectListByStaffId(creaBy);
            if (projectList2 != null) {
                for (ProjectProjectEntity project : projectList2) {
                    proejctAll += "'" + project.getId() + "',";
                }
            }
            String project = proejctAll.substring(0, proejctAll.length() - 1);
            map.put("projectList", project);//项目权限
            list = dailyPatrolInspectionRepository.searchInspectionCount(map);
        }
        List<ProjectDailyPatrolCountExcelDTO> patrolInspectionCountDTOs = new ArrayList<ProjectDailyPatrolCountExcelDTO>();
        int num = 1;
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                ProjectDailyPatrolCountExcelDTO dailyPatrolInspectionCountDTO = new ProjectDailyPatrolCountExcelDTO();
                dailyPatrolInspectionCountDTO.setNumber(num++);
                dailyPatrolInspectionCountDTO.setBuildingName((String) obj[0]);//楼栋名称
                dailyPatrolInspectionCountDTO.setProjectName((String) obj[1]);//项目名称
                dailyPatrolInspectionCountDTO.setQualifiedToatl(((BigInteger) obj[2]).intValue());//完成总数
                dailyPatrolInspectionCountDTO.setUnqualifiedToatl(((BigInteger) obj[3]).intValue());//不合格总数
                dailyPatrolInspectionCountDTO.setClosses(((BigInteger) obj[4]).intValue());//非正常关闭
                dailyPatrolInspectionCountDTO.setTotal(dailyPatrolInspectionCountDTO.getQualifiedToatl() + dailyPatrolInspectionCountDTO.getUnqualifiedToatl());//总数

                patrolInspectionCountDTOs.add(dailyPatrolInspectionCountDTO);
            }
        }
        return patrolInspectionCountDTOs;
    }
}
