package com.maxrocky.vesta.presistent.repositoryHbnlmpl;

import com.maxrocky.vesta.domain.model.CardCRMEntity;
import com.maxrocky.vesta.domain.repository.CardCRMRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/13.
 */
@Repository
public class CardCRMRepositoryImpl implements CardCRMRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * Describe:根据会员编号获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public CardCRMEntity get(String id,String memberId) {
        String hql="FROM CardCRMEntity WHERE id='"+id+"' AND memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<CardCRMEntity> cardCRMList=query.list();
        if(cardCRMList.size()>0){
            return cardCRMList.get(0);
        }
        return null;
    }

    /**
     * Describe:创建会员卡信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:01:12
     */
    @Override
    public void create(CardCRMEntity cardCRMEntity) {
        Session session = getCurrentSession();
        session.save(cardCRMEntity);
        session.flush();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改会员卡信息
     * ModifyBy:
     */
    @Override
    public void update(CardCRMEntity cardCRMEntity) {
        Session session = getCurrentSession();
        session.update(cardCRMEntity);
        session.flush();
        session.close();
    }
    /**
     * Describe:获取所有会员账号信息
     * CreateBy:dinglei
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public List<CardCRMEntity> getCardInfo() {
        String hql="FROM CardCRMEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<CardCRMEntity> cardCRMList=query.list();
        return cardCRMList;
    }
}
