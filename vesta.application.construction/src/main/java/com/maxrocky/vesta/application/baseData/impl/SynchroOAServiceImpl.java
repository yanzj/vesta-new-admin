package com.maxrocky.vesta.application.baseData.impl;

import com.maxrocky.vesta.application.baseData.inf.SynchroOAService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.domain.repository.UserAgencyRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.jdbc.JdbcConnection;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by chen on 2016/12/20.
 * 同步OA人员部门数据
 */
@Service
public class SynchroOAServiceImpl implements SynchroOAService {
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    UserAgencyRepository userAgencyRepository;
    @Autowired
    AgencyRepository agencyRepository;

    @Override
    public void synchroOA() throws Exception {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//JDBC驱动名  
        String dbURL = "jdbc:sqlserver://172.16.104.152:1433;DatabaseName=FranshionplanMgt";//连接服务器和数据库地址 
        String userName = "OAQuery"; //默认用户名  
        String userPwd = "oaquery"; //密码
        JdbcConnection jdbcConnection = new JdbcConnection();
        Connection conn = jdbcConnection.getJdbcConnection(driverName, dbURL, userName, userPwd);
        Statement stmt = conn.createStatement();
        String sql = "select userid,departmentid,loginid,lastname,password,email,dsporder,createdate,lastChangdate,mobile,telephone,status from OAUser where accounttype!='1'"; //查询OA主人员数据
        ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
        UserPropertyStaffEntity userPropertyStaffEntity;
        UserAgencymapEntity userAgencymapEntity;
        while (rs.next()) {
            if (!StringUtil.isEmpty(rs.getString(3))) { //当用户名为空时过滤该条数据
                //根据OA员工ID进行查询，如果有更新OA数据；
                // 如果没有，再根据用户名去查询是否存在；
                UserPropertyStaffEntity userPropertyStaffByStaffId = userPropertyStaffRepository.getStaffUserByStaffId(rs.getString(1));
                if (userPropertyStaffByStaffId != null) {
                    userPropertyStaffByStaffId.setUserName(rs.getString(3));//用户名
                    userPropertyStaffByStaffId.setStaffName(rs.getString(4));//用户真实名称
                    userPropertyStaffByStaffId.setPassword(userPropertyStaffByStaffId.getPassword());//密码
//                    if(StringUtil.isEmpty(rs.getString(5))){
//                        userPropertyStaffByStaffId.setPassword("e10adc3949ba59abbe56e057f20f883e");//OA密码为空默认123456
//                    }else {
//                        userPropertyStaffByStaffId.setPassword(rs.getString(5));//OA密码
//                    }
                    userPropertyStaffByStaffId.setEmail(rs.getString(6));//邮件
                    userPropertyStaffByStaffId.setOrderNum((int) Double.parseDouble(rs.getString(7)));//排序
                    userPropertyStaffByStaffId.setCreateOn(rs.getTimestamp(8));//创建时间
                    userPropertyStaffByStaffId.setModifyOn(rs.getTimestamp(9));//修改时间
                    if (!StringUtil.isEmpty(rs.getString(10))) {
                        userPropertyStaffByStaffId.setMobile(rs.getString(10).split("/")[0]);//手机
                    }
                    userPropertyStaffByStaffId.setOfficePhone(rs.getString(11));//工作电话
                    //OA状态：试用-0、正式-1、临时-2、试用延期-3、解聘-4、离职-5、退休-6、无效-7、删除-10
                    if ("0".equals(rs.getString(12)) || "1".equals(rs.getString(12)) || "2".equals(rs.getString(12)) || "3".equals(rs.getString(12))) {
                        userPropertyStaffByStaffId.setStaffState("1");//状态
                    } else {
                        userPropertyStaffByStaffId.setStaffState("0");
                    }
                    userPropertyStaffRepository.updateStaff(userPropertyStaffByStaffId);
                } else {
                    //员工信息表
                    userPropertyStaffEntity = new UserPropertyStaffEntity();
                    userPropertyStaffEntity.setStaffId(rs.getString(1));//员工ID
                    userPropertyStaffEntity.setUserName(rs.getString(3));//用户名
                    userPropertyStaffEntity.setStaffName(rs.getString(4));//用户真实名称
                    userPropertyStaffEntity.setPassword("e10adc3949ba59abbe56e057f20f883e");//OA密码为空默认123456
//                    if(StringUtil.isEmpty(rs.getString(5))){
//                        userPropertyStaffEntity.setPassword("e10adc3949ba59abbe56e057f20f883e");//OA密码为空默认123456
//                    }else {
//                        userPropertyStaffEntity.setPassword(rs.getString(5));//OA密码
//                    }
                    userPropertyStaffEntity.setEmail(rs.getString(6));//邮件
                    userPropertyStaffEntity.setOrderNum((int) Double.parseDouble(rs.getString(7)));//排序
                    userPropertyStaffEntity.setCreateBy("OA同步");//创建人
                    userPropertyStaffEntity.setCreateOn(rs.getTimestamp(8));//创建时间
                    userPropertyStaffEntity.setModifyBy("OA同步");//修改人
                    userPropertyStaffEntity.setModifyOn(rs.getTimestamp(9));//修改时间
                    if (!StringUtil.isEmpty(rs.getString(10))) {
                        userPropertyStaffEntity.setMobile(rs.getString(10).split("/")[0]);//手机
                    }
                    userPropertyStaffEntity.setOfficePhone(rs.getString(11));//工作电话
                    if ("0".equals(rs.getString(12)) || "1".equals(rs.getString(12)) || "2".equals(rs.getString(12)) || "3".equals(rs.getString(12))) {
                        userPropertyStaffEntity.setStaffState("1");//状态
                    } else {
                        userPropertyStaffEntity.setStaffState("0");
                    }
                    //人员组织关联关系
                    userAgencymapEntity = new UserAgencymapEntity();
                    userAgencymapEntity.setMapId(IdGen.uuid());//主键
                    userAgencymapEntity.setStaffId(rs.getString(1));//员工ID
                    userAgencymapEntity.setAgencyId(rs.getString(2) + "dpx"); //机构ID 由于OA主键有部分与我们的冲突 所以OA部门的全部追加dpx 公司的追加cpx
                    userAgencymapEntity.setModifyTime(rs.getTimestamp(9));//修改时间
                    userAgencymapEntity.setStatus("1");//状态 0删除 1正常
                    userAgencymapEntity.setSourceFrom("OA");//来源

                    //如果存在把现有的数据用户名后面拼接“chongfu”作为标识；
                    // 不存在就直接添加数据；
                    UserPropertyStaffEntity userPropertyStaffByUserName = userPropertyStaffRepository.checkOAUserName(rs.getString(3));
                    if (userPropertyStaffByUserName != null) {
                        userPropertyStaffByUserName.setUserName(userPropertyStaffByUserName.getUserName() + "_chongfu");
                        userPropertyStaffRepository.updateStaff(userPropertyStaffByUserName);//修改现有数据

                        userPropertyStaffRepository.addUserPropertyStaff(userPropertyStaffEntity);//新增OA数据
                        userAgencyRepository.addOAUserAgency(userAgencymapEntity);//新增人员与组织关系数据
                    } else {
                        userPropertyStaffRepository.addUserPropertyStaff(userPropertyStaffEntity);//新增OA数据
                        userAgencyRepository.addOAUserAgency(userAgencymapEntity);//新增人员与组织关系数据
                    }
                }
//                if (userPropertyStaff != null) {//当用户名相同时 以OA为准
//                    userPropertyStaffEntity.setStaffId(userPropertyStaff.getStaffId());
//                    userPropertyStaffEntity.setCreateBy(userPropertyStaff.getCreateBy());
//                    userPropertyStaffEntity.setCreateOn(userPropertyStaff.getCreateOn());
//                    userPropertyStaffEntity.setPassword(userPropertyStaff.getPassword());
//                    userAgencymapEntity.setStaffId(userPropertyStaff.getStaffId());
//                    userAgencyRepository.delOAUserAgency(userPropertyStaff.getStaffId()); //将OA的关系数据置为无效
//                    userAgencyRepository.addOAUserAgency(userAgencymapEntity);
//                    userPropertyStaffRepository.updateStaff(userPropertyStaffEntity);
//                } else {
//                    userAgencymapEntity.setStaffId(userPropertyStaffEntity.getStaffId());
//                    userAgencyRepository.addOAUserAgency(userAgencymapEntity);
//                    userPropertyStaffRepository.addUserPropertyStaff(userPropertyStaffEntity);
//                }
            }
        }

        sql = "select subcompanyid,supsubcompanyid,shortname,fullname,showorder,createtime,lastChangdate,canceled from OACompany where supsubcompanyid<>0"; //查询公司
        ResultSet cop = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
        AgencyEntity agencyEntity;
        while (cop.next()) {
            agencyEntity = new AgencyEntity();
            agencyEntity.setAgencyId(cop.getString(1) + "cpx"); //由于OA主键有部分与我们的冲突 所以OA部门的全部追加dpx 公司的追加cpx
            if ("1".equals(cop.getString(2))) {
                agencyEntity.setParentId("cbe740dcf7d14d5091403928988634cf"); //中国金茂 节点的主键
            } else {
                agencyEntity.setParentId(cop.getString(2) + "cpx");
            }
            AgencyEntity agencyEntity1 = agencyRepository.getAgencyDetail(agencyEntity.getParentId());  //此处必须严格按照上下级顺序来 否则该数据则被抛弃
            if (agencyEntity1 != null) {
                agencyEntity.setIsRoot("0");
                agencyEntity.setAgencyType("2");
                agencyEntity.setAgencyName(cop.getString(3));
                agencyEntity.setAgencyDesc(cop.getString(4));
                agencyEntity.setOrderNum(cop.getInt(5));
                agencyEntity.setCreateTime(cop.getTimestamp(6));
                agencyEntity.setModifyTime(cop.getTimestamp(7));
                if ("0".equals(cop.getString(8))) {
                    agencyEntity.setStatus("1");
                } else {
                    agencyEntity.setStatus("0");
                }
                agencyEntity.setAgencyPath(agencyEntity1.getAgencyPath() + "/" + agencyEntity.getAgencyId());
                agencyEntity.setLevel(agencyEntity1.getLevel() + 1);
                agencyEntity.setOutEmploy("0");
                agencyRepository.addAgency(agencyEntity);
            }
        }
        sql = "select departmentid,supdepartmentid,subcompanyid,shortname,fullname,showorder,createtime,lastChangdate,canceled from OADepartment";//查询部门
        ResultSet dpt = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
        while (dpt.next()) {
            agencyEntity = new AgencyEntity();
            agencyEntity.setAgencyId(dpt.getString(1) + "dpx");
            if ("0".equals(dpt.getString(2))) {
                agencyEntity.setParentId(dpt.getString(3) + "cpx");
                if ("1".equals(dpt.getString(3))) {
                    agencyEntity.setParentId("cbe740dcf7d14d5091403928988634cf");  //中国金茂 节点的主键
                }
            } else {
                agencyEntity.setParentId(dpt.getString(2) + "dpx");
            }
            AgencyEntity agencyEntity1 = agencyRepository.getAgencyDetail(agencyEntity.getParentId());
            if (agencyEntity1 != null) {
                agencyEntity.setAgencyPath(agencyEntity1.getAgencyPath() + "/" + agencyEntity.getAgencyId());
                agencyEntity.setLevel(agencyEntity1.getLevel() + 1);
                agencyEntity.setAgencyName(dpt.getString(4));
                agencyEntity.setAgencyDesc(dpt.getString(5));
                agencyEntity.setAgencyType("1");
                agencyEntity.setIsRoot("0");
                agencyEntity.setCreateTime(dpt.getTimestamp(7));
                agencyEntity.setModifyTime(dpt.getTimestamp(8));
                agencyEntity.setOrderNum(dpt.getInt(6));
                if ("0".equals(dpt.getString(9))) {
                    agencyEntity.setStatus("1");
                } else {
                    agencyEntity.setStatus("0");
                }
                agencyEntity.setOutEmploy("0");
                agencyRepository.addAgency(agencyEntity);
            }

        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void synchronousOA() throws Exception {
        //连接OA数据库
        //JDBC驱动名 
        String DriverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //连接服务器和数据库地址 
        String URL = "jdbc:sqlserver://172.16.104.152:1433;DatabaseName=FranshionplanMgt";
        //默认用户名
        String userName = "OAQuery";
        //密码
        String userPassword = "oaquery";
        JdbcConnection jdbcConnection = new JdbcConnection();
        Connection connection = jdbcConnection.getJdbcConnection(DriverName, URL, userName, userPassword);
        Statement stmt = connection.createStatement();
        //查询OA主人员数据
        String sql = "select userid,departmentid,loginid,lastname,password,email,dsporder,createdate,lastChangdate,mobile,telephone,status from OAUser where accounttype!='1'";
        // executeQuery会返回结果的集合，否则返回空值
        ResultSet rs = stmt.executeQuery(sql);
        UserInformationEntity userInformationEntity;
        UserAgencyEntity userAgencyEntity;
        while (rs.next()) {
            if (!StringUtil.isEmpty(rs.getString(3))) { //当用户名为空时过滤该条数据
                //根据OA员工ID进行查询，如果有更新OA数据；
                // 如果没有，再根据用户名去查询是否存在；
                UserInformationEntity userInformationEntity1 = userPropertyStaffRepository.getStaffByStaffId(rs.getString(1));
                UserMapEntity userMapEntity;
                if (userInformationEntity1 != null) {
                    userInformationEntity1.setUserName(rs.getString(3));//用户名
                    userInformationEntity1.setSysName(rs.getString(3));//本操作系统名
                    userInformationEntity1.setStaffName(rs.getString(4));//用户真实名称
                    userInformationEntity1.setPassword(userInformationEntity1.getPassword());//密码
                    userInformationEntity1.setEmail(rs.getString(6));//邮件
                    userInformationEntity1.setOrderNum((int) Double.parseDouble(rs.getString(7)));//排序
                    userInformationEntity1.setCreateOn(rs.getTimestamp(8));//创建时间
                    userInformationEntity1.setModifyOn(rs.getTimestamp(9));//修改时间
                    if (!StringUtil.isEmpty(rs.getString(10))) {
                        userInformationEntity1.setMobile(rs.getString(10).split("/")[0]);//手机
                    }
                    userInformationEntity1.setOfficePhone(rs.getString(11));//工作电话
                    //OA状态：试用-0、正式-1、临时-2、试用延期-3、解聘-4、离职-5、退休-6、无效-7、删除-10
                    if ("0".equals(rs.getString(12)) || "1".equals(rs.getString(12)) || "2".equals(rs.getString(12)) || "3".equals(rs.getString(12))) {
                        userInformationEntity1.setStaffState("1");//状态
                    } else {
                        userInformationEntity1.setStaffState("0");
                    }
                    userPropertyStaffRepository.saveOrUpdateStaff(userInformationEntity1);
                    //人员启用状态
                    userMapEntity = new UserMapEntity();
                    userMapEntity.setState("0");
                    userMapEntity.setModifyOn(new Date());
                    userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                    userMapEntity.setSourceFrom("OA");
                    userMapEntity.setProjectModule("st");
                    userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                    userMapEntity.setProjectModule("qc");
                    userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                    userMapEntity.setProjectModule("es");
                    userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                } else {
                    //员工信息表
                    userInformationEntity = new UserInformationEntity();
                    userInformationEntity.setStaffId(rs.getString(1));//员工ID
                    userInformationEntity.setUserName(rs.getString(3));//用户名
                    userInformationEntity.setSysName(rs.getString(3));//本操作系统名
                    userInformationEntity.setStaffName(rs.getString(4));//用户真实名称
                    userInformationEntity.setPassword("e10adc3949ba59abbe56e057f20f883e");//OA密码为空默认123456
                    userInformationEntity.setEmail(rs.getString(6));//邮件
                    userInformationEntity.setOrderNum((int) Double.parseDouble(rs.getString(7)));//排序
                    userInformationEntity.setCreateBy("OA同步");//创建人
                    userInformationEntity.setCreateOn(rs.getTimestamp(8));//创建时间
                    userInformationEntity.setModifyBy("OA同步");//修改人
                    userInformationEntity.setModifyOn(rs.getTimestamp(9));//修改时间
                    if (!StringUtil.isEmpty(rs.getString(10))) {
                        userInformationEntity.setMobile(rs.getString(10).split("/")[0]);//手机
                    }
                    userInformationEntity.setOfficePhone(rs.getString(11));//工作电话
                    if ("0".equals(rs.getString(12)) || "1".equals(rs.getString(12)) || "2".equals(rs.getString(12)) || "3".equals(rs.getString(12))) {
                        userInformationEntity.setStaffState("1");//状态
                    } else {
                        userInformationEntity.setStaffState("0");
                    }
                    //人员组织关联关系
                    userAgencyEntity = new UserAgencyEntity();
                    userAgencyEntity.setMapId(IdGen.uuid());//主键
                    userAgencyEntity.setStaffId(rs.getString(1));//员工ID
                    userAgencyEntity.setAgencyId(rs.getString(2) + "dpx"); //机构ID 由于OA主键有部分与我们的冲突 所以OA部门的全部追加dpx 公司的追加cpx
                    userAgencyEntity.setModifyTime(rs.getTimestamp(9));//修改时间
                    userAgencyEntity.setStatus("1");//状态 0删除 1正常
                    userAgencyEntity.setSourceFrom("OA");//来源
                    //如果存在把现有的数据用户名后面拼接“chongfu”作为标识；
                    // 不存在就直接添加数据；
                    UserInformationEntity userInformationEntity2 = userPropertyStaffRepository.getStaffByName(rs.getString(3));
                    if (userInformationEntity2 != null) {
                        userInformationEntity2.setUserName(userInformationEntity2.getUserName() + "_chongfu");
                        userPropertyStaffRepository.saveOrUpdateStaff(userInformationEntity2);//修改现有数据
                        userPropertyStaffRepository.addUserInformation(userInformationEntity2);//新增OA数据
                        userAgencyRepository.addUserAgency(userAgencyEntity);//新增人员与组织关系数据
                    } else {
                        userPropertyStaffRepository.addUserInformation(userInformationEntity2);//新增OA数据
                        userAgencyRepository.addUserAgency(userAgencyEntity);//新增人员与组织关系数据
                    }
                    //人员启用状态
                    userMapEntity = new UserMapEntity();
                    userMapEntity.setState("0");
                    userMapEntity.setModifyOn(new Date());
                    userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                    userMapEntity.setSourceFrom("OA");
                    userMapEntity.setProjectModule("st");
                    userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                    userMapEntity.setProjectModule("qc");
                    userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                    userMapEntity.setProjectModule("es");
                    userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                }
            }
        }
        sql = "select subcompanyid,supsubcompanyid,shortname,fullname,showorder,createtime,lastChangdate,canceled from OACompany where supsubcompanyid<>0"; //查询公司
        ResultSet cop = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
        AuthOuterAgencyEntity authOuterAgencyEntity ;
        while (cop.next()) {
            authOuterAgencyEntity = new AuthOuterAgencyEntity();
            authOuterAgencyEntity.setAgencyId(cop.getString(1) + "cpx"); //由于OA主键有部分与我们的冲突 所以OA部门的全部追加dpx 公司的追加cpx
            if ("1".equals(cop.getString(2))) {
                authOuterAgencyEntity.setParentId("cbe740dcf7d14d5091403928988634cf"); //中国金茂 节点的主键
            } else {
                authOuterAgencyEntity.setParentId(cop.getString(2) + "cpx");
            }
            AuthOuterAgencyEntity authOuterAgencyEntity1 = agencyRepository.getAgencyDetailByAgencyId(authOuterAgencyEntity.getParentId());//此处必须严格按照上下级顺序来 否则该数据则被抛弃
            if (authOuterAgencyEntity1 != null) {
                authOuterAgencyEntity.setOutEmploy("0");
                authOuterAgencyEntity.setIsTemporary("0");
                authOuterAgencyEntity.setAgencyName(cop.getString(3));
                authOuterAgencyEntity.setAgencyDesc(cop.getString(4));
                authOuterAgencyEntity.setOrderNum(cop.getInt(5));
                authOuterAgencyEntity.setCreateTime(cop.getTimestamp(6));
                authOuterAgencyEntity.setModifyTime(cop.getTimestamp(7));
                if ("0".equals(cop.getString(8))) {
                    authOuterAgencyEntity.setStatus("1");
                } else {
                    authOuterAgencyEntity.setStatus("0");
                }
                authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity1.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());
                authOuterAgencyEntity.setLevel(authOuterAgencyEntity1.getLevel() + 1);
                agencyRepository.saveOrupdate(authOuterAgencyEntity);
            }
        }
        sql = "select departmentid,supdepartmentid,subcompanyid,shortname,fullname,showorder,createtime,lastChangdate,canceled from OADepartment";//查询部门
        ResultSet dpt = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
        while (dpt.next()) {
            authOuterAgencyEntity = new AuthOuterAgencyEntity();
            authOuterAgencyEntity.setAgencyId(dpt.getString(1) + "dpx");
            if ("0".equals(dpt.getString(2))) {
                authOuterAgencyEntity.setParentId(dpt.getString(3) + "cpx");
                if ("1".equals(dpt.getString(3))) {
                    authOuterAgencyEntity.setParentId("cbe740dcf7d14d5091403928988634cf");  //中国金茂 节点的主键
                }
            } else {
                authOuterAgencyEntity.setParentId(dpt.getString(2) + "dpx");
            }
            AuthOuterAgencyEntity authOuterAgencyEntity1 = agencyRepository.getAgencyDetailByAgencyId(authOuterAgencyEntity.getParentId());//此处必须严格按照上下级顺序来 否则该数据则被抛弃
            if (authOuterAgencyEntity1 != null) {
                authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity1.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());
                authOuterAgencyEntity.setLevel(authOuterAgencyEntity1.getLevel() + 1);
                authOuterAgencyEntity.setAgencyName(dpt.getString(4));
                authOuterAgencyEntity.setAgencyDesc(dpt.getString(5));
                authOuterAgencyEntity.setIsTemporary("0");
                authOuterAgencyEntity.setCreateTime(dpt.getTimestamp(7));
                authOuterAgencyEntity.setModifyTime(dpt.getTimestamp(8));
                authOuterAgencyEntity.setOrderNum(dpt.getInt(6));
                if ("0".equals(dpt.getString(9))) {
                    authOuterAgencyEntity.setStatus("1");
                } else {
                    authOuterAgencyEntity.setStatus("0");
                }
                authOuterAgencyEntity.setOutEmploy("0");
                agencyRepository.saveOrupdate(authOuterAgencyEntity);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

