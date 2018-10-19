package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserImageEntity;
import com.maxrocky.vesta.domain.repository.UserImageRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/21 17:56.
 * Describe:用户图片Repository接口实现类
 */
@Repository
public class UserImageRepositoryImpl implements UserImageRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:创建用户图片
     * CreateBy:Tom
     * CreateOn:2016-01-21 06:00:10
     */
    @Override
    public void create(List<UserImageEntity> userImageEntityList) {
        Session session = getCurrentSession();
        for (UserImageEntity userImageEntity : userImageEntityList){
            session.save(userImageEntity);
        }
        session.flush();
    }

    /**
     * 查询图片列表
     * @param businessId
     * @return
     */
    @Override
    public List<UserImageEntity> getUserImageByBusinessId(String businessId) {
        String hql = "from UserImageEntity where businessId="+"'"+businessId+"'";
        List<UserImageEntity> userImageList = getCurrentSession().createQuery(hql).list();
        return userImageList;
    }

}
