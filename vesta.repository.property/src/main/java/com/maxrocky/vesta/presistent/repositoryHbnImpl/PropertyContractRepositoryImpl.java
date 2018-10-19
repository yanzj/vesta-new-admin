package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyContractEntity;
import com.maxrocky.vesta.domain.model.PropertyOwnerEntity;
import com.maxrocky.vesta.domain.repository.PropertyContractRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/3/8 16:56
 * @deprecated 合同查询模块Dao实现类
 */
@Repository
public class PropertyContractRepositoryImpl extends HibernateDaoImpl implements PropertyContractRepository{

    @Autowired
    @Qualifier("sessionFactory")
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
     * 获取合同管理列表
     * @param params 参数
     * @param webPage 分页
     * @return List<PropertyContractEntity>
     */
    public List<PropertyContractEntity> getPropertyContractList(Map<String,Object> params, WebPage webPage){
        StringBuilder hql = new StringBuilder("FROM PropertyContractEntity p WHERE 1=1");
        List<Object> parameterList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //权限范围
        Object roleScopes = params.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            hql.append(" and p.projectCode in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+")");
        }
        //城市
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects)){
            hql.append(" and p.projectCode in ("+cityProjects.toString().substring(0,cityProjects.toString().length()-1)+")");
        }
        //项目编码
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            hql.append(" and p.projectCode = ? ");
            parameterList.add(projectCode);
        }
        //地块编码
        Object blockCode = params.get("blockCode");
        if (null != blockCode && !"".equals(blockCode) && !"0".equals(blockCode)){
            hql.append(" and p.blockCode = ? ");
            parameterList.add(blockCode);
        }
        //楼栋编码
        Object buildingNum = params.get("buildingNum");
        if (null != buildingNum && !"".equals(buildingNum) && !"0".equals(buildingNum)){
            hql.append(" and p.buildingNum = ? ");
            parameterList.add(buildingNum);
        }
        //单元编码
        Object unitCode = params.get("unitCode");
        if (null != unitCode && !"".equals(unitCode) && !"0".equals(unitCode)){
            hql.append(" and p.unitCode = ? ");
            parameterList.add(unitCode);
        }
        //房间号
        Object roomName = params.get("roomName");
        if (null != roomName && !"".equals(roomName)){
            hql.append(" and p.roomName = ? ");
            parameterList.add(roomName);
        }
        //业主姓名
        Object ownerName = params.get("ownerName");
        if (null != ownerName && !"".equals(ownerName)){
            hql.append(" and p.ownerName like ? ");
            parameterList.add("%"+ownerName+"%");
        }
        //合同类型
        Object propertyType = params.get("propertyType");
        if (null != propertyType && !"".equals(propertyType)){
            hql.append(" and p.propertyType = ? ");
            parameterList.add(propertyType);
        }
        //办理状态
        Object handleStatus = params.get("handleStatus");
        if (null != handleStatus && !"".equals(handleStatus)){
            hql.append(" and p.handleStatus = ? ");
            parameterList.add(handleStatus);
        }
        hql.append(" ORDER BY p.createOn desc ");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, parameterList);
        }
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = parameterList.size(); i < length; i++){
            query.setParameter(i,parameterList.get(i));
        }
        List<PropertyContractEntity> propertyEntities = query.list();
        return propertyEntities;
    }

    /**
     * 通过房产编码获取合同信息
     * @param roomNum 房屋编码
     * @return List<PropertyContractEntity>
     */
    public List<PropertyContractEntity> getPropertyContractByRoomNum(String roomNum,String propertyType){
        StringBuilder hql = new StringBuilder("FROM PropertyContractEntity p WHERE p.roomNum = ? and p.propertyType = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, roomNum).setParameter(1,propertyType);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }
}
