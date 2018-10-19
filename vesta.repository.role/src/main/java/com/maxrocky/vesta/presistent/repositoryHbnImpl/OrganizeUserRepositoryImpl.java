package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.AppRolesetEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.model.OrganizeUsermapEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.OrganizeUserRepository;
import com.maxrocky.vesta.utility.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/6/2.
 */
@Repository
public class OrganizeUserRepositoryImpl implements OrganizeUserRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addOrganizeUser(OrganizeUsermapEntity organizeUsermapEntity) {
        Session session = getCurrentSession();
        session.save(organizeUsermapEntity);
        session.flush();
    }

    @Override
    public void addDumpOrganizeUser(OrganizeUsermapEntity organizeUsermapEntity) {
        String sql1 = "INSERT INTO organize_usermap(ID,STAFF_ID,ORGANIZE_ID,ORG_STATUS,MODIFY_TIME) VALUES(?,?,?,1,?) ON DUPLICATE KEY UPDATE ORG_STATUS='1',MODIFY_TIME=?";
        Query query1 = getCurrentSession().createSQLQuery(sql1);
        query1.setString(0,organizeUsermapEntity.getId());
        query1.setString(1,organizeUsermapEntity.getStaffId());
        query1.setString(2,organizeUsermapEntity.getOrganizeId());
        query1.setString(3, DateUtils.format(new Date()));
        query1.setString(4, DateUtils.format(new Date()));
        query1.executeUpdate();
    }

    @Override
    public void delOrganizeUser(String organizeId) {
        String hql = "update OrganizeUsermapEntity set orgStatus='0',modifyTime=:modifyTime where organizeId=:organizeId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("organizeId",organizeId);
        query.executeUpdate();
    }

    @Override
    public void delUserOrganize(String staffId) {
        String hql = "update OrganizeUsermapEntity set orgStatus='0',modifyTime=:modifyTime where staffId=:staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("staffId",staffId);
        query.executeUpdate();
    }

    @Override
    public List<UserPropertyStaffEntity> getStaffsByOrganizeId(String organizeId) {
        String hql = "from UserPropertyStaffEntity where staffId in(select distinct staffId from OrganizeUsermapEntity where organizeId =:organizeId and orgStatus='1')";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("organizeId",organizeId);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public List<UserPropertyStaffEntity> getStaffsOutOrganize(OrganizeUsermapEntity organizeUsermapEntity) {
        String hql = "from UserPropertyStaffEntity where 1=1";
        if(!StringUtils.isEmpty(organizeUsermapEntity.getStaffId())){
            hql += "and staffName like '%"+organizeUsermapEntity.getStaffId()+"%'";
        }
        hql += "and staffId not in(select distinct staffId from OrganizeUsermapEntity where organizeId =:organizeId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("organizeId",organizeUsermapEntity.getOrganizeId());
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public OrganizeUsermapEntity getOrganizeUsermap(String organizeId, String staffId) {
        String hql = "from OrganizeUsermapEntity where organizeId=:organizeId and staffId=:staffId and orgStatus='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("organizeId",organizeId);
        query.setParameter("staffId",staffId);
        OrganizeUsermapEntity organizeUsermapEntity = (OrganizeUsermapEntity) query.uniqueResult();
        return organizeUsermapEntity;
    }

    @Override
    public List<OrganizeUsermapEntity> getOrganizeUsermapList(String organizeId) {
        String hql = "from OrganizeUsermapEntity where organizeId=:organizeId and orgStatus='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("organizeId",organizeId);
        List<OrganizeUsermapEntity> organizeUsermapEntities = query.list();
        return organizeUsermapEntities;
    }

    @Override
    public void delOrganizeStaff(OrganizeUsermapEntity organizeUsermapEntity) {
        Session session = getCurrentSession();
        session.delete(organizeUsermapEntity);
        session.flush();
    }

    @Override
    public void delOStaffs(String organizeId, String staffId) {
        String hql = "update OrganizeUsermapEntity set orgStatus='0',modifyTime=:modifyTime where organizeId=:organizeId and staffId=:staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("organizeId",organizeId);
        query.setParameter("staffId",staffId);
        query.executeUpdate();
    }

    @Override
    public List<HouseProjectEntity> getOProjectByStaff(String staffId) {
        String hql = "select dataId from RoleDataEntity where status = '1' and dataType = '1' and authorityType = '2' and authorityId in (select organizeId from OrganizeUsermapEntity where staffId='"+staffId+"')";
        Query query = getCurrentSession().createQuery(hql);
        String projectIds = query.getQueryString();
        String sql = "from HouseProjectEntity where id in ("+projectIds+")";
        Query query1 = getCurrentSession().createQuery(sql);
        List<HouseProjectEntity> houseProjectEntities = query1.list();
        return houseProjectEntities;
    }

    @Override
    public List<AppRolesetEntity> getRoleSetByStaff(String staffId) {
        String hql = "select dataId from RoleDataEntity where status = '1' and dataType = '2' and authorityType = '2' and authorityId in (select organizeId from OrganizeUsermapEntity where staffId= '"+staffId+"')";
        Query query = getCurrentSession().createQuery(hql);
        String roleSetIds = query.getQueryString();
        String sql = "from AppRolesetEntity where appSetState='1' and appSetId in ("+roleSetIds+")";
        Query query1 = getCurrentSession().createQuery(sql);
        List<AppRolesetEntity> appRolesetEntityList = query1.list();
        return appRolesetEntityList;
    }
}
