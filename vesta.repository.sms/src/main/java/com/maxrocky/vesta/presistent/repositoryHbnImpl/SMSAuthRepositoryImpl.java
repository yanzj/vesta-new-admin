package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SMSAuthEntity;
import com.maxrocky.vesta.domain.config.SMSConfig;
import com.maxrocky.vesta.domain.repository.SMSAuthRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Tom on 2016/1/15 10:03.
 * Describe:手机短信Repository接口实现类
 */
@Repository
public class SMSAuthRepositoryImpl implements SMSAuthRepository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:创建手机验证码
     * CreateBy:Tom
     * CreateOn:2016-01-15 11:34:11
     */
    @Override
    public void create(SMSAuthEntity smsAuthEntity) {
        Session session = getCurrentSession();
        session.save(smsAuthEntity);
        session.flush();
    }

    /**
     * Describe:根据手机号、验证码、类型判断是否有效
     * CreateBy:Tom
     * CreateOn:2016-01-17 04:20:03
     */
    @Override
    public Boolean getSMSAuthIsValid(String phone, String authCode, String type) {
        String hql = "FROM SMSAuthEntity WHERE phone = :phone AND authType = :authType AND code = :code ORDER BY sendTime DESC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("phone", phone);
        query.setParameter("authType", type);
        query.setParameter("code", authCode);
        List<SMSAuthEntity> smsAuthEntityList = query.list();

        if(smsAuthEntityList.size() == 0){
            return false;
        }

        int deadTime = 0;
        try {
            deadTime = StringUtil.toInt(SMSConfig.DEAD_TIME);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("获取短信失效时间失败。");
        }

        Date dateNow = DateUtils.getDate();
        Date sentTime = DateUtils.addMinute(smsAuthEntityList.get(0).getSendTime(), deadTime);

        if(sentTime.compareTo(dateNow) == 1){
            return true;
        }

        return false;
    }
}
