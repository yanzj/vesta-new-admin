package com.maxrocky.vesta.presistent.project.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baisc.model.BasicStaffDataEntity;
import com.maxrocky.vesta.domain.project.model.SecurityAreaEntity;
import com.maxrocky.vesta.domain.project.model.SecurityGroupEntity;
import com.maxrocky.vesta.domain.project.model.SecurityProjectEntity;
import com.maxrocky.vesta.domain.project.model.SecurityRoleEntity;
import com.maxrocky.vesta.domain.project.repository.SecurityProjectRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
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
 * Created by Jason on 2017/6/5.
 */
@Repository
public class SecurityProjectRepositoryImpl extends HibernateDaoImpl implements SecurityProjectRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<SecurityGroupEntity> getSecurityGroupList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from SecurityGroupEntity sg where sg.state='1' ";
        if (map.get("groupName") != null && !"".equals(map.get("groupName").toString())) {
            hql += " and sg.groupName like '%" + map.get("groupName").toString() + "%' ";
        }
        hql += " order by sg.modifyOn desc";

        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<SecurityGroupEntity> securityGroupEntityList = (List<SecurityGroupEntity>) getHibernateTemplate().find(hql, params.toArray());
        return securityGroupEntityList;
    }

    @Override
    public void addSecurityEntity(Object o) {
        Session session = getCurrentSession();
        session.save(o);
        session.flush();
        session.close();
    }

    @Override
    public SecurityGroupEntity getSecurityGroupDetailById(String groupId) {
        String hql = " from SecurityGroupEntity sg where sg.groupId=:groupId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("groupId", groupId);
        SecurityGroupEntity securityGroupEntity = (SecurityGroupEntity) query.uniqueResult();
        return securityGroupEntity;
    }

    @Override
    public List<Object[]> getEmploysForGroupDepByGroupId(String groupId, String s) {
        String sql = "SELECT DISTINCT ra.AGENCY_ID,ra.AGENCY_NAME FROM role_agency ra LEFT JOIN st_role sr ON sr.DATA_ID = ra.AGENCY_ID " +
                " WHERE sr.STATUS='1' AND ra.STATUS='1' AND sr.DATA_TYPE='1' AND sr.TYPE_ROLE=:permission AND  sr.TYPE_ID=:typeId ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("permission", s);
        query.setParameter("typeId", groupId);
        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public List<Object[]> getStaffsForGroupStaffByGroupId(String groupId, String s) {
        String sql = "SELECT DISTINCT up.STAFF_ID,up.STAFF_NAME FROM user_propertyStaff up LEFT JOIN st_role sr ON sr.DATA_ID = up.STAFF_ID " +
                "WHERE up.STAFF_STATE='1' AND sr.STATUS='1' AND sr.DATA_TYPE='2' AND sr.TYPE_ROLE=:permission AND  sr.TYPE_ID=:typeId";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("permission", s);
        query.setParameter("typeId", groupId);
        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public boolean updateEntity(Object o) {
        Session session = getCurrentSession();
        session.update(o);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public List<SecurityRoleEntity> getStaffEmploys(String typeId, String dataType, String typeRole) {
        String hql = "from SecurityRoleEntity where typeId=:typeId and dataType=:dataType and typeRole=:permission and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("typeId", typeId);
        query.setParameter("dataType", dataType);
        query.setParameter("permission", typeRole);
        List<SecurityRoleEntity> securityRoleEntityList = query.list();
        return securityRoleEntityList;
    }

    @Override
    public void deleteGroupRole(SecurityRoleEntity securityRoleEntity) {
        String hql = "update SecurityRoleEntity set modifyTime=:modifyTime,status='0' where dataType =:dataType and dataId =:dataId and typeId =:typeId and typeRole=:permission";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dataId", securityRoleEntity.getDataId());
        query.setParameter("dataType", securityRoleEntity.getDataType());
        query.setParameter("typeId", securityRoleEntity.getTypeId());
        query.setParameter("permission", securityRoleEntity.getTypeRole());
        query.setParameter("modifyTime", securityRoleEntity.getModifyTime());
        query.executeUpdate();
    }

    @Override
    public void dumpAddGroupRole(SecurityRoleEntity securityRoleEntity) {
        String sql1 = "INSERT INTO st_role(TYPE_ID,TYPE_ROLE,DATA_TYPE,DATA_ID,MODIFY_TIME,STATUS) VALUES(?,?,?,?,?,1) ON DUPLICATE KEY UPDATE STATUS='1',MODIFY_TIME=?";
        Query query1 = getCurrentSession().createSQLQuery(sql1);
        query1.setString(0, securityRoleEntity.getTypeId());
        query1.setString(1, securityRoleEntity.getTypeRole());
        query1.setString(2, securityRoleEntity.getDataType());
        query1.setString(3, securityRoleEntity.getDataId());
        query1.setString(4, DateUtils.format(new Date()));
        query1.setString(5, DateUtils.format(new Date()));
        query1.executeUpdate();
    }

    @Override
    public List<SecurityAreaEntity> getSecurityAreaList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from SecurityAreaEntity sa where sa.state='1'";
        if (map.get("groupId") != null && !"".equals(map.get("groupId").toString())) {
            hql += "  and sa.groupId='" + map.get("groupId").toString() + "' ";
        }
        if (map.get("groupName") != null && !"".equals(map.get("groupName").toString())) {
            hql += " and sa.groupName like '%" + map.get("groupName").toString() + "%' ";
        }
        if (map.get("areaName") != null && !"".equals(map.get("areaName").toString())) {
            hql += " and sa.areaName like '%" + map.get("areaName").toString() + "%' ";
        }
        hql += " order by sa.modifyOn desc";

        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<SecurityAreaEntity> securityAreaEntityList = (List<SecurityAreaEntity>) getHibernateTemplate().find(hql, params.toArray());
        return securityAreaEntityList;
    }

    @Override
    public SecurityAreaEntity getSecurityAreaDetailById(String areaId) {
        String hql = " from SecurityAreaEntity sa where sa.areaId=:areaId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("areaId", areaId);
        SecurityAreaEntity securityAreaEntity = (SecurityAreaEntity) query.uniqueResult();
        return securityAreaEntity;
    }

    @Override
    public List<SecurityProjectEntity> getSecurityProjectList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from SecurityProjectEntity sp where sp.state='1'";
        if (map.get("groupId") != null && !"".equals(map.get("groupId").toString())) {
            hql += "  and sp.groupId='" + map.get("groupId").toString() + "' ";
        }
        if (map.get("areaId") != null && !"".equals(map.get("areaId").toString())) {
            hql += " and sp.areaId = '" + map.get("areaId").toString() + "' ";
        }
        if (map.get("projectName") != null && !"".equals(map.get("projectName").toString())) {
            hql += " and sp.projectName like '%" + map.get("projectName").toString() + "%' ";
        }
        hql += " order by sp.modifyOn desc";

        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<SecurityProjectEntity> securityProjectEntityList = (List<SecurityProjectEntity>) getHibernateTemplate().find(hql, params.toArray());
        return securityProjectEntityList;
    }

    @Override
    public SecurityProjectEntity getSecurityProjectDetailById(String projectId) {
        String hql = " from SecurityProjectEntity sp where sp.projectId=:projectId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        SecurityProjectEntity securityProjectEntity = (SecurityProjectEntity) query.uniqueResult();
        return securityProjectEntity;
    }

    @Override
    public boolean deleteSecurityProject(String projectId, String groupId) {
        String hql = "update SecurityProjectEntity set state='0',modifyOn=:modifyDate where ";
        if (!StringUtil.isEmpty(projectId)) {
            hql += " projectId = '" + projectId + "'";
        }
        if (!StringUtil.isEmpty(groupId)) {
            hql += " groupId = '" + groupId + "'";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyDate", new Date());
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteBasicProject(String id, String ids) {
        String sql = "UPDATE st_basic_project sbp SET sbp.STATE='0',sbp.MODIFY_DATE=:modifyTime WHERE ";
        if (!StringUtil.isEmpty(id)) {
            sql += " sbp.CURRENT_ID='"+id+"' or sbp.PARENT_ID ='"+id+"' ";
        }
        if (!StringUtil.isEmpty(ids)) {
            sql += " sbp.CURRENT_ID in(" + ids + ") ";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
        return false;
    }

    @Override
    public void deleteSecurityRole(String typeId, String typeIds) {
        String sql = "UPDATE st_role sr SET sr.STATUS='0',sr.MODIFY_TIME=:modifyTime WHERE  ";
        if (!StringUtil.isEmpty(typeId)) {
            sql += " sr.TYPE_ID='" + typeId + "'";
        }
        if (!StringUtil.isEmpty(typeIds)) {
            sql += " sr.TYPE_ID in(" + typeIds + ")";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }

    @Override
    public boolean deleteSecurityArea(String areaId, String groupId) {
        String hql = "update SecurityAreaEntity set state='0',modifyOn=:modifyDate where ";
        if (!StringUtil.isEmpty(areaId)) {
            hql += " areaId = '" + areaId + "'";
        }
        if (!StringUtil.isEmpty(groupId)) {
            hql += " groupId ='" + groupId + "'";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyDate", new Date());
        query.executeUpdate();
        return true;
    }

    @Override
    public List<SecurityProjectEntity> getSecurityProjectList(String areaId, String groupId) {
        String hql = " from SecurityProjectEntity sp where 1=1 ";
        if (!StringUtil.isEmpty(areaId)) {
            hql += " and sp.areaId ='" + areaId + "'";
        }
        if (!StringUtil.isEmpty(groupId)) {
            hql += " and sp.groupId='" + groupId + "'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<SecurityProjectEntity> securityProjectEntityList = query.list();
        return securityProjectEntityList;
    }

    @Override
    public boolean deleteSecurityProjectByAreaId(String areaId) {
        String hql = "update SecurityProjectEntity set state='0',modifyOn=:modifyDate where areaId=:areaId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyDate", new Date());
        query.setParameter("areaId", areaId);
        query.executeUpdate();
        return true;
    }

    @Override
    public void deleteSecurityRoleByTypeIds(String typeIds) {
        String sql = "UPDATE st_role sr SET sr.STATUS='0',sr.MODIFY_TIME=:modifyTime WHERE sr.TYPE_ID in(" + typeIds + ") ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }

    @Override
    public boolean deleteSecurityGroup(String groupId) {
        String hql = "update SecurityGroupEntity set state='0',modifyOn=:modifyDate where groupId=:groupId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyDate", new Date());
        query.setParameter("groupId", groupId);
        query.executeUpdate();
        return true;
    }

    @Override
    public List<SecurityAreaEntity> getSecurityAreaList(String groupId) {
        String hql = " from SecurityAreaEntity sa where sa.groupId='" + groupId + "' ";
        Query query = getCurrentSession().createQuery(hql);
        List<SecurityAreaEntity> securityAreaEntityList = query.list();
        return securityAreaEntityList;
    }

    @Override
    public List<SecurityProjectEntity> getProject() {
        String hql = "from SecurityProjectEntity where state='1' ";
        Query query = getCurrentSession().createQuery(hql);
        List<SecurityProjectEntity> securityProject = query.list();
        return securityProject;
    }

    @Override
    public List<SecurityGroupEntity> getGroup() {
        String hql = "from SecurityGroupEntity where state='1' ";
        Query query = getCurrentSession().createQuery(hql);
        List<SecurityGroupEntity> securityGroup = query.list();
        return securityGroup;
    }

    @Override
    public List<SecurityAreaEntity> getAreaById(String id) {
        String hql = "from SecurityAreaEntity where state='1' and groupId=:id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        List<SecurityAreaEntity> securityArea = query.list();
        return securityArea;
    }

    @Override
    public List<SecurityProjectEntity> getProjectById(String id) {
        String hql = "from SecurityProjectEntity where state='1' and areaId=:id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        List<SecurityProjectEntity> securityProject = query.list();
        return securityProject;
    }

    @Override
    public void updateBasicProject(String id, String name) {
        String hql = "update BasicProjectEntity set name=:name,modifyDate=:modifyTime where currentId=:currentId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime", new Date());
        query.setParameter("currentId", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public boolean checkGroupName(String groupName) {
        String hql = "from SecurityGroupEntity where state='1' and groupName=:groupName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("groupName", groupName);
        if (query.list() != null && query.list().size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean checkAreaName(String areaName) {
        String hql = "from SecurityAreaEntity where state='1' and areaName=:areaName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("areaName", areaName);
        if (query.list() != null && query.list().size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean checkProjectName(String projectName) {
        String hql = "from SecurityProjectEntity where state='1' and projectName=:projectName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectName", projectName);
        if (query.list() != null && query.list().size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<BasicStaffDataEntity> getBasicStaffEmploys(String dataId, String dataRole, String dataType) {
        String hql = "from BasicStaffDataEntity where dataId=:dataId and dataRole=:dataRole and dataType=:dataType and state='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dataId", dataId);
        query.setParameter("dataRole", dataRole);
        query.setParameter("dataType", dataType);
        List<BasicStaffDataEntity> basicStaffDataEntityList = query.list();
        return basicStaffDataEntityList;
    }

    @Override
    public void deleteBasicRole(BasicStaffDataEntity basicStaffDataEntity) {
        String hql = "update BasicStaffDataEntity set modifyTime=:modifyTime,state='0' where dataId =:dataId and dataRole =:dataRole and staffId =:staffId and dataType=:dataType";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dataId", basicStaffDataEntity.getDataId());
        query.setParameter("dataRole", basicStaffDataEntity.getDataRole());
        query.setParameter("staffId", basicStaffDataEntity.getStaffId());
        query.setParameter("dataType", basicStaffDataEntity.getDataType());
        query.setParameter("modifyTime", basicStaffDataEntity.getModifyTime());
        query.executeUpdate();
    }

    @Override
    public void deleteBasicRoles(String id,String ids) {
        String sql = "UPDATE st_basic_staff sbf SET sbf.STATE='0',sbf.MODIFY_TIME=:modifyTime WHERE ";
        if (!StringUtil.isEmpty(id)) {
            sql += " sbf.DATA_ID='" + id + "'";
        }
        if (!StringUtil.isEmpty(ids)) {
            sql += " sbf.DATA_ID in(" + ids + ")";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }

    @Override
    public void dumpAddBasicRole(BasicStaffDataEntity basicStaffDataEntity) {
        String sql1 = "INSERT INTO st_basic_staff(DATA_ID,DATA_TYPE,DATA_ROLE,STAFF_ID,STATE,MODIFY_TIME) VALUES(?,?,?,?,'1',?) ON DUPLICATE KEY UPDATE STATE='1',MODIFY_TIME=?";
        Query query1 = getCurrentSession().createSQLQuery(sql1);
        query1.setString(0, basicStaffDataEntity.getDataId());
        query1.setString(1, basicStaffDataEntity.getDataType());
        query1.setString(2, basicStaffDataEntity.getDataRole());
        query1.setString(3, basicStaffDataEntity.getStaffId());
        query1.setString(4, DateUtils.format(new Date()));
        query1.setString(5, DateUtils.format(new Date()));
        query1.executeUpdate();
    }

    @Override
    public List<SecurityProjectEntity> getPorjectByGroupId(String id) {
        String hql = " from SecurityProjectEntity where 1=1 and groupId='"+id+"' ";
        Query query = getCurrentSession().createQuery(hql);
        List<SecurityProjectEntity> securityProjectEntityList = query.list();
        return securityProjectEntityList;
    }

    @Override
    public Object[] getPlanById(String planId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ID,MODIFY_DATE,APP_ID,PLAN_ID,PLAN_NAME,CREATE_ID,CREATE_NAME,CREATE_DATE,CREATE_UNIT_ID,CREATE_UNIT_NAME, ");
        sql.append(" PLAN_START,PLAN_END,PARTICIPANT,STATE,SCORE,TYPE,FRACTION ");
        sql.append(" from st_inspection_plan ");
        sql.append(" where 1=1");
        sql.append(" and PLAN_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", planId);
        List<Object[]> list = query.list();
        if(list.size()>0 && null != list){
            return list.get(0);
        }
        return null;
    }


    @Override
    public List<Object[]> getPlanImageById(String planId,String type) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" BUSINESS_ID,ID,IMAGE_URL,IMAGE_TYPE ");
        sql.append(" from st_security_image ");
        sql.append(" where 1=1");
        sql.append(" and BUSINESS_ID=:id ");
        sql.append(" and STATE='1' and IMAGE_TYPE=:type ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("type",type);
        query.setParameter("id", planId);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getPlanProjectById(String planId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" TYPE,ID,MODIFY_DATE,LEVEL,UNIT_ID,PLAN_ID,PLAN_NAME,PROJECT_ID,PROJECT_NAME,STATE,SCORE,PLAN_START,PLAN_END,PARTICIPANT,FRACTION,RECTIFY_STATE ");
        sql.append(" from st_inspection_unit ");
        sql.append(" where 1=1");
        sql.append(" and PLAN_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", planId);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getPlanImageByIdList(List<String> idList, String type) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" BUSINESS_ID,ID,IMAGE_URL,IMAGE_TYPE ");
        sql.append(" from st_security_image ");
        sql.append(" where 1=1");
        sql.append(" and BUSINESS_ID in(:idList) ");
        sql.append(" and STATE='1' and IMAGE_TYPE=:type ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("type",type);
        query.setParameterList("idList", idList);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<SecurityRoleEntity> securityRoleEntity() {
        StringBuilder hql = new StringBuilder(" FROM SecurityRoleEntity ");
        hql.append(" WHERE 1=1 AND status='1' ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<SecurityRoleEntity> list = query.list();
        return list;
    }
}
