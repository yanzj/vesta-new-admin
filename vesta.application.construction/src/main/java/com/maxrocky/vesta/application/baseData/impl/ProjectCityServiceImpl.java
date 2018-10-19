package com.maxrocky.vesta.application.baseData.impl;

import com.maxrocky.vesta.application.baseData.adminDTO.OperationDTO;
import com.maxrocky.vesta.application.baseData.inf.ProjectCityService;
import com.maxrocky.vesta.domain.baseData.model.ProjectCityEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectOperationEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectCityRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectOperationRepository;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chen on 2016/12/7.
 */
@Service
public class ProjectCityServiceImpl implements ProjectCityService {
    @Autowired
    ProjectCityRepository projectCityRepository;
    @Autowired
    ProjectOperationRepository projectOperationRepository;

    @Override
    public Map getCityListByOptId(String optId) {
        List<ProjectCityEntity> projectCityEntities = projectCityRepository.getCityByOptId(optId);
        Map<String, String> map = new HashMap<>();
        if (projectCityEntities != null) {
            projectCityEntities.forEach(projectCityEntity -> map.put(projectCityEntity.getCityId(), projectCityEntity.getCityName()));
        }
        return map;
    }

    @Override
    public Map getProjectOperationList() {
        List<ProjectOperationEntity> projectOperationEntities = projectOperationRepository.getProjectOperationList();
        Map<String, String> map = new HashMap<>();
        if (projectOperationEntities != null) {
            projectOperationEntities.forEach(projectOperationEntity -> map.put(projectOperationEntity.getOptId(), projectOperationEntity.getOptName()));
        }
        return map;
    }

    @Override
    public List<OperationDTO> getOperationList() {
        List<OperationDTO> operationDTOs = new ArrayList<OperationDTO>();
        List<ProjectOperationEntity> projectOperationEntities = projectOperationRepository.getProjectOperationList();
        if (projectOperationEntities != null) {
            OperationDTO operationDTO;
            for (ProjectOperationEntity projectOperationEntity : projectOperationEntities) {
                operationDTO = new OperationDTO();
                operationDTO.setOptId(projectOperationEntity.getOptId());
                operationDTO.setOptName(projectOperationEntity.getOptName());
                operationDTOs.add(operationDTO);
            }
        }
        return operationDTOs;
    }

    @Override
    public List<OperationDTO> getAllCityList() {
        List<OperationDTO> operationDTOs = new ArrayList<OperationDTO>();
        List<ProjectCityEntity> projectCityEntities = projectCityRepository.getAllCityList();
        if (projectCityEntities != null) {
            OperationDTO operationDTO;
            for (ProjectCityEntity projectCityEntity : projectCityEntities) {
                operationDTO = new OperationDTO();
                operationDTO.setOptId(projectCityEntity.getOptId());
                if (!StringUtil.isEmpty(projectCityEntity.getOptId())) {
                    ProjectOperationEntity projectOperationEntity = projectOperationRepository.getProjectOperationDetail(projectCityEntity.getOptId());
                    if (projectOperationEntity != null) {
                        operationDTO.setOptName(projectOperationEntity.getOptName());
                    }
                }
                operationDTO.setCityId(projectCityEntity.getCityId());
                operationDTO.setCityName(projectCityEntity.getCityName());
                operationDTOs.add(operationDTO);
            }
        }
        return operationDTOs;
    }

    @Override
    public void addProjectOperation(String optName) {
        ProjectOperationEntity projectOperationEntity = new ProjectOperationEntity();
        projectOperationEntity.setOptId(IdGen.uuid());
        projectOperationEntity.setOptName(optName);
        projectOperationEntity.setCreateOn(new Date());
        projectOperationEntity.setModifyOn(new Date());
        projectOperationEntity.setState("1");
        projectOperationRepository.addProjectOperation(projectOperationEntity);
    }

    @Override
    public void updateProjectOperation(OperationDTO operationDTO) {
        ProjectOperationEntity projectOperationEntity = projectOperationRepository.getProjectOperationDetail(operationDTO.getOptId());
        projectOperationEntity.setOptName(operationDTO.getOptName());
        projectOperationEntity.setModifyOn(new Date());
        projectOperationRepository.updateProjectOperation(projectOperationEntity);
    }

    @Override
    public void delProjectOperation(String optId) {
        ProjectOperationEntity projectOperationEntity = projectOperationRepository.getProjectOperationDetail(optId);
        projectOperationEntity.setState("0");
        projectOperationEntity.setModifyOn(new Date());
        projectOperationRepository.updateProjectOperation(projectOperationEntity);
    }

    @Override
    public void addProjectCity(OperationDTO operationDTO) {
        ProjectCityEntity projectCityEntity = new ProjectCityEntity();
        projectCityEntity.setCityId(IdGen.uuid());
        projectCityEntity.setCityName(operationDTO.getCityName());
        if (!StringUtil.isEmpty(operationDTO.getOptId()) || "0".equals(operationDTO.getOptId())) {
            projectCityEntity.setOptId(operationDTO.getOptId());
        }
        projectCityEntity.setCreateOn(new Date());
        projectCityEntity.setModifyOn(new Date());
        projectCityEntity.setState("1");
        projectCityRepository.addProjectCity(projectCityEntity);
    }

    @Override
    public void updateProjectCity(OperationDTO operationDTO) {
        ProjectCityEntity projectCityEntity = projectCityRepository.getCityDetail(operationDTO.getCityId());
        projectCityEntity.setCityName(operationDTO.getCityName());
        projectCityEntity.setModifyOn(new Date());
        if (!StringUtil.isEmpty(operationDTO.getOptId()) || "0".equals(operationDTO.getOptId())) {
            projectCityEntity.setOptId(operationDTO.getOptId());
        }
        projectCityRepository.updateProjectCity(projectCityEntity);
    }

    @Override
    public void delProjectCity(String cityId) {
        ProjectCityEntity projectCityEntity = projectCityRepository.getCityDetail(cityId);
        projectCityEntity.setState("0");
        projectCityEntity.setModifyOn(new Date());
        projectCityRepository.updateProjectCity(projectCityEntity);
    }

    @Override
    public boolean checkProjectCity(String cityName) {
        return projectCityRepository.checkCityDetail(cityName);
    }

    @Override
    public Map getProejctListByOptId(String optId) {
        List<Object[]> list = projectCityRepository.getProejctListByOptId(optId);
        Map<String, String> map = new HashMap<>();
        map.put("", "请选择");
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                map.put((String) obj[0], (String) obj[1]);
            }
        }
        return map;
    }
}
