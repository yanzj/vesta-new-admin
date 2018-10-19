package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserWandaStaffEntity;

import java.util.List;

/**
 * Created by zhanghj on 2016/2/14.
 */
public interface UserWandaStaffRepository {


    /**
     * 获取万达员工列表
     * @return
     */
    public List<UserWandaStaffEntity> listUserWandaStaff();

    /**
     * 通过Id获取万达员工信息
     * @param staffId
     * @return
     */
    public UserWandaStaffEntity getWandaStaffById(String staffId);

    /**
     * 更新万达员工
     * @param userWandaStaffEntity
     * @return
     */
    public boolean updateUserWandaStaff(UserWandaStaffEntity userWandaStaffEntity);

    /**
     * 根据userName查询Ehr中员工信息
     * @param userName
     * @return
     */
    //public EhrStaffEntity getEhrWandaByUserName(String userName);
}
