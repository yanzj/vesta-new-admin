package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.ButlerScoreLogEntity;
import com.maxrocky.vesta.domain.model.ButlerStyleEntity;
import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.repository.ButlerStyleRespository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管家模块_数据持久层实现类
 * Created by WeiYangDong on 2017/5/5.
 */
@Repository
public class ButlerStyleRespositoryImpl implements ButlerStyleRespository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){ return sessionFactory.openSession(); }

    /**
     * 保存或更新Entity
     * @param entity
     */
    @Override
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
    @Override
    public <E> void delete(E entity) {
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    /**
     * 获取管家列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<ButlerStyleEntity>
     */
    @Override
    public List<ButlerStyleEntity> getButlerStyleList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder("FROM ButlerStyleEntity bs WHERE 1=1");
        //数据权限
        Object roleScopes = paramsMap.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            hql.append(" and bs.serviceProjectCode in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+") ");
        }
        List<String> paramsList = new ArrayList<>();
        //主键ID
        Object butlerId = paramsMap.get("butlerId");
        if (null != butlerId && !"".equals(butlerId.toString())){
            hql.append(" AND bs.butlerId = ? ");
            paramsList.add(butlerId.toString());
        }
        //区域
        Object cityId = paramsMap.get("cityId");
        if (null != cityId && !"".equals(cityId.toString()) && !"0".equals(cityId.toString())){
            hql.append(" AND bs.serviceCityId = ? ");
            paramsList.add(cityId.toString());
        }
        //项目
        Object projectNum = paramsMap.get("projectNum");
        if (null != projectNum && !"".equals(projectNum.toString()) && !"0".equals(projectNum.toString())){
            hql.append(" AND bs.serviceProjectCode = ? ");
            paramsList.add(projectNum.toString());
        }
        //服务楼栋
        Object serviceBuilding = paramsMap.get("serviceBuilding");
        if (null != serviceBuilding && !"".equals(serviceBuilding.toString())){
            hql.append(" AND bs.serviceBuilding like ? ");
            paramsList.add("%"+serviceBuilding.toString()+"%");
        }
        //管家名称
        Object butlerNum = paramsMap.get("butlerNum");
        if (null != butlerNum && !"".equals(butlerNum.toString())){
            hql.append(" AND bs.butlerNum = ? ");
            paramsList.add(butlerNum.toString());
        }
        //联系电话
        Object telephone = paramsMap.get("telephone");
        if (null != telephone && !"".equals(telephone.toString())){
            hql.append(" AND bs.telephone = ? ");
            paramsList.add(telephone.toString());
        }
        //微信昵称
        Object wechatNickname = paramsMap.get("wechatNickname");
        if (null != wechatNickname && !"".equals(wechatNickname.toString())){
            hql.append(" AND bs.wechatNickname like ? ");
            paramsList.add("%"+wechatNickname.toString()+"%");
        }
        hql.append(" ORDER BY bs.createOn desc");
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

    /**
     * 更新管家责任房屋
     * @param butlerId 管家ID
     * @param roomIdList 房产列表
     */
    @Override
    public void updateHouseButlerId(String butlerId,List<String> roomIdList){
        StringBuilder sql = new StringBuilder("UPDATE house_houseInfo h SET h.butler_id = '"+butlerId+"'");
        sql.append(" WHERE h.ROOM_ID in (");
        for (int i=0,length=roomIdList.size();i<length;i++){
            if (i==length-1){
                sql.append("'"+roomIdList.get(i)+"'");
            }else{
                sql.append("'"+roomIdList.get(i)+"',");
            }
        }
        sql.append(")");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 获取管家责任房产
     * @param butlerId 管家ID
     * @return List<HouseInfoEntity>
     */
    @Override
    public List<HouseInfoEntity> getHouseInfoListByButler(String butlerId){
        StringBuilder hql = new StringBuilder("FROM HouseInfoEntity h WHERE h.butlerId = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, butlerId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取管家评分日志列表
     * @param butlerId 管家ID
     * @param webPage 分页
     * @return List<ButlerScoreLogEntity>
     */
    @Override
    public List<ButlerScoreLogEntity> getButlerScoreLogList(String butlerId,WebPage webPage){
        StringBuilder hql = new StringBuilder("FROM ButlerScoreLogEntity WHERE butlerId = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0,butlerId);
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 删除管家房产关系(删除管家后,更新房产管家为空)
     * @param butlerId 管家ID
     */
    @Override
    public void deleteHouseButlerByButler(String butlerId){
        StringBuilder sql = new StringBuilder(" UPDATE house_houseInfo h SET h.butler_id = '' ");
        sql.append(" WHERE h.butler_id = ? ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString()).setParameter(0, butlerId);
        query.executeUpdate();
    }
}
