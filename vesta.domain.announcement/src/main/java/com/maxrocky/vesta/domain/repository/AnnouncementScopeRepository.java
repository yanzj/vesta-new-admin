package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.CommunityNewScope;

import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:43
 * Describe:
 */
public interface AnnouncementScopeRepository extends BaseRepository {

    /**
     * 根据通告id查询通告所有范围信息
     * @param id
     * @return
     */
   public  List<Object[]> queryByAnnouncementId(String id);

    /**
     * 根据通告id删除通告关联范围
     * @param id
     */
    public void deleteAnnouncementScopeByAnnouncementId(String id);
    /**=============================================================**/

    /**
     * 根据新闻id查询新闻所有范围信息
     * @param id
     * @return
     */
    public List<Object[]> queryByCommunitNewsId(String id);

    /**
     * 根据新闻id删除所有范围新闻
     * @param id
     */
    public void deleteCommunitNewsId(String id);

    /**
     * 添加\修改新闻范围信息
     * @param communityNewScope
     */
    public void addOrUpdateCommunitNews(CommunityNewScope communityNewScope);

 /**
  * 按照城市（pinyingCode）查询新闻信息
  * @param communityNewScope
  * @return
  */
    public List<CommunityNewScope> findByProjectIdAndNewsId(CommunityNewScope communityNewScope);
}
