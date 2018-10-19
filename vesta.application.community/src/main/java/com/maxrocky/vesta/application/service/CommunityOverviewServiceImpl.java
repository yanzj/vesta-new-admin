package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.CommunityOverviewDto;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.AnnouncementRepository;
import com.maxrocky.vesta.domain.repository.CommunityOverviewRepository;
import com.maxrocky.vesta.domain.repository.SystemLogRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:10
 * Describe:
 */
@Service
public class CommunityOverviewServiceImpl extends BaseServiceImpl<CommunityOverviewEntity> implements CommunityOverviewService {

    @Autowired
    CommunityOverviewRepository communityOverviewRepository;
    @Autowired
    ImgService imgService;
    @Autowired
    AnnouncementRepository announcementRepository;
    @Autowired
    SystemLogRepository systemLogRepository;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;


    /**
     * Describe:根据计划id获取信息:分页查询批次信息
     * CreateBy:yifan
     *
     * @param communityOverviewDto
     * @param webPage
     * @return CommunityOverviewDto
     * @throws Exception
     */
    @Override
    public List<CommunityOverviewDto> queryAllByPage(CommunityOverviewDto communityOverviewDto, WebPage webPage) throws Exception {
        {
            //封装条件
            Map<String,Object> params = new HashMap<>();
            /*  查询条件传递方式变更
            CommunityOverviewEntity communityOverviewEntity = new CommunityOverviewEntity();
            //BUG规避：orderDate引起的类型转换错误
            communityOverviewDto.setOrderDate(null);
            //数据处理转换为
            BeanUtils.copyProperties(communityOverviewEntity, communityOverviewDto);
            //设置tag
            communityOverviewEntity.setTypes(communityOverviewDto.getTypes());
            //设置项目名称
            communityOverviewEntity.setName(communityOverviewDto.getProjectCode());
            */
            //设置用户权限范围(单位项目)
            String roleScopes = "";
            List<Map<String, Object>> roleScopeList = communityOverviewDto.getRoleScopeList();
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
            params.put("roleScopes",roleScopes);        //权限范围
            if (null != communityOverviewDto.getScopeId() && !"".equals(communityOverviewDto.getScopeId())){
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(communityOverviewDto.getScopeId());
                String cityProjects = "";
                for (Object[] project : projectList) {
                    if (!cityProjects.contains(project[0].toString())) {
                        cityProjects += "'" + project[0].toString() + "',";
                    }
                }
                params.put("cityProjects",cityProjects);                    //城市下所有项目,以城市为单位检索
            }
            params.put("projectCode",communityOverviewDto.getProjectCode());       //项目编码
            params.put("priceAverage",communityOverviewDto.getPriceAverage());      //楼盘参考价
            params.put("types",communityOverviewDto.getTypes());        //标签
            params.put("phone",communityOverviewDto.getPhone());        //售楼电话
            //设置查询时间段
            Date staDate = null;
            Date endDate = null;
            if (!StringUtil.isEmpty(communityOverviewDto.getStaDate())) {
                staDate = DateUtils.parse(communityOverviewDto.getStaDate(), DateUtils.FORMAT_SHORT);
                params.put("staDate",staDate);
            }
            if (!StringUtil.isEmpty(communityOverviewDto.getEndDate())) {
                endDate = DateUtils.parse(communityOverviewDto.getEndDate(), DateUtils.FORMAT_SHORT);
                params.put("endDate",endDate);
            }

//            List<CommunityOverviewEntity> communityOverviewEntitys = this.communityOverviewRepository.queryAllByPage(communityOverviewEntity, webPage, staDate, endDate,roleScopes);
            List<CommunityOverviewEntity> communityOverviewEntitys = this.communityOverviewRepository.queryAllByPage(params, webPage);
            //遍历DTO
            List<CommunityOverviewDto> communityOverviewDtos = new ArrayList<CommunityOverviewDto>();
            CommunityOverviewDto temp = null;
            for (CommunityOverviewEntity entity : communityOverviewEntitys) {
                temp = new CommunityOverviewDto();
                BeanUtils.copyProperties(temp, entity);
                //设置tag
                temp.setTypes(entity.getTypes());
                //设置项目名称
                temp.setProjectName(entity.getName());
                //设置发布日期
                temp.setReleaseDate(DateUtils.format(entity.getReleaseDate(), "yyyy-MM-dd"));
                //设置状态
                temp.setReleaseStatus(entity.getReleaseStatus());
                //设置排序时间显示
                temp.setOrderDate(DateUtils.format(entity.getOrderDate(),DateUtils.FORMAT_SHORT));
                //追加项目发布范围查询(展示)    Wyd20170406
                List<Map<String, Object>> projects = communityOverviewRepository.queryProjectByCommunityId(entity.getId());
                String projectList = "";
                for (int i=0,length=projects.size(); i<length; i++){
                    projectList += projects.get(i).get("projectName").toString()+",";
                }
                temp.setProjectList(projectList);
                communityOverviewDtos.add(temp);
            }
            return communityOverviewDtos;
        }
    }

    /**
     * 带条件查询
     *
     * @param communityOverviewDto
     * @return
     * @throws Exception
     */
    @Override
    public List<CommunityOverviewDto> queryAllByParam(CommunityOverviewDto communityOverviewDto) throws Exception {
        return null;
    }

    /**
     * 更新状态
     *
     * @param communityOverviewDto
     */
    @Override
    public void saveOrUpdateStatus(CommunityOverviewDto communityOverviewDto) throws Exception {
        if (StringUtil.isEmpty(communityOverviewDto.getId())) {
            CommunityOverviewEntity communityOverviewEntity = this.communityOverviewRepository.get(CommunityOverviewEntity.class, communityOverviewDto.getId());
            org.springframework.beans.BeanUtils.copyProperties(communityOverviewDto, communityOverviewEntity, getNullPropertyNames(communityOverviewDto));
            communityOverviewRepository.saveOrUpdate(communityOverviewEntity);
        }
    }

    /**
     * 保存或者更新
     *
     * @param communityOverviewDto
     * @throws Exception
     */
    @Override
    public void saveOrUpdateOverview(CommunityOverviewDto communityOverviewDto) throws Exception {
        //根据id获取实体类
        CommunityOverviewEntity communityOverviewEntity = this.get(CommunityOverviewEntity.class, communityOverviewDto.getId());
        if (communityOverviewEntity == null) {
            communityOverviewEntity = new CommunityOverviewEntity();
            //设置id
            communityOverviewEntity.setId(IdGen.uuid());
            //创建时间
            communityOverviewEntity.setCreateDate(new Date());
            //创建人
            communityOverviewEntity.setCreatePerson(communityOverviewDto.getReleasePerson());
            //排序_根据日期排序
            communityOverviewEntity.setOrderDate(new Date());
            //获取最大ordernum进行增加
            //Integer orderNum = this.communityOverviewRepository.getMaxOrderNum();
            //communityOverviewEntity.setOrderNum(orderNum+1);
        } else {
            //修改人
            communityOverviewEntity.setOperator(communityOverviewDto.getReleasePerson());
            //排序_根据日期排序
            communityOverviewEntity.setOrderDate(DateUtils.parse(communityOverviewDto.getOrderDate(),DateUtils.FORMAT_SHORT));
        }
        if (communityOverviewDto.getReleaseStatus() == 1) {
            //发布人
            communityOverviewEntity.setReleasePerson(communityOverviewDto.getReleasePerson());
            //发布时间
            communityOverviewEntity.setReleaseDate(new Date());
        }
        //修改时间
        communityOverviewEntity.setOperatorDate(new Date());
        //发布状态
        communityOverviewEntity.setReleaseStatus(communityOverviewDto.getReleaseStatus());
        //状态
        communityOverviewEntity.setStatus(1);
        //项目名称
        communityOverviewEntity.setName(communityOverviewDto.getName());
        //均价
        communityOverviewEntity.setPriceAverage(communityOverviewDto.getPriceAverage());
        //标签
        communityOverviewEntity.setTypes(communityOverviewDto.getTypes());
        //优惠信息
        communityOverviewEntity.setFavorable(communityOverviewDto.getFavorable());
        //售楼电话
        communityOverviewEntity.setPhone(communityOverviewDto.getPhone());
        //H5链接
        communityOverviewEntity.setUrl(communityOverviewDto.getUrl());
        //城市
        communityOverviewEntity.setCity(communityOverviewDto.getCity());
        //全景图_图片上传
        String fileName = imgService.uploadAdminImage(communityOverviewDto.getHomePageimgpath(), ImgType.ACTIVITY);
        //图片地址特殊处理
        //String urlTitle = "http://211.94.93.223/images/";
        String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
        //fileName = urlTitle + fileName.replace("/opt/image.server/images/images_source/", "");
        fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
        if (fileName.equals("")) {
            fileName = "默认图";
        }
        if (!fileName.equals(ImageConfig.PIC_OSS_ADMIN_URL)) {
            communityOverviewEntity.setPanoramaImg(fileName);
        }

        this.saveOrUpdate(communityOverviewEntity);
        //保存或更新区域范围
        //通过楼盘Id清空区域项目信息
        communityOverviewRepository.deleteCommunityOverviewScope(communityOverviewEntity.getId());
        //城市、项目列表数据准备
        List<String> projectIds = Arrays.asList(communityOverviewDto.getProjectIds().split(","));
        List<String> projectList = Arrays.asList(communityOverviewDto.getProjectList().split(","));
        List<String> cityIds = Arrays.asList(communityOverviewDto.getCityIds().split(","));
        List<String> cityList = Arrays.asList(communityOverviewDto.getCityList().split(","));

        if (cityIds.size() == 1 && "0".equals(cityIds.get(0))){
            //全部城市
            //获取所有城市下所有项目
            List<Object[]> projects = announcementRepository.listAllProject();
            for (int i = 0; i < projects.size(); i++){
                CommunityOverviewScopeEntity communityOverviewScopeEntity = new CommunityOverviewScopeEntity();
                communityOverviewScopeEntity.setId(IdGen.uuid());
                communityOverviewScopeEntity.setCommunityOverviewId(communityOverviewEntity.getId());
                communityOverviewScopeEntity.setProjectCode((String) projects.get(i)[0]);
                communityOverviewScopeEntity.setProjectName((String) projects.get(i)[1]);
                communityOverviewScopeEntity.setStatus(0);
                communityOverviewScopeEntity.setCreateOn(new Date());
                communityOverviewScopeEntity.setCreateBy(communityOverviewDto.getReleasePerson());
                communityOverviewScopeEntity.setModifyOn(new Date());
                communityOverviewScopeEntity.setModifyBy(communityOverviewDto.getReleasePerson());
                communityOverviewRepository.saveOrUpdate(communityOverviewScopeEntity);
            }
        }else if (cityIds.size() > 0 && projectIds.size() == 1 && "0".equals(projectIds.get(0))){
            //全部项目
            //遍历城市列表，获取每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++){
                List<Object[]> projects = announcementRepository.listProject(cityIds.get(i));
                for (int j = 0; j<projects.size(); j++){
                    CommunityOverviewScopeEntity communityOverviewScopeEntity = new CommunityOverviewScopeEntity();
                    communityOverviewScopeEntity.setId(IdGen.uuid());
                    communityOverviewScopeEntity.setCommunityOverviewId(communityOverviewEntity.getId());
                    communityOverviewScopeEntity.setProjectCode((String) projects.get(j)[0]);
                    communityOverviewScopeEntity.setProjectName((String) projects.get(j)[1]);
                    communityOverviewScopeEntity.setStatus(0);
                    communityOverviewScopeEntity.setCreateOn(new Date());
                    communityOverviewScopeEntity.setCreateBy(communityOverviewDto.getReleasePerson());
                    communityOverviewScopeEntity.setModifyOn(new Date());
                    communityOverviewScopeEntity.setModifyBy(communityOverviewDto.getReleasePerson());
                    communityOverviewRepository.saveOrUpdate(communityOverviewScopeEntity);
                }
            }
        }else if (cityIds.size() > 0 && !"0".equals(cityIds.get(0)) && projectIds.size() > 0 && !"0".equals(projectIds.get(0))){
            //遍历项目列表
            for (int i = 0; i < projectIds.size(); i++){
                CommunityOverviewScopeEntity communityOverviewScopeEntity = new CommunityOverviewScopeEntity();
                communityOverviewScopeEntity.setId(IdGen.uuid());
                communityOverviewScopeEntity.setCommunityOverviewId(communityOverviewEntity.getId());
                communityOverviewScopeEntity.setProjectCode(projectIds.get(i));
                communityOverviewScopeEntity.setProjectName(projectList.get(i));
                communityOverviewScopeEntity.setStatus(0);
                communityOverviewScopeEntity.setCreateOn(new Date());
                communityOverviewScopeEntity.setCreateBy(communityOverviewDto.getReleasePerson());
                communityOverviewScopeEntity.setModifyOn(new Date());
                communityOverviewScopeEntity.setModifyBy(communityOverviewDto.getReleasePerson());
                communityOverviewRepository.saveOrUpdate(communityOverviewScopeEntity);
            }
        }
    }

    @Override
    public void saveOrUpdateOverview(UserPropertyStaffEntity user, CommunityOverviewDto communityOverviewDto)  {
        //根据id获取实体类
        CommunityOverviewEntity communityOverviewEntity = this.get(CommunityOverviewEntity.class, communityOverviewDto.getId());
        String state="";
        if (communityOverviewEntity == null) {
            communityOverviewEntity = new CommunityOverviewEntity();
            //设置id
            communityOverviewEntity.setId(IdGen.uuid());
            //创建时间
            communityOverviewEntity.setCreateDate(new Date());
            //创建人
            communityOverviewEntity.setCreatePerson(communityOverviewDto.getReleasePerson());
            //排序_根据日期排序
            communityOverviewEntity.setOrderDate(new Date());
            //获取最大ordernum进行增加
            //Integer orderNum = this.communityOverviewRepository.getMaxOrderNum();
            //communityOverviewEntity.setOrderNum(orderNum+1);
            state="add";
        } else {
            //修改人
            communityOverviewEntity.setOperator(communityOverviewDto.getReleasePerson());
            //排序_根据日期排序
            communityOverviewEntity.setOrderDate(DateUtils.parse(communityOverviewDto.getOrderDate(),DateUtils.FORMAT_SHORT));
        }
        if (communityOverviewDto.getReleaseStatus() == 1) {
            //发布人
            communityOverviewEntity.setReleasePerson(communityOverviewDto.getReleasePerson());
            //发布时间
            communityOverviewEntity.setReleaseDate(new Date());
        }
        //修改时间
        communityOverviewEntity.setOperatorDate(new Date());
        //发布状态
        communityOverviewEntity.setReleaseStatus(communityOverviewDto.getReleaseStatus());
        //状态
        communityOverviewEntity.setStatus(1);
        //项目名称
        communityOverviewEntity.setName(communityOverviewDto.getName());
        //项目地址
        communityOverviewEntity.setAddress(communityOverviewDto.getAddress());
        //项目介绍
        communityOverviewEntity.setIntroduction(communityOverviewDto.getIntroduction());
        //配套设施描述
        communityOverviewEntity.setPeriphery(communityOverviewDto.getPeriphery());
        //均价
        communityOverviewEntity.setPriceAverage(communityOverviewDto.getPriceAverage());
        //标签
        communityOverviewEntity.setTypes(communityOverviewDto.getTypes());
        //优惠信息
        communityOverviewEntity.setFavorable(communityOverviewDto.getFavorable());
        //售楼电话
        communityOverviewEntity.setPhone(communityOverviewDto.getPhone());
        //H5链接
        communityOverviewEntity.setUrl(communityOverviewDto.getUrl());
        //城市
        communityOverviewEntity.setCity(communityOverviewDto.getCity());
        //全景图
        communityOverviewEntity.setPanoramaImg(communityOverviewDto.getHomePageimgUrl());
//        String fileName = imgService.uploadAdminImage(communityOverviewDto.getHomePageimgpath(), ImgType.ACTIVITY);
        //图片地址特殊处理
//        String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
//        fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
//        if (fileName.equals("")) {
//            fileName = "默认图";
//        }
//        if (!fileName.equals(ImageConfig.PIC_OSS_ADMIN_URL)) {
//            communityOverviewEntity.setPanoramaImg(fileName);
//        }
        this.saveOrUpdate(communityOverviewEntity);
        //保存或更新楼盘图片
        CommunityOverviewImgEntity communityOverviewImgEntity = null;
        //户型图
        List<String> floorPlanUrlList = communityOverviewDto.getFloorPlanUrlList();
        if (null != floorPlanUrlList && floorPlanUrlList.size() > 0){
            communityOverviewRepository.deleteImgByOverview(communityOverviewEntity.getId(),CommunityOverviewImgEntity.IMG_TYPE_FLOOR_PLAN);
            for (String floorPlanUrl : floorPlanUrlList){
                communityOverviewImgEntity = new CommunityOverviewImgEntity();
                communityOverviewImgEntity.setId(IdGen.uuid());
                communityOverviewImgEntity.setCreateOn(new Date());
                communityOverviewImgEntity.setImgType(CommunityOverviewImgEntity.IMG_TYPE_FLOOR_PLAN);
                communityOverviewImgEntity.setOverviewId(communityOverviewEntity.getId());
                communityOverviewImgEntity.setImgUrl(floorPlanUrl);
                communityOverviewRepository.saveOrUpdate(communityOverviewImgEntity);
            }
        }
        //总平面图
        List<String> masterPlanUrlList = communityOverviewDto.getMasterPlanUrlList();
        if (null != masterPlanUrlList && masterPlanUrlList.size() > 0){
            communityOverviewRepository.deleteImgByOverview(communityOverviewEntity.getId(),CommunityOverviewImgEntity.IMG_TYPE_MASTER_PLAN);
            for (String masterPlanUrl : masterPlanUrlList){
                communityOverviewImgEntity = new CommunityOverviewImgEntity();
                communityOverviewImgEntity.setId(IdGen.uuid());
                communityOverviewImgEntity.setCreateOn(new Date());
                communityOverviewImgEntity.setImgType(CommunityOverviewImgEntity.IMG_TYPE_MASTER_PLAN);
                communityOverviewImgEntity.setOverviewId(communityOverviewEntity.getId());
                communityOverviewImgEntity.setImgUrl(masterPlanUrl);
                communityOverviewRepository.saveOrUpdate(communityOverviewImgEntity);
            }
        }
        //室内设计图
        List<String> interiorDesignList = communityOverviewDto.getInteriorDesignList();
        if (null != interiorDesignList && interiorDesignList.size() > 0){
            communityOverviewRepository.deleteImgByOverview(communityOverviewEntity.getId(),CommunityOverviewImgEntity.IMG_TYPE_INTERIOR_DESIGN);
            for (String interiorDesign : interiorDesignList){
                communityOverviewImgEntity = new CommunityOverviewImgEntity();
                communityOverviewImgEntity.setId(IdGen.uuid());
                communityOverviewImgEntity.setCreateOn(new Date());
                communityOverviewImgEntity.setImgType(CommunityOverviewImgEntity.IMG_TYPE_INTERIOR_DESIGN);
                communityOverviewImgEntity.setOverviewId(communityOverviewEntity.getId());
                communityOverviewImgEntity.setImgUrl(interiorDesign);
                communityOverviewRepository.saveOrUpdate(communityOverviewImgEntity);
            }
        }
        //园林设计图
        List<String> gardenDesignList = communityOverviewDto.getGardenDesignList();
        if (null != gardenDesignList && gardenDesignList.size() > 0){
            communityOverviewRepository.deleteImgByOverview(communityOverviewEntity.getId(),CommunityOverviewImgEntity.IMG_TYPE_GARDEN_DESIGN);
            for (String gardenDesign : gardenDesignList){
                communityOverviewImgEntity = new CommunityOverviewImgEntity();
                communityOverviewImgEntity.setId(IdGen.uuid());
                communityOverviewImgEntity.setCreateOn(new Date());
                communityOverviewImgEntity.setImgType(CommunityOverviewImgEntity.IMG_TYPE_GARDEN_DESIGN);
                communityOverviewImgEntity.setOverviewId(communityOverviewEntity.getId());
                communityOverviewImgEntity.setImgUrl(gardenDesign);
                communityOverviewRepository.saveOrUpdate(communityOverviewImgEntity);
            }
        }
        //配套设施图
        List<String> supportingFacilitiesList = communityOverviewDto.getSupportingFacilitiesList();
        if (null != supportingFacilitiesList && supportingFacilitiesList.size() > 0){
            communityOverviewRepository.deleteImgByOverview(communityOverviewEntity.getId(),CommunityOverviewImgEntity.IMG_TYPE_SUPPORTING_FACILITIES);
            for (String supportingFacilities : supportingFacilitiesList){
                communityOverviewImgEntity = new CommunityOverviewImgEntity();
                communityOverviewImgEntity.setId(IdGen.uuid());
                communityOverviewImgEntity.setCreateOn(new Date());
                communityOverviewImgEntity.setImgType(CommunityOverviewImgEntity.IMG_TYPE_SUPPORTING_FACILITIES);
                communityOverviewImgEntity.setOverviewId(communityOverviewEntity.getId());
                communityOverviewImgEntity.setImgUrl(supportingFacilities);
                communityOverviewRepository.saveOrUpdate(communityOverviewImgEntity);
            }
        }
        //保存或更新区域范围
        //通过楼盘Id清空区域项目信息
        communityOverviewRepository.deleteCommunityOverviewScope(communityOverviewEntity.getId());
        //城市、项目列表数据准备
        List<String> projectIds = Arrays.asList(communityOverviewDto.getProjectIds().split(","));
        List<String> projectList = Arrays.asList(communityOverviewDto.getProjectList().split(","));
        List<String> cityIds = Arrays.asList(communityOverviewDto.getCityIds().split(","));
        List<String> cityList = Arrays.asList(communityOverviewDto.getCityList().split(","));
        if (cityIds.size() == 1 && "0".equals(cityIds.get(0))){
            //全部城市
            //获取所有城市下所有项目
            List<Object[]> projects = announcementRepository.listAllProject();
            for (int i = 0; i < projects.size(); i++){
                CommunityOverviewScopeEntity communityOverviewScopeEntity = new CommunityOverviewScopeEntity();
                communityOverviewScopeEntity.setId(IdGen.uuid());
                communityOverviewScopeEntity.setCommunityOverviewId(communityOverviewEntity.getId());
                communityOverviewScopeEntity.setProjectCode((String) projects.get(i)[0]);
                communityOverviewScopeEntity.setProjectName((String) projects.get(i)[1]);
                communityOverviewScopeEntity.setStatus(0);
                communityOverviewScopeEntity.setCreateOn(new Date());
                communityOverviewScopeEntity.setCreateBy(communityOverviewDto.getReleasePerson());
                communityOverviewScopeEntity.setModifyOn(new Date());
                communityOverviewScopeEntity.setModifyBy(communityOverviewDto.getReleasePerson());
                communityOverviewRepository.saveOrUpdate(communityOverviewScopeEntity);
            }
        }else if (cityIds.size() > 0 && projectIds.size() == 1 && "0".equals(projectIds.get(0))){
            //全部项目
            //遍历城市列表，获取每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++){
                List<Object[]> projects = announcementRepository.listProject(cityIds.get(i));
                for (int j = 0; j<projects.size(); j++){
                    CommunityOverviewScopeEntity communityOverviewScopeEntity = new CommunityOverviewScopeEntity();
                    communityOverviewScopeEntity.setId(IdGen.uuid());
                    communityOverviewScopeEntity.setCommunityOverviewId(communityOverviewEntity.getId());
                    communityOverviewScopeEntity.setProjectCode((String) projects.get(j)[0]);
                    communityOverviewScopeEntity.setProjectName((String) projects.get(j)[1]);
                    communityOverviewScopeEntity.setStatus(0);
                    communityOverviewScopeEntity.setCreateOn(new Date());
                    communityOverviewScopeEntity.setCreateBy(communityOverviewDto.getReleasePerson());
                    communityOverviewScopeEntity.setModifyOn(new Date());
                    communityOverviewScopeEntity.setModifyBy(communityOverviewDto.getReleasePerson());
                    communityOverviewRepository.saveOrUpdate(communityOverviewScopeEntity);
                }
            }
        }else if (cityIds.size() > 0 && !"0".equals(cityIds.get(0)) && projectIds.size() > 0 && !"0".equals(projectIds.get(0))){
            //遍历项目列表
            for (int i = 0; i < projectIds.size(); i++){
                CommunityOverviewScopeEntity communityOverviewScopeEntity = new CommunityOverviewScopeEntity();
                communityOverviewScopeEntity.setId(IdGen.uuid());
                communityOverviewScopeEntity.setCommunityOverviewId(communityOverviewEntity.getId());
                communityOverviewScopeEntity.setProjectCode(projectIds.get(i));
                communityOverviewScopeEntity.setProjectName(projectList.get(i));
                communityOverviewScopeEntity.setStatus(0);
                communityOverviewScopeEntity.setCreateOn(new Date());
                communityOverviewScopeEntity.setCreateBy(communityOverviewDto.getReleasePerson());
                communityOverviewScopeEntity.setModifyOn(new Date());
                communityOverviewScopeEntity.setModifyBy(communityOverviewDto.getReleasePerson());
                communityOverviewRepository.saveOrUpdate(communityOverviewScopeEntity);
            }
        }
        String projectNames="";
        List<Map<String, Object>> mapList = communityOverviewRepository.queryProjectByCommunityId(communityOverviewEntity.getId());
        for (int i = 0; i < mapList.size(); i++){
            Map<String, Object> map = mapList.get(i);
            projectNames = projectNames + (String) map.get("projectName") + ",";
        }
        if(state.equals("add")){
            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("资讯管理-金茂楼盘");//功能
            infoReleaseLog.setThisType("新增");//操作类型
            infoReleaseLog.setLogContent(communityOverviewEntity.getName());//发布内容
            infoReleaseLog.setAsscommunity(projectNames);//关联社区;
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        }else{
            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("资讯管理-金茂楼盘");//功能
            infoReleaseLog.setThisType("修改");//操作类型
            infoReleaseLog.setLogContent(communityOverviewEntity.getName());//发布内容
            infoReleaseLog.setAsscommunity(projectNames);//关联社区;
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        }
    }

    /**
     * 查詢金茂樓盤項目
     * @return
     */
    @Override
    public List<String> listProject() {
        List<String> list = this.communityOverviewRepository.listProject();
        return list;
    }

    /**
     * 获取null字段
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 通过楼盘Id查询区域范围列表信息
     * @param communityId 楼盘Id
     * @return List<Map<String,Object>>
     */
    public Map<String,Object> queryProjectByCommunityId(String communityId){
        Map<String,Object> resuleMap = new HashMap<>();
        String projectCodes = "";
        String projectNames = "";
        List<Map<String, Object>> mapList = communityOverviewRepository.queryProjectByCommunityId(communityId);
        for (int i = 0; i < mapList.size(); i++){
            Map<String, Object> map = mapList.get(i);
            projectCodes = projectCodes + (String) map.get("projectCode") + ",";
            projectNames = projectNames + (String) map.get("projectName") + ",";
        }
        resuleMap.put("projectCodes",projectCodes);
        resuleMap.put("projectNames",projectNames);
        return resuleMap;
    }

    /**
     * 通过项目Code集合检索城市列表信息
     * @param projectCodes  项目Code集合
     * @return Map<String,Object>
     */
    public Map<String,Object> queryCityByProjectIds(String projectCodes){
        Map<String,Object> resultMap = new HashMap<>();
        String cityIds = "";
        String cityNames = "";
        List<Map<String, Object>> mapList = communityOverviewRepository.queryCityByProjectIds(projectCodes);
        for (int i = 0; i < mapList.size(); i++){
            Map<String, Object> map = mapList.get(i);
            cityIds += map.get("cityId").toString() + ",";
            cityNames += map.get("cityName").toString() + ",";
        }
        resultMap.put("cityIds",cityIds);
        resultMap.put("cityNames",cityNames);
        return resultMap;
    }

    /**
     * 物理删除楼盘项目关系数据
     * @param communityId   楼盘Id
     */
    public void deleteCommunityOverviewScope(String communityId){
        communityOverviewRepository.deleteCommunityOverviewScope(communityId);
    }

    @Override
    public void deleteCommunityOverviewScope(UserPropertyStaffEntity user, String communityId) {
        CommunityOverviewEntity communityOverviewEntity = this.communityOverviewRepository.get(CommunityOverviewEntity.class, communityId);

        String projectNames="";
        List<Map<String, Object>> mapList = communityOverviewRepository.queryProjectByCommunityId(communityId);
        for (int i = 0; i < mapList.size(); i++){
            Map<String, Object> map = mapList.get(i);
            // projectCodes = projectCodes + (String) map.get("projectCode") + ",";
            projectNames = projectNames + (String) map.get("projectName") + ",";
        }
        InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
        infoReleaseLog.setLogId(IdGen.uuid());
        infoReleaseLog.setLogTime(new Date());//注册日期
        infoReleaseLog.setUserName(user.getStaffName());//用户昵称
        infoReleaseLog.setUserMobile(user.getMobile());//手机号
        infoReleaseLog.setThisSection("服务");//版块
        infoReleaseLog.setThisFunction("资讯管理-金茂楼盘");//功能
        infoReleaseLog.setThisType("删除");//操作类型
        if(communityOverviewEntity!=null){
            infoReleaseLog.setLogContent(communityOverviewEntity.getName());//发布内容
        }else{
            infoReleaseLog.setLogContent("");//发布内容
        }
       /* HouseProjectEntity houseProjectEntity=houseProjectRepository.getProjectByCode(projectCode);
        if(houseProjectEntity!=null){
            infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
        }else{
            infoReleaseLog.setAsscommunity("");//关联社区;
        }*/
        infoReleaseLog.setAsscommunity(projectNames);//关联社区;
        systemLogRepository.addInfoReleaseLog(infoReleaseLog);

        communityOverviewRepository.deleteCommunityOverviewScope(communityId);

    }

    /**
     * 导出Excel
     * @param user
     * @param httpServletResponse
     * @param communityOverviewDto
     * @param webPage
     * @return
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user, HttpServletResponse httpServletResponse, CommunityOverviewDto communityOverviewDto, WebPage webPage,HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        try {
            List<CommunityOverviewDto> communityOverviewDtos =
                    this.queryAllByPage(communityOverviewDto, webPage);

            //创建Sheet页
            XSSFSheet sheet = workBook.createSheet("金茂楼盘");

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

            String[] titles = {"序号", "项目名称", "楼盘参考价", "标签", "售楼电话", "项目开盘时间","发布时间","状态"};

            XSSFRow headRow = sheet.createRow(0);

            if(communityOverviewDtos!=null&&communityOverviewDtos.size()>0){
                communityOverviewDtos.forEach(communityOverview -> {
                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }
                    for (int i = 0; i < communityOverviewDtos.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        CommunityOverviewDto resultCommunityOverviewDto = communityOverviewDtos.get(i);

                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(resultCommunityOverviewDto.getProjectName());//项目名称

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(resultCommunityOverviewDto.getPriceAverage()!=null?
                                resultCommunityOverviewDto.getPriceAverage()+"元/m2":"");//楼盘参考价

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(resultCommunityOverviewDto.getTypes());//标签

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(resultCommunityOverviewDto.getPhone());//售楼电话

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(resultCommunityOverviewDto.getOrderDate());//项目开盘时间

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(resultCommunityOverviewDto.getReleaseDate());//发布时间

                        cell = bodyRow.createCell(7);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(resultCommunityOverviewDto.getReleaseStatus()!=null?
                                (resultCommunityOverviewDto.getReleaseStatus()==0?"未发布":"已发布"):"");//状态
                    }
                });
                try {
                    //String fileName = new String(("金茂楼盘列表").getBytes(), "ISO8859-1");
                    String fileName = new String(("金茂楼盘列表").getBytes(), "ISO8859-1");
                    String agent = httpServletRequest.getHeader("USER-AGENT");
                    if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                            && -1 != agent.indexOf("Trident")) {// ie

                        fileName = java.net.URLEncoder.encode("金茂楼盘列表", "UTF8");
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 通过金茂楼盘Id检索发布项目范围
     */
    public List<Map<String,Object>> getProjectScopeByCommunityOverviewId(String communityOverviewId){
        List<Map<String,Object>> projectList = new ArrayList<>();
        List<CommunityOverviewScopeEntity> communityOverviewScopeEntities = communityOverviewRepository.getScopeByCommunityOverview(communityOverviewId);
        if (communityOverviewScopeEntities.size() > 0){
            for (CommunityOverviewScopeEntity communityOverviewScopeEntity : communityOverviewScopeEntities){
                Map<String,Object> project = new HashMap<>();
                project.put("projectId",communityOverviewScopeEntity.getProjectCode());
                project.put("projectName",communityOverviewScopeEntity.getProjectName());
                projectList.add(project);
            }
        }
        return projectList;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 修改或保存金茂楼盘去编辑信息
    */
    @Override
    public void saveOrUpdateDetailEdit(UserPropertyStaffEntity userPropertystaffEntity, CommunityOverviewDto communityOverviewDto) {
        //根据id获取实体类
        CommunityOverviewEntity communityOverviewEntity = this.get(CommunityOverviewEntity.class, communityOverviewDto.getId());
        String state="";
        if (communityOverviewEntity == null) {
            communityOverviewEntity = new CommunityOverviewEntity();
            //设置id
            communityOverviewEntity.setId(IdGen.uuid());
            //创建时间
            communityOverviewEntity.setCreateDate(new Date());
            //创建人
            communityOverviewEntity.setCreatePerson(communityOverviewDto.getReleasePerson());
            //排序_根据日期排序
            communityOverviewEntity.setOrderDate(new Date());
            //获取最大ordernum进行增加
            //Integer orderNum = this.communityOverviewRepository.getMaxOrderNum();
            //communityOverviewEntity.setOrderNum(orderNum+1);
            state="add";
        } else {
            //修改人
            communityOverviewEntity.setOperator(communityOverviewDto.getReleasePerson());
            //排序_根据日期排序
            communityOverviewEntity.setOrderDate(DateUtils.parse(communityOverviewDto.getOrderDate(),DateUtils.FORMAT_SHORT));
        }
        if (communityOverviewDto.getReleaseStatus() == 1) {
            //发布人
            communityOverviewEntity.setReleasePerson(communityOverviewDto.getReleasePerson());
            //发布时间
            communityOverviewEntity.setReleaseDate(new Date());
        }
        //修改时间
        communityOverviewEntity.setOperatorDate(new Date());
        //发布状态
        communityOverviewEntity.setReleaseStatus(communityOverviewDto.getReleaseStatus());
        //状态
        communityOverviewEntity.setStatus(1);
        //项目名称
        communityOverviewEntity.setName(communityOverviewDto.getName());
        //均价
        communityOverviewEntity.setPriceAverage(communityOverviewDto.getPriceAverage());
        //标签
        communityOverviewEntity.setTypes(communityOverviewDto.getTypes());
        //优惠信息
        communityOverviewEntity.setFavorable(communityOverviewDto.getFavorable());
        //售楼电话
        communityOverviewEntity.setPhone(communityOverviewDto.getPhone());
        /*//H5链接
        communityOverviewEntity.setUrl(communityOverviewDto.getUrl());*/
        //城市
        communityOverviewEntity.setCity(communityOverviewDto.getCity());
        //全景图_图片上传
        String imgName = communityOverviewDto.getPanoramaImg();
        if (imgName.equals("")) {
            imgName = "默认图";
        }
        if (!imgName.equals(ImageConfig.PIC_OSS_ADMIN_URL)) {
            communityOverviewEntity.setPanoramaImg(imgName);
        }

        this.saveOrUpdate(communityOverviewEntity);
        //保存或更新区域范围
        //通过楼盘Id清空区域项目信息
        communityOverviewRepository.deleteCommunityOverviewScope(communityOverviewEntity.getId());
        //城市、项目列表数据准备
        List<String> projectIds = Arrays.asList(communityOverviewDto.getProjectIds().split(","));
        List<String> projectList = Arrays.asList(communityOverviewDto.getProjectList().split(","));
        List<String> cityIds = Arrays.asList(communityOverviewDto.getCityIds().split(","));
        List<String> cityList = Arrays.asList(communityOverviewDto.getCityList().split(","));

        if (cityIds.size() == 1 && "0".equals(cityIds.get(0))){
            //全部城市
            //获取所有城市下所有项目
            List<Object[]> projects = announcementRepository.listAllProject();
            for (int i = 0; i < projects.size(); i++){
                CommunityOverviewScopeEntity communityOverviewScopeEntity = new CommunityOverviewScopeEntity();
                communityOverviewScopeEntity.setId(IdGen.uuid());
                communityOverviewScopeEntity.setCommunityOverviewId(communityOverviewEntity.getId());
                communityOverviewScopeEntity.setProjectCode((String) projects.get(i)[0]);
                communityOverviewScopeEntity.setProjectName((String) projects.get(i)[1]);
                communityOverviewScopeEntity.setStatus(0);
                communityOverviewScopeEntity.setCreateOn(new Date());
                communityOverviewScopeEntity.setCreateBy(communityOverviewDto.getReleasePerson());
                communityOverviewScopeEntity.setModifyOn(new Date());
                communityOverviewScopeEntity.setModifyBy(communityOverviewDto.getReleasePerson());
                communityOverviewRepository.saveOrUpdate(communityOverviewScopeEntity);
            }
        }else if (cityIds.size() > 0 && projectIds.size() == 1 && "0".equals(projectIds.get(0))){
            //全部项目
            //遍历城市列表，获取每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++){
                List<Object[]> projects = announcementRepository.listProject(cityIds.get(i));
                for (int j = 0; j<projects.size(); j++){
                    CommunityOverviewScopeEntity communityOverviewScopeEntity = new CommunityOverviewScopeEntity();
                    communityOverviewScopeEntity.setId(IdGen.uuid());
                    communityOverviewScopeEntity.setCommunityOverviewId(communityOverviewEntity.getId());
                    communityOverviewScopeEntity.setProjectCode((String) projects.get(j)[0]);
                    communityOverviewScopeEntity.setProjectName((String) projects.get(j)[1]);
                    communityOverviewScopeEntity.setStatus(0);
                    communityOverviewScopeEntity.setCreateOn(new Date());
                    communityOverviewScopeEntity.setCreateBy(communityOverviewDto.getReleasePerson());
                    communityOverviewScopeEntity.setModifyOn(new Date());
                    communityOverviewScopeEntity.setModifyBy(communityOverviewDto.getReleasePerson());
                    communityOverviewRepository.saveOrUpdate(communityOverviewScopeEntity);
                }
            }
        }else if (cityIds.size() > 0 && !"0".equals(cityIds.get(0)) && projectIds.size() > 0 && !"0".equals(projectIds.get(0))){
            //遍历项目列表
            for (int i = 0; i < projectIds.size(); i++){
                CommunityOverviewScopeEntity communityOverviewScopeEntity = new CommunityOverviewScopeEntity();
                communityOverviewScopeEntity.setId(IdGen.uuid());
                communityOverviewScopeEntity.setCommunityOverviewId(communityOverviewEntity.getId());
                communityOverviewScopeEntity.setProjectCode(projectIds.get(i));
                communityOverviewScopeEntity.setProjectName(projectList.get(i));
                communityOverviewScopeEntity.setStatus(0);
                communityOverviewScopeEntity.setCreateOn(new Date());
                communityOverviewScopeEntity.setCreateBy(communityOverviewDto.getReleasePerson());
                communityOverviewScopeEntity.setModifyOn(new Date());
                communityOverviewScopeEntity.setModifyBy(communityOverviewDto.getReleasePerson());
                communityOverviewRepository.saveOrUpdate(communityOverviewScopeEntity);
            }
        }

        String projectNames="";
        List<Map<String, Object>> mapList = communityOverviewRepository.queryProjectByCommunityId(communityOverviewEntity.getId());
        for (int i = 0; i < mapList.size(); i++){
            Map<String, Object> map = mapList.get(i);
            // projectCodes = projectCodes + (String) map.get("projectCode") + ",";
            projectNames = projectNames + (String) map.get("projectName") + ",";
        }

        //通过楼盘id获取该楼盘对应的所有去编辑信息
        List<CommunityDetailEntity> list = communityOverviewRepository.getCommunityDetailList(communityOverviewEntity.getId());
        //先删除再添加，以防出错
        for (CommunityDetailEntity communityDetailEntity : list) {
            communityOverviewRepository.deleteCommunityDetailEntity(communityDetailEntity);
        }

        CommunityDetailEntity communityDetailEntity = new CommunityDetailEntity();
        //标题
        List<String> titles = communityOverviewDto.getTitles();

        //描述
        List<String> describes = communityOverviewDto.getDescribes();

        //背景图片
        List<MultipartFile> homePageImg = communityOverviewDto.getHomePageimgpaths();
        for (int i=0; i<homePageImg.size(); i++) {
            communityDetailEntity.setId(UUID.randomUUID().toString());
            communityDetailEntity.setCommunityId(communityOverviewEntity.getId());
            communityDetailEntity.setGrade(i + 1);
            communityDetailEntity.setCreateDate(new Date());
            communityDetailEntity.setCreatePerson(userPropertystaffEntity.getStaffName());
            communityDetailEntity.setOperatorDate(new Date());
            communityDetailEntity.setOperator(userPropertystaffEntity.getStaffName());
            String fileName = imgService.uploadAdminImage(homePageImg.get(i), ImgType.ACTIVITY);
            //图片地址特殊处理
            //String urlTitle = "http://211.94.93.223/images/";
            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
            //fileName = urlTitle + fileName.replace("/opt/image.server/images/images_source/", "");
            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            if (fileName.equals("")) {
                fileName = "默认图";
            }
            if (!fileName.equals(ImageConfig.PIC_OSS_ADMIN_URL)) {
                communityDetailEntity.setImg(fileName);
            }
            if (!titles.isEmpty()) {
                String title = titles.get(i);
                communityDetailEntity.setTitle(title);
            }else {
                communityDetailEntity.setTitle("");
            }
            if (!describes.isEmpty()) {
                String describe = describes.get(i);
                communityDetailEntity.setContent(describe);
            }else {
                communityDetailEntity.setTitle("");
            }
            communityOverviewRepository.saveCommunityDetailEntity(communityDetailEntity);
        }

        if(state.equals("add")){
            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(userPropertystaffEntity.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(userPropertystaffEntity.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("资讯管理-金茂楼盘");//功能
            infoReleaseLog.setThisType("新增");//操作类型
            infoReleaseLog.setLogContent(communityOverviewEntity.getName());//发布内容
            infoReleaseLog.setAsscommunity(projectNames);//关联社区;
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        }else{
            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(userPropertystaffEntity.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(userPropertystaffEntity.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("资讯管理-金茂楼盘");//功能
            infoReleaseLog.setThisType("修改");//操作类型
            infoReleaseLog.setLogContent(communityOverviewEntity.getName());//发布内容
            infoReleaseLog.setAsscommunity(projectNames);//关联社区;
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        }
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取所有该楼盘的CommunityDetailEntity
     */
    @Override
    public List<CommunityDetailEntity> getAllDetails(String communityId) {
        List<CommunityDetailEntity> list = communityOverviewRepository.getAllDetails(communityId);
        return list;
    }

    /**
     * 获取楼盘预约列表
     */
    public List<CommunityOverviewReservationEntity> getOverviewReservationList(CommunityOverviewDto communityOverviewDto,WebPage webPage){
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("overviewId",communityOverviewDto.getId());
        paramsMap.put("overviewName",communityOverviewDto.getOverviewName());
        paramsMap.put("reservationPer",communityOverviewDto.getReservationPer());
        paramsMap.put("reservationTel",communityOverviewDto.getReservationTel());
        return communityOverviewRepository.getOverviewReservationList(paramsMap,webPage);
    }
}
