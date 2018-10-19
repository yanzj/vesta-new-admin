package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.*;
import com.maxrocky.vesta.domain.baseData.repository.ProjectCategoryRepository;
import com.maxrocky.vesta.domain.model.AgencyEntity;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import com.mysql.jdbc.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/10/24.
 */
@Repository
public class ProjectCategoryRepositoryImpl implements ProjectCategoryRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addProjectCategory(ProjectCategoryEntity projectCategoryEntity) {
        Session session = getCurrentSession();
        session.save(projectCategoryEntity);
        session.flush();
    }

    @Override
    public void updateProjectCategory(ProjectCategoryEntity projectCategoryEntity) {
        Session session = getCurrentSession();
        session.update(projectCategoryEntity);
        session.flush();
    }

    @Override
    public List<ProjectCategoryEntity> getCategoryListByDomain(String domain) {
        if("6".equals(domain)){
            domain="2";
        }
        String hql = "from ProjectCategoryEntity where categoryState='1' and domain=:domain";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("domain", domain);
        List<ProjectCategoryEntity> projectCategoryEntities = query.list();
        return projectCategoryEntities;
    }

    @Override
    public ProjectCategoryEntity getCategoryDetail(String categoryId) {
        String hql = "from ProjectCategoryEntity where categoryId=:categoryId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("categoryId", categoryId);
        ProjectCategoryEntity projectCategoryEntity = (ProjectCategoryEntity) query.uniqueResult();
        return projectCategoryEntity;
    }

    @Override
    public void delCategory(String categoryId) {
        String hql = "update ProjectCategoryEntity set categoryState='0',modifyOn=:modifyOn where categoryId=:categoryId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyOn", new Date());
        query.setParameter("categoryId",categoryId);
        query.executeUpdate();  //删除当前检查项
        String sql = "select categoryId from ProjectCategoryEntity where categoryState='1' and parentId='"+categoryId+"'";
        Query query1 = getCurrentSession().createQuery(sql);
        List<String> ids=query1.list();
        String hql2 = "update ProjectCategoryEntity set categoryState='0',modifyOn=:modifyOn where parentId=:categoryId";
        Query query3 = getCurrentSession().createQuery(hql2);
        query3.setParameter("categoryId",categoryId);
        query3.setParameter("modifyOn",new Date());
        query3.executeUpdate();  //删除当前子级检查项
        while(ids!=null&&ids.size()>0){
            String categoryIds="";
            for(String i:ids){
                categoryIds+=",'"+i+"'";
            }
            String sql2 = "select categoryId from ProjectCategoryEntity  where categoryState='1' and parentId in ("+categoryIds.substring(1)+")";
            Query query2 = getCurrentSession().createQuery(sql2);
            ids = query2.list();
            String hql3 = "update ProjectCategoryEntity set categoryState='0',modifyOn=:modifyOn where parentId in ("+categoryIds.substring(1)+")";
            Query query4 = getCurrentSession().createQuery(hql3);
            query4.setParameter("modifyOn",new Date());
            query4.executeUpdate(); //递归删除所有子级检查项
        }
    }

    @Override
    public void addCategorySupplier(SupplierCategoryEntity supplierCategoryEntity) {
        Session session = getCurrentSession();
        session.save(supplierCategoryEntity);
        session.flush();
    }

    @Override
    public List<ProjectCategoryEntity> getCategoryListForTime(long autoId, String timeStamp) {
        String hql = "from ProjectCategoryEntity where ((modifyOn='"+timeStamp+"' and autoNum>:autoId) or modifyOn>'"+timeStamp+"') order by modifyOn,autoNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("autoId",autoId);
        query.setMaxResults(1000);
        List<ProjectCategoryEntity> projectCategoryEntities = query.list();
        return projectCategoryEntities;
    }

    @Override
    public List<SupplierCategoryEntity> getSupplierCategoryForTime(String projectId, String timeStamp, long autoId) {
   //     String sql = "select a.categoryId,a.supplierId,a.buildId,a.categoryStatus,a.buildStatus,a.id,a.modifyOn,a.domain from duty_build_category as a,project_staff_employ as b where a.supplierId=b.data_Id and b.data_Type='1' and b.project_role is null and b.project_Id =:projectId and((a.modifyOn='"+timeStamp+"' and a.id>=:autoId) or a.modifyOn>'"+timeStamp+"') order by a.modifyOn,a.id";
        String hql = "from SupplierCategoryEntity where ckState<>'0' and ((modifyTime='"+timeStamp+"' and id>:autoId) or modifyTime>'"+timeStamp+"') " +
                "and supplierId in(select distinct dataId from ProjectStaffEmployEntity where dataType='1' and status='1' and projectRole is null and projectId=:projectId) order by modifyTime,id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("autoId",autoId);
        query.setParameter("projectId",projectId);
        query.setMaxResults(1000);
        List<SupplierCategoryEntity> supplierCategoryEntities = query.list();
        return supplierCategoryEntities;
    }

    @Override
    public List<BuildingSupplierEntity> getSupplierBuildingForTime(String projectId, String timeStamp, long autoId) {
        String hql = "from BuildingSupplierEntity where ((modifyTime='"+timeStamp+"' and autoId>:autoId) or modifyTime>'"+timeStamp+"') " +
                "and agencyId in(select distinct dataId from ProjectStaffEmployEntity where dataType='1' and status='1' and projectRole is null and projectId=:projectId) order by modifyTime,autoId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("autoId",autoId);
        query.setParameter("projectId",projectId);
        query.setMaxResults(1000);
        List<BuildingSupplierEntity> buildingSupplierEntities = query.list();
        return buildingSupplierEntities;
    }

    @Override
    public List<ProjectTargetEntity> getProjectTargetForTime(long autoId, String timeStamp) {
        String hql = "from ProjectTargetEntity where ((modifyOn='"+timeStamp+"' and autoNum>:autoId) or modifyOn>'"+timeStamp+"') order by modifyOn,autoNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("autoId",autoId);
        query.setMaxResults(500);
        List<ProjectTargetEntity> projectTargetEntities = query.list();
        return projectTargetEntities;
    }

    @Override
    public List<SupplierCategoryEntity> getSupplierCategorys(String dutyId,String domain) {
        String hql = "from SupplierCategoryEntity where supplierId=:dutyId and status='1' and domain=:domain and ckState<>'0'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dutyId", dutyId);
        query.setParameter("domain", domain);
        List<SupplierCategoryEntity> supplierCategoryEntities = query.list();
        return supplierCategoryEntities;
    }

    @Override
    public void updateSupplierCategory(SupplierCategoryEntity supplierCategoryEntity) {
        String[] ids = supplierCategoryEntity.getCategoryId().split(",");
        String hql;
        for(String categoryId:ids){
            if(!StringUtil.isEmpty(supplierCategoryEntity.getDefManageId())){
                hql = "update SupplierCategoryEntity set defManageId=:defManageId,modifyTime=:modifyTime where supplierId=:supplierId and categoryId=:categoryId";
                Query query = getCurrentSession().createQuery(hql);
                query.setParameter("defManageId",supplierCategoryEntity.getDefManageId());
                query.setParameter("modifyTime",new Date());
                query.setParameter("supplierId",supplierCategoryEntity.getSupplierId());
                query.setParameter("categoryId",categoryId);
                query.executeUpdate();
            }
            if(!StringUtil.isEmpty(supplierCategoryEntity.getDefOwnerId())){
                hql = "update SupplierCategoryEntity set defOwnerId=:defOwnerId,modifyTime=:modifyTime where supplierId=:supplierId and categoryId=:categoryId";
                Query query = getCurrentSession().createQuery(hql);
                query.setParameter("defOwnerId",supplierCategoryEntity.getDefOwnerId());
                query.setParameter("modifyTime",new Date());
                query.setParameter("supplierId",supplierCategoryEntity.getSupplierId());
                query.setParameter("categoryId",categoryId);
                query.executeUpdate();
            }
            if(!StringUtil.isEmpty(supplierCategoryEntity.getDefSupervisorId())){
                hql = "update SupplierCategoryEntity set defSupervisorId=:defSupervisorId,modifyTime=:modifyTime where supplierId=:supplierId and categoryId=:categoryId";
                Query query = getCurrentSession().createQuery(hql);
                query.setParameter("defSupervisorId",supplierCategoryEntity.getDefSupervisorId());
                query.setParameter("modifyTime",new Date());
                query.setParameter("supplierId",supplierCategoryEntity.getSupplierId());
                query.setParameter("categoryId",categoryId);
                query.executeUpdate();
            }
        }
    }

    @Override
    public List<AgencyEntity> getEmploys() {
        String hql = "from AgencyEntity where agencyType='4' and parentId='301' and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AgencyEntity> getSurveyors() {
        String hql = "from AgencyEntity where agencyType='5' and parentId='304' and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<ProjectCategoryEntity> getProjectCategoryListAll(int level, String domain, String parentId) {
        String hql = "from ProjectCategoryEntity where level=:level and categoryState='1' and domain=:domain ";
        if(!StringUtil.isEmpty(parentId)){
            hql += " and parentId=:parentId ";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("domain",domain);
        query.setParameter("level", level);
        if(!StringUtil.isEmpty(parentId)){
            query.setParameter("parentId",parentId);
        }
        List<ProjectCategoryEntity> projectCategoryList = query.list();
        return projectCategoryList;
    }

    @Override
    public List<ProjectCategoryEntity> getTreeCategoryList(String categoryId) {
        String hql = "from ProjectCategoryEntity where categoryState='1' and parentId=:categoryId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("categoryId",categoryId);
        List<ProjectCategoryEntity> projectCategoryEntities = query.list();
        return projectCategoryEntities;
    }

    @Override
    public boolean checkIsParent(String categoryId) {
        String hql = "from ProjectCategoryEntity where parentId=:categoryId and categoryState='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("categoryId", categoryId);
        List<ProjectCategoryEntity> projectCategoryEntities = query.list();
        if(projectCategoryEntities!=null&&projectCategoryEntities.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public void addSupplierCategory(SupplierCategoryEntity supplierCategoryEntity) {
        String sql1 = "INSERT INTO project_supplier_category(SUPPLIER_ID,CATEGORY_ID,DOMAIN,MODIFY_TIME,CHECK_STATE,STATUS) VALUES(?,?,?,?,?,1) ON DUPLICATE KEY UPDATE STATUS='1',MODIFY_TIME=?,CHECK_STATE=? ";
        Query query1 = getCurrentSession().createSQLQuery(sql1);
        query1.setString(0, supplierCategoryEntity.getSupplierId());
        query1.setString(1, supplierCategoryEntity.getCategoryId());
        query1.setString(2, supplierCategoryEntity.getDomain());
        query1.setString(3, DateUtils.format(new Date()));
        query1.setString(4, supplierCategoryEntity.getCkState());
        query1.setString(5,DateUtils.format(new Date()));
        query1.setString(6, supplierCategoryEntity.getCkState());
        query1.executeUpdate();
    }

    @Override
    public List<String> getCategoryIds(SupplierCategoryEntity supplierCategoryEntity) {
        String hql = "select categoryId from SupplierCategoryEntity where supplierId =:supplierId and domain=:domain and status='1' and ckState=:ckState";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("supplierId",supplierCategoryEntity.getSupplierId());
        query.setParameter("domain",supplierCategoryEntity.getDomain());
        query.setParameter("ckState", supplierCategoryEntity.getCkState());
        List<String> categoryIds = query.list();
        return categoryIds;
    }

    @Override
    public void delSupplierCategory(String dutyId) {
        String hql = "update SupplierCategoryEntity set status='0',modifyTime =:modifyTime where supplierId=:dutyId and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("dutyId",dutyId);
        query.executeUpdate();
    }

    @Override
    public void deleteSupplierCategory(SupplierCategoryEntity supplierCategoryEntity) {
        String hql = "update SupplierCategoryEntity set status='0',modifyTime =:modifyTime where supplierId=:dutyId and status='1'and domain=:domain and categoryId=:categoryId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime", new Date());
        query.setParameter("dutyId", supplierCategoryEntity.getSupplierId());
        query.setParameter("categoryId", supplierCategoryEntity.getCategoryId());
        query.setParameter("domain", supplierCategoryEntity.getDomain());
        query.executeUpdate();
    }

    @Override
    public List<ProjectCategoryEntity> getCategoryByDutyId(String dutyId, String domain, String parentId) {
        String hql = "from ProjectCategoryEntity where categoryId in(select a.categoryId from ProjectCategoryEntity as a,SupplierCategoryEntity as b where a.categoryId=b.categoryId  and b.status='1' and b.domain=:domain and b.supplierId=:dutyId)";
        if(!StringUtil.isEmpty(parentId)){
            hql += " and parentId=:parentId ";
        }else{
            hql +="and level=1";
        }
        hql += "and categoryState='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("domain",domain);
        query.setParameter("dutyId",dutyId);
        if(!StringUtil.isEmpty(parentId)){
            query.setParameter("parentId",parentId);
        }
        List<ProjectCategoryEntity> projectCategoryList = query.list();
        return projectCategoryList;
    }

    @Override
    public List<ProjectCategoryEntity> getAllCategoryByDutyId(String dutyId, String domain) {
        String hql = "from ProjectCategoryEntity where categoryState='1' and categoryId in(select a.categoryId from ProjectCategoryEntity as a,SupplierCategoryEntity as b where a.categoryId=b.categoryId  and b.status='1' and b.supplierId=:dutyId and b.domain=:domain )";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("domain", domain);
        query.setParameter("dutyId", dutyId);
        List<ProjectCategoryEntity> projectCategoryList = query.list();
        return projectCategoryList;
    }

    @Override
    public List<ProjectCategoryEntity> getCategoryTree(String domain, String categoryId) {
        String hql = "from ProjectCategoryEntity where categoryState='1' and domain=:domain";
        if(!StringUtil.isEmpty(categoryId)){
            hql += " and parentId=:parentId ";
        }else{
            hql +=" and level=1";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("domain",domain);
        if(!StringUtil.isEmpty(categoryId)){
            query.setParameter("parentId",categoryId);
        }
        List<ProjectCategoryEntity> projectCategoryList = query.list();
        return projectCategoryList;
    }

    @Override
    public List<ProjectTargetEntity> getTargetList(String categoryId) {
        String hql = "from ProjectTargetEntity where state='1' and categoryId=:categoryId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("categoryId",categoryId);
        List<ProjectTargetEntity> projectTargetEntities = query.list();
        return projectTargetEntities;
    }

    @Override
    public ProjectCategoryEntity getProjectCategoryByName(int level,String categoryName,String parentId,String domain) {
        String hql = "from ProjectCategoryEntity where categoryName=:categoryName and level=:level and domain=:domain";
        if(!StringUtil.isEmpty(parentId)){
            hql +=" and parentId='"+parentId+"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("categoryName",categoryName);
        query.setParameter("level",level);
        query.setParameter("domain",domain);
        ProjectCategoryEntity projectCategoryEntity = (ProjectCategoryEntity) query.uniqueResult();
        return projectCategoryEntity;
    }

    @Override
    public ProjectTargetEntity getTargetByName(String targetName, String categoryId) {
        String hql = "from ProjectTargetEntity where name=:targetName and categoryId=:categoryId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("targetName",targetName);
        query.setParameter("categoryId", categoryId);
        ProjectTargetEntity projectTargetEntity = (ProjectTargetEntity) query.uniqueResult();
        return projectTargetEntity;
    }

    @Override
    public void addOrUpdateTarget(ProjectTargetEntity projectTargetEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(projectTargetEntity);
        session.flush();
    }

    @Override
    public void addTarget(ProjectTargetEntity projectTargetEntity) {
        Session session = getCurrentSession();
        session.save(projectTargetEntity);
        session.flush();
    }

    @Override
    public void updateTarget(ProjectTargetEntity projectTargetEntity) {
        Session session = getCurrentSession();
        session.update(projectTargetEntity);
        session.flush();
    }


    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 保存检查项和标段关系
     */
    @Override
    public void addTenderCategory(TendersCategoryEntity tenderCategoryEntity) {
        String sql1 = "INSERT INTO tenders_category(TENDERS_ID,CATEGORY_ID,DOMAIN,MODIFY_TIME,CK_STATE,NEXUS_STATUS) VALUES(?,?,?,?,?,1) ON DUPLICATE KEY UPDATE NEXUS_STATUS='1',MODIFY_TIME=?,CK_STATE=? ";
        Query query1 = getCurrentSession().createSQLQuery(sql1);
        query1.setString(0,tenderCategoryEntity.getTendersId());
        query1.setString(1,tenderCategoryEntity.getCategoryId());
        query1.setString(2,tenderCategoryEntity.getDomain());
        query1.setString(3, DateUtils.format(new Date()));
        query1.setString(4, tenderCategoryEntity.getCkState());
        query1.setString(5,DateUtils.format(new Date()));
        query1.setString(6, tenderCategoryEntity.getCkState());
        query1.executeUpdate();
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 获取当前标段当前模块下的检查项id
     */
    @Override
    public List<String> getCategoryByIds(TendersCategoryEntity tendersCategoryEntity) {
        String hql = "select categoryId from TendersCategoryEntity where tendersId =:tenderId and domain=:domain and nexusStatus='1' and ckState=:ckState";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("tenderId",tendersCategoryEntity.getTendersId());
        query.setParameter("domain",tendersCategoryEntity.getDomain());
        query.setParameter("ckState",tendersCategoryEntity.getCkState());
        List<String> categoryIds = query.list();
        return categoryIds;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 删除标段与检查项关系
     */
    @Override
    public void deleteTenderCategory(TendersCategoryEntity tendersCategoryEntity) {
        String hql = "update TendersCategoryEntity set nexusStatus='0',modifyTime =:modifyTime where tendersId=:tenderId and nexusStatus='1'and domain=:domain and categoryId=:categoryId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("tenderId",tendersCategoryEntity.getTendersId());
        query.setParameter("categoryId",tendersCategoryEntity.getCategoryId());
        query.setParameter("domain",tendersCategoryEntity.getDomain());
        query.executeUpdate();
    }


    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 根据标段id和模块获取检查项信息
     */
    @Override
    public List<ProjectCategoryEntity> getCategoryByTenderId(String tenderId, String domain, String parentId) {
        StringBuffer hql = new StringBuffer(" from ProjectCategoryEntity where 1=1 and categoryState='1'");
        hql.append(" and categoryId in (select pc.categoryId from ProjectCategoryEntity pc, TendersCategoryEntity tc where pc.categoryId=tc.categoryId and tc.domain=:domain and tc.nexusStatus='1' and tc.tendersId=:tenderId)");
        //如果不存在父级，则为一级
        if (!StringUtils.isNullOrEmpty(parentId)) {
            hql.append(" and parentId=:parentId");
        }else {
            hql.append(" and level=1");
        }
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("domain",domain);
        query.setParameter("tenderId",tenderId);
        if(!StringUtil.isEmpty(parentId)){
            query.setParameter("parentId",parentId);
        }
        List<ProjectCategoryEntity> projectCategoryList = query.list();
        return projectCategoryList;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 根据标段获取所有检查项信息
     */
    @Override
    public List<ProjectCategoryEntity> getAllCategoryByTenderId(String tenderId, String domain) {
        StringBuffer hql = new StringBuffer("from ProjectCategoryEntity where 1=1 ");
        hql.append(" and categoryState='1'");
        hql.append(" and categoryId in (select pc.categoryId from ProjectCategoryEntity pc, TendersCategoryEntity tc where pc.categoryId=tc.categoryId and tc.nexusStatus='1' and domain=:domain and tc.tendersId=:tenderId)");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("domain",domain);
        query.setParameter("tenderId", tenderId);
        List<ProjectCategoryEntity> projectCategoryList = query.list();
        return projectCategoryList;
    }

    @Override
    public List<Object[]> exportCategory() {
        String sql = "SELECT t1.CATEGORY_NAME as TypeOne,t2.CATEGORY_NAME as TypeTwo,t3.CATEGORY_NAME as TypeThree,t4.CATEGORY_NAME as TypeFour,t5.NAME,t5.DESCRIPTION,t5.HAVE_PHOTO FROM project_category AS t1,project_category AS t2,project_category AS t3,project_category AS t4,project_target AS t5 WHERE t1.CATEGORY_DOMAIN='2' AND t1.CATEGORY_ID=t2.PARENT_ID AND t2.category_id=t3.parent_id AND t3.category_id=t4.parent_Id AND t4.CATEGORY_ID=t5.CATEGORY_ID " +
                "AND t1.CATEGORY_STATE='1' AND t2.CATEGORY_STATE='1' AND t3.CATEGORY_STATE='1' AND t4.CATEGORY_STATE='1' AND t5.STATE='1'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public List<Object[]> exportSide() {
        String sql = "SELECT category_name,FREE_FIELD,time_space FROM project_category WHERE CATEGORY_DOMAIN='5' AND CATEGORY_STATE='1'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public List<Object[]> exportInspected() {
        String sql = "SELECT t1.category_name as typeOne,t2.category_name as typeTwo,t2.FREE_FIELD,t3.name,t3.description FROM project_category as t1,project_category as t2,project_target as t3 WHERE t1.CATEGORY_DOMAIN='4' AND t1.CATEGORY_STATE='1' and t1.category_id=t2.parent_id and t2.category_state='1' and t2.category_id=t3.category_id";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public List<Object[]> exportDaily() {
        String sql = "SELECT t1.CATEGORY_NAME as TypeOne,t2.CATEGORY_NAME as TypeTwo,t3.CATEGORY_NAME as TypeThree FROM project_category AS t1,project_category AS t2,project_category AS t3 WHERE t1.CATEGORY_DOMAIN='1' AND t1.CATEGORY_ID=t2.PARENT_ID AND t2.category_id=t3.parent_id AND t1.CATEGORY_STATE='1' AND t2.CATEGORY_STATE='1' AND t3.CATEGORY_STATE='1'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> objects =  query.list();
        return objects;
    }

    @Override
    public List<Object[]> exportModelReviewsy() {
        String sql = "SELECT t1.CATEGORY_NAME as TypeOne,t2.CATEGORY_NAME as TypeTwo,t3.CATEGORY_NAME as TypeThree,t5.NAME,t5.DESCRIPTION,t5.HAVE_PHOTO FROM project_category AS t1,project_category AS t2,project_category AS t3,project_target AS t5 WHERE t1.CATEGORY_DOMAIN='3' AND t1.CATEGORY_ID=t2.PARENT_ID AND t2.category_id=t3.parent_id  AND t3.CATEGORY_ID=t5.CATEGORY_ID " +
                "AND t1.CATEGORY_STATE='1' AND t2.CATEGORY_STATE='1' AND t3.CATEGORY_STATE='1'  AND t5.STATE='1'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> objects = query.list();
        return objects;
    }
}

