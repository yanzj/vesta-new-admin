package com.maxrocky.vesta.application.clientAuthority.impl;

import com.maxrocky.vesta.application.clientAuthority.inf.ClientUserAuthorityService;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.clientAuthority.repository.ClientUserAuthorityRepository;
import com.maxrocky.vesta.domain.model.AuthOuterAgencyEntity;
import com.maxrocky.vesta.domain.model.UserAgencyEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserMapEntity;
import com.maxrocky.vesta.domain.projectAuthority.repository.ProjectUserAuthorityRepository;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.domain.repository.UserAgencyRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by yuanyn on 2018/5/7.
 */
@Service
public class ClientUserAuthorityServiceImpl implements ClientUserAuthorityService {

    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private ClientUserAuthorityRepository clientUserAuthorityRepository;
    @Autowired
    private UserAgencyRepository userAgencyRepository;

    @Override
    public List<UserStaffDTO> getStaffListByAgencyId(String agencyId) {

        List<UserStaffDTO> userStaffDTOS = new ArrayList<UserStaffDTO>();
        List<String> idList = new ArrayList<String>();
        //获取所有机构
        List<AuthOuterAgencyEntity> authOuterAgencyEntityList = agencyRepository.getOAAgencyList();
        for(AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntityList){
            String agencyPath = authOuterAgencyEntity.getAgencyPath().replace("/", ",").substring(1);
            String str[] = agencyPath.split(",");
            List<String> list = Arrays.asList(str);
            if(list.contains(agencyId)){
                idList.add(authOuterAgencyEntity.getAgencyId());
            }
        }
        if(null != agencyId) {
            List<UserAgencyEntity> userAgencyEntities = userAgencyRepository.getStaffIdByAgencyId(idList);
            List<UserInformationEntity> userInformationEntities = userAgencyRepository.getStaffByAgencyId(idList);
            List<UserMapEntity> userMapEntities = clientUserAuthorityRepository.getUserMap();
            for (UserInformationEntity user : userInformationEntities) {
                UserStaffDTO userStaffDTO = new UserStaffDTO();
                userStaffDTO.setStaffIdB(user.getStaffId());
                userStaffDTO.setStaffNameB(user.getStaffName());
                userStaffDTO.setSysNameB(user.getSysName());
                userStaffDTO.setUserNameB(user.getUserName());
                userStaffDTO.setEmailB(user.getEmail());
                userStaffDTO.setMobileB(user.getMobile());
                if(agencyId.equals("401dpx")){
                    userStaffDTO.setMobileB("***********");
                }
                userStaffDTO.setModifyOnB(DateUtils.format((Date) user.getModifyOn()));
                userStaffDTO.setIsEnabledB("0");
                for(UserMapEntity userMapEntity : userMapEntities){
                    if(userMapEntity.getStaffId().equals(user.getStaffId())){
                        userStaffDTO.setIsEnabledB("1");
                    }
                }
                for (UserAgencyEntity userAgencyEntity : userAgencyEntities) {
                    if (user.getStaffId().equals(userAgencyEntity.getStaffId())) {
                        for (AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntityList) {
                            if (authOuterAgencyEntity.getAgencyId().equals(userAgencyEntity.getAgencyId())) {
                                userStaffDTO.setAgencyNameB(authOuterAgencyEntity.getAgencyName());
                            }
                        }
                    }
                }
                userStaffDTOS.add(userStaffDTO);
            }
        }
        return userStaffDTOS;
    }

    @Override
    public List<UserStaffDTO> conditionQueryClientUser(UserStaffDTO userStaffDTO, WebPage webPage) {
        Map map = new HashMap();
        List<UserStaffDTO> userStaffDTOS = new ArrayList<>();
        List<UserStaffDTO> flagDTO = new ArrayList<>();
        List<String> idList = new ArrayList<String>();
        Boolean flag = true;
        map.put("agencyId","");
        map.put("agencyName","");
        map.put("staffName","");
        map.put("userName","");
        map.put("isEnabled","");
        map.put("mobile","");
        map.put("email","");
        if(!StringUtil.isEmpty(userStaffDTO.getAgencyIdB())){
            //获取所有机构
            List<AuthOuterAgencyEntity> authOuterAgencyEntityList = agencyRepository.getOAAgencyList();
            for(AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntityList){
                String agencyPath = authOuterAgencyEntity.getAgencyPath().replace("/", ",").substring(1);
                String str[] = agencyPath.split(",");
                List<String> list = Arrays.asList(str);
                if(list.contains(userStaffDTO.getAgencyIdB())){
                    idList.add(authOuterAgencyEntity.getAgencyId());
                }
            }
            flag = false;
        }
        if(!StringUtil.isEmpty(userStaffDTO.getAgencyNameB())){
            map.put("agencyName","%"+userStaffDTO.getAgencyNameB()+"%");
            flag = false;
        }
        if(!StringUtil.isEmpty(userStaffDTO.getStaffNameB())){
            map.put("staffName","%"+userStaffDTO.getStaffNameB()+"%");
            flag = false;
        }
        if(!StringUtil.isEmpty(userStaffDTO.getUserNameB())){
            map.put("userName","%"+userStaffDTO.getUserNameB()+"%");
            flag = false;
        }
        if(!StringUtil.isEmpty(userStaffDTO.getIsEnabledB())){
            map.put("isEnabled",userStaffDTO.getIsEnabledB());
            flag = false;
        }
        if(!StringUtil.isEmpty(userStaffDTO.getMobileB())){
            map.put("mobile","%"+userStaffDTO.getMobileB()+"%");
            flag = false;
        }
        if(!StringUtil.isEmpty(userStaffDTO.getEmailB())){
            map.put("email","%"+userStaffDTO.getEmailB()+"%");
            flag = false;
        }
        List<Object[]> list = clientUserAuthorityRepository.getOAUserManageListByCondition(map,webPage,idList);
        for(Object[] obj : list){
            UserStaffDTO userStaffDTO1 =  new UserStaffDTO();
            userStaffDTO1.setStaffIdB(obj[0] == null ? "" : obj[0].toString());
            userStaffDTO1.setUserNameB(obj[1] == null ? "" : obj[1].toString());
            userStaffDTO1.setSysNameB(obj[2] == null ? "" : obj[2].toString());
            userStaffDTO1.setMobileB(obj[3] == null ? "" : obj[3].toString());
            userStaffDTO1.setEmailB(obj[4] == null ? "" : obj[4].toString());
            userStaffDTO1.setAgencyNameB(obj[5] == null ? "" : obj[5].toString());
            userStaffDTO1.setModifyOnB(obj[6] == null ? "" : DateUtils.format((Date) obj[6]));
            userStaffDTO1.setStaffNameB(obj[7] == null ? "" : obj[7].toString());
            userStaffDTO1.setIsEnabledB(obj[8] == null ? "" : obj[8].toString());
            if("401dpx".equals(obj[9] == null ? "" : obj[9].toString())){
                userStaffDTO1.setMobileB("***********");
            }
            userStaffDTOS.add(userStaffDTO1);
        }
        if(flag){
            return flagDTO;
        }else {
            return userStaffDTOS;
        }
    }

    @Override
    public void startInsideClientStaff(StaffBatchDTO staffBatchDTO) {
        if(!StringUtil.isEmpty(staffBatchDTO.getStaffIds())){
            String str[] = staffBatchDTO.getStaffIds().split(",");
            List<String> list = Arrays.asList(str);
            for(String staffId : list){
                UserMapEntity userMapEntity = new UserMapEntity();
                userMapEntity.setModifyOn(new Date());
                userMapEntity.setProjectModule("qc");
                userMapEntity.setStaffId(staffId);
                userMapEntity.setState(staffBatchDTO.getState());
                userMapEntity.setSourceFrom(staffBatchDTO.getSourceFrom());
                clientUserAuthorityRepository.saveOrUpdateClientStaff(userMapEntity);
            }
        }
    }

    @Override
    public List<AgencyTreeDTO> getClientOuterAgencyList() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = clientUserAuthorityRepository.getClientOuterAgencyList();
        if(null != authOuterAgencyEntities && authOuterAgencyEntities.size()>0){
            AgencyTreeDTO agencyTreeDTO;
            for(AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntities){
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                agencyTreeDTO.setId(authOuterAgencyEntity.getAgencyId());
                agencyTreeDTO.setpId(authOuterAgencyEntity.getParentId());
                agencyTreeDTO.setName(authOuterAgencyEntity.getAgencyName());
                agencyTreeDTO.setOpen("false");
                if(authOuterAgencyEntity.getLevel().equals(2)){
                    agencyTreeDTO.setOpen("true");
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getClientOuterAgencyListById(String staffIdW) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = clientUserAuthorityRepository.getClientOuterAgencyList();
        List<String> agencyIds = clientUserAuthorityRepository.getClientAgencyByStaffId(staffIdW);
        if(null != authOuterAgencyEntities && authOuterAgencyEntities.size()>0){
            AgencyTreeDTO agencyTreeDTO;
            for(AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntities){
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                agencyTreeDTO.setId(authOuterAgencyEntity.getAgencyId());
                agencyTreeDTO.setpId(authOuterAgencyEntity.getParentId());
                agencyTreeDTO.setName(authOuterAgencyEntity.getAgencyName());
                agencyTreeDTO.setOpen("false");
                if(authOuterAgencyEntity.getLevel().equals(2) || authOuterAgencyEntity.getLevel().equals(3)){
                    agencyTreeDTO.setOpen("true");
                }
                if(null != agencyIds && agencyIds.size()>0){
                    for(String agencyId : agencyIds){
                        if(agencyId.equals(authOuterAgencyEntity.getAgencyId())){
                            agencyTreeDTO.setChecked(true);
                        }
                    }
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<OuterUserDTO> getClientOuterUserList(OuterUserDTO outerUserDTO, WebPage webPage) {
        List<OuterUserDTO> outerUserDTOList = new ArrayList<OuterUserDTO>();
        List<OuterUserDTO> flagDTO = new ArrayList<OuterUserDTO>();
        Boolean flag = true;
        Map map = new HashMap();
        map.put("agencyName","");//所属机构
        map.put("staffName","");//人员
        map.put("sysName","");// 系统账号
        map.put("state",outerUserDTO.getStateO());//启用状态
        map.put("mobile","");//电话
        map.put("email","");//邮箱
        map.put("agencyId","");//机构Id
        if(!StringUtil.isEmpty(outerUserDTO.getAgencyNameO())){
            map.put("agencyName","%"+outerUserDTO.getAgencyNameO()+"%");//所属机构
            flag = false;
        }
        if(!StringUtil.isEmpty(outerUserDTO.getStaffNameO())){
            map.put("staffName","%"+outerUserDTO.getStaffNameO()+"%");//人员
            flag = false;
        }
        if(!StringUtil.isEmpty(outerUserDTO.getSysNameO())){
            map.put("sysName","%"+outerUserDTO.getSysNameO()+"%");// 系统账号
            flag = false;
        }
        if(!StringUtil.isEmpty(outerUserDTO.getMobileO())){
            map.put("mobile","%"+outerUserDTO.getMobileO()+"%");//电话
            flag = false;
        }
        if(!StringUtil.isEmpty(outerUserDTO.getEmailO())){
            map.put("email","%"+outerUserDTO.getEmailO()+"%");//邮箱
            flag = false;
        }
        if(!StringUtil.isEmpty(outerUserDTO.getAgencyIdO())){
            map.put("agencyId",outerUserDTO.getAgencyIdO());//机构Id
            flag = false;
        }
        if(!StringUtil.isEmpty(outerUserDTO.getStateO())){
            flag = false;
        }
        List<Object[]> list = clientUserAuthorityRepository.getClientOuterUserList(map,webPage);
        for(Object[] obj : list){
            OuterUserDTO outerUserDTO1 = new OuterUserDTO();
            outerUserDTO1.setStaffIdO(obj[0] == null ? "" : obj[0].toString());
            outerUserDTO1.setSysNameO(obj[1] == null ? "" : obj[1].toString());
            outerUserDTO1.setStaffNameO(obj[2] == null ? "" : obj[2].toString());
            outerUserDTO1.setMobileO(obj[3] == null ? "" : obj[3].toString());
            outerUserDTO1.setEmailO(obj[4] == null ? "" :obj[4].toString());
            outerUserDTO1.setModifyOnO(obj[5] == null ? "" : DateUtils.format((Date) obj[5]));
            outerUserDTO1.setStateO(obj[6] == null ? "" :obj[6].toString());
            List<String> agencyNameList = clientUserAuthorityRepository.getClientAgencyNameByStaffId(outerUserDTO1.getStaffIdO());
            String agencyName="";  //所属机构
            if(null != agencyNameList && agencyNameList.size()>0){
                for(int i = 0 ; i < agencyNameList.size(); i ++){
                    if( i==0 ){
                        agencyName = agencyNameList.get(i).toString();
                    }else {
                        agencyName = agencyName + " ," + agencyNameList.get(i).toString();
                    }
                }
            }
            outerUserDTO1.setAgencyNameO(agencyName);
            outerUserDTOList.add(outerUserDTO1);
        }
        if(flag){
            return flagDTO;
        }else{
            return outerUserDTOList;
        }
    }

    @Override
    public void toDeleteClientUser(String staffId) {
        List<UserAgencyEntity> userAgencyEntities = agencyRepository.getUserAgency(staffId);
        if(null != userAgencyEntities && userAgencyEntities.size()>0){
            for(UserAgencyEntity userAgencyEntity : userAgencyEntities){
                userAgencyEntity.setStatus("0");
                userAgencyEntity.setModifyTime(new Date());
                agencyRepository.saveOrupdate(userAgencyEntity);
            }
        }
        UserMapEntity userMapEntity = clientUserAuthorityRepository.getUserMapById(staffId);
        if(null != userMapEntity ){
            userMapEntity.setState("0");
            userMapEntity.setModifyOn(new Date());
            agencyRepository.saveOrupdate(userMapEntity);
        }
        List<UserInformationEntity> userInformationEntities = agencyRepository.getUserInformation(staffId);
        if(null != userInformationEntities && userInformationEntities.size()>0){
            for(UserInformationEntity userInformationEntity : userInformationEntities){
                userInformationEntity.setStaffState("0");
                userInformationEntity.setModifyOn(new Date());
                agencyRepository.saveOrupdate(userInformationEntity);
            }
        }
    }

    @Override
    public void batchDeleteClientUser(StaffBatchDTO staffBatchDTO) {
        if(!StringUtil.isEmpty(staffBatchDTO.getStaffIds())){
            String str[] = staffBatchDTO.getStaffIds().split(",");
            List<String> list = Arrays.asList(str);
            for(String staffId : list){
                toDeleteClientUser(staffId);
            }
        }
    }

    @Override
    public List<EnabledUserDTO> getClientEnabledUserList(EnabledUserDTO enabledUserDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("agencyName",""); //所属机构
        map.put("staffName","");//人员
        map.put("userName","");//OA账号
        map.put("sourceFrom",enabledUserDTO.getSourceFromE());
        map.put("mobile","");//电话
        map.put("email","");//邮箱
        if(!StringUtil.isEmpty(enabledUserDTO.getAgencyNameE())){
            map.put("agencyName","%"+enabledUserDTO.getAgencyNameE()+"%");//所属机构
        }
        if(!StringUtil.isEmpty(enabledUserDTO.getStaffNameE())){
            map.put("staffName","%"+enabledUserDTO.getStaffNameE()+"%");//人员
        }
        if(!StringUtil.isEmpty(enabledUserDTO.getUserNameE())){
            map.put("userName","%"+enabledUserDTO.getUserNameE()+"%");//OA账号
        }
        if(!StringUtil.isEmpty(enabledUserDTO.getMobileE())){
            map.put("mobile","%"+enabledUserDTO.getMobileE()+"%");//电话
        }
        if(!StringUtil.isEmpty(enabledUserDTO.getEmailE())){
            map.put("email","%"+enabledUserDTO.getEmailE()+"%");//邮箱
        }
        List<EnabledUserDTO> enabledUserDTOS = new ArrayList<EnabledUserDTO>();
        List<Object[]> list = clientUserAuthorityRepository.getClientEnabledUserList(map,webPage);
        for(Object[] obj : list){
            EnabledUserDTO enabledUserDTO1 = new EnabledUserDTO();
            enabledUserDTO1.setStaffIdE(obj[0] == null ? "" : obj[0].toString());
            enabledUserDTO1.setUserNameE(obj[1] == null ? "" : obj[1].toString());
            enabledUserDTO1.setSysNameE(obj[2] == null ? "" : obj[2].toString());
            enabledUserDTO1.setStaffNameE(obj[3] == null ? "" : obj[3].toString());
            enabledUserDTO1.setMobileE(obj[4] == null ? "" : obj[4].toString());
            enabledUserDTO1.setEmailE(obj[5] == null ? "" :obj[5].toString());
            enabledUserDTO1.setModifyOnE(obj[6] == null ? "" : DateUtils.format((Date) obj[6]));
            enabledUserDTO1.setSourceFromE(obj[7] == null ? "" : obj[7].toString());
            List<String> agencyNameList = agencyRepository.getAgencyAllNameByStaffId(enabledUserDTO1.getStaffIdE());
            String agencyName="";  //所属机构
            if(null != agencyNameList && agencyNameList.size()>0){
                for(int i = 0 ; i < agencyNameList.size(); i ++){
                    if( i==0 ){
                        agencyName = agencyNameList.get(i).toString();
                    }else {
                        agencyName = agencyName + " ," + agencyNameList.get(i).toString();
                    }
                }
            }
            enabledUserDTO1.setAgencyNameE(agencyName);
            enabledUserDTOS.add(enabledUserDTO1);
        }
        return enabledUserDTOS;
    }

    @Override
    public Map getClientAgencys() {
        List<AuthOuterAgencyEntity> list = clientUserAuthorityRepository.getClientOuterAgencyList();
        Map<String, String> agencys = new LinkedHashMap<>();
        if (list.size() > 0) {
            for (AuthOuterAgencyEntity authOuterAgencyEntity : list) {
                agencys.put(authOuterAgencyEntity.getAgencyId(), authOuterAgencyEntity.getAgencyName());
            }
        }
        return agencys;
    }

    @Override
    public UserManageDTO getClientUserManage(String staffId) {
        List<UserAgencyEntity> userAgencyEntities = agencyRepository.getUserAgency(staffId);
        UserMapEntity userMapEntity = clientUserAuthorityRepository.getUserMapById(staffId);
        List<UserInformationEntity> userInformationEntities = agencyRepository.getUserInformation(staffId);
        UserManageDTO userManageDTO = new UserManageDTO();
        if(null != userAgencyEntities && userAgencyEntities.size()>0){
            userManageDTO.setAgencyIdW(userAgencyEntities.get(0).getAgencyId());
            userManageDTO.setAgencyNameW(agencyRepository.getAgencyByAgencyId(userAgencyEntities.get(0).getAgencyId()).getAgencyName());
        }
        if(null != userInformationEntities && userInformationEntities.size()>0){
            userManageDTO.setEmailW(userInformationEntities.get(0).getEmail());
            userManageDTO.setMemoW(userInformationEntities.get(0).getMemo());
            userManageDTO.setMobileW(userInformationEntities.get(0).getMobile());
            userManageDTO.setStaffIdW(userInformationEntities.get(0).getStaffId());
            userManageDTO.setStaffNameW(userInformationEntities.get(0).getStaffName());
            userManageDTO.setSysNameW(userInformationEntities.get(0).getSysName());
            userManageDTO.setModifyOnW(DateUtils.format((Date) userInformationEntities.get(0).getModifyOn()));
            userManageDTO.setTemporaryW(userInformationEntities.get(0).getTemporary());
        }
        if(null != userMapEntity){
            userManageDTO.setStatusW(userMapEntity.getState());
        }else {
            userManageDTO.setStatusW("0");
        }
        return userManageDTO;
    }

    @Override
    public String updateClientStaff(UserManageDTO userManageDTO, UserInformationEntity userInformation) {
        UserInformationEntity userInformationEntity1 = agencyRepository.getUserByNameAndId(userManageDTO.getSysNameW(),userManageDTO.getStaffIdW());
        if(null != userInformationEntity1){
            return "该用户名已被注册！";
        }
        boolean flag = false;
        UserInformationEntity userInformationEntity = agencyRepository.getUserById(userManageDTO.getStaffIdW());
        if(!userInformationEntity.getStaffName().equals(userManageDTO.getStaffNameW())){
            flag = true;
        }
//        userInformationEntity.setSysName(userManageDTO.getMobileW());//手机号作为登录账号
        userInformationEntity.setStaffName(userManageDTO.getStaffNameW());//姓名
        userInformationEntity.setMobile(userManageDTO.getMobileW());//手机
        userInformationEntity.setModifyBy(userInformation.getStaffName());//修改人
        userInformationEntity.setModifyOn(new Date());//修改时间
        userInformationEntity.setEmail(userManageDTO.getEmailW());//邮箱
        userInformationEntity.setMemo(userManageDTO.getMemoW());//备注
        userInformationEntity.setTemporary("0");//非临时账号
        if(userManageDTO.getTemporaryW().equals("1")){
            userInformationEntity.setTemporary("1");//临时账号
        }
        agencyRepository.saveOrupdate(userInformationEntity);
        //修改启用状态
        if(null != userManageDTO.getStatusW()){
            UserMapEntity userMapEntity = clientUserAuthorityRepository.getUserMapById(userManageDTO.getStaffIdW());
            if("".equals(userManageDTO.getStatusW())){
                userMapEntity.setState("0");//启用状态
                userMapEntity.setModifyOn(new Date());//修改时间
            }else {
                userMapEntity.setState(userManageDTO.getStatusW());//启用状态
                userMapEntity.setModifyOn(new Date());//修改时间
            }
            agencyRepository.saveOrupdate(userMapEntity);
        }
        //关联组织机构
        if(null != userManageDTO.getAgencyIdW() && !StringUtil.isEmpty(userManageDTO.getAgencyIdW())){
            agencyRepository.delUserAgencyMap(userManageDTO.getStaffIdW(),new Date());
            String agencyId[] = userManageDTO.getAgencyIdW().split(",");
            for (int i = 0; i < agencyId.length; i++) {
                UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                userAgencyEntity.setMapId(IdGen.uuid());//主键id
                userAgencyEntity.setModifyTime(new Date());//修改 时间
                userAgencyEntity.setStatus("1");//状态
                userAgencyEntity.setAgencyId(agencyId[i].toString());//组织机构Id
                userAgencyEntity.setStaffId(userInformationEntity.getStaffId());//人员id
                userAgencyEntity.setSourceFrom("external");
                agencyRepository.saveOrUpdateUserAgencyMap(userAgencyEntity);  //保存组织机构关系
            }
        }
        if(flag){
            agencyRepository.updateBasePeople(userManageDTO.getStaffIdW(), userManageDTO.getStaffNameW(), new Date());
            agencyRepository.updateRoleStaff(userManageDTO.getStaffIdW(), new Date());
        }
        return "ok";
    }

    @Override
    public String saveClientStaff(UserManageDTO userManageDTO, UserInformationEntity userInformation) {
//
//            UserInformationEntity userInformationEntity1 = agencyRepository.getUserByName(userManageDTO.getMobileW());
//            if(null != userInformationEntity1){
//                return "该用户名已被注册！";
//            }
        if(null != userManageDTO.getSysNameW() && !StringUtil.isEmpty(userManageDTO.getSysNameW())){
            if(agencyRepository.checkUserByName(userManageDTO.getSysNameW())){
                UserInformationEntity userInformationEntity = new UserInformationEntity();
                String pwd = PasswordEncode.digestPassword("123456");//默认密码是123456
                userInformationEntity.setStaffId(IdGen.uuid());//id
                userInformationEntity.setSysName(userManageDTO.getSysNameW());//登录账号
//            userInformationEntity.setSysName(userManageDTO.getMobileW());//手机号作为
                userInformationEntity.setPassword(pwd);//密码
                userInformationEntity.setStaffName(userManageDTO.getStaffNameW());//姓名
                userInformationEntity.setStaffState("1");//是否启用
                userInformationEntity.setMobile(userManageDTO.getMobileW());//手机
                userInformationEntity.setCreateBy(userInformation.getStaffName());//创建人
                userInformationEntity.setCreateOn(new Date());//创建时间
                userInformationEntity.setModifyBy(userInformation.getStaffName());//修改人
                userInformationEntity.setModifyOn(new Date());//修改时间
                userInformationEntity.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
                userInformationEntity.setEmail(userManageDTO.getEmailW());//email
                userInformationEntity.setJinmaoIs("0");//是否是金茂内部人
                userInformationEntity.setMemo(userManageDTO.getMemoW());//备注
                if(userManageDTO.getTemporaryW().equals("1")){
                    userInformationEntity.setTemporary("1");//临时账号
                }
                agencyRepository.saveOrupdate(userInformationEntity);

                //启用此账号
                UserMapEntity userMapEntity = new UserMapEntity();
                userMapEntity.setStaffId(userInformationEntity.getStaffId());//人员Id
                userMapEntity.setModifyOn(new Date());//修改时间
                userMapEntity.setState("0");
                userMapEntity.setSourceFrom("external");//来源
                if(userManageDTO.getStatusW().equals("1")){
                    userMapEntity.setState("1");//启用状态
                }
                if(userManageDTO.getTemporaryW().equals("0")){
                    //非临时账号
                    userMapEntity.setProjectModule("es");
                    userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                    userMapEntity.setProjectModule("st");
                    userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                }
                userMapEntity.setProjectModule("qc");
                userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);   //保存用户启用关系表
                //关联组织机构
                if(null != userManageDTO.getAgencyIdW() && !StringUtil.isEmpty(userManageDTO.getAgencyIdW())){
                    String agencyId[] = userManageDTO.getAgencyIdW().split(",");
                    for (int i = 0; i < agencyId.length; i++) {
                        UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                        userAgencyEntity.setMapId(IdGen.uuid());//主键id
                        userAgencyEntity.setModifyTime(new Date());//修改 时间
                        userAgencyEntity.setStatus("1");//状态
                        userAgencyEntity.setAgencyId(agencyId[i].toString());//组织机构Id
                        userAgencyEntity.setStaffId(userInformationEntity.getStaffId());//人员id
                        userAgencyEntity.setSourceFrom("external");
                        agencyRepository.saveOrupdate(userAgencyEntity);  //保存组织机构关系
                    }
                }
                return "ok";
            }else{
                return "该用户名已被注册！";
            }
        }
        return "错误，用户名不能为空";
    }

    @Override
    public ApiResult saveClientOuterAgency(OuterAgencyDTO outerAgencyDTO, UserInformationEntity userPropertyStaffEntity) {
        if(null != outerAgencyDTO){
            AuthOuterAgencyEntity authOuterAgencyEntity = new AuthOuterAgencyEntity();
            authOuterAgencyEntity.setAgencyId(IdGen.uuid());//组织机构Id
            authOuterAgencyEntity.setAgencyName(outerAgencyDTO.getAgencyName());//组织机构名称
            authOuterAgencyEntity.setAgencyDesc(outerAgencyDTO.getMemo());
            authOuterAgencyEntity.setStatus("0");
            if(null != outerAgencyDTO.getStatus() && !StringUtil.isEmpty(outerAgencyDTO.getStatus())){
                authOuterAgencyEntity.setStatus(outerAgencyDTO.getStatus());
            }
            authOuterAgencyEntity.setAgencyType("3");
            authOuterAgencyEntity.setCreateTime(new Date());
            authOuterAgencyEntity.setCreateBy(userPropertyStaffEntity.getStaffId());
            authOuterAgencyEntity.setModifyTime(new Date());
            authOuterAgencyEntity.setModifyBy(userPropertyStaffEntity.getStaffId());
            authOuterAgencyEntity.setOutEmploy("1");
            authOuterAgencyEntity.setParentId("03d3df6a599747ef9bfa4332c0f919b6");//在外部合作单位下
            authOuterAgencyEntity.setAgencyPath("/1/03d3df6a599747ef9bfa4332c0f919b6/"+authOuterAgencyEntity.getAgencyId());
            authOuterAgencyEntity.setLevel(3);
            authOuterAgencyEntity.setIsTemporary("0");//是否为临时组
            if(null != outerAgencyDTO.getAgencyId()){
                AuthOuterAgencyEntity authOuterAgencyEntity1 = agencyRepository.getAgencyByAgencyId(outerAgencyDTO.getAgencyId());
                String agencyPath = authOuterAgencyEntity1.getAgencyPath().replace("/", ",").substring(1);
                String str[] = agencyPath.split(",");
                List<String> list = Arrays.asList(str);
                if(list.contains("89bb2c6cd573426697f11a3e872e9c2f")){//客关临时组id
                    authOuterAgencyEntity.setIsTemporary("1");//是临时组
                    authOuterAgencyEntity.setTemporaryScope("qc");//临时组生效范围
                }
                authOuterAgencyEntity.setParentId(outerAgencyDTO.getAgencyId());
                authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity1.getAgencyPath()+"/"+authOuterAgencyEntity.getAgencyId());
                authOuterAgencyEntity.setLevel(authOuterAgencyEntity1.getLevel()+1);
            }
            agencyRepository.saveOrupdate(authOuterAgencyEntity);
            return new SuccessApiResult("ok");
        }else {
            return new SuccessApiResult("没有可操作数据");
        }
    }

    @Override
    public ApiResult delClientOuterAgency(String agencyId, UserInformationEntity userPropertyStaffEntity) {
        try{
            if(null != agencyId){
                if("89bb2c6cd573426697f11a3e872e9c2f".equals(agencyId) || "03d3df6a599747ef9bfa4332c0f919b6".equals(agencyId)){
                    return new SuccessApiResult("错误，无法删除‘临时组’与‘外部合作单位’根目录！");
                }else {
                    AuthOuterAgencyEntity authOuterAgencyEntity = agencyRepository.getAgencyByAgencyId(agencyId);
                    authOuterAgencyEntity.setStatus("0");
                    authOuterAgencyEntity.setModifyBy(userPropertyStaffEntity.getStaffId());
                    authOuterAgencyEntity.setModifyTime(new Date());
                    agencyRepository.saveOrupdate(authOuterAgencyEntity);
                    return new SuccessApiResult("ok");
                }
            }else {
                return new SuccessApiResult("参数错误！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new SuccessApiResult("操作失败，请联系管理员！");
        }
    }

    @Override
    public void clientEnabledUserExcel(String title, String[] headers, ServletOutputStream out, EnabledUserDTO enabledUserDTO) {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        List<EnabledUserDTO> enabledUserDTOS = this.getClientEnabledUserList(enabledUserDTO,webPage);
        List<EnabledUserExcelDTO> enabledUserExcelDTOS = new ArrayList<EnabledUserExcelDTO>();
        ExportExcel<EnabledUserExcelDTO> ex = new ExportExcel();
        if(null != enabledUserDTOS && enabledUserDTOS.size()>0){
            for(EnabledUserDTO enabledUserDTO1 : enabledUserDTOS){
                EnabledUserExcelDTO enabledUserExcelDTO = new EnabledUserExcelDTO();
                enabledUserExcelDTO.setStaffName(enabledUserDTO1.getStaffNameE());
                enabledUserExcelDTO.setUserName(enabledUserDTO1.getUserNameE());
                enabledUserExcelDTO.setSysName(enabledUserDTO1.getSysNameE());
                enabledUserExcelDTO.setSourceFrom(enabledUserDTO1.getSourceFromE());
                if("external".equals(enabledUserDTO1.getSourceFromE())){
                    enabledUserExcelDTO.setSourceFrom("外部添加");
                }
                enabledUserExcelDTO.setMobile(enabledUserDTO1.getMobileE());
                enabledUserExcelDTO.setEmail(enabledUserDTO1.getEmailE());
                enabledUserExcelDTO.setAgencyName(enabledUserDTO1.getAgencyNameE());
                enabledUserExcelDTOS.add(enabledUserExcelDTO);
            }
        }
        ex.exportExcel(title, headers, enabledUserExcelDTOS, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    @Override
    public void clientOuterUserExcel(String title, String[] headers, ServletOutputStream out, OuterUserDTO outerUserDTO) {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        List<OuterUserDTO> outerUserDTOList = this.getClientOuterUserList(outerUserDTO,webPage);
        List<OuterUserExcelDTO> outerUserExcelDTOS = new ArrayList<OuterUserExcelDTO>();
        ExportExcel<OuterUserExcelDTO> ex = new ExportExcel();
        if(null != outerUserDTOList && outerUserDTOList.size()>0){
            for(OuterUserDTO outerUserDTO1 : outerUserDTOList){
                OuterUserExcelDTO outerUserExcelDTO = new OuterUserExcelDTO();
                outerUserExcelDTO.setStaffName(outerUserDTO1.getStaffNameO());
                outerUserExcelDTO.setSysName(outerUserDTO1.getSysNameO());
                outerUserExcelDTO.setMobile(outerUserDTO1.getMobileO());
                outerUserExcelDTO.setEmail(outerUserDTO1.getEmailO());
                outerUserExcelDTO.setAgencyName(outerUserDTO1.getAgencyNameO());
                outerUserExcelDTOS.add(outerUserExcelDTO);
            }
        }
        ex.exportExcel(title, headers, outerUserExcelDTOS, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    @Override
    public String importClientOuterPeopleExcel(InputStream fis, UserInformationEntity userPropertyStaffEntity) {
        try{
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                sheet = hwb.getSheetAt(i);
                //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                    if (row.getCell(0) != null) {//第一列数据 组织架构
                        String agencyName = row.getCell(0).getRichStringCellValue().getString().trim();
                        //查看组织架构名是否已存在
                        AuthOuterAgencyEntity authOuterAgencyEntity1 = clientUserAuthorityRepository.getOuterAgencyByName(agencyName);
                        if (authOuterAgencyEntity1 != null) {//如果有则使用已有ID
                            if (row.getCell(3) != null) {//第四列数据 联系方式 做为用户名
                                String userNameR = getCellValue(row.getCell(3)).trim();
                                UserInformationEntity userInformationEntity = clientUserAuthorityRepository.getStaffBySysName(userNameR);//根据用户名查询是否已被注册
                                if (userInformationEntity != null) {
                                    return "错误！第" + (j + 1) + "行第4列用户名已被注册！";
                                } else {
                                    UserInformationEntity userInformationEntity1= new UserInformationEntity();
                                    String pwd = PasswordEncode.digestPassword("123456");
                                    userInformationEntity1.setStaffId(IdGen.uuid());
                                    userInformationEntity1.setSysName(userNameR);//用户名
                                    userInformationEntity1.setPassword(pwd);//密码
                                    if (row.getCell(2) != null) {//姓名 第三列 姓名
                                        String staffNameR = row.getCell(2).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setStaffName(staffNameR);//姓名
                                    } else {
                                        return "错误！数据不规范！ 第" + (j + 1) + "行第3列姓名数据为空";
                                    }
                                    userInformationEntity1.setJinmaoIs("0");//编外，自建
                                    if (row.getCell(3) != null) {//手机
                                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                                        String userMobile = row.getCell(3).getStringCellValue();
                                        if (StringUtil.isMobile(userMobile)) {
                                            userInformationEntity1.setMobile(userMobile);//手机
                                        } else if (StringUtil.isFixedPhone(userMobile)) {
                                            userInformationEntity1.setMobile(userMobile);//区号+座机号码+分机号码
                                        }
                                    }
                                    if (row.getCell(4) != null) {//第5列数据 人员备注
                                        String memo = row.getCell(4).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setMemo(memo);
                                    }
                                    if(row.getCell(5) !=null){//第6列数据 排序
                                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                                        String orderNum = row.getCell(5).getStringCellValue();
                                        userInformationEntity1.setOrderNum(Integer.parseInt(orderNum));
                                    }
                                    if(row.getCell(6) !=null) {//第7列数据 状态
                                        String staffState = row.getCell(6).getRichStringCellValue().getString().trim();
                                        if("是".equals(staffState)){
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("1");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("qc");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);//保存用户启用关系表
                                            userMapEntity.setState("0");
                                            userMapEntity.setProjectModule("es");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        }else if("否".equals(staffState)){
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("0");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);  //保存用户启用关系表
                                            userMapEntity.setProjectModule("qc");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("es");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        }else {
                                            return "错误！ 第" + (j + 1) + "行第7列数据不规范！";
                                        }
                                    }else{
                                        return "错误！ 第" + (j + 1) + "行第7列数据为空！";
                                    }
                                    userInformationEntity1.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                    userInformationEntity1.setCreateOn(SqlDateUtils.getDate());//创建时间
                                    userInformationEntity1.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                                    userInformationEntity1.setModifyOn(SqlDateUtils.getDate());//修改时间
                                    userInformationEntity1.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
                                    userInformationEntity1.setStaffState("1");//账号有效
                                    boolean savestaff = clientUserAuthorityRepository.addClientOuterStaff(userInformationEntity1);//保存人员信息
                                    if (savestaff) {
                                        UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                                        userAgencyEntity.setMapId(IdGen.uuid());
                                        userAgencyEntity.setStaffId(userInformationEntity1.getStaffId());
                                        userAgencyEntity.setAgencyId(authOuterAgencyEntity1.getAgencyId());
                                        userAgencyEntity.setModifyTime(new Date());
                                        userAgencyEntity.setStatus("1");
                                        userAgencyEntity.setSourceFrom("external");
                                        agencyRepository.saveOrupdate(userAgencyEntity);   //保存用户与组织机构关系
                                    } else {
                                        return "添加人员信息出错";
                                    }
                                }
                            } else {
                                return "错误！数据不规范！第" + (j + 1) + "行第4列数据为空！";
                            }
                        }else{//如果没有这个组织机构  则新增组织机构
                            AuthOuterAgencyEntity outerAgencyEntity2 = new AuthOuterAgencyEntity();
                            outerAgencyEntity2.setAgencyId(IdGen.uuid());//组织机构Id
                            outerAgencyEntity2.setAgencyName(agencyName);//组织机构名称
                            outerAgencyEntity2.setStatus("1");
                            outerAgencyEntity2.setCreateTime(new Date());
                            outerAgencyEntity2.setCreateBy(userPropertyStaffEntity.getStaffId());
                            outerAgencyEntity2.setModifyTime(new Date());
                            outerAgencyEntity2.setModifyBy(userPropertyStaffEntity.getStaffId());
                            outerAgencyEntity2.setOutEmploy("1");
                            outerAgencyEntity2.setParentId("03d3df6a599747ef9bfa4332c0f919b6");//在外部合作单位下
                            outerAgencyEntity2.setAgencyPath("/1/03d3df6a599747ef9bfa4332c0f919b6/"+outerAgencyEntity2.getAgencyId());
                            outerAgencyEntity2.setLevel(3);
                            outerAgencyEntity2.setIsTemporary("0");//不是临时组
                            agencyRepository.saveOrupdate(outerAgencyEntity2);
                            if (row.getCell(3) != null) {//第4列数据 用户名
                                String userNameR = getCellValue(row.getCell(3)).trim();
                                UserInformationEntity userInformationEntity = clientUserAuthorityRepository.getStaffBySysName(userNameR);//根据用户名查询是否已被注册s
                                if (userInformationEntity != null) {
                                    return "错误！第" + (j + 1) + "行第4列用户名已被注册！";
                                }else {
                                    UserInformationEntity userInformationEntity1= new UserInformationEntity();
                                    String pwd = PasswordEncode.digestPassword("123456");
                                    userInformationEntity1.setStaffId(IdGen.uuid());
                                    userInformationEntity1.setSysName(userNameR);//用户名
                                    userInformationEntity1.setPassword(pwd);//密码
                                    if (row.getCell(2) != null) {//姓名 第三列 姓名
                                        String staffNameR = row.getCell(2).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setStaffName(staffNameR);//姓名
                                    } else {
                                        return "错误！数据不规范！ 第" + (j + 1) + "行第3列姓名数据为空";
                                    }
                                    userInformationEntity1.setJinmaoIs("0");//编外，自建
                                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                                    String mobile = row.getCell(3).getStringCellValue();
                                    if (StringUtil.isMobile(mobile)) {
                                        userInformationEntity1.setMobile(mobile);//手机
                                    } else if (StringUtil.isFixedPhone(mobile)) {
                                        userInformationEntity1.setMobile(mobile);//区号+座机号码+分机号码
                                    }
                                    if (row.getCell(4) != null) {//第5列数据 人员备注
                                        String memo = row.getCell(4).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setMemo(memo);
                                    }
                                    if(row.getCell(5) !=null){//第6列数据 排序
                                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                                        String orderNum = row.getCell(5).getStringCellValue();
                                        userInformationEntity1.setOrderNum(Integer.parseInt(orderNum));
                                    }
                                    if(row.getCell(6) !=null) {//第7列数据 状态
                                        String state = row.getCell(6).getRichStringCellValue().getString().trim();
                                        if("是".equals(state)){
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("1");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("qc");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);   //保存用户启用关系表
                                            userMapEntity.setProjectModule("es");
                                            userMapEntity.setState("0");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        }else if("否".equals(state)){
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("0");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("es");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);   //保存用户启用关系表
                                            userMapEntity.setProjectModule("qc");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        }else {
                                            return "错误！ 第" + (j + 1) + "行第7列数据不规范！";
                                        }
                                    }else{
                                        return "错误！ 第" + (j + 1) + "行第7列数据为空！";
                                    }
                                    userInformationEntity1.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                    userInformationEntity1.setCreateOn(SqlDateUtils.getDate());//创建时间
                                    userInformationEntity1.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                                    userInformationEntity1.setModifyOn(SqlDateUtils.getDate());//修改时间
                                    userInformationEntity1.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
                                    userInformationEntity1.setStaffState("1");//账号有效
                                    boolean savestaff = clientUserAuthorityRepository.addClientOuterStaff(userInformationEntity1);//保存人员信息
                                    if (savestaff) {
                                        UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                                        userAgencyEntity.setMapId(IdGen.uuid());
                                        userAgencyEntity.setStaffId(userInformationEntity1.getStaffId());
                                        userAgencyEntity.setAgencyId(outerAgencyEntity2.getAgencyId());
                                        userAgencyEntity.setModifyTime(new Date());
                                        userAgencyEntity.setStatus("1");
                                        userAgencyEntity.setSourceFrom("external");
                                        agencyRepository.saveOrupdate(userAgencyEntity);   //保存用户与组织机构关系
                                    } else {
                                        return "添加人员信息出错";
                                    }
                                }
                            }else{
                                return "错误！数据不规范！第" + (j + 1) + "行第4列数据为空！";
                            }
                        }
                    }else{
                        return "错误！第" + (j + 1) + "行第1列机构名称为空！";
                    }
                }
            }
            return "ok";
        }catch (IOException e) {
            e.printStackTrace();
            return "未知错误！";
        }
    }

    @Override
    public String QCimportClientOuterPeopleExcel(InputStream fis, UserInformationEntity userPropertyStaffEntity) {
        try{
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                sheet = hwb.getSheetAt(i);
                //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                    if (row.getCell(0) != null) {//第一列数据 组织架构
                        String agencyName = row.getCell(0).getRichStringCellValue().getString().trim();
                        //查看组织架构名是否已存在
                        AuthOuterAgencyEntity authOuterAgencyEntity1 = clientUserAuthorityRepository.getOuterAgencyByName(agencyName);
                        if (authOuterAgencyEntity1 != null) {//如果有则使用已有ID
                            if (row.getCell(3) != null) {//第四列数据  登录账号
                                String userNameR = getCellValue(row.getCell(3)).trim();
                                UserInformationEntity userInformationEntity = clientUserAuthorityRepository.getStaffBySysName(userNameR);//根据用户名查询是否已被注册
                                if (userInformationEntity != null) {
                                    return "错误！第" + (j + 1) + "行第4列用户名已被注册！";
                                } else {
                                    UserInformationEntity userInformationEntity1= new UserInformationEntity();
                                    String pwd = PasswordEncode.digestPassword("123456");
                                    userInformationEntity1.setStaffId(IdGen.uuid());
                                    userInformationEntity1.setSysName(userNameR);//用户名
                                    userInformationEntity1.setPassword(pwd);//密码
                                    if (row.getCell(2) != null) {//姓名 第三列 姓名
                                        String staffNameR = row.getCell(2).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setStaffName(staffNameR);//姓名
                                    } else {
                                        return "错误！数据不规范！ 第" + (j + 1) + "行第3列姓名数据为空";
                                    }
                                    userInformationEntity1.setJinmaoIs("0");//编外，自建
                                    if (row.getCell(4) != null) { // 第五列 手机
                                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                        String userMobile = row.getCell(4).getStringCellValue();
                                        if (StringUtil.isMobile(userMobile)) {
                                            userInformationEntity1.setMobile(userMobile);//手机
                                        } else if (StringUtil.isFixedPhone(userMobile)) {
                                            userInformationEntity1.setMobile(userMobile);//区号+座机号码+分机号码
                                        }
                                    }
                                    if (row.getCell(5) != null) {//第6列数据 人员备注
                                        String memo = row.getCell(5).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setMemo(memo);
                                    }
                                    if(row.getCell(6) !=null){//第7列数据 排序
                                        row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                                        String orderNum = row.getCell(6).getStringCellValue();
                                        userInformationEntity1.setOrderNum(Integer.parseInt(orderNum));
                                    }
                                    if(row.getCell(7) !=null) {//第8列数据 状态
                                        String staffState = row.getCell(7).getRichStringCellValue().getString().trim();
                                        if("是".equals(staffState)){
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("1");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("qc");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);//保存用户启用关系表
                                            userMapEntity.setState("0");
                                            userMapEntity.setProjectModule("es");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        }else if("否".equals(staffState)){
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("0");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);  //保存用户启用关系表
                                            userMapEntity.setProjectModule("qc");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("es");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        }else {
                                            return "错误！ 第" + (j + 1) + "行第8列数据不规范！";
                                        }
                                    }else{
                                        return "错误！ 第" + (j + 1) + "行第8列数据为空！";
                                    }
                                    userInformationEntity1.setTemporary("0");
                                    if(row.getCell(8) !=null) {//第9列数据 是否为临时
                                        String staffState = row.getCell(8).getRichStringCellValue().getString().trim();
                                        if("是".equals(staffState)){
                                            userInformationEntity1.setTemporary("1");
                                        }
                                    }
                                    userInformationEntity1.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                    userInformationEntity1.setCreateOn(SqlDateUtils.getDate());//创建时间
                                    userInformationEntity1.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                                    userInformationEntity1.setModifyOn(SqlDateUtils.getDate());//修改时间
                                    userInformationEntity1.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
                                    userInformationEntity1.setStaffState("1");//账号有效
                                    boolean savestaff = clientUserAuthorityRepository.addClientOuterStaff(userInformationEntity1);//保存人员信息
                                    if (savestaff) {
                                        UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                                        userAgencyEntity.setMapId(IdGen.uuid());
                                        userAgencyEntity.setStaffId(userInformationEntity1.getStaffId());
                                        userAgencyEntity.setAgencyId(authOuterAgencyEntity1.getAgencyId());
                                        userAgencyEntity.setModifyTime(new Date());
                                        userAgencyEntity.setStatus("1");
                                        userAgencyEntity.setSourceFrom("external");
                                        agencyRepository.saveOrupdate(userAgencyEntity);   //保存用户与组织机构关系
                                    } else {
                                        return "添加人员信息出错";
                                    }
                                }
                            } else {
                                return "错误！数据不规范！第" + (j + 1) + "行第4列数据为空！";
                            }
                        }else{//如果没有这个组织机构  则新增组织机构
                            AuthOuterAgencyEntity outerAgencyEntity2 = new AuthOuterAgencyEntity();
                            outerAgencyEntity2.setAgencyId(IdGen.uuid());//组织机构Id
                            outerAgencyEntity2.setAgencyName(agencyName);//组织机构名称
                            outerAgencyEntity2.setStatus("1");
                            outerAgencyEntity2.setCreateTime(new Date());
                            outerAgencyEntity2.setCreateBy(userPropertyStaffEntity.getStaffId());
                            outerAgencyEntity2.setModifyTime(new Date());
                            outerAgencyEntity2.setModifyBy(userPropertyStaffEntity.getStaffId());
                            outerAgencyEntity2.setOutEmploy("1");
                            outerAgencyEntity2.setParentId("03d3df6a599747ef9bfa4332c0f919b6");//在外部合作单位下
                            outerAgencyEntity2.setAgencyPath("/1/03d3df6a599747ef9bfa4332c0f919b6/"+outerAgencyEntity2.getAgencyId());
                            outerAgencyEntity2.setLevel(3);
                            outerAgencyEntity2.setIsTemporary("0");//不是临时组
                            agencyRepository.saveOrupdate(outerAgencyEntity2);
                            if (row.getCell(3) != null) {//第4列数据 用户名
                                String userNameR = getCellValue(row.getCell(3)).trim();
                                UserInformationEntity userInformationEntity = clientUserAuthorityRepository.getStaffBySysName(userNameR);//根据用户名查询是否已被注册s
                                if (userInformationEntity != null) {
                                    return "错误！第" + (j + 1) + "行第4列用户名已被注册！";
                                }else {
                                    UserInformationEntity userInformationEntity1= new UserInformationEntity();
                                    String pwd = PasswordEncode.digestPassword("123456");
                                    userInformationEntity1.setStaffId(IdGen.uuid());
                                    userInformationEntity1.setSysName(userNameR);//用户名
                                    userInformationEntity1.setPassword(pwd);//密码
                                    if (row.getCell(2) != null) {//姓名 第三列 姓名
                                        String staffNameR = row.getCell(2).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setStaffName(staffNameR);//姓名
                                    } else {
                                        return "错误！数据不规范！ 第" + (j + 1) + "行第3列姓名数据为空";
                                    }
                                    userInformationEntity1.setJinmaoIs("0");//编外，自建
                                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                    String mobile = row.getCell(4).getStringCellValue();
                                    if (StringUtil.isMobile(mobile)) {
                                        userInformationEntity1.setMobile(mobile);//手机
                                    } else if (StringUtil.isFixedPhone(mobile)) {
                                        userInformationEntity1.setMobile(mobile);//区号+座机号码+分机号码
                                    }
                                    if (row.getCell(5) != null) {//第6列数据 人员备注
                                        String memo = row.getCell(5).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setMemo(memo);
                                    }
                                    if(row.getCell(6) !=null){//第7列数据 排序
                                        row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                                        String orderNum = row.getCell(6).getStringCellValue();
                                        userInformationEntity1.setOrderNum(Integer.parseInt(orderNum));
                                    }
                                    if(row.getCell(7) !=null) {//第8列数据 状态
                                        String state = row.getCell(7).getRichStringCellValue().getString().trim();
                                        if("是".equals(state)){
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("1");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("qc");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);   //保存用户启用关系表
                                            userMapEntity.setProjectModule("es");
                                            userMapEntity.setState("0");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        }else if("否".equals(state)){
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("0");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("es");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);   //保存用户启用关系表
                                            userMapEntity.setProjectModule("qc");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        }else {
                                            return "错误！ 第" + (j + 1) + "行第8列数据不规范！";
                                        }
                                    }else{
                                        return "错误！ 第" + (j + 1) + "行第8列数据为空！";
                                    }
                                    userInformationEntity1.setTemporary("0");
                                    if(row.getCell(8) !=null) {//第9列数据 是否为临时
                                        String staffState = row.getCell(8).getRichStringCellValue().getString().trim();
                                        if("是".equals(staffState)){
                                            userInformationEntity1.setTemporary("1");
                                        }
                                    }
                                    userInformationEntity1.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                    userInformationEntity1.setCreateOn(SqlDateUtils.getDate());//创建时间
                                    userInformationEntity1.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                                    userInformationEntity1.setModifyOn(SqlDateUtils.getDate());//修改时间
                                    userInformationEntity1.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
                                    userInformationEntity1.setStaffState("1");//账号有效
                                    boolean savestaff = clientUserAuthorityRepository.addClientOuterStaff(userInformationEntity1);//保存人员信息
                                    if (savestaff) {
                                        UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                                        userAgencyEntity.setMapId(IdGen.uuid());
                                        userAgencyEntity.setStaffId(userInformationEntity1.getStaffId());
                                        userAgencyEntity.setAgencyId(outerAgencyEntity2.getAgencyId());
                                        userAgencyEntity.setModifyTime(new Date());
                                        userAgencyEntity.setStatus("1");
                                        userAgencyEntity.setSourceFrom("external");
                                        agencyRepository.saveOrupdate(userAgencyEntity);   //保存用户与组织机构关系
                                    } else {
                                        return "添加人员信息出错";
                                    }
                                }
                            }else{
                                return "错误！数据不规范！第" + (j + 1) + "行第4列数据为空！";
                            }
                        }
                    }else{
                        return "错误！第" + (j + 1) + "行第1列机构名称为空！";
                    }
                }
            }
            return "ok";
        }catch (IOException e) {
            e.printStackTrace();
            return "未知错误！";
        }
    }

    @Override
    public int checkClientStaff(String sysName) {
        if(agencyRepository.checkUserByName(sysName)){
            return 1;
        }else{
            return 0;
        }
    }

    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }

}
