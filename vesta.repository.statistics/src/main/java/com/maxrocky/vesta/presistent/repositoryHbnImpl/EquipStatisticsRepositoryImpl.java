package com.maxrocky.vesta.presistent.repositoryHbnImpl;


import com.maxrocky.vesta.domain.model.EquipStatisticsEntity;
import com.maxrocky.vesta.domain.model.PropertyRepairEntity;
import com.maxrocky.vesta.domain.model.PropertyServicesEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.repository.EquipStatisticsRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmei on 2016/2/17.
 */
@Repository
public class EquipStatisticsRepositoryImpl extends HibernateDaoImpl implements EquipStatisticsRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() { return sessionFactory.openSession();  }
    @Override
    public List<EquipStatisticsEntity> EquipManage(EquipStatisticsEntity equipStatisticsEntity, WebPage webPage) {
        StringBuffer hql = new StringBuffer(" from EquipStatisticsEntity as e where 1=1 ");
        List<EquipStatisticsEntity> equipStatisticsList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        if(!"0".equals(equipStatisticsEntity.getProjectId())&&null!=equipStatisticsEntity.getProjectId()){
            hql.append(" and e.projectId = ?");
            params.add(equipStatisticsEntity.getProjectId());
        }
        if(!"0".equals(equipStatisticsEntity.getCompanyId())&&null!=equipStatisticsEntity.getCompanyId()){
            hql.append(" and e.companyId = ?");
            params.add(equipStatisticsEntity.getCompanyId());
        }
        if(!"0".equals(equipStatisticsEntity.getRegionId())&&null!=equipStatisticsEntity.getRegionId()){
            hql.append(" and e.regionId = ?");
            params.add(equipStatisticsEntity.getRegionId());
        }
        hql.append(" GROUP BY e.type");
        if(webPage !=null){
            return this.findByPage(hql.toString(), webPage,params);
        }
        equipStatisticsList =  (List<EquipStatisticsEntity>)getHibernateTemplate().find(hql.toString());
        return equipStatisticsList;
    }

    @Override
    public int getEquipCount(String projectId, String type) {
        List<EquipStatisticsEntity> equipStatisticsList = new ArrayList<>();
        String hql ="FROM EquipStatisticsEntity as es where 1=1 ";

        if(!"0".equals(projectId)&&null!=projectId){
            hql = hql + " and es.projectId = '"+ projectId +"'";
        }
        if(!"".equals(type)&&null!=type){
            hql = hql + " and es.type = '"+ type +"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        equipStatisticsList= query.list();
        return equipStatisticsList.size();
    }

    @Override
    public void addEquipManage(EquipStatisticsEntity equipStatisticsEntity) {
        Session createSession = getCurrentSession();
        createSession.save(equipStatisticsEntity);
        createSession.flush();
    }

    @Override
    public void updateEquipManage(EquipStatisticsEntity equipStatisticsEntity) {
        Session tempSession = getCurrentSession();
        if(equipStatisticsEntity != null){
            tempSession.update(equipStatisticsEntity);
            tempSession.flush();
        }
    }

    @Override
    public EquipStatisticsEntity getEquipStatistics(String projectId,String userId) {
        String hql = "from EquipStatisticsEntity e where 1=1 ";
        if(!"".equals(projectId)&&null!=projectId){
            hql+=" and e.projectId= '"+projectId+"'";
        }
        if(!"".equals(userId)&&null!=userId){
            hql+=" and e.userId= '"+userId+"'";
        }
       /* if(!"".equals(companyId)&&null!=companyId){
            hql+=" and e.companyId= '"+companyId+"'";
        }
        if(!"".equals(regionId)&&null!=regionId){
            hql+=" and e.regionId= '"+regionId+"'";
        }*/
        Query query = getCurrentSession().createQuery(hql);
        if(query.list().size()!=0){
            return (EquipStatisticsEntity)query.list().get(0);
        }else{
            return null;
        }
    }


}
