package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.MessageDepartmentEntity;
import com.maxrocky.vesta.domain.model.MessageManageEntity;
import com.maxrocky.vesta.domain.repository.MessageManageRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmei on 2016/3/3.
 */
@Repository
public class MessageManageRepositoryImpl extends HibernateDaoImpl implements MessageManageRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    @Override
    public List<MessageManageEntity> MessageList(MessageManageEntity messageManageEntity, WebPage webPage) {
        List<MessageManageEntity> MessageManageList=new ArrayList<>();
        StringBuffer hql = new StringBuffer("FROM MessageManageEntity as l where 1=1");
        List<Object> params = new ArrayList<Object>();
        // 不为空则拼接搜索条件  推送范围
        // 初始化 登陆人所负责范围
        hql.append(SqlJoiningTogether.sqlStatement("l.projectId", messageManageEntity.getProjectId()));
        hql.append("  ORDER BY l.creatDate desc");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        MessageManageList = (List<MessageManageEntity>) getHibernateTemplate().find(hql.toString());

        return MessageManageList;
    }

    @Override
    public void addMessage(MessageManageEntity messageManageEntity) {
        Session createSession = getCurrentSession();
        createSession.save(messageManageEntity);
        createSession.flush();
    }

    @Override
    public void addMessageDepartment(MessageDepartmentEntity messageDepartmentEntity) {
        Session createSession = getCurrentSession();
        createSession.save(messageDepartmentEntity);
        createSession.flush();
    }

    @Override
    public List<MessageDepartmentEntity> getMessageDepartment(String messageId) {
        String hql = "from MessageDepartmentEntity where messageId='"+messageId+"'";
        List<MessageDepartmentEntity> messageDepartmentList = getCurrentSession().createQuery(hql).list();
        return messageDepartmentList;
    }

    @Override
    public MessageManageEntity getMessages(String id) {
        return (MessageManageEntity)getCurrentSession().get(MessageManageEntity.class, id);
    }

    @Override
    public void updateMessage(MessageManageEntity messageManageEntity) {
        Session tempSession = getCurrentSession();
        if(messageManageEntity != null){
            tempSession.update(messageManageEntity);
            tempSession.flush();
        }

    }

    @Override
    public void deleteMessageDepartment(String adId) {
        Session deleteSession = getCurrentSession();
        MessageDepartmentEntity messageDepartment = (MessageDepartmentEntity)deleteSession.get(MessageDepartmentEntity.class, adId);
        if(messageDepartment != null){
            if(!StringUtil.isEmpty(messageDepartment.getId())){
                deleteSession.delete(messageDepartment);
                deleteSession.flush();
            }
        }
    }
}
