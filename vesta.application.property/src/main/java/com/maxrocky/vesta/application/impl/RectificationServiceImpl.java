package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.DTO.admin.*;
import com.maxrocky.vesta.application.DTO.json.HI0009.DeliveryInformationDTO;
import com.maxrocky.vesta.application.DTO.json.HI0009.HouseReceptionDTO;
import com.maxrocky.vesta.application.DTO.json.HI0009.InternalDTO;
import com.maxrocky.vesta.application.DTO.json.HI0010.RectipierJsomDTO;

import com.maxrocky.vesta.application.DTO.json.HI0012.*;
import com.maxrocky.vesta.application.inf.HouseHouseProjectAllService;
import com.maxrocky.vesta.application.inf.InspectionService;
import com.maxrocky.vesta.application.inf.RectificationService;
import com.maxrocky.vesta.application.inf.RepairClientService;
import com.maxrocky.vesta.application.model.*;
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
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Magic on 2016/4/29.
 */
@Service
public class RectificationServiceImpl implements RectificationService {
    @Autowired
    PropertyRectifyCRMRepository propertyRectifyCRMRepository;
    @Autowired
    PropertyImageRepository propertyImageRepository;
    @Autowired
    private PropertyRepairTaskRepository propertyRepairTaskRepository;
    @Autowired
    private InspectionService inspectionService;
    @Autowired
    private PropertyRepairRepository propertyRepairRepository;
    @Autowired
    RectificationRepository rectificationRepository;
    @Autowired
    MapperFacade mapper;
    @Autowired
    RepairClientService repairClientService;
    /* 员工信息 */
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    private HouseInfoRepository houseInfoRepository;//房屋业主地址
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;

    @Override
    public RepairUpDateTimeDTO getAllRepairByApp(String id, String time, String projectNum) {
        {
            List<PropertyRepairListDTO> RectificationList=new ArrayList<PropertyRepairListDTO>();//具体数据
            RepairUpDateTimeDTO RepairCationUpTimeDTO=new RepairUpDateTimeDTO();
            List<Object[]> propertyListNew=rectificationRepository.getAllRepairByApp(id, time, projectNum);
            if(propertyListNew !=null && !propertyListNew.isEmpty()){
                for(Object[] obj : propertyListNew){
                    List<ProjectReturnImageJsonDTO> imgJsonList = new ArrayList<>();
                    List<ProjectReturnImageJsonDTO> imageJsonList = new ArrayList<>();
                    PropertyRepairListDTO rectific = new PropertyRepairListDTO();
                    rectific.setType("0");//保修单标识
                    rectific.setPlantype("repair");
                    rectific.setUptime(obj[0] == null ? "" : DateUtils.format((Date) obj[0]));//最后修改时间
                    rectific.setRepairManager(obj[1] == null ? "" : obj[1].toString());//维修负责人
                    rectific.setRoomId(obj[2] == null ? "" : obj[2].toString()); //房间id
                    rectific.setRoomNum(obj[3] == null ? "" : obj[3].toString());//房间编码
                    rectific.setMemberId(obj[4] == null ? "" : obj[4].toString());//报修人会员编号
                    rectific.setClassifyOne(obj[5] == null ? "" : obj[5].toString());//一级分类
                    rectific.setClassifyTwo(obj[6] == null ? "" : obj[6].toString());//二级分类
                    rectific.setClassifyThree(obj[7] == null ? "" : obj[7].toString());//三级分类
                    rectific.setMode(obj[8] == null ? "" : obj[8].toString());//维修方式
                    rectific.setRepairDate(obj[9] == null ? "" : obj[9].toString());//维修工时
                    rectific.setState(obj[10] == null ? "" : obj[10].toString());//订单状态
                    rectific.setContent(obj[11] == null ? "" : obj[11].toString());//描述
                    rectific.setDutyCompanyOne(obj[12] == null ? "" : obj[12].toString());//责任单位1
                    rectific.setDutyCompanyTwo(obj[13] == null ? "" : obj[13].toString());//责任单位2
                    rectific.setDutyCompanyThree(obj[14] == null ? "" : obj[14].toString());//责任单位3
                    rectific.setDealPeople(obj[15] == null ? "" : obj[15].toString());//处理人
                    rectific.setDealPeoplename(obj[16] == null ? "" : obj[16].toString());//处理人姓名
                    rectific.setDealMode(obj[17] == null ? "" : obj[17].toString());//处理方式:内部/责任单位/第三方
                    rectific.setDealPhone(obj[18] == null ? "" : obj[18].toString());//处理人电话
                    rectific.setDealResult(obj[19] == null ? "" : obj[19].toString());//处理结果
                    rectific.setDealCompleteDate(obj[20] == null ? "" : DateUtils.format((Date) obj[20]));//处理人完工时间
                    rectific.setRoomLocation(obj[21] == null ? "" : obj[21].toString());//报修位置
                    rectific.setTaskDate(obj[22] == null ? "" : DateUtils.format((Date) obj[22]));//接单时间
                    rectific.setRepairId(obj[23] == null ? "" : obj[23].toString());//报修单号
                    rectific.setUserPhone(obj[24] == null ? "" : obj[24].toString());//报修人电话
                    rectific.setUserAddress(obj[25] == null ? "" : obj[25].toString());//报修人地址
                    rectific.setUsername(obj[26] == null ? "" : obj[26].toString());//报修人姓名
                    rectific.setCreateDate(obj[27] == null ? "" : DateUtils.format((Date) obj[27]));//报修时间 登记时间
                    rectific.setProjectname(obj[28] == null ? "" : obj[28].toString());//所属项目name
                    rectific.setProjectid(obj[29] == null ? "" : obj[29].toString());//项目id
                    rectific.setAddress(obj[30] == null ? "" : obj[30].toString());//房屋地址
                    rectific.setBuildnum(obj[31] == null ? "" : obj[31].toString());//楼栋编码
                    rectific.setProjectNum(obj[32] == null ? "" : obj[32].toString());//所属项目编码
                    rectific.setId(obj[33] == null ? "" : obj[33].toString());
                    rectific.setRepairCompany(obj[34] == null ? "" : obj[34].toString());
                    rectific.setDepartment(obj[35] == null ? "" : obj[35].toString());//部门
                    rectific.setRepairManagerId(obj[36] == null ? "" : obj[36].toString());//内部负责人id
                    rectific.setSupplierId(obj[37] == null ? "" : obj[37].toString());//责任单位负责人id
                    rectific.setxCoordinates(obj[38] == null ? "" : obj[38].toString());
                    rectific.setyCoordinates(obj[39] == null ? "" : obj[39].toString());
                    rectific.setAppId(obj[40] == null ? "" : obj[40].toString());
                    List<PropertyImageEntity> image = rectificationRepository.getImageForId(obj[23] == null ? "" : obj[23].toString());
                    if( image!=null) {
                        for (PropertyImageEntity imageEntity : image) {
                            ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                            RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId());
                            RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                            RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                            RcitifytDTO.setCaseId(obj[23] == null ? "" : obj[23].toString());
                            imageJsonList.add(RcitifytDTO);
                        }
                    }
                    rectific.setImageList(imageJsonList);//报修前的imageList
                    //获取报修后image
                    List<PropertyImageEntity> img = rectificationRepository.getImageForOver(obj[23] == null ? "" : obj[23].toString());
                    if(img!=null) {
                        for (PropertyImageEntity imageEntity : img) {
                            ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                            RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId() );
                            RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                            RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                            RcitifytDTO.setCaseId(obj[23] == null ? "" : obj[23].toString());
                            imgJsonList.add(RcitifytDTO);
                        }
                    }
                    rectific.setImgList(imgJsonList);

                    RectificationList.add(rectific);
                }

            }
            if(RectificationList.size()>0){
                RepairCationUpTimeDTO.setList(RectificationList);
                RepairCationUpTimeDTO.setId(RectificationList.get(RectificationList.size()-1).getId());
                RepairCationUpTimeDTO.setTimeStamp(RectificationList.get(RectificationList.size()-1).getUptime());
            }
            return RepairCationUpTimeDTO;
        }
    }

    @Override
    public QuestionUpdateCheckDTO getCountPropertyRepairForApp(String id, String time, String projectNum) {
        boolean checkFlag = propertyRectifyCRMRepository.getCountRepairByProjectNum(id, time, projectNum);
        if(checkFlag){
            return new QuestionUpdateCheckDTO("Y");
        }
        return new QuestionUpdateCheckDTO("N");
    }

    @Override
    public List<QuestionUpdateCheckAllDTO> getCheckoutAllByProjectNum(PropertyCheckYNDTO propertyCheckYNDTO) {

        List<QuestionUpdateCheckAllDTO> list= propertyCheckYNDTO.getCheckList();
        List<QuestionUpdateCheckAllDTO> getCheckList=new ArrayList<QuestionUpdateCheckAllDTO>();
        if(list!=null){
            for(QuestionUpdateCheckAllDTO checkDto : list){
                QuestionUpdateCheckAllDTO getCheck= new QuestionUpdateCheckAllDTO();
                String time = "";
                if (checkDto.getTime() != null && !"".equals(checkDto.getTime())) {
                    time = DateUtils.format(DateUtils.parse(checkDto.getTime(), "yyyyMMddHHmmss"));
                }
                boolean checkFlag = propertyRectifyCRMRepository.getCountRepairByProjectNum(checkDto.getId(), time, checkDto.getProjectNum());
                if(checkFlag){
                    getCheck.setUpdateFlag("Y");
                }else{
                    getCheck.setUpdateFlag("N");
                }
                getCheck.setProjectNum(checkDto.getProjectNum());
                getCheck.setId(checkDto.getId());
                getCheck.setTime(checkDto.getTime());
                getCheckList.add(getCheck);
            }
        }
        return getCheckList;
    }

    @Override
    public ApiResult savePropertyRepairForApp(PropertyRepairAppDTO repair,UserPropertyStaffEntity userPropertyStaffEntity) {
        if (repair == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if (StringUtil.isEmpty(repair.getRoomNum())) {
            return ErrorResource.getError("tip_00000563");//房间号不能为空
        }
        if (StringUtil.isEmpty(repair.getDepartment())) {
            return ErrorResource.getError("tip_00000577");//组信息不能为空
        }
        HouseInfoEntity house=houseInfoRepository.getHouseByRoomId(repair.getRoomNum());

        if(house == null){
            return ErrorResource.getError("tip_00000565");//房屋信息不存在
        }
        if(StringUtil.isEmpty(house.getProjectNum())){
            return ErrorResource.getError("tip_00000565");//房屋信息不存在
        }
        RectificationListDTO RectificationListDTO=new RectificationListDTO();
        UserInfoEntity user=userInfoRepository.getById(house.getMemberId());

        PropertyRepairCRMEntity propertyRepairCRMEntity=new PropertyRepairCRMEntity();

        String []ss=house.getProjectNum().split("-");
        String repairid=houseInfoRepository.getRepairidNew(ss[ss.length-1]+"-B");
        if(repairid.length()==1){
            repairid="000"+repairid;
        }
        if(repairid.length()==2){
            repairid="00"+repairid;
        }
        if(repairid.length()==3){
            repairid="0"+repairid;
        }
        String id=ss[ss.length-1]+"-B-"+DateUtils.getNow("yyyyMMdd")+"-A-"+repairid;

        propertyRepairCRMEntity.setRepairId(id);    //保修单id
        RectificationListDTO.setRepairId(id);
        propertyRepairCRMEntity.setDepartment(repair.getDepartment());//组  必填
        RectificationListDTO.setDepartment(repair.getDepartment());
        if(!StringUtil.isEmpty(repair.getRoomId())){
            propertyRepairCRMEntity.setRoomId(repair.getRoomId());//房间id
            RectificationListDTO.setRoomId(repair.getRoomId());
        }else{
            propertyRepairCRMEntity.setRoomId(house.getRoomId());//房间id
            RectificationListDTO.setRoomId(house.getRoomId());
        }
        propertyRepairCRMEntity.setRoomNum(repair.getRoomNum());//房间编码   必填
        RectificationListDTO.setRoomNum(repair.getRoomNum());
        if(!StringUtil.isEmpty(house.getMemberId())){
            propertyRepairCRMEntity.setMemberId(house.getMemberId());//会员id
            RectificationListDTO.setMemberId(house.getMemberId());
        }
        propertyRepairCRMEntity.setUserName(repair.getUserName());//报修人姓名 必填
        RectificationListDTO.setUsername(repair.getUserName());
        propertyRepairCRMEntity.setUserPhone(repair.getUserPhone());//报修人电话 必填
        RectificationListDTO.setUserPhone(repair.getUserPhone());
        propertyRepairCRMEntity.setUserAddress(house.getAddress());//报修人地址
        RectificationListDTO.setUserAddress(house.getAddress());
        propertyRepairCRMEntity.setCreateDate(new Date());
        RectificationListDTO.setCreateDate(DateUtils.format(propertyRepairCRMEntity.getCreateDate()));
        propertyRepairCRMEntity.setModifyDate(new Date());  //修改时间
        RectificationListDTO.setUptime(DateUtils.format(propertyRepairCRMEntity.getModifyDate()));
        propertyRepairCRMEntity.setClassifyOne(repair.getClassifyOne());    //一级分类
        RectificationListDTO.setClassifyOne(repair.getClassifyOne());
        propertyRepairCRMEntity.setClassifyTwo(repair.getClassifyTwo());    //二级分类
        RectificationListDTO.setClassifyTwo(repair.getClassifyTwo());
        propertyRepairCRMEntity.setClassifyThree(repair.getClassifyThree());//三级分类
        RectificationListDTO.setClassifyThree(repair.getClassifyThree());
        propertyRepairCRMEntity.setMode(repair.getMode());      //维修方式
        RectificationListDTO.setMode(repair.getMode());
        propertyRepairCRMEntity.setRepairDate(repair.getRepairDate());  //维修工时
        RectificationListDTO.setRepairDate(repair.getRepairDate());
        if(!StringUtil.isEmpty(repair.getRoomLocation())){ //位置
            RoomLocationEntititly roomlocal=houseInfoRepository.getRoomLocation(repair.getRoomLocation());
            if(roomlocal!=null){
                RectificationListDTO.setRoomLocation(roomlocal.getName());
            }
            propertyRepairCRMEntity.setRoomLocation(repair.getRoomLocation());
        }
        propertyRepairCRMEntity.setState("accepted");
        RectificationListDTO.setState("accepted");
        propertyRepairCRMEntity.setDuty(repair.getDuty());
        propertyRepairCRMEntity.setProblemLevel(repair.getProblemLevel());
        propertyRepairCRMEntity.setRepairWay("APP");
        propertyRepairCRMEntity.setContent(repair.getContent());    //描述
        RectificationListDTO.setContent(repair.getContent());
        propertyRepairCRMEntity.setDealWay(repair.getDealWay());    //处理方案
        propertyRepairCRMEntity.setDealMode(repair.getDealMode());  //处理方式  第三方 内部.......
        RectificationListDTO.setDealMode(repair.getDealMode());
        propertyRepairCRMEntity.setDutyCompanyOne(repair.getDutyCompanyOne()); //责任单位一
        RectificationListDTO.setDutyCompanyOne(repair.getDutyCompanyOne());
        propertyRepairCRMEntity.setDutyCompanyTwo(repair.getDutyCompanyTwo());//责任单位二
        RectificationListDTO.setDutyCompanyTwo(repair.getDutyCompanyTwo());
        propertyRepairCRMEntity.setDutyCompanyThree(repair.getDutyCompanyThree());//责任单位三
        RectificationListDTO.setDutyCompanyThree(repair.getDutyCompanyThree());
        propertyRepairCRMEntity.setRepairCompany(repair.getRepairCompany());    //维修单位
        RectificationListDTO.setRepairCompany((repair.getRepairCompany()));
        propertyRepairCRMEntity.setxCoordinates((repair.getxCoordinates() == null || "".equals(repair.getxCoordinates())) ? null : new BigDecimal(repair.getxCoordinates()));
        RectificationListDTO.setxCoordinates(repair.getxCoordinates());
        propertyRepairCRMEntity.setyCoordinates((repair.getyCoordinates() == null || "".equals(repair.getyCoordinates())) ? null : new BigDecimal(repair.getyCoordinates()));
        RectificationListDTO.setyCoordinates(repair.getyCoordinates());
        if(!StringUtil.isEmpty(repair.getRepairManagerId())){
            propertyRepairCRMEntity.setSendName(userPropertyStaffEntity.getUserName());
            propertyRepairCRMEntity.setSendDate(new Date());
            propertyRepairCRMEntity.setRepairManagerId(repair.getRepairManagerId());
            RectificationListDTO.setRepairManagerId(repair.getRepairManagerId());
            propertyRepairCRMEntity.setDealPeople(repair.getRepairManagerId());
            RectificationListDTO.setDealPeople(repair.getRepairManagerId());
            UserPropertyStaffEntity UserStaff=rectificationRepository.getusername(repair.getRepairManagerId());
            if(UserStaff!=null){
                propertyRepairCRMEntity.setRepairManager(UserStaff.getUserName());
                RectificationListDTO.setRepairManager(UserStaff.getUserName());
                propertyRepairCRMEntity.setDealPhone(UserStaff.getMobile());
                RectificationListDTO.setDealPeoplename(UserStaff.getStaffName());
            }
        }
        propertyRepairCRMEntity.setDealState(repair.getDealState());
        //整改单图片
        List<RectifyImageDTO> qImageList = repair.getImageList(); //报修完成前list
        if(qImageList!=null && ! qImageList.isEmpty()){
            for(RectifyImageDTO rectifyImageDTO : qImageList){
                PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                propertyImageEntity.setUploadDate(new Date());//上传日期
                propertyImageEntity.setImgFkId(id);//图片外键id
                propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                propertyImageEntity.setImageType("0");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                propertyImageEntity.setState("0");//状态:0为有效；1为无效
                propertyImageRepository.saveImage(propertyImageEntity);
            }
        }
        RectificationListDTO.setImageList(repair.getImageList());

        propertyRepairRepository.saveRepairCrm(propertyRepairCRMEntity);
        //调用日志
        InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
        interfaceLogEntity.setId(IdGen.uuid());
        interfaceLogEntity.setInterfaceName("质检APP调用报修接口:创建报修数据！");
        interfaceLogEntity.setCode("200");
        interfaceLogEntity.setEntityName("property_repair_crm");
        interfaceLogEntity.setErrorDate(new Date());
        interfaceLogRepository.create(interfaceLogEntity);

        /*************************保修单主表增加*****************************/
        PropertyRepairEntity propertyRepairs=new PropertyRepairEntity();

        propertyRepairs.setRepairId(id);//报修单id
        if(user!=null) {
            propertyRepairs.setCreateBy(user.getUserId());
        }else{
            propertyRepairs.setCreateBy(house.getMemberId());
        }
        if(house!=null) {
            propertyRepairs.setUserAddress(house.getAddress());//业主地址
            propertyRepairs.setRegionId(house.getProjectId());//项目id
            propertyRepairs.setRegionName(house.getProjectName());//项目名称
        }
        if(!StringUtil.isEmpty(repair.getUserName())){
            propertyRepairs.setUserName(repair.getUserName());//业主姓名
        }
        if(!StringUtil.isEmpty(repair.getUserPhone())){
            propertyRepairs.setUserPhone(repair.getUserPhone());//业主电话
        }
        propertyRepairs.setUserAddress(house.getAddress());//房屋地址
        if(!StringUtil.isEmpty(repair.getDepartment())) {
            propertyRepairs.setDepartment(repair.getDepartment());  //组 必填
        }
        if(!StringUtil.isEmpty(repair.getContent())){
            propertyRepairs.setContent(repair.getContent());    //描述
        }
        if(!StringUtil.isEmpty(repair.getRoomId())) {
            propertyRepairs.setRoomId(repair.getRoomId());
        }else{
            propertyRepairs.setRoomId(house.getRoomId());//房间id
        }
        propertyRepairs.setCreateDate(new Date());//创建时间
        propertyRepairs.setModifyDate(new Date()); //修改时间
        propertyRepairs.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
        propertyRepairs.setRepairWay("APP");//app
        propertyRepairs.setMemo("报修");
        propertyRepairs.setTaskStatus("0");//0为用户提交报修(业主)
        propertyRepairs.setState("已受理");
        PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
        propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
        propertyRepairTaskEntity.setRepairId(propertyRepairs.getRepairId());//报修单id
        propertyRepairTaskEntity.setTaskNode("提交报修");//任务节点
        propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
        propertyRepairTaskEntity.setTaskName("已创建");
        propertyRepairTaskEntity.setTaskStatus("6");
        propertyRepairTaskEntity.setTaskContent("您的报修信息已受理。");//任务内容
        propertyRepairTaskEntity.setReadStatus("0");//0为未读
        propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);

        propertyRepairRepository.saveRepair(propertyRepairs);
        repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
        return new SuccessApiResult(RectificationListDTO);
    }

    @Override
    public ApiResult savePropertyRepairForAppNew(PropertyRepairAppDTO repair, UserPropertyStaffEntity userPropertyStaffEntity) {
        if (repair == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if (StringUtil.isEmpty(repair.getRoomNum())) {
            return ErrorResource.getError("tip_00000563");//房间号不能为空
        }
        if (StringUtil.isEmpty(repair.getDepartment())) {
            return ErrorResource.getError("tip_00000577");//组信息不能为空
        }
        HouseInfoEntity house=houseInfoRepository.getHouseByRoomId(repair.getRoomNum());

        if(house == null){
            return ErrorResource.getError("tip_00000565");//房屋信息不存在
        }
        if(StringUtil.isEmpty(house.getProjectNum())){
            return ErrorResource.getError("tip_00000565");//房屋信息不存在
        }

        Object[] obj=rectificationRepository.getAllRepairById(repair.getRepairId(), repair.getAppId());
        if(obj!=null){
            PropertyRepairListDTO rectific =getrepairByID(null,null,obj);
            return new SuccessApiResult(rectific);
        }else{
            PropertyRepairCRMEntity propertyRepairCRMEntity=new PropertyRepairCRMEntity();
            String []ss=house.getProjectNum().split("-");
            String repairid=houseInfoRepository.getRepairidNew(ss[ss.length-1]+"-B");
            if(repairid.length()==1){
                repairid="000"+repairid;
            }
            if(repairid.length()==2){
                repairid="00"+repairid;
            }
            if(repairid.length()==3){
                repairid="0"+repairid;
            }
            String id=ss[ss.length-1]+"-B-"+DateUtils.getNow("yyyyMMdd")+"-A-"+repairid;
            propertyRepairCRMEntity.setRepairId(id);    //保修单id
            propertyRepairCRMEntity.setDepartment(repair.getDepartment());//组  必填
            if(!StringUtil.isEmpty(repair.getRoomId())){
                propertyRepairCRMEntity.setRoomId(repair.getRoomId());//房间id
            }else{
                propertyRepairCRMEntity.setRoomId(house.getRoomId());//房间id
            }
            propertyRepairCRMEntity.setRoomNum(repair.getRoomNum());//房间编码   必填
            if(!StringUtil.isEmpty(house.getMemberId())){
                propertyRepairCRMEntity.setMemberId(house.getMemberId());//会员id
            }
            propertyRepairCRMEntity.setUserName(repair.getUserName());//报修人姓名 必填
            propertyRepairCRMEntity.setUserPhone(repair.getUserPhone());//报修人电话 必填
            propertyRepairCRMEntity.setUserAddress(house.getAddress());//报修人地址
            propertyRepairCRMEntity.setCreateDate(new Date());
            propertyRepairCRMEntity.setModifyDate(new Date());  //修改时间
            propertyRepairCRMEntity.setClassifyOne(repair.getClassifyOne());    //一级分类
            propertyRepairCRMEntity.setClassifyTwo(repair.getClassifyTwo());    //二级分类
            propertyRepairCRMEntity.setClassifyThree(repair.getClassifyThree());//三级分类
            propertyRepairCRMEntity.setMode(repair.getMode());      //维修方式
            propertyRepairCRMEntity.setRepairDate(repair.getRepairDate());  //维修工时
            propertyRepairCRMEntity.setRoomLocation(repair.getRoomLocation());
            propertyRepairCRMEntity.setState("accepted");
            propertyRepairCRMEntity.setDuty(repair.getDuty());
            propertyRepairCRMEntity.setProblemLevel(repair.getProblemLevel());
            propertyRepairCRMEntity.setRepairWay("APP");
            propertyRepairCRMEntity.setContent(repair.getContent());    //描述
            propertyRepairCRMEntity.setDealWay(repair.getDealWay());    //处理方案
            propertyRepairCRMEntity.setDealMode(repair.getDealMode());  //处理方式  第三方 内部.......
            propertyRepairCRMEntity.setDutyCompanyOne(repair.getDutyCompanyOne()); //责任单位一
            propertyRepairCRMEntity.setDutyCompanyTwo(repair.getDutyCompanyTwo());//责任单位二
            propertyRepairCRMEntity.setDutyCompanyThree(repair.getDutyCompanyThree());//责任单位三
            propertyRepairCRMEntity.setRepairCompany(repair.getRepairCompany());    //维修单位
            propertyRepairCRMEntity.setxCoordinates((repair.getxCoordinates() == null || "".equals(repair.getxCoordinates())) ? null : new BigDecimal(repair.getxCoordinates()));
            propertyRepairCRMEntity.setyCoordinates((repair.getyCoordinates() == null || "".equals(repair.getyCoordinates())) ? null : new BigDecimal(repair.getyCoordinates()));
            if(!StringUtil.isEmpty(repair.getRepairManagerId())){
                propertyRepairCRMEntity.setSendName(userPropertyStaffEntity.getUserName());
                propertyRepairCRMEntity.setSendDate(new Date());
                propertyRepairCRMEntity.setRepairManagerId(repair.getRepairManagerId());
                propertyRepairCRMEntity.setDealPeople(repair.getRepairManagerId());
                UserPropertyStaffEntity UserStaff=rectificationRepository.getusername(repair.getRepairManagerId());
                if(UserStaff!=null){
                    propertyRepairCRMEntity.setRepairManager(UserStaff.getUserName());
                    propertyRepairCRMEntity.setDealPhone(UserStaff.getMobile());
                }
            }
            propertyRepairCRMEntity.setDealState(repair.getDealState());
            //暂时注释
            propertyRepairCRMEntity.setAppId(repair.getAppId());//校验唯一性
            //整改单图片
            List<RectifyImageDTO> qImageList = repair.getImageList(); //报修完成前list
            if(qImageList!=null && ! qImageList.isEmpty()){
                for(RectifyImageDTO rectifyImageDTO : qImageList){
                    PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                    propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                    propertyImageEntity.setUploadDate(new Date());//上传日期
                    propertyImageEntity.setImgFkId(id);//图片外键id
                    propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                    propertyImageEntity.setImageType("0");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                    propertyImageEntity.setState("0");//状态:0为有效；1为无效
                    propertyImageRepository.saveImage(propertyImageEntity);
                }
            }
            try{
                propertyRepairCRMEntity.setFailNum(0);
                propertyRepairCRMEntity.setFailType("0");
                propertyRepairRepository.saveRepairCrm(propertyRepairCRMEntity);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("质检APP调用报修接口:创建报修数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("property_repair_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
                /*************************保修单主表增加*****************************/
                PropertyRepairEntity propertyRepairs=new PropertyRepairEntity();

                propertyRepairs.setRepairId(id);//报修单id
                if(!StringUtil.isEmpty(house.getMemberId())){
                    propertyRepairs.setCreateBy(house.getMemberId());
                }
                if(house!=null) {
                    propertyRepairs.setUserAddress(house.getAddress());//业主地址
                    propertyRepairs.setRegionId(house.getProjectId());//项目id
                    propertyRepairs.setRegionName(house.getProjectName());//项目名称
                }
                if(!StringUtil.isEmpty(repair.getUserName())){
                    propertyRepairs.setUserName(repair.getUserName());//业主姓名
                }
                if(!StringUtil.isEmpty(repair.getUserPhone())){
                    propertyRepairs.setUserPhone(repair.getUserPhone());//业主电话
                }
                propertyRepairs.setUserAddress(house.getAddress());//房屋地址
                if(!StringUtil.isEmpty(repair.getDepartment())) {
                    propertyRepairs.setDepartment(repair.getDepartment());  //组 必填
                }
                if(!StringUtil.isEmpty(repair.getContent())){
                    propertyRepairs.setContent(repair.getContent());    //描述
                }
                if(!StringUtil.isEmpty(repair.getRoomId())) {
                    propertyRepairs.setRoomId(repair.getRoomId());
                }else{
                    propertyRepairs.setRoomId(house.getRoomId());//房间id
                }
                propertyRepairs.setCreateDate(new Date());//创建时间
                propertyRepairs.setModifyDate(new Date()); //修改时间
                propertyRepairs.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                propertyRepairs.setRepairWay("APP");//app
                propertyRepairs.setMemo("报修");
                propertyRepairs.setTaskStatus("0");//0为用户提交报修(业主)
                propertyRepairs.setState("已受理");
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairs.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskNode("提交报修");//任务节点
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                propertyRepairTaskEntity.setTaskName("已创建");
                propertyRepairTaskEntity.setTaskStatus("6");
                propertyRepairTaskEntity.setTaskContent("您的报修信息已受理。");//任务内容
                propertyRepairTaskEntity.setReadStatus("0");//0为未读
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);

                propertyRepairRepository.saveRepair(propertyRepairs);
                String checkCrmState=repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
                if(!"200".equals(checkCrmState) && !"".equals(checkCrmState)){
                    //调用接口失败
                    propertyRepairCRMEntity.setFailNum(1);
                    propertyRepairCRMEntity.setFailType("1");
                    propertyRepairRepository.updateRepairCrm(propertyRepairCRMEntity);
                }
            }catch (Exception e){
                PropertyRepairListDTO rectific =getrepairByID(repair.getRepairId(), repair.getAppId(),null);
                return new SuccessApiResult(rectific);
            }
            //成功直接查询返回(需修改)
            PropertyRepairListDTO rectific =getrepairByID(propertyRepairCRMEntity.getRepairId(), propertyRepairCRMEntity.getAppId(),null);
//            PropertyRepairListDTO rectific =getrepairByID(propertyRepairCRMEntity.getRepairId(), null,null);

            return new SuccessApiResult(rectific);
        }
    }

    @Override
    public int countMessage(String channelId, String type) {
        if(channelId==null || "".equals(channelId)){
            return 0;
        }
        if(type==null || "".equals(type)){
            return 0;
        }
        List<MessageTargetEntity>  MessageTokenlist=rectificationRepository.getMessagelist(channelId, type);
        try{
            if(MessageTokenlist!=null) {
                for (MessageTargetEntity MessageToken : MessageTokenlist) {
                    MessageTargetEntity MessageTar=new MessageTargetEntity();
                    MessageTar.setMessageReadStatus("1");
                    rectificationRepository.upMessageTar(MessageTar);
                }
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int pushMessage(String userid, String channelId, String type) {
        if(userid==null || "".equals(userid)){
            return 0;
        }
        if(channelId==null || "".equals(channelId)){
            return 0;
        }
        if(type==null || "".equals(type)){
            return 0;
        }
        List<MessageTokenEntity>  MessageTokenlist=rectificationRepository.getMessageTokenList(channelId, type);
        try{
            if(MessageTokenlist!=null){
                for(MessageTokenEntity MessageToken:MessageTokenlist){
//                    MessageTokenEntity MessageTokenEntity=new MessageToken;
                    MessageToken.setUserId(userid);
                    MessageToken.setTokenCreateTime(new Date());
                    rectificationRepository.updateMessageToken(MessageToken);
                }
            }else{
                MessageTokenEntity MessageTokenEntity=new MessageTokenEntity();
                MessageTokenEntity.setMobileType(Integer.parseInt(type));
                MessageTokenEntity.setUserId(userid);
                MessageTokenEntity.setMessageTokenNum(channelId);
                MessageTokenEntity.setTokenCreateTime(new Date());
                MessageTokenEntity.setMessageTokenId(channelId+DateUtils.format(new Date(),"yyyyMMddHHmmss"));
                rectificationRepository.saveMessageToken(MessageTokenEntity);
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public ThirdTypeClassUpTimeDTO getThirdClassNew(String id, String time) {
        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        ThirdTypeClassUpTimeDTO ThirdTypeClass=new ThirdTypeClassUpTimeDTO();
        List<ThirdTypeClassUPListDTO> ClassUplist=new ArrayList<ThirdTypeClassUPListDTO>();
        List<ThirdClassificationCommEntity> ClassTemporaryTimeList=rectificationRepository.getClassNewTimeList((id == null ? "" : id), time1);
        if(ClassTemporaryTimeList!=null){
            for(ThirdClassificationCommEntity ClassTimeEntity : ClassTemporaryTimeList){
                ThirdTypeClassUPListDTO ClassTimeEntityDTO =new ThirdTypeClassUPListDTO();
                ClassTimeEntityDTO.setId(ClassTimeEntity.getId() == null ? "" : ClassTimeEntity.getId().toString());
                ClassTimeEntityDTO.setCurrentId(ClassTimeEntity.getValue() == null ? "" : ClassTimeEntity.getValue());
                //新增排序字段
                ClassTimeEntityDTO.setItemOrder(-1);
                if (ClassTimeEntity.getItemOrder() != null ) {
                    ClassTimeEntityDTO.setItemOrder(ClassTimeEntity.getItemOrder());
                }

//                ClassTimeEntityDTO.setType(ClassTimeEntity.getType() == null ? "" : ClassTimeEntity.getType());
                ClassTimeEntityDTO.setGraded("3");
                ClassTimeEntityDTO.setTimeStamp(ClassTimeEntity.getTimeStamp() == null ? "" : DateUtils.format(ClassTimeEntity.getTimeStamp()));
                ClassTimeEntityDTO.setParentId(ClassTimeEntity.getSecondId() == null ? "" : ClassTimeEntity.getSecondId());
                //新增的三级分类别名三级分类别名
                if(StringUtil.isEmpty(ClassTimeEntity.getAlias())){
                    //别名为空：赋值label
                    ClassTimeEntityDTO.setName(ClassTimeEntity.getLabel() == null ? "" : ClassTimeEntity.getLabel());
                }else {
                    //别名为空：赋值alias
                    ClassTimeEntityDTO.setName(ClassTimeEntity.getAlias());
                }
//                ClassTimeEntityDTO.setStart(ClassTimeEntity.getStart() == null ? "" : ClassTimeEntity.getStart());
                ClassUplist.add(ClassTimeEntityDTO);
            }
            ThirdTypeClass.setList(ClassUplist);
            ThirdTypeClass.setId(ClassUplist.get(ClassUplist.size()-1).getId());
            ThirdTypeClass.setTimeStamp(ClassUplist.get(ClassUplist.size()-1).getTimeStamp());
        }
        return ThirdTypeClass;
    }

    @Override
    public int getRepairForTimeCountNew(List user, String time, String id) {
        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        int ss=rectificationRepository.getRepairForTimeCountNew(user, time1, id);
        return ss;
    }

    @Override
    public String getRepairForTimeCount(List user, String time, String id, String userid) {
        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        String ss=rectificationRepository.getRepairForTimeCount(user, time1, id, userid);
        return ss;
    }

    @Override
    public List<String> getRepairForTimedelete(List user, String time, String id, String userid) {
        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        List<String> delete=rectificationRepository.getRepairForTimedelete(user, time1, id, userid);
        return delete;
    }

    @Override
    public RepairCationUpTimeDTO getRepairForTimeNew(List user, String time, String id,String userid) {
        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        List<RectificationListDTO> RectificationList=new ArrayList<RectificationListDTO>();//具体数据
        RepairCationUpTimeDTO RepairCationUpTimeDTO=new RepairCationUpTimeDTO();
        List<Object[]> propertyListNew=rectificationRepository.getRepairForTimeNew(user, time1, id, userid);
        if(propertyListNew !=null && !propertyListNew.isEmpty()){
            for(Object[] obj : propertyListNew){
                List<ProjectReturnImageJsonDTO> imgJsonList = new ArrayList<>();
                List<ProjectReturnImageJsonDTO> imageJsonList = new ArrayList<>();
                RectificationListDTO rectific = new RectificationListDTO();
                rectific.setType("0");//保修单标识
                rectific.setPlantype("repair");
                rectific.setUptime(obj[0] == null ? "" : DateUtils.format((Date) obj[0]));//最后修改时间
                rectific.setRepairManager(obj[1] == null ? "" : obj[1].toString());//维修负责人
                rectific.setRoomId(obj[2] == null ? "" : obj[2].toString()); //房间id
                rectific.setRoomNum(obj[3] == null ? "" : obj[3].toString());//房间编码
                rectific.setMemberId(obj[4] == null ? "" : obj[4].toString());//报修人会员编号
                rectific.setClassifyOne(obj[5] == null ? "" : obj[5].toString());//一级分类
                rectific.setClassifyTwo(obj[6] == null ? "" : obj[6].toString());//二级分类
                rectific.setClassifyThree(obj[7] == null ? "" : obj[7].toString());//三级分类
                rectific.setMode(obj[8] == null ? "" : obj[8].toString());//维修方式
                rectific.setRepairDate(obj[9] == null ? "" : obj[9].toString());//维修工时
                rectific.setState(obj[10] == null ? "" : obj[10].toString());//订单状态
                rectific.setContent(obj[11] == null ? "" : obj[11].toString());//描述
                rectific.setDutyCompanyOne(obj[12] == null ? "" : obj[12].toString());//责任单位1
                rectific.setDutyCompanyTwo(obj[13] == null ? "" : obj[13].toString());//责任单位2
                rectific.setDutyCompanyThree(obj[14] == null ? "" : obj[14].toString());//责任单位3
                rectific.setDealPeople(obj[15] == null ? "" : obj[15].toString());//处理人
                rectific.setDealPeoplename(obj[16] == null ? "" : obj[16].toString());//处理人姓名
                rectific.setDealMode(obj[17] == null ? "" : obj[17].toString());//处理方式:内部/责任单位/第三方
                rectific.setDealPhone(obj[18] == null ? "" : obj[18].toString());//处理人电话
                rectific.setDealResult(obj[19] == null ? "" : obj[19].toString());//处理结果
                rectific.setDealCompleteDate(obj[20] == null ? "" : DateUtils.format((Date) obj[20]));//处理人完工时间
                rectific.setRoomLocation(obj[21] == null ? "" : obj[21].toString());//报修位置
                rectific.setTaskDate(obj[22] == null ? "" : DateUtils.format((Date) obj[22]));//接单时间
                rectific.setRepairId(obj[23] == null ? "" : obj[23].toString());//报修单号
                rectific.setUserPhone(obj[24] == null ? "" : obj[24].toString());//报修人电话
                rectific.setUserAddress(obj[25] == null ? "" : obj[25].toString());//报修人地址
                rectific.setUsername(obj[26] == null ? "" : obj[26].toString());//报修人姓名
                rectific.setCreateDate(obj[27] == null ? "" : DateUtils.format((Date) obj[27]));//报修时间 登记时间
                rectific.setProjectname(obj[28] == null ? "" : obj[28].toString());//所属项目name
                rectific.setProjectid(obj[29] == null ? "" : obj[29].toString());//项目id
                rectific.setAddress(obj[30] == null ? "" : obj[30].toString());//房屋地址
                rectific.setBuildnum(obj[31] == null ? "" : obj[31].toString());//楼栋编码
                rectific.setProjectNum(obj[32] == null ? "" : obj[32].toString());//所属项目编码
                rectific.setId(obj[33] == null ? "" : obj[33].toString());
                rectific.setRepairCompany(obj[34] == null ? "" : obj[34].toString());
                rectific.setDepartment(obj[35] == null ? "" : obj[35].toString());//部门
                rectific.setRepairManagerId(obj[36] == null ? "" : obj[36].toString());//内部负责人id
                rectific.setSupplierId(obj[37] == null ? "" : obj[37].toString());//责任单位负责人id
                rectific.setxCoordinates(obj[38] == null ? "" : obj[38].toString());
                rectific.setyCoordinates(obj[39] == null ? "" : obj[39].toString());
                List<PropertyImageEntity> image = rectificationRepository.getImageForId(obj[23] == null ? "" : obj[23].toString());
                if( image!=null) {
                    for (PropertyImageEntity imageEntity : image) {
                        ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                        RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId());
                        RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                        RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                        RcitifytDTO.setCaseId(obj[23] == null ? "" : obj[23].toString());
                        imageJsonList.add(RcitifytDTO);
                    }
                }
                rectific.setImageList(imageJsonList);//报修前的imageList
                //获取报修后image
                List<PropertyImageEntity> img = rectificationRepository.getImageForOver(obj[23] == null ? "" : obj[23].toString());
                if(img!=null) {
                    for (PropertyImageEntity imageEntity : img) {
                        ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                        RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId() );
                        RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                        RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                        RcitifytDTO.setCaseId(obj[23] == null ? "" : obj[23].toString());
                        imgJsonList.add(RcitifytDTO);
                    }
                }
                rectific.setImgList(imgJsonList);

                RectificationList.add(rectific);
            }

        }
        if(RectificationList.size()>0){
            RepairCationUpTimeDTO.setList(RectificationList);
            RepairCationUpTimeDTO.setId(RectificationList.get(RectificationList.size()-1).getId());
            RepairCationUpTimeDTO.setTimeStamp(RectificationList.get(RectificationList.size()-1).getUptime());
        }
        return RepairCationUpTimeDTO;
    }

    @Override
    public PlanActivityCountDTO getPlanActivityCountTimeId(String id, String time) {
        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        PlanActivityCountDTO PlanCount=new PlanActivityCountDTO();
        List<ActiveTemporaryTimeEntity> palnList=rectificationRepository.getActiveTemporaryCountList((id == null ? "" : id), time1);
        if(palnList!=null){
            for(ActiveTemporaryTimeEntity ActiveCountEntity:palnList){
                if("houseInternalPreInspection".equals(ActiveCountEntity.getType())){
                    PlanCount.setHouseInternalPreInspection("1");
                }
                if("clientOpendayActivity".equals(ActiveCountEntity.getType())){
                    PlanCount.setClientOpendayActivity("1");
                }
                if("centralizeDeliverHouse".equals(ActiveCountEntity.getType()) || "scatteredDeliverHouse".equals(ActiveCountEntity.getType())){
                    PlanCount.setCentralizeDeliverHouse("1");
                }
            }
        }
        return PlanCount;
    }

    @Override
    public PalnActivityUpTimeDTO getPlanActivityForTimeId(String id, String time,String projectNum) {
        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        PalnActivityUpTimeDTO  planActivity=new PalnActivityUpTimeDTO();
        List<PlanActivityUpTimeListDTO> palnList=new ArrayList<PlanActivityUpTimeListDTO>();
        List<ActiveTemporaryTimeEntity> ActivePlanList=rectificationRepository.getActiveTemporaryTimeList(id, time1, projectNum);
        if(ActivePlanList!=null){
            for(ActiveTemporaryTimeEntity ActiveTimeEntity : ActivePlanList){
                PlanActivityUpTimeListDTO PlanActivity =new PlanActivityUpTimeListDTO();
                PlanActivity.setId(ActiveTimeEntity.getId() == null ? "" : ActiveTimeEntity.getId().toString());
                PlanActivity.setTimeStamp(ActiveTimeEntity.getTimeStamp() == null ? "" : DateUtils.format(ActiveTimeEntity.getTimeStamp()));
                PlanActivity.setName(ActiveTimeEntity.getName() == null ? "" : ActiveTimeEntity.getName());
                PlanActivity.setStart(ActiveTimeEntity.getStart() == null ? "" : ActiveTimeEntity.getStart());
                PlanActivity.setCurrentId(ActiveTimeEntity.getCurrentId() == null ? "" : ActiveTimeEntity.getCurrentId());
                PlanActivity.setCurrentNum(ActiveTimeEntity.getCurrentNum() == null ? "" : ActiveTimeEntity.getCurrentNum());
                PlanActivity.setType(ActiveTimeEntity.getType() == null ? "" : ActiveTimeEntity.getType());
                PlanActivity.setGraded(ActiveTimeEntity.getGraded() == null ? "" : ActiveTimeEntity.getGraded());
                PlanActivity.setParentId(ActiveTimeEntity.getParentId() == null ? "" : ActiveTimeEntity.getParentId());
                PlanActivity.setParentNum(ActiveTimeEntity.getParentNum() == null ? "" : ActiveTimeEntity.getParentNum());
                PlanActivity.setPath(ActiveTimeEntity.getPath() == null ? "" : DateUtils.format(ActiveTimeEntity.getPath()));
                palnList.add(PlanActivity);
            }
            planActivity.setList(palnList);
            planActivity.setId(palnList.get(palnList.size() - 1).getId());
            planActivity.setTimeStamp(palnList.get(palnList.size()-1).getTimeStamp());
        }
        return planActivity;
    }

    @Override
    public BuildingMappingUpDTO getBuildingForTimeId(String id, String time,String projectNum) {
        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        BuildingMappingUpDTO BuildingMappingUpDTO=new BuildingMappingUpDTO();
        List<BuildingMappingTimeDTO> Buildinglist=new ArrayList<BuildingMappingTimeDTO>();
        List<BuildingMappingTimeEntity> BuildingMappingList=rectificationRepository.getBuildingMappingList(id, time1, projectNum);
        if(BuildingMappingList!=null){
            for(BuildingMappingTimeEntity BuildingMapping:BuildingMappingList){
                BuildingMappingTimeDTO BuildingDTO=new BuildingMappingTimeDTO();
                BuildingDTO.setId(BuildingMapping.getId() == null ? "" : BuildingMapping.getId().toString());
                BuildingDTO.setCurrentId(BuildingMapping.getCurrentId() == null ? "" : BuildingMapping.getCurrentId());
                BuildingDTO.setCurrentNum(BuildingMapping.getCurrentNum() == null ? "" : BuildingMapping.getCurrentNum());
                //修改此处
                BuildingDTO.setName(BuildingMapping.getName() == null ? "" : BuildingMapping.getName());
                if(!StringUtil.isEmpty(BuildingMapping.getBuildingAlias())){
                    BuildingDTO.setName(BuildingMapping.getBuildingAlias());
                }
                BuildingDTO.setAddress(BuildingMapping.getAddress() == null ? "" : BuildingMapping.getAddress());
                BuildingDTO.setGraded(BuildingMapping.getGraded() == null ? "" : BuildingMapping.getGraded());
                BuildingDTO.setParentId(BuildingMapping.getParentId() == null ? "" : BuildingMapping.getParentId());
                BuildingDTO.setParentNum(BuildingMapping.getParentNum() == null ? "" : BuildingMapping.getParentNum());
                BuildingDTO.setStart(BuildingMapping.getStart() == null ? "" : BuildingMapping.getStart());
                BuildingDTO.setTimeStamp(BuildingMapping.getTimeStamp() == null ? "" : DateUtils.format(BuildingMapping.getTimeStamp()));
                BuildingDTO.setPath(BuildingMapping.getPath() == null ? "" : BuildingMapping.getPath());
                Buildinglist.add(BuildingDTO);
            }
            BuildingMappingUpDTO.setList(Buildinglist);
            BuildingMappingUpDTO.setId(Buildinglist.get(Buildinglist.size() - 1).getId());
            BuildingMappingUpDTO.setTimeStamp(Buildinglist.get(Buildinglist.size() - 1).getTimeStamp());
        }

        return BuildingMappingUpDTO;
    }


    @Override
    public ThirdTypeClassUpTimeDTO getThirdClassForTimrId(String id, String time) {
        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        ThirdTypeClassUpTimeDTO ThirdTypeClass=new ThirdTypeClassUpTimeDTO();
        List<ThirdTypeClassUPListDTO> ClassUplist=new ArrayList<ThirdTypeClassUPListDTO>();
        List<ClassificationTemporaryTimeEntity> ClassTemporaryTimeList=rectificationRepository.getClassTemporaryTimeList((id == null ? "" : id), time1);
        if(ClassTemporaryTimeList!=null){
            for(ClassificationTemporaryTimeEntity ClassTimeEntity : ClassTemporaryTimeList){
                ThirdTypeClassUPListDTO ClassTimeEntityDTO =new ThirdTypeClassUPListDTO();
                ClassTimeEntityDTO.setId(ClassTimeEntity.getId() == null ? "" : ClassTimeEntity.getId().toString());
                ClassTimeEntityDTO.setCurrentId(ClassTimeEntity.getCurrentId() == null ? "" : ClassTimeEntity.getCurrentId());
                //
                ClassTimeEntityDTO.setName(ClassTimeEntity.getName() == null ? "" : ClassTimeEntity.getName());
                if(!StringUtil.isEmpty(ClassTimeEntity.getAlias())){
                    ClassTimeEntityDTO.setName(ClassTimeEntity.getAlias());
                }
                ClassTimeEntityDTO.setType(ClassTimeEntity.getType() == null ? "" : ClassTimeEntity.getType());
                ClassTimeEntityDTO.setGraded(ClassTimeEntity.getGraded() == null ? "" : ClassTimeEntity.getGraded());
                ClassTimeEntityDTO.setTimeStamp(ClassTimeEntity.getTimeStamp() == null ? "" : DateUtils.format(ClassTimeEntity.getTimeStamp()));
                ClassTimeEntityDTO.setParentId(ClassTimeEntity.getParentId() == null ? "" : ClassTimeEntity.getParentId());
                ClassTimeEntityDTO.setStart(ClassTimeEntity.getStart() == null ? "" : ClassTimeEntity.getStart());
                //由于和常用三级分类使用一个DTO,此处多余了itemOrder字段,该字段设为空字符串""
                ClassTimeEntityDTO.setItemOrder(-1);
                ClassUplist.add(ClassTimeEntityDTO);
            }
            ThirdTypeClass.setList(ClassUplist);
            ThirdTypeClass.setId(ClassUplist.get(ClassUplist.size()-1).getId());
            ThirdTypeClass.setTimeStamp(ClassUplist.get(ClassUplist.size()-1).getTimeStamp());
        }
        return ThirdTypeClass;
    }

    @Override
    public RepairCationUpTimeDTO getRepairForTime(Object[] projectid,Object[] groupid,String time,String id) {
        List<RectificationListDTO> RectificationList=new ArrayList<RectificationListDTO>();//具体数据
        if(groupid.length<1){
            return new RepairCationUpTimeDTO();
        }
        String a="";
        for(int i=0;i<groupid.length;i++){
            a=a+",'"+groupid[i]+"'";
        }
        if(!StringUtil.isEmpty(a)){
            a = a.substring(1);
        }

        if(projectid.length<1){
            return new RepairCationUpTimeDTO();
        }
        String b="";
        for(int i=0;i<projectid.length;i++){
            b=b+",'"+projectid[i]+"'";
        }
        if(!StringUtil.isEmpty(a)){
            b = b.substring(1);
        }

        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        RepairCationUpTimeDTO RepairCationUpTimeDTO=new RepairCationUpTimeDTO();
        List<PropertyRepairEntity> propertyList=rectificationRepository.getRepairForTime(b, a, time1, id);//部门 主表中查询所有保修单
        if(propertyList!=null){
            for(PropertyRepairEntity RepairEntity:propertyList){
                List<ProjectReturnImageJsonDTO> imgJsonList = new ArrayList<>();
                List<ProjectReturnImageJsonDTO> imageJsonList = new ArrayList<>();
                //保修前图片
                List<PropertyImageEntity> image = rectificationRepository.getImageForId(RepairEntity.getRepairId());
                RectificationListDTO rectific = new RectificationListDTO();
                if( image!=null) {
                    for (PropertyImageEntity imageEntity : image) {
                        ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                        RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId());
                        RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                        RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                        imageJsonList.add(RcitifytDTO);
                    }
                }
                rectific.setImageList(imageJsonList);//报修前的imageList
                //获取报修后image
                List<PropertyImageEntity> img = rectificationRepository.getImageForOver(RepairEntity.getRepairId());
                if(img!=null) {
                    for (PropertyImageEntity imageEntity : img) {
                        ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                        RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId() );
                        RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                        RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                        imgJsonList.add(RcitifytDTO);
                    }
                }
                rectific.setImgList(imgJsonList);
                rectific.setUptime(RepairEntity.getModifyDate() == null ? "" : DateUtils.format(RepairEntity.getModifyDate()));
                PropertyRepairCRMEntity RepairCRM=rectificationRepository.getRepairTimeList(RepairEntity.getRepairId());
                if(RepairCRM!=null){
                    //房屋位置
                    HouseInfoEntity houst=rectificationRepository.getProject(RepairCRM.getRoomNum());
                    //保修的位置
                    RoomLocationEntititly room=rectificationRepository.getRoomLocation(RepairCRM.getRoomLocation());
                    rectific.setType("0");//保修单标识
                    rectific.setPlantype("repair");
                    rectific.setRepairManager(RepairCRM.getRepairManager() == null ? "" : RepairCRM.getRepairManager());
                    rectific.setRoomId(RepairCRM.getRoomId() == null ? "" : RepairCRM.getRoomId()); //房间id
                    rectific.setRoomNum(RepairCRM.getRoomNum() == null ? "" : RepairCRM.getRoomNum());//房间编码
                    rectific.setMemberId(RepairCRM.getMemberId() == null ? "" : RepairCRM.getMemberId());//报修人会员编号
                    rectific.setClassifyOne(RepairCRM.getClassifyOne() == null ? "" : RepairCRM.getClassifyOne());//一级分类
                    rectific.setClassifyTwo(RepairCRM.getClassifyTwo() == null ? "" : RepairCRM.getClassifyTwo());//二级分类
                    rectific.setClassifyThree(RepairCRM.getClassifyThree() == null ? "" : RepairCRM.getClassifyThree());//三级分类
                    rectific.setMode(RepairCRM.getMode() == null ? "" : RepairCRM.getMode());//维修方式
                    rectific.setRepairDate(RepairCRM.getRepairDate() == null ? "" : RepairCRM.getRepairDate());
                    rectific.setState(RepairCRM.getState() == null ? "" : RepairCRM.getState());//
                    rectific.setContent(RepairCRM.getContent() == null ? "" : RepairCRM.getContent());
                    rectific.setDutyCompanyOne(RepairCRM.getDutyCompanyOne() == null ? "" : RepairCRM.getDutyCompanyOne());
                    rectific.setDutyCompanyTwo(RepairCRM.getDutyCompanyTwo() == null ? "" : RepairCRM.getDutyCompanyTwo());
                    rectific.setDutyCompanyThree(RepairCRM.getDutyCompanyThree() == null ? "" : RepairCRM.getDutyCompanyThree());
                    rectific.setDealPeoplename("");
                    rectific.setDealPeople(RepairCRM.getDealPeople() == null ? "" : RepairCRM.getDealPeople());
                    rectific.setDealMode(RepairCRM.getDealMode() == null ? "" : RepairCRM.getDealMode());
                    if(RepairCRM.getDealPeople()!=null){
                        UserPropertyStaffEntity userPropertyStaffEntity =userPropertyStaffRepository.get(RepairCRM.getDealPeople());
                        if(userPropertyStaffEntity!=null){
                            rectific.setDealPeoplename(userPropertyStaffEntity.getUserName()== null ? "" : userPropertyStaffEntity.getUserName());
                        }
                    }
                    rectific.setDealPhone(RepairCRM.getDealPhone() == null ? "" : RepairCRM.getDealPhone());
                    rectific.setDealResult(RepairCRM.getDealWay() == null ? "" : RepairCRM.getDealWay());
                    rectific.setDealCompleteDate(RepairCRM.getDealCompleteDate() == null ? "" : DateUtils.format(RepairCRM.getDealCompleteDate()));
                    if(room!=null){
                        rectific.setRoomLocation(room.getName() == null ? RepairCRM.getRoomLocation() : room.getName());
                    }else{
                        rectific.setRoomLocation("");
                    }
                    rectific.setTaskDate(RepairCRM.getTaskDate() == null ? "" : DateUtils.format(RepairCRM.getTaskDate()));
                    rectific.setRepairId(RepairEntity.getRepairId() == null ? "" : RepairEntity.getRepairId());//编号
                    rectific.setUserPhone(RepairEntity.getUserPhone() == null ? "" : RepairEntity.getUserPhone());
                    rectific.setUserAddress(RepairEntity.getUserAddress() == null ? "" : RepairEntity.getUserAddress());
                    rectific.setUsername(RepairEntity.getUserName() == null ? "" : RepairEntity.getUserName());
                    rectific.setCreateDate(RepairEntity.getCreateDate() == null ? "" : DateUtils.format(RepairEntity.getCreateDate()));
                    if(houst!=null){
                        rectific.setProjectname(houst.getProjectName() == null ? "" : houst.getProjectName());
                        rectific.setProjectid(houst.getProjectId() == null ? "" : houst.getProjectId());
                        rectific.setAddress(houst.getAddress() == null ? "" : houst.getAddress());
                        rectific.setBuildnum(houst.getBuildingNum() == null ? "" : houst.getBuildingNum());
                        rectific.setProjectNum(houst.getProjectNum() == null ? "" : houst.getProjectNum());
                    }else{
                        rectific.setProjectname("");
                        rectific.setProjectid("");
                        rectific.setAddress("");
                        rectific.setBuildnum("");
                        rectific.setProjectNum("");
                    }
                    rectific.setId(RepairEntity.getId()==null ? "" : RepairEntity.getId().toString());
                    RectificationList.add(rectific);
                }
            }
            if(RectificationList.size()>0){
                RepairCationUpTimeDTO.setList(RectificationList);
                RepairCationUpTimeDTO.setId(RectificationList.get(RectificationList.size()-1).getId());
                RepairCationUpTimeDTO.setTimeStamp(RectificationList.get(RectificationList.size()-1).getUptime());
            }
        }
        return RepairCationUpTimeDTO;
    }

    @Override
    public BuildingListTimeDTO getProjectBuildForTime(Object[] projectid, String time) {
        String a="";
        for(int i=0;i<projectid.length;i++){
            a=a+",'"+projectid[i]+"'";
        }
        if(!StringUtil.isEmpty(a)){
            a = a.substring(1);
        }
        String time1="";
        if(time!=null){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        BuildingListTimeDTO BuildingListTimeDTO=new BuildingListTimeDTO();
        List<BuildingListDTOJson> buildList=new ArrayList<BuildingListDTOJson>();
        String uptime="";
        List<Object> houseroom=rectificationRepository.getHouseinfoRoomList(a,time1);
        if(houseroom!=null){
            for(Object or:houseroom){
                Object[] object = (Object[])or;
                BuildingListDTOJson room=new BuildingListDTOJson();
                room.setId(object[0] == null ? "" : object[0].toString());
                room.setNum(object[1] == null ? "" : object[1].toString());
                room.setName(object[2] == null ? "" : object[2].toString());
                room.setParentId(object[3] == null ? "" : object[3].toString());
                room.setParentNum(object[4] == null ? "" : object[4].toString());
                room.setCreationTime(object[5] == null ? "" : object[5].toString());
                room.setUpTime(object[6] == null ? "" : object[6].toString());
                room.setRoomType(object[7] == null ? "" : object[7].toString());
                uptime=(object[6] == null ? "" : object[6].toString());
                room.setGraded("5");
                buildList.add(room);
            }
        }
        List<HouseInfoEntity> housefloor=rectificationRepository.getHouseinfoFloorList(a,time1);
        if(housefloor!=null){
            for(HouseInfoEntity floorlist:housefloor){
                BuildingListDTOJson floor=new BuildingListDTOJson();
                floor.setId(floorlist.getFloorId() == null ? "" : floorlist.getFloorId());
                floor.setNum(floorlist.getFloorNum() == null ? "" : floorlist.getFloorNum());
                floor.setName(floorlist.getFloor() == null ? "" : floorlist.getFloor());
                floor.setParentId(floorlist.getUnitId() == null ? "" : floorlist.getUnitId());
                floor.setParentNum(floorlist.getUnitNum() == null ? "" : floorlist.getUnitNum());
                floor.setCreationTime(floorlist.getCreateOn() == null ? "" : DateUtils.format(floorlist.getCreateOn()));
                floor.setUpTime(floorlist.getModifyOn() == null ? "" : DateUtils.format(floorlist.getModifyOn()));
                floor.setRoomType("");//只有room对应户型
                floor.setGraded("4");
                buildList.add(floor);
            }
        }
        List<HouseInfoEntity> houseunit=rectificationRepository.getHouseinfoUnitList(a,time1);
        if(houseunit!=null){
            for(HouseInfoEntity unitlist:houseunit){
                BuildingListDTOJson unit=new BuildingListDTOJson();
                unit.setId(unitlist.getUnitId() == null ? "" : unitlist.getUnitId());
                unit.setNum(unitlist.getUnitNum() == null ? "" : unitlist.getUnitNum());
                unit.setName(unitlist.getUnit() == null ? "" : unitlist.getUnit());
                unit.setParentId(unitlist.getBuildingId() == null ? "" : unitlist.getBuildingId());
                unit.setParentNum(unitlist.getBuildingNum() == null ? "" : unitlist.getBuildingNum());
                unit.setCreationTime(unitlist.getCreateOn() == null ? "" : DateUtils.format(unitlist.getCreateOn()));
                unit.setUpTime(unitlist.getModifyOn() == null ? "" : DateUtils.format(unitlist.getModifyOn()));
                unit.setRoomType("");//只有room对应户型
                unit.setGraded("3");
                buildList.add(unit);
            }
        }
        List<HouseInfoEntity> housebuild=rectificationRepository.getHouseinfoBuildList(a, time1);
        if(housebuild!=null){
            for(HouseInfoEntity buildlist:housebuild){
                BuildingListDTOJson build=new BuildingListDTOJson();
                build.setId(buildlist.getBuildingId() == null ? "" : buildlist.getBuildingId());
                build.setNum(buildlist.getBuildingNum() == null ? "" : buildlist.getBuildingNum());
                build.setName(buildlist.getBuildingRecord() == null ? "" : buildlist.getBuildingRecord());
                build.setParentId(buildlist.getProjectId() == null ? "" : buildlist.getProjectId());
                build.setParentNum(buildlist.getProjectNum() == null ? "" : buildlist.getProjectNum());
                build.setCreationTime(buildlist.getCreateOn() == null ? "" : DateUtils.format(buildlist.getCreateOn()));
                build.setUpTime(buildlist.getModifyOn() == null ? "" : DateUtils.format(buildlist.getModifyOn()));
                build.setRoomType("");//只有room对应户型
                build.setGraded("2");
                buildList.add(build);
            }
        }
        List<HouseInfoEntity> houseproject=rectificationRepository.getHouseinfoProjectList(a, time1);
        if(houseproject!=null){
            for(HouseInfoEntity projectlist:houseproject){
                BuildingListDTOJson project=new BuildingListDTOJson();
                project.setId(projectlist.getProjectId() == null ? "" : projectlist.getProjectId());
                project.setNum(projectlist.getProjectNum() == null ? "" : projectlist.getProjectNum());
                project.setName(projectlist.getProjectName() == null ? "" : projectlist.getProjectName());
                project.setParentId("");// 项目为顶级
                project.setParentNum("");//项目为顶级
                project.setCreationTime(projectlist.getCreateOn() == null ? "" : DateUtils.format(projectlist.getCreateOn()));
                project.setUpTime(projectlist.getModifyOn() == null ? "" : DateUtils.format(projectlist.getModifyOn()));
                project.setRoomType("");//只有room对应户型
                project.setGraded("1");
                buildList.add(project);
            }
        }
        BuildingListTimeDTO.setBuildingList(buildList);
        BuildingListTimeDTO.setDatetime(uptime);
        return BuildingListTimeDTO;
    }

    @Override
    public PlanUpTimeListDTO getProjectPlanListForTime(Object[] projectid, String time) {
        PlanUpTimeListDTO PlanUpTimeList=new PlanUpTimeListDTO();
        String a="";
        for(int i=0;i<projectid.length;i++){
            a=a+",'"+projectid[i]+"'";
        }
        if(!StringUtil.isEmpty(a)){
            a = a.substring(1);
        }
        String time1="";
        if(time!=null){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        String upTime="";
        List<PlanListDTOJson> PlanListDTOJsonList=new ArrayList<PlanListDTOJson>();
        List<HouseProjectEntity> housList=rectificationRepository.getRepairForprojectTime(a, time1);
        if(housList!=null){
            for(HouseProjectEntity HouseProject:housList){
                PlanListDTOJson project=new PlanListDTOJson();
                project.setId(HouseProject.getId() == null ? "" : HouseProject.getId());
                project.setNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                project.setName(HouseProject.getName() == null ? "" : HouseProject.getName());
                project.setParentNum("");
                project.setParentId("");
                project.setType("");
                project.setRoomtype("");
                project.setUpTime(HouseProject.getModifyOn() == null ? "" : DateUtils.format(HouseProject.getModifyOn()));
                project.setCreationTime(HouseProject.getCreateOn() == null ? "" : DateUtils.format(HouseProject.getCreateOn()));
                project.setGraded("1");
                PlanListDTOJsonList.add(project);
            }
        }
        List<DeliveryPlanCrmEntity> DeliveryPlanCrmList=rectificationRepository.getPlanCrmFroProject(time1, a);//查询批次交付计划
        if(DeliveryPlanCrmList !=null){
            for(DeliveryPlanCrmEntity DeliveryPlan : DeliveryPlanCrmList){
                PlanListDTOJson plan=new PlanListDTOJson();
                plan.setId(DeliveryPlan.getId() == null ? "" : DeliveryPlan.getId());
                plan.setNum(DeliveryPlan.getPlanNum() == null ? "" : DeliveryPlan.getPlanNum());
                plan.setName(DeliveryPlan.getPlanName() == null ? "" : DeliveryPlan.getPlanName());
                plan.setParentNum(DeliveryPlan.getProjectNum() == null ? "" : DeliveryPlan.getProjectNum());
                plan.setParentId("");
                plan.setRoomtype("");
                plan.setGraded("2");
                plan.setType(DeliveryPlan.getPlanType() == null ? "" : DeliveryPlan.getPlanType());
                plan.setUpTime(DeliveryPlan.getPlanStart() == null ? "" : DateUtils.format(DeliveryPlan.getPlanStart()));
                upTime=(DeliveryPlan.getPlanStart() == null ? "" : DateUtils.format(DeliveryPlan.getPlanStart()));
                plan.setCreationTime(DeliveryPlan.getPlanStart() == null ? "" : DateUtils.format(DeliveryPlan.getPlanStart()));
                PlanListDTOJsonList.add(plan);
                List<Object> planroom=rectificationRepository.getPlanRoomList(DeliveryPlan.getId());//房间
                if(planroom!=null){
                    for(Object or:planroom){
                        PlanListDTOJson room=new PlanListDTOJson();
                        Object[] object = (Object[])or;
                        room.setId(object[0] == null ? "" : object[0].toString());
                        room.setName(object[1] == null ? "" : object[1].toString());
                        room.setNum(object[2] == null ? "" : object[2].toString());
                        room.setRoomtype(object[3] == null ? "" : object[3].toString());
                        room.setParentId(object[4] == null ? "" : object[4].toString());
                        room.setParentNum(object[5] == null ? "" : object[5].toString());
                        room.setCreationTime(object[6] == null ? "" : object[6].toString());
                        room.setUpTime(object[7] == null ? "" : object[7].toString());
                        room.setType("");
                        room.setGraded("6");
                        PlanListDTOJsonList.add(room);
                    }
                }
                List<Object> planfloor=rectificationRepository.getPlanFloorList(DeliveryPlan.getId());//楼层
                if(planfloor!=null){
                    for(Object of:planfloor){
                        PlanListDTOJson floor=new PlanListDTOJson();
                        Object[] object = (Object[])of;
                        floor.setId(object[0] == null ? "" : object[0].toString());
                        floor.setName(object[2] == null ? "" : object[2].toString());
                        floor.setNum(object[1] == null ? "" : object[1].toString());
                        floor.setParentId(object[3] == null ? "" : object[3].toString());
                        floor.setParentNum(object[4] == null ? "" : object[4].toString());
                        floor.setCreationTime(object[5] == null ? "" : object[5].toString());
                        floor.setUpTime(object[6] == null ? "" : object[6].toString());
                        floor.setType("");
                        floor.setRoomtype("");
                        floor.setGraded("5");
                        PlanListDTOJsonList.add(floor);
                    }
                }
                List<Object> planunit=rectificationRepository.getPlanUnitList(DeliveryPlan.getId());//单元
                if(planunit!=null){
                    for(Object ou:planunit){
                        PlanListDTOJson unit=new PlanListDTOJson();
                        Object[] object = (Object[])ou;
                        unit.setId(object[0] == null ? "" : object[0].toString());
                        unit.setName(object[1] == null ? "" : object[1].toString());
                        unit.setNum(object[2] == null ? "" : object[2].toString());
                        unit.setParentId(object[3] == null ? "" : object[3].toString());
                        unit.setParentNum(object[4] == null ? "" : object[4].toString());
                        unit.setCreationTime(object[5] == null ? "" : object[5].toString());
                        unit.setUpTime(object[6] == null ? "" : object[6].toString());
                        unit.setType("");
                        unit.setRoomtype("");
                        unit.setGraded("4");
                        PlanListDTOJsonList.add(unit);
                    }
                }
                List<Object> planbuild=rectificationRepository.getPlanBuildList(DeliveryPlan.getId());//楼栋
                if(planbuild!=null){
                    for(Object ob:planbuild){
                        PlanListDTOJson build=new PlanListDTOJson();
                        Object[] object = (Object[])ob;
                        build.setId(object[0] == null ? "" : object[0].toString());
                        build.setName(object[1] == null ? "" : object[1].toString());
                        build.setNum(object[2] == null ? "" : object[2].toString());
                        build.setCreationTime(object[3] == null ? "" : object[3].toString());
                        build.setUpTime(object[4] == null ? "" : object[4].toString());
                        build.setParentId(DeliveryPlan.getId() == null ? "" : DeliveryPlan.getId());
                        build.setParentNum(DeliveryPlan.getPlanNum() == null ? "" : DeliveryPlan.getPlanNum());
                        build.setType("");
                        build.setRoomtype("");
                        build.setGraded("3");
                        PlanListDTOJsonList.add(build);
                    }
                }
            }
        }
        PlanUpTimeList.setPlanListDTOJson(PlanListDTOJsonList);
        PlanUpTimeList.setDateTime(upTime);
        return PlanUpTimeList;
    }

    @Override
    public int getBuildCountForTime(Object[] groupid, String time) {
        String a="";
        for(int i=0;i<groupid.length;i++){
            a=a+",'"+groupid[i]+"'";
        }
        if(!StringUtil.isEmpty(a)){
            a = a.substring(1);
        }
        try{
            String time1="";
            if(time!=null){
                time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
            }
            int s=rectificationRepository.getRoomCountList(a, time1);
            return s;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int getProjectPlanCountForTime(Object[] projectid, String time) {
        String a="";
        for(int i=0;i<projectid.length;i++){
            a=a+",'"+projectid[i]+"'";
        }
        if(!StringUtil.isEmpty(a)){
            a = a.substring(1);
        }
        try{
            String time1="";
            if(time!=null){
                time1=DateUtils.format(DateUtils.parse(time,"yyyyMMddHHmmss"));
            }
            int s=rectificationRepository.getPlanCountFro(time1, a);
            return s;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<RectificationListDTO> getRectificationList(String id,String companyId) {
        List<ProjectReturnImageJsonDTO> imageJsonList =  null;//
        List<ProjectReturnImageJsonDTO> imgJsonList =  null;//
        //
        List<RectificationListDTO> RectificationList=new ArrayList<RectificationListDTO>();//具体数据
        SimpleDateFormat DF =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<PropertyRepairEntity> propertyList=rectificationRepository.getAllList(companyId);//部门 主表中查询所有保修单

        if(propertyList!=null){
            for(PropertyRepairEntity propertyReapir:propertyList){
                String rommname="";
                imageJsonList = new ArrayList<ProjectReturnImageJsonDTO>();
                imgJsonList =  new ArrayList<ProjectReturnImageJsonDTO>();
                //保修前图片
                List<PropertyImageEntity> image = rectificationRepository.getImageForId(propertyReapir.getRepairId());
                RectificationListDTO rectific = new RectificationListDTO();
                if( image!=null) {
                    for (PropertyImageEntity imageEntity : image) {
                        ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                        RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId());
                        RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                        RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                        imageJsonList.add(RcitifytDTO);
                    }
                }
                rectific.setImageList(imageJsonList);//报修前的imageList
                //获取报修后image
                List<PropertyImageEntity> img = rectificationRepository.getImageForOver(propertyReapir.getRepairId());
                if(img!=null) {
                    for (PropertyImageEntity imageEntity : img) {
                        ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                        RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId() );
                        RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                        RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                        imgJsonList.add(RcitifytDTO);
                    }
                }
                rectific.setImgList(imgJsonList);
                //crm保修单数据
                PropertyRepairCRMEntity PropertyRepairty=new PropertyRepairCRMEntity();

                if(propertyReapir.getState().equals("已受理")){
                    PropertyRepairty=rectificationRepository.getQueryForidList(propertyReapir.getRepairId());
                }else{
                    PropertyRepairty=rectificationRepository.getRecitifyList(id, propertyReapir.getRepairId());
                }
                if(PropertyRepairty!=null){
                    //房屋位置
                    HouseInfoEntity houst=rectificationRepository.getProject(PropertyRepairty.getRoomNum());
                    //保修的位置
                    RoomLocationEntititly room=rectificationRepository.getRoomLocation(PropertyRepairty.getRoomLocation());
                    rectific.setType("0");//保修单标识

                    rectific.setRoomId(PropertyRepairty.getRoomId() == null ? "" : PropertyRepairty.getRoomId()); //房间id
                    rectific.setRoomNum(PropertyRepairty.getRoomNum() == null ? "" : PropertyRepairty.getRoomNum());//房间编码
                    rectific.setMemberId(PropertyRepairty.getMemberId() == null ? "" : PropertyRepairty.getMemberId());//报修人会员编号
                    rectific.setClassifyOne(PropertyRepairty.getClassifyOne() == null ? "" : PropertyRepairty.getClassifyOne());//一级分类
                    rectific.setClassifyTwo(PropertyRepairty.getClassifyTwo() == null ? "" : PropertyRepairty.getClassifyTwo());//二级分类
                    rectific.setClassifyThree(PropertyRepairty.getClassifyThree() == null ? "" : PropertyRepairty.getClassifyThree());//三级分类
                    rectific.setMode(PropertyRepairty.getMode() == null ? "" : PropertyRepairty.getMode());//维修方式
                    rectific.setState(PropertyRepairty.getState() == null ? "" : PropertyRepairty.getState());//
                    rectific.setContent(PropertyRepairty.getContent() == null ? "" : PropertyRepairty.getContent());
                    rectific.setDutyCompanyOne(PropertyRepairty.getDutyCompanyOne() == null ? "" : PropertyRepairty.getDutyCompanyOne());
                    rectific.setPlantype("repair");
                    rectific.setDealPeoplename("");
                    rectific.setDealPeople("");
                    if(PropertyRepairty.getDealPeople()!=null){
                        UserPropertyStaffEntity userPropertyStaffEntity =userPropertyStaffRepository.get(PropertyRepairty.getDealPeople());
                        if(userPropertyStaffEntity!=null){
                            rectific.setDealPeoplename(userPropertyStaffEntity.getUserName()== null ? "" : userPropertyStaffEntity.getUserName());
                        }
                        rectific.setDealPeople(PropertyRepairty.getDealPeople() == null ? "" : PropertyRepairty.getDealPeople());
                    }
                    rectific.setDealPhone(PropertyRepairty.getDealPhone() == null ? "" : PropertyRepairty.getDealPhone());
                    rectific.setDealResult(PropertyRepairty.getDealResult() == null ? "" : PropertyRepairty.getDealResult());
                    rectific.setDealCompleteDate(PropertyRepairty.getDealCompleteDate() == null ? "" : DF.format(PropertyRepairty.getDealCompleteDate()));
                    if(room!=null){
                        rectific.setRoomLocation(room.getName() == null ? PropertyRepairty.getRoomLocation() : room.getName());
                    }else{
                        rectific.setRoomLocation("");
                    }
                    rectific.setTaskDate(PropertyRepairty.getTaskDate() == null ? "" : DF.format(PropertyRepairty.getTaskDate()));
                    rectific.setRepairId(propertyReapir.getRepairId() == null ? "" : propertyReapir.getRepairId());//编号
                    rectific.setUserPhone(propertyReapir.getUserPhone() == null ? "" : propertyReapir.getUserPhone());
                    rectific.setUserAddress(propertyReapir.getUserAddress() == null ? "" : propertyReapir.getUserAddress());
                    rectific.setUsername(propertyReapir.getUserName() == null ? "" : propertyReapir.getUserName());
                    rectific.setCreateDate(propertyReapir.getCreateDate() == null ? "" : DF.format(propertyReapir.getCreateDate()) );
                    String projectname="";
                    String projectid="";
                    String address="";
                    String buidnum="";
                    if(houst!=null){
                        rectific.setProjectname(houst.getProjectName() == null ? "" : houst.getProjectName());
                        rectific.setProjectid(houst.getProjectId() == null ? "" : houst.getProjectId());
                        rectific.setAddress(houst.getAddress() == null ? "" : houst.getAddress());
                        rectific.setBuildnum(houst.getBuildingNum() == null ? "" : houst.getBuildingNum());
                    }else{
                        rectific.setProjectname(projectname);
                        rectific.setProjectid(projectid);
                        rectific.setAddress(address);
                        rectific.setBuildnum(buidnum);
                    }
                    RectificationList.add(rectific);
                }


            }
        }
        return RectificationList;

    }
    @Override
    public ReturnJsonDTO setRectificAction(List<RectificationListDTO> PropertyAllList,String id,String username) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ReturnJsonDTO ReturnJson=new ReturnJsonDTO();
        try{
            List<String> scuuess=new ArrayList<>();
            List<String> fail=new ArrayList<>();
            for (RectificationListDTO Rectification : PropertyAllList){
                PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(Rectification.getRepairId());
                if(DateUtils.parse(Rectification.getUptime(),"yyyy-MM-dd HH:mm:ss").compareTo(propertyRepairInfo.getModifyDate())<0){
                    fail.add(propertyRepairInfo.getRepairId());
                }else{
                    PropertyRepairCRMEntity pprcrm=rectificationRepository.getdelepople(Rectification.getRepairId());
                    //修改报修单:未完成改为未评价
                    if("processing".equals(Rectification.getState())){
                        propertyRepairInfo.setModifyBy(Rectification.getDealPeople());
//                        propertyRepairInfo.setModifyDate(DateUtils.getDate());
                        PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                        propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                        propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                        propertyRepairTaskEntity.setTaskNode("正在处理中");//任务节点
                        //propertyRepairTaskEntity.setTaskUserId(user.getStaffId());//接单人
                        propertyRepairTaskEntity.setTaskContent(Rectification.getDealResult());//任务内容
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
                        //保修单图片
                        propertyImageRepository.deleteByFkId(Rectification.getRepairId(), "2");//先删除
                        List<ProjectReturnImageJsonDTO> qImageList = Rectification.getImgendList();
                        if (qImageList != null && !qImageList.isEmpty()) {
                            for(ProjectReturnImageJsonDTO image:qImageList){
                                PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                                propertyImageEntity.setImageId(image.getImageId());//图片id
                                propertyImageEntity.setUploadDate(new Date());//上传日期
                                if(image.getCaseId()==null || "".equals(image.getCaseId())){
                                    propertyImageEntity.setImgFkId(Rectification.getRepairId() ==null ? "" : Rectification.getRepairId());//图片外键id
                                }else{
                                    propertyImageEntity.setImgFkId(image.getCaseId());//图片外键id
                                }
                                propertyImageEntity.setImagePath(image.getImageUrl());//图片路径
                                propertyImageEntity.setImageType("2");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                                propertyImageEntity.setState("0");//状态:0为有效；1为无效
                                propertyImageRepository.saveImage(propertyImageEntity);
                            }
                        }
                        if(Rectification.getClassifyOne()!=null){
                            pprcrm.setClassifyOne(Rectification.getClassifyOne());
                        }
                        if(Rectification.getClassifyTwo()!=null){
                            pprcrm.setClassifyTwo(Rectification.getClassifyTwo());
                        }
                        if(Rectification.getClassifyThree()!=null){
                            pprcrm.setClassifyThree(Rectification.getClassifyThree());
                        }
                        if(Rectification.getRepairDate()!=null){
                            pprcrm.setRepairDate(Rectification.getRepairDate());
                        }
                        if(Rectification.getDutyCompanyOne()!=null){
                            pprcrm.setDutyCompanyOne(Rectification.getDutyCompanyOne());
                        }
                        if(Rectification.getDutyCompanyTwo()!=null){
                            pprcrm.setDutyCompanyTwo(Rectification.getDutyCompanyTwo());
                        }
                        if(Rectification.getDutyCompanyThree()!=null){
                            pprcrm.setDutyCompanyThree(Rectification.getDutyCompanyThree());
                        }
                        if(Rectification.getMode()!=null){
                            pprcrm.setMode(Rectification.getMode());
                        }
                        if(Rectification.getDealMode()!=null){
                            pprcrm.setDealMode(Rectification.getDealMode());
                        }
                        if(!StringUtil.isEmpty(username)){
                            pprcrm.setRepairManager(username);
                        }
                        if(Rectification.getDealPeople()!=null){
                            pprcrm.setDealPeople(id);
                        }
                        if(Rectification.getDealPhone()!=null){
                            pprcrm.setDealPhone(Rectification.getDealPhone());
                        }
                        if(Rectification.getDealResult()!=null){
                            pprcrm.setDealWay(Rectification.getDealResult());
                        }
//                        pprcrm.setDealCompleteDate(DateUtils.getDate());
                        if(Rectification.getRepairCompany()!=null){
                            pprcrm.setRepairCompany(Rectification.getRepairCompany());
                        }
                        propertyRepairInfo.setModifyBy(id);
                        propertyRepairInfo.setModifyDate(DateUtils.getDate());
                        repairClientService.getPropertyRepair(pprcrm, null);
                        propertyRepairRepository.updateRepair(propertyRepairInfo);
                        rectificationRepository.updateProperty(pprcrm);
                        propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                        scuuess.add(propertyRepairInfo.getRepairId());
                    }
                    if("completed".equals(Rectification.getState())){
                        propertyRepairInfo.setState("已完成");//状态由正在处理改为已完成
                        propertyRepairInfo.setModifyBy(Rectification.getDealPeople());
                        propertyRepairInfo.setModifyDate(DateUtils.getDate());
                        propertyRepairInfo.setCompleteDate(DateUtils.getDate());
                        //添加任务：新的任务
                        PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                        propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                        propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                        propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                        propertyRepairTaskEntity.setReadStatus("0");
                        //获取报修类型
                        //PropertyRepairTaskEntity repairTaskEntities=rectificationRepository.getRepairTaskById(propertyRepairInfo.getRepairId());
                        List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                        if (repairTaskEntities.size() > 0) {
                            if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                                propertyRepairInfo.setTaskStatus("10");//10为维修完成
                                propertyRepairTaskEntity.setTaskName("已完成");//任务名称
                                propertyRepairTaskEntity.setTaskNode("维修完成");//任务节点
                                propertyRepairTaskEntity.setTaskStatus("10");//10为维修完成
                                propertyRepairTaskEntity.setTaskContent("您的报修信息已完成。");//任务内容
                            } else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                                propertyRepairInfo.setTaskStatus("16");//16为回访完成
                                propertyRepairTaskEntity.setTaskName("已解决");//任务名称
                                propertyRepairTaskEntity.setTaskNode("投诉解决");//任务节点
                                propertyRepairTaskEntity.setTaskStatus("16");//16为回访完成
                                propertyRepairTaskEntity.setTaskContent("本次投诉已解决。");//任务内容
                            }
                        }
                        //保修单图片
                        propertyImageRepository.deleteByFkId(Rectification.getRepairId(), "2");//先删除
                        List<ProjectReturnImageJsonDTO> qImageList = Rectification.getImgendList();
                        if (qImageList != null && !qImageList.isEmpty()) {
                            for(ProjectReturnImageJsonDTO image:qImageList){
                                PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                                propertyImageEntity.setImageId(image.getImageId());//图片id
                                propertyImageEntity.setUploadDate(new Date());//上传日期
                                if(image.getCaseId()==null || "".equals(image.getCaseId())){
                                    propertyImageEntity.setImgFkId(Rectification.getRepairId() ==null ? "" : Rectification.getRepairId());//图片外键id
                                }else{
                                    propertyImageEntity.setImgFkId(image.getCaseId());//图片外键id
                                }
                                propertyImageEntity.setImagePath(image.getImageUrl());//图片路径
                                propertyImageEntity.setImageType("2");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                                propertyImageEntity.setState("0");//状态:0为有效；1为无效
                                propertyImageRepository.saveImage(propertyImageEntity);
                            }
                        }
                        propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                        pprcrm.setState("completed");
                        if(Rectification.getRepairId()!=null){
                            if(Rectification.getClassifyOne()!=null){
                                pprcrm.setClassifyOne(Rectification.getClassifyOne());
                            }
                            if(Rectification.getClassifyTwo()!=null){
                                pprcrm.setClassifyTwo(Rectification.getClassifyTwo());
                            }
                            if(Rectification.getClassifyThree()!=null){
                                pprcrm.setClassifyThree(Rectification.getClassifyThree());
                            }
                            if(Rectification.getRepairDate()!=null){
                                pprcrm.setRepairDate(Rectification.getRepairDate());
                            }
                            if(Rectification.getDutyCompanyOne()!=null){
                                pprcrm.setDutyCompanyOne(Rectification.getDutyCompanyOne());
                            }
                            if(Rectification.getDutyCompanyTwo()!=null){
                                pprcrm.setDutyCompanyTwo(Rectification.getDutyCompanyTwo());
                            }
                            if(Rectification.getDutyCompanyThree()!=null){
                                pprcrm.setDutyCompanyThree(Rectification.getDutyCompanyThree());
                            }
                            if(Rectification.getMode()!=null){
                                pprcrm.setMode(Rectification.getMode());
                            }
                            if(Rectification.getDealMode()!=null){
                                pprcrm.setDealMode(Rectification.getDealMode());
                            }
                            if(!StringUtil.isEmpty(username)){
                                pprcrm.setRepairManager(username);
                            }

                            if(Rectification.getDealPeople()!=null){
                                pprcrm.setDealPeople(id);
                            }
                            if(Rectification.getRepairCompany()!=null){
                                pprcrm.setRepairCompany(Rectification.getRepairCompany());
                            }
                            if(Rectification.getDealPhone()!=null){
                                pprcrm.setDealPhone(Rectification.getDealPhone());
                            }
                            if(Rectification.getDealResult()!=null){
                                pprcrm.setDealWay(Rectification.getDealResult());
                            }
                            pprcrm.setDealCompleteDate(DateUtils.getDate());
                            repairClientService.getPropertyRepair(pprcrm, null);
                            rectificationRepository.updateProperty(pprcrm);
                            propertyRepairRepository.updateRepair(propertyRepairInfo);
                            scuuess.add(propertyRepairInfo.getRepairId());
                        }
                    }
                }
                ReturnJson.setFail(fail);
                ReturnJson.setSuccess(scuuess);
            }
            return ReturnJson;
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    @Override
    public RectipierJsomDTO getRectificaType(String id, String type, String name) {
        PropertyRepairCRMEntity property =rectificationRepository.getdelepople(id);
        RectipierJsomDTO hsonDTO=new RectipierJsomDTO();
        if(!property.getDealPeople().equals("") && property.getDealPeople()!=null){
            if(!property.getDealPeople().equals(name)){
                if(type.equals("1")){
                    property.setDealPeople(name);
                    rectificationRepository.updateProperty(property);
                    hsonDTO.setId(id);
                    hsonDTO.setType("0003");//第二次提交接受订单  修改服务器当前id的delepeople
                }else if(type.equals("0")){
                    hsonDTO.setId(id);
                    hsonDTO.setType("0002");//提示已经有人接收该订单
                }
            }else{
                hsonDTO.setId(id);
                hsonDTO.setType("0004");//自己的订单不需要修改
            }
        }else{
            property.setDealPeople(name);
            property.setState("processing");
            rectificationRepository.updateProperty(property);
            hsonDTO.setId(id);
            hsonDTO.setType("0001");//当前订单没有任何人接收  当前元供接收该报修单
        }
        return hsonDTO;
    }
    /**
     * 内部预验
     * */
    @Override
    public List<RectifyPlanDTO> getInternalPreInspection(String id,String type) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<RectifyPlanDTO> RectifyPlanList=new ArrayList<RectifyPlanDTO>();
        List<HouseProjectEntity> housList=rectificationRepository.gethousListforcompid(id);
        if(housList!=null){
            for(HouseProjectEntity HouseProjectEntity:housList){
                RectifyPlanDTO RectifyPlan=new RectifyPlanDTO();
                RectifyPlan.setId(HouseProjectEntity.getId() == null ? "" : HouseProjectEntity.getId());
                RectifyPlan.setName(HouseProjectEntity.getName() == null ? "" : HouseProjectEntity.getName());
                RectifyPlan.setProjectnum(HouseProjectEntity.getPinyinCode() == null ? "" : HouseProjectEntity.getPinyinCode());
                RectifyPlan.setCompanyId(HouseProjectEntity.getCompanyId() == null ? "" : HouseProjectEntity.getCompanyId());
                RectifyPlan.setCreateOn(HouseProjectEntity.getCreateOn() == null ? "" : sdf.format(HouseProjectEntity.getCreateOn()));
                RectifyPlan.setInstalment(HouseProjectEntity.getInstalment() == null ? "" : HouseProjectEntity.getInstalment());

                List<DeliveryPlanCrmEntity> DeliveryPlanCrmList=rectificationRepository.getDeliveryPlanCrm(type, (HouseProjectEntity.getPinyinCode() == null ? "" : HouseProjectEntity.getPinyinCode()));//查询批次交付计划
                List<DeliveryPlanJsonDTO> DeliveryPlanJsonList=new ArrayList<DeliveryPlanJsonDTO>();
//                List<DeliveryPlanJsonDTO> DeliveryPlanJsonList=null;
                if(DeliveryPlanCrmList!=null){
                    for(DeliveryPlanCrmEntity DeliveryPlan:DeliveryPlanCrmList){
                        List<RectifyJsonListDTO> RectifyJsonList=new ArrayList<RectifyJsonListDTO>();
                        List<HouseBuildingJSONDTO> HouseBuildingList=new ArrayList<HouseBuildingJSONDTO>();
                        List<HouseUnitJSONDTO> HouseUnitList=new ArrayList<HouseUnitJSONDTO>();
                        List<HouseFloorJSONDTO> HouseFloorList=new ArrayList<HouseFloorJSONDTO>();
                        DeliveryPlanJsonDTO DeliveryPlanJson=new DeliveryPlanJsonDTO();
                        List<Object> HousePlanCRMList=rectificationRepository.gethouseList(DeliveryPlan.getId());//房间
                        if(HousePlanCRMList!=null){
                            for(Object o:HousePlanCRMList){
                                RectifyJsonListDTO RectifyJson=new RectifyJsonListDTO();
                                Object[] object = (Object[])o;
                                RectifyJson.setRoomid(object[0] == null ? "" : object[0].toString());
                                RectifyJson.setRoomName(object[1] == null ? "" : object[1].toString());
                                RectifyJson.setRoomNum(object[2] == null ? "" : object[2].toString());
                                RectifyJson.setFloor(object[3] == null ? "" : object[3].toString());
                                RectifyJson.setFloornum(object[4] == null ? "" : object[4].toString());
                                RectifyJson.setUnit(object[5] == null ? "" : object[5].toString());
                                RectifyJson.setUnitnum(object[6] == null ? "" : object[6].toString());
                                RectifyJson.setBuildingid(object[7] == null ? "" : object[7].toString());
                                RectifyJson.setBuilding(object[8] == null ? "" : object[8].toString());
                                RectifyJson.setBuildingnum(object[9] == null ? "" : object[9].toString());
                                RectifyJson.setPlannum(DeliveryPlan.getPlanNum() == null ? "" : DeliveryPlan.getPlanNum());
                                RectifyJson.setProjectnum(HouseProjectEntity.getPinyinCode() == null ? "" : HouseProjectEntity.getPinyinCode());
                                RectifyJsonList.add(RectifyJson);
                            }
                        }
                        DeliveryPlanJson.setRoomList(RectifyJsonList);
                        List<Object> buildingList=rectificationRepository.gethouseBuildingList(DeliveryPlan.getId());//楼栋
                        if(buildingList!=null){
                            for(Object o:buildingList){
                                HouseBuildingJSONDTO HouseBuild=new HouseBuildingJSONDTO();
                                Object[] object = (Object[])o;
                                HouseBuild.setBuildingnum(object[0] == null ? "" : object[0].toString());
                                HouseBuild.setBuildingid(object[1] == null ? "" : object[1].toString());
                                HouseBuild.setBuildingname(object[2] == null ? "" : object[2].toString());
                                HouseBuild.setBuildingrecode(object[3] == null ? "" : object[3].toString());
                                HouseBuild.setPlannum(DeliveryPlan.getPlanNum() == null ? "" : DeliveryPlan.getPlanNum());
                                HouseBuild.setProjectnum(HouseProjectEntity.getPinyinCode() == null ? "" : HouseProjectEntity.getPinyinCode());
                                HouseBuildingList.add(HouseBuild);
                            }
                        }
                        DeliveryPlanJson.setBuildingList(HouseBuildingList);
                        List<Object> unitList=rectificationRepository.gethouseunitList(DeliveryPlan.getId());//单元
                        if(unitList!=null){
                            for(Object op:unitList){
                                HouseUnitJSONDTO HouseUnit=new HouseUnitJSONDTO();
                                Object[] object = (Object[])op;
                                HouseUnit.setUnit(object[0] == null ? "" : object[0].toString());
                                HouseUnit.setUnitnum(object[1] == null ? "" : object[1].toString());
                                HouseUnit.setBuildingnum(object[2] == null ? "" : object[2].toString());
                                HouseUnit.setPlannum(DeliveryPlan.getPlanNum() == null ? "" : DeliveryPlan.getPlanNum());
                                HouseUnit.setProjectnum(HouseProjectEntity.getPinyinCode() == null ? "" : HouseProjectEntity.getPinyinCode());
                                HouseUnitList.add(HouseUnit);
                            }
                        }
                        DeliveryPlanJson.setUnitList(HouseUnitList);
                        List<Object> floorList=rectificationRepository.gethouseFloorList(DeliveryPlan.getId());//楼层
                        if(floorList!=null){
                            for(Object of:floorList){
                                HouseFloorJSONDTO HouseFloor=new HouseFloorJSONDTO();
                                Object[] object = (Object[])of;
                                HouseFloor.setFloor(object[0] == null ? "" : object[0].toString());
                                HouseFloor.setFloorid(object[1] == null ? "" : object[1].toString());
                                HouseFloor.setFloornum(object[2] == null ? "" : object[2].toString());
                                HouseFloor.setUnitnum(object[3] == null ? "" : object[3].toString());
                                HouseFloor.setPlannum(DeliveryPlan.getPlanNum() == null ? "" : DeliveryPlan.getPlanNum());
                                HouseFloor.setProjectnum(HouseProjectEntity.getPinyinCode() == null ? "" : HouseProjectEntity.getPinyinCode());
                                HouseFloorList.add(HouseFloor);
                            }
                        }
                        DeliveryPlanJson.setFloorList(HouseFloorList);
                        DeliveryPlanJson.setId(DeliveryPlan.getId() == null ? "" : DeliveryPlan.getId());
                        DeliveryPlanJson.setProjectNum(DeliveryPlan.getProjectNum() == null ? "" : DeliveryPlan.getProjectNum());
                        DeliveryPlanJson.setPlanNum(DeliveryPlan.getPlanNum() == null ? "" : DeliveryPlan.getPlanNum());
                        DeliveryPlanJson.setPlanName(DeliveryPlan.getPlanName() == null ? "" : DeliveryPlan.getPlanName());
                        DeliveryPlanJson.setPlanStart(DeliveryPlan.getPlanStart() == null ? "" : sdf.format(DeliveryPlan.getPlanStart()));
                        DeliveryPlanJsonList.add(DeliveryPlanJson);

                    }
                    RectifyPlan.setDeliveryPlanList(DeliveryPlanJsonList);
                }
                RectifyPlanList.add(RectifyPlan);
            }
        }
        return RectifyPlanList;
    }

    @Override
    public List<ThirdTypeJsonDTO> getThirdTypeJson() {
        List<ThirdTypeJsonDTO> ThirdTypeJsonList=new ArrayList<ThirdTypeJsonDTO>();
        List<FirstClassificationEntity> firstClass=rectificationRepository.getfirstClass();
        if(firstClass!=null){
            for(FirstClassificationEntity first:firstClass){
                ThirdTypeJsonDTO ThirdTypeJson=new ThirdTypeJsonDTO();
                ThirdTypeJson.setId(first.getValue() == null ? "" : first.getValue());
                ThirdTypeJson.setName(first.getLabel() == null ? "" : first.getLabel());
                ThirdTypeJson.setDegree("1");
                ThirdTypeJson.setDegreeId("");
                ThirdTypeJsonList.add(ThirdTypeJson);
            }
        }
        List<SecondClassificationEntity> SecondClass=rectificationRepository.getSecondClass();
        if(SecondClass!=null){
            for(SecondClassificationEntity Second:SecondClass){
                ThirdTypeJsonDTO ThirdTypeJson=new ThirdTypeJsonDTO();
                ThirdTypeJson.setId(Second.getValue() == null ? "" : Second.getValue());
                ThirdTypeJson.setName(Second.getLabel() == null ? "" : Second.getLabel());
                ThirdTypeJson.setDegree("2");
                ThirdTypeJson.setDegreeId(Second.getFirstId() == null ? "" : Second.getFirstId());
                ThirdTypeJsonList.add(ThirdTypeJson);
            }
        }
        List<ThirdClassificationEntity> ThirdClass=rectificationRepository.getThirdClass();
        if(ThirdClass!=null){
            for(ThirdClassificationEntity Third:ThirdClass){
                ThirdTypeJsonDTO ThirdTypeJson=new ThirdTypeJsonDTO();
                ThirdTypeJson.setId(Third.getValue() == null ? "" : Third.getValue());
                ThirdTypeJson.setName(Third.getLabel() == null ? "" : Third.getLabel());
                ThirdTypeJson.setDegree("3");
                ThirdTypeJson.setDegreeId(Third.getSecondId() == null ? "" : Third.getSecondId());
                ThirdTypeJsonList.add(ThirdTypeJson);
            }
        }
        List<RepairModeEntity>  RepairModeList=rectificationRepository.getRepairMode();
        if(RepairModeList!=null){
            for(RepairModeEntity RepairMode:RepairModeList){
                ThirdTypeJsonDTO ThirdTypeJson=new ThirdTypeJsonDTO();
                ThirdTypeJson.setId(RepairMode.getValue() == null ? "" : RepairMode.getValue());
                ThirdTypeJson.setName(RepairMode.getLabel() == null ? "" : RepairMode.getLabel());
                ThirdTypeJson.setDegree("4");
                ThirdTypeJson.setDegreeId(RepairMode.getThirdId() == null ? "" : RepairMode.getThirdId());
                ThirdTypeJsonList.add(ThirdTypeJson);
            }
        }
        List<WorkTimeEntity> WorkTimeList=rectificationRepository.getWorkTime();
        if(WorkTimeList!=null){
            for(WorkTimeEntity WorkTime:WorkTimeList){
                ThirdTypeJsonDTO ThirdTypeJson=new ThirdTypeJsonDTO();
                ThirdTypeJson.setId(WorkTime.getValue() == null ? "" : WorkTime.getValue());
                ThirdTypeJson.setName(WorkTime.getLabel() == null ? "" : WorkTime.getLabel());
                ThirdTypeJson.setDegree("5");
                ThirdTypeJson.setDegreeId(WorkTime.getRepairId() == null ? "" : WorkTime.getRepairId());
                ThirdTypeJsonList.add(ThirdTypeJson);
            }
        }
        return ThirdTypeJsonList;
    }

    @Override
    public ApiResult setHouseReception(List<HouseReceptionDTO> HouseReceptionList) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            if(HouseReceptionList.size()>0){
                for(HouseReceptionDTO HouseReception:HouseReceptionList){
                    HouseReceptionEntity HouseReceptionEntity=new HouseReceptionEntity();
                    HouseOpenEntity HouseOpenEntity=new HouseOpenEntity();
                    if(HouseReception.getRoomnum()!=null){
                        HouseReceptionEntity.setRoomnum(HouseReception.getRoomnum());
                        HouseOpenEntity.setHouseCode(HouseReception.getRoomnum());//crm传输数据
                    }
                    if(HouseReception.getDeliveryPlan()!=null){
                        HouseReceptionEntity.setDeliveryPlan(HouseReception.getDeliveryPlan());
                        HouseOpenEntity.setDeliveryPlan(HouseReception.getDeliveryPlan());//crm
                    }
                    if(HouseReception.getRoomid()!=null){
                        HouseReceptionEntity.setRoomid(HouseReception.getRoomid());
//                        HouseOpenEntity.setId(HouseReception.getRoomid());
                    }
                    if(HouseReception.getRoomaddress()!=null){
                        HouseReceptionEntity.setRoomaddress(HouseReception.getRoomaddress());
                    }
                    if(HouseReception.getRectificationspeed()!=null){
                        HouseReceptionEntity.setRectificationspeed(HouseReception.getRectificationspeed());
                    }
                    if(HouseReception.getDatetime()!=null){
                        HouseReceptionEntity.setDatetime(sdf.parse(HouseReception.getDatetime()));
                    }
                    if(HouseReception.getDetailprocessing()!=null){
                        HouseReceptionEntity.setDetailprocessing(HouseReception.getDetailprocessing());
                    }
                    if(HouseReception.getMobile()!=null){
                        HouseReceptionEntity.setMobile(HouseReception.getMobile());
                    }
                    if(HouseReception.getOverallfeeling()!=null){
                        HouseReceptionEntity.setOverallfeeling(HouseReception.getOverallfeeling());
                        //工程质量
                        HouseOpenEntity.setQuality(HouseReception.getOverallfeeling());//crm
                    }
                    if(HouseReception.getOwnername()!=null){
                        HouseReceptionEntity.setOwnername(HouseReception.getOwnername());
                    }
                    if(HouseReception.getOwnersignature()!=null){
                        HouseReceptionEntity.setOwnersignature(HouseReception.getOwnersignature());
                    }
                    if(HouseReception.getServiceattitude()!=null){
                        HouseReceptionEntity.setServiceattitude(HouseReception.getServiceattitude());
                        //服务态度
                        HouseOpenEntity.setSchedulesatisfied(HouseReception.getServiceattitude());//crm
                    }
                    if(HouseReception.getProfessionaldegree()!=null){
                        HouseReceptionEntity.setProfessionaldegree(HouseReception.getProfessionaldegree());
                    }
                    HouseOpenEntity.setCompletedStatus("finish");
                    rectificationRepository.saveHouseReception(HouseReceptionEntity);
                    inspectionService.getInspection(null,HouseOpenEntity,null);
                }
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch(Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    @Override
    public ApiResult setDeliveryList(List<DeliveryInformationDTO> DeliveryList) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            if(DeliveryList.size()>0){
                for(DeliveryInformationDTO DeliveryInformationDTO:DeliveryList){
                    DeliveryInformationEntity DeliveryInformation=new DeliveryInformationEntity();
                    CustomerDeliveryEntity CustomerDeliveryEntity=new CustomerDeliveryEntity();
                    if(DeliveryInformationDTO.getRoomnum()!=null){
                        DeliveryInformation.setRoomnum(DeliveryInformationDTO.getRoomnum());
                        CustomerDeliveryEntity.setHouseCode(DeliveryInformationDTO.getRoomnum());//crm
                    }
                    if(DeliveryInformationDTO.getDeliveryPlan()!=null){
                        DeliveryInformation.setDeliveryPlan(DeliveryInformationDTO.getDeliveryPlan());
                        CustomerDeliveryEntity.setInterdeliveryPla(DeliveryInformationDTO.getDeliveryPlan());//crm
                    }
                    if(DeliveryInformationDTO.getRoomid()!=null){
                        DeliveryInformation.setRoomid(DeliveryInformationDTO.getRoomid());
                        CustomerDeliveryEntity.setId(DeliveryInformationDTO.getRoomid());
                    }
                    if(DeliveryInformationDTO.getRoomaddress()!=null){
                        DeliveryInformation.setRoomaddress(DeliveryInformationDTO.getRoomaddress());
                    }
                    if(DeliveryInformationDTO.getRetainkey()!=null){
                        DeliveryInformation.setRetainkey(DeliveryInformationDTO.getRetainkey());
                    }
                    if(DeliveryInformationDTO.getOverallfeeling()!=null){
                        DeliveryInformation.setOverallfeeling(DeliveryInformationDTO.getOverallfeeling());
                    }
                    if(DeliveryInformationDTO.getOwnername()!=null){
                        DeliveryInformation.setOwnername(DeliveryInformationDTO.getOwnername());
                    }
                    if(DeliveryInformationDTO.getMobile()!=null){
                        DeliveryInformation.setMobile(DeliveryInformationDTO.getMobile());
                    }
                    if(DeliveryInformationDTO.getDeliveryDate()!=null){
                        DeliveryInformation.setDeliveryDate(sdf.parse(DeliveryInformationDTO.getDeliveryDate()));
                        CustomerDeliveryEntity.setEndDate(sdf.parse(DeliveryInformationDTO.getDeliveryDate()));
                    }
                    if(DeliveryInformationDTO.getGastablebase()!=null){
                        DeliveryInformation.setGastablebase(DeliveryInformationDTO.getGastablebase());
                        CustomerDeliveryEntity.setGasMeter(DeliveryInformationDTO.getGastablebase());

                    }
                    if(DeliveryInformationDTO.getGastablenumber()!=null){
                        DeliveryInformation.setGastablenumber(DeliveryInformationDTO.getGastablenumber());
                        CustomerDeliveryEntity.setGasMeterReading(DeliveryInformationDTO.getGastablenumber());

                    }
                    if(DeliveryInformationDTO.getWaterbase()!=null){
                        DeliveryInformation.setWaterbase(DeliveryInformationDTO.getWaterbase());
                        CustomerDeliveryEntity.setWaterMeter(DeliveryInformationDTO.getWaterbase());
                    }
                    if(DeliveryInformationDTO.getWaternumber()!=null){
                        DeliveryInformation.setWaternumber(DeliveryInformationDTO.getWaternumber());
                        CustomerDeliveryEntity.setWaterMeteReadings(DeliveryInformationDTO.getWaternumber());
                    }
                    if(DeliveryInformationDTO.getMeterbase()!=null){
                        DeliveryInformation.setWaterbase(DeliveryInformationDTO.getMeterbase());
                        CustomerDeliveryEntity.setElectricityMeter(DeliveryInformationDTO.getMeterbase());
                    }
                    if(DeliveryInformationDTO.getMeternumber()!=null){
                        DeliveryInformation.setMeternumber(DeliveryInformationDTO.getMeternumber());
                        CustomerDeliveryEntity.setElectricMeterReadings(DeliveryInformationDTO.getMeternumber());
                    }
                    rectificationRepository.saveDeliveryInformation(DeliveryInformation);
                    inspectionService.getInspection(null,null,CustomerDeliveryEntity);
                }
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    @Override
    public ApiResult setInternalList(List<InternalDTO> DeliveryList) throws ParseException {
        try{
            if(DeliveryList.size()>0){
                for(InternalDTO Internal :DeliveryList){
                    InternalacceptanceHouseEntity Internalacce=new InternalacceptanceHouseEntity();
                    Internalacce.setAcceptanceStatus(Internal.getStart() == null ? "" : Internal.getStart());
                    Internalacce.setInterdeliveryPla(Internal.getProjectnum() == null ? "" : Internal.getProjectnum());
                    Internalacce.setHouseCode(Internal.getRoomnum() == null ? "" : Internal.getRoomnum());

                    inspectionService.getInspection(Internalacce, null,null);
                }
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    @Override
    public List<PreInspectionList> getProjectListAllRoom() {
        List<PreInspectionList> PreInspectionList=new ArrayList<PreInspectionList>();
        List<HouseProjectEntity> housList=rectificationRepository.gethousListAll();//获取所有项目信息
        if(housList!=null){
            for(HouseProjectEntity HouseProject:housList){
                PreInspectionList PreInspection=new PreInspectionList();
                PreInspection.setProjectid(HouseProject.getId() == null ? "" : HouseProject.getId());
                String instalment=(HouseProject.getInstalment() == null ? "" : HouseProject.getInstalment());
                PreInspection.setProjectName(HouseProject.getName() == null ? "" : HouseProject.getName()+instalment);
                PreInspection.setCompanyId(HouseProject.getCompanyId() == null ? "" : HouseProject.getCompanyId());
                PreInspection.setInstalment(HouseProject.getInstalment() == null ? "" : HouseProject.getInstalment());
                PreInspection.setProjectNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                //根据项目id获取楼栋信息取消

                List<HouseBuildingEntity> HouseBuildingList=rectificationRepository.gethouseBuilding(HouseProject.getPinyinCode());
                List<BuildingListDTO> BuildingList=new ArrayList<BuildingListDTO>();
                if(HouseBuildingList!=null){
                    for(HouseBuildingEntity HouseBuilding : HouseBuildingList){
                        BuildingListDTO BuildingListDTO=new BuildingListDTO();
                        BuildingListDTO.setBuildingId(HouseBuilding.getId() == null ? "" : HouseBuilding.getId());
                        BuildingListDTO.setBuildingName(HouseBuilding.getName() == null ? "" : HouseBuilding.getBuildingRecord());
                        BuildingListDTO.setBuildingNum(HouseBuilding.getBuildingNum() == null ? "" : HouseBuilding.getBuildingNum());
                        BuildingListDTO.setProjectNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                        //根据buildingID获取单元信息
                        List<HouseUnitEntity> houseUnit=rectificationRepository.gethouseUnit(HouseBuilding.getBuildingNum() == null ? "" : HouseBuilding.getBuildingNum());
                        List<UnitListDTO> UnitList =new ArrayList<UnitListDTO>();
                        if(houseUnit !=null){
                            for(HouseUnitEntity HouseUnit:houseUnit){
                                UnitListDTO UnitListDTO=new UnitListDTO();
                                UnitListDTO.setUnitId(HouseUnit.getId() == null ? "" : HouseUnit.getId());
                                UnitListDTO.setUnitName(HouseUnit.getName() == null ? "" : HouseUnit.getName());
                                UnitListDTO.setUnitNum(HouseUnit.getUnitCode() == null ? "" : HouseUnit.getUnitCode());
                                UnitListDTO.setProjectNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                                List<HouseFloorEntity> HouseFloor=rectificationRepository.getfloorList(HouseUnit.getUnitCode() == null ? "" : HouseUnit.getUnitCode());
                                List<FloorListDTO> FloorList=new ArrayList<FloorListDTO>();
                                if(HouseFloor !=null){
                                    for(HouseFloorEntity HouseFloorEntity:HouseFloor){
                                        FloorListDTO FloorListDTO=new FloorListDTO();
                                        FloorListDTO.setFloorId(HouseFloorEntity.getId() == null ? "" : HouseFloorEntity.getId());
                                        FloorListDTO.setFloorName(HouseFloorEntity.getFloorName() == null ? "" : HouseFloorEntity.getFloorName());
                                        FloorListDTO.setFloorNum(HouseFloorEntity.getFloorCode() == null ? "" : HouseFloorEntity.getFloorCode());
                                        FloorListDTO.setProjectNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                                        List<HouseRoomEntity>  houseRoom=rectificationRepository.gethouseroom(HouseFloorEntity.getFloorCode() == null ? "" : HouseFloorEntity.getFloorCode());
                                        List<RoomListDTO> RoomList=new ArrayList<RoomListDTO>();
                                        if(houseRoom!=null){
                                            for(HouseRoomEntity HouseRoomEntity:houseRoom){
                                                RoomListDTO RoomListDTO=new RoomListDTO();
                                                RoomListDTO.setRoomId(HouseRoomEntity.getId() == null ? "" : HouseRoomEntity.getId());
                                                RoomListDTO.setRoomName(HouseRoomEntity.getName() == null ? "" : HouseRoomEntity.getName());
                                                RoomListDTO.setRoomNum(HouseRoomEntity.getRoomNum() == null ? "" : HouseRoomEntity.getRoomNum());
                                                RoomListDTO.setProjectNum(HouseProject.getPinyinCode() == null ? "" : HouseProject.getPinyinCode());
                                                RoomList.add(RoomListDTO);
                                            }
                                        }
                                        FloorListDTO.setRoomList(RoomList);
                                        FloorList.add(FloorListDTO);
                                    }
                                }
                                UnitListDTO.setFloorList(FloorList);
                                UnitList.add(UnitListDTO);
                            }

                        }
                        BuildingListDTO.setUnitList(UnitList);
                        BuildingList.add(BuildingListDTO);
                    }
                }

                PreInspection.setBuilding(BuildingList);
                PreInspectionList.add(PreInspection);

            }
        }
        PreInspectionJSON.PreInspectionStatic.clear();
        PreInspectionJSON.PreInspectionStatic.addAll(PreInspectionList);
//        PreInspectionStatic.clear();
//        PreInspectionStatic.addAll(PreInspectionList);
        return PreInspectionList;
    }

    @Override
    public List<PreInspectionList> getProjectForUser(String id) {
        List<PreInspectionList> PreInspectionList=new ArrayList<PreInspectionList>();
        if(PreInspectionJSON.PreInspectionStatic!=null && PreInspectionJSON.PreInspectionStatic.size()>0){
            for(PreInspectionList PreInspect:PreInspectionJSON.PreInspectionStatic){
                if(PreInspect.getCompanyId().equals(id)){
                    PreInspectionList.add(PreInspect);
                }else{
                    continue;
                }
            }
        }
        return PreInspectionList;
    }

    @Override
    public List<PreInspectionList> getProjectForPROJECT(String id) {
        List<PreInspectionList> PreInspectionList=new ArrayList<PreInspectionList>();
        if(PreInspectionJSON.PreInspectionStatic!=null && PreInspectionJSON.PreInspectionStatic.size()>0){
            for(PreInspectionList PreInspect:PreInspectionJSON.PreInspectionStatic){
                if(PreInspect.getProjectNum().equals(id)){
//                if(PreInspect.getProjectid().equals(id)){
                    PreInspectionList.add(PreInspect);
                }else{
                    continue;
                }
            }
        }
        return PreInspectionList;
    }

    @Override
    public int setRePieOrder(String id, String username,RePieOrderDTO RePieOrderDTO,String stafName) {
        if(RePieOrderDTO.getRepairId()!=null && !"".equals(RePieOrderDTO.getRepairId()) && RePieOrderDTO.getType()!=null && !"".equals(RePieOrderDTO.getType())){
            if(RePieOrderDTO.getType().equals("0")){ //保修单
                PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(RePieOrderDTO.getRepairId());
                if(propertyRepairInfo!=null){
                    if(RePieOrderDTO.getDepartment()!=null && !"".equals(RePieOrderDTO.getDepartment())){
                        propertyRepairInfo.setDepartment(RePieOrderDTO.getDepartment());
                    }
                    propertyRepairInfo.setModifyDate(DateUtils.getDate());
                    propertyRepairInfo.setState("已受理");
                    PropertyRepairCRMEntity pprcrm=rectificationRepository.getdelepople(RePieOrderDTO.getRepairId());
                    if(pprcrm!=null){
                        pprcrm.setRepairManagerId("");
                        pprcrm.setRepairManager("");
                        pprcrm.setDutyCompanyOneUserId("");
                        pprcrm.setDutyCompanyOneUser("");
                        pprcrm.setDutyCompanyOne("");
                        pprcrm.setDealPeople("");
                        pprcrm.setModifyDate(DateUtils.getDate());
                        if(RePieOrderDTO.getClassifyOne()!=null && !"".equals(RePieOrderDTO.getClassifyOne())){
                            pprcrm.setClassifyOne(RePieOrderDTO.getClassifyOne());
                        }
                        if(RePieOrderDTO.getClassifyTwo()!=null && !"".equals(RePieOrderDTO.getClassifyTwo())){
                            pprcrm.setClassifyTwo(RePieOrderDTO.getClassifyTwo());
                        }
                        if(RePieOrderDTO.getClassifyThree()!=null && !"".equals(RePieOrderDTO.getClassifyThree())){
                            pprcrm.setClassifyThree(RePieOrderDTO.getClassifyThree());
                        }
                        if(RePieOrderDTO.getRepairManagerId()!=null && !"".equals(RePieOrderDTO.getRepairManagerId())){
                            pprcrm.setRepairManagerId(RePieOrderDTO.getRepairManagerId());
                            pprcrm.setDealPeople(RePieOrderDTO.getRepairManagerId());
                            UserPropertyStaffEntity UserPropertyStaff=rectificationRepository.getusername(RePieOrderDTO.getRepairManagerId());
                            pprcrm.setRepairManager(UserPropertyStaff.getUserName());
                        }
                        if(RePieOrderDTO.getSupplierId()!=null && !"".equals(RePieOrderDTO.getSupplierId())){
                            pprcrm.setDutyCompanyOneUserId(RePieOrderDTO.getSupplierId());
                            pprcrm.setDealPeople(RePieOrderDTO.getSupplierId());
                            UserPropertyStaffEntity UserPropertyStaff=rectificationRepository.getusername(RePieOrderDTO.getSupplierId());
                            pprcrm.setDutyCompanyOneUser(UserPropertyStaff.getUserName());
                            pprcrm.setRepairManager(UserPropertyStaff.getUserName());
                        }
                        if(RePieOrderDTO.getDepartment()!=null && !"".equals(RePieOrderDTO.getDepartment())){
                            pprcrm.setDepartment(RePieOrderDTO.getDepartment());
                        }
                        if(RePieOrderDTO.getSupplier()!=null && !"".equals(RePieOrderDTO.getSupplier())){
                            pprcrm.setDutyCompanyOne(RePieOrderDTO.getSupplier());
                        }
                        pprcrm.setState("accepted");
                        pprcrm.setSendName(username);//派单人姓名
                        pprcrm.setSendDate(DateUtils.getDate());//派单时间
                    }
                    repairClientService.getPropertyRepair(pprcrm, null);
                    rectificationRepository.updateProperty(pprcrm);
                    propertyRepairRepository.updateRepair(propertyRepairInfo);
                }
                return 1;
            }else if(RePieOrderDTO.getType().equals("1")){ //整改单
                PropertyRectifyCRMEntity propertyRectifyCRMEntity = propertyRectifyCRMRepository.getById(RePieOrderDTO.getRepairId());
                if(propertyRectifyCRMEntity!=null){
                    propertyRectifyCRMEntity.setRepairManagerId("");
                    propertyRectifyCRMEntity.setRepairManager("");
                    propertyRectifyCRMEntity.setSupplierName("");
                    propertyRectifyCRMEntity.setSupplier("");
                    propertyRectifyCRMEntity.setSupplierID("");
                    propertyRectifyCRMEntity.setDepartment("2");
                    propertyRectifyCRMEntity.setDealPeople("");
                    propertyRectifyCRMEntity.setDealPeopleName("");
                    propertyRectifyCRMEntity.setModifyDate(DateUtils.getDate());
                    propertyRectifyCRMEntity.setSendName(username);//派单人账号
                    propertyRectifyCRMEntity.setSenUserName(stafName);//派单人姓名
                    propertyRectifyCRMEntity.setSendDate(DateUtils.getDate());//派单时间
                    if(RePieOrderDTO.getClassifyOne()!=null && !"".equals(RePieOrderDTO.getClassifyOne())){
                        propertyRectifyCRMEntity.setClassifyOne(RePieOrderDTO.getClassifyOne());
                    }
                    if(RePieOrderDTO.getClassifyTwo()!=null && !"".equals(RePieOrderDTO.getClassifyTwo())){
                        propertyRectifyCRMEntity.setClassifyTwo(RePieOrderDTO.getClassifyTwo());
                    }
                    if(RePieOrderDTO.getClassifyThree()!=null && !"".equals(RePieOrderDTO.getClassifyThree())){
                        propertyRectifyCRMEntity.setClassifyThree(RePieOrderDTO.getClassifyThree());
                    }
                    if(RePieOrderDTO.getRepairManagerId()!=null && !"".equals(RePieOrderDTO.getRepairManagerId())){
                        propertyRectifyCRMEntity.setDealPeople(RePieOrderDTO.getRepairManagerId());
                        propertyRectifyCRMEntity.setRepairManagerId(RePieOrderDTO.getRepairManagerId());
                        UserPropertyStaffEntity UserPropertyStaff=rectificationRepository.getusername(RePieOrderDTO.getRepairManagerId());
                        propertyRectifyCRMEntity.setRepairManager(UserPropertyStaff.getUserName());
                        propertyRectifyCRMEntity.setDealPeopleName(UserPropertyStaff.getStaffName());
                    }
                    if(RePieOrderDTO.getSupplierId()!=null && !"".equals(RePieOrderDTO.getSupplierId())){
                        propertyRectifyCRMEntity.setDealPeople(RePieOrderDTO.getSupplierId());
                        propertyRectifyCRMEntity.setSupplierID(RePieOrderDTO.getSupplierId());
                        UserPropertyStaffEntity UserPropertyStaff=rectificationRepository.getusername(RePieOrderDTO.getSupplierId());
                        propertyRectifyCRMEntity.setSupplierName(UserPropertyStaff.getUserName());
                        propertyRectifyCRMEntity.setDealPeopleName(UserPropertyStaff.getStaffName());
//                        propertyRectifyCRMEntity.setRepairManager(UserPropertyStaff.getUserName());
                    }
                    if(RePieOrderDTO.getDepartment()!=null && !"".equals(RePieOrderDTO.getDepartment())){
                        propertyRectifyCRMEntity.setDepartment(RePieOrderDTO.getDepartment());
                    }
                    if(RePieOrderDTO.getSupplier()!=null && !"".equals(RePieOrderDTO.getSupplier())){
                        propertyRectifyCRMEntity.setSupplier(RePieOrderDTO.getSupplier());
                    }
                    propertyRectifyCRMEntity.setRectifyState("待接单");
                    uploadCRM(propertyRectifyCRMEntity);//上传crm
                    rectificationRepository.updatePropertyRectify(propertyRectifyCRMEntity);
                }
                return 1;
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }

    @Override
    public int ToRePieOrder(UserInformationEntity userInformationEntity,PropertyRectifyAdminDTO propertyRectifyAdminDTO) {
//        private String repairManagerId;//内部负责人id
        RePieOrderDTO rePieOrderDTO = new RePieOrderDTO();
        rePieOrderDTO.setClassifyOne(propertyRectifyAdminDTO.getClassifyOne());
        rePieOrderDTO.setClassifyTwo(propertyRectifyAdminDTO.getClassifyTwo());
        rePieOrderDTO.setClassifyThree(propertyRectifyAdminDTO.getClassifyThree());
        rePieOrderDTO.setType("1");
        rePieOrderDTO.setDepartment("2");
        rePieOrderDTO.setSupplier(propertyRectifyAdminDTO.getSupplier());
        rePieOrderDTO.setSupplierId(propertyRectifyAdminDTO.getSupplierResponsible());
        rePieOrderDTO.setRepairId(propertyRectifyAdminDTO.getRectifyId());
        rePieOrderDTO.setRepairManagerId(propertyRectifyAdminDTO.getInnerPerson());


        return setRePieOrder(userInformationEntity.getStaffId(),userInformationEntity.getUserName(),rePieOrderDTO,userInformationEntity.getStaffName());
    }
    /**
     *
     * 质检app新增报修单 重做接口Magic
     * */
    @Override
    public ApiResult saveRepairMagicAppNew(PropertyRepairAppDTO repair, UserPropertyStaffEntity userPropertyStaffEntity) {
        if (repair == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if (StringUtil.isEmpty(repair.getRoomNum())) {
            return ErrorResource.getError("tip_00000563");//房间号不能为空
        }
        if (StringUtil.isEmpty(repair.getDepartment())) {
            return ErrorResource.getError("tip_00000577");//组信息不能为空
        }
        HouseInfoEntity house=houseInfoRepository.getHouseByRoomId(repair.getRoomNum());

        if(house == null){
            return ErrorResource.getError("tip_00000565");//房屋信息不存在
        }
        if(StringUtil.isEmpty(house.getProjectNum())){
            return ErrorResource.getError("tip_00000565");//房屋信息不存在
        }

        Object[] obj=rectificationRepository.getAllRepairById(repair.getRepairId(), repair.getAppId());
        if(obj!=null){
            //公共方法返回数据
            PropertyRepairToDoMagicDTO repairMagic =returnRepairMagic(null, null, obj, null);
            return new SuccessApiResult(repairMagic);
        }else{
            PropertyRepairCRMEntity propertyRepairCRMEntity=new PropertyRepairCRMEntity();
            String []ss=house.getProjectNum().split("-");
            String repairid=houseInfoRepository.getRepairidNew(ss[ss.length-1]+"-B");
            if(repairid.length()==1){
                repairid="000"+repairid;
            }
            if(repairid.length()==2){
                repairid="00"+repairid;
            }
            if(repairid.length()==3){
                repairid="0"+repairid;
            }
            String id=ss[ss.length-1]+"-B-"+DateUtils.getNow("yyyyMMdd")+"-A-"+repairid;
            propertyRepairCRMEntity.setRepairId(id);    //保修单id
            propertyRepairCRMEntity.setDepartment(repair.getDepartment());//组  必填
            if(!StringUtil.isEmpty(repair.getRoomId())){
                propertyRepairCRMEntity.setRoomId(repair.getRoomId());//房间id
            }else{
                propertyRepairCRMEntity.setRoomId(house.getRoomId());//房间id
            }
            propertyRepairCRMEntity.setRoomNum(repair.getRoomNum());//房间编码   必填
            if(!StringUtil.isEmpty(house.getMemberId())){
                propertyRepairCRMEntity.setMemberId(house.getMemberId());//会员id
            }
            propertyRepairCRMEntity.setUserName(repair.getUserName());//报修人姓名 必填
            propertyRepairCRMEntity.setUserPhone(repair.getUserPhone());//报修人电话 必填
            propertyRepairCRMEntity.setUserAddress(house.getAddress());//报修人地址
            propertyRepairCRMEntity.setCreateDate(new Date());
            propertyRepairCRMEntity.setModifyDate(new Date());  //修改时间
            propertyRepairCRMEntity.setClassifyOne(repair.getClassifyOne());    //一级分类
            propertyRepairCRMEntity.setClassifyTwo(repair.getClassifyTwo());    //二级分类
            propertyRepairCRMEntity.setClassifyThree(repair.getClassifyThree());//三级分类
            propertyRepairCRMEntity.setMode(repair.getMode());      //维修方式
            propertyRepairCRMEntity.setRepairDate(repair.getRepairDate());  //维修工时
            propertyRepairCRMEntity.setRoomLocation(repair.getRoomLocation());
            propertyRepairCRMEntity.setState("accepted");
            propertyRepairCRMEntity.setDuty(repair.getDuty());
            propertyRepairCRMEntity.setProblemLevel(repair.getProblemLevel());
            propertyRepairCRMEntity.setRepairWay("APP");
            propertyRepairCRMEntity.setContent(repair.getContent());    //描述
            propertyRepairCRMEntity.setDealWay(repair.getDealWay());    //处理方案
            propertyRepairCRMEntity.setDealMode(repair.getDealMode());  //处理方式  第三方 内部.......
            propertyRepairCRMEntity.setDutyCompanyOne(repair.getDutyCompanyOne()); //责任单位一
            propertyRepairCRMEntity.setDutyCompanyTwo(repair.getDutyCompanyTwo());//责任单位二
            propertyRepairCRMEntity.setDutyCompanyThree(repair.getDutyCompanyThree());//责任单位三
            propertyRepairCRMEntity.setRepairCompany(repair.getRepairCompany());    //维修单位
            propertyRepairCRMEntity.setxCoordinates((repair.getxCoordinates() == null || "".equals(repair.getxCoordinates())) ? null : new BigDecimal(repair.getxCoordinates()));
            propertyRepairCRMEntity.setyCoordinates((repair.getyCoordinates() == null || "".equals(repair.getyCoordinates())) ? null : new BigDecimal(repair.getyCoordinates()));
            if(!StringUtil.isEmpty(repair.getRepairManagerId())){
                propertyRepairCRMEntity.setSendName(userPropertyStaffEntity.getUserName());
                propertyRepairCRMEntity.setSendDate(new Date());
                propertyRepairCRMEntity.setRepairManagerId(repair.getRepairManagerId());
                propertyRepairCRMEntity.setDealPeople(repair.getRepairManagerId());
                UserPropertyStaffEntity UserStaff=rectificationRepository.getusername(repair.getRepairManagerId());
                if(UserStaff!=null){
                    propertyRepairCRMEntity.setRepairManager(UserStaff.getUserName());
                    propertyRepairCRMEntity.setDealPhone(UserStaff.getMobile());
                }
            }
            propertyRepairCRMEntity.setDealState(repair.getDealState());
            //暂时注释
            propertyRepairCRMEntity.setAppId(repair.getAppId());//校验唯一性
            //整改单图片
            List<RectifyImageDTO> qImageList = repair.getImageList(); //报修完成前list
            if(qImageList!=null && ! qImageList.isEmpty()){
                for(RectifyImageDTO rectifyImageDTO : qImageList){
                    PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                    propertyImageEntity.setImageId(rectifyImageDTO.getImageId());//图片id
                    propertyImageEntity.setUploadDate(new Date());//上传日期
                    propertyImageEntity.setImgFkId(id);//图片外键id
                    propertyImageEntity.setImagePath(rectifyImageDTO.getImageUrl());//图片路径
                    propertyImageEntity.setImageType("0");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                    propertyImageEntity.setState("0");//状态:0为有效；1为无效
                    propertyImageRepository.saveImage(propertyImageEntity);
                }
            }
            try{
                propertyRepairCRMEntity.setFailNum(0);
                propertyRepairCRMEntity.setFailType("0");
                propertyRepairRepository.saveRepairCrm(propertyRepairCRMEntity);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("质检APP调用报修接口:创建报修数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("property_repair_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
                /*************************保修单主表增加*****************************/
                PropertyRepairEntity propertyRepairs=new PropertyRepairEntity();

                propertyRepairs.setRepairId(id);//报修单id
                if(!StringUtil.isEmpty(house.getMemberId())){
                    propertyRepairs.setCreateBy(house.getMemberId());
                }
                if(house!=null) {
                    propertyRepairs.setUserAddress(house.getAddress());//业主地址
                    propertyRepairs.setRegionId(house.getProjectId());//项目id
                    propertyRepairs.setRegionName(house.getProjectName());//项目名称
                }
                if(!StringUtil.isEmpty(repair.getUserName())){
                    propertyRepairs.setUserName(repair.getUserName());//业主姓名
                }
                if(!StringUtil.isEmpty(repair.getUserPhone())){
                    propertyRepairs.setUserPhone(repair.getUserPhone());//业主电话
                }
                propertyRepairs.setUserAddress(house.getAddress());//房屋地址
                if(!StringUtil.isEmpty(repair.getDepartment())) {
                    propertyRepairs.setDepartment(repair.getDepartment());  //组 必填
                }
                if(!StringUtil.isEmpty(repair.getContent())){
                    propertyRepairs.setContent(repair.getContent());    //描述
                }
                if(!StringUtil.isEmpty(repair.getRoomId())) {
                    propertyRepairs.setRoomId(repair.getRoomId());
                }else{
                    propertyRepairs.setRoomId(house.getRoomId());//房间id
                }
                propertyRepairs.setCreateDate(new Date());//创建时间
                propertyRepairs.setModifyDate(new Date()); //修改时间
                propertyRepairs.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                propertyRepairs.setRepairWay("APP");//app
                propertyRepairs.setMemo("报修");
                propertyRepairs.setTaskStatus("0");//0为用户提交报修(业主)
                propertyRepairs.setState("已受理");
                PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                propertyRepairTaskEntity.setRepairId(propertyRepairs.getRepairId());//报修单id
                propertyRepairTaskEntity.setTaskNode("提交报修");//任务节点
                propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                propertyRepairTaskEntity.setTaskName("已创建");
                propertyRepairTaskEntity.setTaskStatus("6");
                propertyRepairTaskEntity.setTaskContent("您的报修信息已受理。");//任务内容
                propertyRepairTaskEntity.setReadStatus("0");//0为未读
                propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);

                propertyRepairRepository.saveRepair(propertyRepairs);
                new Thread() {
                    public void run() {
                        String checkCrmState=repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
                        if(!"200".equals(checkCrmState) && !"".equals(checkCrmState)){
                            //调用接口失败
                            propertyRepairCRMEntity.setFailNum(1);
                            propertyRepairCRMEntity.setFailType("1");
                            propertyRepairRepository.updateRepairCrm(propertyRepairCRMEntity);
                        }
                    }
                }.start();

            }catch (Exception e){
                //错误异常 直接返回数据 调用公共方法
                PropertyRepairToDoMagicDTO repairMagic =returnRepairMagic(repair.getRepairId(), repair.getAppId(), null, null);
                return new SuccessApiResult(repairMagic);
            }
            //成功直接调用公共方法查询返回(需修改)
            PropertyRepairToDoMagicDTO repairMagic =returnRepairMagic(repair.getRepairId(), repair.getAppId(), null, null);
            return new SuccessApiResult(repairMagic);
        }
    }
    /**
     * 根据id 项目编码 time查询增量查询保修单信息 重做magic
     * @param projectNum     项目编码
     * @param time    增量查询判断条件 时间
     * @param id      当前登陆人员工id
     * */
    @Override
    public RepairTodoTimeMagicDTO getAllRepairMagicAppNew(String id, String time, String projectNum,List<String> permis,String userid) {
        List<PropertyRepairToDoMagicDTO> repairMagic=new ArrayList<PropertyRepairToDoMagicDTO>();//具体数据
        RepairTodoTimeMagicDTO repairToDoMagic=new RepairTodoTimeMagicDTO();

        List<Object[]> propertyListNew=rectificationRepository.getAllRepairByApp(id, time, projectNum);
        if(propertyListNew !=null && !propertyListNew.isEmpty()){
            for(Object[] obj : propertyListNew){
                //type 0 + null 通过 详情下载和save返回
                String type="0";
                //如果当前处理人为当前登录人放入代办
                if(obj[15]!=null && obj[15].toString().equals(userid)){
                    type="1";
                }else if(obj[35]!=null && obj[15]==null){
                    for(String per:permis){
                        if(obj[35].toString().equals(per)){
                            type="1";
                            continue;
                        }
                    }
                }
                PropertyRepairToDoMagicDTO repairReturnMagic =returnRepairMagic(null, null, obj,type);
                repairMagic.add(repairReturnMagic);
            }
        }
        if(repairMagic.size()>0){
            repairToDoMagic.setList(repairMagic);
            repairToDoMagic.setId(repairMagic.get(repairMagic.size()-1).getId());
            repairToDoMagic.setTimeStamp(repairMagic.get(repairMagic.size()-1).getModifyDate());
        }
        return repairToDoMagic;
    }

    @Override
    public RepairTodoTimeMagicDTO getRepairMagicTimeNew(List user, String time, String id, String userid) {
        String time1="";
        if(time!=null && !"".equals(time)){
            time1=DateUtils.format(DateUtils.parse(time, "yyyyMMddHHmmss"));
        }
        List<PropertyRepairToDoMagicDTO> repairMagic=new ArrayList<PropertyRepairToDoMagicDTO>();//具体数据
        RepairTodoTimeMagicDTO repairToDoMagic=new RepairTodoTimeMagicDTO();
        List<Object[]> propertyListNew=rectificationRepository.getRepairForTimeNew(user, time1, id,userid);
        if(propertyListNew !=null && !propertyListNew.isEmpty()){
            for(Object[] obj : propertyListNew){
                //type 1 通过代办接口返回数据
                PropertyRepairToDoMagicDTO repairReturnMagic =returnRepairMagic(null, null, obj, "1");
                repairMagic.add(repairReturnMagic);
            }
        }
        if(repairMagic.size()>0){
            repairToDoMagic.setList(repairMagic);
            repairToDoMagic.setId(repairMagic.get(repairMagic.size()-1).getId());
            repairToDoMagic.setTimeStamp(repairMagic.get(repairMagic.size()-1).getModifyDate());
        }
        return repairToDoMagic;
    }

    @Override
    public ReturnJsonDTO updateRepairNew(List<PropertyRepairToDoMagicDTO> repairList, String id, String username) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ReturnJsonDTO ReturnJson=new ReturnJsonDTO();
        try{
            List<String> scuuess=new ArrayList<>();
            List<String> fail=new ArrayList<>();
            for (PropertyRepairToDoMagicDTO repairMagic : repairList){
                PropertyRepairEntity propertyRepairInfo=propertyRepairRepository.getRepairDetail(repairMagic.getRepairId());
                //当前传入报修单数据的最后修改时间与服务器最后修改时间作比较
                if(DateUtils.parse(repairMagic.getModifyDate(),"yyyy-MM-dd HH:mm:ss").compareTo(propertyRepairInfo.getModifyDate())<0){
                    fail.add(propertyRepairInfo.getRepairId());
                }else{
                    PropertyRepairCRMEntity pprcrm=rectificationRepository.getdelepople(repairMagic.getRepairId());
                    //修改报修单:未完成改为未评价
                    if("processing".equals(repairMagic.getState())){
                        propertyRepairInfo.setModifyBy(id);
//                        propertyRepairInfo.setModifyDate(DateUtils.getDate());
                        PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
                        propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                        propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                        propertyRepairTaskEntity.setTaskNode("正在处理中");//任务节点
                        //propertyRepairTaskEntity.setTaskUserId(user.getStaffId());//接单人
                        propertyRepairTaskEntity.setTaskContent(repairMagic.getDealResult());//任务内容
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
                        //保修单图片
                        propertyImageRepository.deleteByFkId(repairMagic.getRepairId(), "2");//先删除
                        List<ProjectReturnImageJsonDTO> qImageList = repairMagic.getImgList();
                        if (qImageList != null && !qImageList.isEmpty()) {
                            for(ProjectReturnImageJsonDTO image:qImageList){
                                PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                                propertyImageEntity.setImageId(image.getImageId());//图片id
                                propertyImageEntity.setUploadDate(new Date());//上传日期
                                if(image.getCaseId()==null || "".equals(image.getCaseId())){
                                    propertyImageEntity.setImgFkId(repairMagic.getRepairId() ==null ? "" : repairMagic.getRepairId());//图片外键id
                                }else{
                                    propertyImageEntity.setImgFkId(image.getCaseId());//图片外键id
                                }
                                propertyImageEntity.setImagePath(image.getImageUrl());//图片路径
                                propertyImageEntity.setImageType("2");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                                propertyImageEntity.setState("0");//状态:0为有效；1为无效
                                propertyImageRepository.saveImage(propertyImageEntity);
                            }
                        }
                        if(!StringUtil.isEmpty(repairMagic.getClassifyOne())){
                            pprcrm.setClassifyOne(repairMagic.getClassifyOne());
                        }
                        if(!StringUtil.isEmpty(repairMagic.getClassifyTwo())){
                            pprcrm.setClassifyTwo(repairMagic.getClassifyTwo());
                        }
                        if(!StringUtil.isEmpty(repairMagic.getClassifyThree())){
                            pprcrm.setClassifyThree(repairMagic.getClassifyThree());
                        }
                        if(!StringUtil.isEmpty(repairMagic.getRepairDate())){
                            pprcrm.setRepairDate(repairMagic.getRepairDate());
                        }
                        if(!StringUtil.isEmpty(repairMagic.getDutyCompanyOne())){
                            pprcrm.setDutyCompanyOne(repairMagic.getDutyCompanyOne());
                        }
                        if(!StringUtil.isEmpty(repairMagic.getDutyCompanyTwo())){
                            pprcrm.setDutyCompanyTwo(repairMagic.getDutyCompanyTwo());
                        }
                        if(!StringUtil.isEmpty(repairMagic.getDutyCompanyThree())){
                            pprcrm.setDutyCompanyThree(repairMagic.getDutyCompanyThree());
                        }
                        if(!StringUtil.isEmpty(repairMagic.getMode())){
                            pprcrm.setMode(repairMagic.getMode());
                        }
                        if(!StringUtil.isEmpty(repairMagic.getDealMode())){
                            pprcrm.setDealMode(repairMagic.getDealMode());
                        }
                        if(!StringUtil.isEmpty(username)){
                            pprcrm.setRepairManager(username);
                        }
                        //处理人为当前人ID
                        pprcrm.setDealPeople(id);
                        if(!StringUtil.isEmpty(repairMagic.getDealPhone())){
                            pprcrm.setDealPhone(repairMagic.getDealPhone());
                        }
                        if(!StringUtil.isEmpty(repairMagic.getDealResult())){
                            pprcrm.setDealWay(repairMagic.getDealResult());
                        }
                        if(!StringUtil.isEmpty(repairMagic.getRepairCompany())){
                            pprcrm.setRepairCompany(repairMagic.getRepairCompany());
                        }
                        //提醒时间
                        if(!StringUtil.isEmpty(repairMagic.getReminderTime())){
                            pprcrm.setReminderTime(DateUtils.parse(repairMagic.getReminderTime(),DateUtils.FORMAT_LONG));
                        }
                        propertyRepairInfo.setModifyBy(id);
                        propertyRepairInfo.setModifyDate(DateUtils.getDate());
                        new Thread() {
                            public void run() {
                                repairClientService.getPropertyRepair(pprcrm, null);
                            }
                        }.start();
//                        repairClientService.getPropertyRepair(pprcrm, null);
                        propertyRepairRepository.updateRepair(propertyRepairInfo);
                        rectificationRepository.updateProperty(pprcrm);
                        propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                        scuuess.add(propertyRepairInfo.getRepairId());
                    }
                    if("completed".equals(repairMagic.getState())){
                        propertyRepairInfo.setState("已完成");//状态由正在处理改为已完成
                        propertyRepairInfo.setModifyBy(id);
                        propertyRepairInfo.setModifyDate(DateUtils.getDate());
                        propertyRepairInfo.setCompleteDate(DateUtils.getDate());
                        //添加任务：新的任务
                        PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                        propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                        propertyRepairTaskEntity.setRepairId(propertyRepairInfo.getRepairId());//报修单id
                        propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                        propertyRepairTaskEntity.setReadStatus("0");
                        //获取报修类型
                        //PropertyRepairTaskEntity repairTaskEntities=rectificationRepository.getRepairTaskById(propertyRepairInfo.getRepairId());
                        List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairInfo.getRepairId());
                        if (repairTaskEntities.size() > 0) {
                            if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//维修
                                propertyRepairInfo.setTaskStatus("10");//10为维修完成
                                propertyRepairTaskEntity.setTaskName("已完成");//任务名称
                                propertyRepairTaskEntity.setTaskNode("维修完成");//任务节点
                                propertyRepairTaskEntity.setTaskStatus("10");//10为维修完成
                                propertyRepairTaskEntity.setTaskContent("您的报修信息已完成。");//任务内容
                            } else if (repairTaskEntities.get(0).getTaskStatus().equals("1")) {//投诉
                                propertyRepairInfo.setTaskStatus("16");//16为回访完成
                                propertyRepairTaskEntity.setTaskName("已解决");//任务名称
                                propertyRepairTaskEntity.setTaskNode("投诉解决");//任务节点
                                propertyRepairTaskEntity.setTaskStatus("16");//16为回访完成
                                propertyRepairTaskEntity.setTaskContent("本次投诉已解决。");//任务内容
                            }
                        }
                        //保修单图片
                        propertyImageRepository.deleteByFkId(repairMagic.getRepairId(), "2");//先删除
                        List<ProjectReturnImageJsonDTO> qImageList = repairMagic.getImgList();
                        if (qImageList != null && !qImageList.isEmpty()) {
                            for(ProjectReturnImageJsonDTO image:qImageList){
                                PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                                propertyImageEntity.setImageId(image.getImageId());//图片id
                                propertyImageEntity.setUploadDate(new Date());//上传日期
                                if(image.getCaseId()==null || "".equals(image.getCaseId())){
                                    propertyImageEntity.setImgFkId(repairMagic.getRepairId() ==null ? "" : repairMagic.getRepairId());//图片外键id
                                }else{
                                    propertyImageEntity.setImgFkId(image.getCaseId());//图片外键id
                                }
                                propertyImageEntity.setImagePath(image.getImageUrl());//图片路径
                                propertyImageEntity.setImageType("2");//图片类型：0为报修;1为投诉;2为维修/处理完成;4为便民信息
                                propertyImageEntity.setState("0");//状态:0为有效；1为无效
                                propertyImageRepository.saveImage(propertyImageEntity);
                            }
                        }
                        propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                        pprcrm.setState("completed");
                        if(repairMagic.getRepairId()!=null){
                            if(!StringUtil.isEmpty(repairMagic.getClassifyOne())){
                                pprcrm.setClassifyOne(repairMagic.getClassifyOne());
                            }
                            if(!StringUtil.isEmpty(repairMagic.getClassifyTwo())){
                                pprcrm.setClassifyTwo(repairMagic.getClassifyTwo());
                            }
                            if(!StringUtil.isEmpty(repairMagic.getClassifyThree())){
                                pprcrm.setClassifyThree(repairMagic.getClassifyThree());
                            }
                            if(!StringUtil.isEmpty(repairMagic.getRepairDate())){
                                pprcrm.setRepairDate(repairMagic.getRepairDate());
                            }
                            if(!StringUtil.isEmpty(repairMagic.getDutyCompanyOne())){
                                pprcrm.setDutyCompanyOne(repairMagic.getDutyCompanyOne());
                            }
                            if(!StringUtil.isEmpty(repairMagic.getDutyCompanyTwo())){
                                pprcrm.setDutyCompanyTwo(repairMagic.getDutyCompanyTwo());
                            }
                            if(!StringUtil.isEmpty(repairMagic.getDutyCompanyThree())){
                                pprcrm.setDutyCompanyThree(repairMagic.getDutyCompanyThree());
                            }
                            if(!StringUtil.isEmpty(repairMagic.getMode())){
                                pprcrm.setMode(repairMagic.getMode());
                            }
                            if(!StringUtil.isEmpty(repairMagic.getDealMode())){
                                pprcrm.setDealMode(repairMagic.getDealMode());
                            }
                            if(!StringUtil.isEmpty(username)){
                                pprcrm.setRepairManager(username);
                            }
                            pprcrm.setDealPeople(id);
                            if(!StringUtil.isEmpty(repairMagic.getRepairCompany())){
                                pprcrm.setRepairCompany(repairMagic.getRepairCompany());
                            }
                            if(!StringUtil.isEmpty(repairMagic.getDealPhone())){
                                pprcrm.setDealPhone(repairMagic.getDealPhone());
                            }
                            if(!StringUtil.isEmpty(repairMagic.getDealResult())){
                                pprcrm.setDealWay(repairMagic.getDealResult());
                            }
                            pprcrm.setDealCompleteDate(DateUtils.getDate());
                            new Thread() {
                                public void run() {
                                    repairClientService.getPropertyRepair(pprcrm, null);
                                }
                            }.start();
//                            repairClientService.getPropertyRepair(pprcrm, null);
                            rectificationRepository.updateProperty(pprcrm);
                            propertyRepairRepository.updateRepair(propertyRepairInfo);
                            scuuess.add(propertyRepairInfo.getRepairId());
                        }
                    }
                }
                ReturnJson.setFail(fail);
                ReturnJson.setSuccess(scuuess);
            }
            return ReturnJson;
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 整改单上传crm
     */
    private void uploadCRM(PropertyRectifyCRMEntity propertyRectifyCRMEntity){
        if(propertyRectifyCRMEntity.getRectifyState() != null && "草稿".equals(propertyRectifyCRMEntity.getRectifyState())){
            return;
        }
        //上传crm，新增或者修改都调用同一个接口
        PropertyRectifyCRMEntity propertyRectify = null;//整改单
        PropertyRepairCRMEntity propertyRepair = null;//保修单
        propertyRectify = new PropertyRectifyCRMEntity();
        propertyRectify.setRectifyId(propertyRectifyCRMEntity.getRectifyId());//整改单号
        propertyRectify.setDepartment(propertyRectifyCRMEntity.getDepartment());//部门
        propertyRectify.setRoomId(propertyRectifyCRMEntity.getRoomId());//房间id
        propertyRectify.setRoomNum(propertyRectifyCRMEntity.getRoomNum());//房间编码
        propertyRectify.setPlanNum(propertyRectifyCRMEntity.getPlanNum());//房间计划编码
        propertyRectify.setAcceptanceDate(propertyRectifyCRMEntity.getAcceptanceDate());//内部预验房日期
        propertyRectify.setProblemType(propertyRectifyCRMEntity.getProblemType());//问题类型
        propertyRectify.setClassifyOne(propertyRectifyCRMEntity.getClassifyOne());//一级分类
        propertyRectify.setClassifyTwo(propertyRectifyCRMEntity.getClassifyTwo());//二级分类
        propertyRectify.setClassifyThree(propertyRectifyCRMEntity.getClassifyThree());//三级分类
        propertyRectify.setCreateDate(propertyRectifyCRMEntity.getCreateDate());//登记日期
//        propertyRectify.setCreateBy(propertyRectifyCRMEntity.getCreateBy());//创建人
        if ("已完成".equals(propertyRectifyCRMEntity.getRectifyState())) {
            propertyRectify.setRectifyState("已完成");
        } else if ("已废弃".equals(propertyRectifyCRMEntity.getRectifyState())) {
            propertyRectify.setRectifyState("已废弃");
        } else if ("处理中".equals(propertyRectifyCRMEntity.getRectifyState())){
            propertyRectify.setRectifyState("处理中");
        } else if ("强制关闭".equals(propertyRectifyCRMEntity.getRectifyState())){
            propertyRectify.setRectifyState("强制关闭");
        } else{
            propertyRectify.setRectifyState("开始");
        }
        if(!StringUtil.isEmpty(propertyRectifyCRMEntity.getRepairManagerId())){
            UserPropertyStaffEntity UserPropertyStaff=rectificationRepository.getusername(propertyRectifyCRMEntity.getRepairManagerId());
            if(UserPropertyStaff!=null){
                if(!StringUtil.isEmpty(UserPropertyStaff.getUserName())){
                    propertyRectify.setRepairManager(UserPropertyStaff.getStaffName());
                }
            }
        }
        if(!StringUtil.isEmpty(propertyRectifyCRMEntity.getSupplierID())){
            UserPropertyStaffEntity UserPropertyStaff=rectificationRepository.getusername(propertyRectifyCRMEntity.getSupplierID());
            if(UserPropertyStaff!=null){
                if(!StringUtil.isEmpty(UserPropertyStaff.getUserName())){
                    propertyRectify.setSupplierName(UserPropertyStaff.getStaffName());
                    propertyRectify.setRepairPhone(UserPropertyStaff.getMobile());
                }
            }
        }
        propertyRectify.setDealPeople(propertyRectifyCRMEntity.getDealPeople());
        propertyRectify.setRoomLocation(propertyRectifyCRMEntity.getRoomLocation());//房屋位置
        propertyRectify.setSupplier(propertyRectifyCRMEntity.getSupplier());//供应商
        propertyRectify.setRectifyCompleteDate(propertyRectifyCRMEntity.getRectifyCompleteDate());//整改完成时间
        propertyRectify.setRealityDate(propertyRectifyCRMEntity.getRealityDate());//实际完成时间
        propertyRectify.setProblemDescription(propertyRectifyCRMEntity.getProblemDescription());//问题描述
        propertyRectify.setDealResult(propertyRectifyCRMEntity.getDealResult());//处理结果
        propertyRectify.setCreateDate(propertyRectifyCRMEntity.getCreateDate());//创建时间
        propertyRectify.setModifyDate(propertyRectifyCRMEntity.getModifyDate());//修改时间
        propertyRectify.setLimitDate(propertyRectifyCRMEntity.getLimitDate());//整改时间
        propertyRectify.setCreateByName(propertyRectifyCRMEntity.getCreateByName());//创建人姓名
        propertyRectify.setUpdateUserName(propertyRectifyCRMEntity.getUpdateUserName());//填报人姓名
        repairClientService.getPropertyRepair(propertyRepair, propertyRectify);
    }

    public PropertyRepairListDTO getrepairByID(String id,String appId,Object[] obj){
        if(obj != null){
            List<ProjectReturnImageJsonDTO> imgJsonList = new ArrayList<>();
            List<ProjectReturnImageJsonDTO> imageJsonList = new ArrayList<>();
            PropertyRepairListDTO rectific = new PropertyRepairListDTO();
            rectific.setType("0");//保修单标识
            rectific.setPlantype("repair");
            rectific.setUptime(obj[0] == null ? "" : DateUtils.format((Date) obj[0]));//最后修改时间
            rectific.setRepairManager(obj[1] == null ? "" : obj[1].toString());//维修负责人
            rectific.setRoomId(obj[2] == null ? "" : obj[2].toString()); //房间id
            rectific.setRoomNum(obj[3] == null ? "" : obj[3].toString());//房间编码
            rectific.setMemberId(obj[4] == null ? "" : obj[4].toString());//报修人会员编号
            rectific.setClassifyOne(obj[5] == null ? "" : obj[5].toString());//一级分类
            rectific.setClassifyTwo(obj[6] == null ? "" : obj[6].toString());//二级分类
            rectific.setClassifyThree(obj[7] == null ? "" : obj[7].toString());//三级分类
            rectific.setMode(obj[8] == null ? "" : obj[8].toString());//维修方式
            rectific.setRepairDate(obj[9] == null ? "" : obj[9].toString());//维修工时
            rectific.setState(obj[10] == null ? "" : obj[10].toString());//订单状态
            rectific.setContent(obj[11] == null ? "" : obj[11].toString());//描述
            rectific.setDutyCompanyOne(obj[12] == null ? "" : obj[12].toString());//责任单位1
            rectific.setDutyCompanyTwo(obj[13] == null ? "" : obj[13].toString());//责任单位2
            rectific.setDutyCompanyThree(obj[14] == null ? "" : obj[14].toString());//责任单位3
            rectific.setDealPeople(obj[15] == null ? "" : obj[15].toString());//处理人
            rectific.setDealPeoplename(obj[16] == null ? "" : obj[16].toString());//处理人姓名
            rectific.setDealMode(obj[17] == null ? "" : obj[17].toString());//处理方式:内部/责任单位/第三方
            rectific.setDealPhone(obj[18] == null ? "" : obj[18].toString());//处理人电话
            rectific.setDealResult(obj[19] == null ? "" : obj[19].toString());//处理结果
            rectific.setDealCompleteDate(obj[20] == null ? "" : DateUtils.format((Date) obj[20]));//处理人完工时间
            rectific.setRoomLocation(obj[21] == null ? "" : obj[21].toString());//报修位置
            rectific.setTaskDate(obj[22] == null ? "" : DateUtils.format((Date) obj[22]));//接单时间
            rectific.setRepairId(obj[23] == null ? "" : obj[23].toString());//报修单号
            rectific.setUserPhone(obj[24] == null ? "" : obj[24].toString());//报修人电话
            rectific.setUserAddress(obj[25] == null ? "" : obj[25].toString());//报修人地址
            rectific.setUsername(obj[26] == null ? "" : obj[26].toString());//报修人姓名
            rectific.setCreateDate(obj[27] == null ? "" : DateUtils.format((Date) obj[27]));//报修时间 登记时间
            rectific.setProjectname(obj[28] == null ? "" : obj[28].toString());//所属项目name
            rectific.setProjectid(obj[29] == null ? "" : obj[29].toString());//项目id
            rectific.setAddress(obj[30] == null ? "" : obj[30].toString());//房屋地址
            rectific.setBuildnum(obj[31] == null ? "" : obj[31].toString());//楼栋编码
            rectific.setProjectNum(obj[32] == null ? "" : obj[32].toString());//所属项目编码
            rectific.setId(obj[33] == null ? "" : obj[33].toString());
            rectific.setRepairCompany(obj[34] == null ? "" : obj[34].toString());
            rectific.setDepartment(obj[35] == null ? "" : obj[35].toString());//部门
            rectific.setRepairManagerId(obj[36] == null ? "" : obj[36].toString());//内部负责人id
            rectific.setSupplierId(obj[37] == null ? "" : obj[37].toString());//责任单位负责人id
            rectific.setxCoordinates(obj[38] == null ? "" : obj[38].toString());
            rectific.setyCoordinates(obj[39] == null ? "" : obj[39].toString());
            rectific.setAppId(obj[40] == null ? "" : obj[40].toString());
            List<PropertyImageEntity> image = rectificationRepository.getImageForId(obj[23] == null ? "" : obj[23].toString());
            if( image!=null) {
                for (PropertyImageEntity imageEntity : image) {
                    ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                    RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId());
                    RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                    RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                    RcitifytDTO.setCaseId(obj[23] == null ? "" : obj[23].toString());
                    imageJsonList.add(RcitifytDTO);
                }
            }
            rectific.setImageList(imageJsonList);//报修前的imageList
            //获取报修后image
            List<PropertyImageEntity> img = rectificationRepository.getImageForOver(obj[23] == null ? "" : obj[23].toString());
            if(img!=null) {
                for (PropertyImageEntity imageEntity : img) {
                    ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                    RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId() );
                    RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                    RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                    RcitifytDTO.setCaseId(obj[23] == null ? "" : obj[23].toString());
                    imgJsonList.add(RcitifytDTO);
                }
            }
            rectific.setImgList(imgJsonList);
            return rectific;
        }else{
            Object[] ob=rectificationRepository.getAllRepairById(id,appId);
            PropertyRepairListDTO rectific = new PropertyRepairListDTO();
            if(ob !=null){
                List<ProjectReturnImageJsonDTO> imgJsonList = new ArrayList<>();
                List<ProjectReturnImageJsonDTO> imageJsonList = new ArrayList<>();
                rectific.setType("0");//保修单标识
                rectific.setPlantype("repair");
                rectific.setUptime(ob[0] == null ? "" : DateUtils.format((Date) ob[0]));//最后修改时间
                rectific.setRepairManager(ob[1] == null ? "" : ob[1].toString());//维修负责人
                rectific.setRoomId(ob[2] == null ? "" : ob[2].toString()); //房间id
                rectific.setRoomNum(ob[3] == null ? "" : ob[3].toString());//房间编码
                rectific.setMemberId(ob[4] == null ? "" : ob[4].toString());//报修人会员编号
                rectific.setClassifyOne(ob[5] == null ? "" : ob[5].toString());//一级分类
                rectific.setClassifyTwo(ob[6] == null ? "" : ob[6].toString());//二级分类
                rectific.setClassifyThree(ob[7] == null ? "" : ob[7].toString());//三级分类
                rectific.setMode(ob[8] == null ? "" : ob[8].toString());//维修方式
                rectific.setRepairDate(ob[9] == null ? "" : ob[9].toString());//维修工时
                rectific.setState(ob[10] == null ? "" : ob[10].toString());//订单状态
                rectific.setContent(ob[11] == null ? "" : ob[11].toString());//描述
                rectific.setDutyCompanyOne(ob[12] == null ? "" : ob[12].toString());//责任单位1
                rectific.setDutyCompanyTwo(ob[13] == null ? "" : ob[13].toString());//责任单位2
                rectific.setDutyCompanyThree(ob[14] == null ? "" : ob[14].toString());//责任单位3
                rectific.setDealPeople(ob[15] == null ? "" : ob[15].toString());//处理人
                rectific.setDealPeoplename(ob[16] == null ? "" : ob[16].toString());//处理人姓名
                rectific.setDealMode(ob[17] == null ? "" : ob[17].toString());//处理方式:内部/责任单位/第三方
                rectific.setDealPhone(ob[18] == null ? "" : ob[18].toString());//处理人电话
                rectific.setDealResult(ob[19] == null ? "" : ob[19].toString());//处理结果
                rectific.setDealCompleteDate(ob[20] == null ? "" : DateUtils.format((Date) ob[20]));//处理人完工时间
                rectific.setRoomLocation(ob[21] == null ? "" : ob[21].toString());//报修位置
                rectific.setTaskDate(ob[22] == null ? "" : DateUtils.format((Date) ob[22]));//接单时间
                rectific.setRepairId(ob[23] == null ? "" : ob[23].toString());//报修单号
                rectific.setUserPhone(ob[24] == null ? "" : ob[24].toString());//报修人电话
                rectific.setUserAddress(ob[25] == null ? "" : ob[25].toString());//报修人地址
                rectific.setUsername(ob[26] == null ? "" : ob[26].toString());//报修人姓名
                rectific.setCreateDate(ob[27] == null ? "" : DateUtils.format((Date) ob[27]));//报修时间 登记时间
                rectific.setProjectname(ob[28] == null ? "" : ob[28].toString());//所属项目name
                rectific.setProjectid(ob[29] == null ? "" : ob[29].toString());//项目id
                rectific.setAddress(ob[30] == null ? "" : ob[30].toString());//房屋地址
                rectific.setBuildnum(ob[31] == null ? "" : ob[31].toString());//楼栋编码
                rectific.setProjectNum(ob[32] == null ? "" : ob[32].toString());//所属项目编码
                rectific.setId(ob[33] == null ? "" : ob[33].toString());
                rectific.setRepairCompany(ob[34] == null ? "" : ob[34].toString());
                rectific.setDepartment(ob[35] == null ? "" : ob[35].toString());//部门
                rectific.setRepairManagerId(ob[36] == null ? "" : ob[36].toString());//内部负责人id
                rectific.setSupplierId(ob[37] == null ? "" : ob[37].toString());//责任单位负责人id
                rectific.setxCoordinates(ob[38] == null ? "" : ob[38].toString());
                rectific.setyCoordinates(ob[39] == null ? "" : ob[39].toString());
                rectific.setAppId(ob[40] == null ? "" : ob[40].toString());
                List<PropertyImageEntity> image = rectificationRepository.getImageForId(ob[23] == null ? "" : ob[23].toString());
                if( image!=null) {
                    for (PropertyImageEntity imageEntity : image) {
                        ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                        RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId());
                        RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                        RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                        RcitifytDTO.setCaseId(ob[23] == null ? "" : ob[23].toString());
                        imageJsonList.add(RcitifytDTO);
                    }
                }
                rectific.setImageList(imageJsonList);//报修前的imageList
                //获取报修后image
                List<PropertyImageEntity> img = rectificationRepository.getImageForOver(ob[23] == null ? "" : ob[23].toString());
                if(img!=null) {
                    for (PropertyImageEntity imageEntity : img) {
                        ProjectReturnImageJsonDTO RcitifytDTO = new ProjectReturnImageJsonDTO();
                        RcitifytDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId() );
                        RcitifytDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                        RcitifytDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                        RcitifytDTO.setCaseId(ob[23] == null ? "" : ob[23].toString());
                        imgJsonList.add(RcitifytDTO);
                    }
                }
                rectific.setImgList(imgJsonList);

            }
            return rectific;
        }
    }
    public PropertyRepairToDoMagicDTO returnRepairMagic(String id,String appId,Object[] obj,String type){
        if(obj != null){
            List<ProjectReturnImageJsonDTO> imgJsonList = new ArrayList<>();
            List<ProjectReturnImageJsonDTO> imageJsonList = new ArrayList<>();
            PropertyRepairToDoMagicDTO repair = new PropertyRepairToDoMagicDTO();
            repair.setModifyDate(obj[0] == null ? "" : DateUtils.format((Date) obj[0]));//最后修改时间
            repair.setRepairManager(obj[1] == null ? "" : obj[1].toString());//维修负责人
            repair.setRoomId(obj[2] == null ? "" : obj[2].toString()); //房间id
            repair.setRoomNum(obj[3] == null ? "" : obj[3].toString());//房间编码
            repair.setMemberId(obj[4] == null ? "" : obj[4].toString());//报修人会员编号
            repair.setClassifyOne(obj[5] == null ? "" : obj[5].toString());//一级分类
            repair.setClassifyTwo(obj[6] == null ? "" : obj[6].toString());//二级分类
            repair.setClassifyThree(obj[7] == null ? "" : obj[7].toString());//三级分类
            repair.setMode(obj[8] == null ? "" : obj[8].toString());//维修方式
            repair.setRepairDate(obj[9] == null ? "" : obj[9].toString());//维修工时
            repair.setState(obj[10] == null ? "" : obj[10].toString());//订单状态
            repair.setContent(obj[11] == null ? "" : obj[11].toString());//描述
            repair.setDutyCompanyOne(obj[12] == null ? "" : obj[12].toString());//责任单位1
            repair.setDutyCompanyTwo(obj[13] == null ? "" : obj[13].toString());//责任单位2
            repair.setDutyCompanyThree(obj[14] == null ? "" : obj[14].toString());//责任单位3
            repair.setDealPeople(obj[15] == null ? "" : obj[15].toString());//处理人
            repair.setDealPeoplename(obj[16] == null ? "" : obj[16].toString());//处理人姓名
            repair.setDealMode(obj[17] == null ? "" : obj[17].toString());//处理方式:内部/责任单位/第三方
            repair.setDealPhone(obj[18] == null ? "" : obj[18].toString());//处理人电话
            repair.setDealResult(obj[19] == null ? "" : obj[19].toString());//处理结果
            repair.setDealCompleteDate(obj[20] == null ? "" : DateUtils.format((Date) obj[20]));//处理人完工时间
            repair.setRoomLocation(obj[21] == null ? "" : obj[21].toString());//报修位置
            repair.setTaskDate(obj[22] == null ? "" : DateUtils.format((Date) obj[22]));//接单时间
            repair.setRepairId(obj[23] == null ? "" : obj[23].toString());//报修单号
            repair.setUserPhone(obj[24] == null ? "" : obj[24].toString());//报修人电话
            repair.setUserAddress(obj[25] == null ? "" : obj[25].toString());//报修人地址
            repair.setUserName(obj[26] == null ? "" : obj[26].toString());//报修人姓名
            repair.setCreateDate(obj[27] == null ? "" : DateUtils.format((Date) obj[27]));//报修时间 登记时间
            repair.setProjectName(obj[28] == null ? "" : obj[28].toString());//所属项目name
            repair.setProjectId(obj[29] == null ? "" : obj[29].toString());//项目id
            repair.setAddress(obj[30] == null ? "" : obj[30].toString());//房屋地址
            repair.setBuildNum(obj[31] == null ? "" : obj[31].toString());//楼栋编码
            repair.setProjectNum(obj[32] == null ? "" : obj[32].toString());//所属项目编码
            repair.setId(obj[33] == null ? "" : obj[33].toString());
            repair.setRepairCompany(obj[34] == null ? "" : obj[34].toString());
            repair.setDepartment(obj[35] == null ? "" : obj[35].toString());//部门
            repair.setRepairManagerId(obj[36] == null ? "" : obj[36].toString());//内部负责人id
            repair.setSupplierId(obj[37] == null ? "" : obj[37].toString());//责任单位负责人id
            repair.setxCoordinates(obj[38] == null ? "" : obj[38].toString());
            repair.setyCoordinates(obj[39] == null ? "" : obj[39].toString());
            repair.setAppId(obj[40] == null ? "" : obj[40].toString());
            repair.setReminderTime(obj[41] == null ? "" : DateUtils.format((Date) obj[41]));
            if(!StringUtil.isEmpty(type) && "1".equals(type)){
                repair.setDeType("1");
            }else{
                repair.setDeType("0");
            }
            List<PropertyImageEntity> image = rectificationRepository.getImageForId(obj[23] == null ? "" : obj[23].toString());
            if( image!=null) {
                for (PropertyImageEntity imageEntity : image) {
                    ProjectReturnImageJsonDTO repairDTO = new ProjectReturnImageJsonDTO();
                    repairDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId());
                    repairDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                    repairDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                    repairDTO.setCaseId(obj[23] == null ? "" : obj[23].toString());
                    imageJsonList.add(repairDTO);
                }
            }
            repair.setImageList(imageJsonList);//报修前的imageList
            //获取报修后image
            List<PropertyImageEntity> img = rectificationRepository.getImageForOver(obj[23] == null ? "" : obj[23].toString());
            if(img!=null) {
                for (PropertyImageEntity imageEntity : img) {
                    ProjectReturnImageJsonDTO repairDTO = new ProjectReturnImageJsonDTO();
                    repairDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId() );
                    repairDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                    repairDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                    repairDTO.setCaseId(obj[23] == null ? "" : obj[23].toString());
                    imgJsonList.add(repairDTO);
                }
            }
            repair.setImgList(imgJsonList);
            return repair;
        }else{
            Object[] ob=rectificationRepository.getAllRepairById(id,appId);
            PropertyRepairToDoMagicDTO repair = new PropertyRepairToDoMagicDTO();
            if(ob !=null){
                List<ProjectReturnImageJsonDTO> imgJsonList = new ArrayList<>();
                List<ProjectReturnImageJsonDTO> imageJsonList = new ArrayList<>();
                repair.setModifyDate(ob[0] == null ? "" : DateUtils.format((Date) ob[0]));//最后修改时间
                repair.setRepairManager(ob[1] == null ? "" : ob[1].toString());//维修负责人
                repair.setRoomId(ob[2] == null ? "" : ob[2].toString()); //房间id
                repair.setRoomNum(ob[3] == null ? "" : ob[3].toString());//房间编码
                repair.setMemberId(ob[4] == null ? "" : ob[4].toString());//报修人会员编号
                repair.setClassifyOne(ob[5] == null ? "" : ob[5].toString());//一级分类
                repair.setClassifyTwo(ob[6] == null ? "" : ob[6].toString());//二级分类
                repair.setClassifyThree(ob[7] == null ? "" : ob[7].toString());//三级分类
                repair.setMode(ob[8] == null ? "" : ob[8].toString());//维修方式
                repair.setRepairDate(ob[9] == null ? "" : ob[9].toString());//维修工时
                repair.setState(ob[10] == null ? "" : ob[10].toString());//订单状态
                repair.setContent(ob[11] == null ? "" : ob[11].toString());//描述
                repair.setDutyCompanyOne(ob[12] == null ? "" : ob[12].toString());//责任单位1
                repair.setDutyCompanyTwo(ob[13] == null ? "" : ob[13].toString());//责任单位2
                repair.setDutyCompanyThree(ob[14] == null ? "" : ob[14].toString());//责任单位3
                repair.setDealPeople(ob[15] == null ? "" : ob[15].toString());//处理人
                repair.setDealPeoplename(ob[16] == null ? "" : ob[16].toString());//处理人姓名
                repair.setDealMode(ob[17] == null ? "" : ob[17].toString());//处理方式:内部/责任单位/第三方
                repair.setDealPhone(ob[18] == null ? "" : ob[18].toString());//处理人电话
                repair.setDealResult(ob[19] == null ? "" : ob[19].toString());//处理结果
                repair.setDealCompleteDate(ob[20] == null ? "" : DateUtils.format((Date) ob[20]));//处理人完工时间
                repair.setRoomLocation(ob[21] == null ? "" : ob[21].toString());//报修位置
                repair.setTaskDate(ob[22] == null ? "" : DateUtils.format((Date) ob[22]));//接单时间
                repair.setRepairId(ob[23] == null ? "" : ob[23].toString());//报修单号
                repair.setUserPhone(ob[24] == null ? "" : ob[24].toString());//报修人电话
                repair.setUserAddress(ob[25] == null ? "" : ob[25].toString());//报修人地址
                repair.setUserName(ob[26] == null ? "" : ob[26].toString());//报修人姓名
                repair.setCreateDate(ob[27] == null ? "" : DateUtils.format((Date) ob[27]));//报修时间 登记时间
                repair.setProjectName(ob[28] == null ? "" : ob[28].toString());//所属项目name
                repair.setProjectId(ob[29] == null ? "" : ob[29].toString());//项目id
                repair.setAddress(ob[30] == null ? "" : ob[30].toString());//房屋地址
                repair.setBuildNum(ob[31] == null ? "" : ob[31].toString());//楼栋编码
                repair.setProjectNum(ob[32] == null ? "" : ob[32].toString());//所属项目编码
                repair.setId(ob[33] == null ? "" : ob[33].toString());
                repair.setRepairCompany(ob[34] == null ? "" : ob[34].toString());
                repair.setDepartment(ob[35] == null ? "" : ob[35].toString());//部门
                repair.setRepairManagerId(ob[36] == null ? "" : ob[36].toString());//内部负责人id
                repair.setSupplierId(ob[37] == null ? "" : ob[37].toString());//责任单位负责人id
                repair.setxCoordinates(ob[38] == null ? "" : ob[38].toString());
                repair.setyCoordinates(ob[39] == null ? "" : ob[39].toString());
                repair.setAppId(ob[40] == null ? "" : ob[40].toString());
                if(!StringUtil.isEmpty(type)){
                    repair.setDeType("1");
                }else{
                    repair.setDeType("0");
                }
                List<PropertyImageEntity> image = rectificationRepository.getImageForId(ob[23] == null ? "" : ob[23].toString());
                if( image!=null) {
                    for (PropertyImageEntity imageEntity : image) {
                        ProjectReturnImageJsonDTO repairDTO = new ProjectReturnImageJsonDTO();
                        repairDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId());
                        repairDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                        repairDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                        repairDTO.setCaseId(ob[23] == null ? "" : ob[23].toString());
                        imageJsonList.add(repairDTO);
                    }
                }
                repair.setImageList(imageJsonList);//报修前的imageList
                //获取报修后image
                List<PropertyImageEntity> img = rectificationRepository.getImageForOver(ob[23] == null ? "" : ob[23].toString());
                if(img!=null) {
                    for (PropertyImageEntity imageEntity : img) {
                        ProjectReturnImageJsonDTO  repairDTO = new ProjectReturnImageJsonDTO();
                        repairDTO.setImageId(imageEntity.getImageId() == null ? "" : imageEntity.getImageId() );
                        repairDTO.setImageUrl(imageEntity.getImagePath() == null ? "" : imageEntity.getImagePath());
                        repairDTO.setImage(imageEntity.getBinaryStream() == null ? "" : imageEntity.getBinaryStream());
                        repairDTO.setCaseId(ob[23] == null ? "" : ob[23].toString());
                        imgJsonList.add(repairDTO);
                    }
                }
                repair.setImgList(imgJsonList);

            }
            return repair;
        }
    }

}
