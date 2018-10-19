package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.WorkApportionDTO;
import com.maxrocky.vesta.application.DTO.WorkOrderDepartmentDTO;
import com.maxrocky.vesta.application.DTO.WorkOrderTelBookDTO;
import com.maxrocky.vesta.application.DTO.WorkOrderUserDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseSectionAdminDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.StaffRegisterDTO;
import com.maxrocky.vesta.application.inf.HouseSectionService;
import com.maxrocky.vesta.application.inf.StaffRegisterService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.StaffRegisterEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.StaffRegisterRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhanghj on 2016/1/21.
 */
@Service
public class StaffRegisterServiceImpl implements StaffRegisterService {

    @Autowired
    private StaffRegisterRepository staffRegisterRepository;

    @Autowired
    private HouseSectionService houseSectionService;

    @Autowired
    private UserPropertyStaffRepository userPropertystaffReposiroty;


    /**
     * 员工签到记录
     * @param staffId
     *
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult saveStaffRegister(String staffId)  throws GeneralException {
        if (staffId==null){
            return  ErrorResource.getError("tip_Re_StaffNull");
        }
        try {
            UserPropertyStaffEntity userPropertystaffEntity  = userPropertystaffReposiroty.get(staffId);
            if (userPropertystaffEntity==null){
                return ErrorResource.getError("tip_Re_StaffNull");//如果相应的员工id查不到员工
            }
            HouseSectionAdminDTO houseSectionAdminDTO = houseSectionService.getSectionDTOById(userPropertystaffEntity.getDepartmentId());//查找相应部门信息
            if (houseSectionAdminDTO==null){
                return ErrorResource.getError("tip_Re_SectionNull");//如果相应的部门id查不到部门
            }

            StaffRegisterEntity staffRegisterEntity = staffRegisterRepository.getStaffRegisterById(staffId, DateUtils.format(new Date(), "yyyy-MM-dd"));//判断今天是否签到
            if (staffRegisterEntity == null) {//如果今天没签，则进行签到
                StaffRegisterEntity staffRegister = new StaffRegisterEntity();
                staffRegister.setStaffRegisterId(IdGen.uuid());
                staffRegister.setStaffId(staffId);
                staffRegister.setStaffName(userPropertystaffEntity.getStaffName());//userPropertystaffEntity.getStaffName()
                staffRegister.setMobile(userPropertystaffEntity.getMobile());
                staffRegister.setProjectId(userPropertystaffEntity.getProjectId());
                staffRegister.setStaffSectionId(houseSectionAdminDTO.getSectionId());
                staffRegister.setStaffSection(houseSectionAdminDTO.getSectionName());//houseSectionAdminDTO.getSectionName()
                staffRegister.setStaffRegisterStatus("1");//签到状态，1-签到成功，2-签到失败
                staffRegister.setRegisterDateTime(DateUtils.getDate());
                staffRegister.setRegisterDate(DateUtils.format(new Date(),"yyyy-MM-dd"));
                staffRegisterRepository.saveRegister(staffRegister);
                return new SuccessApiResult(SuccessResource.getResource("tip_su_RegisterSuc"),null);
            } else {
                return ErrorResource.getError("tip_re0005");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }

    }

    /**
     * 获取签到列表
     * @param staffId
     *
     * @param page
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult listStaffRegister(String staffId, Page page)  throws GeneralException{
            if (staffId==null){
                return  ErrorResource.getError("tip_RE_StaffIdNull");
            }
//        if(pageInfo.getNumber()==0){
//            return  ErrorResource.getError("tip_page");
//        }
//        if (pageInfo.getSize()==0){
//            return  ErrorResource.getError("tip_count");
//        }
        try {
            List<StaffRegisterDTO> staffRegisterDTOList = new ArrayList<>();
            //获取员工信息
            UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.get(staffId);
            String CreateOn = DateUtils.format(userPropertyStaffEntity.getCreateOn(), "yyyy-MM-dd");
            int x = page.getFirstResult();
            int y = page.getFirstResult()+page.getMaxResult();
            for (int i = x; i < y; i++) {
                Date date = DateUtils.addDay(new Date(), -i);
                String d = DateUtils.format(date, "yyyy-MM-dd");
                String dM = DateUtils.format(date,"yyyy-MM");//当月
                Date lastMdate = DateUtils.addMonth(new Date(), -1);//上个月
                String lastM = DateUtils.format(lastMdate, "yyyy-MM");//上个月
                String staffCreateOn = DateUtils.format(userPropertyStaffEntity.getCreateOn(), "yyyy-MM-dd");
                Date sCreateOn = DateUtils.parse(staffCreateOn + " 00:00:00");//+"00:00:00"
                Date sd = DateUtils.parse(d+" 00:00:00");
                long xx = sCreateOn.getTime();
                if (dM.equals(lastM)){
                    break;//如果日期为上个月则不查
                }

                if (sd.getTime()-sCreateOn.getTime()<0){
                        break;//如果查到开工日期的上一天，则不查
                }
                StaffRegisterEntity staffRegisterEntity = staffRegisterRepository.getStaffRegisterById(staffId, d);//判断某天是否签到

                if (staffRegisterEntity != null) {
                    StaffRegisterDTO staffRegisterDTO = new StaffRegisterDTO();
                    staffRegisterDTO.setStaffId(staffRegisterEntity.getStaffId());
                    staffRegisterDTO.setStaffName(staffRegisterEntity.getStaffName());
                    staffRegisterDTO.setSectionId("");
                    staffRegisterDTO.setStaffSection(staffRegisterEntity.getStaffSection());
                    staffRegisterDTO.setRegisterDate(staffRegisterEntity.getRegisterDate());
                    staffRegisterDTO.setRegisterDateTime(DateUtils.format(staffRegisterEntity.getRegisterDateTime(),"yy-MM-dd HH:mm"));
                    staffRegisterDTO.setStaffRegisterStatus(staffRegisterEntity.getStaffRegisterStatus());

                    staffRegisterDTOList.add(staffRegisterDTO);
                } else {
                    StaffRegisterDTO staffRegisterDTO = new StaffRegisterDTO();

//                    staffRegisterDTO.setStaffId(staffId);
//
//                    staffRegisterDTO.setRegisterDate(d);
//
//                    staffRegisterDTO.setStaffRegisterStatus("0");
                    staffRegisterDTO.setStaffId("");
                    staffRegisterDTO.setStaffName("");
                    staffRegisterDTO.setSectionId("");
                    staffRegisterDTO.setStaffSection("");
                    staffRegisterDTO.setRegisterDate(d);
                    staffRegisterDTO.setRegisterDateTime("");
                    staffRegisterDTO.setStaffRegisterStatus("0");


                    staffRegisterDTOList.add(staffRegisterDTO);
                }
                if (d.equals(staffCreateOn)){
//                    return ErrorResource.getError("tip_RE_DateError");//已经到签到第一天
//                    if(i==page.getFirstResult()*page.getMaxResult()) {
//                        return new SuccessApiResult(SuccessResource.getResource("tip_RE_DateError"), null);
//                    }else {
//                        break;
//                    }
                    break;
                }

            }
            return new SuccessApiResult(staffRegisterDTOList);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取签到人员的所有部门(员工端)
     * createBy：liudongxin
     * @param projectId
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult department(String projectId) throws GeneralException {
        if(StringUtil.isEmpty(projectId)){
            return ErrorResource.getError("tip_pe00000020");//项目id不能为空
        }
        try{
            List<StaffRegisterEntity> registers=staffRegisterRepository.getDepartments(projectId);
            if(registers.size()==0){
                return ErrorResource.getError("tip_Directory_SectionNull");//项目下部门为空
            }
            //封装到通讯录dto
            WorkOrderTelBookDTO telBook=new WorkOrderTelBookDTO();
            //部门集合
            List<WorkOrderDepartmentDTO> departmentList=new ArrayList<WorkOrderDepartmentDTO>();
            //部门dto
            WorkOrderDepartmentDTO department=new WorkOrderDepartmentDTO();
            //人员集合
            List<WorkOrderUserDTO> userList=new ArrayList<WorkOrderUserDTO>();
            String today= DateUtils.format(new Date(), "yyyy-MM-dd");//当前日期
            for(StaffRegisterEntity register:registers){
                List<StaffRegisterEntity> registerEntities =staffRegisterRepository.getRegister(register.getStaffSectionId(),today);
                if(registerEntities.size()>0){
                    for(StaffRegisterEntity staff:registerEntities) {
                        WorkOrderUserDTO workOrderUserDTO = new WorkOrderUserDTO();
                        workOrderUserDTO.setUserId(staff.getStaffId());//用户id
                        workOrderUserDTO.setName(staff.getStaffName());//用户姓名
                        workOrderUserDTO.setMobile(staff.getMobile());//用户电话
                        userList.add(workOrderUserDTO);
                    }
                }
                department.setGroupName(register.getStaffSection());//部门名称
            }
            department.setMemberList(userList);
            departmentList.add(department);
            telBook.setGroupList(departmentList);
            return new SuccessApiResult(telBook);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取部门下当天所有签到人员(管理端)
     * createBy：liudongxin
     * @param sectionId
     * @return
     * @throws GeneralException
     */
    @Override
    public List<WorkApportionDTO> registers(String sectionId){
        List<WorkApportionDTO> apportion=new ArrayList<WorkApportionDTO>();
        if(sectionId!=null) {
            String today= DateUtils.format(new Date(), "yyyy-MM-dd");//当前日期
            List<StaffRegisterEntity> registerList = staffRegisterRepository.getRegister(sectionId,today);
            if (registerList.size() > 0) {
                for(StaffRegisterEntity register:registerList){
                    WorkApportionDTO work=new WorkApportionDTO();
                    work.setUserId(register.getStaffId());
                    work.setUserName(register.getStaffName());
                    apportion.add(work);
                }
            }
        }
        return apportion;
    }

    /**
     * 测试专用
     * @param page
     * @param count
     * @return
     */
    public int UnRegister(int page, int count){
        for (int i = (page-1)*count;i<page*count;i++){
            Date date = DateUtils.addDay(new Date(),-i);
            String d = DateUtils.format(date);
            System.out.println(d);
        }
        return 0;
    }

    /*测试专用
    public static void main(String[] args) {
        StaffRegisterServiceImpl staffRegisterService = new StaffRegisterServiceImpl();

    }
    */

}
