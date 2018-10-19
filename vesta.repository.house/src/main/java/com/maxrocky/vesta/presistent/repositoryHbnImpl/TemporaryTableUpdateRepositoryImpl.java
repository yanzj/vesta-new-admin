package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ActiveTemporaryTimeEntity;
import com.maxrocky.vesta.domain.model.BuildingMappingTimeEntity;
import com.maxrocky.vesta.domain.model.ClassificationTemporaryTimeEntity;
import com.maxrocky.vesta.domain.model.PersonnelAuthorityTimeEntity;
import com.maxrocky.vesta.domain.repository.TemporaryTableUpdateRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Magic on 2016/6/15.
 */
@Repository
public class TemporaryTableUpdateRepositoryImpl extends HibernateDaoImpl implements TemporaryTableUpdateRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public PersonnelAuthorityTimeEntity queryPersonnel(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from personnel_authority_time s WHERE s.CURRENT_ID=:id ").addEntity(PersonnelAuthorityTimeEntity.class);
        sqlQuery.setParameter("id",id);
        List<PersonnelAuthorityTimeEntity> BuildingMapping = sqlQuery.list();
        if(BuildingMapping.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return BuildingMapping.get(0);
    }

    @Override
    public BuildingMappingTimeEntity queryBuild(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from building_mapping_time s WHERE s.CURRENT_ID=:id ").addEntity(BuildingMappingTimeEntity.class);
        sqlQuery.setParameter("id",id);
        List<BuildingMappingTimeEntity> BuildingMapping = sqlQuery.list();
        if(BuildingMapping.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return BuildingMapping.get(0);
    }

    @Override
    public void createBuild(BuildingMappingTimeEntity BuildingMappingTime) {
        Session session = getCurrentSession();
        session.save(BuildingMappingTime);
        session.flush();
    }

    @Override
    public void createPersonnel(PersonnelAuthorityTimeEntity PersonnelAuthorityTime) {
        Session session = getCurrentSession();
        session.save(PersonnelAuthorityTime);
        session.flush();
    }

    @Override
    public void updateBuild(BuildingMappingTimeEntity BuildingMappingTime) {
        Session session = getCurrentSession();
        session.update(BuildingMappingTime);
        session.flush();
        session.close();
    }

    @Override
    public void updatePersonnel(PersonnelAuthorityTimeEntity PersonnelAuthorityTime) {
        Session session = getCurrentSession();
        session.update(PersonnelAuthorityTime);
        session.flush();
        session.close();
    }

    /**
     * 获取房屋数据
     */
    @Override
    public List<BuildingMappingTimeEntity> getBuildingList() {
        String hql="FROM BuildingMappingTimeEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<BuildingMappingTimeEntity> list=query.list();
        return list;
    }

    @Override
    public ActiveTemporaryTimeEntity queryActive(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from active_temporary_time s WHERE s.CURRENT_ID=:id").addEntity(ActiveTemporaryTimeEntity.class);
        sqlQuery.setParameter("id",id);
        List<ActiveTemporaryTimeEntity> Active = sqlQuery.list();
        if(Active.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return Active.get(0);
    }

    @Override
    public ActiveTemporaryTimeEntity queryActiveBUild(String id,String PlanId) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from active_temporary_time s WHERE s.CURRENT_ID=:id and PARENT_ID=:PlanId").addEntity(ActiveTemporaryTimeEntity.class);
        sqlQuery.setParameter("id",id);
        sqlQuery.setParameter("PlanId",PlanId);
        List<ActiveTemporaryTimeEntity> Active = sqlQuery.list();
        if(Active.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return Active.get(0);
    }

    @Override
    public void createActive(ActiveTemporaryTimeEntity ActiveTemporaryTime) {
        Session session = getCurrentSession();
        session.save(ActiveTemporaryTime);
        session.flush();
    }

    @Override
    public void updateActive(ActiveTemporaryTimeEntity ActiveTemporaryTime) {
        Session session = getCurrentSession();
        session.update(ActiveTemporaryTime);
        session.flush();
        session.close();
    }

    @Override
    public void updateActiveStateById(String planId) {
        String sql = "update  active_temporary_time  set START='0' where PARENT_ID=:planId  ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("planId",planId);
        query.executeUpdate();
    }

    @Override
    public void updateActiveStateByIdList(List<String> idList) {
        String sql = "update  active_temporary_time  set START='0' where PARENT_ID in(:idList)  ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameterList("idList",idList);
        query.executeUpdate();
    }

    /**
     * 获取所有计划数据
     */
    @Override
    public List<ActiveTemporaryTimeEntity> getActiveList() {
        String hql="FROM ActiveTemporaryTimeEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<ActiveTemporaryTimeEntity> list=query.list();
        return list;
    }

    @Override
    public ClassificationTemporaryTimeEntity queryClass(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from classification_temporary_time s WHERE  s.CURRENT_ID=:id").addEntity(ClassificationTemporaryTimeEntity.class);
        sqlQuery.setParameter("id", id);
        List<ClassificationTemporaryTimeEntity> classifc = sqlQuery.list();
        if(classifc.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return classifc.get(0);
    }

    @Override
    public ClassificationTemporaryTimeEntity queryClassforgradle(String id,String grad) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from classification_temporary_time s WHERE s.CURRENT_ID=:id and s.GRADED=:grad").addEntity(ClassificationTemporaryTimeEntity.class);
        sqlQuery.setParameter("id", id);
        sqlQuery.setParameter("grad", grad);
        List<ClassificationTemporaryTimeEntity> classifc = sqlQuery.list();
        if(classifc.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return classifc.get(0);
    }

    @Override
    public ClassificationTemporaryTimeEntity queryClassfour(String id, String type) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from classification_temporary_time s WHERE s.CURRENT_ID=:id and s.GRADED='4'and s.TYPE=:typ").addEntity(ClassificationTemporaryTimeEntity.class);
        sqlQuery.setParameter("id", id);
        sqlQuery.setParameter("typ", type);
        List<ClassificationTemporaryTimeEntity> classifc = sqlQuery.list();
        if(classifc.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return classifc.get(0);
    }

    /**
     * 查询临时表数据
     **/
    @Override
    public ClassificationTemporaryTimeEntity queryByParentId(String parentId) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from classification_temporary_time s WHERE s.PARENT_ID=:parentId ").addEntity(ClassificationTemporaryTimeEntity.class);
        sqlQuery.setParameter("parentId", parentId);
        List<ClassificationTemporaryTimeEntity> classifc = sqlQuery.list();
        if(classifc.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return classifc.get(0);
    }

    @Override
    public void createClass(ClassificationTemporaryTimeEntity ClassificationTemporaryTime) {
        Session session = getCurrentSession();
        session.save(ClassificationTemporaryTime);
        session.flush();
    }

    @Override
    public void updateClass(ClassificationTemporaryTimeEntity ClassificationTemporaryTime) {
        Session session = getCurrentSession();
        session.update(ClassificationTemporaryTime);
        session.flush();
        session.close();
    }

    /**
     * 获取所有三级分类数据
     */
    @Override
    public List<ClassificationTemporaryTimeEntity> getClassList() {
        String hql="FROM ClassificationTemporaryTimeEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<ClassificationTemporaryTimeEntity> list=query.list();
        return list;
    }
}
