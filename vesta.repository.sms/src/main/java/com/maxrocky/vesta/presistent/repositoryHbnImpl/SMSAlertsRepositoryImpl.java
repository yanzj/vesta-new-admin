package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SMSAlertsEntity;
import com.maxrocky.vesta.domain.model.SMSPeopleAlertsEntity;
import com.maxrocky.vesta.domain.repository.SMSAlertsRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import com.mysql.jdbc.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 27978 on 2016/9/1.
 */
@Repository
public class SMSAlertsRepositoryImpl extends HibernateDaoImpl implements SMSAlertsRepository {

    @Autowired
    SessionFactory sessionFactory;


    public Session getSession() {
        return sessionFactory.openSession();
    }
    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 将编辑的短信保存
    */
    @Override
    public void addSMSAlerts(SMSAlertsEntity smsAlertsEntity) {
        Session session = this.getSession();
        session.save(smsAlertsEntity);
        session.flush();
        session.close();
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取所有短信提醒人员
    */
    @Override
    public List<SMSPeopleAlertsEntity> getAllSMSAlertsPeople(SMSPeopleAlertsEntity smsPeopleAlertsEntity, WebPage webPage, String roleScopes) {
        List<SMSPeopleAlertsEntity> smsPeopleAlertsEntities = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        StringBuffer hql = new StringBuffer(" from SMSPeopleAlertsEntity as s where 1=1");
        if (smsPeopleAlertsEntity.getCity()!=null && !"".equals(smsPeopleAlertsEntity.getCity())) {
            hql.append(" and s.city like ?");
            params.add("%" +smsPeopleAlertsEntity.getCity()+"%");
        }
        if (smsPeopleAlertsEntity.getScope()!=null && !"".equals(smsPeopleAlertsEntity.getScope())) {
            hql.append(" and s.scope like ?");
            params.add("%" +smsPeopleAlertsEntity.getScope()+"%");
        }
        if (smsPeopleAlertsEntity.getModel()!=null && !"".equals(smsPeopleAlertsEntity.getModel()) && !"请选择".equals(smsPeopleAlertsEntity.getModel())) {
            hql.append(" and s.model like ?");
            params.add("%" + smsPeopleAlertsEntity.getModel() + "%");
        }
        if (smsPeopleAlertsEntity.getPhone()!=null && !"".equals(smsPeopleAlertsEntity.getPhone())) {
            hql.append(" and s.phone like ?");
            params.add("%" + smsPeopleAlertsEntity.getPhone() + "%");
        }
        if (smsPeopleAlertsEntity.getName()!=null && !"".equals(smsPeopleAlertsEntity.getName())) {
            hql.append(" and s.name like ?");
            params.add("%" + smsPeopleAlertsEntity.getName() + "%");
        }
        /*if (roleScopes!=null && !"".equals(roleScopes) && !"all,".equals(roleScopes)) {
            hql.append(" and s.projectNum in ("+roleScopes.substring(0,roleScopes.length()-1)+") ");
        }*/
        if (!"".equals(roleScopes) && !roleScopes.contains("all")){
            hql.append(" and s.projectNum in ("+roleScopes.substring(0,roleScopes.length()-1)+") ");
        }
        hql.append(" order by s.makeDate desc");
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }

        smsPeopleAlertsEntities = (List<SMSPeopleAlertsEntity>) getHibernateTemplate().find(hql.toString());
        return smsPeopleAlertsEntities;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 新建人员
    */
    @Override
    public void addSMSPeople(SMSPeopleAlertsEntity smsPeopleAlertsEntity) {
        Session session = this.getSession();
        session.save(smsPeopleAlertsEntity);
        session.flush();
        session.close();
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取项目对应的最新短信
    */
    @Override
    public Object[] getSMSAlerts(String projectNum, String cityId) {
        StringBuffer hql = new StringBuffer(" select s.repairContent,s.appealContent,s.activityContent from SMSAlertsEntity s where 1=1 ");
        hql.append(" and s.projectNum=:projectNum and s.cityNum=:cityId order by s.sequence desc ");
        Query query = getSession().createQuery(hql.toString());
        query.setParameter("projectNum", projectNum);
        query.setParameter("cityId", cityId);
        List<Object[]> list = query.list();
        if (!list.isEmpty() && list!=null) {
            return list.get(0);
        }
        return null;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取projectNum对应的projectName
    */
    @Override
    public String getProjectName(String projectNum) {
        StringBuffer hql = new StringBuffer(" select h.name from HouseProjectEntity h where h.pinyinCode=:projectNum ");
        Query query = getSession().createQuery(hql.toString());
        query.setParameter("projectNum", projectNum);
        List list = query.list();
        String projectName = "";
        if (!list.isEmpty()) {
            projectName = (String)list.get(0);
        }
        return projectName;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取projectName对应的projectNum
    */
    @Override
    public String getProjectNum(String projectName) {
        StringBuffer hql = new StringBuffer("select h.projectNum from HouseInfoEntity h where h.projectName=:projectName");
        Query query = this.getSession().createQuery(hql.toString());
        query.setParameter("projectName", projectName);
        List list = query.list();
        String projectNum = "";
        if (!list.isEmpty()) {
            projectNum = (String) list.get(0);
        }
        return projectNum;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取cityId
    */
    @Override
    public String getCityId(String projectNum) {
        StringBuffer hql = new StringBuffer(" select h.cityId from HouseProjectEntity h where h.pinyinCode=:projectNum");
        Query query = this.getSession().createQuery(hql.toString());
        query.setParameter("projectNum", projectNum);
        List list = query.list();
        String cityId = "";
        if (!list.isEmpty()) {
            cityId = (String) list.get(0);
        }
        return cityId;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 根据id获取SMSPeopleAlerts实体
    */
    @Override
    public SMSPeopleAlertsEntity getSMSPeopleById(String id) {
        StringBuffer hql = new StringBuffer(" from SMSPeopleAlertsEntity s where s.id=:id");
        Query query = getSession().createQuery(hql.toString());
        query.setParameter("id", id);
        List<SMSPeopleAlertsEntity> list = query.list();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 修改人员信息
    */
    @Override
    public void updatePeople(SMSPeopleAlertsEntity smsPeopleAlertsEntity) {
        Session session = this.getSession();
        session.update(smsPeopleAlertsEntity);
        //session.update(smsPeopleAlertsEntity);
        session.flush();
        session.close();
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 删除人员
    */
    @Override
    public void deletePeople(SMSPeopleAlertsEntity smsPeopleAlertsEntity) {
        Session session = this.getSession();
        session.delete(smsPeopleAlertsEntity);
        session.flush();
        session.close();
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取需要发送短信的人员
    */
    @Override
    public List<SMSPeopleAlertsEntity> getPeople(String projectName, String model) {
        List<SMSPeopleAlertsEntity> list = new ArrayList<>();
        if ("身份申诉管理".equals(model)) {
            StringBuffer hql = new StringBuffer(" from SMSPeopleAlertsEntity s where 1=1 ");
            hql.append(" and s.model like :model ");
            Query query = getSession().createQuery(hql.toString());
            query.setParameter("model", "%"+model+"%");
            list = query.list();
        }else {
            StringBuffer hql = new StringBuffer(" from SMSPeopleAlertsEntity s where 1=1 ");
            hql.append(" and s.projectNum like :projectName and s.model like :model ");
            Query query = getSession().createQuery(hql.toString());
            query.setParameter("projectName", "%"+projectName+"%");
            query.setParameter("model", "%"+model+"%");
            list = query.list();
        }
        return list;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取短信顺序最大值
    */
    @Override
    public Integer getSequence() {
        StringBuffer hql = new StringBuffer("select max(s.sequence) from SMSAlertsEntity s");
        Query query = this.getSession().createQuery(hql.toString());
        Integer sequence = (Integer)query.uniqueResult();
        return sequence;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取全部城市
    */
    @Override
    public List getAllCity() {
        StringBuffer hql = new StringBuffer("select distinct h.cityId from HouseProjectEntity h");
        Query query = getSession().createQuery(hql.toString());
        List list = query.list();
        return list;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取某城市下的全部项目
    */
    @Override
    public List<Object[]> getAllProjectByCityId(String cityId) {
        StringBuffer hql = new StringBuffer("select h.pinyinCode, h.name from HouseProjectEntity h where h.cityId=:cityId");
        Query query = getSession().createQuery(hql.toString());
        query.setParameter("cityId", cityId);
        List<Object[]> list = query.list();
        return list;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取最新的短信信息
    */
    @Override
    public SMSAlertsEntity getSMSAlertsNew(String cityNum, String projectNum) {
        StringBuffer hql = new StringBuffer("from SMSAlertsEntity s where 1=1 ");
        if (("null".equals(projectNum) || "0".equals(projectNum)) && !StringUtils.isNullOrEmpty(cityNum)) {
            //编辑短信时，选择城市后，全部项目对应查询
            hql.append(" and s.cityNum=:cityNum order by s.sequence");
        }else if (StringUtils.isNullOrEmpty(cityNum) && StringUtils.isNullOrEmpty(projectNum)){
            //点击编辑短信，对应查询
            hql.append(" order by s.sequence");
        }else {
            //编辑短信时，选择城市和项目后，对应查询
            hql.append(" and s.cityNum=:cityNum and s.projectNum=:projectNum order by s.sequence desc");
        }
        Query query = getSession().createQuery(hql.toString());

        if (("null".equals(projectNum) || "0".equals(projectNum)) && !StringUtils.isNullOrEmpty(cityNum)) {
            query.setParameter("cityNum", cityNum);
        }else if (!StringUtils.isNullOrEmpty(cityNum) && !StringUtils.isNullOrEmpty(projectNum)){
            query.setParameter("cityNum", cityNum);
            query.setParameter("projectNum", projectNum);
        }
        List<SMSAlertsEntity> list = query.list();
        if (!list.isEmpty()) {
            SMSAlertsEntity smsAlertsEntity = list.get(0);
            return smsAlertsEntity;
        }
        return null;
    }


    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 查询某城市下所有SMSPeopleAlertsEntity
    */
    @Override
    public List<SMSPeopleAlertsEntity> getSMSPeopleAlertsByCityId(String cityNum) {
        StringBuffer hql = new StringBuffer("from SMSPeopleAlertsEntity s where s.cityNum=:cityNum order by s.makeDate");
        Query query = getSession().createQuery(hql.toString());
        List<SMSPeopleAlertsEntity> list = query.list();
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 判断一个项目是否在该城市下
    */
    @Override
    public boolean isCity(String projectNum, String cityId) {
        StringBuffer hql = new StringBuffer("from HouseProjectEntity h where h.pinyinCode=:projectNum and h.cityId=:cityId");
        Query query = getSession().createQuery(hql.toString());
        query.setParameter("projectNum", projectNum);
        query.setParameter("cityId", cityId);
        List list = query.list();
        if (!list.isEmpty()) {
            return true;
        }
        return false;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 判断是否存在相同name、phone、scope、model的人员，存在则删除
    */
    @Override
    public void deleteCommonPeople(SMSPeopleAlertsEntity smsPeopleAlertsEntity) {
        StringBuffer hql = new StringBuffer("delete from SMSPeopleAlertsEntity s where s.name=:name and s.phone=:phone and s.projectNum=:projectNum and s.model=:model ");
        Query query = getSession().createQuery(hql.toString());
        if (smsPeopleAlertsEntity.getName() != null && !"".equals(smsPeopleAlertsEntity.getName())) {
            query.setParameter("name", smsPeopleAlertsEntity.getName());
        }
        if (smsPeopleAlertsEntity.getPhone() != null && !"".equals(smsPeopleAlertsEntity.getProjectNum())) {
            query.setParameter("phone", smsPeopleAlertsEntity.getPhone());
        }
        if (smsPeopleAlertsEntity.getProjectNum() != null && !"".equals(smsPeopleAlertsEntity.getProjectNum())) {
            query.setParameter("projectNum", smsPeopleAlertsEntity.getProjectNum());
        }
        if (smsPeopleAlertsEntity.getModel() !=null && !"".equals(smsPeopleAlertsEntity.getModel())) {
            query.setParameter("model", smsPeopleAlertsEntity.getModel());
        }
        query.executeUpdate();
    }
}
