package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.PropertyAnnouncementDTO;
import com.maxrocky.vesta.application.inf.PropertyAnnouncementDetaildService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.PropertyAnnouncementEntity;
import com.maxrocky.vesta.domain.repository.PropertyAnnouncementRepository;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


/**
 * Created by JillChen on 2016/1/23.
 */
@Service
public class PropertyAnnouncementDetaildServiceImpl implements PropertyAnnouncementDetaildService {

    @Autowired
    private PropertyAnnouncementRepository propertyAnnouncementRepository;


    @Override
    public ApiResult propertyAnnouncementDetail(String announcementId) {
        PropertyAnnouncementEntity propertyAnnouncementEntity =  propertyAnnouncementRepository.propertyAnnouncementDetail(announcementId);
        if (propertyAnnouncementEntity==null||propertyAnnouncementEntity.getAnnouncementId()==null){
            return ErrorResource.getError("tip_AnnocementNull");
        }
        PropertyAnnouncementDTO propertyAnnouncementDTO = new PropertyAnnouncementDTO();
        propertyAnnouncementDTO.setAnnouncementId(propertyAnnouncementEntity.getAnnouncementId());
        propertyAnnouncementDTO.setAnnouncementContent(propertyAnnouncementEntity.getAnnouncementContent());
        propertyAnnouncementDTO.setCreateTime(DateUtils.format(propertyAnnouncementEntity.getCreateTime()));
        propertyAnnouncementDTO.setTitle(propertyAnnouncementEntity.getTitle());
        propertyAnnouncementDTO.setType(propertyAnnouncementEntity.getType());
        ModelMap result=new ModelMap();
        result.addAttribute("propertyAnnouncement", propertyAnnouncementDTO);
        return new SuccessApiResult(result);
    }
}
