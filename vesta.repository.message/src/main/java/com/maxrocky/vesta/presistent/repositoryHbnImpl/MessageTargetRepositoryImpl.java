package com.maxrocky.vesta.presistent.repositoryHbnImpl;


import com.maxrocky.vesta.domain.model.MessageTargetEntity;
import com.maxrocky.vesta.domain.repository.MessageTargetRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.Page;
import com.maxrocky.vesta.utility.PageInfo;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/14.
 */

@Repository
public class MessageTargetRepositoryImpl extends HibernateDaoImpl implements MessageTargetRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.openSession();
    }


    /**
     * 根据用户Id查所有未读消息总数
     * @param userId
     * @return
     */
    @Override
    public int countUnreadMessage(String userId) {
        String hql = "select count(o.messageDetailId) from MessageTargetEntity as o where o.messageDeleteStatue = :messageDeleteStatue and o.messageReadStatus = :messageReadStatus and o.userId = :userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("messageDeleteStatue","1");
        query.setParameter("messageReadStatus","0");
        query.setParameter("userId",userId);
        int number = ((Number)query.iterate().next()).intValue();
//        if (query.list().size()>0){
//            return query.list().size();
//        }

        return number;
    }


    /**
     * 获取某条消息的所有收件信息
     * @param messageDetailId
     * @return
     */
    @Override
    public List<MessageTargetEntity> listMessageTargetByMessageDetailId(String messageDetailId) {
        String hql = "from MessageTargetEntity as o where o.messageDetailId = '"+messageDetailId+"' order by o.targetCreateTime desc ";
        Query query = getCurrentSession().createQuery(hql);
        List<MessageTargetEntity> messageTargetEntities = query.list();
        return messageTargetEntities;
    }

    /**
     * 查询某用户的某个模块的未读消息总数
     * @param messageType
     * @param userId
     * @return
     */
    @Override
    public int countUnreadMessageByTitleAndUser(String messageType, String userId) {
        String hql = "from MessageTargetEntity as o where o.messageReadStatus = '0' and o.userId = '"+userId+"' and o.messageType = '"+messageType+"'";
        Query query = getCurrentSession().createQuery(hql);
        int count = query.list().size();
        return count;
    }

    /**
     * 分页某用户的某个标题下所有消息
     * @param userId
     * @param page
     * @return
     */
    @Override
    public List<MessageTargetEntity> listMessageByTargetPage(String userId,Page page) {
        String hql = "from MessageTargetEntity as o where  o.messageDeleteStatue = '1' and o.userId = '"+userId+"'  order by o.targetCreateTime desc ";
        Query query = getCurrentSession().createQuery(hql);

        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());

        List<MessageTargetEntity> list = query.list();

        return list;
    }

    /**
     * 获取某人所有消息
     * @param userId
     * @param page
     *
     * @return
     */
    @Override
    public List<MessageTargetEntity> listMessageByPage(String userId, Page page) {
        String hql = "from MessageTargetEntity as o where  o.messageDeleteStatue = '1' and o.userId = '"+userId+"' order by o.targetCreateTime desc ";
        Query query = getCurrentSession().createQuery(hql);

        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());

        List<MessageTargetEntity> list = query.list();

        return list;
    }

    /**
     * 获得该模块下所有消息
     * @param userId
     * @param messageType
     * @return
     */
    @Override
    public List<MessageTargetEntity> listMessageByTarget(String userId, String messageType) {
        String hql = "from MessageTargetEntity as o where  o.userId = '"+userId+"' and o.messageType = '"+messageType+"' order by o.targetCreateTime desc ";

        Query query = getCurrentSession().createQuery(hql);

        List<MessageTargetEntity> list = query.list();

        return list;
    }
    /**
     * 获取用户在该模块下最新一条消息
     * @param userId
     * @param messageType
     * @return
     */
    @Override
    public MessageTargetEntity getMessageByTarget(String userId, String messageType) {
        String hql = "from MessageTargetEntity as o where  o.userId = '"+userId+"' and o.messageType = '"+messageType+"' order by o.targetCreateTime desc ";

        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size()>0) {
            MessageTargetEntity messageTargetEntity = (MessageTargetEntity) query.list().get(0);
            return messageTargetEntity;
        }
        else {
            return null;
        }
    }

    /**
     * 更新消息接收信息
     * @param messageTargetEntity
     * @return
     */
    @Override
    public boolean updateMessageTarget(MessageTargetEntity messageTargetEntity) {
       MessageTargetEntity mTargetEntity = getMessageTarget(messageTargetEntity.getMessageTargetId());
        if (mTargetEntity !=null){
            Session session = getCurrentSession();
            session.update(messageTargetEntity);
            session.flush();
            session.close();
            return  true;
        }
        else {
            return false;
        }
    }

    /**
     * 根据消息接收信息id查询消息接收情况
     * @param messageTargetId
     * @return
     */
    @Override
    public MessageTargetEntity getMessageTarget(String messageTargetId) {
        String hql = "from MessageTargetEntity as o where o.messageTargetId = '"+ messageTargetId+"'";
        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size()>0){
            MessageTargetEntity messageTargetEntity = (MessageTargetEntity)query.list().get(0);
            return messageTargetEntity;
        }
        else {
            return null;
        }
    }

    /**
     * 添加消息收件人
     * @param messageTargetEntity
     * @return
     */
    @Override
    public boolean saveMessageTarget(MessageTargetEntity messageTargetEntity) {
        Session session = getCurrentSession();
        session.save(messageTargetEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 批量添加消息信息
     * @param messageTargetEntities
     * @return
     */
    @Override
    public boolean saveListTarget(List<MessageTargetEntity> messageTargetEntities) throws Exception{

        if (messageTargetEntities.size()>0) {
//            this.saveAll(messageTargetEntities);
            Session session = getCurrentSession();
            session.setFlushMode(FlushMode.COMMIT);
            int i = 0;
            int j = 0;

            //计算分几个批次
            //按批次数量循环{
        // for 50
        //}
            for (MessageTargetEntity messageTargetEntity:messageTargetEntities){
                session.save(messageTargetEntity);
                i++;
                System.out.println("---------------------正在添加"+j++);
                if (i%400==0||i==messageTargetEntities.size()){
                    session.flush();
                    session.clear();
                }

            }
            session.close();
        }
        return true;
    }

    /**
     * 批量更新目标
     * @param messageTargetEntities
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateListTarget(List<MessageTargetEntity> messageTargetEntities) throws Exception {
//        this.updateAll(messageTargetEntities);
//        return false;
        if (messageTargetEntities.size()>0) {
//            this.saveAll(messageTargetEntities);
            Session session = getCurrentSession();
            session.setFlushMode(FlushMode.COMMIT);
            int i = 0;
            int j = 0;

            //计算分几个批次
            //按批次数量循环{
            // for 50
            //}
            for (MessageTargetEntity messageTargetEntity:messageTargetEntities){
                session.update(messageTargetEntity);
                i++;
                System.out.println("---------------------正在修改" + j++);
                if (i%400==0||i==messageTargetEntities.size()){
                    session.flush();
                    session.clear();
                }

            }
            session.close();
        }
//
        return true;
    }

    /**
     * 删除消息收件人
     * @param messageTargetEntity
     * @return
     */
    @Override
    public boolean deleteMessageTarget(MessageTargetEntity messageTargetEntity) {
        messageTargetEntity = getMessageTarget(messageTargetEntity.getMessageTargetId());
        if (messageTargetEntity !=null){
            Session session = getCurrentSession();
            session.delete(messageTargetEntity);
            session.flush();
            session.close();
            return  true;
        }
        else {
            return false;
        }
    }

    /**
     * 查询一段时间内的消息，并且满足未发送-0，未读-0
     * @param beforeTime
     * @param endTime
     * @return
     */
    @Override
    public List<MessageTargetEntity> getPushTarget(String beforeTime, String endTime) {
        String hql = "from MessageTargetEntity as o where o.messageDeleteStatue = :messageDeleteStatue and o.messagePushStatus = :messagePushStatus and o.messageReadStatus = :messageReadStatus and o.targetCreateTime between :beforeTime and  :endTime";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("messageDeleteStatue","1");
        query.setParameter("messagePushStatus","0");
        query.setParameter("messageReadStatus","0");
        query.setParameter("beforeTime", DateUtils.parse(beforeTime));
        query.setParameter("endTime",DateUtils.parse(endTime));
        List<MessageTargetEntity> messageTargetEntities = query.list();
        return messageTargetEntities;
    }

    /**
     * 联合查询，消息，消息内容，token
     * @param beforeTime
     * @param endTime
     * @return
     */
    @Override
    public List<Object> getPushDto(String beforeTime, String endTime) {
        String hql = "from MessageTargetEntity as o,MessageDetailEntity as m,MessageTokenEntity as t where o.messageDetailId = m.messageDetailId and o.userId = t.userId and o.messageDeleteStatue = :messageDeleteStatue and o.messagePushStatus = :messagePushStatus and o.messageReadStatus = :messageReadStatus and o.targetCreateTime between :beforeTime and  :endTime";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("messageDeleteStatue","1");
        query.setParameter("messagePushStatus","0");
        query.setParameter("messageReadStatus","0");
        query.setParameter("beforeTime", DateUtils.parse(beforeTime));
        query.setParameter("endTime",DateUtils.parse(endTime));
        List<Object> list = query.list();
        return list;
    }

    @Override
    public List<Object> getPushDto(String beforeTime, String endTime, WebPage webPage) {
        String hql="from MessageTargetEntity as o,MessageDetailEntity as m,MessageTokenEntity as t where o.messageDetailId = m.messageDetailId and o.userId = t.userId and o.userType=t.mobileType and o.messageTokenNum=t.messageTokenNum and o.messageDeleteStatue = :messageDeleteStatue and  o.messagePushStatus = :messagePushStatus and o.messageReadStatus = :messageReadStatus and o.targetCreateTime between :beforeTime and  :endTime ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("messageDeleteStatue","1");
        query.setParameter("messagePushStatus","0");
        query.setParameter("messageReadStatus","0");
        query.setParameter("beforeTime", DateUtils.parse(beforeTime));
        query.setParameter("endTime",DateUtils.parse(endTime));
        List<Object> list = query.list();
        return list;
    }

    @Override
    public int getPushCout(String phoneid) {
        String hql="from MessageTargetEntity as o where o.messageDeleteStatue = :messageDeleteStatue and  o.messagePushStatus = :messagePushStatus and o.messageReadStatus = :messageReadStatus and o.messageTokenNum = :messageTokenNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("messageDeleteStatue","1");
        query.setParameter("messagePushStatus","0");
        query.setParameter("messageReadStatus","0");
        query.setParameter("messageTokenNum",phoneid);
        List<Object> list = query.list();
        return list.size();
    }

    @Override
    public void updateIdRepair() throws Exception {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("update repair_sequence w set w.current_value='1'");

        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    @Override
    public void updateIdRectify() throws Exception {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("update print_sequence w set w.current_value='1'");
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }
}
