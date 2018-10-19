package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.PropertyRepairRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.Page;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/1/15.
 * 物业报修数据操作实现方法
 */
@Repository
public class PropertyRepairRepositoryImpl extends HibernateDaoImpl implements PropertyRepairRepository {
    /* mapper */
    @Autowired
    private MapperFacade mapper;
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**---------------------------------业主端部分-------------------------------------*/
    /**
     * 历史报修列表：未完成/完成
     * @param roomId
     * @param type
     * @return
     */
    @Override
    public List<Object[]> getHistory(String type,String roomId,Page page) {
        String hql = "";
        if ("0".equals(type)){
            //未完成报修
            hql="SELECT repairId AS id,content,state FROM PropertyRepairEntity WHERE types='"+type+"' " +
                    "AND roomId='"+roomId+"' AND memo='报修' ORDER BY createDate DESC";
        }else if ("1".equals(type)){
            //已完成报修
            hql="SELECT repairId AS id,content,state FROM PropertyRepairEntity WHERE types='"+type+"' " +
                    "AND roomId='"+roomId+"' AND memo='报修' AND datediff(NOW(), completeDate)<7 ORDER BY createDate DESC";
        }
//        String hql="SELECT repairId AS id,content,state FROM PropertyRepairEntity WHERE types='"+type+"' " +
//                "AND roomId='"+roomId+"' AND memo='报修' AND datediff(NOW(), completeDate)<7 ORDER BY createDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<Object[]> historyList=query.list();
        return historyList;
    }

    /**
     * 获取完成/未完成的总条数(已废弃)
     * @return
     */
    @Override
    public int getPageNum(String type) {
        String hql="FROM PropertyRepairEntity WHERE types='"+type+"' AND memo='报修'";
        Query query = getCurrentSession().createQuery(hql);
        int num=query.list().size();
        return num;
    }

    /**
     * 获取未完成报修数量
     * @return
     */
    @Override
    public Integer getRepairHistoryNum(UserInfoEntity user) {
        String hql="FROM PropertyRepairEntity WHERE types='0' AND memo='报修' AND createBy='"+user.getUserId()+"'";
        Query query = getCurrentSession().createQuery(hql);
        Integer count=query.list().size();
        return count;
    }

    /**
     * 添加
     * @param propertyRepairEntity
     */
    @Override
    public void saveRepair(PropertyRepairEntity propertyRepairEntity) {
        Session session = getCurrentSession();
        session.save(propertyRepairEntity);
        session.flush();
    }

    /**
     * 获取报修详情
     * @param id
     * @return
     */
    @Override
    public PropertyRepairEntity getRepairDetail(String id) {
        String hql="FROM PropertyRepairEntity WHERE repairId='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairEntity> propertyRepairEntity=query.list();
        if(propertyRepairEntity.size() > 0){
            return propertyRepairEntity.get(0);
        }
        return null;
    }

    /**
     * 修改报修单
     * @param propertyRepairEntity
     */
    @Override
    public void updateRepair(PropertyRepairEntity propertyRepairEntity) {
        Session session = getCurrentSession();
        session.update(propertyRepairEntity);
        session.flush();
        session.close();
    }

    /**
     * 获取投诉列表
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getComplaint(UserInfoEntity user,Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND memo='投诉' AND createBy='"+user.getUserId()
                +"' AND (taskStatus='1' OR taskStatus='2' OR taskStatus='3' " +
                "OR taskStatus='5' OR taskStatus='8' OR taskStatus='15' " +
                "OR taskStatus='16' OR taskStatus='17') ORDER BY createDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }
/**---------------------------------员工端部分-------------------------------------*/
    /**
     * 维修：获取待分配的任务(维修人员/维修主管)
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getRepairPending(String projectId,Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId
                +"' AND (taskStatus='0' OR taskStatus='6') AND (memo='报修' OR memo='维修') ORDER BY createDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 投诉：获取待分配的任务(客服主管)
     * 获取超过24小时未评价的维修工单
     * @param page
     * @return
     */
    @Override
    public List<Object[]> getComplaintPending(String projectId,Page page) {
        String sql="SELECT repair_id,user_name,user_phone,content,create_date,user_address,task_status " +
                "FROM property_repair WHERE types!='3' AND region_id='"+projectId+"'"
                +" AND (task_status='1' OR task_status='3') AND (memo='投诉' OR memo='客服') " +
                "UNION SELECT repair_id,user_name,user_phone,content,create_date,user_address,task_status " +
                "FROM property_repair WHERE complete_Date<DATE_ADD(NOW(),INTERVAL 2 MINUTE) " +
                "AND types!='3' AND region_id='"+projectId+"' AND task_status='10' " +
                "ORDER BY create_date DESC";
        Query query = getCurrentSession().createSQLQuery(sql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<Object[]> taskList=query.list();
        return taskList;
    }

    /**
     * 秩序：获取待分配的任务(秩序主管)
     * @param projectId
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getSequencePending(String projectId, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId
                +"' AND (taskStatus='1' OR taskStatus='3') AND memo='秩序' ORDER BY createDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 环境：获取待分配的任务(环境主管)
     * @param projectId
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getEnvironmentPending(String projectId, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId
                +"' AND (taskStatus='1' OR taskStatus='3') AND memo='环境' ORDER BY createDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 获取所有待分配的任务(管理员)
     * @param projectId
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getAllPending(String projectId, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId
                +"' AND (taskStatus='1' OR taskStatus='3' OR taskStatus='0' OR taskStatus='6') " +
                "ORDER BY createDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 获取待分配总条数(已废弃)
     * @return
     */
    @Override
    public int getTaskPendingNum() {
        String hql="SELECT DISTINCT re.repairId FROM PropertyRepairEntity AS re,PropertyRepairTaskEntity AS ta " +
                "WHERE re.repairId=ta.repairId AND (ta.taskStatus='0' OR taskStatus='6')";
        Query query = getCurrentSession().createQuery(hql);
        int num=query.list().size();
        return num;
    }

    /**
     * 维修：获取进行中的任务(维修主管)
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getRepairUnderway(String projectId,Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId
                +"' AND (memo='报修' OR memo='维修') AND (taskStatus='4' OR taskStatus='7' OR taskStatus='9' " +
                "OR taskStatus='10' OR taskStatus='12' OR taskStatus='13') ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 维修：获取自己进行中的任务(维修人员)
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getMyRepairUnderway(UserPropertyStaffEntity user,Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+user.getProjectId()+"' AND userId='"+user.getStaffId()
                +"' AND (memo='报修' OR memo='维修') AND (taskStatus='4' OR taskStatus='7' OR taskStatus='9' " +
                "OR taskStatus='10' OR taskStatus='12' OR taskStatus='13') ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 投诉：获取进行中的任务(客服主管)
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getComplaintUnderway(String projectId,Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId
                +"' AND (memo='报修' OR memo='维修' OR memo='投诉' OR memo='客服') AND " +
                "(taskStatus='2' OR taskStatus='5' OR taskStatus='8' OR taskStatus='12' " +
                "OR taskStatus='15') ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 投诉：获取自己进行中的任务(客服人员)
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getMyComplaintUnderway(UserPropertyStaffEntity user, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' " +
                "AND regionId='"+user.getProjectId()+"' AND (userId='"+user.getStaffId()+"' OR customerId='"
                +user.getStaffId()+"') AND (memo='报修' OR memo='维修' OR memo='投诉' OR memo='客服')" +
                "AND (taskStatus='2' OR taskStatus='5' OR taskStatus='8' OR taskStatus='12' " +
                "OR taskStatus='15') ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 秩序：获取进行中的任务(秩序主管)
     * @param projectId
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getSequenceUnderway(String projectId, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId
                +"' AND memo='秩序'  AND " +
                "(taskStatus='2' OR taskStatus='5' OR taskStatus='8' OR taskStatus='15') " +
                "ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 秩序：获取自己进行中的任务(秩序人员)
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getMySequenceUnderway(UserPropertyStaffEntity user, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' " +
                "AND regionId='"+user.getProjectId()+"' AND userId='"+user.getStaffId()+"' AND memo='秩序' " +
                "AND (taskStatus='2' OR taskStatus='5' OR taskStatus='8' OR taskStatus='15') " +
                "ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 环境：获取进行中的任务(环境主管)
     * @param projectId
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getEnvironmentUnderway(String projectId, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId+"' AND memo='环境'  AND " +
                "(taskStatus='2' OR taskStatus='5' OR taskStatus='8' OR taskStatus='15') " +
                "ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 环境：获取自己进行中的任务(环境人员)
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getMyEnvironmentUnderway(UserPropertyStaffEntity user, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' " +
                "AND regionId='"+user.getProjectId()+"' AND userId='"+user.getStaffId()+"' AND memo='环境' " +
                "AND (taskStatus='2' OR taskStatus='5' OR taskStatus='8' OR taskStatus='15') " +
                "ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 获取所有进行中的任务(管理员)
     * @param projectId
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getAllUnderway(String projectId, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId+"' AND " +
                "(taskStatus='2' OR taskStatus='5' OR taskStatus='8' OR taskStatus='15' " +
                "OR taskStatus='4' OR taskStatus='7' OR taskStatus='9' OR taskStatus='10' " +
                "OR taskStatus='12' OR taskStatus='13') " +
                "ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 获取进行中总条数(已废弃)
     * @return
     */
    @Override
    public int getTaskUnderwayNum() {
        String hql="SELECT DISTINCT re.repairId FROM PropertyRepairEntity AS re,PropertyRepairTaskEntity AS ta " +
                "WHERE re.repairId=ta.repairId AND (ta.taskStatus='4' OR ta.taskStatus='7' OR ta.taskStatus='9' " +
                "OR ta.taskStatus='10' OR taskStatus='12' or taskStatus='13')";
        Query query = getCurrentSession().createQuery(hql);
        int num=query.list().size();
        return num;
    }

    /**
     * 维修：获取已完成的任务(维修主管)
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getRepairComplete(String projectId,Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId
                +"' AND (memo='报修' OR memo='维修') AND (taskStatus='13' OR taskStatus='14') ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 维修：获取自己已完成的任务(维修人员)
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getMyRepairComplete(UserPropertyStaffEntity user, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+user.getProjectId()+"' " +
                "AND (memo='报修' OR memo='维修') AND userId='"+user.getStaffId()+"' AND (taskStatus='13' OR taskStatus='14') " +
                "ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 投诉：获取已完成的任务(客服主管)
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getComplaintComplete(String projectId,Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId+"' " +
                "AND (memo='报修' OR memo='维修' OR memo='投诉' OR memo='客服') " +
                "AND (taskStatus='13' OR taskStatus='16' OR taskStatus='17') ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 投诉：获取自己已完成的任务(客服人员)
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getMyComplaintComplete(UserPropertyStaffEntity user, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+user.getProjectId()+"' AND (userId='"
                +user.getStaffId()+"' OR customerId='"+user.getStaffId()+"') " +
                "AND (memo='报修' OR memo='维修' OR memo='投诉' OR memo='客服') " +
                "AND (taskStatus='13' OR taskStatus='14' OR taskStatus='16' OR taskStatus='17') " +
                "ORDER BY modifyDate DESC ";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 秩序：获取已完成的任务(秩序主管)
     * @param projectId
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getSequenceComplete(String projectId, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId+"' " +"AND memo='秩序'" +
                "AND (taskStatus='16' OR taskStatus='17') ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 秩序：获取自己已完成的任务(秩序人员)
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getMySequenceComplete(UserPropertyStaffEntity user, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+user.getProjectId()+"' AND userId='"
                +user.getStaffId()+"' AND memo='秩序' AND (taskStatus='16' OR taskStatus='17') " +
                "ORDER BY modifyDate DESC ";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 环境：获取已完成的任务(环境主管)
     * @param projectId
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getEnvironmentComplete(String projectId, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId+"' " +"AND memo='环境'" +
                "AND (taskStatus='16' OR taskStatus='17') ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 环境：获取自己已完成的任务(环境人员)
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getMyEnvironmentComplete(UserPropertyStaffEntity user, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+user.getProjectId()+"' AND userId='"
                +user.getStaffId()+"' AND memo='环境' AND (taskStatus='16' OR taskStatus='17') " +
                "ORDER BY modifyDate DESC ";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 获取所有已完成的任务(管理员)
     * @param projectId
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getAllComplete(String projectId, Page page) {
        String hql="FROM PropertyRepairEntity WHERE types!='3' AND regionId='"+projectId+"' " +
                "AND (taskStatus='16' OR taskStatus='17' OR taskStatus='13' OR taskStatus='14') " +
                "ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> taskList=query.list();
        return taskList;
    }

    /**
     * 随手报历史报修列表
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getReportsHistory(UserPropertyStaffEntity user, Page page) {
        String hql="FROM PropertyRepairEntity WHERE createBy='"+user.getStaffId()
                +"' AND types!='3' AND (memo='维修' OR memo='客服' OR memo='秩序' OR memo='环境')" +
                "ORDER BY createDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PropertyRepairEntity> historyList=query.list();
        return historyList;
    }
/**---------------------------------管理系统部分-------------------------------------*/
    /**
     * 获取工单列表
     * @param propertyRepair
     * @param webPage
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getWorkOrderList(PropertyRepairEntity propertyRepair, WebPage webPage,String roleScopes) {
        StringBuilder hql = new StringBuilder("");
        //数据权限范围条件
        if (!roleScopes.equals("") && !roleScopes.contains("all")){
            hql.append("from PropertyRepairEntity pr,HouseInfoEntity h where pr.roomId = h.roomId and types!='3' ");
            hql.append(" and h.projectNum in ("+roleScopes.substring(0,roleScopes.length()-1)+") ");
        }else{
            hql.append("from PropertyRepairEntity pr where types!='3' ");
        }
        if(null!=propertyRepair.getRegionName() && !"".equals(propertyRepair.getRegionName())){
            if (hql.indexOf("HouseInfoEntity") > 0){
                hql.append(" and h.projectNum in ("+propertyRepair.getRegionName().substring(0, propertyRepair.getRegionName().length()-1)+")");
            }else{
                hql.delete(0,hql.length());
                hql.append("from PropertyRepairEntity pr,HouseInfoEntity h where pr.roomId = h.roomId and types!='3' ");
                hql.append(" and h.projectNum in ("+propertyRepair.getRegionName().substring(0, propertyRepair.getRegionName().length()-1)+")");
            }
        }
        List<Object> params = new ArrayList<Object>();
        //根据项目查询数据
        //hql.append(SqlJoiningTogether.sqlStatement("regionId", projectId));
        //如果完成时间不为空，则加入超过24小时未评价的条件
        /*if (null != propertyRepair.getCompleteDate() && !"".equals(propertyRepair.getCompleteDate())) {
            hql.append(" and UNIX_TIMESTAMP(completeDate)<UNIX_TIMESTAMP(NOW())-3600*24 ");
        }*/
        //如果不为空，则加入检索条件：来源
        if(null!=propertyRepair.getRepairWay() && !"".equals(propertyRepair.getRepairWay())){
            if(propertyRepair.getRepairWay().equals("1")) {
                hql.append(" and pr.repairWay='APP'");
            }else if(propertyRepair.getRepairWay().equals("2")){
                hql.append(" and pr.repairWay='微信'");
            }else if(propertyRepair.getRepairWay().equals("3")){
                hql.append(" and pr.repairWay='呼叫中心'");
            }else if(propertyRepair.getRepairWay().equals("4")){
                hql.append(" and pr.repairWay='网页'");
            }else if(propertyRepair.getRepairWay().equals("5")){
                hql.append(" and pr.repairWay='项目前台'");
            }else if(propertyRepair.getRepairWay().equals("6")){
                hql.append(" and pr.repairWay='物业单据'");
            }
        }
        //如果不为空，则加入检索条件：单号
        if(null!=propertyRepair.getRepairId() && !"".equals(propertyRepair.getRepairId())){
            hql.append(" and pr.repairId like ?");
            params.add("%"+ propertyRepair.getRepairId() +"%");
        }
        //如果不为空，则加入检索条件：业主姓名
        if(null!=propertyRepair.getUserName() && !"".equals(propertyRepair.getUserName())){
            hql.append(" and pr.userName like ?");
            params.add("%"+ propertyRepair.getUserName() +"%");
        }
        //如果不为空，则加入检索条件：业主电话
        if(null!=propertyRepair.getUserPhone() && !"".equals(propertyRepair.getUserPhone())){
            hql.append(" and pr.userPhone like ?");
            params.add("%"+ propertyRepair.getUserPhone() +"%");
        }
        //如果不为空，则加入检索条件：报修时间
        if(null != propertyRepair.getCreateBy() && !"".equals(propertyRepair.getCreateBy())){
            hql.append(" and pr.createDate >= ?");
            params.add(DateUtils.parse(propertyRepair.getCreateBy() + " 00:00:00"));
        }
        if(null != propertyRepair.getModifyBy() && !"".equals(propertyRepair.getModifyBy())){
            hql.append(" and pr.createDate <= ?");
            params.add(DateUtils.parse(propertyRepair.getModifyBy() + " 23:59:59"));
        }
        //如果不为空，则加入检索条件：完成时间
        if(null != propertyRepair.getUserAddress() && !"".equals(propertyRepair.getUserAddress())){
            hql.append(" and pr.completeDate >= ?");
            params.add(DateUtils.parse(propertyRepair.getUserAddress() + " 00:00:00"));
        }
        if(null != propertyRepair.getMemo() && !"".equals(propertyRepair.getMemo())){
            hql.append(" and pr.completeDate <= ?");
            params.add(DateUtils.parse(propertyRepair.getMemo() + " 23:59:59"));
        }
        //如果不为空，则加入检索条件：工单状态
        if(null!=propertyRepair.getTaskStatus() && !"".equals(propertyRepair.getTaskStatus())){
            if(propertyRepair.getTaskStatus().equals("1")){
                hql.append(" and pr.state='已创建'");
            }
            if(propertyRepair.getTaskStatus().equals("2")){
                hql.append(" and pr.state='已受理'");
            }
            if(propertyRepair.getTaskStatus().equals("3")){
                hql.append(" and pr.state='处理中'");
            }
            if(propertyRepair.getTaskStatus().equals("4")){
                hql.append(" and pr.state='已完成'");
            }
            if(propertyRepair.getTaskStatus().equals("6")){
                hql.append(" and pr.state='已评价'");
            }
        }
        //如果不为空，则加入检索条件：工单类型
        if(null!=propertyRepair.getContent() && !"".equals(propertyRepair.getContent())){
            if(propertyRepair.getContent().equals("1")){
                hql.append(" and pr.memo='报修'");
            }
            if(propertyRepair.getContent().equals("2")){
                hql.append(" and pr.memo='投诉'");
            }
        }
        hql.append(" ORDER BY pr.createDate DESC");
        if(webPage != null){
            List<PropertyRepairEntity> repairList = new ArrayList<>();
            if (!roleScopes.equals("") && !roleScopes.contains("all") || null!=propertyRepair.getRegionName() && !"".equals(propertyRepair.getRegionName())){
                List<Object[]> objectList = this.findByPage(hql.toString(), webPage, params);
                for (int i = 0,length = objectList.size(); i < length; i++){
                    PropertyRepairEntity repair = (PropertyRepairEntity) objectList.get(i)[0];
                    repairList.add(repair);
                }
            }else{
                repairList = this.findByPage(hql.toString(), webPage, params);
            }
            return repairList;
        }
        List<PropertyRepairEntity> repairList=(List<PropertyRepairEntity>)getHibernateTemplate().find(hql.toString());
        return repairList;
    }

    @Override
    public List<Object[]> getRepairList(Object[] obj, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer();
        hql.append(" SELECT a.repairId,a.content,c.address,a.userName,a.userPhone,a.regionName,a.state ");
        hql.append(" FROM PropertyRepairEntity a,PropertyRepairCRMEntity b,HouseInfoEntity c ");
        hql.append(" WHERE a.repairId = b.repairId AND b.roomId = c.roomId ");

        //如果不为空，则加入检索条件：项目名称
        if(null!=obj[0] && !"".equals(obj[0].toString())){
            hql.append(" and a.regionName like ?");
            params.add("%"+ obj[0].toString() +"%");
        }

        //如果不为空，则加入检索条件：楼栋
        if(null!=obj[1] && !"".equals(obj[1].toString()) && !"0000".equals(obj[1].toString())){
            hql.append(" and c.buildingNum = ?");
            params.add(obj[1].toString());
        }

        //如果不为空，则加入检索条件：楼层
        if(null!=obj[2] && !"".equals(obj[2].toString()) && !"0000".equals(obj[2].toString())){
            hql.append(" and c.floor = ?");
            params.add(obj[2].toString());
        }

        //如果不为空，则加入检索条件：一级分类
        if(null!=obj[3] && !"".equals(obj[3].toString()) && !"0000".equals(obj[3].toString())){
            hql.append(" and b.classifyOne = ?");
            params.add(obj[3].toString());
        }

        //如果不为空，则加入检索条件：二级分类
        if(null!=obj[4] && !"".equals(obj[4].toString()) && !"0000".equals(obj[4].toString())){
            hql.append(" and b.classifyTwo = ?");
            params.add(obj[4].toString());
        }

        //如果不为空，则加入检索条件：工单状态
        if(null!=obj[6] && !"".equals(obj[6].toString()) && !"0000".equals(obj[6].toString())) {
            if(obj[6].toString().equals("1")){
                hql.append(" and a.state='已创建'");
            }
            if(obj[6].toString().equals("2")){
                hql.append(" and a.state='已受理'");
            }
            if(obj[6].toString().equals("3")){
                hql.append(" and a.state='处理中'");
            }
            if(obj[6].toString().equals("4")){
                hql.append(" and a.state='已完成'");
            }
            if(obj[6].toString().equals("5")){
                hql.append(" and a.state='已关闭'");
            }
            if(obj[6].toString().equals("6")){
                hql.append(" and a.state='已评价'");
            }
        }

        //如果不为空，则加入检索条件：工单类型
        if(null!=obj[5] && !"".equals(obj[5].toString()) && !"0000".equals(obj[5].toString())){
            hql.append(" and a.types=?");
            params.add(obj[5].toString());
        }
        hql.append(" and a.types!='3' ORDER BY a.createDate DESC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<Object[]> repairList=(List<Object[]>)getHibernateTemplate().find(hql.toString(),params);
        return repairList;
    }

    /**
     * 删除/修改报修单
     * @param propertyRepairEntity
     */
    @Override
    public boolean updateRepaired(PropertyRepairEntity propertyRepairEntity) {
        Session session = getCurrentSession();
        session.update(propertyRepairEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public void saveRepairCrm(PropertyRepairCRMEntity propertyRepairCRMEntity) {
        Session session=getCurrentSession();
        session.save(propertyRepairCRMEntity);
        session.flush();
        session.close();
    }

    @Override
    public PropertyRepairCRMEntity queryRepairByIdOrappId(String id, String appId) {
        String hql="FROM PropertyRepairCRMEntity WHERE repairId='"+id+"' or appId='"+appId+"' ";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairCRMEntity> repairList=query.list();
        if(repairList.size()>0){
            return repairList.get(0);
        }
        return null;
    }

    @Override
    public void updateRepairCrm(PropertyRepairCRMEntity propertyRepairCRMEntity) {
        Session session=getCurrentSession();
        session.update(propertyRepairCRMEntity);
        session.flush();
        session.close();
    }

    /**
     * 检索物业报修服务问题答应列表
     * @param repairId 报修单ID
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getRepairServiceQuestionList(String repairId){
        StringBuilder sql = new StringBuilder(" select rs.question_id questionId,rs.question_content questionContent, ");
        sql.append(" rs.question_type questionType,rsa.score_lev scoreLev,rsa.suggestion suggestion ");
        sql.append(" from repair_service_question_answer rsa,repair_service_question rs ");
        sql.append(" where rsa.question_id = rs.question_id and rs.question_status = 1 ");
        sql.append(" and rsa.repair_id = ? ");
        sql.append(" order by rs.question_order ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString()).setParameter(0, repairId);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取物业报修完整数据列表
     */
    @Override
    public List<Map<String,Object>> getPropertyRepairCrmList(Map<String,Object> params, WebPage webPage) {
        StringBuilder sql = new StringBuilder(" select ");
        sql.append(" h.PROJECT_NAME projectName,h.BUILDING_RECORD buildingRecord,h.UNIT unit,h.ROOM_NAME roomName,prc.REPAIR_MANAGER repairManager, ");
        sql.append(" fc.LABEL labelOne,sc.LABEL labelTwo,tc.LABEL labelThree,prc.CONTENT content,prc.USER_NAME userName, ");
        sql.append(" prc.USER_PHONE userPhone,prc.DEAL_WAY dealWay,pr.STATE state,pr.REPAIR_ID repairId, ");
        sql.append(" prc.DEAL_MODE dealMode,s1.NAME dutyCompanyOneName,s2.NAME dutyCompanyTwoName,s3.NAME dutyCompanyThreeName, ");
        sql.append(" prc.REPAIR_COMPANY repairCompany,prc.PROBLEM_LEVEL problemLevel, ");
        sql.append(" prc.CREATE_DATE createDate,prc.SEND_DATE sendDate,prc.TASK_DATE taskDate,prc.DEAL_COMPLETE_DATE dealCompleteDate ");
        sql.append(" from property_repair_crm prc ");
        sql.append(" left join property_repair pr on prc.REPAIR_ID = pr.REPAIR_ID ");
        sql.append(" left join house_houseInfo h on prc.ROOM_ID = h.ROOM_ID ");
        sql.append(" left join first_classification fc on prc.CLASSIFY_ONE = fc.VALUE ");
        sql.append(" left join second_classification sc on prc.CLASSIFY_TWO = sc.VALUE ");
        sql.append(" left join third_classification tc on prc.CLASSIFY_THREE = tc.VALUE ");
        sql.append(" left join supplier s1 on prc.DUTY_COMPANY_ONE = s1.ID ");
        sql.append(" left join supplier s2 on prc.DUTY_COMPANY_Two = s2.ID ");
        sql.append(" left join supplier s3 on prc.DUTY_COMPANY_Three = s3.ID ");
        sql.append(" where pr.TYPES != '3' ");//3为逻辑删除
        //数据权限范围条件
        String roleScopes = params.get("roleScopes").toString();
        if (!roleScopes.equals("") && !roleScopes.contains("all")){
            sql.append(" and h.PROJECT_NUM in ("+roleScopes.substring(0,roleScopes.length()-1)+") ");
        }
        Object projectIds = params.get("projectIds");
        if(null!=projectIds && !"".equals(projectIds)){
            sql.append(" and h.PROJECT_NUM in ("+projectIds.toString().substring(0, projectIds.toString().length()-1)+")");
        }
        //封装参数
        List<Object> paramsList = new ArrayList<Object>();
        //如果不为空，则加入检索条件：来源
        Object repairWay = params.get("repairWay");
        if(null!=repairWay && !"".equals(repairWay)){
            if(repairWay.equals("1")) {
                sql.append(" and pr.REPAIR_WAY='APP'");
            }else if(repairWay.equals("2")){
                sql.append(" and pr.REPAIR_WAY='微信'");
            }else if(repairWay.equals("3")){
                sql.append(" and pr.REPAIR_WAY='呼叫中心'");
            }else if(repairWay.equals("4")){
                sql.append(" and pr.REPAIR_WAY='网页'");
            }else if(repairWay.equals("5")){
                sql.append(" and pr.REPAIR_WAY='项目前台'");
            }else if(repairWay.equals("6")){
                sql.append(" and pr.REPAIR_WAY='物业单据'");
            }
        }
        //如果不为空，则加入检索条件：单号
        Object id = params.get("id");
        if(null!=id && !"".equals(id)){
            sql.append(" and pr.REPAIR_ID like ?");
            paramsList.add("%"+ id +"%");
        }
        //如果不为空，则加入检索条件：业主姓名
        Object userName = params.get("userName");
        if(null!=userName && !"".equals(userName)){
            sql.append(" and pr.USER_NAME like ?");
            paramsList.add("%"+ userName +"%");
        }
        //如果不为空，则加入检索条件：业主电话
        Object userPhone = params.get("userPhone");
        if(null!=userPhone && !"".equals(userPhone)){
            sql.append(" and pr.USER_PHONE like ?");
            paramsList.add("%"+ userPhone +"%");
        }
        //如果不为空，则加入检索条件：报修时间
        Object startDate = params.get("startDate");
        if(null != startDate && !"".equals(startDate)){
            sql.append(" and pr.CREATE_DATE >= ?");
            paramsList.add(DateUtils.parse(startDate + " 00:00:00"));
        }
        Object endDate = params.get("endDate");
        if(null != endDate && !"".equals(endDate)){
            sql.append(" and pr.CREATE_DATE <= ?");
            paramsList.add(DateUtils.parse(endDate + " 23:59:59"));
        }
        //如果不为空，则加入检索条件：完成时间
        Object completeStartDate = params.get("completeStartDate");
        if(null != completeStartDate && !"".equals(completeStartDate)){
            sql.append(" and pr.COMPLETE_DATE >= ?");
            paramsList.add(DateUtils.parse(completeStartDate + " 00:00:00"));
        }
        Object completeEndDate = params.get("completeEndDate");
        if(null != completeEndDate && !"".equals(completeEndDate)){
            sql.append(" and pr.COMPLETE_DATE <= ?");
            paramsList.add(DateUtils.parse(completeEndDate + " 23:59:59"));
        }
        //如果不为空，则加入检索条件：工单状态
        Object status = params.get("status");
        if(null!=status && !"".equals(status)){
            if(status.equals("1")){
                sql.append(" and pr.STATE='已创建'");
            }
            if(status.equals("2")){
                sql.append(" and pr.STATE='已受理'");
            }
            if(status.equals("3")){
                sql.append(" and pr.STATE='处理中'");
            }
            if(status.equals("4")){
                sql.append(" and pr.STATE='已完成'");
            }
            if(status.equals("6")){
                sql.append(" and pr.STATE='已评价'");
            }
        }
        //如果不为空，则加入检索条件：工单类型
        Object type = params.get("type");
        if(null!=type && !"".equals(type)){
            if(type.equals("1")){
                sql.append(" and pr.MEMO='报修'");
            }
            if(type.equals("2")){
                sql.append(" and pr.MEMO='投诉'");
            }
        }
        sql.append(" order by prc.CREATE_DATE desc ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            query.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }
}