package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ContactCRMEntity;
import com.maxrocky.vesta.domain.repository.ContactCRMRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/28.
 */
@Repository
public class ContactCRMRepositoryImpl implements ContactCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据会员编号获取信息
     * CreateBy:lingdongxin
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public ContactCRMEntity get(String memberId) {
        String hql="FROM ContactCRMEntity WHERE memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<ContactCRMEntity> contactCRMList=query.list();
        if(contactCRMList.size()>0){
            return contactCRMList.get(0);
        }
        return null;
    }

    /**
     * Describe:创建通讯信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-19 10:01:12
     */
    @Override
    public void create(ContactCRMEntity contactCRMEntity) {
        Session session = getCurrentSession();
        session.save(contactCRMEntity);
        session.flush();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改通讯信息
     * ModifyBy:
     */
    @Override
    public void update(ContactCRMEntity contactCRMEntity) {
        Session session = getCurrentSession();
        session.update(contactCRMEntity);
        session.flush();
        session.close();
    }
    /**
     * Describe:获取会员的通讯信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public List<ContactCRMEntity> getContactInfo() {
        String hql="FROM ContactCRMEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<ContactCRMEntity> contactList=query.list();
        return contactList;
    }
}
