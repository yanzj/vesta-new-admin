package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.model.OrganizeEntity;
import com.maxrocky.vesta.domain.repository.OrganizeRepository;
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
 * Created by chen on 2016/6/2.
 */
@Repository
public class OrganizeRepositoryImpl extends HibernateDaoImpl implements OrganizeRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addOrganize(OrganizeEntity organizeEntity) {
        Session session = getCurrentSession();
        session.save(organizeEntity);
        session.flush();
    }

    @Override
    public List<OrganizeEntity> getOrganizeInProject(String projectId) {
        String hql = "from OrganizeEntity where organizeId in (select authorityId from RoleDataEntity where dataType = '1'" +
                " and authorityType = '2' and status = '1' and dataId=:projectId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        List<OrganizeEntity> organizeEntities = query.list();
        return organizeEntities;
    }

    @Override
    public List<OrganizeEntity> getOrganizeInProjectNum(String projectNum) {
        String hql = "from OrganizeEntity where organizeId in (select a.authorityId from RoleDataEntity a,HouseProjectEntity b where a.dataType = '1'" +
                " and a.authorityType = '2' and a.status = '1' and a.dataId=b.id and b.pinyinCode=:projectNum)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectNum",projectNum);
        List<OrganizeEntity> organizeEntities = query.list();
        return organizeEntities;
    }

    @Override
    public List<HouseProjectEntity> getHouseProject() {
        StringBuffer hql = new StringBuffer(" FROM HouseProjectEntity ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<HouseProjectEntity> list = query.list();
        return list;
    }

    @Override
    public List<OrganizeEntity> getOrganizeOutProject(String projectId) {
        String hql = "from OrganizeEntity where organizeId not in (select distinct authorityId from RoleDataEntity where dataType = '1'" +
                " and authorityType = '2' and status = '1' and dataId=:projectId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        List<OrganizeEntity> organizeEntities = query.list();
        return organizeEntities;
    }

    @Override
    public List<OrganizeEntity> getOrganizeInRoleSet(String roleSetId) {
        String hql = "from OrganizeEntity where organizeId in (select distinct authorityId from RoleDataEntity where dataType = '2'" +
                " and authorityType = '2' and status = '1' and dataId =:roleSetId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("roleSetId",roleSetId);
        List<OrganizeEntity> organizeEntities = query.list();
        return organizeEntities;
    }

    @Override
    public List<OrganizeEntity> getOrganizeOutRoleSet(String roleSetId) {
        String hql = "from OrganizeEntity where organizeId not in (select distinct authorityId from RoleDataEntity where dataType = '2'" +
                " and authorityType = '2' and status = '1' and dataId =:roleSetId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("roleSetId",roleSetId);
        List<OrganizeEntity> organizeEntities = query.list();
        return organizeEntities;
    }

    @Override
    public List<OrganizeEntity> getOrganizeByStaffId(String staffId) {
        String hql = "from OrganizeEntity where organizeId in (select distinct organizeId from OrganizeUsermapEntity where staffId=:staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        List<OrganizeEntity> organizeEntities = query.list();
        return organizeEntities;
    }

    @Override
    public List<OrganizeEntity> getOrganizeList() {
        String hql = "from OrganizeEntity where organizeStatus='1'";
        Query query = getCurrentSession().createQuery(hql);
        List<OrganizeEntity> organizeEntities = query.list();
        return organizeEntities;
    }

    @Override
    public List<OrganizeEntity> getOrganizes(OrganizeEntity organizeEntity,WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from OrganizeEntity where 1=1";
        if(!StringUtil.isEmpty(organizeEntity.getOrganizeName())){
            hql += "and organizeName like '%"+organizeEntity.getOrganizeName()+"%'";
        }
        hql += "and organizeStatus = '1' order by orderNum,modifyTime desc";
        Query query = getCurrentSession().createQuery(hql);
        List<OrganizeEntity> organizeEntities = query.list();
        if(webPage !=null){
            return this.findByPage(hql, webPage,params);
        }
        return organizeEntities;
    }

    @Override
    public OrganizeEntity getOrganizeDetail(String organizeId) {
        String hql = "from OrganizeEntity where organizeId =:organizeId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("organizeId",organizeId);
        OrganizeEntity organizeEntity = (OrganizeEntity) query.uniqueResult();
        return organizeEntity;
    }

    @Override
    public void delOrganize(OrganizeEntity organizeEntity) {
        Session session = getCurrentSession();
        session.update(organizeEntity);
        session.flush();
    }

    @Override
    public void updateOrganize(OrganizeEntity organizeEntity) {
        Session session = getCurrentSession();
        session.update(organizeEntity);
        session.flush();
    }

    @Override
    public OrganizeEntity getOrganizeInfo(String crmId) {
        String hql="FROM OrganizeEntity WHERE crmId='"+crmId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<OrganizeEntity> organizeEntities = query.list();
        if(organizeEntities.size()>0){
            return organizeEntities.get(0);
        }
        return null;
    }

}
