package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserIntegralRuleRecordEntity;
import com.maxrocky.vesta.domain.repository.UserIntegralRuleRecordRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/14.
 */
@Repository
public class UserIntegralRuleRecordRepositoryImpl implements UserIntegralRuleRecordRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Object> getAll(Map<String, Object> paramsMap, WebPage webPage) {
        /*
       String sql = "SELECT " +
               "r.user_integral_rule_record_id ," +
               "u.REAL_NAME," +
               "u.MOBILE," +
               "u.WC_NICK_NAME," +
               "h.NAME," +
               "r.userAddress," +
               "n.integral_number," +
               "c.client_name," +
               "r.userId," +
               "r.houseProjectId  " +
               " from  User_Integral_Rule_Record r " +
               "left join   user_userInfo u   on r.userId = u.USER_ID " +
               "left join   house_project h on  r.houseProjectId = h.PINYIN_CODE " +
               "left join   User_Integral_Rule n on r.userId = n.USER_ID " +
               "left join   client_config c on r.weChatAppId = c.weChat_appId  " +
               "where  r.userId is not null ";
       */
       String sql = "SELECT " +
               "u.REAL_NAME," +
               "u.MOBILE," +
               "r.integral_number," +
               "c.client_name," +
               "r.USER_ID," +
               "upm.card_num "+
               "from  user_userInfo u " +
               "left join   User_Integral_Rule r   on r.USER_ID = u.USER_ID " +
               "left join   client_config c on r.weChatAppId = c.weChat_appId " +
               "left join   up_membership_card upm on u.USER_ID = upm.user_id " +
               "where  r.USER_ID is not null ";
        List<Object> paramsList = new ArrayList<>();
        //姓名
        Object realName = paramsMap.get("realName");
        if (null != realName && !"".equals(realName)){
            sql += " AND u.REAL_NAME like ? ";
            paramsList.add("%"+realName+"%");
        }
        //电话
        Object mobile = paramsMap.get("mobile");
        if (null != mobile && !"".equals(mobile)){
            sql += " AND u.MOBILE like ? ";
            paramsList.add("%"+mobile+"%");
        }
        //项目
//        Object name = paramsMap.get("name");
//        if (null != name){
//            sql += " AND h.NAME like ? ";
//            paramsList.add("%"+name+"%");
//        }
        //积分
        Object integralNumber = paramsMap.get("integralNumber");
        if (null != integralNumber && !"".equals(integralNumber)){
            sql += " AND r.integral_number = ? ";
            paramsList.add(integralNumber);
        }
        //微信APPID
        Object weChatAppId = paramsMap.get("weChatAppId");
        if (null != weChatAppId && !"".equals(weChatAppId)){
            sql += " AND r.weChatAppId = ? ";
            paramsList.add(weChatAppId);
        }
        //U+会员卡
        Object cardNum = paramsMap.get("cardNum");
        if (null != cardNum && !"".equals(cardNum)){
            sql += " AND upm.card_num = ? ";
            paramsList.add(cardNum);
        }
//        sql += " GROUP BY r.houseProjectId,r.userId";

        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        for (int i = 0,length = paramsList.size(); i < length; i++){
            sqlQuery.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }

        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public List<Object> getUserIntegralRuleById(Map<String, Object> paramsMap) {
        Object userId = paramsMap.get("userId");
//        Object houseProjectId = paramsMap.get("houseProjectId");

        /*
        String sql = "SELECT " +
                "r.create_on ," +
                "im.model_name," +
                "r.number " +
                " from  User_Integral_Rule_Record r " +
                "left join  integral_rule_model im on r.modelType = im.model_type " +
                "where r.userId = ? AND r.houseProjectId = ?  order by r.create_on desc ";
        */
        String sql = "SELECT " +
                "r.create_on ," +
                "im.model_name," +
                "r.number " +
                " from  User_Integral_Rule_Record r " +
                "left join  integral_rule_model im on r.modelType = im.model_type " +
                "where r.userId = ?  order by r.create_on desc ";
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0,userId);
//        sqlQuery.setParameter(1,houseProjectId);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public void AddIntegralRuleEntity(UserIntegralRuleRecordEntity u) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(u);
        session.flush();
        session.close();
    }

    @Override
    public List<Object> getRule(Map<String, Object> paramsMap) {

        Object cityId = paramsMap.get("cityId");
        Object projectNum = paramsMap.get("projectNum");
        Object weChatAppId = paramsMap.get("weChatAppId");


        String sql = "select " +
                "u.modelType," +
                "i.model_name," +
                "count(*)" +
                " from User_Integral_Rule_Record u" +
                " ,house_project p" +
                " ,integral_rule_model i" +
                "  where u.houseProjectId = p.PINYIN_CODE and i.model_type = u.modelType" +
                "    ";
        List<Object> paramsList = new ArrayList<>();
        //姓名
        if (null != cityId){
            sql += " AND p.CITY_ID like ? ";
            paramsList.add("%"+cityId+"%");
        }
        //姓名
        if (null != projectNum){
            sql += " AND p.PINYIN_CODE  like ?  ";
            paramsList.add("%"+projectNum+"%");
        }

        //公众号
        if (null != weChatAppId){
            sql += " AND u.weChatAppId  like ?  ";
            paramsList.add("%"+weChatAppId+"%");
        }

        sql += "  GROUP BY u.modelType";
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        for (int i = 0,length = paramsList.size(); i < length; i++){
            sqlQuery.setParameter(i,paramsList.get(i));
        }
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }
}
