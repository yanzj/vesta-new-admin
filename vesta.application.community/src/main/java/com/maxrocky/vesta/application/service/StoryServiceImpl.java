package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.StoryDTO;
import com.maxrocky.vesta.domain.model.StoryEntity;
import com.maxrocky.vesta.domain.model.StoryScopeEntity;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author WeiYangDong
 * @date 2018/3/22 16:54
 * @deprecated 故事荟Service实现类
 */
@Service
public class StoryServiceImpl implements StoryService{

    @Autowired
    private StoryRespository storyRespository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 获取故事荟列表
     */
    public List<StoryDTO> getStoryInfoList(StoryDTO storyDTO, WebPage webPage){
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("projectNum",storyDTO.getProjectNum());
        paramsMap.put("title",storyDTO.getTitle());
        paramsMap.put("releasePerson",storyDTO.getReleasePerson());
        paramsMap.put("releaseStatus",storyDTO.getReleaseStatus());
        paramsMap.put("releaseStaDate",storyDTO.getReleaseStaDate());
        paramsMap.put("releaseEndDate",storyDTO.getReleaseEndDate());
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = storyDTO.getRoleScopeList();
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
        if (null != storyDTO.getCityId() && !"".equals(storyDTO.getCityId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(storyDTO.getCityId());
            String cityProjects = "";
            for (Object[] project : projectList) {
                if (!cityProjects.contains(project[0].toString())) {
                    cityProjects += "'" + project[0].toString() + "',";
                }
            }
            paramsMap.put("cityProjects",cityProjects);//城市下所有项目,以城市为单位检索
        }
        //执行检索
        List<StoryEntity> storyInfoList = storyRespository.getStoryInfoList(paramsMap, webPage);
        //数据处理
        List<StoryDTO> storyDTOS = new ArrayList<>();
        StoryEntity storyEntity = null;
        StoryDTO storyInfoDTO = null;
        for (int i=0,length=storyInfoList.size();i<length;i++){
            storyEntity = storyInfoList.get(i);
            storyInfoDTO = new StoryDTO();
            storyInfoDTO.setId(storyEntity.getId());//主键ID
            storyInfoDTO.setTitle(storyEntity.getTitle());//故事标题
            storyInfoDTO.setReleaseStatus(storyEntity.getReleaseStatus());//发布状态
            storyInfoDTO.setReleasePerson(storyEntity.getReleasePerson());//发布人
            storyInfoDTO.setReleaseDate(DateUtils.format(storyEntity.getReleaseDate(),"yyyy-MM-dd HH:mm") );//发布日期
            //发布范围
            String releaseProjectScopes = "";
            List<StoryScopeEntity> storyScopeEntityList = storyRespository.getStoryInfoScopeById(storyEntity.getId());
            for (StoryScopeEntity storyScopeEntity : storyScopeEntityList){
                if (storyScopeEntity.getScopeLev() == 1){
                    //全部城市
                    releaseProjectScopes = "全部城市";
                    break;
                }else{
                    releaseProjectScopes += storyScopeEntity.getProjectName()+",";
                }
            }
            storyInfoDTO.setReleaseProjectScopes(releaseProjectScopes);
            storyDTOS.add(storyInfoDTO);
        }
        return storyDTOS;
    }

    /**
     * 获取故事详情
     */
    public StoryEntity getStoryInfoById(String storyId){
        StoryEntity storyEntity = null;
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("id",storyId);
        List<StoryEntity> storyEntities = storyRespository.getStoryInfoList(paramsMap, null);
        if (null != storyEntities && storyEntities.size() > 0){
            storyEntity = storyEntities.get(0);
        }
        return storyEntity;
    }

    public StoryDTO getStoryInfoDTOById(String storyId){
        StoryEntity storyEntity = getStoryInfoById(storyId);
        StoryDTO storyDTO = new StoryDTO();
        try {
            BeanUtils.copyProperties(storyDTO, storyEntity);
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
        List<StoryScopeEntity> scopeEntityList = storyRespository.getStoryInfoScopeById(storyEntity.getId());
        for (StoryScopeEntity storyScopeEntity : scopeEntityList){
            if (storyScopeEntity.getScopeLev() == 1){
                //全部城市
                releaseCityIds = "0";
                releaseCityScopes = "全部城市";
                break;
            }else{
                if (null != storyScopeEntity.getCity() && !releaseCityScopes.contains(storyScopeEntity.getCity())){
                    releaseCityScopes += storyScopeEntity.getCity()+",";
                    releaseCityIds += storyScopeEntity.getCityId()+",";
                }
                releaseProjectScopes += storyScopeEntity.getProjectName()+",";
                releaseProjectIds += storyScopeEntity.getProjectId()+",";
            }
        }
        storyDTO.setProjects(releaseProjectScopes);
        storyDTO.setProjectIds(releaseProjectIds);
        storyDTO.setCitys(releaseCityScopes);
        storyDTO.setCityIds(releaseCityIds);
        return storyDTO;
    }

    /**
     * 保存或更新故事荟
     */
    public void saveOrUpdateStoryInfo(StoryDTO storyDTO){
        StoryEntity storyEntity = null;
        if (null != storyDTO.getId() && !"".equals(storyDTO.getId())){
            //执行更新
            Map<String,Object> paramsMap = new HashedMap();
            paramsMap.put("id",storyDTO.getId());
            List<StoryEntity> storyEntities = storyRespository.getStoryInfoList(paramsMap, null);
            if (null != storyEntities && storyEntities.size() > 0){
                storyEntity = storyEntities.get(0);
                storyEntity.setModifyBy(storyDTO.getModifyBy());//修改人
                storyEntity.setModifyOn(new Date());//修改时间
            }
        }else{
            //执行新增
            storyEntity = new StoryEntity();
            storyEntity.setId(IdGen.uuid());//主键ID
            storyEntity.setCreateBy(storyDTO.getModifyBy());//创建人
            storyEntity.setCreateOn(new Date());//创建时间
        }
        storyEntity.setTitle(storyDTO.getTitle());//故事标题
        storyEntity.setContent(storyDTO.getContent());//故事内容
        storyEntity.setInfoSignImgUrl(storyDTO.getInfoSignImgUrl());//故事标识图
        storyEntity.setClientId(storyDTO.getClientId());//故事所属客户端ID
        storyEntity.setIsLink(storyDTO.getIsLink());//是否外链
        if (storyDTO.getIsLink() == 1){
            storyEntity.setLinkSrc(storyDTO.getLinkSrc());//外链地址
        }
//        salesPromotionInfoEntity.setOrderNum(salesPromotionInfoDTO.getOrderNum());//信息排序字段
        storyEntity.setInfoStatus(0);//故事荟状态(0,未删除/1,已删除)
        storyEntity.setReleaseStatus(storyDTO.getReleaseStatus());//发布状态(0,未发布/1,已发布)
        storyEntity.setReleaseDate(DateUtils.parse(storyDTO.getReleaseDate(),"yyyy-MM-dd HH:mm"));//发布日期
        storyEntity.setReleasePerson(storyDTO.getModifyBy());//发布人
        storyRespository.saveOrUpdate(storyEntity);
        //保存故事荟发布范围
        //1.清空故事荟发布范围
        storyRespository.delStoryInfoScopeById(storyEntity.getId());
        //2.基础数据准备
        List<String> cityList = Arrays.asList(storyDTO.getCitys().split(","));
        List<String> projectList = Arrays.asList(storyDTO.getProjects().split(","));
        List<String> cityIds = Arrays.asList(storyDTO.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(storyDTO.getProjectIds().split(","));
        //3.重新添加发布范围
        StoryScopeEntity storyScopeEntity = null;
        //全部城市
        if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
            storyScopeEntity = new StoryScopeEntity();
            storyScopeEntity.setId(IdGen.uuid());
            storyScopeEntity.setStoryId(storyEntity.getId());
            storyScopeEntity.setCity("全部城市");
            storyScopeEntity.setCityId("0");
            storyScopeEntity.setScopeLev(1);
            storyScopeEntity.setCreateOn(new Date());
            storyScopeEntity.setCreateBy(storyDTO.getModifyBy());
            storyRespository.saveOrUpdate(storyScopeEntity);
        }
        //全部项目
        if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
            //遍历城市范围,检索每个城市下所有项目
            for (int i = 0; i < cityIds.size(); i++) {
                String cityId = cityIds.get(i);
                List<Object[]> projects = listProject(cityId);
                //为每个项目添加发布范围信息
                for (int j = 0; j < projects.size(); j++) {
                    storyScopeEntity = new StoryScopeEntity();
                    storyScopeEntity.setId(IdGen.uuid());
                    storyScopeEntity.setStoryId(storyEntity.getId());
                    storyScopeEntity.setCity(cityList.get(i));
                    storyScopeEntity.setCityId(cityId);
                    storyScopeEntity.setProjectName((String) projects.get(j)[1]);
                    storyScopeEntity.setProjectId((String) projects.get(j)[0]);
                    storyScopeEntity.setScopeLev(2);
                    storyScopeEntity.setCreateOn(new Date());
                    storyScopeEntity.setCreateBy(storyDTO.getModifyBy());
                    storyRespository.saveOrUpdate(storyScopeEntity);
                }
            }
        }
        //单个项目
        if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
            for (int i = 0; i < projectList.size(); i++) {
                String project = projectList.get(i);
                if (!StringUtils.isEmpty(project)) {
                    storyScopeEntity = new StoryScopeEntity();
                    storyScopeEntity.setId(IdGen.uuid());
                    storyScopeEntity.setStoryId(storyEntity.getId());
                    //通过项目Code绑定城市
                    List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                    if (!list.isEmpty() && list.size() > 0){
                        storyScopeEntity.setCity(list.get(0)[1].toString());
                        storyScopeEntity.setCityId(list.get(0)[0].toString());
                    }
                    storyScopeEntity.setProjectName(project);
                    storyScopeEntity.setProjectId(projectIds.get(i));
                    storyScopeEntity.setScopeLev(2);
                    storyScopeEntity.setCreateOn(new Date());
                    storyScopeEntity.setCreateBy(storyDTO.getModifyBy());
                    storyRespository.saveOrUpdate(storyScopeEntity);
                }
            }
        }
    }

    /**
     * 删除故事荟(逻辑删除)
     */
    public void deleteStoryInfo(String storyId){
        StoryEntity storyEntity = getStoryInfoById(storyId);
        storyEntity.setInfoStatus(1);
        storyRespository.saveOrUpdate(storyEntity);
    }

    /**
     * 根据城市查询所有城市下项目(通用方法)
     */
    public List<Object[]> listProject(String cityId) {
        return announcementRepository.listProject(cityId);
    }
}
