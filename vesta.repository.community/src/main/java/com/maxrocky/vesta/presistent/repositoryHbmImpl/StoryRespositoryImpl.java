package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.StoryEntity;
import com.maxrocky.vesta.domain.model.StoryScopeEntity;
import com.maxrocky.vesta.domain.repository.StoryRespository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/3/22 16:55
 * @deprecated 故事荟功能模块Dao实现类
 */
@Repository
public class StoryRespositoryImpl implements StoryRespository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){ return sessionFactory.openSession(); }

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
     * 获取故事荟列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<StoryEntity>
     */
    public List<StoryEntity> getStoryInfoList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder("FROM StoryEntity sp WHERE 1=1");
        //数据权限
        Object roleScopes = paramsMap.get("roleScopes");
        String sql_1 = " and sp.id in (select distinct sc.storyId from StoryScopeEntity sc where 1=1 ";
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            if (hql.indexOf(sql_1) < 0){
                hql.append(sql_1);
            }
            hql.append(" and sc.cityId = '0' or sc.projectId in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+") ");
        }
        //城市范围
        Object cityProjects = paramsMap.get("cityProjects");
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
        //项目
        Object projectCode = paramsMap.get("projectNum");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            if (hql.indexOf(sql_1) < 0){
                hql.append(sql_1);
            }
            hql.append(" and sc.projectId = '" + projectCode + "'");
        }
        if (hql.indexOf(sql_1) > 0){
            hql.append(" ) ");
        }
        List<Object> paramsList = new ArrayList<>();
        //主键ID
        Object id = paramsMap.get("id");
        if (null != id && !"".equals(id.toString())){
            hql.append(" AND sp.id = ? ");
            paramsList.add(id.toString());
        }
        //故事标题
        Object title = paramsMap.get("title");
        if (null != title && !"".equals(title)){
            hql.append(" AND sp.title like ? ");
            paramsList.add("%"+title.toString()+"%");
        }
        //发布人
        Object releasePerson = paramsMap.get("releasePerson");
        if (null != releasePerson && !"".equals(releasePerson)){
            hql.append(" AND sp.releasePerson like ? ");
            paramsList.add("%"+releasePerson.toString()+"%");
        }
        //发布状态
        Object releaseStatus = paramsMap.get("releaseStatus");
        if (null != releaseStatus){
            hql.append(" AND sp.releaseStatus = ? ");
            paramsList.add(releaseStatus);
        }
        //发布日期区间1
        Object releaseStaDate = paramsMap.get("releaseStaDate");
        if (releaseStaDate != null && !"".equals(releaseStaDate)) {
            hql.append(" and sp.releaseDate >= "+"'"+releaseStaDate+"'");
//            paramsList.add("'"+releaseStaDate+"'");
        }
        //发布日期区间2
        Object releaseEndDate = paramsMap.get("releaseEndDate");
        if (releaseEndDate != null && !"".equals(releaseEndDate)) {
            hql.append(" and sp.releaseDate <= "+"'"+releaseEndDate+"'");
        }
        hql.append(" and sp.infoStatus = 0 ");
        hql.append(" ORDER BY sp.createOn desc");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            query.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 删除故事荟发布范围
     * @param storyId 故事ID
     */
    public void delStoryInfoScopeById(String storyId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("DELETE FROM StoryScopeEntity WHERE storyId = ? ");
        query.setParameter(0,storyId);
        query.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 通过故事ID获取故事荟发布范围
     * @param storyId 故事ID
     * @return List<StoryScopeEntity>
     */
    public List<StoryScopeEntity> getStoryInfoScopeById(String storyId){
        StringBuilder hql = new StringBuilder("FROM StoryScopeEntity sc WHERE sc.storyId = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, storyId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }
}
