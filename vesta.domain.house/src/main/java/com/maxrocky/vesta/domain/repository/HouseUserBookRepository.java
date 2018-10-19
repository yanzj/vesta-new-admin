package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.HouseUserBookEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by Tom on 2016/1/19 10:05.
 * Describe:房屋业主关系Repository接口
 */
public interface HouseUserBookRepository {

    /**
     * Describe:创建房屋业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:05:55
     */
    void create(HouseUserBookEntity houseUserBookEntity);

    /**
     * Describe:根据房产Id查询房产业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 05:41:36
     */
    List<HouseUserBookEntity> getListByHouseId(String houseId);

    /**
     * Describe:根据用户Id、项目Id查询房产业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 06:37:13
     */
    List<Object[]> getObjectListByUserIdAndProjectId(String userId, String projectId);

    /**
     * Describe:根据id获取房产业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 08:42:46
     */
    HouseUserBookEntity get(String houseUserId);

    /**
     * Describe:验证是否业主
     * CreateBy:Tom
     * CreateOn:2016-01-20 08:52:48
     */
    Boolean checkOwner(String userId,String houseId);

    /**
     * Describe:修改房产成员关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 09:04:05
     */
    void update(HouseUserBookEntity houseUserBookEntity);

    /**
     * Describe:根据用户Id获取房产成员关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 09:08:25
     */
    List<HouseUserBookEntity> getListByUserId(String userId);

    /**
     * Describe:除了房产Id获取当前用户最高状态
     * CreateBy:Tom
     * CreateOn:2016-01-20 09:18:29
     */
    String getUserType(String userId,String houseId);

    /**
     * Describe:根据用户Id、房产Id获取房产成员关系(正常状态)
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:52:49
     */
    HouseUserBookEntity getByUserIdAndHouseId(String userId, String houseId);

    /**
     * Describe:根据用户Id、房产Id获取房产成员关系(解除状态)
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:52:49
     */
    HouseUserBookEntity getUserByUserIdAndHouseId(String userId, String houseId);

    /**
     * 根据房子Id和用户类型获得相关人员
     * @param houseId
     * @param userType
     * @return
     */
    List<HouseUserBookEntity> listUser(String houseId,String userType);

    /**
     * 查询房子人员关系
     * @param houseInfoEntity
     * @param houseUserBookEntity
     * @param userInfoEntity
     * @param webPage
     * @return
     */
    public List<Object> listUsers(HouseInfoEntity houseInfoEntity,HouseUserBookEntity houseUserBookEntity,UserInfoEntity userInfoEntity,WebPage webPage);



    /**
     * 删除关系
     * @param houseUserid
     * @return
     */
    public boolean delBookById(String houseUserid);

    /**
     * 根据用户ID和项目ID 查询房产列表
     * */
    public List<Object[]> getEstates(String userId,String projectId);

    String maxUserType(String userId);

    /***
     * 查询用户的所有房屋信息
     * @param userId
     * @param projectId
     * @return
     */
    List<HouseInfoEntity> getHouseUserBookByUserIdAndProjcetId(String userId, String projectId);

    /**
     * Describe:根据id获取房产业主关系
     * CreateBy:liudongxin
     * CreateOn:2016-01-20 08:42:46
     */
    HouseUserBookEntity getByHouseId(String houseId);
}
