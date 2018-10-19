package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.DirectoryEntity;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/22.
 */
public interface DirectoryRepository {

    /**
     * 根据公司获取所有部门
     * @param companyName
     * @return
     */
    public List<String> listSectionByCompany(String companyName);

    /**
     * 根据部门获取相应电话
     * @param section
     * @return
     */
    public List<DirectoryEntity> listDirectoryBySection(String section,String companyName);


}
