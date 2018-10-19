package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.inf.ConsultService;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.AdminDTO.ConsultImgDTO;
import com.maxrocky.vesta.application.JsonDTO.ConsultDTO;
import com.maxrocky.vesta.application.JsonDTO.ImageDTO;
import com.maxrocky.vesta.application.AdminDTO.PropertyConsultDTO;
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
public class ConsultServiceImpl implements ConsultService {
    @Autowired
    ConsultRepository consultRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    IsayImageRepository isayImageRepository;
    @Autowired
    private HouseInfoRepository houseInfoRepository;//房屋业主地址
    /* 用户设置 */
    @Autowired
    UserSettingRepository userSettingRepository;
    /**
     *  后台核心日志 业务逻辑层接口
     */
    @Autowired
    private OperationLogService operationLogService;

    @Override
    public ApiResult AddConsult(UserInfoEntity userInfoEntity,ConsultDTO consultDTO) {
        ConsultEntity consultEntity = new ConsultEntity();
        consultEntity.setId(IdGen.uuid());
        consultEntity.setUserId(userInfoEntity.getUserId());
        consultEntity.setUserName(userInfoEntity.getRealName());
        consultEntity.setMobile(userInfoEntity.getMobile());
        // 根据用户ID查询 用户默认项目ID
        UserSettingEntity userSettingEntity = userSettingRepository.get(userInfoEntity.getUserId());
        consultEntity.setProjectId(userSettingEntity.getProjectId());
        consultEntity.setProjectName(userSettingEntity.getProjectName());
        consultEntity.setAddress(consultDTO.getAddress());
        consultEntity.setCrtTime(new Date());
        consultEntity.setContent(consultDTO.getContent().replaceAll("/\n|\r\n/", "<br/>"));
        consultEntity.setStatus(ConsultEntity.STATUS_VALID);
        consultEntity.setReplyStatus(ConsultEntity.REPLY_STATUS_NO);
        if(consultDTO.getImageList()!=null&&consultDTO.getImageList().size()>0){                     //如果有图片则添加图片
            for(int i=0;i<consultDTO.getImageList().size();i++){
                IsayImageEntity isayImageEntity = new IsayImageEntity();
                isayImageEntity.setId(IdGen.uuid());
                isayImageEntity.setCrtTime(new Date());
                isayImageEntity.setUrl(consultDTO.getImageList().get(i).getUrl());
                isayImageEntity.setBussinessId(consultEntity.getId());
                isayImageRepository.AddSayImage(isayImageEntity);
            }
        }
        consultRepository.AddConsult(consultEntity);
        return new SuccessApiResult();
    }

    @Override
    public ApiResult getConsultList(String userId,Page page) {
        List<ConsultDTO> consultDTOs = new ArrayList<ConsultDTO>();
        List<ConsultEntity> consultEntities = consultRepository.getConsults(userId,page);
        if(consultEntities!=null){
            for(ConsultEntity consultEntity:consultEntities){
                ConsultDTO consultDTO = new ConsultDTO();
                consultDTO.setId(consultEntity.getId());
                consultDTO.setUserId(consultEntity.getUserId());
                consultDTO.setContent(consultEntity.getContent());
                consultDTO.setCrtTime(DateUtils.format(consultEntity.getCrtTime()));
                consultDTO.setMobile(consultEntity.getMobile());
                consultDTO.setUserName(consultEntity.getUserName());
                consultDTO.setAddress(consultEntity.getAddress());
                List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
                List<IsayImageEntity> isayImageEntities = isayImageRepository.getImageList(consultEntity.getId());
                if(isayImageEntities!=null){
                    for(IsayImageEntity isayImageEntity:isayImageEntities){
                        ImageDTO imageDTO = new ImageDTO();
                        imageDTO.setUrl(isayImageEntity.getUrl());
                        imageDTOs.add(imageDTO);
                    }
                }
                consultDTO.setImageList(imageDTOs);
                consultDTO.setIsAnswer(consultEntity.getReplyStatus());
                AnswerEntity answerEntity = answerRepository.getAnswer(consultEntity.getId(),AnswerEntity.answer_consult);
                if(answerEntity!=null){
                    consultDTO.setAnswerContent(answerEntity.getContent());
                    consultDTO.setAnswerUserId(answerEntity.getUserId());
                    consultDTO.setAnswerTime(DateUtils.format(answerEntity.getCrtTime()));
                }
                consultDTOs.add(consultDTO);
            }
        }
        return new SuccessApiResult(consultDTOs);
    }

    @Override
    public ApiResult getConsultDetail(String id) {
        ConsultEntity consultEntity = consultRepository.getConsultDetail(id);
        ConsultDTO consultDTO = null;
        if(consultEntity!=null){
            consultDTO = new ConsultDTO();
            consultDTO.setId(consultEntity.getId());
            consultDTO.setCrtTime(DateUtils.format(consultEntity.getCrtTime()));
            consultDTO.setContent(consultEntity.getContent());
            consultDTO.setUserId(consultEntity.getUserId());
            consultDTO.setUserName(consultEntity.getUserName());
            consultDTO.setMobile(consultEntity.getMobile());
            consultDTO.setAddress(consultEntity.getAddress());
            AnswerEntity answerEntity = answerRepository.getAnswer(consultEntity.getId(),AnswerEntity.answer_consult);
            if(answerEntity!=null){
                consultDTO.setAnswerContent(answerEntity.getContent());
                consultDTO.setAnswerUserId(answerEntity.getUserId());
                consultDTO.setAnswerTime(DateUtils.format(answerEntity.getCrtTime()));
            }
            List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
            List<IsayImageEntity> isayImageEntities = isayImageRepository.getImageList(consultEntity.getId());
            if(isayImageEntities!=null){
                for(IsayImageEntity isayImageEntity:isayImageEntities){
                    ImageDTO imageDTO = new ImageDTO();
                    imageDTO.setUrl(isayImageEntity.getUrl());
                    imageDTOs.add(imageDTO);
                }
            }
            consultDTO.setImageList(imageDTOs);
        }
        return new SuccessApiResult(consultDTO);
    }

    /**
     *  初始化物业咨询管理列表
     * @param propertyConsult
     * @param webPage
     * @return
     */
    @Override
    public List<PropertyConsultDTO> queryConsult(PropertyConsultDTO propertyConsult, WebPage webPage) {
        List<PropertyConsultDTO> propertyConsultDTO = new ArrayList<>();//咨询管理DTO
        ConsultEntity consult = new ConsultEntity();//咨询表实体
        // 初始化 登陆人查询范围
        consult.setAddress(propertyConsult.getQueryScope());
        /*搜索条件*/
        consult.setProjectId(propertyConsult.getProject()); //项目Id
        consult.setUserName(propertyConsult.getCrtTimeStart());//临时变量(用户名 临时当做 咨询时间开始)
        consult.setMobile(propertyConsult.getCrtTimeEnd());//临时变量(手机 临时当做 咨询时间结束)
        consult.setReplyStatus(propertyConsult.getReplyStatus());//答复状态 1 已回复 2 未回复
        consult.setContent(propertyConsult.getContent());//咨询内容
        // 查询咨询列表数据
        List<ConsultEntity> consultList = consultRepository.queryConsultEntity(consult, webPage);
        for(ConsultEntity consultEntity : consultList){
            PropertyConsultDTO propertyConsults = new PropertyConsultDTO();
            propertyConsults.setId(consultEntity.getId()); //咨询ID
            propertyConsults.setProjectName(consultEntity.getProjectName());//项目名称
            propertyConsults.setCrtTime(DateUtils.format(consultEntity.getCrtTime()));  //咨询时间
            propertyConsults.setUserName(consultEntity.getUserName()); //用户名
            propertyConsults.setMobile(consultEntity.getMobile()); //手机
            propertyConsults.setAddress(consultEntity.getAddress()); //地址
            propertyConsults.setContent(consultEntity.getContent()); //咨询内容
            propertyConsults.setReplyStatus(consultEntity.getReplyStatus());// 回复状态
           /* if(consultEntity.getReplyStatus().equals(ConsultEntity.REPLY_STATUS_YES)){
                propertyConsults.setReplyStatus("已答复");// 回复状态
            }else {
                propertyConsults.setReplyStatus("未答复");// 回复状态
            }*/
            // 答复时间
            AnswerEntity answerEntity = answerRepository.getAnswer(consultEntity.getId(),AnswerEntity.answer_consult);
            if(null != answerEntity){//不为空则格式化答复时间
                if(!"".equals(answerEntity.getCrtTime())){
                    propertyConsults.setAnswercCrtTime(DateUtils.format(answerEntity.getCrtTime()));
                }else {// 为空则赋默认空值值
                    propertyConsults.setAnswercCrtTime("");
                }
            }else {// 为空则赋默认空值值
                propertyConsults.setAnswercCrtTime("");
            }
            propertyConsultDTO.add(propertyConsults);
        }

        return propertyConsultDTO;
    }

    /**
     * 根据咨询信息id删除(更新状态)
     * @param id
     * @param userPropertystaffEntity
     * @return
     */
    @Override
    public String removePropertyConsultById(String id, UserPropertyStaffEntity userPropertystaffEntity) {
        String consultMessage = "";
        //根据咨询信息ID查询信息
        ConsultEntity consultEntity = consultRepository.queryConsultDetail(id);
        if(null != consultEntity){
            consultEntity.setStatus(ConsultEntity.STATUS_INVALID);//当前信息更新为 2 删除状态
            //更新数据
            boolean consult = consultRepository.chengeConsult(consultEntity);
            if(consult){
                consultMessage = "0";//此信息删除成功!
            }else {
                consultMessage = "1";//此信息删除失败!
            }
            // 后台核心日志
            OperationLogDTO operation = new OperationLogDTO();
            operation.setUserName(userPropertystaffEntity.getStaffName());  // 用户名
            operation.setProjectId(userPropertystaffEntity.getProjectId());// 用户项目ID
            operation.setContent("删除咨询信息!");      // 操作动作
            // 添加后台核心日志
            operationLogService.addOperationLog(operation);
        }else {
            consultMessage = "2";//此信息不存在!
        }

        return consultMessage;
    }

    /**
     * 根据咨询信息id 查询咨询信息及回复信息
     * @param id
     * @return
     */
    @Override
    public PropertyConsultDTO queryAnswerMessage(String id) {
        PropertyConsultDTO propertyConsult = new PropertyConsultDTO();
        //查询咨询信息
        ConsultEntity consultEntity = consultRepository.queryConsultDetail(id);
        if(null != consultEntity){
            propertyConsult.setId(consultEntity.getId()); //咨询ID
            propertyConsult.setCrtTime(DateUtils.format(consultEntity.getCrtTime())); //咨询时间
            propertyConsult.setUserName(consultEntity.getUserName()); //用户名
            propertyConsult.setMobile(consultEntity.getMobile()); //手机
            propertyConsult.setAddress(consultEntity.getAddress());  //地址
            propertyConsult.setContent(consultEntity.getContent());  //咨询内容
        }
        // 查询回复信息
        AnswerEntity answerEntity = answerRepository.getAnswer(id,AnswerEntity.answer_consult);
        if(null != answerEntity){
            propertyConsult.setAnswerContent(answerEntity.getContent());//回复信息
        }
        // 查询回复图片信息
        List<IsayImageEntity> isayImage = answerRepository.getIsayImageList(consultEntity.getId());
        List<ConsultImgDTO> consultIm = new ArrayList<>();
        for(IsayImageEntity isayImages : isayImage){
            ConsultImgDTO consultImg = new ConsultImgDTO();
            consultImg.setImg(isayImages.getUrl());
            consultIm.add(consultImg);
        }
        propertyConsult.setImgList(consultIm);
        return propertyConsult;
    }

    /**
     * 回复咨询信息(添加或修改回复信息)
     * @param userPropertystaffEntit
     * @param propertyConsult
     */
    @Override
    public void saveORupdatePropertyConsult(UserPropertyStaffEntity userPropertystaffEntit, PropertyConsultDTO propertyConsult) {
        //查询咨询信息
        ConsultEntity consultEntity = consultRepository.queryConsultDetail(propertyConsult.getId());
        if(null != consultEntity){
            consultEntity.setReplyStatus(ConsultEntity.REPLY_STATUS_YES);//修改咨询信息状态 为 已回复
            //更新咨询信息
            boolean consult = consultRepository.chengeConsult(consultEntity);
        }
        //查询咨询回复信息
        AnswerEntity answerEntity = answerRepository.getAnswer(propertyConsult.getId(),AnswerEntity.answer_consult);
        //回复信息不为空 则更新回复信息内容时间
        if(null != answerEntity){
            answerEntity.setContent(propertyConsult.getAnswerContent());//回复内容
            answerEntity.setCrtTime(new Date());//回复内容时间
            answerRepository.chengeAnswerEntity(answerEntity);
        }else {//为空则添加回复信息
            AnswerEntity answer = new AnswerEntity();
            answer.setId(IdGen.uuid()); //回复ID
            answer.setConsultId(consultEntity.getId()); //咨询ID
            answer.setUserId(consultEntity.getUserId());//用户ID
            answer.setAnswerType(AnswerEntity.answer_consult);//回复类型
            answer.setContent(propertyConsult.getAnswerContent());//回复内容
            answer.setCrtTime(new Date());//回复时间
            //执行添加回复信息
            answerRepository.AddAnswer(answer);
        }
    }
}
