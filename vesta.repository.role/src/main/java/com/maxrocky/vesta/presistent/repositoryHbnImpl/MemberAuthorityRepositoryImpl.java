package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.MemberAuthorityRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
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
import java.util.List;
import java.util.Map;

/**
 * 会员权限管理_Dao实现类
 * Created by WeiYangDong on 2016/8/4.
 */
@Repository
public class MemberAuthorityRepositoryImpl implements MemberAuthorityRepository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存或更新Entity
     * @param entity (RoleDataAuthorityEntity_数据权限,RoleDataAuthoritysetmapEntity_角色数据权限)
     */
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * 通过角色Id物理删除角色信息
     * @param setId 角色Id
     */
    public void deleteRoleById(String setId){
        Session session = getCurrentSession();
        String hql = " delete RoleRolesetEntity where setId = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,setId);
        query.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 通过角色Id删除角色操作数据范围信息
     * @param setId 角色Id
     */
    public void deleteRoleScopeBySetId(String setId){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(" delete from member_role_scope where role_set_id = ? ");
        sqlQuery.setParameter(0,setId);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 通过角色Id删除角色菜单操作级别数据
     * @param setId 角色Id
     */
    public void deleteRoleMenuBySetId(String setId){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(" delete from member_role_menu where role_set_id = ? ");
        sqlQuery.setParameter(0,setId);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 通过角色Id更新角色操作范围数据状态值
     * @param setId 角色Id
     */
    public void updateRoleScopeStateBySetId(String setId){
        Session session = getCurrentSession();
        String sql = " update member_role_scope rs set rs.now_state = '1' where rs.role_set_id = ? ";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0,setId);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 获取会员角色列表信息
     * @param params   检索条件
     * @param webPage  分页
     * @return List<RoleRolesetEntity>
     */
    public List<Map<String,Object>> getMemberRolesetList(Map<String,Object> params,WebPage webPage) {
        Session session = getCurrentSession();
        StringBuffer hql = new StringBuffer(" select r.setId as setId,r.roledesc as roledesc,r.remarks as remarks,r.setType as setType,r.makeDate as makeDate,m.scopeName as scopeName ");
        hql.append(" from RoleRolesetEntity r,MemberRoleScopeEntity m where r.setId = m.roleSetId and r.setType = '1' and r.setState = '01' ");
        List<Object> parameterList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //角色描述(名称)
        Object roledesc = params.get("roledesc");
        if (null != roledesc && !"".equals(roledesc.toString())){
            hql.append(" and r.roledesc like ? ");
            parameterList.add("%"+roledesc.toString().trim()+"%");
        }
        //角色备注
        Object remarks = params.get("remarks");
        if (null != remarks && !"".equals(remarks.toString())){
            hql.append(" and r.remarks like ? ");
            parameterList.add("%"+remarks.toString().trim()+"%");
        }
        //创建时间查询_开始日期
        Object staDate = params.get("staDate");
        if (null != staDate && !"".equals(staDate.toString())){
            hql.append(" and r.makeDate >= ? ");
            try {
                parameterList.add(dateFormat.parse(staDate.toString().trim()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //创建时间查询_结束日期
        Object endDate = params.get("endDate");
        if (null != endDate && !"".equals(endDate.toString())){
            hql.append(" and r.makeDate <= ? ");
            try {
                parameterList.add(dateFormat.parse(endDate.toString().trim()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //数据查看范围
        Object projectScope = params.get("projectScope");
        if (null != projectScope && !"".equals(projectScope.toString())){
            hql.append(" and m.scopeName like ? ");
            parameterList.add("%"+projectScope.toString().trim()+"%");
        }
        hql.append(" order by r.makeDate desc,r.makeTime desc");

        Query query = session.createQuery(hql.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (parameterList.size() > 0) {
            for (int i = 0; i < parameterList.size(); i++) {
                query.setParameter(i, parameterList.get(i));
            }
        }
//        query.setFirstResult((webPage.getPageIndex()-1)*10);
//        query.setMaxResults(10);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过角色Id检索角色信息
     * @param setId 角色Id
     * @return RoleRolesetEntity
     */
    public RoleRolesetEntity getRoleById(String setId){
        Session session = getCurrentSession();
        String hql = " from RoleRolesetEntity where setId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, setId);
        RoleRolesetEntity roleRolesetEntity = (RoleRolesetEntity) query.uniqueResult();
        session.flush();
        session.close();
        return roleRolesetEntity;
    }

    /**
     * 通过角色Id检索角色范围数据列表
     * @param setId 角色Id
     * @return  List<MemberRoleScopeEntity>
     */
    public List<MemberRoleScopeEntity> getRoleScopeById(String setId){
        Session session = getCurrentSession();
        String hql = " from MemberRoleScopeEntity where roleSetId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, setId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过菜单Id检索菜单信息
     * @param menuId 菜单Id
     * @return RoleViewmodelEntity
     */
    public RoleViewmodelEntity getViewmodelByMenuId(String menuId){
        Session session = getCurrentSession();
        String hql = " from RoleViewmodelEntity where menuId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, menuId);
        RoleViewmodelEntity roleViewmodelEntity = (RoleViewmodelEntity) query.uniqueResult();
        session.flush();
        session.close();
        return roleViewmodelEntity;
    }

    /**
     * 获取会员一级菜单列表
     * @return  List<RoleViewmodelEntity>
     */
    public List<RoleViewmodelEntity> getMenuLevelOneList(){
        Session session = getCurrentSession();
        String hql = " from RoleViewmodelEntity where menulevel = '1' and belong = '1' ";
        Query query = session.createQuery(hql);
        List<RoleViewmodelEntity> list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过会员一级菜单Id获取二级菜单列表
     * @param menuId 菜单Id
     * @return  List<RoleViewmodelEntity>
     */
    public List<RoleViewmodelEntity> getMenuLevelTwoListByMenuId(String menuId){
        Session session = getCurrentSession();
        String hql = " from RoleViewmodelEntity where menulevel = '2' and parantmenuid = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, menuId);
        List<RoleViewmodelEntity> list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过项目Code_查询城市ID/名称
     */
    public List<Object[]> queryCityByProjectCode(String projectCode){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select distinct hc.ID cityId,hc.CITY_NAME cityName ");
        sql.append(" from house_project hp,house_city hc ");
        sql.append(" where hp.CITY_ID = hc.ID and hp.PINYIN_CODE = ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        List list = sqlQuery.setParameter(0,projectCode).list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过角色Id检索角色操作菜单列表
     * @param setId 角色Id
     * @return  List<MemberRoleMenuEntity>
     */
    public List<MemberRoleMenuEntity> getRoleMenuBySetId(String setId){
        Session session = getCurrentSession();
        String hql = " from MemberRoleMenuEntity where roleSetId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0,setId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过角色名称模糊查询角色信息列表
     * @param roledesc  角色名称
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getRolesByRoledesc(String roledesc){
        Session session = getCurrentSession();
        String sql = " select r.setid setId,r.roledesc roledesc from role_roleset r where r.SetState = '01' and r.SetType = '1' and r.roledesc like ? order by r.MakeDate desc ";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0, "%"+roledesc+"%");
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过角色名称模糊查询角色范围信息列表
     * @param roledesc  角色名称
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getRoleScopesByRoledesc(String roledesc){
        Session session = getCurrentSession();
        String sql = " select r.setid setId,r.roledesc roledesc,s.scope_type scopeType,s.scope_id scopeId,s.scope_name scopeName "+
                     " from role_roleset r,member_role_scope s "+
                     " where r.setid = s.role_set_id and r.SetState = '01' and r.SetType = '1'";
        if (roledesc != null && !roledesc.trim().equals("")){
            sql += " and r.roledesc like ? ";
        }
        sql += " order by r.MakeDate desc ";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        if (roledesc != null && !roledesc.trim().equals("")){
            sqlQuery.setParameter(0, "%"+roledesc+"%");
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过角色Id检索成员列表
     * @param params    参数
     * @param webPage   分页
     * @return
     */
    public List<Map<String,Object>> getStaffUserListByRoleId(Map<String,Object> params,WebPage webPage){
        StringBuffer hql = new StringBuffer(" select u.staffId as staffId,u.userName as userName,u.staffName as staffName,u.password as password,u.mobile as mobile, ");
        hql.append(" u.email as email,u.scope as scope,u.project as project,u.company as company ");
        hql.append(" from UserPropertyStaffEntity u ");
        List<Object> parameterList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //角色
        Object roleSetId = params.get("roleSetId");
        if (null != roleSetId && !"".equals(roleSetId.toString())){
            hql.append(" ,RoleRoleanthorityEntity r,RoleRolesetEntity s where u.staffId = r.staffId and r.setId = s.setId and u.staffState = '1' and s.setId = ? ");
            parameterList.add(roleSetId.toString());
        }else{
            hql.append(" where 1=1 and u.staffState = '1' ");
        }
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
            parameterList.add("%"+scope.toString()+"%");
        }
        //项目
        Object projectName = params.get("projectName");
        if (null != projectName && !"".equals(projectName.toString())){
            hql.append(" and u.project like ? ");
            parameterList.add("%"+projectName.toString()+"%");
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
        query.setFirstResult((webPage.getPageIndex()-1)*10);
        query.setMaxResults(10);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过角色Id检索成员总数
     * @param params 参数
     * @return  Long
     */
    public Long getStaffUserCountByRoleId(Map<String,Object> params){
        StringBuffer hql = new StringBuffer(" SELECT COUNT(u.staffId) FROM UserPropertyStaffEntity u ");
        List<Object> parameterList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //角色
        Object roleSetId = params.get("roleSetId");
        if (null != roleSetId && !"".equals(roleSetId.toString())){
            hql.append(" ,RoleRoleanthorityEntity r,RoleRolesetEntity s where u.staffId = r.staffId and r.setId = s.setId and u.staffState = '1' and s.setId = ? ");
            parameterList.add(roleSetId.toString());
        }else{
            hql.append(" where 1=1 and u.staffState = '1' ");
        }
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
     * 通过用户Id和角色Id删除用户角色关系信息
     * @param params 参数
     */
    public int delMemberRole(Map<String,Object> params){
        String sql = " delete from role_roleanthority where setid = ? and StaffId = ? ";
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0,params.get("setId"));
        sqlQuery.setParameter(1, params.get("staffId"));
        int flag = sqlQuery.executeUpdate();
        session.flush();
        session.close();
        return flag;
    }

    /**
     * 通过用户Id与菜单Id检索角色菜单操作级别信息
     * @param params    条件参数
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getRoleMenuByStaffAndMenu(Map<String,Object> params){
        StringBuffer sql = new StringBuffer();
        sql.append(" select ra.StaffId staffId,ra.setid setId,rm.menu_id menuId,rm.operation_level operationLevel ");
        sql.append(" from role_roleanthority ra,member_role_menu rm ");
        sql.append(" where ra.setid = rm.role_set_id and ra.StaffId = ? and rm.menu_id = ? ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0,params.get("staffId").toString());
        sqlQuery.setParameter(1, params.get("menuId").toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过角色Id与菜单Id检索角色菜单操作级别信息
     * @param params    条件参数
     * @return  List<Map<String,Object>>
     */
    public MemberRoleMenuEntity getRoleMenuByRoleAndMenu(Map<String,Object> params){
        StringBuffer hql = new StringBuffer(" from MemberRoleMenuEntity where nowState = '1' and roleSetId = ? and menuId = ? ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameter(0, params.get("setId").toString());
        query.setParameter(1, params.get("menuId").toString());
        MemberRoleMenuEntity memberRoleMenuEntity  = (MemberRoleMenuEntity) query.uniqueResult();
        return memberRoleMenuEntity;
    }

    /**
     * 通过角色描述检索角色数量(用于校验角色名称重复)
     * @param roledesc 角色描述
     * @return int
     */
    public int getRoleCountByRoledesc(String roledesc){
        Session session = getCurrentSession();
        Query query = session.createQuery(" select count(*) from RoleRolesetEntity where roledesc = ? ");
        query.setParameter(0,roledesc);
        Long count = (Long) query.uniqueResult();
        session.flush();
        session.close();
        return count.intValue();
    }

}
