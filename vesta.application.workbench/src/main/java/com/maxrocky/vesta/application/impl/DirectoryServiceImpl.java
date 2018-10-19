package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.WorkOrderDepartmentDTO;
import com.maxrocky.vesta.application.DTO.WorkOrderTelBookDTO;
import com.maxrocky.vesta.application.DTO.WorkOrderUserDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.DirectoryDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.MobileBookDTO;
import com.maxrocky.vesta.application.inf.DirectoryService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/22.
 */
@Service
public class DirectoryServiceImpl implements DirectoryService {

    @Autowired
    private DirectoryRepository directoryRepository;

    @Autowired
    private HouseCompanyRepository companyRepository;
    @Autowired
    private HouseSectionRepository houseSectionRepository;

    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    private StaffRegisterRepository staffRegisterRepository;

    /**
     * 查询该员工所在公司的所有通讯录
     * @param projectId
     * @return
     */
    @Override
    public ApiResult listDirectory(String projectId) throws GeneralException{
            if (projectId==null){
                return ErrorResource.getError("tip_Directory_CompanyNull");
            }
        try {
            //此处是根据公司Id查询公司信息

            List<HouseSectionEntity> sections = houseSectionRepository.listSectionByProject(projectId);
            if (sections.size() == 0) {
                return ErrorResource.getError("tip_Directory_SectionNull");
            }
            List<DirectoryDTO> directoryDTOs = new ArrayList<>();
            for (HouseSectionEntity s : sections) {
                DirectoryDTO directoryDTO = new DirectoryDTO();
                directoryDTO.setSection(s.getSectionName());
                List<UserPropertyStaffEntity> userPropertyStaffEntities = userPropertyStaffRepository.listStaffByCompanyAndSection(projectId, s.getSectionId());
                if (userPropertyStaffEntities.size() > 0) {
                    List<MobileBookDTO> mobileBook = new ArrayList<>();
                    for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities) {
                        MobileBookDTO mobileBookDTO = new MobileBookDTO();
                        mobileBookDTO.setName(userPropertyStaffEntity.getStaffName());
                        mobileBookDTO.setMobile(userPropertyStaffEntity.getMobile());
                        mobileBook.add(mobileBookDTO);
                    }
                    directoryDTO.setMobileBook(mobileBook);
                }
                directoryDTOs.add(directoryDTO);
            }
            if (directoryDTOs.size() == 0) {
                return ErrorResource.getError("tip_Directory_Null");
            }
            return new SuccessApiResult(directoryDTOs);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }

    }

    /**
     * 获取所有部门的通讯录(员工端)
     * createBy：liudongxin
     * @param projectId
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getTelephoneBook(String projectId) throws GeneralException {
        if(StringUtil.isEmpty(projectId)){
            return ErrorResource.getError("tip_pe00000020");//项目id不能为空
        }
        try{
            //获取所有部门
            List<HouseSectionEntity> sections = houseSectionRepository.getDepartmentList(projectId);
            if (sections.size() == 0) {
                return ErrorResource.getError("tip_Directory_SectionNull");
            }
            //封装到通讯录dto
            WorkOrderTelBookDTO telBook=new WorkOrderTelBookDTO();
            //部门集合
            List<WorkOrderDepartmentDTO> departmentList=new ArrayList<WorkOrderDepartmentDTO>();
            for(HouseSectionEntity section:sections){
                //人员集合
                List<WorkOrderUserDTO> userList=new ArrayList<WorkOrderUserDTO>();
                String today= DateUtils.format(new Date(), "yyyy-MM-dd");//当前日期
                List<StaffRegisterEntity> registerEntities =staffRegisterRepository.getRegister(section.getSectionId(), today);
                if(registerEntities.size()>0){
                    for (StaffRegisterEntity staff: registerEntities) {
                        WorkOrderUserDTO workOrderUserDTO =new WorkOrderUserDTO();
                        workOrderUserDTO.setUserId(staff.getStaffId());//用户id
                        workOrderUserDTO.setName(staff.getStaffName());//用户姓名
                        workOrderUserDTO.setMobile(staff.getMobile());//用户电话
                        userList.add(workOrderUserDTO);
                    }
                }
                //部门dto
                WorkOrderDepartmentDTO department=new WorkOrderDepartmentDTO();
                department.setGroupName(section.getSectionName());//部门名称
                department.setMemberList(userList);
                departmentList.add(department);
                telBook.setGroupList(departmentList);
            }
            return new SuccessApiResult(telBook);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }
}
