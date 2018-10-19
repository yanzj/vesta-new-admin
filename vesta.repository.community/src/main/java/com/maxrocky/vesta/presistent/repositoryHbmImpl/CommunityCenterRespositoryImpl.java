package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.CommunityCenterRespository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:04
 */
@Repository
public class CommunityCenterRespositoryImpl extends HibernateDaoImpl implements CommunityCenterRespository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 更新交付预约
     * @param communityHouseAppointEntity
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateHouseAppointEntity(CommunityHouseAppointEntity communityHouseAppointEntity) throws Exception {
        Session session = getCurrentSession();
        session.update(communityHouseAppointEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 通过项目Code检索楼盘信息列表
     * @param projectCode 项目Code
     * @return List<CommunityOverviewEntity>
     */
    public List<CommunityOverviewEntity> queryCommunityByProject(String projectCode){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" from CommunityOverviewEntity c where 1=1 and c.status = 1 and c.releaseStatus = 1 ");
        sql.append(" and c.id in (select os.communityOverviewId from CommunityOverviewScopeEntity os where os.projectCode = ? ) ");
        sql.append(" ORDER BY c.orderDate DESC ");
        Query query = session.createQuery(sql.toString());
        query.setParameter(0, projectCode);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过城市Id检索楼盘信息列表
     * @param cityId 城市Id
     * @return List<CommunityOverviewEntity>
     */
    public List<CommunityOverviewEntity> queryCommunityByCity(String cityId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" from CommunityOverviewEntity c where 1=1 and c.status = 1 and c.releaseStatus = 1 ");
        sql.append(" and c.id in (select distinct os.communityOverviewId from CommunityOverviewScopeEntity os where os.projectCode in ");
        sql.append(" (select hp.pinyinCode from HouseProjectEntity hp where hp.cityId = ?)) ");
        sql.append(" ORDER BY c.orderDate DESC ");
        Query query = session.createQuery(sql.toString());
        query.setParameter(0,cityId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }


}
