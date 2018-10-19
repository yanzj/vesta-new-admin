package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.DepartmentCRMEntity;
import com.maxrocky.vesta.domain.model.StaffCRMEntity;
import com.maxrocky.vesta.domain.model.SubcompanyCRMEntity;

/**
 * Created by yuanyn on 2018/2/5.
 */
public interface UserMessageCRMRepository {
    /**
     * Describe:根据id获取人员信息
     */
    StaffCRMEntity get(String id);
    /**
     * Describe:创建人员
     */
    void create(StaffCRMEntity staffCRMEntity);
    /**
     * Describe:修改人员
     */
    void update(StaffCRMEntity staffCRMEntity);

    /**
     * Describe:删除人员
     */
    void delete();
    /**
     * Describe:根据id获取分部信息
     */
    SubcompanyCRMEntity getSubcompany(String id);
    /**
     * Describe:创建分部
     */
    void createSubcompany(SubcompanyCRMEntity subcompanyCRMEntity);
    /**
     * Describe:修改分部
     */
    void updateSubcompany(SubcompanyCRMEntity subcompanyCRMEntity);

    /**
     * Describe:删除分部
     */
    void deleteSubcompany();
    /**
     * Describe:根据id获取部门信息
     */
    DepartmentCRMEntity getDepartment(String id);
    /**
     * Describe:创建部门
     */
    void createDepartment(DepartmentCRMEntity departmentCRMEntity);
    /**
     * Describe:修改部门
     */
    void updateDepartment(DepartmentCRMEntity departmentCRMEntity);

    /**
     * Describe:删除部门
     */
    void deleteDepartment();
}
