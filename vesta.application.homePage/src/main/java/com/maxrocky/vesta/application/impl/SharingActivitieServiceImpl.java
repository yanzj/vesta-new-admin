package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.admin.dto.HomeShareCommunityDto;
import com.maxrocky.vesta.application.admin.dto.HomeShareCommunityImageDto;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.inf.SharingActivitieService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.config.ImgType;;
import com.maxrocky.vesta.domain.model.ImgActivitiesEntity;
import com.maxrocky.vesta.domain.model.SharingActivitiesEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.SharingActivitiesRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.ImgUpdate.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by sunmei on 2016/2/24.
 */
@Service
public class SharingActivitieServiceImpl implements SharingActivitieService {
    @Autowired
    SharingActivitiesRepository sharingActivitiesRepository;
    @Autowired
    OperationLogService operationLogService;

    @Override
    public List<SharingActivitiesDTO> ActivitiesList(SharingActivitiesSearchDTO sharingActivitiesSearchDTO, WebPage webPage) {
        List<SharingActivitiesDTO> sharingActivitiesDTOList = new ArrayList<>();//DTO集合
        SharingActivitiesEntity sharingActivitiesEntity = new SharingActivitiesEntity();
        sharingActivitiesEntity.setTitle(sharingActivitiesSearchDTO.getTitle());
        // 初始化 登陆人所负责范围
        sharingActivitiesEntity.setItemId(sharingActivitiesSearchDTO.getQueryScope());
        if (null != sharingActivitiesSearchDTO.getPublishStartDate()&&!"".equals(sharingActivitiesSearchDTO.getPublishStartDate())) {
            sharingActivitiesEntity.setPublishStartDate(DateUtils.parse(sharingActivitiesSearchDTO.getPublishStartDate()));
        }
        if (null != sharingActivitiesSearchDTO.getPublishEndDate()&&!"".equals(sharingActivitiesSearchDTO.getPublishEndDate())) {
            sharingActivitiesEntity.setPublishEndDate(DateUtils.parse(sharingActivitiesSearchDTO.getPublishEndDate()));
        }

        List<SharingActivitiesEntity> sharingActivitiesList = sharingActivitiesRepository.SharingActivities(sharingActivitiesEntity, webPage);
        for (SharingActivitiesEntity SharingActivities : sharingActivitiesList) {
            SharingActivitiesDTO sharingActivitiesDTO = new SharingActivitiesDTO();
            sharingActivitiesDTO.setActivitiesId(SharingActivities.getId());
            sharingActivitiesDTO.setCount(sharingActivitiesList.size());
            sharingActivitiesDTO.setTitle(SharingActivities.getTitle());
            sharingActivitiesDTO.setContent(SharingActivities.getContent());
            sharingActivitiesDTO.setPublishBy(SharingActivities.getPublishBy());
            sharingActivitiesDTO.setPublishdate(DateUtils.format(SharingActivities.getPublishdate()));

            List<ImgActivitiesEntity> imgActivitiesList = sharingActivitiesRepository.getActivitiesImgByImgId(SharingActivities.getImgId());
            Map<String, String> imgUrl = new LinkedHashMap<>();
            for (int i = 0; i < imgActivitiesList.size(); i++) {
                imgUrl.put(imgActivitiesList.get(i).getId(), imgActivitiesList.get(i).getImgUrl());
                sharingActivitiesDTO.setShowUrl(imgUrl);
            }
            if(sharingActivitiesSearchDTO.getPropertyProject()!=null&& !"0".equals(sharingActivitiesSearchDTO.getPropertyProject())&&!"".equals(sharingActivitiesSearchDTO.getPropertyProject())){
                sharingActivitiesDTO.setIsSort(SharingActivitiesEntity.AD_SORT);
            }
            sharingActivitiesDTOList.add(sharingActivitiesDTO);

        }
        return sharingActivitiesDTOList;
    }


    @Override
    public List<ImgActivitiesEntity> getAcitivityImageList(String id) {
        return this.sharingActivitiesRepository.getImageListByAcitvityId(id);
    }

    @Override
    public List<SharingActivitiesImgDTO> getImageList(String id) {
        List<SharingActivitiesImgDTO> imgActivitiesList = new ArrayList<>();
        List<String> imgList =new ArrayList<>();
        if (!"".equals(id) && null != id) {
            SharingActivitiesEntity activities = sharingActivitiesRepository.getActivitiesById(id);
            List<ImgActivitiesEntity> imgActivities =sharingActivitiesRepository.getActivitiesImgByImgId(activities.getImgId());
            for(ImgActivitiesEntity imgs:imgActivities){
                SharingActivitiesImgDTO sharingActivitiesImgDTO=new SharingActivitiesImgDTO();
                sharingActivitiesImgDTO.setImgUrl(imgs.getImgUrl());
                sharingActivitiesImgDTO.setImageId(imgs.getId());
                imgActivitiesList.add(sharingActivitiesImgDTO);
            }
        }
        return imgActivitiesList;
    }

    @Override
    public void AddActivities(UserPropertyStaffEntity userPropertystaffEntit, SharingActivitiesDTO sharingActivitiesDTO) {

        SharingActivitiesEntity activitiesEntity = new SharingActivitiesEntity();
        String imgId = IdGen.uuid();
        activitiesEntity.setId(sharingActivitiesDTO.getActivitiesId());
        activitiesEntity.setTitle(sharingActivitiesDTO.getTitle());
        activitiesEntity.setContent(sharingActivitiesDTO.getContent());
        activitiesEntity.setItemId(sharingActivitiesDTO.getPropertyProject());
        activitiesEntity.setPublishdate(new Date());
        activitiesEntity.setPublishBy(userPropertystaffEntit.getStaffName()); // 操作人
       /* List imgUrl=new ArrayList<>();
        imgUrl.add(sharingActivitiesDTO.getImgUrl1());
        imgUrl.add(sharingActivitiesDTO.getImgUrl2());*/
        sharingActivitiesDTO.setImgUrl(sharingActivitiesDTO.getImgUrl());
        if (null == activitiesEntity.getId() || "".equals(activitiesEntity.getId())) {
                        for (MultipartFile imgPage : sharingActivitiesDTO.getImgUrl()) {
                            ImgActivitiesEntity imgActivitiesEntity = new ImgActivitiesEntity();
                            if (imgPage.getSize() != 0) {
                                //存储图片。
                                String fileName = ImageUpload.saveImageToService(imgPage, ImgType.SHARINGACTIVITY);
                                imgActivitiesEntity.setImgUrl(AppConfig.SERVICEPATH + fileName);
                                imgActivitiesEntity.setId(IdGen.uuid());
                                imgActivitiesEntity.setImgId(imgId);
                                sharingActivitiesRepository.createActivitiesImg(imgActivitiesEntity);
                            }

                        }
                        activitiesEntity.setImgId(imgId);
                        activitiesEntity.setId(IdGen.uuid());
                        List<SharingActivitiesEntity> activitiesList = sharingActivitiesRepository.getActivitiesManageByItemId(sharingActivitiesDTO.getPropertyProject());
                        for (int i = 0; i < activitiesList.size(); i++) {
                            activitiesEntity.setSort(activitiesList.get(0).getSort() + 1);
                            break;
                        }
                        // 执行新增
                        sharingActivitiesRepository.createActivities(activitiesEntity);
                        //添加日志
                        OperationLogDTO operationLogDTO =new OperationLogDTO();
                        operationLogDTO.setProjectId(activitiesEntity.getItemId());
                        operationLogDTO.setUserName(userPropertystaffEntit.getUserName());
                        operationLogDTO.setContent("新增分享活动");
                        operationLogService.addOperationLog(operationLogDTO);
                    } else {

            if(null!=sharingActivitiesDTO.getImgUrl()){
                SharingActivitiesEntity activities = sharingActivitiesRepository.getActivitiesById(activitiesEntity.getId());
                List<ImgActivitiesEntity> imgActivities = sharingActivitiesRepository.getActivitiesImgByImgId(activities.getImgId());
               if(sharingActivitiesDTO.getImageId().size()>0){

                   for (ImgActivitiesEntity img : imgActivities) {
                     boolean msg=this.CompareActivities(img.getId(),sharingActivitiesDTO.getImageId());
                     if(!msg){
                         sharingActivitiesRepository.deleteImgById(img.getId());
                     }

                 }

               }
                activitiesEntity.setImgId(activities.getImgId());
                activitiesEntity.setSort(activities.getSort());
                for (MultipartFile imgPage : sharingActivitiesDTO.getImgUrl()) {
                    if (imgPage.getSize() != 0) {
                    ImgActivitiesEntity imgActivitiesEntity = new ImgActivitiesEntity();

                        //存储图片。
                        String fileName = ImageUpload.saveImageToService(imgPage, ImgType.SHARINGACTIVITY);
                        imgActivitiesEntity.setImgUrl(AppConfig.SERVICEPATH + fileName);
                        imgActivitiesEntity.setImgId(activities.getImgId());
                        imgActivitiesEntity.setId(IdGen.uuid());
                        sharingActivitiesRepository.createActivitiesImg(imgActivitiesEntity);
                    }
                }
            }else{
                SharingActivitiesEntity activities = sharingActivitiesRepository.getActivitiesById(activitiesEntity.getId());
                activitiesEntity.setImgId(activities.getImgId());
            }
            sharingActivitiesRepository.updateActivities(activitiesEntity);
            //添加日志
            OperationLogDTO operationLogDTO =new OperationLogDTO();
            operationLogDTO.setProjectId(activitiesEntity.getItemId());
            operationLogDTO.setUserName(userPropertystaffEntit.getUserName());
            operationLogDTO.setContent("更新分享活动");
            operationLogService.addOperationLog(operationLogDTO);
        }


    }

    @Override
    public SharingActivitiesDTO getActivitiesById(String id) {

        SharingActivitiesDTO activitiesDTO = new SharingActivitiesDTO();
        if (!"".equals(id) && null != id) {
            SharingActivitiesEntity activities = sharingActivitiesRepository.getActivitiesById(id);
            activitiesDTO.setActivitiesId(activities.getId());
            activitiesDTO.setTitle(activities.getTitle());
            activitiesDTO.setPropertyProject(activities.getItemId());
            activitiesDTO.setContent(activities.getContent());
        }
        return activitiesDTO;
        }


    @Override
    public ApiResult info(String id, String vestaToken) {
//        Sharing

        //SharingActivityInfoDto
        SharingActivitiesEntity sharingActivitiesEntity = this.sharingActivitiesRepository.getActivitiesById(id);
        if(sharingActivitiesEntity == null){
            return  new ErrorApiResult("tip_00000474");
        }
        SharingActivityInfoDto sharingActivityInfoDto = new SharingActivityInfoDto();
        sharingActivityInfoDto.setId(sharingActivitiesEntity.getId());
        sharingActivityInfoDto.setContent(sharingActivitiesEntity.getContent());
        sharingActivityInfoDto.setDate(DateUtils.format(sharingActivitiesEntity.getPublishdate()));
        sharingActivityInfoDto.setTitle(sharingActivitiesEntity.getTitle());
        {
            List<ImgActivitiesEntity>  sharingActivitiesEntities = this.sharingActivitiesRepository.getImageListByAcitvityId(sharingActivitiesEntity.getImgId());
            List<SharingActivityImageDto> sharingActivityImageDtos = new ArrayList<>();
            sharingActivitiesEntities.forEach(imgActivitiesEntity -> {
                sharingActivityImageDtos.add(new SharingActivityImageDto().setImgUrl(/*AppConfig.image_domain + "/"+*/imgActivitiesEntity.getImgUrl()));
            });
            sharingActivityInfoDto.setImgList(sharingActivityImageDtos);
        }
        return new SuccessApiResult(sharingActivityInfoDto);
    }

    @Override
    public Object getShaingAcitivity(String projectId, int sort) {
        //        SharingActivitiesEntity sharingActivitiesEntity = this.sharingActivitiesRepository.getActivitiesManageByItemId(projectId);

        List<SharingActivitiesEntity> sharingActivities = this.sharingActivitiesRepository.getSharingActivity(projectId);

        sharingActivities.add(null);
        sharingActivities.add(null);
        if (sharingActivities != null && sharingActivities.size() > 0) {
            SharingActivitiesEntity sharingActivitiesEntity = sharingActivities.get(sort - 1);
            if (sharingActivitiesEntity != null) {

                List<ImgActivitiesEntity> imgActivitiesEntities = this.getAcitivityImageList(sharingActivitiesEntity.getImgId());
                HomeShareCommunityDto homeShareCommunityDto = new HomeShareCommunityDto();
                homeShareCommunityDto.setId(sharingActivitiesEntity.getId());
                {
                    List<HomeShareCommunityImageDto> imageDtos = new ArrayList<>();
                    if(imgActivitiesEntities != null){
                        imgActivitiesEntities.forEach(imgActivitiesEntity -> {

                            HomeShareCommunityImageDto homeShareCommunityImageDto = new HomeShareCommunityImageDto();
                            homeShareCommunityImageDto.setUrl(/*AppConfig.image_domain + "/"+*/imgActivitiesEntity.getImgUrl());
                            imageDtos.add(homeShareCommunityImageDto);
                        });
                    }
                    homeShareCommunityDto.setImages(imageDtos);
                }


                return homeShareCommunityDto;
            }
        }

        return null;
    }

    @Override
    public String delActivitiesById( UserPropertyStaffEntity userPropertystaffEnt,String id) {
        String consultMessage = "";
        SharingActivitiesEntity activities = sharingActivitiesRepository.getActivitiesById(id);
        if (!"".equals(id) && null != id) {
            boolean msg = sharingActivitiesRepository.deleteActivitiesById(id, activities.getImgId());
            if (msg) {
                consultMessage = "0";//此信息删除成功!
                //添加日志
                OperationLogDTO operationLogDTO =new OperationLogDTO();
                operationLogDTO.setProjectId(activities.getItemId());
                operationLogDTO.setUserName(userPropertystaffEnt.getUserName());
                operationLogDTO.setContent("删除广告");
                operationLogService.addOperationLog(operationLogDTO);
            } else {
                consultMessage = "1";//此信息删除失败!
            }
        } else {
            consultMessage = "2";//此信息不存在!
        }

        return consultMessage;
    }

    @Override
    public  List<SharingActivitiesDTO> Activitiessort(String id, String state, WebPage webPage) {
        SharingActivitiesEntity activities=  sharingActivitiesRepository.getActivitiesById(id);
        int sorty=activities.getSort();
        if(state.equals("up")){
            SharingActivitiesEntity activitiesEntity= sharingActivitiesRepository.getActivitiesManageBysortUp(activities.getSort(), activities.getItemId());
            int sortm=activitiesEntity.getSort();
            activitiesEntity.setSort(sorty);
            sharingActivitiesRepository.updateActivities(activitiesEntity);
            activities.setSort(sortm);
            sharingActivitiesRepository.updateActivities(activities);

        }else{
            SharingActivitiesEntity activitiesEntity= sharingActivitiesRepository.getActivitiesManageBysortDown(activities.getSort(), activities.getItemId());
            int sortm = activitiesEntity.getSort();
            activitiesEntity.setSort(sorty);
            sharingActivitiesRepository.updateActivities(activitiesEntity);
            activities.setSort(sortm);
            sharingActivitiesRepository.updateActivities(activities);
        }
        SharingActivitiesSearchDTO sharingActivitiesSearchDTO =new SharingActivitiesSearchDTO();
        sharingActivitiesSearchDTO.setQueryScope(activities.getItemId());
        sharingActivitiesSearchDTO.setPropertyProject(activities.getItemId());
        return this.ActivitiesList(sharingActivitiesSearchDTO, webPage);
    }

    @Override
    public boolean CompareActivities(String ImgId,  List<String>imageId) {

        for(int i=0;i<imageId.size();i++){
                if(ImgId.equals(imageId.get(i))){
                   return true;
                }

            }
        return false;

    }
}
