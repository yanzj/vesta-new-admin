package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.BaseProjectPeopleEntity;

import java.util.List;

/**
 * Created by chen on 2016/11/8.
 */
public interface ProjectPeopleRepository {
    /**
     * @param baseProjectPeopleEntity
     * 新增或修改数据
     */
    void addProjectPeople(BaseProjectPeopleEntity baseProjectPeopleEntity);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoId
     * @return
     * 同步责任人员基础数据
     */
    List<BaseProjectPeopleEntity> getProjectPeopleForTime(String projectId,String timeStamp,long autoId);

    /**
     * @param staffId
     * 根据人员ID删除基础数据
     */
    void delProjectPeople(String staffId);

    /**
     * @param dutyId
     * 根据责任单位ID删除基础数据
     */
    void delProjectPeopleByDutyId(String dutyId);

    /**
     * @param dutyName
     * @param dutyId
     * 根据责任单位ID修改责任单位名
     */
    void updateProjectPeople(String dutyName,String dutyId);

    /**
     * @param projectName
     * @param projectId
     * 根据项目ID修改项目名
     */
    void updateForProjectName(String projectName,String projectId);

    /**
     * @param staffName
     * @param staffId
     * 根据人员ID修改人员名
     */
    void updateForStaffName(String staffName,String staffId);
}
