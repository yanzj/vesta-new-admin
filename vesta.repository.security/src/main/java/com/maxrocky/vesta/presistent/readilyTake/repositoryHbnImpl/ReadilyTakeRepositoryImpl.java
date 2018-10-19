package com.maxrocky.vesta.presistent.readilyTake.repositoryHbnImpl;

import com.maxrocky.vesta.domain.readilyTake.model.ReadilyRecordEntity;
import com.maxrocky.vesta.domain.readilyTake.model.ReadilyTakeEntity;
import com.maxrocky.vesta.domain.readilyTake.model.SecurityImageEntity;
import com.maxrocky.vesta.domain.readilyTake.repository.ReadilyTakeRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/6/9.
 */
@Repository
public class ReadilyTakeRepositoryImpl extends HibernateDaoImpl implements ReadilyTakeRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * 查询随手拍数据List
     */
    @Override
    public List<Object[]> getReadilyTakeList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rt.patId,rt.projectName,rt.createName,rt.createDate,rt.state,rt.examinationParts,rt.severityLevel,rt.projectId ");
        sql.append(" from ReadilyTakeEntity rt ");
        sql.append(" where 1=1 and rt.additional<>'1' ");
        //项目公司ID
        if (!StringUtil.isEmpty(map.get("projectId").toString())) {
            sql.append(" and rt.projectId=? ");
            params.add(map.get("projectId").toString());
        }else if(!StringUtil.isEmpty(map.get("agencyListString").toString())){
            String agencyList =map.get("agencyListString").toString();
            sql.append(" and rt.projectId in (").append(agencyList).append(") ");
//            sql.append(" and rt.projectId in(?) ");
//            params.add(map.get("agencyListString").toString());
        }
        //状态
        if (!StringUtil.isEmpty(map.get("state").toString())) {
            sql.append(" and rt.state=? ");
            params.add(map.get("state").toString());
        }
        //严重等级
        if (!StringUtil.isEmpty(map.get("severityLevel").toString())) {
            sql.append(" and rt.severityLevel like? ");
            params.add(map.get("severityLevel").toString());
        }
        //检查部位
        if (!StringUtil.isEmpty(map.get("examinationParts").toString())) {
            sql.append(" and rt.examinationParts like? ");
            params.add(map.get("examinationParts").toString());
        }
        //开始日期
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql.append(" and rt.createDate >= '" + startDate +"' ");
        }
        //结束时间
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql.append(" and rt.createDate <= '" + endDate +"' ");
        }
        //创建人
        if(!StringUtil.isEmpty(map.get("createName").toString())){
            sql.append(" and rt.createName like '%"+map.get("createName")+"%' ");
        }
        sql.append(" order by rt.modifyDate desc ");
        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }

    /**
     * 查询随手拍详情
     */
    @Override
    public Object[] getReadilyTake(String patId) {
        String sql = "SELECT ";
        sql += " rt.patId,rt.projectId,rt.projectName,rt.state,rt.examinationParts,rt.severityLevel,rt.createName,rt.createDate,rt.description,rt.internalPeople,rt.externalPeople,rt.securityAdministrator, ";
        sql += " rt.rectificationDate,rt.rectificationPeople,rt.supplementaryDescription,rt.address,rt.modifyDate ";
        sql += " FROM ReadilyTakeEntity rt";
        sql += " WHERE 1=1 ";
        sql += " AND additional<>'1' ";
        sql += " AND patId=:patId";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("patId",patId);
        List<Object[]> list = query.list();
        if(list.size()>0 && null != list){
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询随手拍图片
     */
    @Override
    public List<Object[]> getSecurityImageList(List<String> idList) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" BUSINESS_ID,ID,IMAGE_URL,IMAGE_TYPE ");
        sql.append(" from st_security_image ");
        sql.append(" where 1=1");
        sql.append(" and BUSINESS_ID in(:idList) ");
        sql.append(" and STATE='1' and IMAGE_TYPE in ('1','2') ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameterList("idList", idList);
        return (List<Object[]>) query.list();
    }

    /**
     * 查询随手拍整改描述
     */
    @Override
    public List<Object[]> getRectificationDescriptionList(String patId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ID,BUSINESS_ID,RECTIFICATION_DESCRIPTION ");
        sql.append(" from st_readily_record ");
        sql.append(" where 1=1");
        sql.append(" and BUSINESS_ID=:patId order by CREATE_ON");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("patId", patId);
        return (List<Object[]>) query.list();
    }

    /**
     * 根据当前登录人和项目公司id查询在当前公司下的权限
     */
    @Override
    public List<String> queryRoleById(String userId, String projectId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" DATA_ROLE ");
        sql.append(" from st_basic_staff  ");
        sql.append(" where 1=1 ");
        sql.append(" and DATA_ID=:projectId  and STAFF_ID=:userId  and STATE='1' GROUP BY DATA_ROLE  ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("projectId", projectId);
        return (List<String>) query.list();
    }

    /**
     * 根据id 获取随手拍详情
     */
    @Override
    public ReadilyTakeEntity getReadilyTakeById(String patId) {
        String sql = " FROM ReadilyTakeEntity ";
        sql += " WHERE 1=1 ";
        sql += " AND patId=:patId ";
        sql += " AND additional<>'1' ";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("patId",patId);
        ReadilyTakeEntity readilyTakeEntity = (ReadilyTakeEntity)query.uniqueResult();
        return readilyTakeEntity;
    }

    /**
     * 修改整改单
     */
    @Override
    public void updateReadilyTake(ReadilyTakeEntity readilyTakeEntity) {
        Session session = getCurrentSession();
        session.update(readilyTakeEntity);
        session.flush();
        session.close();
    }

    /**
     * 保存整改描述
     */
    @Override
    public void saveReadilyTakeRectify(ReadilyRecordEntity readilyRecordEntity) {
        Session session = getCurrentSession();
        session.save(readilyRecordEntity);
        session.flush();
        session.close();
    }

    /**
     * 保存图片
     */
    @Override
    public void saveImage(SecurityImageEntity securityImageEntity) {
        Session session = getCurrentSession();
        session.save(securityImageEntity);
        session.flush();
        session.close();
    }
}
