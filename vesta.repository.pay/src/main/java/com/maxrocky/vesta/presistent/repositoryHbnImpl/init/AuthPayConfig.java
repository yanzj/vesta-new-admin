package com.maxrocky.vesta.presistent.repositoryHbnImpl.init;

import com.maxrocky.vesta.domain.model.PayConfigEntity;
import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.PayConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/2/20 10:09.
 * Describe:初始化支付配置参数
 * 此配置在初始化设置里db-hbn-jta.xml
 */
//@Component
public class AuthPayConfig {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Bean初始化方法
     */
    public void init(){
        System.out.println("PayConfig init()");

        System.out.println("------------------- start AutoRunController.init() -----------------");

        List<PayConfigEntity> payConfigEntityList = null;

        try {
            System.out.println("------------------- select PayConfig from database -----------------");

            String hql = "FROM PayConfigEntity";
            payConfigEntityList = getCurrentSession().createQuery(hql).list();

            if(payConfigEntityList == null){
                System.out.println("------------------- PayConfig is null -----------------");
            }

            System.out.println("------------------- PayConfig.size() is " + payConfigEntityList.size() + "-----------------");

        }
        catch (Exception ex){
            System.out.println("------------------- select PayConfig from database is Exception -----------------");
            ex.printStackTrace();
        }

        try {
            System.out.println("-------------------start PayConfig to utility.PayConfig -----------------");

            for (PayConfigEntity payConfigEntity : payConfigEntityList) {

                System.out.println("------------------- config_code is " + payConfigEntity.getConfigCode() + " -----------------");
                System.out.println("------------------- config_value is " + payConfigEntity.getConfigValue() + " -----------------");

                switch (payConfigEntity.getConfigCode()) {
                    case "WeChat_Notify_Url_APP":
                        PayConfig.WECHAT_NOTIFYURL_APP = payConfigEntity.getConfigValue();
                        break;
                    case "Bill_Notify_Url_APP":
                        PayConfig.BILL_NOTIFYURL_APP = payConfigEntity.getConfigValue();
                        break;
                    case "Ali_Notify_Url_APP":
                        PayConfig.ALI_NOTIFYURL_APP = payConfigEntity.getConfigValue();
                        break;
                    case "Wanda_Notify_Url_APP":
                        PayConfig.WANDA_NOTIFYURL_APP = payConfigEntity.getConfigValue();
                        break;
                    case "Bill_Request_Pay_Path":
                        PayConfig.BILL_REQUEST_PAY_PATH = payConfigEntity.getConfigValue();
                        break;
                    case "Bill_Success_Show":
                        PayConfig.BILL_SUCCESS_SHOW = payConfigEntity.getConfigValue();
                        break;
                }

                System.out.println("------------------- end PayConfig to utility.PayConfig -----------------");

            }
        }
        catch (Exception ex){
            System.out.println("------------------- Exception PayConfig to utility.PayConfig -----------------");
            ex.printStackTrace();
        }
    }

    /**
     * Bean销毁方法
     */
    public void destroy(){
        System.out.println("PayConfig destroy()");
    }

}
