package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.LoginLogSearchDTO;
import com.maxrocky.vesta.application.DTO.LoginLogDTO;
import com.maxrocky.vesta.application.inf.LoginLogService;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.model.LoginLogEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.repository.LoginLogRepository;
import com.maxrocky.vesta.domain.repository.PropertyCompanyRepository;
import com.maxrocky.vesta.domain.repository.UserInfoRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunmei on 2016/2/18.
 */
@Service
public class LoginLogServiceImpl implements LoginLogService{
    @Autowired
    private LoginLogRepository loginLogRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PropertyCompanyRepository propertyCompanyRepository;

    @Override
    public List<LoginLogDTO> LoginLogManage(LoginLogSearchDTO loginLogSearchDTO, WebPage webPage) {
        List<LoginLogDTO> loginLogDTOList = new ArrayList<>();//DTO集合
        LoginLogEntity LoginLogEntity=new LoginLogEntity();
        LoginLogEntity.setProjectId(loginLogSearchDTO.getQueryScope());//项目
        LoginLogEntity.setRegionId(loginLogSearchDTO.getPropertyArea());//区域
        if(null!=loginLogSearchDTO.getStartDate()&&!"".equals(loginLogSearchDTO.getStartDate())){
            LoginLogEntity.setStartDate(DateUtils.parse(loginLogSearchDTO.getStartDate()));//开始时间
        }
       if(null!=loginLogSearchDTO.getEndDate()&&!"".equals(loginLogSearchDTO.getEndDate())){
           LoginLogEntity.setEndDate(DateUtils.parse(loginLogSearchDTO.getEndDate()));//结束时间
       }
        LoginLogEntity.setUserName(loginLogSearchDTO.getUserName());//用户名
        List<LoginLogEntity> loginLogList= loginLogRepository.LoginLogManage(LoginLogEntity,webPage);//查询登录日志列表
        for(LoginLogEntity loginLogs : loginLogList){
            LoginLogDTO loginLogDTO= new LoginLogDTO();
            loginLogDTO.setName(loginLogs.getName());
            loginLogDTO.setUserName(loginLogs.getUserName());
            loginLogDTO.setProject(loginLogs.getProject());
            loginLogDTO.setLogintime(loginLogs.getLogintime());
            loginLogDTO.setUserIp(loginLogs.getUserIp());
            loginLogDTO.setLoginMsg(loginLogs.getLoginMsg());
            loginLogDTO.setEquip(loginLogs.getEquip());
            loginLogDTO.setAccountType(loginLogs.getAccountType());
            loginLogDTOList.add(loginLogDTO);
        }
        return loginLogDTOList;
    }

    @Override
    public void AddLoginLogManage(String regionId, String projectId, String userId, String ip, String message, String equipment) {
        LoginLogEntity LoginLogEntity = new LoginLogEntity();
        LoginLogEntity.setRegionId(regionId);
        if(userId!=null&&!"".equals(userId)) {
            UserInfoEntity userInfoEntity = userInfoRepository.get(userId);
            LoginLogEntity.setName(userInfoEntity.getRealName());
            LoginLogEntity.setUserName(userInfoEntity.getUserName());
            switch (userInfoEntity.getUserType()) {
                case UserInfoEntity.USER_TYPE_VISITOR:
                    LoginLogEntity.setAccountType("游客");
                    break;
                case UserInfoEntity.USER_TYPE_NORMAL:
                    LoginLogEntity.setAccountType("普通");
                    break;
                case UserInfoEntity.USER_TYPE_OWNER:
                    LoginLogEntity.setAccountType("业主");
                    break;
                case UserInfoEntity.USER_TYPE_FAMILY:
                    LoginLogEntity.setAccountType("家属");
                    break;
                case UserInfoEntity.USER_TYPE_TENANT:
                    LoginLogEntity.setAccountType("租户");
                    break;
            }
        }
        if(projectId!=null&&!"".equals(projectId)){
            List<HouseProjectEntity>  projectList=  propertyCompanyRepository.queryHouseProjectEntity(projectId);
            for(HouseProjectEntity project: projectList){
                LoginLogEntity.setProject(project.getName());
            }
        }
        LoginLogEntity.setProjectId(projectId);
        LoginLogEntity.setUserIp(ip);
        LoginLogEntity.setLoginMsg(message);
        LoginLogEntity.setEquip(equipment);
        LoginLogEntity.setId(IdGen.uuid());
        LoginLogEntity.setLogintime(new Date());
        loginLogRepository.addLoginLogManage(LoginLogEntity);
    }
}
