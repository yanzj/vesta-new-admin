package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.CommunityNewsDto;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
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
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/4/20
 * Time: 10:10
 * Describe:
 */
@Service
public class CommunityNewsServiceImpl implements CommunityNewsService {
    static {
        // 对日期进行处理
        DateConverter dc = new DateConverter();
        dc.setPattern("yyyy-MM-dd HH:mm:ss");
        ConvertUtils.register(dc, Date.class);
    }
    @Autowired
    AnnouncementRepository announcementRepository;
    @Autowired
    CommunityNewsRespository communityNewsRespository;
    @Autowired
    AnnouncementScopeRepository announcementScopeRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    ImgService imgService;
    @Autowired
    ClickUserRepository clickUserRepository;
    @Autowired
    SystemLogRepository systemLogRepository;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 通过社区公告Id检索发布项目范围
     */
    public List<Map<String,Object>> getProjectScopeByCommunityDetailId(String communityDetailId){
        List<Map<String,Object>> projectList = new ArrayList<>();
        List<CommunityNewScope> communityNewScopes = communityNewsRespository.getScopeByCommunityNew(communityDetailId);
        if (communityNewScopes.size() > 0){
            if (communityNewScopes.size() == 1 && communityNewScopes.get(0).getCityId().equals("0")){
                //全部城市
                List<Object[]> list = announcementRepository.listAllProject();
                for (Object[] object : list){
                    Map<String,Object> project = new HashMap<>();
                    project.put("projectId",object[0].toString());
                    project.put("projectName",object[1].toString());
                    projectList.add(project);
                }
            }else{
                for (CommunityNewScope communityNewScope : communityNewScopes){
                    Map<String,Object> project = new HashMap<>();
                    project.put("projectId",communityNewScope.getProjectId());
                    project.put("projectName",communityNewScope.getProjectName());
                    projectList.add(project);
                }
            }
        }
        return projectList;
    }

/**==========================================================================================*/
    /**
     * 根据城市查询所有城市下项目(通用方法，只是写在了公告持久层里)
     *
     * @return
     */
    public List<Object[]> listProject(String cityId) {
        return this.announcementRepository.listProject(cityId);
    }
    /**
     * 增加/修改 新闻
     *
     * @param communityNewsDto
     * @param <E>
     */
    @Override
    public <E> void saveOrUpdateNews(CommunityNewsDto communityNewsDto) {
        CommunityNewsEntity communityNewsEntity = this.communityNewsRespository.get(CommunityNewsEntity.class, communityNewsDto.getId());
        if (null == communityNewsEntity) {
            communityNewsEntity = new CommunityNewsEntity();
            //设置id
            communityNewsEntity.setId(IdGen.uuid());
            //创建时间
            communityNewsEntity.setCreateTime(new Date());
            //创建人
            communityNewsEntity.setCreatePerson(communityNewsDto.getCreatePerson());
            //修改人
            communityNewsEntity.setOperator(communityNewsDto.getCreatePerson());
            //排序
            Integer orderNum = this.communityNewsRespository.getMaxOrderNum();
            communityNewsEntity.setOrderNum(orderNum + 1);
            //类型
            communityNewsEntity.setType(2);

        } else {
            //修改人
            communityNewsEntity.setOperator(communityNewsDto.getOperator());
        }
        //发布人
        if (communityNewsDto.getReleaseStatus() == 1)
            communityNewsEntity.setReleasePerson(communityNewsDto.getReleasePerson());
        //修改时间
        communityNewsEntity.setOperatorTime(new Date());
        //发布状态
        communityNewsEntity.setReleaseStatus(communityNewsDto.getReleaseStatus());
        //内容
        communityNewsEntity.setComment(communityNewsDto.getComment());
        //标题
        communityNewsEntity.setTitle(communityNewsDto.getTitle());
        if (communityNewsDto.getReleaseStatus() == 1) {
            //发布时间
            /*communityNewsEntity.setReleaseDate(new Date());*/
            communityNewsEntity.setReleaseDate(DateUtils.parse(communityNewsDto.getReleaseDate(),"yyyy-MM-dd HH:mm"));
        }
        /**========================================================================================*/
        //发布时间
            /*communityNewsEntity.setReleaseDate(new Date());*/
            communityNewsEntity.setReleaseDate(DateUtils.parse(communityNewsDto.getReleaseDate(),"yyyy-MM-dd HH:mm"));
        /**========================================================================================*/
        //状态
        communityNewsEntity.setStatus(1);

        //图片上传
        String fileName = imgService.uploadAdminImage(communityNewsDto.getHomePageimgpath(), ImgType.ACTIVITY);
        //图片地址特殊处理
        String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
        fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");

        if (fileName.equals("")) {
            fileName = "默认图";
        }
        if (!fileName.equals(ImageConfig.PIC_OSS_ADMIN_URL)) {
            communityNewsEntity.setNewsImg(fileName);
        }

        this.communityNewsRespository.saveOrUpdateNews(communityNewsEntity);
        /**=======================================================================**/
        //#2.根据新闻id，先清空所有为该id的范围表数据
        this.announcementScopeRepository.deleteCommunitNewsId(communityNewsDto.getId());
        //#3.1基础数据准备
        List<String> cityList = Arrays.asList(communityNewsDto.getCitys().split(","));
        List<String> projectList = Arrays.asList(communityNewsDto.getProjects().split(","));
        List<String> cityIds = Arrays.asList(communityNewsDto.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(communityNewsDto.getProjectIds().split(","));
        //#3.2
        //#3.重新进行添加
        CommunityNewScope communityNewScope;
        //批量添加范围单
        //全部城市
        if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
            communityNewScope = new CommunityNewScope();
            communityNewScope.setId(IdGen.uuid());
            communityNewScope.setCommunityDetailId(communityNewsEntity.getId());
            communityNewScope.setCity("全部城市");
            communityNewScope.setCityId("0");
            communityNewScope.setScope("1");
            communityNewScope.setIsAll(1);
            communityNewScope.setStatus(1);
            communityNewScope.setCreateDate(new Date());
            communityNewScope.setOperateDate(new Date());
            communityNewScope.setCreatePerson(communityNewsDto.getReleasePerson());
            communityNewScope.setOperatePerson(communityNewsDto.getReleasePerson());
            this.announcementScopeRepository.addOrUpdateCommunitNews(communityNewScope);
        }
        //全部项目
        if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
            //遍历城市范围,检索每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++) {
                String cityId = cityIds.get(i);
                List<Object[]> projects = listProject(cityId);
                //为每个项目添加公告范围信息
                for (int j = 0; j < projects.size(); j++) {
                    communityNewScope = new CommunityNewScope();
                    communityNewScope.setId(IdGen.uuid());
                    communityNewScope.setCommunityDetailId(communityNewsEntity.getId());
                    communityNewScope.setCity(cityList.get(i));
                    communityNewScope.setCityId(cityId);
                    communityNewScope.setProjectName((String) projects.get(j)[1]);
                    communityNewScope.setProjectId((String) projects.get(j)[0]);
                    communityNewScope.setScope("2");
                    communityNewScope.setStatus(1);
                    communityNewScope.setCreateDate(new Date());
                    communityNewScope.setOperateDate(new Date());
                    communityNewScope.setCreatePerson(communityNewsDto.getReleasePerson());
                    communityNewScope.setOperatePerson(communityNewsDto.getReleasePerson());
                    this.announcementScopeRepository.addOrUpdateCommunitNews(communityNewScope);
                }
            }
        }
        //单个项目
        if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
            for (int i = 0; i < projectList.size(); i++) {
                String project = projectList.get(i);
                if (!StringUtils.isEmpty(project)) {
                    communityNewScope = new CommunityNewScope();
                    communityNewScope.setId(IdGen.uuid());
                    communityNewScope.setCommunityDetailId(communityNewsEntity.getId());
                    //通过项目Code绑定城市
                    List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                    if (!list.isEmpty() && list.size() > 0){
                        communityNewScope.setCity(list.get(0)[1].toString());
                        communityNewScope.setCityId(list.get(0)[0].toString());
                    }
                    communityNewScope.setProjectName(project);
                    communityNewScope.setProjectId(projectIds.get(i));
                    communityNewScope.setScope("3");
                    communityNewScope.setStatus(1);
                    communityNewScope.setCreateDate(new Date());
                    communityNewScope.setOperateDate(new Date());
                    communityNewScope.setCreatePerson(communityNewsDto.getReleasePerson());
                    communityNewScope.setOperatePerson(communityNewsDto.getReleasePerson());
                    this.announcementScopeRepository.addOrUpdateCommunitNews(communityNewScope);
                }
            }
        }
    }

    @Override
    public <E> void saveOrUpdateNews(UserPropertyStaffEntity user, CommunityNewsDto communityNewsDto) {
        CommunityNewsEntity communityNewsEntity = this.communityNewsRespository.get(CommunityNewsEntity.class, communityNewsDto.getId());
        String state="";
        if (null == communityNewsEntity) {
            communityNewsEntity = new CommunityNewsEntity();
            //设置id
            communityNewsEntity.setId(IdGen.uuid());
            //创建时间
            communityNewsEntity.setCreateTime(new Date());
            //创建人
            communityNewsEntity.setCreatePerson(communityNewsDto.getCreatePerson());
            //修改人
            communityNewsEntity.setOperator(communityNewsDto.getCreatePerson());
            //排序
            Integer orderNum = this.communityNewsRespository.getMaxOrderNum();
            communityNewsEntity.setOrderNum(orderNum + 1);
            //类型
            communityNewsEntity.setType(2);
            state="add";
        } else {
            //修改人
            communityNewsEntity.setOperator(communityNewsDto.getOperator());
        }
        //发布人
        if (communityNewsDto.getReleaseStatus() == 1)
            communityNewsEntity.setReleasePerson(communityNewsDto.getReleasePerson());
        //修改时间
        communityNewsEntity.setOperatorTime(new Date());
        //发布状态
        communityNewsEntity.setReleaseStatus(communityNewsDto.getReleaseStatus());
        //内容
        communityNewsEntity.setComment(communityNewsDto.getComment());
        //标题
        communityNewsEntity.setTitle(communityNewsDto.getTitle());
        if (communityNewsDto.getReleaseStatus() == 1) {
            //发布时间
            /*communityNewsEntity.setReleaseDate(new Date());*/
            communityNewsEntity.setReleaseDate(DateUtils.parse(communityNewsDto.getReleaseDate(),"yyyy-MM-dd HH:mm"));
        }
        /**========================================================================================*/
        //发布时间
            /*communityNewsEntity.setReleaseDate(new Date());*/
        communityNewsEntity.setReleaseDate(DateUtils.parse(communityNewsDto.getReleaseDate(),"yyyy-MM-dd HH:mm"));
        /**========================================================================================*/
        //状态
        communityNewsEntity.setStatus(1);

        //图片上传
        String fileName = imgService.uploadAdminImage(communityNewsDto.getHomePageimgpath(), ImgType.ACTIVITY);
        //图片地址特殊处理
        String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
        fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");

        if (fileName.equals("")) {
            fileName = "默认图";
        }
        if (!fileName.equals(ImageConfig.PIC_OSS_ADMIN_URL)) {
            communityNewsEntity.setNewsImg(fileName);
        }

        this.communityNewsRespository.saveOrUpdateNews(communityNewsEntity);
        /**=======================================================================**/
        //#2.根据新闻id，先清空所有为该id的范围表数据
        this.announcementScopeRepository.deleteCommunitNewsId(communityNewsDto.getId());
        //#3.1基础数据准备
        List<String> cityList = Arrays.asList(communityNewsDto.getCitys().split(","));
        List<String> projectList = Arrays.asList(communityNewsDto.getProjects().split(","));
        List<String> cityIds = Arrays.asList(communityNewsDto.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(communityNewsDto.getProjectIds().split(","));
        //#3.2
        //#3.重新进行添加
        CommunityNewScope communityNewScope;
        //批量添加范围单
        //全部城市
        if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
            communityNewScope = new CommunityNewScope();
            communityNewScope.setId(IdGen.uuid());
            communityNewScope.setCommunityDetailId(communityNewsEntity.getId());
            communityNewScope.setCity("全部城市");
            communityNewScope.setCityId("0");
            communityNewScope.setScope("1");
            communityNewScope.setIsAll(1);
            communityNewScope.setStatus(1);
            communityNewScope.setCreateDate(new Date());
            communityNewScope.setOperateDate(new Date());
            communityNewScope.setCreatePerson(communityNewsDto.getReleasePerson());
            communityNewScope.setOperatePerson(communityNewsDto.getReleasePerson());
            this.announcementScopeRepository.addOrUpdateCommunitNews(communityNewScope);
        }
        //全部项目
        if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
            //遍历城市范围,检索每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++) {
                String cityId = cityIds.get(i);
                List<Object[]> projects = listProject(cityId);
                //为每个项目添加公告范围信息
                for (int j = 0; j < projects.size(); j++) {
                    communityNewScope = new CommunityNewScope();
                    communityNewScope.setId(IdGen.uuid());
                    communityNewScope.setCommunityDetailId(communityNewsEntity.getId());
                    communityNewScope.setCity(cityList.get(i));
                    communityNewScope.setCityId(cityId);
                    communityNewScope.setProjectName((String) projects.get(j)[1]);
                    communityNewScope.setProjectId((String) projects.get(j)[0]);
                    communityNewScope.setScope("2");
                    communityNewScope.setStatus(1);
                    communityNewScope.setCreateDate(new Date());
                    communityNewScope.setOperateDate(new Date());
                    communityNewScope.setCreatePerson(communityNewsDto.getReleasePerson());
                    communityNewScope.setOperatePerson(communityNewsDto.getReleasePerson());
                    this.announcementScopeRepository.addOrUpdateCommunitNews(communityNewScope);
                }
            }
        }
        //单个项目
        if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
            for (int i = 0; i < projectList.size(); i++) {
                String project = projectList.get(i);
                if (!StringUtils.isEmpty(project)) {
                    communityNewScope = new CommunityNewScope();
                    communityNewScope.setId(IdGen.uuid());
                    communityNewScope.setCommunityDetailId(communityNewsEntity.getId());
                    //通过项目Code绑定城市
                    List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                    if (!list.isEmpty() && list.size() > 0){
                        communityNewScope.setCity(list.get(0)[1].toString());
                        communityNewScope.setCityId(list.get(0)[0].toString());
                    }
                    communityNewScope.setProjectName(project);
                    communityNewScope.setProjectId(projectIds.get(i));
                    communityNewScope.setScope("3");
                    communityNewScope.setStatus(1);
                    communityNewScope.setCreateDate(new Date());
                    communityNewScope.setOperateDate(new Date());
                    communityNewScope.setCreatePerson(communityNewsDto.getReleasePerson());
                    communityNewScope.setOperatePerson(communityNewsDto.getReleasePerson());
                    this.announcementScopeRepository.addOrUpdateCommunitNews(communityNewScope);
                }
            }
        }
        List<Object[]> announcementScopes = announcementScopeRepository.queryByCommunitNewsId(communityNewsDto.getId());
        //项目
        String projectInScope = "";
        for (Object[] announcementScope : announcementScopes) {
            if (null != announcementScope[3])
                projectInScope = projectInScope + announcementScope[3].toString() + ",";
        }
        if (!StringUtils.isEmpty(projectInScope)){
            projectInScope = StringUtils.substring(projectInScope, 0, projectInScope.length() - 1);
        }
        if(state.equals("add")){
            //记录日志
            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("资讯管理-品牌资讯");//功能
            infoReleaseLog.setThisType("新增");//操作类型
            infoReleaseLog.setLogContent(communityNewsEntity.getTitle());//发布内容
       /* HouseProjectEntity houseProjectEntity=houseProjectRepository.getProjectByCode(projectCode);
        if(houseProjectEntity!=null){
            infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
        }else{
            infoReleaseLog.setAsscommunity("");//关联社区;
        }*/
            infoReleaseLog.setAsscommunity(projectInScope);//关联社区;
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        }else{
            //记录日志
            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("资讯管理-品牌资讯");//功能
            infoReleaseLog.setThisType("修改");//操作类型
            infoReleaseLog.setLogContent(communityNewsEntity.getTitle());//发布内容
            infoReleaseLog.setAsscommunity(projectInScope);//关联社区;
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        }
    }

    /**==============================================================================================*/
    @Override
    public <E> List<CommunityNewsDto> queryAllByPage(CommunityNewsDto communityNewsDto, WebPage webPage) throws GeneralException {
        //封装检索条件
        Map<String,Object> params = new HashMap<>();
        try {
            CommunityNewsEntity communityNewsEntity = new CommunityNewsEntity();
            List<CommunityNewsDto> communityNewsDtos = new ArrayList<CommunityNewsDto>();

            //数据处理转换为
            BeanUtils.copyProperties(communityNewsEntity, communityNewsDto);

            //设置查询时间段
            Date staDate = null;
            Date endDate = null;
            if (!StringUtil.isEmpty(communityNewsDto.getStaDate())) {
                staDate = DateUtils.parse(communityNewsDto.getStaDate(), DateUtils.FORMAT_SHORT);
            }
            if (!StringUtil.isEmpty(communityNewsDto.getEndDate())) {
                endDate = DateUtils.parse(communityNewsDto.getEndDate(), DateUtils.FORMAT_SHORT);
            }

            //设置用户权限范围(单位项目)
            String roleScopes = "";
            List<Map<String, Object>> roleScopeList = communityNewsDto.getRoleScopeList();
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

            if (null != communityNewsDto.getScopeId() && !"".equals(communityNewsDto.getScopeId())){
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(communityNewsDto.getScopeId());
                String cityProjects = "";
                for (Object[] project : projectList) {
                    if (!cityProjects.contains(project[0].toString())) {
                        cityProjects += "'" + project[0].toString() + "',";
                    }
                }
                params.put("cityProjects",cityProjects);                    //城市下所有项目,以城市为单位检索
            }
            //参数封装
            params.put("projectCode",communityNewsDto.getProjectCode());         //项目
            params.put("communityNewsEntity",communityNewsEntity);
            params.put("staDate",staDate);
            params.put("endDate",endDate);
            params.put("roleScopes",roleScopes);        //权限范围

//            List<CommunityNewsEntity> communityNewsEntitys = this.communityNewsRespository.queryAllByPage(communityNewsEntity, webPage, staDate, endDate,roleScopes);
            List<CommunityNewsEntity> communityNewsEntitys = this.communityNewsRespository.queryAllByPage(params,webPage);

            //遍历DTO
            CommunityNewsDto temp = null;
            for (CommunityNewsEntity newsEntity : communityNewsEntitys) {
                temp = new CommunityNewsDto();
                BeanUtils.copyProperties(temp, newsEntity);
                //设置发布时间格式
                temp.setReleaseDate(DateUtils.format(newsEntity.getReleaseDate(),DateUtils.FORMAT_SHORT));
                //查询项目发布范围
                String projects = "";
                CommunityNewScope communityNewScope = null;
                List<CommunityNewScope> communityNewScopes = communityNewsRespository.getScopeByCommunityNew(temp.getId());
                for (int i=0,length=communityNewScopes.size(); i<length; i++){
                    communityNewScope = communityNewScopes.get(i);
                    if (null != communityNewScope.getProjectName() && !"".equals(communityNewScope.getProjectName())){
                        projects += communityNewScope.getProjectName() + ",";
                    }
                }
                temp.setProjects(projects);
                communityNewsDtos.add(temp);
            }
            return communityNewsDtos;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new GeneralException("100", "无效的链接异常");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new GeneralException("100", "无效目标异常，BeanUtiles转换属性出错");
        }
    }

    @Override
    public <E> void deleteNews(CommunityNewsDto communityNewsDto) {
        CommunityNewsEntity communityNewsEntity = this.communityNewsRespository.get(CommunityNewsEntity.class, communityNewsDto.getId());
        communityNewsEntity.setStatus(0);
        this.communityNewsRespository.saveOrUpdateNews(communityNewsEntity);
    }

    @Override
    public <E> void deleteNews(UserPropertyStaffEntity user, CommunityNewsDto communityNewsDto) {
        CommunityNewsEntity communityNewsEntity = this.communityNewsRespository.get(CommunityNewsEntity.class, communityNewsDto.getId());
        communityNewsEntity.setStatus(0);
        this.communityNewsRespository.saveOrUpdateNews(communityNewsEntity);

        List<Object[]> announcementScopes = announcementScopeRepository.queryByCommunitNewsId(communityNewsDto.getId());
        String projectInScope = "";
        for (Object[] announcementScope : announcementScopes) {
            if (null != announcementScope[3])
                projectInScope = projectInScope + announcementScope[3].toString() + ",";
        }
        if (!StringUtils.isEmpty(projectInScope)){
            projectInScope = StringUtils.substring(projectInScope, 0, projectInScope.length() - 1);
        }
        //记录日志
        InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
        infoReleaseLog.setLogId(IdGen.uuid());
        infoReleaseLog.setLogTime(new Date());//注册日期
        infoReleaseLog.setUserName(user.getStaffName());//用户昵称
        infoReleaseLog.setUserMobile(user.getMobile());//手机号
        infoReleaseLog.setThisSection("服务");//版块
        infoReleaseLog.setThisFunction("资讯管理-品牌资讯");//功能
        infoReleaseLog.setThisType("删除");//操作类型
        infoReleaseLog.setLogContent(communityNewsEntity.getTitle());//发布内容
        infoReleaseLog.setAsscommunity(projectInScope);//关联社区;
        systemLogRepository.addInfoReleaseLog(infoReleaseLog);

    }

    @Override
    public <E> E get(Class<E> entityClass, Serializable id) {
        return this.communityNewsRespository.get(entityClass, id);
    }


    @Override
    public ApiResult queryNewTitles(UserInfoEntity userInfo) throws GeneralException {

        List<Map> maps = new ArrayList<Map>();
        try {

            List<CommunityNewsEntity> communityNewsEntitys = communityNewsRespository.queryNewTitles();

            for (CommunityNewsEntity newsEntity : communityNewsEntitys) {
                Map map = new HashMap();
                map.put("newsId",newsEntity.getId());
                map.put("title", newsEntity.getTitle());
                map.put("newsImg", newsEntity.getNewsImg());
                maps.add(map);
            }
            //调用点击人统计
            String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
            if(userInfo==null){
                userInfo=new UserInfoEntity();
                userInfo.setUserId("1");
            }
            ClickUsersEntity clickUserEntity = clickUserRepository.getTotalInfo(dateNow, "1", userInfo.getUserId());
            if(clickUserEntity==null){
                ClickUsersEntity clickUser=new ClickUsersEntity();
                clickUser.setId(IdGen.uuid());
                clickUser.setCreateDate(new Date());
                clickUser.setClicks(1);
                clickUser.setUserId(userInfo.getUserId());
                clickUser.setForeignId("1");
                clickUserRepository.create(clickUser);
            }else{
                clickUserEntity.setClicks(clickUserEntity.getClicks() + 1);
                clickUserRepository.update(clickUserEntity);
            }

            ApiResult apiResult = new SuccessApiResult(maps);
            return apiResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorApiResult("数据异常");
        }
    }

    /**
     * Excel导出
     * @param user
     * @param httpServletResponse
     * @param webPage
     * @return
     * @throws IOException
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user, HttpServletResponse httpServletResponse,CommunityNewsDto communityNewsDto, WebPage webPage,HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        webPage = null;//需求：导出全部数据，而不是当前页数据
        List<CommunityNewsDto> communityNewsDtos =
                this.queryAllByPage(communityNewsDto, webPage);
        //创建Sheet页
        XSSFSheet sheet = workBook.createSheet("品牌资讯");

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

        String[] titles = {"序号", "新闻标题", "发布人", "新闻类型", "状态", "发布时间"};

        XSSFRow headRow = sheet.createRow(0);

        if(communityNewsDtos!=null&&communityNewsDtos.size()>0){
            communityNewsDtos.forEach(ommunityNewsDto -> {
                XSSFCell cell = null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                for (int i = 0; i < communityNewsDtos.size(); i++) {
                    XSSFRow bodyRow = sheet.createRow(i + 1);
                    CommunityNewsDto resultCommunityNewsDto = communityNewsDtos.get(i);

                    cell = bodyRow.createCell(0);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(i + 1);//序号

                    cell = bodyRow.createCell(1);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(resultCommunityNewsDto.getTitle());//新闻标题

                    cell = bodyRow.createCell(2);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(resultCommunityNewsDto.getReleasePerson());//发布人

                    cell = bodyRow.createCell(3);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(resultCommunityNewsDto.getType() != null ?
                            (resultCommunityNewsDto.getType()==2 ? "新闻资讯" : "金茂品牌宣传") : "");//新闻类型

                    cell = bodyRow.createCell(4);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(resultCommunityNewsDto.getReleaseStatus() != null ?
                            (resultCommunityNewsDto.getReleaseStatus()==0 ? "未发布" : "已发布") : "");//状态

                    cell = bodyRow.createCell(5);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(resultCommunityNewsDto.getReleaseDate() != null ?
                            resultCommunityNewsDto.getReleaseDate() : "");//发布时间

                }
            });
            try {
                //String fileName = new String(("品牌资讯列表").getBytes(), "ISO8859-1");
                String fileName = new String(("品牌资讯列表").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("品牌资讯列表", "UTF8");
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


}
