package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.AppRolesetEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.AppRoleSetRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/20.
 */
@Repository
public class AppRoleSetRepositoryImpl extends HibernateDaoImpl implements AppRoleSetRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 获取App角色信息
     * @param id
     * @return
     */
    @Override
    public AppRolesetEntity getAppRolesetById(String id) {
        String hql = "from AppRolesetEntity as a where a.appSetState=:appSetState and a.appSetId = :appSetId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appSetState",AppRolesetEntity.STATE_ON);
        query.setParameter("appSetId",id);
        if (query.list().size()>0){
            return (AppRolesetEntity)query.list().get(0);
        }
        return null;
    }

    @Override
    public AppRolesetEntity getAppRolesetByAppSetName(String appSetName) {
        String hql = "from AppRolesetEntity as a where a.appSetState=:appSetState and a.appSetName = :appSetName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appSetState",AppRolesetEntity.STATE_ON);
        query.setParameter("appSetName",appSetName);
        if (query.list().size()>0){
            return (AppRolesetEntity)query.list().get(0);
        }
        return null;
    }

    /**
     * 获取员工App角色列表
     * @return
     */
    @Override
    public List<AppRolesetEntity> listAppRoleset() {
        String hql = "from AppRolesetEntity as a where a.appSetState=:appSetState and a.appSetAllot=:appSetAllot";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appSetState",AppRolesetEntity.STATE_ON);
        query.setParameter("appSetAllot",AppRolesetEntity.ALLOT_YES);
        List<AppRolesetEntity> appRolesetEntities = query.list();

        return appRolesetEntities;
    }

    @Override
    public List<AppRolesetEntity> getRoleNames(String staffId) {
        String hql = "from AppRolesetEntity where appSetState='1' and appSetId in (select distinct appSetId from RoleRoleanthorityEntity where staffId =:staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        List<AppRolesetEntity> appRolesetEntities = query.list();
        return appRolesetEntities;
    }

    @Override
    public void addAppRoleSet(AppRolesetEntity appRolesetEntity) {
        Session session = getCurrentSession();
        session.save(appRolesetEntity);
        session.flush();
    }

    @Override
    public void updateAppRoleSet(AppRolesetEntity appRolesetEntity) {
        Session session = getCurrentSession();
        session.update(appRolesetEntity);
        session.flush();
    }

    @Override
    public void delAppRoleSet(AppRolesetEntity appRolesetEntity) {
        Session session = getCurrentSession();
        session.delete(appRolesetEntity);
        session.flush();
    }

    @Override
    public List<AppRolesetEntity> getAppRoleSets(String roleSetName,WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        List<AppRolesetEntity> appRolesetEntityList;
        String hql="from AppRolesetEntity where 1=1";
        if(roleSetName!=null&&!roleSetName.equals("")){
            hql += "and appSetName like '%"+roleSetName+"%'";
        }
        hql += "and appSetState='1' order by ModifyOn desc";
        if(webPage!=null){
            return this.findByPage(hql.toString(),webPage,params);
        }
        appRolesetEntityList = (List<AppRolesetEntity>) getHibernateTemplate().find(hql.toString());
        return appRolesetEntityList;
    }

    @Override
    public AppRolesetEntity getAppRoleSetDetail(String appSetId) {
        String hql="from AppRolesetEntity where appSetId=:appSetId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appSetId",appSetId);
        AppRolesetEntity appRolesetEntity = (AppRolesetEntity) query.uniqueResult();
        return appRolesetEntity;
    }

    @Override
    public List<UserPropertyStaffEntity> getSetsStaff(String appSetId) {
        String hql = "from UserPropertyStaffEntity where staffState='1' and staffId in (select distinct staffId from RoleRoleanthorityEntity where appSetId=:appSetId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appSetId",appSetId);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public List<UserPropertyStaffEntity> getSetOutStaff(UserPropertyStaffEntity userPropertyStaffEntity) {
        String hql = "from UserPropertyStaffEntity where 1=1";
        if(!StringUtil.isEmpty(userPropertyStaffEntity.getStaffName())){
            hql += "and staffName like '%"+userPropertyStaffEntity.getStaffName()+"%'";
        }
        hql += "and staffState='1' and staffId not in (select distinct staffId from RoleRoleanthorityEntity where appSetId=:appSetId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appSetId",userPropertyStaffEntity.getRoleSetId());
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }
}
