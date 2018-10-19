package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.application.admin.dto.*;
import com.maxrocky.vesta.application.inf.*;

import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.*;
import com.maxrocky.vesta.utility.ImgUpdate.ImageUpload;
import net.sf.json.JSONArray;
import com.mysql.jdbc.StringUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by liuwei on 2016/1/13.
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    SystemConfigRepository systemConfigRepository;

    @Autowired
    CommunityRespository communityRespository;

    @Autowired
    UserSettingRepository userSettingRepository;

    @Autowired
    HouseProjectService houseProjectService;

    @Autowired
    HouseInfoRepository houseInfoRepository;

    @Autowired
    ImgService imgService;

    @Autowired
    ClickUserRepository clickUserRepository;

    @Autowired
    AnnouncementScopeRepository announcementScopeRepository;

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    HouseCityRepository houseCityRepository;

    @Autowired
    UserActivityScopeRepository userActivityScopeRepository;

    @Autowired
    SystemLogRepository systemLogRepository;

    @Autowired
    UserSettingService userSettingService;

    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;

    @Autowired
    SMSAlertsService smsAlertsService;

    @Autowired
    SMSAuthService smsAuthService;

    @Autowired
    HouseBuildingRepository houseBuildingRepository;

    @Autowired
    HouseUnitRepository houseUnitRepository;

    /****************************************************************************
     *                      首页接口 start
     ****************************************************************************/
    @Override
    public CommunityHomeLastestResDto getLastestCommunity(String projectId,Integer count) {

        if(count != null){
            count = count - 1;
        }

        List<CommunityActivityInfoEntity> communityActivityInfoEntities = this.communityRespository.getLastestActivityInfoByProjectId(projectId);

        if(communityActivityInfoEntities == null || !(communityActivityInfoEntities.size() >= count+1)){
            return null;
        }

        CommunityActivityInfoEntity communityActivityInfoEntity = communityActivityInfoEntities.get(count);


        List<CommunityImageInfoEntity> communityImageInfoEntities = this.communityRespository.getCommunityImageListByActivityId(communityActivityInfoEntity.getActivityId());
        CommunityHomeLastestResDto res = new CommunityHomeLastestResDto();
        res.setId(communityActivityInfoEntity.getActivityId());
        res.setTitle(communityActivityInfoEntity.getTitle());

        if (communityImageInfoEntities != null && communityImageInfoEntities.size() > 0) {
            res.setImageUrl(communityImageInfoEntities.get(0).getUrl());
        }
        if (StringUtil.isEmpty(res.getImageUrl())) {
            res.setImageUrl(AppConfig.default_image.getImagePath(null, AppConfig.default_image.type.community));
        }
        return res;
    }

    /****************************************************************************
     *                      首页接口 end
     ****************************************************************************/

    /**
     * 通过活动Id检索发布项目范围
     */
    @Override
    public List<Map<String,Object>> getProjectScopeByActivityId(String activityId){
        List<Map<String,Object>> projectList = new ArrayList<>();
        List<CommunityActivityInfoScopeEntity> communityActivityInfoScopeEntities = communityRespository.getScopeByActivity(activityId);
        if (communityActivityInfoScopeEntities.size() > 0){
            for (CommunityActivityInfoScopeEntity communityActivityInfoScopeEntity : communityActivityInfoScopeEntities){
                Map<String,Object> project = new HashMap<>();
                project.put("projectId",communityActivityInfoScopeEntity.getProjectId());
                project.put("projectName",communityActivityInfoScopeEntity.getProjectName());
                projectList.add(project);
            }
        }
        return projectList;
    }

    /**
     * Method getCommuntiyActivityList 获取社区活动列表
     * User: liuwei
     * Created: 2016-01-16 03:22:37
     * Params : [size, nubmer]
     * return: com.maxrocky.vesta.common.restHTTPResult.ApiResult
     */
    @Override
    public ApiResult GetCommuntiyActivityList(Page page, UserInfoEntity userInfoEntity) {

        if (userInfoEntity == null) {
            return new ErrorApiResult("tip_00000054");
        }
        UserSettingEntity userSettingEntity = this.userSettingRepository.get(userInfoEntity.getUserId());
        if (userSettingEntity == null || StringUtil.isEmpty(userSettingEntity.getProjectId())) {
            return ErrorResource.getError("tip_ch00000010");
        }
        List<Object> communtiyActivityList = communityRespository.getCommuntiyActivityList(page, CommuntiyInfoDto.class, userSettingEntity.getProjectId());
        SuccessApiResult successApiResult = new SuccessApiResult(communtiyActivityList);
        ApiResult apiResult = new SuccessApiResult(communtiyActivityList);
        apiResult.addAttribute("code", 0).addAttribute("msg", "OK");
        return apiResult;
    }

    /**
     * Method getHomeActivityList  获取首页活动信息
     * User: liuwei
     * Created: 2016-01-16 03:23:31
     * Params : []
     * return: com.maxrocky.vesta.common.restHTTPResult.ApiResult
     */
    @Override
    public ApiResult GetHomeActivityList() {

        List<HomeActivityDto> homeActivityDtoList = new ArrayList<HomeActivityDto>();
        List<CommunityActivityInfoEntity> communityActivityInfoEntityList = this.communityRespository.getHomeActivityEntityList();
        for (CommunityActivityInfoEntity entity : communityActivityInfoEntityList) {
            CommunityImageInfoEntity imageInfoEntity = this.communityRespository.getFirstImageEntity(entity.getActivityId());
            HomeActivityDto homeActivityDto = new HomeActivityDto();
            homeActivityDto.setDesc(entity.getActivityDesc());
            homeActivityDto.setImgUrl(/*CommunityImageInfoEntity.imgVisitPath + */imageInfoEntity.getUrl());
            homeActivityDto.setUrl(/*CommunityImageInfoEntity.imgPath +*/ entity.getActivityId());
            homeActivityDtoList.add(homeActivityDto);
        }

        ApiResult apiResult = new SuccessApiResult(homeActivityDtoList);
        apiResult.addAttribute("code", 0).addAttribute("msg", "OK");
        return apiResult;
    }


    /*
    * 获取活动报名信息最新
    */
    @Override
    public ApiResult activityApplyList(UserInfoEntity user,String projectCode) {
        WebPage page=new WebPage();
        try {
            List<CommunityHomeListDto> community=new ArrayList<CommunityHomeListDto>();
            if(user==null){
                List<CommunityActivityInfoEntity> communities=communityRespository.getOpenActivitiesByProjectId(page, projectCode);
                if(communities.size()>0) {
                    for (CommunityActivityInfoEntity activity : communities) {
                        if(activity.getTypes().equals("1")){
                            //获取活动详情信息
                            CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(activity.getActivityId(), null);
                            if(activityInfo!=null){
                                if(activityInfo.getPeople()==null){
                                    activityInfo.setPeople(0);
                                }
                                if(activityInfo.getHeadCount()==null){
                                    activityInfo.setHeadCount(0);
                                }
                                communityRespository.updateActivityInfo(activityInfo);
                            }
                            CommunityHomeListDto communityDto = new CommunityHomeListDto();
                            communityDto.setId(activity.getActivityId());//活动id
                            communityDto.setPublishDate(activity.getActivityDate());//活动时间
                            communityDto.setScope(activity.getAddress());//活动范围
                            communityDto.setTitle(activity.getTitle());
                            Integer people = (int) activity.getHeadCount() - (int)activity.getPeople();
                            communityDto.setPeople(people.toString());//剩余人数
                            List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activity.getActivityId());
                            if(imageList.size()>0){
                                communityDto.setSrc(imageList.get(0).getUrl());//活动图片
                            }
                            communityDto.setDate(DateUtils.format(new Date(),"yyyy-MM-dd"));
                            community.add(communityDto);
                        }else{
                        }
                    }
                }
            }else{
                if(user.getUserType().equals("1") || user.getUserType().equals("2")){
                    List<CommunityActivityInfoEntity> communities=communityRespository.getOpenActivitiesByProjectId(page, projectCode);
                    if(communities.size()>0) {
                        for (CommunityActivityInfoEntity activity : communities) {
                            if(activity.getTypes().equals("1")){
                                //获取活动详情信息
                                CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(activity.getActivityId(), null);
                                if(activityInfo!=null){
                                    if(activityInfo.getPeople()==null){
                                        activityInfo.setPeople(0);
                                    }
                                    if(activityInfo.getHeadCount()==null){
                                        activityInfo.setHeadCount(0);
                                    }
                                    communityRespository.updateActivityInfo(activityInfo);
                                }
                                CommunityHomeListDto communityDto = new CommunityHomeListDto();
                                communityDto.setId(activity.getActivityId());//活动id
                                communityDto.setPublishDate(activity.getActivityDate());//活动时间

                                communityDto.setCreateTime(activity.getMakedate());//创建时间
                                communityDto.setContent(activity.getActivityDesc());//内容描述
                                communityDto.setTitle(activity.getTitle());
                                // communityDto.setScope(activity.getScope());//活动范围
                                communityDto.setScope(activity.getAddress());//活动范围
                                Integer people = (int) activity.getHeadCount() - (int)activity.getPeople();
                                communityDto.setPeople(people.toString());//剩余人数
                                List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activity.getActivityId());
                                if(imageList.size()>0){
                                    communityDto.setSrc(imageList.get(0).getUrl());//活动图片
                                }
                                CommunityCancelEntity cancelInfo = communityRespository.getCancelInfo(user.getUserId());
                                if(cancelInfo!=null && !StringUtil.isEmpty(cancelInfo.getCancelNumber())){
                                    communityDto.setCancelNumber(cancelInfo.getCancelNumber());//取消次数
                                }
                                CommunityActivityApplyInfoEntity apply=communityRespository.getUserApplyInfo(activity.getActivityId(), user.getUserId());
                                if(apply!=null){
                                    communityDto.setState(apply.getApplyDesc());//报名状态

                                }else {
                                    communityDto.setState("0");//报名状态
                                }
                                communityDto.setDate(DateUtils.format(new Date(),"yyyy-MM-dd"));
                                community.add(communityDto);
                            }else{
                            }
                        }
                    }
                }else if(user.getUserType().equals("3") || user.getUserType().equals("4")
                        || user.getUserType().equals("5")){
                    //------------------------------------获取业主下的项目start-----------------------------------------------
                    //获取业主的所在项目
                   /* HouseInfoEntity houseInfo=houseInfoRepository.getDefaultHouse(user.getId());
                    if(houseInfo==null){
                        if(user.getUserType().equals("3")) {
                            houseInfo = houseInfoRepository.getHouseByMemberId(user.getId());
                        }else if(user.getUserType().equals("4")){
                            List<Object[]> houseList=houseInfoRepository.getHousemateDefaultHouse(user.getUserId());
                            Object[] obj=houseList.get(0).clone();
                            houseInfo.setProjectId(obj[3].toString());
                        }
                    }
                    if(houseInfo==null && StringUtil.isEmpty(houseInfo.getProjectId())){
                        return ErrorResource.getError("tip_pe00000009");//业主项目为空
                    }*/
                    //-------------------------------------获取业主下的项目end------------------------------------------------

                    //List<Object[]> communities=communityRespository.getActivities(houseInfo.getProjectId(), page);
                    List<CommunityActivityInfoEntity> communities=communityRespository.getOpenActivitiesByProjectId(page, projectCode);
                    //如果通过项目查询活动,
                    if(communities.size()>0){
                        for (CommunityActivityInfoEntity activity : communities) {
                            //获取活动详情信息
                            CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(activity.getActivityId(), null);

                            if(activityInfo!=null){
                                if(activityInfo.getPeople()==null){
                                    activityInfo.setPeople(0);
                                }
                                if(activityInfo.getHeadCount()==null){
                                    activityInfo.setHeadCount(0);
                                }
                                communityRespository.updateActivityInfo(activityInfo);
                            }
                            CommunityHomeListDto communityDto = new CommunityHomeListDto();
                            communityDto.setId(activity.getActivityId());//活动id
                            communityDto.setPublishDate(activity.getActivityDate());//活动时间
                            communityDto.setTitle(activity.getTitle());
                            communityDto.setCreateTime(activity.getMakedate());//创建时间
                            communityDto.setContent(activity.getActivityDesc());//活动描述

                            // communityDto.setScope(activity.getScope());//活动范围
                            communityDto.setScope(activity.getAddress());//活动范围
                            Integer people = (int) activity.getHeadCount() - (int)activity.getPeople();
                            communityDto.setPeople(people.toString());//剩余人数
                            List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activity.getActivityId());
                            if(imageList.size()>0){
                                communityDto.setSrc(imageList.get(0).getUrl());//活动图片
                            }
                            CommunityCancelEntity cancelInfo = communityRespository.getCancelInfo(user.getUserId());
                            if(cancelInfo!=null && !StringUtil.isEmpty(cancelInfo.getCancelNumber())){
                                communityDto.setCancelNumber(cancelInfo.getCancelNumber());//取消次数
                            }
                            CommunityActivityApplyInfoEntity apply=communityRespository.getUserApplyInfo(activity.getActivityId(), user.getUserId());
                            if(apply!=null){
                                communityDto.setState(apply.getApplyDesc());//报名状态
                            }else {
                                communityDto.setState("0");//报名状态
                            }
                            communityDto.setDate(DateUtils.format(new Date(),"yyyy-MM-dd"));
                            community.add(communityDto);
                        }
                    }
                }
                //调用点击人统计
                String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
                ClickUsersEntity clickUserEntity=clickUserRepository.getTotalInfo(dateNow,"8",user.getUserId());
                if(clickUserEntity==null){
                    ClickUsersEntity clickUser=new ClickUsersEntity();
                    clickUser.setId(IdGen.uuid());
                    clickUser.setCreateDate(new Date());
                    clickUser.setClicks(1);
                    clickUser.setUserId(user.getUserId());
                    clickUser.setForeignId("8");
                    clickUserRepository.create(clickUser);
                }else{
                    clickUserEntity.setClicks(clickUserEntity.getClicks()+1);
                    clickUserRepository.update(clickUserEntity);
                }
            }
            //通过用户id去查询，
            UserActivityScopeEntity userActivityScopeEntity=userActivityScopeRepository.get(user.getUserId(),"");
           if(userActivityScopeEntity!=null){
               String scopeActivityId=userActivityScopeEntity.getActivityId();//中间表的活动id
               //if中间表的活动id和最新的活动id相等的话,没有新的活动,-----------------------已读不返回数据
               //else 取出最新的活动返回,------------------------------------ update 活动id为最新--返回最新的活动
               if(community.size()>0){
                   if(scopeActivityId.equals(community.get(0).getId())){
                       community.clear();
                   }else{
                       userActivityScopeEntity.setActivityId(community.get(0).getId());
                       userActivityScopeRepository.update(userActivityScopeEntity);
                   }
               }
           }
            if(community.size()>0){
                return new SuccessApiResult(community.get(0));
            }else{
                return new SuccessApiResult();
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    @Override
    public void add() {
        this.communityRespository.add();
    }

    /**
     * Method updateApplyStatus 更新报名状态
     * User: liuwei
     * Created: 2016-01-16 06:46:33
     * Params : [activityId, vestaToken]
     * return: com.maxrocky.vesta.common.restHTTPResult.ApiResult
     */
    @Override
    public ApiResult updateApplyStatus(String activityId, UserInfoEntity userInfoEntity) {

        if (StringUtil.isEmpty(activityId)) {
            return new ErrorApiResult("tip_00000054");
        }
        if (userInfoEntity == null) {
            return new ErrorApiResult("tip_00000484");
        }
        String userId = userInfoEntity.getUserId();
        CommunityActivityApplyInfoEntity commnityInfoEnity = this.communityRespository.getUserApplyInfo(activityId, userId);
        if (commnityInfoEnity == null) {
            //add
            CommunityActivityApplyInfoEntity applyInfoEntity = new CommunityActivityApplyInfoEntity();
            applyInfoEntity.setActivityId(activityId);
            applyInfoEntity.setApplyDesc("1");
            applyInfoEntity.setUserId(userId);
            applyInfoEntity.setApplyId(IdGen.getNanaTimeID());
            applyInfoEntity.setMakedate(new java.sql.Date(new Date().getTime()));
            this.communityRespository.saveApplyInfoEntity(applyInfoEntity);
        } else {
            //需求中暂时没有删除操作
        }
        ApiResult apiResult = new SuccessApiResult(commnityInfoEnity);
        apiResult.addAttribute("code", 0).addAttribute("msg", "OK");
        return apiResult;
    }

    @Override
    public ApiResult saveCommnuityInfo(CommnuitySaveReqDto commnuitySaveReqDto) {

        String commnuityActivityId = IdGen.getNanaTimeID();
        CommunityActivityInfoEntity communityActivityInfoEntity = new CommunityActivityInfoEntity();
        communityActivityInfoEntity.setActivityDate(commnuitySaveReqDto.getActivityDate());
        communityActivityInfoEntity.setModifydate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityActivityInfoEntity.setMakedate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));

        communityActivityInfoEntity.setActivityDate(commnuitySaveReqDto.getActivityDate());
        communityActivityInfoEntity.setActivityId(commnuityActivityId);
        communityActivityInfoEntity.setActivityDesc(commnuitySaveReqDto.getActivityDesc());
        communityActivityInfoEntity.setHotline(commnuitySaveReqDto.getHotline());
        communityActivityInfoEntity.setTitle(commnuitySaveReqDto.getTitle());
        communityActivityInfoEntity.setStatus(String.valueOf(CommunityActivityInfoEntity.CommunityActivityStatus.activity_doing));
        this.communityRespository.saveCommunityInfo(communityActivityInfoEntity);

        for (CommnuityImageSaveReqDto imageSaveReqDto : commnuitySaveReqDto.getImageSaveReqDtos()) {
            CommunityImageInfoEntity imageInfoEntity = new CommunityImageInfoEntity();
            imageInfoEntity.setActivityId(commnuityActivityId);
            imageInfoEntity.setImageId(IdGen.getNanaTimeID());
            imageInfoEntity.setMakedate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
            imageInfoEntity.setUrl(imageSaveReqDto.getUrl());
            this.communityRespository.saveCommnuityImageInfo(imageInfoEntity);
        }
        return (ApiResult) new SuccessApiResult().addAttribute("code", 0).addAttribute("msg", "OK");
    }

    //-----后台接口-----------------------------------------------


    /**
     * 获取活动列表
     * @param activityAdminDto
     * @param webPage
     * @return
     */
    @Override
    public List<ActivityAdminDto> listActivity(ActivityAdminDto activityAdminDto, WebPage webPage) {
        CommunityActivityInfoEntity communityActivityInfoEntity = new CommunityActivityInfoEntity();

        if (activityAdminDto.getTypes()!=null && !"0".equals(communityActivityInfoEntity.getTypes())){
            communityActivityInfoEntity.setTypes(activityAdminDto.getTypes());
        }
        if (activityAdminDto.getCu_projectId() != null &&!"0".equals(activityAdminDto.getCu_projectId())) {
            communityActivityInfoEntity.setProjectId(activityAdminDto.getCu_projectId());
        }
        if (activityAdminDto.getStatus()!=null&&!"0".equals(activityAdminDto.getStatus())){
            communityActivityInfoEntity.setStatus(activityAdminDto.getStatus());
        }
        if (activityAdminDto.getTitle() != null && !"".equals(activityAdminDto.getTitle())) {
            communityActivityInfoEntity.setTitle(activityAdminDto.getTitle());
        }
        if (activityAdminDto.getScope() != null && !"".equals(activityAdminDto.getScope())) {
            //通过名字去搜索项目对象，获取项目的
            communityActivityInfoEntity.setScope(activityAdminDto.getScope());
        }
        if (activityAdminDto.getOperator() != null && !"".equals(activityAdminDto.getOperator())) {
            communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
        }
        //活动时间假定为开始时间
        if (activityAdminDto.getBeginTime() != null && !"".equals(activityAdminDto.getBeginTime())) {
            communityActivityInfoEntity.setActivityDate(activityAdminDto.getBeginTime());
        }
        //活动内容假定为结束时间
        if (activityAdminDto.getEndTime() != null && !"".equals(activityAdminDto.getEndTime())) {
            communityActivityInfoEntity.setActivityDesc(activityAdminDto.getEndTime());
        }
        //活动类型
        communityActivityInfoEntity.setActivityType(activityAdminDto.getActivityType());
        //是否作为宣传位
        communityActivityInfoEntity.setIsBanner(activityAdminDto.getIsBanner());

        communityActivityInfoEntity.setProjectId(activityAdminDto.getCu_projectId());
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = activityAdminDto.getRoleScopeList();
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
        List<ActivityAdminDto> activityAdminDtos = new ArrayList<>();
        //获取活动列表
        List<CommunityActivityInfoEntity> communityActivityInfoEntities = communityRespository.listActivityInfo(communityActivityInfoEntity, webPage, roleScopes);
        if (communityActivityInfoEntities.size() > 0) {
            for (CommunityActivityInfoEntity community : communityActivityInfoEntities) {
                ActivityAdminDto activityDto = new ActivityAdminDto();
                //获取活动的报名人数
                int count = communityRespository.countApply(community.getActivityId());
                HouseProjectDto houseProject = houseProjectService.getProjectById(community.getProjectId());

                /*List<Object[]> obj= communityRespository.getActivitiesByActId(community.getActivityId());
                String projects="";

                projects=projects+",";

                community.setProjectId(projects);*/

                if (houseProject==null||houseProject.getId()==null){
                    activityDto.setCu_projectId("0");
                    activityDto.setCu_projectName("全部");
                }else {
                    activityDto.setCu_projectId(houseProject.getId());
                    activityDto.setCu_projectName(houseProject.getName());
                }
                activityDto.setActivityId(community.getActivityId());
                activityDto.setTitle(community.getTitle());
                activityDto.setActivityDesc(community.getActivityDesc());
                activityDto.setActivityDate(community.getActivityDate());
                activityDto.setScope(community.getScope());
                activityDto.setAddress(community.getAddress());//新增 地址
                activityDto.setPushState(community.getPushState());
                activityDto.setState(community.getState());
                activityDto.setHotline(community.getHotline());
                activityDto.setTypes(community.getTypes());
               // String counts= String.valueOf(community.getApplyCount() == null ? "0" : community.getApplyCount());
                activityDto.setCountApply(community.getPeople() + "/" +community.getHeadCount());
                activityDto.setMakedate(community.getMakedate());
                activityDto.setOperator(community.getOperator());
                activityDto.setIsBanner(community.getIsBanner());
                activityDto.setActivityType(community.getActivityType());

                //当进行查看列表时，需要刷新活动报名状态
                String activityEndDate = community.getActivityEndDate();
                String applyStartTime = community.getApplyStartTime();
                String applyEndTime = community.getApplyEndTime();
                Date now = new Date();
                String nowDate = DateUtils.format(now, "yyyy-MM-dd");
                if ("0".equals(community.getPushState())) {
                    community.setStatus("5");
                }else if ("1".equals(community.getPushState())) {
                    if (nowDate.compareTo(applyStartTime)>=0 && nowDate.compareTo(applyEndTime)<=0) {
                        //报名状态为报名中
                        community.setStatus("1");
                    }else if (nowDate.compareTo(activityEndDate)<=0 && nowDate.compareTo(applyEndTime)>0) {
                        //报名状态为报名结束
                        community.setStatus("3");
                    }else if (nowDate.compareTo(activityEndDate)>0) {
                        //报名状态为活动结束
                        community.setStatus("4");
                    }else if (community.getPeople() == community.getHeadCount()) {
                        //报名状态为已报满
                        community.setStatus("2");
                    }
                }
                communityRespository.changeStatus(community);
                activityDto.setStatus(community.getStatus());
                activityAdminDtos.add(activityDto);
            }
        }
        return activityAdminDtos;
    }

    /**
     * 修改活动
     * @param activityAdminDto
     * @return
     */
    @Override
    public boolean updateActicity(ActivityAdminDto activityAdminDto) {
        CommunityActivityInfoEntity communityActivityInfoEntity = communityRespository.getCommuntiyActivityInfo(activityAdminDto.getActivityId(), null);
        if (communityActivityInfoEntity == null) {
            return false;
        }
        communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
        communityActivityInfoEntity.setAddress(activityAdminDto.getAddress());
        communityActivityInfoEntity.setTitle(activityAdminDto.getTitle());
        communityActivityInfoEntity.setActivityDesc(activityAdminDto.getActivityDesc());
        communityActivityInfoEntity.setHotline(activityAdminDto.getHotline());
        communityActivityInfoEntity.setActivityDate(activityAdminDto.getActivityDate());
        communityActivityInfoEntity.setModifydate(DateUtils.format(DateUtils.getDate()));
        boolean result = communityRespository.updateActivity(communityActivityInfoEntity);
        CommunityImageInfoEntity communityImageInfoEntity = new CommunityImageInfoEntity();
        List<CommunityImageInfoEntity> communityImageInfoEntities = communityRespository.getCommunityImageById(activityAdminDto.getActivityId());
        if (communityImageInfoEntities.size() > 0) {
            for (CommunityImageInfoEntity image : communityImageInfoEntities) {
                if (activityAdminDto.getHomePageimgpath() != null) {
                    if (image.getType() == CommunityImageInfoEntity.type.homePage_type) {
                        String fileName = ImageUpload.saveImageToService(activityAdminDto.getHomePageimgpath(), ImgType.ACTIVITY);
                        SystemConfigEntity defalut_circle_image = systemConfigRepository.get("defalut_image_community");
                        SystemConfigEntity circle_image = systemConfigRepository.get("activity");
                        if (!activityAdminDto.getHomePageimgpath().isEmpty()) {
                            image.setUrl(AppConfig.SERVICEPATH + fileName);
                            image.setModifydate(DateUtils.format(DateUtils.getDate()));
                        }
//                        image.setUrl(AppConfig.SERVICEPATH + fileName);
                        communityRespository.updateCommunityImage(image);//////////////////
                    }
                }
                if (activityAdminDto.getActivityimgpath() != null) {
                    if (image.getType() == CommunityImageInfoEntity.type.activity_type) {
                        String fileName = ImageUpload.saveImageToService(activityAdminDto.getActivityimgpath(), ImgType.ACTIVITY);
                        SystemConfigEntity defalut_circle_image = systemConfigRepository.get("defalut_image_community");
                        SystemConfigEntity circle_image = systemConfigRepository.get("activity");
                        if (!activityAdminDto.getActivityimgpath().isEmpty()) {
                            image.setUrl(AppConfig.SERVICEPATH + fileName);
                            image.setModifydate(DateUtils.format(DateUtils.getDate()));
                        }
//                        image.setUrl(AppConfig.SERVICEPATH + fileName);
                        communityRespository.updateCommunityImage(image);//////////////////
                    }
                }
            }
        }
        return result;
    }

    /**
     * 通过活动Id查找活动
     */
    @Override
    public ActivityAdminDto getActivityById(String activityId) {
        CommunityActivityInfoEntity communityActivityInfoEntity = communityRespository.getCommuntiyActivityInfo(activityId, null);
        ActivityAdminDto activityDto = new ActivityAdminDto();
        if (communityActivityInfoEntity==null||communityActivityInfoEntity.getActivityId()==null){
            return activityDto;
        }
        HouseProjectDto houseProject = houseProjectService.getProjectById(communityActivityInfoEntity.getScope());//
        int count = communityRespository.countApply(activityId);
        if (houseProject==null||houseProject.getId()==null){
            activityDto.setCu_projectId("0");
            activityDto.setCu_projectName("全部");
        }else {
            activityDto.setCu_projectId(houseProject.getId());
            activityDto.setCu_projectName(houseProject.getName());
        }
        activityDto.setActivityId(communityActivityInfoEntity.getActivityId());
        activityDto.setTitle(communityActivityInfoEntity.getTitle());
        activityDto.setActivityDesc(communityActivityInfoEntity.getActivityDesc());
        activityDto.setActivityDate(communityActivityInfoEntity.getActivityDate());
        activityDto.setActivityEndDate(communityActivityInfoEntity.getActivityEndDate());
        activityDto.setApplyStartTime(communityActivityInfoEntity.getApplyStartTime());
        activityDto.setApplyEndTime(communityActivityInfoEntity.getApplyEndTime());
        activityDto.setScope(communityActivityInfoEntity.getScope());
        activityDto.setCu_projectName(houseProject.getName());
        activityDto.setAddress(communityActivityInfoEntity.getAddress());
        activityDto.setHotline(communityActivityInfoEntity.getHotline());
        activityDto.setStatus(communityActivityInfoEntity.getStatus());
        activityDto.setState(communityActivityInfoEntity.getState());
        activityDto.setPushState(communityActivityInfoEntity.getPushState());
        activityDto.setMakedate(communityActivityInfoEntity.getMakedate());
        activityDto.setOperator(communityActivityInfoEntity.getOperator());
        activityDto.setCountApply(count + "人");
        activityDto.setHeadCount(communityActivityInfoEntity.getHeadCount());
        activityDto.setApplyMaxNum(communityActivityInfoEntity.getApplyMaxNum());
        activityDto.setTypes(communityActivityInfoEntity.getTypes());
        activityDto.setActivityType(communityActivityInfoEntity.getActivityType());
        activityDto.setThemeType(communityActivityInfoEntity.getThemeType());
        activityDto.setIsBanner(communityActivityInfoEntity.getIsBanner());
        activityDto.setIsLink(communityActivityInfoEntity.getIsLink());
        activityDto.setLinkSrc(communityActivityInfoEntity.getLinkSrc());
        activityDto.setIsBlacklist(communityActivityInfoEntity.getIsBlacklist());
        activityDto.setBlacklistId(communityActivityInfoEntity.getBlacklistId());
        activityDto.setIsHouseScope(communityActivityInfoEntity.getIsHouseScope());
        activityDto.setHouseScope(communityActivityInfoEntity.getHouseScope());
        activityDto.setIsTimeRange(communityActivityInfoEntity.getIsTimeRange());
        activityDto.setApplyInfoMaxNum(communityActivityInfoEntity.getApplyInfoMaxNum());
        //获取报名信息
        if (!StringUtils.isNullOrEmpty(communityActivityInfoEntity.getApplyInfo())) {
            JSONArray j = JSONArray.fromObject(communityActivityInfoEntity.getApplyInfo());
            List<JsonDto> list = JSONArray.toList(j, new JsonDto(), new JsonConfig());
            int i = 1;
            for (JsonDto jsonDto : list) {
                jsonDto.setNumber(i);
                i++;
            }
            activityDto.setApplyInfo2(list);
        }
        return activityDto;
    }

    /**
     * 删除活动-暂时将活动变为已结束
     */
    @Override
    public boolean deleteActivity(ActivityAdminDto activityAdminDto) {
        CommunityActivityInfoEntity communityActivityInfoEntity = communityRespository.getCommuntiyActivityInfo(activityAdminDto.getActivityId(), null);
        if (communityActivityInfoEntity == null) {
            return false;
        }
        communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
        communityActivityInfoEntity.setState("0");
        communityActivityInfoEntity.setModifydate(DateUtils.format(DateUtils.getDate()));
        boolean result = communityRespository.updateActivity(communityActivityInfoEntity);
        return result;
    }

    @Override
    public boolean deleteActivity(UserPropertyStaffEntity user, ActivityAdminDto activityAdminDto) {
        CommunityActivityInfoEntity communityActivityInfoEntity = communityRespository.getCommuntiyActivityInfo(activityAdminDto.getActivityId(), null);
        if (communityActivityInfoEntity == null) {
            return false;
        }
        communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
        communityActivityInfoEntity.setState("0");
        communityActivityInfoEntity.setModifydate(DateUtils.format(DateUtils.getDate()));
        boolean result = communityRespository.updateActivity(communityActivityInfoEntity);
        //增加日志
        String projectCodes = "";
        String projectNames = "";
        String cityName="";
        List<Map<String, Object>> mapList = communityRespository.queryProjectByActivityId(activityAdminDto.getActivityId());
        if (mapList.size() > 0){
            for (int i = 0; i < mapList.size(); i++){
                Map<String, Object> map = mapList.get(i);
                projectCodes = projectCodes + (String) map.get("projectCode") + ",";
                projectNames = projectNames + (String) map.get("projectName") + ",";
                cityName=(String) map.get("cityName");
            }
        }
        InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
        infoReleaseLog.setLogId(IdGen.uuid());
        infoReleaseLog.setLogTime(new Date());//注册日期
        infoReleaseLog.setUserName(user.getStaffName());//用户昵称
        infoReleaseLog.setUserMobile(user.getMobile());//手机号
        infoReleaseLog.setThisSection("服务");//版块
        infoReleaseLog.setThisFunction("活动信息管理");//功能
        infoReleaseLog.setThisType("删除");//操作类型
        infoReleaseLog.setLogContent(communityActivityInfoEntity.getTitle());//发布内容
        infoReleaseLog.setAsscommunity(projectNames);//关联社区;
        systemLogRepository.addInfoReleaseLog(infoReleaseLog);

        return result;
    }

    /**
     * 获取活动报名人列表
     * @param activityApplyAdminDto
     * @param webPage
     * @return
     */
    @Override
    public List<ActivityApplyAdminDto> listApplyDto(ActivityApplyAdminDto activityApplyAdminDto, WebPage webPage) {
        CommunityActivityApplyInfoEntity communityActivityApplyInfoEntity = new CommunityActivityApplyInfoEntity();
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        CommunityActivityInfoEntity communityActivityInfoEntity = new CommunityActivityInfoEntity();
        if (activityApplyAdminDto.getCu_projectId()!=null&&!"0".equals(activityApplyAdminDto.getCu_projectId())){
            communityActivityInfoEntity.setProjectId(activityApplyAdminDto.getCu_projectId());
        }
        if (activityApplyAdminDto.getUserType()!=null&&!"".equals(activityApplyAdminDto.getUserType())){
            userInfoEntity.setUserType(activityApplyAdminDto.getUserType());
        }
        if (activityApplyAdminDto.getTitle()!=null&&!"".equals(activityApplyAdminDto.getTitle())){
            communityActivityInfoEntity.setTitle(activityApplyAdminDto.getTitle());
        }
        if (activityApplyAdminDto.getUserName() != null && !"".equals(activityApplyAdminDto.getUserName())) {
            userInfoEntity.setRealName(activityApplyAdminDto.getUserName());
        }
        if (activityApplyAdminDto.getUserMobile()!=null&&!"".equals(activityApplyAdminDto.getUserMobile())){
            userInfoEntity.setMobile(activityApplyAdminDto.getUserMobile());
        }
        if (activityApplyAdminDto.getBeginTime() != null && "".equals(activityApplyAdminDto.getBeginTime())) {
            communityActivityApplyInfoEntity.setApplyId(activityApplyAdminDto.getApplyId());
        }
        if (activityApplyAdminDto.getEndTime() != null && "".equals(activityApplyAdminDto.getEndTime())) {
            communityActivityApplyInfoEntity.setApplyDesc(activityApplyAdminDto.getEndTime());
        }


        List<ActivityApplyAdminDto> activityApplyAdminDtos = new ArrayList<>();

        List<Object> list = communityRespository.listActivityApply(communityActivityInfoEntity, communityActivityApplyInfoEntity, userInfoEntity, webPage);
        if (list.size() > 0) {
            for (Object o : list) {
                Object[] objects = (Object[]) o;
                CommunityActivityApplyInfoEntity apply = (CommunityActivityApplyInfoEntity) objects[0];
                UserInfoEntity user = (UserInfoEntity) objects[1];
                CommunityActivityInfoEntity community = (CommunityActivityInfoEntity)objects[2];
                ActivityApplyAdminDto applyDto = new ActivityApplyAdminDto();
                HouseProjectDto houseProject = houseProjectService.getProjectById(community.getProjectId());
                if (houseProject==null||houseProject.getId()==null){
                    applyDto.setCu_projectId("0");
                    applyDto.setCu_projectName("全部");
                }else {
                    applyDto.setCu_projectId(houseProject.getId());
                    applyDto.setCu_projectName(houseProject.getName());
                }
                applyDto.setApplyId(apply.getApplyId());
                applyDto.setUserId(user.getUserId());
                applyDto.setUserName(user.getNickName());//用户名---
                applyDto.setUserMobile(user.getMobile());
                applyDto.setActivityDate(community.getActivityDate());
                applyDto.setActivityId(apply.getActivityId());
                applyDto.setTitle(community.getTitle());
                activityApplyAdminDtos.add(applyDto);
            }
        }

        return activityApplyAdminDtos;
    }

    /**
     * 获取活动报名人列表
     * @param activityApplyAdminDto
     * @param webPage
     * @return
     */
    @Override
    public List<ActivityApplyAdminDto> listApplyDtos(ActivityApplyAdminDto activityApplyAdminDto, WebPage webPage) {

        CommunityActivityApplyInfoEntity communityActivityApplyInfoEntity = new CommunityActivityApplyInfoEntity();
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        CommunityActivityInfoEntity communityActivityInfoEntity = new CommunityActivityInfoEntity();
        if (activityApplyAdminDto.getActivityId()!=null&&!"".equals(activityApplyAdminDto.getActivityId())){
            communityActivityInfoEntity.setActivityId(activityApplyAdminDto.getActivityId());
        }
        if (activityApplyAdminDto.getCu_projectId()!=null&&!"0".equals(activityApplyAdminDto.getCu_projectId())&&!"".equals(activityApplyAdminDto.getCu_projectId())){
            communityActivityInfoEntity.setProjectId(activityApplyAdminDto.getCu_projectId());
        }
        if (activityApplyAdminDto.getUserType()!=null&&!"".equals(activityApplyAdminDto.getUserType())){
            userInfoEntity.setUserType(activityApplyAdminDto.getUserType());
        }
        if (activityApplyAdminDto.getTitle()!=null&&!"".equals(activityApplyAdminDto.getTitle())){
            communityActivityInfoEntity.setTitle(activityApplyAdminDto.getTitle());
        }
        if (activityApplyAdminDto.getUserName() != null && !"".equals(activityApplyAdminDto.getUserName())) {
            userInfoEntity.setRealName(activityApplyAdminDto.getUserName());
        }
        if (activityApplyAdminDto.getUserMobile()!=null&&!"".equals(activityApplyAdminDto.getUserMobile())){
            userInfoEntity.setMobile(activityApplyAdminDto.getUserMobile());
        }
        CommunityActivityInfoScopeEntity communityActivityInfoScopeEntity=new CommunityActivityInfoScopeEntity();
        if (activityApplyAdminDto.getCu_cityName() != null &&! "".equals(activityApplyAdminDto.getCu_cityName())&&!"0".equals(activityApplyAdminDto.getCu_cityName())) {
            HouseCityEntity houseCityEntity=houseCityRepository.getInfoByCityId(activityApplyAdminDto.getCu_cityName());
            communityActivityInfoScopeEntity.setCity(houseCityEntity.getCityName());
        }
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = activityApplyAdminDto.getRoleScopeList();
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
        List<ActivityApplyAdminDto> activityApplyAdminDtos = new ArrayList<>();

        List<Object> list = communityRespository.listActivityApplys
                (communityActivityInfoEntity, communityActivityApplyInfoEntity, communityActivityInfoScopeEntity, userInfoEntity, roleScopes);
        if (list.size() > 0) {
            for (Object o : list) {
                Object[] objects = (Object[]) o;
                CommunityActivityApplyInfoEntity apply = (CommunityActivityApplyInfoEntity) objects[0];

                /*String test = String.valueOf(apply.getApplyCount()==null?"null":apply.getApplyCount());
                if(apply.getApplyCount().equals("null")){
                }else{*/
                    UserInfoEntity user = (UserInfoEntity) objects[1];
                    CommunityActivityInfoScopeEntity CommunityActivityInfoScope =(CommunityActivityInfoScopeEntity)objects[2];
                    CommunityActivityInfoEntity community = (CommunityActivityInfoEntity)objects[3];
                    ActivityApplyAdminDto applyDto = new ActivityApplyAdminDto();
                    //获取中间表对象,通过项目的pingcode去查询项目
                    HouseProjectDto houseProject = houseProjectService.getProjectByProjectCode(CommunityActivityInfoScope.getProjectId());
                    if (houseProject==null||houseProject.getId()==null){
                        applyDto.setCu_projectId("0");
                        applyDto.setCu_projectName("全部");
                    }else {
                        applyDto.setCu_projectId(houseProject.getPinyinCode());
                        applyDto.setCu_projectName(houseProject.getName());
                    }
                    //houseInfoRepository.getByUserId(apply.getUserId());
                    if (apply.getApplyCount() != null) {
                        UserInfoEntity userInfo=houseInfoRepository.getUserById(apply.getUserId());
                        HouseInfoEntity houseInfoEntity=houseInfoRepository.getHouseByMemberId(userInfo.getId());
                        if(houseInfoEntity!=null){
                            String houseProjectName=houseInfoEntity.getProjectName();//项目
                            if(houseProjectName.equals(houseProject.getName())){
                                applyDto.setApplyCount(apply.getApplyCount());
                                applyDto.setApplyPhone(apply.getApplyPhone());
                                applyDto.setApplyId(apply.getApplyId());
                                applyDto.setUserId(user.getUserId());
                                applyDto.setUserName(user.getRealName());//用户名---
                                applyDto.setUserMobile(user.getMobile());
                                applyDto.setActivityDate(community.getActivityDate());
                                applyDto.setActivityId(apply.getActivityId());
                                applyDto.setTitle(community.getTitle());
                                applyDto.setCu_cityName(CommunityActivityInfoScope.getCity());
                                applyDto.setApplyDate(String.valueOf(apply.getMakedate()));
/*                                String applyInfo=community.getApplyInfo();
                                JSONArray jsonArr = JSONArray.fromObject(applyInfo);
                                if(jsonArr.size()>0){
                                    String name="";
                                    String value="";
                                    StringBuilder sb = new StringBuilder("");
                                    for(int i=0;i<jsonArr.size();i++){
                                        JSONObject job = jsonArr.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                                        if(job.get("value").equals("")){
                                            continue;
                                        }
                                        System.out.println(job.get("name")+"="+job.get("value"));
                                        sb.append(job.get("name")+"="+job.get("value")+" ");
                                    }

                                }*/
                                //String str="[{'name':'企鹅','value':'企鹅value'},{'name':'切切','value':'切切value'}]";
                                if (!StringUtils.isNullOrEmpty(apply.getApplyInfo()) && !"null".equals(apply.getApplyInfo())) {
                                    applyDto.setApplyInfo(JSONArray.fromObject(apply.getApplyInfo()));//报名信息 ,报名表中
                                }
                                if (!StringUtils.isNullOrEmpty(community.getApplyInfo())) {
                                    applyDto.setAttributeName(JSONArray.fromObject(community.getApplyInfo()));//属性
                                }


                                //新增报名联系地址
                                applyDto.setApplyAddress(apply.getApplyAddress());
                                activityApplyAdminDtos.add(applyDto);
                            }
                        }
                    }
                }
                String projectScope="";

            //JSONArray jsonArr = JSONArray.fromObject(jsonStr);
/*
            for(int i=0;i<activityApplyAdminDtos.size();i++){

                ActivityApplyAdminDto activityApplyAdmini=activityApplyAdminDtos.get(i);
                projectScope=activityApplyAdminDtos.get(i).getCu_projectName();
                for(int j=i+1;j<activityApplyAdminDtos.size();j++){

                    ActivityApplyAdminDto activityApplyAdminj=activityApplyAdminDtos.get(j);
                    if(activityApplyAdmini.getApplyId().equals(activityApplyAdminj.getApplyId())){
                        if(activityApplyAdmini.getCu_cityName().equals("全部城市")){
                            activityApplyAdmini.setCu_projectName("全国");
                        }else{
                            projectScope+=","+activityApplyAdminj.getCu_projectName();
                            activityApplyAdmini.setCu_projectName(projectScope);
                        }
                        activityApplyAdminDtos.remove(j);
                        j=j-1;
                    }
                }
            }
*/
               // }
        }
        List<ActivityApplyAdminDto> activityApplyAdminDtos1 = new ArrayList<>();
        if (activityApplyAdminDtos.size() > 0) {
            //验证，下面两行可以去掉
            int pageIndex = webPage.getPageIndex();
            int pageSize = webPage.getPageSize();
            //该页第一条记录
            webPage.setRecordCount(activityApplyAdminDtos.size());
            int firstResult = webPage.getStartRow();
            int endResult = webPage.getPageIndex() * webPage.getPageSize();
            if (endResult > webPage.getRecordCount()) {
                for (int i=firstResult; i<webPage.getRecordCount(); i++) {
                    activityApplyAdminDtos1.add(activityApplyAdminDtos.get(i));
                }
            }else {
                for (int i=firstResult; i<endResult; i++) {
                    activityApplyAdminDtos1.add(activityApplyAdminDtos.get(i));
                }
            }
        }

        //排序
        Collections.sort(activityApplyAdminDtos1, new Comparator<ActivityApplyAdminDto>() {
            @Override
            public int compare(ActivityApplyAdminDto o1, ActivityApplyAdminDto o2) {
                Long date1 = null;
                Long date2 = null;
                String applyDate1 = o1.getApplyDate();
                if (!StringUtils.isNullOrEmpty(applyDate1)) {
                    date1 = DateUtils.parse(applyDate1, "yyyy-MM-dd").getTime();
                }
                String applyDate2 = o2.getApplyDate();
                if (!StringUtils.isNullOrEmpty(applyDate2)) {
                    date2 = DateUtils.parse(applyDate2, "yyyy-MM-dd").getTime();
                }
                if (date1 > date2) {
                    return -1;
                } else if (date1 < date2) {
                    return 1;
                }
                return 0;
            }
        });

        return activityApplyAdminDtos1;
    }

    @Override
    public List<ActivityApplyAdminDto> activityProjectApply(ActivityApplyAdminDto activityApplyAdminDto, WebPage webPage) {
        CommunityActivityApplyInfoEntity communityActivityApplyInfoEntity = new CommunityActivityApplyInfoEntity();
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        CommunityActivityInfoEntity communityActivityInfoEntity = new CommunityActivityInfoEntity();
        //活动ID
        if (activityApplyAdminDto.getActivityId()!=null&&!"".equals(activityApplyAdminDto.getActivityId())){
            communityActivityInfoEntity.setActivityId(activityApplyAdminDto.getActivityId());
        }
        //项目ID
        if (activityApplyAdminDto.getCu_projectId()!=null&&!"0".equals(activityApplyAdminDto.getCu_projectId())&&!"".equals(activityApplyAdminDto.getCu_projectId())){
            communityActivityInfoEntity.setProjectId(activityApplyAdminDto.getCu_projectId());
        }
        //用户类型
        if (activityApplyAdminDto.getUserType()!=null&&!"".equals(activityApplyAdminDto.getUserType())){
            userInfoEntity.setUserType(activityApplyAdminDto.getUserType());
        }
        //活动标题
        if (activityApplyAdminDto.getTitle()!=null&&!"".equals(activityApplyAdminDto.getTitle())){
            communityActivityInfoEntity.setTitle(activityApplyAdminDto.getTitle());
        }
        //用户真实姓名
        if (activityApplyAdminDto.getUserName() != null && !"".equals(activityApplyAdminDto.getUserName())) {
            userInfoEntity.setRealName(activityApplyAdminDto.getUserName());
        }
        //手机号
        if (activityApplyAdminDto.getUserMobile()!=null&&!"".equals(activityApplyAdminDto.getUserMobile())){
            userInfoEntity.setMobile(activityApplyAdminDto.getUserMobile());
        }

        CommunityActivityInfoScopeEntity communityActivityInfoScopeEntity=new CommunityActivityInfoScopeEntity();
        if (activityApplyAdminDto.getCu_cityName() != null &&! "".equals(activityApplyAdminDto.getCu_cityName())&&!"0".equals(activityApplyAdminDto.getCu_cityName())) {
            HouseCityEntity houseCityEntity=houseCityRepository.getInfoByCityId(activityApplyAdminDto.getCu_cityName());
            communityActivityInfoScopeEntity.setCity(houseCityEntity.getCityName());
        }

        List<ActivityApplyAdminDto> activityApplyAdminDtos = new ArrayList<>();

        List<Object> list = communityRespository.activityProjectApply
                (communityActivityInfoEntity, communityActivityApplyInfoEntity, communityActivityInfoScopeEntity, userInfoEntity);
       // String str="";
       // String str1="";
        if (list.size() > 0) {
            for (Object o : list) {
                Object[] objects = (Object[]) o;
                CommunityActivityApplyInfoEntity apply = (CommunityActivityApplyInfoEntity) objects[0];
                UserInfoEntity user = (UserInfoEntity) objects[1];
                CommunityActivityInfoScopeEntity CommunityActivityInfoScope =(CommunityActivityInfoScopeEntity)objects[2];
                CommunityActivityInfoEntity community = (CommunityActivityInfoEntity)objects[3];
                ActivityApplyAdminDto applyDto = new ActivityApplyAdminDto();
                //获取中间表对象,通过项目的pingcode去查询项目
                HouseProjectDto houseProject = houseProjectService.getProjectByProjectCode(CommunityActivityInfoScope.getProjectId());
                if (houseProject==null||houseProject.getId()==null){
                    applyDto.setCu_projectId("0");
                    applyDto.setCu_projectName("全部");
                }else {
                    applyDto.setCu_projectId(houseProject.getPinyinCode());
                    applyDto.setCu_projectName(houseProject.getName());
                }

                if (apply.getApplyCount() != null) {
                    UserInfoEntity userInfo=houseInfoRepository.getUserById(apply.getUserId());
                    HouseInfoEntity houseInfoEntity=houseInfoRepository.getHouseByMemberId(userInfo.getId());
                    if(houseInfoEntity!=null){
                        String houseProjectName=houseInfoEntity.getProjectName();//项目
                        String projectNum=houseInfoEntity.getProjectNum();
                        //str+=projectNum+",";
//                        if(houseProjectName.equals(houseProject.getName())){
                      //  str1+=houseProject.getPinyinCode()+",";
                        if(projectNum.equals(houseProject.getPinyinCode())){
                            applyDto.setApplyCount(apply.getApplyCount());
//                            applyDto.setApplyPhone(apply.getApplyPhone());
                            applyDto.setApplyId(apply.getApplyId());
                            applyDto.setUserId(user.getUserId());
                            applyDto.setUserName(user.getRealName());//用户名---
                            applyDto.setUserMobile(user.getMobile());
                            applyDto.setActivityDate(community.getActivityDate());
                            applyDto.setActivityId(apply.getActivityId());
                            applyDto.setTitle(community.getTitle());
                            applyDto.setCu_cityName(CommunityActivityInfoScope.getCity());
                            applyDto.setApplyDate(String.valueOf(apply.getMakedate()));
                            if (!StringUtils.isNullOrEmpty(apply.getApplyInfo()) && !"null".equals(apply.getApplyInfo())) {
                                applyDto.setApplyInfo(JSONArray.fromObject(apply.getApplyInfo()));//报名信息 ,报名表中
                            }else {
                                applyDto.setAttributeName(JSONArray.fromObject("[{'name':'无'}]"));//属性
                            }
                            if (!StringUtils.isNullOrEmpty(community.getApplyInfo())) {
                                applyDto.setAttributeName(JSONArray.fromObject(community.getApplyInfo()));//属性
                            }else {
                                applyDto.setAttributeName(JSONArray.fromObject("[{'name':'无'}]"));//属性
                            }
                            applyDto.setCu_projectName(CommunityActivityInfoScope.getProjectName());
                            //新增报名联系地址
                            applyDto.setApplyAddress(apply.getApplyAddress());
                            activityApplyAdminDtos.add(applyDto);
                        }
                    }
                }
            }
            String projectScope="";
        }
       // System.out.println("projectNum="+str);
       // System.out.println("getPinyinCode="+str1);
        List<ActivityApplyAdminDto> activityApplyAdminDtos1 = new ArrayList<>();
        if (activityApplyAdminDtos.size() > 0) {
            //验证，下面两行可以去掉
            int pageIndex = webPage.getPageIndex();
            int pageSize = webPage.getPageSize();
            //该页第一条记录
            webPage.setRecordCount(activityApplyAdminDtos.size());
            int firstResult = webPage.getStartRow();
            int endResult = webPage.getPageIndex() * webPage.getPageSize();
            if (endResult > webPage.getRecordCount()) {
                for (int i=firstResult; i<webPage.getRecordCount(); i++) {
                    activityApplyAdminDtos1.add(activityApplyAdminDtos.get(i));
                }
            }else {
                for (int i=firstResult; i<endResult; i++) {
                    activityApplyAdminDtos1.add(activityApplyAdminDtos.get(i));
                }
            }
        }

        //排序
        Collections.sort(activityApplyAdminDtos1, new Comparator<ActivityApplyAdminDto>() {
            @Override
            public int compare(ActivityApplyAdminDto o1, ActivityApplyAdminDto o2) {
                Long date1 = null;
                Long date2 = null;
                String applyDate1 = o1.getApplyDate();
                if (!StringUtils.isNullOrEmpty(applyDate1)) {
                    date1 = DateUtils.parse(applyDate1, "yyyy-MM-dd").getTime();
                }
                String applyDate2 = o2.getApplyDate();
                if (!StringUtils.isNullOrEmpty(applyDate2)) {
                    date2 = DateUtils.parse(applyDate2, "yyyy-MM-dd").getTime();
                }
                if (date1 > date2) {
                    return -1;
                } else if (date1 < date2) {
                    return 1;
                }
                return 0;
            }
        });

        return activityApplyAdminDtos1;
    }

    /**
     * 新增活动，同时添加图片
     * @param activityAdminDto
     * @return
     */
    @Override
    public boolean addActivity(ActivityAdminDto activityAdminDto) {
        CommunityActivityInfoEntity communityActivityInfoEntity = new CommunityActivityInfoEntity();
        String commnuityActivityId = IdGen.getNanaTimeID();
        communityActivityInfoEntity.setActivityDate(activityAdminDto.getActivityDate());
        communityActivityInfoEntity.setModifydate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityActivityInfoEntity.setMakedate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityActivityInfoEntity.setScope(activityAdminDto.getProjects());
        communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
        communityActivityInfoEntity.setActivityId(commnuityActivityId);
        communityActivityInfoEntity.setActivityDesc(activityAdminDto.getActivityDesc());
        communityActivityInfoEntity.setAddress(activityAdminDto.getAddress());//新增活动地址 修改
        communityActivityInfoEntity.setHotline(activityAdminDto.getHotline());
        communityActivityInfoEntity.setTitle(activityAdminDto.getTitle());
        communityActivityInfoEntity.setState("1");
        communityActivityInfoEntity.setPushState(activityAdminDto.getPushState());
        if("1".equals(activityAdminDto.getPushState())){
            communityActivityInfoEntity.setStatus("1");
        }else{
            communityActivityInfoEntity.setStatus("5");
        }
        if (activityAdminDto.getCu_projectId()==null) {
            communityActivityInfoEntity.setProjectId("0");
        }else{
            communityActivityInfoEntity.setProjectId(activityAdminDto.getCu_projectId());
        }
        communityActivityInfoEntity.setHeadCount(activityAdminDto.getHeadCount());
        communityActivityInfoEntity.setPeople(0);
        communityActivityInfoEntity.setTypes(activityAdminDto.getTypes());

        communityRespository.saveAdminCommunityActivityInfo(communityActivityInfoEntity);

        //添加图片
        CommunityImageInfoEntity communityImageInfoEntity = new CommunityImageInfoEntity();

        communityImageInfoEntity.setActivityId(commnuityActivityId);
        communityImageInfoEntity.setImageId(IdGen.getNanaTimeID());
        communityImageInfoEntity.setMakedate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityImageInfoEntity.setModifydate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityImageInfoEntity.setOperator(activityAdminDto.getOperator());
        //存储图片。
        String fileName = imgService.uploadAdminImage(activityAdminDto.getHomePageimgpath(),ImgType.ACTIVITY);
        //图片地址特殊处理
        String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
        fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");


        if (fileName.equals("")){
            fileName = "默认图";
        }
        communityImageInfoEntity.setUrl(fileName);
        communityImageInfoEntity.setType(CommunityImageInfoEntity.type.homePage_type);

        communityRespository.saveAdminCommunityImage(communityImageInfoEntity);

        //基础数据准备
        List<String> cityList = Arrays.asList(activityAdminDto.getCitys().split(","));
        List<String> projectList = Arrays.asList(activityAdminDto.getProjects().split(","));
        List<String> cityIds = Arrays.asList(activityAdminDto.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(activityAdminDto.getProjectIds().split(","));
        CommunityActivityInfoScopeEntity communityActivityInfoScope;

        //全部城市
        if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
            //获取所有城市下所有项目
            List<Object[]> projects = announcementRepository.listAllProject();
            for (int i = 0; i < projects.size(); i++) {
                communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                communityActivityInfoScope.setId(IdGen.uuid());
                communityActivityInfoScope.setActivityId(commnuityActivityId);
                communityActivityInfoScope.setCity("全部城市");
                communityActivityInfoScope.setCityId("0");
                communityActivityInfoScope.setProjectId((String) projects.get(i)[0]);
                communityActivityInfoScope.setProjectName((String) projects.get(i)[1]);
                communityActivityInfoScope.setStatus(1);
                communityActivityInfoScope.setCreateDate(new Date());
                communityActivityInfoScope.setOperateDate(new Date());
                communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
            }
        }

        //全部项目
        if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
            //遍历城市范围,检索每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++) {
                String cityId = cityIds.get(i);
                List<Object[]> projects = announcementRepository.listProject(cityId);

                //为每个项目添加公告范围信息
                for (int j = 0; j < projects.size(); j++) {
                    communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                    communityActivityInfoScope.setId(IdGen.uuid());
                    communityActivityInfoScope.setActivityId(commnuityActivityId);
                    communityActivityInfoScope.setCity(cityList.get(i));
                    communityActivityInfoScope.setCityId(cityId);
                    communityActivityInfoScope.setProjectName((String) projects.get(j)[1]);
                    communityActivityInfoScope.setProjectId((String) projects.get(j)[0]);
                    //PropertyHelplineScopeEntity.setScope("2");
                    communityActivityInfoScope.setStatus(1);
                    communityActivityInfoScope.setCreateDate(new Date());
                    communityActivityInfoScope.setOperateDate(new Date());
                    communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                    communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                    this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
                }
            }
        }

        //单个项目
        if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
            for (int i = 0; i < projectList.size(); i++) {
                String projects = projectList.get(i);

                communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                communityActivityInfoScope.setId(IdGen.uuid());
                communityActivityInfoScope.setActivityId(commnuityActivityId);
                //通过项目Code绑定城市
                List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                if (!list.isEmpty() && list.size() > 0){
                    communityActivityInfoScope.setCity(list.get(0)[1].toString());
                    communityActivityInfoScope.setCityId(list.get(0)[0].toString());
                }
                communityActivityInfoScope.setProjectName(projects);
                communityActivityInfoScope.setProjectId(projectIds.get(i));
                // PropertyHelplineScopeEntity.setScope("3");
                communityActivityInfoScope.setStatus(1);
                communityActivityInfoScope.setCreateDate(new Date());
                communityActivityInfoScope.setOperateDate(new Date());
                communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
            }
        }


//        communityImageInfoEntity.setImageId(IdGen.getNanaTimeID());
//        String fileNames = ImageUpload.saveImageToService(activityAdminDto.getActivityimgpath(), ImgType.ACTIVITY);
//        if (activityAdminDto.getActivityimgpath().isEmpty()) {
//            communityImageInfoEntity.setUrl(AppConfig.SERVICEPATH + defalut_circle_image.getConfigValue());
//        }
//        else {
//            communityImageInfoEntity.setUrl(AppConfig.SERVICEPATH + fileNames);
//
//        }
////        communityImageInfoEntity.setUrl(AppConfig.SERVICEPATH + fileNames);
//        communityImageInfoEntity.setType(CommunityImageInfoEntity.type.activity_type);
//        communityRespository.saveAdminCommunityImage(communityImageInfoEntity);

        return true;
    }

    @Override
    public boolean addActivity(UserPropertyStaffEntity user, ActivityAdminDto activityAdminDto) {
        CommunityActivityInfoEntity communityActivityInfoEntity = new CommunityActivityInfoEntity();
        String commnuityActivityId = IdGen.getNanaTimeID();
        String applyInfo = activityAdminDto.getApplyInfo();
        String result = "";
        if (!StringUtils.isNullOrEmpty(applyInfo)) {
            String[] info = applyInfo.split(",");
            for (String text : info) {
                int length = info.length;
                if (length > 1){
                    if (text == info[length-1]){
                        text = "{'name':"+"'"+text+"'}]";
                    }else {
                        if (text == info[0]) {
                            text = "[{'name':"+"'"+text+"'}"+",";
                        }else {
                            text = "{'name':"+"'"+text+"'}"+",";
                        }
                    }
                    result += text;
                }else if (length == 1){
                    result = "[{'name':"+"'"+text+"'}]";
                }
            }
        }
        communityActivityInfoEntity.setApplyInfo(result);
        communityActivityInfoEntity.setActivityEndDate(activityAdminDto.getActivityEndDate());
        communityActivityInfoEntity.setApplyStartTime(activityAdminDto.getApplyStartTime());
        communityActivityInfoEntity.setApplyEndTime(activityAdminDto.getApplyEndTime());
        communityActivityInfoEntity.setActivityDate(activityAdminDto.getActivityDate());
        communityActivityInfoEntity.setModifydate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityActivityInfoEntity.setMakedate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityActivityInfoEntity.setScope(activityAdminDto.getProjects());
        communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
        communityActivityInfoEntity.setActivityId(commnuityActivityId);
        communityActivityInfoEntity.setActivityDesc(activityAdminDto.getActivityDesc());
        communityActivityInfoEntity.setAddress(activityAdminDto.getAddress());//新增活动地址 修改
        communityActivityInfoEntity.setHotline(activityAdminDto.getHotline());
        communityActivityInfoEntity.setTitle(activityAdminDto.getTitle());
        communityActivityInfoEntity.setState("1");
        communityActivityInfoEntity.setPushState(activityAdminDto.getPushState());
        if("1".equals(activityAdminDto.getPushState())){
            communityActivityInfoEntity.setStatus("1");
        }else{
            communityActivityInfoEntity.setStatus("5");
        }
        if (activityAdminDto.getCu_projectId()==null) {
            communityActivityInfoEntity.setProjectId("0");
        }else{
            communityActivityInfoEntity.setProjectId(activityAdminDto.getCu_projectId());
        }
        communityActivityInfoEntity.setHeadCount(activityAdminDto.getHeadCount());
        communityActivityInfoEntity.setApplyMaxNum(activityAdminDto.getApplyMaxNum());
        communityActivityInfoEntity.setPeople(0);
        communityActivityInfoEntity.setTypes(activityAdminDto.getTypes());
        communityActivityInfoEntity.setActivityType(activityAdminDto.getActivityType());
        communityActivityInfoEntity.setThemeType(activityAdminDto.getThemeType());
        communityActivityInfoEntity.setIsBanner(activityAdminDto.getIsBanner());
        communityActivityInfoEntity.setIsLink(activityAdminDto.getIsLink());
        communityActivityInfoEntity.setLinkSrc(activityAdminDto.getLinkSrc());
        communityActivityInfoEntity.setApplyInfoMaxNum(activityAdminDto.getApplyInfoMaxNum());
        communityActivityInfoEntity.setIsBlacklist(activityAdminDto.getIsBlacklist());//是否使用黑名单
        if(null != activityAdminDto.getIsBlacklist() && activityAdminDto.getIsBlacklist() == 1){
            communityActivityInfoEntity.setBlacklistId(activityAdminDto.getBlacklistId());//黑名单ID
        }else{
            communityActivityInfoEntity.setBlacklistId(null);//黑名单ID
        }
        communityRespository.saveAdminCommunityActivityInfo(communityActivityInfoEntity);

        //添加图片
        CommunityImageInfoEntity communityImageInfoEntity = new CommunityImageInfoEntity();

        communityImageInfoEntity.setActivityId(commnuityActivityId);
        communityImageInfoEntity.setImageId(IdGen.getNanaTimeID());
        communityImageInfoEntity.setMakedate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityImageInfoEntity.setModifydate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityImageInfoEntity.setOperator(activityAdminDto.getOperator());
        //存储图片。
        String fileName = imgService.uploadAdminImage(activityAdminDto.getHomePageimgpath(),ImgType.ACTIVITY);
        //图片地址特殊处理
        String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
        fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");


        if (fileName.equals("")){
            fileName = "默认图";
        }
        communityImageInfoEntity.setUrl(fileName);
        communityImageInfoEntity.setType(CommunityImageInfoEntity.type.homePage_type);

        communityRespository.saveAdminCommunityImage(communityImageInfoEntity);

        //基础数据准备
        List<String> cityList = Arrays.asList(activityAdminDto.getCitys().split(","));
        List<String> projectList = Arrays.asList(activityAdminDto.getProjects().split(","));
        List<String> cityIds = Arrays.asList(activityAdminDto.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(activityAdminDto.getProjectIds().split(","));
        CommunityActivityInfoScopeEntity communityActivityInfoScope;
        String assCommunitys="";//关联社区
        //全部城市
        if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
            //获取所有城市下所有项目
            List<Object[]> projects = announcementRepository.listAllProject();
            for (int i = 0; i < projects.size(); i++) {
                communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                communityActivityInfoScope.setId(IdGen.uuid());
                communityActivityInfoScope.setActivityId(commnuityActivityId);
                communityActivityInfoScope.setCity("全部城市");
                communityActivityInfoScope.setCityId("0");
                communityActivityInfoScope.setProjectId((String) projects.get(i)[0]);
                communityActivityInfoScope.setProjectName((String) projects.get(i)[1]);
                communityActivityInfoScope.setStatus(1);
                communityActivityInfoScope.setCreateDate(new Date());
                communityActivityInfoScope.setOperateDate(new Date());
                communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
            }
        }

        //全部项目
        if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
            //遍历城市范围,检索每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++) {
                String cityId = cityIds.get(i);
                List<Object[]> projects = announcementRepository.listProject(cityId);

                //为每个项目添加公告范围信息
                for (int j = 0; j < projects.size(); j++) {
                    communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                    communityActivityInfoScope.setId(IdGen.uuid());
                    communityActivityInfoScope.setActivityId(commnuityActivityId);
                    communityActivityInfoScope.setCity(cityList.get(i));
                    communityActivityInfoScope.setCityId(cityId);
                    communityActivityInfoScope.setProjectName((String) projects.get(j)[1]);
                    communityActivityInfoScope.setProjectId((String) projects.get(j)[0]);
                    //PropertyHelplineScopeEntity.setScope("2");
                    communityActivityInfoScope.setStatus(1);
                    communityActivityInfoScope.setCreateDate(new Date());
                    communityActivityInfoScope.setOperateDate(new Date());
                    communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                    communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                    this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
                }
            }
        }

        //单个项目
        if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
            for (int i = 0; i < projectList.size(); i++) {
                String projects = projectList.get(i);

                communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                communityActivityInfoScope.setId(IdGen.uuid());
                communityActivityInfoScope.setActivityId(commnuityActivityId);
                //通过项目Code绑定城市
                List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                if (!list.isEmpty() && list.size() > 0){
                    communityActivityInfoScope.setCity(list.get(0)[1].toString());
                    communityActivityInfoScope.setCityId(list.get(0)[0].toString());
                }
                communityActivityInfoScope.setProjectName(projects);
                communityActivityInfoScope.setProjectId(projectIds.get(i));
                // PropertyHelplineScopeEntity.setScope("3");
                communityActivityInfoScope.setStatus(1);
                communityActivityInfoScope.setCreateDate(new Date());
                communityActivityInfoScope.setOperateDate(new Date());
                communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
            }
        }

        String projectCodes = "";
        String projectNames = "";
        String cityName="";
        List<Map<String, Object>> mapList = communityRespository.queryProjectByActivityId(commnuityActivityId);
        if (mapList.size() > 0){
            for (int i = 0; i < mapList.size(); i++){
                Map<String, Object> map = mapList.get(i);
                projectCodes = projectCodes + (String) map.get("projectCode") + ",";
                projectNames = projectNames + (String) map.get("projectName") + ",";
                cityName=(String) map.get("cityName");
            }
        }
        //新增信息发布日志
        InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
        infoReleaseLog.setLogId(IdGen.uuid());
        infoReleaseLog.setLogTime(new Date());//注册日期
        infoReleaseLog.setUserName(user.getStaffName());//用户昵称
        infoReleaseLog.setUserMobile(user.getMobile());//手机号
        infoReleaseLog.setThisSection("服务");//版块
        infoReleaseLog.setThisFunction("活动信息管理");//功能
        infoReleaseLog.setThisType("新增");//操作类型
        infoReleaseLog.setLogContent(communityActivityInfoEntity.getTitle());//发布内容
        infoReleaseLog.setAsscommunity(projectNames);//关联社区
        systemLogRepository.addInfoReleaseLog(infoReleaseLog);

//        communityImageInfoEntity.setImageId(IdGen.getNanaTimeID());
//        String fileNames = ImageUpload.saveImageToService(activityAdminDto.getActivityimgpath(), ImgType.ACTIVITY);
//        if (activityAdminDto.getActivityimgpath().isEmpty()) {
//            communityImageInfoEntity.setUrl(AppConfig.SERVICEPATH + defalut_circle_image.getConfigValue());
//        }
//        else {
//            communityImageInfoEntity.setUrl(AppConfig.SERVICEPATH + fileNames);
//
//        }
////        communityImageInfoEntity.setUrl(AppConfig.SERVICEPATH + fileNames);
//        communityImageInfoEntity.setType(CommunityImageInfoEntity.type.activity_type);
//        communityRespository.saveAdminCommunityImage(communityImageInfoEntity);

        return true;
    }

    /**
     * 执行更新
     * @param activityAdminDto
     * @return
     */
    @Override
    public boolean updateEditActicity(ActivityAdminDto activityAdminDto) {
        CommunityActivityInfoEntity communityActivityInfoEntity = communityRespository.queryActivityInfoById(activityAdminDto.getActivityId());
        communityActivityInfoEntity.setActivityDate(activityAdminDto.getActivityDate());
        communityActivityInfoEntity.setModifydate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityActivityInfoEntity.setScope(activityAdminDto.getProjects());
        communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
        communityActivityInfoEntity.setActivityDesc(activityAdminDto.getActivityDesc());
        communityActivityInfoEntity.setAddress(activityAdminDto.getAddress());//新增活动地址 修改
        communityActivityInfoEntity.setHotline(activityAdminDto.getHotline());
        communityActivityInfoEntity.setTitle(activityAdminDto.getTitle());
        communityActivityInfoEntity.setTypes(activityAdminDto.getTypes());
        communityActivityInfoEntity.setState("1");
        communityActivityInfoEntity.setPushState(activityAdminDto.getPushState());
        if("1".equals(activityAdminDto.getPushState())){
            communityActivityInfoEntity.setStatus("1");
        }else{
            communityActivityInfoEntity.setStatus("5");
        }
        if (activityAdminDto.getCu_projectId()==null) {
            communityActivityInfoEntity.setProjectId("0");
        }else{
            communityActivityInfoEntity.setProjectId(activityAdminDto.getCu_projectId());
        }
        communityActivityInfoEntity.setHeadCount(activityAdminDto.getHeadCount());
        communityActivityInfoEntity.setPeople(0);
        communityActivityInfoEntity.setTypes(activityAdminDto.getTypes());
        communityActivityInfoEntity.setApplyInfoMaxNum(activityAdminDto.getApplyInfoMaxNum());
        communityRespository.updateActivity(communityActivityInfoEntity);
        //更新图片


        if(activityAdminDto.getHomePageimgpath().getSize()>0){
            CommunityImageInfoEntity communityImageInfoEntity = communityRespository.getFirstImageEntity(activityAdminDto.getActivityId());

            communityImageInfoEntity.setActivityId(activityAdminDto.getActivityId());
            communityImageInfoEntity.setModifydate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
            communityImageInfoEntity.setOperator(activityAdminDto.getOperator());

            //存储图片。
            String fileName = imgService.uploadAdminImage(activityAdminDto.getHomePageimgpath(),ImgType.ACTIVITY);
            //图片地址特殊处理
            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            if (fileName.equals("")){
                fileName = "默认图";
            }
            communityImageInfoEntity.setUrl(fileName);
            communityImageInfoEntity.setType(CommunityImageInfoEntity.type.homePage_type);
            communityRespository.updateCommunityImage(communityImageInfoEntity);
        }
        //删除活动区域中间表信息
        communityRespository.deleteCommunityActivityInfoScope(activityAdminDto.getActivityId());
        //基础数据准备
        List<String> cityList = Arrays.asList(activityAdminDto.getCitys().split(","));
        List<String> projectList = Arrays.asList(activityAdminDto.getProjects().split(","));
        List<String> cityIds = Arrays.asList(activityAdminDto.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(activityAdminDto.getProjectIds().split(","));
        CommunityActivityInfoScopeEntity communityActivityInfoScope;
        //全部城市
        if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
            //获取所有城市下所有项目
            List<Object[]> projects = announcementRepository.listAllProject();
            for (int i = 0; i < projects.size(); i++) {
                communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                communityActivityInfoScope.setId(IdGen.uuid());
                communityActivityInfoScope.setActivityId(activityAdminDto.getActivityId());
                communityActivityInfoScope.setCity("全部城市");
                communityActivityInfoScope.setCityId("0");
                communityActivityInfoScope.setProjectId((String) projects.get(i)[0]);
                communityActivityInfoScope.setProjectName((String) projects.get(i)[1]);
                communityActivityInfoScope.setStatus(1);
                communityActivityInfoScope.setCreateDate(new Date());
                communityActivityInfoScope.setOperateDate(new Date());
                communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
            }
        }
        //全部项目
        if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
            //遍历城市范围,检索每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++) {
                String cityId = cityIds.get(i);
                List<Object[]> projects = announcementRepository.listProject(cityId);

                //为每个项目添加公告范围信息
                for (int j = 0; j < projects.size(); j++) {
                    communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                    communityActivityInfoScope.setId(IdGen.uuid());
                    communityActivityInfoScope.setActivityId(activityAdminDto.getActivityId());
                    communityActivityInfoScope.setCity(cityList.get(i));
                    communityActivityInfoScope.setCityId(cityId);
                    communityActivityInfoScope.setProjectName((String) projects.get(j)[1]);
                    communityActivityInfoScope.setProjectId((String) projects.get(j)[0]);
                    //PropertyHelplineScopeEntity.setScope("2");
                    communityActivityInfoScope.setStatus(1);
                    communityActivityInfoScope.setCreateDate(new Date());
                    communityActivityInfoScope.setOperateDate(new Date());
                    communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                    communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                    this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
                }
            }
        }
        //单个项目
        if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
            for (int i = 0; i < projectList.size(); i++) {
                String projects = projectList.get(i);

                communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                communityActivityInfoScope.setId(IdGen.uuid());
                communityActivityInfoScope.setActivityId(activityAdminDto.getActivityId());
                //通过项目Code绑定城市
                List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                if (!list.isEmpty() && list.size() > 0){
                    communityActivityInfoScope.setCity(list.get(0)[1].toString());
                    communityActivityInfoScope.setCityId(list.get(0)[0].toString());
                }
                communityActivityInfoScope.setProjectName(projects);
                communityActivityInfoScope.setProjectId(projectIds.get(i));
                // PropertyHelplineScopeEntity.setScope("3");
                communityActivityInfoScope.setStatus(1);
                communityActivityInfoScope.setCreateDate(new Date());
                communityActivityInfoScope.setOperateDate(new Date());
                communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
            }
        }
        return true;
    }

    @Override
    public boolean updateEditActicity(UserPropertyStaffEntity user, ActivityAdminDto activityAdminDto) {
        CommunityActivityInfoEntity communityActivityInfoEntity = communityRespository.queryActivityInfoById(activityAdminDto.getActivityId());
        communityActivityInfoEntity.setActivityDate(activityAdminDto.getActivityDate());//活动开始时间
        communityActivityInfoEntity.setActivityEndDate(activityAdminDto.getActivityEndDate());//活动结束时间
        communityActivityInfoEntity.setApplyStartTime(activityAdminDto.getApplyStartTime());//报名开始时间
        communityActivityInfoEntity.setApplyEndTime(activityAdminDto.getApplyEndTime());//报名结束时间
        communityActivityInfoEntity.setModifydate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
        communityActivityInfoEntity.setScope(activityAdminDto.getProjects());
        communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
        communityActivityInfoEntity.setActivityDesc(activityAdminDto.getActivityDesc());
        communityActivityInfoEntity.setAddress(activityAdminDto.getAddress());//新增活动地址 修改
        communityActivityInfoEntity.setHotline(activityAdminDto.getHotline());
        communityActivityInfoEntity.setTitle(activityAdminDto.getTitle());
        communityActivityInfoEntity.setTypes(activityAdminDto.getTypes());
        communityActivityInfoEntity.setState("1");
        communityActivityInfoEntity.setPushState(activityAdminDto.getPushState());
        communityActivityInfoEntity.setActivityType(activityAdminDto.getActivityType());
        communityActivityInfoEntity.setThemeType(activityAdminDto.getThemeType());
        communityActivityInfoEntity.setIsBanner(activityAdminDto.getIsBanner());
        communityActivityInfoEntity.setIsLink(activityAdminDto.getIsLink());
        communityActivityInfoEntity.setLinkSrc(activityAdminDto.getLinkSrc());
        communityActivityInfoEntity.setIsBlacklist(activityAdminDto.getIsBlacklist());//是否使用黑名单
        if(null != activityAdminDto.getIsBlacklist() && activityAdminDto.getIsBlacklist() == 1){
            communityActivityInfoEntity.setBlacklistId(activityAdminDto.getBlacklistId());//黑名单ID
        }else{
            communityActivityInfoEntity.setBlacklistId(null);//黑名单ID
        }
        communityActivityInfoEntity.setIsTimeRange(activityAdminDto.getIsTimeRange());//是否配置报名时间段
        if("1".equals(activityAdminDto.getPushState())){
            communityActivityInfoEntity.setStatus("1");
        }else{
            communityActivityInfoEntity.setStatus("5");
        }
        if (activityAdminDto.getCu_projectId()==null) {
            communityActivityInfoEntity.setProjectId("0");
        }else{
            communityActivityInfoEntity.setProjectId(activityAdminDto.getCu_projectId());
        }
        communityActivityInfoEntity.setHeadCount(activityAdminDto.getHeadCount());
        communityActivityInfoEntity.setApplyMaxNum(activityAdminDto.getApplyMaxNum());
        //communityActivityInfoEntity.setPeople(0);
        communityActivityInfoEntity.setTypes(activityAdminDto.getTypes());
        String applyInfo = activityAdminDto.getApplyInfo();
        String result = "";
        if (!StringUtils.isNullOrEmpty(applyInfo)) {
            String[] info = applyInfo.split(",");
            for (String text : info) {
                int length = info.length;
                if (length > 1){
                    if (text == info[length-1]){
                        text = "{'name':"+"'"+text+"'}]";
                    }else {
                        if (text == info[0]) {
                            text = "[{'name':"+"'"+text+"'}"+",";
                        }else {
                            text = "{'name':"+"'"+text+"'}"+",";
                        }
                    }
                    result += text;
                }else if (length == 1){
                    result = "[{'name':"+"'"+text+"'}]";
                }
            }
        }
        communityActivityInfoEntity.setApplyInfo(result);
        communityActivityInfoEntity.setApplyInfoMaxNum(activityAdminDto.getApplyInfoMaxNum());
        communityRespository.updateActivity(communityActivityInfoEntity);
        //更新图片
        if(activityAdminDto.getHomePageimgpath().getSize()>0){
            CommunityImageInfoEntity communityImageInfoEntity = communityRespository.getFirstImageEntity(activityAdminDto.getActivityId());

            communityImageInfoEntity.setActivityId(activityAdminDto.getActivityId());
            communityImageInfoEntity.setModifydate((DateUtils.format(new Date(), DateUtils.FORMAT_LONG)));
            communityImageInfoEntity.setOperator(activityAdminDto.getOperator());

            //存储图片。
            String fileName = imgService.uploadAdminImage(activityAdminDto.getHomePageimgpath(),ImgType.ACTIVITY);
            //图片地址特殊处理
            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            if (fileName.equals("")){
                fileName = "默认图";
            }
            communityImageInfoEntity.setUrl(fileName);
            communityImageInfoEntity.setType(CommunityImageInfoEntity.type.homePage_type);
            communityRespository.updateCommunityImage(communityImageInfoEntity);
        }
        //删除活动区域中间表信息
        communityRespository.deleteCommunityActivityInfoScope(activityAdminDto.getActivityId());
        //基础数据准备
        List<String> cityList = Arrays.asList(activityAdminDto.getCitys().split(","));
        List<String> projectList = Arrays.asList(activityAdminDto.getProjects().split(","));
        List<String> cityIds = Arrays.asList(activityAdminDto.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(activityAdminDto.getProjectIds().split(","));
        CommunityActivityInfoScopeEntity communityActivityInfoScope;
        //全部城市
        if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
            //获取所有城市下所有项目
            List<Object[]> projects = announcementRepository.listAllProject();
            for (int i = 0; i < projects.size(); i++) {
                communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                communityActivityInfoScope.setId(IdGen.uuid());
                communityActivityInfoScope.setActivityId(activityAdminDto.getActivityId());
                communityActivityInfoScope.setCity("全部城市");
                communityActivityInfoScope.setCityId("0");
                communityActivityInfoScope.setProjectId((String) projects.get(i)[0]);
                communityActivityInfoScope.setProjectName((String) projects.get(i)[1]);
                communityActivityInfoScope.setStatus(1);
                communityActivityInfoScope.setCreateDate(new Date());
                communityActivityInfoScope.setOperateDate(new Date());
                communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
            }
        }
        //全部项目
        if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
            //遍历城市范围,检索每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++) {
                String cityId = cityIds.get(i);
                List<Object[]> projects = announcementRepository.listProject(cityId);

                //为每个项目添加公告范围信息
                for (int j = 0; j < projects.size(); j++) {
                    communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                    communityActivityInfoScope.setId(IdGen.uuid());
                    communityActivityInfoScope.setActivityId(activityAdminDto.getActivityId());
                    communityActivityInfoScope.setCity(cityList.get(i));
                    communityActivityInfoScope.setCityId(cityId);
                    communityActivityInfoScope.setProjectName((String) projects.get(j)[1]);
                    communityActivityInfoScope.setProjectId((String) projects.get(j)[0]);
                    //PropertyHelplineScopeEntity.setScope("2");
                    communityActivityInfoScope.setStatus(1);
                    communityActivityInfoScope.setCreateDate(new Date());
                    communityActivityInfoScope.setOperateDate(new Date());
                    communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                    communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                    this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
                }
            }
        }
        //单个项目
        if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
            for (int i = 0; i < projectList.size(); i++) {
                String projects = projectList.get(i);

                communityActivityInfoScope = new CommunityActivityInfoScopeEntity();
                communityActivityInfoScope.setId(IdGen.uuid());
                communityActivityInfoScope.setActivityId(activityAdminDto.getActivityId());
                //通过项目Code绑定城市
                List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                if (!list.isEmpty() && list.size() > 0){
                    communityActivityInfoScope.setCity(list.get(0)[1].toString());
                    communityActivityInfoScope.setCityId(list.get(0)[0].toString());
                }
                communityActivityInfoScope.setProjectName(projects);
                communityActivityInfoScope.setProjectId(projectIds.get(i));
                // PropertyHelplineScopeEntity.setScope("3");
                communityActivityInfoScope.setStatus(1);
                communityActivityInfoScope.setCreateDate(new Date());
                communityActivityInfoScope.setOperateDate(new Date());
                communityActivityInfoScope.setCreatePerson(communityActivityInfoEntity.getOperator());
                communityActivityInfoScope.setOperatePerson(communityActivityInfoEntity.getOperator());
                this.announcementScopeRepository.saveOrUpdate(communityActivityInfoScope);
            }
        }

        String projectCodes = "";
        String projectNames = "";
        String cityName="";
        List<Map<String, Object>> mapList = communityRespository.queryProjectByActivityId(activityAdminDto.getActivityId());
        if (mapList.size() > 0){
            for (int i = 0; i < mapList.size(); i++){
                Map<String, Object> map = mapList.get(i);
                projectCodes = projectCodes + (String) map.get("projectCode") + ",";
                projectNames = projectNames + (String) map.get("projectName") + ",";
                cityName=(String) map.get("cityName");
            }
        }

        //增加日志
        InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
        infoReleaseLog.setLogId(IdGen.uuid());
        infoReleaseLog.setLogTime(new Date());//注册日期
        infoReleaseLog.setUserName(user.getStaffName());//用户昵称
        infoReleaseLog.setUserMobile(user.getMobile());//手机号
        infoReleaseLog.setThisSection("服务");//版块
        infoReleaseLog.setThisFunction("活动信息管理");//功能
        infoReleaseLog.setThisType("编辑");//操作类型
        infoReleaseLog.setLogContent(communityActivityInfoEntity.getTitle());//发布内容
       /* HouseProjectEntity houseProjectEntity=houseProjectRepository.getProjectByCode(projectCode);
        if(houseProjectEntity!=null){
            infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
        }else{
            infoReleaseLog.setAsscommunity("");//关联社区;
        }*/
        infoReleaseLog.setAsscommunity(projectNames);//关联社区;
        systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        return true;
    }

    /**
     * 修改发布状态
     * @param activityAdminDto
     * @return
     */
    @Override
    public boolean turnActivity(ActivityAdminDto activityAdminDto) {
        CommunityActivityInfoEntity communityActivityInfoEntity = communityRespository.getCommuntiyActivityInfo(activityAdminDto.getActivityId(), null);
        if (communityActivityInfoEntity!=null&&communityActivityInfoEntity.getActivityId()!=null){

            if (communityActivityInfoEntity.getPushState().equals("1")){
                communityActivityInfoEntity.setPushState("0");
                communityActivityInfoEntity.setStatus("5");
                communityRespository.updateActivity(communityActivityInfoEntity);
                return true;
            }
            if (communityActivityInfoEntity.getPushState().equals("0")){
                communityActivityInfoEntity.setPushState("1");
                int dates = DateUtils.countDays(communityActivityInfoEntity.getActivityDate());
                if(dates>0){
                    communityActivityInfoEntity.setStatus("4");
                }else{
                    communityActivityInfoEntity.setStatus("1");
                }

                communityRespository.updateActivity(communityActivityInfoEntity);
                return true;
            }
        }
        return false;
    }

    /**
     * 通过活动Id检索首页推荐图
     * @param activityId 活动Id
     * @return String
     */
    @Override
    public String getCommunityImageById(String activityId){
        String src = "";
        List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activityId);
        if(imageList.size()>0){
            src = imageList.get(0).getUrl();//活动图片
        }
        return src;
    }

    /**
     * 通过活动Id检索活动范围数据
     * @param activityId 楼盘Id
     * @return List<Map<String,Object>>
     */
    @Override
    public Map<String,Object> queryProjectByCommunityId(String activityId){
        Map<String,Object> resuleMap = new HashMap<>();
        String projectCodes = "";
        String projectNames = "";
        String cityName="";
        List<Map<String, Object>> mapList = communityRespository.queryProjectByActivityId(activityId);
        if (mapList.size() > 0){
            for (int i = 0; i < mapList.size(); i++){
                Map<String, Object> map = mapList.get(i);
                projectCodes = projectCodes + (String) map.get("projectCode") + ",";
                projectNames = projectNames + (String) map.get("projectName") + ",";
                cityName=(String) map.get("cityName");
            }
        }
        resuleMap.put("projectCodes",projectCodes);
        resuleMap.put("projectNames",projectNames);
        resuleMap.put("cityName",cityName);
        return resuleMap;
    }

    /*********************************************金茂项目******************************************************/
    /**
     * CreatedBy:liudongxin
     * 获取活动列表
     * Describe:
     * 游客、普通用户查看所有公开的活动
     * 业主、同住人查看公开和本小区公开的活动
     * param user：用户信息
     * param page：分页
     * ModifyBy:
     */
    @Override
    public ApiResult getActivities(UserInfoEntity user, WebPage page,String projectCode) throws GeneralException {
        try {
            List<CommunityHomeListDto> community=new ArrayList<CommunityHomeListDto>();
            if(user==null){
                List<CommunityActivityInfoEntity> communities=communityRespository.getOpenActivitiesByProjectId(page, projectCode);
                if(communities.size()>0) {
                    for (CommunityActivityInfoEntity activity : communities) {
                         if(activity.getTypes().equals("1")){
                             //获取活动详情信息
                             CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(activity.getActivityId(), null);
                             if(activityInfo!=null){
                                 if(activityInfo.getPeople()==null){
                                     activityInfo.setPeople(0);
                                 }
                                 if(activityInfo.getHeadCount()==null){
                                     activityInfo.setHeadCount(0);
                                 }
                                 communityRespository.updateActivityInfo(activityInfo);
                             }
                             CommunityHomeListDto communityDto = new CommunityHomeListDto();
                             communityDto.setId(activity.getActivityId());//活动id
                             communityDto.setTitle(activity.getTitle());
                             communityDto.setActivityEndDate(activity.getActivityEndDate());//活动结束时间
                             communityDto.setApplyStartTime(activity.getApplyStartTime());//报名开始时间
                             communityDto.setApplyEndTime(activity.getApplyEndTime());//报名结束时间
                             communityDto.setPublishDate(activity.getActivityDate());//活动时间
                             communityDto.setScope(activity.getAddress());//活动范围
                             communityDto.setTypes(activity.getTypes());//类型：1为公开;2为私有
                             Integer headCount = activity.getHeadCount();
                             if (headCount == null) {
                                 headCount = 0;
                             }
                             communityDto.setHeadCount(headCount.toString());//预报名总人数
                             Integer people = (int) activity.getHeadCount() - (int)activity.getPeople();
                             communityDto.setPeople(people.toString());//剩余人数
                             List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activity.getActivityId());
                             if(imageList.size()>0){
                                 communityDto.setSrc(imageList.get(0).getUrl());//活动图片
                             }
                             communityDto.setDate(DateUtils.format(new Date(),"yyyy-MM-dd"));
                             community.add(communityDto);
                         }else{
                         }
                    }
                }
            }else{
                if(user.getUserType().equals("1") || user.getUserType().equals("2")){
                    List<CommunityActivityInfoEntity> communities=communityRespository.getOpenActivitiesByProjectId(page, projectCode);
                    if(communities.size()>0) {
                        for (CommunityActivityInfoEntity activity : communities) {
                            //获取活动详情信息
                            CommunityActivityInfoEntity activityInfo = communityRespository.getCommuntiyActivityInfo(activity.getActivityId(), null);
                            if (activityInfo != null) {
                                if (activityInfo.getPeople() == null) {
                                    activityInfo.setPeople(0);
                                }
                                if (activityInfo.getHeadCount() == null) {
                                    activityInfo.setHeadCount(0);
                                }
                                communityRespository.updateActivityInfo(activityInfo);
                            }
                            CommunityHomeListDto communityDto = new CommunityHomeListDto();
                            communityDto.setId(activity.getActivityId());//活动id
                            communityDto.setTitle(activity.getTitle());
                            communityDto.setActivityEndDate(activity.getActivityEndDate());//活动结束时间
                            communityDto.setApplyStartTime(activity.getApplyStartTime());//报名开始时间
                            communityDto.setApplyEndTime(activity.getApplyEndTime());//报名结束时间
                            communityDto.setPublishDate(activity.getActivityDate());//活动时间
                            communityDto.setTypes(activity.getTypes());//类型：1为公开;2为私有
                            // communityDto.setScope(activity.getScope());//活动范围
                            communityDto.setScope(activity.getAddress());//活动范围
                            activity.getTypes();
                            Integer headCount = activity.getHeadCount();
                            if (headCount == null) {
                                headCount = 0;
                            }
                            communityDto.setHeadCount(headCount.toString());//预报名总人数
                            Integer people = (int) activity.getHeadCount() - (int) activity.getPeople();
                            communityDto.setPeople(people.toString());//剩余人数
                            List<CommunityImageInfoEntity> imageList = communityRespository.getCommunityImageById(activity.getActivityId());
                            if (imageList.size() > 0) {
                                communityDto.setSrc(imageList.get(0).getUrl());//活动图片
                            }
                            CommunityCancelEntity cancelInfo = communityRespository.getCancelInfo(user.getUserId());
                            if (cancelInfo != null && !StringUtil.isEmpty(cancelInfo.getCancelNumber())) {
                                communityDto.setCancelNumber(cancelInfo.getCancelNumber());//取消次数
                            }
                            CommunityActivityApplyInfoEntity apply = communityRespository.getUserApplyInfo(activity.getActivityId(), user.getUserId());
                            if (apply != null) {
                                communityDto.setState(apply.getApplyDesc());//报名状态
                            } else {
                                communityDto.setState("0");//报名状态
                                // }
                            }
                            communityDto.setDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                            community.add(communityDto);
                        }
                    }
                }else if(user.getUserType().equals("3") || user.getUserType().equals("4")
                        || user.getUserType().equals("6")){
                    //------------------------------------获取业主下的项目start-----------------------------------------------
                    //获取业主的所在项目
                  /*  HouseInfoEntity houseInfo=houseInfoRepository.getDefaultHouse(user.getId());
                    if(houseInfo==null){
                        if(user.getUserType().equals("3")) {
                            houseInfo = houseInfoRepository.getHouseByMemberId(user.getId());
                        }else if(user.getUserType().equals("4")){
                            List<Object[]> houseList=houseInfoRepository.getHousemateDefaultHouse(user.getUserId());
                            Object[] obj=houseList.get(0).clone();
                            houseInfo.setProjectId(obj[3].toString());
                        }
                    }
                    if(houseInfo==null && StringUtil.isEmpty(houseInfo.getProjectId())){
                        return ErrorResource.getError("tip_pe00000009");//业主项目为空
                    }*/
                    //-------------------------------------获取业主下的项目end------------------------------------------------

                    //List<Object[]> communities=communityRespository.getActivities(houseInfo.getProjectId(), page);
                    List<CommunityActivityInfoEntity> communities=communityRespository.getOpenActivitiesByProjectId(page, projectCode);
                    //如果通过项目查询活动,
                    if(communities.size()>0){
                        for (CommunityActivityInfoEntity activity : communities) {
                            //获取活动详情信息
                            CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(activity.getActivityId(), null);

                            if(activityInfo!=null){
                                if(activityInfo.getPeople()==null){
                                    activityInfo.setPeople(0);
                                }
                                if(activityInfo.getHeadCount()==null){
                                    activityInfo.setHeadCount(0);
                                }
                                communityRespository.updateActivityInfo(activityInfo);
                            }

                            CommunityHomeListDto communityDto = new CommunityHomeListDto();
                            communityDto.setTypes(activity.getTypes());//类型：1为公开;2为私有
                            communityDto.setId(activity.getActivityId());//活动id
                            communityDto.setTitle(activity.getTitle());
                            communityDto.setActivityEndDate(activity.getActivityEndDate());//活动结束时间
                            communityDto.setApplyStartTime(activity.getApplyStartTime());//报名开始时间
                            communityDto.setApplyEndTime(activity.getApplyEndTime());//报名结束时间
                            communityDto.setPublishDate(activity.getActivityDate());//活动时间
                            // communityDto.setScope(activity.getScope());//活动范围
                            communityDto.setScope(activity.getAddress());//活动范围
                            Integer headCount = activity.getHeadCount();
                            if (headCount == null) {
                                headCount = 0;
                            }
                            communityDto.setHeadCount(headCount.toString());//预报名总人数
                            Integer people = (int) activity.getHeadCount() - (int)activity.getPeople();
                            communityDto.setPeople(people.toString());//剩余人数
                            List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activity.getActivityId());
                            if(imageList.size()>0){
                                communityDto.setSrc(imageList.get(0).getUrl());//活动图片
                            }
                            CommunityCancelEntity cancelInfo = communityRespository.getCancelInfo(user.getUserId());
                            if(cancelInfo!=null && !StringUtil.isEmpty(cancelInfo.getCancelNumber())){
                                communityDto.setCancelNumber(cancelInfo.getCancelNumber());//取消次数
                            }
                            CommunityActivityApplyInfoEntity apply=communityRespository.getUserApplyInfo(activity.getActivityId(), user.getUserId());
                            if(apply!=null){
                                communityDto.setState(apply.getApplyDesc());//报名状态
                            }else {
                                communityDto.setState("0");//报名状态
                            }
                            communityDto.setDate(DateUtils.format(new Date(),"yyyy-MM-dd"));
                            community.add(communityDto);
                        }
                    }
                }
                //调用点击人统计
                String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
                ClickUsersEntity clickUserEntity=clickUserRepository.getTotalInfo(dateNow,"8",user.getUserId());
                if(clickUserEntity==null){
                    ClickUsersEntity clickUser=new ClickUsersEntity();
                    clickUser.setId(IdGen.uuid());
                    clickUser.setCreateDate(new Date());
                    clickUser.setClicks(1);
                    clickUser.setUserId(user.getUserId());
                    clickUser.setForeignId("8");
                    clickUserRepository.create(clickUser);
                }else{
                    clickUserEntity.setClicks(clickUserEntity.getClicks()+1);
                    clickUserRepository.update(clickUserEntity);
                }
            }
            return new SuccessApiResult(community);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    @Override
    public ApiResult getActivitiesWeixin(UserInfoEntity user, WebPage page, String cityId) throws GeneralException {
        try {
            List<CommunityHomeListDto> community=new ArrayList<CommunityHomeListDto>();
            if(user==null){
                List<CommunityActivityInfoEntity> communities=communityRespository.getOpenActivitiesByCity(page, cityId);
                if(communities.size()>0) {
                    for (CommunityActivityInfoEntity activity : communities) {
                        if(activity.getTypes().equals("1")){
                            //获取活动详情信息
                            CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(activity.getActivityId(), null);
                            if(activityInfo!=null){
                                if(activityInfo.getPeople()==null){
                                    activityInfo.setPeople(0);
                                }
                                if(activityInfo.getHeadCount()==null){
                                    activityInfo.setHeadCount(0);
                                }
                                communityRespository.updateActivityInfo(activityInfo);
                            }
                            CommunityHomeListDto communityDto = new CommunityHomeListDto();
                            communityDto.setId(activity.getActivityId());//活动id
                            communityDto.setPublishDate(activity.getActivityDate());//活动时间
                            communityDto.setTitle(activity.getTitle());
                            communityDto.setActivityEndDate(activity.getActivityEndDate());//活动结束时间
                            communityDto.setApplyStartTime(activity.getApplyStartTime());//报名开始时间
                            communityDto.setApplyEndTime(activity.getApplyEndTime());//报名结束时间
                            communityDto.setTypes(activity.getTypes());//类型：1为公开;2为私有
                            //communityDto.setScope(activity.getScope());//活动范围
                            communityDto.setScope(activity.getAddress());//活动范围
                            Integer headCount = activity.getHeadCount();
                            if (headCount == null) {
                                headCount = 0;
                            }
                            communityDto.setHeadCount(headCount.toString());//预报名总人数
                            Integer people = (int) activity.getHeadCount() - (int)activity.getPeople();
                            communityDto.setPeople(people.toString());//剩余人数
                            List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activity.getActivityId());
                            if(imageList.size()>0){
                                communityDto.setSrc(imageList.get(0).getUrl());//活动图片
                            }
                            communityDto.setDate(DateUtils.format(new Date(),"yyyy-MM-dd"));
                            community.add(communityDto);
                        }else{

                        }
                    }
                }
            }else{
                if(user.getUserType().equals("1") || user.getUserType().equals("2")){
                    //通过城市id去查询----活动s
                    List<CommunityActivityInfoEntity> communities=communityRespository.getOpenActivitiesByCity(page, cityId);
                    if(communities.size()>0) {
                        for (CommunityActivityInfoEntity activity : communities) {
                            //获取活动详情信息
                            CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(activity.getActivityId(), null);
                            if(activityInfo!=null){
                                if(activityInfo.getPeople()==null){
                                    activityInfo.setPeople(0);
                                }
                                if(activityInfo.getHeadCount()==null){
                                    activityInfo.setHeadCount(0);
                                }
                                communityRespository.updateActivityInfo(activityInfo);
                            }
                            CommunityHomeListDto communityDto = new CommunityHomeListDto();
                            communityDto.setId(activity.getActivityId());//活动id
                            communityDto.setTitle(activity.getTitle());
                            communityDto.setActivityEndDate(activity.getActivityEndDate());//活动结束时间
                            communityDto.setApplyStartTime(activity.getApplyStartTime());//报名开始时间
                            communityDto.setApplyEndTime(activity.getApplyEndTime());//报名结束时间
                            communityDto.setPublishDate(activity.getActivityDate());//活动时间
                            communityDto.setTypes(activity.getTypes());//类型：1为公开;2为私有
                            //communityDto.setScope(activity.getScope());//活动范围
                            communityDto.setScope(activity.getAddress());//活动范围
                            Integer headCount = activity.getHeadCount();
                            if (headCount == null) {
                                headCount = 0;
                            }
                            communityDto.setHeadCount(headCount.toString());//预报名总人数
                            Integer people = (int) activity.getHeadCount() - (int)activity.getPeople();
                            communityDto.setPeople(people.toString());//剩余人数
                            List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activity.getActivityId());//活动id查询活动img
                            if(imageList.size()>0){
                                communityDto.setSrc(imageList.get(0).getUrl());//活动图片
                            }
                            CommunityCancelEntity cancelInfo = communityRespository.getCancelInfo(user.getUserId());
                            if(cancelInfo!=null && !StringUtil.isEmpty(cancelInfo.getCancelNumber())){
                                communityDto.setCancelNumber(cancelInfo.getCancelNumber());//取消次数
                            }
                            CommunityActivityApplyInfoEntity apply=communityRespository.getUserApplyInfo(activity.getActivityId(), user.getUserId());
                            if(apply!=null){
                                communityDto.setState(apply.getApplyDesc());//报名状态
                            }else {
                                communityDto.setState("0");
                            }
                            communityDto.setDate(DateUtils.format(new Date(),"yyyy-MM-dd"));
                            community.add(communityDto);
                        }
                    }
                }else if(user.getUserType().equals("3") || user.getUserType().equals("4")
                        || user.getUserType().equals("6")){
                    //获取业主的所在项目
                   /* HouseInfoEntity houseInfo=houseInfoRepository.getDefaultHouse(user.getId());
                    if(houseInfo==null){
                        if(user.getUserType().equals("3")) {
                            houseInfo = houseInfoRepository.getHouseByMemberId(user.getId());
                        }else if(user.getUserType().equals("4")){
                            List<Object[]> houseList=houseInfoRepository.getHousemateDefaultHouse(user.getUserId());
                            Object[] obj=houseList.get(0).clone();
                            houseInfo.setProjectId(obj[3].toString());
                        }
                    }
                    if(houseInfo==null && StringUtil.isEmpty(houseInfo.getProjectId())){
                        return ErrorResource.getError("tip_pe00000009");//业主项目为空
                    }*/
                    //List<Object[]> communities=communityRespository.getActivities(houseInfo.getProjectId(), page);
                    List<CommunityActivityInfoEntity> communities=communityRespository.getOpenActivitiesByCity(page, cityId);
                    if(communities.size()>0) {
                        for (CommunityActivityInfoEntity activity : communities) {
                            //获取活动详情信息
                            CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(activity.getActivityId(), null);
                            if(activityInfo!=null){
                                if(activityInfo.getPeople()==null){
                                    activityInfo.setPeople(0);
                                }
                                if(activityInfo.getHeadCount()==null){
                                    activityInfo.setHeadCount(0);
                                }
                                communityRespository.updateActivityInfo(activityInfo);
                            }
                            CommunityHomeListDto communityDto = new CommunityHomeListDto();
                            communityDto.setId(activity.getActivityId());//活动id
                            communityDto.setTitle(activity.getTitle());
                            communityDto.setActivityEndDate(activity.getActivityEndDate());//活动结束时间
                            communityDto.setApplyStartTime(activity.getApplyStartTime());//报名开始时间
                            communityDto.setApplyEndTime(activity.getApplyEndTime());//报名结束时间
                            communityDto.setPublishDate(activity.getActivityDate());//活动时间
                            communityDto.setTypes(activity.getTypes());//类型：1为公开;2为私有
                            //communityDto.setScope(activity.getScope());//活动范围
                            communityDto.setScope(activity.getAddress());//活动范围
                            Integer headCount = activity.getHeadCount();
                            if (headCount == null) {
                                headCount = 0;
                            }
                            communityDto.setHeadCount(headCount.toString());//预报名总人数
                            Integer people = (int) activity.getHeadCount() - (int)activity.getPeople();
                            communityDto.setPeople(people.toString());//剩余人数
                            List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activity.getActivityId());
                            if(imageList.size()>0){
                                communityDto.setSrc(imageList.get(0).getUrl());//活动图片
                            }
                            CommunityCancelEntity cancelInfo = communityRespository.getCancelInfo(user.getUserId());
                            if(cancelInfo!=null && !StringUtil.isEmpty(cancelInfo.getCancelNumber())){
                                communityDto.setCancelNumber(cancelInfo.getCancelNumber());//取消次数
                            }
                            CommunityActivityApplyInfoEntity apply=communityRespository.getUserApplyInfo(activity.getActivityId(), user.getUserId());
                            if(apply!=null){
                                communityDto.setState(apply.getApplyDesc());//报名状态
                            }else {
                                communityDto.setState("0");//报名状态
                            }
                            communityDto.setDate(DateUtils.format(new Date(),"yyyy-MM-dd"));
                            community.add(communityDto);
                        }
                    }
                }
                //调用点击人统计
                String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
                ClickUsersEntity clickUserEntity=clickUserRepository.getTotalInfo(dateNow,"8",user.getUserId());
                if(clickUserEntity==null){
                    ClickUsersEntity clickUser=new ClickUsersEntity();
                    clickUser.setId(IdGen.uuid());
                    clickUser.setCreateDate(new Date());
                    clickUser.setClicks(1);
                    clickUser.setUserId(user.getUserId());
                    clickUser.setForeignId("8");
                    clickUserRepository.create(clickUser);
                }else{
                    clickUserEntity.setClicks(clickUserEntity.getClicks()+1);
                    clickUserRepository.update(clickUserEntity);
                }
            }
            return new SuccessApiResult(community);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查看我已报名的活动列表
     * ModifyBy:
     */
    @Override
    public ApiResult getMyActivities(UserInfoEntity user, WebPage page) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        try {
            List<CommunityHomeListDto> community=new ArrayList<CommunityHomeListDto>();
            List<Object[]> communities=communityRespository.getMyActivities(user.getUserId(), page);
            if(communities.size()>0) {
                for (Object[] activity: communities) {
                    CommunityHomeListDto communityDto = new CommunityHomeListDto();
                    communityDto.setId((String)activity[0]);//活动id
                    communityDto.setPublishDate((String) activity[1]);//活动时间
                    communityDto.setScope((String) activity[2]);//活动范围
                    communityDto.setTitle((String) activity[4]);//活动标题
                    communityDto.setActivityEndDate((String) activity[5]);//活动结束时间
                    communityDto.setApplyStartTime((String) activity[6]);//报名开始时间
                    communityDto.setApplyEndTime((String)activity[7]);//报名结束时间
                    List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById((String)activity[0]);
                    if(imageList.size()>0){
                        communityDto.setSrc(imageList.get(0).getUrl());//活动图片
                    }
                    CommunityCancelEntity cancelInfo = communityRespository.getCancelInfo(user.getUserId());
                    if(cancelInfo!=null && !StringUtil.isEmpty(cancelInfo.getCancelNumber())){
                        communityDto.setCancelNumber(cancelInfo.getCancelNumber());//取消次数
                    }
                    community.add(communityDto);
                }
            }
            return new SuccessApiResult(community);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * 获取活动详情
     * Describe:
     * 通过活动id获取详情信息
     * param id:活动id
     * ModifyBy:
     */
    @Override
    public ApiResult getActivityInfo(UserInfoEntity user,String id) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        try {
            CommunityHomeLastestResDto communityDTO=new CommunityHomeLastestResDto();
            List<HouseInfoEntity> list = houseInfoRepository.getHouseByUserMemberId(user.getId());
            List<String> userHouseAddress = new ArrayList<>();
            for (HouseInfoEntity houseInfoEntity : list) {
                if (houseInfoEntity != null) {
                    userHouseAddress.add(houseInfoEntity.getAddress());
                }
            }
            communityDTO.setUserHouseAddress(userHouseAddress);
            //获取活动详情信息
            CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(id, null);

            if(activityInfo!=null){
                Map<String,Object> resuleMap = new HashMap<>();
                String projectCodes = "";
                String projectNames = "";
                String cityName="";
                List<Map<String, Object>> mapList = communityRespository.queryProjectByActivityId(id);
                if (mapList.size() > 0){
                    for (int i = 0; i < mapList.size(); i++){
                        Map<String, Object> map = mapList.get(i);
                        projectCodes = projectCodes + (String) map.get("projectCode") + ",";
                        projectNames = projectNames + (String) map.get("projectName") + ",";
                        cityName=(String) map.get("cityName");
                    }
                }
                if(cityName.equals("全部城市")){
                    activityInfo.setScope("全部项目");
                }else{
                    projectNames= projectNames.substring(0,projectNames.length()-1);
                    activityInfo.setScope(cityName+":"+projectNames);
                }
               /* resuleMap.put("projectCodes",projectCodes);
                resuleMap.put("projectNames",projectNames);
                resuleMap.put("cityName",cityName);*/

                if(activityInfo.getPeople()==null){
                    activityInfo.setPeople(0);
                }
                if(activityInfo.getHeadCount()==null){
                    activityInfo.setHeadCount(0);
                }
                communityRespository.updateActivityInfo(activityInfo);
                communityDTO.setContent(activityInfo.getActivityDesc());//活动内容
                communityDTO.setPublishDate(activityInfo.getActivityDate());//活动时间
                communityDTO.setTitle(activityInfo.getTitle());//活动标题
                communityDTO.setActivityEndDate(activityInfo.getActivityEndDate());//活动结束时间
                communityDTO.setApplyStartTime(activityInfo.getApplyStartTime());//报名开始时间
                communityDTO.setApplyEndTime(activityInfo.getApplyEndTime());//报名结束时间
                communityDTO.setApplyInfo(activityInfo.getApplyInfo());//用户填写信息字段
                communityDTO.setAddress(activityInfo.getAddress());//活动地址
                communityDTO.setScope(activityInfo.getScope());//活动范围
                communityDTO.setPhone(activityInfo.getHotline());//活动电话
                List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activityInfo.getActivityId());
                if(imageList.size()>0){
                    communityDTO.setSrc(imageList.get(0).getUrl());//活动图片
                }
                CommunityActivityApplyInfoEntity apply=communityRespository.getUserApplyInfo(activityInfo.getActivityId(),user.getUserId());
               // communityRespository.updateApplyInfo();
                if(apply!=null){
                    communityDTO.setApplyInfo(apply.getApplyInfo());
                    if (StringUtils.isNullOrEmpty(apply.getApplyPhone())) {
                        communityDTO.setApplyPhone(user.getMobile());
                    }else{
                        communityDTO.setApplyPhone(apply.getApplyPhone());
                    }
                    communityDTO.setApplyCount(apply.getApplyCount());
                    //新增报名联系地址
                    communityDTO.setApplyAddress(apply.getApplyAddress());
                    communityDTO.setStatus(apply.getApplyDesc());//报名状态
                }else {
                    communityDTO.setStatus("0");//报名状态
                }
                communityDTO.setUserType(user.getUserType());//用户类型
                communityDTO.setDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                Integer headCount = activityInfo.getHeadCount();
                if (headCount == null) {
                    headCount = 0;
                }
                communityDTO.setHeadCount(headCount.toString());//预报名总人数
                communityDTO.setApplyMaxNum(activityInfo.getApplyMaxNum());//每户报名人数上限
                Integer people = (int)activityInfo.getHeadCount() - (int)activityInfo.getPeople();
                communityDTO.setPeople(people.toString());//剩余人数\

                //List<CommunityActivityInfoEntity> communities=communityRespository.getOpenActivitiesByProjectId(page, projectCode);

              /*  UserActivityScopeEntity userActivityScopeEntity=userActivityScopeRepository.get(user.getUserId(),"");
                if(userActivityScopeEntity!=null){
                    userActivityScopeEntity.setActivityId(id);
                    userActivityScopeRepository.update(userActivityScopeEntity);
                }else{
                    UserActivityScopeEntity serActivityScopeEntity=new UserActivityScopeEntity();
                    serActivityScopeEntity.setUserId(user.getUserId());
                    serActivityScopeEntity.setActivityId(id);
                    serActivityScopeEntity.setReaded("1");//已读
                    userActivityScopeRepository.create(serActivityScopeEntity);
                }*/
            }
            //增加用户日志记录
            UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
            userVisitLogEntity.setLogId(IdGen.uuid());
            userVisitLogEntity.setLogTime(new Date());//注册日期
            userVisitLogEntity.setUserName(user.getNickName());//用户昵称
            userVisitLogEntity.setUserType(user.getUserType());//用户类型
            userVisitLogEntity.setUserMobile(user.getMobile());//手机号
            userVisitLogEntity.setSourceFrom(user.getSourceType());//来源
            userVisitLogEntity.setThisSection("服务");
            userVisitLogEntity.setThisFunction("活动报名");
            userVisitLogEntity.setLogContent(activityInfo.getTitle());
            if(user!=null){
                UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
                if (null != userSettingEntity) {
                    userVisitLogEntity.setProjectId(userSettingEntity.getProjectName());
                }
            }else{
                userVisitLogEntity.setProjectId("");//项目
            }

            systemLogRepository.addUserVisitLog(userVisitLogEntity);
            return new SuccessApiResult(communityDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取活动详情(游客)
     */
    @Override
    public ApiResult getActivityInfoByVisitor(String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        try {
            CommunityHomeLastestResDto communityDTO=new CommunityHomeLastestResDto();
            //获取活动详情信息
            CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(id, null);

            if(activityInfo!=null){
                Map<String,Object> resuleMap = new HashMap<>();
                String projectCodes = "";
                String projectNames = "";
                String cityName="";
                List<Map<String, Object>> mapList = communityRespository.queryProjectByActivityId(id);
                if (mapList.size() > 0){
                    for (int i = 0; i < mapList.size(); i++){
                        Map<String, Object> map = mapList.get(i);
                        projectCodes = projectCodes + (String) map.get("projectCode") + ",";
                        projectNames = projectNames + (String) map.get("projectName") + ",";
                        cityName=(String) map.get("cityName");
                    }
                }
                if(cityName.equals("全部城市")){
                    activityInfo.setScope("全部项目");
                }else{
                    projectNames= projectNames.substring(0,projectNames.length()-1);
                    activityInfo.setScope(cityName+":"+projectNames);
                }

                if(activityInfo.getPeople()==null){
                    activityInfo.setPeople(0);
                }
                if(activityInfo.getHeadCount()==null){
                    activityInfo.setHeadCount(0);
                }
                communityRespository.updateActivityInfo(activityInfo);
                communityDTO.setContent(activityInfo.getActivityDesc());//活动内容
                communityDTO.setPublishDate(activityInfo.getActivityDate());//活动时间
                communityDTO.setTitle(activityInfo.getTitle());//活动标题
                communityDTO.setActivityEndDate(activityInfo.getActivityEndDate());//活动结束时间
                communityDTO.setApplyStartTime(activityInfo.getApplyStartTime());//报名开始时间
                communityDTO.setApplyEndTime(activityInfo.getApplyEndTime());//报名结束时间
                communityDTO.setApplyInfo(activityInfo.getApplyInfo());//用户填写信息字段
                communityDTO.setAddress(activityInfo.getAddress());//活动地址
                communityDTO.setScope(activityInfo.getScope());//活动范围
                communityDTO.setPhone(activityInfo.getHotline());//活动电话
                List<CommunityImageInfoEntity> imageList=communityRespository.getCommunityImageById(activityInfo.getActivityId());
                if(imageList.size()>0){
                    communityDTO.setSrc(imageList.get(0).getUrl());//活动图片
                }
                communityDTO.setStatus("0");//报名状态

                communityDTO.setUserType("1");//用户类型
                communityDTO.setDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                Integer headCount = activityInfo.getHeadCount();
                if (headCount == null) {
                    headCount = 0;
                }
                communityDTO.setHeadCount(headCount.toString());//预报名总人数
                communityDTO.setApplyMaxNum(activityInfo.getApplyMaxNum());//每户报名人数上限
                Integer people = (int)activityInfo.getHeadCount() - (int)activityInfo.getPeople();
                communityDTO.setPeople(people.toString());//剩余人数\
            }
            //增加用户日志记录
            UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
            userVisitLogEntity.setLogId(IdGen.uuid());
            userVisitLogEntity.setLogTime(new Date());//注册日期
//            userVisitLogEntity.setUserName("");//用户昵称
            userVisitLogEntity.setUserType("1");//用户类型
//            userVisitLogEntity.setUserMobile("");//手机号
            userVisitLogEntity.setSourceFrom("APP");//来源
            userVisitLogEntity.setThisSection("服务");
            userVisitLogEntity.setThisFunction("活动报名");
            userVisitLogEntity.setLogContent(activityInfo.getTitle());
            userVisitLogEntity.setProjectId("");//项目
            systemLogRepository.addUserVisitLog(userVisitLogEntity);

            return new SuccessApiResult(communityDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    //删除最新活动
    @Override
    public ApiResult delActivityNewInfo(UserInfoEntity user, String id) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        try {
                UserActivityScopeEntity userActivityScopeEntity=userActivityScopeRepository.get(user.getUserId(),"");
                if(userActivityScopeEntity!=null){
                    userActivityScopeEntity.setActivityId(id);
                    userActivityScopeRepository.update(userActivityScopeEntity);
                }else{
                    UserActivityScopeEntity serActivityScopeEntity=new UserActivityScopeEntity();
                    serActivityScopeEntity.setUserId(user.getUserId());
                    serActivityScopeEntity.setActivityId(id);
                    serActivityScopeEntity.setReaded("1");//已读
                    userActivityScopeRepository.create(serActivityScopeEntity);
                }
            return new SuccessApiResult("");
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 活动报名
     * param id:活动id
     * param user:用户信息
     * ModifyBy:
     */
    @Override
    public ApiResult getApply(UserInfoEntity user, HomeShareCommunityDto obj) throws GeneralException {
        String id=obj.getId();//活动id
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        try {
            CommunityCancelEntity cancelInfo=communityRespository.getCancelInfo(user.getUserId());
            List<HouseInfoEntity> houseInfoList = houseInfoRepository.getHouseByUserMemberId(user.getId());
            CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(id, null);
            if (activityInfo != null) {
                String types = activityInfo.getTypes();//1公开，2私有
                if("2".equals(types)) {
                    //私有
                    //UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
//                    String projectCodes="";
//                    String projectNames="";
                    if(houseInfoList.size()>0){
                        //标示-活动报名项目范围是否包含业主所在项目,0-不包含,1-包含
                        int f = 0;
                        for (HouseInfoEntity houseInfoEntity : houseInfoList) {
                            if (houseInfoEntity != null) {
                                List<Map<String, Object>> mapList = communityRespository.queryProjectByActivityId(id);
                                if (mapList.size() > 0){
                                    for (int i = 0; i < mapList.size(); i++){
                                        Map<String, Object> map = mapList.get(i);
                                        if (houseInfoEntity.getProjectNum().equals(map.get("projectCode"))){
                                            f = 1;
                                        }
                                    }
                                }
                            }
                        }
                        if (f == 0){
                            //不能报名,不在项目范围内
                            return ErrorResource.getError("tip_pe00000036");//活动不在项目报名范围内
                        }
                    }else{
                        //houserInfo为空,非业主,不允许报名
                        return ErrorResource.getError("tip_pe00000037");
                    }
                }
            }else {
                //无该活动信息，不可报名
                return ErrorResource.getError("tip_pe00000037");
            }
            if(activityInfo!=null){
                //校验报名总人数
//                if(activityInfo.getPeople().equals(activityInfo.getHeadCount())){
//                    return ErrorResource.getError("tip_pe00000029");//报名人数已满
//                }
                if (null != activityInfo.getPeople()){
                    if (Integer.valueOf(obj.getApplyCount()) > (activityInfo.getHeadCount()-activityInfo.getPeople())){
                        return ErrorResource.getError("tip_pe00000029");//报名人数已满
                    }
                }
                CommunityActivityApplyInfoEntity apply=communityRespository.getUserApplyInfo(id, user.getUserId());
                if(apply==null) {
                    //新增报名，增加报名人数，和报名人联系电话,
                    CommunityActivityApplyInfoEntity applyInfoEntity = new CommunityActivityApplyInfoEntity();
                    applyInfoEntity.setApplyId(IdGen.getNanaTimeID());
                    applyInfoEntity.setActivityId(id);
                    applyInfoEntity.setUserId(user.getUserId());
                    applyInfoEntity.setMakedate(new java.sql.Date(new Date().getTime()));
                    applyInfoEntity.setApplyDesc("1");
                    applyInfoEntity.setApplyCount(obj.getApplyCount());//报名人数
                    applyInfoEntity.setApplyPhone(obj.getApplyPhone());//联系电话
                    applyInfoEntity.setApplyAddress(obj.getApplyAddress());//联系地址
                    if(null != obj.getApplyInfo() && !"".equals(obj.getApplyInfo())){
                        applyInfoEntity.setApplyInfo(obj.getApplyInfo());
                    }else{
                        applyInfoEntity.setApplyInfo(null);
                    }
                    applyInfoEntity.setReadStatus("0");//是否已读标记

                    //通过活动id在community_activityInfo_scope中拿到所有项目
                    List<CommunityActivityInfoScopeEntity> list = communityRespository.getAllActivityProject(id);
                    List<SMSPeopleAlertsEntity> smsPeopleAlertsEntityList = new ArrayList<>();
                    for (CommunityActivityInfoScopeEntity communityActivityInfoScopeEntity : list) {
                        String projectName = communityActivityInfoScopeEntity.getProjectId();
                        //发送短信消息提醒
                        smsPeopleAlertsEntityList.addAll(smsAlertsService.getAllByModel(projectName, "活动报名管理"));
                    }
                    //同一个人，如果是不同项目，相同模块，选择一个，相同的name、phone、model
                    for (int i=0; i<smsPeopleAlertsEntityList.size()-1; i++) {
                        SMSPeopleAlertsEntity smsPeopleAlertsEntity = smsPeopleAlertsEntityList.get(i);
                        for (int j=smsPeopleAlertsEntityList.size()-1; j>i; j--) {
                            SMSPeopleAlertsEntity smsPeopleAlertsEntity1 = smsPeopleAlertsEntityList.get(j);
                            if ((smsPeopleAlertsEntity.getName()).equals(smsPeopleAlertsEntity1.getName()) && (smsPeopleAlertsEntity.getPhone()).equals(smsPeopleAlertsEntity1.getPhone()) && (smsPeopleAlertsEntity.getModel()).equals(smsPeopleAlertsEntity1.getModel())) {
                                smsPeopleAlertsEntityList.remove(smsPeopleAlertsEntity1);
                            }
                        }
                    }
                    for (SMSPeopleAlertsEntity smsPeopleAlertsEntity : smsPeopleAlertsEntityList) {
                        String userName = "";
                        if (!StringUtils.isNullOrEmpty(user.getRealName())) {
                            userName = user.getRealName();
                            smsPeopleAlertsEntity.setContent(smsPeopleAlertsEntity.getContent()+",业主姓名:"+userName+" ,活动标题:"+activityInfo.getTitle()+" ,联系电话:"+applyInfoEntity.getApplyPhone()+"。");
                        }else {
                            smsPeopleAlertsEntity.setContent(smsPeopleAlertsEntity.getContent()+" ,活动标题:"+activityInfo.getTitle()+" ,联系电话:"+applyInfoEntity.getApplyPhone()+"。");
                        }
                        smsAuthService.sendSMSAlerts(smsPeopleAlertsEntity);
                    }

                    communityRespository.saveApplyInfo(applyInfoEntity);
                }else{
                    //修改报名,
                    apply.setApplyDesc("1");
                    apply.setMakedate(new java.sql.Date(new Date().getTime()));
                    apply.setApplyCount(obj.getApplyCount());//报名人数
                    apply.setApplyPhone(obj.getApplyPhone());//联系电话
                    apply.setApplyAddress(obj.getApplyAddress());//联系地址
                    apply.setApplyInfo(obj.getApplyInfo());
                    apply.setReadStatus("0");//是否已读标记

                    //通过活动id在community_activityInfo_scope中拿到所有项目
                    List<CommunityActivityInfoScopeEntity> list = communityRespository.getAllActivityProject(id);
                    List<SMSPeopleAlertsEntity> smsPeopleAlertsEntityList = new ArrayList<>();
                    for (CommunityActivityInfoScopeEntity communityActivityInfoScopeEntity : list) {
                        String projectName = communityActivityInfoScopeEntity.getProjectId();
                        //发送短信消息提醒
                        smsPeopleAlertsEntityList.addAll(smsAlertsService.getAllByModel(projectName, "活动报名管理"));
                    }
                    //同一个人，如果是不同项目，相同模块，选择一个，相同的name、phone、model
                    for (int i=0; i<smsPeopleAlertsEntityList.size()-1; i++) {
                        SMSPeopleAlertsEntity smsPeopleAlertsEntity = smsPeopleAlertsEntityList.get(i);
                        for (int j=smsPeopleAlertsEntityList.size()-1; j>i; j--) {
                            SMSPeopleAlertsEntity smsPeopleAlertsEntity1 = smsPeopleAlertsEntityList.get(j);
                            if ((smsPeopleAlertsEntity.getName()).equals(smsPeopleAlertsEntity1.getName()) && (smsPeopleAlertsEntity.getPhone()).equals(smsPeopleAlertsEntity1.getPhone()) && (smsPeopleAlertsEntity.getModel()).equals(smsPeopleAlertsEntity1.getModel())) {
                                smsPeopleAlertsEntityList.remove(smsPeopleAlertsEntity1);
                            }
                        }
                    }

                    for (SMSPeopleAlertsEntity smsPeopleAlertsEntity : smsPeopleAlertsEntityList) {
                        String userName = "";
                        if (!StringUtils.isNullOrEmpty(user.getRealName())) {
                            userName = user.getRealName();
                            smsPeopleAlertsEntity.setContent(smsPeopleAlertsEntity.getContent()+",业主姓名:"+userName+" ,活动标题:"+activityInfo.getTitle()+" ,联系电话:"+apply.getApplyPhone()+"。");
                        }else {
                            smsPeopleAlertsEntity.setContent(smsPeopleAlertsEntity.getContent()+" ,活动标题:"+activityInfo.getTitle()+" ,联系电话:"+apply.getApplyPhone()+"。");

                        }
                        smsAuthService.sendSMSAlerts(smsPeopleAlertsEntity);
                    }
                    communityRespository.updateApplyInfo(apply);
                }
                //查询已报名的信息,活动id去查询活动报名
                /*
                //这块他妈的坑啊
                List<CommunityActivityApplyInfoEntity> applyInfo=communityRespository.getApplyInfo(id);
                if(applyInfo.size()==0) {
                    //修改活动主表
                    activityInfo.setPeople(applyInfo.size() + 1);
                    activityInfo.setPeople(activityInfo.getHeadCount() - obj.getApplyCount());
                    activityInfo.setApplyCount(applyInfo.size() + 1);
                    //activityInfo.setApplyInfo(obj.getApplyInfo());
                    //obj.getApplyCount()

                    communityRespository.updateActivityInfo(activityInfo);
                }else{
                    //修改活动主表
//                    activityInfo.setPeople(applyInfo.size());
//                    activityInfo.setPeople(activityInfo.getHeadCount()-obj.getApplyCount());
                    activityInfo.setPeople(applyInfo.size());
                    activityInfo.setApplyCount(applyInfo.size());
                    //activityInfo.setApplyInfo(obj.getApplyInfo());
                    communityRespository.updateActivityInfo(activityInfo);
                }
                */
                if (null != activityInfo.getPeople() && activityInfo.getPeople() != 0){
                    activityInfo.setPeople(activityInfo.getPeople() + obj.getApplyCount());
                    activityInfo.setApplyCount(activityInfo.getApplyCount() + obj.getApplyCount());
                }else{
                    activityInfo.setPeople(obj.getApplyCount());
                    activityInfo.setApplyCount(obj.getApplyCount());
                }
                communityRespository.updateActivityInfo(activityInfo);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), "活动报名成功！");//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 活动取消
     * param id:活动id
     * param user:用户信息
     * ModifyBy:
     */
    @Override
    public ApiResult getCancel(UserInfoEntity user, String id) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        try {
            CommunityActivityInfoEntity activityInfo=communityRespository.getCommuntiyActivityInfo(id, null);
            if(activityInfo!=null) {
                CommunityCancelEntity cancelInfo = communityRespository.getCancelInfo(user.getUserId());
                if (cancelInfo != null && !StringUtil.isEmpty(cancelInfo.getCancelNumber())) {
                   /* if (cancelInfo.getCancelNumber().equals("3")) {
                        return ErrorResource.getError("tip_pe00000028");//取消次数已过3次
                    }*/
                    int num=Integer.parseInt(cancelInfo.getCancelNumber());
                    cancelInfo.setCancelNumber(String.valueOf(num+1));
                    cancelInfo.setCreateDate(new Date());
                    communityRespository.updateCancle(cancelInfo);
                }else{
                    CommunityCancelEntity communityCancel=new CommunityCancelEntity();
                    communityCancel.setId(IdGen.uuid());
                    communityCancel.setCreateDate(new Date());
                    communityCancel.setCreateBy(user.getUserId());
                    communityCancel.setCancelNumber("1");
                    //保存取消表
                    communityRespository.saveCancel(communityCancel);
                }
                //修改报名表
                CommunityActivityApplyInfoEntity apply=communityRespository.getUserApplyInfo(id,user.getUserId());
                if(apply!=null && apply.getApplyDesc().equals("1")){
                    apply.setApplyDesc("0");
                    communityRespository.updateApplyInfo(apply);
                }
                //查询已报名的信息,为什么这么写?直接取到值减去该用户报名人数即可
//                List<CommunityActivityApplyInfoEntity> applyInfo = communityRespository.getApplyInfo(id);
//                activityInfo.setPeople(applyInfo.size());

                activityInfo.setPeople(activityInfo.getPeople() - apply.getApplyCount());
                activityInfo.setApplyCount(activityInfo.getApplyCount() - apply.getApplyCount());
                //修改活动主表
                communityRespository.updateActivityInfo(activityInfo);
                /*CommunityActivityApplyInfoEntity applies=communityRespository.getUserApplyInfo(id,user.getUserId());
                if(applies!=null && applies.getApplyDesc().equals("0")){
                    applies.setApplyDesc("1");
                    communityRespository.updateApplyInfo(applies);
                }*/
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), "活动已取消！");
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    @Override
    public Map<String, Object> queryByActivityId(String activityId) {
        Map<String,Object> resuleMap = new HashMap<>();
        String projectCodes = "";
        String projectNames = "";
        List<Map<String, Object>> mapList = communityRespository.queryProjectByActivityId(activityId);
        if (mapList.size() > 0){
            for (int i = 0; i < mapList.size(); i++){
                Map<String, Object> map = mapList.get(i);
                projectCodes = projectCodes + (String) map.get("projectCode") + ",";
                projectNames = projectNames + (String) map.get("projectName") + ",";
            }
        }
        resuleMap.put("projectCodes",projectCodes);
        resuleMap.put("projectNames",projectNames);
        return resuleMap;
    }

    @Override
    public Map queryProjectByActivityId(String activityId) {
        Map<String,String> projects = new LinkedHashMap<>();
        List<Map<String, Object>> mapList = communityRespository.queryProjectByActivityId(activityId);
        projects.put("0","--请选择项目--");
        if (mapList.size() > 0){
            for (int i = 0; i < mapList.size(); i++){
                Map<String, Object> map = mapList.get(i);
                projects.put((String) map.get("projectCode"),(String) map.get("projectName"));
            }
        }
        return projects;
    }

    /**
     *  @Author: shanshan
     *  @Description: 获取个人活动消息列表
     */
    @Override
    public List getActivityPersonalMessag(UserInfoEntity user, String visit) {
        String userId = user.getUserId();
        List<CommunityActivityInfoEntity> communityActivityInfoEntityList = new ArrayList<>();
        //获取该用户已报名的活动开始时间早于报名开始时间的全部活动
        //发送消息的时间是活动结束时间前24小时
        List<CommunityActivityInfoEntity> list = communityRespository.getActivityByEndDate(user.getUserId());
        communityActivityInfoEntityList.addAll(list);

        //获取该用户已报名的活动开始时间晚于报名开始时间的全部活动
        //发送消息的时间是活动开始时间前24小时
        List<CommunityActivityInfoEntity> list1 = communityRespository.getActivityByStartDate(user.getUserId());
        communityActivityInfoEntityList.addAll(list1);

        /*//排序
        Collections.sort(communityActivityInfoEntityList, new Comparator<CommunityActivityInfoEntity>() {
            @Override
            public int compare(CommunityActivityInfoEntity o1, CommunityActivityInfoEntity o2) {
                Long time1 = 0L;
                Long time2 = 0L;
                if (!StringUtils.isNullOrEmpty(o1.getActivityDate())) {
                    time1 = DateUtils.parse(o1.getActivityDate()).getTime();
                }
                if (!StringUtils.isNullOrEmpty(o2.getActivityDate())) {
                    time2 = DateUtils.parse(o2.getActivityDate()).getTime();
                }
                if (time1 > time2) {
                    return 1;
                }else if (time1 < time2) {
                    return -1;
                }
                return 0;
            }
        });*/

        List<CommunityActivityMessageDto> communityActivityMessageDtoList = new ArrayList<>();
        for (CommunityActivityInfoEntity communityActivityInfoEntity : communityActivityInfoEntityList) {
            CommunityActivityMessageDto communityActivityMessageDto = new CommunityActivityMessageDto();
            communityActivityMessageDto.setActivityId(communityActivityInfoEntity.getActivityId());
            communityActivityMessageDto.setTitle(communityActivityInfoEntity.getTitle());
            communityActivityMessageDto.setActivityDate(communityActivityInfoEntity.getActivityDate());
            communityActivityMessageDto.setActivityEndDate(communityActivityInfoEntity.getActivityEndDate());
            communityActivityMessageDto.setApplyStartTime(communityActivityInfoEntity.getApplyStartTime());
            //查看消息是否已读状态
            CommunityActivityApplyInfoEntity communityActivityApplyInfoEntity = communityRespository.getCommunityApply(userId, communityActivityInfoEntity.getActivityId());
            if (communityActivityApplyInfoEntity!=null) {
                communityActivityMessageDto.setReadStatus(communityActivityApplyInfoEntity.getReadStatus());
            }
            communityActivityMessageDtoList.add(communityActivityMessageDto);
        }

        if(("indexInfo").equals(visit)){
            //        增加日志
            UserVisitLogEntity userVisitLog=new UserVisitLogEntity();
            userVisitLog.setLogId(IdGen.uuid());
            userVisitLog.setLogTime(new Date());//注册日期
            userVisitLog.setUserName(user.getNickName());//用户昵称
            userVisitLog.setUserType(user.getUserType());//用户类型
            userVisitLog.setUserMobile(user.getMobile());//手机号
            userVisitLog.setSourceFrom(user.getSourceType());//来源
            userVisitLog.setThisSection("首页");//板块
            userVisitLog.setThisFunction("首页消息");//功能
            userVisitLog.setLogContent("");//内容
            UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
            if (null != userSettingEntity) {
                userVisitLog.setProjectId(userSettingEntity.getProjectName());
            }else{
                userVisitLog.setProjectId("");
            }
            systemLogRepository.addUserVisitLog(userVisitLog);


        }
        if(("ownInfo").equals(visit)){
            //        增加日志
            UserVisitLogEntity userVisitLog=new UserVisitLogEntity();
            userVisitLog.setLogId(IdGen.uuid());
            userVisitLog.setLogTime(new Date());//注册日期
            userVisitLog.setUserName(user.getNickName());//用户昵称
            userVisitLog.setUserType(user.getUserType());//用户类型
            userVisitLog.setUserMobile(user.getMobile());//手机号
            userVisitLog.setSourceFrom(user.getSourceType());//来源
            userVisitLog.setThisSection("我的");//板块
            userVisitLog.setThisFunction("个人消息");//功能
            userVisitLog.setLogContent("");//内容
            UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
            if (null != userSettingEntity) {
                userVisitLog.setProjectId(userSettingEntity.getProjectName());
            }else{
                userVisitLog.setProjectId("");
            }
            systemLogRepository.addUserVisitLog(userVisitLog);
        }
        return communityActivityMessageDtoList;
    }

    /**
     *  @Author: shanshan
     *  @Description: 个人活动消息已读标记
     */
    @Override
    public ApiResult changeReadStatus(UserInfoEntity user, String activityId) {
        try{
            communityRespository.changeReadStatus(user.getUserId(), activityId);
            return new SuccessApiResult();
        }catch (Exception e) {
            e.printStackTrace();
            return new ErrorApiResult(500,"数据错误，请刷新后重试");
        }
    }

    /**
     * 导出excel
     */
    @Override
    public String exportExcel(ActivityAdminDto activityAdminDto,HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        CommunityActivityInfoEntity communityActivityInfoEntity =new CommunityActivityInfoEntity();
        if (activityAdminDto.getTypes()!=null && !"0".equals(communityActivityInfoEntity.getTypes())){
            communityActivityInfoEntity.setTypes(activityAdminDto.getTypes());
        }
        if (activityAdminDto.getCu_projectId() != null &&!"0".equals(activityAdminDto.getCu_projectId())) {
            communityActivityInfoEntity.setProjectId(activityAdminDto.getCu_projectId());
        }
        if (activityAdminDto.getStatus()!=null&&!"0".equals(activityAdminDto.getStatus())){
            communityActivityInfoEntity.setStatus(activityAdminDto.getStatus());
        }
        if (activityAdminDto.getTitle() != null && !"".equals(activityAdminDto.getTitle())) {
            communityActivityInfoEntity.setTitle(activityAdminDto.getTitle());
        }
        if (activityAdminDto.getScope() != null && !"".equals(activityAdminDto.getScope())) {
            //通过名字去搜索项目对象，获取项目的
            communityActivityInfoEntity.setScope(activityAdminDto.getScope());
        }
        if (activityAdminDto.getOperator() != null && !"".equals(activityAdminDto.getOperator())) {
            communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
        }
        //活动时间假定为开始时间
        if (activityAdminDto.getBeginTime() != null && !"".equals(activityAdminDto.getBeginTime())) {
            communityActivityInfoEntity.setActivityDate(activityAdminDto.getBeginTime());
        }
        //活动内容假定为结束时间
        if (activityAdminDto.getEndTime() != null && !"".equals(activityAdminDto.getEndTime())) {
            communityActivityInfoEntity.setActivityDesc(activityAdminDto.getEndTime());
        }
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = activityAdminDto.getRoleScopeList();
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
        List<CommunityActivityInfoEntity> communityActivityInfoEntities = communityRespository.listActivityInfo(communityActivityInfoEntity, webPage,roleScopes);
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("活动报名统计");
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
        String[] titles = {"序号", "活动主题", "活动时间", "活动地点", "项目", "报名类型", "报名人数", "发布人","发布时间","活动状态"};
        XSSFRow headRow = sheet.createRow(0);

        if (communityActivityInfoEntities.size() > 0) {

            communityActivityInfoEntities.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (communityActivityInfoEntities.size() > 0) {
                    for (int i = 0; i < communityActivityInfoEntities.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        CommunityActivityInfoEntity communityActivityInfo = communityActivityInfoEntities.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(communityActivityInfo.getTitle());//活动主题

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);
                        cell.setCellValue(communityActivityInfo.getActivityDate());//活动时间

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(communityActivityInfo.getAddress());//活动地点

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        Map<String, Object> map=queryProjectByCommunityId(communityActivityInfo.getActivityId());
                        cell.setCellValue(map.get("projectNames").toString());//项目

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if(communityActivityInfo.getTypes().equals("1")){
                            cell.setCellValue("公开");//报名类型
                        }else{
                            cell.setCellValue("私有");//报名类型
                        }

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(communityActivityInfo.getPeople() + "/" + communityActivityInfo.getHeadCount());//报名人数

                        cell = bodyRow.createCell(7);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(communityActivityInfo.getOperator());//发布人

                        cell = bodyRow.createCell(8);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(communityActivityInfo.getMakedate());//发布时间

                        cell = bodyRow.createCell(9);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        if(communityActivityInfo.getPushState().equals("0")){

                            cell.setCellValue("未发布");
                        }
                        if(communityActivityInfo.getPushState().equals("1")){
                            if(communityActivityInfo.getStatus().equals("1")){
                                cell.setCellValue("报名中");
                            }
                            if(communityActivityInfo.getStatus().equals("2")){
                                cell.setCellValue("已报满");
                            }
                            if(communityActivityInfo.getStatus().equals("3")){
                                cell.setCellValue("报名结束");
                            }
                            if(communityActivityInfo.getStatus().equals("4")){
                                cell.setCellValue("活动结束");
                            }
                            if(communityActivityInfo.getStatus().equals("5")){
                                cell.setCellValue("未发布");
                            }

                        }
                    }
                }
            });
        }
        try {
            String fileName = new String(("活动报名列表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("活动报名列表", "UTF8");
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

    /**
     * 导出excel 报名信息
     */
    @Override
    public void applyExportExcel(String title, String[] headers, ServletOutputStream out, ActivityApplyAdminDto activityApplyAdminDto) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setWrapText(true);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));

        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLACK.index);
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //检索活动项目报名信息列表
        List<Map<String, Object>> activityApplys = getApplyList(activityApplyAdminDto, null);
        HSSFCell cell = null;
        for (int i = 0; i < activityApplys.size(); i++) {
            Map<String, Object> activityApply = activityApplys.get(i);
            row = sheet.createRow(i+1);
            //序号
            cell = row.createCell(0);
            cell.setCellStyle(style2);
            cell.setCellValue(i + 1);//序号
            //真实姓名
            cell = row.createCell(1);
            cell.setCellStyle(style2);
            cell.setCellValue(activityApply.get("realName") == null ? "" : activityApply.get("realName").toString());
            //用户名
            cell = row.createCell(2);
            cell.setCellStyle(style2);
            cell.setCellValue(activityApply.get("userName") == null ? "" : activityApply.get("userName").toString());
            //项目
            cell = row.createCell(3);
            cell.setCellStyle(style2);
            cell.setCellValue(activityApply.get("projectName") == null ? "" : activityApply.get("projectName").toString());
            //房产信息
            cell = row.createCell(4);
            cell.setCellStyle(style2);
            cell.setCellValue(activityApply.get("address") == null ? "" : activityApply.get("address").toString());
            //联系方式
            cell = row.createCell(5);
            cell.setCellStyle(style2);
            cell.setCellValue(activityApply.get("applyPhone") == null ? "" : activityApply.get("applyPhone").toString());
            //报名时间
            cell = row.createCell(6);
            cell.setCellStyle(style2);
            cell.setCellValue(activityApply.get("makeDate") == null ? "" : activityApply.get("makeDate").toString());
            //活动主题
            cell = row.createCell(7);
            cell.setCellStyle(style2);
            cell.setCellValue(activityApply.get("title") == null ? "" : activityApply.get("title").toString());
            //报名人数
            cell = row.createCell(8);
            cell.setCellStyle(style2);
            cell.setCellValue(activityApply.get("applyCount") == null ? "" : activityApply.get("applyCount").toString());
        }
        System.out.println("excel导出成功！");
        try {
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 优化活动项目报名信息导出Excel
     */
    @Override
    public void activityProjectApplyExportExcel(String title, String[] headers, ServletOutputStream out, ActivityApplyAdminDto activityApplyAdminDto){
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setWrapText(true);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));

        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLACK.index);
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //检索活动项目报名信息列表
        List<Map<String, Object>> activityApplys = getApplyList(activityApplyAdminDto, null);
        HSSFCell cell = null;
        int num = 0;//计数器
        for (int i = 0; i < activityApplys.size(); i++) {
            Map<String, Object> activityApply = activityApplys.get(i);
            Object applyInfo = activityApply.get("applyInfo");
            if (null != applyInfo && !"".equals(applyInfo)){
                System.out.println("报名信息为:"+applyInfo.toString());
                JSONArray jsonArray = JSONArray.fromObject(applyInfo);
                for (int j = 0,len = jsonArray.size(); j < len; j++){
                    num ++;
                    row = sheet.createRow(num);
                    //序号
                    cell = row.createCell(0);
                    cell.setCellStyle(style2);
                    cell.setCellValue(num);//序号
                    //真实姓名
                    cell = row.createCell(1);
                    cell.setCellStyle(style2);
                    cell.setCellValue(activityApply.get("realName") == null ? "" : activityApply.get("realName").toString());
                    //用户名
                    cell = row.createCell(2);
                    cell.setCellStyle(style2);
                    cell.setCellValue(activityApply.get("userName") == null ? "" : activityApply.get("userName").toString());
                    //项目
                    cell = row.createCell(3);
                    cell.setCellStyle(style2);
                    cell.setCellValue(activityApply.get("projectName") == null ? "" : activityApply.get("projectName").toString());
                    //房产信息
                    cell = row.createCell(4);
                    cell.setCellStyle(style2);
                    cell.setCellValue(activityApply.get("address") == null ? "" : activityApply.get("address").toString());
                    //联系方式
                    cell = row.createCell(5);
                    cell.setCellStyle(style2);
                    cell.setCellValue(activityApply.get("applyPhone") == null ? "" : activityApply.get("applyPhone").toString());
                    //报名时间
                    cell = row.createCell(6);
                    cell.setCellStyle(style2);
                    cell.setCellValue(activityApply.get("makeDate") == null ? "" : activityApply.get("makeDate").toString());
                    //活动主题
                    cell = row.createCell(7);
                    cell.setCellStyle(style2);
                    cell.setCellValue(activityApply.get("title") == null ? "" : activityApply.get("title").toString());
                    //报名人数
                    cell = row.createCell(8);
                    cell.setCellStyle(style2);
//                    cell.setCellValue(activityApply.get("applyCount") == null ? "" : activityApply.get("applyCount").toString());
                    cell.setCellValue("1");
                    //时间段
                    cell = row.createCell(9);
                    cell.setCellStyle(style2);
                    cell.setCellValue(activityApply.get("applyTimeRange") == null ? "" : activityApply.get("applyTimeRange").toString());
                    //报名信息
                    JSONArray jSONArray2 = (JSONArray) jsonArray.get(j);
                    for (int m = 0,len2 = jSONArray2.size();m<len2;m++){
                        JSONObject jsonObject = (JSONObject) jSONArray2.get(m);
                        Object value = jsonObject.get("value");
                        if (null != value && !"".equals(value.toString())){
                            if (jsonObject.get("name").toString().contains("图片") || jsonObject.get("name").toString().contains("照片")){
                                try {
                                    System.out.println("图片地址为:"+value.toString());
                                    URL url = new URL(value.toString());
                                    //打开链接
                                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                    //设置请求方式为"GET"
                                    conn.setRequestMethod("GET");
                                    //超时响应时间为5秒
                                    conn.setConnectTimeout(5 * 1000);
                                    //通过输入流获取图片数据
                                    InputStream inStream = conn.getInputStream();
                                    //得到图片的二进制数据，以二进制封装得到数据，具有通用性
                                    byte[] data = readInputStream(inStream);
                                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                            1023, 255, (short)(9+m+1), i+1, (short) (9+m+1), i+1);
                                    //row+1,因为第一行为标题行
                                    anchor.setAnchorType(2);
                                    patriarch.createPicture(anchor, workbook.addPicture(
                                            data, HSSFWorkbook.PICTURE_TYPE_JPEG));
                                    // 有图片时，设置行高为60px;
                                    row.setHeightInPoints(60);
                                    // 设置图片所在列宽度为80px,注意这里单位的一个换算
                                    sheet.setColumnWidth(i, (short) (35.7 * 80));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }else{
                                cell = row.createCell(10+m);
                                cell.setCellStyle(style2);
                                cell.setCellValue(value.toString());
                            }
                        }

                    }
                }
            }else{
                row = sheet.createRow(i+1);
                //序号
                cell = row.createCell(0);
                cell.setCellStyle(style2);
                cell.setCellValue(i + 1);//序号
                //真实姓名
                cell = row.createCell(1);
                cell.setCellStyle(style2);
                cell.setCellValue(activityApply.get("realName") == null ? "" : activityApply.get("realName").toString());
                //用户名
                cell = row.createCell(2);
                cell.setCellStyle(style2);
                cell.setCellValue(activityApply.get("userName") == null ? "" : activityApply.get("userName").toString());
                //项目
                cell = row.createCell(3);
                cell.setCellStyle(style2);
                cell.setCellValue(activityApply.get("projectName") == null ? "" : activityApply.get("projectName").toString());
                //房产信息
                cell = row.createCell(4);
                cell.setCellStyle(style2);
                cell.setCellValue(activityApply.get("address") == null ? "" : activityApply.get("address").toString());
                //联系方式
                cell = row.createCell(5);
                cell.setCellStyle(style2);
                cell.setCellValue(activityApply.get("applyPhone") == null ? "" : activityApply.get("applyPhone").toString());
                //报名时间
                cell = row.createCell(6);
                cell.setCellStyle(style2);
                cell.setCellValue(activityApply.get("makeDate") == null ? "" : activityApply.get("makeDate").toString());
                //活动主题
                cell = row.createCell(7);
                cell.setCellStyle(style2);
                cell.setCellValue(activityApply.get("title") == null ? "" : activityApply.get("title").toString());
                //报名人数
                cell = row.createCell(8);
                cell.setCellStyle(style2);
                cell.setCellValue(activityApply.get("applyCount") == null ? "" : activityApply.get("applyCount").toString());
                //时间段
                cell = row.createCell(9);
                cell.setCellStyle(style2);
                cell.setCellValue(activityApply.get("applyTimeRange") == null ? "" : activityApply.get("applyTimeRange").toString());
            }
        }
        System.out.println("excel导出成功！");
        try {
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * 导出excel    活动项目报名信息
     */
    @Override
    public String activityProjectApply(ActivityApplyAdminDto activityApplyAdminDto, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        List<Map<String, Object>> activityApplys = getApplyList(activityApplyAdminDto, null);

        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("报名信息统计");
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
        String[] titles = {"序号","真实姓名","用户名","项目","房产信息","联系方式","报名时间","活动主题","报名信息","报名人数"};
        XSSFRow headRow = sheet.createRow(0);

        if (activityApplys.size() > 0) {

            activityApplys.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (activityApplys.size() > 0) {
                    for (int i = 0; i < activityApplys.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        Map<String,Object> apply = activityApplys.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        Object realName = apply.get("realName");
                        if (null != realName){
                            cell.setCellValue(realName.toString());//真实姓名
                        }else{
                            cell.setCellValue("");//真实姓名
                        }


                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        Object userName = apply.get("userName");
                        if (null != userName){
                            cell.setCellValue(userName.toString());//用户名
                        }else{
                            cell.setCellValue("");//用户名
                        }

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        Object projectName = apply.get("projectName");
                        if (null != projectName){
                            cell.setCellValue(projectName.toString());//项目
                        }else{
                            cell.setCellValue("");
                        }

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        Object address = apply.get("address");
                        if (null != address){
                            cell.setCellValue(address.toString());//房产信息
                        }else{
                            cell.setCellValue("");//房产信息
                        }

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);
                        Object applyPhone = apply.get("applyPhone");
                        if (null != applyPhone){
                            cell.setCellValue(applyPhone.toString());//联系方式
                        }else{
                            cell.setCellValue("");//联系方式
                        }

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);
                        Object makeDate = apply.get("makeDate");
                        if (null != makeDate){
                            cell.setCellValue(makeDate.toString());//报名时间
                        }else{
                            cell.setCellValue("");//报名时间
                        }

                        cell = bodyRow.createCell(7);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        Object title = apply.get("title");
                        if (null != title){
                            cell.setCellValue(title.toString());//活动主题
                        }else{
                            cell.setCellValue("");//活动主题
                        }

                        cell = bodyRow.createCell(8);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        Object applyInfo = apply.get("applyInfo");
                        if(null != applyInfo && !"null".equals(applyInfo.toString()) && !"".equals(applyInfo.toString())){
                            //此处处理报名信息(Json格式数据解析)
                            String applyInfoStr = "";
                            JSONArray jsonArray = JSONArray.fromObject(applyInfo);
                            for (int j = 0, length = jsonArray.size(); j < length; j++){
                                JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                                Object value = jsonObject.get("value");
                                if (null != value && !"".equals(value.toString())){
                                    applyInfoStr += value.toString()+",";
                                }
                            }
                            cell.setCellValue(applyInfoStr);//报名信息
                        }else{
                            cell.setCellValue("");//报名信息
                        }

                        cell = bodyRow.createCell(9);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        Object applyCount = apply.get("applyCount");
                        if (null != applyCount){
                            cell.setCellValue(applyCount.toString());//报名人数
                        }else{
                            cell.setCellValue("");//报名人数
                        }

                    }
                }
            });
        }
        try {
            /*String fileName = new String(("报名信息列表").getBytes(), "ISO8859-1");*/
            String fileName = new String(("报名信息列表").getBytes(), "ISO8859-1");
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
        return null;
    }

    /**
     * 通过条件检索活动报名信息列表 WeiYangDong_2016-12-19
     */
    @Override
    public List<Map<String,Object>> getApplyList(ActivityApplyAdminDto activityApplyAdminDto,WebPage webPage){
        //封装参数
        Map<String,Object> params = new HashMap<>();
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = activityApplyAdminDto.getRoleScopeList();
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
        params.put("roleScopes",roleScopes);                                //权限范围
        if (null != activityApplyAdminDto.getScopeId() && !"".equals(activityApplyAdminDto.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(activityApplyAdminDto.getScopeId());
            String cityProjects = "";
            for (Object[] project : projectList) {
                if (!cityProjects.contains(project[0].toString())) {
                    cityProjects += "'" + project[0].toString() + "',";
                }
            }
            params.put("cityProjects",cityProjects);                    //城市下所有项目,以城市为单位检索
        }
        params.put("activityId",activityApplyAdminDto.getActivityId());     //活动ID
        params.put("projectCode",activityApplyAdminDto.getProjectCode());   //项目编码
        params.put("userType",activityApplyAdminDto.getUserType());         //用户类型
        params.put("userName",activityApplyAdminDto.getUserName());         //用户名称
        params.put("title",activityApplyAdminDto.getTitle());               //活动标题
        params.put("userMobile",activityApplyAdminDto.getUserMobile());     //联系方式
        params.put("activityDate",activityApplyAdminDto.getActivityDate()); //活动时间
        params.put("applyDate",activityApplyAdminDto.getApplyDate());       //报名时间

        //执行查询
        List<Map<String, Object>> applyList = communityRespository.getApplyList(params, webPage);
        return applyList;
    }

    /**
     * 通过活动ID获取活动发布项目范围
     */
    @Override
    public List<CommunityActivityInfoScopeEntity> getActivityScopeList(String id){
        //通过活动id在community_activityInfo_scope中拿到所有项目
        List<CommunityActivityInfoScopeEntity> list = communityRespository.getAllActivityProject(id);
        return list;
    }

    /**
     * 保存活动发布范围
     */
    @Override
    public void saveHouseScope(ActivityAdminDto activityAdminDto){
        CommunityActivityInfoEntity communityActivityInfoEntity = communityActivityInfoEntity = communityRespository.getCommuntiyActivityInfo(activityAdminDto.getActivityId(), null);
        if(null != communityActivityInfoEntity){
            if (null != activityAdminDto.getHouseScope() && !"".equals(activityAdminDto.getHouseScope())){
                communityActivityInfoEntity.setIsHouseScope(1);
                communityActivityInfoEntity.setHouseScope(activityAdminDto.getHouseScope());
                communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
            }else{
                communityActivityInfoEntity.setIsHouseScope(0);
                communityActivityInfoEntity.setHouseScope(null);
                communityActivityInfoEntity.setOperator(activityAdminDto.getOperator());
            }
            communityRespository.updateActivity(communityActivityInfoEntity);
        }
    }

    /**
     * 通过活动房产发布范围获取房产范围树形数据结构
     */
    @Override
    public String getScopeTreeByHouseScope(ActivityAdminDto activityAdminDto){
        String resultStr = "[";
        //通过项目获取地块
        List<HouseAreaEntity> houseAreaEntities = houseBuildingRepository.getAreaListByProjectNum(activityAdminDto.getProjectCode());
        HouseAreaEntity houseAreaEntity = null;
        for (int a = 0,al = houseAreaEntities.size(); a < al; a++){
            houseAreaEntity = houseAreaEntities.get(a);
            String aStr = getTreeNodeId(a+1);
            String areaTreeNodeJson = "";
            if (null != activityAdminDto.getHouseScope() && activityAdminDto.getHouseScope().contains(houseAreaEntity.getId())){
                areaTreeNodeJson = getTreeNodeJson(aStr, "0", "地块_"+houseAreaEntity.getName(), houseAreaEntity.getId(), true,true,true);
            }else{
                areaTreeNodeJson = getTreeNodeJson(aStr, "0", "地块_"+houseAreaEntity.getName(), houseAreaEntity.getId(), true,true,false);
            }
            resultStr += areaTreeNodeJson + ",";
            //通过地块获取楼栋
            List<HouseBuildingEntity> houseBuildingEntities = houseBuildingRepository.getBuildListByBlockNum(houseAreaEntity.getBlockCode());
            HouseBuildingEntity houseBuildingEntity = null;
            for(int b = 0,bl = houseBuildingEntities.size();b < bl;b++){
                houseBuildingEntity = houseBuildingEntities.get(b);
                String bStr = getTreeNodeId(b+1);
                String buildingTreeNodeJson = "";
                if (null != activityAdminDto.getHouseScope() && activityAdminDto.getHouseScope().contains(houseBuildingEntity.getId())){
                    buildingTreeNodeJson = getTreeNodeJson(aStr+""+bStr, String.valueOf(aStr), "楼栋_"+houseBuildingEntity.getBuildingRecord(), houseBuildingEntity.getId(), true,true,true);
                }else{
                    buildingTreeNodeJson = getTreeNodeJson(aStr+""+bStr, String.valueOf(aStr), "楼栋_"+houseBuildingEntity.getBuildingRecord(), houseBuildingEntity.getId(), true,true,false);
                }
                resultStr += buildingTreeNodeJson+ ",";
                //通过楼栋获取单元
                List<HouseUnitEntity> houseUnitEntities = houseUnitRepository.getListByBuildingNum(houseBuildingEntity.getBuildingNum());
                HouseUnitEntity houseUnitEntity = null;
                for(int c = 0,cl = houseUnitEntities.size();c < cl;c++){
                    houseUnitEntity = houseUnitEntities.get(c);
                    String unitTreeNodeJson = "";
                    if (null != activityAdminDto.getHouseScope() && activityAdminDto.getHouseScope().contains(houseUnitEntity.getId())){
                        unitTreeNodeJson = getTreeNodeJson(aStr+""+bStr+""+(c+1), aStr+""+bStr, "单元_"+houseUnitEntity.getName(), houseUnitEntity.getId(), true,false,true);
                    }else{
                        unitTreeNodeJson = getTreeNodeJson(aStr+""+bStr+""+(c+1), aStr+""+bStr, "单元_"+houseUnitEntity.getName(), houseUnitEntity.getId(), true,false,false);
                    }
                    resultStr += unitTreeNodeJson+ ",";
                    //通过单元获取房屋
                    List<HouseInfoEntity> houseInfoEntities = houseInfoRepository.getInfoByUnitIdOrderBy(houseUnitEntity.getId());
                    HouseInfoEntity houseInfoEntity = null;
                    for(int d = 0,dl=houseInfoEntities.size();d < dl;d++){
                        houseInfoEntity = houseInfoEntities.get(d);
                        String houseTreeNodeJson = "";
                        if (null != activityAdminDto.getHouseScope() && activityAdminDto.getHouseScope().contains(houseInfoEntity.getRoomId())){
                            houseTreeNodeJson = getTreeNodeJson(aStr+""+bStr+""+(c+1)+""+(d+1), aStr+""+bStr+""+(c+1), houseInfoEntity.getAddress(), houseInfoEntity.getRoomId(), false,false,true);
                        }else{
                            houseTreeNodeJson = getTreeNodeJson(aStr+""+bStr+""+(c+1)+""+(d+1), aStr+""+bStr+""+(c+1), houseInfoEntity.getAddress(), houseInfoEntity.getRoomId(), false,false,false);
                        }
                        resultStr += houseTreeNodeJson+ ",";
                    }
                }
            }
        }
        return resultStr+"]";
    }

    public String getTreeNodeJson(String id,String pid,String name,String value,boolean isParent,boolean open,boolean checked){
        ZtreeNodeDTO ztreeNodeDTO = new ZtreeNodeDTO();
        ztreeNodeDTO.setId(id);
        ztreeNodeDTO.setPid(pid);
        ztreeNodeDTO.setValue(value);
        if (name.contains("null")){
            if (value.contains("#G")){
                //公共区域
                ztreeNodeDTO.setName(name.replace("null","公共区域"));
            }else if (value.contains("#")){
                //无
                ztreeNodeDTO.setName(name.replace("null","无"));
            }else{
                ztreeNodeDTO.setName(name.replace("null","数据异常"));
            }
        }else{
            ztreeNodeDTO.setName(name);
        }
        ztreeNodeDTO.setParent(isParent);
        ztreeNodeDTO.setOpen(open);
        ztreeNodeDTO.setChecked(checked);
        return ztreeNodeDTO.toString();
    }

    public static String getTreeNodeId(int num){
        String str = "";
        //10之后返回字母
        if (num >= 10){
            //取num十位数
            int sW = num/10%10;
            num = num + 55;
            char ch = (char)num;
            str = sW + String.valueOf(ch);
        }else{
            str = String.valueOf(num);
        }
        return str;
    }

    /**
     * 保存活动报名时间段配置
     */
    @Override
    public void saveApplyTimeRange(ActivityAdminDto activityAdminDto){
        //删除该日期下所有时间段配置
        communityRespository.deleteApplyTimeRangeByActivityAndDate(activityAdminDto.getActivityId(),DateUtils.parse(activityAdminDto.getApplyDate(),"yyyy-MM-dd"));
        //设置报名时间段
        CommunityActivityApplyTimeRangeEntity communityActivityApplyTimeRangeEntity = null;
        for (int i=0,length=activityAdminDto.getStartTimeList().size();i<length;i++){
            communityActivityApplyTimeRangeEntity = new CommunityActivityApplyTimeRangeEntity();
            communityActivityApplyTimeRangeEntity.setId(IdGen.uuid());
            communityActivityApplyTimeRangeEntity.setActivityId(activityAdminDto.getActivityId());//活动ID
            communityActivityApplyTimeRangeEntity.setApplyDate(DateUtils.parse(activityAdminDto.getApplyDate(),"yyyy-MM-dd"));//报名日期
            communityActivityApplyTimeRangeEntity.setStartTime(activityAdminDto.getStartTimeList().get(i));//起始时间
            communityActivityApplyTimeRangeEntity.setEndTime(activityAdminDto.getEndTimeList().get(i));//截止时间
            communityActivityApplyTimeRangeEntity.setMaxUser(Integer.valueOf(activityAdminDto.getMaxUserList().get(i)));//人数配额
            communityActivityApplyTimeRangeEntity.setApplyNum(0);//已报名人数
            communityActivityApplyTimeRangeEntity.setCreateOn(new Date());
            communityActivityApplyTimeRangeEntity.setCreateBy(activityAdminDto.getOperator());
            communityRespository.saveOrUpdate(communityActivityApplyTimeRangeEntity);
        }
    }

    /**
     * 获取活动报名时间段配置列表
     */
    @Override
    public List<CommunityActivityApplyTimeRangeEntity> getApplyTimeRangeList(ActivityAdminDto activityAdminDto){
        return communityRespository.getApplyTimeRangeListByActivity(activityAdminDto.getActivityId(),DateUtils.parse(activityAdminDto.getApplyDate(),"yyyy-MM-dd"));
    }
}
