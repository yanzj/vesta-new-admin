package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.LoginLogEntity;
import com.maxrocky.vesta.domain.repository.LoginLogRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmei on 2016/2/18.
 */
@Repository
public class LoginLogRepositoryImpl extends HibernateDaoImpl implements LoginLogRepository{
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    @Override
    public List<LoginLogEntity> LoginLogManage(LoginLogEntity loginLogEntity, WebPage Page) {
        StringBuffer hql = new StringBuffer("FROM LoginLogEntity as l where 1=1");
        List<LoginLogEntity> loginLogList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        // 不为空则拼接搜索条件  推送范围
        // 初始化 登陆人所负责范围
        hql.append(SqlJoiningTogether.sqlStatement("l.projectId", loginLogEntity.getProjectId()));
        // 不为空则拼接搜索条件
        if(!"0".equals(loginLogEntity.getRegionId())&&null!=loginLogEntity.getRegionId()){//区域
            hql.append(" and l.regionId like ?");
            params.add("%"+loginLogEntity.getRegionId()+"%");
        }
        if(!"".equals(loginLogEntity.getUserName())&&null!=loginLogEntity.getUserName()){//用户名
            hql.append(" and l.userName like ?");
            params.add("%"+loginLogEntity.getUserName()+"%");
        }
        // 不为空则拼接搜索添加 时间
        if(null != loginLogEntity.getStartDate()){
            hql.append(" and l.logintime >= ?");
            params.add(loginLogEntity.getStartDate());
        }
        if(null != loginLogEntity.getEndDate()){
            hql.append(" and l.logintime <= ?");
            params.add(loginLogEntity.getEndDate());
        }
        hql.append(" ORDER BY l.logintime DESC");
        if(Page != null){
            return this.findByPage(hql.toString(), Page, params);
        }

        loginLogList =  ( List<LoginLogEntity>)getHibernateTemplate().find(hql.toString());
        return loginLogList;
    }

    @Override
    public void addLoginLogManage(LoginLogEntity loginLogEntity) {
        Session createSession = getCurrentSession();
        createSession.save(loginLogEntity);
        createSession.flush();
    }
}
