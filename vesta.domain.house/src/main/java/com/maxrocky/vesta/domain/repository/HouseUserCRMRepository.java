package com.maxrocky.vesta.domain.repository;

/**
 * 房产业主关系Dao
 * Created by WeiYangDong on 2017/3/1.
 */
public interface HouseUserCRMRepository {

    /**
     * 保存或更新Entity
     * @param entity
     */
    public <T> void saveOrUpdate(T entity);

    /**
     * 删除Entity
     * @param entity
     */
    public <E> void delete(E entity);

    /**
     * 通过memberId删除房产业主关系数据
     * @param memberId CRM业主Id
     */
    void delHouseUserByMemberId(String memberId);

    /**
     * 更新HouseUserCrm 地址，项目，业主姓名等信息
     * @param memberId
     */
    public void updateHouseUserCrm(String memberId);

}
