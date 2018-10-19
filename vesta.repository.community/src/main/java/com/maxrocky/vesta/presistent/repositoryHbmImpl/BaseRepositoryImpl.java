package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.repository.BaseRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by wangyifan on 2016/5/09.
 */
@Repository
public class BaseRepositoryImpl extends HibernateDaoImpl implements BaseRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    //带条件查询
    @Override
    public <T> List<T> find(String hql, Class<T> entityClass, Object[] params) {
        Session session = this.getCurrentSession();
        Query query = session.createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        List<T> list = query.list();
        session.flush();
        session.close();
        return list;
    }

    //获取一条，根据主键id
    @Override
    public <T> T get(Class<T> entityClass, Serializable id) {
        Session session = this.getCurrentSession();
        T t = (T) session.get(entityClass, id);
        session.flush();
        session.close();
        return t;
    }

    //新增和修改，hibernate根据id是否为null自动判断
    @Override
    public <T> void saveOrUpdate(T entity) {
        //this.getCurrentSession().clear();
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    //集合保存，这时新增还是修改，就自动判断，调用时是否简洁。适合批量新增和修改时。（Mrecord控件）
    @Override
    public <T> void saveOrUpdateAll(Collection<T> entitys) {
        Session session = this.getCurrentSession();
        for (T entity : entitys) {
            session.saveOrUpdate(entity);//为什么hibernate批量操作时，要用循环一条一条记录去更新？
        }
        session.flush();
        session.close();
    }

    //按主键id删除
    @Override
    public <T> void deleteById(Class<T> entityClass, Serializable id) {
        Session session = this.getCurrentSession();
        session.delete(get(entityClass, id));
        session.flush();
        session.close();
    }

    //批量删除
    @Override
    public <T> void delete(Class<T> entityClass, Serializable[] ids) {
        for (Serializable s : ids) {
            deleteById(entityClass, s);
        }
    }

    /**
     * 取得最大值
     * @param hql
     * @param params
     * @return
     */
    @Override
    public Integer getMax(String hql, Object[] params) {
        Session session = this.getCurrentSession();
        Query query = session.createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        session.flush();
        session.close();
        return (Integer) query.uniqueResult();
    }


}
