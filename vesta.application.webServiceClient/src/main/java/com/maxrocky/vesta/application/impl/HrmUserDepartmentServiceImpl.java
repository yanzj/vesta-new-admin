package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.HrmServicePortType;
import com.maxrocky.vesta.application.inf.HrmUserDepartmentService;
import com.maxrocky.vesta.application.model.DepartmentBean;
import com.maxrocky.vesta.application.model.SubCompanyBean;
import com.maxrocky.vesta.application.model.UserBean;
import com.maxrocky.vesta.application.user.HrmServiceLocator;
import com.maxrocky.vesta.domain.model.DepartmentCRMEntity;
import com.maxrocky.vesta.domain.model.InterfaceLogEntity;
import com.maxrocky.vesta.domain.model.StaffCRMEntity;
import com.maxrocky.vesta.domain.model.SubcompanyCRMEntity;
import com.maxrocky.vesta.domain.repository.InterfaceLogRepository;
import com.maxrocky.vesta.domain.repository.UserMessageCRMRepository;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;

/**
 * Created by yuanyn on 2018/2/5.
 *  2018年6月 修改为 每次更新都是全量更新
 * 同步OA数据
 */
@Service
public class HrmUserDepartmentServiceImpl implements HrmUserDepartmentService {

    @Autowired
    private InterfaceLogRepository interfaceLogRepository;
    @Autowired
    private UserMessageCRMRepository userMessageCRMRepository;

    @Override
    public String userDepartment() {
        try {
//            String ip = "172.16.124.113";
            String ip = "172.16.105.88";
//            String url = "http://172.16.124.113/services/HrmService";
            String url = "http://172.16.105.88/services/HrmService";
            URL authUrl = new URL(url);
            HrmServiceLocator hrmServiceLocator = new HrmServiceLocator();
//            HrmServicePortType hrmServicePortType = hrmServiceLocator.getHrmServiceHttpPort(authUrl);
            HrmServicePortType hrmServicePortType = hrmServiceLocator.getHrmServiceHttpPort();
            UserBean[] userBeans = hrmServicePortType.getHrmUserInfo(ip,null,null,null,null,null);//人员
            SubCompanyBean[] subCompanyBeans = hrmServicePortType.getHrmSubcompanyInfo(ip);//分部
            DepartmentBean[] departmentBeans = hrmServicePortType.getHrmDepartmentInfo(ip,null);//部门
            if(null != userBeans && userBeans.length>0 ){
                userMessageCRMRepository.delete();
                for(UserBean user:userBeans){
//                    StaffCRMEntity userEntity = userMessageCRMRepository.get(user.getUserid().toString());
                    StaffCRMEntity userEntity = null;
                    //有则修改
                    if(null != userEntity) {
                        String up="0";
                        if(!StringUtil.isEmpty(user.getSubcompanyid1())){    //分部
                            if(!StringUtil.isEmpty(userEntity.getSubcompanyid1())){
                                if(!user.getSubcompanyid1().equals(userEntity.getSubcompanyid1())){
                                    userEntity.setSubcompanyid1(user.getSubcompanyid1());
                                    up="1";
                                }
                            }else{
                                userEntity.setSubcompanyid1(user.getSubcompanyid1());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getDepartmentid())){ //部门
                            if(!StringUtil.isEmpty(userEntity.getDepartmentid())){
                                if(!user.getDepartmentid().equals(userEntity.getDepartmentid())){
                                    userEntity.setDepartmentid(user.getDepartmentid());
                                    up="1";
                                }
                            }else{
                                userEntity.setDepartmentid(user.getDepartmentid());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getWorkcode())){//编号
                            if(!StringUtil.isEmpty(userEntity.getWorkcode())){
                                if(!user.getWorkcode().equals(userEntity.getWorkcode())){
                                    userEntity.setWorkcode(user.getWorkcode());
                                    up="1";
                                }
                            }else{
                                userEntity.setWorkcode(user.getWorkcode());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getLastname())){//姓名
                            if(!StringUtil.isEmpty(userEntity.getLastname())){
                                if(!user.getLastname().equals(userEntity.getLastname())){
                                    userEntity.setLastname(user.getLastname());
                                    up="1";
                                }
                            }else{
                                userEntity.setLastname(user.getLastname());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getLoginid())){  //系统账号
                            if(!StringUtil.isEmpty(userEntity.getLoginid())){
                                if(!user.getLoginid().equals(userEntity.getLoginid())){
                                    userEntity.setLoginid(user.getLoginid());
                                    up="1";
                                }
                            }else{
                                userEntity.setLoginid(user.getLoginid());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getPassword())){//密码
                            if(!StringUtil.isEmpty(userEntity.getPassword())){
                                if(!user.getPassword().equals(userEntity.getPassword())){
                                    userEntity.setPassword(user.getPassword());
                                    up="1";
                                }
                            }else{
                                userEntity.setPassword(user.getPassword());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getSeclevel())){//安全级别
                            if(!StringUtil.isEmpty(userEntity.getSeclevel())){
                                if(!user.getSeclevel().equals(userEntity.getSeclevel())){
                                    userEntity.setSeclevel(user.getSeclevel());
                                    up="1";
                                }
                            }else{
                                userEntity.setSeclevel(user.getSeclevel());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getSex())){//性别
                            if(!StringUtil.isEmpty(userEntity.getSex())){
                                if(!user.getSex().equals(userEntity.getSex())){
                                    userEntity.setSex(user.getSex());
                                    up="1";
                                }
                            }else{
                                userEntity.setSex(user.getSex());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getJobtitle())){//岗位
                            if(!StringUtil.isEmpty(userEntity.getJobtitle())){
                                if(!user.getJobtitle().equals(userEntity.getJobtitle())){
                                    userEntity.setJobtitle(user.getJobtitle());
                                    up="1";
                                }
                            }else{
                                userEntity.setJobtitle(user.getJobtitle());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getJobactivityid())){//职务
                            if(!StringUtil.isEmpty(userEntity.getJobactivityid())){
                                if(!user.getJobactivityid().equals(userEntity.getJobactivityid())){
                                    userEntity.setJobactivityid(user.getJobactivityid());
                                    up="1";
                                }
                            }else{
                                userEntity.setJobactivityid(user.getJobactivityid());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getJobgroupid())){//职务类型
                            if(!StringUtil.isEmpty(userEntity.getJobgroupid())){
                                if(!user.getJobgroupid().equals(userEntity.getJobgroupid())){
                                    userEntity.setJobgroupid(user.getJobgroupid());
                                    up="1";
                                }
                            }else{
                                userEntity.setJobgroupid(user.getJobgroupid());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getJobcall())){//职称
                            if(!StringUtil.isEmpty(userEntity.getJobcall())){
                                if(!user.getJobcall().equals(userEntity.getJobcall())){
                                    userEntity.setJobcall(user.getJobcall());
                                    up="1";
                                }
                            }else{
                                userEntity.setJobcall(user.getJobcall());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getJoblevel())){ //职级
                            if(!StringUtil.isEmpty(userEntity.getJoblevel())){
                                if(!user.getJoblevel().equals(userEntity.getJoblevel())){
                                    userEntity.setJoblevel(user.getJoblevel());
                                    up="1";
                                }
                            }else{
                                userEntity.setJoblevel(user.getJoblevel());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getJobactivitydesc())){//职责描述
                            if(!StringUtil.isEmpty(userEntity.getJobactivitydesc())){
                                if(!user.getJobactivitydesc().equals(userEntity.getJobactivitydesc())){
                                    userEntity.setJobactivitydesc(user.getJobactivitydesc());
                                    up="1";
                                }
                            }else{
                                userEntity.setJobactivitydesc(user.getJobactivitydesc());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getManagerid())){//直接上级
                            if(!StringUtil.isEmpty(userEntity.getManagerid())){
                                if(!user.getManagerid().equals(userEntity.getManagerid())){
                                    userEntity.setManagerid(user.getManagerid());
                                    up="1";
                                }
                            }else{
                                userEntity.setManagerid(user.getManagerid());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getAssistantid())){//助理
                            if(!StringUtil.isEmpty(userEntity.getAssistantid())){
                                if(!user.getAssistantid().equals(userEntity.getAssistantid())){
                                    userEntity.setAssistantid(user.getAssistantid());
                                    up="1";
                                }
                            }else{
                                userEntity.setAssistantid(user.getAssistantid());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getStatus())){ //状态  eg:正式、试用等
                            if(!StringUtil.isEmpty(userEntity.getStatus())){
                                if(!user.getStatus().equals(userEntity.getStatus())){
                                    userEntity.setStatus(user.getStatus());
                                    up="1";
                                }
                            }else{
                                userEntity.setStatus(user.getStatus());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getLocationid())){//办公地点
                            if(!StringUtil.isEmpty(userEntity.getLocationid())){
                                if(!user.getLocationid().equals(userEntity.getLocationid())){
                                    userEntity.setLocationid(user.getLocationid());
                                    up="1";
                                }
                            }else{
                                userEntity.setLocationid(user.getLocationid());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getWorkroom())){//办公室
                            if(!StringUtil.isEmpty(userEntity.getWorkroom())){
                                if(!user.getWorkroom().equals(userEntity.getWorkroom())){
                                    userEntity.setWorkroom(user.getWorkroom());
                                    up="1";
                                }
                            }else{
                                userEntity.setWorkroom(user.getWorkroom());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getTelephone())){//办公电话
                            if(!StringUtil.isEmpty(userEntity.getTelephone())){
                                if(!user.getTelephone().equals(userEntity.getTelephone())){
                                    userEntity.setTelephone(user.getTelephone());
                                    up="1";
                                }
                            }else{
                                userEntity.setTelephone(user.getTelephone());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getMobile())){//移动电话
                            if(!StringUtil.isEmpty(userEntity.getMobile())){
                                if(!user.getMobile().equals(userEntity.getMobile())){
                                    userEntity.setMobile(user.getMobile());
                                    up="1";
                                }
                            }else{
                                userEntity.setMobile(user.getMobile());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getMobilecall())){ //其他电话
                            if(!StringUtil.isEmpty(userEntity.getMobilecall())){
                                if(!user.getMobilecall().equals(userEntity.getMobilecall())){
                                    userEntity.setMobilecall(user.getMobilecall());
                                    up="1";
                                }
                            }else{
                                userEntity.setMobilecall(user.getMobilecall());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getFax())){//传真
                            if(!StringUtil.isEmpty(userEntity.getFax())){
                                if(!user.getFax().equals(userEntity.getFax())){
                                    userEntity.setFax(user.getFax());
                                    up="1";
                                }
                            }else{
                                userEntity.setFax(user.getFax());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getEmail())){//电子邮件
                            if(!StringUtil.isEmpty(userEntity.getEmail())){
                                if(!user.getEmail().equals(userEntity.getEmail())){
                                    userEntity.setEmail(user.getEmail());
                                    up="1";
                                }
                            }else{
                                userEntity.setEmail(user.getEmail());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getSystemlanguage())){//系统语言  默认7
                            if(!StringUtil.isEmpty(userEntity.getSystemlanguage())){
                                if(!user.getSystemlanguage().equals(userEntity.getSystemlanguage())){
                                    userEntity.setSystemlanguage(user.getSystemlanguage());
                                    up="1";
                                }
                            }else{
                                userEntity.setSystemlanguage(user.getSystemlanguage());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getBirthday())){ //生日
                            if(!StringUtil.isEmpty(userEntity.getBirthday())){
                                if(!user.getBirthday().equals(userEntity.getBirthday())){
                                    userEntity.setBirthday(user.getBirthday());
                                    up="1";
                                }
                            }else{
                                userEntity.setBirthday(user.getBirthday());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getFolk())){ //民族
                            if(!StringUtil.isEmpty(userEntity.getFolk())){
                                if(!user.getFolk().equals(userEntity.getFolk())){
                                    userEntity.setFolk(user.getFolk());
                                    up="1";
                                }
                            }else{
                                userEntity.setFolk(user.getFolk());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getNativeplace())){//籍贯
                            if(!StringUtil.isEmpty(userEntity.getNativeplace())){
                                if(!user.getNativeplace().equals(userEntity.getNativeplace())){
                                    userEntity.setNativeplace(user.getNativeplace());
                                    up="1";
                                }
                            }else{
                                userEntity.setNativeplace(user.getNativeplace());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getRegresidentplace())){//户口
                            if(!StringUtil.isEmpty(userEntity.getRegresidentplace())){
                                if(!user.getRegresidentplace().equals(userEntity.getRegresidentplace())){
                                    userEntity.setRegresidentplace(user.getRegresidentplace());
                                    up="1";
                                }
                            }else{
                                userEntity.setRegresidentplace(user.getRegresidentplace());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getCertificatenum())){//身份证号
                            if(!StringUtil.isEmpty(userEntity.getCertificatenum())){
                                if(!user.getCertificatenum().equals(userEntity.getCertificatenum())){
                                    userEntity.setCertificatenum(user.getCertificatenum());
                                    up="1";
                                }
                            }else{
                                userEntity.setCertificatenum(user.getCertificatenum());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getMaritalstatus())){//婚姻状况
                            if(!StringUtil.isEmpty(userEntity.getMaritalstatus())){
                                if(!user.getMaritalstatus().equals(userEntity.getMaritalstatus())){
                                    userEntity.setMaritalstatus(user.getMaritalstatus());
                                    up="1";
                                }
                            }else{
                                userEntity.setMaritalstatus(user.getMaritalstatus());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getPolicy())){//政治面貌
                            if(!StringUtil.isEmpty(userEntity.getPolicy())){
                                if(!user.getPolicy().equals(userEntity.getPolicy())){
                                    userEntity.setPolicy(user.getPolicy());
                                    up="1";
                                }
                            }else{
                                userEntity.setPolicy(user.getPolicy());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getBememberdate())){ //入团日期
                            if(!StringUtil.isEmpty(userEntity.getBememberdate())){
                                if(!user.getBememberdate().equals(userEntity.getBememberdate())){
                                    userEntity.setBememberdate(user.getBememberdate());
                                    up="1";
                                }
                            }else{
                                userEntity.setBememberdate(user.getBememberdate());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getBepartydate())){//入党日期
                            if(!StringUtil.isEmpty(userEntity.getBepartydate())){
                                if(!user.getBepartydate().equals(userEntity.getBepartydate())){
                                    userEntity.setBepartydate(user.getBepartydate());
                                    up="1";
                                }
                            }else{
                                userEntity.setBepartydate(user.getBepartydate());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getIslabouunion())){//是否是工会会员
                            if(!StringUtil.isEmpty(userEntity.getIslabouunion())){
                                if(!user.getIslabouunion().equals(userEntity.getIslabouunion())){
                                    userEntity.setIslabouunion(user.getIslabouunion());
                                    up="1";
                                }
                            }else{
                                userEntity.setIslabouunion(user.getIslabouunion());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getEducationlevel())){//学历
                            if(!StringUtil.isEmpty(userEntity.getEducationlevel())){
                                if(!user.getEducationlevel().equals(userEntity.getEducationlevel())){
                                    userEntity.setEducationlevel(user.getEducationlevel());
                                    up="1";
                                }
                            }else{
                                userEntity.setEducationlevel(user.getEducationlevel());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getDegree())){//学位
                            if(!StringUtil.isEmpty(userEntity.getDegree())){
                                if(!user.getDegree().equals(userEntity.getDegree())){
                                    userEntity.setDegree(user.getDegree());
                                    up="1";
                                }
                            }else{
                                userEntity.setDegree(user.getDegree());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getHealthinfo())){ //健康状况
                            if(!StringUtil.isEmpty(userEntity.getHealthinfo())){
                                if(!user.getHealthinfo().equals(userEntity.getHealthinfo())){
                                    userEntity.setHealthinfo(user.getHealthinfo());
                                    up="1";
                                }
                            }else{
                                userEntity.setHealthinfo(user.getHealthinfo());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getHeight())){//身高
                            if(!StringUtil.isEmpty(userEntity.getHeight())){
                                if(!user.getHeight().equals(userEntity.getHeight())){
                                    userEntity.setHeight(user.getHeight());
                                    up="1";
                                }
                            }else{
                                userEntity.setHeight(user.getHeight());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getWeight())){//体重
                            if(!StringUtil.isEmpty(userEntity.getWeight())){
                                if(!user.getWeight().equals(userEntity.getWeight())){
                                    userEntity.setWeight(user.getWeight());
                                    up="1";
                                }
                            }else{
                                userEntity.setWeight(user.getWeight());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getResidentplace())){ //居住地
                            if(!StringUtil.isEmpty(userEntity.getResidentplace())){
                                if(!user.getResidentplace().equals(userEntity.getResidentplace())){
                                    userEntity.setResidentplace(user.getResidentplace());
                                    up="1";
                                }
                            }else{
                                userEntity.setResidentplace(user.getResidentplace());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getHomeaddress())){//家庭住址
                            if(!StringUtil.isEmpty(userEntity.getHomeaddress())){
                                if(!user.getHomeaddress().equals(userEntity.getHomeaddress())){
                                    userEntity.setHomeaddress(user.getHomeaddress());
                                    up="1";
                                }
                            }else{
                                userEntity.setHomeaddress(user.getHomeaddress());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getTempresidentnumber())){//暂住证号码
                            if(!StringUtil.isEmpty(userEntity.getTempresidentnumber())){
                                if(!user.getTempresidentnumber().equals(userEntity.getTempresidentnumber())){
                                    userEntity.setTempresidentnumber(user.getTempresidentnumber());
                                    up="1";
                                }
                            }else{
                                userEntity.setTempresidentnumber(user.getTempresidentnumber());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getStartdate())){//合同开始日期
                            if(!StringUtil.isEmpty(userEntity.getStartdate())){
                                if(!user.getStartdate().equals(userEntity.getStartdate())){
                                    userEntity.setStartdate(user.getStartdate());
                                    up="1";
                                }
                            }else{
                                userEntity.setStartdate(user.getStartdate());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getEnddate())){ //合同结束日期
                            if(!StringUtil.isEmpty(userEntity.getEnddate())){
                                if(!user.getEnddate().equals(userEntity.getEnddate())){
                                    userEntity.setEnddate(user.getEnddate());
                                    up="1";
                                }
                            }else{
                                userEntity.setEnddate(user.getEnddate());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getCreatedate())){//创建日期
                            if(!StringUtil.isEmpty(userEntity.getCreatedate())){
                                if(!user.getCreatedate().equals(userEntity.getCreatedate())){
                                    userEntity.setCreatedate(user.getCreatedate());
                                    up="1";
                                }
                            }else{
                                userEntity.setCreatedate(user.getCreatedate());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getLastChangdate())){//最后修改日期
                            if(!StringUtil.isEmpty(userEntity.getLastChangdate())){
                                if(!user.getLastChangdate().equals(userEntity.getLastChangdate())){
                                    userEntity.setLastChangdate(user.getLastChangdate());
                                    up="1";
                                }
                            }else{
                                userEntity.setLastChangdate(user.getLastChangdate());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getAccounttype().toString())){//账号类型
                            if(!StringUtil.isEmpty(userEntity.getAccounttype().toString())){
                                if(!user.getAccounttype().equals(userEntity.getAccounttype())){
                                    userEntity.setAccounttype(user.getAccounttype());
                                    up="1";
                                }
                            }else{
                                userEntity.setAccounttype(user.getAccounttype());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(user.getDsporder().toString())){//显示顺序
                            if(!StringUtil.isEmpty(userEntity.getDsporder().toString())){
                                if(!userEntity.getDsporder().equals(user.getDsporder().intValue())){
                                    userEntity.setDsporder(user.getDsporder().intValue());
                                    up="1";
                                }
                            }else{
                                userEntity.setDsporder(user.getDsporder().intValue());
                                up="1";
                            }
                        }
                        if("1".equals(up)){
                            userMessageCRMRepository.update(userEntity);
                        }
//                        //调用日志
//                        InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
//                        interfaceLogEntity.setId(IdGen.uuid());
//                        interfaceLogEntity.setInterfaceName("查询OA同步人员数据接口:更新数据！");
//                        interfaceLogEntity.setCode("200");
//                        interfaceLogEntity.setEntityName("staff_OA");
//                        interfaceLogEntity.setErrorDate(new Date());
//                        interfaceLogRepository.create(interfaceLogEntity);
                    }else{
                        //没有新增
                        StaffCRMEntity staffCRMEntity = new StaffCRMEntity();
                        if(!StringUtil.isEmpty(user.getUserid().toString())){  //用户id
                            staffCRMEntity.setUserid(user.getUserid());
                        }
                        if(!StringUtil.isEmpty(user.getSubcompanyid1())){    //分部
                            staffCRMEntity.setSubcompanyid1(user.getSubcompanyid1());
                        }
                        if(!StringUtil.isEmpty(user.getDepartmentid())){ //部门
                            staffCRMEntity.setDepartmentid(user.getDepartmentid());
                        }
                        if(!StringUtil.isEmpty(user.getWorkcode())){//编号
                            staffCRMEntity.setWorkcode(user.getWorkcode());
                        }
                        if(!StringUtil.isEmpty(user.getLastname())){//姓名
                            staffCRMEntity.setLastname(user.getLastname());
                        }
                        if(!StringUtil.isEmpty(user.getLoginid())){  //系统账号
                            staffCRMEntity.setLoginid(user.getLoginid());
                        }
                        if(!StringUtil.isEmpty(user.getPassword())){//密码
                            staffCRMEntity.setPassword(user.getPassword());
                        }
                        if(!StringUtil.isEmpty(user.getSeclevel())){//安全级别
                            staffCRMEntity.setSeclevel(user.getSeclevel());
                        }
                        if(!StringUtil.isEmpty(user.getSex())){//性别
                            staffCRMEntity.setSex(user.getSex());
                        }
                        if(!StringUtil.isEmpty(user.getJobtitle())){//岗位
                            staffCRMEntity.setJobtitle(user.getJobtitle());
                        }
                        if(!StringUtil.isEmpty(user.getJobactivityid())){//职务
                            staffCRMEntity.setJobactivityid(user.getJobactivityid());
                        }
                        if(!StringUtil.isEmpty(user.getJobgroupid())){//职务类型
                            staffCRMEntity.setJobgroupid(user.getJobgroupid());
                        }
                        if(!StringUtil.isEmpty(user.getJobcall())){//职称
                            staffCRMEntity.setJobcall(user.getJobcall());
                        }
                        if(!StringUtil.isEmpty(user.getJoblevel())){ //职级
                            staffCRMEntity.setJoblevel(user.getJoblevel());
                        }
                        if(!StringUtil.isEmpty(user.getJobactivitydesc())){//职责描述
                            staffCRMEntity.setJobactivitydesc(user.getJobactivitydesc());
                        }
                        if(!StringUtil.isEmpty(user.getManagerid())){//直接上级
                            staffCRMEntity.setManagerid(user.getManagerid());
                        }
                        if(!StringUtil.isEmpty(user.getAssistantid())){//助理
                            staffCRMEntity.setAssistantid(user.getAssistantid());
                        }
                        if(!StringUtil.isEmpty(user.getStatus())){ //状态  eg:正式、试用等
                            staffCRMEntity.setStatus(user.getStatus());
                        }
                        if(!StringUtil.isEmpty(user.getLocationid())){//办公地点
                            staffCRMEntity.setLocationid(user.getLocationid());
                        }
                        if(!StringUtil.isEmpty(user.getWorkroom())){//办公室
                            staffCRMEntity.setWorkroom(user.getWorkroom());
                        }
                        if(!StringUtil.isEmpty(user.getTelephone())){//办公电话
                            staffCRMEntity.setTelephone(user.getTelephone());
                        }
                        if(!StringUtil.isEmpty(user.getMobile())){//移动电话
                            staffCRMEntity.setMobile(user.getMobile());
                        }
                        if(!StringUtil.isEmpty(user.getMobilecall())){ //其他电话
                            staffCRMEntity.setMobilecall(user.getMobilecall());
                        }
                        if(!StringUtil.isEmpty(user.getFax())){//传真
                            staffCRMEntity.setFax(user.getFax());
                        }
                        if(!StringUtil.isEmpty(user.getEmail())){//电子邮件
                            staffCRMEntity.setEmail(user.getEmail());
                        }
                        if(!StringUtil.isEmpty(user.getSystemlanguage())){//系统语言  默认7
                            staffCRMEntity.setSystemlanguage(user.getSystemlanguage());
                        }
                        if(!StringUtil.isEmpty(user.getBirthday())){ //生日
                            staffCRMEntity.setBirthday(user.getBirthday());
                        }
                        if(!StringUtil.isEmpty(user.getFolk())){ //民族
                            staffCRMEntity.setFolk(user.getFolk());
                        }
                        if(!StringUtil.isEmpty(user.getNativeplace())){//籍贯
                            staffCRMEntity.setNativeplace(user.getNativeplace());
                        }
                        if(!StringUtil.isEmpty(user.getRegresidentplace())){//户口
                            staffCRMEntity.setRegresidentplace(user.getRegresidentplace());
                        }
                        if(!StringUtil.isEmpty(user.getCertificatenum())){//身份证号
                            staffCRMEntity.setCertificatenum(user.getCertificatenum());
                        }
                        if(!StringUtil.isEmpty(user.getMaritalstatus())){//婚姻状况
                            staffCRMEntity.setMaritalstatus(user.getMaritalstatus());
                        }
                        if(!StringUtil.isEmpty(user.getPolicy())){//政治面貌
                            staffCRMEntity.setPolicy(user.getPolicy());
                        }
                        if(!StringUtil.isEmpty(user.getBememberdate())){ //入团日期
                            staffCRMEntity.setBememberdate(user.getBememberdate());
                        }
                        if(!StringUtil.isEmpty(user.getBepartydate())){//入党日期
                            staffCRMEntity.setBepartydate(user.getBepartydate());
                        }
                        if(!StringUtil.isEmpty(user.getIslabouunion())){//是否是工会会员
                            staffCRMEntity.setIslabouunion(user.getIslabouunion());
                        }
                        if(!StringUtil.isEmpty(user.getEducationlevel())){//学历
                            staffCRMEntity.setEducationlevel(user.getEducationlevel());
                        }
                        if(!StringUtil.isEmpty(user.getDegree())){//学位
                            staffCRMEntity.setDegree(user.getDegree());
                        }
                        if(!StringUtil.isEmpty(user.getHealthinfo())){ //健康状况
                            staffCRMEntity.setHealthinfo(user.getHealthinfo());
                        }
                        if(!StringUtil.isEmpty(user.getHeight())){//身高
                            staffCRMEntity.setHeight(user.getHeight());
                        }
                        if(!StringUtil.isEmpty(user.getWeight())){//体重
                            staffCRMEntity.setWeight(user.getWeight());
                        }
                        if(!StringUtil.isEmpty(user.getResidentplace())){ //居住地
                            staffCRMEntity.setResidentplace(user.getResidentplace());
                        }
                        if(!StringUtil.isEmpty(user.getHomeaddress())){//家庭住址
                            staffCRMEntity.setHomeaddress(user.getHomeaddress());
                        }
                        if(!StringUtil.isEmpty(user.getTempresidentnumber())){//暂住证号码
                            staffCRMEntity.setTempresidentnumber(user.getTempresidentnumber());
                        }
                        if(!StringUtil.isEmpty(user.getStartdate())){//合同开始日期
                            staffCRMEntity.setStartdate(user.getStartdate());
                        }
                        if(!StringUtil.isEmpty(user.getEnddate())){ //合同结束日期
                            staffCRMEntity.setEnddate(user.getEnddate());
                        }
                        if(!StringUtil.isEmpty(user.getCreatedate())){//创建日期
                            staffCRMEntity.setCreatedate(user.getCreatedate());
                        }
                        if(!StringUtil.isEmpty(user.getLastChangdate())){//最后修改日期
                            staffCRMEntity.setLastChangdate(user.getLastChangdate());
                        }
                        if(!StringUtil.isEmpty(user.getAccounttype().toString())){//账号类型
                            staffCRMEntity.setAccounttype(user.getAccounttype());
                        }
                        if(!StringUtil.isEmpty(user.getDsporder().toString())){//显示顺序
                            staffCRMEntity.setDsporder(user.getDsporder().intValue());
                        }
                        userMessageCRMRepository.create(staffCRMEntity);
//                        //调用日志
//                        InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
//                        interfaceLogEntity.setId(IdGen.uuid());
//                        interfaceLogEntity.setInterfaceName("查询OA同步人员数据接口:创建数据！");
//                        interfaceLogEntity.setCode("200");
//                        interfaceLogEntity.setEntityName("staff_OA");
//                        interfaceLogEntity.setErrorDate(new Date());
//                        interfaceLogRepository.create(interfaceLogEntity);
                    }
                }
            }
            if(null != subCompanyBeans && subCompanyBeans.length>0){
                userMessageCRMRepository.deleteSubcompany();
                for(SubCompanyBean sub:subCompanyBeans) {
//                    SubcompanyCRMEntity subEntity = userMessageCRMRepository.getSubcompany(sub.get_subcompanyid());
                    SubcompanyCRMEntity subEntity = null;
                    //有则修改
                    if (null != subEntity) {
                        String up = "0";
                        if(!StringUtil.isEmpty(sub.get_shortname())){//简称
                            if(!StringUtil.isEmpty(subEntity.getShortname())){
                                if(!sub.get_shortname().equals(subEntity.getShortname())){
                                    subEntity.setShortname(sub.get_shortname());
                                    up="1";
                                }
                            }else{
                                subEntity.setShortname(sub.get_shortname());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(sub.get_fullname())){//全称
                            if(!StringUtil.isEmpty(subEntity.getFullname())){
                                if(!sub.get_fullname().equals(subEntity.getFullname())){
                                    subEntity.setFullname(sub.get_fullname());
                                    up="1";
                                }
                            }else{
                                subEntity.setFullname(sub.get_fullname());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(sub.get_supsubcompanyid())){//上级分部id
                            if(!StringUtil.isEmpty(subEntity.getSupsubcompanyid())){
                                if(!sub.get_supsubcompanyid().equals(subEntity.getSupsubcompanyid())){
                                    subEntity.setSupsubcompanyid(sub.get_supsubcompanyid());
                                    up="1";
                                }
                            }else{
                                subEntity.setSupsubcompanyid(sub.get_supsubcompanyid());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(sub.get_website())){//网站
                            if(!StringUtil.isEmpty(subEntity.getWebsite())){
                                if(!sub.get_website().equals(subEntity.getWebsite())){
                                    subEntity.setWebsite(sub.get_website());
                                    up="1";
                                }
                            }else{
                                subEntity.setWebsite(sub.get_website());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(sub.get_showorder())){ //显示顺序
                            if(!StringUtil.isEmpty(subEntity.getShoworder())){
                                if(!sub.get_showorder().equals(subEntity.getShoworder())){
                                    subEntity.setShoworder(sub.get_showorder());
                                    up="1";
                                }
                            }else{
                                subEntity.setShoworder(sub.get_showorder());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(sub.get_code())){//公司编码
                            if(!StringUtil.isEmpty(subEntity.getCode())){
                                if(!sub.get_code().equals(subEntity.getCode())){
                                    subEntity.setCode(sub.get_code());
                                    up="1";
                                }
                            }else{
                                subEntity.setCode(sub.get_code());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(sub.get_canceled())){//是否封存
                            if(!StringUtil.isEmpty(subEntity.getCanceled())){
                                if(!sub.get_canceled().equals(subEntity.getCanceled())){
                                    subEntity.setCanceled(sub.get_canceled());
                                    up="1";
                                }
                            }else{
                                subEntity.setCanceled(sub.get_canceled());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(sub.getLastChangdate())){//最后更新日期
                            if(!StringUtil.isEmpty(subEntity.getLastChangdate())){
                                if(!sub.getLastChangdate().equals(subEntity.getLastChangdate())){
                                    subEntity.setLastChangdate(sub.getLastChangdate());
                                    up="1";
                                }
                            }else{
                                subEntity.setLastChangdate(sub.getLastChangdate());
                                up="1";
                            }
                        }
                        if("1".equals(up)){
                            userMessageCRMRepository.updateSubcompany(subEntity);
                        }
//                        //调用日志
//                        InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
//                        interfaceLogEntity.setId(IdGen.uuid());
//                        interfaceLogEntity.setInterfaceName("查询OA同步分部数据接口:更新数据！");
//                        interfaceLogEntity.setCode("200");
//                        interfaceLogEntity.setEntityName("subcompany_crm");
//                        interfaceLogEntity.setErrorDate(new Date());
//                        interfaceLogRepository.create(interfaceLogEntity);
                    }else{
                        //没有则新增
                        SubcompanyCRMEntity subcompanyCRMEntity = new SubcompanyCRMEntity();
                        if(!StringUtil.isEmpty(sub.get_subcompanyid())){//分部id
                            subcompanyCRMEntity.setSubcompanyid(sub.get_subcompanyid());
                        }
                        if(!StringUtil.isEmpty(sub.get_shortname())){//简称
                            subcompanyCRMEntity.setShortname(sub.get_shortname());
                        }
                        if(!StringUtil.isEmpty(sub.get_fullname())){//全称
                            subcompanyCRMEntity.setFullname(sub.get_fullname());
                        }
                        if(!StringUtil.isEmpty(sub.get_supsubcompanyid())){//上级分部id
                            subcompanyCRMEntity.setSupsubcompanyid(sub.get_supsubcompanyid());
                        }
                        if(!StringUtil.isEmpty(sub.get_website())){//网站
                            subcompanyCRMEntity.setWebsite(sub.get_website());
                        }
                        if(!StringUtil.isEmpty(sub.get_showorder())){ //显示顺序
                            subcompanyCRMEntity.setShoworder(sub.get_showorder());
                        }
                        if(!StringUtil.isEmpty(sub.get_code())){//公司编码
                            subcompanyCRMEntity.setCode(sub.get_code());
                        }
                        if(!StringUtil.isEmpty(sub.get_canceled())){//是否封存
                            subcompanyCRMEntity.setCanceled(sub.get_canceled());
                        }
                        if(!StringUtil.isEmpty(sub.getLastChangdate())){//最后更新日期
                            subcompanyCRMEntity.setLastChangdate(sub.getLastChangdate());
                        }
                        userMessageCRMRepository.createSubcompany(subcompanyCRMEntity);
//                        //调用日志
//                        InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
//                        interfaceLogEntity.setId(IdGen.uuid());
//                        interfaceLogEntity.setInterfaceName("查询OA同步分部数据接口:创建数据！");
//                        interfaceLogEntity.setCode("200");
//                        interfaceLogEntity.setEntityName("subcompany_crm");
//                        interfaceLogEntity.setErrorDate(new Date());
//                        interfaceLogRepository.create(interfaceLogEntity);
                    }
                }
            }
            if(null != departmentBeans && departmentBeans.length>0){
                userMessageCRMRepository.deleteDepartment();
                for(DepartmentBean department:departmentBeans) {
//                    DepartmentCRMEntity departmentEntity = userMessageCRMRepository.getDepartment(department.get_departmentid());
                    DepartmentCRMEntity departmentEntity = null;
                    //有则修改
                    if (null != departmentEntity) {
                        String up = "0";
                        if(!StringUtil.isEmpty(department.get_shortname())){//简称
                            if(!StringUtil.isEmpty(departmentEntity.getShortname())){
                                if(!department.get_shortname().equals(departmentEntity.getShortname())){
                                    departmentEntity.setShortname(department.get_shortname());
                                    up="1";
                                }
                            }else{
                                departmentEntity.setShortname(department.get_shortname());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(department.get_fullname())){//全称
                            if(!StringUtil.isEmpty(departmentEntity.getFullname())){
                                if(!department.get_fullname().equals(departmentEntity.getFullname())){
                                    departmentEntity.setFullname(department.get_fullname());
                                    up="1";
                                }
                            }else{
                                departmentEntity.setFullname(department.get_fullname());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(department.get_subcompanyid())){//所属分部id
                            if(!StringUtil.isEmpty(departmentEntity.getSubcompanyid())){
                                if(!department.get_subcompanyid().equals(departmentEntity.getSubcompanyid())){
                                    departmentEntity.setSubcompanyid(department.get_subcompanyid());
                                    up="1";
                                }
                            }else{
                                departmentEntity.setSubcompanyid(department.get_subcompanyid());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(department.get_supdepartmentid())){//上级部门id
                            if(!StringUtil.isEmpty(departmentEntity.getSupdepartmentid())){
                                if(!department.get_supdepartmentid().equals(departmentEntity.getSupdepartmentid())){
                                    departmentEntity.setSupdepartmentid(department.get_supdepartmentid());
                                    up="1";
                                }
                            }else{
                                departmentEntity.setSupdepartmentid(department.get_supdepartmentid());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(department.get_showorder())){ //显示顺序
                            if(!StringUtil.isEmpty(departmentEntity.getShoworder())){
                                if(!department.get_showorder().equals(departmentEntity.getShoworder())){
                                    departmentEntity.setShoworder(department.get_showorder());
                                    up="1";
                                }
                            }else{
                                departmentEntity.setShoworder(department.get_showorder());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(department.get_code())){//部门编码
                            if(!StringUtil.isEmpty(departmentEntity.getCode())){
                                if(!department.get_code().equals(departmentEntity.getCode())){
                                    departmentEntity.setCode(department.get_code());
                                    up="1";
                                }
                            }else{
                                departmentEntity.setCode(department.get_code());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(department.get_canceled())){//是否封存
                            if(!StringUtil.isEmpty(departmentEntity.getCanceled())){
                                if(!department.get_canceled().equals(departmentEntity.getCanceled())){
                                    departmentEntity.setCanceled(department.get_canceled());
                                    up="1";
                                }
                            }else{
                                departmentEntity.setCanceled(department.get_canceled());
                                up="1";
                            }
                        }
                        if(!StringUtil.isEmpty(department.getLastChangdate())){//最后更新日期
                            if(!StringUtil.isEmpty(departmentEntity.getLastChangdate())){
                                if(!department.getLastChangdate().equals(departmentEntity.getLastChangdate())){
                                    departmentEntity.setLastChangdate(department.getLastChangdate());
                                    up="1";
                                }
                            }else{
                                departmentEntity.setLastChangdate(department.getLastChangdate());
                                up="1";
                            }
                        }
                        if("1".equals(up)){
                            userMessageCRMRepository.updateDepartment(departmentEntity);
                        }
//                        //调用日志
//                        InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
//                        interfaceLogEntity.setId(IdGen.uuid());
//                        interfaceLogEntity.setInterfaceName("查询OA同步部门数据接口:更新数据！");
//                        interfaceLogEntity.setCode("200");
//                        interfaceLogEntity.setEntityName("department_crm");
//                        interfaceLogEntity.setErrorDate(new Date());
//                        interfaceLogRepository.create(interfaceLogEntity);
                    }else {
                        //无则新增
                        DepartmentCRMEntity departmentCRMEntity = new DepartmentCRMEntity();
                        if(!StringUtil.isEmpty(department.get_departmentid())){//部门id
                            departmentCRMEntity.setDepartmentid(department.get_departmentid());
                        }
                        if(!StringUtil.isEmpty(department.get_shortname())){//简称
                            departmentCRMEntity.setShortname(department.get_shortname());
                        }
                        if(!StringUtil.isEmpty(department.get_fullname())){//全称
                            departmentCRMEntity.setFullname(department.get_fullname());
                        }
                        if(!StringUtil.isEmpty(department.get_subcompanyid())){//所属分部id
                            departmentCRMEntity.setSubcompanyid(department.get_subcompanyid());
                        }
                        if(!StringUtil.isEmpty(department.get_supdepartmentid())){//上级部门id
                            departmentCRMEntity.setSupdepartmentid(department.get_supdepartmentid());
                        }
                        if(!StringUtil.isEmpty(department.get_showorder())){ //显示顺序
                            departmentCRMEntity.setShoworder(department.get_showorder());
                        }
                        if(!StringUtil.isEmpty(department.get_code())){//部门编码
                            departmentCRMEntity.setCode(department.get_code());
                        }
                        if(!StringUtil.isEmpty(department.get_canceled())){//是否封存
                            departmentCRMEntity.setCanceled(department.get_canceled());
                        }
                        if(!StringUtil.isEmpty(department.getLastChangdate())){//最后更新日期
                            departmentCRMEntity.setLastChangdate(department.getLastChangdate());
                        }
                        userMessageCRMRepository.createDepartment(departmentCRMEntity);
//                        //调用日志
//                        InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
//                        interfaceLogEntity.setId(IdGen.uuid());
//                        interfaceLogEntity.setInterfaceName("查询OA同步部门数据接口:创建数据！");
//                        interfaceLogEntity.setCode("200");
//                        interfaceLogEntity.setEntityName("department_crm");
//                        interfaceLogEntity.setErrorDate(new Date());
//                        interfaceLogRepository.create(interfaceLogEntity);
                    }
                }
            }
            return "200";
        }catch (Exception e) {
            e.printStackTrace();
            //调用日志
            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
            interfaceLogEntity.setId(IdGen.uuid());
            interfaceLogEntity.setInterfaceName("OA同步数据接口！");
            interfaceLogEntity.setCode("500");
            interfaceLogEntity.setEntityName("staff_OA/subcompany_crm/department_crm");
            interfaceLogEntity.setErrorDate(new Date());
            interfaceLogRepository.create(interfaceLogEntity);
            return "500";
        }
    }
}
