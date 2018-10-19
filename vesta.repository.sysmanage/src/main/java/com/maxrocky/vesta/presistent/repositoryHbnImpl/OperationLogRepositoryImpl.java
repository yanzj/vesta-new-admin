package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.LoginLogEntity;
import com.maxrocky.vesta.domain.model.OperationLogEntity;
import com.maxrocky.vesta.domain.repository.OperationLogRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmei on 2016/2/18.
 */
@Repository
public class OperationLogRepositoryImpl extends HibernateDaoImpl implements OperationLogRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    @Override
    public List<OperationLogEntity> OperationLogManage(OperationLogEntity operationLogEntity, WebPage Page) {
        StringBuffer hql = new StringBuffer("FROM OperationLogEntity as ol where 1=1");
        List<OperationLogEntity> operationLogList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        // 不为空则拼接搜索条件
        if(!"".equals(operationLogEntity.getProjectId())&&null!=operationLogEntity.getProjectId()){//项目id
            hql.append(" and ol.projectId = ?");
            params.add(operationLogEntity.getProjectId());
        }
        if(!"".equals(operationLogEntity.getUserName())&&null!=operationLogEntity.getUserName()){//用户名
            hql.append(" and ol.userName like ?");
            params.add("%"+operationLogEntity.getUserName()+"%");
        }
        if(!"".equals(operationLogEntity.getContent())&&null!=operationLogEntity.getContent()){//内容
            hql.append(" and ol.content like ?");
            params.add("%"+operationLogEntity.getContent() +"%");
        }
        // 不为空则拼接搜索添加 时间
        if(null != operationLogEntity.getStartDate()){
            hql.append(" and ol.time >= ?");
            params.add(operationLogEntity.getStartDate());
        }
        if(null != operationLogEntity.getEndDate()){
            hql.append(" and ol.time <= ?");
            params.add(operationLogEntity.getEndDate());
        }
        if(Page != null){
            return this.findByPage(hql.toString(), Page, params);
        }

        operationLogList =  ( List<OperationLogEntity>)getHibernateTemplate().find(hql.toString());
        return operationLogList;
    }

    @Override
    public void addOperationLog(OperationLogEntity operationLogEntity) {
        Session createSession = getCurrentSession();
        createSession.save(operationLogEntity);
        createSession.flush();
    }
}
