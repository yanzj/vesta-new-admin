package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SellerPictureEntity;
import com.maxrocky.vesta.domain.repository.SellerPictureRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/1/15.
 */
@Repository
public class SellerPictureRepositoryImpl implements SellerPictureRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void AddSellerPicture(SellerPictureEntity sellerPictureEntity) {
        Session session = getCurrentSession();
        session.save(sellerPictureEntity);
        session.flush();
    }

    @Override
    public List<SellerPictureEntity> getSellerPictures(String bussnessId) {
        String hql = "from SellerPictureEntity where sellerId = ? and pictureStatus ="+ SellerPictureEntity.STATUS_VALID;
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter(0,bussnessId);
        List<SellerPictureEntity> sellerPictureEntities = query.list();
        return sellerPictureEntities;
    }
}
