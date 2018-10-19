package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseCRMEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by liudongxin on 2016/4/13.
 */
public interface HouseCRMRepository {

    /**
     * Describe:根据业主房屋关系Id获取业主房屋关系信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    HouseCRMEntity get(String houseId);

    /**
     * Describe:根据用户Id获取用户下的房产
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:47:34
     */
    List<HouseCRMEntity> getByUserId(String userId);

    /**
     * Describe:创建房产信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:01:12
     */
    void create(HouseCRMEntity HouseCRMEntity);

    /**
     * Describe:根据用户Id、项目Id获取房产列表
     * CreateBy:Tom
     * CreateOn:2016-01-20 11:52:36
     */
    List<HouseCRMEntity> getListByUserIdAndProjectId(String userId, String projectId);

    /**
     * Describe:根据物业房产Id获取业主房产
     * CreateBy:Tom
     * CreateOn:2016-01-21 02:20:45
     */
    HouseCRMEntity getByViewAppHouseId(int viewAppHouseId);

    /**
     * 根据各种情况查询房子列表
     * CreateBy:zhanghj
     * CreateOn:2016-01-26
     * @param HouseCRMEntity
     * @return
     */
    List<HouseCRMEntity> listHouseInfo(HouseCRMEntity HouseCRMEntity, WebPage webPage);

    /***
     * 根据houseId 查询HouseCRMEntity
     * @param houseId
     * @return
     */
    HouseCRMEntity getHouseCRMEntityByHouseId(String houseId);

    /**
     * 根据项目ID 查询HouseCRMEntity
     * @param projectId
     * @return
     */
    List<HouseCRMEntity> houseInfoByProjectId(String projectId, String building, String formatId);

    /**获取所有房屋户型*/
    List<HouseCRMEntity> getHouseTypeList();

    /**
     * 根据项目，业态，楼号，单元，房号获取房产信息
     * @param HouseCRMEntity
     * @return
     */
    public HouseCRMEntity getHouseInfo(HouseCRMEntity HouseCRMEntity);


    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改房产信息
     * ModifyBy:
     */
    void updateHouseInfo(HouseCRMEntity houseCRMEntity);

    /**
     * Describe:根据会员编号获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    HouseCRMEntity getByMemberId(String id,String memberId);
}
