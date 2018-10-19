package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserFeedbackEntity;
import com.maxrocky.vesta.domain.repository.UserFeedbackRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2016/1/21 17:57.
 * Describe:用户意见反馈Repository接口实现类
 */
@Repository
public class UserFeedbackRepositoryImpl extends HibernateDaoImpl implements UserFeedbackRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:创建意见反馈
     * CreateBy:Tom
     * CreateOn:2016-01-21 05:59:39
     */
    @Override
    public void create(UserFeedbackEntity userFeedbackEntity) {
        Session session = getCurrentSession();
        session.save(userFeedbackEntity);
        session.flush();
    }

    @Override
    public List<UserFeedbackEntity> FeedbackList(UserFeedbackEntity userFeedbackEntity, WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM UserFeedbackEntity uf WHERE 1=1");
        List<UserFeedbackEntity> userFeedbackEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        // 初始化 登陆人所负责范围
        hql.append(SqlJoiningTogether.sqlStatement("uf.projectId", userFeedbackEntity.getProjectId()));

        // 不为空则拼接搜索条件  推送范围
        if (null != userFeedbackEntity.getContent() && !"0".equals(userFeedbackEntity.getContent())&&!"".equals(userFeedbackEntity.getContent())) {
            hql.append(" and uf.content like ?");
            params.add("%"+userFeedbackEntity.getContent()+"%");
        }
        if (null != userFeedbackEntity.getUserId() && !"0".equals(userFeedbackEntity.getUserId())&&!"".equals(userFeedbackEntity.getUserId())) {
            hql.append(" and uf.userId like ?");
            params.add("%"+userFeedbackEntity.getUserId()+"%");
        }

        // 不为空则拼接搜索添加 反馈时间
        if (null != userFeedbackEntity.getStartDate()&!"".equals(userFeedbackEntity.getStartDate())) {
            hql.append(" and uf.createOn >= ?");
            params.add(userFeedbackEntity.getStartDate());
        }
        if (null != userFeedbackEntity.getEndDate()&&!"".equals(userFeedbackEntity.getEndDate())) {
            hql.append(" and uf.createOn <= ?");
            params.add(userFeedbackEntity.getEndDate());
        }
        hql.append("and feedBackType='1' ORDER BY uf.createOn DESC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        userFeedbackEntityList = (List<UserFeedbackEntity>) getHibernateTemplate().find(hql.toString());

        return userFeedbackEntityList;
    }


    /**
     * 获取意见反馈列表
     * param userFeedbackEntity
     * param webPage
     * return
     */
    @Override
    public List<UserFeedbackEntity> getFeedbackList(UserFeedbackEntity userFeedback, WebPage webPage,String roleScopes) {
        StringBuffer hql = new StringBuffer("from UserFeedbackEntity where 1=1 ");
        List<Object> params = new ArrayList<Object>();

        //项目
//        if(!StringUtil.isEmpty(userFeedback.getProjectName())){
//            hql.append(" and projectName like ?");
//            params.add("%" + userFeedback.getProjectName() + "%");
//        }
        if (!StringUtil.isEmpty(userFeedback.getProjectId())){
            hql.append(" and projectId in ("+userFeedback.getProjectId().substring(0, userFeedback.getProjectId().length()-1)+")");
        }
        //用户类型
        if(!StringUtil.isEmpty(userFeedback.getUserType())){
            if(userFeedback.getUserType().equals("1")){//普通会员
                hql.append(" and userType='2'");
            }else if(userFeedback.getUserType().equals("2")){//同住人
                hql.append(" and userType='4'");
            }else if(userFeedback.getUserType().equals("3")){//业主
                hql.append(" and userType='3'");
            }else if(userFeedback.getUserType().equals("6")){//业主
                hql.append(" and userType='6'");
            }
        }
        //反馈状态
        if(!StringUtil.isEmpty(userFeedback.getState())){
            if(userFeedback.getState().equals("1")){//未处理
                hql.append(" and state='1'");
            }else if(userFeedback.getState().equals("2")){//已处理
                hql.append(" and state='2'");
            }
        }
        //来源
        if(!StringUtil.isEmpty(userFeedback.getFeedBackType())){
            if(userFeedback.getFeedBackType().equals("1")){//app
                hql.append(" and feedBackType='1'");
            }else if(userFeedback.getFeedBackType().equals("3")){//微信
                hql.append(" and feedBackType='3'");
            }
        }
        //类型
        if(!StringUtil.isEmpty(userFeedback.getMemo())){
            if(userFeedback.getMemo().equals("1")){
                hql.append(" and memo='1'");
            }else if(userFeedback.getMemo().equals("2")){
                hql.append(" and memo='2'");
            }
        }
        //意见反馈来源分类   1:便民信息纠错 2:意见反馈
        if(!StringUtil.isEmpty(userFeedback.getFbClassification())){
            if(userFeedback.getFbClassification().equals("1")){
                hql.append(" and fbClassification='1'");
            }else if(userFeedback.getFbClassification().equals("2")){
                hql.append(" and fbClassification='2'");
            }
        }
        //电话
        if(!StringUtil.isEmpty(userFeedback.getMobile())){
            hql.append(" and mobile like ?");
            params.add("%" + userFeedback.getMobile() + "%");
        }
        //内容
        if(!StringUtil.isEmpty(userFeedback.getContent())){
            hql.append(" and content like ?");
            params.add("%" + userFeedback.getContent() + "%");
        }
        //创建时间
        if(!StringUtil.isEmpty(userFeedback.getCreateBy())){
            hql.append(" and createOn >= ?");
            params.add(DateUtils.parse(userFeedback.getCreateBy() + " 00:00:00"));
        }
        if(!StringUtil.isEmpty(userFeedback.getModifyBy())){
            hql.append(" and createOn <= ?");
            params.add(DateUtils.parse(userFeedback.getModifyBy() + " 23:59:59"));
        }
        //数据权限范围条件
        if (!"".equals(roleScopes) && !roleScopes.contains("all")){
            hql.append(" and projectId in ("+roleScopes.substring(0,roleScopes.length()-1)+") ");
        }
        hql.append(" ORDER BY createOn DESC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<UserFeedbackEntity> feedbackList=(List<UserFeedbackEntity>)getHibernateTemplate().find(hql.toString());
        return feedbackList;
    }

    /**
     * 获取意见反馈信息
     * param id
     * return
     */
    @Override
    public UserFeedbackEntity getFeedbackInfo(String id) {
        String hql="FROM UserFeedbackEntity WHERE id='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserFeedbackEntity> feedbackEntities=query.list();
        if(feedbackEntities.size() > 0){
            return feedbackEntities.get(0);
        }
        return null;
    }

    /**
     * 修改
     * param feedbackEntity
     */
    @Override
    public boolean update(UserFeedbackEntity feedbackEntity) {
        Session session = getCurrentSession();
        session.update(feedbackEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 通过真实姓名和身份证去检索申诉信息
     * @param content  内容(姓名:身份证)
     * @return List<UserFeedbackEntity>
     */
    public List<UserFeedbackEntity> getFeedbackListByContent(String content){
        Session session = getCurrentSession();
        Query query = session.createQuery(" FROM UserFeedbackEntity WHERE content = ? ");
        query.setParameter(0, content);
        List<UserFeedbackEntity> feedbackEntities = query.list();
        session.flush();
        session.close();
        return feedbackEntities;
    }
}
