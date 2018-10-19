package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseTypeEntity;
import com.maxrocky.vesta.domain.repository.HouseTypeRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/3.
 */
@Repository
public class HouseTypeRepositoryImpl extends HibernateDaoImpl implements HouseTypeRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public List<Object[]> getHouseTypeList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "select t.id,t.name,t.comments,t.createBy,t.createDate,t.imgUrl,t.operateDate,t.type from HouseTypeEntity t where t.state='1' ";
        if (map.get("name") != null && !"".equals(map.get("name").toString())) {
            hql += " and t.name like ?";
            params.add("%" + map.get("name").toString() + "%");
        }

        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString()) && !"0".equals(map.get("projectNum").toString())) {
            hql += " and t.id in (select distinct t1.houseType from HouseRoomTypeEntity t1,HouseInfoEntity t2 where t1.roomNum =t2.roomNum and t2.projectNum=?)";
            params.add(map.get("projectNum").toString());
        }
        hql += "order by t.operateDate desc";
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<Object[]> houseTypeList = (List<Object[]>) getHibernateTemplate().find(hql.toString(), params);
        return houseTypeList;
    }

    /**
     * 添加
     *
     * @param houseTypeEntity
     */
    @Override
    public void saveHouseType(HouseTypeEntity houseTypeEntity) {
        Session session = getCurrentSession();
        session.save(houseTypeEntity);
        session.flush();
    }

    /**
     * 修改
     *
     * @param houseTypeEntity
     */
    @Override
    public void updateHouseType(HouseTypeEntity houseTypeEntity) {
        Session session = getCurrentSession();
        session.update(houseTypeEntity);
        session.flush();
    }

    /**
     * 删除
     *
     * @param houseTypeEntity
     */
    @Override
    public void deleteHouseType(HouseTypeEntity houseTypeEntity) {
        Session session = getCurrentSession();
        session.delete(houseTypeEntity);
        session.flush();
    }

    @Override
    public HouseTypeEntity getHouseTypeById(String id) {
        String hql = " from HouseTypeEntity where id=:id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", Long.parseLong(id));
        List<HouseTypeEntity> list = query.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<HouseTypeEntity> getAllHouseType() {
        String hql = " from HouseTypeEntity  where state='1' order by operateDate desc";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseTypeEntity> list = query.list();
        return list;
    }

    @Override
    public List<HouseTypeEntity> getAllHouseType(String houseType, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from HouseTypeEntity  where 1=1";
        if (!StringUtil.isEmpty(houseType)) {
            hql += "and name like '%" + houseType + "%'";
//            params.add("'%"+houseType+"%'");
        }
        hql += "and state='1' order by operateDate desc";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseTypeEntity> list = query.list();
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        return list;
    }

    @Override
    public List<HouseTypeEntity> getByOperateDate(Date operateDate, String id) {
        String hql = " from HouseTypeEntity  ";
        if (operateDate != null) {
            hql += " where operateDate > :operateDate";
        }
        if (id != null && !"".equals(id)) {
            hql += " or (operateDate=:operateDate1 and id>:id)";
        }
        hql += " order by operateDate,id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(500);
        if (operateDate != null) {
            query.setParameter("operateDate", operateDate);
        }
        if (id != null && !"".equals(id)) {
            query.setParameter("operateDate1", operateDate);
            query.setParameter("id", Long.parseLong(id));
        }
        List<HouseTypeEntity> list = query.list();
        return list;
    }

    @Override
    public List<HouseTypeEntity> getByOperateDate(String operateDate, String id, String projectNum) {

        String hql = " from HouseTypeEntity hte where 1=1 ";
        if(!StringUtil.isEmpty(projectNum)){
            hql += " and hte.id IN(SELECT hrte.houseType FROM HouseRoomTypeEntity hrte WHERE hrte.roomNum LIKE '%"+projectNum+"%' GROUP BY hrte.roomNum ) ";
        }else{
            hql += " and hte.id IN(SELECT hrte.houseType FROM HouseRoomTypeEntity hrte WHERE hrte.roomNum LIKE '%%' GROUP BY hrte.roomNum ) ";
        }
        if (operateDate != null && !operateDate.equals("") && !StringUtil.isEmpty(id)) {
            hql += " and ((hte.operateDate = '" + operateDate + "' and hte.id> '" + id + "') or hte.operateDate >'"+operateDate+"')";
        }else{
            hql += " and ((hte.operateDate = '' and hte.id> '') or hte.operateDate >'')";
        }

        hql += " ORDER BY hte.operateDate,hte.id  ";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(500);
        List<HouseTypeEntity> list = query.list();
        return list;
    }

    @Override
    public List<String> getProjectNamesByHouseType(String houseTypeId) {
        String sql = "select distinct house.project_name from house_houseInfo house,house_room_type room where room.house_type=:houseTypeId and house.room_num=room.room_num";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("houseTypeId", houseTypeId);
        return query.list();
    }

}
