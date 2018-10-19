package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.MessagePushLogEntity;
import com.maxrocky.vesta.domain.model.MessagePushRepairEntity;
import com.maxrocky.vesta.domain.model.MessagePushTargeEntity;
import com.maxrocky.vesta.domain.model.MessagePushUserEntity;
import com.maxrocky.vesta.domain.repository.MessageRepairPushRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Magic on 2017/7/13.
 */
@Repository
public class MessageRepairPushRepositoryImpl extends HibernateDaoImpl implements MessageRepairPushRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.openSession();
    }

    /**
     * 根据用户idList查询匹配每台app记录的别名
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    @Override
    public List<String> getaAliasByUserId(List<String> userId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ALIAS ");
        sql.append(" from message_push_user ");
        sql.append(" where USER_ID in(:userId)  ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameterList("userId", userId);
        return (List<String>) query.list();
    }

    /**
     * 获取消息推送的标题和内容 1.员工2.经理
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    @Override
    public MessagePushTargeEntity getTargetByType(String type1) {
        String hql = "from MessagePushTargeEntity  where messageType = '1' and type=:type1";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("type1",type1);
        List<MessagePushTargeEntity> messageTargetEntities = query.list();
        if(messageTargetEntities!=null && messageTargetEntities.size()>0){
            return messageTargetEntities.get(0);
        }else{
            return null;
        }
    }

    /**
     * 根据id 修改推送记录（报修单）
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    @Override
    public void updatePush(String repairId,List<String> alias) {
        String sql = "update  message_push_repair  set PUSH_STATUS='1' where  REPAIR_ID=:repairId and ALIAS in(:alias)  ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("repairId",repairId);
        query.setParameterList("alias",alias);
        query.executeUpdate();
    }

    /**
     * Describe:创建接口日志信息(消息推送)
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    @Override
    public void createPlanLog(MessagePushLogEntity messagePushLogEntity) {
        Session session = getCurrentSession();
        session.save(messagePushLogEntity);
        session.flush();
    }

    /**
     * 根据用户idList查询匹配 登录记录人员设备信息
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    @Override
    public List<MessagePushUserEntity> getaPushUserByUserId(List<String> userId, String type) {
        String hql="FROM MessagePushUserEntity WHERE userId in(:useridList) and messageType =:atype ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameterList("useridList",userId);
        query.setParameter("atype",type);
        List<MessagePushUserEntity> planCRMList=query.list();
        return planCRMList;
    }

    /**
     * 保存保修单消息推送记录实体信息
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    @Override
    public void saveMessagePushRepairEntity(MessagePushRepairEntity pushRepairEntity) {
        Session session = getCurrentSession();
        session.save(pushRepairEntity);
        session.flush();
    }

    /**
     * 根据登录记录设备码id查询信息
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    @Override
    public MessagePushUserEntity getMessagePushUser(String id) {
        String hql = "from MessagePushUserEntity  where registrationId =:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        List<MessagePushUserEntity> pushUserEntity = query.list();
        if(pushUserEntity!=null && pushUserEntity.size()>0){
            return pushUserEntity.get(0);
        }else{
            return null;
        }
    }

    /**
     * 修改登录记录设备码信息
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    @Override
    public void updateMessagePushUser(MessagePushUserEntity messagePushUserEntity) {
        Session session = getCurrentSession();
        session.update(messagePushUserEntity);
        session.flush();
    }

    /**
     * 保存登录记录设备码信息
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    @Override
    public void saveMessagePushUser(MessagePushUserEntity messagePushUserEntity) {
        Session session = getCurrentSession();
        session.save(messagePushUserEntity);
        session.flush();
    }
}
