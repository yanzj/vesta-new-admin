package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.CommunityNewScope;
import com.maxrocky.vesta.domain.model.CommunityNewsEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.repository.CommunityNewsRespository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:04
 */
@Repository
public class CommunityNewsRespositoryImpl extends HibernateDaoImpl implements CommunityNewsRespository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 通过品牌资讯Id检索发布范围列表
     * @param communityDetailId
     * @return  List<CommunityNewScope>
     */
    public List<CommunityNewScope> getScopeByCommunityNew(String communityDetailId){
        Session session = getCurrentSession();
        String hql = " from CommunityNewScope where communityDetailId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0,communityDetailId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public <E> void saveOrUpdateNews(E e) {
        Session session = getCurrentSession();
        session.saveOrUpdate(e);
        session.flush();
        session.close();
    }

    @Override
    public  <E> E get(Class<E> entityClass, Serializable id) {
        Session session = getCurrentSession();
        E entity = (E) session.get(entityClass, id);
        session.flush();
        session.close();
        return entity;
    }




    /**
     * 获取排序最大值进行添加
     * @return
     */
    public Integer getMaxOrderNum(){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select MAX(order_num) from community_news");
        Integer maxNum = (Integer)sqlQuery.uniqueResult();
        return maxNum;
    }



    @Override
    public List<CommunityNewsEntity> queryAllByPage(CommunityNewsEntity communityNewsEntity, WebPage webPage, Date staDate, Date endDate,String roleScopes) {

        List<CommunityNewsEntity> appoints = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from CommunityNewsEntity as c where 1=1");
        //数据权限范围条件
        if (!roleScopes.equals("") && !roleScopes.contains("all")){
            hql.append(" and c.id in (select distinct sc.communityDetailId from CommunityNewScope sc ");
            hql.append(" where sc.cityId = '0' or sc.projectId in ("+roleScopes.substring(0,roleScopes.length()-1)+") ) ");
        }

        //新闻标题
        if (!StringUtil.isEmpty(communityNewsEntity.getTitle()) && !"0".equals(communityNewsEntity.getTitle())) {
            hql.append(" and c.title like ?");
            params.add("%"+communityNewsEntity.getTitle()+"%");
        }
        //发布人
        if (!StringUtil.isEmpty(communityNewsEntity.getReleasePerson()) && !"0".equals(communityNewsEntity.getReleasePerson())) {
            hql.append(" and c.releasePerson like ?");
            params.add("%"+communityNewsEntity.getReleasePerson()+"%");
        }
        //新闻类别
        if (communityNewsEntity.getType() != null && !"0".equals(communityNewsEntity.getType())) {
            //品牌宣传
            if (communityNewsEntity.getType() == 1) {
                hql.append(" and c.type <= ?");
                params.add(communityNewsEntity.getType());
                //新闻资讯
            } else if (communityNewsEntity.getType() == 2) {
                hql.append(" and c.type >= ?");
                params.add(communityNewsEntity.getType());
            }
        }
        //发布状态
        if (communityNewsEntity.getReleaseStatus() != null && 999 != communityNewsEntity.getReleaseStatus()) {
            hql.append(" and c.releaseStatus = ?");
            params.add(communityNewsEntity.getReleaseStatus());
        }
        //发布日期区间1
        if (staDate != null) {
            hql.append(" and c.releaseDate >= ?");
            params.add(staDate);
        }
        //发布日期区间2
        if (endDate != null) {
            hql.append(" and c.releaseDate <= ?");
            params.add(endDate);
        }

        hql.append(" and c.status = 1 ");
        hql.append(" order by c.releaseDate DESC");

        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }

        appoints = (List<CommunityNewsEntity>) getHibernateTemplate().find(hql.toString());

        return appoints;
    }

    /**
     * 重写品牌新闻查询方法   Wyd_20170316
     * @param params    参数
     * @param webPage   分页
     * @return  List<CommunityNewsEntity>
     */
    public List<CommunityNewsEntity> queryAllByPage(Map<String,Object> params, WebPage webPage) {
        //解析参数
        CommunityNewsEntity communityNewsEntity = (CommunityNewsEntity) params.get("communityNewsEntity");
        Object staDate = params.get("staDate");
        Object endDate = params.get("endDate");
        String roleScopes = params.get("roleScopes").toString();

        List<CommunityNewsEntity> appoints = new ArrayList<>();
        List<Object> paramsValList = new ArrayList<Object>();
        StringBuilder hql = new StringBuilder("from CommunityNewsEntity as c where 1=1");

        String sql_1 = " and c.id in (select distinct sc.communityDetailId from CommunityNewScope sc where 1=1 ";
        if (!roleScopes.equals("") && !roleScopes.contains("all")){
            if (hql.indexOf(sql_1) < 0){
                hql.append(sql_1);
            }
            hql.append(" and sc.cityId = '0' or sc.projectId in ("+roleScopes.substring(0,roleScopes.length()-1)+") ");
        }
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects)){
            if (hql.indexOf(sql_1) < 0){
                hql.append(sql_1);
            }
            hql.append(" and ( ");
            String[] cityProjectStrs = cityProjects.toString().split(",");
            for (int i=0,length=cityProjectStrs.length;i<length;i++){
                if (i == 0){
                    hql.append(" locate("+cityProjectStrs[i]+",sc.projectId)>0 ");
                }else{
                    hql.append(" or locate("+cityProjectStrs[i]+",sc.projectId)>0 ");
                }
            }
            hql.append(" ) ");
        }
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            if (hql.indexOf(sql_1) < 0){
                hql.append(sql_1);
            }
            hql.append(" and sc.projectId = '" + projectCode + "'");
        }
        if (hql.indexOf(sql_1) > 0){
            hql.append(" ) ");
        }
        //数据权限范围条件
//        if (!roleScopes.equals("") && !roleScopes.contains("all")){
//            hql.append(" and c.id in (select distinct sc.communityDetailId from CommunityNewScope sc ");
//            hql.append(" where sc.cityId = '0' or sc.projectId in ("+roleScopes.substring(0,roleScopes.length()-1)+") ) ");
//        }
        //新闻标题
        if (!StringUtil.isEmpty(communityNewsEntity.getTitle()) && !"0".equals(communityNewsEntity.getTitle())) {
            hql.append(" and c.title like ?");
            paramsValList.add("%"+communityNewsEntity.getTitle()+"%");
        }
        //发布人
        if (!StringUtil.isEmpty(communityNewsEntity.getReleasePerson()) && !"0".equals(communityNewsEntity.getReleasePerson())) {
            hql.append(" and c.releasePerson like ?");
            paramsValList.add("%"+communityNewsEntity.getReleasePerson()+"%");
        }
        //新闻类别
        if (communityNewsEntity.getType() != null && !"0".equals(communityNewsEntity.getType())) {
            //品牌宣传
            if (communityNewsEntity.getType() == 1) {
                hql.append(" and c.type <= ?");
                paramsValList.add(communityNewsEntity.getType());
                //新闻资讯
            } else if (communityNewsEntity.getType() == 2) {
                hql.append(" and c.type >= ?");
                paramsValList.add(communityNewsEntity.getType());
            }
        }
        //发布状态
        if (communityNewsEntity.getReleaseStatus() != null && 999 != communityNewsEntity.getReleaseStatus()) {
            hql.append(" and c.releaseStatus = ?");
            paramsValList.add(communityNewsEntity.getReleaseStatus());
        }
        //发布日期区间1
        if (staDate != null) {
            hql.append(" and c.releaseDate >= ?");
            paramsValList.add(staDate);
        }
        //发布日期区间2
        if (endDate != null) {
            hql.append(" and c.releaseDate <= ?");
            paramsValList.add(endDate);
        }

        hql.append(" and c.status = 1 ");
        hql.append(" order by c.releaseDate DESC");

        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, paramsValList);
        }
        appoints = (List<CommunityNewsEntity>) getHibernateTemplate().find(hql.toString());
        return appoints;
    }

    @Override
    public <E> List<CommunityNewsEntity> queryNewTitles() {
        List<CommunityNewsEntity> appoints = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from CommunityNewsEntity as c where 1=1");
        hql.append(" and c.releaseStatus = 1");//已发布新闻
        hql.append(" and c.status = 1 ");//未删除新闻
        hql.append(" order by c.releaseDate DESC");//取得最新发布的5条数据

        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());

        query.setFirstResult(0);
        query.setMaxResults(5);

        appoints = query.list();
        return appoints;
    }

    /**
     * 按照id查询新闻信息
     * @param communityNewsEntity
     * @return
     */
    @Override
    public CommunityNewsEntity findByCommunityNewsEntityId(CommunityNewsEntity communityNewsEntity) {
        Session session = getCurrentSession();
        String hql = "from CommunityNewsEntity where id = ? and status = 1 and releaseStatus = 1 ORDER BY releaseDate DESC";
        Query query = session.createQuery(hql);
        query.setParameter(0,communityNewsEntity.getId());
        CommunityNewsEntity result = (CommunityNewsEntity)query.uniqueResult();
        return result;
    }

    /**
     * 按照城市id查询项目
     * @param houseProjectEntity
     * @return
     */
    @Override
    public List<HouseProjectEntity> findHouseProjectEntitybyCityId(HouseProjectEntity houseProjectEntity) {
        Session session = getCurrentSession();
        String hql = "from HouseProjectEntity where cityId = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,houseProjectEntity.getCityId());
        List<HouseProjectEntity> result = query.list();
        return result;
    }


}
