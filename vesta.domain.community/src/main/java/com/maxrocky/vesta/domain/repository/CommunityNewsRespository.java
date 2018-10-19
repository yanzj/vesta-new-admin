package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.CommunityNewScope;
import com.maxrocky.vesta.domain.model.CommunityNewsEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.hibernate.IHibernateDao;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 9:46
 */
public interface CommunityNewsRespository extends IHibernateDao {
    <E> E get(Class<E> entityClass, Serializable id);
    <E> List<E> find(String queryString);
    <E> List<E> find(Class<E> bean);
    List<?> find(String queryString, Object[] values);
    <E> E findUniqueEntity(final String queryString,
                                  final Object... params);
    <E> Serializable save(E entity);
    <E> void update(E entity);
    <E> void saveOrUpdate(E entity);


    /**
     * 通过品牌资讯Id检索发布范围列表
     * @param communityDetailId
     * @return  List<CommunityNewScope>
     */
    List<CommunityNewScope> getScopeByCommunityNew(String communityDetailId);

    /**
     * 根据实体类进行保存或者更新
     * @param entity
     * @param <E>
     */
    <E> void saveOrUpdateNews(E entity);

    /**
     * 分页查询所有的新闻信息
     * @param communityNewsEntity
     * @param webPage
     * @param staDate
     * @param endDate
     * @param <E>
     * @return
     */
    <E> List<CommunityNewsEntity> queryAllByPage(CommunityNewsEntity communityNewsEntity, WebPage webPage, Date staDate, Date endDate,String roleScopes);

    /**
     * 重写品牌新闻查询方法   Wyd_20170316
     * @param params    参数
     * @param webPage   分页
     * @return  List<CommunityNewsEntity>
     */
    List<CommunityNewsEntity> queryAllByPage(Map<String,Object> params, WebPage webPage);

    /**
     * 获取排序最大值进行添加
     * @return
     */
    Integer getMaxOrderNum();


    /**
     *查询最后发布的新闻
     * @param <E>
     * @return
     */
    <E> List<CommunityNewsEntity> queryNewTitles();

    /**
     * 按照id查询新闻信息
     * @param communityNewsEntity
     * @return
     */
    CommunityNewsEntity findByCommunityNewsEntityId(CommunityNewsEntity communityNewsEntity);

    /**
     * 按照城市id查询项目
     * @param houseProjectEntity
     * @return
     */
    List<HouseProjectEntity> findHouseProjectEntitybyCityId(HouseProjectEntity houseProjectEntity);

}
