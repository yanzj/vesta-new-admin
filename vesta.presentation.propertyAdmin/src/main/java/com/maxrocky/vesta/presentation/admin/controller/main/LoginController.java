package com.maxrocky.vesta.presentation.admin.controller.main;

import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.QualityLogEntity;
import com.maxrocky.vesta.domain.model.RoleViewmodelEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.vesta.Viewmodel;
import com.maxrocky.vesta.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JillChen on 2016/1/12.
 */

@Controller
@RequestMapping(value = "/login")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist", "tabValue"})

public class LoginController {


    @Autowired
    RoleViewmodelService roleViewmodelService;

    @Autowired
    UserPropertystaffService userPropertystaffService;

    @Autowired
    OperationLogService operationLogService;
    @Autowired
    QualityLogService qualityLogService;

    @Autowired
    AuthAgencyService authAgencyService;

//    @Autowired
//    private MapperFacade mapper;

    @RequestMapping(value = "/loginCheck.html", method = RequestMethod.POST)
    public ModelAndView loginCheck(HttpServletRequest request, HttpServletResponse response) {
        //默认设置 ---系统中如果拥有商城管理权限的用户会再拥有一个商户角色用户 id 与物业用户相同

        String userid = request.getParameter("userId");
        String old_pwd = request.getParameter("password");

        String pwd = PasswordEncode.digestPassword(old_pwd);
        String checkLogin = request.getParameter("checkLogin");//st为登录新权限安全
        ModelAndView mav;
        mav = new ModelAndView("/main/main");
        try {

            //查询物业人员
            UserPropertyStaffEntity propertystaff = new UserPropertyStaffEntity();
            propertystaff.setStaffId(userid);
            propertystaff.setUserName(userid);
            propertystaff.setPassword(pwd);
            //物业人员
            UserPropertyStaffEntity rePropertystaff = userPropertystaffService.CheckStaffByIdAPwd(propertystaff);
            /**********************以下安全权限修改*************************************/
            UserInformationEntity userInformationEntity=new UserInformationEntity();
            userInformationEntity.setStaffId(userid);
            userInformationEntity.setSysName(userid);
            userInformationEntity.setPassword(pwd);
            UserInformationEntity reUserInformationEntity = userPropertystaffService.CheckUserInforByIdAPwd(userInformationEntity,checkLogin);

            /**********************以上安全权限修改*************************************/
            if (!"st".equals(checkLogin) && rePropertystaff != null && !"es".equals(checkLogin)&& !"qc".equals(checkLogin)) {
                rePropertystaff.setPassword(""); ///置空密码
                mav.addObject("propertystaff", rePropertystaff);

//                reUserInformationEntity.setPassword(""); ///置空密码
//                mav.addObject("userInforStaff", reUserInformationEntity);
                //-----------查询角色权限 ---取出的是第一級

                List<RoleViewmodelEntity> oldlistone = roleViewmodelService.getViewListByUserId("property", rePropertystaff.getStaffId());
                //獲取除一級外的其他菜單
                List<RoleViewmodelEntity> oldlisttwo = roleViewmodelService.getViewListOtherByUserId("property", rePropertystaff.getStaffId());

                List<Viewmodel> viewmodelList = new ArrayList<>();
                List<Viewmodel> secanViewlist = new ArrayList<>();

                List<Viewmodel> view1 = new ArrayList<>();
                List<Viewmodel> view2 = new ArrayList<>();
                List<Viewmodel> view3 = new ArrayList<>();
                List<Viewmodel> view4 = new ArrayList<>();
                List<Viewmodel> view5 = new ArrayList<>();
                List<Viewmodel> view6 = new ArrayList<>();

                if (oldlistone != null && oldlistone.size() > 0) {
                    for (RoleViewmodelEntity viewone : oldlistone) {
                        Viewmodel viewmodelnew = new Viewmodel();
                        viewmodelnew.setChildFlag(viewone.getChildFlag());
                        viewmodelnew.setMenuDescription(viewone.getMenuDescription());
                        viewmodelnew.setMenuId(viewone.getMenuId());
                        viewmodelnew.setMenulevel(viewone.getMenulevel());
                        viewmodelnew.setMenuName(viewone.getMenuName());
                        viewmodelnew.setMenuorder(viewone.getMenuorder());
                        viewmodelnew.setMenuState(viewone.getMenuState());
                        viewmodelnew.setOperator(viewone.getOperator());
                        viewmodelnew.setOwner(viewone.getOwner());
                        viewmodelnew.setParantmenuid(viewone.getParantmenuid());
                        viewmodelnew.setRunscript(viewone.getRunscript());
                        viewmodelnew.setBelong(viewone.getBelong());
                        viewmodelList.add(viewmodelnew);
                        switch (viewone.getBelong()) {
                            case "1":
                                view1.add(viewmodelnew);
                            case "2":
                                view2.add(viewmodelnew);
                            case "3":
                                view3.add(viewmodelnew);
                            case "4":
                                view4.add(viewmodelnew);
                            case "5":
                                view5.add(viewmodelnew);

                        }
                    }

                    if (oldlisttwo != null && oldlisttwo.size() > 0) {
                        for (RoleViewmodelEntity viewone2 : oldlisttwo) {
                            Viewmodel viewmodelnew2 = new Viewmodel();
                            viewmodelnew2.setChildFlag(viewone2.getChildFlag());
                            viewmodelnew2.setMenuDescription(viewone2.getMenuDescription());
                            viewmodelnew2.setMenuId(viewone2.getMenuId());
                            viewmodelnew2.setMenulevel(viewone2.getMenulevel());
                            viewmodelnew2.setMenuName(viewone2.getMenuName());
                            viewmodelnew2.setMenuorder(viewone2.getMenuorder());
                            viewmodelnew2.setMenuState(viewone2.getMenuState());
                            viewmodelnew2.setOperator(viewone2.getOperator());
                            viewmodelnew2.setOwner(viewone2.getOwner());
                            viewmodelnew2.setParantmenuid(viewone2.getParantmenuid());
                            viewmodelnew2.setRunscript(viewone2.getRunscript());
                            secanViewlist.add(viewmodelnew2);
                        }
                    }
                }

                mav.addObject("menulist", viewmodelList);
                mav.addObject("secanViewlist", secanViewlist);

                String tabValue = "";

                if (view1 != null && view1.size() > 0) {
                    tabValue = "1";
                } else if (view2 != null && view2.size() > 0) {
                    tabValue = "2";
                } else if (view3 != null && view3.size() > 0) {
                    tabValue = "3";
                } else if (view4 != null && view4.size() > 0) {
                    tabValue = "4";
                } else if (view5 != null && view5.size() > 0) {
                    tabValue = "5";
                }

                Cookie cookie = new Cookie("tabValue", tabValue);
                cookie.setPath("/");
                cookie.setDomain(request.getServerName());
                cookie.setMaxAge(3600 * 24 * 30);
                response.addCookie(cookie);
                response.setHeader("P3P", "CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");

                try {
                    //添加日志
                    QualityLogEntity qualityLogEntity = new QualityLogEntity();
                    qualityLogEntity.setLogId(IdGen.uuid());//id
                    qualityLogEntity.setUserName(rePropertystaff.getUserName());//用户名
                    qualityLogEntity.setStaffName(rePropertystaff.getStaffName());//姓名
                    qualityLogEntity.setUserMobile(rePropertystaff.getMobile());//手机号
                    qualityLogEntity.setSourceFrom("Web");//来源
                    qualityLogEntity.setIpAddress(IpUtils.getIpAddress(request));//ip地址
                    String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录成功";
                    qualityLogEntity.setSysContent(content);//登录内容
                    qualityLogEntity.setSysTime(new Date());//访问时间
                    qualityLogService.addQualityLogInfo(qualityLogEntity);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("---------->>Create login log exception.");
                }

            }else if("st".equals(checkLogin) && reUserInformationEntity != null){  //安全
//                rePropertystaff.setPassword(""); ///置空密码
                mav.addObject("propertystaff", rePropertystaff);

                reUserInformationEntity.setPassword(""); ///置空密码
                mav.addObject("propertystaff", reUserInformationEntity);

                List<Viewmodel> viewmodelList = new ArrayList<>();
                List<Viewmodel> secanViewlist = new ArrayList<>();
                //-----------查询角色权限 ---取出的是第一級
                List<RoleViewmodelEntity> authlistone=new ArrayList<>();
                List<String> newViewOneIdList=authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(),"2","3");
                if(newViewOneIdList!=null && newViewOneIdList.size()>0){
                    authlistone = roleViewmodelService.getAuthRoleViewByIdList(newViewOneIdList,"3");
                }
                //-----------查询角色权限 ---取出的是第二級
                List<RoleViewmodelEntity> authlisttwo=new ArrayList<>();
                List<String> newViewTwoIdList=authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(),"3","3");
                if(newViewTwoIdList!=null && newViewTwoIdList.size()>0){
                    authlisttwo = roleViewmodelService.getAuthRoleViewByIdList(newViewTwoIdList,"3");
                }
                if (authlistone != null && authlistone.size() > 0) {
                    for (RoleViewmodelEntity viewone : authlistone) {
                        Viewmodel viewmodelnew = new Viewmodel();
                        viewmodelnew.setChildFlag(viewone.getChildFlag());
                        viewmodelnew.setMenuDescription(viewone.getMenuDescription());
                        viewmodelnew.setMenuId(viewone.getMenuId());
                        viewmodelnew.setMenulevel(viewone.getMenulevel());
                        viewmodelnew.setMenuName(viewone.getMenuName());
                        viewmodelnew.setMenuorder(viewone.getMenuorder());
                        viewmodelnew.setMenuState(viewone.getMenuState());
                        viewmodelnew.setOperator(viewone.getOperator());
                        viewmodelnew.setOwner(viewone.getOwner());
                        viewmodelnew.setParantmenuid(viewone.getParantmenuid());
                        viewmodelnew.setRunscript(viewone.getRunscript());
                        viewmodelnew.setBelong(viewone.getBelong());
                        viewmodelList.add(viewmodelnew);
                    }

                    if (authlisttwo != null && authlisttwo.size() > 0) {
                        for (RoleViewmodelEntity viewone2 : authlisttwo) {
                            Viewmodel viewmodelnew2 = new Viewmodel();
                            viewmodelnew2.setChildFlag(viewone2.getChildFlag());
                            viewmodelnew2.setMenuDescription(viewone2.getMenuDescription());
                            viewmodelnew2.setMenuId(viewone2.getMenuId());
                            viewmodelnew2.setMenulevel(viewone2.getMenulevel());
                            viewmodelnew2.setMenuName(viewone2.getMenuName());
                            viewmodelnew2.setMenuorder(viewone2.getMenuorder());
                            viewmodelnew2.setMenuState(viewone2.getMenuState());
                            viewmodelnew2.setOperator(viewone2.getOperator());
                            viewmodelnew2.setOwner(viewone2.getOwner());
                            viewmodelnew2.setParantmenuid(viewone2.getParantmenuid());
                            viewmodelnew2.setRunscript(viewone2.getRunscript());
                            secanViewlist.add(viewmodelnew2);
                        }
                    }
                }

                mav.addObject("menulist", viewmodelList);
                mav.addObject("secanViewlist", secanViewlist);
                String tabValue = "6";
                Cookie cookie = new Cookie("tabValue", tabValue);
                cookie.setPath("/");
                cookie.setDomain(request.getServerName());
                cookie.setMaxAge(3600 * 24 * 30);
                response.addCookie(cookie);
                response.setHeader("P3P", "CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");
            }else if("es".equals(checkLogin) && reUserInformationEntity != null){ //工程
                mav.addObject("propertystaff", rePropertystaff);

                reUserInformationEntity.setPassword(""); ///置空密码
                mav.addObject("propertystaff", reUserInformationEntity);

                List<Viewmodel> viewmodelList = new ArrayList<>();
                List<Viewmodel> secanViewlist = new ArrayList<>();
                //-----------查询角色权限 ---取出的是第一級
                List<RoleViewmodelEntity> authlistone=new ArrayList<>();
                List<String> newViewOneIdList=authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(),"2","2");
                if(newViewOneIdList!=null && newViewOneIdList.size()>0){
                    authlistone = roleViewmodelService.getAuthRoleViewByIdList(newViewOneIdList,"2");
                }
                //-----------查询角色权限 ---取出的是第二級
                List<RoleViewmodelEntity> authlisttwo=new ArrayList<>();
                List<String> newViewTwoIdList=authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(),"3","2");
                if(newViewTwoIdList!=null && newViewTwoIdList.size()>0){
                    authlisttwo = roleViewmodelService.getAuthRoleViewByIdList(newViewTwoIdList,"2");
                }
                if (authlistone != null && authlistone.size() > 0) {
                    for (RoleViewmodelEntity viewone : authlistone) {
                        Viewmodel viewmodelnew = new Viewmodel();
                        viewmodelnew.setChildFlag(viewone.getChildFlag());
                        viewmodelnew.setMenuDescription(viewone.getMenuDescription());
                        viewmodelnew.setMenuId(viewone.getMenuId());
                        viewmodelnew.setMenulevel(viewone.getMenulevel());
                        viewmodelnew.setMenuName(viewone.getMenuName());
                        viewmodelnew.setMenuorder(viewone.getMenuorder());
                        viewmodelnew.setMenuState(viewone.getMenuState());
                        viewmodelnew.setOperator(viewone.getOperator());
                        viewmodelnew.setOwner(viewone.getOwner());
                        viewmodelnew.setParantmenuid(viewone.getParantmenuid());
                        viewmodelnew.setRunscript(viewone.getRunscript());
                        viewmodelnew.setBelong(viewone.getBelong());
                        viewmodelList.add(viewmodelnew);
                    }

                    if (authlisttwo != null && authlisttwo.size() > 0) {
                        for (RoleViewmodelEntity viewone2 : authlisttwo) {
                            Viewmodel viewmodelnew2 = new Viewmodel();
                            viewmodelnew2.setChildFlag(viewone2.getChildFlag());
                            viewmodelnew2.setMenuDescription(viewone2.getMenuDescription());
                            viewmodelnew2.setMenuId(viewone2.getMenuId());
                            viewmodelnew2.setMenulevel(viewone2.getMenulevel());
                            viewmodelnew2.setMenuName(viewone2.getMenuName());
                            viewmodelnew2.setMenuorder(viewone2.getMenuorder());
                            viewmodelnew2.setMenuState(viewone2.getMenuState());
                            viewmodelnew2.setOperator(viewone2.getOperator());
                            viewmodelnew2.setOwner(viewone2.getOwner());
                            viewmodelnew2.setParantmenuid(viewone2.getParantmenuid());
                            viewmodelnew2.setRunscript(viewone2.getRunscript());
                            secanViewlist.add(viewmodelnew2);
                        }
                    }
                }

                mav.addObject("menulist", viewmodelList);
                mav.addObject("secanViewlist", secanViewlist);
                String tabValue = "2";
                Cookie cookie = new Cookie("tabValue", tabValue);
                cookie.setPath("/");
                cookie.setDomain(request.getServerName());
                cookie.setMaxAge(3600 * 24 * 30);
                response.addCookie(cookie);
                response.setHeader("P3P", "CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");
            } else if("qc".equals(checkLogin) && reUserInformationEntity != null){ //客关
                mav.addObject("propertystaff", rePropertystaff);

                reUserInformationEntity.setPassword(""); ///置空密码
                mav.addObject("propertystaff", reUserInformationEntity);

                List<Viewmodel> viewmodelList = new ArrayList<>();
                List<Viewmodel> secanViewlist = new ArrayList<>();
                //-----------查询角色权限 ---取出的是第一級
                List<RoleViewmodelEntity> authlistone=new ArrayList<>();
                List<String> newViewOneIdList=authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(),"2","1");
                if(newViewOneIdList!=null && newViewOneIdList.size()>0){
                    authlistone = roleViewmodelService.getAuthRoleViewByIdList(newViewOneIdList,"1");
                }
                //-----------查询角色权限 ---取出的是第二級
                List<RoleViewmodelEntity> authlisttwo=new ArrayList<>();
                List<String> newViewTwoIdList=authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(),"3","1");
                if(newViewTwoIdList!=null && newViewTwoIdList.size()>0){
                    authlisttwo = roleViewmodelService.getAuthRoleViewByIdList(newViewTwoIdList,"1");
                }
                if (authlistone != null && authlistone.size() > 0) {
                    for (RoleViewmodelEntity viewone : authlistone) {
                        Viewmodel viewmodelnew = new Viewmodel();
                        viewmodelnew.setChildFlag(viewone.getChildFlag());
                        viewmodelnew.setMenuDescription(viewone.getMenuDescription());
                        viewmodelnew.setMenuId(viewone.getMenuId());
                        viewmodelnew.setMenulevel(viewone.getMenulevel());
                        viewmodelnew.setMenuName(viewone.getMenuName());
                        viewmodelnew.setMenuorder(viewone.getMenuorder());
                        viewmodelnew.setMenuState(viewone.getMenuState());
                        viewmodelnew.setOperator(viewone.getOperator());
                        viewmodelnew.setOwner(viewone.getOwner());
                        viewmodelnew.setParantmenuid(viewone.getParantmenuid());
                        viewmodelnew.setRunscript(viewone.getRunscript());
                        viewmodelnew.setBelong(viewone.getBelong());
                        viewmodelList.add(viewmodelnew);
                    }

                    if (authlisttwo != null && authlisttwo.size() > 0) {
                        for (RoleViewmodelEntity viewone2 : authlisttwo) {
                            Viewmodel viewmodelnew2 = new Viewmodel();
                            viewmodelnew2.setChildFlag(viewone2.getChildFlag());
                            viewmodelnew2.setMenuDescription(viewone2.getMenuDescription());
                            viewmodelnew2.setMenuId(viewone2.getMenuId());
                            viewmodelnew2.setMenulevel(viewone2.getMenulevel());
                            viewmodelnew2.setMenuName(viewone2.getMenuName());
                            viewmodelnew2.setMenuorder(viewone2.getMenuorder());
                            viewmodelnew2.setMenuState(viewone2.getMenuState());
                            viewmodelnew2.setOperator(viewone2.getOperator());
                            viewmodelnew2.setOwner(viewone2.getOwner());
                            viewmodelnew2.setParantmenuid(viewone2.getParantmenuid());
                            viewmodelnew2.setRunscript(viewone2.getRunscript());
                            secanViewlist.add(viewmodelnew2);
                        }
                    }
                }

                mav.addObject("menulist", viewmodelList);
                mav.addObject("secanViewlist", secanViewlist);
                String tabValue = "3";
                Cookie cookie = new Cookie("tabValue", tabValue);
                cookie.setPath("/");
                cookie.setDomain(request.getServerName());
                cookie.setMaxAge(3600 * 24 * 30);
                response.addCookie(cookie);
                response.setHeader("P3P", "CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");
            }else {
                mav = new ModelAndView("/error/500");
                mav.addObject("errormsg", "用户名或密码错误");
                /**
                 * 添加日志记录
                 */
                QualityLogEntity qualityLogEntity = new QualityLogEntity();
                qualityLogEntity.setLogId(IdGen.uuid());//id
                qualityLogEntity.setUserName(propertystaff.getUserName());//用户名
                qualityLogEntity.setSourceFrom("Web");//来源
                qualityLogEntity.setIpAddress(IpUtils.getIpAddress(request));//ip地址
                String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录失败";
                qualityLogEntity.setSysContent(content);//登录内容
                qualityLogEntity.setSysTime(new Date());//访问时间
                qualityLogService.addQualityLogInfo(qualityLogEntity);
            }
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/authLoginCheck.html", method = RequestMethod.POST)
    public ModelAndView authLoginCheck(HttpServletRequest request, HttpServletResponse response) {
        //默认设置 ---系统中如果拥有商城管理权限的用户会再拥有一个商户角色用户 id 与物业用户相同

        String userid = request.getParameter("userId");
        String old_pwd = request.getParameter("password");

        String pwd = PasswordEncode.digestPassword(old_pwd);
        ModelAndView mav;
        mav = new ModelAndView("/main/main");
        try {
            //查询物业人员 会员适用登录接口表
            UserPropertyStaffEntity propertystaff = new UserPropertyStaffEntity();
            propertystaff.setStaffId(userid);
            propertystaff.setUserName(userid);
            propertystaff.setPassword(pwd);
            UserPropertyStaffEntity rePropertystaff = userPropertystaffService.CheckStaffByIdAPwd(propertystaff);

            List<Viewmodel> viewmodelList = new ArrayList<>();
            List<Viewmodel> secanViewlist = new ArrayList<>();
            List<Viewmodel> view1 = new ArrayList<>();
            List<Viewmodel> view2 = new ArrayList<>();
            List<Viewmodel> view3 = new ArrayList<>();
            List<Viewmodel> view4 = new ArrayList<>();
            List<Viewmodel> view5 = new ArrayList<>();
            List<Viewmodel> view6 = new ArrayList<>();
            List<RoleViewmodelEntity> autOneRoleView = new ArrayList<>();
            List<RoleViewmodelEntity> autTwoRoleView = new ArrayList<>();
            if (rePropertystaff != null) {
                rePropertystaff.setPassword(""); ///置空密码
                mav.addObject("propertystaff", rePropertystaff);
                List<RoleViewmodelEntity> oldlistone = roleViewmodelService.getAuthViewListByUserId("property", rePropertystaff.getStaffId());
                if (oldlistone != null && oldlistone.size() > 0) {
                    autOneRoleView.addAll(oldlistone);
                }
                //獲取除一級外的其他菜單
                List<RoleViewmodelEntity> oldlisttwo = roleViewmodelService.getAuthViewListOtherByUserId("property", rePropertystaff.getStaffId());
                if (oldlisttwo != null && oldlisttwo.size() > 0) {
                    autTwoRoleView.addAll(oldlisttwo);
                }
            }

            /**********************以下新权限修改  客关 工程  安全 *************************************/
            UserInformationEntity userInformationEntity = new UserInformationEntity();
            userInformationEntity.setStaffId(userid);
            userInformationEntity.setSysName(userid);
            userInformationEntity.setPassword(pwd);
            UserInformationEntity reUserInformationEntity = userPropertystaffService.CheckAuthUserInforByIdAPwd(userInformationEntity);
            if (reUserInformationEntity != null) {
                reUserInformationEntity.setPassword(""); ///置空密码
//                mav.addObject("authPropertystaff", reUserInformationEntity);
                request.getSession().setAttribute("authPropertystaff", reUserInformationEntity);

                // 客关校验
                if (userPropertystaffService.getCheckUserMapById(reUserInformationEntity.getStaffId(), "qc")) {
                    List<RoleViewmodelEntity> authlistone = new ArrayList<>();
                    List<String> newViewOneIdList = authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(), "2", "1");
                    if (newViewOneIdList != null && newViewOneIdList.size() > 0) {
                        authlistone = roleViewmodelService.getAuthRoleViewByIdList(newViewOneIdList, "1");
                        autOneRoleView.addAll(authlistone);
                    }
                    //-----------查询角色权限 ---取出的是第二級
                    List<RoleViewmodelEntity> authlisttwo = new ArrayList<>();
                    List<String> newViewTwoIdList = authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(), "3", "1");
                    if (newViewTwoIdList != null && newViewTwoIdList.size() > 0) {
                        authlisttwo = roleViewmodelService.getAuthRoleViewByIdList(newViewTwoIdList, "1");
                        autTwoRoleView.addAll(authlisttwo);
                    }
                }
                //工程校验
                if (userPropertystaffService.getCheckUserMapById(reUserInformationEntity.getStaffId(), "es")) {
                    List<RoleViewmodelEntity> authlistone = new ArrayList<>();
                    List<String> newViewOneIdList = authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(), "2", "2");
                    if (newViewOneIdList != null && newViewOneIdList.size() > 0) {
                        authlistone = roleViewmodelService.getAuthRoleViewByIdList(newViewOneIdList, "2");
                        autOneRoleView.addAll(authlistone);
                    }
                    //-----------查询角色权限 ---取出的是第二級
                    List<RoleViewmodelEntity> authlisttwo = new ArrayList<>();
                    List<String> newViewTwoIdList = authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(), "3", "2");
                    if (newViewTwoIdList != null && newViewTwoIdList.size() > 0) {
                        authlisttwo = roleViewmodelService.getAuthRoleViewByIdList(newViewTwoIdList, "2");
                        autTwoRoleView.addAll(authlisttwo);
                    }
                }
                //安全校验
                if (userPropertystaffService.getCheckUserMapById(reUserInformationEntity.getStaffId(), "st")) {
                    List<RoleViewmodelEntity> authlistone = new ArrayList<>();
                    List<String> newViewOneIdList = authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(), "2", "3");
                    if (newViewOneIdList != null && newViewOneIdList.size() > 0) {
                        authlistone = roleViewmodelService.getAuthRoleViewByIdList(newViewOneIdList, "3");
                        autOneRoleView.addAll(authlistone);
                    }
                    //-----------查询角色权限 ---取出的是第二級
                    List<RoleViewmodelEntity> authlisttwo = new ArrayList<>();
                    List<String> newViewTwoIdList = authAgencyService.getNewVimList(reUserInformationEntity.getStaffId(), "3", "3");
                    if (newViewTwoIdList != null && newViewTwoIdList.size() > 0) {
                        authlisttwo = roleViewmodelService.getAuthRoleViewByIdList(newViewTwoIdList, "3");
                        autTwoRoleView.addAll(authlisttwo);
                    }
                }
            }

            if (autOneRoleView != null && autOneRoleView.size() > 0) {
                for (RoleViewmodelEntity viewone : autOneRoleView) {
                    Viewmodel viewmodelnew = new Viewmodel();
                    viewmodelnew.setChildFlag(viewone.getChildFlag());
                    viewmodelnew.setMenuDescription(viewone.getMenuDescription());
                    viewmodelnew.setMenuId(viewone.getMenuId());
                    viewmodelnew.setMenulevel(viewone.getMenulevel());
                    viewmodelnew.setMenuName(viewone.getMenuName());
                    viewmodelnew.setMenuorder(viewone.getMenuorder());
                    viewmodelnew.setMenuState(viewone.getMenuState());
                    viewmodelnew.setOperator(viewone.getOperator());
                    viewmodelnew.setOwner(viewone.getOwner());
                    viewmodelnew.setParantmenuid(viewone.getParantmenuid());
                    viewmodelnew.setRunscript(viewone.getRunscript());
                    viewmodelnew.setBelong(viewone.getBelong());
                    viewmodelList.add(viewmodelnew);
                    switch (viewone.getBelong()) {
                        case "1":
                            view1.add(viewmodelnew);
                        case "2":
                            view2.add(viewmodelnew);
                        case "3":
                            view3.add(viewmodelnew);
                        case "4":
                            view4.add(viewmodelnew);
                        case "5":
                            view5.add(viewmodelnew);
                        case "6":
                            view6.add(viewmodelnew);
                    }
                }

                if (autTwoRoleView != null && autTwoRoleView.size() > 0) {
                    for (RoleViewmodelEntity viewone2 : autTwoRoleView) {
                        Viewmodel viewmodelnew2 = new Viewmodel();
                        viewmodelnew2.setChildFlag(viewone2.getChildFlag());
                        viewmodelnew2.setMenuDescription(viewone2.getMenuDescription());
                        viewmodelnew2.setMenuId(viewone2.getMenuId());
                        viewmodelnew2.setMenulevel(viewone2.getMenulevel());
                        viewmodelnew2.setMenuName(viewone2.getMenuName());
                        viewmodelnew2.setMenuorder(viewone2.getMenuorder());
                        viewmodelnew2.setMenuState(viewone2.getMenuState());
                        viewmodelnew2.setOperator(viewone2.getOperator());
                        viewmodelnew2.setOwner(viewone2.getOwner());
                        viewmodelnew2.setParantmenuid(viewone2.getParantmenuid());
                        viewmodelnew2.setRunscript(viewone2.getRunscript());
                        secanViewlist.add(viewmodelnew2);
                    }
                }
            }

            mav.addObject("menulist", viewmodelList);
            mav.addObject("secanViewlist", secanViewlist);

            String tabValue = "";

            if (view1 != null && view1.size() > 0) {
                tabValue = "1";
            } else if (view2 != null && view2.size() > 0) {
                tabValue = "2";
            } else if (view3 != null && view3.size() > 0) {
                tabValue = "3";
            } else if (view4 != null && view4.size() > 0) {
                tabValue = "4";
            } else if (view5 != null && view5.size() > 0) {
                tabValue = "5";
            } else if (view6 != null && view6.size() > 0) {
                tabValue = "6";
            }

            Cookie cookie = new Cookie("tabValue", tabValue);
            cookie.setPath("/");
            cookie.setDomain(request.getServerName());
            cookie.setMaxAge(3600 * 24 * 30);
            response.addCookie(cookie);
            response.setHeader("P3P", "CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");
            if(rePropertystaff == null && reUserInformationEntity ==null){
                mav = new ModelAndView("/error/500");
                mav.addObject("errormsg", "用户名或密码错误");
                /**
                 * 添加日志记录
                 */
                QualityLogEntity qualityLogEntity = new QualityLogEntity();
                qualityLogEntity.setLogId(IdGen.uuid());//id
                qualityLogEntity.setUserName(propertystaff.getUserName());//用户名
                qualityLogEntity.setSourceFrom("Web");//来源
                qualityLogEntity.setIpAddress(IpUtils.getIpAddress(request));//ip地址
                String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录失败";
                qualityLogEntity.setSysContent(content);//登录内容
                qualityLogEntity.setSysTime(new Date());//访问时间
                qualityLogService.addQualityLogInfo(qualityLogEntity);
            }
            try {
                //添加日志
                if(rePropertystaff!=null){
                    QualityLogEntity qualityLogEntity = new QualityLogEntity();
                    qualityLogEntity.setLogId(IdGen.uuid());//id
                    qualityLogEntity.setUserName(rePropertystaff.getUserName());//用户名
                    qualityLogEntity.setStaffName(rePropertystaff.getStaffName());//姓名
                    qualityLogEntity.setUserMobile(rePropertystaff.getMobile());//手机号
                    qualityLogEntity.setSourceFrom("Web");//来源
                    qualityLogEntity.setIpAddress(IpUtils.getIpAddress(request));//ip地址
                    String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录成功";
                    qualityLogEntity.setSysContent(content);//登录内容
                    qualityLogEntity.setSysTime(new Date());//访问时间
                    qualityLogService.addQualityLogInfo(qualityLogEntity);
                }
                if(reUserInformationEntity!=null){
                    QualityLogEntity qualityLogEntity = new QualityLogEntity();
                    qualityLogEntity.setLogId(IdGen.uuid());//id
                    qualityLogEntity.setUserName(reUserInformationEntity.getUserName());//用户名
                    qualityLogEntity.setStaffName(reUserInformationEntity.getStaffName());//姓名
                    qualityLogEntity.setUserMobile(reUserInformationEntity.getMobile());//手机号
                    qualityLogEntity.setSourceFrom("Web");//来源
                    qualityLogEntity.setIpAddress(IpUtils.getIpAddress(request));//ip地址
                    String content = qualityLogEntity.getUserName() + "在" + DateUtils.format(new Date()) + "登录成功";
                    qualityLogEntity.setSysContent(content);//登录内容
                    qualityLogEntity.setSysTime(new Date());//访问时间
                    qualityLogService.addQualityLogInfo(qualityLogEntity);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("---------->>Create login log exception.");
            }
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }











    @RequestMapping(value = "/gotoSSOCheck.html", method = RequestMethod.GET)
    public ModelAndView gotoSSOCheckGet(HttpServletRequest request) {
        return gotoSSOCheck(request);
    }

    @RequestMapping(value = "/gotoSSOCheck.html", method = RequestMethod.POST)
    public ModelAndView gotoSSOCheck(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("/main/main");

        //默认设置 ---系统中如果拥有商城管理权限的用户会再拥有一个商户角色用户 id 与物业用户相同
        final Cookie[] cookies = request.getCookies();
        String staffId = "";
        if (cookies != null) {
            for (Cookie item : cookies) {
                String name = item.getName();
                if (StringUtil.isEqual(name, "AuthUser_LoginId")) {
                    staffId = item.getValue();
                }
            }
        }
//        staffId = "guoyan10";
        if (StringUtil.isEmpty(staffId)) {
            mav = new ModelAndView("/error/500");
            mav.addObject("errormsg", "未找到该用户");
            System.out.println("staffId is null");
        }

        System.out.println("staffId--->>" + staffId);

        try {

            //物业人员
            UserPropertyStaffEntity rePropertystaff = userPropertystaffService.getByUserName(staffId);


            if (rePropertystaff != null) {
                rePropertystaff.setPassword(""); ///置空密码
                mav.addObject("propertystaff", rePropertystaff);


                //-----------查询角色权限 ---取出的是第一級

                List<RoleViewmodelEntity> oldlistone = roleViewmodelService.getViewListByUserId("property", staffId);
                //獲取除一級外的其他菜單
                List<RoleViewmodelEntity> oldlisttwo = roleViewmodelService.getViewListOtherByUserId("property", staffId);

                List<Viewmodel> viewmodelList = new ArrayList<>();
                List<Viewmodel> secanViewlist = new ArrayList<>();

                if (oldlistone != null && oldlistone.size() > 0) {

                    for (RoleViewmodelEntity viewone : oldlistone) {
                        Viewmodel viewmodelnew = new Viewmodel();
                        viewmodelnew.setChildFlag(viewone.getChildFlag());
                        viewmodelnew.setMenuDescription(viewone.getMenuDescription());
                        viewmodelnew.setMenuId(viewone.getMenuId());
                        viewmodelnew.setMenulevel(viewone.getMenulevel());
                        viewmodelnew.setMenuName(viewone.getMenuName());
                        viewmodelnew.setMenuorder(viewone.getMenuorder());
                        viewmodelnew.setMenuState(viewone.getMenuState());
                        viewmodelnew.setOperator(viewone.getOperator());
                        viewmodelnew.setOwner(viewone.getOwner());
                        viewmodelnew.setParantmenuid(viewone.getParantmenuid());
                        viewmodelnew.setRunscript(viewone.getRunscript());
                        viewmodelList.add(viewmodelnew);
                    }

                    if (oldlisttwo != null && oldlisttwo.size() > 0) {
                        for (RoleViewmodelEntity viewone2 : oldlisttwo) {
                            Viewmodel viewmodelnew2 = new Viewmodel();
                            viewmodelnew2.setChildFlag(viewone2.getChildFlag());
                            viewmodelnew2.setMenuDescription(viewone2.getMenuDescription());
                            viewmodelnew2.setMenuId(viewone2.getMenuId());
                            viewmodelnew2.setMenulevel(viewone2.getMenulevel());
                            viewmodelnew2.setMenuName(viewone2.getMenuName());
                            viewmodelnew2.setMenuorder(viewone2.getMenuorder());
                            viewmodelnew2.setMenuState(viewone2.getMenuState());
                            viewmodelnew2.setOperator(viewone2.getOperator());
                            viewmodelnew2.setOwner(viewone2.getOwner());
                            viewmodelnew2.setParantmenuid(viewone2.getParantmenuid());
                            viewmodelnew2.setRunscript(viewone2.getRunscript());
                            secanViewlist.add(viewmodelnew2);
                        }
                    }
                }

                mav.addObject("menulist", viewmodelList);
                mav.addObject("secanViewlist", secanViewlist);
            } else {
                mav = new ModelAndView("/error/500");
                mav.addObject("errormsg", "未找到该用户");
            }
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //可以在此请求吗？还用重启
    @RequestMapping(value = "/logOut.html")
    public String logoutUser(ModelMap model, SessionStatus sessionStatus) {

        sessionStatus.setComplete();

        return "/welcome";
    }


    /**
     * 菜单session传递
     */
    @RequestMapping(value = "/getViewSession", method = RequestMethod.GET)
    public ApiResult getViewSession(@ModelAttribute("menulist") List<Viewmodel> viewmodels) {
        return new SuccessApiResult(viewmodels);
    }

    /**
     * 保存tab页active值
     */
    @RequestMapping(value = "saveTab", method = RequestMethod.GET)
    public ApiResult saveTab(@RequestParam(value = "tabValue") String tabValue, HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = new Cookie("tabValue", tabValue);
        cookie.setPath("/");
        cookie.setDomain(request.getServerName());
        cookie.setMaxAge(3600 * 24 * 30);
        response.addCookie(cookie);
        response.setHeader("P3P", "CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");
        return new SuccessApiResult();
    }

    /**
     * 获取tab页active值
     */
    @RequestMapping(value = "getTab", method = RequestMethod.GET)
    public ApiResult getTabValue(@CookieValue(value = "tabValue", required = false) String tabValue) {
        System.out.println(tabValue);
        return new SuccessApiResult(tabValue);
    }
}
