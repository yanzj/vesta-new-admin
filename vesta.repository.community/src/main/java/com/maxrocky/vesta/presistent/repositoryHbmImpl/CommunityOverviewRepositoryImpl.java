package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.CommunityDetailEntity;
import com.maxrocky.vesta.domain.model.CommunityOverviewEntity;
import com.maxrocky.vesta.domain.model.CommunityOverviewReservationEntity;
import com.maxrocky.vesta.domain.model.CommunityOverviewScopeEntity;
import com.maxrocky.vesta.domain.repository.CommunityOverviewRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wangyifan on 2016/5/09.
 */
@Repository
public class CommunityOverviewRepositoryImpl extends BaseRepositoryImpl implements CommunityOverviewRepository {
    @Resource(name = "sessionFactory")
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
     * Describe:待条件查询批次管理信息
     * CreateBy:yifan
     * CreateOn:2016-05-09 15:40:37
     *
     * @param communityOverviewEntity
     * @return
     * @throws Exception
     */
    @Override
    public List<CommunityOverviewEntity> queryAllByParam(CommunityOverviewEntity communityOverviewEntity, Date staDate, Date endDate) throws Exception {
        StringBuffer hql = new StringBuffer("FROM CommunityOverviewEntity as ad WHERE 1=1");
        List<CommunityOverviewEntity> communityOverviewEntityList = new ArrayList<CommunityOverviewEntity>();
        List<Object> params = new ArrayList<Object>();

        // 楼盘参考价
        if (null != communityOverviewEntity.getPriceAverage()) {
            hql.append(" and ad.priceAverage = ? ");
            params.add(communityOverviewEntity.getPriceAverage());
        }

        // 项目名称
        if (null != communityOverviewEntity.getName() && !"".equals(communityOverviewEntity.getName())) {
            hql.append(" and ad.name like ?");
            params.add('%' + communityOverviewEntity.getName() + '%');
        }

        // 标签
        if (!StringUtil.isEmpty(communityOverviewEntity.getTypes()) && !"".equals(communityOverviewEntity.getTypes())) {
            hql.append(" and ad.types like ?");
            params.add('%' + communityOverviewEntity.getTypes() + '%');
        }
        // 售楼电话
        if (!StringUtil.isEmpty(communityOverviewEntity.getPhone())) {
            hql.append(" and ad.phone like ?");
            params.add('%' + communityOverviewEntity.getPhone() + '%');
        }
        //发布日期区间1
        if (staDate != null) {
            hql.append(" and ad.releaseDate >= ?");
            params.add(staDate);
        }
        //发布日期区间2
        if (endDate != null) {
            hql.append(" and ad.releaseDate <= ?");
            params.add(endDate);
        }
        hql.append(" and ad.status = 1 ");
        hql.append(" ORDER BY ad.orderNum ASC");

        communityOverviewEntityList = (List<CommunityOverviewEntity>) getHibernateTemplate().find(hql.toString(), params.toArray());
        return communityOverviewEntityList;
    }

    /**
     * Describe:待条件分页查询批次管理信息
     * CreateBy:yifan
     * CreateOn:2016-05-09 09:40:37
     *
     * @param communityOverviewEntity
     * @param webPage
     * @return
     * @throws Exception
     */
    @Override
    public List<CommunityOverviewEntity> queryAllByPage(CommunityOverviewEntity communityOverviewEntity, WebPage webPage, Date staDate, Date endDate,String roleScopes) throws Exception {
        StringBuffer hql = new StringBuffer("FROM CommunityOverviewEntity as ad WHERE 1=1");
        //数据权限范围条件
        if (!roleScopes.contains("all")){
            hql.append(" and ad.id in (select distinct sc.communityOverviewId from CommunityOverviewScopeEntity sc ");
            hql.append(" where sc.projectCode in ("+roleScopes.substring(0, roleScopes.length() - 1)+") ) ");
        }
        List<CommunityOverviewEntity> communityOverviewEntityList = new ArrayList<CommunityOverviewEntity>();
        List<Object> params = new ArrayList<Object>();

        // 楼盘参考价
        if (null != communityOverviewEntity.getPriceAverage() && !"".equals(communityOverviewEntity.getPriceAverage())) {
            hql.append(" and ad.priceAverage = ? ");
            params.add(communityOverviewEntity.getPriceAverage());
        }

        // 项目名称
        if (null != communityOverviewEntity.getName() && !"".equals(communityOverviewEntity.getName())) {
            hql.append(" and ad.name = ?");
            params.add(communityOverviewEntity.getName());
        }

        // 标签
        if (!StringUtil.isEmpty(communityOverviewEntity.getTypes()) && !"".equals(communityOverviewEntity.getTypes())) {
            hql.append(" and ad.types like ?");
            params.add('%' + communityOverviewEntity.getTypes() + '%');
        }
        // 售楼电话
        if (!StringUtil.isEmpty(communityOverviewEntity.getPhone())) {
            hql.append(" and ad.phone like ?");
            params.add('%' + communityOverviewEntity.getPhone() + '%');
        }
        //发布日期区间1
        if (staDate != null) {
            hql.append(" and ad.releaseDate >= ?");
            params.add(staDate);
        }
        //发布日期区间2
        if (endDate != null) {
            hql.append(" and ad.releaseDate <= ?");
            params.add(endDate);
        }

        hql.append(" and ad.status = 1 ");
        hql.append(" ORDER BY ad.orderDate DESC");

        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }

        communityOverviewEntityList = (List<CommunityOverviewEntity>) getHibernateTemplate().find(hql.toString());

        return communityOverviewEntityList;
    }

    public List<CommunityOverviewEntity> queryAllByPage(Map<String,Object> params, WebPage webPage) throws Exception {
        StringBuilder hql = new StringBuilder("FROM CommunityOverviewEntity as ad WHERE 1=1");

        //数据权限范围条件
        Object roleScopes = params.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            hql.append(" and ad.id in (select distinct sc.communityOverviewId from CommunityOverviewScopeEntity sc ");
            hql.append(" where sc.projectCode in ("+roleScopes.toString().substring(0, roleScopes.toString().length() - 1)+") ");
        }
        //城市
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects)){
            //判断权限范围HQL是否追加
            if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
                hql.append(" and sc.projectCode in (" + cityProjects.toString().substring(0, cityProjects.toString().length() - 1) + ")");
            }else{
                hql.append(" and ad.id in (select distinct sc.communityOverviewId from CommunityOverviewScopeEntity sc ");
                hql.append(" where sc.projectCode in ("+cityProjects.toString().substring(0, cityProjects.toString().length() - 1)+") ");
            }
            //项目
            Object projectCode = params.get("projectCode");
            if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
                hql.append(" and sc.projectCode = '"+projectCode+"'");
            }
        }
        if (hql.toString().contains("CommunityOverviewScopeEntity")){
            hql.append(") ");
        }

        List<Object> parameterList = new ArrayList<>();
        // 楼盘参考价
        Object priceAverage = params.get("priceAverage");
        if (null != priceAverage && !"".equals(priceAverage)) {
            hql.append(" and ad.priceAverage = ? ");
            parameterList.add(priceAverage);
        }
        // 标签
        Object types = params.get("types");
        if (null != types && !"".equals(types)) {
            hql.append(" and ad.types like ?");
            parameterList.add('%' + types.toString() + '%');
        }
        // 售楼电话
        Object phone = params.get("phone");
        if (null != phone && !"".equals(phone)) {
            hql.append(" and ad.phone like ?");
            parameterList.add('%' + phone.toString() + '%');
        }
        //发布日期区间1
        Object staDate = params.get("staDate");
        if (staDate != null) {
            hql.append(" and ad.releaseDate >= ?");
            parameterList.add(staDate);
        }
        //发布日期区间2
        Object endDate = params.get("endDate");
        if (endDate != null) {
            hql.append(" and ad.releaseDate <= ?");
            parameterList.add(endDate);
        }
        hql.append(" and ad.status = 1 ");
        hql.append(" ORDER BY ad.orderDate DESC");

        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, parameterList);
        }
        List<CommunityOverviewEntity> communityOverviewEntityList = new ArrayList<CommunityOverviewEntity>();
        communityOverviewEntityList = (List<CommunityOverviewEntity>) getHibernateTemplate().find(hql.toString());

        return communityOverviewEntityList;
    }

    /**
     * 获取排序最大值进行添加
     * @return
     */
    public Integer getMaxOrderNum(){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select MAX(order_num) from community_overview");
        Integer maxNum = (Integer)sqlQuery.uniqueResult();
        return maxNum;
    }

    /**
     * 查詢金馬樓盤下項目
     * @return
     */
    @Override
    public List<String> listProject() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        Session session = getCurrentSession();
        List<String> list = session.createSQLQuery("select DISTINCT name from community_overview where status = 1 ").list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过楼盘Id清空区域项目信息
     * @param communityOverviewId 楼盘Id
     */
    public void deleteCommunityOverviewScope(String communityOverviewId){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("delete from community_overview_scope where community_overview_id = ?");
        sqlQuery.setParameter(0, communityOverviewId);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 通过楼盘Id查询区域范围列表信息
     * @param communityId 楼盘Id
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> queryProjectByCommunityId(String communityId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select os.project_code projectCode,os.project_name projectName ");
        sql.append(" from community_overview_scope os where os.community_overview_id = ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, communityId);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过项目Code集合检索城市列表信息
     * @param projectCodes  项目Code集合
     * @return Map<String,Object>
     */
    public List<Map<String,Object>> queryCityByProjectIds(String projectCodes){
        List<String> projectCodeList = Arrays.asList(projectCodes.split(","));
        String sql_where = "";
        for (int i = 0; i < projectCodeList.size(); i++){
            String projectCode = projectCodeList.get(i);
            if (i == projectCodeList.size()-1){
                //最后一个不加,
                sql_where += "\""+projectCode+"\"";
            }else{
                sql_where += "\""+projectCode+"\",";
            }
        }
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select distinct c.ID cityId,c.CITY_NAME cityName from house_project p,house_city c ");
        sql.append(" where p.CITY_ID = c.ID and p.PINYIN_CODE in (" + sql_where + ") ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过楼盘Id检索发布范围列表
     * @param announcementId
     * @return
     */
    public List<CommunityOverviewScopeEntity> getScopeByCommunityOverview(String announcementId){
        Session session = getCurrentSession();
        String hql = " from CommunityOverviewScopeEntity where communityOverviewId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0, announcementId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 通过楼盘id获取所有该楼盘对应的去编辑信息
    */
    @Override
    public List<CommunityDetailEntity> getCommunityDetailList(String communityId) {
        StringBuffer hql = new StringBuffer("from CommunityDetailEntity c where c.communityId=:communityId");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("communityId", communityId);
        List<CommunityDetailEntity> list = query.list();
        return list;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 删除CommunityDetailEntity
     */
    @Override
    public void deleteCommunityDetailEntity(CommunityDetailEntity communityDetailEntity) {
        Session session = getCurrentSession();
        session.delete(communityDetailEntity);
        session.flush();
        session.close();
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 保存去编辑信息
    */
    @Override
    public void saveCommunityDetailEntity(CommunityDetailEntity communityDetailEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(communityDetailEntity);
        session.flush();
        session.close();
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取该楼盘对应的所有CommunityDetailEntity
     */
    @Override
    public List<CommunityDetailEntity> getAllDetails(String communityId) {
        StringBuffer hql = new StringBuffer("from CommunityDetailEntity c where c.communityId=:communityId order by c.grade");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("communityId", communityId);
        List<CommunityDetailEntity> list = query.list();
        return list;
    }

    /**
     * 通过楼盘ID及图片类型删除图片
     * @param overviewId 楼盘ID
     * @param imgType 图片类型delete from community_overview_scope where community_overview_id = ?
     */
    public void deleteImgByOverview(String overviewId,String imgType){
        StringBuilder sql = new StringBuilder(" delete from community_overview_img where overview_id = ? and img_type = ? ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0,overviewId);
        sqlQuery.setParameter(1,imgType);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 获取楼盘预约列表
     * @param paramsMap 参数
     * @return List<CommunityOverviewReservationEntity>
     */
    public List<CommunityOverviewReservationEntity> getOverviewReservationList(Map<String,Object> paramsMap,WebPage webPage){
        StringBuilder hql = new StringBuilder("FROM CommunityOverviewReservationEntity co WHERE 1=1");
        List<String> paramsList = new ArrayList<>();
        Object overviewId = paramsMap.get("overviewId");
        if (null != overviewId && !"".equals(overviewId)){
            hql.append(" AND co.overviewId = ? ");
            paramsList.add((String) overviewId);
        }
        Object overviewName = paramsMap.get("overviewName");
        if (null != overviewName && !"".equals(overviewName)){
            hql.append(" AND co.overviewName like ? ");
            paramsList.add("%"+overviewName+"%");
        }
        Object reservationPer = paramsMap.get("reservationPer");
        if (null != reservationPer && !"".equals(reservationPer)){
            hql.append(" AND co.reservationPer = ? ");
            paramsList.add(reservationPer.toString());
        }
        Object reservationTel = paramsMap.get("reservationTel");
        if (null != reservationTel && !"".equals(reservationTel)){
            hql.append(" AND co.reservationTel = ? ");
            paramsList.add(reservationTel.toString());
        }
        hql.append(" ORDER BY co.reservationDate ");
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, paramsList);
        }
        List<CommunityOverviewReservationEntity> communityOverviewEntityList = null;
        communityOverviewEntityList = (List<CommunityOverviewReservationEntity>) getHibernateTemplate().find(hql.toString());
        return communityOverviewEntityList;
//        Session session = getCurrentSession();
//        Query query = session.createQuery(hql.toString());
//        for (int i=0,length=paramsList.size();i<length;i++){
//            query.setParameter(i,paramsList.get(i));
//        }
//        List list = query.list();
//        session.flush();
//        session.close();
//        return list;
    }
}
