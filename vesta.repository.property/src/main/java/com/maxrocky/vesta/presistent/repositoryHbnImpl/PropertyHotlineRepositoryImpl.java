package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyDoorEntity;
import com.maxrocky.vesta.domain.model.PropertyHotlineEntity;
import com.maxrocky.vesta.domain.repository.PropertyHotlineRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 物业服务热线数据持久层实现类
 * Created by WeiYangDong on 2016/12/14.
 */
@Repository
public class PropertyHotlineRepositoryImpl extends HibernateDaoImpl implements PropertyHotlineRepository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存或更新Entity
     * @param entity
     */
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * 删除Entity
     * @param entity
     */
    public <E> void delete(E entity){
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    /**
     * 获取物业服务热线列表数据
     * @param params 参数
     * @param webPage 分页
     * @return List<PropertyHotlineEntity>
     */
    public List<PropertyHotlineEntity> getPropertyHotlineList(Map<String,Object> params,WebPage webPage){
        StringBuilder hql = new StringBuilder("FROM PropertyHotlineEntity ph WHERE 1=1");
        List<Object> parameterList = new ArrayList<>();
        //权限范围
        Object roleScopes = params.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            hql.append(" and ph.projectCode in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+")");
        }
        //城市
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects)){
            hql.append(" and ph.projectCode in ("+cityProjects.toString().substring(0,cityProjects.toString().length()-1)+")");
        }
        //项目编码
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            hql.append(" and ph.projectCode = ? ");
            parameterList.add(projectCode);
        }
        //功能模块编码
        Object functionModuleCode = params.get("functionModuleCode");
        if (null != functionModuleCode && !"".equals(functionModuleCode) && !"0".equals(functionModuleCode)){
            hql.append(" and ph.functionModuleCode = ? ");
            parameterList.add(functionModuleCode);
        }

        hql.append("ORDER BY ph.createOn DESC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, parameterList);
        }
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = parameterList.size(); i < length; i++){
            query.setParameter(i,parameterList.get(i));
        }
        List<PropertyHotlineEntity> propertyHotlineEntityList = query.list();
        session.flush();
        session.close();
        return propertyHotlineEntityList;
    }

    /**
     * 通过主键ID查询物业服务热线信息
     * @param id 服务热线ID
     * @return PropertyHotlineEntity
     */
    public PropertyHotlineEntity getPropertyHotlineById(String id){
        StringBuilder hql = new StringBuilder("FROM PropertyHotlineEntity ph WHERE ph.id = '"+id+"'");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        PropertyHotlineEntity propertyHotlineEntity = (PropertyHotlineEntity) query.uniqueResult();
        session.flush();
        session.close();
        return propertyHotlineEntity;
    }
}
