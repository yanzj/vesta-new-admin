package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserWandaStaffEntity;
import com.maxrocky.vesta.domain.repository.UserWandaStaffRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/14.
 */
@Repository
public class UserWandaStaffRepositoryImpl implements UserWandaStaffRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    /**
     * 获取万达员工列表
     * @return
     */
    @Override
    public List<UserWandaStaffEntity> listUserWandaStaff() {
        String hql = "from UserWandaStaffEntity as o where o.staffState = :staffState and o.type = :stafftype order by o.staffName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffState",UserWandaStaffEntity.State_On);//显示有效的万达员工
        query.setParameter("stafftype",UserWandaStaffEntity.Type_NO);//显示尚未引入的万达员工
        List<UserWandaStaffEntity> userWandaStaffEntities = query.list();
        return userWandaStaffEntities;
    }

    /**
     * 根据Id查找万达员工信息
     * @param staffId
     * @return
     */
    @Override
    public UserWandaStaffEntity getWandaStaffById(String staffId) {
        String hql = "from UserWandaStaffEntity as o where o.staffId = :staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        if (query.list().size()>0){
            return (UserWandaStaffEntity)query.list().get(0);
        }
        else {
            return null;
        }
    }

    /**
     * 更新万达员工信息
     * @param userWandaStaffEntity
     * @return
     */
    @Override
    public boolean updateUserWandaStaff(UserWandaStaffEntity userWandaStaffEntity) {
        UserWandaStaffEntity userWandaStaffEntity_1 = getWandaStaffById(userWandaStaffEntity.getStaffId());
        if (userWandaStaffEntity_1!=null){
            Session session = getCurrentSession();
            session.update(userWandaStaffEntity);
            session.flush();
            session.close();
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * 根据userName查询Ehr中员工信息
     * @param userName
     * @return
     */
    /*@Override
    public EhrStaffEntity getEhrWandaByUserName(String userName) {
        EhrStaffEntity ehrStaffEntity = new EhrStaffEntity();
        String hql = "from EhrStaffEntity as s where s.username = :username";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("username",userName);
        if (query.list().size()>0){
            ehrStaffEntity = (EhrStaffEntity)query.list().get(0);
            return ehrStaffEntity;
        }
        else {
            return null;
        }
    }*/
}
