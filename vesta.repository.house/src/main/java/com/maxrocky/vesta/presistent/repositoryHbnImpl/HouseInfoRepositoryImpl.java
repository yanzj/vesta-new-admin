package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.HouseUserCRMEntity;
import com.maxrocky.vesta.domain.model.RoomLocationEntititly;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.repository.HouseInfoRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tom on 2016/1/14 21:41.
 * Describe:业主房屋关系Repository接口实现类
 */
@Repository
public class HouseInfoRepositoryImpl extends HibernateDaoImpl implements HouseInfoRepository {

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
    public HouseInfoEntity get(String houseId) {
        return (HouseInfoEntity)getCurrentSession().get(HouseInfoEntity.class, houseId);
    }

    @Override
    public List<HouseInfoEntity> getList(List<String> idList) {
        String hql = "SELECT h FROM HouseInfoEntity AS h  WHERE h.id in(:idList)  ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameterList("idList", idList);
        return query.list();
    }

    /**
     * Describe:根据用户Id获取用户下的房产
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:48:07
     */
    @Override
    public List<HouseInfoEntity> getByUserId(String userId) {
        String hql = "SELECT h FROM HouseInfoEntity AS h,HouseUserBookEntity AS hub WHERE h.id = hub.houseId AND hub.userId = :userId ";
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
    public void create(HouseInfoEntity houseInfoEntity) {
        Session session = getCurrentSession();
        session.save(houseInfoEntity);
        session.flush();
    }

    /**
     * Describe:根据用户Id、项目Id获取房产列表
     * CreateBy:Tom
     * CreateOn:2016-01-20 11:54:52
     */
    @Override
    public List<HouseInfoEntity> getListByUserIdAndProjectId(String userId, String projectId) {
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT hi FROM HouseInfoEntity AS hi,HouseUserBookEntity AS hub WHERE 1 = 1 ");
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
    public HouseInfoEntity getByViewAppHouseId(int viewAppHouseId) {
        String hql = "FROM HouseInfoEntity WHERE viewAppHouseId = :viewAppHouseId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("viewAppHouseId", viewAppHouseId);
        List<HouseInfoEntity> houseInfoEntityList = query.list();
        if(houseInfoEntityList.size() == 0){
            return null;
        }
        return houseInfoEntityList.get(0);
    }

    /**
     * 根据不同条件查找房产
     * 分页分条件
     * @param houseInfoEntity
     * @return
     */
    @Override
    public List<HouseInfoEntity> listHouseInfo(HouseInfoEntity houseInfoEntity,WebPage webPage) {

        List<HouseInfoEntity> houseInfoEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from HouseInfoEntity as h where 1 = 1");
        //如果公司id不为空则拼接公司
        if (houseInfoEntity.getCompanyId()!=null&&!"0".equals(houseInfoEntity.getCompanyId())){
            hql.append("and h.companyId = ?");
            params.add(houseInfoEntity.getCompanyId());
        }
        //如果项目id不为空则拼接项目
        if (houseInfoEntity.getProjectId()!=null&&!"0".equals(houseInfoEntity.getProjectId())){
            hql.append("and h.projectId = ?");
            params.add(houseInfoEntity.getProjectId());
        }
        //如果楼不为空则拼楼
        if (houseInfoEntity.getBuildingId()!=null&&!"0".equals(houseInfoEntity.getBuildingId())){
            hql.append("and h.buildingId = ?");
            params.add(houseInfoEntity.getBuildingId());
        }
        //如果单元不为空则拼单元
        /*if (houseInfoEntity.getUnitId()!=null&&!"0".equals(houseInfoEntity.getUnitId())){
            hql.append("and h.unitId = ?");
            params.add(houseInfoEntity.getUnitId());
        }*/
        //如果房间不为空则拼房间
        if (houseInfoEntity.getRoomId()!=null&&!"0".equals(houseInfoEntity.getRoomId())){
            hql.append("and h.roomId = ?");
            params.add(houseInfoEntity.getRoomId());
        }

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        houseInfoEntities = (List<HouseInfoEntity>)getHibernateTemplate().find(hql.toString());

        return houseInfoEntities;
    }

    @Override
    public HouseInfoEntity getHouseById(String houseId) {
        String hql = "from HouseInfoEntity where state='1' and id='"+houseId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> houseList=query.list();
        if(houseList.size()>0){
            return houseList.get(0);
        }
        return null;
    }

    @Override
    public List<HouseInfoEntity> houseInfoByProjectId(String projectId,String building,String formatId) {
        String hql = "FROM HouseInfoEntity as h WHERE h.projectId =:projectId and buildingId =:building  and formatId=:formatId  order by h.buildingName,h.unitName,h.roomName ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        query.setParameter("building", building);
        query.setParameter("formatId",formatId);
        return query.list();
    }

    @Override
    public List<HouseInfoEntity> getHouseTypeList() {
        String hql = "from HouseInfoEntity";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    /**
     * 根据项目，业态，楼号，单元，房号获取房产信息
     * @param houseInfoEntity
     * @return
     */
    @Override
    public HouseInfoEntity getHouseInfo(HouseInfoEntity houseInfoEntity) {
        String hql = "from HouseInfoEntity as h where 1=1";
        if (houseInfoEntity.getProjectId()!=null&&!"".equals(houseInfoEntity.getProjectId())){
            hql = hql +" and h.projectId = '"+houseInfoEntity.getProjectId() + "'";
        }
        if (houseInfoEntity.getFormatId()!=null&&!"".equals(houseInfoEntity.getFormatId())){
            hql = hql +" and h.formatId = '"+houseInfoEntity.getFormatId() + "'";
        }
        if (houseInfoEntity.getBuildingId()!=null&&!"".equals(houseInfoEntity.getBuildingId())){
            hql = hql+" and h.buildingId = '"+houseInfoEntity.getBuildingId() + "'";
        }
        /*if (houseInfoEntity.getUnitId()!=null&&!"".equals(houseInfoEntity.getUnitId())){
            hql = hql +" and h.unitId = '"+houseInfoEntity.getUnitId() + "'";
        }*/
        if (houseInfoEntity.getRoomId()!=null&&!"".equals(houseInfoEntity.getRoomId())){
            hql = hql +" and h.roomId = '"+houseInfoEntity.getRoomId() + "'";
        }
        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size()>0){
            return (HouseInfoEntity)query.list().get(0);
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
    public void updateHouseInfo(HouseInfoEntity houseInfoEntity) {
        Session session = getCurrentSession();
        session.update(houseInfoEntity);
        session.flush();
        session.close();
    }

    /**
     * 批量修改
     * @param houseInfoEntity
     */
    @Override
    public void updateBatch(HouseInfoEntity houseInfoEntity) {
        String sql="UPDATE house_houseInfo set area='"+houseInfoEntity.getArea()+"' WHERE area_id='"+houseInfoEntity.getAreaId()+"'";
        getCurrentSession().createSQLQuery(sql).executeUpdate();
    }

    /**
     * Describe:根据会员编号Id房产列表
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     * @param memberId
     */
    @Override
    public List<Object[]> getOwnerOtherHouse(String memberId) {
        /*
        //存在问题的SQL:业主B有其他房产1套,业主A添加业主B为同住人,业主B的房产消失,只显示业主A的房产。经查该SQL查询两次合并均为同住人房产。修正为自身拥有房产及同住房产合并。
        String sql = "SELECT h.id,h.address,h.state FROM house_houseInfo h,house_userBook hu" +
                " WHERE hu.house_Id=h.id AND hu.state='NORMAL' AND h.state='1' AND hu.member_Id='"+memberId+"'" +
                " UNION SELECT h.id,h.address,h.state FROM user_userInfo u,house_userBook hu,house_houseInfo h" +
                " WHERE u.user_Id=hu.user_Id AND hu.house_Id=h.id AND u.user_State=1 AND hu.state='NORMAL'" +
                " AND h.state='1' AND hu.member_id='"+memberId+"' ";
        */
//        String sql = "SELECT h.id,h.address,h.state FROM house_houseInfo h" +
//                " WHERE h.state='1' AND h.member_Id='"+memberId+"'" +
//                " UNION SELECT h.id,h.address,h.state FROM user_userInfo u,house_userBook hu,house_houseInfo h" +
//                " WHERE u.user_Id=hu.user_Id AND hu.house_Id=h.id AND u.user_State=1 AND hu.state='NORMAL'" +
//                " AND h.state='1' AND hu.member_id='"+memberId+"' ";
        String sql = "SELECT h.id,h.address,h.state FROM house_houseInfo h,house_user_crm hu" +
                " WHERE h.ROOM_ID = hu.room_id AND h.state='1' AND hu.member_id='"+memberId+"'" +
                " UNION SELECT h.id,h.address,h.state FROM user_userInfo u,house_userBook hu,house_houseInfo h" +
                " WHERE u.user_Id=hu.user_Id AND hu.house_Id=h.id AND u.user_State=1 AND hu.state='NORMAL'" +
                " AND h.state='1' AND hu.member_id='"+memberId+"' ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> houseList=query.list();
        return houseList;
    }

    /**
     * 获取所有其他房产
     * param memberId
     * return
     */
    @Override
    public List<HouseInfoEntity> getOtherHouse(String memberId) {
//        String hql ="FROM HouseInfoEntity WHERE state='1' AND memberId='"+memberId+"'";
        String hql = "from HouseInfoEntity h,HouseUserCRMEntity hu where h.roomId = hu.roomId and h.state='1' and hu.memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> houseList = new ArrayList<>();
        List<Object[]> list = query.list();
        for (int i = 0,length = list.size(); i < length; i++){
            Object[] objects = list.get(i);
            houseList.add((HouseInfoEntity) objects[0]);
        }
        return houseList;
    }

    /**
     * Describe:获取同住人其他房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    @Override
    public List<Object[]> getHousemateOtherHouse(String userId) {
        String sql = "SELECT h.id,h.address,h.state FROM house_houseInfo h,house_userBook hu" +
                " WHERE hu.house_Id=h.id AND hu.state='NORMAL' AND h.state='1' AND hu.user_id='"+userId+"'" +
                " UNION SELECT h.id,h.address,h.state FROM user_userInfo u,house_userBook hu,house_houseInfo h" +
                " WHERE u.user_Id=hu.user_Id AND hu.house_Id=h.id AND u.user_State=1 AND hu.state='NORMAL'" +
                " AND h.state='1' AND hu.user_id='"+userId+"'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> houseList=query.list();
        return houseList;
    }

    /**
     * Describe:获取同住人默认房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    @Override
    public List<Object[]> getHousemateDefaultHouse(String userId) {
        String sql = "SELECT h.id,h.address,h.state,h.project_id FROM house_houseInfo h,house_userBook hu" +
                " WHERE hu.house_Id=h.id AND hu.state='NORMAL' AND h.state='0' AND hu.user_id='"+userId+"'" +
                " UNION SELECT h.id,h.address,h.state,h.project_id FROM user_userInfo u,house_userBook hu,house_houseInfo h" +
                " WHERE u.user_Id=hu.user_Id AND hu.house_Id=h.id AND u.user_State=1 AND hu.state='NORMAL'" +
                " AND h.state='0' AND hu.user_id='"+userId+"'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> houseList=query.list();
        return houseList;
    }

    /**
     * Describe:获取业主/同住人默认房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    @Override
    public List<Object[]> getOwnerDefaultHouse(String userId,String memberId) {
//        String sql = "SELECT id,address,state FROM house_houseInfo WHERE state='0' AND member_id='"+memberId+"'" +
//                " UNION SELECT h.id,h.address,h.state FROM user_userInfo u,house_userBook hu,house_houseInfo h" +
//                " WHERE u.user_Id=hu.user_Id AND hu.house_Id=h.id AND u.user_State=1 AND hu.state='NORMAL'" +
//                " AND h.state='0' AND hu.user_id='"+userId+"'";
        String sql = "SELECT h1.id,h1.address,h1.state FROM house_houseInfo h1,house_user_crm hu1 WHERE h1.ROOM_ID = hu1.room_id AND h1.state='0' AND hu1.member_id='"+memberId+"'" +
                " UNION SELECT h.id,h.address,h.state FROM user_userInfo u,house_userBook hu,house_houseInfo h" +
                " WHERE u.user_Id=hu.user_Id AND hu.house_Id=h.id AND u.user_State=1 AND hu.state='NORMAL'" +
                " AND h.state='0' AND hu.user_id='"+userId+"'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> houseList=query.list();
        return houseList;
    }

    /**
     * Describe:根据会员编号Id获取默认房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    @Override
    public HouseInfoEntity getDefaultHouse(String memberId) {
        String hql = "FROM HouseInfoEntity WHERE state='0' AND memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> houseList=query.list();
        if(houseList.size()>0){
            return houseList.get(0);
        }
        return null;
    }

    /**
     * Describe:根据会员编号Id获取房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    @Override
    public HouseInfoEntity getHouseByMemberId(String memberId) {
        String hql = "from HouseInfoEntity where memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> houseList=query.list();
        if(houseList.size()>0){
            return houseList.get(0);
        }
        return null;
    }

    /**
     * Describe:根据会员编号Id获取房产列表
     * CreateBy:shanshan
     * CreateOn:2016-01-20 11:52:36
     */
    @Override
    public List<HouseInfoEntity> getHouseByUserMemberId(String memberId) {
//        String hql = "from HouseInfoEntity where memberId='"+memberId+"'";
        String hql = "from HouseInfoEntity h,HouseUserCRMEntity hu where h.roomId = hu.roomId and hu.memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> houseList = new ArrayList<>();
        List<Object[]> list = query.list();
        for (int i = 0,length = list.size(); i < length; i++){
            Object[] objects = list.get(i);
            houseList.add((HouseInfoEntity) objects[0]);
        }
        return houseList;
    }

    /**
     * Describe:获取业主所有房产(包括同住人)
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    @Override
    public List<Object[]> getAllHouse(String memberId) {
//        String sql = "SELECT room_Id,address,state FROM house_houseInfo WHERE member_Id='"+memberId+"'" +
//                " UNION SELECT h.room_Id,h.address,h.state FROM user_userInfo u,house_userBook hu,house_houseInfo h " +
//                "WHERE u.user_Id=hu.user_Id AND hu.house_Id=h.id AND u.user_State=1 AND hu.state='NORMAL' " +
//                "AND hu.member_id='"+memberId+"' ";
        String sql = "SELECT h.room_Id,h.address,h.state FROM house_houseInfo h,house_user_crm hu WHERE h.ROOM_ID = hu.room_id AND hu.member_Id='"+memberId+"'" +
                " UNION SELECT h.room_Id,h.address,h.state FROM user_userInfo u,house_userBook hu,house_houseInfo h " +
                "WHERE u.user_Id=hu.user_Id AND hu.house_Id=h.id AND u.user_State=1 AND hu.state='NORMAL' " +
                "AND hu.member_id='"+memberId+"' ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> houseList=query.list();
        return houseList;
    }

    /**
     * Describe:获取同住人所有房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    @Override
    public List<Object[]> getHousemateHouse(String userId) {
        String hql = "SELECT h.roomId,h.address,h.state FROM HouseInfoEntity h,HouseUserBookEntity hu " +
                "WHERE hu.houseId=h.id AND hu.state='NORMAL' AND hu.userId='"+userId+"' ";
        Query query = getCurrentSession().createQuery(hql);
        List<Object[]> houseList=query.list();
        return houseList;
    }

    /**
     * Describe:根据业主房屋关系Id获取业主房屋关系信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public Object[] getById(String id) {
        String hql="SELECT address,houseType,buildingArea,usableArea FROM HouseInfoEntity WHERE id='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<Object[]> houseList=query.list();
        if(houseList.size()>0){
            return houseList.get(0);
        }
        return null;
    }


    /**
     * Describe:根据房屋id去查询房屋信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     * param:房间id
     */
    @Override
    public HouseInfoEntity getHouseByRoomId(String roomId) {
        String hql="FROM HouseInfoEntity WHERE roomNum='"+roomId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> houseList=query.list();
        if(houseList.size()>0){
            return houseList.get(0);
        }
        return null;
    }

    /**
     * 通过房屋id检索房屋信息_Wyd
     * @param roomId    房屋Id
     * @return  HouseInfoEntity
     */
    public HouseInfoEntity getHouseInfoByRoomId(String roomId){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM HouseInfoEntity WHERE roomId='" + roomId + "'");
        List<HouseInfoEntity> list = query.list();
        session.flush();
        session.close();
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * Describe:根据项目id0去查询项目和楼栋信息信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-27 20:40
     *
     * @param projectId
     */
    @Override
    public List<HouseInfoEntity> getInfoByProjectId(String projectId) {
        String  hql="FROM HouseInfoEntity WHERE projectId='"+projectId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> projectList=query.list();
        return projectList;
    }

    /**
     * Describe:根据项目编号去查询项目和楼栋信息信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-27 20:40
     *
     * @param projectNum
     */
    @Override
    public List<HouseInfoEntity> getInfoByProjectNum(String projectNum) {
        String  hql="FROM HouseInfoEntity WHERE projectNum='"+projectNum+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> projectList=query.list();
        return projectList;
    }

    /**
     * Describe:根据楼栋id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28
     *
     * @param buildingId
     */
    @Override
    public List<HouseInfoEntity> getInfoByBuildingId(String buildingId) {
        String  hql="FROM HouseInfoEntity WHERE buildingId='"+buildingId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> buildingList=query.list();
        return buildingList;
    }

    /**
     * Describe:根据楼层编码去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-05-07
     *
     * @param floorId
     */
    @Override
    public List<HouseInfoEntity> getInfoByFloorId(String floorId) {
        String  hql="FROM HouseInfoEntity WHERE floorId='"+floorId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> floorList=query.list();
        return floorList;
    }

    /**
     * Describe:根据单元Id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-05-07
     *
     * @param unitId
     */
    @Override
    public List<HouseInfoEntity> getInfoByUnitId(String unitId) {
        String  hql="FROM HouseInfoEntity WHERE unitId='"+unitId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> unitList=query.list();
        return unitList;
    }

    /**
     * Describe:根据单元Id去查询信息(按房屋号码排序)
     * CreateBy:WeiYangDong
     * CreateOn:2017-11-03
     */
    @Override
    public List<HouseInfoEntity> getInfoByUnitIdOrderBy(String unitId) {
        String  hql="FROM HouseInfoEntity WHERE unitId='"+unitId+"' ORDER BY roomName";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> unitList=query.list();
        return unitList;
    }

    /**
     * Describe:根据城市Id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-05-08
     *
     * @param cityId
     */
    @Override
    public List<HouseInfoEntity> getInfoByCityId(String cityId) {
        String  hql="FROM HouseInfoEntity WHERE cityId='"+cityId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> cityList=query.list();
        return cityList;
    }

    /**
     * Describe:根据地块Id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-05-08
     *
     * @param blockId
     */
    @Override
    public List<HouseInfoEntity> getInfoByBlockId(String blockId) {
        String  hql="FROM HouseInfoEntity WHERE areaId='"+blockId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> blockList=query.list();
        return blockList;
    }

    @Override
    public HouseInfoEntity getHouseInfoEntityByUserId(String userId) {
        String  hql="FROM HouseInfoEntity  hi WHERE state = '0' and memberId='"+userId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> blokcList=query.list();
        if(blokcList.size()>0){
            return blokcList.get(0);
        }
        return null;
    }

    @Override
    public List<HouseInfoEntity> getHouseInfoByBuildingNum(String BuildingNum) {
        String hql = "from HouseInfoEntity where 1=1";
        if(BuildingNum!=null && !"".equals(BuildingNum)){
            List<Object> params = new ArrayList<Object>();
            hql += " and buildingNum = ? ";
            params.add(BuildingNum);
            return (List<HouseInfoEntity>)getHibernateTemplate().find(hql, params.toArray());
        }else{
            return (List<HouseInfoEntity>)getHibernateTemplate().find(hql);
        }

    }

    @Override
    public List<String> getFloorListByUnitId(String projectId, String areaId, String buildingId, String unitId) {
        StringBuffer sql = new StringBuffer("SELECT DISTINCT(f.FLOOR_NAME) b FROM house_houseInfo a  LEFT JOIN house_floor f on a.FLOOR_ID=f.ID ");
        sql.append("where a.room_num like ?and a.room_num like ? and f.UNIT_CODE=? order by b*1");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0,"%"+projectId+"%");
        query.setParameter(1,"%"+buildingId+"%");
        query.setParameter(2,unitId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public UserInfoEntity getUserById(String userId) {
        return (UserInfoEntity) getCurrentSession().get(UserInfoEntity.class, userId);
    }
    public String getRepairid() {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append("_nextval('repairid')");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        List<Integer> list = query.list();
        if(list != null && !list.isEmpty()){
            return list.get(0)+"";
        }
        return null;
    }

    @Override
    public String getRepairidNew(String num) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append("_repairnextval('"+num+"')");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        List<Integer> list = query.list();
        if(list != null && !list.isEmpty()){
            return list.get(0)+"";
        }
        return null;
    }

    @Override
    public RoomLocationEntititly getRoomLocation(String name) {
        String  hql="FROM RoomLocationEntititly  hi WHERE  id='"+name+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<RoomLocationEntititly> blokcList=query.list();
        if(blokcList.size()>0){
            return blokcList.get(0);
        }
        return null;
    }

    @Override
    public Boolean updateHouseInfoById(List<String> idList) {
        StringBuffer sql = new StringBuffer("UPDATE house_houseInfo hh, house_floor hf, house_unit hu, house_building hb, house_area ha, house_project hp, house_city hc ");
        sql.append(" SET ");
        sql.append(" hh.FLOOR_NUM = hf.FLOOR_CODE, hh.FLOOR_ID = hf.ID, hh.FLOOR = hf.FLOOR_NAME, hh.UNIT = hu.`NAME`, hh.UNIT_ID = hu.ID, hh.UNIT_NUM = hu.UNIT_CODE, ");
        sql.append(" hh.BUILDING_ID = hb.ID, hh.BUILDING_NUM = hb.building_num, hh.BUILDING_RECORD = hb.BUILDING_RECORD, hh.BUILDING_SALE = hb.BUILDING_SALE,hh.AREA = ha.`NAME`, ");
        sql.append(" hh.AREA_ID = ha.ID, hh.AREA_NUM = ha.BLOCK_CODE, hh.PROJECT_NAME = hp.`NAME`, hh.CITY = hc.CITY_NAME, hh.CITY_ID = hc.ID, hh.CITY_NUM = hc.CITY_CODE");
        sql.append(" WHERE ");
        sql.append(" hh.FLOOR_ID = hf.ID AND hf.UNIT_ID = hu.ID AND hu.BUILDING_ID = hb.ID AND hb.BLOCK_ID = ha.ID AND ha.PROJECT_ID = hp.ID AND hp.CITY_ID = hc.ID ");
        sql.append(" AND hh.ID in(:idList) ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setParameterList("idList",idList);
        sqlQuery.executeUpdate();
        return true;
    }

    @Override
    public Boolean updateBudingMappingById(List<String> idList, String date) {
        StringBuffer sql = new StringBuffer("UPDATE building_mapping_time bmt,house_houseInfo hh ");
        sql.append(" SET ");
        sql.append(" bmt.ADDRESS=hh.ADDRESS,bmt.TIME_STAMP='"+date+"' ");
        sql.append(" WHERE ");
        sql.append(" bmt.CURRENT_ID=hh.ID ");
        sql.append(" AND  bmt.CURRENT_ID in(:idList) ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setParameterList("idList",idList);
        sqlQuery.executeUpdate();
        return true;
    }

    /**
     * 通过roomId获取房产业主关联信息列表
     * @param roomId 房产ID
     * @return List<HouseUserCRMEntity>
     */
    public List<HouseUserCRMEntity> getHouseUserCRMListByRoomId(String roomId){
        StringBuilder hql = new StringBuilder("FROM HouseUserCRMEntity hu WHERE hu.roomId = ? GROUP BY hu.memberId,hu.roomId");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0,roomId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

}
