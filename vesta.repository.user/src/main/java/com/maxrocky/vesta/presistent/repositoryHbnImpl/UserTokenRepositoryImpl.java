package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserTokenEntity;
import com.maxrocky.vesta.domain.repository.UserTokenRepository;
import com.maxrocky.vesta.utility.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/4/6.
 */
@Repository
public class UserTokenRepositoryImpl implements UserTokenRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void Add(UserTokenEntity object) {
        Session session = getCurrentSession();
        session.save(object);
        session.flush();
    }

    @Override
    public void ClearUnvaliableUserToken() {
        Session s = getCurrentSession();
        String hqlDelete = "delete UserTokenEntity uk where uk.isActive = :isActive";
        s.createQuery(hqlDelete).setString("isActive", "0");
        s.flush();
    }

    @Override
    public UserTokenEntity GET(String vestaToken) {
        if(vestaToken==null){
            return null;
        }
        return (UserTokenEntity) getCurrentSession().get(UserTokenEntity.class, vestaToken);
    }

    @Override
    public void ClearToken(String formUser) {
        Session s = getCurrentSession();
        String hqlDelete = "delete UserTokenEntity uk where uk.userId = '"+formUser+"'";
        getCurrentSession().createQuery(hqlDelete).executeUpdate();
    }

    @Override
    public void UpdateToken(UserTokenEntity formUser, String toUser) {
        System.out.println("-----UpdateToken------from--"+formUser.getUserId()+"----to----"+toUser);
        Session s = getCurrentSession();
        String hqlDelete = "update UserTokenEntity set userId = '"+toUser+"' where userId = '"+formUser.getUserId()+"' and tokenId ='"+formUser.getTokenId()+"'";
        getCurrentSession().createQuery(hqlDelete).executeUpdate();
        System.out.println("-----UpdateToken------from--" + formUser + "----to----" + toUser);
    }

    @Override
    public void Update(String vestatoken) {
        String updateDate = DateUtils.getNow(DateUtils.FORMAT_SHORT);
        String hqlupdate = "update UserTokenEntity set createDate = '"+updateDate+"' where tokenId ='"+vestatoken+"'";
        getCurrentSession().createQuery(hqlupdate).executeUpdate();
    }

    @Override
    public UserTokenEntity getByUserId(String userId) {
        String hql = "FROM UserTokenEntity WHERE userId='" + userId + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserTokenEntity> userTokenList = query.list();
        if (userTokenList.size() > 0) {
            return userTokenList.get(0);
        }
        return null;
    }
}
