package com.maxrocky.vesta.application.baseData.impl;

import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.inf.RoleViewmodelService;
import com.maxrocky.vesta.domain.baseData.model.ProjectBuildingEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectCategoryEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectTendersEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectBuildingRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectCategoryRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectProjectRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectTendersRepository;
import com.maxrocky.vesta.domain.model.RoleViewmodelEntity;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2016/10/18.
 */
@Service
public class GetAllClassifyServiceImpl implements GetAllClassifyService {
    @Autowired
    ProjectCategoryRepository projectCategoryRepository;
    @Autowired
    ProjectProjectRepository projectProjectRepository;
    @Autowired
    ProjectTendersRepository projectTendersRepository;
    @Autowired
    ProjectBuildingRepository projectBuildingRepository;
    @Autowired
    RoleViewmodelService roleViewmodelService;

    @Override
    public Map<String, String> getClassifyOne(String domain) {
        List<ProjectCategoryEntity> list = projectCategoryRepository.getProjectCategoryListAll(1, domain, null);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择一级分类");
        if (list != null && !list.isEmpty()) {
            for (ProjectCategoryEntity fce : list) {
                map.put(fce.getCategoryId(), fce.getCategoryName());
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getClassifyTwo(String firstid, String domain) {
        if (!StringUtil.isEmpty(firstid)) {
            List<ProjectCategoryEntity> list = projectCategoryRepository.getProjectCategoryListAll(2, domain, firstid);
            Map<String, String> map = new LinkedHashMap<>();
            map.put("", "请选择二级分类");
            if (list != null && !list.isEmpty()) {
                for (ProjectCategoryEntity fce : list) {
                    map.put(fce.getCategoryId(), fce.getCategoryName());
                }
            }
            return map;
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("", "请选择二级分类");
            return map;
        }

    }

    @Override
    public Map<String, String> getClassifyThree(String secondId, String domain) {
        if (!StringUtil.isEmpty(secondId)) {
            List<ProjectCategoryEntity> list = projectCategoryRepository.getProjectCategoryListAll(3, domain, secondId);
            Map<String, String> map = new LinkedHashMap<>();
            map.put("", "请选择三级分类");
            if (list != null && !list.isEmpty()) {
                for (ProjectCategoryEntity fce : list) {
                    map.put(fce.getCategoryId(), fce.getCategoryName());
                }
            }
            return map;
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("", "请选择三级分类");
            return map;
        }
    }

    @Override
    public Map<String, String> getClassifyFour(String secondId, String domain) {
        List<ProjectCategoryEntity> list = projectCategoryRepository.getProjectCategoryListAll(4, domain, secondId);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择四级分类");
        if (list != null && !list.isEmpty()) {
            for (ProjectCategoryEntity fce : list) {
                map.put(fce.getCategoryId(), fce.getCategoryName());
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getAllproject() {
        return null;
    }

    @Override
    public Map<String, String> getTendersByProjectId(String projectId) {
        List<ProjectTendersEntity> list = projectTendersRepository.getTendersByProjectId(projectId);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择标段");
        if (list != null && !list.isEmpty()) {
            for (ProjectTendersEntity pt : list) {
                map.put(pt.getTenderId(), pt.getTenderName());
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getBuildingByTenderId(String tenderId) {
        List<Object[]> list = projectBuildingRepository.getBuildingsByTenderId(tenderId);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择楼栋");
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                map.put((String) obj[0], (String) obj[1]);
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getBuildingForProjectNum(String projectNum) {
        return null;
    }

    @Override
    public Map<String, String> getDutyList(String projectId) {
        List<Object[]> list = projectProjectRepository.getDutyList(projectId);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择责任单位");
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                map.put(obj[0].toString(), obj[1].toString());
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getDutyPeople(String dutyId) {
        List<Object[]> list = projectProjectRepository.getDutyPeople(dutyId);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择整改人");
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                map.put(obj[0].toString(), obj[1].toString());
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getSurveyorUserList(String projectId) {
        List<Object[]> list = projectProjectRepository.getSurveyorUserList(projectId);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择监理");
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                map.put(obj[0].toString(), obj[1].toString());
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getProjectProjectsByStaffId(String staffId) {
        List<ProjectProjectEntity> list = projectProjectRepository.getProjectProjectsByStaffId(staffId);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择项目");
        if (list != null && !list.isEmpty()) {
            for (ProjectProjectEntity projectProjectEntity : list) {
                map.put(projectProjectEntity.getId(), projectProjectEntity.getName());
            }
        }
        return map;
    }

    @Override
    public boolean getRoleViewmodelByStaffId(String staffId) {
        //获取当前登录人的菜单权限
        List<RoleViewmodelEntity> oldlisttwo = roleViewmodelService.getViewListOtherByUserId("property", staffId);
        for (RoleViewmodelEntity roleViewmodelEntity : oldlisttwo) {
            //如果有（项目权限管理）菜单权限，查询全都项目；否则就查询自己所拥有的项目权限
            if (roleViewmodelEntity.getMenuId().equals("003100030000")) {
                return true;
            }
        }
        return false;
    }
}
