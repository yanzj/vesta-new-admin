package com.maxrocky.vesta.application.baseData.impl;

import com.maxrocky.vesta.application.baseData.JsonDTO.BaseDataDTO;
import com.maxrocky.vesta.application.baseData.JsonDTO.FloorImageDTO;
import com.maxrocky.vesta.application.baseData.JsonDTO.ProjectBuildDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.BuildingDownloadExcelModelDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.ProjectBuildingDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.ProjectFloorDTO;
import com.maxrocky.vesta.application.baseData.inf.ProjectBuildingService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.ProjectBuildingEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectFloorEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectHouseImageEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectBuildingRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectFloorRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectHouseImageRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectTendersRepository;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.HouseFloorEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.*;
import com.mysql.jdbc.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chen on 2016/10/18.
 */

@Service
public class ProjectBuildingServiceImpl implements ProjectBuildingService {
    @Autowired
    ProjectBuildingRepository projectBuildingRepository;
    @Autowired
    ProjectFloorRepository projectFloorRepository;
    @Autowired
    ImgService imgService;
    @Autowired
    ProjectHouseImageRepository projectHouseImageRepository;
    @Autowired
    ProjectTendersRepository projectTendersRepository;

    @Override
    public List<ProjectBuildingDTO> getProjectBuildings(String projectId, WebPage webPage) {
        List<ProjectBuildingEntity> projectBuildingEntities = projectBuildingRepository.getBuildingsByProjectId(projectId, webPage);
        List<ProjectBuildingDTO> projectBuildingDTOs = new ArrayList<ProjectBuildingDTO>();
        if (projectBuildingEntities != null) {
            ProjectBuildingDTO projectBuildingDTO;
            for (ProjectBuildingEntity projectBuildingEntity : projectBuildingEntities) {
                projectBuildingDTO = new ProjectBuildingDTO();
                projectBuildingDTO.setBuildId(String.valueOf(projectBuildingEntity.getId()));
                projectBuildingDTO.setBuildName(projectBuildingEntity.getName());
                projectBuildingDTO.setCreateOn(DateUtils.format(projectBuildingEntity.getCreateOn()));
                String floorNum = projectFloorRepository.getFloorNumByBuildId(projectBuildingEntity.getId());
                if (StringUtil.isEmpty(floorNum)) {
                    floorNum = "0";
                }
                projectBuildingDTO.setFloorNum(floorNum);
                projectBuildingDTOs.add(projectBuildingDTO);
            }
        }
        return projectBuildingDTOs;
    }

    @Override
    public List<ProjectBuildingDTO> getBuildingList(String projectId) {
        List<ProjectBuildingEntity> projectBuildingEntities = projectBuildingRepository.getBuildingsByProjectId(projectId);
        List<ProjectBuildingDTO> projectBuildingDTOs = new ArrayList<ProjectBuildingDTO>();
        if (projectBuildingEntities != null) {
            ProjectBuildingDTO projectBuildingDTO;
            for (ProjectBuildingEntity projectBuildingEntity : projectBuildingEntities) {
                projectBuildingDTO = new ProjectBuildingDTO();
                projectBuildingDTO.setBuildId(String.valueOf(projectBuildingEntity.getId()));
                projectBuildingDTO.setBuildName(projectBuildingEntity.getName());
                projectBuildingDTO.setCreateOn(DateUtils.format(projectBuildingEntity.getModifyOn()));
                projectBuildingDTOs.add(projectBuildingDTO);
            }
        }
        return projectBuildingDTOs;
    }

    @Override
    public List<ProjectBuildingDTO> getBuildsByProjectId(String projectId, String tendId) {
        List<String> buildList = projectTendersRepository.getTendBuildsByProjectId(projectId, tendId);
        List<ProjectBuildingEntity> projectBuildingEntities = projectBuildingRepository.getBuildingsByProjectId(projectId);
        List<ProjectBuildingEntity> temp = new ArrayList<ProjectBuildingEntity>();
        List<ProjectBuildingDTO> projectBuildingDTOs = new ArrayList<ProjectBuildingDTO>();
        if (buildList != null) {
            for (String buildId : buildList) {
                for (ProjectBuildingEntity projectBuildingEntity : projectBuildingEntities) {
                    if (buildId.equals(projectBuildingEntity.getId())) {
                        temp.add(projectBuildingEntity);
                    }
                }
            }
        }

        if (projectBuildingEntities != null) {
            projectBuildingEntities.removeAll(temp);
            ProjectBuildingDTO projectBuildingDTO;
            for (ProjectBuildingEntity projectBuildingEntity : projectBuildingEntities) {
                projectBuildingDTO = new ProjectBuildingDTO();
                projectBuildingDTO.setBuildId(String.valueOf(projectBuildingEntity.getId()));
                projectBuildingDTO.setBuildName(projectBuildingEntity.getName());
                projectBuildingDTO.setCreateOn(DateUtils.format(projectBuildingEntity.getModifyOn()));
                projectBuildingDTOs.add(projectBuildingDTO);
            }
        }
        return projectBuildingDTOs;
    }

    @Override
    public ProjectBuildingDTO getProjectBuildingDetail(String buildId) {
        ProjectBuildingEntity projectBuildingEntity = projectBuildingRepository.getBuildDetail(buildId);
        ProjectBuildingDTO projectBuildingDTO = new ProjectBuildingDTO();
        projectBuildingDTO.setBuildId(String.valueOf(projectBuildingEntity.getId()));
        projectBuildingDTO.setBuildName(projectBuildingEntity.getName());
        projectBuildingDTO.setFloorNum(String.valueOf(projectFloorRepository.getFloorNumByBuildId(projectBuildingEntity.getId())));
        return projectBuildingDTO;
    }

    @Override
    public void delProjectBuilding(String buildId) {
        projectBuildingRepository.delProjectBuild(buildId);
        List<ProjectFloorEntity> projectFloorEntityList = projectFloorRepository.getProjectFloorsByBuildId(buildId);
        if (projectFloorEntityList != null && projectFloorEntityList.size() > 0) {
            for (ProjectFloorEntity projectFloorEntity : projectFloorEntityList) {
                projectFloorEntity.setFloorState("0");
                projectFloorEntity.setModifyOn(new Date());
                projectFloorRepository.updateProjectFloor(projectFloorEntity);
                ProjectHouseImageEntity projectHouseImageEntity = projectHouseImageRepository.getImageByFloorId(projectFloorEntity.getFloorId());
                if (projectHouseImageEntity != null) {
                    projectHouseImageEntity.setImgState("0");
                    projectHouseImageEntity.setModifyOn(new Date());
                    projectHouseImageRepository.saveOrUpdateHouseImg(projectHouseImageEntity);
                }
            }
        }
    }

    @Override
    public ApiResult updateProjectBuilding(ProjectBuildingDTO projectBuildingDTO, String projectId) {
        ProjectBuildingEntity projectBuildingEntity = projectBuildingRepository.getBuildDetail(projectBuildingDTO.getBuildId());
        if (projectBuildingRepository.checkThisName(projectBuildingDTO.getBuildId(), projectBuildingDTO.getBuildName(), projectId)) {
            projectBuildingEntity.setName(projectBuildingDTO.getBuildName());
            projectBuildingEntity.setModifyOn(new Date());
            projectBuildingRepository.altProjectBuild(projectBuildingEntity);
            return new SuccessApiResult("ok");
        } else {
            return new SuccessApiResult("该楼栋已存在！");
        }
    }

    @Override
    public List<ProjectFloorDTO> getProjectFloors(String buildId, WebPage webPage) {
        List<ProjectFloorEntity> projectFloorEntities = projectFloorRepository.getFloorsByBuildId(buildId, webPage);
        List<ProjectFloorDTO> projectFloorDTOs = new ArrayList<ProjectFloorDTO>();
        if (projectFloorEntities != null) {
            ProjectFloorDTO projectFloorDTO;
            for (ProjectFloorEntity projectFloorEntity : projectFloorEntities) {
                projectFloorDTO = new ProjectFloorDTO();
                projectFloorDTO.setFloorId(projectFloorEntity.getFloorId());
                projectFloorDTO.setFloorName(projectFloorEntity.getFloorName());
                projectFloorDTO.setCreateOn(DateUtils.format(projectFloorEntity.getCreateOn()));
                projectFloorDTO.setModifyOn(DateUtils.format(projectFloorEntity.getModifyOn()));
                projectFloorDTOs.add(projectFloorDTO);
            }
        }
        return projectFloorDTOs;
    }

    @Override
    public Map<String,String> getProjectFloors(String buildingId){
        List<ProjectFloorEntity> projectFloorEntities = projectFloorRepository.getFloorsByBuildId(buildingId,null);
        Map<String, String> floors = new LinkedHashMap<>();
        floors.put("", "请选择");
        if (projectFloorEntities != null && projectFloorEntities.size() > 0) {
            for (ProjectFloorEntity projectFloorEntity : projectFloorEntities) {
                floors.put(projectFloorEntity.getFloorId(), projectFloorEntity.getFloorName());
            }
        }
        return floors;
    }

    @Override
    public void delProjectFloor(String floorID, UserInformationEntity userPropertyStaffEntity) {
        ProjectFloorEntity projectFloorEntity = projectFloorRepository.getProjectFloorDetail(floorID);
        projectFloorEntity.setFloorState("0");
        projectFloorEntity.setModifyOn(new Date());
        projectFloorRepository.updateProjectFloor(projectFloorEntity);

        //并将对应的楼层id的户型图删除
        ProjectHouseImageEntity projectHouseImageEntity = projectHouseImageRepository.getImageByFloorId(floorID);
        if (projectHouseImageEntity != null) {
            projectHouseImageEntity.setImgState("0");
            projectHouseImageEntity.setModifyOn(new Date());
            projectHouseImageEntity.setModifyBy(userPropertyStaffEntity.getStaffName());
            projectHouseImageRepository.saveOrUpdateHouseImg(projectHouseImageEntity);
        }
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 新增楼栋，添加信息为楼栋名称
     */
    @Override
    public ApiResult insertBuilding(String buildingName, String projectId) {
        if (projectBuildingRepository.checkThisName("", buildingName, projectId)) {
            ProjectBuildingEntity projectBuildingEntity = new ProjectBuildingEntity();
            projectBuildingEntity.setId(UUID.randomUUID().toString());
            projectBuildingEntity.setName(buildingName);
            projectBuildingEntity.setProjectId(projectId);
            projectBuildingEntity.setState("1");//正常
            projectBuildingEntity.setCreateOn(new Date());
            projectBuildingEntity.setModifyOn(new Date());
            projectBuildingRepository.insertBuilding(projectBuildingEntity);
            return new SuccessApiResult("ok");
        } else {
            return new SuccessApiResult("该楼栋已存在！");
        }
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 新增楼层
     */
    @Override
    public String insertFloor(ProjectFloorDTO projectFloorDTO,  UserInformationEntity userInformationEntity) {
        if (!projectFloorRepository.checkThisFloorName(projectFloorDTO.getFloorName() + "层", projectFloorDTO.getBuildId(), "")) {
            ProjectFloorEntity projectFloorEntity = new ProjectFloorEntity();
            projectFloorEntity.setFloorId(UUID.randomUUID().toString());
            projectFloorEntity.setBuildId(projectFloorDTO.getBuildId());
            projectFloorEntity.setFloorName(projectFloorDTO.getFloorName() + "层");
            projectFloorEntity.setFloorState("1");//正常
            projectFloorEntity.setCreateOn(new Date());
            projectFloorEntity.setModifyOn(new Date());

            if (projectFloorDTO.getHomePageimgpath() != null && !StringUtil.isEmpty(projectFloorDTO.getHomePageimgpath().getOriginalFilename())) {
                ProjectHouseImageEntity projectHouseImageEntity = new ProjectHouseImageEntity();
                //存储图片(户型图)
                String fileName = imgService.uploadAdminImage(projectFloorDTO.getHomePageimgpath(), ImgType.ACTIVITY);
                //图片地址特殊处理
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                if (fileName.equals("")) {
                    fileName = "默认图";
                }
                projectHouseImageEntity.setImgId(UUID.randomUUID().toString());
                projectHouseImageEntity.setImgUrl(fileName);
                projectHouseImageEntity.setFloorId(projectFloorEntity.getFloorId());
                projectHouseImageEntity.setImgState("1");//正常
                projectHouseImageEntity.setCreateBy(userInformationEntity.getStaffName());
                projectHouseImageEntity.setCreateOn(new Date());
                projectHouseImageEntity.setModifyBy(userInformationEntity.getStaffName());
                projectHouseImageEntity.setModifyOn(new Date());
                projectHouseImageRepository.saveOrUpdateHouseImg(projectHouseImageEntity);
            }

            //保存新增楼层，以及，新增户型图
            projectFloorRepository.addProjectFloor(projectFloorEntity);

            return "3";
        } else {
            return "1";
        }

    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 批量添加楼层
     */
    @Override
    public void executeFloor(ProjectFloorDTO projectFloorDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        int first = Integer.parseInt(projectFloorDTO.getFirstFloorArea());
        int second = Integer.parseInt(projectFloorDTO.getSecondFloorArea());

        for (int i = first; i <= second; i++) {
            String floorName = i + "层";
            if (i != 0) {   //没有0层
                if (projectFloorDTO.getHomePageimgpathExecute() != null && !StringUtil.isEmpty(projectFloorDTO.getHomePageimgpathExecute().getOriginalFilename())) {
                    //存储图片(户型图)
                    String fileName = imgService.uploadAdminImage(projectFloorDTO.getHomePageimgpathExecute(), ImgType.ACTIVITY);
                    //图片地址特殊处理
                    String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                    fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                    if (fileName.equals("")) {
                        fileName = "默认图";
                    }
                    //检验该楼层是否存在
                    ProjectFloorEntity projectFloorEntity = projectFloorRepository.getProjectFloor(floorName, projectFloorDTO.getBuildId());
                    if (projectFloorEntity == null) {
                        projectFloorEntity = new ProjectFloorEntity();
                        projectFloorEntity.setFloorId(UUID.randomUUID().toString());
                        projectFloorEntity.setBuildId(projectFloorDTO.getBuildId());
                        projectFloorEntity.setFloorName(i + "层");
                        projectFloorEntity.setFloorState("1");//正常
                        projectFloorEntity.setCreateOn(new Date());
                        projectFloorEntity.setModifyOn(new Date());
                        projectFloorRepository.addProjectFloor(projectFloorEntity);
                    }
                    ProjectHouseImageEntity projectHouseImageEntity = new ProjectHouseImageEntity();
                    projectHouseImageEntity.setImgId(UUID.randomUUID().toString());
                    projectHouseImageEntity.setImgUrl(fileName);
                    projectHouseImageEntity.setFloorId(projectFloorEntity.getFloorId());
                    projectHouseImageEntity.setImgState("1");//正常
                    projectHouseImageEntity.setCreateBy(userPropertyStaffEntity.getStaffName());
                    projectHouseImageEntity.setCreateOn(new Date());
                    projectHouseImageEntity.setModifyBy(userPropertyStaffEntity.getStaffName());
                    projectHouseImageEntity.setModifyOn(new Date());
                    projectHouseImageRepository.saveOrUpdateHouseImg(projectHouseImageEntity);
                } else {
                    if (!projectFloorRepository.checkThisFloorName(floorName, projectFloorDTO.getBuildId(), "")) {  //不存在则添加 否则忽略
                        ProjectFloorEntity projectFloorEntity = new ProjectFloorEntity();
                        projectFloorEntity.setFloorId(UUID.randomUUID().toString());
                        projectFloorEntity.setBuildId(projectFloorDTO.getBuildId());
                        projectFloorEntity.setFloorName(i + "层");
                        projectFloorEntity.setFloorState("1");//正常
                        projectFloorEntity.setCreateOn(new Date());
                        projectFloorEntity.setModifyOn(new Date());
                        projectFloorRepository.addProjectFloor(projectFloorEntity);
                    }
                }
            }
        }

    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 修改楼层
     */
    @Override
    public String updateFloor(ProjectFloorDTO projectFloorDTO,UserInformationEntity userInformationEntity) {
        //获取该楼层
        ProjectFloorEntity projectFloorEntity = projectFloorRepository.getProjectFloorDetail(projectFloorDTO.getFloorId());
        if (projectFloorEntity != null) {
            projectFloorEntity.setModifyOn(new Date());
            if (!StringUtils.isNullOrEmpty(projectFloorDTO.getFloorName()) && !projectFloorRepository.checkThisFloorName(projectFloorDTO.getFloorName() + "层", projectFloorEntity.getBuildId(), projectFloorDTO.getFloorId())) {
                projectFloorEntity.setFloorName(projectFloorDTO.getFloorName() + "层");

                if (projectFloorDTO.getHomePageimgpath() != null && !StringUtil.isEmpty(projectFloorDTO.getHomePageimgpath().getOriginalFilename())) {
                    MultipartFile file = projectFloorDTO.getHomePageimgpath();
                    String name = file.getOriginalFilename();
                    if (!"".equals(name)) {
                        //获取该楼层对应的户型图
                        ProjectHouseImageEntity projectHouseImageEntity = projectHouseImageRepository.getImageByFloorId(projectFloorDTO.getFloorId());
                        if (projectFloorDTO.getHomePageimgpath() != null) {
                            //存储图片(户型图)
                            String fileName = imgService.uploadAdminImage(projectFloorDTO.getHomePageimgpath(), ImgType.ACTIVITY);
                            //图片地址特殊处理
                            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                            if (projectHouseImageEntity != null) {
                                if (("").equals(fileName)) {
                                    fileName = "默认图";
                                    projectHouseImageEntity.setImgUrl("");
                                } else {
                                    fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                                    projectHouseImageEntity.setImgUrl(fileName);
                                }
                            } else {
                                projectHouseImageEntity = new ProjectHouseImageEntity();
                                if (("").equals(fileName)) {
                                    fileName = "默认图";
                                    projectHouseImageEntity.setImgUrl("");
                                } else {
                                    fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                                    projectHouseImageEntity.setImgUrl(fileName);
                                }
                                projectHouseImageEntity.setImgId(UUID.randomUUID().toString());
                                projectHouseImageEntity.setImgUrl(fileName);
                                projectHouseImageEntity.setFloorId(projectFloorEntity.getFloorId());
                                projectHouseImageEntity.setImgState("1");//正常
                                projectHouseImageEntity.setCreateBy(userInformationEntity.getStaffName());
                                projectHouseImageEntity.setCreateOn(new Date());
                            }
                            projectHouseImageEntity.setModifyBy(userInformationEntity.getStaffName());
                            projectHouseImageEntity.setModifyOn(new Date());
                            projectHouseImageRepository.saveOrUpdateHouseImg(projectHouseImageEntity);
                        }
                    }
                }
                projectFloorRepository.updateProjectFloor(projectFloorEntity);
                return "3";
            }
            return "1";
        } else
            return "2";
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param: floorId
     * @Description: 获取该楼层对应的户型图
     */
    @Override
    public ProjectHouseImageEntity getHouseImgByFloorId(String floorId) {
        ProjectHouseImageEntity projectHouseImageEntity = projectHouseImageRepository.getImageByFloorId(floorId);
        return projectHouseImageEntity;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 批量修改楼层
     */
    @Override
    public void executeUpdateFloor(ProjectFloorDTO projectFloorDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        int first = Integer.parseInt(projectFloorDTO.getFirstFloorAreaUpdate());
        int second = Integer.parseInt(projectFloorDTO.getSecondFloorAreaUpdate());

        for (int i = first; i <= second; i++) {  //没有0层
            if (i != 0) {
                String floorName = i + "层";
                //根据楼层名称，楼层状态以及楼栋id检验该楼层是否存在
                ProjectFloorEntity projectFloorEntity = projectFloorRepository.getProjectFloor(floorName, projectFloorDTO.getBuildId());
                if (projectFloorEntity != null) {
                    //批量修改楼层，实际上就是修改户型图
                    projectFloorEntity.setModifyOn(new Date());

                    //户型图修改
                    if (projectFloorDTO.getHomePageimgpathExecuteUpdate() != null && !StringUtil.isEmpty(projectFloorDTO.getHomePageimgpathExecuteUpdate().getOriginalFilename())) {
                        MultipartFile file = projectFloorDTO.getHomePageimgpathExecuteUpdate();
                        String name = file.getOriginalFilename();
                        if (!"".equals(name)) {
                            //获取该楼层对应的户型图
                            ProjectHouseImageEntity projectHouseImageEntity = projectHouseImageRepository.getImageByFloorId(projectFloorEntity.getFloorId());
                            //存储图片(户型图)
                            String fileName = imgService.uploadAdminImage(projectFloorDTO.getHomePageimgpathExecuteUpdate(), ImgType.ACTIVITY);
                            //图片地址特殊处理
                            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                            if (projectHouseImageEntity != null) {
                                if (("").equals(fileName)) {
                                    fileName = "默认图";
                                    projectHouseImageEntity.setImgUrl("");
                                } else {
                                    fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                                    projectHouseImageEntity.setImgUrl(fileName);
                                }
                            } else {
                                projectHouseImageEntity = new ProjectHouseImageEntity();
                                if (("").equals(fileName)) {
                                    fileName = "默认图";
                                    projectHouseImageEntity.setImgUrl("");
                                } else {
                                    fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                                    projectHouseImageEntity.setImgUrl(fileName);
                                }
                                projectHouseImageEntity.setImgId(UUID.randomUUID().toString());
                                projectHouseImageEntity.setImgUrl(fileName);
                                projectHouseImageEntity.setFloorId(projectFloorEntity.getFloorId());
                                projectHouseImageEntity.setImgState("1");//正常
                                projectHouseImageEntity.setCreateBy(userPropertyStaffEntity.getStaffName());
                                projectHouseImageEntity.setCreateOn(new Date());
                            }
                            projectHouseImageEntity.setModifyBy(userPropertyStaffEntity.getStaffName());
                            projectHouseImageEntity.setModifyOn(new Date());
                            projectHouseImageRepository.saveOrUpdateHouseImg(projectHouseImageEntity);
                        }
                    }
                    projectFloorRepository.updateProjectFloor(projectFloorEntity);
                }

            }
        }
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 获取最新的户型图
     */
    @Override
    public ProjectHouseImageEntity getHouseImg() {
        ProjectHouseImageEntity projectHouseImageEntity = projectHouseImageRepository.getHouseImg();
        return projectHouseImageEntity;
    }

    @Override
    public ApiResult getProjectBuildForTime(String projectId, String timeStamp, String autoNum) {
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        List<ProjectBuildingEntity> projectBuildingEntities = projectBuildingRepository.getBuildingForTime(projectId, timeStamp, Long.parseLong(autoNum));
        List<ProjectBuildDTO> projectBuildDTOs = new ArrayList<ProjectBuildDTO>();
        if (projectBuildingEntities != null && projectBuildingEntities.size() > 0) {
            ProjectBuildDTO projectBuildDTO;
            for (ProjectBuildingEntity projectBuildingEntity : projectBuildingEntities) {
                projectBuildDTO = new ProjectBuildDTO();
                projectBuildDTO.setCurrentId(projectBuildingEntity.getId());
                projectBuildDTO.setName(projectBuildingEntity.getName());
                projectBuildDTO.setParentId(projectBuildingEntity.getProjectId());
                projectBuildDTO.setState(projectBuildingEntity.getState());
                projectBuildDTO.setGrade("1");
                projectBuildDTOs.add(projectBuildDTO);
            }
            baseDataDTO.setId(String.valueOf(projectBuildingEntities.get(projectBuildingEntities.size() - 1).getAutoNum()));
            baseDataDTO.setTimeStamp(DateUtils.format(projectBuildingEntities.get(projectBuildingEntities.size() - 1).getModifyOn()));
        }
        baseDataDTO.setList(projectBuildDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    @Override
    public ApiResult getProjectFloorForTime(String projectId, String timeStamp, String autoNum) {
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        List<ProjectFloorEntity> projectFloorEntities = projectFloorRepository.getFloorListForTime(projectId, timeStamp, Long.parseLong(autoNum));
        List<ProjectBuildDTO> projectBuildDTOs = new ArrayList<ProjectBuildDTO>();
        if (projectFloorEntities != null && projectFloorEntities.size() > 0) {
            ProjectBuildDTO projectBuildDTO;
            for (ProjectFloorEntity projectFloorEntity : projectFloorEntities) {
                projectBuildDTO = new ProjectBuildDTO();
                projectBuildDTO.setCurrentId(projectFloorEntity.getFloorId());
                projectBuildDTO.setName(projectFloorEntity.getFloorName());
                projectBuildDTO.setParentId(projectFloorEntity.getBuildId());
                projectBuildDTO.setState(projectFloorEntity.getFloorState());
                projectBuildDTO.setGrade("2");
                projectBuildDTOs.add(projectBuildDTO);
            }
            baseDataDTO.setId(String.valueOf(projectFloorEntities.get(projectFloorEntities.size() - 1).getAutoNum()));
            baseDataDTO.setTimeStamp(DateUtils.format(projectFloorEntities.get(projectFloorEntities.size() - 1).getModifyOn()));
        }
        baseDataDTO.setList(projectBuildDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    @Override
    public ApiResult getFloorImageForTime(String projectId, String timeStamp, String autoNum) {
        List<ProjectHouseImageEntity> projectHouseImageEntities = projectFloorRepository.getHouseImageForTime(projectId, timeStamp, Long.parseLong(autoNum));
        List<FloorImageDTO> floorImageDTOs = new ArrayList<FloorImageDTO>();
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        if (projectHouseImageEntities != null && projectHouseImageEntities.size() > 0) {
            FloorImageDTO floorImageDTO;
            for (ProjectHouseImageEntity projectHouseImageEntity : projectHouseImageEntities) {
                floorImageDTO = new FloorImageDTO();
                floorImageDTO.setFloorId(projectHouseImageEntity.getFloorId());
                floorImageDTO.setImgDesc(projectHouseImageEntity.getImgDesc());
                floorImageDTO.setImgId(projectHouseImageEntity.getImgId());
                floorImageDTO.setImgState(projectHouseImageEntity.getImgState());
                floorImageDTO.setImgUrl(projectHouseImageEntity.getImgUrl());
                floorImageDTO.setImgName(projectHouseImageEntity.getImgName());
                floorImageDTOs.add(floorImageDTO);
            }
            baseDataDTO.setId(String.valueOf(projectHouseImageEntities.get(projectHouseImageEntities.size() - 1).getAutoNum()));
            baseDataDTO.setTimeStamp(DateUtils.format(projectHouseImageEntities.get(projectHouseImageEntities.size() - 1).getModifyOn()));
        }
        baseDataDTO.setList(floorImageDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 导出excel
     */
    @Override
    public String exportProjectBuildingExcel(String projectId, WebPage webPage, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();

        webPage = null;//导出所有数据（需求）

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        List<Object[]> list = projectFloorRepository.getBuildFloorsByProject(projectId);
        List<ProjectBuildingDTO> projectBuildingDTOs = new ArrayList<ProjectBuildingDTO>();
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                ProjectBuildingDTO projectBuildingDTO = new ProjectBuildingDTO();
                projectBuildingDTO.setBuildName((String) obj[0]);//楼栋名称
                projectBuildingDTO.setStartFloor(((BigDecimal) obj[1]).toString());//开始楼层
                projectBuildingDTO.setEndFloor(((BigDecimal) obj[2]).toString());//结束楼层

                projectBuildingDTOs.add(projectBuildingDTO);
            }
        }
        //获取所有楼栋信息
//        List<ProjectBuildingEntity> projectBuildingEntities = projectBuildingRepository.getBuildingsByProjectId(projectId, webPage);
//        List<ProjectBuildingDTO> projectBuildingDTOs = new ArrayList<ProjectBuildingDTO>();
//        if (projectBuildingEntities != null) {
//            ProjectBuildingDTO projectBuildingDTO;
//            for (ProjectBuildingEntity projectBuildingEntity : projectBuildingEntities) {
//                projectBuildingDTO = new ProjectBuildingDTO();
//                projectBuildingDTO.setBuildId(String.valueOf(projectBuildingEntity.getId()));
//                projectBuildingDTO.setBuildName(projectBuildingEntity.getName());
//                projectBuildingDTO.setCreateOn(DateUtils.format(projectBuildingEntity.getCreateOn()));
//                String floorNum = projectFloorRepository.getFloorNumByBuildId(projectBuildingEntity.getId());
//                if(StringUtil.isEmpty(floorNum)){
//                    floorNum="0";
//                }
//                projectBuildingDTO.setFloorNum(floorNum);
//                projectBuildingDTOs.add(projectBuildingDTO);
//            }
//        }

        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("楼栋信息列表");
        ExportUtil exportUtil = new ExportUtil(workBook, sheet);
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();

        // 百分比
        XSSFDataFormat fmt = workBook.createDataFormat();
        XSSFDataFormat fmt1 = workBook.createDataFormat();

        // 四个边框加粗
        XSSFCellStyle style1 = workBook.createCellStyle();
        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
        XSSFFont font = workBook.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        style1.setFont(font);

        String[] titles = {"楼栋名称", "开始楼层", "结束楼层"};
        XSSFRow headRow = sheet.createRow(0);

        if (projectBuildingDTOs.size() > 0) {

            projectBuildingDTOs.forEach(userDTO -> {

                XSSFCell cell = null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (projectBuildingDTOs.size() > 0) {
                    for (int i = 0; i < projectBuildingDTOs.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        ProjectBuildingDTO projectBuildingDTO = projectBuildingDTOs.get(i);

                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(projectBuildingDTO.getBuildName());//楼栋名称

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(projectBuildingDTO.getStartFloor());//开始楼层

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);
                        cell.setCellValue(projectBuildingDTO.getEndFloor());//结束楼层
                    }
                }
            });
        }
        try {
            String fileName = new String(("楼栋信息列表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("楼栋信息列表", "UTF8");
            }
            httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 下载模板
     */
    @Override
    public String downloadModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();

        //创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();

        //在workBook中添加一个sheet，对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("楼栋列表模板");

        ExportUtil exportUtil = new ExportUtil(workBook, sheet);
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();

        //百分比
        XSSFDataFormat fmt = workBook.createDataFormat();
        XSSFDataFormat fmt1 = workBook.createDataFormat();

        //四个边框加粗
        XSSFCellStyle style1 = workBook.createCellStyle();
        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style1.setVerticalAlignment(XSSFCellStyle.ALIGN_CENTER);
        style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);

        //设置字体加粗
        XSSFFont font = workBook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        style1.setFont(font);

        String[] titles = {"楼栋名称", "开始楼层（只能是数字）", "结束楼层（只能是数字）"};

        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = headRow.createCell(i);
            headRow.createCell(i).setCellValue(titles.length);

            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }

        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);

        XSSFRow bodyRow = sheet.createRow(1);
        cell = bodyRow.createCell(0);
        cell.setCellStyle(bodyStyle);//表格黑色边框
        cell.setCellValue("楼栋1");

        cell = bodyRow.createCell(1);
        cell.setCellStyle(bodyStyle);//表格黑色边框
        cell.setCellValue("-1");

        cell = bodyRow.createCell(2);
        cell.setCellStyle(bodyStyle);//表格黑色边框
        cell.setCellValue("10");
        try {
            String fileName = new String("楼栋列表模板".getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("楼栋列表模板", "UTF8");
            }
            httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 导入excel
     */
    @Override
    public boolean importExcelByPoi(UserInformationEntity user, InputStream fis, String projectId) {
        ProjectBuildingEntity projectBuildEntity = null;
        ProjectFloorEntity projectFloorEntity = null;

        try {
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                sheet = hwb.getSheetAt(i);
                //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    projectBuildEntity = new ProjectBuildingEntity();
                    projectFloorEntity = new ProjectFloorEntity();

                    /*此方法规定Excel文件中的数据必须为文本格式，所以在解析数据的时候未进行判断*/

                    //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                    if (ProjectBuildingServiceImpl.getCellValue(row.getCell(0)) != null && !"".equals(ProjectBuildingServiceImpl.getCellValue(row.getCell(0)))) {
                        //新增楼栋的时候，需要判断该楼栋的名字是否在数据库中存在
                        //存在则修改，不存在则新建
                        projectBuildEntity = projectBuildingRepository.getProjectBuildByName(ProjectBuildingServiceImpl.getCellValue(row.getCell(0)).trim(), projectId);
                        if (projectBuildEntity != null) {
                            //修改
                            projectBuildEntity.setModifyOn(new Date());
                            projectBuildingRepository.altProjectBuild(projectBuildEntity);
                        } else {
                            projectBuildEntity = new ProjectBuildingEntity();
                            //新增
                            projectBuildEntity.setId(UUID.randomUUID().toString());
                            projectBuildEntity.setName(ProjectBuildingServiceImpl.getCellValue(row.getCell(0)));
                            projectBuildEntity.setProjectId(projectId);
                            projectBuildEntity.setState("1");
                            projectBuildEntity.setCreateOn(new Date());
                            projectBuildEntity.setModifyOn(new Date());
                            projectBuildingRepository.insertBuilding(projectBuildEntity);
                        }
                    }
                    if (ProjectBuildingServiceImpl.getCellValue(row.getCell(1)) != null && !"".equals(ProjectBuildingServiceImpl.getCellValue(row.getCell(1)))) {
                        String floorArea = ProjectBuildingServiceImpl.getCellValue(row.getCell(1));
                        if (!StringUtils.isNullOrEmpty(floorArea)) {
                            String[] areas = floorArea.split("~");
                            if (areas.length != 2) {
                                return false;
                            } else {
                                String firstArea = areas[0];
                                String secondArea = areas[1];
                                if (!StringUtils.isNullOrEmpty(firstArea) && !StringUtils.isNullOrEmpty(secondArea)) {
                                    int first = Integer.parseInt(firstArea.trim());
                                    int second = Integer.parseInt(secondArea.trim());
                                    for (int m = first; m <= second; m++) {
                                        if (m != 0) {
                                            //新增楼层的时候，判断该楼层是否存在
                                            projectFloorEntity = projectFloorRepository.getProjectFloor(m + "层", projectBuildEntity.getId());
                                            if (projectFloorEntity != null) {
                                                projectFloorEntity.setModifyOn(new Date());
                                                projectFloorRepository.updateProjectFloor(projectFloorEntity);
                                            } else {
                                                projectFloorEntity = new ProjectFloorEntity();
                                                projectFloorEntity.setFloorId(UUID.randomUUID().toString());
                                                projectFloorEntity.setBuildId(projectBuildEntity.getId());
                                                projectFloorEntity.setFloorName(m + "层");
                                                projectFloorEntity.setFloorState("1");//正常
                                                projectFloorEntity.setCreateOn(new Date());
                                                projectFloorEntity.setModifyOn(new Date());
                                                projectFloorRepository.addProjectFloor(projectFloorEntity);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map getBuildListByProjectId(String projectId) {
        List<ProjectBuildingEntity> projectBuildingEntities = projectBuildingRepository.getBuildingsByProjectId(projectId);
        Map<String, String> units = new LinkedHashMap<>();
        units.put("", "请选择");
        if (projectBuildingEntities != null && projectBuildingEntities.size() > 0) {
            for (ProjectBuildingEntity projectBuildingEntity : projectBuildingEntities) {
                units.put(projectBuildingEntity.getId(), projectBuildingEntity.getName());
            }
        }
        return units;
    }

    @Override
    public Map getBuildListByProjectIds(List<String> projectIds){
        Map<String, String> units = new LinkedHashMap<>();
        units.put("", "请选择");
        if(projectIds != null && projectIds.size() > 0){
            //key为数组下标，value为数组大小
            Map<Integer,Integer> map = new HashMap<>();
            for (int i = 0; i < projectIds.size(); i++) {
                int size = projectBuildingRepository.getBuildingsCountByProjectId(projectIds.get(i));
                map.put(i,size);
            }
            if(map != null){
                Collection<Integer> c = map.values();
                Object[] obj = c.toArray();
                //排序
                Arrays.sort(obj);
                //获取最后一个
                int value =  (int)obj[obj.length-1];
                int index = 0;
                for (int i = 0; i < map.size(); i++) {
                    if(map.get(i) == value){
                        index = i;
                    }
                }
                List<ProjectBuildingEntity> projectBuildingEntities = projectBuildingRepository.getBuildingsByProjectId(projectIds.get(index));
                if (projectBuildingEntities.size()>0){
                    for (ProjectBuildingEntity projectBuildingEntity:projectBuildingEntities){
                        units.put(projectBuildingEntity.getId(), projectBuildingEntity.getName());
                    }
                }
            }
        }
        return units;
    }

    @Override
    public List<String> getDutysBuildId(String dutyId) {
        List<String> buildIds = projectBuildingRepository.getBuildSupplier(dutyId);
        return buildIds;
    }

    @Override
    public void downloadModel(String title, String[] headers, ServletOutputStream out) {
        List<BuildingDownloadExcelModelDTO> dataset = new ArrayList<BuildingDownloadExcelModelDTO>();
        // 导出数据
        ExportExcel<BuildingDownloadExcelModelDTO> ex = new ExportExcel<BuildingDownloadExcelModelDTO>();
        dataset.add(new BuildingDownloadExcelModelDTO("一号楼", "-1", "10"));
        dataset.add(new BuildingDownloadExcelModelDTO("二号楼", "-2", "20"));
        dataset.add(new BuildingDownloadExcelModelDTO("三号楼", "-3", "30"));
        ex.exportExcel(title, headers, dataset, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    @Override
    public boolean importBuildsExcel(UserInformationEntity userPropertyStaffEntity, InputStream fis, String projectId) {
        try {
            ProjectBuildingEntity projectBuildEntity = null;
            ProjectFloorEntity projectFloorEntity = null;
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                sheet = hwb.getSheetAt(i);
                //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    //判断导入的数据是否为空
                    if (StringUtil.isEmpty(ProjectBuildingServiceImpl.getCellValue(row.getCell(0))) || StringUtil.isEmpty(ProjectBuildingServiceImpl.getCellValue(row.getCell(1))) || StringUtil.isEmpty(ProjectBuildingServiceImpl.getCellValue(row.getCell(2)))) {
                        return false;
                    }
                    //判断楼层是否正确
                    Pattern pattern = Pattern.compile("-?[0-9]*");
                    Matcher floorStar = pattern.matcher(ProjectBuildingServiceImpl.getCellValue(row.getCell(1)));
                    Matcher floorEnd = pattern.matcher(ProjectBuildingServiceImpl.getCellValue(row.getCell(2)));
                    if (!floorStar.matches()) {
                        return false;
                    }
                    if (!floorEnd.matches()) {
                        return false;
                    }
                    int first = Integer.parseInt(ProjectBuildingServiceImpl.getCellValue(row.getCell(1)).trim());
                    int second = Integer.parseInt(ProjectBuildingServiceImpl.getCellValue(row.getCell(2)).trim());
                    //根据楼栋名称去查询是否存在（如果是修改）
                    projectBuildEntity = projectBuildingRepository.getProjectBuildByName(ProjectBuildingServiceImpl.getCellValue(row.getCell(0)).trim(), projectId);
                    if (projectBuildEntity != null) {
                        //修改
                        projectBuildEntity.setName(ProjectBuildingServiceImpl.getCellValue(row.getCell(0)));
                        projectBuildEntity.setModifyOn(new Date());
                        projectBuildingRepository.altProjectBuild(projectBuildEntity);
                        for (int m = first; m <= second; m++) {
                            if (m != 0) {
                                //新增楼层的时候，判断该楼层是否存在
                                projectFloorEntity = projectFloorRepository.getProjectFloor(m + "层", projectBuildEntity.getId());
                                if (projectFloorEntity != null) {
                                    projectFloorEntity.setFloorName(m + "层");
                                    projectFloorEntity.setModifyOn(new Date());
                                    projectFloorRepository.updateProjectFloor(projectFloorEntity);
                                } else {
                                    projectFloorEntity = new ProjectFloorEntity();
                                    projectFloorEntity.setFloorId(UUID.randomUUID().toString());
                                    projectFloorEntity.setBuildId(projectBuildEntity.getId());
                                    projectFloorEntity.setFloorName(m + "层");
                                    projectFloorEntity.setFloorState("1");//正常
                                    projectFloorEntity.setCreateOn(new Date());
                                    projectFloorEntity.setModifyOn(new Date());
                                    projectFloorRepository.addProjectFloor(projectFloorEntity);
                                }
                            }
                        }
                    } else {
                        projectBuildEntity = new ProjectBuildingEntity();
                        //新增
                        projectBuildEntity.setId(UUID.randomUUID().toString());
                        projectBuildEntity.setName(ProjectBuildingServiceImpl.getCellValue(row.getCell(0)));
                        projectBuildEntity.setProjectId(projectId);
                        projectBuildEntity.setState("1");
                        projectBuildEntity.setCreateOn(new Date());
                        projectBuildEntity.setModifyOn(new Date());
                        projectBuildingRepository.insertBuilding(projectBuildEntity);
                        for (int m = first; m <= second; m++) {
                            if (m != 0) {
                                projectFloorEntity = new ProjectFloorEntity();
                                projectFloorEntity.setFloorId(UUID.randomUUID().toString());
                                projectFloorEntity.setBuildId(projectBuildEntity.getId());
                                projectFloorEntity.setFloorName(m + "层");
                                projectFloorEntity.setFloorState("1");//正常
                                projectFloorEntity.setCreateOn(new Date());
                                projectFloorEntity.setModifyOn(new Date());
                                projectFloorRepository.addProjectFloor(projectFloorEntity);
                            }
                        }
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateBatchFloorFromJason(ProjectFloorDTO projectFloorDTO,UserInformationEntity userInformationEntity) {

//        if (projectFloorDTO.getHomePageimgpathExecuteUpdate() != null && !StringUtil.isEmpty(projectFloorDTO.getHomePageimgpathExecuteUpdate().getOriginalFilename())) {
            //存储图片(户型图)
            String fileName="";
            if(!StringUtil.isEmpty(projectFloorDTO.getHomePageimgpathExecuteUpdate().getOriginalFilename())){
                fileName = imgService.uploadAdminImage(projectFloorDTO.getHomePageimgpathExecuteUpdate(), ImgType.ACTIVITY);
                //图片地址特殊处理
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            }

            int first = Integer.parseInt(projectFloorDTO.getFirstFloorAreaUpdate());
            int second = Integer.parseInt(projectFloorDTO.getSecondFloorAreaUpdate());
            for (int i = first; i <= second; i++) {//没有0层
                if (i != 0) {
                    String floorName = i + "层";
                    //根据楼层名称，楼层状态以及楼栋id检验该楼层是否存在
                    ProjectFloorEntity projectFloorEntity = projectFloorRepository.getProjectFloor(floorName, projectFloorDTO.getBuildId());
                    if (projectFloorEntity != null) {
                        //批量修改楼层，实际上就是修改户型图
                        projectFloorEntity.setModifyOn(new Date());
                        //获取该楼层对应的户型图
                        ProjectHouseImageEntity projectHouseImageEntity = projectHouseImageRepository.getImageByFloorId(projectFloorEntity.getFloorId());
                        if (projectHouseImageEntity != null) {
                            if (StringUtil.isEmpty(fileName)) {
                                projectHouseImageEntity.setImgUrl("");
                            } else {
                                projectHouseImageEntity.setImgUrl(fileName);
                            }
                        } else {
                            projectHouseImageEntity = new ProjectHouseImageEntity();
                            if (StringUtil.isEmpty(fileName)) {
                                projectHouseImageEntity.setImgUrl("");
                            } else {
                                projectHouseImageEntity.setImgUrl(fileName);
                            }
                            projectHouseImageEntity.setImgId(UUID.randomUUID().toString());
//                            projectHouseImageEntity.setImgUrl(fileName);
                            projectHouseImageEntity.setFloorId(projectFloorEntity.getFloorId());
                            projectHouseImageEntity.setImgState("1");//正常
                            projectHouseImageEntity.setCreateBy(userInformationEntity.getStaffName());
                            projectHouseImageEntity.setCreateOn(new Date());
                        }
                        projectHouseImageEntity.setModifyBy(userInformationEntity.getStaffName());
                        projectHouseImageEntity.setModifyOn(new Date());
                        projectHouseImageRepository.saveOrUpdateHouseImg(projectHouseImageEntity);
                        projectFloorRepository.updateProjectFloor(projectFloorEntity);
                    } else {
                        projectFloorEntity = new ProjectFloorEntity();
                        projectFloorEntity.setFloorId(UUID.randomUUID().toString());
                        projectFloorEntity.setBuildId(projectFloorDTO.getBuildId());
                        projectFloorEntity.setFloorName(i + "层");
                        projectFloorEntity.setFloorState("1");//正常
                        projectFloorEntity.setCreateOn(new Date());
                        projectFloorEntity.setModifyOn(new Date());
                        projectFloorRepository.addProjectFloor(projectFloorEntity);

                        ProjectHouseImageEntity projectHouseImageEntity = new ProjectHouseImageEntity();
                        projectHouseImageEntity.setImgId(UUID.randomUUID().toString());
                        projectHouseImageEntity.setImgUrl(fileName);
                        projectHouseImageEntity.setFloorId(projectFloorEntity.getFloorId());
                        projectHouseImageEntity.setImgState("1");//正常
                        projectHouseImageEntity.setCreateBy(userInformationEntity.getStaffName());
                        projectHouseImageEntity.setCreateOn(new Date());
                        projectHouseImageEntity.setModifyBy(userInformationEntity.getStaffName());
                        projectHouseImageEntity.setModifyOn(new Date());
                        projectHouseImageRepository.saveOrUpdateHouseImg(projectHouseImageEntity);
                    }
                }
            }
//        }
    }

    @Override
    public List<ProjectBuildingDTO> getBuildersBySupplierId(String supplierId,String projectId) {
        List<Object[]> list = projectBuildingRepository.getBuildBySupplierId(supplierId,projectId);
        List<ProjectBuildingDTO> projectBuildingDTOs = new ArrayList<ProjectBuildingDTO>();
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                ProjectBuildingDTO projectBuildingDTO = new ProjectBuildingDTO();
                projectBuildingDTO.setBuildId((String) obj[0]);
                projectBuildingDTO.setBuildName((String) obj[1]);

                projectBuildingDTOs.add(projectBuildingDTO);
            }
        }
        return projectBuildingDTOs;
    }

    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell) {
        String value = null;
        //简单的查检列类型
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long) cell.getNumericCellValue();
                value = dd + "";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }
}
