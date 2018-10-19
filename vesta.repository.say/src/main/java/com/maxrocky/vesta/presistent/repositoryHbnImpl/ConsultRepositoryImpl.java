package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ConsultEntity;
import com.maxrocky.vesta.domain.repository.ConsultRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.Page;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/1/21.
 */
@Repository
public class ConsultRepositoryImpl extends HibernateDaoImpl implements ConsultRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public void AddConsult(ConsultEntity consultEntity) {
        Session session = getCurrentSession();
        session.save(consultEntity);
        session.flush();
    }

    @Override
    public List<ConsultEntity> getConsults(String userId,Page page) {
        String hql = "from ConsultEntity where userId = :userId and status ="+ConsultEntity.STATUS_VALID+" order by crtTime desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<ConsultEntity> consultEntities = query.list();
        return consultEntities;
    }

    @Override
    public ConsultEntity getConsultDetail(String id) {
        String hql = "from ConsultEntity where id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        ConsultEntity consultEntity = (ConsultEntity) query.uniqueResult();
        return consultEntity;
    }

    /**
     * 后台获取咨询列表
     * @param consult
     * @param webPage
     * @return
     */
    @Override
    public List<ConsultEntity> queryConsultEntity(ConsultEntity consult, WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM ConsultEntity as c WHERE 1=1");
        List<ConsultEntity> consultEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        // 初始化 登陆人查询范围
        hql.append(SqlJoiningTogether.sqlStatement("c.projectId", consult.getAddress()));

        // 拼接状态为 1正常状态 未被删除
        hql.append(" and c.status = '1'");

        // 不为空则拼接搜索条件 项目
        if(null != consult.getProjectId() && !"".equals(consult.getProjectId())){
            if(!"0".equals(consult.getProjectId())){
                hql.append(" and c.projectId like ?");
                params.add("%"+ consult.getProjectId() +"%");
            }
        }

        // 不为空则拼接搜索条件 咨询开始时间
        if(null != consult.getUserName() && !"".equals(consult.getUserName())){
            hql.append(" and c.crtTime >= ?");
            params.add(DateUtils.parse(consult.getUserName() + " 00:00:00"));
        }
        // 不为空则拼接搜索条件 咨询结束时间
        if(null != consult.getMobile() && !"".equals(consult.getMobile())){
            hql.append(" and c.crtTime <= ?");
            params.add(DateUtils.parse(consult.getMobile() + " 23:59:59"));
        }
        // 不为空则拼接搜索条件 回复状态
        if(null != consult.getReplyStatus() && !"".equals(consult.getReplyStatus())){
            if(!"0".equals(consult.getReplyStatus())){
                hql.append(" and c.replyStatus like ?");
                params.add("%"+ consult.getReplyStatus() +"%");
            }
        }
        // 不为空则拼接搜索条件 咨询内容
        if(null != consult.getContent() && !"".equals(consult.getContent())){
            hql.append(" and c.content like ?");
            params.add("%"+ consult.getContent() +"%");
        }
        hql.append(" ORDER BY c.crtTime DESC");

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        consultEntityList = (List<ConsultEntity>)getHibernateTemplate().find(hql.toString());
        return consultEntityList;
    }

    /**
     * 根据咨询信息ID查询详情
     * @param id
     * @return
     */
    @Override
    public ConsultEntity queryConsultDetail(String id) {
        String hql = "from ConsultEntity as c where c.id='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<ConsultEntity> consultEntity=query.list();
        if(consultEntity.size()>0){
            return consultEntity.get(0);
        }
        return null;
    }

    /**
     * 更新咨询信息
     * @param consultEntity
     * @return
     */
    @Override
    public boolean chengeConsult(ConsultEntity consultEntity) {
        if(null !=consultEntity){
            Session session = getCurrentSession();
            session.update(consultEntity);
            session.flush();
            return true;
        }
        return false;
    }


}
