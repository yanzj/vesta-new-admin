package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.domain.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/10
 * Time: 13:53
 * Describe:
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Qualifier("baseRepositoryImpl")
    @Autowired
    public BaseRepository baseRepository;

    @Override
    public T get(Class<T> entityClass, Serializable id) {
        return this.baseRepository.get(entityClass,id);
    }

    @Override
    public void saveOrUpdate(T entity) {
        this.baseRepository.saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdateAll(Collection<T> entitys) {
        this.baseRepository.saveOrUpdateAll(entitys);
    }

    @Override
    public void deleteById(Class<T> entityClass, Serializable id) {
        this.deleteById(entityClass, id);
    }

    @Override
    public void delete(Class<T> entityClass, Serializable[] ids) {
        this.delete(entityClass, ids);
    }

    @Override
    public void delete(T model) {
        this.delete(model);
    }
}
