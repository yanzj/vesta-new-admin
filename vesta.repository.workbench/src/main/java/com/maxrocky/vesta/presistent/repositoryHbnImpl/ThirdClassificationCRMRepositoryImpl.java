package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ClassificationTemporaryTimeEntity;
import com.maxrocky.vesta.domain.model.ThirdClassificationCommEntity;
import com.maxrocky.vesta.domain.model.ThirdClassificationEntity;
import com.maxrocky.vesta.domain.repository.ThirdClassificationCRMRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import com.maxrocky.vesta.taglib.page.WebPage;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dl on 2016/5/9.
 */
@Repository
public class ThirdClassificationCRMRepositoryImpl extends HibernateDaoImpl implements ThirdClassificationCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * Describe:创建一级分类
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    @Override
    public void create(ThirdClassificationEntity thirdClassificationEntity) {
        Session session = getCurrentSession();
        session.save(thirdClassificationEntity);
        session.flush();
    }
    /**
     * CreatedBy:dl
     * Describe:
     * 修改三级分类
     * ModifyBy:
     */
    @Override
    public void update(ThirdClassificationEntity thirdClassificationEntity) {
        Session session = getCurrentSession();
        session.update(thirdClassificationEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<ThirdClassificationEntity> getSecondClassification(String secondId) {
        String hql = "from ThirdClassificationEntity where secondId = :secondId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("secondId", secondId);
        return (List<ThirdClassificationEntity>)query.list();
    }

    @Override
    public List<ThirdClassificationCommEntity> getAllCommonlyUsedManagement() {
        String hql = "from ThirdClassificationCommEntity t order by t.itemOrder";
        Query query = getCurrentSession().createQuery(hql);
        return (List<ThirdClassificationCommEntity>)query.list();
    }



    /**
     * CreatedBy:dl
     * Describe:
     * 删除三级分类
     * ModifyBy:
     */
    @Override
    public void delete() {
        String hql="delete FROM ThirdClassificationEntity";
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    /**
     * Describe:根据id获取信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     *
     * @param id
     */
    @Override
    public ThirdClassificationEntity get(String id) {
        String hql="FROM ThirdClassificationEntity WHERE value='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<ThirdClassificationEntity> ThirdClassificationList=query.list();
        if(ThirdClassificationList.size()>0){
            return ThirdClassificationList.get(0);
        }
        return null;
    }

    @Override
    public ThirdClassificationCommEntity getComm(String value) {
        String hql="FROM ThirdClassificationCommEntity WHERE value='"+value+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<ThirdClassificationCommEntity> thirdClassificationCommList=query.list();
        if(thirdClassificationCommList.size()>0){
            return thirdClassificationCommList.get(0);
        }
        return null;
    }

    @Override
    public void update(ThirdClassificationCommEntity thirdClassificationCommEntity) {
        Session session = getCurrentSession();
        session.update(thirdClassificationCommEntity);
        session.flush();
        session.close();
    }

    @Override
    public boolean savethirdClassificationComm(ThirdClassificationCommEntity thirdClassificationCommEntity) {
        Session session = getCurrentSession();
        session.save(thirdClassificationCommEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean deleteAll() {
        String hql="delete FROM ThirdClassificationCommEntity";
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteById(String id) {
        String hql=" delete FROM ThirdClassificationCommEntity WHERE value='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
        return true;
    }

    @Override
    public List<Object[]> getAllClassifyLlist(String oneType,String twoType,String threeType,String alias,String thirdTypeName,WebPage webPage) {

        List<Object> params = new ArrayList<Object>();
        StringBuilder hql = new StringBuilder("SELECT f.value AS first_value,f.label AS first_label," +
                "s.value AS second_value,s.label AS second_label," +
                "t.value AS third_value,t.label AS third_label,t.modifyDate AS third_modify_date,t.alias AS third_alias" +
                " FROM FirstClassificationEntity AS f ,SecondClassificationEntity AS s,ThirdClassificationEntity AS t" +
                " WHERE t.secondId =s.value " +
                " AND s.firstId = f.value");
        if(!StringUtil.isEmpty(threeType)&&!threeType.equals("0000")){
            hql.append(" AND t.value = ?");
            params.add(threeType);
        }
        if (!StringUtil.isEmpty(twoType)&&!twoType.equals("0000")){
                hql.append(" AND s.value = ?");
                params.add(twoType);
        }
        if (!StringUtil.isEmpty(oneType)&&!oneType.equals("0000")){
            hql.append(" AND f.value = ?");
            params.add(oneType);
        }
        if (!StringUtil.isEmpty(alias)){
            hql.append(" AND t.alias LIKE ?");
            alias = "%"+alias+"%";
            params.add(alias);
        }
        if(!StringUtil.isEmpty(thirdTypeName)){
            hql.append(" AND t.label LIKE ?");
            thirdTypeName = "%"+thirdTypeName+"%";
            params.add(thirdTypeName);
        }
        //params.add(communityId);
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        return null;
    }

    @Override
    public List<Map<String, String>> getAllClassifyLlist(WebPage webPage) {
        String hql = "SELECT f.value AS first_value,f.label AS first_label,\n" +
                "s.value AS second_value,s.label AS second_label,\n" +
                "t.value AS third_value,t.label AS third_label,t.modify_date AS third_modify_date,t.VALUE_ALIAS AS third_alias\n" +
                "FROM first_classification AS f ,second_ClassificationEntity AS s,third_ClassificationEntity AS t\n" +
                "WHERE t.second_id =s.value \n" +
                "AND  s.first_id = f.value";
        String hql1 = " SELECT COUNT(*)\n" +
                "         FROM first_classification AS f ,second_classification AS s,third_classification AS t\n" +
                "         WHERE t.second_id =s.value \n" +
                "         AND   s.first_id = f.value";
        Object count =  getCurrentSession().createSQLQuery(hql1).uniqueResult();
        if(count!=null){
            webPage.setRecordCount(((BigInteger) count).intValue());
        }
        Query query = getCurrentSession().createSQLQuery(hql);
        query.setMaxResults(webPage.getPageSize());
        query.setFirstResult(webPage.getStartRow());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        list=query.list();

        return list;
    }

    @Override
    public ClassificationTemporaryTimeEntity getClassificationTemporaryTimeEntity(String thirdClassifyValue) {

        String hql = "FROM ClassificationTemporaryTimeEntity WHERE current_id =:current_id AND graded = 3";
        Query query = getCurrentSession().createQuery(hql);
        if(!StringUtil.isEmpty(thirdClassifyValue)){
            query.setParameter("current_id",thirdClassifyValue);
        }
        ClassificationTemporaryTimeEntity obj = (ClassificationTemporaryTimeEntity)query.list().get(0);
        return obj;

    }

    @Override
    public ThirdClassificationCommEntity getThirdClassificationCommEntity(String thirdClassifyValue) {

        String hql = "FROM ThirdClassificationCommEntity WHERE value =:id";
        Query query = getCurrentSession().createQuery(hql);
        if(!StringUtil.isEmpty(thirdClassifyValue)){
            query.setParameter("id",thirdClassifyValue);
        }
        List<ThirdClassificationCommEntity> obj =query.list();
        if(obj!=null&&obj.size()>0){
           return obj.get(0);
        }
        return null;
    }

    @Override
    public void updateClassificationTemporaryTimeEntity(ClassificationTemporaryTimeEntity entity) {
        Session session = getCurrentSession();
        session.update(entity);
        session.flush();
        session.close();
    }

    @Override
    public void updateThirdClassificationCommEntity(ThirdClassificationCommEntity e) {
        Session session = getCurrentSession();
        session.update(e);
        session.flush();
        session.close();
    }

//    //Describe:通过上移下一进行排序，operation=1，下移 -1上移
//    @Override
//    public void orderThirdClassification(String value, String operation) {
//
//        String hql ="select ItemOrder from ThirdClassificationCommEntity";
//
//    }
}
