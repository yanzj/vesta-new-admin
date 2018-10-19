package com.maxrocky.vesta.application.service;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/10
 * Time: 13:48
 * Describe:
 */
public interface BaseService<T> {
    //获取一条记录
    public  T get(Class<T> entityClass, Serializable id);
    //新增和修改保存
    public  void saveOrUpdate(T entity);

    //批量新增和修改保存
    public  void saveOrUpdateAll(Collection<T> entitys);

    //单条删除，按id
    public  void deleteById(Class<T> entityClass, Serializable id);
    //批量删除
    public  void delete(Class<T> entityClass, Serializable[] ids);
    //通过对象删除
    public void delete(T model);
}
