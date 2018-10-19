package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.AuthAgencyRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by magic on 2017/12/6.
 */
@Repository
public class AuthAgencyRepositoryImpl extends HibernateDaoImpl implements AuthAgencyRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public AuthAgencyEntity getAuthAgencyByID(String id) {
        String hql = "from AuthAgencyEntity where agencyId=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        AuthAgencyEntity authAgencyEntity = (AuthAgencyEntity) query.uniqueResult();
        return authAgencyEntity;
    }

    @Override
    public AuthAgencyESEntity getESAuthAgencyByID(String id) {
        String hql = "from AuthAgencyESEntity where agencyId=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        AuthAgencyESEntity authAgencyEntity = (AuthAgencyESEntity) query.uniqueResult();
        return authAgencyEntity;
    }

    @Override
    public AuthAgencyQCEntity getQCAuthAgencyByID(String id) {
        String hql = "from AuthAgencyQCEntity where agencyId=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        AuthAgencyQCEntity authAgencyEntity = (AuthAgencyQCEntity) query.uniqueResult();
        return authAgencyEntity;
    }

    @Override
    public List<AuthAgencyEntity> getAllAuthAgencyList() {
        String hql = "from AuthAgencyEntity where status = '1' ";
        Query query = getCurrentSession().createQuery(hql);
        List<AuthAgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public void addAuthAgency(AuthAgencyEntity authAgencyEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(authAgencyEntity);
        session.flush();
    }

    @Override
    public List<AuthAgencyEntity> getAllAgencyList() {
        String hql = "from AuthAgencyEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<AuthAgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyESEntity> getESAllAgencyList() {
        String hql = "from AuthAgencyESEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') order by sort,agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<AuthAgencyESEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyESEntity> getESAllAgencyListByLevel(String level){
        String hql = "from AuthAgencyESEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') and level = '"+level+"' order by sort,agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<AuthAgencyESEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyQCEntity> getQCAllAgencyList() {
        String hql = "from AuthAgencyQCEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<AuthAgencyQCEntity> agencyEntities = query.list();
        return agencyEntities;
    }


    @Override
    public List<AuthAgencyEntity> getAllAgencyListByAgencyId(String agencyId) {
        String hql = "from AuthAgencyEntity where status = '1' and agencyPath like:agencyId order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyId", "%"+agencyId+"%");
        List<AuthAgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyEntity> getAllAgencyListByAgencyIdList(List<String> idList) {
        String hql = "from AuthAgencyEntity where status = '1' and agencyId in(:idList) order by agencyId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameterList("idList", idList);
        List<AuthAgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<String> getAgencyPathListByAgencyId(List<String> idList) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ara.AGENCY_PATH   ");
        sql.append(" from auth_role_agency ara  ");
        sql.append(" where  ara.`STATUS`='1'  and ara.AGENCY_ID in(:idList) ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameterList("idList", idList);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<AuthAgencyEntity> getAllAgencyListById(String id,String agencyName) {
//        String hql = "from AuthAgencyEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') and parentId like:id and agencyName like:agencyName or agencyId like:id order by agencyType,orderNum";
        String hql = "from AuthAgencyEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') and parentId like:id and agencyName like:agencyName  order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("agencyName", agencyName);
        List<AuthAgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyESEntity> getAllESAgencyListById(String id, String agencyName) {
        String hql = "from AuthAgencyESEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') and parentId like:id and agencyName like:agencyName  order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("agencyName", agencyName);
        List<AuthAgencyESEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyQCEntity> getAllQCAgencyListById(String id, String agencyName) {
        String hql = "from AuthAgencyQCEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') and parentId like:id and agencyName like:agencyName  order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("agencyName", agencyName);
        List<AuthAgencyQCEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public AuthAgencyEntity getAgencyListById(String id) {
        String hql = "from AuthAgencyEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') and agencyId=:id  order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        List<AuthAgencyEntity> agencyEntities = query.list();
        if(agencyEntities.size() == 0){
            return  null;
        }else{
            return  agencyEntities.get(0);
        }
    }

    @Override
    public AuthAgencyESEntity getEStAgencyListById(String id) {
        String hql = "from AuthAgencyESEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') and agencyId=:id  order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        List<AuthAgencyESEntity> agencyEntities = query.list();
        if(agencyEntities.size() == 0){
            return  null;
        }else{
            return  agencyEntities.get(0);
        }
    }

    @Override
    public AuthAgencyQCEntity getQCtAgencyListById(String id) {
        String hql = "from AuthAgencyQCEntity where status = '1' and agencyType in('100000000','100000001','100000003','100000002') and agencyId=:id  order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        List<AuthAgencyQCEntity> agencyEntities = query.list();
        if(agencyEntities.size() == 0){
            return  null;
        }else{
            return  agencyEntities.get(0);
        }
    }

    @Override
    public List<AuthAgencyEntity> getAllAgency() {
        String hql = "from AuthAgencyEntity where status = '1' and agencyType in('100000000') order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<AuthAgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyEntity> getAgencyByAgencyId(String id) {
        String hql = "from AuthAgencyEntity where status = '1' and parentId=:parentId order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("parentId",id);
        List<AuthAgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyESEntity> getESAgencyByAgencyId(String id) {
        String hql = "from AuthAgencyESEntity where status = '1' and parentId=:parentId order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("parentId",id);
        List<AuthAgencyESEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyQCEntity> getQCAgencyByAgencyId(String id) {
        String hql = "from AuthAgencyQCEntity where status = '1' and parentId=:parentId order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("parentId",id);
        List<AuthAgencyQCEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<Object[]> getAuthRoleseList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" are.roleId,are.roleName,are.modifyOn,are.roleType,are.roleNature,are.category,are.prefix ");
        sql.append(" from AuthRoleseEntity are ");
        sql.append(" where 1=1 and are.state='0'");
        if (map.get("roleName") != null && !"".equals(map.get("roleName").toString()) && !"0".equals(map.get("roleName").toString())) {
            sql.append(" and are.roleName like? ");
            params.add(map.get("roleName").toString());
        }
        if (map.get("roleId") != null && !"".equals(map.get("roleId").toString()) && !"0".equals(map.get("roleId").toString())) {
            sql.append(" and are.roleId=? ");
            params.add(map.get("roleId").toString());
        }
        if (map.get("category") != null && !"".equals(map.get("category").toString()) && !"0".equals(map.get("category").toString())) {
            sql.append(" and are.category=? ");
            params.add(map.get("category").toString());
        }
        sql.append(" order by are.modifyOn desc ");
        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }

    @Override
    public List<String> getAuthRoleseProject(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ara.AGENCY_NAME ");
        sql.append(" from auth_role_project arp left join auth_role_agency ara on arp.AUTH_AGENCY_ID=ara.AGENCY_ID ");
        sql.append(" where  1=1 and arp.AUTH_ROLE_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getESAuthRoleseProject(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ara.AGENCY_NAME ");
        sql.append(" from auth_role_project arp left join auth_role_agencyes ara on arp.AUTH_AGENCY_ID=ara.AGENCY_ID ");
        sql.append(" where  1=1 and arp.AUTH_ROLE_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getQCAuthRoleseProject(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ara.AGENCY_NAME ");
        sql.append(" from auth_role_project arp left join auth_role_agencyqc ara on arp.AUTH_AGENCY_ID=ara.AGENCY_ID ");
        sql.append(" where  1=1 and arp.AUTH_ROLE_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getAuthRoleseProjectID(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" arp.AUTH_AGENCY_ID ");
        sql.append(" from auth_role_project arp ");
        sql.append(" where  1=1 and arp.AUTH_ROLE_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        List<String> list = query.list();
        return list;
    }

    @Override
    public Boolean delAuthRoleseProject(String id) {
        StringBuffer sql = new StringBuffer(" delete ");
        sql.append(" from auth_role_project   ");
        sql.append(" where  1=1 and AUTH_ROLE_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        query.executeUpdate();
        return true;
    }

    @Override
    public Boolean delAuthRoleseProjectMap(String id, String time) {
        StringBuffer sql = new StringBuffer(" update ");
        sql.append("  role_staff_project_map   ");
        sql.append("  SET STATE='0' , MODIFY_ON='"+time+"' ");
        sql.append(" where  1=1 and ROLE_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        query.executeUpdate();
        return true;
    }

    @Override
    public Boolean delAuthRoleseProjectQCMapList(List<String> roleAgnecy, String time, String roleId) {
        StringBuffer sql = new StringBuffer(" update ");
        sql.append("  role_staff_projectqc_map   ");
        sql.append("  SET STATE='0' , MODIFY_ON='"+time+"' ");
        sql.append(" where  1=1 and ROLE_ID=:id  and CONCAT(ROLE_ID,AGENCY_ID) not in(:roleAgnecy) ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", roleId);
        query.setParameterList("roleAgnecy", roleAgnecy);
        query.executeUpdate();
        return true;
    }

    @Override
    public Boolean delAuthRoleseProjectESMapList(List<String> roleAgnecy, String time, String roleId) {
        StringBuffer sql = new StringBuffer(" update ");
        sql.append("  role_staff_projectes_map   ");
        sql.append("  SET STATE='0' , MODIFY_ON='"+time+"' ");
        sql.append(" where  1=1 and ROLE_ID=:id  and CONCAT(ROLE_ID,AGENCY_ID) not in(:roleAgnecy) ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", roleId);
        query.setParameterList("roleAgnecy", roleAgnecy);
        query.executeUpdate();
        return true;
    }

    @Override
    public Boolean delAuthRoleseProjectMapList(List<String> roleAgnecy, String time, String roleId) {
        StringBuffer sql = new StringBuffer(" update ");
        sql.append("  role_staff_project_map   ");
        sql.append("  SET STATE='0' , MODIFY_ON='"+time+"' ");
        sql.append(" where  1=1 and ROLE_ID=:id  and CONCAT(ROLE_ID,AGENCY_ID) not in(:roleAgnecy) ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", roleId);
        query.setParameterList("roleAgnecy", roleAgnecy);
        query.executeUpdate();
        return true;
    }

    @Override
    public Boolean delAuthRoleseProjectMapByid(String roleId, String agencyId, String time) {
        StringBuffer sql = new StringBuffer(" update ");
        sql.append("  role_staff_project_map   ");
        sql.append("  SET STATE='0' , MODIFY_ON='"+time+"' ");
        sql.append(" where  1=1 and ROLE_ID=:id  and AGENCY_ID=:agencyId");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", roleId);
        query.setParameter("agencyId", agencyId);
        query.executeUpdate();
        return true;
    }

    @Override
    public Boolean delESAuthRoleseProjectMapByid(String roleId, String agencyId, String time) {
        StringBuffer sql = new StringBuffer(" update ");
        sql.append("  role_staff_projectes_map   ");
        sql.append("  SET STATE='0' , MODIFY_ON='"+time+"' ");
        sql.append(" where  1=1 and ROLE_ID=:id  and AGENCY_ID=:agencyId");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", roleId);
        query.setParameter("agencyId", agencyId);
        query.executeUpdate();
        return true;
    }

    @Override
    public Boolean delQCAuthRoleseProjectMapByid(String roleId, String agencyId, String time) {
        StringBuffer sql = new StringBuffer(" update ");
        sql.append("  role_staff_projectqc_map   ");
        sql.append("  SET STATE='0' , MODIFY_ON='"+time+"' ");
        sql.append(" where  1=1 and ROLE_ID=:id  and AGENCY_ID=:agencyId");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", roleId);
        query.setParameter("agencyId", agencyId);
        query.executeUpdate();
        return true;
    }

    @Override
    public Boolean upAuthRoleseProjectMap(String id, String time,String agencyId) {
        StringBuffer sql = new StringBuffer(" update ");
        sql.append("  role_staff_project_map   ");
        sql.append("  SET STATE='1' , MODIFY_ON='"+time+"' ");
        sql.append(" where  1=1 and ROLE_ID=:id and AGENCY_ID=:agencyId");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        query.setParameter("agencyId", agencyId);
        query.executeUpdate();
        return true;
    }

    @Override
    public void saveAuthRolese(AuthRoleseEntity authRoleseEntity) {
        Session session = getCurrentSession();
        session.save(authRoleseEntity);
        session.flush();
    }

    @Override
    public void upAuthRolese(AuthRoleseEntity authRoleseEntity) {
        Session session = getCurrentSession();
        session.update(authRoleseEntity);
        session.flush();
    }

    @Override
    public List<AuthRoleseEntity> getAuthRoleseEntity(String category) {
        String hql = "from AuthRoleseEntity where category = :category and state='0' ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("category",category);
        return (List<AuthRoleseEntity>)query.list();
    }

    @Override
    public List<AuthRoleseEntity> getAuthRoleseEntityAll() {
        String hql = "from AuthRoleseEntity where  state='0' ";
        Query query = getCurrentSession().createQuery(hql);
        return (List<AuthRoleseEntity>)query.list();
    }

    @Override
    public void saveAuthRoleseProject(AuthRoleProjectEntity authRoleProjectEntity) {
        Session session = getCurrentSession();
        session.save(authRoleProjectEntity);
        session.flush();
    }

    @Override
    public AuthRoleseEntity getAuthRoleseEntityByid(String id) {
        String hql = "from AuthRoleseEntity where state = '0' and roleId=:id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        List<AuthRoleseEntity> agencyEntities = query.list();
        if(agencyEntities!=null){
            return  agencyEntities.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<AuthFunctionPointEntity> getAuthFunctionPointEntityList(String category, String classification, String level) {
        String hql = "from AuthFunctionPointEntity where state = '0' and category=:category and classification=:classification and level=:level ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("category", category);
        query.setParameter("classification", classification);
        query.setParameter("level", level);
        List<AuthFunctionPointEntity> list = query.list();
        return list;
    }

    @Override
    public List<AuthFunctionPointEntity> getAuthFunctionPointEntityListById(String parentId) {
        String hql = "from AuthFunctionPointEntity where state = '0' and parentId=:parentId  ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("parentId", parentId);
        List<AuthFunctionPointEntity> list = query.list();
        return list;
    }

    @Override
    public List<AuthFunctionPointRoleEntity> getAuthFunctionPointRoleEntityList(String category, String classification, String authRoleId) {
        String hql = "from AuthFunctionPointRoleEntity where state = '0' and category=:category and classification=:classification and authRoleId=:authRoleId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("category", category);
        query.setParameter("classification", classification);
        query.setParameter("authRoleId", authRoleId);
        List<AuthFunctionPointRoleEntity> list = query.list();
        return list;
    }

    @Override
    public void upFunctionPointRole(String id, String category, String classification,String userid,String updaTime) {
        StringBuffer sql = new StringBuffer("UPDATE auth_function_point_role afpr ");
        sql.append(" SET ");
        sql.append(" afpr.STATE='1',afpr.MODIFY_BY='"+userid+"',MODIFY_DATE='"+updaTime+"' ");
        sql.append(" WHERE ");
        sql.append(" afpr.CATEGORY=:category and afpr.CLASSIFICATION=:classification ");
        sql.append(" AND  afpr.AUTH_ROLE_ID =:id ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setParameter("id",id);
        sqlQuery.setParameter("category",category);
        sqlQuery.setParameter("classification",classification);
        sqlQuery.executeUpdate();
    }

    @Override//删除不属于当前 角色id+功能点id+类别+分类 的组合
    public void upConcatFunctionPointRoleBy(String id, String category, String classification, String userid, String updaTime, List<String> deleteList) {
        StringBuffer sql = new StringBuffer("UPDATE auth_function_point_role afpr ");
        sql.append(" SET ");
        sql.append(" afpr.STATE='1',afpr.MODIFY_BY='"+userid+"',MODIFY_DATE='"+updaTime+"' ");
        sql.append(" WHERE ");
        sql.append(" afpr.CATEGORY=:category and afpr.CLASSIFICATION=:classification ");
        sql.append(" AND  afpr.AUTH_ROLE_ID =:id and  CONCAT(AUTH_ROLE_ID,AUTH_FUNCTION_ID,CATEGORY,CLASSIFICATION) not in(:delist)");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setParameter("id",id);
        sqlQuery.setParameter("category",category);
        sqlQuery.setParameter("classification",classification);
        sqlQuery.setParameterList("delist",deleteList);
        sqlQuery.executeUpdate();
    }

    @Override
    public void saveAuthFunctionPointRole(AuthFunctionPointRoleEntity authFunctionPointRoleEntity) {
        Session session = getCurrentSession();
        session.save(authFunctionPointRoleEntity);
        session.flush();
    }

    @Override
    public List<Object[]> getAuthAgencyRoleById(String authAgencyid,String category,WebPage webPage,String roleNature) {

        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(" SELECT arr.ROLE_ID,arr.ROLE_NAME,arr.PREFIX ");
        sql.append( " from auth_role_project ar " );
        sql.append( " LEFT JOIN  auth_role_roleset arr on ar.AUTH_ROLE_ID=arr.ROLE_ID " );
        sql.append( " where arr.STATE='0'" );
        //机构Id
        if(!StringUtil.isEmpty(category)){
            sql.append( " AND  arr.CATEGORY =? " );
            params.add(category);
        }
        if(!StringUtil.isEmpty(authAgencyid)){
            sql.append( " AND  ar.AUTH_AGENCY_ID =? " );
            params.add(authAgencyid);
        }
        if(!StringUtil.isEmpty(roleNature)){
            sql.append( " AND  arr.ROLE_NATURE =? " );
            params.add(roleNature);
        }

        sql.append(" order by arr.ROLE_NAME ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());
        for (int i = 0,length = params.size(); i < length; i++){
            query.setParameter(i,params.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public List<Object[]> getESAuthAgencyRoleById(String authAgencyid, String category, WebPage webPage, String roleNature) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(" SELECT arr.ROLE_ID,arr.ROLE_NAME,arr.PREFIX ");
        sql.append( " from auth_role_project ar " );
        sql.append( " LEFT JOIN  auth_role_roleset arr on ar.AUTH_ROLE_ID=arr.ROLE_ID " );
        sql.append( " where arr.STATE='0'and arr.ROLE_TYPE='0' " );
        //机构Id
        if(!StringUtil.isEmpty(category)){
            sql.append( " AND  arr.CATEGORY =? " );
            params.add(category);
        }
        if(!StringUtil.isEmpty(authAgencyid)){
            sql.append( " AND  ar.AUTH_AGENCY_ID =? " );
            params.add(authAgencyid);
        }
        if(!StringUtil.isEmpty(roleNature)){
            sql.append( " AND  arr.ROLE_NATURE =? " );
            params.add(roleNature);
        }

        sql.append(" order by arr.ROLE_NAME ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());
        for (int i = 0,length = params.size(); i < length; i++){
            query.setParameter(i,params.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public List<Object[]> getQCAuthAgencyRoleById(String authAgencyid, String category, WebPage webPage, String roleNature) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(" SELECT arr.ROLE_ID,arr.ROLE_NAME,arr.PREFIX ");
        sql.append( " from auth_role_project ar " );
        sql.append( " LEFT JOIN  auth_role_roleset arr on ar.AUTH_ROLE_ID=arr.ROLE_ID " );
        sql.append( " where arr.STATE='0' " );
        //机构Id
        if(!StringUtil.isEmpty(category)){
            if(!"1".equals(category)){
                sql.append("and arr.ROLE_TYPE='0' ");
            }
            sql.append( " AND  arr.CATEGORY =? " );
            params.add(category);
        }else{
            sql.append("and arr.ROLE_TYPE='0' ");
        }
        if(!StringUtil.isEmpty(authAgencyid)){
            sql.append( " AND  ar.AUTH_AGENCY_ID =? " );
            params.add(authAgencyid);
        }
        if(!StringUtil.isEmpty(roleNature)){
            sql.append( " AND  arr.ROLE_NATURE =? " );
            params.add(roleNature);
        }

        sql.append(" order by arr.ROLE_NAME ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());
        for (int i = 0,length = params.size(); i < length; i++){
            query.setParameter(i,params.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public List<Object[]> getAuthAgencyRoleUserById(String authAgencyid, String roleId) {
        return null;
    }

    @Override
    public List<Object[]> getusetNameByRoleIdAuthAgencyId(String roleId, String authAgnecyId,String type) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ui.STAFF_NAME,rsp.MODIFY_ON,ui.STAFF_ID ");
        sql.append(" from role_staff_project_map rsp ");
        sql.append(" LEFT JOIN user_information ui ON rsp.STAFF_ID = ui.STAFF_ID");
        sql.append(" LEFT JOIN user_map um on ui.STAFF_ID=um.STAFF_ID");
        sql.append(" where  rsp.STATE = '1' and um.STATE='1'  ");
        sql.append(" and (um.PROJECT_MODULE=:type or um.PROJECT_MODULE='all')  and rsp.AGENCY_ID=:authAgnecyId and rsp.ROLE_ID=:roleId  ORDER BY rsp.MODIFY_ON");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("roleId", roleId);
        query.setParameter("authAgnecyId", authAgnecyId);
        query.setParameter("type", type);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getusetNameByESRoleIdAuthAgencyId(String roleId, String authAgnecyId, String type) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ui.STAFF_NAME,rsp.MODIFY_ON,ui.STAFF_ID ");
        sql.append(" from role_staff_projectes_map rsp ");
        sql.append(" LEFT JOIN user_information ui ON rsp.STAFF_ID = ui.STAFF_ID");
        sql.append(" LEFT JOIN user_map um on ui.STAFF_ID=um.STAFF_ID");
        sql.append(" where  rsp.STATE = '1' and um.STATE='1'  ");
        sql.append(" and (um.PROJECT_MODULE=:type or um.PROJECT_MODULE='all')  and rsp.AGENCY_ID=:authAgnecyId and rsp.ROLE_ID=:roleId  ORDER BY rsp.MODIFY_ON");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("roleId", roleId);
        query.setParameter("authAgnecyId", authAgnecyId);
        query.setParameter("type", type);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getusetNameByQCRoleIdAuthAgencyId(String roleId, String authAgnecyId, String type) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ui.STAFF_NAME,rsp.MODIFY_ON,ui.STAFF_ID ");
        sql.append(" from role_staff_projectqc_map rsp ");
        sql.append(" LEFT JOIN user_information ui ON rsp.STAFF_ID = ui.STAFF_ID");
        sql.append(" LEFT JOIN user_map um on ui.STAFF_ID=um.STAFF_ID");
        sql.append(" where  rsp.STATE = '1' and um.STATE='1'  ");
        sql.append(" and (um.PROJECT_MODULE=:type or um.PROJECT_MODULE='all')  and rsp.AGENCY_ID=:authAgnecyId and rsp.ROLE_ID=:roleId  ORDER BY rsp.MODIFY_ON");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("roleId", roleId);
        query.setParameter("authAgnecyId", authAgnecyId);
        query.setParameter("type", type);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<String> getRoleIdlistById(String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ROLE_ID ");
        sql.append(" FROM role_staff_project_map  ");
        sql.append(" where STATE='1' and STAFF_ID=:staffId  GROUP BY ROLE_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }

    }

    @Override
    public List<String> getESRoleIdlistById(String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ROLE_ID ");
        sql.append(" FROM role_staff_projectes_map  ");
        sql.append(" where STATE='1' and STAFF_ID=:staffId  GROUP BY ROLE_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<String> getQCRoleIdlistById(String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ROLE_ID ");
        sql.append(" FROM role_staff_projectqc_map  ");
        sql.append(" where STATE='1' and STAFF_ID=:staffId  GROUP BY ROLE_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<String> getRoleViewIdlistById(List<String> roleIdList, String leve,String category) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" AUTH_FUNCTION_ID ");
        sql.append(" FROM auth_function_point_role afp  ");
        sql.append(" LEFT JOIN auth_function_point af ON afp.AUTH_FUNCTION_ID = af.CURRENT_ID  ");
        sql.append(" where afp.STATE='0' and af.STATE='0' and af.`LEVEL`=:leve and af.CATEGORY=:category and af.CLASSIFICATION='2'  ");
        sql.append(" and afp.AUTH_ROLE_ID in(:roleIdList)  ");
        sql.append(" GROUP BY AUTH_FUNCTION_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("leve", leve);
        query.setParameter("category", category);
        query.setParameterList("roleIdList", roleIdList);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public boolean checkAuthByUseridAndAgencyId(String authAgencyid, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" AGENCY_ID   ");
        sql.append(" from role_staff_project_map    ");
        sql.append(" where  1=1 and STATE='1'  and STAFF_ID=:staffId and AGENCY_ID=:authAgencyid ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("authAgencyid", authAgencyid);
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAuthFunctionByStaffId(String staffId,String level,String category) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" afp.AUTH_FUNCTION_ID ");
        sql.append(" FROM auth_function_point_role  afp  ");
        sql.append(" LEFT JOIN auth_function_point af on afp.AUTH_FUNCTION_ID=af.CURRENT_ID ");
        sql.append(" LEFT JOIN role_staff_project_map asp ON afp.AUTH_ROLE_ID = asp.ROLE_ID ");
        sql.append(" where af.STATE='0' and af.CATEGORY=:category and af.CLASSIFICATION='2' and afp.STATE='0' and asp.STATE='1' ");

        sql.append(" and  af.`LEVEL`=:level and asp.STAFF_ID =:staffId  ");
        sql.append(" GROUP BY afp.AUTH_FUNCTION_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("level", level);
        query.setParameter("staffId", staffId);
        query.setParameter("category", category);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getProjectAuthFunctionByStaffId(String staffId, String level, String category) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" afp.AUTH_FUNCTION_ID ");
        sql.append(" FROM auth_function_point_role  afp  ");
        sql.append(" LEFT JOIN auth_function_point af on afp.AUTH_FUNCTION_ID=af.CURRENT_ID ");
        sql.append(" LEFT JOIN role_staff_projectes_map asp ON afp.AUTH_ROLE_ID = asp.ROLE_ID ");
        sql.append(" where af.STATE='0' and af.CATEGORY=:category and af.CLASSIFICATION='2' and afp.STATE='0' and asp.STATE='1' ");

        sql.append(" and  af.`LEVEL`=:level and asp.STAFF_ID =:staffId  ");
        sql.append(" GROUP BY afp.AUTH_FUNCTION_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("level", level);
        query.setParameter("staffId", staffId);
        query.setParameter("category", category);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getQCProjectAuthFunctionByStaffId(String staffId, String level, String category) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" afp.AUTH_FUNCTION_ID ");
        sql.append(" FROM auth_function_point_role  afp  ");
        sql.append(" LEFT JOIN auth_function_point af on afp.AUTH_FUNCTION_ID=af.CURRENT_ID ");
        sql.append(" LEFT JOIN role_staff_projectqc_map asp ON afp.AUTH_ROLE_ID = asp.ROLE_ID ");
        sql.append(" where af.STATE='0' and af.CATEGORY=:category and af.CLASSIFICATION='2' and afp.STATE='0' and asp.STATE='1' ");

        sql.append(" and  af.`LEVEL`=:level and asp.STAFF_ID =:staffId  ");
        sql.append(" GROUP BY afp.AUTH_FUNCTION_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("level", level);
        query.setParameter("staffId", staffId);
        query.setParameter("category", category);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getAuthFunctionAndProjectIdByStaffId(String function, String staffId, String level) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID ");
        sql.append(" FROM role_staff_project_map asp ");
        sql.append(" LEFT JOIN auth_function_point_role afp ON afp.AUTH_ROLE_ID = asp.ROLE_ID ");
        sql.append(" LEFT JOIN auth_function_point af on afp.AUTH_FUNCTION_ID=af.CURRENT_ID ");
        sql.append(" where af.STATE='0' and af.CATEGORY='3' and af.CLASSIFICATION='2' and afp.STATE='0' and asp.STATE='1' ");

        sql.append(" and  af.`LEVEL`=:level and asp.STAFF_ID =:staffId and afp.AUTH_FUNCTION_ID=:function ");
        sql.append(" GROUP BY asp.AGENCY_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("level", level);
        query.setParameter("staffId", staffId);
        query.setParameter("function",function);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getNewsFunctionByStaffId(String function, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID ");
        sql.append(" FROM role_staff_projectqc_map asp ");
        sql.append(" LEFT JOIN auth_function_point_role afp ON afp.AUTH_ROLE_ID = asp.ROLE_ID ");
        sql.append(" LEFT JOIN auth_function_point af on afp.AUTH_FUNCTION_ID=af.CURRENT_ID ");
        sql.append(" LEFT JOIN auth_role_agencyqc ara on ara.AGENCY_ID=asp.AGENCY_ID ");
        sql.append(" where af.STATE='0' and af.CATEGORY='1' and af.CLASSIFICATION='2' and afp.STATE='0' and asp.STATE='1' ");
        sql.append(" and asp.STAFF_ID =:staffId and afp.AUTH_FUNCTION_ID=:function and ara.AGENCY_TYPE in('100000000','100000001')  ");
        sql.append(" GROUP BY asp.AGENCY_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("staffId", staffId);
        query.setParameter("function",function);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getNewsProjectNameAndIdByStaffId(String function, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID,ara.AGENCY_NAME ");
        sql.append(" FROM role_staff_projectqc_map asp ");
        sql.append(" LEFT JOIN auth_function_point_role afp ON afp.AUTH_ROLE_ID = asp.ROLE_ID ");
        sql.append(" LEFT JOIN auth_function_point af on afp.AUTH_FUNCTION_ID=af.CURRENT_ID ");
        sql.append(" LEFT JOIN auth_role_agencyqc ara on ara.AGENCY_ID=asp.AGENCY_ID ");
        sql.append(" where af.STATE='0' and af.CATEGORY='1' and af.CLASSIFICATION='2' and afp.STATE='0' and asp.STATE='1' ");
        sql.append(" and asp.STAFF_ID =:staffId and afp.AUTH_FUNCTION_ID=:function and ara.AGENCY_TYPE in('100000000','100000001')  ");
        sql.append(" GROUP BY asp.AGENCY_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("staffId", staffId);
        query.setParameter("function",function);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public boolean checkAuthRoleByUserIdandtype(String type, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID   ");
        sql.append(" from role_staff_project_map asp   ");
        sql.append(" LEFT JOIN auth_role_agency ara on asp.AGENCY_ID=ara.AGENCY_ID  ");
        sql.append(" where  asp.STATE='1' and ara.`STATUS`='1'  and asp.STAFF_ID=:staffId and ara.AGENCY_TYPE=:type ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("type", type);
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkESAuthRoleByUserIdandtype(String type, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID   ");
        sql.append(" from role_staff_projectes_map asp   ");
        sql.append(" LEFT JOIN auth_role_agencyes ara on asp.AGENCY_ID=ara.AGENCY_ID  ");
        sql.append(" where  asp.STATE='1' and ara.`STATUS`='1'  and asp.STAFF_ID=:staffId and ara.AGENCY_TYPE=:type ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("type", type);
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkQCAuthRoleByUserIdandtype(String type, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID   ");
        sql.append(" from role_staff_projectqc_map asp   ");
        sql.append(" LEFT JOIN auth_role_agencyqc ara on asp.AGENCY_ID=ara.AGENCY_ID  ");
        sql.append(" where  asp.STATE='1' and ara.`STATUS`='1'  and asp.STAFF_ID=:staffId and ara.AGENCY_TYPE=:type ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("type", type);
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<String> checkAuthRoleListByUserIdandtype(String type, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID   ");
        sql.append(" from role_staff_project_map asp   ");
        sql.append(" LEFT JOIN auth_role_agency ara on asp.AGENCY_ID=ara.AGENCY_ID  ");
        sql.append(" where  asp.STATE='1' and ara.`STATUS`='1'  and asp.STAFF_ID=:staffId and ara.AGENCY_TYPE=:type  group by asp.AGENCY_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("type", type);
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<String> checkESAuthRoleListByUserIdandtype(String type, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID   ");
        sql.append(" from role_staff_projectes_map asp   ");
        sql.append(" LEFT JOIN auth_role_agencyes ara on asp.AGENCY_ID=ara.AGENCY_ID  ");
        sql.append(" where  asp.STATE='1' and ara.`STATUS`='1'  and asp.STAFF_ID=:staffId and ara.AGENCY_TYPE=:type  group by asp.AGENCY_ID order by ara.sort");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("type", type);
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    public List<String> checkQCAuthRoleListByUserIdandtype(String type, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID   ");
        sql.append(" from role_staff_projectqc_map asp   ");
        sql.append(" LEFT JOIN auth_role_agencyqc ara on asp.AGENCY_ID=ara.AGENCY_ID  ");
        sql.append(" where  asp.STATE='1' and ara.`STATUS`='1'  and asp.STAFF_ID=:staffId and ara.AGENCY_TYPE=:type  group by asp.AGENCY_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("type", type);
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public boolean checkAuthRoleByUserIdAndAgencyId(String agencyId, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID  ");
        sql.append(" from role_staff_project_map asp   ");
        sql.append(" where  asp.STATE='1' and asp.STAFF_ID=:staffId and  asp.AGENCY_ID=:agencyId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("agencyId", agencyId);
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkESAuthRoleByUserIdAndAgencyId(String agencyId, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID  ");
        sql.append(" from role_staff_projectes_map asp   ");
        sql.append(" where  asp.STATE='1' and asp.STAFF_ID=:staffId and  asp.AGENCY_ID=:agencyId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("agencyId", agencyId);
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkQCAuthRoleByUserIdAndAgencyId(String agencyId, String staffId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID  ");
        sql.append(" from role_staff_projectqc_map asp   ");
        sql.append(" where  asp.STATE='1' and asp.STAFF_ID=:staffId and  asp.AGENCY_ID=:agencyId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("agencyId", agencyId);
        query.setParameter("staffId", staffId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<String> getRoleIdlistByAgencyId(String agencyId, String level) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" arp.AUTH_ROLE_ID ");
        sql.append(" from auth_role_project arp ");
        sql.append(" LEFT JOIN auth_role_roleset arr ON arp.AUTH_ROLE_ID = arr.ROLE_ID ");
        sql.append(" where  arr.STATE='0' and arr.CATEGORY='3'  ");
        sql.append(" and  arr.ROLE_NATURE=:level  and arp.AUTH_AGENCY_ID=:agencyId  ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("level", level);
        query.setParameter("agencyId", agencyId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public boolean checkDeleteRole(String roleId) {
        StringBuffer sql = new StringBuffer(" select");
        sql.append(" ID ");
        sql.append(" from role_staff_project_map ");
        sql.append(" where  STATE='1' and ROLE_ID=:roleId1 ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("roleId1", roleId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    @Override

    public List<AuthAgencyEntity> getAllAgencyListByParentId(List<String> parentIdList, List<String> agencyIdList,String type) {
        String hql = "from AuthAgencyEntity where status = '1' and agencyType=:type1 ";
        if (parentIdList != null && parentIdList.size() > 0) {
            hql += " and parentId in(:parentIdList) ";
        }
        if (agencyIdList != null && agencyIdList.size() > 0) {
            hql += " and  agencyId in(:agencyIdList) ";
        }
        hql += "group by agencyId order by agencyType,orderNum ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("type1", type);
        if (parentIdList != null && parentIdList.size() > 0) {
            query.setParameterList("parentIdList", parentIdList);
        }
        if (agencyIdList != null && agencyIdList.size() > 0) {
            query.setParameterList("agencyIdList", agencyIdList);
        }
        List<AuthAgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyESEntity> getESAllAgencyListByParentId(List<String> parentIdList, List<String> agencyIdList, String type) {
        String hql = "from AuthAgencyESEntity where status = '1' ";
        if (type != null && !"".equals(type)) {
            hql += " and agencyType=:type";
        }
        if (parentIdList != null && parentIdList.size() > 0) {
            hql += " and parentId in(:parentIdList) ";
        }
        if (agencyIdList != null && agencyIdList.size() > 0) {
            hql += " or  agencyId in(:agencyIdList) ";
        }
        hql += "group by agencyId order by sort,agencyType,orderNum ";
        Query query = getCurrentSession().createQuery(hql);
        if (type != null && !"".equals(type)) {
            query.setParameter("type", type);
        }
        if (parentIdList != null && parentIdList.size() > 0) {
            query.setParameterList("parentIdList", parentIdList);
        }
        if (agencyIdList != null && agencyIdList.size() > 0) {
            query.setParameterList("agencyIdList", agencyIdList);
        }
        List<AuthAgencyESEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyQCEntity> getQCAllAgencyListByParentId(List<String> parentIdList, List<String> agencyIdList, String type) {
        String hql = "from AuthAgencyQCEntity where status = '1' ";
        if (type != null && !"".equals(type)) {
            hql += " and agencyType=:type";
        }
        if (parentIdList != null && parentIdList.size() > 0) {
            hql += " and parentId in(:parentIdList) ";
        }
        if (agencyIdList != null && agencyIdList.size() > 0) {
            hql += " and  agencyId in(:agencyIdList) ";
        }
        hql += "group by agencyId order by agencyType,orderNum ";
        Query query = getCurrentSession().createQuery(hql);
        if (type != null && !"".equals(type)) {
            query.setParameter("type", type);
        }
        if (parentIdList != null && parentIdList.size() > 0) {
            query.setParameterList("parentIdList", parentIdList);
        }
        if (agencyIdList != null && agencyIdList.size() > 0) {
            query.setParameterList("agencyIdList", agencyIdList);
        }
        List<AuthAgencyQCEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    public List<AuthAgencyEntity> getCityListByAreaId(String areaId) {
        StringBuilder hql = new StringBuilder(" FROM AuthAgencyEntity ");
        if(null != areaId && !StringUtil.isEmpty(areaId)){
            hql.append(" WHERE 1=1 AND parentId=:areaId AND agencyType='100000003' AND status='1' ");
        }else {
            hql.append(" WHERE 1=1 AND agencyType='100000001' AND status='1' ");
        }
        Query query = getCurrentSession().createQuery(hql.toString());
        if(null != areaId && !StringUtil.isEmpty(areaId)) {
            query.setParameter("areaId", areaId);
        }
        List<AuthAgencyEntity> list = query.list();
        return list;
    }

    @Override
    public List<AuthAgencyESEntity> getESCityListByAreaId(String areaId) {
        StringBuilder hql = new StringBuilder(" FROM AuthAgencyESEntity ");
        if(null != areaId && !StringUtil.isEmpty(areaId)){
            hql.append(" WHERE 1=1 AND parentId=:areaId AND agencyType='100000003' AND status='1' ");
        }else {
            hql.append(" WHERE 1=1 AND agencyType='100000001' AND status='1' ");
        }
        Query query = getCurrentSession().createQuery(hql.toString());
        if(null != areaId && !StringUtil.isEmpty(areaId)) {
            query.setParameter("areaId", areaId);
        }
        List<AuthAgencyESEntity> list = query.list();
        return list;
    }

    @Override
    public List<AuthAgencyQCEntity> getQCCityListByAreaId(String areaId) {
        StringBuilder hql = new StringBuilder(" FROM AuthAgencyQCEntity ");
        if(null != areaId && !StringUtil.isEmpty(areaId)){
            hql.append(" WHERE 1=1 AND parentId=:areaId AND agencyType='100000003' AND status='1' ");
        }else {
            hql.append(" WHERE 1=1 AND agencyType='100000001' AND status='1' ");
        }
        Query query = getCurrentSession().createQuery(hql.toString());
        if(null != areaId && !StringUtil.isEmpty(areaId)) {
            query.setParameter("areaId", areaId);
        }
        List<AuthAgencyQCEntity> list = query.list();
        return list;
    }

    @Override
    public void saveAuthAgency(AuthAgencyEntity authAgencyEntity) {
        Session session = getCurrentSession();
        session.save(authAgencyEntity);
        session.flush();
        session.close();

    }

    @Override
    public void saveESAuthAgency(AuthAgencyESEntity authAgencyESEntity) {
        Session session = getCurrentSession();
        session.save(authAgencyESEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<Object[]> getAuthClassifyStaff(String projectNum, String classifyId,WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(" SELECT ui.STAFF_NAME,qcs.classify_id,hp.`NAME`,qcs.modify_on ");
        sql.append( " from qc_classify_staff_relation qcs  " );
        sql.append( " LEFT JOIN  user_information ui on qcs.staff_id=ui.STAFF_ID " );
        sql.append( " LEFT JOIN  house_project hp on qcs.project_num=hp.PINYIN_CODE " );
        sql.append( " where ui.STAFF_STATE='1' " );
        //机构Id
        if(!StringUtil.isEmpty(projectNum)){
            sql.append( " AND  qcs.project_num =? " );
            params.add(projectNum);
        }
        if(!StringUtil.isEmpty(classifyId)){
            sql.append( " AND  qcs.classify_id =? " );
            params.add(classifyId);
        }
        sql.append(" order by hp.`NAME` ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());
        for (int i = 0,length = params.size(); i < length; i++){
            query.setParameter(i,params.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

}
