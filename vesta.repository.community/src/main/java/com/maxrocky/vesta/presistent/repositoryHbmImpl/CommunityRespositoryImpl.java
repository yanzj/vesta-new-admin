package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.CommunityRespository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import com.maxrocky.vesta.utility.Page;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwei on 2016/1/13.
 */
@Repository
public class CommunityRespositoryImpl extends HibernateDaoImpl implements CommunityRespository {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存或更新Entity
     * @param entity
     */
    @Override
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * 通过公告Id检索发布范围列表
     * @param activityId    活动Id
     * @return  List<CommunityActivityInfoScopeEntity>
     */
    @Override
    public List<CommunityActivityInfoScopeEntity> getScopeByActivity(String activityId){
        Session session = getSession();
        String hql = " from CommunityActivityInfoScopeEntity where activityId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, activityId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public void add() {
        Session session = this.getSession();
        CommunityActivityInfoEntity info = (CommunityActivityInfoEntity) session.get(CommunityActivityInfoEntity.class, "1");
        info.setTitle("66666");
        info.setHotline("1255856444");
        session.saveOrUpdate(info);
        throw new GeneralException();

    }

    @Override
    public List<Object> getCommuntiyActivityList(Page page, Class clazz, String projectId) {

        String querySql = "select result2.* from (SELECT \n" +
                "   resultInfo.*\n" +
                "FROM\n" +
                "    (SELECT \n" +
                "        info.activity_id as activityId," +
                "        info.activity_desc as des," +
                "        info.acitivity_date as makeDate," +
                "        info.title as title,\n" +
                "        img.url  as imageUrl \n" +
                "    FROM\n" +
                "        community_activity_info info\n" +
                "     left JOIN community_image_info img ON info.activity_id = img.activity_id  where project_id='%s'  and status='1'\n" +
                "    ORDER BY img.makedate) resultInfo\n" +
                "GROUP BY resultInfo.activityId) result2 ";


        querySql = String.format(querySql,projectId);
        return getSession()
                .createSQLQuery(querySql)
                .setResultTransformer(Transformers.aliasToBean(clazz))
                .setFirstResult(page.getFirstResult())
                .setMaxResults(page.getMaxResult()).list();

    }

    @Override
    public CommunityActivityInfoEntity getCommuntiyActivityInfo(String id, String projectId) {
        return (CommunityActivityInfoEntity) this.getSession().get(CommunityActivityInfoEntity.class, id);
    }

    @Override
    public List<CommunityImageInfoEntity> getCommunityImageListByActivityId(String id) {

        String queryHql = "from CommunityImageInfoEntity where activityId='%s'";
        queryHql = String.format(queryHql, id);
        return this.getSession().createQuery(queryHql).list();
    }

    /**
     * Method getHomeActivityEntityList 根据时间获取最新的两条数据
     * User: liuwei
     * Created: 2016-01-15 06:44:58
     * Params : []
     * return: java.util.List<com.maxrocky.vesta.domain.model.CommunityActivityInfoEntity>
     */
    @Override
    public List<CommunityActivityInfoEntity> getHomeActivityEntityList() {
        String queryHql = "from CommunityActivityInfoEntity and status='1' order by  makedate desc";
        List<CommunityActivityInfoEntity> homeActivityList = this.getSession().createQuery(queryHql).setFirstResult(0).setMaxResults(2).list();
        return homeActivityList;
    }

    /**
     * Method getFirstImageEntity 获取第一条图片数据
     * User: liuwei
     * Created: 2016-01-15 06:57:04
     * Params : [activityId]
     * return: com.maxrocky.vesta.domain.model.CommunityImageInfoEntity
     */
    @Override
    public CommunityImageInfoEntity getFirstImageEntity(String activityId) {
        String queryHql = "from %s where activityId='%s' order by makedate";
        queryHql = String.format(queryHql, CommunityImageInfoEntity.class.getName(), activityId);
        List<CommunityImageInfoEntity> imageList = getSession().createQuery(queryHql).setFirstResult(0).setMaxResults(2).list();
        if (imageList == null || imageList.size() == 0) {
            return null;
        }
        return imageList.get(0);
    }

    /**
     * Method getApplyInfoEntity 查询用户是否已报名
     * User: liuwei
     * Created: 2016-01-16 03:46:15
     * Params : [id, userId]
     * return: com.maxrocky.vesta.domain.model.CommunityActivityApplyInfoEntity
     */
    @Override
    public CommunityActivityApplyInfoEntity getApplyInfoEntity(String id, String userId) {

        String queryHql = "from %s where activityId='%s' and userId='%s'";
        queryHql = String.format(queryHql, CommunityActivityApplyInfoEntity.class.getName(),
                id, userId
        );
        return (CommunityActivityApplyInfoEntity) this.getSession().createQuery(queryHql).uniqueResult();
    }

    /**
     * Method getUserApplyInfo 查询当前用户的报名状态
     * User: liuwei
     * Created: 2016-01-16 10:07:59
     * Params : [activityId, userId]
     * return: com.maxrocky.vesta.domain.model.CommunityActivityApplyInfoEntity
     */
    @Override
    public CommunityActivityApplyInfoEntity getUserApplyInfo(String activityId, String userId) {

        String hql = "from CommunityActivityApplyInfoEntity where activityId='"+activityId+"' and userId='"+userId+"'";
        Query query = getSession().createQuery(hql);
        List<CommunityActivityApplyInfoEntity> communityList=query.list();
        if(communityList.size()>0){
            return communityList.get(0);
        }
        return null;
    }

    @Override
    public void saveApplyInfoEntity(CommunityActivityApplyInfoEntity applyInfoEntity) {

        this.getSession().save(applyInfoEntity);


    }

    @Override
    public List<CommunityActivityInfoEntity> getCommunityListByProjectId(String projectId, String communitys) {

        String queryHql = "from CommunityActivityInfoEntity where projectId='%s' order by makedate desc ";
        queryHql = String.format(queryHql,projectId);
        return this.getSession().createQuery(queryHql).setFirstResult(0).setMaxResults(Integer.valueOf(communitys)).list();

    }

    @Override
    public List<CommunityActivityInfoEntity> getLastestActivityInfoByProjectId(String projectId) {
        //暂改成状态值为1的
        String queryHql = "from CommunityActivityInfoEntity where projectId='%s' and status='1' order by makedate desc";
        queryHql = String.format(queryHql,projectId);
        List<CommunityActivityInfoEntity> activityInfoEntities = this.getSession().createQuery(queryHql).setFirstResult(0).setMaxResults(10).list();

        return activityInfoEntities;
    }

    @Override
    public CommunityActivityInfoEntity getCommunityByProjectIdAndType(String projectId, Integer activity_share_type) {
        String queryHql = "from CommunityActivityInfoEntity where projectId='%s' and types=%s order by makedate";
        queryHql = String.format(queryHql,projectId,activity_share_type);
        List<CommunityActivityInfoEntity> communityActivityInfoEntities = this.getSession().createQuery(queryHql).list();
        if(communityActivityInfoEntities == null || communityActivityInfoEntities.size() == 0){
            return null;
        }
        return communityActivityInfoEntities.get(0);
    }

    @Override
    public void saveCommunityInfo(CommunityActivityInfoEntity communityActivityInfoEntity) {
        this.getSession().save(communityActivityInfoEntity);
    }

    @Override
    public void saveCommnuityImageInfo(CommunityImageInfoEntity imageInfoEntity) {
        this.getSession().save(imageInfoEntity);
    }

    /**
     * 管理后台活动列表带分页
     *
     * @param communityActivityInfoEntity
     * @param webPage
     * @return
     */
    @Override
    public List<CommunityActivityInfoEntity> listActivityInfo(CommunityActivityInfoEntity communityActivityInfoEntity, WebPage webPage,String roleScopes) {
        List<CommunityActivityInfoEntity> communityActivityInfoEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from CommunityActivityInfoEntity as c where 1=1");
        //查询未删除的
        hql.append(" and c.state = ?");
        params.add("1");
        //数据权限范围条件
        if (!"".equals(roleScopes) && !roleScopes.contains("all")){
            hql.append(" and c.activityId in (select distinct sc.activityId from CommunityActivityInfoScopeEntity sc ");
            hql.append(" where sc.projectId in ("+roleScopes.substring(0,roleScopes.length()-1)+") ) ");
        }
        //如果类型为公开
        if (communityActivityInfoEntity.getTypes()!=null&&!communityActivityInfoEntity.getTypes().equals("0")&& !"".equals(communityActivityInfoEntity.getTypes())){
            hql.append(" and c.types = ?");
            params.add(communityActivityInfoEntity.getTypes());
        }
        //项目搜索全部或者一个
     /*   if (communityActivityInfoEntity.getProjectId() != null && !"0".equals(communityActivityInfoEntity.getProjectId())){
            hql.append(SqlJoiningTogether.sqlStatement("c.projectId", communityActivityInfoEntity.getProjectId()));
        }*/
        //如果状态不为空则查询状态
        if(communityActivityInfoEntity.getStatus()!=null&&!"0".equals(communityActivityInfoEntity.getStatus())&&!"".equals(communityActivityInfoEntity.getStatus())){
            hql.append(" and c.status = ?");
            params.add(communityActivityInfoEntity.getStatus());
        }
        //主题不为空添加主题
        if (communityActivityInfoEntity.getTitle() != null && !"".equals(communityActivityInfoEntity.getTitle())) {
            hql.append(" and c.title like ?");
            params.add("%"+communityActivityInfoEntity.getTitle()+"%");
        }
        //主题不为空添加主题
        if (communityActivityInfoEntity.getScope() != null && !"".equals(communityActivityInfoEntity.getScope() )) {
        /*    hql.append(" and c.scope like ?");
            params.add("%"+communityActivityInfoEntity.getAddress()+"%");*/

            hql.append(" and c.address like ?");
            params.add("%"+communityActivityInfoEntity.getScope()+"%");
        }
        //如果地址不为空添加地址
        if (communityActivityInfoEntity.getProjectId() != null && !"".equals(communityActivityInfoEntity.getProjectId())&& !"请选择".equals(communityActivityInfoEntity.getProjectId())) {
//            hql.append(" and (c.scope = '全部项目' or c.scope like ?) ");
//            hql.append(" and c.scope like ? ");
            hql.append(" and c.activityId in (select distinct sc.activityId from CommunityActivityInfoScopeEntity sc ");
            hql.append(" where sc.projectName like ? ) ");
            params.add("%" +communityActivityInfoEntity.getProjectId()+"%");

        }
        //操作人不为空添加操作人
        if (communityActivityInfoEntity.getOperator() != null && !"".equals(communityActivityInfoEntity.getOperator())) {
            hql.append(" and c.operator like ?");
            params.add("%"+communityActivityInfoEntity.getOperator()+"%");
        }
        //活动时间假定为活动初始时间
        if (communityActivityInfoEntity.getActivityDate() != null && !"".equals(communityActivityInfoEntity.getActivityDate())) {
            hql.append(" and c.activityDate >= ?");
            params.add(communityActivityInfoEntity.getActivityDate());
        }
        //活动详情假定为活动结束时间
        if (communityActivityInfoEntity.getActivityDesc() != null && !"".equals(communityActivityInfoEntity.getActivityDesc())) {
            hql.append(" and c.activityDate <= ?");
            params.add(communityActivityInfoEntity.getActivityDesc());
        }
        //活动类型
        if (null != communityActivityInfoEntity.getActivityType() && !"".equals(communityActivityInfoEntity.getActivityType())){
            hql.append(" and c.activityType = ?");
            params.add(communityActivityInfoEntity.getActivityType());
        }
        //是否作为宣传位
        if (null != communityActivityInfoEntity.getIsBanner() && !"".equals(communityActivityInfoEntity.getIsBanner())){
            hql.append(" and c.isBanner = ?");
            params.add(communityActivityInfoEntity.getIsBanner());
        }
//        if (communityActivityInfoEntity.get)
        hql.append(" order by c.activityDate desc");
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        communityActivityInfoEntities = (List<CommunityActivityInfoEntity>) getHibernateTemplate().find(hql.toString());

        return communityActivityInfoEntities;
    }

    /**
     * 获取某个活动的报名人数
     *
     * @param activityId
     * @return
     */
    @Override
    public int countApply(String activityId) {
        String hql = "select count(*) as number from CommunityActivityApplyInfoEntity as c where c.activityId = :activityId and c.applyDesc = :applyDesc";
        Query query = getSession().createQuery(hql);
        query.setParameter("activityId", activityId);
        query.setParameter("applyDesc", "1");
        int number = ((Number) query.iterate().next()).intValue();
        return number;
    }

    /**
     * 修改活动详情
     *
     * @param communityActivityInfoEntity
     * @return
     */
    @Override
    public boolean updateActivity(CommunityActivityInfoEntity communityActivityInfoEntity) {
        CommunityActivityInfoEntity activity = getCommuntiyActivityInfo(communityActivityInfoEntity.getActivityId(), null);
        if (activity != null) {
            Session session = getSession();
            session.update(communityActivityInfoEntity);
            session.flush();
            session.close();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取某个活动的报名信息列表
     *
     * @param communityActivityInfoEntity
     * @param communityActivityApplyInfoEntity
     * @param userInfoEntity
     * @param webPage
     * @return
     */
    @Override
    public List<Object> listActivityApply(CommunityActivityInfoEntity communityActivityInfoEntity, CommunityActivityApplyInfoEntity communityActivityApplyInfoEntity, UserInfoEntity userInfoEntity, WebPage webPage) {

        List<Object> activityApply = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from CommunityActivityApplyInfoEntity as c,UserInfoEntity as u,CommunityActivityInfoEntity as ca where c.userId = u.userId and c.activityId = ca.activityId and c.applyDesc = '1' ");
        hql.append(" and ca.state = ?");
        params.add("1");
        //项目
        if (communityActivityInfoEntity.getProjectId()!=null&&!communityActivityInfoEntity.getProjectId().equals("0")){
            hql.append(" and ca.projectId = ?");
            params.add(communityActivityInfoEntity.getProjectId());
        }
        //业主
        if (userInfoEntity.getUserType()!=null&&!"".equals(userInfoEntity.getUserType())){
            hql.append(" and u.userType = ?");
            params.add(userInfoEntity.getUserType());
        }
        //活动名称
        if (communityActivityInfoEntity.getTitle()!=null&&!"".equals(communityActivityInfoEntity.getTitle())){
            hql.append(" and ca.title = ?");
            params.add(communityActivityInfoEntity.getTitle());
        }
        //姓名
        if (userInfoEntity.getRealName()!=null&&!"".equals(userInfoEntity.getRealName())){
            hql.append(" and u.realName = ?");
            params.add(userInfoEntity.getRealName());
        }
        //手机号
        if (userInfoEntity.getMobile()!=null&&!"".equals(userInfoEntity.getMobile())){
            hql.append("and u.mobile = ?");
            params.add(userInfoEntity.getMobile());
        }
        //将创建时间假定为初始时间
        if (communityActivityApplyInfoEntity.getApplyId() != null && !"".equals(communityActivityApplyInfoEntity.getApplyId())) {
            hql.append(" and c.activityDate >= ? ");
            params.add(communityActivityApplyInfoEntity.getApplyId());
        }
        //将applyDesc假定为结束时间
        if (communityActivityApplyInfoEntity.getApplyDesc() != null && !"".equals(communityActivityApplyInfoEntity.getApplyDesc())) {
            hql.append(" and c.activityDate <= ?");
            params.add(communityActivityApplyInfoEntity.getApplyDesc());
        }
        if (userInfoEntity.getRealName() != null && !"".equals(userInfoEntity.getRealName())) {
            hql.append(" and u.realName = ?");
            params.add(userInfoEntity.getRealName());
        }
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        activityApply = (List<Object>) getHibernateTemplate().find(hql.toString());

        return activityApply;

    }

    /**
     * 获取某个活动的报名信息列表s
     *
     * @param communityActivityInfoEntity
     * @param communityActivityApplyInfoEntity
     * @param userInfoEntity
     * @return
     */
    @Override
    public List<Object> listActivityApplys(CommunityActivityInfoEntity communityActivityInfoEntity, CommunityActivityApplyInfoEntity communityActivityApplyInfoEntity
            ,CommunityActivityInfoScopeEntity communityActivityInfoScopeEntity,UserInfoEntity userInfoEntity,String roleScopes) {
        List<Object> activityApply = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql =
                new StringBuffer("from CommunityActivityApplyInfoEntity as c,UserInfoEntity as u,CommunityActivityInfoScopeEntity as cas,CommunityActivityInfoEntity as ca  where c.userId = u.userId and cas.activityId=c.activityId and c.activityId = ca.activityId and c.applyDesc = '1' ");
        hql.append(" and ca.state = ? ");
        params.add("1");

        //活动id 查询
        if (communityActivityInfoEntity.getActivityId()!=null&&!"".equals(communityActivityInfoEntity.getActivityId())){
            hql.append(" and ca.activityId = ?");
            params.add(communityActivityInfoEntity.getActivityId());
        }

        //项目
        if (communityActivityInfoEntity.getProjectId()!=null&&!communityActivityInfoEntity.getProjectId().equals("0")){
            hql.append(" and cas.projectId = ?");
            params.add(communityActivityInfoEntity.getProjectId());
        }
        //业主
        if (userInfoEntity.getUserType()!=null&&!"".equals(userInfoEntity.getUserType())){
            hql.append(" and u.userType like ?");
            params.add("%" + userInfoEntity.getUserType() + "%");
        }
        //活动名称
        if (communityActivityInfoEntity.getTitle()!=null&&!"".equals(communityActivityInfoEntity.getTitle())){
            hql.append(" and ca.title like ?");
            params.add("%" + communityActivityInfoEntity.getTitle() + "%");
        }
        //姓名
        if (userInfoEntity.getRealName()!=null&&!"".equals(userInfoEntity.getRealName())){
            hql.append(" and u.nickName like ?");
            params.add("%" + userInfoEntity.getRealName() + "%");
        }

          /* if (userInfoEntity.getRealName() != null && !"".equals(userInfoEntity.getRealName())) {
            hql.append(" and u.realName = ?");
            params.add(userInfoEntity.getRealName());
        }*/

        //手机号
        if (userInfoEntity.getMobile()!=null&&!"".equals(userInfoEntity.getMobile())){
            hql.append("and u.mobile like ?");
            params.add("%" + userInfoEntity.getMobile() + "%");
        }
        //将创建时间假定为初始时间
        if (communityActivityApplyInfoEntity.getApplyId() != null && !"".equals(communityActivityApplyInfoEntity.getApplyId())) {
            hql.append(" and c.activityDate >= ? ");
            params.add(communityActivityApplyInfoEntity.getApplyId());
        }
        //将applyDesc假定为结束时间
        if (communityActivityApplyInfoEntity.getApplyDesc() != null && !"".equals(communityActivityApplyInfoEntity.getApplyDesc())) {
            hql.append(" and c.activityDate <= ?");
            params.add(communityActivityApplyInfoEntity.getApplyDesc());
        }

        if (communityActivityInfoScopeEntity.getCity() != null && !"".equals(communityActivityInfoScopeEntity.getCity())&& !"0".equals(communityActivityInfoScopeEntity.getCity())) {

            hql.append(" and cas.city = ?");
            params.add(communityActivityInfoScopeEntity.getCity());
        }
        //数据权限范围条件
        if (!"".equals(roleScopes) && !roleScopes.contains("all")){
            hql.append(" and cas.projectId in (" + roleScopes.substring(0, roleScopes.length() - 1) + ") ) ");
        }
        hql.append(" ORDER BY ca.activityDate DESC");
        /*if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }*/
        Object[] objects = new Object[params.size()];
        for (int i=0; i<params.size(); i++) {
            objects[i] = params.get(i);
        }
        activityApply = (List<Object>) getHibernateTemplate().find(hql.toString(), objects);

        return activityApply;
    }

    @Override
    public List<Object> activityProjectApply(CommunityActivityInfoEntity communityActivityInfoEntity, CommunityActivityApplyInfoEntity communityActivityApplyInfoEntity, CommunityActivityInfoScopeEntity communityActivityInfoScopeEntity, UserInfoEntity userInfoEntity) {
        List<Object> activityApply = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql =
                new StringBuffer("from CommunityActivityApplyInfoEntity as c,UserInfoEntity as u,CommunityActivityInfoScopeEntity as cas,CommunityActivityInfoEntity as ca  where c.userId = u.userId and cas.activityId=c.activityId and c.activityId = ca.activityId and c.applyDesc = '1' ");
        hql.append(" and ca.state = ? ");
        params.add("1");

        //活动id 查询
        if (communityActivityInfoEntity.getActivityId()!=null&&!"".equals(communityActivityInfoEntity.getActivityId())){
            hql.append(" and ca.activityId = ?");
            params.add(communityActivityInfoEntity.getActivityId());
        }

        //项目
        if (communityActivityInfoEntity.getProjectId()!=null&&!communityActivityInfoEntity.getProjectId().equals("0")){
            hql.append(" and cas.projectId = ?");
            params.add(communityActivityInfoEntity.getProjectId());
        }
        //业主
        if (userInfoEntity.getUserType()!=null&&!"".equals(userInfoEntity.getUserType())){
            hql.append(" and u.userType = ?");
            params.add(userInfoEntity.getUserType());
        }
        //活动名称
        if (communityActivityInfoEntity.getTitle()!=null&&!"".equals(communityActivityInfoEntity.getTitle())){
            hql.append(" and ca.title = ?");
            params.add(communityActivityInfoEntity.getTitle());
        }
        //姓名
        if (userInfoEntity.getRealName()!=null&&!"".equals(userInfoEntity.getRealName())){
            hql.append(" and u.nickName = ?");
            params.add(userInfoEntity.getRealName());
        }

          /* if (userInfoEntity.getRealName() != null && !"".equals(userInfoEntity.getRealName())) {
            hql.append(" and u.realName = ?");
            params.add(userInfoEntity.getRealName());
        }*/

        //手机号
        if (userInfoEntity.getMobile()!=null&&!"".equals(userInfoEntity.getMobile())){
            hql.append("and u.mobile = ?");
            params.add(userInfoEntity.getMobile());
        }
        //将创建时间假定为初始时间
        if (communityActivityApplyInfoEntity.getApplyId() != null && !"".equals(communityActivityApplyInfoEntity.getApplyId())) {
            hql.append(" and c.activityDate >= ? ");
            params.add(communityActivityApplyInfoEntity.getApplyId());
        }
        //将applyDesc假定为结束时间
        if (communityActivityApplyInfoEntity.getApplyDesc() != null && !"".equals(communityActivityApplyInfoEntity.getApplyDesc())) {
            hql.append(" and c.activityDate <= ?");
            params.add(communityActivityApplyInfoEntity.getApplyDesc());
        }

        if (communityActivityInfoScopeEntity.getCity() != null && !"".equals(communityActivityInfoScopeEntity.getCity())&& !"0".equals(communityActivityInfoScopeEntity.getCity())) {

            hql.append(" and cas.city = ?");
            params.add(communityActivityInfoScopeEntity.getCity());
        }
        hql.append(" ORDER BY ca.activityDate DESC");
        /*if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }*/
        Object[] objects = new Object[params.size()];
        for (int i=0; i<params.size(); i++) {
            objects[i] = params.get(i);
        }
        activityApply = (List<Object>) getHibernateTemplate().find(hql.toString(), objects);

        return activityApply;
    }

    /**
     * 后台添加活动
     * @param communityActivityInfoEntity
     */
    @Override
    public void saveAdminCommunityActivityInfo(CommunityActivityInfoEntity communityActivityInfoEntity) {
        Session session = getSession();
        session.save(communityActivityInfoEntity);
        session.flush();
        session.close();
    }

    @Override
    public void saveAdminCommunityImage(CommunityImageInfoEntity communityImageInfoEntity) {
        Session session = getSession();
        session.save(communityImageInfoEntity);
        session.flush();
        session.close();
    }

    /**
     * 根据活动ID查询图片
     * @param activityId
     * @return
     */
    @Override
    public List<CommunityImageInfoEntity> getCommunityImageById(String activityId) {
        String hql="FROM CommunityImageInfoEntity WHERE activityId='"+activityId+"'";
        Query query = getSession().createQuery(hql);
        List<CommunityImageInfoEntity> image=query.list();
        return image;
    }


    /**
     * 更新图片信息
     * @param communityImageInfoEntity
     * @return
     */
    @Override
    public boolean updateCommunityImage(CommunityImageInfoEntity communityImageInfoEntity) {
        CommunityImageInfoEntity image = getImageById(communityImageInfoEntity.getImageId());
        if (image!=null){
            Session session = getSession();
            session.update(communityImageInfoEntity);
            session.flush();
            session.close();
        }
        return false;
    }


    /**
     * 获取图片信息
     * @param imageId
     * @return
     */
    @Override
    public CommunityImageInfoEntity getImageById(String imageId) {
        String hql = "from CommunityImageInfoEntity as c where c.imageId=:imageId";
        Query query = getSession().createQuery(hql);
        query.setParameter("imageId", imageId);
        if (query.list().size()>0){
            return (CommunityImageInfoEntity)query.list().get(0);
        }
        return null;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 报名状态更改
    */
    @Override
    public void changeStatus(CommunityActivityInfoEntity communityActivityInfoEntity) {
        Session session = getSession();
        session.update(communityActivityInfoEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:liudongxin
     * 获取所有活动
     * Describe:
     * ModifyBy:
     */
    @Override
    public List<CommunityActivityInfoEntity> getAllActivities() {
        String hql="FROM CommunityActivityInfoEntity";
        Query query = getSession().createQuery(hql);
        List<CommunityActivityInfoEntity> activityInfoEntities=query.list();
        return activityInfoEntities;
    }

    /**
     * CreatedBy:liudongxin
     * 获取活动列表
     * Describe:
     * 游客、普通用户查看所有公开的活动
     * ModifyBy:
     */
    @Override
    public List<CommunityActivityInfoEntity> getOpenActivities(WebPage page) {
        String hql ="FROM CommunityActivityInfoEntity WHERE types='1' and state!='0' and pushState='1'";
        Query query = getSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getPageSize());
        List<CommunityActivityInfoEntity> activityInfoEntities = query.list();
        return activityInfoEntities;
    }

    @Override
    public List<CommunityActivityInfoEntity> getOpenActivitiesByProjectId(WebPage page, String projectCode) {
        // String sql ="FROM CommunityActivityInfoEntity WHERE types='1' and state!='0' and pushState='1'";
        StringBuffer sql = new StringBuffer();
        //sql.append(" from CommunityActivityInfoEntity c where c.types='1' and c.state!='0' and c.pushState='1'");
        sql.append(" from CommunityActivityInfoEntity c where c.state!='0' and c.pushState='1'");
        sql.append(" and c.activityId in (select os.activityId from CommunityActivityInfoScopeEntity os where os.projectId = ? ) ");
        sql.append(" ORDER BY c.activityDate DESC ");
        Query query = getSession().createQuery(sql.toString());
        //分页
        query.setParameter(0,projectCode);
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getPageSize());
        List<CommunityActivityInfoEntity> activityInfoEntities = query.list();
        return activityInfoEntities;
    }

    @Override
    public List<CommunityActivityInfoEntity> getOpenActivitiesByCity(WebPage page, String cityId) {
        // String sql ="FROM CommunityActivityInfoEntity WHERE types='1' and state!='0' and pushState='1'";
        StringBuffer sql = new StringBuffer();
        //sql.append(" from CommunityActivityInfoEntity c where c.types='1' and c.state!='0' and c.pushState='1'");
        sql.append(" from CommunityActivityInfoEntity c where c.state!='0' and c.pushState='1'");
        sql.append(" and c.activityId in (select distinct os.activityId from CommunityActivityInfoScopeEntity os where os.projectId in");
        sql.append(" (select hp.pinyinCode from HouseProjectEntity hp where hp.cityId = ?)) ");
        sql.append(" ORDER BY c.activityDate DESC ");
        Query query = getSession().createQuery(sql.toString());
        //分页
        query.setParameter(0,cityId);
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getPageSize());
        List<CommunityActivityInfoEntity> activityInfoEntities = query.list();
        return activityInfoEntities;
    }

    /**
     * CreatedBy:liudongxin
     * 获取活动列表
     * Describe:
     * 业主、同住人查看公开和本小区公开的活动
     * ModifyBy:
     * param projectId:项目id
     * param page:分页
     */
    @Override
    public List<Object[]> getActivities(String projectId,WebPage page) {
        /*List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("FROM CommunityActivityInfoEntity WHERE (STATUS='1' OR STATUS='2') OR TYPES='1'");
        hql.append(" and projectId = ?");
        params.add(projectId);
        hql.append(" ORDER BY activityDate DESC");
        if (page != null) {
            return this.findByPage(hql.toString(), page, params);
        }
        List<CommunityActivityInfoEntity> activityInfoEntities = (List<CommunityActivityInfoEntity>) getHibernateTemplate().find(hql.toString());
        return activityInfoEntities;*/

        String sql="SELECT activity_Id,acitivity_date,scope,Head_Count,people FROM community_activity_info " +
                "WHERE project_Id='"+projectId+"' AND state!='0' AND pushState='1'" +
                "UNION " +
                "SELECT activity_Id,acitivity_date,scope,Head_Count,people FROM community_activity_info " +
                "WHERE state!='0' AND pushState='1' ORDER BY acitivity_date DESC";
        Query query = getSession().createSQLQuery(sql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getPageSize());
        List<Object[]> activityInfoEntities=query.list();
        return activityInfoEntities;

    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查看我已报名的活动列表
     * param userId:用户id
     * param page:分页
     * ModifyBy:
     */
    @Override
    public List<Object[]> getMyActivities(String userId, WebPage page) {
        String sql="SELECT act.activity_Id,act.acitivity_date,act.scope,app.user_Id,act.title,act.activity_EndDate,act.apply_StartTime,act.apply_EndTime FROM community_activity_info AS act " +
                "left join community_activity_apply_info AS app ON act.activity_Id=app.activity_Id " +
                "WHERE app.user_Id='"+userId+"' and apply_desc='1' AND state!='0' " +
                "AND pushState='1' AND (status='1' OR status='2')" +
                "ORDER BY act.acitivity_date";
        Query query = getSession().createSQLQuery(sql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getPageSize());
        List<Object[]> activityInfoEntities=query.list();
        return activityInfoEntities;
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查看已报名信息
     * ModifyBy:
     * param activityId:活动id
     */
    @Override
    public List<CommunityActivityApplyInfoEntity> getApplyInfo(String activityId){
        String hql="FROM CommunityActivityApplyInfoEntity WHERE activityId='"+activityId+"' AND applyDesc='1'";
        Query query = getSession().createQuery(hql);
        List<CommunityActivityApplyInfoEntity> activityInfoEntities=query.list();
        return activityInfoEntities;
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查看所有报名信息
     * ModifyBy:
     * param activityId:活动id
     */
    @Override
    public List<CommunityActivityApplyInfoEntity> getAllApplyInfo(String activityId) {
        String hql="FROM CommunityActivityApplyInfoEntity WHERE activityId='"+activityId+"'";
        Query query = getSession().createQuery(hql);
        List<CommunityActivityApplyInfoEntity> activityInfoEntities=query.list();
        return activityInfoEntities;
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 活动取消创建
     * ModifyBy:
     */
    @Override
    public void saveCancel(CommunityCancelEntity communityCancel) {
        Session session = getSession();
        session.save(communityCancel);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查询取消信息
     * param userId:用户id
     * ModifyBy:
     */
    @Override
    public CommunityCancelEntity getCancelInfo(String userId) {
        String hql="FROM CommunityCancelEntity WHERE createBy='"+userId+"'";
        Query query = getSession().createQuery(hql);
        List<CommunityCancelEntity> cancelEntity =query.list();
        if(cancelEntity.size()>0){
            return cancelEntity.get(0);
        }
        return null;
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查询取消信息
     * ModifyBy:
     */
    @Override
    public List<CommunityCancelEntity> getCancelInfo() {
        String hql="FROM CommunityCancelEntity WHERE cancelNumber='3'";
        Query query = getSession().createQuery(hql);
        List<CommunityCancelEntity> cancelEntity =query.list();
        return cancelEntity;
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 通过id查询取消信息
     * ModifyBy:
     */
    @Override
    public CommunityCancelEntity getCancelInfoById(String id) {
        String hql="FROM CommunityCancelEntity WHERE id='"+id+"'";
        Query query = getSession().createQuery(hql);
        List<CommunityCancelEntity> cancelEntity =query.list();
        if(cancelEntity.size()>0) {
            return cancelEntity.get(0);
        }
        return null;
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改取消信息
     * ModifyBy:
     */
    @Override
    public void updateCancle(CommunityCancelEntity communityCancelEntity) {
        Session session = getSession();
        session.update(communityCancelEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改活动信息
     * ModifyBy:
     */
    @Override
    public void updateActivityInfo(CommunityActivityInfoEntity communityActivityInfoEntity) {
        Session session = getSession();
        session.update(communityActivityInfoEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改报名信息
     * ModifyBy:
     */
    @Override
    public void updateApplyInfo(CommunityActivityApplyInfoEntity communityActivityApplyInfo) {
        Session session = getSession();
        session.update(communityActivityApplyInfo);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 保存报名信息
     * ModifyBy:
     */
    @Override
    public void saveApplyInfo(CommunityActivityApplyInfoEntity communityActivityApplyInfo) {
        Session session = getSession();
        session.save(communityActivityApplyInfo);
        session.flush();
    }

    /**
     * 通过活动Id查询区域范围列表信息
     * @param activityId 活动Id
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> queryProjectByActivityId(String activityId){
        Session session = getSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select project_id projectCode,project_name projectName,city_name cityName ");
        sql.append(" from community_activityInfo_scope where activityId =  ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, activityId);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过主键Id检索活动详情
     * @param activityId
     * @return
     */
    public CommunityActivityInfoEntity queryActivityInfoById(String activityId){
        Session session = getSession();
        String hql = " from CommunityActivityInfoEntity where activityId = ? ";
        Query query = session.createQuery(hql);
        CommunityActivityInfoEntity communityActivityInfoEntity = (CommunityActivityInfoEntity) query.setParameter(0, activityId).uniqueResult();
        session.flush();
        session.close();
        return communityActivityInfoEntity;
    }

    /**
     * 通过活动Id删除活动区域中间表信息
     * @param activityId    活动Id
     */
    public void deleteCommunityActivityInfoScope(String activityId){
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery("delete from community_activityInfo_scope where activityId = ?");
        sqlQuery.setParameter(0, activityId);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    @Override
    public List<Object[]> getActivitiesByActId(String activeId) {
        String sql="SELECT project_id,project_name FROM community_activityInfo_scope " +
                "WHERE activityId='"+activeId+"' AND state!='0'";
        Query query = getSession().createSQLQuery(sql);
        List<Object[]> activityInfoEntities=query.list();
        return activityInfoEntities;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 通过活动id拿到所有活动项目
     */
    @Override
    public List<CommunityActivityInfoScopeEntity> getAllActivityProject(String activityId) {
        StringBuffer hql = new StringBuffer("from CommunityActivityInfoScopeEntity ca where ca.activityId=:activityId");
        Query query = getSession().createQuery(hql.toString());
        query.setParameter("activityId", activityId);
        List<CommunityActivityInfoScopeEntity> list = query.list();
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取该用户已报名的活动开始时间早于报名开始时间的全部活动
     */
    @Override
    public List<CommunityActivityInfoEntity> getActivityByEndDate(String userId) {
        //活动开始时间早于报名开始时间
        StringBuffer hql = new StringBuffer(" from CommunityActivityInfoEntity c where 1=1 ");
        hql.append(" and c.activityId in (select distinct ca.activityId from CommunityActivityApplyInfoEntity ca where ca.userId=:userId and ca.applyDesc='1' )");
        hql.append(" and c.activityDate<=c.applyStartTime " +
                " and (TO_DAYS(now())+1)>=(TO_DAYS(c.activityEndDate)-1) and (TO_DAYS(now())+1)<=TO_DAYS(c.activityEndDate)  " +
                " and c.state='1' and c.pushState='1' " +
                " order by c.activityDate");
        Query query = getSession().createQuery(hql.toString());
        query.setParameter("userId", userId);
        List<CommunityActivityInfoEntity> list = query.list();

        return list;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取该用户已报名的活动开始时间晚于报名开始时间的全部活动
     */
    @Override
    public List<CommunityActivityInfoEntity> getActivityByStartDate(String userId) {
        StringBuffer hql = new StringBuffer();
        //活动开始时间早于报名开始时间
        hql.append(" from CommunityActivityInfoEntity c where 1=1 ");
        hql.append(" and c.activityId in (select distinct ca.activityId from CommunityActivityApplyInfoEntity ca where ca.userId=:userId and ca.applyDesc='1' )");
        hql.append(" and c.activityDate>c.applyStartTime ");
        hql.append(" and c.state='1' and c.pushState='1' ");
        hql.append(" and (TO_DAYS(now())+1)>=(TO_DAYS(c.activityDate)-1) and (TO_DAYS(now())+1)<=TO_DAYS(c.activityEndDate) ");
        hql.append(" order by c.activityDate");
        Query query = getSession().createQuery(hql.toString());
        query.setParameter("userId", userId);
        List<CommunityActivityInfoEntity> list = query.list();
        return list;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取已报名的活动详情
     */
    @Override
    public CommunityActivityApplyInfoEntity getCommunityApply(String userId, String activityId) {
        StringBuffer hql = new StringBuffer(" from CommunityActivityApplyInfoEntity c where c.userId=:userId and c.activityId=:activityId");
        Query query = getSession().createQuery(hql.toString());
        query.setParameter("userId", userId);
        query.setParameter("activityId", activityId);
        List<CommunityActivityApplyInfoEntity> list = query.list();
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 个人活动消息已读标记
     */
    @Override
    public void changeReadStatus(String userId, String activityId) {
        Session session = getSession();
        String sql = " update community_activity_apply_info set read_status = '1' where activity_id = "+activityId+" and user_id = "+userId;
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 通过条件检索活动报名信息列表 WeiYangDong_2016-12-19
     * @param params 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    @Override
    public List<Map<String,Object>> getApplyListOld(Map<String,Object> params,WebPage webPage){
        /*
        select u.REAL_NAME 姓名,u.USER_NAME 用户名, h.PROJECT_NAME 项目,ai.apply_phone 联系方式,ai.makedate 报名时间,a.title 活动主题,ai.apply_count 报名人数
        from community_activity_apply_info ai
        left join community_activity_info a on ai.activity_id = a.activity_id
        left join user_userInfo u on ai.user_id = u.USER_ID
        left join house_houseInfo h on u.ID = h.MEMBER_ID and h.STATE = '0'
        where ai.activity_id = "2016121611181857121331842474745424"
        */
        StringBuilder sql = new StringBuilder();
        sql.append(" select u.REAL_NAME realName,u.USER_NAME userName, hu.project_name projectName,hu.house_address address,ai.apply_phone applyPhone,");
        sql.append(" ai.makedate makeDate,a.title title,ai.apply_info applyInfo,ai.apply_count applyCount,a.acitivity_date activityDate ");
        sql.append(" from community_activity_apply_info ai ");
        sql.append(" left join community_activity_info a on ai.activity_id = a.activity_id ");
        sql.append(" left join user_userInfo u on ai.user_id = u.USER_ID ");
        sql.append(" LEFT JOIN house_user_crm hu ON u.ID = hu.member_id ");
//        sql.append(" LEFT JOIN house_houseInfo h ON h.ROOM_ID = hu.room_id ");
        sql.append(" where ai.apply_desc = '1' ");
        //拼接参数
        List<Object> paramsValList = new ArrayList<>();
        //权限范围
        Object roleScopes = params.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            sql.append(" and hu.project_code in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+")");
        }
        //城市
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects)){
            sql.append(" and hu.project_code in ("+cityProjects.toString().substring(0,cityProjects.toString().length()-1)+")");
        }
        //活动ID
        Object activityId = params.get("activityId");
        if (null != activityId && !"".equals(activityId)){
            sql.append(" and ai.activity_id = ? ");
            paramsValList.add(activityId);
        }
        //项目编码
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode)){
            sql.append(" and hu.project_code = ? ");
            paramsValList.add(projectCode);
        }
        //用户类型
        Object userType = params.get("userType");
        if (null != userType && !"".equals(userType)){
            sql.append(" and u.USER_TYPE = ? ");
            paramsValList.add(userType);
        }
        //用户名称
        Object userName = params.get("userName");
        if (null != userName && !"".equals(userName)){
            sql.append(" and u.REAL_NAME like ? ");
            paramsValList.add("%"+userName+"%");
        }
        //活动标题
        Object title = params.get("title");
        if (null != title && !"".equals(title)){
            sql.append(" and a.title like ? ");
            paramsValList.add("%"+title+"%");
        }
        //联系方式
        Object userMobile = params.get("userMobile");
        if (null != userMobile && !"".equals(userMobile)){
            sql.append(" and ai.apply_phone like ? ");
            paramsValList.add("%"+userMobile+"%");
        }
        //活动时间
        Object activityDate = params.get("activityDate");
        if (null != activityDate && !"".equals(activityDate)){
            sql.append(" and a.acitivity_date = ? ");
            paramsValList.add(activityDate);
        }
        //报名时间
        Object applyDate = params.get("applyDate");
        if (null != applyDate && !"".equals(applyDate)){
            sql.append(" and ai.makedate = ? ");
            paramsValList.add(applyDate);
        }
        sql.append(" order by ai.makedate desc ");
        //执行查询
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsValList.size(); i<length; i++){
            sqlQuery.setParameter(i,paramsValList.get(i));
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过条件检索活动报名信息列表 WeiYangDong_2017-08-15
     * @param params 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    @Override
    public List<Map<String,Object>> getApplyList(Map<String,Object> params,WebPage webPage){
        StringBuilder sql = new StringBuilder();
        sql.append(" select u.REAL_NAME realName,u.USER_NAME userName, ai.project_name projectName,ai.apply_address address,ai.apply_phone applyPhone,");
        sql.append(" ai.apply_timeRange_id applyTimeRangeId,ai.apply_timeRange applyTimeRange,");
        sql.append(" ai.makedate makeDate,a.title title,ai.apply_info applyInfo,ai.apply_count applyCount,a.acitivity_date activityDate ");
        sql.append(" from community_activity_apply_info ai ");
        sql.append(" left join community_activity_info a on ai.activity_id = a.activity_id ");
        sql.append(" left join user_userInfo u on ai.user_id = u.USER_ID ");
        sql.append(" where ai.apply_desc = '1' ");
        //拼接参数
        List<Object> paramsValList = new ArrayList<>();
        //权限范围
        Object roleScopes = params.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            sql.append(" and (ai.project_num in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+") or ai.project_num is null) ");
        }
        //城市
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects)){
            sql.append(" and ai.project_num in ("+cityProjects.toString().substring(0,cityProjects.toString().length()-1)+")");
        }
        //活动ID
        Object activityId = params.get("activityId");
        if (null != activityId && !"".equals(activityId)){
            sql.append(" and ai.activity_id = ? ");
            paramsValList.add(activityId);
        }
        //项目编码
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            sql.append(" and ai.project_num = ? ");
            paramsValList.add(projectCode);
        }
        //用户类型
        Object userType = params.get("userType");
        if (null != userType && !"".equals(userType)){
            sql.append(" and u.USER_TYPE = ? ");
            paramsValList.add(userType);
        }
        //用户名称
        Object userName = params.get("userName");
        if (null != userName && !"".equals(userName)){
            sql.append(" and u.REAL_NAME like ? ");
            paramsValList.add("%"+userName+"%");
        }
        //活动标题
        Object title = params.get("title");
        if (null != title && !"".equals(title)){
            sql.append(" and a.title like ? ");
            paramsValList.add("%"+title+"%");
        }
        //联系方式
        Object userMobile = params.get("userMobile");
        if (null != userMobile && !"".equals(userMobile)){
            sql.append(" and ai.apply_phone like ? ");
            paramsValList.add("%"+userMobile+"%");
        }
        //活动时间
        Object activityDate = params.get("activityDate");
        if (null != activityDate && !"".equals(activityDate)){
            sql.append(" and a.acitivity_date = ? ");
            paramsValList.add(activityDate);
        }
        //报名时间
        Object applyDate = params.get("applyDate");
        if (null != applyDate && !"".equals(applyDate)){
            sql.append(" and ai.makedate = ? ");
            paramsValList.add(applyDate);
        }
        sql.append(" order by ai.makedate desc ");
        //执行查询
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsValList.size(); i<length; i++){
            sqlQuery.setParameter(i,paramsValList.get(i));
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过活动ID及报名日期删除时间段配置
     * @param activityId 活动ID
     * @param applyDate 报名日期
     */
    @Override
    public void deleteApplyTimeRangeByActivityAndDate(String activityId,Date applyDate){
        getSession().createQuery("DELETE CommunityActivityApplyTimeRangeEntity a WHERE a.activityId = ? AND a.applyDate = ?")
                .setParameter(0,activityId).setParameter(1,applyDate).executeUpdate();
    }

    /**
     * 获取活动报名时间段配置列表
     * @param activityId 活动ID
     * @return List<CommunityActivityApplyTimeRangeEntity>
     */
    @Override
    public List<CommunityActivityApplyTimeRangeEntity> getApplyTimeRangeListByActivity(String activityId,Date applyDate){
        Session session = getSession();
        Query query = session.createQuery("FROM CommunityActivityApplyTimeRangeEntity WHERE activityId = ? AND applyDate = ? ORDER BY startTime")
                .setParameter(0,activityId).setParameter(1,applyDate);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }
}