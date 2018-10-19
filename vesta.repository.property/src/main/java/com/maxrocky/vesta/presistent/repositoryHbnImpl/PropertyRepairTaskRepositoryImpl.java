package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyRepairTaskEntity;
import com.maxrocky.vesta.domain.repository.PropertyRepairTaskRepository;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/22.
 * 物业报修任务表数据操作实现
 */
@Repository
public class PropertyRepairTaskRepositoryImpl implements PropertyRepairTaskRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 添加物业报修任务
     * @param propertyRepairTaskEntity
     */
    @Override
    public void createRepairTask(PropertyRepairTaskEntity propertyRepairTaskEntity) {
        Session session = getCurrentSession();
        session.save(propertyRepairTaskEntity);
        session.flush();
    }

    /**
     * 获取任务进展（业主端）[已废弃]
     * @param id
     * @return
     */
    @Override
    public List<Object[]> getTaskProgress(String id) {
        String hql="SELECT DISTINCT taskName,taskContent,taskDate,repairId,taskStatus FROM PropertyRepairTaskEntity " +
                "WHERE repairId='"+id+"' AND taskName IS NOT NULL ORDER BY taskDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<Object[]> taskList=query.list();
        return taskList;
    }

    /**
     * 通过报修单id获取任务内容(员工端)
     * @param id：报修单id
     * @return
     */
    @Override
    public List<PropertyRepairTaskEntity> getTasksProgress(String id) {
        String hql="FROM PropertyRepairTaskEntity WHERE repairId='"+id+"' AND (taskStatus='5' OR taskStatus='7') " +
                "AND taskNode='正在处理中' ORDER BY taskDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 通过报修单id获取任务信息
     * @param id
     * @return
     */
    @Override
    public PropertyRepairTaskEntity getTaskInfo(String id) {
        String hql="FROM PropertyRepairTaskEntity WHERE taskId='"+id+"' ORDER BY taskDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        if(taskList.size()>0){
            return taskList.get(0);
        }
        return null;
    }

    /**
     * 获取维修人员id(已废弃)
     * @param id：任务id
     * @return
     */
    @Override
    public PropertyRepairTaskEntity getTaskUserId(String id) {
        String hql="FROM PropertyRepairTaskEntity WHERE taskId='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        if(taskList.size()>0){
            return taskList.get(0);
        }
        return null;
    }
    /**
     * 获取提交报修的任务时间
     * @param id
     * @return
     */
    @Override
    public List<PropertyRepairTaskEntity> getTaskDateOne(String id) {
        String hql="FROM PropertyRepairTaskEntity WHERE repairId='"+id+"' AND (taskStatus='0' OR taskStatus='1') " +
                "ORDER BY taskDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 获取派工信息的任务时间
     * @param id
     * @return
     */
    @Override
    public List<PropertyRepairTaskEntity> getTaskDateTwo(String id) {
        String hql="FROM PropertyRepairTaskEntity WHERE repairId='"+id+"' AND (taskStatus='2' OR taskStatus='4') " +
                " ORDER BY taskDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        return taskList;
    }
    /**
     * 获取维修进展的任务时间
     * @param id
     * @return
     */
    @Override
    public List<PropertyRepairTaskEntity> getTaskDateThree(String id) {
        String hql="FROM PropertyRepairTaskEntity WHERE repairId='"+id+"' AND taskStatus='4' ORDER BY taskDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 获取维修完成的任务时间
     * @param id
     * @return
     */
    @Override
    public List<PropertyRepairTaskEntity> getTaskDateFour(String id) {
        String hql="FROM PropertyRepairTaskEntity WHERE repairId='"+id+"' AND (taskStatus='10' OR taskStatus='16') " +
                "ORDER BY taskDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 获取业主回访的任务时间
     * @param id
     * @return
     */
    @Override
    public List<PropertyRepairTaskEntity> getTaskDateFive(String id) {
        String hql="FROM PropertyRepairTaskEntity WHERE repairId='"+id+"' AND (taskStatus='13' OR taskStatus='14') " +
                "ORDER BY taskDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 获取报修受理的任务时间(员工端)
     * @param id
     * @return
     */
    @Override
    public List<PropertyRepairTaskEntity> getTaskDateSix(String id) {
        String hql="FROM PropertyRepairTaskEntity WHERE repairId='"+id+"' AND taskStatus='6' ORDER BY taskDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 获取报修关闭的任务时间(员工端)
     * @param id
     * @return
     */
    @Override
    public List<PropertyRepairTaskEntity> getTaskDateSeven(String id) {
        String hql="FROM PropertyRepairTaskEntity WHERE repairId='"+id+"' AND taskStatus='9' ORDER BY taskDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        return taskList;
    }

    @Override
    public List<Object[]> getPersonalCollection(String userId) {
        Session session = getCurrentSession();
        /*
        String sql = "select PRT.TASK_ID,PRT.TASK_DATE,PRT.TASK_CONTENT,PRT.READ_STATUS,PRT.TASK_NAME,PRT.REPAIR_ID,PR.CONTENT from property_repair PR,property_repair_task PRT where PRT.REPAIR_ID = PR.REPAIR_ID" +
//                " AND  PR.CREATE_BY = ? ORDER BY PRT.READ_STATUS ASC ,PRT.TASK_DATE DESC";
                  " AND  PR.CREATE_BY = ? group by PRT.REPAIR_ID,PRT.TASK_NAME,PRT.TASK_CONTENT ORDER BY PRT.READ_STATUS ASC ,PRT.TASK_DATE DESC";
        */
        String sql = " select * from ( select PRT.TASK_ID,PRT.TASK_DATE,PRT.TASK_CONTENT,PRT.READ_STATUS,PRT.TASK_NAME,PRT.REPAIR_ID,PR.CONTENT "+
                     " from property_repair PR,property_repair_task PRT "+
                     " where PRT.REPAIR_ID = PR.REPAIR_ID AND PR.CREATE_BY = ? and PRT.READ_STATUS = 0 order BY PRT.TASK_DATE DESC ) b "+
                     " group by b.REPAIR_ID ORDER BY b.TASK_DATE DESC ";
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, userId);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public PropertyRepairTaskEntity getPropertyRepairTaskById(String taskId) {
        String hql="FROM PropertyRepairTaskEntity WHERE taskId='"+taskId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairTaskEntity> taskList=query.list();
        if(taskList.size() > 0 ){
            return taskList.get(0);
        }

        return null;
    }

    @Override
    public void editPropertyRepairTask(PropertyRepairTaskEntity propertyRepairTaskEntity) {
        Session session = getCurrentSession();
        session.update(propertyRepairTaskEntity);
        session.flush();
    }

    @Override
    public String getPersonalCollectionNum(String userId) {
        Session session = getCurrentSession();
        String sql = "select count(PR.REPAIR_ID) from property_repair PR,property_repair_task PRT where PRT.REPAIR_ID = PR.REPAIR_ID" +
                " AND  PR.CREATE_BY = ?  AND PRT.READ_STATUS = '0'";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, userId);

        return query.uniqueResult().toString();
    }

    /**
     * 通过报修单Id及维修状态值修改该状态(多条相同状态)为已读
     * @param repairId  报修单Id
     * @param taskStatus    维修状态值
     */
    public void updateRepairTaskReadStatus(String repairId,String taskStatus){
        Session session = getCurrentSession();
        String sql = " update property_repair_task set READ_STATUS = '1' where REPAIR_ID = ? and TASK_STATUS = ? ";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0,repairId);
        sqlQuery.setParameter(1,taskStatus);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }
}