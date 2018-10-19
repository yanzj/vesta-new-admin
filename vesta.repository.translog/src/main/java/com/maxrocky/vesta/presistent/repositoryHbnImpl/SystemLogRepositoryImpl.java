package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.InfoReleaseEntity;
import com.maxrocky.vesta.domain.model.SystemLogEntity;
import com.maxrocky.vesta.domain.model.UserDocumentsEntity;
import com.maxrocky.vesta.domain.model.UserVisitLogEntity;
import com.maxrocky.vesta.domain.repository.SystemLogRepository;
import com.maxrocky.vesta.presistent.repositoryHbmImpl.BaseRepositoryImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/7/19.
 */
@Repository
public class SystemLogRepositoryImpl extends BaseRepositoryImpl implements SystemLogRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 删除Entity
     * @param entity
     */
    @Override
    public <E> void delete(E entity){
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    @Override
    public void addSysLog(SystemLogEntity systemLogEntity) {
        Session session = getCurrentSession();
        session.save(systemLogEntity);
        session.flush();
    }

    @Override
    public List<SystemLogEntity> getSystemLogList(SystemLogEntity systemLogEntity,String beginTime,String endTime,WebPage webPage) {
        List<Object> params = new ArrayList<>();
        String hql = "from SystemLogEntity where 1=1";
        if(!StringUtils.isEmpty(systemLogEntity.getLogType())){
            hql += "and logType = '"+systemLogEntity.getLogType()+"'";
        }
        if(!StringUtils.isEmpty(systemLogEntity.getProjectId())&&!systemLogEntity.getProjectId().equals("0")&&!systemLogEntity.getProjectId().equals("请选择")){
            hql += " and projectId= '"+systemLogEntity.getProjectId()+"'";
        }
        if(!StringUtils.isEmpty(systemLogEntity.getUserType())&&!systemLogEntity.getUserType().equals("0")){
            hql += " and userType = '"+systemLogEntity.getUserType()+"'";
        }
        if(!StringUtils.isEmpty(systemLogEntity.getSourceFrom())&&!systemLogEntity.getSourceFrom().equals("0")){
            hql += " and sourceFrom = '"+systemLogEntity.getSourceFrom()+"'";
        }
        if(!StringUtils.isEmpty(systemLogEntity.getUserMobile())){
            hql += " and userMobile like '%"+systemLogEntity.getUserMobile()+"%'";
        }
        if(!StringUtils.isEmpty(beginTime)){
            hql += " and logTime >= '"+beginTime+"'";
        }
        if(!StringUtils.isEmpty(endTime)){
            hql += " and logTime <= '"+endTime+"'";
        }
        hql+="order by logTime desc";
        Query query = getCurrentSession().createQuery(hql);
        List<SystemLogEntity> systemLogEntities = query.list();
        if (webPage != null){
            return this.findByPage(hql,webPage,params);
        }
        return systemLogEntities;
    }

    @Override
    public void addUserVisitLog(UserVisitLogEntity userVisitLogObj) {
        Session session = getCurrentSession();
        session.save(userVisitLogObj);
        session.flush();
    }

    @Override
    public List<UserVisitLogEntity> getUserVisitLogList(UserVisitLogEntity userVisitLogObj, String beginTime, String endTime, WebPage webPage) {
        List<Object> params = new ArrayList<>();
        String hql = "from UserVisitLogEntity where 1=1";
        if(!StringUtils.isEmpty(userVisitLogObj.getProjectId())&&!userVisitLogObj.getProjectId().equals("0")&&!userVisitLogObj.getProjectId().equals("请选择")){
            hql += " and projectId= '"+userVisitLogObj.getProjectId()+"'";
        }
        if(!StringUtils.isEmpty(userVisitLogObj.getUserType())&&!userVisitLogObj.getUserType().equals("0")){
            hql += " and userType = '"+userVisitLogObj.getUserType()+"'";
        }
        if(!StringUtils.isEmpty(userVisitLogObj.getSourceFrom())&&!userVisitLogObj.getSourceFrom().equals("0")){
            hql += " and sourceFrom = '"+userVisitLogObj.getSourceFrom()+"'";
        }
        if(!StringUtils.isEmpty(userVisitLogObj.getUserMobile())){
            hql += " and userMobile = '"+userVisitLogObj.getUserMobile()+"'";
        }
        if(!StringUtils.isEmpty(beginTime)){
            hql += " and logTime >= '"+beginTime+"'";
        }
        if(!StringUtils.isEmpty(endTime)){
            hql += " and logTime <= '"+endTime+"'";
        }
        hql+="order by logTime desc";
        Query query = getCurrentSession().createQuery(hql);
        List<UserVisitLogEntity> systemLogEntities = query.list();
        if (webPage != null){
            return this.findByPage(hql,webPage,params);
        }
        return systemLogEntities;
    }

    @Override
    public void addUserDocumentsLog(UserDocumentsEntity systemLogEntity) {
        Session session = getCurrentSession();
        session.save(systemLogEntity);
        session.flush();
    }

    @Override
    public List<UserDocumentsEntity> getUserDocumentsLogList(UserDocumentsEntity userVisitLogObj, String beginTime, String endTime, WebPage webPage,String roleScopes) {
        List<Object> params = new ArrayList<>();
        String hql = "from UserDocumentsEntity where 1=1";
        //数据权限范围条件
        if (!"".equals(roleScopes) && !roleScopes.contains("all")){
            hql += " and projectId in ("+roleScopes.substring(0, roleScopes.length() - 1)+") ";
        }
        if(!StringUtils.isEmpty(userVisitLogObj.getProjectId())&&!userVisitLogObj.getProjectId().equals("0")&&!userVisitLogObj.getProjectId().equals("请选择")){
            hql += " and projectId in ("+userVisitLogObj.getProjectId().substring(0, userVisitLogObj.getProjectId().length()-1)+")";
        }
        if(!StringUtils.isEmpty(userVisitLogObj.getUserType())&&!userVisitLogObj.getUserType().equals("0")){
            hql += " and userType = '"+userVisitLogObj.getUserType()+"'";
        }
        if(!StringUtils.isEmpty(userVisitLogObj.getSourceFrom())&&!userVisitLogObj.getSourceFrom().equals("0")){
            hql += " and sourceFrom = '"+userVisitLogObj.getSourceFrom()+"'";
        }
        if(!StringUtils.isEmpty(userVisitLogObj.getUserMobile())){
            hql += " and userMobile like '%"+userVisitLogObj.getUserMobile()+"%'";
        }
        if (!StringUtils.isEmpty(userVisitLogObj.getThisType()) && !userVisitLogObj.getThisType().equals("0")){
            hql += "and thisType = '" + userVisitLogObj.getThisType() + "'";
        }
        if(!StringUtils.isEmpty(beginTime)){
            hql += " and logTime >= '"+beginTime+"'";
        }
        if(!StringUtils.isEmpty(endTime)){
            hql += " and logTime <= '"+endTime+"'";
        }
        hql+="order by logTime desc";
        Query query = getCurrentSession().createQuery(hql);
        List<UserDocumentsEntity> systemLogEntities = query.list();
        if (webPage != null){
            return this.findByPage(hql,webPage,params);
        }
        return systemLogEntities;
    }

    @Override
    public void addInfoReleaseLog(InfoReleaseEntity systemLogEntity) {
        Session session = getCurrentSession();
        session.save(systemLogEntity);
        session.flush();
    }

    @Override
    public List<InfoReleaseEntity> getInfoReleaseLogList(InfoReleaseEntity userVisitLogObj, String beginTime, String endTime, WebPage webPage) {
        List<Object> params = new ArrayList<>();
        String hql = "from InfoReleaseEntity where 1=1";
        if(!StringUtils.isEmpty(userVisitLogObj.getProjectId())&&!userVisitLogObj.getProjectId().equals("0")&&!userVisitLogObj.getProjectId().equals("请选择")){
            hql += " and projectId= '"+userVisitLogObj.getProjectId()+"'";
        }
        if(!StringUtils.isEmpty(userVisitLogObj.getUserType())&&!userVisitLogObj.getUserType().equals("0")){
            hql += " and userType = '"+userVisitLogObj.getUserType()+"'";
        }
        if(!StringUtils.isEmpty(userVisitLogObj.getSourceFrom())&&!userVisitLogObj.getSourceFrom().equals("0")){
            hql += " and sourceFrom = '"+userVisitLogObj.getSourceFrom()+"'";
        }
        if(!StringUtils.isEmpty(userVisitLogObj.getUserMobile())){
            hql += " and userMobile = '"+userVisitLogObj.getUserMobile()+"'";
        }
        if(!StringUtils.isEmpty(beginTime)){
            hql += " and logTime >= '"+beginTime+"'";
        }
        if(!StringUtils.isEmpty(endTime)){
            hql += " and logTime <= '"+endTime+"'";
        }
        hql+="order by logTime desc";
        Query query = getCurrentSession().createQuery(hql);
        List<InfoReleaseEntity> systemLogEntities = query.list();
        if (webPage != null){
            return this.findByPage(hql,webPage,params);
        }
        return systemLogEntities;
    }

    /**
     * 通过用户类型检索新增用户日志重复数据
     * @param userType 用户类型
     * @return List<Map<String,Object>>
     */
    @Override
    public List<Map<String,Object>> getSystemLogListByUserType(String userType){
        StringBuilder sql = new StringBuilder(" select count(*) count,sl.USER_MOBILE userMobile from system_log sl " +
                "where sl.USER_TYPE = ? group by sl.USER_MOBILE having count(*)>=2 ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString()).setParameter(0, userType);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过用户类型及手机号码检索新增用户日志重复数据
     * @param userType 用户类型
     * @param userMobile 用户手机
     * @return List<SystemLogEntity>
     */
    @Override
    public List<SystemLogEntity> getSystemLogListByUser(String userType,String userMobile){
        StringBuilder hql = new StringBuilder(" FROM SystemLogEntity sl WHERE sl.userType = ? AND sl.userMobile = ? ORDER BY sl.logTime DESC");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0,userType).setParameter(1,userMobile);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }
}
