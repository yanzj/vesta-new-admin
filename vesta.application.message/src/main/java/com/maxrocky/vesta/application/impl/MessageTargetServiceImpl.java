package com.maxrocky.vesta.application.impl;


import com.maxrocky.vesta.application.DTO.adminDTO.MessageDetailDTO;
import com.maxrocky.vesta.application.DTO.adminDTO.MessageTargetDTO;
import com.maxrocky.vesta.application.DTO.appDTO.SecMessageListDto;
import com.maxrocky.vesta.application.inf.MessageDetailService;
import com.maxrocky.vesta.application.DTO.adminDTO.MessageMouldDTO;
import com.maxrocky.vesta.application.DTO.appDTO.FirMessageListDto;
import com.maxrocky.vesta.application.inf.MessageMouldService;
import com.maxrocky.vesta.application.inf.MessageTargetService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.MessageTargetEntity;
import com.maxrocky.vesta.domain.repository.MessageTargetRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/14.
 */
@Service
public class MessageTargetServiceImpl implements MessageTargetService {

    @Resource
    private MessageTargetRepository messageTargetRepository;

    @Resource
    private MessageMouldService messageMouldService;

    @Resource
    private MessageDetailService messageDetailService;


    /**
     * 前台接口----------------------------------------------------------------------------------------
     */
    /**
     * 获取该用户未读消息总数
     * @param userId
     * @return
     */
    @Override
    public int countUnreadMessage(String userId) {

        int count = messageTargetRepository.countUnreadMessage(userId);


        return count;
    }

    /**
     * 整个模块的消息标为已读
     * @param userId
     * @param messageType
     * @return
     */
    @Override
    public ApiResult readMessageByTitle(String userId, String messageType) throws GeneralException {
        if (userId==null){
            return ErrorResource.getError("tip_me0001");
        }
        if (messageType==null){
            return ErrorResource.getError("tip_me0002");
        }
        try {
            List<MessageTargetEntity> messageTargetEntities = messageTargetRepository.listMessageByTarget(userId, messageType);
            if (messageTargetEntities.size() > 0) {
                for (MessageTargetEntity messageTargetEntity : messageTargetEntities) {
                    messageTargetEntity.setMessageReadStatus("1");
                    messageTargetEntity.setMessageReadTime(DateUtils.getDate());
                    messageTargetRepository.updateMessageTarget(messageTargetEntity);
                }
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_me00000001"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 某条消息标为已读
     * @param userId
     * @param messageTargetId
     * @return
     */
    @Override
    public ApiResult readMessageById(String userId, String messageTargetId) throws GeneralException{
        if (userId==null){
            return ErrorResource.getError("tip_me0001");
        }
        if (messageTargetId==null){
            return ErrorResource.getError("tip_me0003");
        }
        try{
        MessageTargetEntity messageTargetEntity = messageTargetRepository.getMessageTarget(messageTargetId);
//            List<MessageTargetEntity> messageTargetEntities = messageTargetRepository.
        messageTargetEntity.setMessageReadStatus("1");
        messageTargetEntity.setMessageReadTime(DateUtils.getDate());
        messageTargetRepository.updateMessageTarget(messageTargetEntity);
        return new SuccessApiResult(SuccessResource.getResource("tip_message_readSuccess"), null);//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 删除某条消息
     * @param userId
     * @param messageTargetId
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult delMessageById(String userId, String messageTargetId) throws GeneralException {
        if (userId==null){
            return ErrorResource.getError("tip_me0001");
        }
        if (messageTargetId==null){
            return ErrorResource.getError("tip_me0003");
        }
        try{
            MessageTargetEntity messageTargetEntity = messageTargetRepository.getMessageTarget(messageTargetId);
            messageTargetEntity.setMessageDeleteStatue("0");
            messageTargetEntity.setMessageDeleteTime(DateUtils.getDate());
            messageTargetRepository.updateMessageTarget(messageTargetEntity);
            return new SuccessApiResult(SuccessResource.getResource("tip_message_delSuccess"), null);//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取一级消息列表,userType:1-业主端，2-员工端
     * @param userType
     * @param userId
     * @return
     */
    @Override
    public ApiResult getFirMessageList(String userId,String userType,Page page) throws GeneralException  {
        if (userId==null){
            return ErrorResource.getError("tip_me0001");
        }
        if (userType==null){
            return ErrorResource.getError("tip_me0004");
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            List<MessageMouldDTO> messageMouldDTOs = messageMouldService.listMessageMould(userType, page);//获取请求来源的所有模板，目的是获取所有类型，userType：1-业主，2-员工
            List<FirMessageListDto> firMessageListDtos = new ArrayList<>();
            if (messageMouldDTOs.size() > 0) {
                for (MessageMouldDTO messageMouldDTO : messageMouldDTOs) {
                    //获取对应用户和对应类型的第一条收件信息
                    MessageTargetEntity messageTargetEntity = messageTargetRepository.getMessageByTarget(userId, messageMouldDTO.getMessageType());
                    if (messageTargetEntity != null) {//当该模块内有消息时继续进行
                        //获取用户在该模块下所有未读信息
                        int unReadCount = messageTargetRepository.countUnreadMessageByTitleAndUser(messageMouldDTO.getMessageType(), userId);
                        //获得收件的消息详情
                        MessageDetailDTO messageDetailDTO = messageDetailService.getMessageDetailById(messageTargetEntity.getMessageDetailId());
                        if (messageDetailDTO != null) {//当消息详情不为空时继续进行
                            FirMessageListDto firMessageListDto = new FirMessageListDto();
                            //先得到消息信息
                            firMessageListDto.setMessageDetailId(messageDetailDTO.getMessageDetailId());
                            if (messageDetailDTO.getMessageTitle().length() > 15) {//消息标题不长过十五字
                                firMessageListDto.setMessageTitle(messageDetailDTO.getMessageTitle().substring(0, 15) + "···");
                            } else {
                                firMessageListDto.setMessageTitle(messageDetailDTO.getMessageTitle());
                            }

                            if (messageDetailDTO.getMessageContent().length() > 30) {//列表里的消息内容不超过30字
                                firMessageListDto.setMessageContent(messageDetailDTO.getMessageContent().substring(1, 30) + "···");
                            } else {
                                firMessageListDto.setMessageContent(messageDetailDTO.getMessageContent());
                            }
                            firMessageListDto.setMessageCreateTime(simpleDateFormat.format(messageDetailDTO.getMessageCreateTime()));
                            firMessageListDto.setMessageTargetUrl(messageDetailDTO.getMessageTargetUrl());
                            firMessageListDto.setMessageSenderName(messageDetailDTO.getMessageSenderName());
                            firMessageListDto.setMessageType(messageDetailDTO.getMessageType());
                            //再得到消息接收信息
                            firMessageListDto.setMessageTargetId(messageTargetEntity.getMessageTargetId());
                            firMessageListDto.setUserId(messageTargetEntity.getUserId());
                            firMessageListDto.setTargetCreateTime(simpleDateFormat.format(messageTargetEntity.getTargetCreateTime()));
                            firMessageListDto.setMessagePushStatus(messageTargetEntity.getMessagePushStatus());
//                firMessageListDto.setMessagePushTime(simpleDateFormat.format(messageTargetEntity.getMessagePushTime()));
                            firMessageListDto.setCountUnreadMessage(unReadCount);

                            firMessageListDtos.add(firMessageListDto);
                        }
                    }
                }
            }
            return new SuccessApiResult(firMessageListDtos);
        }catch (Exception e){
        e.printStackTrace();
        throw new GeneralException("100","系统处理错误");
    }

    }

    @Override
    public ApiResult getFirMessageList(String userId, String userType, WebPage webpage) throws GeneralException {
        if (userId==null){
            return ErrorResource.getError("tip_me0001");
        }
        if (userType==null){
            return ErrorResource.getError("tip_me0004");
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            List<MessageMouldDTO> messageMouldDTOs = messageMouldService.listMessageMould(userType, webpage);//获取请求来源的所有模板，目的是获取所有类型，userType：1-业主，2-员工
            List<FirMessageListDto> firMessageListDtos = new ArrayList<>();
            if (messageMouldDTOs.size() > 0) {
                for (MessageMouldDTO messageMouldDTO : messageMouldDTOs) {
                    //获取对应用户和对应类型的第一条收件信息
                    MessageTargetEntity messageTargetEntity = messageTargetRepository.getMessageByTarget(userId, messageMouldDTO.getMessageType());
                    if (messageTargetEntity != null) {//当该模块内有消息时继续进行
                        //获取用户在该模块下所有未读信息
                        int unReadCount = messageTargetRepository.countUnreadMessageByTitleAndUser(messageMouldDTO.getMessageType(), userId);
                        //获得收件的消息详情
                        MessageDetailDTO messageDetailDTO = messageDetailService.getMessageDetailById(messageTargetEntity.getMessageDetailId());
                        if (messageDetailDTO != null) {//当消息详情不为空时继续进行
                            FirMessageListDto firMessageListDto = new FirMessageListDto();
                            //先得到消息信息
                            firMessageListDto.setMessageDetailId(messageDetailDTO.getMessageDetailId());
                            if (messageDetailDTO.getMessageTitle().length() > 15) {//消息标题不长过十五字
                                firMessageListDto.setMessageTitle(messageDetailDTO.getMessageTitle().substring(0, 15) + "···");
                            } else {
                                firMessageListDto.setMessageTitle(messageDetailDTO.getMessageTitle());
                            }

                            if (messageDetailDTO.getMessageContent().length() > 30) {//列表里的消息内容不超过30字
                                firMessageListDto.setMessageContent(messageDetailDTO.getMessageContent().substring(1, 30) + "···");
                            } else {
                                firMessageListDto.setMessageContent(messageDetailDTO.getMessageContent());
                            }
                            firMessageListDto.setMessageCreateTime(simpleDateFormat.format(messageDetailDTO.getMessageCreateTime()));
                            firMessageListDto.setMessageTargetUrl(messageDetailDTO.getMessageTargetUrl());
                            firMessageListDto.setMessageSenderName(messageDetailDTO.getMessageSenderName());
                            firMessageListDto.setMessageType(messageDetailDTO.getMessageType());
                            //再得到消息接收信息
                            firMessageListDto.setMessageTargetId(messageTargetEntity.getMessageTargetId());
                            firMessageListDto.setUserId(messageTargetEntity.getUserId());
                            firMessageListDto.setTargetCreateTime(simpleDateFormat.format(messageTargetEntity.getTargetCreateTime()));
                            firMessageListDto.setMessagePushStatus(messageTargetEntity.getMessagePushStatus());
//                firMessageListDto.setMessagePushTime(simpleDateFormat.format(messageTargetEntity.getMessagePushTime()));
                            firMessageListDto.setCountUnreadMessage(unReadCount);

                            firMessageListDtos.add(firMessageListDto);
                        }
                    }
                }
            }
            return new SuccessApiResult(firMessageListDtos);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }

    }

    /**
     * 获取二级消息列表
     * @param userId
     * @param page
     * @return
     */
    @Override
    public ApiResult getSecMessageList(String userId,Page page)  throws GeneralException {
        if (userId==null){
            return ErrorResource.getError("tip_me0001");
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<MessageTargetEntity> messageTargetEntities = messageTargetRepository.listMessageByTargetPage(userId, page);
            List<SecMessageListDto> secMessageListDtos = new ArrayList<>();
            if (messageTargetEntities.size() > 0) {
                for (MessageTargetEntity messageTargetEntity : messageTargetEntities) {
                    MessageDetailDTO messageDetailDTO = messageDetailService.getMessageDetailById(messageTargetEntity.getMessageDetailId());
                    if (messageDetailDTO != null) {
                        SecMessageListDto secMessageListDto = new SecMessageListDto();
                        //先得到消息信息
                        secMessageListDto.setMessageDetailId(messageDetailDTO.getMessageDetailId());

                        if (messageDetailDTO.getMessageTitle().length() > 25) {//消息标题不长过十五字
                            secMessageListDto.setMessageTitle(messageDetailDTO.getMessageTitle().substring(0, 25) + "···");
                        } else {
                            secMessageListDto.setMessageTitle(messageDetailDTO.getMessageTitle());
                        }

                        if (messageDetailDTO.getMessageContent().length() > 40) {//列表里的消息内容不超过30字
                            secMessageListDto.setMessageContent(messageDetailDTO.getMessageContent().substring(1, 40) + "···");
                        } else {
                            secMessageListDto.setMessageContent(messageDetailDTO.getMessageContent());
                        }

                        secMessageListDto.setMessageCreateTime(simpleDateFormat.format(messageDetailDTO.getMessageCreateTime()));
                        secMessageListDto.setMessageTargetUrl(messageDetailDTO.getMessageTargetUrl());
                        secMessageListDto.setMessageSenderName(messageDetailDTO.getMessageSenderName());
                        secMessageListDto.setMessageType(messageDetailDTO.getMessageType());
                        //再得到消息接收信息
                        secMessageListDto.setMessageTargetId(messageTargetEntity.getMessageTargetId());
                        secMessageListDto.setUserId(messageTargetEntity.getUserId());
                        secMessageListDto.setTargetCreateTime(simpleDateFormat.format(messageTargetEntity.getTargetCreateTime()));
                        secMessageListDto.setMessagePushStatus(messageTargetEntity.getMessagePushStatus());
                        secMessageListDto.setMessagePushTime("");
                        secMessageListDto.setMessageReadStatus(messageTargetEntity.getMessageReadStatus());
                        secMessageListDto.setMessageReadTime("");
                        secMessageListDto.setMessageSenderName("");
//                    secMessageListDto.setMessagePushTime(simpleDateFormat.format(messageTargetEntity.getMessagePushTime()));

                        secMessageListDtos.add(secMessageListDto);
                    }
                }
            }

            return new SuccessApiResult(secMessageListDtos);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 员工端消息列表
     * @param userId
     *
     * @param page
     *
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getStaffMessageList(String userId, Page page) throws GeneralException {
        if (userId==null){
            return ErrorResource.getError("tip_me0001");
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<MessageTargetEntity> messageTargetEntities = messageTargetRepository.listMessageByPage(userId, page);
            List<SecMessageListDto> secMessageListDtos = new ArrayList<>();
            if (messageTargetEntities.size() > 0) {
                for (MessageTargetEntity messageTargetEntity : messageTargetEntities) {
                    MessageDetailDTO messageDetailDTO = messageDetailService.getMessageDetailById(messageTargetEntity.getMessageDetailId());
                    if (messageDetailDTO != null) {
                        SecMessageListDto secMessageListDto = new SecMessageListDto();
                        //先得到消息信息
                        secMessageListDto.setMessageDetailId(messageDetailDTO.getMessageDetailId());

                        if (messageDetailDTO.getMessageTitle().length() > 25) {//消息标题不长过十五字
                            secMessageListDto.setMessageTitle(messageDetailDTO.getMessageTitle().substring(0, 25) + "···");
                        } else {
                            secMessageListDto.setMessageTitle(messageDetailDTO.getMessageTitle());
                        }

                        if (messageDetailDTO.getMessageContent().length() > 40) {//列表里的消息内容不超过30字
                            secMessageListDto.setMessageContent(messageDetailDTO.getMessageContent().substring(0, 40) + "···");
                        } else {
                            secMessageListDto.setMessageContent(messageDetailDTO.getMessageContent());
                        }

                        secMessageListDto.setMessageCreateTime(simpleDateFormat.format(messageDetailDTO.getMessageCreateTime()));
                        secMessageListDto.setMessageTargetUrl(messageDetailDTO.getMessageTargetUrl());
                        if (messageDetailDTO.getMessageSenderName()!=null) {
                            secMessageListDto.setMessageSenderName(messageDetailDTO.getMessageSenderName());
                        }else {
                            secMessageListDto.setMessageSenderName("");
                        }
                        secMessageListDto.setMessageType(messageDetailDTO.getMessageType());
                        //再得到消息接收信息
                        secMessageListDto.setMessageTargetId(messageTargetEntity.getMessageTargetId());
                        secMessageListDto.setUserId(messageTargetEntity.getUserId());
                        secMessageListDto.setTargetCreateTime(simpleDateFormat.format(messageTargetEntity.getTargetCreateTime()));
                        secMessageListDto.setMessagePushStatus(messageTargetEntity.getMessagePushStatus());
//                    secMessageListDto.setMessagePushTime(simpleDateFormat.format(messageTargetEntity.getMessagePushTime()));
                        secMessageListDto.setMessagePushTime("");
                        secMessageListDto.setMessageReadStatus("");
                        secMessageListDto.setMessageReadTime("");

                        secMessageListDtos.add(secMessageListDto);
                    }
                }
            }

            return new SuccessApiResult(secMessageListDtos);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取某条消息详细信息
     * @param messageTargetId
     * @return
     */
    @Override
    public ApiResult getMessageDetail(String messageTargetId) throws GeneralException {

        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SecMessageListDto secMessageListDto = new SecMessageListDto();
        MessageTargetEntity messageTargetEntity = messageTargetRepository.getMessageTarget(messageTargetId);
        if (messageTargetEntity != null) {
            MessageDetailDTO messageDetailDTO = messageDetailService.getMessageDetailById(messageTargetEntity.getMessageDetailId());
            if (messageDetailDTO != null) {

                //先得到消息信息
                secMessageListDto.setMessageDetailId(messageDetailDTO.getMessageDetailId());

                if (messageDetailDTO.getMessageTitle().length() > 25) {//消息标题不长过十五字
                    secMessageListDto.setMessageTitle(messageDetailDTO.getMessageTitle().substring(0, 25) + "···");
                } else {
                    secMessageListDto.setMessageTitle(messageDetailDTO.getMessageTitle());
                }

                if (messageDetailDTO.getMessageContent().length() > 40) {//列表里的消息内容不超过30字
                    secMessageListDto.setMessageContent(messageDetailDTO.getMessageContent().substring(0, 40) + "···");
                } else {
                    secMessageListDto.setMessageContent(messageDetailDTO.getMessageContent());
                }

                secMessageListDto.setMessageCreateTime(simpleDateFormat.format(messageDetailDTO.getMessageCreateTime()));
                secMessageListDto.setMessageTargetUrl(messageDetailDTO.getMessageTargetUrl());
                secMessageListDto.setMessageSenderName(messageDetailDTO.getMessageSenderName());
                secMessageListDto.setMessageType(messageDetailDTO.getMessageType());
                //再得到消息接收信息
                secMessageListDto.setMessageTargetId(messageTargetEntity.getMessageTargetId());
                secMessageListDto.setUserId(messageTargetEntity.getUserId());
                secMessageListDto.setTargetCreateTime(simpleDateFormat.format(messageTargetEntity.getTargetCreateTime()));
                secMessageListDto.setMessagePushStatus(messageTargetEntity.getMessagePushStatus());
//                secMessageListDto.setMessagePushTime(simpleDateFormat.format(messageTargetEntity.getMessagePushTime()));
            }
        }
        return new SuccessApiResult(secMessageListDto);
    }catch (Exception e){
        e.printStackTrace();
        throw new GeneralException("100","系统处理错误");
    }
    }


    /**
     * 后台接口----------------------------------------------------------------------------------------
     */


    /**
     * 添加消息收件人
     * DTO里要带的参数，MessageDetailId，UserId
     * @param messageTargetDTOList
     * @return
     */
    @Override
    public int saveMessageTarget(List<MessageTargetDTO> messageTargetDTOList) {
        int successNum = 0;             //目标添加数量记录
        if (messageTargetDTOList.size()>0){
            List<MessageTargetEntity> messageTargetEntities = new ArrayList<>();
            for (MessageTargetDTO messageTargetDTO:messageTargetDTOList){
                MessageTargetEntity messageTargetEntity = new MessageTargetEntity();
                messageTargetEntity.setMessageTargetId(IdGen.uuid());//定义接收信息Id
                messageTargetEntity.setMessageDetailId(messageTargetDTO.getMessageDetailId());//赋值消息id
                messageTargetEntity.setUserId(messageTargetDTO.getUserId());//赋值用户Id
                messageTargetEntity.setMessageType(messageTargetDTO.getMessageType());//消息模块
                messageTargetEntity.setTargetCreateTime(DateUtils.getDate());//消息发送时间
                messageTargetEntity.setMessagePushStatus("0");//消息推送状态，未推送
                if (messageTargetDTO.getIsPush()!=null) {
                    if (messageTargetDTO.getIsPush().equals("1")) {
                        messageTargetEntity.setMessagePushStatus("1");//消息推送状态，不用推送
                    }
                }
                messageTargetEntity.setMessageReadStatus("0");//消息读取状态，未读
                messageTargetEntity.setMessageDeleteStatue("1");//消息删除状态，未删
                messageTargetEntity.setUserType(messageTargetDTO.getUserType());
                messageTargetEntities.add(messageTargetEntity );
            }
            if (messageTargetEntities.size()>0) {
//                for (MessageTargetEntity messageTargetEntity:messageTargetEntities){
//                    messageTargetRepository.saveMessageTarget(messageTargetEntity);
//                    successNum++;
//                }
                try {
                    messageTargetRepository.saveListTarget(messageTargetEntities);
                    successNum=messageTargetEntities.size();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return successNum;
    }
}
