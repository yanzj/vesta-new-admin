package com.maxrocky.vesta.task;

import com.maxrocky.vesta.domain.model.SystemConfigEntity;
import com.maxrocky.vesta.domain.repository.SystemConfigRepository;
import com.maxrocky.vesta.utility.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by Tom on 2016/3/8 11:02.
 * Describe:
 */
//@Component
public class SystemConfigTask {

    /* 系统配置 */
    @Autowired
    SystemConfigRepository systemConfigRepository;

    /* 每一个小时执行一次 */
    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void initSystemConfig(){

        System.out.println("-------------------->> start SystemConfigTask.initSystemConfig()...");
        System.out.println("-------------------->> select SystemConfig from database...");
        List<SystemConfigEntity> systemConfigEntityList = systemConfigRepository.getList();
        if(systemConfigEntityList == null){
            System.out.println("-------------------->> SystemConfig is null.");
        }

        System.out.println("-------------------->> SystemConfig.size() is " + systemConfigEntityList.size() + "");

        System.out.println("-------------------->> start SystemConfig to AppConfig.SystemConfig -----------------");

        for (SystemConfigEntity systemConfigEntity : systemConfigEntityList) {

            System.out.println("-------------------->> config_code is " + systemConfigEntity.getConfigCode() + " -----------------");
            System.out.println("-------------------->> config_value is " + systemConfigEntity.getConfigValue() + " -----------------");

            switch (systemConfigEntity.getConfigCode()) {
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
                    AppConfig.INTERFACEPATH = systemConfigEntity.getConfigValue();
                    break;
                case "AccessPath":
                    AppConfig.PATH = systemConfigEntity.getConfigValue();
                    break;
                case "SSOClientLoginPath":
                    AppConfig.SSO_CLIENT_LOGIN_PATH = systemConfigEntity.getConfigValue();
                    break;
                case "SSOSystemCode":
                    AppConfig.SSO_SYSTEM_CODE = systemConfigEntity.getConfigValue();
                    break;
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
                    AppConfig.LOGORAPH = systemConfigEntity.getConfigValue();
                    break;
                case "DefaultProjectId":
                    AppConfig.DEFAULT_PROJECT_ID = systemConfigEntity.getConfigValue();
                    break;
                case "DefaultProjectName":
                    AppConfig.DEFAULT_PROJECT_NAME = systemConfigEntity.getConfigValue();
                    break;
                case "activity":
                    AppConfig.ACTIVITY = systemConfigEntity.getConfigValue();
                    break;
                case "sharingactivity":
                    AppConfig.SHARINGACTIVITY = systemConfigEntity.getConfigValue();
                    break;
                case "merchant":
                    AppConfig.MERCHANT = systemConfigEntity.getConfigValue();
                    break;
                case "house":
                    AppConfig.HOUSE = systemConfigEntity.getConfigValue();
                    break;
                case "say":
                    AppConfig.SAY = systemConfigEntity.getConfigValue();
                    break;
                case "feedback":
                    AppConfig.FEEDBACK = systemConfigEntity.getConfigValue();
                    break;
                case "topic":
                    AppConfig.TOPIC = systemConfigEntity.getConfigValue();
                    break;
                case "startPage":
                    AppConfig.STARTPAGE = systemConfigEntity.getConfigValue();
                    break;
                case "details":
                    AppConfig.DETAILS = systemConfigEntity.getConfigValue();
                    break;
                case "Integration":
                    AppConfig.INTEGRATION = systemConfigEntity.getConfigValue();
                    break;
                case ".1image_domain":
                    AppConfig.image_domain = systemConfigEntity.getConfigValue();
                    break;
                case "pay_call_url":
                    AppConfig.payingCallUrl = systemConfigEntity.getConfigValue();
                    break;
                case "default_image_club":
                    AppConfig.default_image.club_path = AppConfig.image_domain + "/" + systemConfigEntity.getConfigValue();
                    break;
                case "defalut_image_community":
                    AppConfig.default_image.community_path = AppConfig.image_domain + "/" + systemConfigEntity.getConfigValue();
                    break;
                case "StaffStartIdentify":
                    AppConfig.STAFF_START_IDENTIFY = systemConfigEntity.getConfigValue();
                    break;
                case "StaffCookieDomain":
                    AppConfig.Staff_Cookie_Domain = systemConfigEntity.getConfigValue();
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
                case "userStorePushP12Path":
                    AppConfig.USER_STORE_P12_PATH=systemConfigEntity.getConfigValue();
                    break;
                case "userStorePushP12Pwd":
                    AppConfig.USER_STORE_P12_PWD=systemConfigEntity.getConfigValue();
                    break;
                case "userStoreP12Type":
                    AppConfig.USER_STORE_P12_TYPE=systemConfigEntity.getConfigValue();
                    break;
                case "StaffStorePushP12Path":
                    AppConfig.STAFF_STORE_P12_PATH=systemConfigEntity.getConfigValue();
                    break;
                case "StaffStorePushP12Pwd":
                    AppConfig.STAFF_STORE_P12_PWD=systemConfigEntity.getConfigValue();
                    break;
                case "staffStoreP12Type":
                    AppConfig.STAFF_STORE_P12_TYPE=systemConfigEntity.getConfigValue();
                    break;
                //上面是张洪健的消息推送
            }
        }

        System.out.println("-------------------->> end SystemConfig to AppConfig.AppConfig -----------------");

    }

}
