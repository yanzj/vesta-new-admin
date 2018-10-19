package com.maxrocky.vesta.hibernate;


import com.maxrocky.vesta.taglib.page.WebPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JillChen on 2015/12/15.
 */
public interface IHibernateDao {

    public <E> List<E> find(String queryString);

    public <E> List<E> find(Class<E> bean);
    /**
     * 批量修改或删除
     */
    public int bulkUpdate(String queryString, Object[] values);
    /**
     * 批量删除
     */
    public <E> void deleteAll(Collection<E> entities);

    public List<?> find(String queryString, Object[] values);
    /**
     * 获取唯一实体
     */
    public <E> E findUniqueEntity(String queryString, Object... params);

    /**
     * 多条件分页查询
     */
    public <E> List<E> findByPage(String queryString, Integer startRow,
                                  Integer pageSize, Object... params);

    public <E> List<E> findByNamedQuery(String queryName);


    public <E> List<E> findByNamedQuery(String queryName, Object... values);

    /**
     * 获取单个实体
     */
    public <E> E get(Class<E> entityClass, Serializable id);

    public <E> Iterator<E> iterate(String queryString);

    /**
     */
    public <E> Iterator<E> iterate(String queryString, Object... values);

    /**
     */
    public <E> E load(Class<E> entityClass, Serializable id);

    /**
     * 持久化一个对象
     */
    public <E> void persist(E entity);

    /**
     * 刷新一个对象
     */
    public <E> void refresh(E entity);

    /**
     * 保存一个对象
     */
    public <E> Serializable save(E entity);

    /**
     * 保存一个集合
     */
    public <E> void saveAll(Collection<E> entities);

    /**
     * 保存或修改一个实体
     */
    public <E> void saveOrUpdate(E entity);

    /**
     * 保存或修改一个集合
     */
    public <E> void saveOrUpdateAll(Collection<E> entities);

    /**
     * 修改一个实体
     */
    public <E> void update(E entity);

    /**
     * 修改一个集合
     */
    public <E> void updateAll(Collection<E> entities);

    /**
     * id对应的对象是否存在
     */
    public <E> boolean exist(Class<E> c, Serializable id);

    /**
     * 统计总条数
     */
    public Integer count(String hql);

    /**
     * 根据条件统计总条数
     */
    public Integer count(String hql, Object... obj);

    /**
     * 多条件分页查询
     * @param queryString HQL语句
     * @param params   参数集合
     * @return 分页查询结果
     * @see #findByPage(String, Integer, Integer, Object...)
     */
    public <E> List<E> findByPage(String queryString, WebPage pageModel, List<?> params);

    <E> List<E> findByPageGroup(String queryString, WebPage pageModel,List<?> params);

}
