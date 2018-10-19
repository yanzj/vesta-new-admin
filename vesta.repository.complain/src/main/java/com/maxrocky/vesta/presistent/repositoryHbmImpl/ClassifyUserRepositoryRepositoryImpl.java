package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.ClassifyStaffRelationEntity;
import com.maxrocky.vesta.domain.model.ComplainClassifyEntity;
import com.maxrocky.vesta.domain.repository.ClassifyUserRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Magic on 2017/7/18.
 */
@Repository
public class ClassifyUserRepositoryRepositoryImpl extends HibernateDaoImpl implements ClassifyUserRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){ return sessionFactory.openSession(); }


    @Override
    public void saveClassUser(ClassifyStaffRelationEntity classifyStaffRelationEntity) {
        Session session = getCurrentSession();
        session.save(classifyStaffRelationEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateClassUser(ClassifyStaffRelationEntity classifyStaffRelationEntity) {
        Session session = getCurrentSession();
        session.update(classifyStaffRelationEntity);
        session.flush();
        session.close();
    }

    @Override
    public ClassifyStaffRelationEntity getClassifyStaffRelation(String id) {
        String hql = "FROM ClassifyStaffRelationEntity WHERE staffId='" + id + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<ClassifyStaffRelationEntity> userList = query.list();
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public ComplainClassifyEntity getComplainClassifyEntity(String id) {
        String hql = "FROM ComplainClassifyEntity WHERE classifyId='" + id + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<ComplainClassifyEntity> classList = query.list();
        if (classList.size() > 0) {
            return classList.get(0);
        }
        return null;
    }

    @Override
    public void saveClass(ComplainClassifyEntity complainClassifyEntity) {
        Session session = getCurrentSession();
        session.save(complainClassifyEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateClass(ComplainClassifyEntity complainClassifyEntity) {
        Session session = getCurrentSession();
        session.update(complainClassifyEntity);
        session.flush();
        session.close();
    }

    @Override
    public boolean deleteClassifyStaff(List<String> idList) {
        String sql = "delete from qc_classify_staff_relation where concat(project_num,classify_id) in(:idlist)";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameterList("idlist", idList);
        query.executeUpdate();
        return true;
    }
}
