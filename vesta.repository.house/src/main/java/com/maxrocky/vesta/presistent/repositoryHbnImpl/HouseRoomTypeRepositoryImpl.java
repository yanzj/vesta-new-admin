package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseRoomTypeEntity;
import com.maxrocky.vesta.domain.repository.HouseRoomTypeRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/6/4.
 */
@Repository
public class HouseRoomTypeRepositoryImpl extends HibernateDaoImpl implements HouseRoomTypeRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Object[]> getRoomTypeList(String projectId, String areaId, String buildingId, String unitId) {
        StringBuffer sql = new StringBuffer(" SELECT a.room_id,a.room_num,a.room_name,f.FLOOR_NAME,c.name,RIGHT(a.room_name,2),a.unit_id,c.id FROM house_houseInfo a ");
        sql.append(" LEFT JOIN house_room_type b ON a.room_id=b.room_id LEFT JOIN house_type c ON b.house_type=c.id LEFT JOIN house_floor f on a.FLOOR_ID=f.ID");
        sql.append(" where a.room_num like ? and a.room_num like ? and a.room_num like ? order by a.floor,a.room_name ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0,"%"+projectId+"%");
        query.setParameter(1,"%"+buildingId+"%");
        query.setParameter(2,"%"+unitId+"%");
        List<Object[]> list = query.list();
        return list;
    }


    @Override
    public List<String> getFloorRooms(String projectId, String areaId, String buildingId, String unitId) {
        StringBuffer sql = new StringBuffer("SELECT DISTINCT(RIGHT(a.room_name,2)) b FROM house_houseInfo a ");
        sql.append("where a.room_num like ? and a.room_num like ? and a.room_num like ? order by b");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0,"%"+projectId+"%");
        query.setParameter(1,"%"+buildingId+"%");
        query.setParameter(2,"%"+unitId+"%");
        List<String> list = query.list();
        return list;
    }

    public void deleteByUnitId(String unitId,String floorRoomName){
        String sql = "delete from house_room_type where room_id in (select room_id from house_houseInfo where ROOM_NUM like '%"+unitId+"%' and room_name like '%"+floorRoomName+"')";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    public void saveByUnitId(String unitId,String floorRoomName,String houseType){
        String sql = "insert into house_room_type(house_type,room_id,room_num,modify_time) select "+houseType+",room_id,room_num,:modifyTime from house_houseInfo where ROOM_NUM like '%"+unitId+"%' and room_name like '%"+floorRoomName+"'";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("modifyTime",new Date());
        query.executeUpdate();
    }

    public void deleteByRoomId(String roomNum){
        String sql = "delete from house_room_type where room_num='"+roomNum+"'";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    public void saveByRoomId(String roomId,String roomNum,String houseType){
        String sql = "insert into house_room_type(house_type,room_Id,room_num,modify_time) values ('"+houseType+"','"+roomId+"','"+roomNum+"',:modifyDate)";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("modifyDate",new Date());
        query.executeUpdate();
    }

    @Override
    public List<HouseRoomTypeEntity> getByModifyDateAndId(String modifyDate,String id,String projectNum) {
        String hql="FROM HouseRoomTypeEntity where 1=1";
        if(!StringUtil.isEmpty(projectNum)){
            hql += " and roomNum like '%"+projectNum+"%' ";
        }else{
            hql += " and roomNum like '%%' ";

        }
        if(!StringUtil.isEmpty(id) && !StringUtil.isEmpty(modifyDate)){
            hql += " and (modifyTime >'"+modifyDate+"' or (modifyTime ='"+modifyDate+"' and id >'"+id+"' ))";
        }
        hql += " order by modifyTime,id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(500);
        List<HouseRoomTypeEntity> list=query.list();
        return list;
    }

    @Override
    public Object[] getRoomHouseType(String roomNum) {
        String sql = "select t1.name,t1.img_url,t1.id  from house_room_type t,house_type t1 where t.house_type=t1.id and t.room_num=:roomNum";
        Query query = this.getCurrentSession().createSQLQuery(sql);
        query.setParameter("roomNum",roomNum);
        List<Object[]> list = query.list();
        if(list != null && !list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }


}
