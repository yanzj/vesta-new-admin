package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseSectionEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/22.
 */

/**
 * 公司部门持久层
 */
public interface HouseSectionRepository {

    /**
     * 通过部门id获取部门信息
     * @param sectionId
     * @return
     */
    public HouseSectionEntity getSectionById(String sectionId);

    /**
     * 获得部门列表
     * @return
     */
    public List<HouseSectionEntity> listHouseSection();

    /**
     * 获取某项目下的所有部门
     * @param projectId
     * @return
     */
    public List<HouseSectionEntity> listSectionByProject(String projectId,WebPage webPage);

    /**
     * 添加部门
     * @param houseSectionEntity
     * @return
     */
    public boolean addSection(HouseSectionEntity houseSectionEntity);

    /**
     * 修改部门
     * @param houseSectionEntity
     * @return
     */
    public boolean updateSecton(HouseSectionEntity houseSectionEntity);

    /**
     * 获取项目下所有部门
     * @param projectId
     * @return
     */
    public List<HouseSectionEntity> listSectionByProject(String projectId);

    /**
     * 根据级别获取部门
     * @param moveStatus
     * @return
     */
    public HouseSectionEntity getSectionByLevel(String moveStatus,String projectId,int level);

    /**
     * 获取部门数量
     * @return
     */
    public int countSection(String projectId);

    /**
     * 获取最低级别部门
     * @param projectId
     * @return
     */
    public HouseSectionEntity getLastSection(String projectId);

    /**
     * 所有维修和客服部门(员工端)
     * createBy：liudongxin
     * @param projectId
     * @return
     */
    List<HouseSectionEntity> getDepartmentList(String projectId);

    /**
     * 所有维修部门(管理端)
     * createBy：liudongxin
     * @param projectId
     * @return
     */
    List<HouseSectionEntity> getServiceDepartment(String projectId);

    /**
     * 所有客服部门(管理端)
     * createBy：liudongxin
     * @param projectId
     * @return
     */
    List<HouseSectionEntity> getCustomerDepartment(String projectId);

    /**
     * 判断部门是否存在
     * @param sectionName
     * @param projectId
     * @return
     */
    public HouseSectionEntity testSectionByName(String sectionName,String projectId);
}
