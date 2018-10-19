package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.CommunityBatchManageEntity;
import com.maxrocky.vesta.domain.model.CommunityHouseAppointEntity;
import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.repository.CommunityCenterRespository;
import com.maxrocky.vesta.domain.repository.CommunityHouseBatchRespository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:04
 */
@Repository
public class CommunityBatchRespositoryImpl extends HibernateDaoImpl implements CommunityHouseBatchRespository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 根据条件分页查询所有交付批次信息
     *
     * @param communityBatchManageEntity
     * @param webPage
     * @return
     */
    @Override
    public List<CommunityBatchManageEntity> queryAllDeliveryBatch(CommunityBatchManageEntity communityBatchManageEntity, WebPage webPage) throws Exception {
        StringBuffer hql = new StringBuffer("FROM CommunityBatchManageEntity as ad WHERE 1=1");
        List<CommunityBatchManageEntity> communityBatchManageEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        // 不为空则拼接搜索条件  项目id
        if (null != communityBatchManageEntity.getCommunityId() && !"0".equals(communityBatchManageEntity.getCommunityId())) {
            hql.append(" and ad.communityId = ?");
            params.add(communityBatchManageEntity.getCommunityId());
        }
        // 不为空则拼接搜索添加 交付批次
        if (!StringUtil.isEmpty(communityBatchManageEntity.getDeliveryBatch())) {
            hql.append(" and ad.deliveryBatch = ?");
            params.add(communityBatchManageEntity.getDeliveryBatch());
        }
        // 不为空则拼接搜索添加 交付状态  //需要进行转换
        if (null != communityBatchManageEntity.getBatchStatus()) {
            hql.append(" and ad.batchStatus = ?");
            params.add(communityBatchManageEntity.getBatchStatus());
        }
        // 不为空则拼接搜索添加 集中交付开始时间
        if (null != communityBatchManageEntity.getPayStaTime()) {
            hql.append(" and ad.payStaTime >= ?");
            params.add(communityBatchManageEntity.getPayStaTime());
        }
        // 不为空则拼接搜索添加 集中交付结束时间
        if (communityBatchManageEntity.getPayEndTime() != null) {
            hql.append(" and ad.payEndTime <= ?");
            params.add(communityBatchManageEntity.getPayEndTime());
        }
        //设置为查询批次
        hql.append(" and ad.status = 1 ");
        hql.append(" ORDER BY ad.operatorDate DESC");

        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        communityBatchManageEntityList = (List<CommunityBatchManageEntity>) getHibernateTemplate().find(hql.toString());

        return communityBatchManageEntityList;
    }


    /**
     * 根据条件分页查询所有交付批次信息
     * @param communityHouseAppoint
     * @param webPage
     * @return
     */
   /* @Override
    public List<CommunityHouseAppointEntity> queryAllDeliveryBatch(CommunityHouseAppointEntity communityHouseAppoint, WebPage webPage) throws Exception {
        StringBuffer hql = new StringBuffer("FROM CommunityHouseAppointEntity as ad WHERE 1=1");
        List<CommunityHouseAppointEntity> communityHouseAppointEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        String type = "0";
        //初始化type信息，0，交付批次信息 1，预约单信息
        hql.append(SqlJoiningTogether.sqlStatement("ad.type", type));

        // 不为空则拼接搜索条件  项目id
        if(null != communityHouseAppoint.getCommunityId() && !"0".equals(communityHouseAppoint.getCommunityId())){
            hql.append(" and ad.communityId = ?");
            params.add(communityHouseAppoint.getCommunityId());
        }
        // 不为空则拼接搜索添加 交付批次
        if(!StringUtil.isEmpty(communityHouseAppoint.getDeliveryBatch())){
            hql.append(" and ad.deliveryBatch = ?");
            params.add(communityHouseAppoint.getDeliveryBatch());
        }
        // 不为空则拼接搜索添加 交付状态  //需要进行转换
        if(null != communityHouseAppoint.getStatus()){
            hql.append(" and ad.status = ?");
            params.add(communityHouseAppoint.getStatus());
        }
        // 不为空则拼接搜索添加 集中交付开始时间
        if(null != communityHouseAppoint.getPayStaTime()){
            hql.append(" and ad.payStaTime >= ?");
            params.add(communityHouseAppoint.getPayStaTime());
        }
        // 不为空则拼接搜索添加 集中交付结束时间
        if(communityHouseAppoint.getPayEndTime() != null){
            hql.append(" and ad.payEndTime <= ?");
            params.add(communityHouseAppoint.getPayEndTime());
        }
        //设置为查询批次
        hql.append(" and ad.type = 0 ");
        hql.append(" ORDER BY ad.operatorDate DESC");

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        communityHouseAppointEntityList =  ( List<CommunityHouseAppointEntity>)getHibernateTemplate().find(hql.toString());

        return communityHouseAppointEntityList;
    }*/

    /**
     * 保存或者更新批次管理
     * @param communityHouseAppointEntity
     * @return
     * @throws Exception
     */
    /*@Override
    public boolean saveORupdateBatchManage(CommunityHouseAppointEntity communityHouseAppointEntity) throws Exception {

        Session session = getCurrentSession();
        session.saveOrUpdate(communityHouseAppointEntity);
        session.flush();
        session.close();
        return true;
    }*/

    /**
     * 保存或者更新批次管理
     *
     * @param communityBatchManageEntity
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveBatchManage(CommunityBatchManageEntity communityBatchManageEntity) throws Exception {

        Session session = getCurrentSession();
        session.saveOrUpdate(communityBatchManageEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 添加预约单
     *
     * @param communityHouseAppointEntity
     * @return
     */
    @Override
    public boolean saveAppointOrder(CommunityHouseAppointEntity communityHouseAppointEntity) throws Exception {
        Session session = getCurrentSession();
        session.save(communityHouseAppointEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean upadteAppointOrder(CommunityHouseAppointEntity communityHouseAppointEntity) throws Exception {
        Session session = getCurrentSession();
        session.update(communityHouseAppointEntity);
        session.flush();
        session.close();
        return true;
    }


    /**
     * 更新批次管理id
     *
     * @param batchManageId
     * @param communityId
     * @return
     */
    @Override
    public boolean updateBatchManageIdAndName(String batchManageName, String batchManageId, String communityId) {
        Session session = getCurrentSession();
        Query query = session.createSQLQuery("UPDATE community_house_appoint set batch_manage_id = ?, delivery_batch = ?  WHERE pay_status = 0 AND community_id = ? AND batch_manage_id IS NULL ");
        query.setString(0, batchManageId);
        query.setString(1, batchManageName);
        query.setString(2, communityId);
        query.executeUpdate();
        session.flush();
        session.close();

        return true;
    }

    /**
     * 修改/删除交付批次管理
     *
     * @param communityBatchManageEntity
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateBatchManage(CommunityBatchManageEntity communityBatchManageEntity) throws Exception {

        Session session = getCurrentSession();
        session.update(communityBatchManageEntity);
        session.flush();
        session.close();

        return true;
    }


}
