package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.StaffRegisterEntity;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/21.
 */
public interface StaffRegisterRepository {

    /**
     * 签到
     * @param registerEntity
     * @return
     */
    public boolean saveRegister(StaffRegisterEntity registerEntity);

    /**
     * 分页获取签到信息
     * @param staffId
     * @param page
     * @param count
     * @return
     */
    public List<StaffRegisterEntity> listStaffRegisterEntity(String staffId,int page,int count);

    /**
     * 获得员工最新一条签到信息
     * @param staffId
     * @return
     */
    public StaffRegisterEntity getStaffRegisterById(String staffId,String date);

    /**
     * 获取签到人员的维修、客服、秩序、环境部门(员工端)
     * @param projectId
     * createBy：liudongxin
     * @return
     */
    List<StaffRegisterEntity> getDepartments(String projectId);

    /**
     * 获取签到人员的维修部门(管理端)
     * @param projectId
     * createBy：liudongxin
     * @return
     */
    List<StaffRegisterEntity> getRepairDepartment(String projectId,String today);

    /**
     * 获取签到人员的客服、秩序、环境部门(管理端)
     * @param projectId
     * createBy：liudongxin
     * @return
     */
    List<StaffRegisterEntity> getDepartment(String projectId,String today);

    /**
     * 获取部门下当天签到所有人员(管理端)
     * createBy：liudongxin
     * @param sectionId
     * @param today
     * @return
     */
    List<StaffRegisterEntity> getRegister(String sectionId,String today);
}
