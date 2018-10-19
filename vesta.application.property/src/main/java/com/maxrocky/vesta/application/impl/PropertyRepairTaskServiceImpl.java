package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.application.DTO.adminDTO.MessageInsertDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liudongxin on 2016/1/22.
 * 物业报修任务方法实现
 */
@Service
public class PropertyRepairTaskServiceImpl implements PropertyRepairTaskService {
    @Autowired
    private PropertyRepairTaskRepository propertyRepairTaskRepository;
    @Autowired
    private PropertyRepairRepository propertyRepairRepository;
    @Autowired
    private PropertyImageRepository propertyImageRepository;
    @Autowired
    private StaffRegisterRepository staffRegisterRepository;//app人员签到
    /* 登录信息服务 */
    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    private RoleAnthorityRepository roleAnthorityRepository;//app人员部门
    @Autowired
    private AppRoleSetRepository appRoleSetRepository;//app人员角色
    @Autowired
    private MessageInsertService messageInsertService;//消息推送
    @Autowired
    private OperationLogService operationLogService;//管理端操作日志
    @Autowired
    private HouseInfoRepository houseInfoRepository;//房屋业主地址
    @Autowired
    private PropertyRepairCRMRepository propertyRepairCRMRepository;
    @Autowired
    private RepairClientService repairClientService;
    @Autowired
    private ClickUserRepository clickUserRepository;

    @Autowired
    SystemLogRepository systemLogRepository;

    /* 用户信息服务 */
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserSettingService userSettingService;



    /**---------------------------------业主端部分-------------------------------------*/
    /**
     * 获取报修进展
     * @param id ：保修单id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getTaskProgress(String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        try{
            //查询报修所需要的详情信息
            PropertyRepairEntity propertyRepairEntity=propertyRepairRepository.getRepairDetail(id);
            PropertyProgressInfoDTO progressInfo=new PropertyProgressInfoDTO();
            if (propertyRepairEntity!=null){
                progressInfo.setStatus(propertyRepairEntity.getState());
                progressInfo.setTaskStatus(propertyRepairEntity.getTaskStatus());
                List<PropertyProgressDTO> progressList=new ArrayList<PropertyProgressDTO>();
                List<PropertyContentDTO> propertyContentOne=new ArrayList<PropertyContentDTO>();
                List<PropertyContentDTO> propertyContentTwo=new ArrayList<PropertyContentDTO>();
                List<PropertyContentDTO> propertyContentThree=new ArrayList<PropertyContentDTO>();
                List<PropertyContentDTO> propertyContentFour=new ArrayList<PropertyContentDTO>();
                List<PropertyContentDTO> propertyContentFive=new ArrayList<PropertyContentDTO>();
                List<PropertyContentDTO> propertyContentSix=new ArrayList<PropertyContentDTO>();
                List<PropertyContentDTO> propertyContentSeven=new ArrayList<PropertyContentDTO>();

                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//如果为维修工单，则展示业主回访环节;如果不是，则取消展示
                        //业主回访信息
                        List<PropertyRepairTaskEntity> taskOneEntity=propertyRepairTaskRepository.getTaskDateFive(propertyRepairEntity.getRepairId());
                        if(taskOneEntity.size()>0){
                            PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                            propertyProgress.setTaskName(taskOneEntity.get(0).getTaskName());//任务名称
                            propertyProgress.setTaskDate(DateUtils.format(taskOneEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                            propertyProgress.setTaskTime(DateUtils.format(taskOneEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                            //任务内容
                            PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                            propertyContentDTO.setTaskContent(taskOneEntity.get(0).getTaskContent());//任务内容
                            propertyContentOne.add(propertyContentDTO);
                            propertyProgress.setTaskContentList(propertyContentOne);//封装内容
                            progressList.add(propertyProgress);
                            progressInfo.setProgressList(progressList);//封装所有任务信息
                        }
                    }
                }

                //报修关闭信息
                List<PropertyRepairTaskEntity> taskSevenEntity=propertyRepairTaskRepository.getTaskDateSeven(propertyRepairEntity.getRepairId());
                if(taskSevenEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskSevenEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskSevenEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                    propertyProgress.setTaskTime(DateUtils.format(taskSevenEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                    //任务内容
                    PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                    propertyContentDTO.setTaskContent(taskSevenEntity.get(0).getTaskContent());//任务内容
                    propertyContentSeven.add(propertyContentDTO);
                    propertyProgress.setTaskContentList(propertyContentSeven);//封装内容
                    progressList.add(propertyProgress);
                    progressInfo.setProgressList(progressList);//封装所有任务信息
                }

                //维修完成信息
                List<PropertyRepairTaskEntity> taskTwoEntity=propertyRepairTaskRepository.getTaskDateFour(propertyRepairEntity.getRepairId());
                if(taskTwoEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskTwoEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskTwoEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                    propertyProgress.setTaskTime(DateUtils.format(taskTwoEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                    //任务内容
                    PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                    propertyContentDTO.setTaskContent(taskTwoEntity.get(0).getTaskContent());//任务内容
                    propertyContentTwo.add(propertyContentDTO);
                    propertyProgress.setTaskContentList(propertyContentTwo);//封装内容
                    progressList.add(propertyProgress);
                    progressInfo.setProgressList(progressList);//封装所有任务信息
                }

                //维修进展信息
                List<PropertyRepairTaskEntity> taskThreeEntity=propertyRepairTaskRepository.getTaskDateThree(propertyRepairEntity.getRepairId());
                if(taskThreeEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    //for(PropertyRepairTaskEntity taskThree:taskThreeEntity){
                        propertyProgress.setTaskName(taskThreeEntity.get(0).getTaskName());//任务名称
                        propertyProgress.setTaskDate(DateUtils.format(taskThreeEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                        propertyProgress.setTaskTime(DateUtils.format(taskThreeEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                        //任务内容
                        //PropertyRepairTaskEntity taskContent=propertyRepairTaskRepository.getTaskInfo(taskThree.getTaskId());
                        PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                        propertyContentDTO.setTaskContent(taskThreeEntity.get(0).getTaskContent());//任务内容
                        propertyContentThree.add(propertyContentDTO);
                    //}
                    propertyProgress.setTaskContentList(propertyContentThree);//封装内容
                    progressList.add(propertyProgress);
                    progressInfo.setProgressList(progressList);//封装所有任务信息
                }

                //派工信息
                /*List<PropertyRepairTaskEntity> taskFourEntity=propertyRepairTaskRepository.getTaskDateTwo(propertyRepairEntity.getRepairId());
                if(taskFourEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskFourEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskFourEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                    propertyProgress.setTaskTime(DateUtils.format(taskFourEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                    //通过维修人员id,获取用户头像
                    String taskUserId = taskFourEntity.get(0).getTaskUserId();
                    UserPropertyStaffEntity  userStaff = null;
                    if (null != taskUserId && !"".equals(taskUserId)){
                        userStaff = userPropertyStaffRepository.get(taskUserId);
                    }
                    if (null != userStaff){
                        if(userStaff.getLogo()!=null && !"".equals(userStaff.getLogo())) {
                            propertyProgress.setImageUrl(userStaff.getLogo());//用户头像
                            propertyProgress.setSrc(userStaff.getLogo());//用户头像
                        }else{
                            propertyProgress.setImageUrl("http://lifestatic.wanda.cn/frontimg/images/interface/user/head.png");//默认用户头像
                            propertyProgress.setSrc("http://lifestatic.wanda.cn/frontimg/images/interface/user/head.png");//默认用户头像
                        }
                    }
                    //任务内容
                    PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                    propertyContentDTO.setTaskContent(taskFourEntity.get(0).getTaskContent());//任务内容
                    propertyContentFour.add(propertyContentDTO);
                    propertyProgress.setTaskContentList(propertyContentFour);//封装内容
                    progressList.add(propertyProgress);
                    progressInfo.setProgressList(progressList);//封装所有任务信息
                }*/

                //报修受理信息
                List<PropertyRepairTaskEntity> taskSixEntity=propertyRepairTaskRepository.getTaskDateSix(propertyRepairEntity.getRepairId());
                if(taskSixEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskSixEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskSixEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                    propertyProgress.setTaskTime(DateUtils.format(taskSixEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                    //任务内容
                    PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                    propertyContentDTO.setTaskContent(taskSixEntity.get(0).getTaskContent());//任务内容
                    propertyContentSix.add(propertyContentDTO);
                    propertyProgress.setTaskContentList(propertyContentSix);//封装内容
                    progressList.add(propertyProgress);
                    progressInfo.setProgressList(progressList);//封装所有任务信息
                }

                //提交报修信息
                List<PropertyRepairTaskEntity> taskFiveEntity=propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
                if(taskFiveEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskFiveEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskFiveEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                    propertyProgress.setTaskTime(DateUtils.format(taskFiveEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                    //任务内容
                    PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                    propertyContentDTO.setTaskContent(taskFiveEntity.get(0).getTaskContent());//任务内容
                    propertyContentFive.add(propertyContentDTO);
                    propertyProgress.setTaskContentList(propertyContentFive);//封装内容
                    progressList.add(propertyProgress);
                    progressInfo.setProgressList(progressList);//封装所有任务信息
                }
                /*if(taskFourEntity.size()>0) {
                    //通过维修人员id,获取用户头像
                    String taskUserId = taskFourEntity.get(0).getTaskUserId();
                    UserPropertyStaffEntity  userStaff = null;
                    if (null != taskUserId && !"".equals(taskUserId)){
                        userStaff = userPropertyStaffRepository.get(taskUserId);
                    }
                    //UserPropertyStaffEntity userStaff = userPropertyStaffRepository.get(taskFourEntity.get(0).getTaskUserId());
                    if(userStaff!=null) {
                        progressInfo.setUserId(userStaff.getStaffId());//维修人员id
                        if (userStaff.getLogo()!= null && !"".equals(userStaff.getLogo())) {
                            progressInfo.setImageUrl(userStaff.getLogo());//用户头像
                            progressInfo.setSrc(userStaff.getLogo());//用户头像
                        } else {
                            progressInfo.setImageUrl("http://lifestatic.wanda.cn/frontimg/images/interface/user/head.png");//默认用户头像
                            progressInfo.setSrc("http://lifestatic.wanda.cn/frontimg/images/interface/user/head.png");//默认用户头像
                        }
                    }
                }*/
            }

            List<PropertyProgressDTO> progressList = progressInfo.getProgressList();
            if (null != progressList && progressList.size() > 0){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //已受理的时间节点
                String dateTime_1 = "";
                for (PropertyProgressDTO progressDTO : progressList){
                    String taskName = progressDTO.getTaskName();
                    if (taskName.equals("已受理")){
                        //状态:已受理
                        dateTime_1 += progressDTO.getTaskDate();
                        dateTime_1 += " "+progressDTO.getTaskTime();
                    }
                }
                if (!dateTime_1.equals("")){
                    for (PropertyProgressDTO progressDTO : progressList){
                        String taskName = progressDTO.getTaskName();
                        if (taskName.equals("处理中")){
                            //状态:处理中
                            String dateTime_2 = progressDTO.getTaskDate() + " " + progressDTO.getTaskTime();
                            if (dateFormat.parse(dateTime_1).getTime() > dateFormat.parse(dateTime_2).getTime()){
                                progressList.remove(progressDTO);
                                break;
                            }
                        }
                    }
                }
            }
            return new SuccessApiResult(progressInfo);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 继续报修：转变成新的报修单，之前的某些内容不变
     * @param user ：用户
     * @param id   ：保修单id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult repairContinue(UserInfoEntity user, String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getUserId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairInfo!=null){
                if(propertyRepairInfo.getTypes().equals("1")){
                    return ErrorResource.getError("tip_pe00000007");//已终止维修，不可再继续报修
                }
                //修改报修单：除了新的单号和时间，其他的都是之前的信息
                propertyRepairInfo.setModifyBy(propertyRepairInfo.getCreateBy());//修改人
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                //propertyRepairEntity.setTypes("0");//0代表未完成；1代表已完成
                propertyRepairInfo.setState("正在派工");
                //报修任务表
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setTaskStatus("0");//0为用户提交维修(业主)
                        propertyRepairTaskEntity.setTaskName("提交报修");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("0");//0为用户提交报修(业主)
                        propertyRepairTaskEntity.setTaskContent("您的报修信息已成功提交。");//任务内容
                    }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("1");//1为用户提交投诉(业主)
                        propertyRepairTaskEntity.setTaskName("提交投诉");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("1");//1为用户提交投诉(业主)
                        propertyRepairTaskEntity.setTaskContent("您的投诉信息已成功提交。");//任务内容
                    }
                }
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                //添加任务：新的任务
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskNode("继续报修");//任务节点
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                //修改报修图时间和报修id
                List<PropertyImageEntity> imageInfoList=propertyImageRepository.getImageUrl(propertyRepairInfo.getRepairId());
                for(PropertyImageEntity imageInfo:imageInfoList){
                    imageInfo.setModifyDate(DateUtils.getDate());
                    propertyImageRepository.updateImage(imageInfo);
                }
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);//操作成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 停止报修(业主端)
     * @param id ：保修单id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult repairStop(UserInfoEntity user,String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getUserId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            PropertyRepairEntity propertyRepairEntity=  propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairEntity!=null){
                if(propertyRepairEntity.getTypes().equals("1")){
                    return ErrorResource.getError("tip_pe00000006");//已是终止状态，不可重复提交
                }
                propertyRepairEntity.setModifyBy(user.getNickName());
                propertyRepairEntity.setModifyDate(DateUtils.getDate());
                propertyRepairEntity.setTypes("1");//已完成报修
                propertyRepairEntity.setState("已完成");
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());
                propertyRepairTaskEntity.setRepairId(propertyRepairEntity.getRepairId());
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairEntity.setTaskStatus("14");//14为终止维修(业主)
                        propertyRepairTaskEntity.setTaskName("评价/回访");
                        propertyRepairTaskEntity.setTaskNode("终止报修");
                        propertyRepairTaskEntity.setTaskStatus("14");//14为终止维修(业主)
                        propertyRepairTaskEntity.setTaskContent("已与业主沟通，维修工作已完成。");
                    }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairEntity.setTaskStatus("17");//17为终止处理(业主)
                        propertyRepairTaskEntity.setTaskName("投诉解决");
                        propertyRepairTaskEntity.setTaskNode("终止投诉");
                        propertyRepairTaskEntity.setTaskStatus("17");//17为终止处理(业主)
                        propertyRepairTaskEntity.setTaskContent("本次投诉已解决。");
                    }
                }
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());
                propertyRepairRepository.updateRepair(propertyRepairEntity);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**---------------------------------员工端部分-------------------------------------*/
    /**
     * 工单抢单
     * @param user ：用户
     * @param workApportionDTO  ：保修单id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult repairScramble(UserPropertyStaffEntity user,WorkApportionDTO workApportionDTO) throws GeneralException {
        if(StringUtil.isEmpty(workApportionDTO.getClassifyOne())){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(workApportionDTO.getId());
            if (propertyRepairInfo!=null && "已受理".equals(propertyRepairInfo.getState())){
                //通过报修单id，获取维修人
//                PropertyRepairTaskEntity propertyRepairTask=propertyRepairTaskRepository.getTaskUserId(propertyRepairInfo.getRepairId());
//                if(!StringUtil.isEmpty(propertyRepairTask.getTaskUserId())){
//                    return ErrorResource.getError("tip_pe00000013");//接单已成功，不可重复提交
//                }
                //修改报修单
//                return ErrorResource.getError("tip_pe00000035");
                propertyRepairInfo.setUserId(user.getStaffId());
                propertyRepairInfo.setState("处理中");//状态由正在派工改为处理中
                propertyRepairInfo.setModifyBy(user.getStaffId());
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskNode("抢单成功");//任务节点
                propertyRepairTaskEntity.setTaskUserId(user.getStaffId());//工单任务人
                propertyRepairTaskEntity.setTaskUserPhone(user.getMobile());//任务人电话
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                propertyRepairTaskEntity.setReadStatus("0");
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setTaskStatus("4");//4为维修人员接单
                        propertyRepairTaskEntity.setTaskName("处理中");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("4");//4为维修人员接单
                        propertyRepairTaskEntity.setTaskUserType("0");//0为维修人员;1为客服人员
                        propertyRepairTaskEntity.setTaskContent("维修专员" + user.getStaffName()+ " " + user.getMobile() + "将与您联系。");//任务内容
                    }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("2");//2为客服接单
                        propertyRepairTaskEntity.setTaskName("处理中");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("2");//2为客服接单
                        propertyRepairTaskEntity.setTaskUserType("1");//0为维修人员;1为客服人员
                        propertyRepairTaskEntity.setTaskContent("客服专员" + user.getStaffName()+ " " + user.getMobile() + "将与您联系。");//任务内容
                    }
                }else{
                    propertyRepairInfo.setTaskStatus("4");//4为维修人员接单
                    propertyRepairTaskEntity.setTaskName("处理中");//任务名称
                    propertyRepairTaskEntity.setTaskStatus("4");//4为维修人员接单
                    propertyRepairTaskEntity.setTaskUserType("0");//0为维修人员;1为客服人员
                    propertyRepairTaskEntity.setTaskContent("维修专员" + user.getStaffName()+ " " + user.getMobile() + "将与您联系。");//任务内容
                }
                PropertyRepairCRMEntity propertyRepairCRMEntity=propertyRepairCRMRepository.getById(propertyRepairInfo.getRepairId());
                if(propertyRepairCRMEntity.getDealPeople()!=null && !"".equals(propertyRepairCRMEntity.getDealPeople())){
                    if(!propertyRepairCRMEntity.getDealPeople().equals(user.getStaffId())){
                        return ErrorResource.getError("tip_pe00000035");
                    }
                }
                propertyRepairCRMEntity.setRepairId(propertyRepairInfo.getRepairId());
                propertyRepairCRMEntity.setRoomId(propertyRepairInfo.getRoomId());
                HouseInfoEntity house=houseInfoRepository.getHouseByRoomId(propertyRepairInfo.getRoomId());
                if(house!=null) {
                    propertyRepairCRMEntity.setRoomNum(house.getRoomNum());
                    propertyRepairCRMEntity.setMemberId(house.getMemberId());
                }
                propertyRepairCRMEntity.setUserName(propertyRepairInfo.getUserName());
                //propertyRepairCRMEntity.setDealPeople(user.getUserName());
                propertyRepairCRMEntity.setUserPhone(propertyRepairInfo.getUserPhone());
                if(workApportionDTO.getDealMode()!=null){
                    propertyRepairCRMEntity.setDealMode(workApportionDTO.getDealMode());
                }
                propertyRepairCRMEntity.setUserAddress(propertyRepairInfo.getUserAddress());
                propertyRepairCRMEntity.setCreateDate(propertyRepairInfo.getCreateDate());
                propertyRepairCRMEntity.setState("processing");
                if(workApportionDTO.getClassifyOne()!=null){
                    propertyRepairCRMEntity.setClassifyOne(workApportionDTO.getClassifyOne());
                }
                if(workApportionDTO.getClassifyTwo()!=null){
                    propertyRepairCRMEntity.setClassifyTwo(workApportionDTO.getClassifyTwo());
                }
                if(workApportionDTO.getClassifyThree()!=null){
                    propertyRepairCRMEntity.setClassifyThree(workApportionDTO.getClassifyThree());
                }
                if(workApportionDTO.getMode()!=null){
                    propertyRepairCRMEntity.setMode(workApportionDTO.getMode());
                }
                if(workApportionDTO.getDutyCompanyOne()!=null){
                    propertyRepairCRMEntity.setDutyCompanyOne(workApportionDTO.getDutyCompanyOne());
                }
                if(workApportionDTO.getDutyCompanyTwo()!=null){
                    propertyRepairCRMEntity.setDutyCompanyTwo(workApportionDTO.getDutyCompanyTwo());
                }
                if(workApportionDTO.getDutyCompanyThree()!=null){
                    propertyRepairCRMEntity.setDutyCompanyThree(workApportionDTO.getDutyCompanyThree());
                }
                if(workApportionDTO.getRepairDate()!=null){
                    propertyRepairCRMEntity.setRepairDate(workApportionDTO.getRepairDate());
                }
                propertyRepairCRMEntity.setDealPeople(propertyRepairTaskEntity.getTaskUserId());
                propertyRepairCRMEntity.setRepairManager(user.getUserName());
                propertyRepairCRMEntity.setDealPhone(propertyRepairTaskEntity.getTaskUserPhone());
                propertyRepairCRMEntity.setTaskDate(propertyRepairTaskEntity.getTaskDate());
                propertyRepairCRMRepository.update(propertyRepairCRMEntity);
                //调用webService接口,向CRM传送数据
                new Thread() {
                    public void run() {
                        repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
                    }
                }.start();
//                repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), "1");
            }else{
                return ErrorResource.getError("tip_pe00000035");
//                return new SuccessApiResult(SuccessResource.getResource("tip_pe00000035"), null);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 工单继续
     * @param user ：用户
     * @param id   ：保修单id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult orderContinue(UserPropertyStaffEntity user, String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairInfo!=null){
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                //propertyRepairTaskEntity.setTaskName("维修进展");//任务名称
                propertyRepairTaskEntity.setTaskNode("继续维修");
                propertyRepairTaskEntity.setTaskUserId(user.getStaffId());//工单任务人
                propertyRepairTaskEntity.setTaskContent("维修进行中...");//任务内容
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setTaskStatus("7");//7为维修中
                        propertyRepairTaskEntity.setTaskStatus("7");//7为维修中
                    }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("5");//5为处理中
                        propertyRepairTaskEntity.setTaskStatus("5");//5为处理中
                    }
                }
                propertyRepairInfo.setModifyBy(user.getStaffId());
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 工单派单
     * @param user
     * @param workOrderTelBookDTO
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult repairsApportion(UserPropertyStaffEntity user, WorkOrderTelBookDTO workOrderTelBookDTO) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        if(StringUtil.isEmpty(workOrderTelBookDTO.getApportionList().get(0).getId())){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if(StringUtil.isEmpty(workOrderTelBookDTO.getUserId())){
            return ErrorResource.getError("tip_pe00000015");//接单人为空
        }
        try{
            if(workOrderTelBookDTO.getApportionList().size()>0) {
                for (WorkApportionDTO apportion : workOrderTelBookDTO.getApportionList()) {
                    //查看报修信息
                    PropertyRepairEntity propertyRepairInfo = propertyRepairRepository.getRepairDetail(apportion.getId());
                    if (propertyRepairInfo != null) {
                        //修改报修单
                        propertyRepairInfo.setState("正在处理");//状态由正在派工改为正在处理
                        propertyRepairInfo.setModifyBy(user.getStaffId());
                        propertyRepairInfo.setModifyDate(DateUtils.getDate());
                        //添加任务：新的任务
                        PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                        propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                        propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                        propertyRepairTaskEntity.setTaskNode("接单成功");//任务节点
                        propertyRepairTaskEntity.setTaskManagerId(user.getStaffId());//管理人
                        propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                        //获取维修人员的姓名和电话
                        UserPropertyStaffEntity userStaff=userPropertyStaffRepository.get(workOrderTelBookDTO.getUserId());
                        if(userStaff==null && userStaff.getStaffId()==null){
                            return ErrorResource.getError("tip_pe00000022");//接单人不存在
                        }
                        //获取接单人角色
                        RoleRoleanthorityEntity role = roleAnthorityRepository.getAnthorityByStaffId(userStaff.getStaffId());
                        if(role == null && role.getAppSetId()==null && "".equals(role.getAppSetId())){
                            return ErrorResource.getError("tip_pe00000023");//接单人未分配角色
                        }
                        AppRolesetEntity appRoleset = appRoleSetRepository.getAppRolesetById(role.getAppSetId());
                        if (propertyRepairInfo.getTaskStatus().equals("10")) {//如果为10，则是维修未评价工单
                            if (appRoleset != null) {
                                if (appRoleset.getAppSetId().equals("1") || appRoleset.getAppSetId().equals("2")) {
                                    return ErrorResource.getError("tip_pe00000014");//接单人为维修人员
                                }
                            }
                            propertyRepairInfo.setTaskStatus("12");//12为待客服回访
                            propertyRepairInfo.setCustomerId(workOrderTelBookDTO.getUserId());
                            propertyRepairTaskEntity.setTaskName("客服回访");//任务名称
                            propertyRepairTaskEntity.setTaskStatus("12");
                            propertyRepairTaskEntity.setTaskUserType("1");//0为维修人员;1为客服人员
                            propertyRepairTaskEntity.setTaskUserId(workOrderTelBookDTO.getUserId());//接单人
                        }else if(propertyRepairInfo.getTaskStatus().equals("0")) {//如果为0,则是维修待分配的工单
                            if (appRoleset != null) {
                                if (appRoleset.getAppSetId().equals("3")
                                        || appRoleset.getAppSetId().equals("4")
                                        || appRoleset.getAppSetId().equals("5")
                                        || appRoleset.getAppSetId().equals("6")
                                        || appRoleset.getAppSetId().equals("7")
                                        || appRoleset.getAppSetId().equals("9")) {
                                    return ErrorResource.getError("tip_pe00000017");//接单人非维修人员
                                }
                            }
                            propertyRepairInfo.setTaskStatus("4");//4为维修人员接单
                            propertyRepairInfo.setUserId(workOrderTelBookDTO.getUserId());
                            propertyRepairTaskEntity.setTaskName("派工信息");//任务名称
                            propertyRepairTaskEntity.setTaskStatus("4");//4为维修人员接单
                            propertyRepairTaskEntity.setTaskUserType("0");//0为维修人员;1为客服人员
                            propertyRepairTaskEntity.setTaskUserId(workOrderTelBookDTO.getUserId());//接单人
                            propertyRepairTaskEntity.setTaskContent("维修专员" + userStaff.getStaffName() + " " + userStaff.getMobile() + "将与您联系。");//任务内容
                        }else if(propertyRepairInfo.getTaskStatus().equals("1")){//如果为1，则是投诉待分配工单
                            if(appRoleset!=null) {
                                if (appRoleset.getAppSetId().equals("1") || appRoleset.getAppSetId().equals("2")) {
                                    return ErrorResource.getError("tip_pe00000014");//接单人为维修人员
                                }
                            }
                            propertyRepairInfo.setTaskStatus("2");//2为客服接单
                            propertyRepairInfo.setUserId(workOrderTelBookDTO.getUserId());
                            propertyRepairTaskEntity.setTaskName("受理投诉");//任务名称
                            propertyRepairTaskEntity.setTaskStatus("2");//2为客服接单
                            propertyRepairTaskEntity.setTaskUserType("1");//0为维修人员;1为客服人员
                            propertyRepairTaskEntity.setTaskUserId(workOrderTelBookDTO.getUserId());//接单人
                            propertyRepairTaskEntity.setTaskContent("客服专员" + userStaff.getStaffName()+ " " + userStaff.getMobile() + "将与您联系。");//任务内容
                        }
                        propertyRepairRepository.updateRepair(propertyRepairInfo);
                        propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                        if(propertyRepairInfo.getMemo().equals("报修")) {
                            //调用消息推送：业主推送
                            MessageInsertDTO messageInsert = new MessageInsertDTO();
                            messageInsert.setMessageTitle(apportion.getId());
                            messageInsert.setMessageUserType("1");//1为业主;2为员工
                            messageInsert.setMessageUrl(apportion.getId());
                            messageInsert.setMessageType("2");//业主类型
                            messageInsert.setMessageTypeState("2");//派工状态
                            List<String> users = new ArrayList<>();
                            users.add(propertyRepairInfo.getCreateBy());
                            messageInsertService.InsertMessage(messageInsert, users);
                            //调用消息推送：员工推送
                            MessageInsertDTO message = new MessageInsertDTO();
                            message.setMessageTitle(apportion.getId());
                            message.setMessageUserType("2");//1为业主;2为员工
                            message.setMessageUrl(apportion.getId());
                            message.setMessageType("5");//员工类型
                            message.setMessageTypeState("1");//派工状态
                            List<String> userId = new ArrayList<>();
                            userId.add(workOrderTelBookDTO.getUserId());
                            messageInsertService.InsertMessage(message, userId);
                        }else if(propertyRepairInfo.getMemo().equals("投诉")){
                            //调用消息推送：业主推送
                            MessageInsertDTO messageInsert = new MessageInsertDTO();
                            messageInsert.setMessageTitle(apportion.getId());
                            messageInsert.setMessageUserType("1");//1为业主;2为员工
                            messageInsert.setMessageUrl(apportion.getId());
                            messageInsert.setMessageType("9");//业主类型
                            messageInsert.setMessageTypeState("2");//派工状态
                            List<String> users = new ArrayList<>();
                            users.add(propertyRepairInfo.getCreateBy());
                            messageInsertService.InsertMessage(messageInsert, users);
                            //调用消息推送：员工推送
                            MessageInsertDTO message = new MessageInsertDTO();
                            message.setMessageTitle(apportion.getId());
                            message.setMessageUserType("2");//1为业主;2为员工
                            message.setMessageUrl(apportion.getId());
                            message.setMessageType("8");//员工类型
                            message.setMessageTypeState("1");//派工状态
                            List<String> userId = new ArrayList<>();
                            userId.add(workOrderTelBookDTO.getUserId());
                            messageInsertService.InsertMessage(message, userId);
                        }
                    }
                }
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 工单退单
     * @param user
     * @param id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult chargeBack(UserPropertyStaffEntity user, String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairInfo!=null){
                //修改报修单：将正在处理改为正在派工
                propertyRepairInfo.setState("正在处理");//状态由正在派工改为正在处理
                propertyRepairInfo.setModifyBy(user.getStaffId());
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                //propertyRepairTaskEntity.setTaskName("维修进展");//任务名称
                propertyRepairTaskEntity.setTaskNode("退单");//任务节点
                propertyRepairTaskEntity.setTaskContent("转给其他同事处理。");//任务内容
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setTaskStatus("6");//6为维修退单
                        propertyRepairTaskEntity.setTaskStatus("6");//6为维修退单
                        propertyRepairTaskEntity.setTaskUserType("0");//0为维修人员;1为客服人员
                    }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("3");//3为客服退单
                        propertyRepairTaskEntity.setTaskStatus("3");//3为客服退单
                        propertyRepairTaskEntity.setTaskUserType("1");//0为维修人员;1为客服人员
                    }
                }
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 通过报修单id获取工单进展
     * @param id ：保修单id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getRepairsProgress(String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_00000055");
        }
        try{
            //获取任务进展
            //List<PropertyRepairTaskEntity>  taskList=propertyRepairTaskRepository.getTasksProgress(id);
            //任务信息
            WorkOrderTaskDTO workOrderTaskDTO = new WorkOrderTaskDTO();
            //workOrderTaskDTO.setTaskName(repairTask.getTaskName());//任务名称
            //提交报修/提交投诉任务时间
            List<PropertyRepairTaskEntity> repairOne=propertyRepairTaskRepository.getTaskDateOne(id);
            if(repairOne.size()>0){
                workOrderTaskDTO.setTaskDateOne(DateUtils.format(repairOne.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                workOrderTaskDTO.setTaskTimeOne(DateUtils.format(repairOne.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
            }
            //派工信息/受理投诉任务时间
            List<PropertyRepairTaskEntity> repairTwo=propertyRepairTaskRepository.getTaskDateTwo(id);
            if(repairTwo.size()>0){
                workOrderTaskDTO.setTaskDateTwo(DateUtils.format(repairTwo.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                workOrderTaskDTO.setTaskTimeTwo(DateUtils.format(repairTwo.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
            }
            //维修进展/投诉进展任务时间
            List<PropertyRepairTaskEntity> repairThree=propertyRepairTaskRepository.getTaskDateThree(id);
            if(repairThree.size()>0){
                workOrderTaskDTO.setTaskDateThree(DateUtils.format(repairThree.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                workOrderTaskDTO.setTaskTimeThree(DateUtils.format(repairThree.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
            }
            //维修完成/投诉解决任务时间
            List<PropertyRepairTaskEntity> repairFour=propertyRepairTaskRepository.getTaskDateFour(id);
            if (repairFour.size() > 0) {
                workOrderTaskDTO.setTaskDateFour(DateUtils.format(repairFour.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                workOrderTaskDTO.setTaskTimeFour(DateUtils.format(repairFour.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
            }
            //业主回访任务时间
            List<PropertyRepairTaskEntity> repairFive = propertyRepairTaskRepository.getTaskDateFive(id);
            if (repairFive.size() > 0) {
                workOrderTaskDTO.setTaskDateFive(DateUtils.format(repairFive.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                workOrderTaskDTO.setTaskTimeFive(DateUtils.format(repairFive.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
            }
            return new SuccessApiResult(workOrderTaskDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 通过报修单id获取工单进展内容
     * @param id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getTaskContent(String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_00000055");
        }
        try{
            //获取任务进展
            List<PropertyRepairTaskEntity>  taskList=propertyRepairTaskRepository.getTasksProgress(id);
            WorkTaskContentDTO workTaskContentDTO=new WorkTaskContentDTO();
            if (taskList.size()>0){
                List<WorkOrderContentDTO> contentList=new ArrayList<WorkOrderContentDTO>();
                if(taskList.size()>0) {
                    for (PropertyRepairTaskEntity task : taskList) {
                        //任务信息
                        WorkOrderContentDTO workOrderContentDTO = new WorkOrderContentDTO();
                        workOrderContentDTO.setTaskDate(DateUtils.format(task.getTaskDate(), "yy-MM-dd HH:mm"));//任务日期
                        workOrderContentDTO.setTaskContent(task.getTaskContent());//任务内容
                        contentList.add(workOrderContentDTO);
                    }
                    workTaskContentDTO.setTaskConentList(contentList);//封装所有任务信息
                }
            }
            return new SuccessApiResult(workTaskContentDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 添加进展任务内容
     * @param user
     * @param workApportionDTO
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult repairsContent(UserPropertyStaffEntity user, WorkApportionDTO workApportionDTO) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        if(StringUtil.isEmpty(workApportionDTO.getId())){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if(StringUtil.isEmpty(workApportionDTO.getTaskContent())){
            return ErrorResource.getError("tip_pe00000016");//进展内容为空
        }
        try{
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(workApportionDTO.getId());
            if (propertyRepairInfo!=null){
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskNode("正在处理中");//任务节点
                //propertyRepairTaskEntity.setTaskUserId(user.getStaffId());//接单人
                propertyRepairTaskEntity.setTaskContent(workApportionDTO.getTaskContent());//任务内容
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setTaskStatus("7");//7为维修中
                        propertyRepairTaskEntity.setTaskName("处理中");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("7");//7为维修中
                        propertyRepairTaskEntity.setTaskUserType("0");//任务用户类型：0为维修人员;1为客服人员
                    }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("5");//5为处理中
                        propertyRepairTaskEntity.setTaskName("处理中");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("5");//5为处理中
                        propertyRepairTaskEntity.setTaskUserType("1");//任务用户类型：0为维修人员;1为客服人员
                    }
                }
                propertyRepairInfo.setModifyBy(user.getStaffId());
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_ps00000001"), null);//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 提交抵达现场
     * @param user
     * @param id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult arriveLocale(UserPropertyStaffEntity user, String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairInfo!=null){
                /*List<PropertyRepairTaskEntity> repairTask=propertyRepairTaskRepository.getTaskProgress(propertyRepairInfo.getRepairId());
                if(repairTask.get(0).getTaskStatus().equals("7")){
                    return ErrorResource.getError("tip_pe00000017");//抵达现场，不可重复提交
                }*/
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskNode("抵达现场");//任务节点
                propertyRepairTaskEntity.setTaskContent("已抵达业主家中。");//任务内容
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setTaskStatus("7");//7为维修中
                        propertyRepairTaskEntity.setTaskName("维修进展");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("7");//7为维修中
                    } else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("5");//5为处理中
                        propertyRepairTaskEntity.setTaskName("投诉进展");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("5");//5为处理中
                    }
                }
                propertyRepairInfo.setModifyBy(user.getStaffId());
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 工单暂停
     * @param user
     * @param id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult repairPause(UserPropertyStaffEntity user, String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairInfo!=null){
                /*List<PropertyRepairTaskEntity> repairTask=propertyRepairTaskRepository.getTaskProgress(propertyRepairInfo.getRepairId());
                if(repairTask.get(0).getTaskStatus().equals("9")){
                    return ErrorResource.getError("tip_pe00000018");//已是暂停状态，不可重复提交
                }*/
                //修改报修单
                propertyRepairInfo.setState("正在处理");//状态由正在派工改为正在处理
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setTaskStatus("9");//9为暂停维修
                        propertyRepairTaskEntity.setTaskName("维修进展");//任务名称
                        propertyRepairTaskEntity.setTaskNode("暂停维修");//任务节点
                        propertyRepairTaskEntity.setTaskStatus("9");//9为暂停维修
                        propertyRepairTaskEntity.setTaskContent("维修进行中...");//任务内容
                    } else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("8");//8为暂停处理
                        propertyRepairTaskEntity.setTaskName("投诉进展");//任务名称
                        propertyRepairTaskEntity.setTaskNode("暂停处理");//任务节点
                        propertyRepairTaskEntity.setTaskStatus("8");//8为暂停处理
                        propertyRepairTaskEntity.setTaskContent("投诉进行中...");//任务内容
                    }
                }
                propertyRepairInfo.setModifyBy(user.getStaffId());
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 工单维修完成
     * @param user
     * @param id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult repairComplete(UserPropertyStaffEntity user, String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairInfo!=null){
                /*List<PropertyRepairTaskEntity> repairTask=propertyRepairTaskRepository.getTaskProgress(propertyRepairInfo.getRepairId());
                if(repairTask.get(0).getTaskStatus().equals("10")){
                    return ErrorResource.getError("tip_pe00000020");//已是完成状态，不可重复提交
                }*/
                //修改报修单:未完成改为未评价
                propertyRepairInfo.setState("未评价");//状态由正在处理改为未评价
                propertyRepairInfo.setModifyBy(user.getStaffId());
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                propertyRepairInfo.setCompleteDate(DateUtils.getDate());
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                if(repairTaskEntities.size()>0){
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setTaskStatus("10");//10为维修完成
                        propertyRepairTaskEntity.setTaskName("维修完成");//任务名称
                        propertyRepairTaskEntity.setTaskNode("维修完成");//任务节点
                        propertyRepairTaskEntity.setTaskStatus("10");//10为维修完成
                        propertyRepairTaskEntity.setTaskContent("本次维修工作已完成。");//任务内容
                    } else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("16");//16为回访完成
                        propertyRepairTaskEntity.setTaskName("投诉解决");//任务名称
                        propertyRepairTaskEntity.setTaskNode("投诉解决");//任务节点
                        propertyRepairTaskEntity.setTaskStatus("16");//16为回访完成
                        propertyRepairTaskEntity.setTaskContent("本次投诉已解决。");//任务内容
                    }
                }
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                if(propertyRepairInfo.getMemo().equals("报修")) {
                    //调用消息推送
                    MessageInsertDTO messageInsert = new MessageInsertDTO();
                    messageInsert.setMessageTitle(propertyRepairInfo.getRepairId());
                    messageInsert.setMessageUserType("1");//1为业主；2为员工
                    messageInsert.setMessageUrl(propertyRepairInfo.getRepairId());
                    messageInsert.setMessageType("2");//业主类型
                    messageInsert.setMessageTypeState("4");//完成状态
                    List<String> users = new ArrayList<>();
                    users.add(propertyRepairInfo.getCreateBy());
                    messageInsertService.InsertMessage(messageInsert, users);
                }else if(propertyRepairInfo.getMemo().equals("投诉")){
                    //调用消息推送
                    MessageInsertDTO messageInsert = new MessageInsertDTO();
                    messageInsert.setMessageTitle(propertyRepairInfo.getRepairId());
                    messageInsert.setMessageUserType("1");//1为业主；2为员工
                    messageInsert.setMessageUrl(propertyRepairInfo.getRepairId());
                    messageInsert.setMessageType("9");//业主类型
                    messageInsert.setMessageTypeState("4");//完成状态
                    List<String> users = new ArrayList<>();
                    users.add(propertyRepairInfo.getCreateBy());
                    messageInsertService.InsertMessage(messageInsert, users);
                }
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 工单回访完成
     * @param user
     * @param id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult repairVisiting(UserPropertyStaffEntity user, String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairInfo!=null){
                /*if(propertyRepairInfo.getTypes().equals("13")){
                    return ErrorResource.getError("tip_pe00000020");//已是完成状态，不可重复提交
                }*/
                //修改报修单
                propertyRepairInfo.setModifyBy(user.getStaffId());
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                propertyRepairInfo.setTypes("1");
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskName("评价/回访");//任务名称
                propertyRepairTaskEntity.setTaskNode("回访完成");//任务节点
                propertyRepairTaskEntity.setTaskUserType("1");//0为维修人员;1为客服人员
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                if(repairTaskEntities.size()>0){
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setState("已完成");//状态由正在处理改为已评价
                        propertyRepairInfo.setTaskStatus("13");//13为回访完成(结束)
                        propertyRepairTaskEntity.setTaskStatus("13");//13为回访完成(结束)
                        propertyRepairTaskEntity.setTaskContent("已与业主沟通，维修工作已完成。");//任务内容
                    } else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setState("未评价");//状态由正在处理改为未评价
                        propertyRepairInfo.setTaskStatus("16");//16为回访完成(结束：可评价，也可以不评价)
                        propertyRepairTaskEntity.setTaskName("投诉解决");//任务名称
                        propertyRepairTaskEntity.setTaskNode("投诉解决");//任务节点
                        propertyRepairTaskEntity.setTaskStatus("16");//16为回访完成(结束：可评价，也可以不评价)
                        propertyRepairTaskEntity.setTaskContent("本次投诉已解决。");//任务内容
                    }
                }
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 随手报：获取报修进展
     * @param id ：保修单id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult reportsProgress(String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        try{
            //查询报修所需要的详情信息
            PropertyRepairEntity propertyRepairEntity=propertyRepairRepository.getRepairDetail(id);
            PropertyProgressInfoDTO progressInfo=new PropertyProgressInfoDTO();
            if (propertyRepairEntity!=null){
                progressInfo.setStatus(propertyRepairEntity.getState());
                progressInfo.setTaskStatus(propertyRepairEntity.getTaskStatus());
                List<PropertyProgressDTO> progressList=new ArrayList<PropertyProgressDTO>();
                List<PropertyContentDTO> propertyContentOne=new ArrayList<PropertyContentDTO>();
                List<PropertyContentDTO> propertyContentTwo=new ArrayList<PropertyContentDTO>();
                List<PropertyContentDTO> propertyContentThree=new ArrayList<PropertyContentDTO>();
                List<PropertyContentDTO> propertyContentFour=new ArrayList<PropertyContentDTO>();
                List<PropertyContentDTO> propertyContentFive=new ArrayList<PropertyContentDTO>();

                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//如果为维修工单，则展示业主回访环节;如果不是，则取消展示
                        //业主回访信息
                        List<PropertyRepairTaskEntity> taskOneEntity=propertyRepairTaskRepository.getTaskDateFive(propertyRepairEntity.getRepairId());
                        if(taskOneEntity.size()>0){
                            PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                            propertyProgress.setTaskName(taskOneEntity.get(0).getTaskName());//任务名称
                            propertyProgress.setTaskDate(DateUtils.format(taskOneEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                            propertyProgress.setTaskTime(DateUtils.format(taskOneEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                            //任务内容
                            PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                            propertyContentDTO.setTaskContent(taskOneEntity.get(0).getTaskContent());//任务内容
                            propertyContentOne.add(propertyContentDTO);
                            propertyProgress.setTaskContentList(propertyContentOne);//封装内容
                            progressList.add(propertyProgress);
                            progressInfo.setProgressList(progressList);//封装所有任务信息
                        }
                    }
                }

                //维修完成信息
                List<PropertyRepairTaskEntity> taskTwoEntity=propertyRepairTaskRepository.getTaskDateFour(propertyRepairEntity.getRepairId());
                if(taskTwoEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskTwoEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskTwoEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                    propertyProgress.setTaskTime(DateUtils.format(taskTwoEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                    //任务内容
                    PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                    propertyContentDTO.setTaskContent(taskTwoEntity.get(0).getTaskContent());//任务内容
                    propertyContentTwo.add(propertyContentDTO);
                    propertyProgress.setTaskContentList(propertyContentTwo);//封装内容
                    progressList.add(propertyProgress);
                    progressInfo.setProgressList(progressList);//封装所有任务信息
                }

                //维修进展信息
                List<PropertyRepairTaskEntity> taskThreeEntity=propertyRepairTaskRepository.getTaskDateThree(propertyRepairEntity.getRepairId());
                if(taskThreeEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    for(PropertyRepairTaskEntity taskThree:taskThreeEntity){
                        propertyProgress.setTaskName(taskThreeEntity.get(0).getTaskName());//任务名称
                        propertyProgress.setTaskDate(DateUtils.format(taskThreeEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                        propertyProgress.setTaskTime(DateUtils.format(taskThreeEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                        //任务内容
                        PropertyRepairTaskEntity taskContent=propertyRepairTaskRepository.getTaskInfo(taskThree.getTaskId());
                        PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                        propertyContentDTO.setTaskContent(taskContent.getTaskContent());//任务内容
                        propertyContentThree.add(propertyContentDTO);
                    }
                    propertyProgress.setTaskContentList(propertyContentThree);//封装内容
                    progressList.add(propertyProgress);
                    progressInfo.setProgressList(progressList);//封装所有任务信息
                }

                //派工信息
                List<PropertyRepairTaskEntity> taskFourEntity=propertyRepairTaskRepository.getTaskDateTwo(propertyRepairEntity.getRepairId());
                if(taskFourEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskFourEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskFourEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                    propertyProgress.setTaskTime(DateUtils.format(taskFourEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                    //通过维修人员id,获取用户头像
                    UserPropertyStaffEntity  userStaff = userPropertyStaffRepository.get(taskFourEntity.get(0).getTaskUserId());
                    if(userStaff.getLogo()!=null && !"".equals(userStaff.getLogo())) {
                        propertyProgress.setImageUrl(userStaff.getLogo());//用户头像
                    }else{
                        propertyProgress.setImageUrl("http://lifestatic.wanda.cn/frontimg/images/interface/user/head.png");//默认用户头像
                    }
                    //任务内容
                    PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                    propertyContentDTO.setTaskContent(taskFourEntity.get(0).getTaskContent());//任务内容
                    propertyContentFour.add(propertyContentDTO);
                    propertyProgress.setTaskContentList(propertyContentFour);//封装内容
                    progressList.add(propertyProgress);
                    progressInfo.setProgressList(progressList);//封装所有任务信息
                }

                //提交报修信息
                List<PropertyRepairTaskEntity> taskFiveEntity=propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
                if(taskFiveEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskFiveEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskFiveEntity.get(0).getTaskDate(), "yyyy-MM-dd"));//任务日期
                    propertyProgress.setTaskTime(DateUtils.format(taskFiveEntity.get(0).getTaskDate(), "HH:mm:ss"));//任务时间
                    //任务内容
                    PropertyContentDTO propertyContentDTO=new PropertyContentDTO();
                    propertyContentDTO.setTaskContent(taskFiveEntity.get(0).getTaskContent());//任务内容
                    propertyContentFive.add(propertyContentDTO);
                    propertyProgress.setTaskContentList(propertyContentFive);//封装内容
                    progressList.add(propertyProgress);
                    progressInfo.setProgressList(progressList);//封装所有任务信息
                }
                if(taskFourEntity.size()>0) {
                    //通过维修人员id,获取用户头像
                    UserPropertyStaffEntity userStaff = userPropertyStaffRepository.get(taskFourEntity.get(0).getTaskUserId());
                    if(userStaff!=null) {
                        progressInfo.setUserId(userStaff.getStaffId());//维修人员id
                        if (userStaff.getLogo()!= null && !"".equals(userStaff.getLogo())) {
                            progressInfo.setImageUrl(userStaff.getLogo());//用户头像
                        } else {
                            progressInfo.setImageUrl("http://lifestatic.wanda.cn/frontimg/images/interface/user/head.png");//默认用户头像
                        }
                    }
                }
            }
            return new SuccessApiResult(progressInfo);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 随手报：继续报修
     * @param user ：用户
     * @param id   ：保修单id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult reportsContinue(UserPropertyStaffEntity user, String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairInfo!=null){
                if(propertyRepairInfo.getTypes().equals("1")){
                    return ErrorResource.getError("tip_pe00000007");//已终止维修，不可再继续报修
                }
                //修改报修单：除了新的单号和时间，其他的都是之前的信息
                propertyRepairInfo.setModifyBy(propertyRepairInfo.getCreateBy());//修改人
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                //propertyRepairEntity.setTypes("0");//0代表未完成；1代表已完成
                propertyRepairInfo.setState("正在派工");
                //报修任务表
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairInfo.setTaskStatus("0");//0为用户提交维修(业主)
                        propertyRepairTaskEntity.setTaskName("提交报修");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("0");//0为用户提交报修(业主)
                        propertyRepairTaskEntity.setTaskContent("您的报修信息已成功提交。");//任务内容
                    }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("1");//1为用户提交投诉(业主)
                        propertyRepairTaskEntity.setTaskName("提交投诉");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("1");//1为用户提交投诉(业主)
                        propertyRepairTaskEntity.setTaskContent("您的投诉信息已成功提交。");//任务内容
                    }
                }
                propertyRepairRepository.updateRepair(propertyRepairInfo);
                //添加任务：新的任务
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskNode("继续报修");//任务节点
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                //修改报修图时间和报修id
                List<PropertyImageEntity> imageInfoList=propertyImageRepository.getImageUrl(propertyRepairInfo.getRepairId());
                for(PropertyImageEntity imageInfo:imageInfoList){
                    imageInfo.setModifyDate(DateUtils.getDate());
                    propertyImageRepository.updateImage(imageInfo);
                }
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 随手报：停止报修
     * @param user ：用户
     * @param id   ：保修单id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult reportsStop(UserPropertyStaffEntity user, String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        try{
            PropertyRepairEntity propertyRepairEntity=  propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairEntity!=null){
                if(propertyRepairEntity.getTypes().equals("1")){
                    return ErrorResource.getError("tip_pe00000006");//已是终止状态，不可重复提交
                }
                propertyRepairEntity.setModifyBy(user.getStaffName());
                propertyRepairEntity.setModifyDate(DateUtils.getDate());
                propertyRepairEntity.setTypes("1");//已完成报修
                propertyRepairEntity.setState("已完成");
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());
                propertyRepairTaskEntity.setRepairId(propertyRepairEntity.getRepairId());
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        propertyRepairEntity.setTaskStatus("14");//14为终止维修(业主)
                        propertyRepairTaskEntity.setTaskName("评价/回访");
                        propertyRepairTaskEntity.setTaskNode("终止报修");
                        propertyRepairTaskEntity.setTaskStatus("14");//14为终止维修(业主)
                        propertyRepairTaskEntity.setTaskContent("已与业主沟通，维修工作已完成。");
                    }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairEntity.setTaskStatus("17");//17为终止处理(业主)
                        propertyRepairTaskEntity.setTaskName("投诉解决");
                        propertyRepairTaskEntity.setTaskNode("终止投诉");
                        propertyRepairTaskEntity.setTaskStatus("17");//17为终止处理(业主)
                        propertyRepairTaskEntity.setTaskContent("本次投诉已解决。");
                    }
                }
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());
                propertyRepairRepository.updateRepair(propertyRepairEntity);
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }
/**-------------------------------------管理系统端部分---------------------------------------------*/
    /**
     * 反馈显示
     * @param workOrderContentDTO
     * @return
     */
    @Override
    public WorkOrderContentDTO repairFreeBack(UserPropertyStaffEntity user,WorkOrderContentDTO workOrderContentDTO) {
        WorkOrderContentDTO workOrderContent=new WorkOrderContentDTO();
        //查看报修信息
        PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(workOrderContentDTO.getId());
        if (propertyRepairInfo!=null){
            workOrderContent.setId(propertyRepairInfo.getRepairId());
        }
        return workOrderContent;
    }

    /**
     * 反馈提交
     * @param workOrderContentDTO
     * @return
     */
    @Override
    public String saveFreeBack(UserPropertyStaffEntity user,WorkOrderContentDTO workOrderContentDTO) {
        // 判断是否执行更新成功
        String resultMessage = "";
        //查看报修信息
        PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(workOrderContentDTO.getId());
        if (propertyRepairInfo!=null){
            if(propertyRepairInfo.getTaskStatus().equals("2") || propertyRepairInfo.getTaskStatus().equals("4") ||
                    propertyRepairInfo.getTaskStatus().equals("5") || propertyRepairInfo.getTaskStatus().equals("7")) {
                if (workOrderContentDTO.getTaskContent() != null && !"".equals(workOrderContentDTO.getTaskContent())) {
                    //添加任务：新的任务
                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                    propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                    propertyRepairTaskEntity.setTaskNode("正在处理中");//任务节点
                    //propertyRepairTaskEntity.setTaskUserId(user.getStaffId());//接单人
                    propertyRepairTaskEntity.setTaskUserType("1");//0为维修人员;1为客服人员
                    propertyRepairTaskEntity.setTaskContent(workOrderContentDTO.getTaskContent() + "[管理端]");//任务内容
                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                    //获取报修类型
                    List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                    if (repairTaskEntities.size() > 0) {
                        if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                            propertyRepairInfo.setTaskStatus("7");//7为维修中
                            propertyRepairTaskEntity.setTaskName("维修进展");//任务名称
                            propertyRepairTaskEntity.setTaskStatus("7");//7为维修中
                        } else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                            propertyRepairInfo.setTaskStatus("5");//5为处理中
                            propertyRepairTaskEntity.setTaskName("投诉进展");//任务名称
                            propertyRepairTaskEntity.setTaskStatus("5");//5为处理中
                        }
                    }
                    propertyRepairTaskEntity.setTaskUserType("1");//任务用户类型：0为维修人员;1为客服人员
                    //修改报修单
                    propertyRepairInfo.setModifyBy(user.getStaffId());
                    propertyRepairInfo.setModifyDate(DateUtils.getDate());
                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                    //执行新增操作，加入日志
                    OperationLogDTO operationLogDTO = new OperationLogDTO();
                    operationLogDTO.setContent("添加反馈");
                    operationLogDTO.setProjectId(user.getProjectId());
                    operationLogDTO.setUserName(user.getStaffName());
                    operationLogService.addOperationLog(operationLogDTO);
                    //修改报修单状态
                    boolean repair = propertyRepairRepository.updateRepaired(propertyRepairInfo);
                    if (repair) {
                        resultMessage = "0";//成功!
                    } else {
                        resultMessage = "1";//失败!
                    }
                }
            }else{
                resultMessage="2";//工单不在可编辑状态！
            }
        }else{
            resultMessage = "3";//此信息不存在!
        }
        return resultMessage;
    }

    /**
     * 分配显示
     * @param user
     * @param workApportionDTO
     * @return
     */
    @Override
    public WorkApportionDTO repairsAssign(UserPropertyStaffEntity user, WorkApportionDTO workApportionDTO) {
        //封装到dto
        WorkApportionDTO apportion = new WorkApportionDTO();
        if(!StringUtil.isEmpty(workApportionDTO.getId())) {
            //查看报修信息
            PropertyRepairEntity repairInfo=propertyRepairRepository.getRepairDetail(workApportionDTO.getId());
            if(repairInfo.getTaskStatus()!=null && repairInfo.getCompleteDate()!=null){
                apportion.setTaskStatus(repairInfo.getTaskStatus());//任务状态
                long completeDate=repairInfo.getCompleteDate().getTime();//时间戳
                apportion.setCompleteDate(Long.toString(completeDate));//维修完成时间
            }
            //获得部门列表
            Map<String, String> department = new LinkedHashMap<>();
            department.put("0", "---请选择部门---");
            Map<String, String> worker = new LinkedHashMap<>();
            worker.put("0", "---请选择姓名---");
            //获取报修类型
            List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(workApportionDTO.getId());
            if(repairTaskEntities.size()>0){
                String today= DateUtils.format(new Date(), "yyyy-MM-dd");//当前日期
                if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                    if(repairInfo.getTaskStatus().equals("10")){//维修：24小时未评价，待回访
                        //查询投诉工单的部门
                        List<StaffRegisterEntity> registers=staffRegisterRepository.getDepartment(user.getProjectId(),today);
                        if (registers.size()>0) {
                            for (StaffRegisterEntity register : registers) {
                                department.put(register.getStaffSectionId(), register.getStaffSection());//部门id、部门名称
                            }
                        }
                    }else{
                        //查询维修部门
                        List<StaffRegisterEntity> sections = staffRegisterRepository.getRepairDepartment(user.getProjectId(),today);
                        if (sections.size()>0) {
                            for (StaffRegisterEntity register : sections) {
                                department.put(register.getStaffSectionId(), register.getStaffSection());//部门id、部门名称
                            }
                        }
                    }
                }else if(repairTaskEntities.get(0).getTaskStatus().equals("1")){//投诉
                    //查询投诉工单的部门
                    List<StaffRegisterEntity> sections = staffRegisterRepository.getDepartment(user.getProjectId(),today);
                    if (sections.size()>0) {
                        for (StaffRegisterEntity register : sections) {
                            //查询投诉工单的部门
                            /*List<StaffRegisterEntity> staff = staffRegisterRepository.getRegister(register.getStaffSectionId(),today);
                            if (staff.size() > 0) {
                                for (StaffRegisterEntity userStaff : staff) {
                                    worker.put(userStaff.getStaffId(), userStaff.getStaffName());//用户id、用户姓名
                                }
                            }*/
                            department.put(register.getStaffSectionId(), register.getStaffSection());//部门id、部门名称
                        }
                    }
                }
            }
            apportion.setDepartmentMap(department);
            apportion.setWorkerMap(worker);
        }
        return apportion;
    }

    /**
     * 分配提交
     * @param user
     * @param workApportionDTO
     * @return
     */
    @Override
    public String saveAssign(UserPropertyStaffEntity user, WorkApportionDTO workApportionDTO) {
        // 判断是否执行更新成功
        String resultMessage = "";
        if(!StringUtil.isEmpty(workApportionDTO.getId()) && !StringUtil.isEmpty(workApportionDTO.getUserId())){
            //查看报修信息
            PropertyRepairEntity propertyRepairInfo = propertyRepairRepository.getRepairDetail(workApportionDTO.getId());
            if (propertyRepairInfo != null) {
                //修改报修单
                propertyRepairInfo.setState("正在处理");//状态由正在派工改为正在处理
                propertyRepairInfo.setUserId(workApportionDTO.getUserId());
                propertyRepairInfo.setModifyBy(user.getStaffId());
                propertyRepairInfo.setModifyDate(DateUtils.getDate());
                //添加任务：新的任务
                PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskNode("接单成功");//任务节点
                propertyRepairTaskEntity.setTaskManagerId(user.getStaffId());//管理人
                //获取维修人员的姓名和电话
                UserPropertyStaffEntity userStaff=userPropertyStaffRepository.get(workApportionDTO.getUserId());
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                        if(propertyRepairInfo.getTaskStatus().equals("10")){
                            propertyRepairInfo.setTaskStatus("12");//12为待客服回访
                            propertyRepairTaskEntity.setTaskName("客服回访");//任务名称
                            propertyRepairTaskEntity.setTaskStatus("12");
                            propertyRepairTaskEntity.setTaskUserType("1");//0为维修人员;1为客服人员
                            propertyRepairTaskEntity.setCustomerServiceId(workApportionDTO.getUserId());//接单人
                        }else {
                            propertyRepairInfo.setTaskStatus("4");//4为维修接单
                            propertyRepairTaskEntity.setTaskName("派工信息");//任务名称
                            propertyRepairTaskEntity.setTaskStatus("4");//4为维修接单
                            propertyRepairTaskEntity.setTaskUserType("0");//0为维修人员;1为客服人员
                            propertyRepairTaskEntity.setTaskUserId(workApportionDTO.getUserId());//工单接单人
                            propertyRepairTaskEntity.setTaskContent("维修专员" + userStaff.getStaffName() + " " + userStaff.getMobile() + "将与您联系。");//任务内容
                        }
                    }else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                        propertyRepairInfo.setTaskStatus("2");//2为投诉接单
                        propertyRepairTaskEntity.setTaskName("受理投诉");//任务名称
                        propertyRepairTaskEntity.setTaskStatus("2");//2为投诉接单
                        propertyRepairTaskEntity.setTaskUserType("1");//0为维修人员;1为客服人员
                        propertyRepairTaskEntity.setTaskUserId(workApportionDTO.getUserId());//工单接单人
                        propertyRepairTaskEntity.setTaskContent("客服专员" + userStaff.getStaffName()+ " " + userStaff.getMobile() + "将与您联系。");//任务内容
                    }
                }
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                if(propertyRepairInfo.getMemo().equals("报修")) {
                    //调用消息推送：业主推送
                    MessageInsertDTO messageInsert = new MessageInsertDTO();
                    messageInsert.setMessageTitle(propertyRepairInfo.getRepairId());
                    messageInsert.setMessageUserType("1");//1为业主；2为员工
                    messageInsert.setMessageUrl(propertyRepairInfo.getRepairId());
                    messageInsert.setMessageType("2");//业主类型
                    messageInsert.setMessageTypeState("2");//派工状态
                    List<String> users = new ArrayList<>();
                    users.add(propertyRepairInfo.getCreateBy());
                    messageInsertService.InsertMessage(messageInsert, users);
                    //调用消息推送：员工推送
                    MessageInsertDTO message = new MessageInsertDTO();
                    message.setMessageTitle(propertyRepairInfo.getRepairId());
                    message.setMessageUserType("2");//1为业主；2为员工
                    message.setMessageUrl(propertyRepairInfo.getRepairId());
                    message.setMessageType("5");//员工类型
                    message.setMessageTypeState("1");//派工状态
                    List<String> userId = new ArrayList<>();
                    userId.add(workApportionDTO.getUserId());
                    messageInsertService.InsertMessage(message, userId);
                }else if(propertyRepairInfo.getMemo().equals("投诉")){
                    //调用消息推送：业主推送
                    MessageInsertDTO messageInsert = new MessageInsertDTO();
                    messageInsert.setMessageTitle(propertyRepairInfo.getRepairId());
                    messageInsert.setMessageUserType("1");//1为业主；2为员工
                    messageInsert.setMessageUrl(propertyRepairInfo.getRepairId());
                    messageInsert.setMessageType("9");//业主类型
                    messageInsert.setMessageTypeState("2");//派工状态
                    List<String> users = new ArrayList<>();
                    users.add(propertyRepairInfo.getCreateBy());
                    messageInsertService.InsertMessage(messageInsert, users);
                    //调用消息推送：员工推送
                    MessageInsertDTO message = new MessageInsertDTO();
                    message.setMessageTitle(propertyRepairInfo.getRepairId());
                    message.setMessageUserType("2");//1为业主；2为员工
                    message.setMessageUrl(propertyRepairInfo.getRepairId());
                    message.setMessageType("8");//员工类型
                    message.setMessageTypeState("1");//派工状态
                    List<String> userId = new ArrayList<>();
                    userId.add(workApportionDTO.getUserId());
                    messageInsertService.InsertMessage(message, userId);
                }
                //执行添加操作，加入日志
                OperationLogDTO operationLogDTO=new OperationLogDTO();
                operationLogDTO.setContent("添加分配人员");
                operationLogDTO.setProjectId(user.getProjectId());
                operationLogDTO.setUserName(user.getStaffName());
                operationLogService.addOperationLog(operationLogDTO);
                //修改报修单
                boolean repair=propertyRepairRepository.updateRepaired(propertyRepairInfo);
                if(repair){
                    resultMessage = "0";//成功!
                }else {
                    resultMessage = "1";//失败!
                }
            }else{
                resultMessage = "2";//此信息不存在!
            }
        }
        return resultMessage;
    }

    /**
     * 获取报修进展
     * @param repairId
     * @return
     */
    @Override
    public PropertyProgressInfoDTO repairProgress(String repairId) {
        //封装到dto
        PropertyProgressInfoDTO ProgressInfo=new PropertyProgressInfoDTO();
        List<PropertyProgressDTO> progressList=new ArrayList<PropertyProgressDTO>();
        if(repairId!=null){
            //查询报修所需要的详情信息
            PropertyRepairEntity propertyRepairEntity=propertyRepairRepository.getRepairDetail(repairId);
            ProgressInfo.setStatus(propertyRepairEntity.getState());
            if (propertyRepairEntity!=null){
                //业主回访信息
                List<PropertyRepairTaskEntity> taskOneEntity=propertyRepairTaskRepository.getTaskDateFive(propertyRepairEntity.getRepairId());
                if(taskOneEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskOneEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskOneEntity.get(0).getTaskDate(), DateUtils.FORMAT_LONG));//任务日期
                    propertyProgress.setTaskContent(taskOneEntity.get(0).getTaskContent());//任务内容
                    progressList.add(propertyProgress);
                }

                //报修关闭信息
                List<PropertyRepairTaskEntity> taskSevenEntity=propertyRepairTaskRepository.getTaskDateSeven(propertyRepairEntity.getRepairId());
                if(taskSevenEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskSevenEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskSevenEntity.get(0).getTaskDate(), DateUtils.FORMAT_LONG));//任务日期
                    propertyProgress.setTaskContent(taskSevenEntity.get(0).getTaskContent());//任务内容
                    progressList.add(propertyProgress);
                }

                //维修完成信息
                List<PropertyRepairTaskEntity> taskTwoEntity=propertyRepairTaskRepository.getTaskDateFour(propertyRepairEntity.getRepairId());
                if(taskTwoEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskTwoEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskTwoEntity.get(0).getTaskDate(), DateUtils.FORMAT_LONG));//任务日期
                    propertyProgress.setTaskContent(taskTwoEntity.get(0).getTaskContent());//任务内容
                    progressList.add(propertyProgress);
                }

                //维修进展信息
                List<PropertyRepairTaskEntity> taskThreeEntity=propertyRepairTaskRepository.getTaskDateThree(propertyRepairEntity.getRepairId());
                if(taskThreeEntity.size()>0){
                    //for(PropertyRepairTaskEntity taskThree:taskThreeEntity){
                        PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                        propertyProgress.setTaskName(taskThreeEntity.get(0).getTaskName());//任务名称
                        //任务内容
                        //PropertyRepairTaskEntity taskContent=propertyRepairTaskRepository.getTaskInfo(taskThree.getTaskId());
                        propertyProgress.setTaskDate(DateUtils.format(taskThreeEntity.get(0).getTaskDate(), DateUtils.FORMAT_LONG));//任务日期
                        propertyProgress.setTaskContent(taskThreeEntity.get(0).getTaskContent());//任务内容
                        progressList.add(propertyProgress);
                    //}
                }

                //派工信息
                /*List<PropertyRepairTaskEntity> taskFourEntity=propertyRepairTaskRepository.getTaskDateTwo(propertyRepairEntity.getRepairId());
                if(taskFourEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskFourEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskFourEntity.get(0).getTaskDate(), DateUtils.FORMAT_LONG));//任务日期
                    propertyProgress.setTaskContent(taskFourEntity.get(0).getTaskContent());//任务内容
                    progressList.add(propertyProgress);
                }*/

                //报修受理信息
                List<PropertyRepairTaskEntity> taskSixEntity=propertyRepairTaskRepository.getTaskDateSix(propertyRepairEntity.getRepairId());
                if(taskSixEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskSixEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskSixEntity.get(0).getTaskDate(), DateUtils.FORMAT_LONG));//任务日期
                    propertyProgress.setTaskContent(taskSixEntity.get(0).getTaskContent());//任务内容
                    progressList.add(propertyProgress);
                }

                //提交报修信息
                List<PropertyRepairTaskEntity> taskFiveEntity=propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
                if(taskFiveEntity.size()>0){
                    PropertyProgressDTO propertyProgress = new PropertyProgressDTO();
                    propertyProgress.setTaskName(taskFiveEntity.get(0).getTaskName());//任务名称
                    propertyProgress.setTaskDate(DateUtils.format(taskFiveEntity.get(0).getTaskDate(), DateUtils.FORMAT_LONG));//任务日期
                    propertyProgress.setTaskContent(taskFiveEntity.get(0).getTaskContent());//任务内容
                    progressList.add(propertyProgress);
                }
                ProgressInfo.setProgressList(progressList);
            }
        }
        return ProgressInfo;
    }

    @Override
    public List<PropertyRepairTaskDTO> getPersonalCollection(String userId, UserInfoEntity userInfo,String visit) {
        List<Object[]> progressList = propertyRepairTaskRepository.getPersonalCollection(userId);

        List<PropertyRepairTaskDTO> propertyRepairTasks = new ArrayList<PropertyRepairTaskDTO>();

        for(Object[] objects:progressList){

            PropertyRepairTaskDTO propertyRepairTask = new PropertyRepairTaskDTO();

            propertyRepairTask.setTaskId((String) objects[0]);
            if(null != objects[1] && !"".equals(objects[1])) {
                propertyRepairTask.setTaskDate(DateUtils.format((Date) objects[1]));
            }
            propertyRepairTask.setTaskContent((String)objects[2]);
            propertyRepairTask.setReadStatus((String) objects[3]);
            propertyRepairTask.setTaskName((String) objects[4]);
            propertyRepairTask.setRepairId((String) objects[5]);
            propertyRepairTask.setRepairContent((String) objects[6]);
            propertyRepairTasks.add(propertyRepairTask);
        }

        //调用点击人统计
        String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
        ClickUsersEntity clickUserEntity=clickUserRepository.getTotalInfo(dateNow,"2",userId);
        if(clickUserEntity==null){
            ClickUsersEntity clickUser=new ClickUsersEntity();
            clickUser.setId(IdGen.uuid());
            clickUser.setCreateDate(new Date());
            clickUser.setClicks(1);
            clickUser.setUserId(userId);
            clickUser.setForeignId("2");
            clickUserRepository.create(clickUser);
        }else{
            clickUserEntity.setClicks(clickUserEntity.getClicks() + 1);
            clickUserRepository.update(clickUserEntity);
        }
        if(visit.equals("indexInfo")){
            //        增加日志
            UserVisitLogEntity userVisitLog=new UserVisitLogEntity();
            userVisitLog.setLogId(IdGen.uuid());
            userVisitLog.setLogTime(new Date());//注册日期
            userVisitLog.setUserName(userInfo.getNickName());//用户昵称
            userVisitLog.setUserType(userInfo.getUserType());//用户类型
            userVisitLog.setUserMobile(userInfo.getMobile());//手机号
            userVisitLog.setSourceFrom(userInfo.getSourceType());//来源
            userVisitLog.setThisSection("首页");//板块
            userVisitLog.setThisFunction("首页消息");//功能
            userVisitLog.setLogContent("");//内容
            UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfo.getUserId());
            if (null != userSettingEntity) {
                userVisitLog.setProjectId(userSettingEntity.getProjectName());
            }else{
                userVisitLog.setProjectId("");
            }
            systemLogRepository.addUserVisitLog(userVisitLog);


        }
        if(visit.equals("ownInfo")){
            //增加日志
            UserVisitLogEntity userVisitLog=new UserVisitLogEntity();
            userVisitLog.setLogId(IdGen.uuid());
            userVisitLog.setLogTime(new Date());//注册日期
            userVisitLog.setUserName(userInfo.getNickName());//用户昵称
            userVisitLog.setUserType(userInfo.getUserType());//用户类型
            userVisitLog.setUserMobile(userInfo.getMobile());//手机号
            userVisitLog.setSourceFrom(userInfo.getSourceType());//来源
            userVisitLog.setThisSection("我的");//板块
            userVisitLog.setThisFunction("个人消息");//功能
            userVisitLog.setLogContent("");//内容
            UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfo.getUserId());
            if (null != userSettingEntity) {
                userVisitLog.setProjectId(userSettingEntity.getProjectName());
            }else{
                userVisitLog.setProjectId("");
            }
            systemLogRepository.addUserVisitLog(userVisitLog);
        }
        return propertyRepairTasks;
    }


    /**
     * 通过报修单Id及维修状态值修改该状态(多条相同状态)为已读
     * @param taskId    维修状态Id
     * @return  ApiResult
     */
    public ApiResult editPropertyRepairTask(String taskId) {
        //通过维修状态taskId检索维修状态信息(目的:获取报修单Id及维修状态值统一更改该状态为已读)
        PropertyRepairTaskEntity propertyRepairTaskEntity = propertyRepairTaskRepository.getPropertyRepairTaskById(taskId);
        if (null != propertyRepairTaskEntity){
            String repairId = propertyRepairTaskEntity.getRepairId();   //报修单Id
            String taskStatus = propertyRepairTaskEntity.getTaskStatus();   //维修状态
            propertyRepairTaskRepository.updateRepairTaskReadStatus(repairId,taskStatus);
            return new SuccessApiResult();
        }
        /*
        //原逻辑
        if(null != propertyRepairTaskEntity) {
            propertyRepairTaskEntity.setReadStatus("1");
            propertyRepairTaskRepository.editPropertyRepairTask(propertyRepairTaskEntity);
            return new SuccessApiResult();
        }
        */
        return new ErrorApiResult(500,"数据错误，请刷新后重试");
    }

    @Override
    public ApiResult getPersonalCollectionNum(String userId) {
        return new SuccessApiResult(propertyRepairTaskRepository.getPersonalCollectionNum(userId));
    }

}