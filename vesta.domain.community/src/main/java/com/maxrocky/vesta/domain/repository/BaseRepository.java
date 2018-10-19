package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.hibernate.IHibernateDao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by wangyifan on 2016/4/22.
 */
public interface BaseRepository extends IHibernateDao {

    //查询所有，带条件查询
    public <T> List<T> find(String hql, Class<T> entityClass, Object[] params);

    //获取一条记录
    public <T> T get(Class<T> entityClass, Serializable id);

    //新增和修改保存
    public <T> void saveOrUpdate(T entity);

    //批量新增和修改保存
    public <T> void saveOrUpdateAll(Collection<T> entitys);

    //单条删除，按id
    public <T> void deleteById(Class<T> entityClass, Serializable id);

    //批量删除
    public <T> void delete(Class<T> entityClass, Serializable[] ids);

    //获取最大值
    public Integer getMax(String hql, Object[] params);



}
