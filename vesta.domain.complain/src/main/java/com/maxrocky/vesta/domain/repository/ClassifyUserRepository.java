package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.ClassifyStaffRelationEntity;
import com.maxrocky.vesta.domain.model.ComplainClassifyEntity;

import java.util.List;

/**
 * Created by Magic on 2017/7/18.
 */
public interface ClassifyUserRepository {
    /**
     * 保存分类员工信息
     * */
    void saveClassUser(ClassifyStaffRelationEntity classifyStaffRelationEntity);

    /**
     * 修改分类员工信息
     * */
    void updateClassUser(ClassifyStaffRelationEntity classifyStaffRelationEntity);

    /**
     * 根据员工id查询项目分类人员信息
     * */
    ClassifyStaffRelationEntity getClassifyStaffRelation(String id);



    /**
     * 根据分类id查询分类信息
     * */
    ComplainClassifyEntity getComplainClassifyEntity(String id);

    /**
     * 保存分类信息
     * */
    void saveClass(ComplainClassifyEntity complainClassifyEntity);

    /**
     * 修改分类信息
     * */
    void updateClass(ComplainClassifyEntity complainClassifyEntity);

    /**
     * 根据项目编码和分类id组合删除分类人员关系
     * */
    boolean deleteClassifyStaff(List<String> idList);
}
