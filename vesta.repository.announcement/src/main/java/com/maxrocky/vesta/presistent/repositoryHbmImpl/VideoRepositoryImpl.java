package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.VideoHQEntity;
import com.maxrocky.vesta.domain.repository.VideoRepository;
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
 * 视频功能模块数据持久层实现类
 * Created by WeiYangDong on 2017/9/27.
 */
@Repository
public class VideoRepositoryImpl implements VideoRepository{

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
     * 删除Entity
     * @param entity
     */
    public <E> void delete(E entity){
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    /**
     * 通过主键ID获取总部视频详情
     * @param id 主键ID
     * @return VideoHQEntity
     */
    public VideoHQEntity getVideoHQById(String id) {
        Session session = getCurrentSession();
        Query query = session.createQuery(" FROM VideoHQEntity WHERE id = ? ").setParameter(0,id);
        VideoHQEntity videoHQEntity = (VideoHQEntity)query.uniqueResult();
        session.flush();
        session.close();
        return videoHQEntity;
    }

    /**
     * 获取总部视频列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<VideoHQEntity>
     */
    public List<VideoHQEntity> getVideoHQList(Map<String, Object> paramsMap, WebPage webPage) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM VideoHQEntity vhq WHERE 1=1");
        List<Object> paramsList = new ArrayList<>();
        //视频名称
        Object videoName = paramsMap.get("videoName");
        if (null != videoName && !"".equals(videoName)){
            hql.append(" and vhq.videoName like ? ");
            paramsList.add("%"+videoName.toString()+"%");
        }
        //视频ID
        Object videoId = paramsMap.get("videoId");
        if (null != videoId && !"".equals(videoId)){
            hql.append(" and vhq.videoId = ? ");
            paramsList.add(videoId.toString());
        }
        //状态
        Object releaseStatus = paramsMap.get("releaseStatus");
        if (null != releaseStatus && !"".equals(releaseStatus)){
            hql.append(" and vhq.releaseStatus = ? ");
            paramsList.add(releaseStatus);
        }
        //发布日期-开始时间
        Object releaseStartDate = paramsMap.get("releaseStartDate");
        if (null != releaseStartDate && !"".equals(releaseStartDate)){
            hql.append(" and date_format(vhq.releaseDate,'%Y-%m-%d') >= "+"'"+releaseStartDate+"'");
        }
        //发布日期-结束时间
        Object releaseEndDate = paramsMap.get("releaseEndDate");
        if (null != releaseEndDate && !"".equals(releaseEndDate)){
            hql.append(" and date_format(vhq.releaseDate,'%Y-%m-%d') <= "+"'"+releaseEndDate+"'");
        }
        hql.append(" order by vhq.createOn desc ");
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
}
