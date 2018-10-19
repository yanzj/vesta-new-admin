package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseCityEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/22 11:39.
 * Describe:员工信息Repository接口实现类
 */
@Repository
public class UserPropertyStaffRepositoryImpl extends HibernateDaoImpl implements UserPropertyStaffRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    /**
     * Describe:根据用户名、密码获取用户信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 11:55:02
     */
    @Override
    public UserPropertyStaffEntity getByUserNameAndPassword(String userName, String password) {
        String hql = "FROM UserPropertyStaffEntity WHERE userName = :userName AND password = :password and staffState='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        List<UserPropertyStaffEntity> userPropertyStaffEntityList = query.list();
        if(userPropertyStaffEntityList.size() == 0){
            return null;
        }

        return userPropertyStaffEntityList.get(0);
    }

    @Override
    public UserPropertyStaffEntity getByUserNameAndMobile(String userName, String mobile) {
        String hql = "from UserPropertyStaffEntity where userName=:userName and mobile=:mobile";
        Query query = getCurrentSession().createQuery(hql);
        List<UserPropertyStaffEntity> userPropertyStaffEntitys = query.list();
        if(userPropertyStaffEntitys.size() == 0){
            return null;
        }

        return userPropertyStaffEntitys.get(0);
    }
    /**
     * Describe:根据Id获取员工信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 01:53:22
     */
    @Override
    public UserPropertyStaffEntity get(String staffId) {
        return (UserPropertyStaffEntity)getCurrentSession().get(UserPropertyStaffEntity.class, staffId);
    }

    /**
     * Describe:根据Id获取员工信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 01:53:22
     */
    @Override
    public UserInformationEntity getUser(String staffId) {
        return (UserInformationEntity)getCurrentSession().get(UserInformationEntity.class, staffId);
    }


    @Override
    public UserPropertyStaffEntity CheckStaffByIdAPwd(UserPropertyStaffEntity propertystaff) {
        String hql = "FROM UserPropertyStaffEntity WHERE userName = :userName and password = :password and staffState = :staffState";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userName", propertystaff.getUserName());
        query.setParameter("password", propertystaff.getPassword());
        query.setParameter("staffState", UserPropertyStaffEntity.State_On);
        List<UserPropertyStaffEntity> propertystaffList = query.list();
        if(propertystaffList == null){
            return null;
        }
        if(propertystaffList.size() == 0){
            return null;
        }
        return propertystaffList.get(0);
    }

    /**
     * 获取所有员工信息列表
     * @return
     */
    @Override
    public List<UserPropertyStaffEntity> listPropertyStaff(UserPropertyStaffEntity userPropertyStaffEntity,WebPage webPage) {
        List<UserPropertyStaffEntity> userPropertyStaffEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from UserPropertyStaffEntity as us where 1 = 1");
        //项目不为空拼接项目
        if (userPropertyStaffEntity.getProjectId()!=null&&!"".equals(userPropertyStaffEntity.getProjectId())){
//            hql.append("and us.projectId = ?");
//            params.add(userPropertyStaffEntity.getProjectId());
            hql.append(SqlJoiningTogether.sqlStatement("us.projectId", userPropertyStaffEntity.getProjectId()));
        }
        if(userPropertyStaffEntity.getStaffId()!=null&&!"".equals(userPropertyStaffEntity.getStaffId())){
            hql.append("and us.staffId in (?)");
            params.add("%"+userPropertyStaffEntity.getStaffId()+"%");
        }
//        //部门不为空拼接部门
//        if (userPropertyStaffEntity.getDepartmentId()!=null&&!"".equals(userPropertyStaffEntity.getDepartmentId())){
//            hql.append("and us.departmentId = ?");
//            params.add(userPropertyStaffEntity.getDepartmentId());
//        }
        //姓名不为空拼接姓名
        if (userPropertyStaffEntity.getStaffName()!=null&&!"".equals(userPropertyStaffEntity.getStaffName())){
            hql.append("and us.staffName like ?");
            params.add("%"+userPropertyStaffEntity.getStaffName()+"%");
        }

        //用户名不为空拼接用户名
        if (userPropertyStaffEntity.getUserName()!=null&&!"".equals(userPropertyStaffEntity.getUserName())){
            hql.append("and us.userName like ?");
            params.add("%"+userPropertyStaffEntity.getUserName()+"%");
        }
//        //创建开始时间
//        if (userPropertyStaffEntity.getCreateBy()!=null&&!"".equals(userPropertyStaffEntity.getCreateBy())){
//            hql.append("and us.createOn >= ?");
//            params.add(DateUtils.parse(userPropertyStaffEntity.getCreateBy()+" 00:00:00"));
//        }
//        //创建结束时间
//        if (userPropertyStaffEntity.getModifyBy()!=null&&!"".equals(userPropertyStaffEntity.getModifyBy())){
//            hql.append("and us.createOn <= ?");
//            params.add(DateUtils.parse(userPropertyStaffEntity.getModifyBy()+" 23:00:00"));
//        }
        hql.append("and us.staffState =?");
        params.add(UserPropertyStaffEntity.State_On);
        hql.append("ORDER BY us.createOn DESC");


        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
//        Query query = getCurrentSession().createQuery(hql);
        userPropertyStaffEntities = (List<UserPropertyStaffEntity>)getHibernateTemplate().find(hql.toString());
        return userPropertyStaffEntities;
    }

    /**
     * 删除员工信息
     * @param staffId
     * @return
     */
    @Override
    public boolean deleteStaff(String staffId) {
        UserPropertyStaffEntity userPropertyStaffEntity = get(staffId);
        if (userPropertyStaffEntity!=null){
            Session session = getCurrentSession();
            session.delete(userPropertyStaffEntity);
            session.flush();
            session.close();
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * 添加新员工
     * @param userPropertyStaffEntity
     * @return
     */
    @Override
    public boolean addStaff(UserPropertyStaffEntity userPropertyStaffEntity) {
        UserPropertyStaffEntity staffEntity = get(userPropertyStaffEntity.getStaffId());
        if (staffEntity==null) {
            Session session = getCurrentSession();
            session.save(userPropertyStaffEntity);
            session.flush();
            session.close();
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addOuterStaff(UserInformationEntity userInformationEntity) {
        UserInformationEntity staffEntity = getUser(userInformationEntity.getStaffId());
        if (staffEntity==null) {
            Session session = getCurrentSession();
            session.save(userInformationEntity);
            session.flush();
            session.close();
            return true;
        }else {
            return false;
        }
    }

    /**
     * 修改新员工
     * @param userPropertyStaffEntity
     * @return
     */
    @Override
    public boolean updateStaff(UserPropertyStaffEntity userPropertyStaffEntity) {
        UserPropertyStaffEntity staff = get(userPropertyStaffEntity.getStaffId());
        if (staff!=null){
            Session session = getCurrentSession();
            session.update(userPropertyStaffEntity);
            session.flush();
            session.close();
            return true;
        }
        {
            return false;
        }
    }

    /**
     * 修改员工
     * @param userInformationEntity
     * @return
     */
    @Override
    public boolean saveOrUpdateStaff(UserInformationEntity userInformationEntity) {
        UserInformationEntity staff = getUser(userInformationEntity.getStaffId());
        if (staff!=null){
            Session session = getCurrentSession();
            session.update(userInformationEntity);
            session.flush();
            session.close();
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取项目和部门下的员工
     * @param sectionId
     * @param projectId
     * @return
     */
    @Override
    public List<UserPropertyStaffEntity> listStaffByCompanyAndSection(String projectId,String sectionId) {
        String hql = "from UserPropertyStaffEntity as u where u.staffState=:staffState and u.projectId=:projectId and u.departmentId = :sectionId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffState",UserPropertyStaffEntity.State_On);//有效
        query.setParameter("projectId",projectId);//项目
        query.setParameter("sectionId", sectionId);//部门
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    /**
     * 判断用户名是否存在
     * @param userName
     * @return
     */
    @Override
    public UserPropertyStaffEntity testStaffByUserName(String userName) {
        UserPropertyStaffEntity userPropertyStaffEntity = new UserPropertyStaffEntity();
        String hql = "from UserPropertyStaffEntity as u where u.userName = :userName";
        Query query = getCurrentSession().createQuery(hql);
//        query.setParameter("staffState",UserPropertyStaffEntity.State_On);//有效
        query.setParameter("userName", userName);//部门
        if(query.list().size()>0){
            userPropertyStaffEntity=(UserPropertyStaffEntity)query.list().get(0);
            return userPropertyStaffEntity;
        }
        return null;
    }

    @Override
    public UserPropertyStaffEntity checkOAUserName(String userName) {
        String hql = "from UserPropertyStaffEntity as u where u.userName = :userName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userName", userName);
        if(query.list().size()>0){
            UserPropertyStaffEntity userPropertyStaffEntity=(UserPropertyStaffEntity)query.list().get(0);
            return userPropertyStaffEntity;
        }
        return null;
    }

    @Override
    public UserInformationEntity getStaffByName(String userName) {
        String hql = "from UserInformationEntity as u where u.userName = :userName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userName", userName);
        if(query.list().size()>0){
            UserInformationEntity userInformationEntity=(UserInformationEntity)query.list().get(0);
            return userInformationEntity;
        }
        return null;
    }

    /**
     * 添加员工
     * @param userPropertyStaffEntity
     */
    @Override
    public void addUserPropertyStaff(UserPropertyStaffEntity userPropertyStaffEntity) {
        Session session = getCurrentSession();
        session.save(userPropertyStaffEntity);
        session.flush();
        session.close();
    }

    @Override
    public void addUserInformation(UserInformationEntity userInformationEntity) {
        Session session = getCurrentSession();
        session.save(userInformationEntity);
        session.flush();
        session.close();
    }

    /**
     * 修改员工
     * @param userPropertyStaffEntity
     */
    @Override
    public void updateUserPropertyStaff(UserPropertyStaffEntity userPropertyStaffEntity) {
        Session session = getCurrentSession();
        session.update(userPropertyStaffEntity);
        session.flush();
        session.close();
    }

    /**
     * Code:UI0001
     * Type:UI Method
     * Describe:This is describe.
     * CreateBy:Tom
     * CreateOn:2016-03-18 09:32:06
     */
    @Override
    public UserPropertyStaffEntity getByUserName(String userName) {
        String hql = "from UserPropertyStaffEntity as u where u.userName=:userName";
        Query query = getCurrentSession().createQuery(hql);
//        query.setParameter("staffState",UserPropertyStaffEntity.State_On);//有效
        query.setParameter("userName",userName);//项目
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        if(userPropertyStaffEntities == null){
            return null;
        }
        if(userPropertyStaffEntities.size() == 0){
            return null;
        }
        return userPropertyStaffEntities.get(0);
    }

    @Override
    public UserInformationEntity getStaffBySysName(String sysName) {
        String hql = "from UserInformationEntity where sysName=:sysName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("sysName",sysName);//用户名
        List<UserInformationEntity> userInformationEntities = query.list();
        if(userInformationEntities == null){
            return null;
        }
        if(userInformationEntities.size() == 0){
            return null;
        }
        return userInformationEntities.get(0);
    }

    @Override
    public List<UserPropertyStaffEntity> searchStaffByName(String staffName) {
        if(StringUtil.isEmpty(staffName)){
            return null;
        }
        String hql = "from UserPropertyStaffEntity where staffState=:staffState and staffName like '%"+staffName+"%'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffState",UserPropertyStaffEntity.State_On);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public UserPropertyStaffEntity getByNameID(String userName, String id) {
        String hql = "from UserPropertyStaffEntity where userName=:userName and staffId<>:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userName",userName);
        query.setParameter("id",id);
        UserPropertyStaffEntity userPropertyStaffEntity = (UserPropertyStaffEntity) query.uniqueResult();
        return userPropertyStaffEntity;
    }

    @Override
    public UserInformationEntity getAuthByNameID(String userName, String id) {
        String hql = "from UserInformationEntity where sysName=:userName and staffId<>:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userName",userName);
        query.setParameter("id",id);
        UserInformationEntity userInformationEntity = (UserInformationEntity) query.uniqueResult();
        return userInformationEntity;
    }


    /* -------------会员账户管理模块------------- */

    /**
     * 获取员工用户信息列表
     * @param params 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getStaffUserList(Map<String,Object> params,WebPage webPage){
        StringBuffer hql = new StringBuffer(" select u.staffId as staffId,u.userName as userName,u.staffName as staffName,u.password as password,u.mobile as mobile, ");
        hql.append(" u.email as email,u.scope as scope,u.project as project,u.company as company ");
        hql.append(" from UserPropertyStaffEntity u ");
        List<Object> parameterList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //角色
        Object roledesc = params.get("roledesc");
        if (null != roledesc && !"".equals(roledesc.toString())){
            hql.append(" ,RoleRoleanthorityEntity r,RoleRolesetEntity s where u.staffId = r.staffId and r.setId = s.setId and u.staffState = '1' and s.roledesc like ? ");
            parameterList.add("%" + roledesc.toString() + "%");
        }else{
            hql.append(" where 1=1 and u.staffState = '1' ");
        }
        //角色Id
//        Object roleSetId = params.get("roleSetId");
//        if (null != roleSetId && !"".equals(roleSetId.toString())){
//            if (null != roledesc && !"".equals(roledesc.toString())){
//                //如果角色(检索条件)不为空,只需追加条件
//                hql.append(" and s.setId != ? ");
//                parameterList.add(roleSetId.toString());
//            }else{
//                //如果角色(检索条件)为空,追加角色查询表
//                hql.append(" ,RoleRoleanthorityEntity r,RoleRolesetEntity s where u.staffId = r.staffId and r.setId = s.setId and u.staffState = '1' and s.setId != ? ");
//                parameterList.add(roleSetId.toString());
//            }
//        }
//        //判断是否追加条件
//        if ((null == roledesc || "".equals(roledesc.toString())) && (null == roleSetId || "".equals(roleSetId.toString()))){
//            hql.append(" where 1=1 and u.staffState = '1' ");
//        }
        //账号名
        Object userName = params.get("userName");
        if (null != userName && !"".equals(userName.toString())){
            hql.append(" and u.userName like ? ");
            parameterList.add("%"+userName.toString()+"%");
        }
        //手机号
        Object mobile = params.get("mobile");
        if (null != mobile && !"".equals(mobile.toString())){
            hql.append(" and u.mobile = ? ");
            parameterList.add(mobile.toString());
        }
        //公司名
        Object company = params.get("company");
        if (null != company && !"".equals(company.toString())){
            hql.append(" and u.company like ? ");
            parameterList.add("%"+company.toString()+"%");
        }
        //检索开始时间
        Object beginTime = params.get("beginTime");
        if (null != beginTime && !"".equals(beginTime.toString())){
            hql.append(" and u.createOn >= ? ");
            try {
                parameterList.add(dateFormat.parse(beginTime.toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //检索结束时间
        Object endTime = params.get("endTime");
        if (null != endTime && !"".equals(endTime.toString())){
            hql.append(" and u.createOn <= ? ");
            try {
                parameterList.add(dateFormat.parse(endTime.toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //区域
        Object scope = params.get("scope");
        if (null != scope && !"".equals(scope.toString())){
            hql.append(" and u.scope like ? ");
            parameterList.add("%" + scope.toString() + "%");
        }
        //项目
        Object projectName = params.get("projectName");
        if (null != projectName && !"".equals(projectName.toString())){
            hql.append(" and u.project like ? ");
            parameterList.add("%"+projectName.toString()+"%");
        }

        //姓名
        Object staffName = params.get("staffName");
        if (null != staffName && !"".equals(staffName.toString())){
            hql.append(" and u.staffName like ? ");
            parameterList.add("%"+staffName.toString()+"%");
        }


        hql.append(" order by u.createOn desc ");

        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (parameterList.size() > 0) {
            for (int i = 0; i < parameterList.size(); i++) {
                query.setParameter(i, parameterList.get(i));
            }
        }
        Object batchRoleState = params.get("batchRoleState");
        if (null == batchRoleState || "".equals(batchRoleState.toString())){
            query.setFirstResult((webPage.getPageIndex()-1)*20);
            query.setMaxResults(20);
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 检索员工用户总人数
     * @param params 参数
     * @return  Long
     */
    public Long getStaffUserCount(Map<String,Object> params){
        StringBuffer hql = new StringBuffer(" SELECT COUNT(u.staffId) FROM UserPropertyStaffEntity u ");
        List<Object> parameterList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //角色
        Object roledesc = params.get("roledesc");
        if (null != roledesc && !"".equals(roledesc.toString())){
            hql.append(" ,RoleRoleanthorityEntity r,RoleRolesetEntity s where u.staffId = r.staffId and r.setId = s.setId and u.staffState = '1' and s.roledesc like ? ");
            parameterList.add("%" + roledesc.toString() + "%");
        }else{
            hql.append(" where 1=1 and u.staffState = '1' ");
        }
        //角色Id
//        Object roleSetId = params.get("roleSetId");
//        if (null != roleSetId && !"".equals(roleSetId.toString())){
//            if (null != roledesc && !"".equals(roledesc.toString())){
//                //如果角色(检索条件)不为空,只需追加条件
//                hql.append(" and s.setId != ? ");
//                parameterList.add(roleSetId.toString());
//            }else{
//                //如果角色(检索条件)为空,追加角色查询表
//                hql.append(" ,RoleRoleanthorityEntity r,RoleRolesetEntity s where u.staffId = r.staffId and r.setId = s.setId and u.staffState = '1' and s.setId != ? ");
//                parameterList.add(roleSetId.toString());
//            }
//        }
//        //判断是否追加条件
//        if ((null == roledesc || "".equals(roledesc.toString())) && (null == roleSetId || "".equals(roleSetId.toString()))){
//            hql.append(" where 1=1 and u.staffState = '1' ");
//        }
        //账号名
        Object userName = params.get("userName");
        if (null != userName && !"".equals(userName.toString())){
            hql.append(" and u.userName like ? ");
            parameterList.add("%"+userName.toString()+"%");
        }
        //手机号
        Object mobile = params.get("mobile");
        if (null != mobile && !"".equals(mobile.toString())){
            hql.append(" and u.mobile = ? ");
            parameterList.add(mobile.toString());
        }
        //公司名
        Object company = params.get("company");
        if (null != company && !"".equals(company.toString())){
            hql.append(" and u.company like ? ");
            parameterList.add("%" + company.toString() + "%");
        }
        //检索开始时间
        Object beginTime = params.get("beginTime");
        if (null != beginTime && !"".equals(beginTime.toString())){
            hql.append(" and u.createOn >= ? ");
            try {
                parameterList.add(dateFormat.parse(beginTime.toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //检索结束时间
        Object endTime = params.get("endTime");
        if (null != endTime && !"".equals(endTime.toString())){
            hql.append(" and u.createOn <= ? ");
            try {
                parameterList.add(dateFormat.parse(endTime.toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //区域
        Object scope = params.get("scope");
        if (null != scope && !"".equals(scope.toString())){
            hql.append(" and u.scope like ? ");
            parameterList.add("%"+scope.toString()+"%");
        }
        //项目
        Object projectName = params.get("projectName");
        if (null != projectName && !"".equals(projectName.toString())){
            hql.append(" and u.project like ? ");
            parameterList.add("%"+projectName.toString()+"%");
        }

        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        if (parameterList.size() > 0) {
            for (int i = 0; i < parameterList.size(); i++) {
                query.setParameter(i, parameterList.get(i));
            }
        }
        Long count = (Long) query.uniqueResult();
        session.flush();
        session.close();
        return count;
    }

    /**
     * 通过员工用户Id获取角色信息
     * @param staffId  员工用户Id
     * @return
     */
    public List<Map<String,Object>> getRoleByStaffId(String staffId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer(" select s.setid setId,s.roledesc roleDesc ");
        sql.append(" from user_propertyStaff u,role_roleanthority r,role_roleset s ");
        sql.append(" where u.STAFF_ID = r.StaffId and r.setid = s.setid and u.STAFF_ID = ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        sqlQuery.setParameter(0, staffId);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过员工用户Id检索员工详细信息
     * @param staffId 员工用户Id
     * @return UserPropertyStaffEntity
     */
    public UserPropertyStaffEntity getStaffUserByStaffId(String staffId){
        String hql = " from UserPropertyStaffEntity where staffId = ? ";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter(0,staffId);
        UserPropertyStaffEntity userPropertyStaffEntity = (UserPropertyStaffEntity) query.uniqueResult();
        session.flush();
        session.close();
        return userPropertyStaffEntity;
    }

    @Override
    public UserInformationEntity getStaffByStaffId(String staffId) {
        String hql = " from UserInformationEntity where staffId = ? ";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter(0,staffId);
        UserInformationEntity userInformationEntity = (UserInformationEntity) query.uniqueResult();
        session.flush();
        session.close();
        return userInformationEntity;
    }

    /**
     * 保存或更新Entity
     */
    public <T> void saveOrUpdate(T entity){
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * 通过员工用户Id删除员工角色关系
     * @param staffId 员工用户Id
     */
    public void deleteRoleanthorityByStaffId(String staffId){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(" delete from role_roleanthority where StaffId = ? ");
        sqlQuery.setParameter(0,staffId);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 通过员工用户Id获取范围权限
     * @param staffId
     * @return
     */
    public List<Map<String,Object>> getRoleScopeByStaffId(String staffId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer(" select ra.setid setId,rs.scope_id scopeId,rs.scope_name scopeName,rs.scope_type scopeType ");
        sql.append(" from user_propertyStaff u right join role_roleanthority ra on u.STAFF_ID = ra.StaffId ");
        sql.append(" right join member_role_scope rs on ra.setid = rs.role_set_id where u.STAFF_ID = ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0,staffId);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取城市列表(ALL)
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> listCity() {
        Session session = getCurrentSession();
        List list = session.createSQLQuery("select DISTINCT c.id cityId,c.city_name cityName  from house_project p,house_city c where p.city_id = c.id").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过项目Id检索城市信息
     * @param projectId 项目Id
     * @return  Map<String,Object>
     */
    public Map<String,Object> getCityByProjectId(String projectId){
        String sql = " select c.ID cityId,c.CITY_NAME cityName from house_project p,house_city c where p.CITY_ID = c.ID and p.PINYIN_CODE = ? ";
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0, projectId);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String,Object> city = (Map<String, Object>) sqlQuery.uniqueResult();
        session.flush();
        session.close();
        return city;
    }

    /**
     * 通过城市Id检索项目列表
     * @param cityId
     * @return
     */
    public List<Object[]> listProjectByCity(String cityId){
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select DISTINCT p.PINYIN_CODE,p.name from house_project p,house_city c where p.city_id = c.id and p.CITY_ID = ?");
        sqlQuery.setParameter(0, cityId);
        List<Object[]> list = (List<Object[]>) sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 检索所有城市所有项目
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> listAllProject(){
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(" select DISTINCT p.PINYIN_CODE projectId,p.name projectName from house_project p ");
        List list = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过城市Id检索城市信息
     * @param cityId    城市Id
     * @return  HouseCityEntity
     */
    public Map<String,Object> getHouseCityByCityId(String cityId){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(" select c.ID id,c.CITY_CODE cityCode,c.CITY_NAME cityName from house_city c where c.ID = ? ");
        sqlQuery.setParameter(0,cityId);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> list = sqlQuery.list();
        session.flush();
        session.close();
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过项目Code检索项目信息
     * @param projectCode   项目Code
     * @return  HouseProjectEntity
     */
    public Map<String,Object> getHouseProjectByCode(String projectCode){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(" select * from house_project p where p.PINYIN_CODE = ? ");
        sqlQuery.setParameter(0,projectCode);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> list = sqlQuery.list();
        session.flush();
        session.close();
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取批量员工用户信息列表
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getBatchStaffUserList(Map<String,Object> params){
        StringBuffer hql = new StringBuffer(" select u.staffId as staffId,u.userName as userName,u.staffName as staffName,u.password as password,u.mobile as mobile, ");
        hql.append(" u.email as email,u.scope as scope,u.project as project,u.company as company ");
        hql.append(" from UserPropertyStaffEntity u ");
        //批量员工Id集合
        Object staffUserIds = params.get("staffUserIds");
        if (null != staffUserIds && !"".equals(staffUserIds.toString())){
            hql.append(" where u.staffId in "+staffUserIds);
        }
        hql.append(" order by u.createOn desc ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public UserInformationEntity CheckUserStaffByIdAPwd(UserInformationEntity userInformationEntity) {
        String hql = "FROM UserInformationEntity WHERE sysName = :userName and password = :password and staffState='1' ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userName", userInformationEntity.getSysName());
        query.setParameter("password", userInformationEntity.getPassword());
        List<UserInformationEntity> propertystaffList = query.list();
        if(propertystaffList == null){
            return null;
        }
        if(propertystaffList.size() == 0){
            return null;
        }
        return propertystaffList.get(0);
    }

    @Override
    public boolean getuserMapById(String staffId,String checkLogin) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ID ");
        sql.append(" from user_map  ");
        sql.append(" where  1=1 AND STATE= '1' AND STAFF_ID=:id AND PROJECT_MODULE=:checklogin ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", staffId);
        query.setParameter("checklogin", checkLogin);
        List<Object[]> list = query.list();
        if(list!=null && list.size()>0){
            return true;
        }else{
            return false;
        }
    }

}


