package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.CommunityBatchManageEntity;
import com.maxrocky.vesta.domain.model.CommunityHouseAppointEntity;
import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.repository.CommunityHouseAppointRespository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2016/4/14 0:07.
 * Describe:
 */
@Repository
public class CommunityHouseAppointRespositoryImpl extends HibernateDaoImpl implements CommunityHouseAppointRespository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /* 查询交付预约列表 */
    @Override
    public List<CommunityHouseAppointEntity> getList(CommunityHouseAppointEntity communityHouseAppointEntity ,WebPage webPage) {

        StringBuffer hql = new StringBuffer("FROM CommunityHouseAppointEntity");
        List<Object> params = new ArrayList<Object>();

        if(!StringUtil.isEmpty(communityHouseAppointEntity.getCommunityId())
                && !StringUtil.isEqual(communityHouseAppointEntity.getCommunityId(), "0")){
            hql.append(" AND communityId = ?");
            params.add(communityHouseAppointEntity.getCommunityId());
        }
        if(communityHouseAppointEntity.getStatus() != null
                && communityHouseAppointEntity.getStatus() != 999){
            hql.append(" AND status = ?");
            params.add(communityHouseAppointEntity.getStatus());
        }
        if(!StringUtil.isEmpty(communityHouseAppointEntity.getDeliveryBatch())){
            hql.append(" AND deliveryBatch = ?");
            params.add(communityHouseAppointEntity.getDeliveryBatch());
        }

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<CommunityHouseAppointEntity> communityHouseAppointEntityList = (List<CommunityHouseAppointEntity>)getHibernateTemplate().find(hql.toString());
        return communityHouseAppointEntityList;
    }




    @Override
    public List<Object> getUserAppoints(CommunityHouseAppointEntity communityHouseAppointEntity, UserInfoEntity userInfoEntity, HouseInfoEntity houseInfoEntity, WebPage webPage) {
        List<Object> appoints = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from CommunityHouseAppointEntity as c,UserInfoEntity as u,HouseInfoEntity as h where c.userId = u.userId and c.houseInfoId = h.id");
        //项目筛选
        if (communityHouseAppointEntity.getCommunityId()!=null&&!"0".equals(communityHouseAppointEntity.getCommunityId())){
            hql.append(" and PROJECT_ID = ?");
            params.add(communityHouseAppointEntity.getCommunityId());
        }
        //楼号筛选
        if (houseInfoEntity.getBuildingId()!=null&&!"0".equals(houseInfoEntity.getBuildingId())){
            hql.append(" and h.buildingId = ?");
            params.add(houseInfoEntity.getBuildingId());
        }
        //单元筛选
        /*if (houseInfoEntity.getUnitId()!=null&&!"0".equals(houseInfoEntity.getUnitId())){
            hql.append(" and UNIT_ID = ?");
            params.add(houseInfoEntity.getUnitId());
        }*/
        //批次筛选
        if (communityHouseAppointEntity.getDeliveryBatch()!=null&&!"".equals(communityHouseAppointEntity.getDeliveryBatch())){
            hql.append(" and c.deliveryBatch = ?");
            params.add(communityHouseAppointEntity.getDeliveryBatch());
        }
        //时间段筛选
        if (communityHouseAppointEntity.getAmOrPm()!=null&&!"".equals(communityHouseAppointEntity.getAmOrPm())){
            hql.append(" and c.amOrPm = ?");
            params.add(communityHouseAppointEntity.getAmOrPm());
        }
        //手机号筛选
        if (userInfoEntity.getMobile()!=null&&!"".equals(userInfoEntity.getMobile())){
            hql.append(" and mobile = ?");
            params.add(userInfoEntity.getMobile());
        }
        hql.append(" and c.appointStatus = 1 ");

        //业主预约时间
        //先不写

        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        appoints = (List<Object>) getHibernateTemplate().find(hql.toString());



        return appoints;
    }

    @Override
    public CommunityHouseAppointEntity propertyHousePay(String housepayID) {

        return (CommunityHouseAppointEntity)getCurrentSession().get(CommunityHouseAppointEntity.class, housepayID);

    }

    @Override
    public boolean delPropertyHousePay(CommunityHouseAppointEntity communityHouseAppointEntity) {
        if(null != communityHouseAppointEntity){
            Session session = getCurrentSession();
            session.delete(communityHouseAppointEntity);
            session.flush();
            return true;
        }
        return false;
    }
}
