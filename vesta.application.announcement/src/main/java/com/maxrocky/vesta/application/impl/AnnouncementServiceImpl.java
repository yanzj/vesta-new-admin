package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.application.inf.UserSettingService;
import com.maxrocky.vesta.application.service.BaseServiceImpl;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:10
 * Describe:
 */
@Service
public class AnnouncementServiceImpl extends BaseServiceImpl<AnnouncementEntity> implements AnnouncementService {

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    AnnouncementScopeRepository announcementScopeRepository;

    @Autowired
    VoteRepository voteRepository;
    @Autowired
    private ClickUserRepository clickUserRepository;

    @Autowired
    private SystemLogRepository systemLogRepository;


    @Autowired
    private CommunityCenterRespository communityCenterRespository;

    @Autowired
    private HouseInfoRepository houseInfoRepository;
    @Autowired
    UserSettingService userSettingService;

    @Override
    public List<AnnouncementDTO> queryAllByPage(AnnouncementDTO announcementDTO, WebPage webPage) throws Exception {

        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        List<AnnouncementDTO> announcementDTOs = new ArrayList<AnnouncementDTO>();
        Integer releaseStatus=998;
        String title="";
        String content="";
        String releasePerson="";
        String startDate="";
        String endDates="";
        if(announcementDTO!=null){
            announcementEntity.setReleaseStatus(announcementDTO.getReleaseStatus());
            announcementEntity.setTitle(announcementDTO.getTitle());
            announcementEntity.setContent(announcementDTO.getContent());
            announcementEntity.setReleasePerson(announcementDTO.getReleasePerson());
            startDate=announcementDTO.getStaDate();
            endDates=announcementDTO.getEndDate();
        }
        //数据处理转换为
        //BeanUtils.copyProperties(announcementEntity, announcementDTO);
        //设置查询时间段
        Date staDate = null;
        Date endDate = null;
        if (!StringUtil.isEmpty(startDate)) {
            staDate = DateUtils.parse(announcementDTO.getStaDate(), DateUtils.FORMAT_SHORT);
        }
        if (!StringUtil.isEmpty(endDates)) {
            endDate = DateUtils.parse(announcementDTO.getEndDate(), DateUtils.FORMAT_SHORT);
        }

        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = announcementDTO.getRoleScopeList();
        for (Map<String,Object> roleScope : roleScopeList){
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")){
                //城市级别(分公司)
                List<Object[]> projectList = listProject(roleScope.get("scopeId").toString());
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

        List<AnnouncementEntity> list = this.announcementRepository.queryAllByPage(announcementEntity, webPage, staDate, endDate, roleScopes);

        //遍历DTO
        AnnouncementDTO temp = null;
        for (AnnouncementEntity entity : list) {
            temp = new AnnouncementDTO();
            BeanUtils.copyProperties(temp, entity);
            //城市、项目范围
            List<Object[]> strList = this.announcementRepository.queryCitysById(entity.getId());
            String citys = "";
            String projects = "";
            for (int i = 0; i < strList.size(); i++){
                Object[] obj = strList.get(i);
                if (obj[0] != null)
                    if (!citys.contains(obj[0].toString())){
                        citys += obj[0].toString() + ",";
                    }
                if (obj[1] != null)
                    if (!projects.contains(obj[1].toString())){
                        projects += obj[1].toString() + ",";
                    }
            }
            temp.setCitys(citys);
            temp.setProjects(projects);

            announcementDTOs.add(temp);
        }
        return announcementDTOs;
    }

    /**
     * 查詢所有CRM_传输项目城市
     *
     * @return
     */
    @Override
    public List<Object[]> listCity() {
        return this.announcementRepository.listCity();
    }

    /**
     * 根据城市查询所有城市下项目
     *
     * @return
     */
    @Override
    public List<Object[]> listProject(String cityId) {
        return this.announcementRepository.listProject(cityId);
    }

    /**
     * 更新通知范围与详情
     *
     * @param announcementDTO
     */
    @Override
    public void updateAnnouncementAndScope(AnnouncementDTO announcementDTO) {
        //#1.保存或者更新通告基础信息
        //根据id获取实体类
        AnnouncementEntity announcementEntity = this.get(AnnouncementEntity.class, announcementDTO.getId());
        if (announcementEntity == null) {
            announcementEntity = new AnnouncementEntity();
            //设置id
            announcementEntity.setId(IdGen.uuid());
            //创建时间
            announcementEntity.setCreateDate(new Date());
            //创建人
            announcementEntity.setCreatePerson(announcementDTO.getReleasePerson());

            /**
             * Wyd_2016.05.31
             */
            //设置点赞数初始值0
            announcementEntity.setLikeNum(0l);
            //设置回复数初始值0
            announcementEntity.setReplyNum(0l);
        }
        //修改人
        announcementEntity.setOperatPerson(announcementDTO.getReleasePerson());
        //设置修改日期
        announcementEntity.setOperatDate(new Date());
        //设置title
        announcementEntity.setTitle(announcementDTO.getTitle());
        //设置connent
        announcementEntity.setContent(announcementDTO.getContent());
        //设置contentSynopsis_2016-06-17-Wyd
        announcementEntity.setContentSynopsis(announcementDTO.getContentSynopsis());
        //设置是否投票
        announcementEntity.setIsVote(announcementDTO.getIsVote());
        //设置发布状态
        announcementEntity.setReleaseStatus(announcementDTO.getReleaseStatus());
        if (announcementDTO.getReleaseStatus() == 1) {
            //发布日期
            announcementEntity.setReleaseDate(new Date());
        }
        //设置发布人
        announcementEntity.setReleasePerson(announcementDTO.getReleasePerson());
        //设置为启用
        announcementEntity.setStatus(1);
        this.announcementRepository.saveOrUpdate(announcementEntity);

        //#2.根据公告id，先清空所有为该id的范围表数据
        this.announcementRepository.deleteAnnouncementScope(announcementDTO.getId());

        //#3.1基础数据准备
        List<String> cityList = Arrays.asList(announcementDTO.getCitys().split(","));
        List<String> projectList = Arrays.asList(announcementDTO.getProjects().split(","));
        List<String> cityIds = Arrays.asList(announcementDTO.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(announcementDTO.getProjectIds().split(","));
        //#3.2
        //#3.重新进行添加
        AnnouncementScopeEntity announcementScopeEntity;
        //批量添加范围单
        //全部城市
        if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
            announcementScopeEntity = new AnnouncementScopeEntity();
            announcementScopeEntity.setId(IdGen.uuid());
            announcementScopeEntity.setAnnouncementDetailId(announcementEntity.getId());
            announcementScopeEntity.setCity("全部城市");
            announcementScopeEntity.setCityId("0");
            announcementScopeEntity.setScope("1");
            announcementScopeEntity.setIsAll(1);
            announcementScopeEntity.setStatus(1);
            announcementScopeEntity.setCreateDate(new Date());
            announcementScopeEntity.setOperateDate(new Date());
            announcementScopeEntity.setCreatePerson(announcementDTO.getReleasePerson());
            announcementScopeEntity.setOperatePerson(announcementDTO.getReleasePerson());
            //全部项目
            if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
                announcementScopeEntity.setProjectName(announcementDTO.getProjects());
                announcementScopeEntity.setProjectId(announcementDTO.getProjectIds());
                announcementScopeEntity.setScope("2");
            }
            this.announcementScopeRepository.saveOrUpdate(announcementScopeEntity);
        }
        //全部项目
        /*if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
            //遍历城市范围,检索每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++) {
                String cityId = cityIds.get(i);
                List<Object[]> projects = listProject(cityId);
                //为每个项目添加公告范围信息
                for (int j = 0; j < projects.size(); j++) {
                    announcementScopeEntity = new AnnouncementScopeEntity();
                    announcementScopeEntity.setId(IdGen.uuid());
                    announcementScopeEntity.setAnnouncementDetailId(announcementEntity.getId());
                    announcementScopeEntity.setCity(cityList.get(i));
                    announcementScopeEntity.setCityId(cityId);
                    announcementScopeEntity.setProjectName((String) projects.get(j)[1]);
                    announcementScopeEntity.setProjectId((String) projects.get(j)[0]);
                    announcementScopeEntity.setScope("2");
                    announcementScopeEntity.setStatus(1);
                    announcementScopeEntity.setCreateDate(new Date());
                    announcementScopeEntity.setOperateDate(new Date());
                    announcementScopeEntity.setCreatePerson(announcementDTO.getReleasePerson());
                    announcementScopeEntity.setOperatePerson(announcementDTO.getReleasePerson());
                    this.announcementScopeRepository.saveOrUpdate(announcementScopeEntity);
                }
            }
        }*/
        //单个项目
        if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
            for (int i = 0; i < projectList.size(); i++) {
                String project = projectList.get(i);
                if (!StringUtils.isEmpty(project)) {
                    announcementScopeEntity = new AnnouncementScopeEntity();
                    announcementScopeEntity.setId(IdGen.uuid());
                    announcementScopeEntity.setAnnouncementDetailId(announcementEntity.getId());
                    //通过项目Code绑定城市
                    List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                     if (!list.isEmpty() && list.size() > 0){
                        announcementScopeEntity.setCity(list.get(0)[1].toString());
                        announcementScopeEntity.setCityId(list.get(0)[0].toString());
                    }
                    announcementScopeEntity.setProjectName(project);
                    announcementScopeEntity.setProjectId(projectIds.get(i));
                    announcementScopeEntity.setIsAll(0);
                    announcementScopeEntity.setScope("3");
                    announcementScopeEntity.setStatus(1);
                    announcementScopeEntity.setCreateDate(new Date());
                    announcementScopeEntity.setOperateDate(new Date());
                    announcementScopeEntity.setCreatePerson(announcementDTO.getReleasePerson());
                    announcementScopeEntity.setOperatePerson(announcementDTO.getReleasePerson());
                    this.announcementScopeRepository.saveOrUpdate(announcementScopeEntity);
                }
            }
        }
        //#4关联投票_(Wyd_2016.06.02)
        if (announcementDTO.getIsVote() == 1 && !StringUtils.isEmpty(announcementDTO.getVoteId())) {
            //关联投票
            List<AnnouncementVoteEntity> announcementVoteList = this.voteRepository.queryAnnouncementVoteList(announcementEntity.getId());
            if (announcementVoteList.size() > 0){
                //已有公告_投票信息,删除之,重新添加
                this.voteRepository.deleteAnnouncementVoteByAnnouncementId(announcementEntity.getId());
            }
            //新增公告_投票信息
            //获取投票项列表信息
            List<VoteOptionEntity> optionList = this.voteRepository.queryVoteOptionList(announcementDTO.getVoteId());
            for (VoteOptionEntity optionEntity : optionList){
                if (cityList.size() == 1 && cityList.get(0).equals("全部城市") || projectList.size() == 1 && projectList.get(0).equals("全部项目")){
                    //全部城市或全部项目
                    //获取城市
                    if (cityList.size() > 0 && !cityList.get(0).equals("全部城市")){
                        //城市下所有项目
                        for (String cityId : cityIds){
                            List<Object[]> projects = listProject(cityId);
                            for (int j = 0; j < projects.size(); j++) {
                                AnnouncementVoteEntity announcementVoteEntity = new AnnouncementVoteEntity();
                                announcementVoteEntity.setId(IdGen.uuid());     //主键
                                announcementVoteEntity.setAnnouncementId(announcementEntity.getId());       //公告Id
                                announcementVoteEntity.setProjectId(projects.get(j)[0].toString());             //项目Id
                                announcementVoteEntity.setProjectName(projects.get(j)[1].toString());          //项目名称
                                announcementVoteEntity.setVoteId(announcementDTO.getVoteId());      //投票Id
                                announcementVoteEntity.setVoteOptionId(optionEntity.getId());       //投票项Id
                                announcementVoteEntity.setVoteNumber(0);       //投票数量,初始值:0
                                announcementVoteEntity.setStatus(0);        //状态
                                announcementVoteEntity.setCreateDate(new Date());       //创建时间
                                announcementVoteEntity.setCreatePerson(announcementDTO.getReleasePerson());     //创建人
                                this.voteRepository.saveOrUpdate(announcementVoteEntity);
                            }
                        }
                    }else {
                        //所有项目
                        List<Object[]> projects = this.voteRepository.queryProjectList();
                        for (Object[] project : projects){
                            AnnouncementVoteEntity announcementVoteEntity = new AnnouncementVoteEntity();
                            announcementVoteEntity.setId(IdGen.uuid());     //主键
                            announcementVoteEntity.setAnnouncementId(announcementEntity.getId());       //公告Id
                            announcementVoteEntity.setProjectId(project[0].toString());             //项目Id
                            announcementVoteEntity.setProjectName(project[1].toString());          //项目名称
                            announcementVoteEntity.setVoteId(announcementDTO.getVoteId());      //投票Id
                            announcementVoteEntity.setVoteOptionId(optionEntity.getId());       //投票项Id
                            announcementVoteEntity.setVoteNumber(0);       //投票数量,初始值:0
                            announcementVoteEntity.setStatus(0);        //状态
                            announcementVoteEntity.setCreateDate(new Date());       //创建时间
                            announcementVoteEntity.setCreatePerson(announcementDTO.getReleasePerson());     //创建人
                            this.voteRepository.saveOrUpdate(announcementVoteEntity);
                        }
                    }
                }else{
                    //城市-项目
                    //遍历每条投票项,新增公告_投票记录
                    for (int i = 0; i < projectIds.size(); i++){
                        AnnouncementVoteEntity announcementVoteEntity = new AnnouncementVoteEntity();
                        announcementVoteEntity.setId(IdGen.uuid());     //主键
                        announcementVoteEntity.setAnnouncementId(announcementEntity.getId());       //公告Id
                        announcementVoteEntity.setProjectId(projectIds.get(i));             //项目Id
                        announcementVoteEntity.setProjectName(projectList.get(i));          //项目名称
                        announcementVoteEntity.setVoteId(announcementDTO.getVoteId());      //投票Id
                        announcementVoteEntity.setVoteOptionId(optionEntity.getId());       //投票项Id
                        announcementVoteEntity.setVoteNumber(0);       //投票数量,初始值:0
                        announcementVoteEntity.setStatus(0);        //状态
                        announcementVoteEntity.setCreateDate(new Date());       //创建时间
                        announcementVoteEntity.setCreatePerson(announcementDTO.getReleasePerson());     //创建人
                        this.voteRepository.saveOrUpdate(announcementVoteEntity);
                    }
                }
            }

        }else{
            //未关联投票(未设置关联投票、关闭投票)
            List<AnnouncementVoteEntity> announcementVoteList = this.voteRepository.queryAnnouncementVoteList(announcementEntity.getId());
            if (announcementVoteList.size() > 0){
                //已有公告_投票信息,设置状态1:不启用
                for (AnnouncementVoteEntity announcementVoteEntity : announcementVoteList){
                    announcementVoteEntity.setStatus(1);
                    announcementVoteEntity.setOperatePerson(announcementDTO.getReleasePerson());
                    announcementVoteEntity.setOperateDate(new Date());
                    this.voteRepository.saveOrUpdate(announcementVoteEntity);
                }
            }
        }

      /*  InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
        infoReleaseLog.setLogId(IdGen.uuid());
        infoReleaseLog.setLogTime(new Date());//注册日期
        infoReleaseLog.setUserName(user.getStaffName());//用户昵称
        infoReleaseLog.setUserMobile(user.getMobile());//手机号
        infoReleaseLog.setThisSection("服务");//版块
        infoReleaseLog.setThisFunction("活动信息管理");//功能
        infoReleaseLog.setThisType("新增");//操作类型
        infoReleaseLog.setLogContent("");//发布内容
        HouseProjectEntity houseProjectEntity=houseProjectRepository.getProjectByCode(projectCode);
        if(houseProjectEntity!=null){
            infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
        }else{
            infoReleaseLog.setAsscommunity("");//关联社区;
        }
        systemLogRepository.addInfoReleaseLog(infoReleaseLog);*/

    }

    @Override
    public void updateAnnouncementAndScope(UserPropertyStaffEntity user, AnnouncementDTO announcementDTO) {
        //#1.保存或者更新通告基础信息
        //根据id获取实体类
        AnnouncementEntity announcementEntity = this.get(AnnouncementEntity.class, announcementDTO.getId());
        String state="";
        if (announcementEntity == null) {
            announcementEntity = new AnnouncementEntity();
            //设置id
            announcementEntity.setId(IdGen.uuid());
            announcementDTO.setId(announcementEntity.getId());//新增的时候，写日志用
            //创建时间
            announcementEntity.setCreateDate(new Date());
            //创建人
            announcementEntity.setCreatePerson(announcementDTO.getReleasePerson());

            /**
             * Wyd_2016.05.31
             */
            //设置点赞数初始值0
            announcementEntity.setLikeNum(0l);
            //设置回复数初始值0
            announcementEntity.setReplyNum(0l);
            state="add";
        }
        //修改人
        announcementEntity.setOperatPerson(announcementDTO.getReleasePerson());
        //设置修改日期
        announcementEntity.setOperatDate(new Date());
        //设置title
        announcementEntity.setTitle(announcementDTO.getTitle());
        //设置connent
        announcementEntity.setContent(announcementDTO.getContent());
        //设置contentSynopsis_2016-06-17-Wyd
        announcementEntity.setContentSynopsis(announcementDTO.getContentSynopsis());
        //设置是否投票
        announcementEntity.setIsVote(announcementDTO.getIsVote());
        //设置发布状态
        announcementEntity.setReleaseStatus(announcementDTO.getReleaseStatus());
        if (announcementDTO.getReleaseStatus() == 1) {
            //发布日期
            announcementEntity.setReleaseDate(new Date());
        }
        //设置发布人
        announcementEntity.setReleasePerson(announcementDTO.getReleasePerson());
        //设置为启用
        announcementEntity.setStatus(1);
        this.announcementRepository.saveOrUpdate(announcementEntity);

        //#2.根据公告id，先清空所有为该id的范围表数据
        this.announcementRepository.deleteAnnouncementScope(announcementDTO.getId());

        //#3.1基础数据准备
        List<String> cityList = Arrays.asList(announcementDTO.getCitys().split(","));
        List<String> projectList = Arrays.asList(announcementDTO.getProjects().split(","));
        List<String> cityIds = Arrays.asList(announcementDTO.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(announcementDTO.getProjectIds().split(","));
        //#3.2
        //#3.重新进行添加
        AnnouncementScopeEntity announcementScopeEntity;
        //批量添加范围单
        //全部城市
        if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
            announcementScopeEntity = new AnnouncementScopeEntity();
            announcementScopeEntity.setId(IdGen.uuid());
            announcementScopeEntity.setAnnouncementDetailId(announcementEntity.getId());
            announcementScopeEntity.setCity("全部城市");
            announcementScopeEntity.setCityId("0");
            announcementScopeEntity.setScope("1");
            announcementScopeEntity.setIsAll(1);
            announcementScopeEntity.setStatus(1);
            announcementScopeEntity.setCreateDate(new Date());
            announcementScopeEntity.setOperateDate(new Date());
            announcementScopeEntity.setCreatePerson(announcementDTO.getReleasePerson());
            announcementScopeEntity.setOperatePerson(announcementDTO.getReleasePerson());
            //全部项目
            if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
                announcementScopeEntity.setProjectName(announcementDTO.getProjects());
                announcementScopeEntity.setProjectId(announcementDTO.getProjectIds());
                announcementScopeEntity.setScope("2");
            }
            this.announcementScopeRepository.saveOrUpdate(announcementScopeEntity);
        }
        //全部项目
        if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
            //遍历城市范围,检索每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++) {
                String cityId = cityIds.get(i);
                List<Object[]> projects = listProject(cityId);
                //为每个项目添加公告范围信息
                for (int j = 0; j < projects.size(); j++) {
                    announcementScopeEntity = new AnnouncementScopeEntity();
                    announcementScopeEntity.setId(IdGen.uuid());
                    announcementScopeEntity.setAnnouncementDetailId(announcementEntity.getId());
                    announcementScopeEntity.setCity(cityList.get(i));
                    announcementScopeEntity.setCityId(cityId);
                    announcementScopeEntity.setProjectName((String) projects.get(j)[1]);
                    announcementScopeEntity.setProjectId((String) projects.get(j)[0]);
                    announcementScopeEntity.setScope("2");
                    announcementScopeEntity.setStatus(1);
                    announcementScopeEntity.setCreateDate(new Date());
                    announcementScopeEntity.setOperateDate(new Date());
                    announcementScopeEntity.setCreatePerson(announcementDTO.getReleasePerson());
                    announcementScopeEntity.setOperatePerson(announcementDTO.getReleasePerson());
                    this.announcementScopeRepository.saveOrUpdate(announcementScopeEntity);
                }
            }
        }
        //单个项目
        if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
            for (int i = 0; i < projectList.size(); i++) {
                String project = projectList.get(i);
                if (!StringUtils.isEmpty(project)) {
                    announcementScopeEntity = new AnnouncementScopeEntity();
                    announcementScopeEntity.setId(IdGen.uuid());
                    announcementScopeEntity.setAnnouncementDetailId(announcementEntity.getId());
                    //通过项目Code绑定城市
                    List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                    if (!list.isEmpty() && list.size() > 0){
                        announcementScopeEntity.setCity(list.get(0)[1].toString());
                        announcementScopeEntity.setCityId(list.get(0)[0].toString());
                    }
                    announcementScopeEntity.setProjectName(project);
                    announcementScopeEntity.setProjectId(projectIds.get(i));
                    announcementScopeEntity.setIsAll(0);
                    announcementScopeEntity.setScope("3");
                    announcementScopeEntity.setStatus(1);
                    announcementScopeEntity.setCreateDate(new Date());
                    announcementScopeEntity.setOperateDate(new Date());
                    announcementScopeEntity.setCreatePerson(announcementDTO.getReleasePerson());
                    announcementScopeEntity.setOperatePerson(announcementDTO.getReleasePerson());
                    this.announcementScopeRepository.saveOrUpdate(announcementScopeEntity);
                }
            }
        }
        //#4关联投票_(Wyd_2016.06.02)
        if (announcementDTO.getIsVote() == 1 && !StringUtils.isEmpty(announcementDTO.getVoteId())) {
            //关联投票
            List<AnnouncementVoteEntity> announcementVoteList = this.voteRepository.queryAnnouncementVoteList(announcementEntity.getId());
            if (announcementVoteList.size() > 0){
                //已有公告_投票信息,删除之,重新添加
                this.voteRepository.deleteAnnouncementVoteByAnnouncementId(announcementEntity.getId());
            }
            //新增公告_投票信息
            //获取投票项列表信息
            List<VoteOptionEntity> optionList = this.voteRepository.queryVoteOptionList(announcementDTO.getVoteId());
            for (VoteOptionEntity optionEntity : optionList){
                if (cityList.size() == 1 && cityList.get(0).equals("全部城市") || projectList.size() == 1 && projectList.get(0).equals("全部项目")){
                    //全部城市或全部项目
                    //获取城市
                    if (cityList.size() > 0 && !cityList.get(0).equals("全部城市")){
                        //城市下所有项目
                        for (String cityId : cityIds){
                            List<Object[]> projects = listProject(cityId);
                            for (int j = 0; j < projects.size(); j++) {
                                AnnouncementVoteEntity announcementVoteEntity = new AnnouncementVoteEntity();
                                announcementVoteEntity.setId(IdGen.uuid());     //主键
                                announcementVoteEntity.setAnnouncementId(announcementEntity.getId());       //公告Id
                                announcementVoteEntity.setProjectId(projects.get(j)[0].toString());             //项目Id
                                announcementVoteEntity.setProjectName(projects.get(j)[1].toString());          //项目名称
                                announcementVoteEntity.setVoteId(announcementDTO.getVoteId());      //投票Id
                                announcementVoteEntity.setVoteOptionId(optionEntity.getId());       //投票项Id
                                announcementVoteEntity.setVoteNumber(0);       //投票数量,初始值:0
                                announcementVoteEntity.setStatus(0);        //状态
                                announcementVoteEntity.setCreateDate(new Date());       //创建时间
                                announcementVoteEntity.setCreatePerson(announcementDTO.getReleasePerson());     //创建人
                                this.voteRepository.saveOrUpdate(announcementVoteEntity);
                            }
                        }
                    }else {
                        //所有项目
                        List<Object[]> projects = this.voteRepository.queryProjectList();
                        for (Object[] project : projects){
                            AnnouncementVoteEntity announcementVoteEntity = new AnnouncementVoteEntity();
                            announcementVoteEntity.setId(IdGen.uuid());     //主键
                            announcementVoteEntity.setAnnouncementId(announcementEntity.getId());       //公告Id
                            announcementVoteEntity.setProjectId(project[0].toString());             //项目Id
                            announcementVoteEntity.setProjectName(project[1].toString());          //项目名称
                            announcementVoteEntity.setVoteId(announcementDTO.getVoteId());      //投票Id
                            announcementVoteEntity.setVoteOptionId(optionEntity.getId());       //投票项Id
                            announcementVoteEntity.setVoteNumber(0);       //投票数量,初始值:0
                            announcementVoteEntity.setStatus(0);        //状态
                            announcementVoteEntity.setCreateDate(new Date());       //创建时间
                            announcementVoteEntity.setCreatePerson(announcementDTO.getReleasePerson());     //创建人
                            this.voteRepository.saveOrUpdate(announcementVoteEntity);
                        }
                    }
                }else{
                    //城市-项目
                    //遍历每条投票项,新增公告_投票记录
                    for (int i = 0; i < projectIds.size(); i++){
                        AnnouncementVoteEntity announcementVoteEntity = new AnnouncementVoteEntity();
                        announcementVoteEntity.setId(IdGen.uuid());     //主键
                        announcementVoteEntity.setAnnouncementId(announcementEntity.getId());       //公告Id
                        announcementVoteEntity.setProjectId(projectIds.get(i));             //项目Id
                        announcementVoteEntity.setProjectName(projectList.get(i));          //项目名称
                        announcementVoteEntity.setVoteId(announcementDTO.getVoteId());      //投票Id
                        announcementVoteEntity.setVoteOptionId(optionEntity.getId());       //投票项Id
                        announcementVoteEntity.setVoteNumber(0);       //投票数量,初始值:0
                        announcementVoteEntity.setStatus(0);        //状态
                        announcementVoteEntity.setCreateDate(new Date());       //创建时间
                        announcementVoteEntity.setCreatePerson(announcementDTO.getReleasePerson());     //创建人
                        this.voteRepository.saveOrUpdate(announcementVoteEntity);
                    }
                }
            }

        }else{
            //未关联投票(未设置关联投票、关闭投票)
            List<AnnouncementVoteEntity> announcementVoteList = this.voteRepository.queryAnnouncementVoteList(announcementEntity.getId());
            if (announcementVoteList.size() > 0){
                //已有公告_投票信息,设置状态1:不启用
                for (AnnouncementVoteEntity announcementVoteEntity : announcementVoteList){
                    announcementVoteEntity.setStatus(1);
                    announcementVoteEntity.setOperatePerson(announcementDTO.getReleasePerson());
                    announcementVoteEntity.setOperateDate(new Date());
                    this.voteRepository.saveOrUpdate(announcementVoteEntity);
                }
            }
        }

        List<Object[]> strList = this.announcementRepository.queryCitysById(announcementDTO.getId());
        String citys = "";
        String projects = "";
        for (int i = 0; i < strList.size(); i++){
            Object[] obj = strList.get(i);
            if (obj[0] != null)
                if (!citys.contains(obj[0].toString())){
                    citys += obj[0].toString() + ",";
                }
            if (obj[1] != null)
                if (!projects.contains(obj[1].toString())){
                    projects += obj[1].toString() + ",";
                }
        }

        if(state.equals("add")){
            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("物业管理-社区公告");//功能
            infoReleaseLog.setThisType("新增");//操作类型
            infoReleaseLog.setLogContent(announcementEntity.getTitle());//发布内容
      /*  HouseProjectEntity houseProjectEntity=houseProjectRepository.getProjectByCode(projectCode);
        if(houseProjectEntity!=null){
            infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
        }else{
            infoReleaseLog.setAsscommunity("");//关联社区;
        }*/
            infoReleaseLog.setAsscommunity(projects);//关联社区;
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        }else{
            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("物业管理-社区公告");//功能
            infoReleaseLog.setThisType("修改");//操作类型
            infoReleaseLog.setLogContent(announcementEntity.getTitle());//发布内容
      /*  HouseProjectEntity houseProjectEntity=houseProjectRepository.getProjectByCode(projectCode);
        if(houseProjectEntity!=null){
            infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
        }else{
            infoReleaseLog.setAsscommunity("");//关联社区;
        }*/
            infoReleaseLog.setAsscommunity(projects);//关联社区;
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        }
    }

    /**
     * 根据通告id获取当前通告下的关联投票
     *
     * @param id
     * @return
     */
    @Override
    public List<Object[]> queryVoteByAnnouncementId(String id) {
        return this.announcementRepository.queryVoteByAnnouncementId(id);
    }

    /**
     * 删除公告详情与公告关联投票信息
     *
     * @param announcementDTO
     */
    @Override
    public void deleteAnnouncementVote(AnnouncementDTO announcementDTO) {
        //#1.删除公告详情
        this.announcementRepository.delete(AnnouncementEntity.class, new String[]{announcementDTO.getId()});
        //#2.删除公告关联投票
        this.announcementRepository.deleteAnnouncementVote(announcementDTO.getId());
        //#3.删除通告关联范围的记录
        this.announcementScopeRepository.deleteAnnouncementScopeByAnnouncementId(announcementDTO.getId());
        //添加日志记录
    }

    @Override
    public void deleteAnnouncementVote(UserPropertyStaffEntity user, AnnouncementDTO announcementDTO) {
        //#1.删除公告详情
        this.announcementRepository.delete(AnnouncementEntity.class, new String[]{announcementDTO.getId()});
        //#2.删除公告关联投票
        this.announcementRepository.deleteAnnouncementVote(announcementDTO.getId());
        //#3.删除通告关联范围的记录
        this.announcementScopeRepository.deleteAnnouncementScopeByAnnouncementId(announcementDTO.getId());
        AnnouncementEntity announcementEntity = this.get(AnnouncementEntity.class, announcementDTO.getId());;
        //添加日志记录
        InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
        if(announcementEntity!=null){
            infoReleaseLog.setLogContent(announcementEntity.getTitle());//发布内容
        }
        List<Object[]> strList = this.announcementRepository.queryCitysById(announcementDTO.getId());
        String citys = "";
        String projects = "";
        for (int i = 0; i < strList.size(); i++){
            Object[] obj = strList.get(i);
            if (obj[0] != null)
                if (!citys.contains(obj[0].toString())){
                    citys += obj[0].toString() + ",";
                }
            if (obj[1] != null)
                if (!projects.contains(obj[1].toString())){
                    projects += obj[1].toString() + ",";
                }
        }
        infoReleaseLog.setLogId(IdGen.uuid());
        infoReleaseLog.setLogTime(new Date());//注册日期
        infoReleaseLog.setUserName(user.getStaffName());//用户昵称
        infoReleaseLog.setUserMobile(user.getMobile());//手机号
        infoReleaseLog.setThisSection("服务");//版块
        infoReleaseLog.setThisFunction("物业管理-社区公告");//功能
        infoReleaseLog.setThisType("删除");//操作类型
       /* HouseProjectEntity houseProjectEntity=houseProjectRepository.getProjectByCode(projectCode);
        if(houseProjectEntity!=null){
            infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
        }else{
            infoReleaseLog.setAsscommunity("");//关联社区;
        }*/
        infoReleaseLog.setAsscommunity(projects);//关联社区;
        systemLogRepository.addInfoReleaseLog(infoReleaseLog);

    }

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user,AnnouncementDTO announcementsDTO,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws Exception {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        List<AnnouncementDTO> announcementDTO = queryAllByPage(announcementsDTO, webPage);

        XSSFSheet sheet = workBook.createSheet("社区公告列表");

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

        String[] titles = {"序号", "标题", "发布人", "城市", "项目", "创建时间", "状态","评论数", "点赞数"};
        XSSFRow headRow = sheet.createRow(0);


        if (announcementDTO.size() > 0) {

            announcementDTO.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (announcementDTO.size() > 0) {
                    for (int i = 0; i < announcementDTO.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        AnnouncementDTO announcementDto = announcementDTO.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(announcementDto.getTitle());//标题

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(announcementDto.getReleasePerson());//发布人

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(announcementDto.getCitys());//城市

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(announcementDto.getProjects());//项目

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        Date date = announcementDto.getCreateDate();
                        cell.setCellValue(DateUtils.format(date));//创建时间

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        String state="";
                        if(announcementDto.getReleaseStatus().toString().equals("0")){
                            state="未发布";
                        }else if(announcementDto.getReleaseStatus().toString().equals("1")){
                            state="已发布";
                        }
                        cell.setCellValue(state);//状态

                        cell = bodyRow.createCell(7);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(announcementDto.getReplyNum());//评论数

                        cell = bodyRow.createCell(8);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(announcementDto.getLikeNum());//点赞数


                    }
                }
            });
        }
        try {
            //String fileName = new String(("社区公告管理").getBytes(), "ISO8859_1");
            String fileName = new String(("社区公告管理").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("社区公告管理", "UTF8");
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
     * 通过社区公告Id检索发布项目范围
     */
    public List<Map<String,Object>> getProjectScopeByAnnouncementId(String announcementId){
        List<Map<String,Object>> projectList = new ArrayList<>();
        List<AnnouncementScopeEntity> announcementScopeEntities = announcementRepository.getScopeByAnnouncement(announcementId);
        if (announcementScopeEntities.size() > 0){
            if (announcementScopeEntities.size() == 1 && announcementScopeEntities.get(0).getCityId().equals("0")){
                //全部城市
                List<Object[]> list = announcementRepository.listAllProject();
                for (Object[] object : list){
                    Map<String,Object> project = new HashMap<>();
                    project.put("projectId",object[0].toString());
                    project.put("projectName",object[1].toString());
                    projectList.add(project);
                }
            }else{
                for (AnnouncementScopeEntity announcementScopeEntity : announcementScopeEntities){
                    Map<String,Object> project = new HashMap<>();
                    project.put("projectId",announcementScopeEntity.getProjectId());
                    project.put("projectName",announcementScopeEntity.getProjectName());
                    projectList.add(project);
                }
            }
        }
        return projectList;
    }


    //------------------------------------前台接口----------------------------------------------------------

    /**
     * 查询所有在当前项目下的社区公告 ，与区域为项目所在区域的公告
     */
    public ApiResult queryAnnouncementByCityAndProjectName(String city, String projectName) {
        return new SuccessApiResult(this.announcementRepository.queryAnnouncementByCityAndProjectName(city, projectName));
    }

    /**
     * 公告_点赞
     * @auther Wyd_2016.06.01
     * @param userInfoEntity
     * @param announcementId
     * @return
     */
    public ApiResult clickPraise(UserInfoEntity userInfoEntity, String announcementId){
        //校验是否已经点赞
        Integer count = announcementRepository.queryLogByUserAndAnnouncement(userInfoEntity.getUserId(), announcementId);
        if (count > 0){
            //已经存在点赞记录
            return ErrorResource.getError("tip_AN000001");
        }else{
            //获取公告
            AnnouncementEntity announcementEntity = announcementRepository.queryAnnouncementByID(announcementId);
            //执行更新操作(点赞数+1)
            synchronized (announcementEntity){
                announcementEntity.setLikeNum(announcementEntity.getLikeNum() + 1);
                announcementRepository.updateAnnouncement(announcementEntity);
            }
            //保存点赞记录
            AnnouncementLogEntity announcementLogEntity = new AnnouncementLogEntity();
            announcementLogEntity.setId(IdGen.uuid());
            announcementLogEntity.setUserId(userInfoEntity.getUserId());
            announcementLogEntity.setAnnouncementId(announcementId);
            announcementLogEntity.setCreateDate(new Date());
            announcementRepository.saveOrUpdate(announcementLogEntity);
        }
        return new SuccessApiResult();
    }


    @Override
    public ApiResult queryAnnouncementReplyById(String announcementid) {
        List<Object> list = new ArrayList<Object>();
        AnnouncementEntity announcementEntity = announcementRepository.queryAnnouncementByID(announcementid);
        list.add(announcementEntity);

        UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
        userVisitLogEntity.setLogId(IdGen.uuid());
        userVisitLogEntity.setLogTime(new Date());//注册日期
        userVisitLogEntity.setUserName("");//用户昵称
        userVisitLogEntity.setUserType("");//用户类型
        userVisitLogEntity.setUserMobile("");//手机号
        userVisitLogEntity.setSourceFrom("");//来源
        userVisitLogEntity.setThisSection("服务");
        userVisitLogEntity.setThisFunction("社区公告");
        userVisitLogEntity.setLogContent(announcementEntity.getTitle());
        userVisitLogEntity.setProjectId("");//项目
        systemLogRepository.addUserVisitLog(userVisitLogEntity);
//        AnnouncementNolandingDTO announcementNolanding = new AnnouncementNolandingDTO();
//        announcementNolanding.setDetailidId(announcementEntity.getId());
//        announcementNolanding.setTitle(announcementEntity.getTitle());
//        announcementNolanding.setContent(announcementEntity.getContent());
//        announcementNolanding.setReleaseDate(DateUtils.format(announcementEntity.getReleaseDate()));
//        announcementNolanding.setCreatePerson(announcementEntity.getCreatePerson());
//        announcementNolanding.setCollectionId("");
//        return new SuccessApiResult(announcementNolanding);
        return new SuccessApiResult(list);

    }

    @Override
    public ApiResult queryAnnouncementReplyById(String announcementid, String userId) {
        List<Object> list = new ArrayList<Object>();

        AnnouncementEntity announcementEntity = announcementRepository.queryAnnouncementByID(announcementid);
        list.add(announcementEntity);

        List<AnnouncementReplyEntity> announcementReplys = announcementRepository.queryAnnouncementReplyById(announcementid,userId);
        //去重复
        List<AnnouncementReplyEntity> delAnnouncementReplys = new ArrayList<>();
        if (announcementReplys != null && announcementReplys.size()>0){
            for (int i = 0; i < announcementReplys.size(); i++){
                for (int j = i+1; j < announcementReplys.size(); j ++){
                    if (announcementReplys.get(i).getId() != null && announcementReplys.get(j).getId() != null){
                        if (announcementReplys.get(i).getId().equals(announcementReplys.get(j).getId())){
                            delAnnouncementReplys.add(announcementReplys.get(j));
                        }
                    }
                }
            }
        }
        //删除重复
        if(delAnnouncementReplys != null && delAnnouncementReplys.size() > 0){
            for (AnnouncementReplyEntity delA : delAnnouncementReplys){
                announcementReplys.remove(delA);
            }
        }
        list.add(announcementReplys);

        //是否收藏
        List<CollectionEntity> collectionEntities = announcementRepository.queryUserCollection("1", announcementid, userId);
        Map<String,Object> collecMap = new HashMap<>();
        if (collectionEntities.size() > 0){
            collecMap.put("collectionId", collectionEntities.get(0).getId());
        }else{
            collecMap.put("collectionId","");
        }
        //校验是否已经点赞
        Integer count = announcementRepository.queryLogByUserAndAnnouncement(userId, announcementid);
        if (count > 0){
            collecMap.put("isPraise",1);
        }else{
            collecMap.put("isPraise",0);
        }
        list.add(collecMap);

        //增加用户日志记录
        UserInfoEntity userInfo = this.communityCenterRespository.get(UserInfoEntity.class,userId);
        UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
        userVisitLogEntity.setLogId(IdGen.uuid());
        userVisitLogEntity.setLogTime(new Date());//注册日期
        userVisitLogEntity.setUserName(userInfo.getNickName());//用户昵称
        userVisitLogEntity.setUserType(userInfo.getUserType());//用户类型
        userVisitLogEntity.setUserMobile(userInfo.getMobile());//手机号
        userVisitLogEntity.setSourceFrom(userInfo.getSourceType());//来源
        userVisitLogEntity.setThisSection("服务");
        userVisitLogEntity.setThisFunction("社区公告");
        userVisitLogEntity.setLogContent(announcementEntity.getTitle());
        if(userInfo!=null){
            UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfo.getUserId());
            if (null != userSettingEntity) {
                userVisitLogEntity.setProjectId(userSettingEntity.getProjectName());
            }
        }else{
            userVisitLogEntity.setProjectId("");//项目
        }
        systemLogRepository.addUserVisitLog(userVisitLogEntity);
        return new SuccessApiResult(list);
    }


    @Override
    public ApiResult getPropertyAnnouncementList(String houseProjectId,UserInfoEntity user) {

        List<Object[]> announcements = announcementRepository.queryAnnouncementByProjectID(houseProjectId,user.getUserId());

        List<AnnouncementViewDTO> list = new ArrayList<AnnouncementViewDTO>();

        for(Object[] objects:announcements){
            AnnouncementViewDTO announcementView = new AnnouncementViewDTO();

            announcementView.setDetailidId((String) objects[0]);    //公告详情Id
            announcementView.setTitle((String) objects[1]);         //公告标题
            announcementView.setContent((String) objects[2]);       //公告内容
            if(null != objects[3] && !"".equals(objects[3])) {
                announcementView.setReleaseDate(DateUtils.format((Date) objects[3]));
            }       //公告发布日期
            announcementView.setIsVote(String.valueOf(objects[4])); //是否有投票
            announcementView.setLikeNum(String.valueOf(objects[5]));//点赞数
            announcementView.setReplyNum(String.valueOf(objects[6]));//回复数
            announcementView.setCreatePerson((String) objects[7]);  //创建人
            if(null == objects[8]){
                announcementView.setContentSynopsis("");
            }else{
                announcementView.setContentSynopsis((String) objects[8]);
            }       //公告内容简介
            if(null == objects[9]){
                announcementView.setCollectionId("");
            }else{
                announcementView.setCollectionId((String) objects[9]);
            }       //收藏ID

            list.add(announcementView);
        }
        //调用点击人统计
        String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
        if(user==null){
            user=new UserInfoEntity();
            user.setUserId("1");
        }
        ClickUsersEntity clickUserEntity = clickUserRepository.getTotalInfo(dateNow, "6", user.getUserId());
        if(clickUserEntity==null){
            ClickUsersEntity clickUser=new ClickUsersEntity();
            clickUser.setId(IdGen.uuid());
            clickUser.setCreateDate(new Date());
            clickUser.setClicks(1);
            clickUser.setUserId(user.getUserId());
            clickUser.setForeignId("6");
            clickUserRepository.create(clickUser);
        }else{
            clickUserEntity.setClicks(clickUserEntity.getClicks() + 1);
            clickUserRepository.update(clickUserEntity);
        }
        return new SuccessApiResult(list);

    }

    public ApiResult getPropertyAnnouncementList(String houseProjectId) {

        List<Object[]> announcements = announcementRepository.queryAnnouncementByProjectID(houseProjectId);

        List<AnnouncementViewDTO> list = new ArrayList<AnnouncementViewDTO>();

        for(Object[] objects:announcements){

            AnnouncementViewDTO announcementView = new AnnouncementViewDTO();

            announcementView.setDetailidId((String) objects[0]);
            announcementView.setTitle((String) objects[1]);
            announcementView.setContent((String) objects[2]);
            if(null != objects[3] && !"".equals(objects[3])) {
                announcementView.setReleaseDate(DateUtils.format((Date) objects[3]));
            }else{
            }
            announcementView.setIsVote(String.valueOf(objects[4]));
            announcementView.setLikeNum(String.valueOf(objects[5]));
            announcementView.setReplyNum(String.valueOf(objects[6]));
            announcementView.setCreatePerson((String) objects[7]);
            //游客访问-收藏默认为空
            announcementView.setCollectionId("");
            if(null == objects[8]){
                announcementView.setContentSynopsis("");
            }else{
                announcementView.setContentSynopsis((String) objects[8]);
            }

            list.add(announcementView);
        }
        return new SuccessApiResult(list);

    }

    @Override
    public ApiResult addCollection(CollectionDTO collection) {

        CollectionEntity collectionEntity = new CollectionEntity();

        if(null !=collection.getMessageId()&& !"".equals(collection.getMessageId())) {

            collectionEntity.setId(IdGen.uuid());
            collectionEntity.setMessageId(collection.getMessageId());
            collectionEntity.setMessageType(collection.getMessageType());
            collectionEntity.setUserId(collection.getUserId());
            collectionEntity.setCreateDate(new Date());

            if (announcementRepository.saveCollection(collectionEntity)) {
                return new SuccessApiResult();
            }
            return new ErrorApiResult("数据异常，请刷新后重试");
        }
        return new ErrorApiResult("数据错误，请刷新后重试");
    }

    @Override
    public ApiResult delectCollection(CollectionDTO collection) {

        CollectionEntity collectionEntity = announcementRepository.getCollectionById(collection.getId());

        if(null != collectionEntity){
            if (announcementRepository.deleteCollection(collectionEntity)){
                return new SuccessApiResult();
            }
        }

        return new ErrorApiResult("数据异常，请刷新后重试");
    }

    @Override
    public ApiResult getCollectionList(UserInfoEntity userInfoEntity) {
        List<Object[]> collections = announcementRepository.getCollectionList(userInfoEntity.getUserId());
        List<CollectionViewDTO> list = new ArrayList<CollectionViewDTO>();
        for(Object[] objects:collections){
            CollectionViewDTO collectionView = new CollectionViewDTO();
            collectionView.setId((String) objects[0]);
            collectionView.setMessageId((String) objects[1]);
            collectionView.setTitle((String) objects[2]);
            collectionView.setContent((String) objects[3]);
            if(null != objects[4] && !"".equals(objects[4])) {
                collectionView.setReleaseDate(DateUtils.format((Date) objects[4]));
            }else{
                collectionView.setReleaseDate(null);
            }
            collectionView.setIsVote(String.valueOf(objects[5]));
            collectionView.setLikeNum(String.valueOf(objects[6]));
            collectionView.setReplyNum(String.valueOf(objects[7]));
            collectionView.setCreatePerson((String) objects[8]);
            if (null == objects[9]){
                collectionView.setContentSynopsis("");
            }else{
                collectionView.setContentSynopsis((String)objects[9]);
            }

            list.add(collectionView);
        }
        //调用点击人统计
        String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
        ClickUsersEntity clickUserEntity=clickUserRepository.getTotalInfo(dateNow,"13",userInfoEntity.getUserId());
        if(clickUserEntity==null){
            ClickUsersEntity clickUser=new ClickUsersEntity();
            clickUser.setId(IdGen.uuid());
            clickUser.setCreateDate(new Date());
            clickUser.setClicks(1);
            clickUser.setUserId(userInfoEntity.getUserId());
            clickUser.setForeignId("13");
            clickUserRepository.create(clickUser);
        }else{
            clickUserEntity.setClicks(clickUserEntity.getClicks()+1);
            clickUserRepository.update(clickUserEntity);
        }
        //增加用户日志记录
        //UserInfoEntity userInfo = this.communityCenterRespository.get(UserInfoEntity.class,userId);
        UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
        userVisitLogEntity.setLogId(IdGen.uuid());
        userVisitLogEntity.setLogTime(new Date());//注册日期
        userVisitLogEntity.setUserName(userInfoEntity.getNickName());//用户昵称
        userVisitLogEntity.setUserType(userInfoEntity.getUserType());//用户类型
        userVisitLogEntity.setUserMobile(userInfoEntity.getMobile());//手机号
        userVisitLogEntity.setSourceFrom(userInfoEntity.getSourceType());//来源
        userVisitLogEntity.setThisSection("我的");
        userVisitLogEntity.setThisFunction("我的收藏");
        userVisitLogEntity.setLogContent("");
        UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfoEntity.getUserId());
        if (null != userSettingEntity) {
            userVisitLogEntity.setProjectId(userSettingEntity.getProjectName());
        } else{
            userVisitLogEntity.setProjectId("");//项目
        }
        systemLogRepository.addUserVisitLog(userVisitLogEntity);
        return new SuccessApiResult(list);
    }
}
