package com.maxrocky.vesta.presistent.repositoryHbnImpl.init;

import com.maxrocky.vesta.domain.model.SystemConfigEntity;
//import com.maxrocky.vesta.sso.SsoClientUtils;
import com.maxrocky.vesta.utility.AppConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/17 14:10.
 * Describe:初始化系统配置参数
 */
@Component
public class AuthSystemConfig {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Bean初始化方法
     */
    public void init(){
        System.out.println("SystemConfig init()");

        System.out.println("------------------- start AutoRunController.init() -----------------");

        List<SystemConfigEntity> systemConfigEntityList = null;

        try {
            System.out.println("------------------- select SystemConfig from database -----------------");

            String hql = "FROM SystemConfigEntity";
            systemConfigEntityList = getCurrentSession().createQuery(hql).list();

            if(systemConfigEntityList == null){
                System.out.println("------------------- SystemConfig is null -----------------");
            }

            System.out.println("------------------- SystemConfig.size() is " + systemConfigEntityList.size() + "-----------------");

        }
        catch (Exception ex){
            System.out.println("------------------- select SystemConfig from database is Exception -----------------");
            ex.printStackTrace();
        }

        try {
            System.out.println("-------------------start SystemConfig to utility.SystemConfig -----------------");

            for (SystemConfigEntity systemConfigEntity : systemConfigEntityList){

                System.out.println("------------------- config_code is " + systemConfigEntity.getConfigCode() + " -----------------");
                System.out.println("------------------- config_value is " + systemConfigEntity.getConfigValue() + " -----------------");

                switch (systemConfigEntity.getConfigCode()){
                    case "Cookie_Domain":
                        AppConfig.Cookie_Domain = systemConfigEntity.getConfigValue();
                        break;
                    case "UserDefaultLogo":
                        AppConfig.UserDefaultLogo = systemConfigEntity.getConfigValue();
                        break;
                    case "uploadAdvertImage":
                        AppConfig.ADVERT = systemConfigEntity.getConfigValue();
                        break;
                    case "ServiceInterfacePath":
                        AppConfig.SERVICEINTERFACEPATH = systemConfigEntity.getConfigValue();
                        break;
                    case "ServicePath":
                        AppConfig.SERVICEPATH = systemConfigEntity.getConfigValue();
                        break;
                    case "Repairs":
                        AppConfig.REPAIRS = systemConfigEntity.getConfigValue();
                        break;
                    case "PhotoGraph":
                        AppConfig.PHOTOGRAPH = systemConfigEntity.getConfigValue();
                        break;
                    case "AccessInterfacePath":
                        AppConfig.INTERFACEPATH= systemConfigEntity.getConfigValue();
                        break;
                    case "AccessPath":
                        AppConfig.PATH= systemConfigEntity.getConfigValue();
                        break;
                    case "SSOClientLoginPath":
                        AppConfig.SSO_CLIENT_LOGIN_PATH = systemConfigEntity.getConfigValue();
                        break;
                    case "SSOSystemCode":
                        AppConfig.SSO_SYSTEM_CODE = systemConfigEntity.getConfigValue();
//                        SsoClientUtils.SYSTEM_CODE = systemConfigEntity.getConfigValue();
                        break;
//                    case "SSOKey":
//                        SsoClientUtils.KEY = systemConfigEntity.getConfigValue();
//                        break;
                    case "SMSFromSystem":
                        AppConfig.SMS_FROM_SYSTEM = systemConfigEntity.getConfigValue();
                        break;
                    case "SMSCheckFlag":
                        AppConfig.SMS_CHECK_FLAG = systemConfigEntity.getConfigValue();
                        break;
                    case "SMSCheckDomain":
                        AppConfig.SMS_CHECK_DOMAIN = systemConfigEntity.getConfigValue();
                        break;
                    case "LogoPath":
                        AppConfig.LOGORAPH= systemConfigEntity.getConfigValue();
                        break;
                    case "DefaultProjectId":
                        AppConfig.DEFAULT_PROJECT_ID= systemConfigEntity.getConfigValue();
                        break;
                    case "DefaultProjectName":
                        AppConfig.DEFAULT_PROJECT_NAME= systemConfigEntity.getConfigValue();
                        break;
                    case "activity":
                        AppConfig.ACTIVITY= systemConfigEntity.getConfigValue();
                        break;
                    case "sharingactivity":
                        AppConfig.SHARINGACTIVITY= systemConfigEntity.getConfigValue();
                        break;
                    case "merchant":
                        AppConfig.MERCHANT= systemConfigEntity.getConfigValue();
                        break;
                    case "house":
                        AppConfig.HOUSE= systemConfigEntity.getConfigValue();
                        break;
                    case "say":
                        AppConfig.SAY= systemConfigEntity.getConfigValue();
                        break;
                    case "feedback":
                        AppConfig.FEEDBACK= systemConfigEntity.getConfigValue();
                        break;
                    case "topic":
                        AppConfig.TOPIC= systemConfigEntity.getConfigValue();
                        break;
                    case "startPage":
                        AppConfig.STARTPAGE= systemConfigEntity.getConfigValue();
                        break;
                    case "details":
                        AppConfig.DETAILS= systemConfigEntity.getConfigValue();
                        break;
                    case "Integration":
                        AppConfig.INTEGRATION= systemConfigEntity.getConfigValue();
                        break;
                    case ".1image_domain":
                        AppConfig.image_domain = systemConfigEntity.getConfigValue();
                        break;
                    case "pay_call_url":
                        AppConfig.payingCallUrl = systemConfigEntity.getConfigValue();
                        break;
                    case "default_image_club":
                        AppConfig.default_image.club_path = AppConfig.image_domain +"/"+ systemConfigEntity.getConfigValue();
                        System.out.println(" AppConfig.image_domain="+AppConfig.image_domain);
                        System.out.println(" AppConfig.default_image.club_path="+AppConfig.default_image.club_path);
                        break;
                    case "defalut_image_community":
                        AppConfig.default_image.community_path = AppConfig.image_domain +"/"+ systemConfigEntity.getConfigValue();
                        System.out.println(" AppConfig.image_domain="+AppConfig.image_domain);
                        System.out.println(" AppConfig.default_image.club_path="+ AppConfig.default_image.community_path);
                        break;

                    //下面是张洪健的消息推送
                    case "userApiKey":
                        AppConfig.USERAPIKEY=systemConfigEntity.getConfigValue();
                        break;
                    case "userSecretKey":
                        AppConfig.USERSECRETKEY=systemConfigEntity.getConfigValue();
                        break;
                    case "staffApiKey":
                        AppConfig.STAFFAPIKEY=systemConfigEntity.getConfigValue();
                        break;
                    case "staffSecretKey":
                        AppConfig.STAFFSECRETKEY=systemConfigEntity.getConfigValue();
                        break;
                    case "UserIosPushP12Path":
                        AppConfig.USER_IOS_P12_PATH=systemConfigEntity.getConfigValue();
                        break;
                    case "UserIosPushP12Pwd":
                        AppConfig.USER_IOS_P12_PWD=systemConfigEntity.getConfigValue();
                        break;
                    case "userP12Type":
                        AppConfig.USER_IOS_P12_TYPE=systemConfigEntity.getConfigValue();
                        break;
                    case "StaffIosPushP12Path":
                        AppConfig.STAFF_IOS_P12_PATH=systemConfigEntity.getConfigValue();
                        break;
                    case "StaffIosPushP12Pwd":
                        AppConfig.STAFF_IOS_P12_PWD=systemConfigEntity.getConfigValue();
                        break;
                    case "staffP12Type":
                        AppConfig.STAFF_IOS_P12_TYPE=systemConfigEntity.getConfigValue();
                        break;
                    //上面是张洪健的消息推送


                /*    case "URL":
                        AppConfig.URL= systemConfigEntity.getConfigValue();
                        break;
                    case "PROT":
                        AppConfig.PROT= systemConfigEntity.getConfigValue();
                        break;
                    case "USERNAME":
                        AppConfig.USERNAME= systemConfigEntity.getConfigValue();
                        break;
                    case "PASSWORD":
                        AppConfig.PASSWORD= systemConfigEntity.getConfigValue();
                        break;*/
                }

            }

            System.out.println("------------------- end SystemConfig to utility.AppConfig -----------------");

        }
        catch (Exception ex){
            System.out.println("------------------- Exception SystemConfig to utility.AppConfig -----------------");
            ex.printStackTrace();
        }
    }

    /**
     * Bean销毁方法
     */
    public void destroy(){
        System.out.println("SystemConfig destroy()");
    }

}
