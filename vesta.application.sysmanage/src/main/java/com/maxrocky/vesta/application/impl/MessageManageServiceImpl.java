package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.inf.MessageInsertService;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.DTO.MessageManageDTO;
import com.maxrocky.vesta.application.DTO.MessageTypeDTO;
import com.maxrocky.vesta.application.DTO.adminDTO.MessageInsertDTO;
import com.maxrocky.vesta.application.inf.MessageManageService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.MessageManageRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sunmei on 2016/3/3.
 */
@Service
public class MessageManageServiceImpl implements MessageManageService {
    @Autowired
    MessageManageRepository messageManageRepository;
    @Autowired
    MessageInsertService messageInsertService;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    OperationLogService operationLogService;
    @Override
    public List<MessageManageDTO> MessageList(MessageManageDTO messageManageDTO, WebPage webPage) {
        List<MessageManageDTO> messageManageDTOList = new ArrayList<>();//DTO集合
        MessageManageEntity messageManageEntity = new MessageManageEntity();

        // 初始化 登陆人所负责范围
        messageManageEntity.setProjectId(messageManageDTO.getProjectId());

        List<MessageManageEntity> messageManageList = messageManageRepository.MessageList(messageManageEntity,webPage);
        for (MessageManageEntity message : messageManageList) {
            MessageManageDTO messageManage = new MessageManageDTO();
            messageManage.setContent(message.getContent());
            messageManage.setTypeId(message.getType().equals("1") ? "会议" : "通知");
            messageManage.setTitle(message.getTitle());
            messageManage.setMessageManageId(message.getId());
            messageManageDTOList.add(messageManage);
        }
        return messageManageDTOList;
    }

    @Override
    public void addMessage(UserPropertyStaffEntity userPropertystaffEntit,MessageManageDTO messageManageDTO) {
        MessageManageEntity messageManageEntity = new MessageManageEntity();
        messageManageEntity.setProjectId(userPropertystaffEntit.getProjectId());
        messageManageEntity.setType(messageManageDTO.getTypeId());
        messageManageEntity.setContent(messageManageDTO.getContent());
        messageManageEntity.setId(messageManageDTO.getMessageManageId());
        messageManageEntity.setCreatBy(userPropertystaffEntit.getStaffName());
        messageManageEntity.setTitle(messageManageDTO.getTitle());
        messageManageEntity.setCreatDate(new Date());

        if(messageManageDTO.getMessageManageId()==null||("".equals(messageManageDTO.getMessageManageId()))){
            String messageId=IdGen.uuid();
            for(String department:messageManageDTO.getDepartment()){
                MessageDepartmentEntity messageDepartment =new MessageDepartmentEntity();
                messageDepartment.setId(IdGen.uuid());
                messageDepartment.setMessageId(messageId);
                messageDepartment.setDepartmentId(department);
                messageManageRepository.addMessageDepartment(messageDepartment);
            }
            messageManageEntity.setId(messageId);

            messageManageRepository.addMessage(messageManageEntity);
            //添加日志
            OperationLogDTO operationLogDTO =new OperationLogDTO();
            operationLogDTO.setProjectId(messageManageEntity.getProjectId());
            operationLogDTO.setUserName(userPropertystaffEntit.getUserName());
            operationLogDTO.setContent("新增通知");
            operationLogService.addOperationLog(operationLogDTO);
        }else{
            messageManageRepository.updateMessage(messageManageEntity);
            List<MessageDepartmentEntity> MessageDepartmentList=messageManageRepository.getMessageDepartment(messageManageEntity.getId());
            for(MessageDepartmentEntity messageDepartment:MessageDepartmentList){
                messageManageRepository.deleteMessageDepartment(messageDepartment.getId());
            }
            for(String department:messageManageDTO.getDepartment()){
                MessageDepartmentEntity departmentEntity =new MessageDepartmentEntity();
                departmentEntity.setId(IdGen.uuid());
                departmentEntity.setMessageId(messageManageDTO.getMessageManageId());
                departmentEntity.setDepartmentId(department);
                messageManageRepository.addMessageDepartment(departmentEntity);
            }
            //添加日志
            OperationLogDTO operationLogDTO =new OperationLogDTO();
            operationLogDTO.setProjectId(messageManageEntity.getProjectId());
            operationLogDTO.setUserName(userPropertystaffEntit.getUserName());
            operationLogDTO.setContent("更新通知");
            operationLogService.addOperationLog(operationLogDTO);
        }
        // 添加消息推送
        MessageInsertDTO messageInsertDTO = new MessageInsertDTO();
        messageInsertDTO.setMessageTitle(messageManageDTO.getTitle());  // 消息标题
        messageInsertDTO.setMessageContent(messageManageDTO.getContent());//会议通知内容
        messageInsertDTO.setMessageUserType("2"); // 发送人类型，1 为业主
        if (messageManageDTO.getTypeId().equals("1")) {
            messageInsertDTO.setMessageType("6");     // 会议
        }
        if (messageManageDTO.getTypeId().equals("2")) {
            messageInsertDTO.setMessageType("7");     // 通知
        }
        messageInsertDTO.setMessageTypeState("1");// 消息类型对应状态
        messageInsertDTO.setMessageSenderName(userPropertystaffEntit.getStaffName());//会议通知发布人
        messageInsertDTO.setMessageUrl("9999");
        List<String> users = new ArrayList<>();
            // 根据部门projectId 查询 项目部门下所有 员工
        if(messageManageDTO.getDepartment().size()>0){
            for(String department :messageManageDTO.getDepartment()){
                List<UserPropertyStaffEntity>  UserPropertyStaffList = userPropertyStaffRepository.listStaffByCompanyAndSection(messageManageEntity.getProjectId(),department);
                if(UserPropertyStaffList.size() > 0){
                    for(UserPropertyStaffEntity userProperty : UserPropertyStaffList ){
                        users.add(userProperty.getStaffId());//员工ID
                    }
                }
            }
            messageInsertService.InsertMessage(messageInsertDTO, users);
        }

    }

    @Override
    public List<MessageTypeDTO> getMessageType() {
        List<MessageTypeDTO> messageTypes = new ArrayList<>();
        MessageTypeDTO messageTypeDTO1 = new MessageTypeDTO();
        messageTypeDTO1.setTypeId("0");
        messageTypeDTO1.setName("----------请选择消息类型----------");
        MessageTypeDTO messageTypeDTO2 = new MessageTypeDTO();
        messageTypeDTO2.setTypeId("1");
        messageTypeDTO2.setName("会议");
        MessageTypeDTO messageTypeDTO3 = new MessageTypeDTO();
        messageTypeDTO3.setTypeId("2");
        messageTypeDTO3.setName("通知");
        messageTypes.add(messageTypeDTO1);
        messageTypes.add(messageTypeDTO2);
        messageTypes.add(messageTypeDTO3);
        return messageTypes;
    }

    @Override
    public List<String> getMessageDepartment(String messageId) {
        if(null!=messageId&&!messageId.equals("")){
             List<MessageDepartmentEntity> messageManage =messageManageRepository.getMessageDepartment(messageId);
            List<String> messageDepartment=new ArrayList<>();
            for(MessageDepartmentEntity MessageDepartmentEntity:messageManage){
                messageDepartment.add(MessageDepartmentEntity.getDepartmentId());
            }
            return messageDepartment;
        }
        return null;
    }

    @Override
    public MessageManageDTO getMessages(String Id) {
        MessageManageDTO messageManageDTO = new MessageManageDTO();
        if(null!=Id&&!Id.equals("")){
            MessageManageEntity MessageManage= messageManageRepository.getMessages(Id);
            messageManageDTO.setTitle(MessageManage.getTitle());
            messageManageDTO.setContent(MessageManage.getContent());
            messageManageDTO.setTypeId(MessageManage.getType());
            messageManageDTO.setMessageManageId(MessageManage.getId());
        }
        return messageManageDTO;
    }
}
