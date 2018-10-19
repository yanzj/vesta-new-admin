package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseUserBookEntity;
import com.maxrocky.vesta.domain.model.ImgActivitiesEntity;
import com.maxrocky.vesta.domain.model.SharingActivitiesEntity;
import com.maxrocky.vesta.domain.repository.SharingActivitiesRepository;
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
 * Created by sunmei on 2016/2/21.
 */
@Repository
public class SharingActivitiesRepositoryImpl extends HibernateDaoImpl implements SharingActivitiesRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {

        return sessionFactory.openSession();
    }

    @Override
    public List<SharingActivitiesEntity> SharingActivities(SharingActivitiesEntity sharingActivitiesEntity, WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM SharingActivitiesEntity sa  WHERE 1=1");
        List<SharingActivitiesEntity> sharingActivitiesList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        // 不为空则拼接搜索条件  推送范围
        // 初始化 登陆人所负责范围
        hql.append(SqlJoiningTogether.sqlStatement("sa.itemId", sharingActivitiesEntity.getItemId()));

        // 不为空则拼接搜索添加 关键字
        if (null != sharingActivitiesEntity.getTitle() && !"".equals(sharingActivitiesEntity.getTitle())&&!"".equals(sharingActivitiesEntity.getTitle())) {
            hql.append(" and sa.title like ?");
            params.add("%" + sharingActivitiesEntity.getTitle() + "%");
        }

        // 不为空则拼接搜索添加 发布时间
        if (null != sharingActivitiesEntity.getPublishStartDate()&&!"".equals(sharingActivitiesEntity.getPublishStartDate())) {
            hql.append(" and sa.publishdate >= ?");
            params.add(sharingActivitiesEntity.getPublishStartDate());
        }
        if (null != sharingActivitiesEntity.getPublishEndDate()&&!"".equals(sharingActivitiesEntity.getPublishEndDate())) {
            hql.append(" and sa.publishdate <= ?");
            params.add(sharingActivitiesEntity.getPublishEndDate());
        }
        hql.append(" ORDER BY sa.sort ASC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        sharingActivitiesList = (List<SharingActivitiesEntity>) getHibernateTemplate().find(hql.toString());

        return sharingActivitiesList;
    }


    @Override
    public void createActivities(SharingActivitiesEntity sharingActivitiesEntity) {
        Session createSession = getCurrentSession();
        createSession.save(sharingActivitiesEntity);
        createSession.flush();
    }

    @Override
    public void createActivitiesImg(ImgActivitiesEntity imgActivitiesEntity) {
        Session createSession = getCurrentSession();
        createSession.save(imgActivitiesEntity);
        createSession.flush();
    }


    @Override
    public List<SharingActivitiesEntity> getActivitiesManageByItemId(String itemId) {
        String hql = "from SharingActivitiesEntity where itemId="+itemId+"ORDER BY sort DESC";
        List<SharingActivitiesEntity> activitiesList = getCurrentSession().createQuery(hql).list();
        return activitiesList;
    }


    @Override
    public SharingActivitiesEntity getActivitiesById(String id) {
        return (SharingActivitiesEntity)getCurrentSession().get(SharingActivitiesEntity.class, id);
    }

    @Override
    public List<ImgActivitiesEntity> getActivitiesImgByImgId(String imgId) {
        String hql = "FROM ImgActivitiesEntity WHERE 1=1";
        if(!"".equals(imgId)&&null!=imgId){
            hql+="and imgId = :imgId";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("imgId", imgId);

        return query.list();
    }

    @Override
    public void updateActivities(SharingActivitiesEntity sharingActivitiesEntity) {
        Session tempSession = getCurrentSession();
        if(sharingActivitiesEntity != null){
            tempSession.update(sharingActivitiesEntity);
            tempSession.flush();
        }
    }

    @Override
    public void updateActivitiesImg(ImgActivitiesEntity imgActivitiesEntity) {
        Session tempSession = getCurrentSession();
        if(imgActivitiesEntity != null){
            tempSession.update(imgActivitiesEntity);
            tempSession.flush();
        }
    }


    @Override
    public boolean deleteActivitiesById(String activitiesId,String imgId) {
        boolean msg=false;
        Session deleteSession = getCurrentSession();
        SharingActivitiesEntity activitiesEntity = (SharingActivitiesEntity)deleteSession.get(SharingActivitiesEntity.class, activitiesId);
        List<ImgActivitiesEntity> activitiesImgList = this.getActivitiesImgByImgId(activitiesEntity.getImgId());
        if(activitiesEntity != null){
            deleteSession.delete(activitiesEntity);
            deleteSession.flush();
            msg= true;
        }
        if(activitiesImgList!=null){
            for(ImgActivitiesEntity imgActivities:activitiesImgList){
                deleteSession.delete(imgActivities);
                deleteSession.flush();
                msg= true;
            }
        }
        return msg;
    }

    @Override
    public void deleteImgById(String imgId) {
        Session deleteSession = getCurrentSession();
        ImgActivitiesEntity imgActivities = (ImgActivitiesEntity)deleteSession.get(ImgActivitiesEntity.class, imgId);
        if(imgActivities!=null){
            deleteSession.delete(imgActivities);
            deleteSession.flush();
        }

    }

    @Override
    public List<SharingActivitiesEntity> getSharingActivity(String projectId) {

        String queryHql = "from SharingActivitiesEntity where itemId='%s' order by sort      ";
        queryHql = String.format(queryHql,projectId);
        return this.sessionFactory.getCurrentSession().createQuery(queryHql).list();
    }

    @Override
    public List<ImgActivitiesEntity> getImageListByAcitvityId(String id) {

        String queryHql = "from ImgActivitiesEntity where imgId='%s'";
        queryHql = String.format(queryHql,id);

        return sessionFactory.getCurrentSession().createQuery(queryHql).list();
    }

    @Override
    public SharingActivitiesEntity getActivitiesManageBysortDown(int sort, String itemId) {
        String hql = "from SharingActivitiesEntity where itemId="+itemId+"and sort>"+sort+"ORDER BY sort ASC";
        Query query = getCurrentSession().createQuery(hql);
        if(query.list().size()!=0){
            return (SharingActivitiesEntity)query.list().get(0);
        }else{
            return null;
        }

    }

    @Override
    public SharingActivitiesEntity getActivitiesManageBysortUp(int sort, String itemId) {
        String hql = "from SharingActivitiesEntity where  itemId="+itemId+"and sort<"+sort+"ORDER BY sort DESC";
        Query query = getCurrentSession().createQuery(hql);
        if(query.list().size()!=0){
            return (SharingActivitiesEntity)query.list().get(0);
        }else{
            return null;
        }

    }
}