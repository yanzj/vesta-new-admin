package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.repository.HouseUserCRMRepository;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * 房产业主关系Dao实现类
 * Created by WeiYangDong on 2017/3/1.
 */
@Repository
public class HouseUserCRMRepositoryImpl implements HouseUserCRMRepository{

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {

        return sessionFactory.openSession();
    }

    /**
     * 保存或更新Entity
     * @param entity
     */
    public <T> void saveOrUpdate(T entity){
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * 删除Entity
     * @param entity
     */
    public <E> void delete(E entity){
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    /**
     * 通过memberId删除房产业主关系数据
     * @param memberId CRM业主Id
     */
    public void delHouseUserByMemberId(String memberId){
        Session session = getCurrentSession();
        Query query = session.createQuery(" delete HouseUserCRMEntity hu where hu.memberId = ? ");
        query.setParameter(0,memberId);
        query.executeUpdate();
        session.flush();
        session.close();
    }

    @Override
    public void updateHouseUserCrm(String memberId) {
        StringBuilder sql = new StringBuilder("");
        sql.append(" UPDATE house_user_crm huc ");
        sql.append(" INNER JOIN house_houseInfo hh ON huc.room_num = hh.ROOM_NUM ");
        sql.append(" INNER JOIN user_crm uc ON huc.member_id = uc.MEMBER_ID ");
        sql.append(" SET huc.house_address = hh.ADDRESS, huc.real_name = uc.REAL_NAME,huc.project_code = hh.PROJECT_NUM,huc.project_name = hh.PROJECT_NAME ");
        sql.append(" where huc.member_id='"+memberId+"' ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }
}
