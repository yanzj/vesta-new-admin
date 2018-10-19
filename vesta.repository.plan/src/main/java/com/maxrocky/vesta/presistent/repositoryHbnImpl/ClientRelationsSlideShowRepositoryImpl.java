package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ClientRelationsSlideShowEntity;
import com.maxrocky.vesta.domain.repository.ClientRelationsSlideShowRepository;
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
 * 客关轮播图DAO层接口实现
 * Created by Administrator on 2018/6/11 0011.
 */
@Repository
public class ClientRelationsSlideShowRepositoryImpl implements ClientRelationsSlideShowRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<ClientRelationsSlideShowEntity> getSlideShowList(Map<String, Object> paramsMap, WebPage webPage) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM ClientRelationsSlideShowEntity crss WHERE 1=1 AND state='1' ");
        List<Object> paramsList = new ArrayList<>();
        Object type = paramsMap.get("slideShowType");
        if (null != type && !"".equals(type)){
            hql.append(" AND crss.slideShowType =? ");
            paramsList.add(type.toString());
        }
        Object slideShowTitle = paramsMap.get("slideShowTitle");
        if (null != slideShowTitle && !"".equals(slideShowTitle)){
            hql.append(" AND crss.slideShowTitle like ? ");
            paramsList.add("%"+slideShowTitle.toString()+"%");
        }
        Object createName = paramsMap.get("createName");
        if (null != createName && !"".equals(createName)){
            hql.append(" AND crss.createName like ? ");
            paramsList.add("%"+createName.toString()+"%");
        }
        Object createDate = paramsMap.get("createDate");
        if (null != createDate && !"".equals(createDate)){
            hql.append( " AND date_format(crss.createDate,'%Y-%m-%d') = "+"'"+createDate+"' " );
        }
        Object isSlideShow = paramsMap.get("isSlideShow");
        if (null != isSlideShow && !"".equals(isSlideShow)){
            hql.append(" AND crss.isSlideShow =?  ");
            paramsList.add(isSlideShow.toString());
        }
        hql.append(" ORDER BY crss.createDate DESC ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            query.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }else {
            List list = query.list();
            session.flush();
            session.close();
            return list;
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public ClientRelationsSlideShowEntity getSlideShowById(String slideShowId) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM ClientRelationsSlideShowEntity WHERE 1=1");
        hql.append(" AND slideShowId=:slideShowId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("slideShowId",slideShowId);
        ClientRelationsSlideShowEntity clientRelationsSlideShowEntity =(ClientRelationsSlideShowEntity) query.uniqueResult();
        return clientRelationsSlideShowEntity;
    }

    @Override
    public void saveSlideShowEntity(ClientRelationsSlideShowEntity clientRelationsSlideShowEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(clientRelationsSlideShowEntity);
        session.flush();
        session.close();
    }

    @Override
    public boolean getIsSlideShow() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COUNT(1) FROM qc_slide_show WHERE 1=1");
        sql.append(" AND IS_SLIDE_SHOW='1' AND STATE='1' ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        int count=((Number)query.uniqueResult()).intValue();
        if(count<5){
            return true;
        }else {
            return false;
        }
    }
}
