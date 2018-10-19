package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.hibernate.IHibernateDao;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.poi.hssf.record.formula.functions.T;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;

import java.io.Serializable;
import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 9:46
 */
public interface CommunityCenterRespository extends IHibernateDao {
    public <E> E get(Class<E> entityClass, Serializable id);
    public <E> List<E> find(String queryString);
    public <E> List<E> find(Class<E> bean);
    public List<?> find(String queryString, Object[] values);
    public <E> E findUniqueEntity(final String queryString,
                                  final Object... params);
    public <E> Serializable save(E entity);


    //前台数据更新
    public boolean updateHouseAppointEntity(CommunityHouseAppointEntity communityHouseAppointEntity) throws Exception;

    /**
     * 通过项目Code检索楼盘信息列表
     * @param projectCode 项目Code
     * @return List<CommunityOverviewEntity>
     */
    List<CommunityOverviewEntity> queryCommunityByProject(String projectCode);

    /**
     * 通过城市Id检索楼盘信息列表
     * @param cityId 城市Id
     * @return List<CommunityOverviewEntity>
     */
    List<CommunityOverviewEntity> queryCommunityByCity(String cityId);
}
