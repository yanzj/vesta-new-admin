package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.PraiseAdminDTO;
import com.maxrocky.vesta.application.JsonDTO.PraiseReceiveDTO;
import com.maxrocky.vesta.application.AdminDTO.ConsultImgDTO;
import com.maxrocky.vesta.application.JsonDTO.ImageDTO;
import com.maxrocky.vesta.application.JsonDTO.PraiseDTO;
import com.maxrocky.vesta.application.inf.PraiseService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/1/21.
 */
@Service
public class PraiseServiceImpl implements PraiseService{
    @Autowired
    PraiseRepository praiseRepository;
    @Autowired
    IsayImageRepository isayImageRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    private HouseInfoRepository houseInfoRepository;//房屋业主地址
    /* 用户设置 */
    @Autowired
    UserSettingRepository userSettingRepository;

    @Override
    public ApiResult AddPraise(UserInfoEntity userInfoEntity,PraiseReceiveDTO praiseReceiveDTO) {
        PraiseEntity praiseEntity = new PraiseEntity();
        praiseEntity.setId(IdGen.uuid());
        praiseEntity.setContent(praiseReceiveDTO.getContent());
        praiseEntity.setCrtTime(new Date());
        praiseEntity.setAddress(praiseReceiveDTO.getAddress());
        praiseEntity.setUserId(userInfoEntity.getUserId());
        praiseEntity.setMobile(userInfoEntity.getMobile());
        praiseEntity.setUserName(userInfoEntity.getRealName());
        praiseEntity.setMobile(userInfoEntity.getMobile());
        praiseEntity.setUserName(userInfoEntity.getRealName());
        praiseEntity.setStatus(PraiseEntity.STATUS_VALID);
        praiseEntity.setReply(PraiseEntity.REPLY_NO);
        // 根据用户ID查询 用户默认项目ID
        UserSettingEntity userSettingEntity = userSettingRepository.get(userInfoEntity.getUserId());
        praiseEntity.setProjectId(userSettingEntity.getProjectId());
        praiseEntity.setProjectName(userSettingEntity.getProjectName());
        if(praiseReceiveDTO.getImageList()!=null&&praiseReceiveDTO.getImageList().size()>0){                                //如果图片列表不为空则添加图片
            for(int i=0;i<praiseReceiveDTO.getImageList().size();i++){
                IsayImageEntity isayImageEntity = new IsayImageEntity();
                isayImageEntity.setId(IdGen.uuid());
                isayImageEntity.setCrtTime(new Date());
                isayImageEntity.setUrl(praiseReceiveDTO.getImageList().get(i).getUrl());
                isayImageEntity.setBussinessId(praiseEntity.getId());
                isayImageRepository.AddSayImage(isayImageEntity);
            }
        }
        praiseRepository.AddPraise(praiseEntity);
        return new SuccessApiResult();
    }

    @Override
    public ApiResult getUserPraises(String userId,Page page) {
        List<PraiseDTO> praiseDTOs = new ArrayList<PraiseDTO>();
            List<PraiseEntity> praiseEntities = praiseRepository.getPraises(userId,page);
            if(praiseEntities!=null){
                for(PraiseEntity praiseEntity:praiseEntities){
                    PraiseDTO praiseDTO = new PraiseDTO();
                    praiseDTO.setId(praiseEntity.getId());
                    praiseDTO.setUserId(praiseEntity.getUserId());
                    praiseDTO.setCodenum(praiseEntity.getCodenum());
                    praiseDTO.setContent(praiseEntity.getContent());
                    praiseDTO.setLevel(praiseEntity.getLevel());
                    praiseDTO.setCrtTime(DateUtils.format(praiseEntity.getCrtTime()));
                    praiseDTO.setTargetId(praiseEntity.getTargetId());
                    praiseDTO.setTargetName(praiseEntity.getTargetName());
                    praiseDTO.setAddress(praiseEntity.getAddress());
                    praiseDTO.setUserName(praiseEntity.getUserName());
                    praiseDTO.setMobile(praiseEntity.getMobile());
                    praiseDTO.setIsAnswer(praiseEntity.getReply());
                    AnswerEntity answerEntitie = answerRepository.getAnswer(praiseEntity.getId(),AnswerEntity.answer_praise);
                    if(answerEntitie!=null){
                        praiseDTO.setAnswerUserId(answerEntitie.getUserId());
                        praiseDTO.setAnswerTime(DateUtils.format(answerEntitie.getCrtTime()));
                        praiseDTO.setAnswerContent(answerEntitie.getContent());
                    }
                    List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
                    List<IsayImageEntity> isayImageEntities = isayImageRepository.getImageList(praiseEntity.getId());
                    if(isayImageEntities!=null){
                        for(IsayImageEntity isayImageEntity:isayImageEntities){
                            ImageDTO imageDTO = new ImageDTO();
                            imageDTO.setUrl(isayImageEntity.getUrl());
                            imageDTOs.add(imageDTO);
                        }
                    }
                    praiseDTO.setImageList(imageDTOs);
                    praiseDTOs.add(praiseDTO);
                }
        }
        return new SuccessApiResult(praiseDTOs);
    }

    @Override
    public ApiResult getStaffPraise(String userId,Page page) {
        List<PraiseDTO> praiseDTOs = new ArrayList<PraiseDTO>();
        List<PraiseEntity> praiseEntities = praiseRepository.getStaffPraises(userId,page);
        if(praiseEntities!=null){
            for(PraiseEntity praiseEntity:praiseEntities){
                PraiseDTO praiseDTO = new PraiseDTO();
                praiseDTO.setId(praiseEntity.getId());
                praiseDTO.setUserId(praiseEntity.getUserId());
                praiseDTO.setCodenum(praiseEntity.getCodenum());
                praiseDTO.setContent(praiseEntity.getContent());
                praiseDTO.setLevel(praiseEntity.getLevel());
                praiseDTO.setCrtTime(DateUtils.format(praiseEntity.getCrtTime()));
                praiseDTO.setTargetId(praiseEntity.getTargetId());
                praiseDTO.setTargetName(praiseEntity.getTargetName());
                praiseDTO.setAddress(praiseEntity.getAddress());
                praiseDTO.setUserName(praiseEntity.getUserName());
                praiseDTO.setMobile(praiseEntity.getMobile());
                praiseDTO.setIsAnswer(praiseEntity.getReply());
                AnswerEntity answerEntitie = answerRepository.getAnswer(praiseEntity.getId(),AnswerEntity.answer_praise);
                if(answerEntitie!=null){
                    praiseDTO.setAnswerUserId(answerEntitie.getUserId());
                    praiseDTO.setAnswerTime(DateUtils.format(answerEntitie.getCrtTime()));
                    praiseDTO.setAnswerContent(answerEntitie.getContent());
                }
                List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
                List<IsayImageEntity> isayImageEntities = isayImageRepository.getImageList(praiseEntity.getId());
                if(isayImageEntities!=null){
                    for(IsayImageEntity isayImageEntity:isayImageEntities){
                        ImageDTO imageDTO = new ImageDTO();
                        imageDTO.setUrl(isayImageEntity.getUrl());
                        imageDTOs.add(imageDTO);
                    }
                }
                praiseDTO.setImageList(imageDTOs);
                praiseDTOs.add(praiseDTO);
            }
        }
        return new SuccessApiResult(praiseDTOs);
    }

    @Override
    public ApiResult getPraiseDetail(String praiseId) {
        PraiseDTO praiseDTO = new PraiseDTO();
        PraiseEntity praiseEntity = praiseRepository.getPraiseDetail(praiseId);
        praiseDTO.setIsAnswer(praiseEntity.getReply());
        praiseDTO.setMobile(praiseEntity.getMobile());
        praiseDTO.setUserName(praiseEntity.getUserName());
        praiseDTO.setUserId(praiseEntity.getUserId());
        praiseDTO.setContent(praiseEntity.getContent());
        praiseDTO.setCrtTime(DateUtils.format(praiseEntity.getCrtTime()));
        praiseDTO.setId(praiseEntity.getId());
        praiseDTO.setCodenum(praiseEntity.getCodenum());
        praiseDTO.setAddress(praiseEntity.getAddress());
        AnswerEntity answerEntity = answerRepository.getAnswer(praiseEntity.getId(),AnswerEntity.answer_praise);
        if(answerEntity!=null){
            praiseDTO.setAnswerUserId(answerEntity.getUserId());
            praiseDTO.setAnswerTime(DateUtils.format(answerEntity.getCrtTime()));
            praiseDTO.setAnswerContent(answerEntity.getContent());
        }
        List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
        List<IsayImageEntity> isayImageEntities = isayImageRepository.getImageList(praiseEntity.getId());
        if(isayImageEntities!=null){
            for(IsayImageEntity isayImageEntity:isayImageEntities){
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setUrl(isayImageEntity.getUrl());
                imageDTOs.add(imageDTO);
            }
        }
        praiseDTO.setImageList(imageDTOs);
        praiseDTO.setTargetId(praiseEntity.getTargetId());
        praiseDTO.setTargetName(praiseEntity.getTargetName());

        return new SuccessApiResult(praiseDTO);
    }

    @Override
    public ApiResult getUnReaderCount(String targetId) {
        int number = praiseRepository.countUnread(targetId);
        return new SuccessApiResult(number);
    }

    @Override
    public ApiResult altReaderStatus(String targetId) {
        praiseRepository.altUnread(targetId);
        return new SuccessApiResult();
    }

    @Override
    public List<PraiseAdminDTO> getPraiseList(PraiseAdminDTO praiseAdminDTO, WebPage webPage) {
        PraiseEntity praiseEntity = new PraiseEntity();
        praiseEntity.setProjectId(praiseAdminDTO.getProjectIds());
        praiseEntity.setReply(praiseAdminDTO.getReply());
        praiseEntity.setContent(praiseAdminDTO.getContent());
        List<PraiseEntity> praiseEntities = praiseRepository.getPraiseList(praiseEntity,praiseAdminDTO.getBeginTime(),praiseAdminDTO.getEndTime(),webPage);
        List<PraiseAdminDTO> praiseAdminDTOs = new ArrayList<PraiseAdminDTO>();
        if(praiseEntities!=null){
            for(PraiseEntity praiseEntity1:praiseEntities){
                PraiseAdminDTO praiseAdminDTO1 = new PraiseAdminDTO();
                praiseAdminDTO1.setId(praiseEntity1.getId());
                praiseAdminDTO1.setContent(praiseEntity1.getContent());
                praiseAdminDTO1.setAddress(praiseEntity1.getAddress());
                praiseAdminDTO1.setCodenum(praiseEntity1.getCodenum());
                praiseAdminDTO1.setCrtTime(DateUtils.format(praiseEntity1.getCrtTime()));
                praiseAdminDTO1.setLevel(praiseEntity1.getLevel());
                praiseAdminDTO1.setMobile(praiseEntity1.getMobile());
                praiseAdminDTO1.setProjectIds(praiseEntity1.getProjectId());
                praiseAdminDTO1.setProjectName(praiseEntity1.getProjectName());
                praiseAdminDTO1.setReply(praiseEntity1.getReply());
                praiseAdminDTO1.setReadStatus(praiseEntity1.getReadStatus());
                praiseAdminDTO1.setTargetId(praiseEntity1.getTargetId());
                praiseAdminDTO1.setTargetName(praiseEntity1.getTargetName());
                praiseAdminDTO1.setUserId(praiseEntity1.getUserId());
                praiseAdminDTO1.setUserName(praiseEntity1.getUserName());
                praiseAdminDTO1.setStatus(praiseEntity1.getStatus());
                AnswerEntity answerEntity = answerRepository.getAnswer(praiseEntity1.getId(), AnswerEntity.answer_praise);
                if(answerEntity!=null){
                    praiseAdminDTO1.setAnswerTime(DateUtils.format(answerEntity.getCrtTime()));
                    praiseAdminDTO1.setAnswerContent(answerEntity.getContent());
                }
                praiseAdminDTOs.add(praiseAdminDTO1);
            }
        }
        return praiseAdminDTOs;
    }

    @Override
    public PraiseAdminDTO getPraise(String praiseId) {
        PraiseEntity praiseEntity = praiseRepository.getPraiseDetail(praiseId);
        AnswerEntity answerEntity = answerRepository.getAnswer(praiseEntity.getId(),AnswerEntity.answer_praise);
        PraiseAdminDTO praiseAdminDTO = new PraiseAdminDTO();
        praiseAdminDTO.setContent(praiseEntity.getContent());
        praiseAdminDTO.setUserName(praiseEntity.getUserName());
        praiseAdminDTO.setTargetId(praiseEntity.getTargetId());
        praiseAdminDTO.setReadStatus(praiseEntity.getReadStatus());
        praiseAdminDTO.setReply(praiseEntity.getReply());
        praiseAdminDTO.setAddress(praiseEntity.getAddress());
        praiseAdminDTO.setCodenum(praiseEntity.getCodenum());
        praiseAdminDTO.setCrtTime(DateUtils.format(praiseEntity.getCrtTime()));
        praiseAdminDTO.setId(praiseEntity.getId());
        praiseAdminDTO.setLevel(praiseEntity.getLevel());
        praiseAdminDTO.setMobile(praiseEntity.getMobile());
        praiseAdminDTO.setProjectIds(praiseEntity.getProjectId());
        praiseAdminDTO.setTargetName(praiseEntity.getTargetName());
        if(answerEntity!=null){
            praiseAdminDTO.setAnswerContent(answerEntity.getContent());
            praiseAdminDTO.setAnswerTime(DateUtils.format(answerEntity.getCrtTime()));
            praiseAdminDTO.setAnswerUser(answerEntity.getAnswerType());
        }
        // 查询回复图片信息
        List<IsayImageEntity> isayImage = answerRepository.getIsayImageList(praiseEntity.getId());
        List<ConsultImgDTO> consultIm = new ArrayList<>();
        for(IsayImageEntity isayImages : isayImage){
            ConsultImgDTO consultImg = new ConsultImgDTO();
            consultImg.setImg(isayImages.getUrl());
            consultIm.add(consultImg);
        }
        praiseAdminDTO.setImgList(consultIm);
        return praiseAdminDTO;
    }

    @Override
    public void deletePraise(String praiseId) {
        PraiseEntity praiseEntity = praiseRepository.getPraiseDetail(praiseId);
        praiseEntity.setStatus(PraiseEntity.STATUS_INVALID);
        praiseRepository.altPraise(praiseEntity);
    }
}
