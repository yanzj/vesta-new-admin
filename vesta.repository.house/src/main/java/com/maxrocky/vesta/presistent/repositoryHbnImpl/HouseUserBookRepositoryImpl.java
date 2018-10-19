package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.HouseUserBookEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.repository.HouseUserBookRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2016/1/19 10:06.
 * Describe:房屋业主关系Repository接口实现类
 */
@Repository
public class HouseUserBookRepositoryImpl  extends HibernateDaoImpl implements HouseUserBookRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:创建房屋业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:07:08
     */
    @Override
    public void create(HouseUserBookEntity houseUserBookEntity) {
        Session session = getCurrentSession();
        session.save(houseUserBookEntity);
        session.flush();
    }

    /**
     * Describe:根据房产Id查询房产业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 05:42:06
     */
    @Override
    public List<HouseUserBookEntity> getListByHouseId(String houseId) {
        String hql = "FROM HouseUserBookEntity WHERE houseId = :houseId AND state = :state";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("houseId", houseId);
        query.setParameter("state", HouseUserBookEntity.STATE_NORMAL);

        return query.list();
    }

    /**
     * Describe:根据用户Id、项目Id查询房产业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 06:38:54
     */
    @Override
    public List<Object[]> getObjectListByUserIdAndProjectId(String userId, String projectId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" SELECT u.id,u.userId,u.userType,h.address ");
        hql.append(" FROM HouseUserBookEntity AS u,HouseInfoEntity h ");
        hql.append(" WHERE u.houseId = h.id ");
        hql.append(" AND h.id IN ( ");
        hql.append(" SELECT hi.id FROM HouseUserBookEntity AS hub,HouseInfoEntity AS hi ");
        hql.append(" WHERE hub.houseId = hi.id  ");
        hql.append(" AND hi.projectId = :projectId ");
        hql.append(" AND hub.userId = :userId ");
        hql.append(" AND hub.userType = :userType ");
        hql.append(" AND hub.state = :state) ");
        hql.append(" AND u.state = :state ");

        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("projectId", projectId);
        query.setParameter("userId", userId);
        query.setParameter("userType", HouseUserBookEntity.USER_TYPE_OWNER);
        query.setParameter("state", HouseUserBookEntity.STATE_NORMAL);

        List<Object[]> objects = query.list();
        return objects;
    }

    /**
     * Describe:根据id获取房产业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 08:47:07
     */
    @Override
    public HouseUserBookEntity get(String houseUserId) {
        return (HouseUserBookEntity)getCurrentSession().get(HouseUserBookEntity.class, houseUserId);
    }

    /**
     * Describe:验证房产下业主
     * CreateBy:Tom
     * CreateOn:2016-01-20 08:53:05
     */
    @Override
    public Boolean checkOwner(String userId, String houseId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseUserBookEntity WHERE 1 = 1 ");
        hql.append(" AND userId = :userId ");
        hql.append(" AND houseId = :houseId ");
        hql.append(" AND userType = :userType ");
        hql.append(" AND state = :state ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("userId", userId);
        query.setParameter("houseId", houseId);
        query.setParameter("userType", HouseUserBookEntity.USER_TYPE_OWNER);
        query.setParameter("state", HouseUserBookEntity.STATE_NORMAL);

        List<HouseUserBookEntity> houseUserBookEntityList = query.list();
        if(houseUserBookEntityList.size() > 0){
            return true;
        }

        return false;
    }

    /**
     * Describe:修改房产成员关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 09:04:34
     */
    @Override
    public void update(HouseUserBookEntity houseUserBookEntity) {
        Session session = getCurrentSession();
        session.update(houseUserBookEntity);
        session.flush();
    }

    /**
     * Describe:根据用户Id获取房产成员关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 09:08:56
     */
    @Override
    public List<HouseUserBookEntity> getListByUserId(String userId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseUserBookEntity WHERE 1 = 1 ");
        hql.append(" AND userId = :userId ");
        hql.append(" AND state = :state ");
        hql.append(" ORDER BY userType ASC ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("userId", userId);
        query.setParameter("state", HouseUserBookEntity.STATE_NORMAL);

        return query.list();
    }

    /**
     * Describe:除了房产Id获取当前用户最高状态
     * CreateBy:Tom
     * CreateOn:2016-01-20 09:19:23
     */
    @Override
    public String getUserType(String userId, String houseId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseUserBookEntity WHERE 1 = 1 ");
        hql.append(" AND userId = :userId ");
        hql.append(" AND state = :state ");
        hql.append(" AND houseId <> :houseId ");
        hql.append(" ORDER BY userType ASC ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("userId", userId);
        query.setParameter("state", HouseUserBookEntity.STATE_NORMAL);
        query.setParameter("houseId", houseId);

        List<HouseUserBookEntity> houseUserBookEntityList = query.list();
        if(houseUserBookEntityList.size() > 0){
            return houseUserBookEntityList.get(0).getUserType();
        }

        return "1";
    }

    /**
     * Describe:根据用户Id、房产Id获取房产成员关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:53:23
     */
    @Override
    public HouseUserBookEntity getByUserIdAndHouseId(String userId, String houseId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseUserBookEntity WHERE 1 = 1 ");
        hql.append(" AND userId = :userId ");
        hql.append(" AND state = :state ");
        hql.append(" AND houseId = :houseId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("userId", userId);
        query.setParameter("state", HouseUserBookEntity.STATE_NORMAL);
        query.setParameter("houseId", houseId);

        List<HouseUserBookEntity> houseUserBookEntityList = query.list();
        if(houseUserBookEntityList.size() == 0){
            return null;
        }

        return houseUserBookEntityList.get(0);
    }

    /**
     * Describe:根据用户Id、房产Id获取房产成员关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:52:49
     */
    @Override
    public HouseUserBookEntity getUserByUserIdAndHouseId(String userId, String houseId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseUserBookEntity WHERE 1 = 1 ");
        hql.append(" AND userId = :userId ");
        hql.append(" AND state = :state ");
        hql.append(" AND houseId = :houseId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("userId", userId);
        query.setParameter("state", HouseUserBookEntity.STATE_REMOVE);
        query.setParameter("houseId", houseId);

        List<HouseUserBookEntity> houseUserBookEntityList = query.list();
        if(houseUserBookEntityList.size() == 0){
            return null;
        }

        return houseUserBookEntityList.get(0);
    }

    /**
     * 获取房产下某个状态的用户
     * @param houseId
     * @param userType
     * @return
     */
    @Override
    public List<HouseUserBookEntity> listUser(String houseId, String userType) {
        String hql = "from HouseUserBookEntity as h where h.houseId = :houseId and userType=:userType and state=:state group by userId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("houseId",houseId);
        query.setParameter("userType",userType);
        query.setParameter("state",HouseUserBookEntity.STATE_NORMAL);
        List<HouseUserBookEntity> users = query.list();
        return users;
    }

    public List<Object> listUsers(HouseInfoEntity houseInfoEntity,HouseUserBookEntity houseUserBookEntity,UserInfoEntity userInfoEntity,WebPage webPage){

        List<Object> list = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from HouseInfoEntity as h ,HouseUserBookEntity as hu ,UserInfoEntity as u where h.id = hu.houseId and hu.userId= u.userId");
        //拼接关系正常
        hql.append(" and hu.state = ? ");
        params.add(HouseUserBookEntity.STATE_NORMAL);
//
        //如果公司id不为空则拼接公司
        if (houseInfoEntity.getCompanyId()!=null&&!"0".equals(houseInfoEntity.getCompanyId())){
            hql.append(" and h.companyId = ? ");
            params.add(houseInfoEntity.getCompanyId());
        }
        //如果项目id不为空则拼接项目
        if (houseInfoEntity.getProjectId()!=null&&!"0".equals(houseInfoEntity.getProjectId())){
//            hql.append(" and h.projectId = ? ");
//            params.add(houseInfoEntity.getProjectId());
            hql.append(SqlJoiningTogether.sqlStatement("h.projectId", houseInfoEntity.getProjectId()));
        }
        //如果楼不为空则拼楼
        if (houseInfoEntity.getBuildingId()!=null&&!"0".equals(houseInfoEntity.getBuildingId())){
            hql.append(" and h.buildingId = ? ");
            params.add(houseInfoEntity.getBuildingId());
        }
        //如果单元不为空则拼单元
        /*if (houseInfoEntity.getUnitId()!=null&&!"0".equals(houseInfoEntity.getUnitId())){
            hql.append(" and h.unitId = ? ");
            params.add(houseInfoEntity.getUnitId());
        }*/
        //如果房间不为空则拼房间
        if (houseInfoEntity.getRoomId()!=null&&!"0".equals(houseInfoEntity.getRoomId())){
            hql.append(" and h.roomId = ? ");
            params.add(houseInfoEntity.getRoomId());
        }
//        if (userInfoEntity.getUserId()!=null&&!"".equals(userInfoEntity.getUserId())){
//            hql.append(" and u.userId = ? ");
//            params.add(userInfoEntity.getUserId());
//        }
        //拼用户名
        if (userInfoEntity.getUserName()!=null&&!"".equals(userInfoEntity.getUserName())){
            hql.append(" and u.userName like ? ");
            params.add("%"+userInfoEntity.getUserName()+"%");
        }
        //业主姓名
        if (userInfoEntity.getRealName()!=null&&!"".equals(userInfoEntity.getRealName())){
            hql.append(" and u.realName like ? ");
            params.add("%"+userInfoEntity.getRealName()+"%");
        }
        //业主联系方式
        if (userInfoEntity.getMobile()!=null&&!"".equals(userInfoEntity.getMobile())){
            hql.append(" and u.mobile like ? ");
            params.add("%"+userInfoEntity.getMobile()+"%");
        }
        //拼注册开始时间，假定赋值给CreateTime
        if (userInfoEntity.getCreateBy()!=null&&!"".equals(userInfoEntity.getCreateBy())){
            hql.append(" and u.registerDate>= ? ");
            params.add(DateUtils.parse(userInfoEntity.getCreateBy()+" 00:00:00"));
        }
        //拼注册结束时间，假定赋值给ModifyTime
        if (userInfoEntity.getModifyBy()!=null&&!"".equals(userInfoEntity.getModifyBy())){
            hql.append(" and u.registerDate<= ? ");
            params.add(DateUtils.parse(userInfoEntity.getModifyBy()+" 23:59:59"));
        }
        if (houseUserBookEntity.getUserType()!=null){
            if (houseUserBookEntity.getUserType().equals(HouseUserBookEntity.USER_TYPE_FAMILY+HouseUserBookEntity.USER_TYPE_TENANT)){
                hql.append(" and hu.userType between ? ");
                params.add(HouseUserBookEntity.USER_TYPE_FAMILY);
                hql.append(" and ? ");
                params.add(HouseUserBookEntity.USER_TYPE_TENANT);
            }else {
                hql.append(" and hu.userType = ? ");
                params.add(houseUserBookEntity.getUserType());
            }
        }
        hql.append(" order by u.modifyOn desc");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        list = (List<Object>)getHibernateTemplate().find(hql.toString());

        return list;
    }

    /**
     * 删除家属租户关系
     * @param houseUserid
     * @return
     */
    @Override
    public boolean delBookById(String houseUserid) {
        HouseUserBookEntity houseUserBookEntity = get(houseUserid);
        if (houseUserBookEntity!=null){
            Session session = getCurrentSession();
            session.delete(houseUserBookEntity);
            session.flush();
            session.close();
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<Object[]> getEstates(String userId, String projectId) {
        String hql = "select b.houseId,a.address from HouseInfoEntity as a,HouseUserBookEntity as b " +
                "where a.id = b.houseId and b.userId=:userId and a.projectId=:projectId and b.state='"+HouseUserBookEntity.STATE_NORMAL+"'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setParameter("projectId",projectId);
        List objects = query.list();
        return objects;
    }

    @Override
    public String maxUserType(String userId) {
        String hql = "from HouseUserBookEntity as h where h.userId = :userId and h.state = :state order by h.userType";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setParameter("state",HouseUserBookEntity.STATE_NORMAL);
        List<HouseUserBookEntity> houseUserBookEntities = query.list();
        if (houseUserBookEntities.size()>0){
            return houseUserBookEntities.get(0).getUserType();
        }
        return "1";
    }

    @Override
    public List<HouseInfoEntity> getHouseUserBookByUserIdAndProjcetId(String userId, String projectId) {

        String queryHql = "from HouseInfoEntity where id in (select houseId from HouseUserBookEntity where userId='%s' and state='NORMAL') and projectId='%s'";
        queryHql = String.format(queryHql,userId,projectId);
        return this.getCurrentSession().createQuery(queryHql).list();
    }

    /**
     * Describe:根据id获取房产业主关系
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 08:42:46
     */
    @Override
    public HouseUserBookEntity getByHouseId(String houseId) {
        return (HouseUserBookEntity)getCurrentSession().get(HouseUserBookEntity.class, houseId);
    }
}
