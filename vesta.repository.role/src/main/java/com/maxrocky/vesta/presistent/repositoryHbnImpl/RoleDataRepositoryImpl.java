package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.RoleDataRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/6/18.
 */
@Repository
public class RoleDataRepositoryImpl implements RoleDataRepository {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addRoleData(RoleDataEntity roleDataEntity) {
        Session session = getCurrentSession();
        session.save(roleDataEntity);
        session.flush();
    }

    @Override
    public void addDumpRoleData(RoleDataEntity roleDataEntity) {
        String sql1 = "INSERT INTO role_data(ID,DATA_ID,AUTHORITY_TYPE,AUTHORITY_ID,PERMISSION,PROJECT_NUM,DATA_TYPE,MODIFY_TIME,STATUS) VALUES(?,?,?,?,?,?,?,?,1) ON DUPLICATE KEY UPDATE STATUS='1',MODIFY_TIME=?";
        Query query1 = getCurrentSession().createSQLQuery(sql1);
        query1.setString(0,roleDataEntity.getId());
        query1.setString(1,roleDataEntity.getDataId());
        query1.setString(2,roleDataEntity.getAuthorityType());
        query1.setString(3,roleDataEntity.getAuthorityId());
        query1.setString(4,roleDataEntity.getPermission());
        query1.setString(5,roleDataEntity.getProjectNum());
        query1.setString(6, roleDataEntity.getDataType());
        query1.setString(7, DateUtils.format(new Date()));
        query1.setString(8, DateUtils.format(new Date()));
        query1.executeUpdate();
    }

    @Override
    public void updateRoleData(RoleDataEntity roleDataEntity) {
        String hql = "delete from RoleDataEntity where authorityType =:authorityType and dataType =:dataType and authorityId=:authorityId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("authorityId",roleDataEntity.getAuthorityId());
        query.setParameter("dataType",roleDataEntity.getDataType());
        query.setParameter("authorityType",roleDataEntity.getAuthorityType());
        query.executeUpdate();
    }

    @Override
    public void delOrganizeRoleData(RoleDataEntity roleDataEntity) {
        String hql = "update RoleDataEntity set status='0',modifyTime=:modifyTime where authorityType =:authorityType and authorityId=:authorityId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("authorityId",roleDataEntity.getAuthorityId());
        query.setParameter("modifyTime",roleDataEntity.getModifyTime());
        query.setParameter("authorityType",roleDataEntity.getAuthorityType());
        query.executeUpdate();
    }

    @Override
    public void delOrganizeRoleSet(RoleDataEntity roleDataEntity) {
        String hql = "update RoleDataEntity set status='0' where authorityType =:authorityType and dataType =:dataType and dataId=:dataId and authorityId=:authorityId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dataId",roleDataEntity.getDataId());
        query.setParameter("authorityId",roleDataEntity.getAuthorityId());
        query.setParameter("dataType",roleDataEntity.getDataType());
        query.setParameter("authorityType",roleDataEntity.getAuthorityType());
        query.executeUpdate();
    }

    @Override
    public void delAgencyRole(RoleDataEntity roleDataEntity) {
        String hql = "delete from RoleDataEntity where authorityType =:authorityType and dataType =:dataType and dataId=:dataId and permission is null";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dataId",roleDataEntity.getDataId());
        query.setParameter("dataType",roleDataEntity.getDataType());
        query.setParameter("authorityType",roleDataEntity.getAuthorityType());
        query.executeUpdate();
    }

    @Override
    public void delAdminAgencyRole(String setId) {
        String hql = "delete from RoleDataEntity where authorityType = '1' and dataType ='2' and permission = 'admin' and dataId=:setId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("setId",setId);
        query.executeUpdate();
    }

    @Override
    public void delAdminAgencyRole(String setId, String authId,String projectId) {
        String hql = "delete from RoleDataEntity where authorityType = '1' and dataType ='2' and permission = :permission and dataId=:setId and authorityId=:authId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("permission",projectId);
        query.setParameter("setId",setId);
        query.setParameter("authId",authId);
        query.executeUpdate();
    }

    @Override
    public void delAdminAgencyRole(String setId, String projectId) {
        String hql = "delete from RoleDataEntity where authorityType = '1' and dataType ='2' and permission = :permission and dataId=:setId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("permission",projectId);
        query.setParameter("setId",setId);
        query.executeUpdate();
    }

    @Override
    public void delProjectRole(RoleDataEntity roleDataEntity) {
        String hql = "update RoleDataEntity set modifyTime=:modifyTime,status='0' where authorityType =:authorityType and authorityId =:authorityId and dataType =:dataType and dataId=:dataId and permission=:permission";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dataId",roleDataEntity.getDataId());
        query.setParameter("dataType",roleDataEntity.getDataType());
        query.setParameter("authorityType",roleDataEntity.getAuthorityType());
        query.setParameter("authorityId",roleDataEntity.getAuthorityId());
        query.setParameter("permission",roleDataEntity.getPermission());
        query.setParameter("modifyTime",new Date());
        query.executeUpdate();
    }

    @Override
    public List<AppRolesetEntity> getDataByAuthority(String authorityId) {
        String hql = "from AppRolesetEntity where appSetId in (select distinct dataId from RoleDataEntity where authorityType = '1' and dataType = '2' and status='1' and authorityId=:authorityId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("authorityId",authorityId);
        List<AppRolesetEntity> appRolesetEntities = query.list();
        return appRolesetEntities;
    }

    @Override
    public List<RoleDataEntity> getDataByOrganzie(String organizeId) {
        String hql = "from RoleDataEntity where authorityType = '2' and dataType = '2' and status='1' and authorityId=:organized";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("organized",organizeId);
        List<RoleDataEntity> roleDataEntities = query.list();
        return roleDataEntities;
    }

    @Override
    public List<RoleDataEntity> getDataByRoleSet(String appRoleSetId) {
        String hql = "from RoleDataEntity where authorityType = '2' and dataType = '2' and status='1' and dataId=:roleSetId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("roleSetId",appRoleSetId);
        List<RoleDataEntity> roleDataEntities = query.list();
        return roleDataEntities;
    }

    @Override
    public List<AgencyEntity> getAgencyByRoleSet(String appRoleSetId) {
        String hql = "from AgencyEntity where status='1' and agencyId in (select distinct authorityId from RoleDataEntity where authorityType = '1' and dataType = '2' and status='1' and dataId=:roleSetId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("roleSetId",appRoleSetId);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<UserPropertyStaffEntity> getStaffByRoleSet(String appRoleSetId) {
        String hql = "from UserPropertyStaffEntity where staffId in (select distinct authorityId from RoleDataEntity where authorityType = '3' and dataType = '2' and status='1' and dataId=:roleSetId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("roleSetId", appRoleSetId);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public List<HouseProjectEntity> getDataByStaffId(String staffId) {
        String hql = "from HouseProjectEntity where id in (select distinct dataId from RoleDataEntity where authorityType = '3' and dataType = '1' and status = '1'and authorityId = :staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        List<HouseProjectEntity> houseProjectEntities = query.list();
        return houseProjectEntities;
    }

    @Override
    public List<AgencyEntity> getRoleDataByProject(String projectId) {
        String hql = "from AgencyEntity where agencyId in (select distinct authorityId from RoleDataEntity where authorityType = '1' and dataType = '1' and status = '1' and dataId = :projectId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AgencyEntity> getProjectRoles(String projectId, String permission) {
        String hql = "from AgencyEntity where agencyId in (select distinct authorityId from RoleDataEntity where authorityType = '1' and dataType = '1' and status = '1' and dataId = :projectId and permission=:permission)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("permission",permission);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<UserPropertyStaffEntity> getStaffByProject(String projectId) {
        String hql = "from UserPropertyStaffEntity where staffId in (select distinct authorityId from RoleDataEntity where authorityType = '3' and dataType = '1' and status = '1' and dataId = :projectId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public List<UserPropertyStaffEntity> getProjectStaffRole(String projectId, String permission) {
        String hql = "from UserPropertyStaffEntity where staffId in (select distinct authorityId from RoleDataEntity where authorityType = '3' and dataType = '1' and status = '1' and dataId = :projectId and permission=:permission)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("permission",permission);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public List<OrganizeEntity> getProjectOrganizeRole(String projectId, String permission) {
        String hql = "from OrganizeEntity where organizeId in (select distinct authorityId from RoleDataEntity where authorityType = '2' and dataType = '1' and status = '1' and dataId = :projectId and permission=:permission)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("permission", permission);
        List<OrganizeEntity> organizeEntities = query.list();
        return organizeEntities;
    }

    @Override
    public List<RoleDataEntity> getRoleDateOne(RoleDataEntity roleDataEntity) {
        String hql = "from RoleDataEntity where status = '1' and dataType = '"+roleDataEntity.getDataType()+"' and dataId = '"+roleDataEntity.getDataId()+
                "' and authorityType = '"+roleDataEntity.getAuthorityType()+"' and authorityId = '"+roleDataEntity.getAuthorityId()+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<RoleDataEntity> roleDataEntities = query.list();
        return roleDataEntities;
    }

    @Override
    public List<RoleDataEntity> getProjectRoleData(String projectId, String authorityType, String permission) {
        String hql = "from RoleDataEntity where dataType='1' and dataId=:projectId and authorityType=:authorityType and permission=:permission and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("authorityType",authorityType);
        query.setParameter("permission",permission);
        List<RoleDataEntity> roleDataEntities = query.list();
        return roleDataEntities;
    }

    @Override
    public List<AppRolesetEntity> getRoleSetFromData(String staffId) {
        String hql = "from AppRolesetEntity where appSetState='1' and appSetId in (select distinct dataId from RoleDataEntity where status = '1' and dataType = '2' and authorityType = '3' and authorityId = :staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        List<AppRolesetEntity> appRolesetEntityList = query.list();
        return appRolesetEntityList;
    }

    @Override
    public List<HouseProjectEntity> getProjectByOrganizeId(String organizeId) {
        String hql = "from HouseProjectEntity where id in (select distinct dataId from RoleDataEntity where status = '1' and dataType = '1' and authorityType = '2' and authorityId=:organizeId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("organizeId",organizeId);
        List<HouseProjectEntity> houseProjectEntityList = query.list();
        return houseProjectEntityList;
    }

    @Override
    public List<HouseProjectEntity> getProjectByAgencyId(String agencyId) {
        String hql = "from HouseProjectEntity where id in (select distinct dataId from RoleDataEntity where status = '1' and dataType = '1' and authorityType = '1' and authorityId=:agencyId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyId",agencyId);
        List<HouseProjectEntity> houseProjectEntityList = query.list();
        return houseProjectEntityList;
    }

    @Override
    public List<String> getProjectRole(String staffId) {
        //根据用户ID从组织架构找对应的项目
        String hql = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = :staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        List<String> paths = query.list();
        List<String> list3 = new ArrayList<String>();
        String str = "";
        if(paths!=null&&paths.size()>0){
            for(String ids:paths){
                str += ids.replace("/","','");
            }
        }
        if(!StringUtil.isEmpty(str)){
            str = str.substring(2)+"'";
            String sql = "select agencyId from AgencyEntity where agencyId in ("+str+")";
            Query query1 = getCurrentSession().createQuery(sql);
            String authorIds = query1.getQueryString();
            String sql1 = "select concat(projectNum,permission) from RoleDataEntity where dataType = '1' and status = '1' and " +
                    "authorityType = '1' and authorityId in("+authorIds+")";
            Query query2 = getCurrentSession().createQuery(sql1);
            List list2 = query2.list();
            list3.addAll(list2);
        }
        //根据用户ID从群组找对应项目
        String hql4 = "select concat(projectNum,permission) from RoleDataEntity where status = '1' and dataType = '1' and authorityType = '2' and authorityId in (select organizeId from OrganizeUsermapEntity where staffId='"+staffId+"')";
        Query query4 = getCurrentSession().createQuery(hql4);
        List<String> list4 = query4.list();
        if(list4!=null){
            list3.addAll(list4);
        }
        //根据用户ID直接找对应的项目
        String hql3 = "select concat(projectNum,permission) from RoleDataEntity where dataType='1' and authorityType='3' and status='1' and authorityId=:staffId";
        Query query3 = getCurrentSession().createQuery(hql3);
        query3.setParameter("staffId", staffId);
        List<String> list = query3.list();
        if(list!=null){
            list3.addAll(list);
        }
        return list3;
    }
    /**
     * Code:D
     * Type:
     * Describe:
     * CreateBy:
     * CreateOn:2016/9/6
     */
    @Override
    public boolean havaDispatch(String staffId, String projectCode,String permission) {
        //根据用户ID判断所属组织架构是否有项目权限
        String hql = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = :staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        List<String> paths = query.list();
        String str = "";
        if(paths!=null&&paths.size()>0){
            for(String ids:paths){
                str += ids.replace("/","','");
            }
        }
        if(!StringUtil.isEmpty(str)){
            str = str.substring(2)+"'";
            String sql = "select agencyId from AgencyEntity where agencyId in ("+str+")";
            Query query1 = getCurrentSession().createQuery(sql);
            String authorIds = query1.getQueryString();
            String sql1 = "from RoleDataEntity where dataType = '1' and status = '1' and " +
                    "authorityType = '1' and permission=:permission and projectNum=:projectCode and authorityId in("+authorIds+")";
            Query query2 = getCurrentSession().createQuery(sql1);
            query2.setParameter("permission",permission);
            query2.setParameter("projectCode",projectCode);
            List<RoleDataEntity> roleDataEntities1 = query2.list();
            if(roleDataEntities1!=null&&roleDataEntities1.size()>0){
                return true;
            }
        }

        //根据用户ID判断对应群组是否有对应权限
        String hql2 = "from OrganizeUsermapEntity where orgStatus='1' and staffId=:staffId and organizeId in(select authorityId from RoleDataEntity where status='1' and dataType='1' and projectNum=:projectCode and authorityType='2' and permission=:permission)";
        Query query3 = getCurrentSession().createQuery(hql2);
        query3.setParameter("staffId",staffId);
        query3.setParameter("projectCode",projectCode);
        query3.setParameter("permission",permission);
        List<OrganizeUsermapEntity> organizeUsermapEntities = query3.list();
        if(organizeUsermapEntities!=null&&organizeUsermapEntities.size()>0){
            return true;
        }

        //根据用户ID直接判断是否有对应项目权限
        String hqln = "from RoleDataEntity where status='1' and dataType='1' and projectNum=:projectCode and authorityType='3' and authorityId=:staffId and permission=:permission";
        Query queryn = getCurrentSession().createQuery(hqln);
        queryn.setParameter("staffId",staffId);
        queryn.setParameter("projectCode",projectCode);
        queryn.setParameter("permission",permission);
        List<RoleDataEntity> roleDataEntities = queryn.list();
        if(roleDataEntities!=null&&roleDataEntities.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Object[]> getViewsList(String timeStamp, Integer num) {
        String sql = "SELECT * FROM people_authority WHERE TIMESTAMP>= '"+timeStamp+"'";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setFirstResult(num * 500);
        query.setMaxResults(500);
        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public List<String> getProjectRoleByNum(String staffId, String projectNum) {
        List<String> permis = new ArrayList<String>();
        //根据用户Id和项目编码直接查询人物权限
        String hql1 = "select permission from RoleDataEntity where dataType='1' and authorityType='3' and status='1' and authorityId=:staffId and projectNum=:projectNum and permission in ('100000001','100000000') ";
        Query query1 = getCurrentSession().createQuery(hql1);
        query1.setParameter("staffId", staffId);
        query1.setParameter("projectNum", projectNum);
        List<String> list = query1.list();
        if(list!=null){
            for(String perm:list){
                if(permis.size()>0){
                    if("100000001".equals(perm) && !"100000001".equals(permis.get(0))){
                        permis.add(perm);
                    }else if("100000000".equals(perm) && !"100000000".equals(permis.get(0))){
                        permis.add(perm);
                    }
                }else{//permis没有值 直接判断存入
                    if("100000001".equals(perm)){
                        permis.add(perm);
                    }else if("100000000".equals(perm)){
                        permis.add(perm);
                    }
                }
                if(permis.size()==2){
                    return permis;
                }
            }
        }
        //根据用户ID和项目从群组查询权限
        String hql2 = "select permission from RoleDataEntity where status = '1' and dataType = '1' and authorityType = '2' and projectNum=:projectNum and authorityId in (select organizeId from OrganizeUsermapEntity where staffId=:staffId ) and permission in ('100000001','100000000') ";
        Query query2 = getCurrentSession().createQuery(hql2);
        query2.setParameter("staffId", staffId);
        query2.setParameter("projectNum", projectNum);
        List<String> list2 = query2.list();
        if(list2!=null){
            for(String perm:list2){
//                if("100000001".equals(perm) && !"100000001".equals(permis.get(0)) && !"100000001".equals(permis.get(1))){
//                    permis.add(perm);
//                }else if("100000000".equals(perm) && !"100000000".equals(permis.get(0)) && !"100000000".equals(permis.get(1))){
//                    permis.add(perm);
//                }
//                if(permis.size()==2){
//                    return permis;
//                }
                if(permis.size()>0){
                    if("100000001".equals(perm) && !"100000001".equals(permis.get(0))){
                        permis.add(perm);
                    }else if("100000000".equals(perm) && !"100000000".equals(permis.get(0))){
                        permis.add(perm);
                    }
                }else{//permis没有值 直接判断存入
                    if("100000001".equals(perm)){
                        permis.add(perm);
                    }else if("100000000".equals(perm)){
                        permis.add(perm);
                    }
                }
                if(permis.size()==2){
                    return permis;
                }
            }
        }
        //根据项目、用户ID从组织架构查询权限
        String hql3 = "select permission from RoleDataEntity where status = '1' and dataType = '1' and authorityType = '1' and projectNum=:projectNum and authorityId in (select agencyId from UserAgencymapEntity where staffId=:staffId ) and permission in ('100000001','100000000')";
        Query query3 = getCurrentSession().createQuery(hql3);
        query3.setParameter("staffId", staffId);
        query3.setParameter("projectNum", projectNum);
        List<String> list3 = query3.list();
        if(list3!=null){
            for(String perm:list3){
//                if("100000001".equals(perm) && !"100000001".equals(permis.get(0)) && !"100000001".equals(permis.get(1))){
//                    permis.add(perm);
//                }else if("100000000".equals(perm) && !"100000000".equals(permis.get(0)) && !"100000000".equals(permis.get(1))){
//                    permis.add(perm);
//                }
//                if(permis.size()==2){
//                    return permis;
//                }
                if(permis.size()>0){
                    if("100000001".equals(perm) && !"100000001".equals(permis.get(0))){
                        permis.add(perm);
                    }else if("100000000".equals(perm) && !"100000000".equals(permis.get(0))){
                        permis.add(perm);
                    }
                }else{//permis没有值 直接判断存入
                    if("100000001".equals(perm)){
                        permis.add(perm);
                    }else if("100000000".equals(perm)){
                        permis.add(perm);
                    }
                }
                if(permis.size()==2){
                    return permis;
                }
            }
        }
        return permis;
    }

    @Override
    public String getPrintSeq(String num) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append("_printnextval('"+num+"')");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        List<Integer> list = query.list();
        if(list != null && !list.isEmpty()){
            return list.get(0)+"";
        }
        return null;
    }

    @Override
    public List<RoleDataEntity> searchProjectRoleData(String projectId, String authorityType, String dataId) {
        String hql = "from RoleDataEntity where dataType='2' and dataId=:dataId and authorityType=:authorityType and permission=:projectId and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dataId",dataId);
        query.setParameter("authorityType",authorityType);
        query.setParameter("projectId",projectId);
        List<RoleDataEntity> roleDataEntities = query.list();
        return roleDataEntities;
    }

    @Override
    public boolean deleteAgencyRole(RoleDataEntity roleDataEntity) {
        Session session = getCurrentSession();
        session.delete(roleDataEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean deleteAgencyRole(String projectId, String authorityType, String dataId) {
        String hql = "delete from RoleDataEntity where dataType='2' and dataId=:dataId and authorityType=:authorityType and permission=:projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dataId",dataId);
        query.setParameter("authorityType",authorityType);
        query.setParameter("projectId",projectId);
        query.executeUpdate();
       return true;
    }

    @Override
    public List<Object[]> getComplintByProjectId(String projectId) {
        String sql = " select qc.staff_id,up.STAFF_NAME,up.USERNAME from qc_classify_staff_relation qc  ";
        sql +=" LEFT JOIN house_project hp on qc.project_num=hp.PINYIN_CODE ";
        sql +=" LEFT JOIN user_propertyStaff up on qc.staff_id=up.STAFF_ID ";
        sql +=" where hp.ID='"+projectId+"' ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> objects = query.list();
        return objects;
    }
}
