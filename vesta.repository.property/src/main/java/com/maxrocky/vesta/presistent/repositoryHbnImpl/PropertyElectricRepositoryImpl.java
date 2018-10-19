package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyElectricEntity;
import com.maxrocky.vesta.domain.repository.PropertyElectricRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/23.
 */
@Repository
public class PropertyElectricRepositoryImpl extends HibernateDaoImpl implements PropertyElectricRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSesssion(){return sessionFactory.openSession();}

    /**
     * 分页获取电量列表
     * @param propertyElectricEntity
     * @param webPage
     * @return
     */
    @Override
    public List<PropertyElectricEntity> listElectric( PropertyElectricEntity propertyElectricEntity, WebPage webPage) {
        List<PropertyElectricEntity> propertyElectricEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from PropertyElectricEntity as p");
        hql.append(" where p.state = ?");
        params.add(PropertyElectricEntity.State.ElectricState_YES);//电量信息有效
        if(propertyElectricEntity.getHouseNum()!=null&&!"".equals(propertyElectricEntity.getHouseNum())){
            hql.append(" and p.houseNum like ?");
            params.add("%"+propertyElectricEntity.getHouseNum()+"%");//房间号不为空筛选房间号
        }
        if (propertyElectricEntity.getStaffName()!=null&&!"".equals(propertyElectricEntity.getStaffName())){//用员工姓名暂代筛选符号
            if (propertyElectricEntity.getElectricQuantity()!=null&&!"".equals(propertyElectricEntity.getElectricQuantity())){
                hql.append(" and p.electricQuantity"+propertyElectricEntity.getStaffName()+propertyElectricEntity.getElectricQuantity());
            }
        }
        if (propertyElectricEntity.getProjectId()!=null&&!"".equals(propertyElectricEntity.getProjectId())){
//            hql.append(" and p.projectId = ?");
//            params.add(propertyElectricEntity.getProjectId());//项目id
            hql.append(SqlJoiningTogether.sqlStatement("p.projectId",propertyElectricEntity.getProjectId()));
        }
        hql.append(" order by p.createOn desc,p.houseNum");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        propertyElectricEntities = (List<PropertyElectricEntity>)getHibernateTemplate().find(hql.toString());
        return propertyElectricEntities;
    }

    /**
     * 修改电量信息
     * @param propertyElectricEntity
     * @return
     */
    @Override
    public boolean updateElectric(PropertyElectricEntity propertyElectricEntity) {
        PropertyElectricEntity p = getElectricByid(propertyElectricEntity.getElectricId());
        if (p!=null){
            Session session = getCurrentSesssion();
            session.update(propertyElectricEntity);
            session.flush();
            session.close();
            return true;
        }
        return false;
    }

    /**
     * 保存电量信息
     * @param propertyElectricEntity
     * @return
     */
    @Override
    public boolean saveElectric(PropertyElectricEntity propertyElectricEntity) {
        Session session = getCurrentSesssion();
        session.save(propertyElectricEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 获取电量详情
     * @param eleId
     * @return
     */
    @Override
    public PropertyElectricEntity getElectricByid(String eleId) {
        String hql="from PropertyElectricEntity as p where p.electricId=:electricId";
        Query query = getCurrentSesssion().createQuery(hql);
        query.setParameter("electricId",eleId);
        if (query.list().size()>0){
            return (PropertyElectricEntity)query.list().get(0);
        }
        return null;
    }


    //*****************************前台接口****************************************


    public Session getSessionForInterface(){
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<PropertyElectricEntity> getPropertyEletricEnitiesByHouseIds(String houseIds) {

        if(StringUtil.isEmpty(houseIds)){
            return new ArrayList<>();
        }

        String querySql = "select " +
                " tmp.ELECTRIC_ID  as electricId ," +
                " tmp.USER_ID as userId ," +
                " tmp.HOUSE_ID as houseId , " +
                " tmp.PROJECTID  as projectId ,"+
                " tmp.ELECTRIC_QUANTITY as electricQuantity ,"+
                " tmp.CREATEON  as createOn ,"+
                " tmp.ELECTRIC_STATE as state ," +
                " tmp.HOUSE_NUM as houseNum,"+
                " tmp.USER_NAME as userName,"+
                " tmp.USERMOBILE as userMobile,"+
                " tmp.PROJECTNAME as projectName," +
                " tmp.STAFFNAME as staffName"+
                " from (select * from PROPERTY_ELECTRIC p where p.house_id in(%s) and p.ELECTRIC_STATE='yes' order by p.CREATEON desc ) tmp group by tmp.house_Id";

        querySql = String.format(querySql,houseIds);

        return this.getSessionForInterface()
                .createSQLQuery(querySql)
                .setResultTransformer(Transformers.aliasToBean(PropertyElectricEntity.class))
                .list();
    }
}
