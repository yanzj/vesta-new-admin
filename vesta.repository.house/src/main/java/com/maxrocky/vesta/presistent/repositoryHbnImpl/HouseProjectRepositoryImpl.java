package com.maxrocky.vesta.presistent.repositoryHbnImpl;
import com.maxrocky.vesta.domain.model.BuildingMappingTimeEntity;
import com.maxrocky.vesta.domain.model.HouseCityEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.repository.HouseProjectRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/18 9:52.
 * Describe:项目Repository接口实现类
 */
@Repository
public class HouseProjectRepositoryImpl extends HibernateDaoImpl implements HouseProjectRepository{

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:获取状态正常项目列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 09:56:52
     */
    @Override
    public List<HouseProjectEntity> getListOfNormal() {
        String hql = "FROM HouseProjectEntity WHERE state = :state ORDER BY pinyinCode ASC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("state", HouseProjectEntity.STATE_NORMAL);
        return query.list();
    }

    @Override
    public List<HouseProjectEntity> getProjectList(String cityNum) {
        String hql = "FROM HouseProjectEntity WHERE state = :state and cityNum=:cityNum ORDER BY pinyinCode ASC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("state", HouseProjectEntity.STATE_NORMAL);
        query.setParameter("cityNum",cityNum);
        return query.list();
    }

    /**
     * Describe:获取状态正常的城市列表
     * CreateBy:Magic
     * CreateOn:2016-11-09 09:56:23
     */
    @Override
    public List<HouseCityEntity> getCityList() {
        String hql = "FROM HouseCityEntity WHERE 1=1 ORDER BY cityCode ASC";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    /**
     * Describe:根据项目Id获取项目
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:06:07
     */
    @Override
    public HouseProjectEntity get(String projectId) {
        return (HouseProjectEntity) getCurrentSession().get(HouseProjectEntity.class, projectId);
    }

    @Override
    public BuildingMappingTimeEntity getprojectbybuild(String projectId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM BuildingMappingTimeEntity WHERE 1 = 1");
        hql.append(" AND graded = '1' ");
        hql.append(" AND currentId = :companyId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("companyId", projectId);
        List<BuildingMappingTimeEntity> houseProjectEntityList = query.list();
        if (houseProjectEntityList.size() == 0) {
            return null;
        }
        return houseProjectEntityList.get(0);

    }

    /**
     * Describe:根据公司Id、项目名称获取项目
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:08:47
     */
    @Override
    public HouseProjectEntity getByCompanyIdAndProjectName(String companyId, String projectName) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseProjectEntity WHERE 1 = 1");
        hql.append(" AND name = :name ");
        hql.append(" AND companyId = :companyId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("name", projectName);
        query.setParameter("companyId", companyId);
        List<HouseProjectEntity> houseProjectEntityList = query.list();
        if (houseProjectEntityList.size() == 0) {
            return null;
        }
        return houseProjectEntityList.get(0);
    }

    /**
     * Describe:根据项目id去查询项目和楼栋信息信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28 17:40
     */
    @Override
    public HouseProjectEntity getInfoByProjectId(String ProjectId) {
        String hql = "FROM HouseProjectEntity WHERE id='" + ProjectId + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseProjectEntity> projectList = query.list();
        if (projectList.size() > 0) {
            return projectList.get(0);
        }
        return null;
    }

    /**
     * Describe:创建项目信息
     * CreatedBy:langmafeng
     * Describe:2016-04-28 18:11
     *
     * @param houseProjectEntity
     */
    @Override
    public void updateProjectInfo(HouseProjectEntity houseProjectEntity) {
        Session session = getCurrentSession();
        session.update(houseProjectEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateBUildMapingTime(BuildingMappingTimeEntity BuildingMappingTimeEntity) {
        Session session = getCurrentSession();
        session.update(BuildingMappingTimeEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:创建项目信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28 18:11
     *
     * @param houseProjectEntity
     */
    @Override
    public void create(HouseProjectEntity houseProjectEntity) {
        Session session = getCurrentSession();
        session.save(houseProjectEntity);
        session.flush();
        session.close();
    }

    /**
     * 通过城市id获取项目
     * param:城市id
     */
    @Override
    public List<HouseProjectEntity> getProject(String cityId) {
        String hql="FROM HouseProjectEntity WHERE cityId='"+cityId+"' order by createOn asc";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseProjectEntity> projectList = query.list();
        return projectList;
    }

    @Override
    public List<HouseProjectEntity> getProjects(HouseProjectEntity houseProjectEntity,WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "FROM HouseProjectEntity WHERE 1=1";
        if(!StringUtil.isEmpty(houseProjectEntity.getName())){
            hql += "and name like '%"+houseProjectEntity.getName()+"%'";
        }
        if(!StringUtil.isEmpty(houseProjectEntity.getOriginName())){
            hql += "and originName like '%"+houseProjectEntity.getOriginName()+"%'";
        }
        hql += "and state = '"+HouseProjectEntity.STATE_NORMAL+"' order by modifyOn desc";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseProjectEntity> houseProjectEntities = query.list();
        if(webPage !=null){
            return this.findByPage(hql, webPage,params);
        }
        return houseProjectEntities;
    }

    /**
     * 根据pinyingCode查询小区信息
     * @param houseProjectEntity
     * @return
     */
    @Override
    public HouseProjectEntity findByPinYinCode(HouseProjectEntity houseProjectEntity) {
        //获取Session对象
        Session session = getCurrentSession();
        //hql语句
        String hql = "from HouseProjectEntity where pinyinCode = ?";
        //创建Query对象
        Query query = session.createQuery(hql);
        query.setParameter(0,houseProjectEntity.getPinyinCode());
        HouseProjectEntity result = (HouseProjectEntity)query.uniqueResult();
        return result;
    }

    @Override
    public HouseProjectEntity getProjectByCode(String pinyinCode) {
        String hql = "FROM HouseProjectEntity WHERE pinyinCode='" + pinyinCode + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseProjectEntity> projectList = query.list();
        if (projectList.size() > 0) {
            return projectList.get(0);
        }
        return null;
    }

    @Override
    public String getsequence(String num) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" NAME from repair_sequence where name='"+num+"'");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        List<Integer> list = query.list();
        if(list != null && !list.isEmpty()){
            return list.get(0)+"";
        }
        return "";
    }

    @Override
    public void saverepair(String num) {
        StringBuffer sql = new StringBuffer(" insert ");
        sql.append(" into repair_sequence values('"+num+"',1,1);");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.executeUpdate();
    }

    @Override
    public String getprintsequence(String num) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" NAME from print_sequence where name='"+num+"'");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        List<Integer> list = query.list();
        if(list != null && !list.isEmpty()){
            return list.get(0)+"";
        }
        return "";
    }

    @Override
    public void saveprint(String num) {
        StringBuffer sql = new StringBuffer(" insert ");
        sql.append(" into print_sequence values('"+num+"',1,1);");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.executeUpdate();
    }

    @Override
    public String getroomsequence(String num) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" NAME from room_sequence where name='"+num+"'");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        List<Integer> list = query.list();
        if(list != null && !list.isEmpty()){
            return list.get(0)+"";
        }
        return "";
    }

    @Override
    public void saveroom(String num) {
        StringBuffer sql = new StringBuffer(" insert ");
        sql.append(" into room_sequence values('"+num+"',1,1);");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.executeUpdate();
    }

    /**
     * 通过房产Id查询其所属项目信息
     * @param roomId    房屋Id
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getProjectByRoom(String roomId){
        StringBuffer sql = new StringBuffer();
        sql.append(" select h.PROJECT_NUM projectId,h.PROJECT_NAME projectName ");
        sql.append(" from house_houseInfo h ");
        sql.append(" where h.ROOM_ID =  ? ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, roomId);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public List<String> getProjectNumsByProjectIds(List<String> projectIds){
        String sql = "select pinyin_code from house_project where 1=1 ";
        if(projectIds != null && !projectIds.isEmpty()){
            sql += "and id in (:projectIds)";
        }else {
            sql += "and id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if(projectIds != null && !projectIds.isEmpty()){
            query.setParameterList("projectIds",projectIds);
        }
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getProNumsByProIds(List<String> projectIds){
        String sql = "select hp.pinyin_code as num,hp.name as name from house_project hp where 1=1 ";
        if(projectIds != null && !projectIds.isEmpty()){
            sql += "and id in (:projectIds)";
        }else {
            sql += "and id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if(projectIds != null && !projectIds.isEmpty()){
            query.setParameterList("projectIds",projectIds);
        }
        List<Object[]> list= query.list();
        return list;
    }

    @Override
    public HouseProjectEntity getInfoByProjectNum(String projectNum) {
        String hql = "FROM HouseProjectEntity WHERE pinyinCode='" + projectNum + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseProjectEntity> projectList = query.list();
        if (projectList.size() > 0) {
            return projectList.get(0);
        }
        return null;
    }
}
