package com.maxrocky.vesta.presistent.repositoryHbnImpl;


import com.maxrocky.vesta.domain.model.HouseSectionEntity;
import com.maxrocky.vesta.domain.repository.HouseSectionRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/22.
 */
@Repository
public class HouseSectionRepositoryImpl  extends HibernateDaoImpl implements HouseSectionRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * 通过部门Id获取部门信息
     * @param sectionId
     * @return
     */
    @Override
    public HouseSectionEntity getSectionById(String sectionId) {
        String hql = "from HouseSectionEntity as o where o.sectionId = :sectionId and o.state = :state";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("sectionId",sectionId);
        query.setParameter("state",HouseSectionEntity.SECTION_STATE_YES);
        if (query.list().size()>0){
            HouseSectionEntity houseSectionEntity = (HouseSectionEntity)query.list().get(0);
            return houseSectionEntity;
        }else {
            return null;
        }
    }

    /**
     * 获得部门列表
     * @return
     */
    @Override
    public List<HouseSectionEntity> listHouseSection() {
        String hql = "from HouseSectionEntity as o where o.state = :state";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("state",HouseSectionEntity.SECTION_STATE_YES);
        List<HouseSectionEntity> list = query.list();
        return list;
    }

    /**
     * 获取m某项目下所有部门
     * @param projectId
     * @return
     */
    @Override
    public List<HouseSectionEntity> listSectionByProject(String projectId,WebPage webPage) {
        List<HouseSectionEntity> houseSectionEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from HouseSectionEntity as s where 1 = 1");
        //有效
        hql.append(" and s.state = ?");
        params.add(HouseSectionEntity.SECTION_STATE_YES);
        //公司Id
//        hql.append(" and s.projectId = ?");
//        params.add(projectId);
        hql.append(SqlJoiningTogether.sqlStatement("s.projectId",projectId));
        hql.append("  order by s.level");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        houseSectionEntities = (List<HouseSectionEntity>)getHibernateTemplate().find(hql.toString());

        return houseSectionEntities;
    }

    /**
     * 添加部门
     * @param houseSectionEntity
     * @return
     */
    @Override
    public boolean addSection(HouseSectionEntity houseSectionEntity) {
        Session session = getCurrentSession();
        session.save(houseSectionEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 修改部门
     * @param houseSectionEntity
     * @return
     */
    @Override
    public boolean updateSecton(HouseSectionEntity houseSectionEntity) {

        HouseSectionEntity houseSectionEntity_0 = getSectionById(houseSectionEntity.getSectionId());
        if (houseSectionEntity_0!=null){
            Session session = getCurrentSession();
            session.update(houseSectionEntity);
            session.flush();
            session.close();
            return true;
        }
        return false;
    }

    /**
     * 获取项目下所有部门
     * @param projectId
     * @return
     */
    @Override
    public List<HouseSectionEntity> listSectionByProject(String projectId) {
        String hql = "from HouseSectionEntity as h where h.state = :state and h.projectId = :projectId order by h.level";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("state",HouseSectionEntity.SECTION_STATE_YES);
        query.setParameter("projectId",projectId);
        List<HouseSectionEntity> houseSectionEntities = query.list();
        return houseSectionEntities;
    }

    /**
     * 根据级别获取部门
     * @param moveStatus
     * @return
     */
    @Override
    public HouseSectionEntity getSectionByLevel(String moveStatus,String projectId,int level) {
        String hql = "from HouseSectionEntity as o where o.state = :state and o.projectId = :projectId";
        if (moveStatus.equals("down")){
                hql = hql +" and o.level >'"+level+"' order by o.level asc";
        }else if (moveStatus.equals("up")){
            hql = hql+" and o.level <'"+level+"'order by o.level desc";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("state",HouseSectionEntity.SECTION_STATE_YES);
        query.setParameter("projectId",projectId);
        query.setFirstResult(0);
        query.setMaxResults(1);
        if (query.list().size()>0){
            HouseSectionEntity houseSectionEntity = (HouseSectionEntity)query.list().get(0);
            return houseSectionEntity;
        }else {
            return null;
        }
    }

    /**
     * 获取该项目下部门总数
     * @param projectId
     * @return
     */
    @Override
    public int countSection(String projectId) {
        String hql = "select count(p.sectionName) from HouseSectionEntity as p where p.state = :state and p.projectId = :projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("state",HouseSectionEntity.SECTION_STATE_YES);
        query.setParameter("projectId",projectId);
        int number = ((Number)query.iterate().next()).intValue();
        return number;
    }

    /**
     * 获取最低级别部门
     * @param projectId
     * @return
     */
    @Override
    public HouseSectionEntity getLastSection(String projectId) {
        String hql = "from HouseSectionEntity as h where h.projectId=:projectId and h.state=:state order by h.level desc";
        Query query =getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("state",HouseSectionEntity.SECTION_STATE_YES);
        if (query.list().size()>0){
            HouseSectionEntity houseSectionEntity = (HouseSectionEntity)query.list().get(0);
            return houseSectionEntity;
        }else {
            return null;
        }
    }

    /**
     * 所有维修和客服部门(员工端)
     * createBy：liudongxin
     * @param projectId
     * @return
     */
    @Override
    public List<HouseSectionEntity> getDepartmentList(String projectId) {
        String hql="from HouseSectionEntity where state ='YES' and projectId='"+projectId+
                "' and (sectionName like '%维修%' or sectionName like '%工程%' or sectionName like '%客服%' " +
                "or sectionName like '%秩序%' or sectionName like '%环境%') order by createOn asc";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseSectionEntity> houseSectionEntities = query.list();
        return houseSectionEntities;
    }

    /**
     * 所有维修部门(管理端)
     * createBy：liudongxin
     * @param projectId
     * @return
     */
    @Override
    public List<HouseSectionEntity> getServiceDepartment(String projectId) {
        String hql="from HouseSectionEntity where state ='YES' and projectId='"+projectId+
                "' and sectionName like '%维修%' order by createOn asc";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseSectionEntity> houseSectionEntities = query.list();
        return houseSectionEntities;
    }

    /**
     * 所有客服部门(管理端)
     * createBy：liudongxin
     * @param projectId
     * @return
     */
    @Override
    public List<HouseSectionEntity> getCustomerDepartment(String projectId) {
        String hql="from HouseSectionEntity where state ='YES' and projectId='"+projectId+
                "' and sectionName like '%客服%' order by createOn asc";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseSectionEntity> houseSectionEntities = query.list();
        return houseSectionEntities;
    }

    @Override
    public HouseSectionEntity testSectionByName(String sectionName, String projectId) {

        String hql = "from HouseSectionEntity as h where h.projectId=:projectId and h.state=:state and h.sectionName = :sectionName order by h.level desc";
        Query query =getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("state",HouseSectionEntity.SECTION_STATE_YES);
        query.setParameter("sectionName",sectionName);
        if (query.list().size()>0){
            HouseSectionEntity houseSectionEntity = (HouseSectionEntity)query.list().get(0);
            return houseSectionEntity;
        }else {
            return null;
        }
    }
}
