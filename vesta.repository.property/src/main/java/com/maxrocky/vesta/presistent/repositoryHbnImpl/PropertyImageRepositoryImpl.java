package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyImageEntity;
import com.maxrocky.vesta.domain.repository.PropertyImageRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/15.
 * 物业报修图片实现方法
 */
@Repository
public class PropertyImageRepositoryImpl implements PropertyImageRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**
     * 查看报修图片
     * @param id
     * @return
     */
    @Override
    public List<PropertyImageEntity> getImageUrl(String id) {
        String hql="FROM PropertyImageEntity WHERE imgFkId='"+id+"' and state='0' and imageType='0'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyImageEntity> imageList=query.list();
        return imageList;
    }

    /**
     * 查看投诉图片
     * @param id
     * @return
     */
    @Override
    public List<PropertyImageEntity> getComplaintImage(String id) {
        String hql="FROM PropertyImageEntity WHERE imgFkId='"+id+"' and state='0' and imageType='1'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyImageEntity> imageList=query.list();
        return imageList;
    }

    /**
     * 查看维修/处理完成图片
     * @param id
     * @return
     */
    @Override
    public List<PropertyImageEntity> getImagedUrl(String id) {
        String hql="FROM PropertyImageEntity WHERE imgFkId='"+id+"' and state='0' and imageType='2'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyImageEntity> imageList=query.list();
        return imageList;
    }

    /**
     * 添加
     * @param propertyImageEntity
     */
    @Override
    public void saveImage(PropertyImageEntity propertyImageEntity) {
        Session session = getCurrentSession();
        session.save(propertyImageEntity);
        session.flush();
    }

    /**
     * 修改
     * @param propertyImageEntity
     */
    @Override
    public void updateImage(PropertyImageEntity propertyImageEntity) {
        Session session = getCurrentSession();
        session.update(propertyImageEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:liudongxin
     * param:外键id
     * Description:通过外键id获取图片url
     */
    @Override
    public PropertyImageEntity getImageUrlById(String id) {
        String hql="FROM PropertyImageEntity WHERE imgFkId='"+id+"' and state='0' ORDER BY uploadDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyImageEntity> imageList=query.list();
        if(imageList.size()>0){
            return imageList.get(0);
        }
        return null;
    }

    @Override
    public List<PropertyImageEntity> getImageByType(String fkId, String imageType) {
        String hql="FROM PropertyImageEntity WHERE imgFkId='"+fkId+"' and state='0' and imageType='"+imageType+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyImageEntity> imageList=query.list();
        return imageList;
    }

    @Override
    public String getIdByRoomNum(String roomNum, String imageType,String planNum) {
        Session session = getCurrentSession();
        String sql = "SELECT hd.ID from house_deliver hd  where HOUSE_CODE like :roomNum ";
        Query sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter("roomNum", roomNum);
        List<String> idList = (List<String>) sqlQuery.list();
        if(idList!=null){
            return idList.get(0).toString();
        }else {
            return null;
        }

    }


    @Override
    public void deleteByFkId(String fkId, String type) {
        String sql = "delete from property_image where img_fk_id=:fid and image_type=:type";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("fid",fkId);
        query.setParameter("type",type);
        query.executeUpdate();
    }

    @Override
    public void deleteByNotIds(List<String> ids) {
        String sql = "delete from property_image where image_id not in (:ids)";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }

    /**
     * Code:D
     * Type:
     * Describe:获得交房验房的图片
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/26
     */
    @Override
    public List<PropertyImageEntity> getHouseTransferImageUrl(String houseTransferId) {
        String hql = "FROM PropertyImageEntity WHERE imgFkId='" + houseTransferId + "' and imageType ='7' and state='0'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyImageEntity> imageList = query.list();
        return imageList;
    }

    @Override
    public List<PropertyImageEntity> getHouseImageUrl(String houseTransferId) {
        String hql = "FROM PropertyImageEntity WHERE imgFkId='" + houseTransferId + "'and state='0'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyImageEntity> imageList = query.list();
        return imageList;
    }

    @Override
    public List<String> getSignIdBy(String planNum, String roomNum, String planType) {
        Session session = getCurrentSession();
        String sql = "SELECT SIGNA_ID from signa_image where PLAN_NUM like:planNum and ROOM_NUM=:roomNum and PLAN_TYPE=:planType";
        Query sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter("planNum", "%"+planNum+"%");
        sqlQuery.setParameter("planType", planType);
        sqlQuery.setParameter("roomNum", roomNum);
        List<String> list = (List<String>) sqlQuery.list();
        if(list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<PropertyImageEntity> getSignImageByIdList(List<String> idList, String imageType) {
        String hql="FROM PropertyImageEntity WHERE imgFkId in(:idList) and state='0' and imageType=:type";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameterList("idList",idList);
        query.setParameter("type",imageType);
        List<PropertyImageEntity> imageList=query.list();
        return imageList;
    }

    @Override
    public PropertyImageEntity checkRepairImageByURL(String id, String url, String type) {
        Session session = getCurrentSession();
        String sql = "FROM PropertyImageEntity where imageType=:type1 and imagePath=:url and imgFkId=:id and state='0' ";
        Query sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter("id", id);
        sqlQuery.setParameter("url", url);
        sqlQuery.setParameter("type1", type);
        List<PropertyImageEntity> imageList =  sqlQuery.list();
        if(imageList!=null && imageList.size()>0){
            return imageList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<String> getFeedBackImage(String id){
        String sql = "SELECT pi.image_path from property_image pi WHERE pi.img_fk_id = :id AND state = '0' AND image_type = '25'";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("id",id);
        return query.list();
    }
}