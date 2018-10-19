package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.HouseUserCRMEntity;
import com.maxrocky.vesta.domain.model.RoomLocationEntititly;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.Date;
import java.util.List;

/**
 * Created by Tom on 2016/1/14 21:39.
 * Describe:业主房屋关系Repository接口
 */
public interface HouseInfoRepository {

    /**
     * Describe:根据业主房屋关系Id获取业主房屋关系信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    HouseInfoEntity get(String houseId);

    /**
     * Describe:根据业主房屋关系Id获取业主房屋关系信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    List<HouseInfoEntity> getList(List<String> idList);

    /**
     * Describe:根据用户Id获取用户下的房产
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:47:34
     */
    List<HouseInfoEntity> getByUserId(String userId);

    /**
     * Describe:创建房产信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:01:12
     */
    void create(HouseInfoEntity houseInfoEntity);

    /**
     * Describe:根据用户Id、项目Id获取房产列表
     * CreateBy:Tom
     * CreateOn:2016-01-20 11:52:36
     */
    List<HouseInfoEntity> getListByUserIdAndProjectId(String userId, String projectId);

    /**
     * Describe:根据物业房产Id获取业主房产
     * CreateBy:Tom
     * CreateOn:2016-01-21 02:20:45
     */
    HouseInfoEntity getByViewAppHouseId(int viewAppHouseId);

    /**
     * 根据各种情况查询房子列表
     * CreateBy:zhanghj
     * CreateOn:2016-01-26
     * @param houseInfoEntity
     * @return
     */
    List<HouseInfoEntity> listHouseInfo(HouseInfoEntity houseInfoEntity,WebPage webPage);

    /***
     * 根据houseId 查询houseInfoEntity
     * @param houseId
     * @return
     */
    HouseInfoEntity getHouseById(String houseId);

    /**
     * 根据项目ID 查询HouseInfoEntity
     * @param projectId
     * @return
     */
    List<HouseInfoEntity> houseInfoByProjectId(String projectId,String building,String formatId);

    /**获取所有房屋户型*/
    List<HouseInfoEntity> getHouseTypeList();

    /**
     * 根据项目，业态，楼号，单元，房号获取房产信息
     * @param houseInfoEntity
     * @return
     */
    public HouseInfoEntity getHouseInfo(HouseInfoEntity houseInfoEntity);


    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改房产信息
     * ModifyBy:
     */
    void updateHouseInfo(HouseInfoEntity houseInfoEntity);

    /**
     * 批量修改
     * @param houseInfoEntity
     */
    void updateBatch(HouseInfoEntity houseInfoEntity);

    /**
     * Describe:获取业主其他房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    List<Object[]> getOwnerOtherHouse(String memberId);

    /**
     * 获取所有其他房产
     * param memberId
     * @return
     */
    List<HouseInfoEntity> getOtherHouse(String memberId);

    /**
     * Describe:获取同住人其他房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    List<Object[]> getHousemateOtherHouse(String userId);

    /**
     * Describe:获取同住人默认房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    List<Object[]> getHousemateDefaultHouse(String userId);

    /**
     * Describe:获取业主(含同住人)默认房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    List<Object[]> getOwnerDefaultHouse(String userId,String memberId);

    /**
     * Describe:通过会员编号获取默认房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    HouseInfoEntity getDefaultHouse(String memberId);

    /**
     * Describe:根据会员编号Id获取房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    HouseInfoEntity getHouseByMemberId(String memberId);

    /**
     * Describe:根据会员编号Id获取房产列表
     * CreateBy:shanshan
     * CreateOn:2016-01-20 11:52:36
     */
    List<HouseInfoEntity> getHouseByUserMemberId(String memberId);

    /**
     * Describe:获取业主所有房产(包括同住人)
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    List<Object[]> getAllHouse(String memberId);

    /**
     * Describe:获取同住人所有房产
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 11:52:36
     */
    List<Object[]> getHousemateHouse(String userId);


    /**
     * Describe:根据业主房屋关系Id获取业主房屋关系信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    Object[] getById(String id);

    /**
     * Describe:根据房屋id去查询房屋信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    HouseInfoEntity getHouseByRoomId(String roomId);

    /**
     * 通过房屋id检索房屋信息_Wyd
     * @param roomId    房屋Id
     * @return  HouseInfoEntity
     */
    HouseInfoEntity getHouseInfoByRoomId(String roomId);

    /**
     * Describe:根据项目id去查询项目和楼栋信息信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-27 20:40
     */

    List<HouseInfoEntity> getInfoByProjectId(String projectId);

    /**
     * Describe:根据项目编号去查询项目和楼栋信息信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-27 20:40
     */

    List<HouseInfoEntity> getInfoByProjectNum(String projectNum);


    /**
     * Describe:根据楼栋id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28
     */
    List<HouseInfoEntity> getInfoByBuildingId(String buildingId);

    /**
     * Describe:根据楼层编码去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-05-07
     */
    List<HouseInfoEntity> getInfoByFloorId(String floorId);
    /**
     * Describe:根据单元Id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-05-07
     */
    List<HouseInfoEntity> getInfoByUnitId(String unitId);
    /**
     * Describe:根据单元Id去查询信息(按房屋号码排序)
     * CreateBy:WeiYangDong
     * CreateOn:2017-11-03
     */
    List<HouseInfoEntity> getInfoByUnitIdOrderBy(String unitId);
    /**
     * Describe:根据城市Id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-05-08
     */
    List<HouseInfoEntity> getInfoByCityId(String cityId);
    /**
     * Describe:根据地块Id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-05-08
     */
    List<HouseInfoEntity> getInfoByBlockId(String blockId);

    /**
     * 根据会员ID取得默认房产信息
     * @param userId
     * @return
     */
    HouseInfoEntity getHouseInfoEntityByUserId(String userId);

    /**
     * 查询房间信息
     * @return
     */
    List<HouseInfoEntity> getHouseInfoByBuildingNum(String  BuildingNum);


    /**
     * 查询单元楼层
     * @param projectId
     * @param areaId
     * @param buildingId
     * @param unitId
     * @return
     */
    public List<String> getFloorListByUnitId(String projectId, String areaId, String buildingId, String unitId);

    /***
     * 根据houseId 查询houseInfoEntity
     * @param id
     * @return
     */
    UserInfoEntity getUserById(String id);

    /**
     *获取序列id
     * */
    String getRepairid();

    /**
     *获取序列id
     * */
    String getRepairidNew(String num);

    /**
     * 匹配房屋位置
     * */
    RoomLocationEntititly getRoomLocation(String name);


    /**
     * 根据查询出的houseinfo的id==roomId 级联查询其以上级别的参数sethouseInfo  （楼层-->单元-->楼栋-->地块-->项目-->城市）
     * */
    Boolean updateHouseInfoById(List<String> idList);


    /**
     * 根据查询出的roomId 关联查询houseInfo 将地址复制
     * */
    Boolean updateBudingMappingById(List<String> idList, String date);

    /**
     * 通过roomId获取房产业主关联信息列表
     * @param roomId 房产ID
     * @return List<HouseUserCRMEntity>
     */
    List<HouseUserCRMEntity> getHouseUserCRMListByRoomId(String roomId);

}
