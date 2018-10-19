package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.StartPageDTO;
import com.maxrocky.vesta.application.inf.StartPageService;
//import com.maxrocky.vesta.domain.config.ImgType;
//import com.maxrocky.vesta.domain.model.AppAdvertEntity;
import com.maxrocky.vesta.domain.model.StartPageEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
//import com.maxrocky.vesta.domain.repository.AdvertManageRepository;
import com.maxrocky.vesta.domain.repository.StartPageRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by sunmei on 2016/2/29.
 */
@Service
public class StartPageServiceImpl implements StartPageService {
    @Autowired
    StartPageRepository startPageRepository;
    @Override
    public String createStartPage(UserPropertyStaffEntity userPropertystaffEntit,StartPageDTO startPageDTO) {
        StartPageEntity startPageEntity=new StartPageEntity();

        startPageEntity.setPublishBy(userPropertystaffEntit.getStaffName()); // 操作人
        startPageEntity.setPublishdate(new Date());

            if(startPageDTO.getImgUrl()!=null) {
                //存储图片。
//                String fileName = ImageUpload.saveImageToService(startPageDTO.getImgUrl(), ImgType.STARTPAGE);
//                startPageEntity.setImgUrl(AppConfig.SERVICEPATH + fileName);
            }

            startPageEntity.setId(IdGen.uuid());

            // 执行新增
        boolean msg=startPageRepository.createStartPage(startPageEntity);
        if(msg){
            return "保存成功";
        }else {
            return "保存失败";
        }
    }
}
