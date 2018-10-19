package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ActiveTemporaryTimeEntity;
import com.maxrocky.vesta.domain.model.BuildingMappingTimeEntity;
import com.maxrocky.vesta.domain.model.ClassificationTemporaryTimeEntity;
import com.maxrocky.vesta.domain.model.PersonnelAuthorityTimeEntity;

import java.util.List;

/**
 * Created by Magic on 2016/6/15.
 */
public interface TemporaryTableUpdateRepository {
    /**
     * 查询项目人员权限变信息
     * */
    PersonnelAuthorityTimeEntity queryPersonnel(String id);
    /**
     * 查询楼栋临时表数据  id
     * **/
    BuildingMappingTimeEntity queryBuild(String id);
    /**
     * 增加楼栋临时表信息
     */
    void createBuild(BuildingMappingTimeEntity BuildingMappingTime);
    /**
     * 增加项目人员权限临时表信息
     */
    void createPersonnel(PersonnelAuthorityTimeEntity PersonnelAuthorityTime);
    /**
     * 修改楼栋临时表信息
     * */
    void updateBuild(BuildingMappingTimeEntity BuildingMappingTime);
    /**
     * 更新项目人员权限临时表信息
     * */
    void updatePersonnel(PersonnelAuthorityTimeEntity PersonnelAuthorityTime);
    /**
     * 获取所有房屋数据
     */
    List<BuildingMappingTimeEntity> getBuildingList();

    /**
     * 查询活动临时表数据  id
     * **/
    ActiveTemporaryTimeEntity queryActive(String id);


    /**
     * 查询活动临时表数据  id
     * **/
    ActiveTemporaryTimeEntity queryActiveBUild(String id,String PlanId);
    /**
     * 增加活动临时表信息
     */
    void createActive(ActiveTemporaryTimeEntity ActiveTemporaryTime);
    /**
     * 修改活动临时表信息
     * */
    void updateActive(ActiveTemporaryTimeEntity ActiveTemporaryTime);

    /**
     * 修改活动临时表信息 关闭计划
     * */
    void updateActiveStateById(String planId);

    /**
     * 修改活动临时表信息 关闭计划(批量)
     * */
    void updateActiveStateByIdList(List<String> idList);

    /**
     * 获取所有计划数据
     */
    List<ActiveTemporaryTimeEntity> getActiveList();

    /**
     * 查询活动临时表数据  id
     * **/
    ClassificationTemporaryTimeEntity queryClass(String id);

    /**
     * 查询活动临时表数据  id
     * **/
    ClassificationTemporaryTimeEntity queryClassforgradle(String id,String grad);

    /**
     * 查询活动临时表数据  id
     * **/
    ClassificationTemporaryTimeEntity queryClassfour(String id,String type);

    /**
     * 查询临时表数据
     * **/
    ClassificationTemporaryTimeEntity queryByParentId(String parentId);

    /**
     * 增加三级分类临时表信息
     */
    void createClass(ClassificationTemporaryTimeEntity ClassificationTemporaryTime);

    /**
     * 修改三级分类临时表信息
     * */
    void updateClass(ClassificationTemporaryTimeEntity ClassificationTemporaryTime);

    /**
     * 获取所有三级分类数据
     */
    List<ClassificationTemporaryTimeEntity> getClassList();
}
