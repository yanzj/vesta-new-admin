package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.CommunityBatchManageEntity;
import com.maxrocky.vesta.domain.model.CommunityHouseAppointEntity;
import com.maxrocky.vesta.hibernate.IHibernateDao;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.io.Serializable;
import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 9:46
 */
public interface CommunityHouseBatchRespository extends IHibernateDao {
    public <E> E get(Class<E> entityClass, Serializable id);
    public <E> List<E> find(String queryString);
    public <E> List<E> find(Class<E> bean);
    public List<?> find(String queryString, Object[] values);
    public <E> E findUniqueEntity(final String queryString,
                                  final Object... params);
    public <E> Serializable save(E entity);

    //分页查询所有交付批次信息
    public List<CommunityBatchManageEntity> queryAllDeliveryBatch(CommunityBatchManageEntity communityBatchManageEntity, WebPage webPage) throws Exception;
    //public List<CommunityHouseAppointEntity> queryAllDeliveryBatch(CommunityHouseAppointEntity communityHouseAppoint,WebPage webPage) throws Exception;

    //保存或者更新批次管理
    //public boolean saveORupdateBatchManage(CommunityHouseAppointEntity communityHouseAppointEntity) throws Exception;
    public boolean saveBatchManage(CommunityBatchManageEntity communityBatchManageEntity) throws Exception;
    //批量修改预约单批次管理
    public boolean updateBatchManageIdAndName(String batchManageName, String batchManageId, String communityId);
    //更新/删除支付批次
    public boolean updateBatchManage(CommunityBatchManageEntity communityBatchManageEntity) throws Exception;

    //添加预约单
    public boolean saveAppointOrder(CommunityHouseAppointEntity communityHouseAppointEntity) throws Exception;
    //修改预约单
    public boolean upadteAppointOrder(CommunityHouseAppointEntity communityHouseAppointEntity) throws Exception;

}
