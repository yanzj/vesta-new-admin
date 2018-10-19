package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UplusMembershipCardEntity;
import com.maxrocky.vesta.domain.repository.UplusMembershipCardRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * U+会员卡数据持久层实现类
 * Created by WeiYangDong on 2017/11/1.
 */
@Repository
public class UplusMembershipCardRepositoryImpl implements UplusMembershipCardRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存或更新Entity
     */
    @Override
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * 返回ID最大值
     */
    @Override
    public int getMaxId(){
        Session session = getCurrentSession();
        Query query = session.createQuery("select max(id) from UplusMembershipCardEntity");
        Object maxId = query.uniqueResult();
        session.flush();
        session.close();
        if (null == maxId){
            maxId = 0;
        }
        return (int) maxId;
    }

    /**
     * 通过用户ID获取U+会员卡信息
     * @param userId 用户ID
     * @return UplusMembershipCardEntity
     */
    public UplusMembershipCardEntity getUplusMembershipCardByUserId(String userId){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM UplusMembershipCardEntity WHERE userId = ?").setParameter(0, userId);
        UplusMembershipCardEntity uplusMembershipCardEntity = (UplusMembershipCardEntity) query.uniqueResult();
        session.flush();
        session.close();
        return uplusMembershipCardEntity;
    }
}
