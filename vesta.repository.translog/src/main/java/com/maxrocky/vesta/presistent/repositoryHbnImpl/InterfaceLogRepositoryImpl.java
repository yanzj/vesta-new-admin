package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.InterfaceLogRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/22.
 */
@Repository
public class InterfaceLogRepositoryImpl implements InterfaceLogRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:创建接口日志信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    @Override
    public void create(InterfaceLogEntity interfaceLogEntity) {
        Session session = getCurrentSession();
        session.save(interfaceLogEntity);
        session.flush();
    }

    @Override
    public void createPlanLog(InterfacePlanLogEntity interfacePlanLogEntity) {
        Session session = getCurrentSession();
        session.save(interfacePlanLogEntity);
        session.flush();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:修改接口日志信息
     * ModifyBy:
     */
    @Override
    public void update(InterfaceLogEntity interfaceLogEntity) {
        Session session = getCurrentSession();
        session.update(interfaceLogEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据日志id获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public InterfaceLogEntity getById(String id) {
        String hql="FROM InterfaceLogEntity WHERE id='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<InterfaceLogEntity> logCRMList=query.list();
        if(logCRMList.size()>0){
            return logCRMList.get(0);
        }
        return null;
    }

    @Override
    public void createRepair(InterFaceRepairLogsEntity interFaceRepairLogsEntity) {
        Session session = getCurrentSession();
        session.save(interFaceRepairLogsEntity);
        session.flush();
    }

    @Override
    public void createRectify(InterFaceRectifyLogsEntity interFaceRectifyLogsEntity) {
        Session session = getCurrentSession();
        session.save(interFaceRectifyLogsEntity);
        session.flush();
    }

    @Override
    public void createClassUserLog(InterfaceClassUserLogEntity interfaceClassUserLogEntity) {
        Session session = getCurrentSession();
        session.save(interfaceClassUserLogEntity);
        session.flush();
    }

    @Override
    public void createComplainLog(InterfaceComPlainLogEntity interfaceComPlainLogEntity) {
        Session session = getCurrentSession();
        session.save(interfaceComPlainLogEntity);
        session.flush();
    }
}
