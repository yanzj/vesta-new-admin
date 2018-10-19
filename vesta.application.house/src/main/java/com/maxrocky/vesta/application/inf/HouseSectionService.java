package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseSectionAdminDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/22.
 */
public interface HouseSectionService {

    /**
     * 通过部门Id获取部门信息
     * @param sectionId
     * @return
     */
        public HouseSectionAdminDTO getSectionDTOById(String sectionId);

    /**
     * 获取部门列表
     * @param projectId
     * @param webPage
     * @return
     */
    public List<HouseSectionAdminDTO> listSectionByCompany(String projectId,WebPage webPage);

    /**
     * 获取项目下所有部门
     * @param project
     * @return
     */
    public List<HouseSectionAdminDTO> listSectionByProjecj(String project);

    /**
     * 添加部门
     * @param houseSectionAdminDTO
     * @return
     */
    public boolean addSection(HouseSectionAdminDTO houseSectionAdminDTO);

    /**
     * 修改部门
     * @param houseSectionAdminDTO
     * @return
     */
    public boolean updateSection(HouseSectionAdminDTO houseSectionAdminDTO);

    /**
     * 删除部门
     * @param houseSectionAdminDTO
     * @return
     */
    public boolean delSection(HouseSectionAdminDTO houseSectionAdminDTO);

    /**
     * 部门排序上移或者下移
     * @param sectionId
     * @param moveStatus
     * @return
     */
    public boolean updateLevel(String sectionId,String moveStatus,String projectId);
}
