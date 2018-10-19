package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.AuthAgencyDTO;
import com.maxrocky.vesta.application.DTO.AuthAgencyListDTO;
import com.maxrocky.vesta.application.inf.BasicAuthAgencyService;
import com.maxrocky.vesta.domain.model.AuthAgencyEntity;
import com.maxrocky.vesta.domain.model.AuthRoleProjectEntity;
import com.maxrocky.vesta.domain.model.InterfaceComPlainLogEntity;
import com.maxrocky.vesta.domain.repository.AuthAgencyRepository;
import com.maxrocky.vesta.domain.repository.InterfaceLogRepository;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2017/12/6.
 */
@Service("BasicAuthAgencyService")
public class BasicAuthAgencyServiceImpl implements BasicAuthAgencyService {

    @Autowired
    InterfaceLogRepository interfaceLogRepository;

    @Autowired
    AuthAgencyRepository authAgencyRepository;


    @Override
    public String setAuthAgency(AuthAgencyListDTO authAgencyListDTO) {
        try{
            if(authAgencyListDTO!=null){
                if(authAgencyListDTO.getAuthAgencyList()!=null && authAgencyListDTO.getAuthAgencyList().size()>0){
                    for(AuthAgencyDTO authAgencyDTO : authAgencyListDTO.getAuthAgencyList()){
                        if(!StringUtil.isEmpty(authAgencyDTO.getAgencyId())){
                            AuthAgencyEntity authAgencyEntity=authAgencyRepository.getAuthAgencyByID(authAgencyDTO.getAgencyId());
                            if(authAgencyEntity!=null){
                                //组织机构名
                                if(!StringUtil.isEmpty(authAgencyDTO.getAgencyName())){
                                    authAgencyEntity.setAgencyName(authAgencyDTO.getAgencyName());
                                }
                                //机构类型 100000000：总部 100000001：区域 100000003：城市 100000002：项目
                                if(!StringUtil.isEmpty(authAgencyDTO.getAgencyType())){
                                    authAgencyEntity.setAgencyType(authAgencyDTO.getAgencyType());
                                    //是否为根目录 0不是 1是
                                    if("100000000".equals(authAgencyDTO.getAgencyType())){
                                        authAgencyEntity.setIsRoot("1");
                                        authAgencyEntity.setLevel(1);//等级
                                    }else if("100000001".equals(authAgencyDTO.getAgencyType())){
                                        authAgencyEntity.setIsRoot("0");
                                        authAgencyEntity.setLevel(2);//等级
                                    }else if("100000003".equals(authAgencyDTO.getAgencyType())){
                                        authAgencyEntity.setIsRoot("0");
                                        authAgencyEntity.setLevel(3);//等级
                                    }else if("100000002".equals(authAgencyDTO.getAgencyType())){
                                        authAgencyEntity.setIsRoot("0");
                                        authAgencyEntity.setLevel(4);//等级
                                    }
                                }
                                //上级ID
                                if(!StringUtil.isEmpty(authAgencyDTO.getParentId())){
                                    authAgencyEntity.setParentId(authAgencyDTO.getParentId());
                                    //路径
                                    AuthAgencyEntity parentEntity=authAgencyRepository.getAuthAgencyByID(authAgencyDTO.getParentId());
                                    if(parentEntity!=null){
                                        authAgencyEntity.setAgencyPath(parentEntity.getAgencyPath()+"/"+authAgencyDTO.getAgencyId());
                                    }
                                }else{
                                    authAgencyEntity.setAgencyPath("/"+authAgencyDTO.getAgencyId());
                                }
                                authAgencyEntity.setModifyTime(new Date()); //修改时间

                                if(!StringUtil.isEmpty(authAgencyDTO.getStatus())){
                                    authAgencyEntity.setStatus(authAgencyDTO.getStatus());
                                }
                                //业态
                                if(!StringUtil.isEmpty(authAgencyDTO.getBusinesssource())){
                                    authAgencyEntity.setBusinesssource(authAgencyDTO.getBusinesssource());
                                }
                                authAgencyRepository.addAuthAgency(authAgencyEntity);
                            }else{
                                AuthAgencyEntity authAgency = new AuthAgencyEntity();
                                authAgency.setAgencyId(authAgencyDTO.getAgencyId());  //主键
                                if(!StringUtil.isEmpty(authAgencyDTO.getAgencyName())){//组织机构名
                                    authAgency.setAgencyName(authAgencyDTO.getAgencyName());
                                }
                                //机构类型 100000000：总部 100000001：区域 100000003：城市 100000002：项目
                                if(!StringUtil.isEmpty(authAgencyDTO.getAgencyType())){
                                    authAgency.setAgencyType(authAgencyDTO.getAgencyType());
                                    //是否为根目录 0不是 1是
                                    if("100000000".equals(authAgencyDTO.getAgencyType())){
                                        authAgency.setIsRoot("1");
                                        authAgency.setLevel(1);//等级
                                    }else if("100000001".equals(authAgencyDTO.getAgencyType())){
                                        authAgency.setIsRoot("0");
                                        authAgency.setLevel(2);//等级
                                    }else if("100000003".equals(authAgencyDTO.getAgencyType())){
                                        authAgency.setIsRoot("0");
                                        authAgency.setLevel(3);//等级
                                    }else if("100000002".equals(authAgencyDTO.getAgencyType())){
                                        authAgency.setIsRoot("0");
                                        authAgency.setLevel(4);//等级
                                    }
                                }


                                //上级ID
                                if(!StringUtil.isEmpty(authAgencyDTO.getParentId())){
                                    authAgency.setParentId(authAgencyDTO.getParentId());
                                    AuthAgencyEntity parentEntity=authAgencyRepository.getAuthAgencyByID(authAgencyDTO.getParentId());
                                    if(parentEntity!=null){
                                        authAgency.setAgencyPath(parentEntity.getAgencyPath()+"/"+authAgencyDTO.getAgencyId());
                                    }
                                }else{
                                    authAgency.setAgencyPath("/"+authAgencyDTO.getAgencyId());
                                }
                                //状态 0删除 1正常
                                if(!StringUtil.isEmpty(authAgencyDTO.getStatus())){
                                    authAgency.setStatus(authAgencyDTO.getStatus());
                                }
                                //业态
                                if(!StringUtil.isEmpty(authAgencyDTO.getBusinesssource())){
                                    authAgency.setBusinesssource(authAgencyDTO.getBusinesssource());
                                }
                                //以上信息均有crm同步
                                authAgency.setCreateTime(new Date());
                                authAgency.setModifyTime(new Date());
                                authAgency.setOutEmploy("0");
                                authAgencyRepository.addAuthAgency(authAgency);

                                //根据新增的
                                //区域 城市 两个级别 直接查询上一级id是否绑定角色
                                //城市
                                if("100000003".equals(authAgencyDTO.getAgencyType())){
                                    //查询其上一级别 区域是否有角色关联 并且角色级别为城市
                                    List<String> roleList=authAgencyRepository.getRoleIdlistByAgencyId(authAgencyDTO.getParentId(),"100000003");
                                    if(roleList!=null){
                                        for(int i=0 ; i<roleList.size();i++ ){
                                            AuthRoleProjectEntity authRoleProjectEntity = new AuthRoleProjectEntity();
                                            authRoleProjectEntity.setAuthId(IdGen.uuid());// 关系表id
                                            authRoleProjectEntity.setAuthRoleId(roleList.get(i).toString());//角色ID
                                            authRoleProjectEntity.setAuthAgencyId(authAgencyDTO.getAgencyId());//组织结构id
                                            authAgencyRepository.saveAuthRoleseProject(authRoleProjectEntity);
                                        }
                                    }
                                }else if("100000001".equals(authAgencyDTO.getAgencyType())){//区域
                                    //查询其上一级别 集团是否有角色关联 并且角色级别为区域
                                    List<String> roleList=authAgencyRepository.getRoleIdlistByAgencyId(authAgencyDTO.getParentId(),"100000001");
                                    if(roleList!=null){
                                        for(int i=0 ; i<roleList.size();i++ ){
                                            AuthRoleProjectEntity authRoleProjectEntity = new AuthRoleProjectEntity();
                                            authRoleProjectEntity.setAuthId(IdGen.uuid());// 关系表id
                                            authRoleProjectEntity.setAuthRoleId(roleList.get(i).toString());//角色ID
                                            authRoleProjectEntity.setAuthAgencyId(authAgencyDTO.getAgencyId());//组织结构id
                                            authAgencyRepository.saveAuthRoleseProject(authRoleProjectEntity);
                                        }
                                    }
                                }else if("100000002".equals(authAgencyDTO.getAgencyType())){
                                    //项目级别特殊处理
                                    AuthAgencyEntity agency = authAgencyRepository.getAgencyListById(authAgencyDTO.getParentId());
                                    if(agency!=null){
                                        //上一级别为区域
                                        if("100000001".equals(agency.getAgencyType())){
                                            List<String> roleList=authAgencyRepository.getRoleIdlistByAgencyId(agency.getAgencyId(),"100000002");
                                            if(roleList!=null){
                                                for(int i=0 ; i<roleList.size();i++ ){
                                                    AuthRoleProjectEntity authRoleProjectEntity = new AuthRoleProjectEntity();
                                                    authRoleProjectEntity.setAuthId(IdGen.uuid());// 关系表id
                                                    authRoleProjectEntity.setAuthRoleId(roleList.get(i).toString());//角色ID
                                                    authRoleProjectEntity.setAuthAgencyId(authAgencyDTO.getAgencyId());//组织结构id
                                                    authAgencyRepository.saveAuthRoleseProject(authRoleProjectEntity);
                                                }
                                            }
                                        }else if("100000003".equals(agency.getAgencyType())){//上一级别为城市
                                            List<String> roleList=authAgencyRepository.getRoleIdlistByAgencyId(agency.getAgencyId(),"100000002");
                                            if(roleList!=null){
                                                for(int i=0 ; i<roleList.size();i++ ){
                                                    AuthRoleProjectEntity authRoleProjectEntity = new AuthRoleProjectEntity();
                                                    authRoleProjectEntity.setAuthId(IdGen.uuid());// 关系表id
                                                    authRoleProjectEntity.setAuthRoleId(roleList.get(i).toString());//角色ID
                                                    authRoleProjectEntity.setAuthAgencyId(authAgencyDTO.getAgencyId());//组织结构id
                                                    authAgencyRepository.saveAuthRoleseProject(authRoleProjectEntity);
                                                }
                                            }

                                            List<String> parRoleList=authAgencyRepository.getRoleIdlistByAgencyId(agency.getParentId(),"100000002");
                                            if(parRoleList!=null){
                                                for(int i=0 ; i<parRoleList.size();i++ ){
                                                    AuthRoleProjectEntity authRoleProjectEntity = new AuthRoleProjectEntity();
                                                    authRoleProjectEntity.setAuthId(IdGen.uuid());// 关系表id
                                                    authRoleProjectEntity.setAuthRoleId(parRoleList.get(i).toString());//角色ID
                                                    authRoleProjectEntity.setAuthAgencyId(authAgencyDTO.getAgencyId());//组织结构id
                                                    authAgencyRepository.saveAuthRoleseProject(authRoleProjectEntity);
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                    return "200";
                }else{
                    return "502";
                }
            }else{
                return "501";
            }

        }catch (Exception e){
            e.printStackTrace();
            //调用日志
            InterfaceComPlainLogEntity interfaceLog=new InterfaceComPlainLogEntity();
            interfaceLog.setId(IdGen.uuid());
            interfaceLog.setInterfaceName("接收组织机构信息接口:更新/创建数据失败！");
            interfaceLog.setCode("500");
            interfaceLog.setEntityName("auth_role_agency");
            interfaceLog.setErrorDate(new Date());
            interfaceLogRepository.createComplainLog(interfaceLog);
            return "500";
        }
    }
}
