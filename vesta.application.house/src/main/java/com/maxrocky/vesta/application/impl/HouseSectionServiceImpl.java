package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.HouseSectionAdminDTO;
import com.maxrocky.vesta.application.inf.HouseSectionService;
import com.maxrocky.vesta.domain.model.HouseSectionEntity;
import com.maxrocky.vesta.domain.repository.HouseSectionRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/22.
 */
@Service
public class HouseSectionServiceImpl implements HouseSectionService {

    @Autowired
    private HouseSectionRepository houseSectionRepository;


    /**
     * 通过部门id获取部门信息
     * @param sectionId
     * @return
     */
    @Override
    public HouseSectionAdminDTO getSectionDTOById(String sectionId) {
        HouseSectionEntity houseSectionEntity = houseSectionRepository.getSectionById(sectionId);
        if (houseSectionEntity!=null){
            HouseSectionAdminDTO houseSectionAdminDTO = new HouseSectionAdminDTO();

            houseSectionAdminDTO.setSectionId(houseSectionEntity.getSectionId());
            houseSectionAdminDTO.setSectionName(houseSectionEntity.getSectionName());
//            houseSectionAdminDTO.setLevel(houseSectionEntity.getLevel());
            houseSectionAdminDTO.setCreateBy(houseSectionEntity.getCreateBy());
            houseSectionAdminDTO.setCreateOn(DateUtils.format(houseSectionEntity.getCreateOn()));
            houseSectionAdminDTO.setState(houseSectionEntity.getState());
            houseSectionAdminDTO.setModifyBy(houseSectionEntity.getModifyBy());
            houseSectionAdminDTO.setModifyOn(DateUtils.format(houseSectionEntity.getModifyOn()));


            return houseSectionAdminDTO;
        }
        else {
            return null;
        }
    }

    /**
     * 获取部门列表
     * @param projectId
     * @param webPage
     * @return
     */
    @Override
    public List<HouseSectionAdminDTO> listSectionByCompany(String projectId, WebPage webPage) {
        List<HouseSectionAdminDTO> houseSectionAdminDTOs = new ArrayList<>();
        List<HouseSectionEntity> houseSectionEntities = houseSectionRepository.listSectionByProject(projectId,webPage);
        if (houseSectionEntities.size()>0){
            for (HouseSectionEntity houseSectionEntity:houseSectionEntities){
                HouseSectionAdminDTO houseSectionAdminDTO = new HouseSectionAdminDTO();
                houseSectionAdminDTO.setSectionId(houseSectionEntity.getSectionId());//部门Id
                houseSectionAdminDTO.setSectionName(houseSectionEntity.getSectionName());//部门名称
//                houseSectionAdminDTO.setLevel(houseSectionEntity.getLevel());//当前级别
                houseSectionAdminDTOs.add(houseSectionAdminDTO);
            }
        }
        return houseSectionAdminDTOs;
    }

    @Override
    public List<HouseSectionAdminDTO> listSectionByProjecj(String project) {
        List<HouseSectionAdminDTO> houseSectionAdminDTOs = new ArrayList<>();
        List<HouseSectionEntity> houseSectionEntities = houseSectionRepository.listSectionByProject(project);
        HouseSectionAdminDTO section_0 = new HouseSectionAdminDTO();
        section_0.setSectionId("");
        section_0.setSectionName("-------------请选择部门-------------");
        houseSectionAdminDTOs.add(section_0);
        if (houseSectionEntities.size()>0){
            for (HouseSectionEntity houseSectionEntity:houseSectionEntities){
                HouseSectionAdminDTO houseSectionAdminDTO = new HouseSectionAdminDTO();
                houseSectionAdminDTO.setSectionId(houseSectionEntity.getSectionId());//部门Id
                houseSectionAdminDTO.setSectionName(houseSectionEntity.getSectionName());//部门名称
//                houseSectionAdminDTO.setLevel(houseSectionEntity.getLevel());//当前级别
                houseSectionAdminDTOs.add(houseSectionAdminDTO);
            }
        }
        return houseSectionAdminDTOs;
    }

    /**
     * 添加部门
     * @param houseSectionAdminDTO
     * @return
     */
    @Override
    public boolean addSection(HouseSectionAdminDTO houseSectionAdminDTO) {
        if (houseSectionAdminDTO.getSectionName()==null||houseSectionAdminDTO.getProjectId()==null){
            return  false;
        }
        HouseSectionEntity section = houseSectionRepository.testSectionByName(houseSectionAdminDTO.getSectionName(),houseSectionAdminDTO.getProjectId());
        if (section!=null&&section.getSectionName()!=null){
            return false;
        }
        HouseSectionEntity lastSection = houseSectionRepository.getLastSection(houseSectionAdminDTO.getProjectId());
        HouseSectionEntity houseSectionEntity = new HouseSectionEntity();
        houseSectionEntity.setSectionId(IdGen.uuid());//部门Id
        houseSectionEntity.setSectionName(houseSectionAdminDTO.getSectionName());//部门名称 1
        if (lastSection!=null) {
            houseSectionEntity.setLevel(lastSection.getLevel() + 1);//当前级别
        }else {
            houseSectionEntity.setLevel(1);//当前级别
        }
        houseSectionEntity.setState(HouseSectionEntity.SECTION_STATE_YES);//状态-有效
        houseSectionEntity.setCreateBy(houseSectionAdminDTO.getCreateBy());//创建人 1
        houseSectionEntity.setCreateOn(DateUtils.getDate());//创建时间
        houseSectionEntity.setModifyBy(houseSectionAdminDTO.getModifyBy());//修改人 1
        houseSectionEntity.setModifyOn(DateUtils.getDate());//修改时间
        houseSectionEntity.setCompanyId(houseSectionAdminDTO.getCompanyId());//公司Id 1
        houseSectionEntity.setProjectId(houseSectionAdminDTO.getProjectId());//项目Id 1
        boolean result = houseSectionRepository.addSection(houseSectionEntity);

        return result;
    }

    /**
     * 修改部门
     * @param houseSectionAdminDTO
     * @return
     */
    @Override
    public boolean updateSection(HouseSectionAdminDTO houseSectionAdminDTO) {
        HouseSectionEntity houseSectionEntity1 = houseSectionRepository.testSectionByName(houseSectionAdminDTO.getSectionName(),houseSectionAdminDTO.getProjectId());
        HouseSectionEntity houseSectionEntity = houseSectionRepository.getSectionById(houseSectionAdminDTO.getSectionId());
        if (houseSectionEntity1!=null&&!houseSectionEntity.getSectionId().equals(houseSectionEntity1.getSectionId())){
            return false;
        }
        if (houseSectionEntity==null){
            return false;
        }
        houseSectionEntity.setSectionName(houseSectionAdminDTO.getSectionName());//部门名称
        houseSectionEntity.setModifyBy(houseSectionEntity.getModifyBy());//修改人
        houseSectionEntity.setModifyOn(DateUtils.getDate());//修改时间
        boolean result = houseSectionRepository.updateSecton(houseSectionEntity);

        return result;
    }

    /**
     * 删除部门
     * @param houseSectionAdminDTO
     * @return
     */
    @Override
    public boolean delSection(HouseSectionAdminDTO houseSectionAdminDTO) {
        HouseSectionEntity houseSectionEntity = houseSectionRepository.getSectionById(houseSectionAdminDTO.getSectionId());
        if (houseSectionEntity==null){
            return false;
        }
        houseSectionEntity.setState(HouseSectionEntity.SECTION_STATE_NO);//状态-无效
        houseSectionEntity.setModifyBy(houseSectionAdminDTO.getModifyBy());//修改人
        houseSectionEntity.setModifyOn(DateUtils.getDate());//修改时间
        boolean result = houseSectionRepository.updateSecton(houseSectionEntity);

        return result;
    }

    /**
     * 不为级别上移或者下移
     * @param sectionId
     * @param moveStatus
     * @return
     */
    @Override
    public boolean updateLevel(String sectionId, String moveStatus,String projectId) {
        if (moveStatus==null){//如果上下移状态为空，返回失败
            return false;
        }
        HouseSectionEntity lastSection = houseSectionRepository.getLastSection(projectId);
        HouseSectionEntity nowSection = houseSectionRepository.getSectionById(sectionId);//获取要操作的部门
        int countSection = houseSectionRepository.countSection(projectId);
        int moveLevel = 0;//初始化目标部门级别
        HouseSectionEntity moveSection = new HouseSectionEntity();
        if (moveStatus.equals("up")||moveStatus.equals("down")){
            if (moveStatus.equals("up")&&nowSection.getLevel()==1){
                return false;//如果当前是第一个，则不能上移
            }
            if (moveStatus.equals("down")&&nowSection.getLevel()==lastSection.getLevel()){
                return false;//如果当前是最后一个，则不能下移
            }
            moveSection = houseSectionRepository.getSectionByLevel(moveStatus,projectId,nowSection.getLevel());//获取目标部门
        }
        else {
                return false;//如果当前是第一个，则不能上移
        }

        if (moveSection==null){
            return false;
        }
        moveLevel = moveSection.getLevel();
        moveSection.setLevel(nowSection.getLevel());//交换部门级别
        nowSection.setLevel(moveLevel);//交换部门级别
        boolean result = houseSectionRepository.updateSecton(nowSection)&&houseSectionRepository.updateSecton(moveSection);//更新级别信息

        return result;
    }
}
