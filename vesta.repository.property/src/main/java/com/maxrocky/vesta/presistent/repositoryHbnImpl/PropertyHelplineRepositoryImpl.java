package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.PropertyHelplineRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/3/31.
 * 物业热线服务电话数据操作实现类
 */
@Repository
public class PropertyHelplineRepositoryImpl extends HibernateDaoImpl implements PropertyHelplineRepository {
    /* mapper */
    @Autowired
    private MapperFacade mapper;
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    /**
     * 通过便民信息Id检索发布范围列表
     * @param propertyHelplineId 便民信息Id
     * @return  List<PropertyHelplineScopeEntity>
     */
    public List<PropertyHelplineScopeEntity> getScopeByHelpline(String propertyHelplineId){
        Session session = getCurrentSession();
        String hql = " from PropertyHelplineScopeEntity where propertyHelplineId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0,propertyHelplineId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * CreatedBy:liudongxin
     * Description:获取所有城市
     * ModifyBy:
     */
    @Override
    public List<String> getAllCity() {
        String hql="SELECT DISTINCT city FROM PropertyHelplineEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<String> cityList=query.list();
        return cityList;
    }

    @Override
    public List<String> getAllCitys() {
        String hql="SELECT DISTINCT city FROM PropertyHelplineScopeEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<String> cityList=query.list();
        return cityList;
    }

    /**
     * CreatedBy:liudongxin
     * param:城市(默认为北京)
     * Description:通过城市获取所有小区
     * ModifyBy:
     */
    @Override
    public List<Object[]> getAllCommunityName(String city) {
        /*String hql="SELECT DISTINCT projectName,projectId FROM PropertyHelplineEntity WHERE city='"+city+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<Object[]> communityList=query.list();
        return communityList;*/
        String hql="SELECT DISTINCT projectName,projectId FROM PropertyHelplineScopeEntity WHERE city like'"+city+"%'";
        Query query = getCurrentSession().createQuery(hql);
        List<Object[]> communityList=query.list();
        return communityList;
    }

    @Override
    public List<Object[]> getAllCommunityNames(String city) {
        String hql="SELECT DISTINCT projectName,projectId FROM PropertyHelplineScopeEntity WHERE city like'"+city+"%'";
        Query query = getCurrentSession().createQuery(hql);
        List<Object[]> communityList=query.list();
        return communityList;
    }

    /**
     * CreatedBy:liudongxin
     * param:小区id
     * Description:通过小区id获取服务内容为空的热线电话
     * ModifyBy:
     */
    @Override
    public List<PropertyHelplineEntity> getServicePhone(String projectId) {
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        // sql.append(" from PropertyHelplineEntity c where 1=1 and c.status = 1 and c.releaseStatus = 1 ");
        sql.append(" from PropertyHelplineEntity c where 1=1 and c.state = 1 and c.phone!='' and (c.content='' or c.content is null) ");
        sql.append(" and c.id in (select os.propertyHelplineId from PropertyHelplineScopeEntity os where os.projectId = ? ) ");
        sql.append(" ORDER BY c.sortNum ");
        Query query = session.createQuery(sql.toString());
        query.setParameter(0, projectId);
        List list = query.list();

        session.flush();
        session.close();
        List<PropertyHelplineEntity> li = new ArrayList<>();
        if (!list.isEmpty()) {
            li.addAll(list);
        }
        return li;
        /*
        String hql="FROM PropertyHelplineEntity WHERE projectId='"+projectId+"' AND state='1' AND phone!=''";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyHelplineEntity> lineList=query.list();
        return lineList;*/
    }

    /**
     * CreatedBy:liudongxin
     * param:小区id
     * Description:通过小区id获取服务内容为空的热线电话
     * ModifyBy:
     */
    @Override
    public List<PropertyHelplineEntity> getServicePhoneWeiXin(String projectId) {
        /*String hql="FROM PropertyHelplineEntity WHERE projectId='"+projectId+"' AND state='1' AND phone!=''";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyHelplineEntity> lineList=query.list();
        return lineList;*/
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        // sql.append(" from PropertyHelplineEntity c where 1=1 and c.status = 1 and c.releaseStatus = 1 ");
        sql.append(" from PropertyHelplineEntity c where 1=1 and c.state = 1 and c.phone!='' and (c.content='' or c.content is null) ");
        sql.append(" and c.id in (select os.propertyHelplineId from PropertyHelplineScopeEntity os where os.projectId = ? ) ");
        sql.append(" ORDER BY c.sortNum ");
        Query query = session.createQuery(sql.toString());
        query.setParameter(0, projectId);
        List list = query.list();

        session.flush();
        session.close();
        List<PropertyHelplineEntity> li = new ArrayList<>();
        if (!list.isEmpty()) {
            li.addAll(list);
        }
        return li;
    }

    /**
     * CreatedBy:liudongxin
     * param:小区id
     * Description:通过小区id获取服务内容不为空的热线电话
     * ModifyBy:
     */
    @Override
    public List<PropertyHelplineEntity> getServiceInfo(String projectId) {
        Session session = getCurrentSession();
        StringBuffer hql = new StringBuffer();
        hql.append(" from PropertyHelplineEntity c where 1=1 and c.state = 1 and c.content!='' and (c.phone='' or c.phone is null) ");
        hql.append(" and c.id in (select os.propertyHelplineId from PropertyHelplineScopeEntity os where os.projectId = ? ) ");
        hql.append(" ORDER BY c.sortNum ");
        Query query = session.createQuery(hql.toString());
        query.setParameter(0, projectId);
        List<PropertyHelplineEntity> list = query.list();
        session.flush();
        session.close();
        return list;

        /*String hql="FROM PropertyHelplineEntity WHERE projectId='"+projectId+"' AND state='1' AND (phone='' OR phone IS NULL)";

        Query query = getCurrentSession().createQuery(hql);
        List<PropertyHelplineEntity> lineList=query.list();
        return lineList;*/
       /* Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        // sql.append(" from PropertyHelplineEntity c where 1=1 and c.status = 1 and c.releaseStatus = 1 ");
        sql.append(" from PropertyHelplineEntity c where 1=1 and c.state = 1");
        sql.append(" and c.id in (select os.propertyHelplineId from PropertyHelplineScopeEntity os where os.projectId = ? ) ");
        sql.append(" AND (phone='' OR phone IS NULL) ");
        sql.append(" ORDER BY c.createDate DESC ");
        Query query = session.createQuery(sql.toString());
        query.setParameter(0,projectId);
        List list = query.list();
        session.flush();
        session.close();
        return list;*/
    }

    @Override
    public List<PropertyHelplineEntity> getServiceInfoss(String projectId) {
        /*Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        // sql.append(" from PropertyHelplineEntity c where 1=1 and c.status = 1 and c.releaseStatus = 1 ");
        sql.append(" from PropertyHelplineEntity c where 1=1 and c.state = 1");
        sql.append(" and c.id in (select os.propertyHelplineId from PropertyHelplineScopeEntity os where os.projectId = ? ) ");
        //sql.append(" AND (c.phone='' OR c.phone IS NULL)");
        sql.append(" ORDER BY c.createDate DESC ");
        Query query = session.createQuery(sql.toString());
        query.setParameter(0,projectId);
        List list = query.list();
        session.flush();
        session.close();
        return list;*/
        Session session = getCurrentSession();
        StringBuffer hql = new StringBuffer();
        hql.append(" from PropertyHelplineEntity c where 1=1 and c.state = 1 and c.content!='' and (c.phone='' or c.phone is null) ");
        hql.append(" and c.id in (select os.propertyHelplineId from PropertyHelplineScopeEntity os where os.projectId = ? ) ");
        hql.append(" ORDER BY c.sortNum ");
        Query query = session.createQuery(hql.toString());
        query.setParameter(0, projectId);
        List<PropertyHelplineEntity> list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public List<PropertyHelplineScopeEntity> getServiceInfos(String projectId) {
        String hql="FROM PropertyHelplineScopeEntity WHERE propertyHelplineId='"+projectId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyHelplineScopeEntity> lineList=query.list();
        return lineList;
    }

    /**
     * CreatedBy:liudongxin
     * param:热线id
     * Description:通过热线id获取服务信息
     * ModifyBy:
     */
    @Override
    public PropertyHelplineEntity getServiceInfoById(String id) {
        String hql="FROM PropertyHelplineEntity WHERE id='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyHelplineEntity> lineList=query.list();
        if(lineList.size()>0) {
            return lineList.get(0);
        }
        return null;
    }

    @Override
    public List<PropertyHelplineScopeEntity> getScope(String id) {
        return null;
    }


    /**
     * CreatedBy:liudongxin
     * param:propertyHelpline
     * param:webPage
     * Description:获取热线服务信息
     * return
     */
    @Override
    public List<PropertyHelplineEntity> getHotLineList(PropertyHelplineEntity helpline, WebPage webPage,String roleScopes) {
        StringBuffer hql = new StringBuffer("from PropertyHelplineEntity where state='1'");
        //Wyd_20170407_注:这个地方我也不懂了，便民信息发布这详情中已经包含城市项目信息,但也有一张便民信息的发布范围表
        //如下:数据权限范围所查表为发布范围表,城市项目检索查表为详情中的城市项目字段,这就冗余了吧?
        //数据权限范围条件
        if (!roleScopes.equals("") && !roleScopes.contains("all")){
            hql.append(" and id in (select distinct sc.propertyHelplineId from PropertyHelplineScopeEntity sc ");
            hql.append(" where sc.cityId = '0' or sc.projectId in ("+roleScopes.substring(0,roleScopes.length()-1)+") ) ");
        }
        List<Object> params = new ArrayList<Object>();
        //城市
//        if(!StringUtil.isEmpty(helpline.getCity())){
//            hql.append(" and city like ?");
//            params.add("%"+ helpline.getCity() +"%");
//        }
        //项目
        if(!StringUtil.isEmpty(helpline.getProjectId())){
            hql.append(" and projectId in (" +helpline.getProjectId().substring(0, helpline.getProjectId().length()-1)+")");
        }
        //名称
        if(!StringUtil.isEmpty(helpline.getGroupName())){
            hql.append(" and groupName like ?");
            params.add("%"+ helpline.getGroupName() +"%");
        }
        //电话
        if(!StringUtil.isEmpty(helpline.getPhone())){
            hql.append(" and phone like ?");
            params.add("%"+ helpline.getPhone() +"%");
        }
        //内容
        if(!StringUtil.isEmpty(helpline.getContent())){
            hql.append(" and content like ?");
            params.add("%"+ helpline.getContent() +"%");
        }
        //发布时间
        if(!StringUtil.isEmpty(helpline.getCreateBy())){
            hql.append(" and createDate >= ?");
            params.add(DateUtils.parse(helpline.getCreateBy() + " 00:00:00"));
        }
        if(!StringUtil.isEmpty(helpline.getModifyBy())){
            hql.append(" and createDate <= ?");
            params.add(DateUtils.parse(helpline.getModifyBy() + " 23:59:59"));
        }
        hql.append(" ORDER BY sortNum asc, createDate desc");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<PropertyHelplineEntity> lineList=(List<PropertyHelplineEntity>)getHibernateTemplate().find(hql.toString());
        return lineList;
    }

    /**
     * 保存
     * param:propertyHelpline
     * return
     */
    @Override
    public boolean saveHotLine(PropertyHelplineEntity propertyHelpline) {
        Session session = getCurrentSession();
        session.save(propertyHelpline);
        session.flush();
        return true;
    }

    /**
     * 修改/删除
     * param:propertyHelpline
     * return
     */
    @Override
    public boolean updateHotLine(PropertyHelplineEntity propertyHelpline) {
        Session session = getCurrentSession();
        session.update(propertyHelpline);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean updateHotLines(PropertyHelplineScopeEntity propertyHelplineScope) {
        Session session = getCurrentSession();
        session.update(propertyHelplineScope);
        session.flush();
        session.close();
        return true;
    }


    @Override
    public List<Object[]> queryByAnnouncementId(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select id,city_name,is_all,project_name,city_id,project_id from propertyHelpline_scope s where s.propertyHelplineId = ?");
        sqlQuery.setParameter(0,id);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public List<PropertyHelplineEntity> queryCommunityByProject(String projectCode) {
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        // sql.append(" from PropertyHelplineEntity c where 1=1 and c.status = 1 and c.releaseStatus = 1 ");
        sql.append(" from PropertyHelplineEntity c where 1=1 and c.state = 1");
        sql.append(" and c.id in (select os.propertyHelplineId from PropertyHelplineScopeEntity os where os.projectId = ? ) ");
        sql.append(" ORDER BY c.createDate DESC ");
        Query query = session.createQuery(sql.toString());
        query.setParameter(0,projectCode);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public HouseProjectEntity queryProByProjectName(String projectName) {
        String hql="FROM HouseProjectEntity WHERE name='"+projectName+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseProjectEntity> lineList=query.list();
        if(lineList.size()>0) {
            return lineList.get(0);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> queryProjectByHotLineId(String id) {
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select os.project_id projectCode,os.project_name projectName ");
        sql.append(" from propertyHelpline_scope os where os.propertyHelplineId = ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, id);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取所有项目
    */
    @Override
    public List<HouseProjectEntity> getAllProject() {
        StringBuffer hql = new StringBuffer(" from HouseProjectEntity h");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<HouseProjectEntity> list = query.list();
        return list;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取某城市下全部项目
    */
    @Override
    public List<HouseProjectEntity> getAllProjectByCityNum(String cityNum) {
        StringBuffer hql = new StringBuffer(" from HouseProjectEntity h where h.cityId=:cityId");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("cityId", cityNum);
        List<HouseProjectEntity> list = query.list();
        return list;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 通过项目编号获取项目名称
    */
    @Override
    public String getProjectNameById(String projectNum) {
        StringBuffer hql = new StringBuffer("from HouseProjectEntity h where h.pinyinCode=:projectNum");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("projectNum", projectNum);
        List<HouseProjectEntity> list = query.list();
        String projectName = new String();
        if (!list.isEmpty()) {
            HouseProjectEntity houseProjectEntity = list.get(0);
            if (houseProjectEntity!=null) {
                projectName = houseProjectEntity.getOriginName();
            }
        }
        return projectName;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 通过城市id获取城市名称
    */
    @Override
    public String getCityNameById(String cityId) {
        StringBuffer hql = new StringBuffer("from HouseCityEntity h where h.id=:cityId");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("cityId", cityId);
        List<HouseCityEntity> list = query.list();
        String cityName = new String();
        if (!list.isEmpty()) {
            HouseCityEntity houseCityEntity = list.get(0);
            if (houseCityEntity!=null) {
                cityName = houseCityEntity.getCityName();
            }
        }
        return cityName;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 通过城市名称拿到城市id
    */
    @Override
    public String getCityIdByName(String cityName) {
        StringBuffer hql = new StringBuffer("from HouseCityEntity h where h.cityName=:cityName");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("cityName", cityName);
        List<HouseCityEntity> list = query.list();
        String cityId = new String();
        if (!list.isEmpty()) {
            HouseCityEntity houseCityEntity = list.get(0);
            if (houseCityEntity!=null) {
                cityId = houseCityEntity.getId();
            }
        }
        return cityId;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取便民信息排序序号最大值
    */
    @Override
    public Integer getSortNumber(String projectId) {
        Integer sortNum = null;
        if ("0".equals(projectId)) {
            StringBuffer hql = new StringBuffer("select max(p.sortNum) from PropertyHelplineEntity p");
            Query query = getCurrentSession().createQuery(hql.toString());
            sortNum = (Integer)query.uniqueResult();
        }else {
            StringBuffer hql = new StringBuffer("select max(p.sortNum) from PropertyHelplineEntity p where p.projectId=:projectId");
            Query query = getCurrentSession().createQuery(hql.toString());
            query.setParameter("projectId", projectId);
            sortNum = (Integer)query.uniqueResult();
        }
        return sortNum;
    }
}
