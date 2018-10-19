package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.DTO.OperationLogSearchDTO;
import com.maxrocky.vesta.domain.model.OperationLogEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.OperationLogRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunmei on 2016/2/18.
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    private OperationLogRepository operationLogRepository;
    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;
    @Override
    public List<OperationLogDTO> OperationLogManage(OperationLogSearchDTO operationLogSearchDTO, WebPage webPage) {
        List<OperationLogDTO> operationLogDTOList = new ArrayList<>();//DTO集合
        OperationLogEntity operationLogEntity=new OperationLogEntity();
        operationLogEntity.setUserName(operationLogSearchDTO.getUserName());
        operationLogEntity.setContent(operationLogSearchDTO.getContent());
        // 初始化 登陆人所负责范围
        operationLogEntity.setProjectId(operationLogSearchDTO.getQueryScope());
        if(operationLogSearchDTO.getStartDate()!=null&&!"".equals(operationLogSearchDTO.getStartDate())) {
            operationLogEntity.setStartDate(DateUtils.parse(operationLogSearchDTO.getStartDate()+ " 00:00:00"));
        }
        if(operationLogSearchDTO.getEndDate()!=null&&!"".equals(operationLogSearchDTO.getEndDate())) {
            operationLogEntity.setEndDate(DateUtils.parse(operationLogSearchDTO.getEndDate()+ " 23:59:59"));
        }
        List<OperationLogEntity> OperationLogsList= operationLogRepository.OperationLogManage(operationLogEntity, webPage);
        for(OperationLogEntity OperationLogs : OperationLogsList){
            OperationLogDTO operationLogDTO= new OperationLogDTO();
            operationLogDTO.setName(OperationLogs.getName());
            operationLogDTO.setUserName(OperationLogs.getUserName());
            operationLogDTO.setContent(OperationLogs.getContent());
            operationLogDTO.setTime(DateUtils.format(OperationLogs.getTime()));

            operationLogDTOList.add(operationLogDTO);
        }
        return operationLogDTOList;
    }

    @Override
    public void addOperationLog(OperationLogDTO operationLogDTO) {
        OperationLogEntity operationLogEntity=new OperationLogEntity();
        UserPropertyStaffEntity userPropertyStaff=userPropertyStaffRepository.testStaffByUserName(operationLogDTO.getUserName());
       if(null!=userPropertyStaff){
           operationLogEntity.setName(userPropertyStaff.getStaffName());
       }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String content=operationLogEntity.getName()+"在"+sdf.format(new Date())+operationLogDTO.getContent();
        operationLogEntity.setContent(content);
        operationLogEntity.setUserName(operationLogDTO.getUserName());
        operationLogEntity.setTime(new Date());
        operationLogEntity.setId(IdGen.uuid());
        operationLogEntity.setProjectId(operationLogDTO.getProjectId());
        operationLogRepository.addOperationLog(operationLogEntity);
    }
}
