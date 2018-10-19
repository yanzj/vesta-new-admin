package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyImageEntity;

import java.util.List;

/**
 * Created by liudongxin on 2016/1/15.
 * 物业报修图片方法
 */
public interface PropertyImageRepository {
    /**
     * 查看报修图片
     * @param id
     * @return
     */
    List<PropertyImageEntity> getImageUrl(String id);

    /**
     * 查看投诉图片
     * @param id
     * @return
     */
    List<PropertyImageEntity> getComplaintImage(String id);

    /**
     * 查看维修/处理完成图片
     * @param id
     * @return
     */
    List<PropertyImageEntity> getImagedUrl(String id);

    /**
     * 添加
     * @param propertyImageEntity
     */
    void saveImage(PropertyImageEntity propertyImageEntity);

    /**
     * 修改
     * @param propertyImageEntity
     */
    void updateImage(PropertyImageEntity propertyImageEntity);

    /**
     * CreatedBy:liudongxin
     * param:外键id
     * Description:通过外键id获取图片url
     */
    PropertyImageEntity getImageUrlById(String id);

    /**
     * 根据外键和类型查询图片列表
     * @return
     */
    List<PropertyImageEntity> getImageByType(String fkId,String imageType);

    /**
     * 根据外键和类型查询图片列表
     * @return
     */
    public String getIdByRoomNum(String roomNum,String imageType,String planNum);

    /**
     * 根据外键和类型删除图片
     * @param fkId
     */
    void deleteByFkId(String fkId,String type);

    /**
     * 删除不包含id的图片
     * @param id
     */
    void deleteByNotIds(List<String> id);
    /**
     * Code:D
     * Type:
     * Describe:获得交房验房的图片
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/26
     */
    List<PropertyImageEntity> getHouseTransferImageUrl(String houseTransferId);


    /**
     * Code:D
     * Type:
     * Describe:获得交房验房的图片
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/26
     */
    List<PropertyImageEntity> getHouseImageUrl(String houseTransferId);

    /**
     * 根据条件查询
     * @return
     */
    List<String> getSignIdBy(String planNum,String roomNum,String planType);

    /**
     * 根据外键和类型查询图片列表
     * @return
     */
    List<PropertyImageEntity> getSignImageByIdList(List<String> idList,String imageType);

    /**
     * 根据外键id+url+类型 判断是否存在
     * @return
     */
    PropertyImageEntity checkRepairImageByURL(String id,String url,String type);

    /**
     * 查看意见反馈图片
     * @param id
     * @return
     */
    List<String> getFeedBackImage(String id);

}