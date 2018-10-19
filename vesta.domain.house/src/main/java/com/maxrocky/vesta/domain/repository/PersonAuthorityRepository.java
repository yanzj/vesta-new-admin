package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PersonnelAuthorityTimeEntity;

import java.util.List;

/**
 * Created by chen on 2016/9/1.
 */
public interface PersonAuthorityRepository {
    //新增
    void savePersonAuthority(PersonnelAuthorityTimeEntity personnelAuthorityTimeEntity);
    //有则修改 无则新增
    void dumpAdd(PersonnelAuthorityTimeEntity personnelAuthorityTimeEntity);
    //根据条件逻辑删除
    void delPersonAuthority(PersonnelAuthorityTimeEntity personnelAuthorityTimeEntity);
    //根据条件查询
    List<PersonnelAuthorityTimeEntity> getPersonAuthoritys(PersonnelAuthorityTimeEntity personnelAuthorityTimeEntity);
}
