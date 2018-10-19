package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ComplaintDTO;
import com.maxrocky.vesta.application.DTO.ComplaintListDTO;
import com.maxrocky.vesta.application.inf.BasicComplainService;
import com.maxrocky.vesta.application.inf.JGPushMessageService;
import com.maxrocky.vesta.domain.model.ComplainEntity;
import com.maxrocky.vesta.domain.model.InterfaceComPlainLogEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.repository.ComPlainRepository;
import com.maxrocky.vesta.domain.repository.InterfaceLogRepository;
import com.maxrocky.vesta.domain.repository.PropertyRepairCRMRepository;
import com.maxrocky.vesta.domain.repository.UserInfoRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2017/7/18.
 * Description:
 * webService:接收CRM传递的投诉单信息
 * ModifyBy:
 */

@Service("BasicComplainService")
public class BasicComplainServiceImpl implements BasicComplainService {
    @Autowired
    InterfaceLogRepository interfaceLogRepository;

    @Autowired
    ComPlainRepository comPlainRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    JGPushMessageService jgPushMessageService;

    @Autowired
    PropertyRepairCRMRepository propertyRepairCRMRepository;
    /**
     * CreateBy:Magic
     * Description:接收投诉单信息
     * ModifyBy:
     */
    @Override
    public String setComplaint(ComplaintListDTO complaintListDTO) {
        try{
            if(complaintListDTO!=null){
                //投诉单
                if(complaintListDTO.getComplaintList()!=null && complaintListDTO.getComplaintList().size()>0){
                    for(ComplaintDTO complaintDTO:complaintListDTO.getComplaintList()){
                        if(!StringUtil.isEmpty(complaintDTO.getComPlaintId())){
                            ComplainEntity complainEntity = comPlainRepository.getComplainEntity(complaintDTO.getComPlaintId());
                            if(complainEntity!=null){
                                List<String> userList=new ArrayList<>();
                                ///以下判断 为消息推送准备
                                if(!StringUtil.isEmpty(complaintDTO.getDocumentsState())){
                                    complainEntity.setDocumentsState(complaintDTO.getDocumentsState());//单据状态
                                    if("100000001".equals(complaintDTO.getDocumentsState())){
                                        //已创建变处理中 消息推送
                                        if(!StringUtil.isEmpty(complaintDTO.getDisposal())){
//                                            UserPropertyStaffEntity userEntity = userInfoRepository.getUserByUserName(complaintDTO.getDisposal());
                                            UserInformationEntity userEntity = propertyRepairCRMRepository.getUserByUserName(complaintDTO.getDisposal());
                                            if(userEntity!=null){
                                                complainEntity.setDisposal(userEntity.getStaffId());
                                                userList.add(userEntity.getStaffId());
                                            }
                                        }
                                    }
                                }
                                if(!StringUtil.isEmpty(complaintDTO.getDisposal())){
//                                    UserPropertyStaffEntity userEntity = userInfoRepository.getUserByUserName(complaintDTO.getDisposal());
                                    UserInformationEntity userEntity = propertyRepairCRMRepository.getUserByUserName(complaintDTO.getDisposal());
                                    if(userEntity!=null){
                                        complainEntity.setDisposal(userEntity.getStaffId());
                                    }
                                }
                                ///以上判断 为消息推送准备

                                //单据完成时间
                                if(!StringUtil.isEmpty(complaintDTO.getComPleteTime())){
                                    complainEntity.setCompleteTime(DateUtils.parse2(complaintDTO.getComPleteTime()));
                                }
                                //回访人姓名
                                if(!StringUtil.isEmpty(complaintDTO.getRevisit())){
                                    complainEntity.setRevisit(complaintDTO.getRevisit());
                                }
                                //回访意见
                                if(!StringUtil.isEmpty(complaintDTO.getVisitopinion())){
                                    complainEntity.setVisitOpinion(complaintDTO.getVisitopinion());
                                }
                                //回访时间
                                if(!StringUtil.isEmpty(complaintDTO.getVisitDate())){
                                    complainEntity.setVisitDate(DateUtils.parse2(complaintDTO.getVisitDate()));
                                }
                                //回访满意度 100000000：非常满意；100000001：满意；100000002：一般；100000003：不满意；100000004：非常不满意；100000005：无人接听；100000006：暂不回访；100000007：拒访；（地产只有：满意、不满意、暂不回访、拒访）
                                if(!StringUtil.isEmpty(complaintDTO.getVisitsatisfaction())){
                                    complainEntity.setVisitSatisfaction(complaintDTO.getVisitsatisfaction());
                                }
                                //城市编码
                                if(!StringUtil.isEmpty(complaintDTO.getCityNum())){
                                    complainEntity.setCity(complaintDTO.getCityNum());
                                }
                                //业主原话
                                if(!StringUtil.isEmpty(complaintDTO.getOwnerVersion())){
                                    complainEntity.setOwnerVersion(complaintDTO.getOwnerVersion());
                                }
                                //处理方案
                                if(!StringUtil.isEmpty(complaintDTO.getTreatmentPlan())){
                                    complainEntity.setTreatmentPlan(complaintDTO.getTreatmentPlan());
                                }
                                //处理结果
                                if(!StringUtil.isEmpty(complaintDTO.getProcessingResults())){
                                    complainEntity.setProcessingResults(complaintDTO.getProcessingResults());
                                }
                                //房间编码
                                if(!StringUtil.isEmpty(complaintDTO.getRoomNum())){
                                    complainEntity.setHouseCode(complaintDTO.getRoomNum());
                                }
                                //房间描述
                                if(!StringUtil.isEmpty(complaintDTO.getHousedes())){
                                    complainEntity.setHouseDes(complaintDTO.getHousedes());
                                }
                                //投诉人姓名
                                if(!StringUtil.isEmpty(complaintDTO.getPortalcomPlainperson())){
                                    complainEntity.setPortalComplaintPersonName(complaintDTO.getPortalcomPlainperson());
                                }
                                //投诉人情绪100000000：平和；100000001：激进；100000002：愤怒
                                if(!StringUtil.isEmpty(complaintDTO.getEmotion())){
                                    complainEntity.setEmotion(complaintDTO.getEmotion());
                                }
                                //投诉关联单号
                                if(!StringUtil.isEmpty(complaintDTO.getRelatedNumber())){
                                    complainEntity.setRelatedNumber(complaintDTO.getRelatedNumber());
                                }
                                //投诉分类100000005：客户服务类；100000003：物业服务类；100000000：销售服务类；100000001：工程质量类；100000002：规划设计类；100000004：其他类
                                if(!StringUtil.isEmpty(complaintDTO.getClassificationComplaints())){
                                    complainEntity.setClassificationComplaints(complaintDTO.getClassificationComplaints());
                                }
                                //投诉升级人名称
                                if(!StringUtil.isEmpty(complaintDTO.getUpGrade())){
                                    complainEntity.setUpgrade(complaintDTO.getUpGrade());
                                }
                                //投诉人电话
                                if(!StringUtil.isEmpty(complaintDTO.getComPlaintPhone())){
                                    complainEntity.setComplaintPhone(complaintDTO.getComPlaintPhone());
                                }
                                //投诉描述
                                if(!StringUtil.isEmpty(complaintDTO.getComPlainTsdescribes())){
                                    complainEntity.setComplaintsDescribes(complaintDTO.getComPlainTsdescribes());
                                }
                                //投诉时间
                                if(!StringUtil.isEmpty(complaintDTO.getSubmitTime())){
                                    complainEntity.setSubmitTime(DateUtils.parse2(complaintDTO.getSubmitTime()));
                                }
                                // 限时答复时间
                                if(!StringUtil.isEmpty(complaintDTO.getLimiteDreplyTime())){
                                    complainEntity.setLimitedReplyTime(DateUtils.parse2(complaintDTO.getLimiteDreplyTime()));
                                }
                                //是否超期答复0：否；1：是
                                if(!StringUtil.isEmpty(complaintDTO.getTimeOut())){
                                    complainEntity.setTimeOut(complaintDTO.getTimeOut());
                                }
                                //投诉来源 100000000：呼叫中心；100000001：项目前台；100000002：物业前台
                                if(!StringUtil.isEmpty(complaintDTO.getComPlaintSource())){
                                    complainEntity.setComplaintSource(complaintDTO.getComPlaintSource());
                                }

                                //投诉级别 100000000：一般投诉；100000001：热点投诉；100000002：重要投诉；100000003：重大投诉
                                if(!StringUtil.isEmpty(complaintDTO.getComPlaintLevel())){
                                    complainEntity.setComplaintLevel(complaintDTO.getComPlaintLevel());
                                }
                                //重大投诉原因
                                if(!StringUtil.isEmpty(complaintDTO.getMajorcomplaintreason())){
                                    complainEntity.setMajorComplaintReason(complaintDTO.getMajorcomplaintreason());
                                }
                                //重要投诉原因
                                if(!StringUtil.isEmpty(complaintDTO.getImportantcomplaintreason())){
                                    complainEntity.setImportantComplaintReason(complaintDTO.getImportantcomplaintreason());
                                }
                                //是否群诉 0：否；1：是
                                if(!StringUtil.isEmpty(complaintDTO.getWhetherswarmsues())){
                                    complainEntity.setWhetherSwarmsUes(complaintDTO.getWhetherswarmsues());
                                }
                                //派单时间
                                if(!StringUtil.isEmpty(complaintDTO.getDispatchTime())){
                                    complainEntity.setDispatchTime(DateUtils.parse2(complaintDTO.getDispatchTime()));
                                }
                                //物业投诉分类 100000000：房屋管理类；100000001：设备管理类；100000002：安全管理类；100000003：环境管理类；100000004：综合服务类100000005：业户纠纷类；100000006：地产相关类；100000007：市政相关类
                                if(!StringUtil.isEmpty(complaintDTO.getComPlaintType())){
                                    complainEntity.setComplaintType(complaintDTO.getComPlaintType());
                                }
                                //答复时间
                                if(!StringUtil.isEmpty(complaintDTO.getReplyTime())){
                                    complainEntity.setReplyTime(DateUtils.parse2(complaintDTO.getReplyTime()));
                                }
                                //项目编码
                                if(!StringUtil.isEmpty(complaintDTO.getProjectNum())){
                                    complainEntity.setProjectNum(complaintDTO.getProjectNum());
                                }
                                //驳回次数
                                if(!StringUtil.isEmpty(complaintDTO.getReject())){
                                    complainEntity.setReturnTime(complaintDTO.getReject());
                                }
                                //最后驳回时间
                                if(!StringUtil.isEmpty(complaintDTO.getRejecTime())){
                                    complainEntity.setLastReturnTime(DateUtils.parse2(complaintDTO.getRejecTime()));
                                }
                                comPlainRepository.updateComplain(complainEntity);

                                if(userList.size()>0){
                                    //处理人变动消息推送
                                    new Thread() {
                                        public void run() {
                                            jgPushMessageService.specifyPush("", userList, "1.3", complainEntity.getComplainId(), complainEntity.getDocumentsState(),"1.3");
                                        }
                                    }.start();
                                }
                                InterfaceComPlainLogEntity interfaceLog=new InterfaceComPlainLogEntity();
                                interfaceLog.setId(IdGen.uuid());
                                interfaceLog.setInterfaceName("接收投诉单信息接口:更新数据！");
                                interfaceLog.setCode("200");
                                interfaceLog.setEntityName("qc_complain");
                                interfaceLog.setEntityId(complainEntity.getComplainName());
                                interfaceLog.setErrorDate(new Date());
                                interfaceLogRepository.createComplainLog(interfaceLog);

                            }else{
                                List<String> userIdList=new ArrayList<>();
                                ComplainEntity complainy=new ComplainEntity();
                                complainy.setComplainId(IdGen.uuid());
                                if(!StringUtil.isEmpty(complaintDTO.getDisposal())){
//                                    UserPropertyStaffEntity userEntity = userInfoRepository.getUserByUserName(complaintDTO.getDisposal());
                                    UserInformationEntity userEntity = propertyRepairCRMRepository.getUserByUserName(complaintDTO.getDisposal());
                                    if(userEntity!=null){
                                        complainy.setDisposal(userEntity.getStaffId());
                                    }
                                }
                                //投诉单号
                                if(!StringUtil.isEmpty(complaintDTO.getComPlaintId())){
                                    complainy.setComplainName(complaintDTO.getComPlaintId());
                                }
                                //单据完成时间
                                if(!StringUtil.isEmpty(complaintDTO.getComPleteTime())){
                                    complainy.setCompleteTime(DateUtils.parse2(complaintDTO.getComPleteTime()));
                                }
                                //回访人姓名
                                if(!StringUtil.isEmpty(complaintDTO.getRevisit())){
                                    complainy.setRevisit(complaintDTO.getRevisit());
                                }
                                //回访意见
                                if(!StringUtil.isEmpty(complaintDTO.getVisitopinion())){
                                    complainy.setVisitOpinion(complaintDTO.getVisitopinion());
                                }
                                //回访时间
                                if(!StringUtil.isEmpty(complaintDTO.getVisitDate())){
                                    complainy.setVisitDate(DateUtils.parse2(complaintDTO.getVisitDate()));
                                }
                                //回访满意度 100000000：非常满意；100000001：满意；100000002：一般；100000003：不满意；100000004：非常不满意；100000005：无人接听；100000006：暂不回访；100000007：拒访；（地产只有：满意、不满意、暂不回访、拒访）
                                if(!StringUtil.isEmpty(complaintDTO.getVisitsatisfaction())){
                                    complainy.setVisitSatisfaction(complaintDTO.getVisitsatisfaction());
                                }
                                //城市编码
                                if(!StringUtil.isEmpty(complaintDTO.getCityNum())){
                                    complainy.setCity(complaintDTO.getCityNum());
                                }
                                //创建人
                                if(!StringUtil.isEmpty(complaintDTO.getCreateBy())){
                                    complainy.setCreateBy(complaintDTO.getCreateBy());
                                }
                                //创建人姓名
                                if(!StringUtil.isEmpty(complaintDTO.getCreateName())){
                                    complainy.setCreateByName(complaintDTO.getCreateName());
                                }
                                //创建时间
                                if(!StringUtil.isEmpty(complaintDTO.getCreateOn())){
                                    complainy.setCreateOn(DateUtils.parse2(complaintDTO.getCreateOn()));
                                }
                                //业主原话
                                if(!StringUtil.isEmpty(complaintDTO.getOwnerVersion())){
                                    complainy.setOwnerVersion(complaintDTO.getOwnerVersion());
                                }
                                //处理方案
                                if(!StringUtil.isEmpty(complaintDTO.getTreatmentPlan())){
                                    complainy.setTreatmentPlan(complaintDTO.getTreatmentPlan());
                                }
                                //处理结果
                                if(!StringUtil.isEmpty(complaintDTO.getProcessingResults())){
                                    complainy.setProcessingResults(complaintDTO.getProcessingResults());
                                }
                                //房间编码
                                if(!StringUtil.isEmpty(complaintDTO.getRoomNum())){
                                    complainy.setHouseCode(complaintDTO.getRoomNum());
                                }
                                //房间描述
                                if(!StringUtil.isEmpty(complaintDTO.getHousedes())){
                                    complainy.setHouseDes(complaintDTO.getHousedes());
                                }
                                //投诉人姓名
                                if(!StringUtil.isEmpty(complaintDTO.getPortalcomPlainperson())){
                                    complainy.setPortalComplaintPersonName(complaintDTO.getPortalcomPlainperson());
                                }
                                //投诉人情绪100000000：平和；100000001：激进；100000002：愤怒
                                if(!StringUtil.isEmpty(complaintDTO.getEmotion())){
                                    complainy.setEmotion(complaintDTO.getEmotion());
                                }
                                //投诉关联单号
                                if(!StringUtil.isEmpty(complaintDTO.getRelatedNumber())){
                                    complainy.setRelatedNumber(complaintDTO.getRelatedNumber());
                                }
                                //投诉分类100000005：客户服务类；100000003：物业服务类；100000000：销售服务类；100000001：工程质量类；100000002：规划设计类；100000004：其他类
                                if(!StringUtil.isEmpty(complaintDTO.getClassificationComplaints())){
                                    complainy.setClassificationComplaints(complaintDTO.getClassificationComplaints());
                                }
                                //投诉升级人名称
                                if(!StringUtil.isEmpty(complaintDTO.getUpGrade())){
                                    complainy.setUpgrade(complaintDTO.getUpGrade());
                                }
                                //投诉人电话
                                if(!StringUtil.isEmpty(complaintDTO.getComPlaintPhone())){
                                    complainy.setComplaintPhone(complaintDTO.getComPlaintPhone());
                                }
                                //投诉描述
                                if(!StringUtil.isEmpty(complaintDTO.getComPlainTsdescribes())){
                                    complainy.setComplaintsDescribes(complaintDTO.getComPlainTsdescribes());
                                }
                                //投诉时间
                                if(!StringUtil.isEmpty(complaintDTO.getSubmitTime())){
                                    complainy.setSubmitTime(DateUtils.parse2(complaintDTO.getSubmitTime()));
                                }
                                // 限时答复时间
                                if(!StringUtil.isEmpty(complaintDTO.getLimiteDreplyTime())){
                                    complainy.setLimitedReplyTime(DateUtils.parse2(complaintDTO.getLimiteDreplyTime()));
                                }
                                //是否超期答复0：否；1：是
                                if(!StringUtil.isEmpty(complaintDTO.getTimeOut())){
                                    complainy.setTimeOut(complaintDTO.getTimeOut());
                                }
                                //投诉来源 100000000：呼叫中心；100000001：项目前台；100000002：物业前台
                                if(!StringUtil.isEmpty(complaintDTO.getComPlaintSource())){
                                    complainy.setComplaintSource(complaintDTO.getComPlaintSource());
                                }
                                //投诉渠道 1:crm ;2:app
                                complainy.setComplainCanal("100000000");
                                //投诉级别 100000000：一般投诉；100000001：热点投诉；100000002：重要投诉；100000003：重大投诉
                                if(!StringUtil.isEmpty(complaintDTO.getComPlaintLevel())){
                                    complainy.setComplaintLevel(complaintDTO.getComPlaintLevel());
                                }
                                //重大投诉原因
                                if(!StringUtil.isEmpty(complaintDTO.getMajorcomplaintreason())){
                                    complainy.setMajorComplaintReason(complaintDTO.getMajorcomplaintreason());
                                }
                                //重要投诉原因
                                if(!StringUtil.isEmpty(complaintDTO.getImportantcomplaintreason())){
                                    complainy.setImportantComplaintReason(complaintDTO.getImportantcomplaintreason());
                                }
                                //是否群诉 0：否；1：是
                                if(!StringUtil.isEmpty(complaintDTO.getWhetherswarmsues())){
                                    complainy.setWhetherSwarmsUes(complaintDTO.getWhetherswarmsues());
                                }
                                //派单时间
                                if(!StringUtil.isEmpty(complaintDTO.getDispatchTime())){
                                    complainy.setDispatchTime(DateUtils.parse2(complaintDTO.getDispatchTime()));
                                }
                                //物业投诉分类 100000000：房屋管理类；100000001：设备管理类；100000002：安全管理类；100000003：环境管理类；100000004：综合服务类100000005：业户纠纷类；100000006：地产相关类；100000007：市政相关类
                                if(!StringUtil.isEmpty(complaintDTO.getComPlaintType())){
                                    complainy.setComplaintType(complaintDTO.getComPlaintType());
                                }
                                //答复时间
                                if(!StringUtil.isEmpty(complaintDTO.getReplyTime())){
                                    complainy.setReplyTime(DateUtils.parse2(complaintDTO.getReplyTime()));
                                }
                                //项目编码
                                if(!StringUtil.isEmpty(complaintDTO.getProjectNum())){
                                    complainy.setProjectNum(complaintDTO.getProjectNum());
                                }
                                //驳回次数
                                if(!StringUtil.isEmpty(complaintDTO.getReject())){
                                    complainy.setReturnTime(complaintDTO.getReject());
                                }
                                //最后驳回时间
                                if(!StringUtil.isEmpty(complaintDTO.getRejecTime())){
                                    complainy.setLastReturnTime(DateUtils.parse2(complaintDTO.getRejecTime()));
                                }

                                if(!StringUtil.isEmpty(complaintDTO.getDocumentsState())){
                                    complainy.setDocumentsState(complaintDTO.getDocumentsState());//单据状态
                                    if("100000001".equals(complaintDTO.getDocumentsState())){
                                        //已创建变处理中 消息推送
                                        if(!StringUtil.isEmpty(complaintDTO.getDisposal())){
//                                            UserPropertyStaffEntity userEntity = userInfoRepository.getUserByUserName(complaintDTO.getDisposal());
                                            UserInformationEntity userEntity = propertyRepairCRMRepository.getUserByUserName(complaintDTO.getDisposal());
                                            if(userEntity!=null){
                                                complainy.setDisposal(userEntity.getStaffId());
                                                //处理人变动消息推送
                                                userIdList.add(userEntity.getStaffId());
                                            }
                                        }
                                    }
                                }
                                comPlainRepository.saveComplain(complainy);
                                //处理人变动消息推送

                                if(userIdList.size()>0){
                                    new Thread() {
                                        public void run() {
                                            jgPushMessageService.specifyPush("", userIdList,  "1.3", complainy.getComplainId(), complainy.getDocumentsState(),"1.3");
                                        }
                                    }.start();
                                }
                                InterfaceComPlainLogEntity interfaceLog=new InterfaceComPlainLogEntity();
                                interfaceLog.setId(IdGen.uuid());
                                interfaceLog.setInterfaceName("接收投诉单信息接口:创建数据！");
                                interfaceLog.setCode("200");
                                interfaceLog.setEntityName("qc_complain");
                                interfaceLog.setEntityId(complainy.getComplainName());
                                interfaceLog.setErrorDate(new Date());
                                interfaceLogRepository.createComplainLog(interfaceLog);
                            }
                        }
                    }
                }
            }
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            //System.out.println("调用：交付计划接口失败！");
            //调用日志
            InterfaceComPlainLogEntity interfaceLog=new InterfaceComPlainLogEntity();
            interfaceLog.setId(IdGen.uuid());
            interfaceLog.setInterfaceName("接收项目分类人员信息接口:更新/创建数据失败！");
            interfaceLog.setCode("500");
            interfaceLog.setEntityName("qc_complain");
            interfaceLog.setErrorDate(new Date());
            interfaceLogRepository.createComplainLog(interfaceLog);
            return "500";
        }
    }
}
