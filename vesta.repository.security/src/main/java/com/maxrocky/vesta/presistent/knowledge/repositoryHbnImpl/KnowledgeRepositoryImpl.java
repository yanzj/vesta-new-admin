package com.maxrocky.vesta.presistent.knowledge.repositoryHbnImpl;

import com.maxrocky.vesta.domain.knowledge.model.KnowledgeEntity;
import com.maxrocky.vesta.domain.knowledge.repository.KnowledgeRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanyn on 2017/6/5.
 */
@Repository
public class KnowledgeRepositoryImpl extends HibernateDaoImpl implements KnowledgeRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 根据id获取详情
     * @param currentId
     * @return
     */
    @Override
    public KnowledgeEntity getKnowledgeFileById(String currentId) {
        String hql = "FROM KnowledgeEntity WHERE currentId=:currentId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("currentId",currentId);
        KnowledgeEntity knowledgeEntity = (KnowledgeEntity)query.uniqueResult();
        return knowledgeEntity;
    }

    /**
     * 获取列表
     * @param map
     * @param webPage
     * @return
     */
    @Override
    public List<KnowledgeEntity> getKnowledgeFiles(Map<String,Object> map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "FROM KnowledgeEntity WHERE 1=1 ";
        if(map.get("graded") != null && !"".equals(map.get("graded").toString())){
            hql+= " AND graded='"+map.get("graded").toString()+"' ";
        }
        if(map.get("parentId") != null && !"".equals(map.get("parentId").toString())){
            hql+= " AND parentId = '"+map.get("parentId").toString()+ "' ";
        }
        if(map.get("fileName") != null && !"".equals(map.get("fileName"))){
            hql += " AND fileName LIKE '%"+map.get("fileName")+"%'";
        }
        hql+=" AND state='1' ORDER BY createDate ASC";
        if(webPage !=null){
            return this.findByPage(hql,webPage,params);
        }
        List<KnowledgeEntity> knowledgeEntityList = (List<KnowledgeEntity>) getHibernateTemplate().find(hql, params.toArray());
        return knowledgeEntityList;
    }

    /**
     * 增加
     * @param knowledgeEntity
     */
    @Override
    public void addKnowledgeFile(KnowledgeEntity knowledgeEntity) {
        Session session = getCurrentSession();
        session.save(knowledgeEntity);
        session.flush();
        session.close();
    }

    /**
     * 删除
     * @param currentId
     */
    @Override
    public void deleteKnowledgeById(String currentId) {
        String hql = "UPDATE KnowledgeEntity SET state='0',modifyDate=:modifyDate WHERE currentId=:currentId OR parentId=:parentId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyDate",new Date());
        query.setParameter("currentId",currentId);
        query.setParameter("parentId",currentId);
        query.executeUpdate();
    }

    /**
     * 根据模块获取其下属所有目录列表
     * @param currentId
     */
    @Override
    public List<String> getCurrentIdKnowledgByparentId(String currentId) {
        String hql ="SELECT currentId FROM KnowledgeEntity WHERE parentId=:parentId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("parentId",currentId);
        List<String> list =query.list();
        return list;
    }

    /**
     * 删除模块及其下级目录和文档
     * @param currentIdList
     */
    @Override
    public void deleteRelevanceKnowledgById(List<String> currentIdList) {
        String hql = "UPDATE KnowledgeEntity SET state='0',modifyDate=:modifyDate WHERE "
        +" parentId IN(:currentId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyDate",new Date());
        query.setParameterList("currentId",currentIdList);
        query.executeUpdate();
    }
}
