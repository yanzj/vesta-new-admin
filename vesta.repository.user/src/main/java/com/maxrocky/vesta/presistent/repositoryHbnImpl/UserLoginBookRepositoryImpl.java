package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserLoginBookEntity;
import com.maxrocky.vesta.domain.repository.UserLoginBookRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/13 19:04.
 * Describe:登录信息Repository接口实现类
 */

@Repository
public class UserLoginBookRepositoryImpl implements UserLoginBookRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据登录Id获取登录信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 07:06:14
     */
    @Override
    public UserLoginBookEntity get(String loginId) {
        return (UserLoginBookEntity)getCurrentSession().get(UserLoginBookEntity.class, loginId);
    }

    /**
     * Describe:根据用户Id获取有效登录信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:17:04
     */
    @Override
    public UserLoginBookEntity getLoginByUserId(String userId) {
        String hql = "FROM UserLoginBookEntity WHERE state = :state AND userId = :userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("state", UserLoginBookEntity.STATE_NORMAL);
        query.setParameter("userId", userId);
        List<UserLoginBookEntity> userLoginBookEntityList = query.list();
        if(userLoginBookEntityList.size() > 0){
            return userLoginBookEntityList.get(0);
        }
        return null;
    }

    /**
     * Describe:修改登录信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:37:19
     */
    @Override
    public void update(UserLoginBookEntity userLoginBookEntity) {
        Session session = getCurrentSession();
        session.update(userLoginBookEntity);
        session.flush();
    }

    /**
     * Describe:创建登录信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 09:46:41
     */
    @Override
    public void create(UserLoginBookEntity userLoginBookEntity) {
        Session session = getCurrentSession();
        session.save(userLoginBookEntity);
        session.flush();
    }

    @Override
    public UserLoginBookEntity getLoginBookByuniodidAndUserid(String openId, String userId) {
        Session session =  getCurrentSession();
        String hql = "from UserLoginBookEntity as u Where u.userId = '" + userId + "' and u.unionId = '"+openId+"'";

        return (UserLoginBookEntity) session.createQuery(hql).uniqueResult();
    }

    @Override
    public void updateOpenIdInLoginBook(String userId, String user_unionid, String user_openid) {
        Session s = getCurrentSession();
        String hqlupdate = "update UserLoginBookEntity set loginId = '"+user_openid+"' where loginType = 'WC' and userId = '"+userId+"' and unionId ='"+user_unionid+"'";
        getCurrentSession().createQuery(hqlupdate).executeUpdate();
        System.out.println("-----updateOpenIdInLoginBook-------" + user_openid );
    }

}
