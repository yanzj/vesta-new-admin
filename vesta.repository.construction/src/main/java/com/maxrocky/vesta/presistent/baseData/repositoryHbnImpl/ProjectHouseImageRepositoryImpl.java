package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectHouseImageEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectHouseImageRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 27978 on 2016/11/1.
 */
@Repository
public class ProjectHouseImageRepositoryImpl extends HibernateDaoImpl implements ProjectHouseImageRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 新增户型图
    */
    @Override
    public void saveOrUpdateHouseImg(ProjectHouseImageEntity projectHouseImageEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(projectHouseImageEntity);
        session.flush();
        session.close();
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 根据楼层id获取对应的户型图
     */
    @Override
    public ProjectHouseImageEntity getImageByFloorId(String floorId) {
        StringBuffer hql = new StringBuffer(" from ProjectHouseImageEntity p where p.floorId=:floorId and p.imgState='1'");
        Query query = this.getCurrentSession().createQuery(hql.toString());
        query.setParameter("floorId", floorId);
        ProjectHouseImageEntity projectHouseImageEntity = (ProjectHouseImageEntity) query.uniqueResult();
        return projectHouseImageEntity;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 获取最新的户型图
     */
    @Override
    public ProjectHouseImageEntity getHouseImg() {
        StringBuffer hql = new StringBuffer("from ProjectHouseImageEntity p where p.imgState='1' and p.imgUrl!='' order by p.modifyOn desc");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<ProjectHouseImageEntity> list = query.list();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
