package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.CommunityNewsDto;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/4/20
 * Time: 10:10
 * Describe:
 */
public interface CommunityNewsService {

    /**
     * 通过社区公告Id检索发布项目范围
     */
    List<Map<String,Object>> getProjectScopeByCommunityDetailId(String communityDetailId);

    //新增/修改 新闻详情
    public <E> void saveOrUpdateNews(CommunityNewsDto communityNewsDto);
    //新增/修改 新闻详情
    public <E> void saveOrUpdateNews(UserPropertyStaffEntity userPropertystaffEntity,CommunityNewsDto communityNewsDto);
    //分页查询所有的新闻信息
    public <E> List<CommunityNewsDto> queryAllByPage(CommunityNewsDto communityNewsDto,WebPage webPage) throws GeneralException ;
    //逻辑删除新闻详情
    public <E> void deleteNews(CommunityNewsDto communityNewsDto);
    //逻辑删除新闻详情
    public <E> void deleteNews(UserPropertyStaffEntity userPropertystaffEntity,CommunityNewsDto communityNewsDto);
    //获取对象
    public <E> E get(Class<E> entityClass, Serializable id);
    //查询最后发布的新闻
    public ApiResult queryNewTitles(UserInfoEntity userInfo) throws GeneralException ;

    /**
     * Excel导出
     * @param user
     * @param httpServletResponse
     * @param webPage
     * @return
     * @throws IOException
     */
    public String exportExcel(UserPropertyStaffEntity user, HttpServletResponse httpServletResponse,CommunityNewsDto communityNewsDto,WebPage webPage,HttpServletRequest httpServletRequest) throws IOException;

}
