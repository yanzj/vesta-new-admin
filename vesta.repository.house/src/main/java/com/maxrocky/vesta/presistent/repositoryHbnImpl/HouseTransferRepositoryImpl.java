package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.CustomerDeliveryEntity;
import com.maxrocky.vesta.domain.model.HouseOpenEntity;
import com.maxrocky.vesta.domain.model.HouseTransferEntity;
import com.maxrocky.vesta.domain.model.InternalacceptanceHouseEntity;
import com.maxrocky.vesta.domain.repository.HouseTransferRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangzhaowen on 2016/9/1.14:24
 * Describe:房屋交接状态  业主签字
 */
@Repository
public class HouseTransferRepositoryImpl extends HibernateDaoImpl implements HouseTransferRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public boolean getCountInternalacceptanceByProjectNum(String id, String time, String projectNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" count(1) ");
        sql.append(" from house_acceptance s ");
        sql.append(" where 1=1 ");
        sql.append(" and s.HOUSECODE like:projectNum ");
        sql.append(" and ((s.UPDATETIME =:tim and s.ID >:iid) or s.UPDATETIME>:tim)");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("tim", time);
        query.setParameter("iid", id);
        query.setParameter("projectNum", projectNum);
        List list = query.list();
        if (!list.get(0).toString().equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getCountHouseOpenByProjectNum(String id, String time, String projectNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" count(1) ");
        sql.append(" from house_open s ");
        sql.append(" where 1=1 ");
        sql.append(" and s.HOUSE_CODE like:projectNum  ");
        sql.append(" and ((s.UPDATETIME =:tim and s.ID >:iid) or s.UPDATETIME>:tim)");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("tim", time);
        query.setParameter("iid", id);
        query.setParameter("projectNum", projectNum);
        List list = query.list();
        if (!list.get(0).toString().equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveInternalacceptanceHouseEntity(InternalacceptanceHouseEntity internalacceptanceHouseEntity) {
        Session session = getCurrentSession();
        session.save(internalacceptanceHouseEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateInternalacceptanceHouseEntity(InternalacceptanceHouseEntity internalacceptanceHouseEntity) {
        Session session = getCurrentSession();
        session.update(internalacceptanceHouseEntity);
        session.flush();
        session.close();
    }

    @Override
    public void saveHouseOpenEntity(HouseOpenEntity houseOpenEntity) {
        Session session = getCurrentSession();
        session.save(houseOpenEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateHouseOpenEntity(HouseOpenEntity houseOpenEntity) {
        Session session = getCurrentSession();
        session.update(houseOpenEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<InternalacceptanceHouseEntity> getInternalacceptanceHouseList(String id, String timeStamp, String projectNum,List<String> projectList) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  house_acceptance s LEFT JOIN house_room h on s.HOUSECODE=h.ROOM_NUM  where  h.PROJECT_NUM in(:projectList) and HOUSECODE like:projectNum and ((s.UPDATETIME=:tim and s.ID>:id) or s.UPDATETIME>:tim)  ORDER BY s.UPDATETIME , s.ID limit 500").addEntity(InternalacceptanceHouseEntity.class);
        sqlQuery.setParameter("tim",timeStamp);
        sqlQuery.setParameter("id",id);
        sqlQuery.setParameter("projectNum",projectNum);
        sqlQuery.setParameterList("projectList",projectList);
        List<InternalacceptanceHouseEntity> InternalacceptanceHouse = sqlQuery.list();
        if(InternalacceptanceHouse.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return InternalacceptanceHouse;
    }

    @Override
    public InternalacceptanceHouseEntity getInternalacceptanceHouseById(String roomCode) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  house_acceptance  where HOUSECODE =:roomid ").addEntity(InternalacceptanceHouseEntity.class);
        sqlQuery.setParameter("roomid",roomCode);
        List<InternalacceptanceHouseEntity> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList.get(0);
    }

    @Override
    public List<HouseOpenEntity> getHouseOpenList(String id, String timeStamp, String projectNum,List<String> projectList) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select s.* from  house_open s LEFT JOIN house_room  h ON s.HOUSE_CODE=h.ROOM_NUM where h.PROJECT_NUM in(:projectList) and HOUSE_CODE like:projectNum and ((s.UPDATETIME=:tim and s.ID>:id) or s.UPDATETIME>:tim)  ORDER BY s.UPDATETIME , s.ID limit 500").addEntity(HouseOpenEntity.class);
        sqlQuery.setParameter("tim",timeStamp);
        sqlQuery.setParameter("id",id);
        sqlQuery.setParameter("projectNum",projectNum);
        sqlQuery.setParameterList("projectList", projectList);
        List<HouseOpenEntity> HouseOpenList = sqlQuery.list();
        if(HouseOpenList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return HouseOpenList;
    }

    @Override
    public HouseOpenEntity getHouseOpenById(String roomCode) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  house_open  where HOUSE_CODE =:roomid").addEntity(HouseOpenEntity.class);
        sqlQuery.setParameter("roomid",roomCode);
        List<HouseOpenEntity> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList.get(0);
    }

    @Override
    public void saveHouseTransfer(HouseTransferEntity houseTransferEntity) {

        Session session = getCurrentSession();
        session.saveOrUpdate(houseTransferEntity);
        session.flush();
        session.close();

    }

    @Override
    public HouseTransferEntity getHouseTransferById(String roomid) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  house_transfer  where ROOM_NUMBER =:roomid").addEntity(HouseTransferEntity.class);
        sqlQuery.setParameter("roomid",roomid);
        List<HouseTransferEntity> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList.get(0);
//        return (HouseTransferEntity)getCurrentSession().get(HouseTransferEntity.class,id);
    }

    @Override
    public CustomerDeliveryEntity getCustomerById(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  house_deliver  where HOUSE_CODE =:id").addEntity(CustomerDeliveryEntity.class);
        sqlQuery.setParameter("id",id);
        List<CustomerDeliveryEntity> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList.get(0);
    }

    @Override
    public List<HouseTransferEntity> getHouseTransferByIdAndTime(long id, String timeStamp,List<String> projectList) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select s.* from house_transfer s LEFT JOIN house_room  h on s.ROOM_NUMBER=h.ROOM_NUM where 1=1 and h.PROJECT_NUM in(:projectList) and ((s.TIME_STAMP=:tim and s.ID>:id) or s.TIME_STAMP>:tim ) ORDER BY s.TIME_STAMP , s.ID  limit 500").addEntity(HouseTransferEntity.class);
        sqlQuery.setParameter("tim",timeStamp);
        sqlQuery.setParameter("id",id);
        sqlQuery.setParameterList("projectList",projectList);
        List<HouseTransferEntity> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList;
    }

    @Override
    public void updateHouseTransfer(HouseTransferEntity houseTransferEntity) {
        Session session = getCurrentSession();
        session.update(houseTransferEntity);
        session.flush();
        session.close();
    }

    @Override
    public void saveCustomerDelivery(CustomerDeliveryEntity CustomerEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(CustomerEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<Object[]> getMaxIdAndTime() {
        String hql ="SELECT MAX(id) ,MAX(timeStamp)FROM HouseTransferEntity";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        return  query.list();
    }

    @Override
    public HouseTransferEntity getEntityWithMaxTime() {
        String hql = "SELECT * FROM house_transfer WHERE ID= (SELECT MAX(ID)FROM house_transfer)";
        Session session =getCurrentSession();
        Query query = session.createSQLQuery(hql).addEntity(HouseTransferEntity.class);
        return (HouseTransferEntity)query.uniqueResult();
    }

    @Override
    public String saveEntityAndReturn(HouseTransferEntity houseTransferEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(houseTransferEntity);
        String hql = "SELECT LAST_INSERT_ID() FROM house_transfer";
        Query query = getCurrentSession().createSQLQuery(hql);
        String id = query.list().get(0).toString() ;
        session.flush();
        session.close();
        return id;
    }

    @Override
    public HouseOpenEntity getHouseOpenByRoomNum(String roomNum) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  house_open s where  HOUSE_CODE =:roomNum").addEntity(HouseOpenEntity.class);
        sqlQuery.setParameter("roomNum",roomNum);
        List<HouseOpenEntity> HouseOpenList = sqlQuery.list();
        if(HouseOpenList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return HouseOpenList.get(0);
    }

    @Override
    public InternalacceptanceHouseEntity getInternalacceptanceByRoomNum(String roomNum) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  house_acceptance s where  HOUSECODE =:roomNum").addEntity(InternalacceptanceHouseEntity.class);
        sqlQuery.setParameter("roomNum",roomNum);
        List<InternalacceptanceHouseEntity>internalList = sqlQuery.list();
        if(internalList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return internalList.get(0);
    }

    @Override
    public List<CustomerDeliveryEntity> getCustomerListById(String projectNum) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  house_deliver  where HOUSE_CODE like '%"+projectNum+"%'").addEntity(CustomerDeliveryEntity.class);
        List<CustomerDeliveryEntity> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList;
    }
}
