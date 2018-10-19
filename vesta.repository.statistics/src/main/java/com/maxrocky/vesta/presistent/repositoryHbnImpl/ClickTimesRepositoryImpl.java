package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ClickTimesEntity;
import com.maxrocky.vesta.domain.model.ClickTimesPageEntity;
import com.maxrocky.vesta.domain.repository.ClickTimesRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmei on 2016/2/15.
 */
@Repository
public class ClickTimesRepositoryImpl extends HibernateDaoImpl implements ClickTimesRepository{
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() { return sessionFactory.openSession();  }

    @Override
    public List<ClickTimesEntity> ClickManage(ClickTimesEntity clickTimesEntity,  WebPage Page) {
        StringBuffer hql = new StringBuffer("FROM ClickTimesEntity as ce where 1=1");
        List<ClickTimesEntity> ClickTimesList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        // 初始化 登陆人所负责范围
        hql.append(SqlJoiningTogether.sqlStatement("ce.projectId", clickTimesEntity.getProjectId()));
        if(!"0".equals(clickTimesEntity.getCompanyId())&&null!=clickTimesEntity.getCompanyId()){
            hql.append(" and ce.companyId like ?");
            params.add("%"+clickTimesEntity.getCompanyId()+"%");
        }
        if(!"0".equals(clickTimesEntity.getRegionId())&&null!=clickTimesEntity.getRegionId()){
            hql.append(" and ce.regionId like ?");
            params.add("%"+clickTimesEntity.getRegionId()+"%");
        }

        if(Page != null){
            return this.findByPage(hql.toString(), Page, params);
        }

        ClickTimesList =  ( List<ClickTimesEntity>)getHibernateTemplate().find(hql.toString());
        return ClickTimesList;

    }

    @Override
    public ClickTimesPageEntity getClickNums(ClickTimesEntity clickTimesEntity) {

        String hql = "FROM ClickTimesPageEntity as ce where 1=1";
        if(null!=clickTimesEntity.getProjectId()&&!clickTimesEntity.getProjectId().equals("0")&&!clickTimesEntity.getProjectId().equals("")){
            hql = hql + " and ce.projectId = '"+ clickTimesEntity.getProjectId() +"'";
        }

        if(null!=clickTimesEntity.getCompanyId()&&!clickTimesEntity.getCompanyId().equals("0")&&!clickTimesEntity.getCompanyId().equals("")){
            hql = hql + " and ce.companyId = '"+ clickTimesEntity.getCompanyId() +"'";
        }
        if(null!=clickTimesEntity.getRegionId()&&!clickTimesEntity.getRegionId().equals("0")&&!clickTimesEntity.getRegionId().equals("")){
            hql = hql + " and ce.regionId = '"+ clickTimesEntity.getRegionId() +"'";
        }

        Query query = getCurrentSession().createQuery(hql);
        List<ClickTimesPageEntity> clickTimesPageList =  query.list();
        if(clickTimesPageList.size()!=0){
            return clickTimesPageList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public ClickTimesPageEntity getClickPageeNums(ClickTimesPageEntity clickTimesPageEntity) {
        String hql = "FROM ClickTimesPageEntity as ce where 1=1";
        if(null!=clickTimesPageEntity.getProjectId()&&!clickTimesPageEntity.getProjectId().equals("0")&&!clickTimesPageEntity.getProjectId().equals("")){
            hql = hql + " and ce.projectId = '"+ clickTimesPageEntity.getProjectId() +"'";
        }

        if(null!=clickTimesPageEntity.getCompanyId()&&!clickTimesPageEntity.getCompanyId().equals("0")&&!clickTimesPageEntity.getCompanyId().equals("")){
            hql = hql + " and ce.companyId = '"+ clickTimesPageEntity.getCompanyId() +"'";
        }
        if(null!=clickTimesPageEntity.getRegionId()&&!clickTimesPageEntity.getRegionId().equals("0")&&!clickTimesPageEntity.getRegionId().equals("")){
            hql = hql + " and ce.regionId = '"+ clickTimesPageEntity.getRegionId() +"'";
        }

        Query query = getCurrentSession().createQuery(hql);
        List<ClickTimesPageEntity> clickTimesPageList =  query.list();
        if(clickTimesPageList.size()!=0){
            return clickTimesPageList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<ClickTimesPageEntity> ClickPageManage(ClickTimesEntity clickTimesEntity, WebPage Page) {
        StringBuffer hql = new StringBuffer("FROM ClickTimesPageEntity as ce where 1=1");
        List<ClickTimesPageEntity> ClickTimesPageList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        if(!"0".equals(clickTimesEntity.getProjectId())&&null!=clickTimesEntity.getProjectId()){
            hql.append(" and ce.projectId like ?");
            params.add("%"+clickTimesEntity.getProjectId()+"%");
        }
        if(!"0".equals(clickTimesEntity.getCompanyId())&&null!=clickTimesEntity.getCompanyId()){
            hql.append(" and ce.companyId like ?");
            params.add("%"+clickTimesEntity.getCompanyId()+"%");
        }
        if(!"0".equals(clickTimesEntity.getRegionId())&&null!=clickTimesEntity.getRegionId()){
            hql.append(" and ce.regionId like ?");
            params.add("%"+clickTimesEntity.getRegionId()+"%");
        }

        if(Page != null){
            return this.findByPage(hql.toString(), Page, params);
        }

        ClickTimesPageList =  ( List<ClickTimesPageEntity>)getHibernateTemplate().find(hql.toString());
        return ClickTimesPageList;
    }

    @Override
    public void AddClickTimesPage(ClickTimesPageEntity clickTimesPageEntity) {
        Session createSession = getCurrentSession();
        createSession.save(clickTimesPageEntity);
        createSession.flush();
    }

    @Override
    public void AddClickTimes(ClickTimesEntity clickTimesEntity) {
        Session createSession = getCurrentSession();
        createSession.save(clickTimesEntity);
        createSession.flush();
    }

    @Override
    public void updateClickTimesPage(ClickTimesPageEntity clickTimesPageEntity) {
        Session tempSession = getCurrentSession();
        if(clickTimesPageEntity != null){
            tempSession.update(clickTimesPageEntity);
            tempSession.flush();
        }
    }

    @Override
    public void updateClickTimes(ClickTimesEntity clickTimesEntity) {
        Session tempSession = getCurrentSession();
        if(clickTimesEntity != null){
            tempSession.update(clickTimesEntity);
            tempSession.flush();
        }
    }

    @Override
    public ClickTimesEntity getClickTimes(String projectId, String companyId, String regionId) {
        String hql = "from ClickTimesEntity where 1=1";
        if(null!=projectId&&!"".equals(projectId)&&!"0".equals(projectId)){
            hql+=" and projectId='" + projectId + "'";
        }
        if(null!=companyId&&!"".equals(companyId)&&!"0".equals(companyId)){
            hql+=" and companyId='" + companyId + "'";
        }
        if(null!=regionId&&!"".equals(regionId)&&!"0".equals(regionId)){
            hql+=" and regionId='" + regionId + "'";
        }

        Query query = getCurrentSession().createQuery(hql);
        if(query.list().size()!=0){
            return (ClickTimesEntity)query.list().get(0);
        }else{
            return null;
        }
    }

    @Override
    public ClickTimesPageEntity getClickTimesPage(String projectId, String companyId, String regionId) {
        String hql = "from ClickTimesPageEntity where 1=1";
        if(null!=projectId&&!"".equals(projectId)&&!"0".equals(projectId)){
            hql+=" and projectId='" + projectId + "'";
        }
        if(null!=companyId&&!"".equals(companyId)&&!"0".equals(companyId)){
            hql+=" and companyId='" + companyId + "'";
        }
        if(null!=regionId&&!"".equals(regionId)&&!"0".equals(regionId)){
            hql+=" and regionId='" + regionId + "'";
        }
        Query query = getCurrentSession().createQuery(hql);
        if(query.list().size()!=0){
            return (ClickTimesPageEntity)query.list().get(0);
        }else{
            return null;
        }
    }
}
