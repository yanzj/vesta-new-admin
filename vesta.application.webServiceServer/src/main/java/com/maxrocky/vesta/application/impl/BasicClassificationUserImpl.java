package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ClassUserDTO;
import com.maxrocky.vesta.application.DTO.ClassificationDTO;
import com.maxrocky.vesta.application.DTO.ClassificationUserDTO;
import com.maxrocky.vesta.application.inf.BasicClassificationUserService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2017/7/17.
 * Description:
 * webService:接收CRM传递的项目分类人员信息
 * ModifyBy:
 */
@Service("basicClassificationUserService")
public class BasicClassificationUserImpl implements BasicClassificationUserService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;

    @Autowired
    ClassifyUserRepository classifyUserRepository;

    @Autowired
    private InterfaceLogRepository interfaceLogRepository;

    @Autowired
    private HouseProjectRepository houseProjectRepository;

    @Autowired
    UserAccreditRepository userAccreditRepository;
    /**
     * CreateBy:Magic
     * Description:接收分类人员信息
     * ModifyBy:
     */
    @Override
    public String setClassUser(ClassificationUserDTO classificationUserDTO) {
        try{
            if(classificationUserDTO!=null){
                //分类信息
                if(classificationUserDTO.getClassificationList()!=null && classificationUserDTO.getClassificationList().size()>0){
                    for(ClassificationDTO classification : classificationUserDTO.getClassificationList()){
                        if(!StringUtil.isEmpty(classification.getClassificationNum())){
                            ComplainClassifyEntity complainClassifyEntity=classifyUserRepository.getComplainClassifyEntity(classification.getClassificationNum());
                            if(complainClassifyEntity!=null){
                                if(!complainClassifyEntity.getClassifyName().equals(classification.getClassificationName())){
                                    complainClassifyEntity.setClassifyName(classification.getClassificationName());
                                    complainClassifyEntity.setModifyOn(new Date());
                                    complainClassifyEntity.setProjectNum("暂时未使用");
                                    complainClassifyEntity.setModifyBy("crm推送分类信息同步");
                                    classifyUserRepository.updateClass(complainClassifyEntity);
                                    //调用日志
                                    InterfaceClassUserLogEntity interfaceLog=new InterfaceClassUserLogEntity();
                                    interfaceLog.setId(IdGen.uuid());
                                    interfaceLog.setInterfaceName("接收项目分类人员信息接口:更新分类信息数据成功！");
                                    interfaceLog.setCode("200");
                                    interfaceLog.setEntityId(complainClassifyEntity.getClassifyId());
                                    interfaceLog.setEntityName("qc_complain_classify");
                                    interfaceLog.setErrorDate(new Date());
                                    interfaceLogRepository.createClassUserLog(interfaceLog);
                                }
                            }else{
                                ComplainClassifyEntity complainClassify=new ComplainClassifyEntity();
                                complainClassify.setClassifyId(classification.getClassificationNum());
                                complainClassify.setClassifyName(classification.getClassificationName());
                                complainClassify.setCreateBy("crm推送分类信息同步");
                                complainClassify.setCreateOn(new Date());
                                complainClassify.setProjectNum("暂时未使用");
                                complainClassify.setModifyBy("crm推送分类信息同步");
                                complainClassify.setModifyOn(new Date());
                                classifyUserRepository.saveClass(complainClassify);
                                //调用日志
                                InterfaceClassUserLogEntity interfaceLog=new InterfaceClassUserLogEntity();
                                interfaceLog.setId(IdGen.uuid());
                                interfaceLog.setInterfaceName("接收项目分类人员信息接口:创建分类信息数据成功！");
                                interfaceLog.setCode("200");
                                interfaceLog.setEntityId(complainClassify.getClassifyId());
                                interfaceLog.setEntityName("qc_complain_classify");
                                interfaceLog.setErrorDate(new Date());
                                interfaceLogRepository.createClassUserLog(interfaceLog);
                            }
                        }
                    }

                }
                //人员信息
                if(classificationUserDTO.getUserList()!=null && classificationUserDTO.getUserList().size()>0){
                    List<String> idList=new ArrayList<>();
                    //第一次遍历  组合删除分类人员关系
                    for(ClassUserDTO classUserDTO : classificationUserDTO.getUserList()){
                        String combination=classUserDTO.getProjectNum()+classUserDTO.getClassificationNum();
                        idList.add(combination);
                    }
                    if(idList.size()>0){
                        classifyUserRepository.deleteClassifyStaff(idList);
                    }

                    //第二次遍历  组合删除分类人员关系
                    for(ClassUserDTO classUserDTO : classificationUserDTO.getUserList()){
                        if(!StringUtil.isEmpty(classUserDTO.getUsername())){
                            //安心需求增加代码   需求提出人 黄大山
                            UserInformationEntity userInformation= userInfoRepository.getUserInformationEntity(classUserDTO.getUsername());
                            if(userInformation!=null){
                                //项目分类人员关系保存
                                ClassifyStaffRelationEntity classifyStaffRelationEntity=new ClassifyStaffRelationEntity();
                                classifyStaffRelationEntity.setRelationId(IdGen.uuid());//关系ID
                                classifyStaffRelationEntity.setStaffId(userInformation.getStaffId());//人员ID
                                classifyStaffRelationEntity.setClassifyId(classUserDTO.getClassificationNum());//分类ID
                                classifyStaffRelationEntity.setProjectNum(classUserDTO.getProjectNum()); //项目编码
                                classifyStaffRelationEntity.setCreateBy("crm推送分类人员信息同步");//创建人
                                classifyStaffRelationEntity.setCreateOn(new Date());//创建时间
                                classifyStaffRelationEntity.setModifyBy("crm推送分类人员信息同步");//修改人
                                classifyStaffRelationEntity.setModifyOn(new Date());//修改时间
                                classifyUserRepository.saveClassUser(classifyStaffRelationEntity);

                                //呵呵   根本没有明确需求   根据甲方需求  以下版本的需求不合理不需要作废   建议后续开发人员项目经理还是黄大山时候  一定和甲方确定好需求在开发  不是一次了
//
//                                //新需求  黄大山 提出   查询crm同步对接人信息功能点 QCH40010127  所有绑定该功能点的角色 均进行一次 角色 人员  项目绑定关系
//                                List<String> roleIdlIst=userInfoRepository.getRoleByFunction("QCH40010127");
//                                if(roleIdlIst!=null && roleIdlIst.size()>0){
////                                    HouseProjectEntity projectInfo = houseProjectRepository.getInfoByProjectNum(classUserDTO.getProjectNum());
////                                    if(projectInfo!=null){
//                                        //刪除 当前人和当前项目  角色list
//                                        userAccreditRepository.delQCOrUpdateRoleStaff(roleIdlIst,userInformation.getStaffId(),classUserDTO.getProjectId());
//                                        for(String roleId:roleIdlIst){
//                                            RoleStaffProjectMapQCEntity roleStaffProjectMapQCEntity=new RoleStaffProjectMapQCEntity();
//                                            roleStaffProjectMapQCEntity.setAgencyId(classUserDTO.getProjectId());
//                                            roleStaffProjectMapQCEntity.setRoleId(roleId);
//                                            roleStaffProjectMapQCEntity.setModifyOn(new Date());
//                                            roleStaffProjectMapQCEntity.setStaffId(userInformation.getStaffId());
//                                            roleStaffProjectMapQCEntity.setState("1");
//                                            userAccreditRepository.saveQCOrUpdateRoleStaff(roleStaffProjectMapQCEntity);
//                                        }
////                                    }
//                                }
//

                                //调用日志
                                InterfaceClassUserLogEntity interfaceLog=new InterfaceClassUserLogEntity();
                                interfaceLog.setId(IdGen.uuid());
                                interfaceLog.setInterfaceName("接收项目分类人员信息接口:创建分类人员信息数据成功 ！项目："+ classUserDTO.getProjectNum()+"!分类："+classUserDTO.getClassificationNum());
                                interfaceLog.setCode("200");
                                interfaceLog.setEntityId(classifyStaffRelationEntity.getStaffId()+" : "+userInformation.getStaffName());
                                interfaceLog.setEntityName("qc_classify_staff_relation");
                                interfaceLog.setErrorDate(new Date());
                                interfaceLogRepository.createClassUserLog(interfaceLog);
                                return "200";
                            }else {
                                //调用日志
                                InterfaceClassUserLogEntity interfaceLog=new InterfaceClassUserLogEntity();
                                interfaceLog.setId(IdGen.uuid());
                                interfaceLog.setInterfaceName("接收项目分类人员信息接口:创建分类人员信息数据成功 ！项目："+ classUserDTO.getProjectNum()+"!分类："+classUserDTO.getClassificationNum());
                                interfaceLog.setCode("500");
                                interfaceLog.setEntityId(classUserDTO.getUsername()+"账号信息不匹配");
                                interfaceLog.setEntityName("qc_classify_staff_relation");
                                interfaceLog.setErrorDate(new Date());
                                interfaceLogRepository.createClassUserLog(interfaceLog);
                                return "500";
                            }
                           /**  根据新权限需求  不使用该代码  需求提出人 黄大山
                             UserPropertyStaffEntity userEntity = userInfoRepository.getUserByUserName(classUserDTO.getUsername());
                            if(userEntity!=null){
                                String up="0";
                                if(!StringUtil.isEmpty(classUserDTO.getStaffName())){
                                    if(!StringUtil.isEmpty(userEntity.getStaffName())){
                                        if(!userEntity.getStaffName().equals(classUserDTO.getStaffName())){
                                            userEntity.setStaffName(classUserDTO.getStaffName());
                                            up="1";
                                        }
                                    }else {
                                        userEntity.setStaffName(classUserDTO.getStaffName());
                                        up="1";
                                    }

                                }
                                if(!StringUtil.isEmpty(classUserDTO.getPhone())){
                                    if(!StringUtil.isEmpty(userEntity.getMobile())){
                                        if( !classUserDTO.getPhone().equals(userEntity.getMobile())){
                                            userEntity.setMobile(classUserDTO.getPhone());
                                            up="1";
                                        }
                                    }else{
                                        userEntity.setMobile(classUserDTO.getPhone());
                                        up="1";
                                    }
                                }
                                if("1".equals(up)){
                                    //修改员工登录信息
                                    userPropertyStaffRepository.updateUserPropertyStaff(userEntity);
                                }
                                //项目分类人员关系保存
                                ClassifyStaffRelationEntity classifyStaffRelationEntity=new ClassifyStaffRelationEntity();
                                classifyStaffRelationEntity.setRelationId(IdGen.uuid());//关系ID
                                classifyStaffRelationEntity.setStaffId(userEntity.getStaffId());//人员ID
                                classifyStaffRelationEntity.setClassifyId(classUserDTO.getClassificationNum());//分类ID
                                classifyStaffRelationEntity.setProjectNum(classUserDTO.getProjectNum()); //项目编码
                                classifyStaffRelationEntity.setCreateBy("crm推送分类人员信息同步");//创建人
                                classifyStaffRelationEntity.setCreateOn(new Date());//创建时间
                                classifyStaffRelationEntity.setModifyBy("crm推送分类人员信息同步");//修改人
                                classifyStaffRelationEntity.setModifyOn(new Date());//修改时间
                                classifyUserRepository.saveClassUser(classifyStaffRelationEntity);

                                //调用日志
                                InterfaceClassUserLogEntity interfaceLog=new InterfaceClassUserLogEntity();
                                interfaceLog.setId(IdGen.uuid());
                                interfaceLog.setInterfaceName("接收项目分类人员信息接口:创建分类人员信息数据成功 ！项目："+ classUserDTO.getProjectNum()+"!分类："+classUserDTO.getClassificationNum());
                                interfaceLog.setCode("200");
                                interfaceLog.setEntityId(classifyStaffRelationEntity.getStaffId()+" : "+userEntity.getStaffName());
                                interfaceLog.setEntityName("qc_classify_staff_relation");
                                interfaceLog.setErrorDate(new Date());
                                interfaceLogRepository.createClassUserLog(interfaceLog);
                                //根据员工id查询项目分类人员信息
                            }else{
                                //创建用户
                                UserPropertyStaffEntity userPropertyStaffEntity = new UserPropertyStaffEntity();
                                userPropertyStaffEntity.setStaffId(IdGen.uuid());//员工ID
                                userPropertyStaffEntity.setUserName(classUserDTO.getUsername());//用户名
                                userPropertyStaffEntity.setStaffName(classUserDTO.getStaffName());//用户真实名称
                                userPropertyStaffEntity.setPassword("e10adc3949ba59abbe56e057f20f883e");//密码默认123456

                                userPropertyStaffEntity.setCreateBy("crm推送分类人员信息同步");//创建人
                                userPropertyStaffEntity.setCreateOn(new Date());//创建时间
                                userPropertyStaffEntity.setModifyBy("crm推送分类人员信息同步");//修改人
                                userPropertyStaffEntity.setModifyOn(new Date());//修改时间
                                if (!StringUtil.isEmpty(classUserDTO.getPhone())) {
                                    userPropertyStaffEntity.setMobile(classUserDTO.getPhone());//手机
                                }
                                userPropertyStaffEntity.setStaffState("1");//状态
                                userPropertyStaffRepository.addUserPropertyStaff(userPropertyStaffEntity);//新增员工信息数据
                                //项目分类人员关系保存
                                ClassifyStaffRelationEntity classifyStaffRelationEntity=new ClassifyStaffRelationEntity();
                                classifyStaffRelationEntity.setRelationId(IdGen.uuid());//关系ID
                                classifyStaffRelationEntity.setStaffId(userPropertyStaffEntity.getStaffId());//人员ID
                                classifyStaffRelationEntity.setClassifyId(classUserDTO.getClassificationNum());//分类ID
                                classifyStaffRelationEntity.setProjectNum(classUserDTO.getProjectNum()); //项目编码
                                classifyStaffRelationEntity.setCreateBy("crm推送分类人员信息同步");//创建人
                                classifyStaffRelationEntity.setCreateOn(new Date());//创建时间
                                classifyStaffRelationEntity.setModifyBy("crm推送分类人员信息同步");//修改人
                                classifyStaffRelationEntity.setModifyOn(new Date());//修改时间
                                classifyUserRepository.saveClassUser(classifyStaffRelationEntity);

                                //调用日志
                                InterfaceClassUserLogEntity interfaceLog=new InterfaceClassUserLogEntity();
                                interfaceLog.setId(IdGen.uuid());
                                interfaceLog.setInterfaceName("接收项目分类人员信息接口:创建分类人员信息数据成功！项目："+classUserDTO.getProjectNum()+"分类："+classUserDTO.getClassificationNum());
                                interfaceLog.setCode("200");
                                interfaceLog.setEntityId(classifyStaffRelationEntity.getStaffId()+" : "+userPropertyStaffEntity.getStaffName());
                                interfaceLog.setEntityName("qc_classify_staff_relation");
                                interfaceLog.setErrorDate(new Date());
                                interfaceLogRepository.createClassUserLog(interfaceLog);
                            }
                            */
                        }
                    }
                }
            }
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            //System.out.println("调用：交付计划接口失败！");
            //调用日志
            InterfaceClassUserLogEntity interfaceLog=new InterfaceClassUserLogEntity();
            interfaceLog.setId(IdGen.uuid());
            interfaceLog.setInterfaceName("接收项目分类人员信息接口:更新/创建数据失败！");
            interfaceLog.setCode("500");
            interfaceLog.setEntityName("qc_classify_staff_relation/qc_complain_classify");
            interfaceLog.setErrorDate(new Date());
            interfaceLogRepository.createClassUserLog(interfaceLog);
            return "500";
        }
    }
}
