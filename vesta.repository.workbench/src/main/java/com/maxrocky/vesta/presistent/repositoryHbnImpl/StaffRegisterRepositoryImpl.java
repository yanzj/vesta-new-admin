package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.StaffRegisterEntity;
import com.maxrocky.vesta.domain.repository.StaffRegisterRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/21.
 */
@Repository
public class StaffRegisterRepositoryImpl implements StaffRegisterRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * 签到
     * @param registerEntity
     * @return
     */
    @Override
    public boolean saveRegister(StaffRegisterEntity registerEntity) {
        Session session = getCurrentSession();
        session.save(registerEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 获取签到列表
     * @param staffId
     * @param page
     * @param count
     * @return
     */
    @Override
    public List<StaffRegisterEntity> listStaffRegisterEntity(String staffId, int page, int count) {
        String hql = "from StaffRegisterEntity as o where o.staffId = :staffId order by o.registerDateTiem desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        query.setFirstResult((page - 1) * count);
        query.setMaxResults(count);
        List<StaffRegisterEntity> list = query.list();
        return list;
    }
    /**
     * 获得员工最新一条签到信息
     * @param staffId
     * @return
     */
    @Override
    public StaffRegisterEntity getStaffRegisterById(String staffId,String date) {
        String hql = "from StaffRegisterEntity as o where o.staffId = :staffId and o.registerDate = :date";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        query.setParameter("date",date);
        if (query.list().size()>0){
            return (StaffRegisterEntity)query.list().get(0);
        }
        else {
            return null;
        }
    }

    /**
     * 获取签到人员的维修、客服、秩序、环境部门(员工端)
     * createBy：liudongxin
     * @param projectId
     * @return
     */
    @Override
    public List<StaffRegisterEntity> getDepartments(String projectId) {
        String hql="from StaffRegisterEntity where projectId='"+projectId+"' and staffRegisterStatus='1' and " +
                "(staffSection like '%维修%' or staffSection like '%客服%' or staffSection like '%秩序%' " +
                "or staffSection like '%环境%') order by staffSection asc";
        Query query = getCurrentSession().createQuery(hql);
        List<StaffRegisterEntity> departList=query.list();
        return departList;
    }

    /**
     * 获取签到人员的维修部门(管理端)
     * createBy：liudongxin
     * @param projectId
     * @return
     */
    @Override
    public List<StaffRegisterEntity> getRepairDepartment(String projectId,String today) {
        String hql="from StaffRegisterEntity where projectId='"+projectId+"' and staffRegisterStatus='1' " +
                "and registerDate='"+today+"' and (staffSection like '%维修%' or staffSection like '%工程%') " +
                "order by staffSection asc";
        Query query = getCurrentSession().createQuery(hql);
        List<StaffRegisterEntity> departList=query.list();
        return departList;
    }

    /**
     * 获取签到人员的客服、秩序、环境部门(管理端)
     * createBy：liudongxin
     * @param projectId
     * @return
     */
    @Override
    public List<StaffRegisterEntity> getDepartment(String projectId,String today) {
        String hql="from StaffRegisterEntity where projectId='"+projectId+"' and staffRegisterStatus='1' " +
                "and registerDate='"+today+"' and (staffSection like '%客服%' or staffSection like '%秩序%' " +
                "or staffSection like '%环境%') " +" order by staffSection asc";
        Query query = getCurrentSession().createQuery(hql);
        List<StaffRegisterEntity> departList=query.list();
        return departList;
    }

    /**
     * 获取部门下当天签到所有人员(管理端)
     * createBy：liudongxin
     * @param sectionId
     * @param today
     * @return
     */
    @Override
    public List<StaffRegisterEntity> getRegister(String sectionId,String today) {
        String hql="from StaffRegisterEntity where staffSectionId='"+sectionId+"' and staffRegisterStatus='1' " +
                "and registerDate='"+today+"' order by staffName asc";
        Query query = getCurrentSession().createQuery(hql);
        List<StaffRegisterEntity> departList=query.list();
        return departList;
    }
}
