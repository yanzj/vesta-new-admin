package com.maxrocky.vesta.presistent.projectMaterial.projectMaterialRepositoryImpl;

import com.maxrocky.vesta.domain.dailyPatrolInspection.model.DailyPatrolInspectionEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialDetailsEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialOutEntity;
import com.maxrocky.vesta.domain.projectMaterial.repository.ProjectMaterialRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2016/11/24.
 */
@Repository
public class ProjectMaterialRepositoryImpl extends HibernateDaoImpl implements ProjectMaterialRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存材料验收主表
     * */
    @Override
    public void saveProjectMaterial(ProjectMaterialEntity projectMaterialEntity) {
        Session session=getCurrentSession();
        session.save(projectMaterialEntity);
        session.flush();
        session.close();
    }
    /**
     * 修改材料验收主表
     * */
    @Override
    public void updateProjectMaterial(ProjectMaterialEntity projectMaterialEntity) {
        Session session=getCurrentSession();
        session.update(projectMaterialEntity);
        session.flush();
        session.close();
    }
    /**
     * 保存或修改材料验收指标信息
     * */
    @Override
    public void saveUpdateProjectMaterialDetails(ProjectMaterialDetailsEntity projectMaterialDetailsEntity) {
        Session session=getCurrentSession();
        session.saveOrUpdate(projectMaterialDetailsEntity);
        session.flush();
        session.close();
    }
    /**
     * 保存退场纪录实体表
     * */
    @Override
    public void saveUpdateProjectMaterialOut(ProjectMaterialOutEntity projectMaterialOutEntity) {
        Session session=getCurrentSession();
        session.saveOrUpdate(projectMaterialOutEntity);
        session.flush();
        session.close();
    }
    /**
     * 修改退场纪录实体表
     * */
    @Override
    public void updateProjectMaterialOut(ProjectMaterialOutEntity projectMaterialOutEntity) {
        Session session=getCurrentSession();
        session.update(projectMaterialOutEntity);
        session.flush();
        session.close();
    }
    /**
     *根据当前登录人id查询处理中的材料验收数据
     * */
    @Override
    public int coutMaterial(String userId) {
        String sql = "select count(1) from project_material where  STATE='整改中' ";
        if (!StringUtil.isEmpty(userId)) {
            sql += " and DEAL_PEOPLE = '" + userId + "'";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
    }
    /**
     * 根据材料验收id查询材料验收
     * */
    @Override
    public ProjectMaterialEntity getMaterialById(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_material s where MATERIAL_ID=:id ").addEntity(ProjectMaterialEntity.class);
        sqlQuery.setParameter("id", id);
        List<ProjectMaterialEntity> materialEntity = sqlQuery.list();
        if (materialEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return materialEntity.get(0);
    }

    @Override
    public ProjectMaterialEntity getMaterialByIdandAppId(String id, String appId) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_material s where MATERIAL_ID=:id or APP_ID=:aid").addEntity(ProjectMaterialEntity.class);
        sqlQuery.setParameter("id", id);
        sqlQuery.setParameter("aid", appId);
        List<ProjectMaterialEntity> materialEntity = sqlQuery.list();
        if (materialEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return materialEntity.get(0);
    }

    /**
     * 根据材料验收Appid查询材料验收
     * */
    @Override
    public ProjectMaterialEntity getMaterialByAppId(String appId) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_material s where APP_ID=:id ").addEntity(ProjectMaterialEntity.class);
        sqlQuery.setParameter("id", appId);
        List<ProjectMaterialEntity> materialEntity = sqlQuery.list();
        if (materialEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return materialEntity.get(0);
    }

    @Override
    public ProjectMaterialDetailsEntity getProjectMaterialDetails(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_material_details s where ID=:id ").addEntity(ProjectMaterialDetailsEntity.class);
        sqlQuery.setParameter("id", id);
        List<ProjectMaterialDetailsEntity> materialDetailsEntity = sqlQuery.list();
        if (materialDetailsEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return materialDetailsEntity.get(0);
    }

    @Override
    public List<ProjectMaterialDetailsEntity> getMaterialDetailsList(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_material_details s where MATERIAL_ID=:id ").addEntity(ProjectMaterialDetailsEntity.class);
        sqlQuery.setParameter("id", id);
        List<ProjectMaterialDetailsEntity> materialDetailsEntity = sqlQuery.list();
        if (materialDetailsEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return materialDetailsEntity;
    }

    @Override
    public ProjectMaterialOutEntity getMaterialOut(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_material_out s where MATERIAL_ID=:id ").addEntity(ProjectMaterialOutEntity.class);
        sqlQuery.setParameter("id", id);
        List<ProjectMaterialOutEntity> materialDetailsEntity = sqlQuery.list();
        if (materialDetailsEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return materialDetailsEntity.get(0);
    }

    @Override
    public List<ProjectMaterialEntity> getMaterialEntityList(String id, String time, String projectId, String type,String userId) {
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer(" select * from project_material s ");
        sql.append(" where 1=1 ");
        sql.append(" and s.PROJECT_id=:projectId ");
        if ("2".equals(type)) {
            sql.append(" and (s.CREATE_BY=:creaid or s.SUPERVISOR=:creaid or s.DEAL_PEOPLE=:creaid)");
        }
        sql.append(" and ((s.CREATE_BY =:creaid and s.STATE='草稿') or s.STATE <>'草稿') ");
        sql.append(" and ((s.MODIFYDATE=:tim and s.ID>:iid) or s.MODIFYDATE>:tim)  ORDER BY s.MODIFYDATE , s.ID limit 500 ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString()).addEntity(ProjectMaterialEntity.class);
        sqlQuery.setParameter("tim", time);
        sqlQuery.setParameter("iid", id);
        sqlQuery.setParameter("creaid", userId);
        sqlQuery.setParameter("projectId", projectId);
        List<ProjectMaterialEntity> materialEntity = sqlQuery.list();
        if (materialEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return materialEntity;
    }

    @Override
    public boolean checkoutMaterial(String id, String time, String projectId, String type, String userId) {
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer(" select count(1) from project_material s ");
        sql.append(" where 1=1 ");
        sql.append(" and s.PROJECT_id=:projectId ");
        if ("2".equals(type)) {
            sql.append(" and (s.CREATE_BY=:creaid or s.SUPERVISOR=:creaid or s.DEAL_PEOPLE=:creaid)");
        }
        sql.append(" and ((s.CREATE_BY =:creaid and s.STATE='草稿') or s.STATE <>'草稿') ");
        sql.append(" and ((s.MODIFYDATE=:tim and s.ID>:iid) or s.MODIFYDATE>:tim)  ORDER BY s.MODIFYDATE , s.ID limit 500 ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter("tim", time);
        sqlQuery.setParameter("iid", id);
        sqlQuery.setParameter("creaid", userId);
        sqlQuery.setParameter("projectId", projectId);
        BigInteger count = (BigInteger) sqlQuery.uniqueResult();
        if (count.intValue() > 0) {
            return true;
        } else {
            return false;
        }
//        if (count>0) {
//            return true;
//        } else {
//            return false;
//        }
    }

    @Override
    public List<Object[]> searchMaterial(String projectId) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pm.CATEGORY_ID,pm.CATEGORY_NAME,mateAll.mAll,mateOK.mOK,mateNO.mNO,mateOut.mOUT ");
        sql.append(" from project_category pm  ");
        sql.append(" LEFT JOIN (SELECT COUNT(1) as mAll,CLASSIFY_ONE from project_material where 1=1 and PROJECT_ID=:projectId and STATE<>'草稿' GROUP BY CLASSIFY_ONE) as mateAll ON pm.CATEGORY_ID=mateAll.CLASSIFY_ONE ");
        sql.append(" LEFT JOIN (SELECT COUNT(1) as mOK,CLASSIFY_ONE from project_material where 1=1 and PROJECT_ID=:projectId and STATE='合格' GROUP BY CLASSIFY_ONE) as mateOK ON  pm.CATEGORY_ID=mateOK.CLASSIFY_ONE ");
        sql.append(" LEFT JOIN (SELECT COUNT(1) as mNO,CLASSIFY_ONE from project_material where 1=1 and PROJECT_ID=:projectId and STATE='不合格' GROUP BY CLASSIFY_ONE) as mateNO ON pm.CATEGORY_ID=mateNO.CLASSIFY_ONE ");
        sql.append(" LEFT JOIN (SELECT COUNT(1) as mOUT,CLASSIFY_ONE from project_material where 1=1 and PROJECT_ID=:projectId and STATE='已退场' GROUP BY CLASSIFY_ONE) as mateOut ON pm.CATEGORY_ID=mateOut.CLASSIFY_ONE ");
        sql.append(" where CATEGORY_DOMAIN='4' and CATEGORY_LEVEL='1' ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("projectId", projectId);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<ProjectMaterialEntity> getMaterialAdmin(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer("");
        sql.append(" from ProjectMaterialEntity dpl ");
        sql.append(" where 1=1 ");
        //项目id
        if (!StringUtil.isEmpty(map.get("projectId").toString())) {
            sql.append(" and dpl.projectId=? ");
            params.add(map.get("projectId").toString());
        }
        //项目权限
        if(!"NO".equals(map.get("projectList").toString())){
            sql.append(" and dpl.projectId in (" + map.get("projectList").toString() + ") ");
        }
        //一级分类
        if (!StringUtil.isEmpty(map.get("classifyOne").toString())) {
            sql.append(" and dpl.classifyOne=? ");
            params.add(map.get("classifyOne").toString());
        }
        //二级分类
        if (!StringUtil.isEmpty(map.get("classifyTwo").toString())) {
            sql.append(" and dpl.classifyTwo=? ");
            params.add(map.get("classifyTwo").toString());
        }
        //状态
        if (!StringUtil.isEmpty(map.get("state").toString())) {
            sql.append(" and dpl.state=? ");
            params.add(map.get("state").toString());
        }
        //责任单位
        if (!StringUtil.isEmpty(map.get("supplier").toString())) {
            sql.append(" and dpl.supplierName like? ");
            params.add(map.get("supplier").toString());
        }
        //甲方负责人名字
        if (!StringUtil.isEmpty(map.get("firstPartyName").toString())) {
            sql.append(" and dpl.firstPartyName like? ");
            params.add(map.get("firstPartyName").toString());
        }
        //第三方监理名字
        if (!StringUtil.isEmpty(map.get("supervisorName").toString())) {
            sql.append(" and dpl.supervisorName like? ");
            params.add(map.get("supervisorName").toString());
        }
        //材料负责人名字
        if (!StringUtil.isEmpty(map.get("assignName").toString())) {
            sql.append(" and dpl.assignName like? ");
            params.add(map.get("assignName").toString());
        }
        //开始日期
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql.append(" and dpl.createOn >= '" + startDate +"' ");
        }
        //结束时间
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql.append(" and dpl.createOn <= '" + endDate +"' ");
        }
        if (!StringUtil.isEmpty(map.get("creaBy").toString())) {
            sql.append(" and ((dpl.createBy=? and dpl.state='草稿') or dpl.state <>'草稿') ");
            params.add(map.get("creaBy").toString());
        }
        sql.append(" order by dpl.modifyDate desc ");

        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<ProjectMaterialEntity> list = (List<ProjectMaterialEntity>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }
}
