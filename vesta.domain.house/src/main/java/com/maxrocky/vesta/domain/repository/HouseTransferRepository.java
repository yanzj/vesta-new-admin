package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.CustomerDeliveryEntity;
import com.maxrocky.vesta.domain.model.HouseOpenEntity;
import com.maxrocky.vesta.domain.model.HouseTransferEntity;
import com.maxrocky.vesta.domain.model.InternalacceptanceHouseEntity;

import java.util.List;

/**
 * Created by zhangzhaowen on 2016/9/1.14:18
 * Describe:
 */
public interface HouseTransferRepository {
    /**
     * 根据项目编码检查是否有更新（内部预验）
     * @param id
     * @param time
     * @param projectNum
     * @return
     */
    boolean getCountInternalacceptanceByProjectNum(String id,String time,String projectNum);
    /**
     * 根据项目编码检查是否有更新（工地开放）
     * @param id
     * @param time
     * @param projectNum
     * @return
     */
    boolean getCountHouseOpenByProjectNum(String id,String time,String projectNum);
    /**
     * Code:D
     * Describe:saveorUpdate(内部预验实体类)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public void saveInternalacceptanceHouseEntity(InternalacceptanceHouseEntity internalacceptanceHouseEntity);
    /**
     * Code:D
     * Describe:saveorUpdate(内部预验实体类)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public void updateInternalacceptanceHouseEntity(InternalacceptanceHouseEntity internalacceptanceHouseEntity);

    /**
     * Code:D
     * Describe:saveorUpdate(业主开放实体类)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public void saveHouseOpenEntity(HouseOpenEntity houseOpenEntity);
    /**
     * Code:D
     * Describe:saveorUpdate(业主开放实体类)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public void updateHouseOpenEntity(HouseOpenEntity houseOpenEntity);

    /**
     * Code:D
     * Describe:根据房间编码获得实体(内部预验实体类)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public List<InternalacceptanceHouseEntity> getInternalacceptanceHouseList(String id,String timeStamp,String projectNum,List<String> projectList);
    /**
     * Code:D
     * Describe:根据房间编码获得实体(内部预验实体类)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public InternalacceptanceHouseEntity getInternalacceptanceHouseById(String roomCode);
    /**
     * Code:D
     * Describe:根据房间编码获得实体(业主开放日实体类)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public List<HouseOpenEntity> getHouseOpenList(String id,String timeStamp,String projectNum,List<String> projectList);
    /**
     * Code:D
     * Describe:根据房间编码获得实体(业主开放日实体类)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public HouseOpenEntity getHouseOpenById(String roomCode);
    /**
     * Code:D
     * Describe:新增实体
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public void saveHouseTransfer(HouseTransferEntity houseTransferEntity);

    /**
     * Code:D
     * Describe:根据id获得实体
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public HouseTransferEntity getHouseTransferById(String roomid);
    /**
     * Code:D
     * Describe:根据id获得实体
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public CustomerDeliveryEntity getCustomerById(String id);
    /**
     * Code:D
     * Describe:根据id获得实体:离线功能
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public List<HouseTransferEntity> getHouseTransferByIdAndTime(long id, String timeStamp,List<String> projectList);

    /**
     * Code:D
     * Describe:更新实体
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public void updateHouseTransfer(HouseTransferEntity houseTransferEntity);

    public void saveCustomerDelivery(CustomerDeliveryEntity CustomerEntity);

    /**
     * Code:D
     * Type:
     * Describe:获得最大时间和ID
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/21
     */
    public  List<Object[]> getMaxIdAndTime();

    /**
     * Code:D
     * Type:
     * Describe:由于新增之后要返回给前台当前数据
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/21
     */
    public HouseTransferEntity getEntityWithMaxTime();

    /**
     * Code:D
     * Type:
     * Describe:由于自增长ID,所以需要保存当前当前实体并返回（id）
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/23
     */
    public String saveEntityAndReturn(HouseTransferEntity houseTransferEntity);

    /**
     * Code:D
     * Describe:根据房间编码获得实体(业主开放日实体类)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public HouseOpenEntity getHouseOpenByRoomNum(String roomNum);
    /**
     * Code:D
     * Describe:根据房间编码获得实体(内部预验实体类)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public InternalacceptanceHouseEntity getInternalacceptanceByRoomNum(String roomNum);


    /**
     * Code:D
     * Describe:根据id获得实体
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public List<CustomerDeliveryEntity> getCustomerListById(String projectNum);
}
