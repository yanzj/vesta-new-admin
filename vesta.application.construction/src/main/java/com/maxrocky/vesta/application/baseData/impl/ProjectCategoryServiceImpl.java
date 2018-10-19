package com.maxrocky.vesta.application.baseData.impl;

import com.maxrocky.vesta.application.baseData.JsonDTO.*;
import com.maxrocky.vesta.application.baseData.adminDTO.*;
import com.maxrocky.vesta.application.baseData.inf.ProjectCategoryService;
import com.maxrocky.vesta.application.dto.adminDTO.batch.AuthSupplierRoleUserDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.*;
import com.maxrocky.vesta.domain.baseData.repository.ProjectCategoryRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectPeopleRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectProjectRepository;
import com.maxrocky.vesta.domain.baseData.repository.StaffEmployRepository;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.domain.repository.SupplierAgencyRepository;
import com.maxrocky.vesta.domain.repository.UserAgencyRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.utility.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chen on 2016/11/1.
 */
@Service
public class ProjectCategoryServiceImpl implements ProjectCategoryService {
    @Autowired
    ProjectCategoryRepository projectCategoryRepository;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    UserPropertyStaffRepository userPropertystaffReposiroty;
    @Autowired
    UserAgencyRepository userAgencyRepository;
    @Autowired
    ProjectPeopleRepository projectPeopleRepository;
    @Autowired
    ProjectProjectRepository projectProjectRepository;
    @Autowired
    StaffEmployRepository staffEmployRepository;
    @Autowired
    SupplierAgencyRepository supplierAgencyRepository;

    @Override
    public ApiResult getCategoryForTime(String autoId, String timeStamp) {
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        List<ProjectCategoryDTO> projectCategoryDTOs = new ArrayList<ProjectCategoryDTO>();
        List<ProjectCategoryEntity> projectCategoryEntities = projectCategoryRepository.getCategoryListForTime(Long.parseLong(autoId), timeStamp);
        if (projectCategoryEntities != null && projectCategoryEntities.size() > 0) {
            ProjectCategoryDTO projectCategoryDTO;
            for (ProjectCategoryEntity projectCategoryEntity : projectCategoryEntities) {
                projectCategoryDTO = new ProjectCategoryDTO();
                projectCategoryDTO.setCategoryId(projectCategoryEntity.getCategoryId());
                projectCategoryDTO.setCategoryName(projectCategoryEntity.getCategoryName());
                projectCategoryDTO.setCategoryState(projectCategoryEntity.getCategoryState());
                projectCategoryDTO.setDomain(projectCategoryEntity.getDomain());
                projectCategoryDTO.setLevel(projectCategoryEntity.getLevel());
                projectCategoryDTO.setParentId(projectCategoryEntity.getParentId());
                projectCategoryDTO.setFreeFiled(projectCategoryEntity.getFreeField());
                projectCategoryDTO.setTimeSpace(projectCategoryEntity.getTimeSpace());
                projectCategoryDTOs.add(projectCategoryDTO);
            }
            baseDataDTO.setId(String.valueOf(projectCategoryEntities.get(projectCategoryEntities.size() - 1).getAutoNum()));
            baseDataDTO.setTimeStamp(DateUtils.format(projectCategoryEntities.get(projectCategoryEntities.size() - 1).getModifyOn()));
        }
        baseDataDTO.setList(projectCategoryDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    @Override
    public ApiResult getCategoryDutyForTime(String projectId, String autoId, String timeStamp) {
        List<SupplierCategoryEntity> supplierCategoryEntities = projectCategoryRepository.getSupplierCategoryForTime(projectId, timeStamp, Long.parseLong(autoId));
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        List<CategoryDutyDTO> categoryDutyDTOs = new ArrayList<CategoryDutyDTO>();
        if (supplierCategoryEntities != null && supplierCategoryEntities.size() > 0) {
            CategoryDutyDTO categoryDutyDTO;
            for (SupplierCategoryEntity supplierCategoryEntity : supplierCategoryEntities) {
                categoryDutyDTO = new CategoryDutyDTO();
                categoryDutyDTO.setCategoryId(supplierCategoryEntity.getCategoryId());
                categoryDutyDTO.setDutyId(supplierCategoryEntity.getSupplierId());
                categoryDutyDTO.setStatus(supplierCategoryEntity.getStatus());
                categoryDutyDTO.setId(String.valueOf(supplierCategoryEntity.getId()));
                categoryDutyDTO.setDomain(supplierCategoryEntity.getDomain());
                categoryDutyDTOs.add(categoryDutyDTO);
            }
            baseDataDTO.setId(String.valueOf(supplierCategoryEntities.get(supplierCategoryEntities.size() - 1).getId()));
            baseDataDTO.setTimeStamp(DateUtils.format(supplierCategoryEntities.get(supplierCategoryEntities.size() - 1).getModifyTime()));
        }
        baseDataDTO.setList(categoryDutyDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    @Override
    public ApiResult getBuildDutyForTime(String projectId, String autoId, String timeStamp) {
        List<BuildingSupplierEntity> buildingSupplierEntities = projectCategoryRepository.getSupplierBuildingForTime(projectId, timeStamp, Long.parseLong(autoId));
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        List<BuildDutyDTO> buildDutyDTOs = new ArrayList<BuildDutyDTO>();
        if (buildingSupplierEntities != null && buildingSupplierEntities.size() > 0) {
            BuildDutyDTO buildDutyDTO;
            for (BuildingSupplierEntity buildingSupplierEntity : buildingSupplierEntities) {
                buildDutyDTO = new BuildDutyDTO();
                buildDutyDTO.setId(String.valueOf(buildingSupplierEntity.getAutoId()));
                buildDutyDTO.setBuildId(buildingSupplierEntity.getBuildId());
                buildDutyDTO.setDutyId(buildingSupplierEntity.getAgencyId());
                buildDutyDTO.setStatus(buildingSupplierEntity.getStatus());
                buildDutyDTOs.add(buildDutyDTO);
            }
            baseDataDTO.setId(String.valueOf(buildingSupplierEntities.get(buildingSupplierEntities.size() - 1).getAutoId()));
            baseDataDTO.setTimeStamp(DateUtils.format(buildingSupplierEntities.get(buildingSupplierEntities.size() - 1).getModifyTime()));
        }
        baseDataDTO.setList(buildDutyDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    @Override
    public ApiResult getProjectTargetForTime(String autoId, String timeStamp) {
        List<ProjectTargetEntity> projectTargetEntities = projectCategoryRepository.getProjectTargetForTime(Long.parseLong(autoId), timeStamp);
        List<TargetDataDTO> targetDataDTOs = new ArrayList<TargetDataDTO>();
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        if (projectTargetEntities != null && projectTargetEntities.size() > 0) {
            TargetDataDTO targetDataDTO;
            for (ProjectTargetEntity projectTargetEntity : projectTargetEntities) {
                targetDataDTO = new TargetDataDTO();
                targetDataDTO.setTargetId(projectTargetEntity.getId());
                targetDataDTO.setCategoryId(projectTargetEntity.getCategoryId());
                targetDataDTO.setTargetName(projectTargetEntity.getName());
                targetDataDTO.setDescription(projectTargetEntity.getDescription());
                targetDataDTO.setStatus(projectTargetEntity.getState());
                targetDataDTO.setHavePhoto(projectTargetEntity.getHavePhoto());
                targetDataDTOs.add(targetDataDTO);
            }
            baseDataDTO.setId(String.valueOf(projectTargetEntities.get(projectTargetEntities.size() - 1).getAutoNum()));
            baseDataDTO.setTimeStamp(DateUtils.format(projectTargetEntities.get(projectTargetEntities.size() - 1).getModifyOn()));
        }
        baseDataDTO.setList(targetDataDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    @Override
    public List<SupplierCategoryDTO> getSupplierCategory(String dutyId, String domain) {
        List<SupplierCategoryEntity> supplierCategoryEntities = projectCategoryRepository.getSupplierCategorys(dutyId, domain);
        List<SupplierCategoryDTO> supplierCategoryDTOs = new ArrayList<SupplierCategoryDTO>();
        ProjectCategoryEntity projectCategoryEntity;
        if (supplierCategoryEntities != null) {
            SupplierCategoryDTO supplierCategoryDTO;
            for (SupplierCategoryEntity supplierCategoryEntity : supplierCategoryEntities) {
                supplierCategoryDTO = new SupplierCategoryDTO();
                projectCategoryEntity = projectCategoryRepository.getCategoryDetail(supplierCategoryEntity.getCategoryId());
                supplierCategoryDTO.setCategoryId(supplierCategoryEntity.getCategoryId());
                supplierCategoryDTO.setCategoryName(projectCategoryEntity.getCategoryName());
                supplierCategoryDTO.setDomain(projectCategoryEntity.getDomain());
                if (!StringUtil.isEmpty(supplierCategoryEntity.getDefManageId())) {
                    supplierCategoryDTO.setDefManageId(supplierCategoryEntity.getDefManageId());
                    supplierCategoryDTO.setDefManageName(userPropertyStaffRepository.get(supplierCategoryEntity.getDefManageId()).getStaffName());
                }
                if (!StringUtil.isEmpty(supplierCategoryEntity.getDefOwnerId())) {
                    supplierCategoryDTO.setDefOwnerId(supplierCategoryEntity.getDefOwnerId());
                    supplierCategoryDTO.setDefOwnerName(userPropertyStaffRepository.get(supplierCategoryEntity.getDefOwnerId()).getStaffName());
                }
                if (!StringUtil.isEmpty(supplierCategoryEntity.getDefSupervisorId())) {
                    supplierCategoryDTO.setDefSupervisorId(supplierCategoryEntity.getDefSupervisorId());
                    supplierCategoryDTO.setDefSupervisorName(userPropertyStaffRepository.get(supplierCategoryEntity.getDefSupervisorId()).getStaffName());
                }
                supplierCategoryDTOs.add(supplierCategoryDTO);
            }
        }
        return supplierCategoryDTOs;
    }

    @Override
    public void updateSupplierCategory(SupplierCategoryDTO supplierCategoryDTO) {
        if (!StringUtil.isEmpty(supplierCategoryDTO.getCategoryId())) {
            SupplierCategoryEntity supplierCategoryEntity;
            supplierCategoryEntity = new SupplierCategoryEntity();
            supplierCategoryEntity.setCategoryId(supplierCategoryDTO.getCategoryId());
            supplierCategoryEntity.setSupplierId(supplierCategoryDTO.getSupplierId());
            supplierCategoryEntity.setDefManageId(supplierCategoryDTO.getDefManageId());
            supplierCategoryEntity.setDefOwnerId(supplierCategoryDTO.getDefOwnerId());
            supplierCategoryEntity.setDefSupervisorId(supplierCategoryDTO.getDefSupervisorId());
            projectCategoryRepository.updateSupplierCategory(supplierCategoryEntity);
        }
    }

    public void dumpSave(SupplierCategoryEntity supplierCategoryEntity, String[] ids) {
        List<String> compairDTO1 = new ArrayList<String>();
        List<String> compairDTO2 = new ArrayList<String>();
        List<String> compairDTO3 = new ArrayList<String>();
        Iterator<String> it1;
        Iterator<String> it2;
        List<String> categoryIds = projectCategoryRepository.getCategoryIds(supplierCategoryEntity);//查出数据库中已存在的数据
        if (categoryIds != null) {
            for (String categoryId : categoryIds) {
                compairDTO1.add(categoryId);   //将数据库中的数据存放于compairDTO1
            }
        }
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                compairDTO2.add(ids[i]);      //将页面传来的数据存放于compairDTO2
            }
        }
        compairDTO3.addAll(compairDTO1);
        compairDTO1.removeAll(compairDTO2);//比较后 为待删除的数据
        it1 = compairDTO1.iterator();
        while (it1.hasNext()) {
            supplierCategoryEntity.setCategoryId(it1.next());
            projectCategoryRepository.deleteSupplierCategory(supplierCategoryEntity);//删除权限关联数据
        }
        compairDTO2.removeAll(compairDTO3);//比较后 待新增的数据
        it2 = compairDTO2.iterator();
        while (it2.hasNext()) {
            supplierCategoryEntity.setCategoryId(it2.next());
            projectCategoryRepository.addSupplierCategory(supplierCategoryEntity);//保存最新关系
        }
        compairDTO1.clear();
        compairDTO2.clear();
        compairDTO3.clear();
    }

    @Override
    public void addSupplierCategory(CategoryReceiveDTO categoryReceiveDTO) {
        SupplierCategoryEntity supplierCategoryEntity = new SupplierCategoryEntity();
        supplierCategoryEntity.setSupplierId(categoryReceiveDTO.getEmployId());
        supplierCategoryEntity.setDomain(categoryReceiveDTO.getDomain());
        String[] ids;
        String[] ckIds;
        if (!StringUtil.isEmpty(categoryReceiveDTO.getCategoryId())) {
            ids = categoryReceiveDTO.getCategoryId().split(",");
        } else {
            ids = null;
        }
        supplierCategoryEntity.setCkState("1");
        dumpSave(supplierCategoryEntity, ids);
        if (!StringUtil.isEmpty(categoryReceiveDTO.getCkState())) {
            ckIds = categoryReceiveDTO.getCkState().split(",");
        } else {
            ckIds = null;
        }
        supplierCategoryEntity.setCkState("0");
        dumpSave(supplierCategoryEntity, ckIds);
    }

    @Override
    public List<ProjectEmployDTO> getEmploys() {
        List<AgencyEntity> agencyEntities = projectCategoryRepository.getEmploys();
        List<ProjectEmployDTO> projectEmployDTOs = new ArrayList<ProjectEmployDTO>();
        if (agencyEntities != null) {
            ProjectEmployDTO projectEmployDTO;
            for (AgencyEntity agencyEntity : agencyEntities) {
                projectEmployDTO = new ProjectEmployDTO();
                projectEmployDTO.setsId(agencyEntity.getAgencyId());
                projectEmployDTO.setsName(agencyEntity.getAgencyName());
                projectEmployDTOs.add(projectEmployDTO);
            }
        }
        return projectEmployDTOs;
    }

    @Override
    public List<ProjectEmployDTO> getSurveyors() {
        List<AgencyEntity> agencyEntities = projectCategoryRepository.getSurveyors();
        List<ProjectEmployDTO> projectEmployDTOs = new ArrayList<ProjectEmployDTO>();
        if (agencyEntities != null) {
            ProjectEmployDTO projectEmployDTO;
            for (AgencyEntity agencyEntity : agencyEntities) {
                projectEmployDTO = new ProjectEmployDTO();
                projectEmployDTO.setsId(agencyEntity.getAgencyId());
                projectEmployDTO.setsName(agencyEntity.getAgencyName());
                projectEmployDTOs.add(projectEmployDTO);
            }
        }
        return projectEmployDTOs;
    }

    @Override
    public List<CategoryTreeDTO> getRootCategorys(String dutyId, String domain, String parentId) {
        List<CategoryTreeDTO> categoryTreeDTOs = new ArrayList<CategoryTreeDTO>();
        List<ProjectCategoryEntity> projectCategoryEntities = projectCategoryRepository.getCategoryByDutyId(dutyId, domain, parentId);
        if (projectCategoryEntities != null) {
            CategoryTreeDTO categoryTreeDTO;
            for (ProjectCategoryEntity projectCategoryEntity : projectCategoryEntities) {
                categoryTreeDTO = new CategoryTreeDTO();
                categoryTreeDTO.setId(projectCategoryEntity.getCategoryId());
                categoryTreeDTO.setName(projectCategoryEntity.getCategoryName());
                categoryTreeDTO.setpId(projectCategoryEntity.getParentId());
                if (projectCategoryRepository.checkIsParent(projectCategoryEntity.getCategoryId())) {
                    categoryTreeDTO.setIsParent("true");
                } else {
                    categoryTreeDTO.setIsParent("false");
                }
                categoryTreeDTOs.add(categoryTreeDTO);
            }
        }
        return categoryTreeDTOs;
    }

    @Override
    public List<CategoryTreeDTO> getCategoryByPId(String categoryId) {
        List<CategoryTreeDTO> categoryTreeDTOs = new ArrayList<CategoryTreeDTO>();
        List<ProjectCategoryEntity> projectCategoryEntities = projectCategoryRepository.getTreeCategoryList(categoryId);
        if (projectCategoryEntities != null) {
            CategoryTreeDTO categoryTreeDTO;
            for (ProjectCategoryEntity projectCategoryEntity : projectCategoryEntities) {
                categoryTreeDTO = new CategoryTreeDTO();
                categoryTreeDTO.setId(projectCategoryEntity.getCategoryId());
                categoryTreeDTO.setName(projectCategoryEntity.getCategoryName());
                categoryTreeDTO.setpId(projectCategoryEntity.getParentId());
                if (projectCategoryRepository.checkIsParent(projectCategoryEntity.getCategoryId())) {
                    categoryTreeDTO.setIsParent("true");
                } else {
                    categoryTreeDTO.setIsParent("false");
                }
                categoryTreeDTOs.add(categoryTreeDTO);
            }
        }
        return categoryTreeDTOs;
    }

    @Override
    public List<CategoryTreeDTO> getCategoryAllByDutyId(String domain, String dutyId) {
        List<CategoryTreeDTO> categoryTreeDTOs = new ArrayList<CategoryTreeDTO>();
        List<ProjectCategoryEntity> projectCategoryEntities = projectCategoryRepository.getCategoryListByDomain(domain);
        List<ProjectCategoryEntity> projectCategoryEntities1 = projectCategoryRepository.getAllCategoryByDutyId(dutyId, domain);
        if (projectCategoryEntities != null) {
            CategoryTreeDTO categoryTreeDTO;
            for (ProjectCategoryEntity projectCategoryEntity : projectCategoryEntities) {
                categoryTreeDTO = new CategoryTreeDTO();
                categoryTreeDTO.setId(projectCategoryEntity.getCategoryId());
                categoryTreeDTO.setName(projectCategoryEntity.getCategoryName());
                categoryTreeDTO.setpId(projectCategoryEntity.getParentId());
                categoryTreeDTO.setChecked("false");
                for (ProjectCategoryEntity projectCategoryEntity1 : projectCategoryEntities1) {
                    if (projectCategoryEntity.getCategoryId().equals(projectCategoryEntity1.getCategoryId())) {
                        categoryTreeDTO.setChecked("true");
                    }
                }
                categoryTreeDTOs.add(categoryTreeDTO);
            }
        }
        return categoryTreeDTOs;
    }

    @Override
    public List<CategoryTreeDTO> getCategoryTree(String domain, String categoryId) {
        List<CategoryTreeDTO> categoryTreeDTOs = new ArrayList<CategoryTreeDTO>();
        List<ProjectCategoryEntity> projectCategoryEntities = projectCategoryRepository.getCategoryTree(domain, categoryId);
        if (projectCategoryEntities != null) {
            CategoryTreeDTO categoryTreeDTO;
            for (ProjectCategoryEntity projectCategoryEntity : projectCategoryEntities) {
                categoryTreeDTO = new CategoryTreeDTO();
                categoryTreeDTO.setId(projectCategoryEntity.getCategoryId());
                categoryTreeDTO.setName(projectCategoryEntity.getCategoryName());
                categoryTreeDTO.setpId(projectCategoryEntity.getParentId());
                if (projectCategoryRepository.checkIsParent(projectCategoryEntity.getCategoryId())) {
                    categoryTreeDTO.setIsParent("true");
                } else {
                    categoryTreeDTO.setIsParent("false");
                }
                categoryTreeDTOs.add(categoryTreeDTO);
            }
        }
        return categoryTreeDTOs;
    }

    @Override
    public ApiResult getTargetList(String categoryId) {
        List<ProjectTargetEntity> projectTargetEntities = projectCategoryRepository.getTargetList(categoryId);
        List<TargetDTO> targetDTOs = new ArrayList<TargetDTO>();
        if (projectTargetEntities != null) {
            TargetDTO targetDTO;
            for (ProjectTargetEntity projectTargetEntity : projectTargetEntities) {
                targetDTO = new TargetDTO();
                targetDTO.setId(projectTargetEntity.getId());
                targetDTO.setCategoryId(projectTargetEntity.getCategoryId());
                targetDTO.setName(projectTargetEntity.getName());
                targetDTO.setDescription(projectTargetEntity.getDescription());
                targetDTOs.add(targetDTO);
            }
        }
        return new SuccessApiResult(targetDTOs);
    }

    @Override
    public ApiResult getCategory(String categoryId) {
        List<TargetDTO> targetDTOs = new ArrayList<TargetDTO>();
        ProjectCategoryEntity projectCategoryEntity = projectCategoryRepository.getCategoryDetail(categoryId);
        TargetDTO targetDTO = new TargetDTO();
        targetDTO.setCategoryId(projectCategoryEntity.getCategoryId());
        targetDTO.setName(projectCategoryEntity.getTimeSpace());
        targetDTO.setDescription(projectCategoryEntity.getFreeField());
        targetDTOs.add(targetDTO);
        return new SuccessApiResult(targetDTOs);
    }

    @Override
    public String exportModel(String domain, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();

        //创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();

        //在workBook中添加一个sheet，对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("检查项列表");
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
        String[] titles;
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        XSSFRow bodyRow = sheet.createRow(1);
        String fileName;
        switch (domain) {
            case "1":
                fileName = new String("日常巡检检查项模板".getBytes(), "ISO8859-1");
                titles = new String[]{"分部工程(1级分类)", "子分部工程(2级分类)", "分项名称(3级分类)"};
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                sheet.setColumnWidth(0, 9000);
                sheet.setColumnWidth(1, 9000);
                sheet.setColumnWidth(2, 9000);

                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("土建地下工程");

                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("土方");

                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("土方开挖");

                break;
            case "2":
                fileName = new String("检查验收检查项模板".getBytes(), "ISO8859-1");
                titles = new String[]{"分部工程(1级分类)", "子分部工程(2级分类)", "分项名称(3级分类)", "工序名称(4级分类)", "检查主要内容", "指标详解", "是否拍摄照片"};
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                sheet.setColumnWidth(0, 9000);
                sheet.setColumnWidth(1, 9000);
                sheet.setColumnWidth(2, 9000);
                sheet.setColumnWidth(3, 8000);
                sheet.setColumnWidth(4, 8000);
                sheet.setColumnWidth(5, 14000);
                sheet.setColumnWidth(6, 9000);

                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("地基与基础");

                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("无支护土方");

                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("土方开挖");

                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("人工开挖");

                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("开挖边线");

                cell = bodyRow.createCell(5);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("开挖边线位置；开挖长度、宽度");

                cell = bodyRow.createCell(6);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("是");
                break;
            case "6":
                fileName = new String("关键工序检查项模板".getBytes(), "ISO8859-1");
                titles = new String[]{"分部工程(1级分类)", "子分部工程(2级分类)", "分项名称(3级分类)", "工序名称(4级分类)", "检查主要内容", "指标详解", "是否拍摄照片"};
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                sheet.setColumnWidth(0, 9000);
                sheet.setColumnWidth(1, 9000);
                sheet.setColumnWidth(2, 9000);
                sheet.setColumnWidth(3, 8000);
                sheet.setColumnWidth(4, 8000);
                sheet.setColumnWidth(5, 14000);
                sheet.setColumnWidth(6, 9000);

                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("地基与基础");

                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("无支护土方");

                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("土方开挖");

                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("人工开挖");

                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("开挖边线");

                cell = bodyRow.createCell(5);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("开挖边线位置；开挖长度、宽度");

                cell = bodyRow.createCell(6);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("是");
                break;
            case "3":
                fileName = new String("样板点评检查项模板".getBytes(), "ISO8859-1");
                titles = new String[]{"点评等级", "专业分类", "点评项目", "检查主要内容", "指标详情","是否拍照"};
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                sheet.setColumnWidth(0, 9000);
                sheet.setColumnWidth(1, 9000);
                sheet.setColumnWidth(2, 9000);
                sheet.setColumnWidth(3, 12000);
                sheet.setColumnWidth(4, 12000);
                sheet.setColumnWidth(5, 9000);

                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("一级点评");

                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("楼地面");

                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("客厅");

                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("地面光滑");

                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("地面光滑，平整（拉毛地坪，刮痕统一），混 凝土强度达到设计要求，无空鼓。分隔缝按要求设置");

                cell = bodyRow.createCell(5);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("是");

                break;
            case "4":
                fileName = new String("材料验收检查项模板".getBytes(), "ISO8859-1");
                titles = new String[]{"专业(1级分类)", "材料名称(2级分类)", "材料范围说明", "材料要求(指标)", "指标详解"};
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                sheet.setColumnWidth(0, 9000);
                sheet.setColumnWidth(1, 9000);
                sheet.setColumnWidth(2, 9000);
                sheet.setColumnWidth(3, 8000);
                sheet.setColumnWidth(4, 8000);

                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("土建");

                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("钢筋原材");

                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("主体、基坑支护、二次结构等");

                cell = bodyRow.createCell(3);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("厂家及供应商资质");

                cell = bodyRow.createCell(4);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("钢绞线应有出厂合格证");
                break;
            case "5":
                fileName = new String("旁站检查项模板".getBytes(), "ISO8859-1");
                titles = new String[]{"旁站类型(1级分类)", "旁站要点(2级分类)", "时间要求"};
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                sheet.setColumnWidth(0, 9000);
                sheet.setColumnWidth(1, 9000);
                sheet.setColumnWidth(2, 9000);

                cell = bodyRow.createCell(0);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("土方回填");

                cell = bodyRow.createCell(1);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("7.随机见证取样做砼试块;8.观察砼和易性，抽捡砼塌落度，严禁现场加水;");

                cell = bodyRow.createCell(2);
                cell.setCellStyle(bodyStyle);//表格黑色边框
                cell.setCellValue("60分钟左右");
                break;
            default:
                fileName = new String("检查项模板".getBytes(), "ISO8859-1");
                break;
        }
        try {
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie
                fileName = java.net.URLEncoder.encode(fileName, "UTF8");
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
        return "ok";
    }

    @Override
    public String importCategoryExcel(InputStream fis, String domain, UserInformationEntity user) {
        ProjectCategoryEntity projectCategoryEntity = new ProjectCategoryEntity();
        ProjectTargetEntity projectTargetEntity = new ProjectTargetEntity();
        ProjectCategoryEntity projectCategoryEntity1;
        try {
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            String content;
            if ("5".equals(domain)) {//旁站
                for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                    sheet = hwb.getSheetAt(i);
                    //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                    for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                        row = sheet.getRow(j);
                        if (!StringUtil.isEmpty(row.getCell(0).getRichStringCellValue().getString()) && !StringUtil.isEmpty(row.getCell(1).getRichStringCellValue().getString()) && !StringUtil.isEmpty(row.getCell(2).getRichStringCellValue().getString())) {
                            content = row.getCell(0).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(1, content, "", domain);//根据名称查询数据库中是否有这条数据
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                                projectCategoryEntity1.setFreeField(row.getCell(1).getRichStringCellValue().getString().replace(";", "<br/>"));
                                projectCategoryEntity1.setTimeSpace(row.getCell(2).getRichStringCellValue().getString());
                                projectCategoryRepository.updateProjectCategory(projectCategoryEntity1);  //更新
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(content);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(1);
                                projectCategoryEntity.setParentId(null);
                                projectCategoryEntity.setFreeField(row.getCell(1).getRichStringCellValue().getString());
                                projectCategoryEntity.setTimeSpace(row.getCell(2).getRichStringCellValue().getString());
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第一列数据进行处理  保存一级分类
                            }
                        } else {
                            return "错误！数据不规范！ 第" + j + 1 + "行第数据有空";
                        }
                    }
                }
            } else if ("4".equals(domain)) {//材料验收
                for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                    sheet = hwb.getSheetAt(i);
                    //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                    for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                        row = sheet.getRow(j);
                        if (!StringUtil.isEmpty(row.getCell(0).getRichStringCellValue().getString()) && !StringUtil.isEmpty(row.getCell(1).getRichStringCellValue().getString()) && !StringUtil.isEmpty(row.getCell(2).getRichStringCellValue().getString())) {
                            content = row.getCell(0).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(1, content, "", domain);//根据名称查询数据库中是否有这条数据
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(content);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(1);
                                projectCategoryEntity.setParentId(null);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第一列数据进行处理  保存一级分类
                            }
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(2, row.getCell(1).getRichStringCellValue().getString().trim(), projectCategoryEntity.getCategoryId(), domain);//根据名称查询数据库中是否有这条数据
                            projectCategoryEntity.setParentId(projectCategoryEntity.getCategoryId());
                            if (projectCategoryEntity1 != null) {
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(row.getCell(1).getRichStringCellValue().getString().trim());
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(2);
                                projectCategoryEntity.setFreeField(row.getCell(2).getRichStringCellValue().getString().trim());//材料范围
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第二列数据进行处理  保存二级分类
                            }
                        } else {
                            return "错误！数据不规范！ 第" + j + 1 + "行第数据有空";
                        }
                        if (!StringUtil.isEmpty(row.getCell(4).getRichStringCellValue().getString()) && StringUtil.isEmpty(row.getCell(3).getRichStringCellValue().getString())) {
                            return "错误！数据不规范！ 第" + j + 1 + "行第3列数据有空";
                        } else if (!StringUtil.isEmpty(row.getCell(3).getRichStringCellValue().getString())) {
                            String targetName = row.getCell(3).getRichStringCellValue().getString().trim();
                            ProjectTargetEntity projectTargetEntity1 = projectCategoryRepository.getTargetByName(targetName, projectCategoryEntity.getCategoryId());
                            if (projectTargetEntity1 != null) {
                                projectTargetEntity.setId(projectTargetEntity1.getId());
                                projectTargetEntity.setAutoNum(projectTargetEntity1.getAutoNum());
                            } else {
                                projectTargetEntity.setAutoNum(0);
                                projectTargetEntity.setId(IdGen.uuid());
                            }
                            projectTargetEntity.setCategoryId(projectCategoryEntity.getCategoryId());
                            projectTargetEntity.setState("1");
                            if("其他".equals(targetName)){
                                projectTargetEntity.setSort(1);
                            }else if(targetName.length()>2){
                                if("其他".equals(targetName.substring(0,2))){
                                    projectTargetEntity.setSort(1);
                                }else{
                                    projectTargetEntity.setSort(0);
                                }
                            }else{
                                projectTargetEntity.setSort(0);
                            }
                            projectTargetEntity.setName(targetName);
                            projectTargetEntity.setModifyOn(new Date());
                            projectTargetEntity.setModifyBy(user.getStaffName());
                            projectTargetEntity.setCreateOn(new Date());
                            projectTargetEntity.setCreateBy(user.getStaffName());
                            projectTargetEntity.setDescription(row.getCell(4).getRichStringCellValue().getString().replace(";", "<br/>"));
                            projectCategoryRepository.addOrUpdateTarget(projectTargetEntity);   //保存指标
                        }
                    }
                }
            } else if ("1".equals(domain)) {//日常巡检
                for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                    sheet = hwb.getSheetAt(i);
                    //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                    for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                        row = sheet.getRow(j);
                        //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                        if (!StringUtil.isEmpty(row.getCell(0).getRichStringCellValue().getString())) {
                            String categoryName = row.getCell(0).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(1, categoryName, "", domain);//根据名称查询数据库中是否有这条数据
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(categoryName);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(1);
                                projectCategoryEntity.setParentId(null);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第一列数据进行处理  保存一级分类
                            }
                        } else {
                            return "错误！数据不规范！ 第" + j + 1 + "行第1列数据为空";
                        }
                        if (!StringUtil.isEmpty(row.getCell(1).getRichStringCellValue().getString())) {
                            String categoryName = row.getCell(1).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(2, categoryName, projectCategoryEntity.getCategoryId(), domain);//根据名称查询数据库中是否有这条数据
                            projectCategoryEntity.setParentId(projectCategoryEntity.getCategoryId());
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(categoryName);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(2);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第二列数据进行处理  保存二级分类
                            }
                        } else {
                            return "错误！数据不规范！ 第" + j + 1 + "行第2列数据为空";
                        }
                        if (StringUtil.isEmpty(row.getCell(2).getRichStringCellValue().getString())) {
                            return "错误！数据不规范！ 第" + j + 1 + "行第3列数据为空";
                        } else {
                            String categoryName = row.getCell(2).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(3, categoryName, projectCategoryEntity.getCategoryId(), domain);//根据名称查询数据库中是否有这条数据
                            projectCategoryEntity.setParentId(projectCategoryEntity.getCategoryId());
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(categoryName);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(3);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第三列数据进行处理  保存三级分类
                            }
                        }
                    }
                }
            } else if ("3".equals(domain)) {//样板点评
                for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                    sheet = hwb.getSheetAt(i);
                    //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                    for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                        row = sheet.getRow(j);
                        //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                        if (row.getCell(0) != null) {
                            String categoryName = row.getCell(0).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(1, categoryName, "", domain);//根据名称查询数据库中是否有这条数据
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(categoryName);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(1);
                                projectCategoryEntity.setParentId(null);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第一列数据进行处理  保存一级分类
                            }
                        } else {
                            return "错误！数据不规范！ 第" + j + "行第1列数据为空";
                        }
                        if (row.getCell(1) != null) {
                            String categoryName = row.getCell(1).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(2, categoryName, projectCategoryEntity.getCategoryId(), domain);//根据名称查询数据库中是否有这条数据
                            projectCategoryEntity.setParentId(projectCategoryEntity.getCategoryId());
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(categoryName);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(2);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第二列数据进行处理  保存二级分类
                            }
                        } else {
                            return "错误！数据不规范！ 第" + j + "行第2列数据为空";
                        }
                        if (row.getCell(2) == null) {
                            return "错误！数据不规范！ 第" + j + "行第3列数据为空";
                        } else {
                            String categoryName = row.getCell(2).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(3, categoryName, projectCategoryEntity.getCategoryId(), domain);//根据名称查询数据库中是否有这条数据
                            projectCategoryEntity.setParentId(projectCategoryEntity.getCategoryId());
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(categoryName);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(3);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第三列数据进行处理  保存三级分类
                            }
                        }
                        if (row.getCell(3) == null) {
                            return "错误！数据不规范！ 第" + j + "行第4列数据为空";
                        } else {
                            String targetName = row.getCell(3).getRichStringCellValue().getString().trim();
                            ProjectTargetEntity projectTargetEntity1 = projectCategoryRepository.getTargetByName(targetName, projectCategoryEntity.getCategoryId());
                            if (projectTargetEntity1 != null) {
                                projectTargetEntity.setId(projectTargetEntity1.getId());
                                projectTargetEntity.setAutoNum(projectTargetEntity1.getAutoNum());
                            } else {
                                projectTargetEntity.setAutoNum(0);
                                projectTargetEntity.setId(IdGen.uuid());
                            }
                            projectTargetEntity.setCategoryId(projectCategoryEntity.getCategoryId());
                            projectTargetEntity.setState("1");
                            if("其他".equals(targetName)){
                                projectTargetEntity.setSort(1);
                            }else if(targetName.length()>2){
                                if("其他".equals(targetName.substring(0,2))){
                                    projectTargetEntity.setSort(1);
                                }else{
                                    projectTargetEntity.setSort(0);
                                }
                            }else{
                                projectTargetEntity.setSort(0);
                            }
                            projectTargetEntity.setName(targetName);
                            projectTargetEntity.setModifyOn(new Date());
                            projectTargetEntity.setModifyBy(user.getStaffName());
                            projectTargetEntity.setCreateOn(new Date());
                            projectTargetEntity.setCreateBy(user.getStaffName());
                            if (row.getCell(4) == null) {
//                                return "错误！数据不规范！ 第" + j + "行第5列数据为空";
                                projectTargetEntity.setDescription("暂无");
                            }else {
                                projectTargetEntity.setDescription(row.getCell(4).getRichStringCellValue().getString().replace(";", "<br/>"));
                            }
                            if (row.getCell(5) == null) {
//                                return "错误！数据不规范！ 第" + j + "行第6列数据为空";
                                projectTargetEntity.setHavePhoto("是");
                            }else {
                                projectTargetEntity.setHavePhoto(row.getCell(5).getRichStringCellValue().getString().trim());
                            }
                            projectCategoryRepository.addOrUpdateTarget(projectTargetEntity);   //保存指标
                        }
                    }
                }
            } else if (domain.equals("2") || domain.equals("6")) {//检查验收,关键工序
                domain = "2";
                for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                    sheet = hwb.getSheetAt(i);
                    //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                    for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                        row = sheet.getRow(j);
                        //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                        if (!StringUtil.isEmpty(row.getCell(0).getRichStringCellValue().getString())) {
                            String categoryName = row.getCell(0).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(1, categoryName, "", domain);//根据名称查询数据库中是否有这条数据
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(categoryName);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(1);
                                projectCategoryEntity.setParentId(null);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第一列数据进行处理  保存一级分类
                            }
                        } else {
                            return "错误！数据不规范！ 第" + j + 1 + "行第1列数据为空";
                        }
                        if (!StringUtil.isEmpty(row.getCell(1).getRichStringCellValue().getString())) {
                            String categoryName = row.getCell(1).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(2, categoryName, projectCategoryEntity.getCategoryId(), domain);//根据名称查询数据库中是否有这条数据
                            projectCategoryEntity.setParentId(projectCategoryEntity.getCategoryId());
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(categoryName);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(2);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第二列数据进行处理  保存二级分类
                            }
                        } else {
                            return "错误！数据不规范！ 第" + j + 1 + "行第2列数据为空";
                        }
                        if (!StringUtil.isEmpty(row.getCell(3).getRichStringCellValue().getString()) && StringUtil.isEmpty(row.getCell(2).getRichStringCellValue().getString())) {
                            return "错误！数据不规范！ 第" + j + 1 + "行第3列数据为空";
                        } else {
                            String categoryName = row.getCell(2).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(3, categoryName, projectCategoryEntity.getCategoryId(), domain);//根据名称查询数据库中是否有这条数据
                            projectCategoryEntity.setParentId(projectCategoryEntity.getCategoryId());
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(categoryName);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(3);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第三列数据进行处理  保存三级分类
                            }
                        }
                        if (!StringUtil.isEmpty(row.getCell(3).getRichStringCellValue().getString())) {
                            String categoryName = row.getCell(3).getRichStringCellValue().getString().trim();
                            projectCategoryEntity1 = projectCategoryRepository.getProjectCategoryByName(4, categoryName, projectCategoryEntity.getCategoryId(), domain);//根据名称查询数据库中是否有这条数据
                            projectCategoryEntity.setParentId(projectCategoryEntity.getCategoryId());
                            if (projectCategoryEntity1 != null) {       //如果有则使用已有ID
                                projectCategoryEntity.setCategoryId(projectCategoryEntity1.getCategoryId());
                            } else {   //没有则创建ID
                                projectCategoryEntity.setCategoryId(IdGen.uuid());
                                projectCategoryEntity.setDomain(domain);
                                projectCategoryEntity.setCategoryName(categoryName);
                                projectCategoryEntity.setCategoryState("1");
                                projectCategoryEntity.setCreateOn(new Date());
                                projectCategoryEntity.setModifyOn(new Date());
                                projectCategoryEntity.setLevel(4);
                                projectCategoryRepository.addProjectCategory(projectCategoryEntity);  //对第四列数据进行处理  保存四级分类
                            }
                        }
                        if (!StringUtil.isEmpty(row.getCell(5).getRichStringCellValue().getString()) && StringUtil.isEmpty(row.getCell(4).getRichStringCellValue().getString())) {
                            return "错误！数据不规范！ 第" + j + 1 + "行第5列数据为空";
                        } else if (!StringUtil.isEmpty(row.getCell(4).getRichStringCellValue().getString()) && StringUtil.isEmpty(row.getCell(6).getRichStringCellValue().getString())) {
                            return "错误！数据不规范！ 第" + j + 1 + "行第7列数据为空";
                        } else if (!StringUtil.isEmpty(row.getCell(5).getRichStringCellValue().getString())) {
                            String targetName = row.getCell(4).getRichStringCellValue().getString().trim();
                            ProjectTargetEntity projectTargetEntity1 = projectCategoryRepository.getTargetByName(targetName, projectCategoryEntity.getCategoryId());
                            if (projectTargetEntity1 != null) {
                                projectTargetEntity.setId(projectTargetEntity1.getId());
                                projectTargetEntity.setAutoNum(projectTargetEntity1.getAutoNum());
                            } else {
                                projectTargetEntity.setAutoNum(0);
                                projectTargetEntity.setId(IdGen.uuid());
                            }
                            projectTargetEntity.setCategoryId(projectCategoryEntity.getCategoryId());
                            projectTargetEntity.setState("1");
                            if("其他".equals(targetName)){
                                projectTargetEntity.setSort(1);
                            }else if(targetName.length()>2){
                                if("其他".equals(targetName.substring(0,2))){
                                    projectTargetEntity.setSort(1);
                                }else{
                                    projectTargetEntity.setSort(0);
                                }
                            }else{
                                projectTargetEntity.setSort(0);
                            }
                            projectTargetEntity.setName(targetName);
                            projectTargetEntity.setModifyOn(new Date());
                            projectTargetEntity.setModifyBy(user.getStaffName());
                            projectTargetEntity.setCreateOn(new Date());
                            projectTargetEntity.setCreateBy(user.getStaffName());
                            projectTargetEntity.setDescription(row.getCell(5).getRichStringCellValue().getString().replace(";", "<br/>"));
                            projectTargetEntity.setHavePhoto(row.getCell(6).getRichStringCellValue().getString().trim());
                            projectCategoryRepository.addOrUpdateTarget(projectTargetEntity);   //保存指标
                        }
                    }
                }
            }
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "未知错误！";
        }
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 保存标段和检查项关系
     */
    @Override
    public void addTenderCategory(TenderCategoryReceiveDTO tenderCategoryReceiveDTO) {
        TendersCategoryEntity tendersCategoryEntity = new TendersCategoryEntity();
        tendersCategoryEntity.setTendersId(tenderCategoryReceiveDTO.getTenderId()); //标段id
        tendersCategoryEntity.setDomain(tenderCategoryReceiveDTO.getDomain());      //模块
        String[] ids;
        String[] ckIds;
        if (!StringUtil.isEmpty(tenderCategoryReceiveDTO.getCategoryId())) {
            ids = tenderCategoryReceiveDTO.getCategoryId().split(",");
        } else {
            ids = null;
        }
        tendersCategoryEntity.setCkState("1");  //设置勾选状态为1
        tenderSave(tendersCategoryEntity, ids);
        if (!StringUtil.isEmpty(tenderCategoryReceiveDTO.getCkState())) {
            ckIds = tenderCategoryReceiveDTO.getCkState().split(",");
        } else {
            ckIds = null;
        }
        tendersCategoryEntity.setCkState("0");
        tenderSave(tendersCategoryEntity, ckIds);
    }

    //保存标段和检查项关系
    public void tenderSave(TendersCategoryEntity tendersCategoryEntity, String[] ids) {
        List<String> compairDTO1 = new ArrayList<String>();
        List<String> compairDTO2 = new ArrayList<String>();
        List<String> compairDTO3 = new ArrayList<String>();
        Iterator<String> it1;
        Iterator<String> it2;
        List<String> categoryIds = projectCategoryRepository.getCategoryByIds(tendersCategoryEntity);//查出数据库中已存在的数据
        if (categoryIds != null) {
            for (String categoryId : categoryIds) {
                compairDTO1.add(categoryId);   //将数据库中的数据存放于compairDTO1
            }
        }
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                compairDTO2.add(ids[i]);      //将页面传来的数据存放于compairDTO2
            }
        }
        compairDTO3.addAll(compairDTO1);
        compairDTO1.removeAll(compairDTO2);//比较后 为待删除的数据
        it1 = compairDTO1.iterator();
        while (it1.hasNext()) {
            tendersCategoryEntity.setCategoryId(it1.next());
            projectCategoryRepository.deleteTenderCategory(tendersCategoryEntity);//删除权限关联数据
        }
        compairDTO2.removeAll(compairDTO3);//比较后 待新增的数据
        it2 = compairDTO2.iterator();
        while (it2.hasNext()) {
            tendersCategoryEntity.setCategoryId(it2.next());
            projectCategoryRepository.addTenderCategory(tendersCategoryEntity);//保存最新关系
        }
        compairDTO1.clear();
        compairDTO2.clear();
        compairDTO3.clear();
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 根据标段id获取一级检查项
     */
    @Override
    public List<CategoryTreeDTO> getRootCategoryByTenderId(String tenderId, String domain, String parentId) {
        List<CategoryTreeDTO> categoryTreeDTOs = new ArrayList<CategoryTreeDTO>();
        //根据标段id获取与该标段对应的所有检查项
        //根据父级id判断是否存在父级，不存在则获取根级，存在则获取该级下的所有符合要求的检查项
        List<ProjectCategoryEntity> projectCategoryEntities = projectCategoryRepository.getCategoryByTenderId(tenderId, domain, parentId);
        if (projectCategoryEntities != null) {
            CategoryTreeDTO categoryTreeDTO;
            for (ProjectCategoryEntity projectCategoryEntity : projectCategoryEntities) {
                categoryTreeDTO = new CategoryTreeDTO();
                categoryTreeDTO.setId(projectCategoryEntity.getCategoryId());       //当前节点id
                categoryTreeDTO.setName(projectCategoryEntity.getCategoryName());   //当前节点名称
                categoryTreeDTO.setpId(projectCategoryEntity.getParentId());        //当前节点父节点id
                //判断该节点是否是父节点
                if (projectCategoryRepository.checkIsParent(projectCategoryEntity.getCategoryId())) {
                    categoryTreeDTO.setIsParent("true");
                } else {
                    categoryTreeDTO.setIsParent("false");
                }
                categoryTreeDTOs.add(categoryTreeDTO);
            }
        }
        return categoryTreeDTOs;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 根据标段和模块获取所有检查项
     */
    @Override
    public List<CategoryTreeDTO> getCategoryAllByTenderId(String domain, String tenderId) {
        List<CategoryTreeDTO> categoryTreeDTOs = new ArrayList<CategoryTreeDTO>();
        //获取该模块下的所有检查项
        List<ProjectCategoryEntity> projectCategoryEntities = projectCategoryRepository.getCategoryListByDomain(domain);
        //根据标段获取该标段对应的所有检查项
        List<ProjectCategoryEntity> projectCategoryEntityList = projectCategoryRepository.getAllCategoryByTenderId(tenderId, domain);
        if (projectCategoryEntities != null) {
            CategoryTreeDTO categoryTreeDTO;
            for (ProjectCategoryEntity projectCategoryEntity : projectCategoryEntities) {
                categoryTreeDTO = new CategoryTreeDTO();
                categoryTreeDTO.setId(projectCategoryEntity.getCategoryId());       //当前节点id
                categoryTreeDTO.setName(projectCategoryEntity.getCategoryName());   //当前节点名称
                categoryTreeDTO.setpId(projectCategoryEntity.getParentId());        //当前节点的父节点id
                categoryTreeDTO.setChecked("false");                                //当前节点不被选中
                for (ProjectCategoryEntity projectCategory : projectCategoryEntityList) {
                    //如果该节点是该标段下的关联的检查项，则设置节点为选中
                    if (projectCategoryEntity.getCategoryId().equals(projectCategory.getCategoryId())) {
                        categoryTreeDTO.setChecked("true");
                    }
                }
                categoryTreeDTOs.add(categoryTreeDTO);
            }
        }
        return categoryTreeDTOs;
    }

    @Override
    public ApiResult altCategory(String categoryId, String categoryName) {
        ProjectCategoryEntity projectCategoryEntity = projectCategoryRepository.getCategoryDetail(categoryId);
        projectCategoryEntity.setCategoryName(categoryName);
        projectCategoryEntity.setModifyOn(new Date());
        projectCategoryRepository.updateProjectCategory(projectCategoryEntity);
        return new SuccessApiResult();
    }

    @Override
    public ApiResult delCategory(String categoryId) {
        projectCategoryRepository.delCategory(categoryId);
        return new SuccessApiResult();
    }

    @Override
    public String exportCategory(String domain, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        //创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        //在workBook中添加一个sheet，对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("检查项列表");
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

        String domainName = "";
        String[] titles;
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell;
        XSSFRow bodyRow;
        switch (domain) {
            case "1":
                domainName = "日常巡检";
                List<Object[]> objects1 = projectCategoryRepository.exportDaily();
                if (objects1 != null) {
                    titles = new String[]{"分部工程(1级分类)", "子分部工程(2级分类)", "分项名称(3级分类)"};
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }
                    sheet.setColumnWidth(0, 9000);
                    sheet.setColumnWidth(1, 9000);
                    sheet.setColumnWidth(2, 9000);
                    //表格内容部分
                    int rowSize = objects1.size();
                    for (int i = 0; i < rowSize; i++) {
                        bodyRow = sheet.createRow(i + 1);
                        // 此处已经知道每行有多少列 就用固定数值代替 避免系统重复计算该数值
                        for (int j = 0; j < 3; j++) {
                            cell = bodyRow.createCell(j);
                            cell.setCellStyle(bodyStyle);//表格黑色边框
                            cell.setCellValue(objects1.get(i)[j].toString());
                        }
                    }
                }
                break;
            case "2":
                domainName = "检查验收";
                List<Object[]> objects = projectCategoryRepository.exportCategory();
                if (objects != null) {
                    titles = new String[]{"分部工程(1级分类)", "子分部工程(2级分类)", "分项名称(3级分类)", "工序名称(4级分类)", "检查主要内容", "指标详解", "是否拍摄照片"};
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);

                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    sheet.setColumnWidth(0, 9000);
                    sheet.setColumnWidth(1, 9000);
                    sheet.setColumnWidth(2, 9000);
                    sheet.setColumnWidth(3, 8000);
                    sheet.setColumnWidth(4, 8000);
                    sheet.setColumnWidth(5, 14000);
                    sheet.setColumnWidth(6, 9000);
                    int rowSize = objects.size();
                    for (int i = 0; i < rowSize; i++) {
                        bodyRow = sheet.createRow(i + 1);
//                        int colSize = objects.get(i).length; 此处已经知道每行有多少列 就用固定数值代替 避免系统重复计算该数值
                        for (int j = 0; j < 7; j++) {
                            cell = bodyRow.createCell(j);
                            cell.setCellStyle(bodyStyle);//表格黑色边框
                            cell.setCellValue(objects.get(i)[j].toString());
                        }
                    }
                }
                break;
            case "6":
                domainName = "关键工序";
                List<Object[]> object6 = projectCategoryRepository.exportCategory();
                if (object6 != null) {
                    titles = new String[]{"分部工程(1级分类)", "子分部工程(2级分类)", "分项名称(3级分类)", "工序名称(4级分类)", "检查主要内容", "指标详解", "是否拍摄照片"};
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);

                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    sheet.setColumnWidth(0, 9000);
                    sheet.setColumnWidth(1, 9000);
                    sheet.setColumnWidth(2, 9000);
                    sheet.setColumnWidth(3, 8000);
                    sheet.setColumnWidth(4, 8000);
                    sheet.setColumnWidth(5, 14000);
                    sheet.setColumnWidth(6, 9000);
                    int rowSize = object6.size();
                    for (int i = 0; i < rowSize; i++) {
                        bodyRow = sheet.createRow(i + 1);
//                        int colSize = objects.get(i).length; 此处已经知道每行有多少列 就用固定数值代替 避免系统重复计算该数值
                        for (int j = 0; j < 7; j++) {
                            cell = bodyRow.createCell(j);
                            cell.setCellStyle(bodyStyle);//表格黑色边框
                            cell.setCellValue(object6.get(i)[j].toString());
                        }
                    }
                }
                break;
            case "3":
                domainName = "样板点评";
                List<Object[]> object3 = projectCategoryRepository.exportModelReviewsy();
                if (object3 != null) {
                    titles = new String[]{"点评等级", "专业分类", "点评项目(3级分类)", "检查主要内容", "指标详情","是否拍照"};
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);

                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    sheet.setColumnWidth(0, 9000);
                    sheet.setColumnWidth(1, 9000);
                    sheet.setColumnWidth(2, 9000);
                    sheet.setColumnWidth(3, 8000);
                    sheet.setColumnWidth(4, 14000);
                    int rowSize = object3.size();
                    for (int i = 0; i < rowSize; i++) {
                        bodyRow = sheet.createRow(i + 1);
//                        int colSize = objects.get(i).length; 此处已经知道每行有多少列 就用固定数值代替 避免系统重复计算该数值
                        for (int j = 0; j < 6; j++) {
                            cell = bodyRow.createCell(j);
                            cell.setCellStyle(bodyStyle);//表格黑色边框
                            cell.setCellValue(object3.get(i)[j].toString());
                        }
                    }
                }
                break;
            case "4":
                domainName = "材料验收";
                List<Object[]> objects4 = projectCategoryRepository.exportInspected();
                if (objects4 != null) {
                    titles = new String[]{"专业(1级分类)", "材料名称(2级分类)", "材料范围说明", "材料要求(指标)", "指标详解"};
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);

                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    sheet.setColumnWidth(0, 9000);
                    sheet.setColumnWidth(1, 9000);
                    sheet.setColumnWidth(2, 9000);
                    sheet.setColumnWidth(3, 9000);
                    sheet.setColumnWidth(4, 20000);
                    int rowSize = objects4.size();
                    for (int i = 0; i < rowSize; i++) {
                        bodyRow = sheet.createRow(i + 1);
//                        int colSize = objects.get(i).length; 此处已经知道每行有多少列 就用固定数值代替 避免系统重复计算该数值
                        for (int j = 0; j < 5; j++) {
                            cell = bodyRow.createCell(j);
                            cell.setCellStyle(bodyStyle);//表格黑色边框
                            cell.setCellValue(objects4.get(i)[j].toString());
                        }
                    }
                }
                break;
            case "5":
                domainName = "旁站";
                List<Object[]> objects5 = projectCategoryRepository.exportSide();
                if (objects5 != null) {
                    titles = new String[]{"旁站类型(1级分类)", "旁站要点(2级分类)", "时间要求"};
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);

                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    sheet.setColumnWidth(0, 9000);
                    sheet.setColumnWidth(1, 9000);
                    sheet.setColumnWidth(2, 9000);
                    int rowSize = objects5.size();
                    for (int i = 0; i < rowSize; i++) {
                        bodyRow = sheet.createRow(i + 1);
//                        int colSize = objects.get(i).length; 此处已经知道每行有多少列 就用固定数值代替 避免系统重复计算该数值
                        for (int j = 0; j < 3; j++) {
                            cell = bodyRow.createCell(j);
                            cell.setCellStyle(bodyStyle);//表格黑色边框
                            cell.setCellValue(objects5.get(i)[j].toString());
                        }
                    }
                }
                break;
            default:
                break;
        }
        try {
            String fileName = new String(domainName.getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode(domainName, "UTF8");
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
        return "ok";
    }

    @Override
    public String exportMechanismPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        BufferedInputStream buffer = null;
        OutputStream out = null;
        HttpSession session = httpServletRequest.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
//        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "OrgAndStaff.xlsx";
        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "AuthOrgAndStaff.xlsx";

        File file = new File(filePath);
        if (!file.exists()) {
            return "File not found!";
        }
        try {
            buffer = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int len = 0;
            httpServletResponse.reset(); //非常重要
            httpServletResponse.setContentType("application/x-msdownload");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
            out = httpServletResponse.getOutputStream();
            while ((len = buffer.read(buf)) > 0)
                out.write(buf, 0, len);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                out.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return "ok";
    }

    @Override
    public String authExportMechanismPeopleModel(AuthSupplierRoleUserDTO authSupplierRoleUserDTO, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return null;
    }

    @Override
    public String importMechanismPeopleExcel(InputStream fis, String projectId, UserInformationEntity userInformationEntity) {
        AgencyEntity agencyEntity;
        ProjectEmployDTO projectEmployDTO = new ProjectEmployDTO();
        projectEmployDTO.setProjectId(projectId);
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
                    //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                    if (row.getCell(0) != null) {//第一列数据 机构名称
                        String agencyName = row.getCell(0).getRichStringCellValue().getString().trim();
                        agencyEntity = agencyRepository.getAgencyDetailListByName(agencyName, projectEmployDTO.getProjectId());//根据机构名称查询数据库中是否有这条数据
                        if (agencyEntity != null) {//如果有则使用已有ID
                            if (row.getCell(3) != null) {//第四列数据 用户名
                                String userNameR = row.getCell(3).getRichStringCellValue().getString().trim();
                                UserPropertyStaffEntity userPropertyStaffEntity1 = userPropertystaffReposiroty.getByUserName(userNameR);//根据用户名查询是否已被注册
                                if (userPropertyStaffEntity1 != null) {
                                    AgencyEntity agencyEntities = userAgencyRepository.getAgencysByStaffId(userPropertyStaffEntity1.getStaffId(), agencyEntity.getAgencyId());
                                    if (agencyEntities != null) {
                                        continue;
//                                        for (AgencyEntity agencyEntity1 : agencyEntities) {
//                                            if (agencyEntity1.getAgencyId().equals(agencyEntity.getAgencyId())) {
//                                                continue;
//                                            } else {
//                                                return "错误！第" + (j + 1) + "行第4列用户名已被注册！";
//                                            }
//                                        }
                                    } else {
                                        return "错误！第" + (j + 1) + "行第4列用户名已被注册！";
                                    }
                                } else {
                                    UserPropertyStaffEntity userPropertyStaff = new UserPropertyStaffEntity();
                                    String pwd = PasswordEncode.digestPassword("123456");
                                    userPropertyStaff.setStaffId(IdGen.uuid());
                                    userPropertyStaff.setUserName(userNameR);//用户名
                                    userPropertyStaff.setPassword(pwd);//密码
                                    if (row.getCell(4) != null) {
                                        String staffNameR = row.getCell(4).getRichStringCellValue().getString().trim();
                                        userPropertyStaff.setStaffName(staffNameR);//姓名
                                    } else {
                                        return "错误！数据不规范！ 第" + (j + 1) + "行第5列数据为空";
                                    }
                                    userPropertyStaff.setType("OFF");//编外，自建
                                    if (row.getCell(5) != null) {
                                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                                        String userMobile = row.getCell(5).getStringCellValue();
                                        if (StringUtil.isMobile(userMobile)) {
                                            userPropertyStaff.setMobile(userMobile);//手机
                                        } else if (StringUtil.isFixedPhone(userMobile)) {
                                            userPropertyStaff.setMobile(userMobile);//区号+座机号码+分机号码
                                        } else {
                                            return "错误！数据不规范！ 第" + (j + 1) + "行第6列数据格式不对";
                                        }
                                    }
//                                    if (!StringUtil.isEmpty(row.getCell(5).getRichStringCellValue().getString())) {//第六列数据 联系方式
//                                        String userMobile = row.getCell(5).getRichStringCellValue().getString().trim();
//                                        if (StringUtil.isMobile(userMobile)) {
//                                            userPropertyStaff.setMobile(userMobile);//手机
//                                        } else if (StringUtil.isFixedPhone(userMobile)) {
//                                            userPropertyStaff.setMobile(userMobile);//区号+座机号码+分机号码
//                                        } else {
//                                            return "错误！数据不规范！ 第" + j + 1 + "行第6列数据格式不对";
//                                        }
//                                    }
                                    if (row.getCell(6) != null) {//第七列数据 人员备注
                                        String memo = row.getCell(6).getRichStringCellValue().getString().trim();
                                        userPropertyStaff.setMemo(memo);
                                    }
                                    if(row.getCell(7) !=null){//第八列数据 排序
                                        row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                                        String orderNum = row.getCell(7).getStringCellValue();
                                        userPropertyStaff.setOrderNum(Integer.parseInt(orderNum));
                                    }
                                    if(row.getCell(8) !=null) {//第九列数据 状态
                                        String staffState = row.getCell(8).getRichStringCellValue().getString().trim();
                                        if("是".equals(staffState)){
                                            userPropertyStaff.setStaffState("1");//账号有效
                                        }else if("否".equals(staffState)){
                                            userPropertyStaff.setStaffState("0");//账号无效
                                        }else{
                                            return "错误！ 第" + (j + 1) + "行第9列数据不规范！";
                                        }
                                    }else{
                                        return "错误！ 第" + (j + 1) + "行第9列数据为空！";
                                    }

                                    userPropertyStaff.setCreateBy(userInformationEntity.getStaffName());//创建人
                                    userPropertyStaff.setCreateOn(SqlDateUtils.getDate());//创建时间
                                    userPropertyStaff.setModifyBy(userInformationEntity.getStaffName());//修改人
                                    userPropertyStaff.setModifyOn(SqlDateUtils.getDate());//修改时间
                                    userPropertyStaff.setLogo(AppConfig.UserDefaultLogo);//员工默认头像

                                    boolean savestaff = userPropertystaffReposiroty.addStaff(userPropertyStaff);//保存人员信息
                                    if (savestaff) {
                                        UserAgencymapEntity userAgencymapEntity = new UserAgencymapEntity();
                                        BaseProjectPeopleEntity baseProjectPeopleEntity = new BaseProjectPeopleEntity();
                                        baseProjectPeopleEntity.setStatus("1");
                                        baseProjectPeopleEntity.setPeopleName(userPropertyStaff.getStaffName());
                                        baseProjectPeopleEntity.setPeopleId(userPropertyStaff.getStaffId());
                                        if ("4".equals(agencyEntity.getAgencyType())) {  //当类型为责任单位或监理时需维护基础数据表
                                            baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                                            baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                                            baseProjectPeopleEntity.setSupplierType("1");
                                            baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                                            baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                            baseProjectPeopleEntity.setModifyTime(new Date());
                                            projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);   //保存基础人员数据
                                        }
                                        if ("5".equals(agencyEntity.getAgencyType())) {
                                            baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                                            baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                                            baseProjectPeopleEntity.setSupplierType("2");
                                            baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                                            baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                            baseProjectPeopleEntity.setModifyTime(new Date());
                                            projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);
                                        }
                                        userAgencymapEntity.setMapId(IdGen.uuid());
                                        userAgencymapEntity.setStaffId(userPropertyStaff.getStaffId());
                                        userAgencymapEntity.setAgencyId(agencyEntity.getAgencyId());
                                        userAgencyRepository.addDumpUserAgency(userAgencymapEntity);   //保存用户与组织机构关系
                                    } else {
                                        return "添加人员信息出错";
                                    }
                                }
                            } else {
                                return "错误！数据不规范！第" + (j + 1) + "行第4列数据为空！";
                            }
                        } else {   //没有则创建ID
                            if (row.getCell(3) != null) {//第四列数据 用户名
                                String userNameR = row.getCell(3).getRichStringCellValue().getString().trim();
                                UserPropertyStaffEntity userPropertyStaffEntity1 = userPropertystaffReposiroty.getByUserName(userNameR);//根据用户名查询是否已被注册
                                if (userPropertyStaffEntity1 != null) {
                                    return "错误！第" + (j + 1) + "行第4列用户名已被注册！";
                                } else {
                                    agencyEntity = new AgencyEntity();
                                    agencyEntity.setAgencyId(IdGen.uuid());
                                    agencyEntity.setAgencyName(agencyName);//机构名称
                                    agencyEntity.setIsRoot("0");//是否根目录
                                    agencyEntity.setAgencyType("4");//责任单位
                                    String natureValue = "";
                                    if (row.getCell(1) != null) {//第二列数据 机构性质
                                        String nature = row.getCell(1).getRichStringCellValue().getString().trim();
                                        if (nature.equals("总包")) {
                                            natureValue = "1";
                                        } else if (nature.equals("分包")) {
                                            natureValue = "2";
                                        } else {
                                            natureValue = "3";
                                        }
                                        agencyEntity.setNature(natureValue);//监理
                                    } else {
                                        return "错误！数据不规范！第" + (j + 1) + "行第2列数据为空！";
                                    }
                                    if (row.getCell(2) != null) {//第三列数据 机构备注
                                        String memo = row.getCell(2).getRichStringCellValue().getString().trim();
                                        agencyEntity.setAgencyDesc(memo);
                                    }
                                    agencyEntity.setStatus(projectEmployDTO.getsStatus());//正常
                                    agencyEntity.setCreateTime(new Date());//创建时间
                                    agencyEntity.setModifyTime(new Date());//修改时间

                                    AgencyEntity agencyEntity1;
                                    if (!"3".equals(natureValue)) {
                                        agencyEntity1 = agencyRepository.getAgencyDetail("301");   //责任单位根目录主键暂定为301
                                    } else {
                                        agencyEntity.setAgencyType("5");
                                        agencyEntity1 = agencyRepository.getAgencyDetail("304");   //监理根目录主键暂定为304
                                    }
                                    agencyEntity.setParentId(agencyEntity1.getAgencyId());
                                    agencyEntity.setAgencyPath(agencyEntity1.getAgencyPath() + "/" + agencyEntity.getAgencyId());
                                    agencyEntity.setLevel(agencyEntity1.getLevel() + 1);
                                    boolean saveAgency = agencyRepository.addAgencys(agencyEntity);  //保存责任单位
                                    if (saveAgency) {
                                        if ("1".equals(projectEmployDTO.getsStatus())) {
                                            ProjectStaffEmployEntity projectStaffEmployEntity = new ProjectStaffEmployEntity();
                                            projectStaffEmployEntity.setDataId(agencyEntity.getAgencyId());
                                            projectStaffEmployEntity.setProjectId(projectEmployDTO.getProjectId());
                                            projectStaffEmployEntity.setDataType("1");
                                            projectStaffEmployEntity.setStatus("1");
                                            projectStaffEmployEntity.setModifyTime(new Date());
                                            staffEmployRepository.addProjectStaffEmploy(projectStaffEmployEntity);  //保存责任单位与项目的关系
                                        }

                                        UserPropertyStaffEntity userPropertyStaff = new UserPropertyStaffEntity();
                                        String pwd = PasswordEncode.digestPassword("123456");
                                        userPropertyStaff.setStaffId(IdGen.uuid());
                                        userPropertyStaff.setUserName(userNameR);//用户名
                                        userPropertyStaff.setPassword(pwd);//密码
                                        if (row.getCell(4) != null) {//第五列数据 姓名
                                            String staffNameR = row.getCell(4).getRichStringCellValue().getString().trim();
                                            userPropertyStaff.setStaffName(staffNameR);//姓名
                                        }
                                        userPropertyStaff.setType("OFF");//编外，自建
                                        if (row.getCell(5) != null) {//第六列数据 联系方式
                                            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                                            String userMobile = row.getCell(5).getStringCellValue();
                                            if (StringUtil.isMobile(userMobile)) {
                                                userPropertyStaff.setMobile(userMobile);//手机
                                            } else if (StringUtil.isFixedPhone(userMobile)) {
                                                userPropertyStaff.setMobile(userMobile);//区号+座机号码+分机号码
                                            } else {
                                                return "错误！数据不规范！ 第" + (j + 1) + "行第6列数据格式不对";
                                            }
                                        }
                                        if (row.getCell(6) != null) {//第七列数据 人员备注
                                            String memo = row.getCell(6).getRichStringCellValue().getString().trim();
                                            userPropertyStaff.setMemo(memo);
                                        }
                                        if(row.getCell(7) !=null){//第八列数据 排序
                                            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                                            String orderNum = row.getCell(7).getStringCellValue();
                                            userPropertyStaff.setOrderNum(Integer.parseInt(orderNum));
                                        }
                                        if(row.getCell(8) !=null) {//第九列数据 状态
                                            String staffState = row.getCell(8).getRichStringCellValue().getString().trim();
                                            if("是".equals(staffState)){
                                                userPropertyStaff.setStaffState("1");//账号有效
                                            }else if("否".equals(staffState)){
                                                userPropertyStaff.setStaffState("0");//账号无效
                                            }else{
                                                return "错误！ 第" + (j + 1) + "行第9列数据不规范！";
                                            }
                                        }else{
                                            return "错误！ 第" + (j + 1) + "行第9列数据为空！";
                                        }
                                        userPropertyStaff.setCreateBy(userInformationEntity.getStaffName());//创建人
                                        userPropertyStaff.setCreateOn(SqlDateUtils.getDate());//创建时间
                                        userPropertyStaff.setModifyBy(userInformationEntity.getStaffName());//修改人
                                        userPropertyStaff.setModifyOn(SqlDateUtils.getDate());//修改时间
                                        userPropertyStaff.setLogo(AppConfig.UserDefaultLogo);//员工默认头像

                                        boolean savestaff = userPropertystaffReposiroty.addStaff(userPropertyStaff);//保存人员信息
                                        if (savestaff) {
                                            UserAgencymapEntity userAgencymapEntity = new UserAgencymapEntity();
                                            BaseProjectPeopleEntity baseProjectPeopleEntity = new BaseProjectPeopleEntity();
                                            baseProjectPeopleEntity.setStatus("1");
                                            baseProjectPeopleEntity.setPeopleName(userPropertyStaff.getStaffName());
                                            baseProjectPeopleEntity.setPeopleId(userPropertyStaff.getStaffId());
                                            if ("4".equals(agencyEntity.getAgencyType())) {  //当类型为责任单位或监理时需维护基础数据表
                                                baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                                                baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                                                baseProjectPeopleEntity.setSupplierType("1");
                                                baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                                                baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                                baseProjectPeopleEntity.setModifyTime(new Date());
                                                projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);   //保存基础人员数据
                                            }
                                            if ("5".equals(agencyEntity.getAgencyType())) {
                                                baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                                                baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                                                baseProjectPeopleEntity.setSupplierType("2");
                                                baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                                                baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                                baseProjectPeopleEntity.setModifyTime(new Date());
                                                projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);
                                            }
                                            userAgencymapEntity.setMapId(IdGen.uuid());
                                            userAgencymapEntity.setStaffId(userPropertyStaff.getStaffId());
                                            userAgencymapEntity.setAgencyId(agencyEntity.getAgencyId());
                                            userAgencyRepository.addDumpUserAgency(userAgencymapEntity);   //保存用户与组织机构关系
                                        } else {
                                            return "添加人员信息出错";
                                        }
                                    } else {
                                        return "保存架构信息出错";
                                    }
                                }
                            } else {
                                return "错误！数据不规范！第" + (j + 1) + "行第4列数据为空！";
                            }
                        }
                    } else {
                        return "错误！数据不规范！ 第" + (j + 1) + "行第1列数据为空";
                    }
                }
            }
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "未知错误！";
        }
    }

    @Override
    public String importAuthMechanismPeopleExcel(InputStream fis, String projectId, UserInformationEntity userInfor) {
        AuthOuterAgencyEntity authOuterAgencyEntity;
        AuthAgencyESEntity authAgencyESEntity=staffEmployRepository.getAuthAgencyESEntityByid(projectId);
        try{
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
                    //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                    //1.对每一行数据的主要列进行数据校验
                    //1.1 校验责任单位名称
                    if (row.getCell(0) == null) {
                        return "错误！数据不规范！ 第" + (j + 1) + "行第1列数据为空";
                    }
                    //1.2校验责任单位性质
                    if (row.getCell(1) == null) {
                        return "错误！数据不规范！ 第" + (j + 1) + "行第2列数据为空";
                    }
                    //1.3校验责任单位简称
                    if (row.getCell(2) == null) {
                        return "错误！数据不规范！ 第" + (j + 1) + "行第3列数据为空";
                    }
                    //1.4 校验人员姓名
                    if (row.getCell(3) == null) {
                        return "错误！数据不规范！ 第" + (j + 1) + "行第5列数据为空";
                    }
                    //1.5校验手机号
                    if (row.getCell(4) == null) {
                        return "错误！数据不规范！ 第" + (j + 1) + "行第6列数据为空";
                    }
                    //1.6 校验人员启用状态
                    if (row.getCell(6) == null) {
                        return "错误！数据不规范！ 第" + (j + 1) + "行第8列数据为空";
                    }
                    //1.6.1 校验人员启用状态
                    if(row.getCell(6)!= null){
                        String staffState = row.getCell(6).getRichStringCellValue().getString().trim();
                        if(!"是".equals(staffState) && !"否".equals(staffState)){
                            return "错误！ 第" + (j + 1) + "行第9列数据不规范！";
                        }
                    }
                    //*********************校验通过后对该行数据进行数据整合保存***************************************//
                    //1.判断当前行列的责任单位名称是否存在
                    String agencyName = row.getCell(0).getRichStringCellValue().getString().trim();
                    String staffState = row.getCell(6).getRichStringCellValue().getString().trim();//人员状态
                    AuthOuterAgencyEntity authOuterAgency=staffEmployRepository.getAuthOuterAgencyByName(agencyName);
                    if(authOuterAgency!=null){
                        //存在直接使用
                    }else{
                        //不存在新增
                        authOuterAgency=new AuthOuterAgencyEntity();
                        authOuterAgency.setAgencyId(IdGen.uuid());
                        authOuterAgency.setAgencyName(agencyName);
                        authOuterAgency.setParentId("03d3df6a599747ef9bfa4332c0f919b6");//上一级别id
                        authOuterAgency.setAgencyPath("/1/03d3df6a599747ef9bfa4332c0f919b6/"+authOuterAgency.getAgencyId());//路径
                        authOuterAgency.setCreateBy(userInfor.getStaffId());
                        authOuterAgency.setCreateTime(new Date());
                        authOuterAgency.setModifyBy(userInfor.getStaffId());
                        authOuterAgency.setModifyTime(new Date());
                        authOuterAgency.setLevel(3);
                        authOuterAgency.setOutEmploy("1");
                        authOuterAgency.setStatus("1");
                        authOuterAgency.setIsTemporary("0");
                        staffEmployRepository.addAgency(authOuterAgency);
                    }
                    //2.判断当前手机号是否注册用户
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    String phone = row.getCell(4).getStringCellValue();
                    UserInformationEntity userInformationEntity = userPropertystaffReposiroty.getStaffBySysName(phone);
                    if(userInformationEntity!=null){
                        //存在则重置密码
                        String staffNameR = row.getCell(3).getRichStringCellValue().getString().trim();
                        userInformationEntity.setStaffName(staffNameR);
                        String pwd = PasswordEncode.digestPassword("123456");
                        userInformationEntity.setPassword(pwd);//密码
                        userInformationEntity.setModifyBy(userInfor.getStaffId());//修改人
                        userInformationEntity.setModifyOn(new Date());//修改时间
                        if (row.getCell(5) != null) {//第六列数据 人员备注
                            String memo = row.getCell(5).getRichStringCellValue().getString().trim();
                            userInformationEntity.setMemo(memo);//备注
                        }
                        userInformationEntity.setStaffState("1");//人员状态
                        //保存用户表信息
                        staffEmployRepository.saveUserInformationEntity(userInformationEntity);
                        UserMapEntity userMapEntityEs=staffEmployRepository.getUserMapEntity(userInformationEntity.getStaffId(),"es");
                        if(userMapEntityEs!=null){
                            userMapEntityEs.setState("1");
                            userMapEntityEs.setModifyOn(new Date());
                            staffEmployRepository.saveUserMapEntity(userMapEntityEs);
                        }else {
                            UserMapEntity userMapEs=new UserMapEntity();
                            userMapEs.setStaffId(userInformationEntity.getStaffId());
                            userMapEs.setState("1");
                            userMapEs.setSourceFrom("external");  //来源 OA:同步 external:本地创建
                            userMapEs.setModifyOn(new Date());//修改时间
                            userMapEs.setProjectModule("es");//所属模块  st  安全   qc 交付  es 工程
                            staffEmployRepository.saveUserMapEntity(userMapEs);
                        }

                        UserMapEntity userMapEntitySt=staffEmployRepository.getUserMapEntity(userInformationEntity.getStaffId(),"st");
                        if(userMapEntitySt==null){
                            UserMapEntity userMapSt=new UserMapEntity();
                            userMapSt.setStaffId(userInformationEntity.getStaffId());
                            userMapSt.setState("0");//启用状态   0  未启用   1 已启用
                            userMapSt.setSourceFrom("external");  //来源 OA:同步 external:本地创建
                            userMapSt.setModifyOn(new Date());//修改时间
                            userMapSt.setProjectModule("st");//所属模块  st  安全   qc 交付  es 工程
                            staffEmployRepository.saveUserMapEntity(userMapSt);
                        }
                        UserMapEntity userMapEntityQc=staffEmployRepository.getUserMapEntity(userInformationEntity.getStaffId(),"qc");
                        if(userMapEntityQc==null){
                            UserMapEntity userMapQc=new UserMapEntity();
                            userMapQc.setStaffId(userInformationEntity.getStaffId());
                            userMapQc.setState("0");//启用状态   0  未启用   1 已启用
                            userMapQc.setSourceFrom("external");  //来源 OA:同步 external:本地创建
                            userMapQc.setModifyOn(new Date());//修改时间
                            userMapQc.setProjectModule("qc");//所属模块  st  安全   qc 交付  es 工程
                            staffEmployRepository.saveUserMapEntity(userMapQc);
                        }
                    }else{
                        //不存在则新增用户信息
                        userInformationEntity = new UserInformationEntity();
                        userInformationEntity.setStaffId(IdGen.uuid());//员工ID
                        String pwd = PasswordEncode.digestPassword("123456");
                        userInformationEntity.setPassword(pwd);//密码
                        userInformationEntity.setSysName(phone);//系统账号   系统登录账号
                        String staffNameR = row.getCell(3).getRichStringCellValue().getString().trim();
                        userInformationEntity.setStaffName(staffNameR);//名称
                        userInformationEntity.setStaffState("1");//人员状态
                        userInformationEntity.setMobile(phone);
                        userInformationEntity.setCreateBy(userInfor.getStaffId());//创建人
                        userInformationEntity.setCreateOn(new Date());//创建时间
                        userInformationEntity.setModifyBy(userInfor.getStaffId());//修改人
                        userInformationEntity.setModifyOn(new Date());//修改时间
                        userInformationEntity.setEmail("");//邮件
                        if (row.getCell(5) != null) {//第七列数据 人员备注
                            String memo = row.getCell(5).getRichStringCellValue().getString().trim();
                            userInformationEntity.setMemo(memo);//备注
                        }
                        userInformationEntity.setJinmaoIs("0");
                        staffEmployRepository.saveUserInformationEntity(userInformationEntity);
                        UserMapEntity userMapEntityEs=new UserMapEntity();
                        userMapEntityEs.setStaffId(userInformationEntity.getStaffId());
                        userMapEntityEs.setState("1");
                        userMapEntityEs.setSourceFrom("external");  //来源 OA:同步 external:本地创建
                        userMapEntityEs.setModifyOn(new Date());//修改时间
                        userMapEntityEs.setProjectModule("es");//所属模块  st  安全   qc 交付  es 工程
                        staffEmployRepository.saveUserMapEntity(userMapEntityEs);

                        //安全
                        UserMapEntity userMapEntitySt=new UserMapEntity();
                        userMapEntitySt.setStaffId(userInformationEntity.getStaffId());
                        userMapEntitySt.setState("0");
                        userMapEntitySt.setSourceFrom("external");  //来源 OA:同步 external:本地创建
                        userMapEntitySt.setModifyOn(new Date());//修改时间
                        userMapEntitySt.setProjectModule("st");//所属模块  st  安全   qc 交付  es 工程
                        staffEmployRepository.saveUserMapEntity(userMapEntitySt);

                        //客观
                        UserMapEntity userMapEntityQc=new UserMapEntity();
                        userMapEntityQc.setStaffId(userInformationEntity.getStaffId());
                        userMapEntityQc.setState("1");
                        userMapEntityQc.setSourceFrom("external");  //来源 OA:同步 external:本地创建
                        userMapEntityQc.setModifyOn(new Date());//修改时间
                        userMapEntityQc.setProjectModule("qc");//所属模块  st  安全   qc 交付  es 工程
                        staffEmployRepository.saveUserMapEntity(userMapEntityQc);
                    }
                    //3. 校验责任单位和人的关联关系
                    if(staffEmployRepository.getCheckUserAgencyEntity(authOuterAgency.getAgencyId(),userInformationEntity.getStaffId(),"1")){
                        //是否已经绑定
                        UserAgencyEntity userAgencyEntity=staffEmployRepository.getUserAgencyEntity(authOuterAgency.getAgencyId(),userInformationEntity.getStaffId());
                        if(userAgencyEntity!=null){

                        }else{
                            userAgencyEntity=new UserAgencyEntity();
                            userAgencyEntity.setMapId(IdGen.uuid()); //主键
                        }
                        userAgencyEntity.setStaffId(userInformationEntity.getStaffId());//用户ID
                        userAgencyEntity.setAgencyId(authOuterAgency.getAgencyId()); //责任单位机构ID
                        userAgencyEntity.setModifyTime(new Date()); //修改时间
                        userAgencyEntity.setStatus("1");
                        userAgencyEntity.setSourceFrom("external");
                        staffEmployRepository.saveOrUpdateuserAgencyEntity(userAgencyEntity);
                    }

                    //判断项目和责任单位是否关联
                    if(staffEmployRepository.getCheckBaseProjectPeopleEntity(authOuterAgency.getAgencyId(),"",projectId)){
                        BaseProjectPeopleEntity baseProjectPeople=new BaseProjectPeopleEntity();
                        baseProjectPeople.setPeopleId("");//人员ID
                        baseProjectPeople.setPeopleName("");//人员名称
                        baseProjectPeople.setSupplierId(authOuterAgency.getAgencyId());  //责任单位或监理ID
                        baseProjectPeople.setSupplierName(authOuterAgency.getAgencyName());   //责任单位名称
                        String supplierType = row.getCell(1).getRichStringCellValue().getString().trim();
                        String roleType="";
                        if("总包".equals(supplierType)){
                            roleType="1";
                        }else if("分包".equals(supplierType)){
                            roleType="2";
                        }else if("监理".equals(supplierType)){
                            roleType="3";
                        }
                        baseProjectPeople.setSupplierType(roleType);  //类型 1总包  2分包   3.监理
                        baseProjectPeople.setProjectId(projectId);  //项目ID
                        baseProjectPeople.setProjectName(authAgencyESEntity.getAgencyName()); //项目名
                        baseProjectPeople.setModifyTime(new Date());  //修改时间
                        baseProjectPeople.setStatus("1"); //状态 0删除 1正常
                        String abbreviationName = row.getCell(2).getRichStringCellValue().getString().trim();
                        baseProjectPeople.setAbbreviationName(abbreviationName);//责任单位简称
                        staffEmployRepository.addbaseProjectPeople(baseProjectPeople);
                    }
                    //4.校验 项目id+责任单位id+人员id 是否存在绑定数据
                    if(staffEmployRepository.getCheckBaseProjectPeopleEntity(authOuterAgency.getAgencyId(),userInformationEntity.getStaffId(),projectId)){
                        //不存在绑定信息数据  绑定过就不用操作了
                        BaseProjectPeopleEntity baseProjectPeople=new BaseProjectPeopleEntity();
                        baseProjectPeople.setPeopleId(userInformationEntity.getStaffId());//人员ID
                        baseProjectPeople.setPeopleName(userInformationEntity.getStaffName());//人员名称
                        baseProjectPeople.setSupplierId(authOuterAgency.getAgencyId());  //责任单位或监理ID
                        baseProjectPeople.setSupplierName(authOuterAgency.getAgencyName());   //责任单位名称
                        String supplierType = row.getCell(1).getRichStringCellValue().getString().trim();
                        String roleType="";
                        if("总包".equals(supplierType)){
                            roleType="1";
                        }else if("分包".equals(supplierType)){
                            roleType="2";
                        }else if("监理".equals(supplierType)){
                            roleType="3";
                        }
                        baseProjectPeople.setSupplierType(roleType);  //类型 1总包  2分包   3.监理
                        baseProjectPeople.setProjectId(projectId);  //项目ID
                        baseProjectPeople.setProjectName(authAgencyESEntity.getAgencyName()); //项目名
                        baseProjectPeople.setModifyTime(new Date());  //修改时间
                        if("是".equals(staffState)){
                            baseProjectPeople.setStatus("1"); //状态 0删除 1正常
                        }else if("否".equals(staffState)){
                            baseProjectPeople.setStatus("0"); //状态 0删除 1正常
                        }
                        String abbreviationName = row.getCell(2).getRichStringCellValue().getString().trim();
                        baseProjectPeople.setAbbreviationName(abbreviationName);//责任单位简称
                        staffEmployRepository.addbaseProjectPeople(baseProjectPeople);
                    }else{
                        String supplierType = row.getCell(1).getRichStringCellValue().getString().trim();
                        String roleType="";
                        if("总包".equals(supplierType)){
                            roleType="1";
                        }else if("分包".equals(supplierType)){
                            roleType="2";
                        }else if("监理".equals(supplierType)){
                            roleType="3";
                        }
                        String authStatus="1";
                        if("是".equals(staffState)){
                            authStatus="1";
                        }else if("否".equals(staffState)){
                            authStatus="0";
                        }
                        String abbreviationName = row.getCell(2).getRichStringCellValue().getString().trim();
                        staffEmployRepository.upBaseProjectPeopleName(userInformationEntity.getStaffId(),userInformationEntity.getStaffName(),abbreviationName,roleType,authStatus);
                    }
                    //5.校验 项目id+角色+人员id 是否存在绑定数据
                    //不管存不存在信息  先删除 当前人+项目 在人+项目+角色的绑定关系
                    staffEmployRepository.delAgencyRoleUser(projectId,userInformationEntity.getStaffId(),"","0");
                    String supplierType = row.getCell(1).getRichStringCellValue().getString().trim();
                    String roleType="";
                    if("总包".equals(supplierType)){
                        roleType="1";
                    }else if("分包".equals(supplierType)){
                        roleType="2";
                    }else if("监理".equals(supplierType)){
                        roleType="3";
                    }
                    String roleId=staffEmployRepository.getRoleIdBylikeName("",roleType,projectId);
                    if(!StringUtil.isEmpty(roleId)){
                        RoleStaffProjectMapESEntity roleStaffProjectMapES=new RoleStaffProjectMapESEntity();
                        roleStaffProjectMapES.setAgencyId(projectId);
                        roleStaffProjectMapES.setRoleId(roleId);
                        roleStaffProjectMapES.setStaffId(userInformationEntity.getStaffId());
                        if("是".equals(staffState)){
                            roleStaffProjectMapES.setState("1");
                        }else if("否".equals(staffState)){
                            roleStaffProjectMapES.setState("0");
                        }
                        roleStaffProjectMapES.setModifyOn(new Date());
                        staffEmployRepository.saveOrUpdateProjectRoleStaff(roleStaffProjectMapES);
                    }
                }
            }
            return "ok";
        }catch (IOException e) {
            e.printStackTrace();
            return "未知错误！";
        }
    }

    @Override
    public String importMechanismPeopleExcel(InputStream fis, String projectId, UserPropertyStaffEntity userPropertyStaffEntity, String fileName, List<String> attributes, int attributesNotNullNumber) {
        AgencyEntity agencyEntity;
        try {
            // 获取解析的excel中的数据
            List<List<Map<String, String>>> dataList = ReadExcel.importData(fileName, fis, attributes, attributesNotNullNumber);
            List<Map<String, String>> rightDataList = new ArrayList<Map<String, String>>();
            List<Map<String, String>> wrongDataList = new ArrayList<Map<String, String>>();
            if (dataList != null && dataList.size() > 0) {
                rightDataList = dataList.get(0);
                wrongDataList = dataList.get(1);
                if (wrongDataList != null && wrongDataList.size() > 0) {
                    Map<String, String> data = wrongDataList.get(0);
                    String errorMsg = data.get("error");
                    return errorMsg;
                }
                if (rightDataList != null && rightDataList.size() > 0) {
                    for (int i = 0; i < rightDataList.size(); i++) {
                        Map<String, String> data = rightDataList.get(i);

                        String sheetName = data.get("SHEET");
                        String lineNum = data.get("LINE");
                        String title = data.get("title");
                        if (title != null) {
                            return title;
                        }
                        String agencyName = data.get("机构名称");
                        String agencyNature = data.get("机构性质");
                        String agencyMemo = data.get("机构备注");
                        String userName = data.get("用户名");
                        String staffName = data.get("姓名");
                        String mobile = data.get("联系方式");
                        String userMemo = data.get("人员备注");

                        if (StringUtil.isEmpty(agencyName)) {
                            return "错误：" + fileName + "-" + "sheet" + sheetName + "-第" + lineNum + "行" + "-机构名称数据为空";
                        } else {
                            agencyEntity = agencyRepository.getAgencyDetailByName(agencyName);//根据机构名称查询数据库中是否有这条数据
                            if (agencyEntity != null) {//如果机构已存在，为此关联的人员信息
                                if (!StringUtil.isEmpty(userName)) {//第四列数据 用户名
                                    UserPropertyStaffEntity userPropertyStaffEntity1 = userPropertystaffReposiroty.getByUserName(userName);//根据用户名查询是否已被注册
                                    if (userPropertyStaffEntity1 != null) {
                                        List<AgencyEntity> agencyEntities = userAgencyRepository.getAgencysByStaffId(userPropertyStaffEntity1.getStaffId());
                                        if (agencyEntities != null && agencyEntities.size() > 0) {
                                            for (AgencyEntity agencyEntity1 : agencyEntities) {
                                                if (agencyEntity1.getAgencyId().equals(agencyEntity.getAgencyId())) {
                                                    continue;
                                                } else {
                                                    return "错误：" + fileName + "-" + "sheet" + sheetName + "-第" + lineNum + "行" + "-用户名已被注册";
                                                }
                                            }
                                        } else {
                                            return "错误：" + fileName + "-" + "sheet" + sheetName + "-第" + lineNum + "行" + "-用户名已被注册";
                                        }
                                    } else {
                                        UserPropertyStaffEntity userPropertyStaff = new UserPropertyStaffEntity();
                                        userPropertyStaff.setStaffId(IdGen.uuid());
                                        userPropertyStaff.setUserName(userName);//用户名
                                        String pwd = PasswordEncode.digestPassword("123456");
                                        userPropertyStaff.setPassword(pwd);//密码
                                        if (!StringUtil.isEmpty(staffName)) {
                                            userPropertyStaff.setStaffName(staffName);//姓名
                                        }
                                        userPropertyStaff.setStaffState("1");//账号有效
                                        userPropertyStaff.setType("OFF");//编外，自建
                                        if (!StringUtil.isEmpty(mobile)) {
                                            if (StringUtil.isMobile(mobile)) {
                                                userPropertyStaff.setMobile(mobile);//手机
                                            } else if (StringUtil.isFixedPhone(mobile)) {
                                                userPropertyStaff.setMobile(mobile);//区号+座机号码+分机号码
                                            } else {
                                                return "错误！" + fileName + "-" + "sheet" + sheetName + "-第" + lineNum + "行手机数据格式不对";
                                            }
                                        }
                                        if (!StringUtil.isEmpty(userMemo)) {
                                            userPropertyStaff.setMemo(userMemo);
                                        }
                                        userPropertyStaff.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                        userPropertyStaff.setCreateOn(SqlDateUtils.getDate());//创建时间
                                        userPropertyStaff.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                                        userPropertyStaff.setModifyOn(SqlDateUtils.getDate());//修改时间
                                        userPropertyStaff.setLogo(AppConfig.UserDefaultLogo);//员工默认头像

                                        boolean savestaff = userPropertystaffReposiroty.addStaff(userPropertyStaff);//保存人员信息
                                        if (savestaff) {
                                            UserAgencymapEntity userAgencymapEntity = new UserAgencymapEntity();
                                            BaseProjectPeopleEntity baseProjectPeopleEntity = new BaseProjectPeopleEntity();
                                            baseProjectPeopleEntity.setStatus("1");
                                            baseProjectPeopleEntity.setPeopleName(userPropertyStaff.getStaffName());
                                            baseProjectPeopleEntity.setPeopleId(userPropertyStaff.getStaffId());
                                            if ("4".equals(agencyEntity.getAgencyType())) {  //当类型为责任单位或监理时需维护基础数据表
                                                baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                                                baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                                                baseProjectPeopleEntity.setSupplierType("1");
                                                baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                                                baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                                baseProjectPeopleEntity.setModifyTime(new Date());
                                                projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);   //保存基础人员数据
                                            }
                                            if ("5".equals(agencyEntity.getAgencyType())) {
                                                baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                                                baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                                                baseProjectPeopleEntity.setSupplierType("2");
                                                baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                                                baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                                baseProjectPeopleEntity.setModifyTime(new Date());
                                                projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);
                                            }
                                            userAgencymapEntity.setMapId(IdGen.uuid());
                                            userAgencymapEntity.setStaffId(userPropertyStaff.getStaffId());
                                            userAgencymapEntity.setAgencyId(agencyEntity.getAgencyId());
                                            userAgencyRepository.addDumpUserAgency(userAgencymapEntity);   //保存用户与组织机构关系
                                        } else {
                                            return "添加人员信息出错";
                                        }
                                    }
                                } else {
                                    return "错误！" + fileName + "-" + "sheet" + sheetName + "-第" + lineNum + "行用户名数据为空！";
                                }
                            } else {   //没有则创建ID
                                if (!StringUtil.isEmpty(userName)) {//第四列数据 用户名
                                    UserPropertyStaffEntity userPropertyStaffEntity1 = userPropertystaffReposiroty.getByUserName(userName);//根据用户名查询是否已被注册
                                    if (userPropertyStaffEntity1 != null) {
                                        return "错误！" + fileName + "-" + "sheet" + sheetName + "-第" + lineNum + "行用户名已被注册！";
                                    } else {
                                        agencyEntity = new AgencyEntity();
                                        agencyEntity.setAgencyId(IdGen.uuid());
                                        agencyEntity.setAgencyName(agencyName);//机构名称
                                        agencyEntity.setIsRoot("0");//是否根目录
                                        agencyEntity.setAgencyType("4");//责任单位
                                        String natureValue = "";
                                        if (!StringUtil.isEmpty(agencyNature)) {//第二列数据 机构性质
                                            if (agencyNature.equals("总包")) {
                                                natureValue = "1";
                                            } else if (agencyNature.equals("分包")) {
                                                natureValue = "2";
                                            } else {
                                                natureValue = "3";
                                            }
                                            agencyEntity.setNature(natureValue);//监理
                                        } else {
                                            return "错误！" + fileName + "-" + "sheet" + sheetName + "-第" + lineNum + "行机构性质数据为空！";
                                        }
                                        if (!StringUtil.isEmpty(agencyMemo)) {//第三列数据 机构备注
                                            agencyEntity.setAgencyDesc(agencyMemo);
                                        }
                                        agencyEntity.setStatus("1");//正常
                                        agencyEntity.setCreateTime(new Date());//创建时间
                                        agencyEntity.setModifyTime(new Date());//修改时间

                                        AgencyEntity agencyEntity1;
                                        if (!"3".equals(natureValue)) {
                                            agencyEntity1 = agencyRepository.getAgencyDetail("301");   //责任单位根目录主键暂定为301
                                        } else {
                                            agencyEntity.setAgencyType("5");
                                            agencyEntity1 = agencyRepository.getAgencyDetail("304");   //监理根目录主键暂定为304
                                        }
                                        agencyEntity.setParentId(agencyEntity1.getAgencyId());
                                        agencyEntity.setAgencyPath(agencyEntity1.getAgencyPath() + "/" + agencyEntity.getAgencyId());
                                        agencyEntity.setLevel(agencyEntity1.getLevel() + 1);
                                        boolean saveAgency = agencyRepository.addAgencys(agencyEntity);  //保存责任单位
                                        if (saveAgency) {
                                            if ("1".equals(agencyEntity.getStatus())) {
                                                ProjectStaffEmployEntity projectStaffEmployEntity = new ProjectStaffEmployEntity();
                                                projectStaffEmployEntity.setDataId(agencyEntity.getAgencyId());
                                                projectStaffEmployEntity.setProjectId(projectId);
                                                projectStaffEmployEntity.setDataType("1");
                                                projectStaffEmployEntity.setStatus("1");
                                                projectStaffEmployEntity.setModifyTime(new Date());
                                                staffEmployRepository.addProjectStaffEmploy(projectStaffEmployEntity);  //保存责任单位与项目的关系
                                            }
                                            UserPropertyStaffEntity userPropertyStaff = new UserPropertyStaffEntity();
                                            String pwd = PasswordEncode.digestPassword("123456");
                                            userPropertyStaff.setStaffId(IdGen.uuid());
                                            userPropertyStaff.setUserName(userName);//用户名
                                            userPropertyStaff.setPassword(pwd);//密码
                                            if (!StringUtil.isEmpty(staffName)) {//第五列数据 姓名
                                                userPropertyStaff.setStaffName(staffName);//姓名
                                            }
                                            userPropertyStaff.setStaffState("1");//账号有效
                                            userPropertyStaff.setType("OFF");//编外，自建
                                            if (!StringUtil.isEmpty(mobile)) {//第六列数据 联系方式
                                                if (StringUtil.isMobile(mobile)) {
                                                    userPropertyStaff.setMobile(mobile);//手机
                                                } else if (StringUtil.isFixedPhone(mobile)) {
                                                    userPropertyStaff.setMobile(mobile);//区号+座机号码+分机号码
                                                } else {
                                                    return "错误！" + fileName + "-" + "sheet" + sheetName + "-第" + lineNum + "行联系方式格式不对！";
                                                }
                                            }
                                            if (StringUtil.isEmpty(userMemo)) {//第七列数据 人员备注
                                                userPropertyStaff.setMemo(userMemo);
                                            }
                                            userPropertyStaff.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                            userPropertyStaff.setCreateOn(SqlDateUtils.getDate());//创建时间
                                            userPropertyStaff.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                                            userPropertyStaff.setModifyOn(SqlDateUtils.getDate());//修改时间
                                            userPropertyStaff.setLogo(AppConfig.UserDefaultLogo);//员工默认头像

                                            boolean savestaff = userPropertystaffReposiroty.addStaff(userPropertyStaff);//保存人员信息
                                            if (savestaff) {
                                                UserAgencymapEntity userAgencymapEntity = new UserAgencymapEntity();
                                                BaseProjectPeopleEntity baseProjectPeopleEntity = new BaseProjectPeopleEntity();
                                                baseProjectPeopleEntity.setStatus("1");
                                                baseProjectPeopleEntity.setPeopleName(userPropertyStaff.getStaffName());
                                                baseProjectPeopleEntity.setPeopleId(userPropertyStaff.getStaffId());
                                                if ("4".equals(agencyEntity.getAgencyType())) {  //当类型为责任单位或监理时需维护基础数据表
                                                    baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                                                    baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                                                    baseProjectPeopleEntity.setSupplierType("1");
                                                    baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                                                    baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                                    baseProjectPeopleEntity.setModifyTime(new Date());
                                                    projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);   //保存基础人员数据
                                                }
                                                if ("5".equals(agencyEntity.getAgencyType())) {
                                                    baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                                                    baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                                                    baseProjectPeopleEntity.setSupplierType("2");
                                                    baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                                                    baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                                    baseProjectPeopleEntity.setModifyTime(new Date());
                                                    projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);
                                                }
                                                userAgencymapEntity.setMapId(IdGen.uuid());
                                                userAgencymapEntity.setStaffId(userPropertyStaff.getStaffId());
                                                userAgencymapEntity.setAgencyId(agencyEntity.getAgencyId());
                                                userAgencyRepository.addDumpUserAgency(userAgencymapEntity);   //保存用户与组织机构关系
                                            } else {
                                                return "保存人员信息出错";
                                            }
                                        } else {
                                            return "保存架构信息出错";
                                        }
                                    }
                                } else {
                                    return "错误！" + fileName + "-" + "sheet" + sheetName + "-第" + lineNum + "行用户名数据为空！";
                                }
                            }
                        }
                    }
                } else {
                    return "没有可导入的数据";
                }
//            System.out.println(rightDataList);
            } else {
                return "没有可导入的数据";
            }
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "网络异常，请重试！";
        }
    }
}
