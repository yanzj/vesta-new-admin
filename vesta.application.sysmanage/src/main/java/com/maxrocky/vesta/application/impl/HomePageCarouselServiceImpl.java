package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.HomePageCarouselDTO;
import com.maxrocky.vesta.application.inf.HomePageCarouselService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.AnnouncementReplyEntity;
import com.maxrocky.vesta.domain.model.HomePageCarouselEntity;
import com.maxrocky.vesta.domain.repository.AnnouncementRepository;
import com.maxrocky.vesta.domain.repository.HomePageCarouselRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 首页轮播图_Service实现类
 * 2016/6/24_Wyd
 */
@Service
    public class HomePageCarouselServiceImpl implements HomePageCarouselService{

    @Autowired
    private HomePageCarouselRepository homePageCarouselRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    /**
     * 保存或更新轮播图信息
     */
    public void saveOrUpdateCarousel(HomePageCarouselDTO homePageCarouselDTO){
        //城市/项目数据准备
        List<String> cityList = Arrays.asList(homePageCarouselDTO.getCityList().split(","));
        List<String> projectList = Arrays.asList(homePageCarouselDTO.getProjectList().split(","));
        List<String> cityIds = Arrays.asList(homePageCarouselDTO.getCityIds().split(","));
        List<String> projectIds = Arrays.asList(homePageCarouselDTO.getProjectIds().split(","));
        ArrayList<String> imgUrls = homePageCarouselDTO.getImgUrls();
        ArrayList<String> linkUrls = homePageCarouselDTO.getLinkUrls();

        //全部城市(默认轮播图)
        if (cityIds.size() == 1 && "0".equals(cityIds.get(0))){
            //删除
            List<HomePageCarouselEntity> homePageCarouselEntities = homePageCarouselRepository.queryCarouselByProjectCode("0");
            if (homePageCarouselEntities.size() > 0){
                homePageCarouselRepository.deleteCarouselByProjectCode("0");
            }
            //新增
            for (int i=0; i<imgUrls.size(); i++){
                HomePageCarouselEntity homePageCarouselEntity = new HomePageCarouselEntity();
                homePageCarouselEntity.setId(IdGen.uuid());
                homePageCarouselEntity.setImgUrl(imgUrls.get(i));
                homePageCarouselEntity.setLinkUrl(linkUrls.get(i));
                homePageCarouselEntity.setCarouselOrder(i);
                homePageCarouselEntity.setProjectCode("0");
                homePageCarouselEntity.setProjectName("全部城市");
                homePageCarouselEntity.setCreateOn(new Date());
                homePageCarouselEntity.setCreateBy(homePageCarouselDTO.getModifyBy());
                homePageCarouselEntity.setModifyOn(new Date());
                homePageCarouselEntity.setModifyBy(homePageCarouselDTO.getModifyBy());
                homePageCarouselRepository.saveCarousel(homePageCarouselEntity);
            }
        }
        //全部项目(所选城市下所有项目)
        if (projectIds.size() == 1 && "0".equals(projectIds.get(0))){
            for (int i=0; i<cityIds.size(); i++){
                String cityId = cityIds.get(i);
                List<Object[]> projects = listProject(cityId);
                //为每个项目添加公告范围信息
                for (int j=0; j<projects.size(); j++){
                    //删除
                    List<HomePageCarouselEntity> homePageCarouselEntities = homePageCarouselRepository.queryCarouselByProjectCode((String) projects.get(j)[0]);
                    if (homePageCarouselEntities.size() > 0){
                        homePageCarouselRepository.deleteCarouselByProjectCode((String)projects.get(j)[0]);
                    }
                    //新增
                    for (int m=0; m<imgUrls.size(); m++) {
                        HomePageCarouselEntity homePageCarouselEntity = new HomePageCarouselEntity();
                        homePageCarouselEntity.setId(IdGen.uuid());
                        homePageCarouselEntity.setImgUrl(imgUrls.get(m));
                        homePageCarouselEntity.setLinkUrl(linkUrls.get(m));
                        homePageCarouselEntity.setCarouselOrder(m);
                        homePageCarouselEntity.setProjectCode((String) projects.get(j)[0]);
                        homePageCarouselEntity.setProjectName((String) projects.get(j)[1]);
                        homePageCarouselEntity.setCreateOn(new Date());
                        homePageCarouselEntity.setCreateBy(homePageCarouselDTO.getModifyBy());
                        homePageCarouselEntity.setModifyOn(new Date());
                        homePageCarouselEntity.setModifyBy(homePageCarouselDTO.getModifyBy());
                        homePageCarouselRepository.saveCarousel(homePageCarouselEntity);
                    }
                }
            }
        }
        //所选项目
        if (projectIds.size() >= 0 && !"0".equals(projectIds.get(0)) && !"".equals(projectIds.get(0))){
            for (int i=0; i<projectIds.size(); i++){
                //删除
                List<HomePageCarouselEntity> homePageCarouselEntities = homePageCarouselRepository.queryCarouselByProjectCode(projectIds.get(i));
                if (homePageCarouselEntities.size() > 0){
                    homePageCarouselRepository.deleteCarouselByProjectCode(projectIds.get(i));
                }
                //新增
                for (int m=0; m<imgUrls.size(); m++) {
                    HomePageCarouselEntity homePageCarouselEntity = new HomePageCarouselEntity();
                    homePageCarouselEntity.setId(IdGen.uuid());
                    homePageCarouselEntity.setImgUrl(imgUrls.get(m));
                    homePageCarouselEntity.setLinkUrl(linkUrls.get(m));
                    homePageCarouselEntity.setCarouselOrder(m);
                    homePageCarouselEntity.setProjectCode(projectIds.get(i));
                    homePageCarouselEntity.setProjectName(projectList.get(i));
                    homePageCarouselEntity.setCreateOn(new Date());
                    homePageCarouselEntity.setCreateBy(homePageCarouselDTO.getModifyBy());
                    homePageCarouselEntity.setModifyOn(new Date());
                    homePageCarouselEntity.setModifyBy(homePageCarouselDTO.getModifyBy());
                    homePageCarouselRepository.saveCarousel(homePageCarouselEntity);
                }
            }
        }

    }

    /**
     * 通过_projectCode_检索轮播图列表
     * @param projectCode 项目Code
     * @return List<HomePageCarouselEntity>
     */
    public List<HomePageCarouselEntity> queryCarouselByProjectCode(String projectCode){
        return homePageCarouselRepository.queryCarouselByProjectCode(projectCode);
    }


    /**
     * 根据城市查询所有城市下项目
     * @return List
     */
    public List<Object[]> listProject(String cityId) {
        return this.announcementRepository.listProject(cityId);
    }

    /**
     * 查询所有城市所有项目
     * @return List
     */
    public List<Object[]> listAllProject(){
        return announcementRepository.listAllProject();
    }

    /**
     * 通过_projectCode_检索项目
     * @return List
     */
    public List<Object[]> getProjectByCode(String projectCode){
        return announcementRepository.getProjectByCode(projectCode);
    }
}
