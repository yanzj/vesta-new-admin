package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.SalesPromotionInfoDTO;
import com.maxrocky.vesta.domain.model.SalesPromotionInfoEntity;
import com.maxrocky.vesta.domain.model.SalesPromotionInfoScopeEntity;
import com.maxrocky.vesta.domain.repository.AnnouncementRepository;
import com.maxrocky.vesta.domain.repository.SalesPromotionInfoRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.domain.repository.VoteRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 促销信息-功能模块服务层实现类
 * Created by WeiYangDong on 2017/5/11.
 */
@Service
public class SalesPromotionInfoServiceImpl implements SalesPromotionInfoService{

    @Autowired
    private SalesPromotionInfoRepository salesPromotionInfoRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 获取促销信息列表
     */
    public List<SalesPromotionInfoDTO> getSalesPromotionInfoList(SalesPromotionInfoDTO salesPromotionInfoDTO, WebPage webPage){
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("projectNum",salesPromotionInfoDTO.getProjectNum());
        paramsMap.put("title",salesPromotionInfoDTO.getTitle());
        paramsMap.put("releasePerson",salesPromotionInfoDTO.getReleasePerson());
        paramsMap.put("releaseStatus",salesPromotionInfoDTO.getReleaseStatus());
        paramsMap.put("releaseStaDate",salesPromotionInfoDTO.getReleaseStaDate());
        paramsMap.put("releaseEndDate",salesPromotionInfoDTO.getReleaseEndDate());
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = salesPromotionInfoDTO.getRoleScopeList();
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
        paramsMap.put("roleScopes",roleScopes);//权限范围
        if (null != salesPromotionInfoDTO.getCityId() && !"".equals(salesPromotionInfoDTO.getCityId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(salesPromotionInfoDTO.getCityId());
            String cityProjects = "";
            for (Object[] project : projectList) {
                if (!cityProjects.contains(project[0].toString())) {
                    cityProjects += "'" + project[0].toString() + "',";
                }
            }
            paramsMap.put("cityProjects",cityProjects);//城市下所有项目,以城市为单位检索
        }
        //执行检索
        List<SalesPromotionInfoEntity> salesPromotionInfoList = salesPromotionInfoRepository.getSalesPromotionInfoList(paramsMap, webPage);
        //数据处理
        List<SalesPromotionInfoDTO> salesPromotionInfoDTOS = new ArrayList<>();
        SalesPromotionInfoEntity salesPromotionInfoEntity = null;
        SalesPromotionInfoDTO salesPromotionDTO = null;
        for (int i=0,length=salesPromotionInfoList.size();i<length;i++){
            salesPromotionInfoEntity = salesPromotionInfoList.get(i);
            salesPromotionDTO = new SalesPromotionInfoDTO();
            salesPromotionDTO.setId(salesPromotionInfoEntity.getId());//主键ID
            salesPromotionDTO.setTitle(salesPromotionInfoEntity.getTitle());//促销标题
            salesPromotionDTO.setIsBanner(salesPromotionInfoEntity.getIsBanner());//是否作为宣传位
            salesPromotionDTO.setReleaseStatus(salesPromotionInfoEntity.getReleaseStatus());//发布状态
            salesPromotionDTO.setReleasePerson(salesPromotionInfoEntity.getReleasePerson());//发布人
            salesPromotionDTO.setReleaseDate(DateUtils.format(salesPromotionInfoEntity.getReleaseDate(),"yyyy-MM-dd HH:mm") );//发布日期
            //发布范围
            String releaseProjectScopes = "";
            List<SalesPromotionInfoScopeEntity> scopeEntityList = salesPromotionInfoRepository.getSalesPromotionInfoScopeById(salesPromotionInfoEntity.getId());
            for (SalesPromotionInfoScopeEntity salesPromotionInfoScopeEntity : scopeEntityList){
                if (salesPromotionInfoScopeEntity.getScopeLev() == 1){
                    //全部城市
                    releaseProjectScopes = "全部城市";
                    break;
                }else{
                    releaseProjectScopes += salesPromotionInfoScopeEntity.getProjectName()+",";
                }
            }
            salesPromotionDTO.setReleaseProjectScopes(releaseProjectScopes);
            salesPromotionInfoDTOS.add(salesPromotionDTO);
        }
        return salesPromotionInfoDTOS;
    }

    /**
     * 获取促销详情
     */
    public SalesPromotionInfoEntity getSalesPromotionInfoById(String salesPromotionInfoId){
        SalesPromotionInfoEntity salesPromotionInfoEntity = null;
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("id",salesPromotionInfoId);
        List<SalesPromotionInfoEntity> salesPromotionInfoEntities = salesPromotionInfoRepository.getSalesPromotionInfoList(paramsMap, null);
        if (null != salesPromotionInfoEntities && salesPromotionInfoEntities.size() > 0){
            salesPromotionInfoEntity = salesPromotionInfoEntities.get(0);
        }
        return salesPromotionInfoEntity;
    }
    public SalesPromotionInfoDTO getSalesPromotionInfoDTOById(String salesPromotionInfoId){
        SalesPromotionInfoEntity salesPromotionInfoEntity = getSalesPromotionInfoById(salesPromotionInfoId);
        SalesPromotionInfoDTO salesPromotionInfoDTO = new SalesPromotionInfoDTO();
        try {
            BeanUtils.copyProperties(salesPromotionInfoDTO, salesPromotionInfoEntity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //设置项目发布城市/项目
        String releaseCityScopes = "";
        String releaseCityIds = "";
        String releaseProjectScopes = "";
        String releaseProjectIds = "";
        List<SalesPromotionInfoScopeEntity> scopeEntityList = salesPromotionInfoRepository.getSalesPromotionInfoScopeById(salesPromotionInfoEntity.getId());
        for (SalesPromotionInfoScopeEntity salesPromotionInfoScopeEntity : scopeEntityList){
            if (salesPromotionInfoScopeEntity.getScopeLev() == 1){
                //全部城市
                releaseCityIds = "0";
                releaseCityScopes = "全部城市";
                break;
            }else{
                if (null != salesPromotionInfoScopeEntity.getCity() && !releaseCityScopes.contains(salesPromotionInfoScopeEntity.getCity())){
                    releaseCityScopes += salesPromotionInfoScopeEntity.getCity()+",";
                    releaseCityIds += salesPromotionInfoScopeEntity.getCityId()+",";
                }
                releaseProjectScopes += salesPromotionInfoScopeEntity.getProjectName()+",";
                releaseProjectIds += salesPromotionInfoScopeEntity.getProjectId()+",";
            }
        }
        salesPromotionInfoDTO.setProjects(releaseProjectScopes);
        salesPromotionInfoDTO.setProjectIds(releaseProjectIds);
        salesPromotionInfoDTO.setCitys(releaseCityScopes);
        salesPromotionInfoDTO.setCityIds(releaseCityIds);
        return salesPromotionInfoDTO;
    }

    /**
     * 保存或更新促销信息
     */
    public void saveOrUpdateSalesPromotionInfo(SalesPromotionInfoDTO salesPromotionInfoDTO){
        SalesPromotionInfoEntity salesPromotionInfoEntity = null;
        if (null != salesPromotionInfoDTO.getId() && !"".equals(salesPromotionInfoDTO.getId())){
            //执行更新
            Map<String,Object> paramsMap = new HashedMap();
            paramsMap.put("id",salesPromotionInfoDTO.getId());
            List<SalesPromotionInfoEntity> salesPromotionInfoEntities = salesPromotionInfoRepository.getSalesPromotionInfoList(paramsMap, null);
            if (null != salesPromotionInfoEntities && salesPromotionInfoEntities.size() > 0){
                salesPromotionInfoEntity = salesPromotionInfoEntities.get(0);
                salesPromotionInfoEntity.setModifyBy(salesPromotionInfoDTO.getModifyBy());//修改人
                salesPromotionInfoEntity.setModifyOn(new Date());//修改时间
            }
        }else{
            //执行新增
            salesPromotionInfoEntity = new SalesPromotionInfoEntity();
            salesPromotionInfoEntity.setId(IdGen.uuid());//主键ID
            salesPromotionInfoEntity.setCreateBy(salesPromotionInfoDTO.getModifyBy());//创建人
            salesPromotionInfoEntity.setCreateOn(new Date());//创建时间
        }
        salesPromotionInfoEntity.setTitle(salesPromotionInfoDTO.getTitle());//促销标题
        salesPromotionInfoEntity.setContent(salesPromotionInfoDTO.getContent());//促销内容
        salesPromotionInfoEntity.setInfoSignImgUrl(salesPromotionInfoDTO.getInfoSignImgUrl());//促销信息标识图
        salesPromotionInfoEntity.setClientId(salesPromotionInfoDTO.getClientId());//信息所属客户端ID
        salesPromotionInfoEntity.setIsBanner(salesPromotionInfoDTO.getIsBanner());//是否作为banner展示(0,否/1,是)
        salesPromotionInfoEntity.setIsLink(salesPromotionInfoDTO.getIsLink());//是否外链
        if (salesPromotionInfoDTO.getIsLink() == 1){
            salesPromotionInfoEntity.setLinkSrc(salesPromotionInfoDTO.getLinkSrc());//外链地址
        }
//        salesPromotionInfoEntity.setOrderNum(salesPromotionInfoDTO.getOrderNum());//信息排序字段
        salesPromotionInfoEntity.setInfoStatus(0);//促销信息状态(0,未删除/1,已删除)
        salesPromotionInfoEntity.setReleaseStatus(salesPromotionInfoDTO.getReleaseStatus());//发布状态(0,未发布/1,已发布)
        salesPromotionInfoEntity.setReleaseDate(DateUtils.parse(salesPromotionInfoDTO.getReleaseDate(),"yyyy-MM-dd HH:mm"));//发布日期
        salesPromotionInfoEntity.setReleasePerson(salesPromotionInfoDTO.getModifyBy());//发布人
        salesPromotionInfoRepository.saveOrUpdate(salesPromotionInfoEntity);
        //保存促销发布范围
        //1.清空促销发布范围
        salesPromotionInfoRepository.delSalesPromotionInfoScopeById(salesPromotionInfoEntity.getId());
        //2.基础数据准备
        List<String> cityList = Arrays.asList(salesPromotionInfoDTO.getCitys().split(","));
        List<String> projectList = Arrays.asList(salesPromotionInfoDTO.getProjects().split(","));
        List<String> cityIds = Arrays.asList(salesPromotionInfoDTO.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(salesPromotionInfoDTO.getProjectIds().split(","));
        //3.重新添加发布范围
        SalesPromotionInfoScopeEntity salesPromotionInfoScopeEntity = null;
        //全部城市
        if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
            salesPromotionInfoScopeEntity = new SalesPromotionInfoScopeEntity();
            salesPromotionInfoScopeEntity.setId(IdGen.uuid());
            salesPromotionInfoScopeEntity.setSalesPromotionInfoId(salesPromotionInfoEntity.getId());
            salesPromotionInfoScopeEntity.setCity("全部城市");
            salesPromotionInfoScopeEntity.setCityId("0");
            salesPromotionInfoScopeEntity.setScopeLev(1);
            salesPromotionInfoScopeEntity.setCreateOn(new Date());
            salesPromotionInfoScopeEntity.setCreateBy(salesPromotionInfoDTO.getModifyBy());
            salesPromotionInfoRepository.saveOrUpdate(salesPromotionInfoScopeEntity);
        }
        //全部项目
        if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
            //遍历城市范围,检索每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++) {
                String cityId = cityIds.get(i);
                List<Object[]> projects = listProject(cityId);
                //为每个项目添加发布范围信息
                for (int j = 0; j < projects.size(); j++) {
                    salesPromotionInfoScopeEntity = new SalesPromotionInfoScopeEntity();
                    salesPromotionInfoScopeEntity.setId(IdGen.uuid());
                    salesPromotionInfoScopeEntity.setSalesPromotionInfoId(salesPromotionInfoEntity.getId());
                    salesPromotionInfoScopeEntity.setCity(cityList.get(i));
                    salesPromotionInfoScopeEntity.setCityId(cityId);
                    salesPromotionInfoScopeEntity.setProjectName((String) projects.get(j)[1]);
                    salesPromotionInfoScopeEntity.setProjectId((String) projects.get(j)[0]);
                    salesPromotionInfoScopeEntity.setScopeLev(2);
                    salesPromotionInfoScopeEntity.setCreateOn(new Date());
                    salesPromotionInfoScopeEntity.setCreateBy(salesPromotionInfoDTO.getModifyBy());
                    salesPromotionInfoRepository.saveOrUpdate(salesPromotionInfoScopeEntity);
                }
            }
        }
        //单个项目
        if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
            for (int i = 0; i < projectList.size(); i++) {
                String project = projectList.get(i);
                if (!StringUtils.isEmpty(project)) {
                    salesPromotionInfoScopeEntity = new SalesPromotionInfoScopeEntity();
                    salesPromotionInfoScopeEntity.setId(IdGen.uuid());
                    salesPromotionInfoScopeEntity.setSalesPromotionInfoId(salesPromotionInfoEntity.getId());
                    //通过项目Code绑定城市
                    List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                    if (!list.isEmpty() && list.size() > 0){
                        salesPromotionInfoScopeEntity.setCity(list.get(0)[1].toString());
                        salesPromotionInfoScopeEntity.setCityId(list.get(0)[0].toString());
                    }
                    salesPromotionInfoScopeEntity.setProjectName(project);
                    salesPromotionInfoScopeEntity.setProjectId(projectIds.get(i));
                    salesPromotionInfoScopeEntity.setScopeLev(2);
                    salesPromotionInfoScopeEntity.setCreateOn(new Date());
                    salesPromotionInfoScopeEntity.setCreateBy(salesPromotionInfoDTO.getModifyBy());
                    salesPromotionInfoRepository.saveOrUpdate(salesPromotionInfoScopeEntity);
                }
            }
        }
    }

    /**
     * 删除促销信息(逻辑删除)
     */
    public void deleteSalesPromotionInfo(String salesPromotionInfoId){
        SalesPromotionInfoEntity salesPromotionInfoEntity = getSalesPromotionInfoById(salesPromotionInfoId);
        salesPromotionInfoEntity.setInfoStatus(1);
        salesPromotionInfoRepository.saveOrUpdate(salesPromotionInfoEntity);
    }

    /**
     * 根据城市查询所有城市下项目(通用方法)
     */
    public List<Object[]> listProject(String cityId) {
        return announcementRepository.listProject(cityId);
    }
}
