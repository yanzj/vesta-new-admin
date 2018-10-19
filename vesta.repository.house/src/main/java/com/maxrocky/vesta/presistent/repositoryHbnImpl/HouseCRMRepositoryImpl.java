package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseCRMEntity;
import com.maxrocky.vesta.domain.repository.HouseCRMRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/13.
 */
@Repository
public class HouseCRMRepositoryImpl extends HibernateDaoImpl implements HouseCRMRepository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据业主房屋关系Id获取业主房屋关系信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:44:52
     */
    @Override
    public HouseCRMEntity get(String houseId) {
        return (HouseCRMEntity)getCurrentSession().get(HouseCRMEntity.class, houseId);
    }

    /**
     * Describe:根据用户Id获取用户下的房产
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:48:07
     */
    @Override
    public List<HouseCRMEntity> getByUserId(String userId) {
        String hql = "SELECT h FROM HouseCRMEntity AS h,HouseUserBookEntity AS hub WHERE h.id = hub.houseId AND hub.userId = :userId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }

    /**
     * Describe:创建房产信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:01:38
     */
    @Override
    public void create(HouseCRMEntity HouseCRMEntity) {
        Session session = getCurrentSession();
        session.save(HouseCRMEntity);
        session.flush();
    }

    /**
     * Describe:根据用户Id、项目Id获取房产列表
     * CreateBy:Tom
     * CreateOn:2016-01-20 11:54:52
     */
    @Override
    public List<HouseCRMEntity> getListByUserIdAndProjectId(String userId, String projectId) {
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT hi FROM HouseCRMEntity AS hi,HouseUserBookEntity AS hub WHERE 1 = 1 ");
        hql.append(" AND hub.houseId = hi.id ");
        hql.append(" AND hub.userId = :userId ");
//        hql.append(" AND hub.userType = :userType ");
        if(!StringUtil.isEmpty(projectId)){
            hql.append(" AND hi.projectId = '" + projectId + "' ");
        }
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("userId", userId);
//        query.setParameter("userType", HouseUserBookEntity.USER_TYPE_OWNER);

        return query.list();
    }

    /**
     * Describe:根据物业房产Id获取业主房产
     * CreateBy:Tom
     * CreateOn:2016-01-21 02:21:24
     */
    @Override
    public HouseCRMEntity getByViewAppHouseId(int viewAppHouseId) {
        String hql = "FROM HouseCRMEntity WHERE viewAppHouseId = :viewAppHouseId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("viewAppHouseId", viewAppHouseId);
        List<HouseCRMEntity> HouseCRMEntityList = query.list();
        if(HouseCRMEntityList.size() == 0){
            return null;
        }
        return HouseCRMEntityList.get(0);
    }

    /**
     * 根据不同条件查找房产
     * 分页分条件
     * @param HouseCRMEntity
     * @return
     */
    @Override
    public List<HouseCRMEntity> listHouseInfo(HouseCRMEntity HouseCRMEntity,WebPage webPage) {

        List<HouseCRMEntity> houseInfoEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from HouseCRMEntity as h where 1 = 1");
        //如果公司id不为空则拼接公司
        if (HouseCRMEntity.getCompanyId()!=null&&!"0".equals(HouseCRMEntity.getCompanyId())){
            hql.append("and h.companyId = ?");
            params.add(HouseCRMEntity.getCompanyId());
        }
        //如果项目id不为空则拼接项目
        if (HouseCRMEntity.getProjectId()!=null&&!"0".equals(HouseCRMEntity.getProjectId())){
            hql.append("and h.projectId = ?");
            params.add(HouseCRMEntity.getProjectId());
        }
        //如果楼不为空则拼楼
        if (HouseCRMEntity.getBuildingId()!=null&&!"0".equals(HouseCRMEntity.getBuildingId())){
            hql.append("and h.buildingId = ?");
            params.add(HouseCRMEntity.getBuildingId());
        }
        //如果单元不为空则拼单元
        if (HouseCRMEntity.getUnitId()!=null&&!"0".equals(HouseCRMEntity.getUnitId())){
            hql.append("and h.unitId = ?");
            params.add(HouseCRMEntity.getUnitId());
        }
        //如果房间不为空则拼房间
        if (HouseCRMEntity.getRoomId()!=null&&!"0".equals(HouseCRMEntity.getRoomId())){
            hql.append("and h.roomId = ?");
            params.add(HouseCRMEntity.getRoomId());
        }

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        houseInfoEntities = (List<HouseCRMEntity>)getHibernateTemplate().find(hql.toString());

        return houseInfoEntities;
    }

    @Override
    public HouseCRMEntity getHouseCRMEntityByHouseId(String houseId) {

        String queryHql = "from HouseCRMEntity where viewAppHouseId=%s";
        queryHql = String.format(queryHql,houseId);
        List<HouseCRMEntity> houseInfoEntities = this.getCurrentSession().createQuery(queryHql).setFirstResult(0).setMaxResults(1).list();
        if(houseInfoEntities == null || houseInfoEntities.size() == 0){
            return null;
        }
        return houseInfoEntities.get(0);
    }

    @Override
    public List<HouseCRMEntity> houseInfoByProjectId(String projectId,String building,String formatId) {
        String hql = "FROM HouseCRMEntity as h WHERE h.projectId =:projectId and buildingId =:building  and formatId=:formatId  order by h.buildingName,h.unitName,h.roomName ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        query.setParameter("building", building);
        query.setParameter("formatId",formatId);
        return query.list();
    }

    @Override
    public List<HouseCRMEntity> getHouseTypeList() {
        String hql = "from HouseCRMEntity";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    /**
     * 根据项目，业态，楼号，单元，房号获取房产信息
     * @param HouseCRMEntity
     * @return
     */
    @Override
    public HouseCRMEntity getHouseInfo(HouseCRMEntity HouseCRMEntity) {
        String hql = "from HouseCRMEntity as h where 1=1";
        if (HouseCRMEntity.getProjectId()!=null&&!"".equals(HouseCRMEntity.getProjectId())){
            hql = hql +" and h.projectId = '"+HouseCRMEntity.getProjectId() + "'";
        }
        if (HouseCRMEntity.getFormatId()!=null&&!"".equals(HouseCRMEntity.getFormatId())){
            hql = hql +" and h.formatId = '"+HouseCRMEntity.getFormatId() + "'";
        }
        if (HouseCRMEntity.getBuildingId()!=null&&!"".equals(HouseCRMEntity.getBuildingId())){
            hql = hql+" and h.buildingId = '"+HouseCRMEntity.getBuildingId() + "'";
        }
        if (HouseCRMEntity.getUnitId()!=null&&!"".equals(HouseCRMEntity.getUnitId())){
            hql = hql +" and h.unitId = '"+HouseCRMEntity.getUnitId() + "'";
        }
        if (HouseCRMEntity.getRoomId()!=null&&!"".equals(HouseCRMEntity.getRoomId())){
            hql = hql +" and h.roomId = '"+HouseCRMEntity.getRoomId() + "'";
        }
        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size()>0){
            return (HouseCRMEntity)query.list().get(0);
        }

        return null;
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改房产信息
     * ModifyBy:
     */
    @Override
    public void updateHouseInfo(HouseCRMEntity houseCRMEntity) {
        Session session = getCurrentSession();
        session.update(houseCRMEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据会员编号获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public HouseCRMEntity getByMemberId(String id,String memberId) {
        String hql="FROM HouseCRMEntity WHERE id='"+id+"' and memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseCRMEntity> houseCRMList=query.list();
        if(houseCRMList.size()>0){
            return houseCRMList.get(0);
        }
        return null;
    }
}
