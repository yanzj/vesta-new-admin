package com.maxrocky.vesta.presistent.inspectAcceptance.repositoryHbnImpl;

import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineTargetEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.repository.ProjectExamineTargetRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jiazefeng on 2016/10/19.
 */
@Repository
public class ProjectExamineTargetRepositoryImpl extends HibernateDaoImpl implements ProjectExamineTargetRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public boolean addProjectExamineTargetInfo(ProjectExamineTargetEntity projectExamineTargetEntity) {
//        this.save(projectExamineTargetEntity);
        Session session = getCurrentSession();
        session.save(projectExamineTargetEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public List<Object[]> searchProjectExamineTargetListByBatchId(String batchId) {
        String sql ="SELECT pet.ID AS petId,pt.NAME,pet.IS_QUALIFIED_ID,petd.DESCRIPTION,pi.IMAGE_URL,pt.DESCRIPTION AS targetDescripion,petd.STATE FROM project_images pi " +
                "LEFT JOIN project_examine_target_details petd ON pi.BUSINESS_ID = petd.ID " +
                "LEFT JOIN project_examine_target pet ON petd.EXAMINE_TARGET_ID = pet.ID " +
                "LEFT JOIN project_target pt ON pet.TARGET_ID = pt.ID " +
                "WHERE (petd.RECTIFICATION IS NULL AND petd.ACCEPTANCE IS NULL) ";
//        String sql = "SELECT  pt.ID AS targetId,pet.ID AS petId,petd.ID AS petdId,pt.NAME,pet.IS_QUALIFIED_ID,petd.DESCRIPTION," +
//                "pi.IMAGE_URL " +
//                " FROM project_examine_target pet ,project_examine_target_details petd,project_images pi,project_target pt" +
//                " WHERE pet.ID=petd.EXAMINE_TARGET_ID AND pt.ID=pet.TARGET_ID AND pi.BUSINESS_ID =petd.ID" +
//                " AND (petd.RECTIFICATION <> '整改记录' OR petd.ACCEPTANCE <> '验收记录')";
        if (batchId != null && !batchId.equals("")) {
            sql += " AND pet.EXAMINE_ID='" + batchId + "'";
        }
        sql += " ORDER BY petd.CREATE_ON DESC";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> searchProjectExamineTargetCheckListByBatchId(String examineTargetId) {
        String sql ="SELECT pet.ID AS petId,petd.ID AS petdId,petd.DESCRIPTION,pi.IMAGE_URL,petd.CHANGE_TIME,pet.IS_QUALIFIED_ID FROM project_images pi " +
                "LEFT JOIN project_examine_target_details petd ON pi.BUSINESS_ID = petd.ID " +
                "LEFT JOIN project_examine_target pet ON petd.EXAMINE_TARGET_ID = pet.ID " +
                "WHERE petd.RECTIFICATION ='整改记录'";
//        String sql = "SELECT pet.ID AS petId,petd.ID AS petdId,petd.DESCRIPTION,pi.IMAGE_URL" +
//                " FROM project_examine_target pet ,project_examine_target_details petd,project_images pi" +
//                " WHERE pet.ID=petd.EXAMINE_TARGET_ID AND pi.BUSINESS_ID =petd.ID AND petd.RECTIFICATION ='整改记录' ";
        if (examineTargetId != null && !examineTargetId.equals("")) {
            sql += " AND petd.EXAMINE_TARGET_ID='" + examineTargetId + "'";
        }
        sql += " ORDER BY petd.CREATE_ON ASC ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> searchProjectExamineTargetAcceptanceListByBatchId(String examineTargetId) {
        String sql ="SELECT pet.ID AS petId,petd.ID AS petdId,pet.IS_QUALIFIED_ID,petd.DESCRIPTION,pi.IMAGE_URL,petd.STATE FROM project_images pi " +
                "LEFT JOIN project_examine_target_details petd ON pi.BUSINESS_ID = petd.ID " +
                "LEFT JOIN project_examine_target pet ON petd.EXAMINE_TARGET_ID = pet.ID " +
                "LEFT JOIN project_target pt ON pet.TARGET_ID = pt.ID " +
                "WHERE petd.ACCEPTANCE ='验收记录'";
//        String sql = "SELECT pet.ID AS petId,petd.ID AS petdId,petd.DESCRIPTION,pi.IMAGE_URL" +
//                " FROM project_examine_target pet ,project_examine_target_details petd,project_images pi" +
//                " WHERE pet.ID=petd.EXAMINE_TARGET_ID AND pi.BUSINESS_ID =petd.ID AND petd.ACCEPTANCE ='验收记录' ";
        if (examineTargetId != null && !examineTargetId.equals("")) {
            sql += " AND petd.EXAMINE_TARGET_ID='" + examineTargetId + "'";
        }
        sql += " ORDER BY petd.CREATE_ON ASC ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<ProjectExamineTargetEntity> getProjectExamineTargetListByBatchId(String batchId) {
        String sql = " from ProjectExamineTargetEntity where examineId = '" + batchId + "'";
        ;
        Query query = getCurrentSession().createQuery(sql);
        List<ProjectExamineTargetEntity> list = query.list();
        return list;
    }

    @Override
    public ProjectExamineTargetEntity getProjectExamineTargetById(String id) {
        String sql = " from ProjectExamineTargetEntity where id = '" + id + "'";
        Query query = getCurrentSession().createQuery(sql);
        return (ProjectExamineTargetEntity) query.uniqueResult();
    }

    @Override
    public void updateProjectExamineTarget(ProjectExamineTargetEntity projectExamineTargetEntity) {
        Session session = getCurrentSession();
        session.update(projectExamineTargetEntity);
        session.flush();
        session.close();
    }
}
