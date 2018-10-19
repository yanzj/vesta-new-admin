package com.maxrocky.vesta.application.inf;
import com.maxrocky.vesta.application.DTO.SharingActivitiesDTO;
import com.maxrocky.vesta.application.DTO.SharingActivitiesImgDTO;
import com.maxrocky.vesta.application.DTO.SharingActivitiesSearchDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.ImgActivitiesEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/24.
 */
public interface SharingActivitieService {
    /**
     * 分享活动列表
     * @return
     */
    List<SharingActivitiesDTO> ActivitiesList(SharingActivitiesSearchDTO sharingActivitiesSearchDTO, WebPage webPage);



    List<ImgActivitiesEntity> getAcitivityImageList(String id);

    List<SharingActivitiesImgDTO> getImageList(String id);


    /**
     * 新增活动分享
     * @param userPropertystaffEntit
     */
    void AddActivities(UserPropertyStaffEntity userPropertystaffEntit,SharingActivitiesDTO sharingActivitiesDTO);

    /**
     * 根据id查询分享活动
     * @return
     */
    SharingActivitiesDTO getActivitiesById (String id);

    ApiResult info(String id, String vestaToken);

    Object getShaingAcitivity(String projectId,int type);

    String delActivitiesById ( UserPropertyStaffEntity userPropertystaffEnt,String id);

    /****
     * 活动排序
     */
    List<SharingActivitiesDTO>  Activitiessort(String id,String state,WebPage webPage);


    boolean CompareActivities(String ImgId,  List<String>imageId);


}
